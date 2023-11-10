package com.example.lab3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Skapa kontrollern
        HelloController controller = new HelloController();

        // Skapa spelscenen? med vyn från kontrollern.
        Scene scene = new Scene(controller.getView().getGridPane(), 300, 300);

        // Sätt titeln på fönstret (scenen).
        primaryStage.setTitle("Tic Tac Toe");

        // Placera scenen i fönstret (stadiet).
        primaryStage.setScene(scene);

        // Visa fönstret för användaren.
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
