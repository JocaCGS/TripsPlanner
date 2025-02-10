package com.colini.controllers;

import com.colini.App;
import com.colini.dao.UsersDAO;
import com.colini.models.LoggedUser;
import com.colini.models.Users;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AccountSettingsScreenController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Button cancelButton;

    @FXML
    private Button changePasswordButton;

    @FXML
    private TextField emailField;

    @FXML
    private Text emailText;

    @FXML
    private Label headerLabel;

    @FXML
    private Label loggedAccountLabel;

    @FXML
    private Text passwordText;

    @FXML
    private Button saveButton;

    @FXML
    private AnchorPane settingsAnchor;

    @FXML
    private Pane settingsPane;

    @FXML
    private Label subheaderLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private Text usernameText;

    public void initialize(){
        new Utils().loadBackgroundImage(backgroundImage, "usersettingsimage");

        loggedAccountLabel.setText(LoggedUser.getInstance().getUser().getName());
        usernameField.setText(LoggedUser.getInstance().getUser().getName());
        emailField.setText(LoggedUser.getInstance().getUser().getEmail());

    }

    public void goBack(){
        new Utils().changeScreen("MainScreen");
    }

    public void saveChanges(){
        UsersDAO dao = new UsersDAO();
        
        String user_name = usernameField.getText();
        String user_email = emailField.getText();

        if (user_name.isEmpty() || user_email.isEmpty() ){
            new Utils().alert("Error", "All fields must be filled!");
            return;
        } 
        
        Users user = new Users(LoggedUser.getInstance().getUser().getId(), user_name, user_email, LoggedUser.getInstance().getUser().getPassword());
        dao.updateUser(user);
        LoggedUser.getInstance().getUser().setName(user_name);
        LoggedUser.getInstance().getUser().setEmail(user_email);
        loggedAccountLabel.setText(LoggedUser.getInstance().getUser().getName());

        new Utils().alert("Changes saved", "Changes were sucessfully saved.");
    }

    public void changePassword(){
        App.openModalScreen("ChangePasswordModalScreen","Change Password");
    }

}
