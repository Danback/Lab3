package com.example.lab3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the controller, which in turn creates the model and the view
        HelloController controller = new HelloController();

        // The scene is created with the view from the controller
        Scene scene = new Scene(controller.getView().getGridPane(), 300, 300);

        // Set the title of the window (stage)
        primaryStage.setTitle("Tic Tac Toe");

        // Place the scene in the stage
        primaryStage.setScene(scene);

        // Show the stage to the user
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the application
        launch(args);
    }
}
