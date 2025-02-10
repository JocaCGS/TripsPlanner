package com.colini.controllers;

import java.util.List;

import com.colini.App;
import com.colini.dao.TripsDAO;
import com.colini.models.LoggedUser;
import com.colini.models.Trip;
import com.colini.models.TripClickedButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainScreenController {

   @FXML
    private ImageView backgroundImage;

    @FXML
    private Button changeTripButton;

    @FXML
    private TableColumn<Trip, String> dateColumn;

    @FXML
    private TableColumn<Trip, String> destinationColumn;

    @FXML
    private Button filterButton;

    @FXML
    private MenuButton filterMenu;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Pane mainPane;

    @FXML
    private Button newTripButton;

    @FXML
    private TableColumn<Trip, String> priceColumn;

    @FXML
    private Button removeTripButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Trip, String> tripNameColumn;

    @FXML
    private TableView<Trip> tripsTable;

    @FXML
    private MenuButton userMenu;

    @FXML
    private Label usernameLabel;

    
    ObservableList<Trip> userTrips = FXCollections.observableArrayList();
    String clickedID = null;
    
      public void initialize() {
         new Utils().loadBackgroundImage(backgroundImage, "mainimage");

         readDBTrips();

         usernameLabel.setText(LoggedUser.getInstance().getUser().getName());

         tripNameColumn.setCellValueFactory(cellData -> cellData.getValue().getTripNameProperty());
         destinationColumn.setCellValueFactory(cellData -> cellData.getValue().getTripDestinationProperty());
         priceColumn.setCellValueFactory(cellData -> cellData.getValue().getTripEstimatedValueProperty());
         dateColumn.setCellValueFactory(cellData -> cellData.getValue().getTripDateProperty());

         ContextMenu contextMenu = new ContextMenu();
         MenuItem tripnote = new MenuItem("Manage Trip Note");
         contextMenu.getItems().add(tripnote);

         tripsTable.setRowFactory(tv -> {
            TableRow<Trip> row = new TableRow<>();
            
            row.setOnMouseClicked(event -> {
               if (event.getButton() == MouseButton.SECONDARY && !row.isEmpty()) {
                     contextMenu.show(row, event.getScreenX(), event.getScreenY());
               } else {
                     contextMenu.hide();
               }
            });

            return row;
         });



         MenuItem accountItem = new MenuItem("Account settings");
         MenuItem logoutItem = new MenuItem("Log out");
         
         userMenu.getItems().addAll(accountItem, logoutItem);

         MenuItem noFilter = new MenuItem("Show all trips");
         MenuItem nextMonth = new MenuItem("In 30 days");
         MenuItem nextYear = new MenuItem("In this year");

         filterMenu.getItems().addAll(noFilter, nextMonth, nextYear);
         
         tripsTable.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY || event.getButton() == MouseButton.SECONDARY){
               clickedID = tripsTable.getSelectionModel().getSelectedItem().getTripId();
            }
         });



         tripnote.setOnAction(event -> addNote());

         logoutItem.setOnAction(event -> logout());
         accountItem.setOnAction(event -> new Utils().changeScreen("AccountSettingsScreen"));

         noFilter.setOnAction(event -> {
            readDBTrips();
            tripsTable.setItems(userTrips);
            filterMenu.setText("Show all trips");
         });

         nextMonth.setOnAction(event -> {
            nextMonthTrips();  
            filterMenu.setText("In 30 days");
         });

         nextYear.setOnAction(event -> {
            nextYearTrips();
            filterMenu.setText("In this year");
         });

         tripsTable.setItems(userTrips);
      }
   public void readDBTrips() {
      userTrips.clear();
      TripsDAO tripsDAO = new TripsDAO();
      List<Trip> localTripList = tripsDAO.findById(Integer.parseInt(LoggedUser.getInstance().getUser().getId()));

         for(int i = 0; i < localTripList.size() ; i++){
            userTrips.add(localTripList.get(i));
         }   
   }

   private void logout() {
      LoggedUser.getInstance().logoutUser();
      System.out.println("user logged out");
      new Utils().changeScreen("LoginScreen");
   }

   public void newTrip(){
      TripClickedButton.setInstance("add", "null", userTrips);
      App.openModalScreen("NewTripModalScreen", "New Trip");
      readDBTrips();
   }

   public void updateTrip(){
      TripClickedButton.setInstance("update", clickedID, userTrips);
      App.openModalScreen("NewTripModalScreen", "Update Trip");
      readDBTrips();
   }

   public void deleteTrip(){
      Trip selectedTrip = tripsTable.getSelectionModel().getSelectedItem();
        if (selectedTrip != null){
            userTrips.remove(selectedTrip);
            TripsDAO dao = new TripsDAO();
            Integer intClickedID = Integer.parseInt(clickedID);
            dao.deleteTrip(intClickedID);
        }

        userTrips.clear();
        readDBTrips();

        new Utils().alert("Trip removed", "The trip has been successfully removed.");
   }

   public void seachTrip(){
      String input = searchField.getText();
      TripsDAO dao = new TripsDAO();
      dao.searchTrips(input, tripsTable);
   }

   public void nextMonthTrips(){
      TripsDAO dao = new TripsDAO();
      dao.getUpcomingMonthTrips(tripsTable);
   }

   public void nextYearTrips(){
      TripsDAO dao = new TripsDAO();
      dao.getUpcomingYearTrips(tripsTable);
   }

   public void addNote(){
      TripClickedButton.setClickedId(clickedID);
      App.openModalScreen("NotepadModalScreen", "Note");
      readDBTrips();
   }

}

// MADE BY JOCACGS