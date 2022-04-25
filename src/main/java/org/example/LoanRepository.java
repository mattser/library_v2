package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository implements DataRepository {

    private List<Loan> loanList = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    public LoanRepository() {
        loadData();
    }

    public LoanRepository(List<Loan> loanList) {
        this.loanList.addAll(loanList);
    }

    public void printLoans() {
        for(Loan loan: loanList) {
            System.out.println(loan);
        }
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

    @Override
    public void writeData() {
        try {
            mapper.writeValue(new File("src/main/resources/loan_list.json"), loanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
