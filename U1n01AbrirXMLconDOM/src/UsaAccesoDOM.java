
import java.io.File;

public class UsaAccesoDOM {

    public static void main(String[] args) {
        AccesoDOM a = new AccesoDOM();
        File f = new File("Libros.xml");
        a.abriXMLaDOM(f);
        a.recorreDOMyMuestra();
    }
    
}
