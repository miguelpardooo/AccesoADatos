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

    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "miguel";
    static final String PASS = "1234";
    
    public static void main(String[] args) {
        
        try {
            //System.out.println(buscaNombre("jeje"));
            System.out.println(lanzaConsulta("SELECT * FROM videojuegos"));
            //nuevoRegistro("Pokemon XD", "Videojuego de aventuras", new Date(2005, 7, 9), "Nintendo", 59.95);
            //nuevoRegistro();
            //System.out.println(eliminarRegistro("jeje"));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean buscaNombre(String nombre) {
        String nombreQuery = "SELECT * FROM videojuegos WHERE Nombre = '" + nombre + "'";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(nombreQuery);) {

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
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(consulta)) {
            
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
        String insertQuery = "INSERT INTO videojuegos (nombre, genero, fechalanzamiento, compania, precio) " +
                                 "VALUES ('" + nombre + "', '" + genero + "', '" + fechalanzamiento + "', '" + compania + "', " + precio + ")";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();) {

            stmt.executeUpdate(insertQuery);
            System.out.println("Inserción exitosa.");
            
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
