package com.colini.controllers; 

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import com.colini.dao.TripsDAO;
import com.colini.models.LoggedUser;
import com.colini.models.Trip;
import com.colini.models.TripClickedButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TripsController {
    
    @FXML
    private Pane tripControlPane;
    
    @FXML
    private ImageView backgroundImage;
    
    @FXML
    private AnchorPane tripControlAnchor;
    
    @FXML
    private Label headerLabel;

    @FXML
    private Text nameText;
    
    @FXML
    private TextField nameField;

    @FXML
    private Text destinationText;

    @FXML
    private TextField destinationField;

    @FXML
    private Text dateText;
    
    @FXML
    private TextField dateField;

    @FXML
    private Text priceText;

    @FXML
    private TextField priceField;

    @FXML
    private Button doneButton;
    
    String option, clickedID;
    ObservableList<Trip> userTrips = FXCollections.observableArrayList();
    
    public void initialize() {
        option = TripClickedButton.getOption();
        clickedID = TripClickedButton.getClickedId();
        userTrips = TripClickedButton.getUserTrips();
        
        if(option.equals("update")){
            headerLabel.setText("Change a Trip");
            for(int i = 0; i <  userTrips.size(); i++) {
                Trip trip = userTrips.get(i);
                if(trip.getTripId().equals(clickedID)){
                    nameField.setText(trip.getTripName());
                    destinationField.setText(trip.getTripDestination());

                    try {
                        DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
                        LocalDate data = LocalDate.parse(trip.getTripDate(), formatterInput);
                        dateField.setText(data.format(formatterOutput));
                    } catch (DateTimeParseException e) {
                        new Utils().alert("Error", "Something went wrong.");
                        return;
                    }
                    
                    priceField.setText(trip.getTripEstimatedValue());
                    break;
                }
            }
        }

    }

    public void saveTrip(){
        TripsDAO dao = new TripsDAO();
        
        String trip_name = nameField.getText();
        String trip_destination = destinationField.getText();
        String trip_date = dateField.getText();
        String string_trip_estimated_value = priceField.getText().replace(',', '.');
        
        String formated_trip_date;

        if (trip_name.isEmpty() || trip_destination.isEmpty() || trip_date.isEmpty() || string_trip_estimated_value.isEmpty()){
            new Utils().alert("Error", "All fields must be filled!");
            return;
        } else if (!Pattern.matches("\\d{2}/\\d{2}/\\d{4}", trip_date)) {
            new Utils().alert("Error", "Use dd/MM/yyyy format.");
            return;
        }

        try {
            DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate data = LocalDate.parse(trip_date, formatterInput);
            formated_trip_date = data.format(formatterOutput);
        } catch (DateTimeParseException e) {
            new Utils().alert("Error", "Use dd/MM/yyyy format.");
            return;
        }

        if(option.equals("add")){
            Trip newTrip = new Trip(trip_name, trip_destination, formated_trip_date, string_trip_estimated_value, null, LoggedUser.getInstance().getUser().getId());
            dao.insertTrip(newTrip);
            new Utils().alert("Trip Added", "Trip successfully added.");
        } else {
            Trip newTrip = new Trip(clickedID, trip_name, trip_destination, formated_trip_date, string_trip_estimated_value, null, LoggedUser.getInstance().getUser().getId());
            dao.updateTrip(newTrip);
            new Utils().alert("Trip Updated", "Trip successfully updated.");
        }

        Stage stage = (Stage) doneButton.getScene().getWindow();
        stage.close();
    }
}
