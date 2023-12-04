package abrirxmlconsax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TitulosSAXhandler extends DefaultHandler {

    private String etiqueta;

    public TitulosSAXhandler() {
        etiqueta = ""; // también se puede hacer con int etiqueta
    }

    @Override
    public void startDocument() {// abre el documento
        System.out.println("LISTADO DE TITULOS");
        System.out.println("==================");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {// abre el elemento
        if (qName.equals("Libro")) {
            etiqueta = "Libro";
        } else if (qName.equals("Titulo")) {
            etiqueta = "Titulo";
        } else if (qName.equals("Autor")) {
            etiqueta = "Autor";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (etiqueta.equals("Titulo")) {
            String car = new String(ch, start, length);
            car = car.replaceAll("\t", ""); // quita todos los tabuladores
            car = car.replaceAll("\n", "");
            System.out.print(car);
        }
        
        if (etiqueta.equals("Autor")) {
            String car = new String(ch, start, length);
            car = car.replaceAll("\t", ""); // quita todos los tabuladores
            car = car.replaceAll("\n", "");
            System.out.println(car);
        }
    }
}