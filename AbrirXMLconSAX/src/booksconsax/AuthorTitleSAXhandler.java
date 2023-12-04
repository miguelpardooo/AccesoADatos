package booksconsax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AuthorTitleSAXhandler extends DefaultHandler {
    private String etiqueta;
    private StringBuilder currentText;

    public AuthorTitleSAXhandler() {
        etiqueta = "";
        currentText = new StringBuilder();
    }

    @Override
    public void startDocument() {
        System.out.println("AUTHOR & TITLE");
        System.out.println("==================");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        etiqueta = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentText.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("author")) {
            String author = currentText.toString().trim();
            System.out.println("Author: " + author);
        } else if (qName.equals("title")) {
            String title = currentText.toString().trim();
            System.out.println("Title: " + title);
            System.out.println("----------------------");
        }
        // Limpiar el StringBuilder después de procesar una etiqueta
        currentText.setLength(0);
    }
}
