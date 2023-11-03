
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
        System.out.println("<Libros>");

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
                
                //System.out.println(datos[0] + " -- " + datos[2] + " -- " + datos[1]);
                
                System.out.println("\t<Libro publicado=\"" + datos[0] + "\">");
                System.out.println("\t\t<Titulo>" + datos[1] + "</Titulo>");
                System.out.println("\t\t<Autor>" + datos[2] + "</Autor>");
                System.out.println("\t</Libro>");
            }
        }
        
        System.out.println("</Libros>");
    }
    
    public int insertarLibroEnDOM(String titulo, String autor,String fecha) {
        try {
            System.out.println("Añadir libro al árbol DOM: \"" + titulo + "\"; " + autor + "; " +fecha);
            
            Node ntitulo = doc.createElement("Titulo");
            Node ntitulo_text = doc.createTextNode(titulo);
            ntitulo.appendChild(ntitulo_text);
            
            Node nautor = doc.createElement("Autor");
            Node nautor_text=doc.createTextNode(autor);
            nautor.appendChild(nautor_text);
            
            Node nLibro=doc.createElement("Libro");
            ((Element)nLibro).setAttribute("publicado", fecha);
            nLibro.appendChild(ntitulo);
            nLibro.appendChild(nautor);
            
            nLibro.appendChild(doc.createTextNode("\n"));
            Node raiz = doc.getFirstChild();
            raiz.appendChild(nLibro);
            System.out.println("Libro insertado en DOM.");
            
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }
    
    public int deleteNode(String tit) {
    System.out.println("Buscando el Libro \"" + tit + "\" para borrarlo");
    
        try {
            Node raiz = doc.getDocumentElement();
            NodeList nl1 = doc.getElementsByTagName("Titulo");
            Node n1;
            for (int i = 0; i < nl1.getLength(); i++) {
                n1 = nl1.item(i);
                if (n1.getNodeType() == Node.ELEMENT_NODE) {
                    if (n1.getChildNodes().item(0).getNodeValue().equals(tit)) {
                        System.out.println("Borrando el nodo <Libro> con título " + tit);
                        n1.getParentNode().getParentNode().removeChild(n1.getParentNode());
                    }
                }
            }
            System.out.println("Nodo borrado");
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return -1;
        }
    }
}
