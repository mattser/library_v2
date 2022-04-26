package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import java.io.File;
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

    public void printBooks(List<Integer> takenBooks) {

        for (Book book: bookList) {
            if (!takenBooks.contains(book.getBookID())) System.out.println(book);
        }
    }

    public boolean checkForBookByID(int ID) {
        return bookList.stream().anyMatch(o->o.getBookID()==ID);
    }

    public Book getBookById(int ID) {
        return bookList.stream().filter(o -> o.getBookID() == ID).findFirst().get();
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
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("src/main/resources/books.json"), bookList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
