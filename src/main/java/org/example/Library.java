package org.example;

import java.util.Scanner;

public class Library {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    public Library() {
        this.bookRepository = new BookRepository();
        this.loanRepository = new LoanRepository();
        this.userRepository = new UserRepository();
    }

    public Library(BookRepository bookRepository, LoanRepository loanRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    public void start() {

    }

    private void authenticate() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please Type your User Name");
    }

}
