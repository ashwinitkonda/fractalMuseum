import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import java.util.InputMismatchException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * A class for a fractal museum game GUI in JavaFX.
 *@author ashwini thirukkonda
 *@version 1.0
 */
public class FractalMuseum extends Application {

    private int player1Score;
    private int player2Score;
    private int player3Score;
    private int numFractals;
    private int numPlayers;

    /**
     * This is the main method
     * @param args A string array of what is passed into the main method.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
    *Creates the GUI
    *@param mainStage platform for nodes to exist
    */
    public void start(Stage mainStage) {
        mainStage.setTitle("Fractal Museum");

        //INTRO SCENE

        //NUMBER OF PLAYERS BUTTONS
        Font font = Font.font("TW CEN MT CONDENSED EXTRA BOLD", FontWeight.BOLD, 45);
        Font font2 = Font.font("Segoe UI Semibold", FontWeight.BOLD, FontPosture.ITALIC, 20);
        Font font3 = Font.font("TW CEN MT CONDENSED EXTRA BOLD", 40);
        Font font4 = Font.font("Segoe UI Semibold", FontWeight.BOLD, 11);

        Button onePlayer = new Button();
        onePlayer.setText("1");
        onePlayer.setFont(font3);
        onePlayer.setStyle("-fx-text-fill: purple");

        Button twoPlayer = new Button();
        twoPlayer.setText("2");
        twoPlayer.setFont(font3);
        twoPlayer.setStyle("-fx-text-fill: purple");

        Button threePlayer = new Button();
        threePlayer.setText("3");
        threePlayer.setFont(font3);
        threePlayer.setStyle("-fx-text-fill: purple");

        //WELCOME SCREEN TEXT
        Text welcome = new Text("Welcome to Fractal Museum!");
        welcome.setFill(Color.DARKSLATEGRAY);
        welcome.setFont(font);

        Text author = new Text("Designed by Ashwini Thirukkonda, 2020");
        author.setFill(Color.DARKMAGENTA);
        author.setFont(font2);

        Text selectNump = new Text("How many players?");
        selectNump.setFill(Color.DARKSLATEGRAY);
        selectNump.setFont(font3);

        //FORMAT LAYOUTS (BORDER WITH GRIDS INSIDE)
        BorderPane primaryLayout = new BorderPane();

        GridPane nums = new GridPane();
        nums.setHgap(10);
        nums.add(onePlayer, 0, 0);
        nums.add(twoPlayer, 1, 0);
        nums.add(threePlayer, 2, 0);
        nums.setAlignment(Pos.BASELINE_CENTER);
        nums.setPadding(new Insets(10, 10, 10, 10));
        nums.setBorder(new Border(new BorderStroke(Color.DARKMAGENTA,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        VBox center = new VBox();
        center.getChildren().add(selectNump);
        center.getChildren().add(nums);
        center.setPadding(new Insets(10, 10, 10, 10));
        center.setSpacing(10);
        center.setAlignment(Pos.BASELINE_CENTER);
        primaryLayout.setCenter(center);

        GridPane top = new GridPane();
        top.add(welcome, 0, 1);
        top.add(author, 0, 2);
        GridPane.setHalignment(author, HPos.CENTER);
        top.setAlignment(Pos.BASELINE_CENTER);
        top.setPadding(new Insets(50, 50, 50, 50));
        primaryLayout.setTop(top);

        //instructions button
        Button instructButton = new Button();
        instructButton.setText("Click here for instructions");
        instructButton.setFont(font2);
        instructButton.setStyle("-fx-text-fill: darkslategray");

        //how to play
        Text how2Play = new Text("HOW TO PLAY");
        how2Play.setFill(Color.DARKSLATEGRAY);
        how2Play.setFont(font2);
        how2Play.setVisible(false);

        //text instructions
        Text instructions1 = new Text("Select number of players, then click 'new game'. "
                                        + "Guess how many squares are in the image!");
        Text instructions2 = new Text("The closest guess wins the round! Click 'new game' "
                                        + "to begin the next round.");
        instructions1.setFill(Color.DARKSLATEGRAY);
        instructions1.setFont(font4);
        instructions1.setVisible(false);
        instructions2.setFill(Color.DARKSLATEGRAY);
        instructions2.setFont(font4);
        instructions2.setVisible(false);

        instructButton.setOnAction(event -> {
                instructButton.setVisible(false);
                instructions1.setVisible(true);
                instructions2.setVisible(true);
                how2Play.setVisible(true);
            });

        GridPane newB = new GridPane();
        newB.add(instructButton, 0, 1);
        newB.add(how2Play, 0, 1);
        newB.add(instructions1, 0, 2);
        newB.add(instructions2, 0, 3);
        GridPane.setHalignment(instructButton, HPos.CENTER);
        GridPane.setHalignment(how2Play, HPos.CENTER);
        GridPane.setHalignment(instructions1, HPos.CENTER);
        GridPane.setHalignment(instructions2, HPos.CENTER);
        newB.setAlignment(Pos.BASELINE_CENTER);
        newB.setPadding(new Insets(30, 30, 30, 30));
        primaryLayout.setBottom(newB);

        Scene introScene = new Scene(primaryLayout, 650, 650);
        BackgroundFill bFill = new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY);
        Background bground = new Background(bFill);
        primaryLayout.setBackground(bground);

        //GAMEPLAY SCENE

        BorderPane layout = new BorderPane();

        //NEW GAME BUTTON
        Button newGame = new Button();
        newGame.setText("NEW GAME");
        newGame.setStyle("-fx-text-fill: purple");
        newGame.setFont(font4);

        //FRACTAL MUSEUM
        Text fractMus = new Text("FRACTAL MUSEUM");
        fractMus.setFill(Color.DARKSLATEGRAY);
        fractMus.setFont(font);

        //BACK TO HOME BUTTON
        Button backHome = new Button();
        backHome.setText("BACK HOME");
        backHome.setStyle("-fx-text-fill: purple");
        backHome.setFont(font4);

        //SUBMIT GUESSES BUTTON
        Button submitGuesses = new Button();
        submitGuesses.setText("Submit Guesses!");
        submitGuesses.setDisable(true);
        submitGuesses.setStyle("-fx-text-fill: purple");
        submitGuesses.setFont(font4);

        //PLAYER GUESS TEXT FIELDS
        TextField p1Guess = new TextField();
        p1Guess.setDisable(true);
        p1Guess.setPromptText("P1 Guess");
        p1Guess.setFont(font4);
        p1Guess.setVisible(false);
        TextField p2Guess = new TextField();
        p2Guess.setDisable(true);
        p2Guess.setPromptText("P2 Guess");
        p2Guess.setFont(font4);
        p2Guess.setVisible(false);
        TextField p3Guess = new TextField();
        p3Guess.setDisable(true);
        p3Guess.setPromptText("P3 Guess");
        p3Guess.setFont(font4);
        p3Guess.setVisible(false);

        //Output Labels
        Label playerOneScore = new Label("Player 1 Score: ");
        Label score1 = new Label("0");
        playerOneScore.setFont(font4);
        score1.setFont(font4);

        Label playerTwoScore = new Label("Player 2 Score: ");
        Label score2 = new Label("0");
        playerTwoScore.setFont(font4);
        score2.setFont(font4);
        playerTwoScore.setVisible(false);
        score2.setVisible(false);

        Label playerThreeScore = new Label("Player 3 Score: ");
        Label score3 = new Label("0");
        playerThreeScore.setFont(font4);
        score3.setFont(font4);
        playerThreeScore.setVisible(false);
        score3.setVisible(false);

        //CORRECT NUMBER OF FRACTALS LABEL
        Label correctNumFract = new Label();
        correctNumFract.setFont(font4);

        //CORRECT NUMBER OF FRACTALS LABEL
        Text ngRem = new Text("Click new game");
        ngRem.setFill(Color.DARKSLATEGRAY);
        ngRem.setFont(font4);
        Text ngRem2 = new Text("for next round!");
        ngRem2.setFill(Color.DARKSLATEGRAY);
        ngRem2.setFont(font4);

        //Assembling the border pane

        GridPane newTop = new GridPane();
        newTop.setPadding(new Insets(10, 10, 10, 10));
        newTop.add(backHome, 0, 2);
        newTop.add(fractMus, 5, 2);
        newTop.add(newGame, 10, 2);
        newTop.add(ngRem, 10, 3);
        newTop.add(ngRem2, 10, 4);
        newTop.setHgap(10);
        newTop.setAlignment(Pos.BASELINE_CENTER);

        layout.setTop(newTop);

        //Bottom contains text fields, submit guesses button, scores, correct answer

        GridPane guessesAndSubmit = new GridPane();
        guessesAndSubmit.setVgap(3);
        guessesAndSubmit.setHgap(5);
        guessesAndSubmit.add(p1Guess, 0, 5);
        guessesAndSubmit.add(p2Guess, 3, 5);
        guessesAndSubmit.add(p3Guess, 6, 5);
        guessesAndSubmit.add(submitGuesses, 8, 5);
        GridPane scoreTracker = new GridPane();
        scoreTracker.setHgap(5);
        scoreTracker.add(playerOneScore, 0, 10);
        scoreTracker.add(score1, 1, 10);
        scoreTracker.add(playerTwoScore, 15, 10);
        scoreTracker.add(score2, 16, 10);
        scoreTracker.add(playerThreeScore, 30, 10);
        scoreTracker.add(score3, 31, 10);
        GridPane bottom = new GridPane();
        bottom.setHgap(5);
        bottom.setVgap(3);
        bottom.setPadding(new Insets(5, 5, 5, 5));
        bottom.add(guessesAndSubmit, 0, 1);
        bottom.add(scoreTracker, 0, 2);
        bottom.add(correctNumFract, 0, 3);
        bottom.setAlignment(Pos.CENTER);
        layout.setBottom(bottom);

        Pane drawing = new Pane();
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //REMOVE REMINDER
                ngRem.setVisible(false);
                ngRem2.setVisible(false);
                p1Guess.setDisable(false);
                p2Guess.setDisable(false);
                p3Guess.setDisable(false);
                drawing.getChildren().clear();
                if (numPlayers >= 2) {
                    p2Guess.setVisible(true);
                    playerTwoScore.setVisible(true);
                    score2.setVisible(true);
                    if (numPlayers == 3) {
                        p3Guess.setVisible(true);
                        playerThreeScore.setVisible(true);
                        score3.setVisible(true);
                    }
                }
                correctNumFract.setVisible(false);
                HBox fractDrawing = new HBox();
                fractDrawing.setPadding(new Insets(110, 50, 50, 50));
                fractDrawing.setAlignment(Pos.BASELINE_CENTER);
                drawing.setPrefSize(200, 200);
                numFractals = FractalFactory.drawFractal(drawing);
                fractDrawing.getChildren().add(drawing);
                layout.setCenter(fractDrawing);
                submitGuesses.setDisable(false);
                newGame.setDisable(true);
            }
        });

