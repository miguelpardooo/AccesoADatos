package booksconsax;

import abrirxmlconsax.AccesoXMLSAX;
import java.io.File;

public class UsaBooksAccesoXMLSAX {

    public static void main(String[] args) {
        File f=new File("books.xml");
        BooksAccesoXMLSAX a =new BooksAccesoXMLSAX();
        //a.parsearXMLconBooksSAXhandler(f);
        a.parsearXMLAuthorTitleSAXhandler(f);
    }
    
}
