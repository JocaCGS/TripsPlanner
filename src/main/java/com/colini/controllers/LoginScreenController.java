package com.colini.controllers;

import com.colini.models.LoggedUser;

import com.colini.dao.UsersDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class LoginScreenController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private TextField emailField;

    @FXML
    private AnchorPane loginAnchor;

    @FXML
    private Button loginButton;

    @FXML
    private Pane loginPane;

    @FXML
    private Text loginText;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text passwordText;

    @FXML
    private Text seuemailTextField;

    @FXML
    private Button signButton;

    @FXML
    private Text signupText;

    public void initialize() {
        new Utils().loadBackgroundImage(backgroundImage, "registrationimage");
    }

    public void login(){
        String user_email = emailField.getText();
        String user_password = passwordField.getText();
        try{
            String user_id = new UsersDAO().verifyUserLogin(user_email, user_password);

            if (user_email.isEmpty() || user_password.isEmpty()) {
                new Utils().alert("Invalid!", "Fill out all the fields!");
                return;
            } else if (user_id.equals(null)) {
                new Utils().alert("Invalid!", "Email or password are incorrect!"); 
                return;
            }
            LoggedUser.newInstance().setUser(new UsersDAO().findUser(user_id));
            LoggedUser.newInstance().writeUser();
        } catch (Exception e){
            new Utils().alert("Error", "Username or password are incorrect.");
            return;
        }
        new Utils().changeScreen("MainScreen");
    }

    public void changeScreenSignUp() {
        new Utils().changeScreen("SignUpScreen");
    }
}
