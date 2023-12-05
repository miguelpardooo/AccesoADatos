package ConexionBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;


public class ConexionBD {

    //establecemos los datos de conexión a la base de datos
    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "miguel";
    static final String PASS = "1234";
    
    public static void main(String[] args) {
        
        try {
            //mostramos el resultado de nuestras operaciones
            
            //System.out.println(buscaNombre("jeje"));
            System.out.println(lanzaConsulta("SELECT * FROM videojuegos"));
            //nuevoRegistro("Pokemon XD", "Videojuego de aventuras", new Date(2005, 7, 9), "Nintendo", 59.95);
            //nuevoRegistro();
            //System.out.println(eliminarRegistro("jeje"));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //este método nos devuelve true o false dependiendo de si el nombre pasado por parámetro está en nuestra base de datos o no
    public static boolean buscaNombre(String nombre) {
        String nombreQuery = "SELECT * FROM videojuegos WHERE Nombre = '" + nombre + "'";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(nombreQuery);) {

            boolean existeNombre = false;

            //recorremos la tabla en búsqueda del nombre
            while (rs.next()) {
                String nombreJuego = rs.getString("Nombre"); 
                if (nombreJuego.equals(nombre)) {
                    existeNombre = true;//si éste existe, devolvemos true
                }
            }
            
            conn.close();
            
            return existeNombre;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        
    }
    
    //este método devuelve una cadena que será el reusltado de la consulta que le pasaremos por parámetro
    public static String lanzaConsulta(String consulta) {
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(consulta)) {
            
            //con esto pretendemos obtener los datos de la estructura de la tabla para trabajar con ella
            ResultSetMetaData metaData = rs.getMetaData();
            //obtenemos el número de columnas de la tabla
            int columnCount = metaData.getColumnCount();
            
            //almacenamos el resultado
            StringBuilder resultado = new StringBuilder();

            //recorremos la tabla hasta que no haya más columnas
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String valor = rs.getString(i);
                    //añadimos la información al resultado
                    resultado.append(metaData.getColumnName(i)).append(": ").append(valor).append("\t");
                }
                resultado.append("\n");
            }

            conn.close();
            
            //devolvemos el resultado de la consulta como string
            return resultado.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al ejecutar la consulta.";
        }
        
    }
    
    //este método nos permite introducir un nuevo registro a la tabla con los datos introducidos por parámetro
    public static void nuevoRegistro(String nombre, String genero, Date fechalanzamiento, String compania, double precio) {
        //definimos la insert que queremos realizar
        String insertQuery = "INSERT INTO videojuegos (nombre, genero, fechalanzamiento, compania, precio) " +
                                 "VALUES ('" + nombre + "', '" + genero + "', '" + fechalanzamiento + "', '" + compania + "', " + precio + ")";
        //creamos la conexión
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //creamos el statement
            Statement stmt = conn.createStatement();) {

            //ejecutamos el statement
            stmt.executeUpdate(insertQuery);
            //mostramos un mensaje para indicar que se realizó la inserción correctamente, si no se muestra sabemos que no lo hizo
            System.out.println("Inserción exitosa.");
            
            //cerramos la conexión para evitar errores
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //este método nos permite introducir un nuevo registro a la tabla con los datos introducidos manualmente por el usuario
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

        String insertQuery = "INSERT INTO videojuegos (nombre, genero, fechalanzamiento, compania, precio) " +
                                 "VALUES ('" + nombre + "', '" + genero + "', '" + fechalanzamiento + "', '" + compania + "', " + precio + ")";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();) {

            stmt.executeUpdate(insertQuery);
            System.out.println("\nInserción exitosa.");
            
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //este metodo elimina el registro de la tabla que le pasamos por parámetro
    public static boolean eliminarRegistro(String nombre) {
        String deleteQuery = "DELETE FROM videojuegos WHERE Nombre = '" + nombre + "'";
        
        
        if (buscaNombre(nombre)) {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();) {
            
                stmt.executeUpdate(deleteQuery);

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
