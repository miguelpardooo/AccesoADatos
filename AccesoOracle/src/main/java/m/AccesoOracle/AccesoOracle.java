package m.AccesoOracle;

import java.sql.*;

public class AccesoOracle {
	
	private Connection con;
	
	// Método que establece la conexión a la base de datos de Oracle
	void abrirConexion() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYS AS SYSDBA","1234");
			
			System.out.println("Conexion OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Método que cierra la conexión de la base de datos de Oracle
	void cerrarConexion() {
		try {
			System.out.println("\nConexion cerrada");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Muestra los registros de la tabla contactos
	void mostrarContactos() {
		try {
			// Creamos el statment y la consulta
			Statement st = con.createStatement();
			ResultSet resul = st.executeQuery("SELECT c.nombre, c.telefono FROM contactos c");
			
			System.out.println("\nINFORMACION DE CONTACTOS");
			System.out.println("------------------------");
			
			// Recorre la tabla y muestra los datos
			while (resul.next()) {
				System.out.printf("NOMBRE: %s\nTELEFONO: %s",resul.getString(1),resul.getString(2));
			}
			
			System.out.println("\n------------------------");
			resul.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void crearTablaMisAlumnos() {
	    try {
	        Statement st = con.createStatement();
	        
	        // Crea la tabla misAlumnos con el tipo ESTUDIANTE
	        String query = "CREATE TABLE misAlumnos OF estudiante";
	        
	        st.executeUpdate(query);

	        System.out.println("\nTabla misAlumnos creada exitosamente.");
	        
	        st.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	void insertarEstudiante(String estudianteId, String nombre, String telefono) {
	    try {
	        // Creamos la consulta con PreparedStatement
	        String query = "INSERT INTO misAlumnos(id_estudiante, datos_personales) VALUES (?, persona(?, ?))";
	        PreparedStatement ps = con.prepareStatement(query);

	        // Establecemos los valores de los parámetros
	        ps.setString(1, estudianteId);
	        ps.setString(2, nombre);
	        ps.setString(3, telefono);

	        // Ejecutamos la inserción
	        int filasAfectadas = ps.executeUpdate();

	        if (filasAfectadas > 0) {
	            System.out.println("\nEstudiante insertado en la tabla misAlumnos correctamente.");
	        } else {
	            System.out.println("\nError al insertar estudiante en la tabla misAlumnos.");
	        }

	        // Cerramos el PreparedStatement
	        ps.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	void borrarAlumnoPorNombre(String nombreAlumno) {
	    try {
	        // Creamos la consulta con PreparedStatement
	        String query = "DELETE FROM misAlumnos a WHERE a.datos_personales.nombre = ?";
	        PreparedStatement ps = con.prepareStatement(query);

	        // Establecemos el valor del parámetro
	        ps.setString(1, nombreAlumno);

	        // Ejecutamos la eliminación
	        int filasAfectadas = ps.executeUpdate();

	        if (filasAfectadas > 0) {
	            System.out.println("\nAlumno eliminado de la tabla misAlumnos correctamente.");
	        } else {
	            System.out.println("\nNo se encontró ningún alumno con el nombre especificado.");
	        }

	        // Cerramos el PreparedStatement
	        ps.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public String buscarTelefonoPorNombre(String nombreAlumno) {
		try {
	        // Creamos la consulta con PreparedStatement
	        String query = "SELECT a.id_estudiante, a.datos_personales.nombre AS nombre, a.datos_personales.telefono AS telefono "
                    + "FROM misAlumnos a WHERE a.datos_personales.nombre = ?";
	        PreparedStatement ps = con.prepareStatement(query);

	        // Establecemos el valor del parámetro
	        ps.setString(1, nombreAlumno);

	        // Ejecutamos la consulta
	        ResultSet resultSet = ps.executeQuery();

	        if (resultSet.next()) {
	            String telefono = resultSet.getString("telefono");
	            System.out.printf("\nEl teléfono de %s es: %s\n", nombreAlumno, telefono);
	            return telefono;
	        } else {
	            System.out.printf("\nNo se encontró ningún alumno con el nombre %s.\n", nombreAlumno);
	            return null;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
