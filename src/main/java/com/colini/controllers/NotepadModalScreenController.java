package com.colini.controllers;

import java.sql.SQLException;

import com.colini.dao.TripsDAO;
import com.colini.models.TripClickedButton;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NotepadModalScreenController {

    @FXML
    private TextArea noteArea;

    @FXML
    private Pane notesPane;

    @FXML
    private Button saveButton;

    public void initialize() {
        loadPreviousNote();
    }

    public void saveChanges() {
        String content = noteArea.getText();

        try {
            TripsDAO dao = new TripsDAO();
            dao.updateNote(content, Integer.parseInt(TripClickedButton.getClickedId()));
            new Utils().alert("Note Saved", "Notes saved successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    public void loadPreviousNote(){
        TripsDAO dao = new TripsDAO();
        String content = dao.searchLastNote(Integer.parseInt(TripClickedButton.getClickedId()));
        if (content != null) {
            noteArea.setText(content);
        }
    }

}
