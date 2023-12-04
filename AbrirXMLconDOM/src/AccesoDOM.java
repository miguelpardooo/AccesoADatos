
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import java.util.*;
import java.io.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class AccesoDOM {

    Document doc;

    public int abriXMLaDOM(File f) {
        try {
            System.out.println("Abriendo archivo XML file y generando DOM....");

            //creamos nuevo objeto DocumentBuilder al que apunta la variable factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);

            //DocumentBuilder tiene el método parse que es el que genera DOM en memoria
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(f);

            // ahora doc apunta al arbol DOM y podemos recorrerlo
            System.out.println("DOM creado con éxito.");
            return 0;//si el método funciona
        } catch (Exception e) {
            System.out.println(e);
            return -1;//if the method aborta en algún punto
        }
    }

    public void recorreDOMyMuestra() {
        String[] datos = new String[3];
        Node nodo = null;
        Node root = doc.getFirstChild();
        NodeList nodelist = root.getChildNodes();
        
        System.out.println("");
        System.out.println("<catalog>");

        //recorrer el árbol DOM. El 1er nivel de nodos hijos del raíz
        for (int i = 0; i < nodelist.getLength(); i++) {
            nodo = nodelist.item(i);//node toma el valor de los hijos de raíz
            if(nodo.getNodeType()== Node.ELEMENT_NODE){//miramos nodos de tipo Element
                
                Node ntemp = null;
                int contador = 1;
                //sacamos el valor del atributo publicado
                datos[0] = nodo.getAttributes().item(0).getNodeValue();
                //sacamos los valores de los hijos de nodo, Titulo y Autor
                NodeList nl2 = nodo.getChildNodes();//obtenemos la lista de hijos (2)
                for(int j = 0;j < nl2.getLength(); j++){//iteramos en esa lista
                    ntemp = nl2.item(j);
                    if(ntemp.getNodeType() == Node.ELEMENT_NODE){
                        //para conseguir el texto de titulo y autor, se
                        //puedo hacer con getNodeValue(), también con
                        //getTextContent() si es ELEMENT
                        datos[contador] = ntemp.getTextContent();
                        // también datos[contador]=ntemp.getChildNodes().item(0).getNodeValue();
                        contador++;
                    }
                }
                //el array de String datos[] tiene los valores que necesitamos
                //diseñamos la estructura XML para que lo muestre de forma más vistosa
                System.out.println("\t<book id=\"" + datos[0] + "\">");
                System.out.println("\t\t<author>" + datos[1] + "</author>");
                System.out.println("\t\t<title>" + datos[2] + "</title>");
                System.out.println("\t</book>");
            }
        }
        
        System.out.println("</catalog>");
    }
    
    public int insertarLibroEnDOM(String titulo, String autor,String fecha) {
        try {
            System.out.println("Añadir libro al árbol DOM: " + titulo + "; " + autor + "; " +fecha);
            //crea los nodos=&gt;los añade al padre desde las hojas a la raíz
            
            //CREAMOS TITULO
            Node ntitulo = doc.createElement("Titulo");
            Node ntitulo_text = doc.createTextNode(titulo);
            ntitulo.appendChild(ntitulo_text);
            
            //CREAMOS AUTOR
            Node nautor = doc.createElement("Autor");
            Node nautor_text=doc.createTextNode(autor);
            nautor.appendChild(nautor_text);
            
            //CREAMOS LIBRO
            Node nLibro=doc.createElement("Libro");
            ((Element)nLibro).setAttribute("publicado", fecha);
            nLibro.appendChild(ntitulo);
            nLibro.appendChild(nautor);
            
            //APPEND LIBRO A LA RAÍZ
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
            
            //recorremos el arborl buscando el elemento que deseamos
            for (int i = 0; i < nl1.getLength(); i++) {
                n1 = nl1.item(i);
                if (n1.getNodeType() == Node.ELEMENT_NODE) {
                    if (n1.getChildNodes().item(0).getNodeValue().equals(tit)) {
                        System.out.println("Borrando el nodo <Libro> con título " + tit);
                        //Una vez lo hemos encontrado, lo eliminamos
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
    
    public void guardarDOMcomoArchivo(String nuevoArchivo) {
        try {
            Source src = new DOMSource(doc); // Definimos el origen
            StreamResult rst = new StreamResult(new File(nuevoArchivo)); // Definimos el resultado

            // Declaramos el Transformer que tiene el método .transform() que necesitamos.
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            // Opción para indentar el archivo
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(src, rst);
            System.out.println("Archivo creado del DOM con éxito\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
