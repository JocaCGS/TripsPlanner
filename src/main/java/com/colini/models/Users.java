package com.colini.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Users {
    private StringProperty id;
    private StringProperty name;
    private StringProperty email;
    private StringProperty password;

    public Users(String id, String name, String email, String password) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
    }

    public Users(String name, String email, String password) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
    }

    // Getter and Setter for id
    public StringProperty idProperty() {
        return id;
    }
    public String getId() {
        return id.get();
    }
    public void setId(String id) {
        this.id.set(id);
    }

    // Getter and Setter for name
    public StringProperty nameProperty() {
        return name;
    }
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }

    // Getter and Setter for email
    public StringProperty emailProperty() {
        return email;
    }
    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email) {
        this.email.set(email);
    }

    // Getter and Setter for password
    public StringProperty passwordProperty() {
        return password;
    }
    public String getPassword() {
        return password.get();
    }
    public void setPassword(String password) {
        this.password.set(password);
    }
}
