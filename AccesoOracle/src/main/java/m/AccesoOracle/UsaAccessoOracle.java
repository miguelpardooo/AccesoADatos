package m.AccesoOracle;

public class UsaAccessoOracle {
	public static void main(String[] args) {
		
		AccesoOracle a = new AccesoOracle();
		a.abrirConexion();
		
		
		//a.mostrarContactos();
		//a.crearTablaMisAlumnos();
		//a.insertarEstudiante("03C","Manolo Diaz","967333333");
		//a.borrarAlumnoPorNombre("Manolo Diaz");
		String telefono = a.buscarTelefonoPorNombre("Pepe López");
		
		
		a.cerrarConexion();
		
	}
}
