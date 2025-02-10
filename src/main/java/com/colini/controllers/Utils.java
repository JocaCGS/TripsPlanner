package com.colini.controllers;

import java.io.InputStream;

import com.colini.App;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Utils {
    public void loadBackgroundImage(ImageView backgroundImage, String path) {
        InputStream imageStream = getClass().getResourceAsStream("/images/"+ path + ".png");
        Image image = new Image(imageStream);
        backgroundImage.setImage(image);
    }

    public void changeScreen(String path) {
        App.changeScreen("./views/"+path+".fxml");
    }

    public void alert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
    }
}
