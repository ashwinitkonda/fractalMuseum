//I worked on the homework assignment alone, using only course materials.
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

/**
 *Calculates CS1331 grade based on 5 fields (homework, tests x3, final)
 *
 *@author ltapp3
 *@version 1.0
 */
public class GradeCalculator extends Application {

    /**
     *Creates the GUI
     *
     *@param stage a platform for all the buttons, labels, etc. to exist on
     */
    public void start(Stage stage) {
        //Button
        Button gradeButton = new Button();
        gradeButton.setText("Calculate Grade");

        //Labels
        Label homework = new Label("Homework:");
        Label test1 = new Label("Test 1:");
        Label test2 = new Label("Test 2:");
        Label test3 = new Label("Test 3:");
        Label finalExam = new Label("Final Exam:");

        //Text Field
        TextField tfHomework = new TextField();
        TextField tfTest1 = new TextField();
        TextField tfTest2 = new TextField();
        TextField tfTest3 = new TextField();
        TextField tfFinal = new TextField();

        //Putting Labels next to Text Fields, respectively
        HBox boxH = new HBox();
        boxH.getChildren().addAll(homework, tfHomework);
        boxH.setSpacing(10);

        HBox boxT1 = new HBox();
        boxT1.getChildren().addAll(test1, tfTest1);
        boxT1.setSpacing(10);

        HBox boxT2 = new HBox();
        boxT2.getChildren().addAll(test2, tfTest2);
        boxT2.setSpacing(10);

        HBox boxT3 = new HBox();
        boxT3.getChildren().addAll(test3, tfTest3);
        boxT3.setSpacing(10);

        HBox boxF = new HBox();
        boxF.getChildren().addAll(finalExam, tfFinal);
        boxF.setSpacing(10);

        //Output Labels
        Label letterGrade = new Label();
        Label grade = new Label();

        //Button Action
        gradeButton.setOnAction(event -> {
            String homeworkString = tfHomework.getCharacters().toString();
            String test1String = tfTest1.getCharacters().toString();
            String test2String = tfTest2.getCharacters().toString();
            String test3String = tfTest3.getCharacters().toString();
            String finalString = tfFinal.getCharacters().toString();
            try {
                double homeworkD = Double.parseDouble(homeworkString);
                double test1D = Double.parseDouble(test1String);
                double test2D = Double.parseDouble(test2String);
                double test3D = Double.parseDouble(test3String);
                double finalD = Double.parseDouble(finalString);
                double finalGrade = csGrade(homeworkD, test1D, test2D, test3D, finalD);
                String finalLetter = csLetter(finalGrade);
                grade.setText(String.format("%.2f", finalGrade));
                letterGrade.setText(finalLetter);
            } catch (NumberFormatException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Invalid Entry");
                a.setContentText("One or more of you inputs are not numbers.");
                a.showAndWait();
            }
        });

        //Organizing everything
        VBox leftSide = new VBox();
        leftSide.getChildren().addAll(boxH, boxT1, boxT2, boxT3, boxF);
        leftSide.setSpacing(15);
        leftSide.setAlignment(Pos.CENTER);

        VBox rightSide = new VBox();
        rightSide.getChildren().addAll(gradeButton, grade, letterGrade);
        rightSide.setSpacing(15);
        rightSide.setAlignment(Pos.CENTER);

        HBox screen = new HBox();
        screen.getChildren().addAll(leftSide, rightSide);
        screen.setSpacing(20);
        screen.setAlignment(Pos.CENTER);

        Scene scene = new Scene(screen, 400, 400);
        stage.setTitle("Grade Calculator");
        stage.setScene(scene);
        stage.show();

    }

    /**
     *Calculates the number grade for CS1331 class
     *
     *@param homework the user's homework average which accounts for 18% of their grade
     *@param test1 the user's test 1 grade which accounts for 19% of their grade
     *@param test2 the user's test 2 grade which accounts for 19% of their grade
     *@param test3 the user's test 3 grade which accounts for 19% of their grade
     *@param finalExam the user's final exam grade which accounts for 25% of their grade
     *@return this user's overall number grade
     */

    public double csGrade(double homework, double test1, double test2, double test3, double finalExam) {
        return ((homework * .18) + (test1 * .19) + (test2 * .19) + (test3 * .19) + (finalExam * .25));
    }

    /**
     *Determine's user's letter grade based off of their overall number grade
     *
     *@param val the user's overall number grade
     *@return the letter grade the user recieved for the class
     */
    public String csLetter(double val) {
        if (val >= 90.00) {
            return "A";
        } else if (val >= 80.00 && val < 90.00) {
            return "B";
        } else if (val >= 70.00 && val < 80.00) {
            return "C";
        } else if (val >= 60.00 && val < 70.00) {
            return "D";
        } else {
            return "F";
        }
    }
}

//$ javac --module-path javafx-sdk-11.0.2/lib --add-modules=javafx.controls HelloWorld.java

//$ java --module-path javafx-sdk-11.0.2/lib --add-modules=javafx.controls HelloWorld

