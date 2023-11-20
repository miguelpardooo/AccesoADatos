package jcvd;

import java.sql.*;

public class javajcvd {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "miguel";
    static final String PASS = "1234";
    //static final String QUERY = "SELECT * FROM videojuegos";
    

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            /*Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            
            while(rs.next()) {
                System.out.println("ID: " + "\t\t" + rs.getInt("id"));
                System.out.println("Nombre: " + "\t" + rs.getString("nombre"));
                System.out.println("Género: " + "\t" + rs.getString("genero"));
                System.out.println("Fecha: " + "\t\t" + rs.getDate("fechalanzamiento"));
                System.out.println("Compañía: " + "\t" + rs.getString("compania"));
                System.out.println("Precio: " + "\t" + rs.getFloat("precio") + "\n");
            }*/
            
            String insertQuery = "INSERT INTO videojuegos (nombre, genero, fechalanzamiento, compania, precio) " +
                                 "VALUES ('The Legend of Zelda: Majoras Mask', 'Videojuego de acción', '2000-04-27', 'Nintendo', 150.00)";
            
            try (Statement stmt = conn.createStatement()) {
                // Ejecutar la inserción
                stmt.executeUpdate(insertQuery);
                
                System.out.println("Inserción exitosa.");
            }
            
            /*int idRegistroEliminar = 116;
            
            String deleteQuery = "DELETE FROM videojuegos WHERE id = ?";

            // Preparar la declaración
            try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                pstmt.setInt(1, idRegistroEliminar);

                // Ejecutar la operación DELETE
                int filasAfectadas = pstmt.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Eliminación exitosa. Filas afectadas: " + filasAfectadas);
                } else {
                    System.out.println("No se encontró el registro con ID: " + idRegistroEliminar);
                }
            }*/
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
