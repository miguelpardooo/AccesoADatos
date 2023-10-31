
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import java.util.*;
import java.io.*;

public class AccesoDOM {

    Document doc;

    public int abriXMLaDOM(File f) {
        try {
            System.out.println("Abriendo archivo XML file y generando DOM....");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(f);

            System.out.println("DOM creado con éxito.");
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public void recorreDOMyMuestra() {
        String[] datos = new String[3];
        Node nodo = null;
        Node root = doc.getFirstChild();
        NodeList nodelist = root.getChildNodes();
        
        System.out.println("");

        for (int i = 0; i < nodelist.getLength(); i++) {
            nodo = nodelist.item(i);
            if(nodo.getNodeType()== Node.ELEMENT_NODE){
                
                Node ntemp = null;
                int contador = 1;
                
                datos[0] = nodo.getAttributes().item(0).getNodeValue();
                
                NodeList nl2 = nodo.getChildNodes();
                for(int j = 0;j < nl2.getLength(); j++){
                    ntemp = nl2.item(j);
                    if(ntemp.getNodeType() == Node.ELEMENT_NODE){
                        datos[contador] = ntemp.getTextContent();
                        contador++;
                    }
                }
                
                System.out.println(datos[0] + " -- " + datos[2] + " -- " + datos[1]);
            }
        }
    }
}
