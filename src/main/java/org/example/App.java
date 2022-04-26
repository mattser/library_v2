package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        BookRepository bookRepo = new BookRepository();

        UserRepository userRepo = new UserRepository();

        LoanRepository loanRepo = new LoanRepository();

        Library library = new Library(bookRepo,loanRepo,userRepo);

        library.start();
    }
}
