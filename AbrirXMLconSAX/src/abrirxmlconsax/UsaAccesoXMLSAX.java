package abrirxmlconsax;

import java.io.File;

public class UsaAccesoXMLSAX {

    public static void main(String[] args) {
        File f=new File("Libros.xml");
        AccesoXMLSAX a =new AccesoXMLSAX();
        //a.parsearXMLconLibrosSAXhandler(f);
        a.parsearXMLconTitulosSAXhandler(f);
    }
    
}
