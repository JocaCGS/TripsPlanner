package com.colini.controllers;

import com.colini.dao.UsersDAO;
import com.colini.models.Users;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SignUpScreenController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private TextField emailField;

    @FXML
    private Text emailText;

    @FXML
    private Button loginButton;

    @FXML
    private Text loginText;

    @FXML
    private TextField nameField;

    @FXML
    private Text nameText;

    @FXML
    private TextField passwordField;

    @FXML
    private Text passwordText;

    @FXML
    private Pane signUpPane;

    @FXML
    private AnchorPane signupAnchor;

    @FXML
    private Button signupButton;

    @FXML
    private Text signupText;

    public void initialize() {
        new Utils().loadBackgroundImage(backgroundImage, "registrationimage");
    }

    public void signUpUser() {
        
        String user_name = nameField.getText();
        String user_email = emailField.getText();
        String user_password = passwordField.getText();
        
        UsersDAO dao = new UsersDAO();
        
        if (user_name.isEmpty() || user_email.isEmpty() || user_password.isEmpty()) {
            new Utils().alert("Error", "All fields must be filled.");
            return;
        }
        
        Users user = new Users(user_name, user_email, user_password);
        dao.insertUser(user);
        changeScreenLogin();
    }

    public void changeScreenLogin() {
        new Utils().changeScreen("LoginScreen");
    }
}
