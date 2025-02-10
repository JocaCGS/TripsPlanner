package com.colini;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxTela = loadFXML("./views/FirstScreen.fxml");
        Parent root = fxTela.load();
        scene = new Scene(root, 1280, 720);
        stage.setTitle("Trips Planner by JocaCGS");
        stage.setScene(scene);
        stage.show();
    }

    public static FXMLLoader loadFXML(String path) {
        return new FXMLLoader(App.class.getResource(path));
    }

    public static void changeScreen(String path) {
        try {   
            FXMLLoader fxTela = loadFXML(path);
            Parent root = fxTela.load();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openModalScreen(String path, String title) {
        try {
            FXMLLoader loader = loadFXML("./views/"+path+".fxml");
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

// MADE BY JOCACGS