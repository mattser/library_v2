package org.example;

public class Book {

    private int bookID;
    private String title;
    private String author;
    private String genre;
    private String subGenre;
    private String publisher;

    public Book(int bookID, String title, String author, String genre, String subGenre, String publisher) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.subGenre = subGenre;
        this.publisher = publisher;
    }

    public Book() {
    }

    public Book (String[] data) {
        this.bookID = Integer.parseInt(data[0]);
        this.title = data[1];
        this.author = data[2];
        this.genre = data[3];
        this.subGenre = data[4];
        this.publisher = data[5];
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
            return "[ID: " + bookID +
                    " | Title: " + title +
                    " | Author: " + author +
                    " | Genre: " + genre + ", "+ subGenre +
                    " | Publisher: " + publisher + "]";
    }
}
