package org.example;

import java.sql.Timestamp;

public class Loan {

    private Book book;
    private User user;
    private Timestamp outDate;
    private Timestamp dueDate;
    private Boolean returned;

    public Loan() {
    }

    public Loan(Book book, User user, Timestamp outDate, Timestamp dueDate, Boolean returned) {
        this.book = book;
        this.user = user;
        this.outDate = outDate;
        this.dueDate = dueDate;
        this.returned = returned;
    }

    public Loan(Book book, User user) {
        this.book = book;
        this.user = user;
        this.outDate = new Timestamp(System.currentTimeMillis());
        this.dueDate = new Timestamp(System.currentTimeMillis() + 60*60*24*7*1000);
        this.returned = false;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getOutDate() {
        return outDate;
    }

    public void setOutDate(Timestamp outDate) {
        this.outDate = outDate;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return ((returned) ? "RETURNED" : "OUT") + " [ BookID: " + book.getBookID() +
                " | Title: " + book.getTitle() +
                " | Borrower: " + user.getUserName() +
                " | Taken Out: " + outDate +
                " | Due Back: " + dueDate + " ]";
    }
}