        submitGuesses.setOnAction(event -> {
                newGame.setDisable(false);
                drawing.getChildren().clear();
                String guess1 = p1Guess.getCharacters().toString();
                String guess2 = null;
                String guess3 = null;
                if (numPlayers == 2) {
                    guess2 = p2Guess.getCharacters().toString();
                } else if (numPlayers == 3) {
                    guess2 = p2Guess.getCharacters().toString();
                    guess3 = p3Guess.getCharacters().toString();
                }
                try {
                    if (numPlayers == 1) {
                        int player1Guess = Integer.parseInt(guess1);
                        if (guess1.equals(null) || (player1Guess < 0)) {
                            p1Guess.clear();
                            throw new InputMismatchException("Player 1, please enter a non-negative integer guess!");
                        }
                        correctNumFract.setText("Number of Fractals: " + numFractals);
                        correctNumFract.setVisible(true);
                        if (player1Guess == numFractals) {
                            //pop up that player one won the round (FIGURE OUT HOW TO FADE THIS)
                            //increase player one score
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("GAMEPLAY UPDATE");
                            a.setHeaderText("You guessed the exact number of fractals, score increased by 10 points!");
                            a.setContentText("The correct answer was: " + numFractals + "!");
                            a.showAndWait();
                            player1Score += 10;
                            score1.setText("" + player1Score);
                        } else if ((player1Guess <= numFractals * 1.15) && (player1Guess >= numFractals * 0.85)) {
                            //within fifteen %, +5 points
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("GAMEPLAY UPDATE");
                            a.setHeaderText("So close, score increased by 5 points!");
                            a.setContentText("The correct answer was: " + numFractals + "!");
                            a.showAndWait();
                            player1Score += 5;
                            score1.setText("" + player1Score);
                        } else {
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("GAMEPLAY UPDATE");
                            a.setHeaderText("Better luck next round!");
                            a.setContentText("The correct answer was: " + numFractals + "!");
                            a.showAndWait();
                        }
                        p1Guess.clear();
                        p1Guess.setDisable(true);
                        submitGuesses.setDisable(true);
                    } else if (numPlayers == 3) {
                        guess2 = p2Guess.getCharacters().toString();
                        guess3 = p3Guess.getCharacters().toString();
                        int player1Guess = Integer.parseInt(guess1);
                        int player2Guess = Integer.parseInt(guess2);
                        int player3Guess = Integer.parseInt(guess3);
                        if (guess1.equals(null) || (player1Guess < 0)) {
                            p1Guess.clear();
                            throw new InputMismatchException("Player 1, please enter a non-negative integer guess!");
                        } else if (guess2.equals(null) || (player2Guess < 0)) {
                            p2Guess.clear();
                            throw new InputMismatchException("Player 2, please enter a non-negative integer guess!");
                        } else if (guess3.equals(null) || (player3Guess < 0)) {
                            p2Guess.clear();
                            throw new InputMismatchException("Player 3, please enter a non-negative integer guess!");
                        }
                        int diff1 = Math.abs(player1Guess - numFractals);
                        int diff2 = Math.abs(player2Guess - numFractals);
                        int diff3 = Math.abs(player3Guess - numFractals);
                        correctNumFract.setText("Number of Fractals: " + numFractals);
                        correctNumFract.setVisible(true);
                        if ((diff1 < diff2) && (diff1 < diff3)) {
                            //pop up that player one won the round (FIGURE OUT HOW TO FADE THIS)
                            //increase player one score
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("GAMEPLAY UPDATE");
                            a.setHeaderText("PLAYER ONE HAS WON THE ROUND!");
                            a.setContentText("The correct answer was: " + numFractals + "!");
                            a.showAndWait();
                            player1Score++;
                            score1.setText("" + player1Score);
                        } else if ((diff2 < diff1) && (diff2 < diff3)) {
                            //pop up that player two won the round
                            //increase player 2 score
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("GAMEPLAY UPDATE");
                            a.setHeaderText("PLAYER TWO HAS WON THE ROUND!");
                            a.setContentText("The correct answer was: " + numFractals + "!");
                            a.showAndWait();
                            player2Score++;
                            score2.setText("" + player2Score);
                        } else if ((diff3 < diff1) && (diff3 < diff2)) {
                            //pop up that player three won the round
                            //increase player 2 score
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("GAMEPLAY UPDATE");
                            a.setHeaderText("PLAYER THREE HAS WON THE ROUND!");
                            a.setContentText("The correct answer was: " + numFractals + "!");
                            a.showAndWait();
                            player3Score++;
                            score3.setText("" + player3Score);
                        } else {
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("GAMEPLAY UPDATE");
                            a.setHeaderText("IT'S A TIE!");
                            a.setContentText("The correct answer was: " + numFractals + "!");
                            a.showAndWait();
                        }
                        p1Guess.clear();
                        p2Guess.clear();
                        p3Guess.clear();
                        p1Guess.setDisable(true);
                        p2Guess.setDisable(true);
                        p3Guess.setDisable(true);
                        submitGuesses.setDisable(true);
                    } else {
                        int player1Guess = Integer.parseInt(guess1);
                        int player2Guess = Integer.parseInt(guess2);
                        if (guess1.equals(null) || (player1Guess < 0)) {
                            p1Guess.clear();
                            throw new InputMismatchException("Player 1, please enter a non-negative integer guess!");
                        } else if (guess2.equals(null) || (player2Guess < 0)) {
                            p2Guess.clear();
                            throw new InputMismatchException("Player 2, please enter a non-negative integer guess!");
                        }

                        int diff1 = Math.abs(player1Guess - numFractals);
                        int diff2 = Math.abs(player2Guess - numFractals);
                        correctNumFract.setText("Number of Fractals: " + numFractals);
                        correctNumFract.setVisible(true);
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
                        p1Guess.clear();
                        p2Guess.clear();
                        p1Guess.setDisable(true);
                        p2Guess.setDisable(true);
                        submitGuesses.setDisable(true);
                    }
                    //MAKE NEW GAME REMINDER VISIBLE
                    ngRem.setVisible(true);
                    ngRem2.setVisible(true);

                } catch (NumberFormatException e) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText("Invalid Entry");
                    a.setContentText("Please enter a valid numeric guess!");
                    a.showAndWait();

                } catch (InputMismatchException e) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText("Invalid Entry");
                    a.setContentText(e.getMessage());
                    a.showAndWait();
                }
            });

        Scene gamePlay = new Scene(layout, 650, 650);

        onePlayer.setOnAction(event -> {
                numPlayers = 1;
                mainStage.setScene(gamePlay);
                p1Guess.setVisible(true);
                playerOneScore.setVisible(true);
                score1.setVisible(true);
            });

        twoPlayer.setOnAction(event -> {
                numPlayers = 2;
                mainStage.setScene(gamePlay);
                p1Guess.setVisible(true);
                playerOneScore.setVisible(true);
                score1.setVisible(true);
                p2Guess.setVisible(true);
                playerTwoScore.setVisible(true);
                score2.setVisible(true);
            });

        threePlayer.setOnAction(event -> {
                numPlayers = 3;
                mainStage.setScene(gamePlay);
                p1Guess.setVisible(true);
                playerOneScore.setVisible(true);
                score1.setVisible(true);
                p2Guess.setVisible(true);
                playerTwoScore.setVisible(true);
                score2.setVisible(true);
                p3Guess.setVisible(true);
                playerThreeScore.setVisible(true);
                score3.setVisible(true);
            });

        backHome.setOnAction(event -> {
                correctNumFract.setVisible(false);
                ngRem.setVisible(true);
                ngRem2.setVisible(true);
                p1Guess.setDisable(true);
                p2Guess.setDisable(true);
                p3Guess.setDisable(true);
                player1Score = 0;
                score1.setText("0");
                player2Score = 0;
                score2.setText("0");
                player3Score = 0;
                score3.setText("0");
                p1Guess.setVisible(false);
                playerOneScore.setVisible(false);
                score1.setVisible(false);
                p2Guess.setVisible(false);
                playerTwoScore.setVisible(false);
                score2.setVisible(false);
                p3Guess.setVisible(false);
                playerThreeScore.setVisible(false);
                score3.setVisible(false);
                instructButton.setVisible(true);
                instructions1.setVisible(false);
                instructions2.setVisible(false);
                how2Play.setVisible(false);
                mainStage.setScene(introScene);
                drawing.getChildren().clear();
            });

        // create a background fill
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        layout.setBackground(background);

        mainStage.setScene(introScene);
        mainStage.show();
    }
}