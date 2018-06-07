package com.javafx.libassistant.beans;
        
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Book {

    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final StringProperty publisher = new SimpleStringProperty();
    private final BooleanProperty isAvail = new SimpleBooleanProperty();

    //Constructor
    public Book(){}
    public Book(String title, String id, String author, String publ, Boolean avail){
        super();
        this.setTitle(title);
        this.setAuthor(author);
        this.setId(id);
        this.setPublisher(publ);
        this.setAvailability(avail);
    }

    //Getters & Setters SimpleProperty
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public void setPublisher(String publ) {
        this.publisher.set(publ);
    }

    public Boolean getAvailability() {
        return isAvail.get();
    }

    public void setAvailability(Boolean avail) {
        this.isAvail.set(avail);
    }

    /*
    //Getters & Setters Property
    public StringProperty getPropertyTitle() {
        return title;
    }

    public void setPropertyTitle(StringProperty title) {
        this.title = title;
    }

    public StringProperty getPropertyId() {
        return id;
    }

    public void setPropertyId(StringProperty id) {
        this.id = id;
    }

    public StringProperty getPropertyAuthor() {
        return author;
    }

    public void setPropertyAuthor(StringProperty author) {
        this.author = author;
    }

    public StringProperty getPropertyPublisher() {
        return publisher;
    }

    public void setPropertyPublisher(StringProperty publisher) {
        this.publisher = publisher;
    }

    public BooleanProperty getPropertyAvailability() {
        return availability;
    }

    public void setPropertyAvailability(BooleanProperty avail) {
        this.availability = avail;
    }
    */
    @Override
    public String toString(){
        return this.getTitle().concat(" "+this.getId()+" ").concat(" "+this.getAuthor()+" ").concat(" "+this.getPublisher()+" ").concat(this.getAvailability().toString());
    }

}