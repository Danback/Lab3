package com.example.lab3;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

// användargränssnittet och interaktion.
public class HelloController {
    private final TicTacToeModel model; // Modellen som håller i spellogiken.
    private final TicTacToeView view; // Vyn som visar spelbrädet och gränssnittet.

    // Konstruktorn initierar modellen och vyn och kopplar händelsehanterare till knapparna.
    public HelloController() {
        model = new TicTacToeModel();
        view = new TicTacToeView();
        addEventHandlers();
    }

    // koppla en händelsehanterare till varje knapp i spelbrädet.
    private void addEventHandlers() {
        for (int i = 0; i < 9; i++) {
            Button button = (Button) view.getGridPane().getChildren().get(i);
            int row = i / 3;
            int col = i % 3;
            button.setOnAction(event -> {
                // Kontrollerar om spelet är igång och om det är spelarens tur.
                if (model.isGameInProgress() && model.isXTurn()) {
                    // Spelare 'X' gör drag.
                    model.makeMove(row, col, 'X');
                    view.updateBoard(model.getBoard());

                    // Kontrollera om spelaren vann eller om det blev oavgjort.
                    if (model.checkForWin('X')) {
                        gameEnd("Player X wins!");
                    } else if (model.checkForDraw()) {
                        gameEnd("It's a draw!");
                    } else {
                        // Datorns gör sitt drag.
                        makeComputerMove();
                    }
                }
            });
        }
    }

    // Datorns drag baserat på tillgängliga platser.
    private void makeComputerMove() {
        model.makeComputerMove();
        view.updateBoard(model.getBoard());

        // Kolla om datorn vann eller om det blev oavgjort.
        if (model.checkForWin('O')) {
            gameEnd("Computer wins!");
        } else if (model.checkForDraw()) {
            gameEnd("It's a draw!");
        }
    }

    // Hanterar slutet av spelet och visar ett meddelande.
    private void gameEnd(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message + "\n" + getScoreMessage());
        alert.showAndWait();

        // Återställer spelet för en ny omgång.
        model.resetGame();
        view.updateBoard(model.getBoard());
    }

    // Returnerar en sträng med aktuell poängställning.
    private String getScoreMessage() {
        return "Scores:\nPlayer X - " + model.getPlayerXScore() + "\nPlayer O - " + model.getPlayerOScore();
    }

    // Returnerar vyn så att den kan användas i huvudapplikationen.
    public TicTacToeView getView() {
        return view;
    }
}
