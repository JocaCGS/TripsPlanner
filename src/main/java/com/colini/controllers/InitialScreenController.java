package com.colini.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class InitialScreenController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private AnchorPane firstScreenAnchor;

    @FXML
    private Pane firstScreenPane;

    @FXML
    private Button startButton;

    public void initialize() {
        new Utils().loadBackgroundImage(backgroundImage, "registrationimage");
    }
    public void changeScreen(){
        new Utils().changeScreen("LoginScreen");
    }
}
