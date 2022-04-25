package org.example;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements DataRepository {

    private List<User> userList = new ArrayList<>();

    public UserRepository() {
    }

    public UserRepository(List<User> userList) {
        this.userList.addAll(userList);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void addData(Object O) {

    }

    @Override
    public List getData() {
        return new ArrayList<>();
    }

    @Override
    public void writeData() {

    }
}
