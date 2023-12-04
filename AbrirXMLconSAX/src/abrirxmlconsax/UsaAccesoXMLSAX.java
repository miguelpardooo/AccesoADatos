package abrirxmlconsax;

import java.io.File;

public class UsaAccesoXMLSAX {

    public static void main(String[] args) {
        File f=new File("Libros.xml");
        AccesoXMLSAX a =new AccesoXMLSAX();//creamos la instancia de nuestra clase para poder llamar los métodos
        //a.parsearXMLconLibrosSAXhandler(f);
        a.parsearXMLconTitulosSAXhandler(f);
    }
    
}
