package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        BookRepository bookRepo = new BookRepository();

        bookRepo.printBooks();
    }
}
