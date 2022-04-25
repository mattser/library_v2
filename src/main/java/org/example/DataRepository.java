package org.example;

import java.util.List;

public interface DataRepository {

    void loadData();

    void addData(Object O);

    List getData();

    void writeData();

}
