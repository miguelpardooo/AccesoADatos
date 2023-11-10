package abrirxmlconsax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class LibrosSAXhandler extends DefaultHandler {
    
    private int contadorLibro = 0;
    private boolean primerElemento = true;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (primerElemento) {
            System.out.println("\tLISTADO DE LIBROS\t\n");
            primerElemento = false;
        }
        
        if (qName.equals("Libro")) {
            contadorLibro++;
            System.out.println("Libro nº " + contadorLibro + " - Publicado en: " + atts.getValue(atts.getQName(0))); // extrae el primer atributo
        } else if (qName.equals("Titulo")) {
            System.out.print("\n " + "El título es: "); // aún no sabemos cuál es el título, eso lo sabremos en el evento characters
        } else if (qName.equals("Autor")) {
            System.out.print("\n " + "El autor es: ");
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Libro")) {
            System.out.println("\n-----------------------");
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String car = new String(ch, start, length);
        car = car.replaceAll("\t", ""); // quita todos los tabuladores
        car = car.replaceAll("\n", "");
        System.out.print(car);
    }
}
