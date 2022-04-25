package org.example;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;

public class BookRepository implements DataRepository {

    private List<Book> bookList = new ArrayList<>();

    public BookRepository() {
        loadData();
    }

    public BookRepository(List<Book> bookList) {
        this.bookList.addAll(bookList);
    }

    public void printBooks() {

        for(Book book: bookList) {
            System.out.println(book);
        }
    }

    @Override
    public void loadData() {
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/books_data.csv"));
            String[] nextRecord;
            while( (nextRecord = reader.readNext()) != null) {
                if (nextRecord[0].equals("Number")) continue;
                bookList.add(new Book(nextRecord));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addData(Object O) {
        try {
            bookList.add((Book) O);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List getData() {
        return bookList;
    }

    @Override
    public void writeData() {

    }
}
