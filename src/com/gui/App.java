package com.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("behandlungoperationen.fxml")); //@@@@@@@@ TEST
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ZahnarztklinikAPP");
        stage.setScene(scene);
        stage.show();
    }
    public static void changeStage(ActionEvent event, String ressource, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/gui/"+ressource));
        Scene scene = new Scene(fxmlLoader.load());
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
