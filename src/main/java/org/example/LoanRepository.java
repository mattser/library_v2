package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoanRepository implements DataRepository {

    private List<Loan> loanList = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    public LoanRepository() {
        loadData();
    }

    public LoanRepository(List<Loan> loanList) {
        this.loanList.addAll(loanList);
    }

    public void printLoans(boolean printActive) {
        for(Loan loan: loanList) {
            if (!printActive || !loan.getReturned()) {
                System.out.println(loan);
            }
        }
    }

    public List<Integer> getActiveLoanBookIDs() {
        return loanList.stream().filter(o->!o.getReturned())
                .map(o -> o.getBook().getBookID()).collect(Collectors.toList());
    }

    public void printBookPopularity() {
        System.out.println("Title=Number Of Rentals");
        System.out.println(
                loanList.stream().collect(Collectors.groupingBy(o->o.getBook().getTitle(),Collectors.counting()))
        );
    }

    public void activeLoans(User user) {
//        return loanList.stream().filter(o -> o.getUser().getUserName().equals(user.getUserName()) && !o.getReturned()).collect(Collectors.toList());
        for (Loan loan: loanList) {
            if (loan.getUser().getUserName().equals(user.getUserName()) && !loan.getReturned()) System.out.println(loan);
        }
    }

    public void returnBook(User user, Book book) {
        Loan loanToEdit = loanList.stream().filter(o -> o.getUser().getUserName().equals(user.getUserName()) && o.getBook().getBookID() == book.getBookID() && !o.getReturned())
                .findFirst().get();
        loanList.remove(loanToEdit);
        loanToEdit.setReturned(true);
        loanList.add(loanToEdit);
    }

    @Override
    public void loadData() {
        try {
            loanList.addAll(mapper.readValue(new File("src/main/resources/loan_list.json"), new TypeReference<List<Loan>>() {}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addData(Object O) {
        loanList.add((Loan) O);
    }

    @Override
    public List getData() {
        return loanList;
    }

    public void writeCSVData() {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(new File("src/main/resources/report.csv")));
            writer.writeNext(new String[] {"BookID","Title", "Borrower","Date Out","Due Date","Returned"});

            for (Loan loan: loanList) {
                writer.writeNext(new String[] {"" +loan.getBook().getBookID(),
                        loan.getBook().getTitle(),
                        loan.getUser().getUserName(),
                        "" + loan.getOutDate(),
                        "" + loan.getDueDate(),
                        "" + loan.getReturned()
                });
            }
            writer.close();
            System.out.println("Report Saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeData() {
        try {
            mapper.writeValue(new File("src/main/resources/loan_list.json"), loanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
