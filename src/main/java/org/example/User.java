package org.example;

public class User {

    private String userName;
    private boolean isAdmin;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
        this.isAdmin = false;
    }

    public User(String userName, boolean isAdmin) {
        this.userName = userName;
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return (isAdmin ? "Admin: " : "User: ") + userName;
    }
}
