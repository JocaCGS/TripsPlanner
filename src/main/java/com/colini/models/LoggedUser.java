package com.colini.models;

public class LoggedUser {
    private static LoggedUser instance;
    private Users user;

    private LoggedUser() {} // Private constructor

    public static LoggedUser newInstance() {
        if (instance == null) {
            instance = new LoggedUser();
        }
        return instance;
    }

    public static LoggedUser getInstance() {
        return instance;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void writeUser() {
        System.out.println("Name: " + user.getName());
        System.out.println("ID: " + user.getId());
        System.out.println("Email: " + user.getEmail());
    }

    public void logoutUser() {
        LoggedUser.instance = null;
        this.user = null; // Removes the logged-in user
    }
}
