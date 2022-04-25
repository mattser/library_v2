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

        bookRepo.printBooks();

        UserRepository userRepo = new UserRepository();

        userRepo.printUsers();

        LoanRepository loanRepo = new LoanRepository();

        loanRepo.printLoans();



    }
}
