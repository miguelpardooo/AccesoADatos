package booksxml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        try {
            File file = new File("books.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(catalog.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            catalog catalog1 = (catalog) jaxbUnmarshaller.unmarshal(file);

            // Ahora puedes acceder a la lista de libros
            List<book> books1 = catalog1.getBooks();
            System.out.println("<catalog>");
            for (book book : books1) {
                System.out.println("\t<book id=\"" + book.getId() +"\">");
                System.out.println("\t\t<author>" + book.getAuthor() + "</author>");
                System.out.println("\t\t<title>" + book.getTitle() + "</title>");
                System.out.println("\t\t<genre>" + book.getGenre() + "</genre>");
                System.out.println("\t\t<price>" + book.getPrice() + "</price>");
                System.out.println("\t\t<publish_date>" + book.getPublish_date() + "</publish_date>");
                System.out.println("\t\t<description>" + book.getDescription() + "</description>");
                System.out.println("\t</book>");
                System.out.println();
            }
            System.out.println("</catalog>");
 
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
