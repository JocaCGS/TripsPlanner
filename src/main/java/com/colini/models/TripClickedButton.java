package com.colini.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TripClickedButton {
    private static String instance[] = new String[2];
    private static ObservableList<Trip> userTrips = FXCollections.observableArrayList();

    private TripClickedButton() {} // Private constructor

    public static void setInstance(String option, String clickedID, ObservableList<Trip> collectedList ) {
        instance[0] = option;
        instance[1] = clickedID;
        userTrips = collectedList;
    }

    public static String getOption() {
        return instance[0];
    }
    public static String getClickedId() {
        return instance[1];
    }

    public static void setClickedId(String value){
        instance[1] = value;
    }

    public static ObservableList<Trip> getUserTrips() {
        return userTrips;
    }   
}
