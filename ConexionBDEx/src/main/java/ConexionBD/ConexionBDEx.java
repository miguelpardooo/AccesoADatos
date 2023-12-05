package ConexionBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.PoolDataSource;

public class ConexionBDEx {

    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "miguel";
    static final String PASS = "1234";
    
    //creamos el pool para trabajar con él en todo el documento
    static PoolDataSource pds;
    
    public static void main(String[] args) {
        
        try {
            
            //establecemos el pool de conexiones que vamos a usar, que tendrá un tamaño inicial de 5
            pds = PoolDataSourceFactory.getPoolDataSource();            
            pds.setConnectionFactoryClassName("com.mysql.cj.jdbc.Driver");
            pds.setURL(DB_URL);
            pds.setUser(USER);
            pds.setPassword(PASS);
            pds.setInitialPoolSize(5);
            
            //System.out.println(buscaNombre("Pokemon XD"));
            System.out.println(lanzaConsulta("SELECT * FROM videojuegos"));
            //nuevoRegistro("Pokemon X", "Videojuego de aventuras", Date.valueOf("2011-09-11"), "Nintendo", 59.95);
            //nuevoRegistro();
            //System.out.println(eliminarRegistro("Pokemon X"));
  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean buscaNombre(String nombre) {
        String query = "SELECT * FROM videojuegos WHERE Nombre = ?";
        
        //creamos la conexión y la obtenemos del pool de conexiones
        try (Connection conn = pds.getConnection();) {
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            boolean existeNombre = false;

            while (rs.next()) {
                String nombreJuego = rs.getString("Nombre"); 
                if (nombreJuego.equals(nombre)) {
                    existeNombre = true;
                }
            }
            
            conn.close();
            
            return existeNombre;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        
    }
    
    public static String lanzaConsulta(String consulta) {
        
        try (Connection conn = pds.getConnection()) {
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            StringBuilder resultado = new StringBuilder();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String valor = rs.getString(i);
                    resultado.append(metaData.getColumnName(i)).append(": ").append(valor).append("\t");
                }
                resultado.append("\n");
            }

            conn.close();
            
            return resultado.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al ejecutar la consulta.";
        }
        
    }
    
    public static void nuevoRegistro(String nombre, String genero, Date fechalanzamiento, String compania, double precio) {
        String query = "INSERT INTO videojuegos (nombre, genero, fechalanzamiento, compania, precio) " +
                                 "VALUES (?, ?, ?, ?, ?)";
        //vamos a usar un prepared statement, por lo que no es necesario escribir todos los paramétros, pues hacemos un binding con todos ellos
        
        //creamos una conexion con el pool data source
        try (Connection conn = pds.getConnection();) {
            
            //creamos un prepared statement apra ejecutar la consulta
            PreparedStatement stmt = conn.prepareStatement(query);
            //establecemos los valores de los parámetros
            stmt.setString(1, nombre);
            stmt.setString(2, genero);
            stmt.setDate(3, fechalanzamiento);
            stmt.setString(4, compania);
            stmt.setDouble(5, precio);
            //ejecutamos el prepared statment y almacenamos el número de filas afectadas por si fuera necesario en caso de errores
            int rowsAffected = stmt.executeUpdate();
            //mostramos un mensaje para indicar que se realizó la inserción correctamente, si no se muestra sabemos que no lo hizo
            System.out.println("\nInserción exitosa.");
            
            //cerramos la conexión para evitar errores
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void nuevoRegistro() throws ParseException {
        Scanner teclado = new Scanner(System.in);
        
        String nombre, genero, fechalanzamiento, compania;
        double precio;
        
        System.out.println("Inserta un nuevo videojuego: ");
        System.out.print("\t- Nombre: ");
        nombre = teclado.nextLine();
        System.out.print("\t- Género: ");
        genero = teclado.nextLine();
        System.out.print("\t- Fecha de lanzamiento (yyyy-MM-dd): ");
        fechalanzamiento = teclado.nextLine();
        System.out.print("\t- Compañía: ");
        compania = teclado.nextLine();
        System.out.print("\t- Precio: ");
        precio = teclado.nextDouble();

        String query = "INSERT INTO videojuegos (nombre, genero, fechalanzamiento, compania, precio) " +
                                 "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = pds.getConnection();) {

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, genero);
            stmt.setString(3, fechalanzamiento);
            stmt.setString(4, compania);
            stmt.setDouble(5, precio);
            int rowsAffected = stmt.executeUpdate();
            
            System.out.println("\nInserción exitosa.");
            
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean eliminarRegistro(String nombre) {
        String query = "DELETE FROM videojuegos WHERE Nombre = ?";
        
        
        if (buscaNombre(nombre)) {
            try (Connection conn = pds.getConnection();) {
            
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, nombre);
                int rowsAffected = stmt.executeUpdate();

                conn.close();
                return true;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("El nombre introducido no existe en la base de datos.");
            return false;
        }
        
        
    }
}
