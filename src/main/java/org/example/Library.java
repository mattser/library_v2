package org.example;

import java.util.List;
import java.util.Locale;
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
        authenticate();
        run();
        shutDown();
    }

    private void authenticate() {
        Scanner s = new Scanner(System.in);
        boolean authed = false;
        String name = null;
        while(!authed) {
            System.out.println("Please Type Enter Your User Name: ");
            name = s.next();
            if (userRepository.checkForUser(name)) {
                authed = true;

            } else {
                System.out.println(name + " is currently not a user. Would you like to make a new user? (y)");
                String confirm = s.next();
                if (confirm.toLowerCase().equals("y")) {
                    userRepository.addData(new User(name));
                    authed = true;
                }

            }

        }
        userRepository.setActiveUser(name);

    }

    private void run() {
        boolean running = true;
        Scanner s = new Scanner(System.in);
        int userInput;
        while(running) {

            System.out.println("Welcome " + userRepository.getActiveUser().getUserName() + "!");
            System.out.println("Select From the Options Below:");
            System.out.println("1. Show All Books");
            if (!userRepository.getActiveUser().isAdmin()) {
                System.out.println("2. Check Your Current Loans");
                System.out.println("3. Loan a Book");
                System.out.println("4. Return a Book");
            } else {
                System.out.println("2. See Loan History");
                System.out.println("3. See Active Loans");
                System.out.println("4. See Book Popularity");
                System.out.println("5. Save Loan Reports to CSV File");
            }
            System.out.println("0. Log Off");

            userInput = s.nextInt();

            if (userInput == 1) {
                bookRepository.printBooks();
            } else if (userInput == 0) {
                running = false;
                System.out.println("Thank you for using this service!");
            } else if (userInput == 2 && !userRepository.getActiveUser().isAdmin()) {
                loanRepository.activeLoans(userRepository.getActiveUser());
            } else if (userInput == 3 && !userRepository.getActiveUser().isAdmin()) {

                System.out.println("Books Available to Loan:");
                bookRepository.printBooks(loanRepository.getActiveLoanBookIDs());
                System.out.println("Type the Book ID from the list available books:");
                userInput = s.nextInt();

                if (loanRepository.getActiveLoanBookIDs().contains(userInput) || !bookRepository.checkForBookByID(userInput)) {
                    System.out.println("Invalid Selection");
                } else  {
                    loanRepository.addData(new Loan(bookRepository.getBookById(userInput),userRepository.getActiveUser()));
                    System.out.println("Created New Loan");
                }

            } else if (userInput == 4 && !userRepository.getActiveUser().isAdmin()) {
                loanRepository.activeLoans(userRepository.getActiveUser());
                System.out.println("Type the BookID you wish to Return:");
                userInput = s.nextInt();

                loanRepository.returnBook(userRepository.getActiveUser(),bookRepository.getBookById(userInput));


            } else if (userInput == 2) {
                loanRepository.printLoans(false);
            } else if (userInput == 3) {
                loanRepository.printLoans(true);
            } else if (userInput == 4) {
                loanRepository.printBookPopularity();
            } else if (userInput == 5 && userRepository.getActiveUser().isAdmin()) {
                loanRepository.writeCSVData();
            } else {
                System.out.println("Invalid Input");
            }
        }
    }

    private void shutDown() {
        loanRepository.writeData();
        bookRepository.writeData();
        userRepository.writeData();
    }

}
