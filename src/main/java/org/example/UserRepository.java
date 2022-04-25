package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements DataRepository {

    private List<User> userList = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    public UserRepository() {
        loadData();
    }

    public UserRepository(List<User> userList) {
        this.userList.addAll(userList);
    }

    public void printUsers() {
        for (User user: userList) {
            System.out.println(user);
        }
    }

    @Override
    public void loadData() {
        try {
            userList.addAll(mapper.readValue(new File("src/main/resources/users_list.json"), new TypeReference<List<User>>() {}));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addData(Object O) {
        try {
            userList.add((User) O);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List getData() {
        return userList;
    }

    @Override
    public void writeData() {
        try {
            mapper.writeValue(new File("src/main/resources/users_list.json"),userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
