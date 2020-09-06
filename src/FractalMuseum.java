import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;


public class FractalMuseum extends Application {

    private int player1Score;
    private int player2Score;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage mainStage) {
        mainStage.setTitle("Fractal Museum");
        BorderPane layout = new BorderPane();

        //GENERATE FRACTAL BUTTON
        Button genFract = new Button();
        genFract.setText("Generate Fractal");

        //NEW GAME BUTTON
        Button newGame = new Button();
        genFract.setText("New Game!");

        //SUBMIT GUESSES BUTTON
        Button submitGuesses = new Button();
        genFract.setText("Submit Guesses!");

        //PLAYER GUESS TEXT FIELDS
        TextField P1Guess = new TextField();
        P1Guess.setPromptText("P1 Guess");
        TextField P2Guess = new TextField();
        P1Guess.setPromptText("P2 Guess");

        //Output Labels
        Label PlayerOneScore = new Label("Player 1 Score: ");
        Label score1 = new Label();
        Label PlayerTwoScore = new Label("Player 2 Score: ");
        Label score2 = new Label();
        Label correctAnswer = new Label();

        //Assembling the border pane
        HBox top = new HBox();
        top.getChildren().addAll(newGame);
        //boxH.setSpacing(10);
        layout.setTop(top);

        //Bottom contains text fields, submit guesses button, scores, correct answer
        GridPane bottom = new GridPane();
        bottom.add(P1Guess, 0, 0, 2, 1);
        bottom.add(P2Guess, 0, 3, 2, 1);
        bottom.add(submitGuesses, 0, 6, 2, 1);
        bottom.add(PlayerOneScore, 1, 0, 1, 1);
        bottom.add(Score1, 1, 2, 1, 1);
        bottom.add(PlayerTwoScore, 1, 3, 1, 1);
        bottom.add(Score2, 1, 4, 1, 1);
        bottom.setHgap(10);
        bottom.setVgap(10);
        layout.setBottom(bottom);

        EventHandler <ActionEvent> genFractButton = new EventHandler <>() {
            public void handle (ActionEvent genFractButton) {
                Pane drawing = new Pane(200,200);
                layout.setCenter(DrawFractal(drawing));
            }
        }
        int numFractals = DrawFractal(.....);

        submitGuesses.setOnAction(event -> {
            String guess1 = P1Guess.getCharacters().toString();
            String guess2 = P2Guess.getCharacters().toString();
            try {
                int player1Guess = Integer.parseInt(guess1);
                int player2Guess = Integer.parseInt(guess2);
                if (guess1.equals(null) || (player1Guess < 0)) {
                    P1Guess.clear();
                    throw new InputMismatchException("Player 1, please enter a non-negative integer guess!");
                } else if (guess2.equals(null) || (player2Guess < 0)) {
                    P2Guess.clear();
                    throw new InputMismatchException("Player 2, please enter a non-negative integer guess!");
                }

                int diff1 = Math.abs(player1Guess - numFractals);
                int diff2 = Math.abs(player2Guess - numFractals);
                if (diff1 < diff2) {
                    //pop up that player one won the round (FIGURE OUT HOW TO FADE THIS)
                    //increase player one score
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("GAMEPLAY UPDATE");
                    a.setHeaderText("PLAYER ONE HAS WON THE ROUND!");
                    a.setContentText("The correct answer was: " + numFractals + "!");
                    a.showAndWait();
                    player1Score++;
                    score1.setText("" + player1Score);
                } else if (diff2 < diff1) {
                    //pop up that player two won the round
                    //increase player 2 score
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("GAMEPLAY UPDATE");
                    a.setHeaderText("PLAYER TWO HAS WON THE ROUND!");
                    a.setContentText("The correct answer was: " + numFractals + "!");
                    a.showAndWait();
                    player2Score++;
                    score2.setText("" + player2Score);
                } else {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("GAMEPLAY UPDATE");
                    a.setHeaderText("IT'S A TIE!");
                    a.setContentText("The correct answer was: " + numFractals + "!");
                    a.showAndWait();
                }
                correctAnswer.setText("" + numFractals);
                P1Guess.clear();
                P2Guess.clear();
                submitGuesses.isDisable(true);

            } catch (NumberFormatException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Invalid Entry");
                a.setContentText("One or more of you inputs are not numbers.");
                a.showAndWait();

            } catch (InputMismatchException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Invalid Entry");
                a.setContentText("One or more of you inputs are not numbers.");
                a.showAndWait();
            }
        });
        Scene scene = new Scene(layout, 1000, 1000);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
