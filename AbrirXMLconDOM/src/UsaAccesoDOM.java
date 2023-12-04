
import java.io.File;

public class UsaAccesoDOM {

    public static void main(String[] args) {
        AccesoDOM a = new AccesoDOM();//creamos un objeto AccesoDOM con el que trabajar
        File f = new File("books.xml");//debemos escribir la ruta correcta para poder pasarle nuestro archivo a los métodos
        a.abriXMLaDOM(f);//abrimos el archivo para poder trabajar con él
        a.insertarLibroEnDOM("Yerma", "Lorca", "1935");//insertamos el nodo con los parámetros introducidos
        a.deleteNode("Don Quijote");//borramos el nodo con el título especificado
        a.recorreDOMyMuestra();//recorremos y mostramos el contenido del archivo
        a.guardarDOMcomoArchivo("LibrosDeDOM.xml");//guardamos el archivo
        
    }
    
}
