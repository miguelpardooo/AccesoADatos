package booksconsax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BooksSAXhandler extends DefaultHandler {
    
    private int contadorLibro = 0;
    private boolean primerElemento = true;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (primerElemento) {
            System.out.println("\tcatalog\t\n");
            primerElemento = false;
        }
        
        if (qName.equals("book")) {
            contadorLibro++;
            System.out.println("book nº " + contadorLibro + " - ID: " + atts.getValue(atts.getQName(0))); // extrae el primer atributo
        } else if (qName.equals("title")) {
            System.out.print("\n " + "The title is: "); 
        } else if (qName.equals("author")) {
            System.out.print("\n" + "The author is: ");
        } else if (qName.equals("genre")) {
            System.out.print("\n " + "The genre is: "); 
        } else if (qName.equals("price")) {
            System.out.print("\n" + "The price is: ");
        } else if (qName.equals("publish_date")) {
            System.out.print("\n " + "The publish date is: "); 
        } else if (qName.equals("description")) {
            System.out.print("\n" + "The description is: ");
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("book")) {
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
