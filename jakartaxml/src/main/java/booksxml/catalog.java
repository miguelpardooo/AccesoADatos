package booksxml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class catalog {
    
    @XmlElement(name = "book")
    private List<book> booksList = null;

    public List<book> getBooks() {
        return booksList;
    }

    public void setBooks(List<book> books) {
        this.booksList = books;
    }
    
}
