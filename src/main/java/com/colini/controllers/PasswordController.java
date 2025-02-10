package com.colini.controllers;

import com.colini.dao.UsersDAO;
import com.colini.models.LoggedUser;
import com.colini.models.Users;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PasswordController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private TextField confirmNewPasswordField;

    @FXML
    private Text confirmNewPasswordText;

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private Text currentPasswordText;

    @FXML
    private Button doneButton;

    @FXML
    private Label headerLabel;

    @FXML
    private TextField newPasswordField;

    @FXML
    private Text newPasswordText;

    @FXML
    private AnchorPane passwordAnchor;

    @FXML
    private Pane passwordPane;

    public void saveChanges(){
        UsersDAO dao = new UsersDAO();
        
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmNewPassword = confirmNewPasswordField.getText();
        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()){
            new Utils().alert("Error", "All fields must be filled!");
            return;
        }

        if (!(LoggedUser.getInstance().getUser().getPassword().equals(currentPassword))){
            new Utils().alert("Error", "Wrong Current Password.");
            return;
        } else if (newPassword.equals(LoggedUser.getInstance().getUser().getPassword())){
            new Utils().alert("Error", "The new password can't be the same as your last password.");
            return;
        } else if (!(newPassword.equals(confirmNewPassword))){
            new Utils().alert("Error", "Your new password and its confirmation doesn't match.");
            return;
        }

        LoggedUser.getInstance().getUser().setPassword(newPassword);
        Users user = new Users(LoggedUser.getInstance().getUser().getId(), LoggedUser.getInstance().getUser().getName(), LoggedUser.getInstance().getUser().getEmail(), LoggedUser.getInstance().getUser().getPassword());
        dao.updateUser(user);
        
        new Utils().alert("Password Updated", "Password sucessfully updated.");

        Stage stage = (Stage) doneButton.getScene().getWindow();
        stage.close();
    }

}
