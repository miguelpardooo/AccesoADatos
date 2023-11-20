package booksxml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

public class book {
    
    private String author;

    private String title;
    
    private String genre;
    
    private double price;
    
    private String publish_date;
    
    private String description;
    
    private String id;

    public book() {
    }

    public book(String author, String title, String genre, double price, String publish_date, String description, String id) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publish_date = publish_date;
        this.description = description;
        this.id = id;
    }
    
    @XmlElement(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @XmlElement(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlElement(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @XmlElement(name = "publish_date")
    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

}
