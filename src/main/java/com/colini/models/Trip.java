package com.colini.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trip {
    private StringProperty tripId;
    private StringProperty tripName;
    private StringProperty tripDestination;
    private StringProperty tripDate;
    private StringProperty tripEstimatedValue;
    private StringProperty tripNotepad; // Adicionando a nova propriedade 
    private StringProperty userId;

    // Constructor including ID (for queries, updates, or loading data)
    public Trip(String id, String name, String destination, String date, String value, String notepad, String userId) {
        this.tripId = new SimpleStringProperty(id);
        this.tripName = new SimpleStringProperty(name); 
        this.tripDestination = new SimpleStringProperty(destination);
        this.tripDate = new SimpleStringProperty(date);
        this.tripEstimatedValue = new SimpleStringProperty(value);
        this.tripNotepad = new SimpleStringProperty(notepad);
        this.userId = new SimpleStringProperty(userId);
    }

    // Constructor for adding new trips (without ID)
    public Trip(String name, String destination, String date, String value, String notepad, String userId) {
        this.tripId = new SimpleStringProperty();
        this.tripName = new SimpleStringProperty(name);
        this.tripDestination = new SimpleStringProperty(destination);
        this.tripDate = new SimpleStringProperty(date);
        this.tripEstimatedValue = new SimpleStringProperty(value);
        this.tripNotepad = new SimpleStringProperty(notepad);
        this.userId = new SimpleStringProperty(userId);
    }

    // Empty constructor (useful for creating an instance and filling it later)
    public Trip() {
        this.tripId = new SimpleStringProperty();
        this.tripName = new SimpleStringProperty();
        this.tripDestination = new SimpleStringProperty();
        this.tripDate = new SimpleStringProperty();
        this.tripEstimatedValue = new SimpleStringProperty();
        this.tripNotepad = new SimpleStringProperty(); 
        this.userId = new SimpleStringProperty();
    }
    
    
    public String getTripId() {
        return tripId.get();
    }

    public void setTripId(StringProperty tripId) {
        this.tripId = tripId;
    }

    public StringProperty getTripIdProperty() {
        return tripEstimatedValue;
    }

    public String getTripName() {
        return tripName.get();
    }
    
    public StringProperty getTripNameProperty() {
        return tripName;
    }
    
    public void setTripName(StringProperty tripName) {
        this.tripName = tripName;
    }

    public String getTripDestination() {
        return tripDestination.get();
    }

    public void setTripDestination(StringProperty tripDestination) {
        this.tripDestination = tripDestination;
    }

    public StringProperty getTripDestinationProperty() {
        return tripDestination;
    }

    public String getTripDate() {
        return tripDate.get();
    }

    public void setTripDate(StringProperty tripDate) {
        this.tripDate = tripDate;
    }

    public StringProperty getTripDateProperty() {
        return tripDate;
    }

    public String getTripEstimatedValue() {
        return tripEstimatedValue.get();
    }

    public void setTripEstimatedValue(StringProperty tripEstimatedValue) {
        this.tripEstimatedValue = tripEstimatedValue;
    }

    public StringProperty getTripEstimatedValueProperty() {
        return tripEstimatedValue;
    }

    public String getTripNotepad() {
        return tripNotepad.get();
    }

    public void setTripNotepad(StringProperty tripNotepad) {
        this.tripNotepad = tripNotepad;
    }

    public StringProperty getTripNotepadProperty() {
        return tripNotepad;
    }

    public String getUserId() {
        return userId.get();
    }

    public void setUserId(StringProperty userId) {
        this.userId = userId;
    }

    public StringProperty getUserIdProperty() {
        return tripId;
    }
    
}
