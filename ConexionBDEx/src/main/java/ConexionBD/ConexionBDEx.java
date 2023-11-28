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

//No funciona, no sé por qué
public class ConexionBDEx {

    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "miguel";
    static final String PASS = "1234";
    static PoolDataSource pds;
    
    public static void main(String[] args) {
        
        try {
            
            pds = PoolDataSourceFactory.getPoolDataSource();            
            pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
            pds.setURL(DB_URL);
            pds.setUser(USER);
            pds.setPassword(PASS);
            pds.setInitialPoolSize(5);
            
            System.out.println(buscaNombre("Pokemon XD"));
            //System.out.println(lanzaConsulta("SELECT * FROM videojuegos"));
            //nuevoRegistro("Pokemon X", "Videojuego de aventuras", Date.valueOf("2011-09-11"), "Nintendo", 59.95);
            //nuevoRegistro();
            //System.out.println(eliminarRegistro("Pokemon X"));
  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean buscaNombre(String nombre) {
        String query = "SELECT * FROM videojuegos WHERE Nombre = ?";
        
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
        
        try (Connection conn = pds.getConnection();) {

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, genero);
            stmt.setDate(3, fechalanzamiento);
            stmt.setString(4, compania);
            stmt.setDouble(5, precio);
            int rowsAffected = stmt.executeUpdate();
            
            System.out.println("\nInserción exitosa.");
            
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void nuevoRegistro(PoolDataSource pds) throws ParseException {
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
