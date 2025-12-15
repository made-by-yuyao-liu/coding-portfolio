package cn.sixinarow.game;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

/**
 * RockPaperScissors Class
 * This class represents a graphical user interface for the game of rock-paper-scissors, allowing
 * the user to choose and display their hand and compete against a random hand from the computer,
 * and providing a method to determine the winner.
 * @author Cindy Liu
 * @version 05.06.2023
 */
public class RockPaperScissors extends GridPane {

    // Attributes/instance variables
    private static final String[] IMAGES_LEFT = {"left_rock.png", "left_paper.png", "left_scissors.png"};
    private static final String[] IMAGES_RIGHT = {"right_rock.png", "right_paper.png", "right_scissors.png"};
    private final Image[] leftImages;
    private final Image[] rightImages;
    private final ImageView leftView;
    private final ImageView rightView;
    private int leftHand;
    private int rightHand;
    private final Random random = new Random();
    private boolean disable = true;
    private int countLeft = 0;
    private int countRight = 0;

    // Parameterized constructor
    public RockPaperScissors(Handle handle) {
        leftImages = new Image[3];
        rightImages = new Image[3];
        for (int i = 0; i < 3; i++) {
            leftImages[i] = new Image(new File(IMAGES_LEFT[i]).getPath());
            rightImages[i] = new Image(new File(IMAGES_RIGHT[i]).getPath());
        }

        leftView = new ImageView(leftImages[0]);
        rightView = new ImageView(rightImages[0]);

        // Set the size of the imageview to fit window
        leftView.setFitHeight(100);
        leftView.setFitWidth(100);
        rightView.setFitHeight(100);
        rightView.setFitWidth(100);

        HBox left = new HBox();
        left.setAlignment(Pos.CENTER);
        left.prefWidth(100);
        Circle leftCircle = new Circle(Board.CELL_SIZE * 0.30);
        leftCircle.setStroke(Color.BLACK);
        leftCircle.setFill(Color.WHITE);
        leftCircle.setCenterX(Board.CELL_SIZE/2.0);
        leftCircle.setCenterY(Board.CELL_SIZE/2.0);
        left.getChildren().addAll(leftCircle, new Label("Player"));

        HBox right = new HBox();
        right.setAlignment(Pos.CENTER);
        right.prefWidth(100);
        Circle rightCircle = new Circle(Board.CELL_SIZE * 0.30);
        rightCircle.setStroke(Color.BLACK);
        rightCircle.setFill(Color.BLACK);
        rightCircle.setCenterX(Board.CELL_SIZE/2.0);
        rightCircle.setCenterY(Board.CELL_SIZE/2.0);
        right.getChildren().addAll(rightCircle, new Label("Player"));


        this.add(left , 0, 0);
        this.add(right , 1, 0);
        this.add(leftView, 0, 1);
        this.add(rightView, 1, 1);

        FadeTransition fadeTrLeft = new FadeTransition(Duration.millis(500), leftView);
        fadeTrLeft.setFromValue(0.0);
        fadeTrLeft.setToValue(1.0);
        fadeTrLeft.setOnFinished(event1 -> {
            leftHand = random.nextInt(3);
            leftView.setImage(leftImages[leftHand]);
            if (countLeft < 3) {
                countLeft ++;
                fadeTrLeft.play();
            } else {
                countLeft = 0;
            }
        });

        FadeTransition fadeTrRight = new FadeTransition(Duration.millis(500), rightView);
        fadeTrRight.setFromValue(0.0);
        fadeTrRight.setToValue(1.0);
        fadeTrRight.setOnFinished(event1 -> {
            rightHand = random.nextInt(3);
            rightView.setImage(rightImages[rightHand]);
            if (countRight < 3) {
                countRight ++;
                fadeTrRight.play();
            } else {
                countRight = 0;
                handle.handle(leftHand, rightHand);
            }
        });

        this.setOnMouseClicked(event -> {
            if (disable) {
                return;
            }
            if (countLeft == 0 && countRight ==0) {
                fadeTrLeft.play();
                fadeTrRight.play();
            }
        });
    }

    /**
     * This method takes in the values representing the choices of two players in a game of Rock-Paper-Scissors
     * and returns an integer value representing the winner (or a tie).
     * Pre: parameters passed in
     * Post: returns an integer value representing the winner
     * @param leftHand left-hand icon
     * @param rightHand right-hand icon
     * @return int integer value representing the winner (or a tie)
     */

    public static int getWin(int leftHand, int rightHand){
        if ((leftHand == 2 && rightHand == 1) || (leftHand == 1 && rightHand == 0) || leftHand == 0 && rightHand == 2) {
            return 1;
        } else if ((leftHand == 1 && rightHand == 2) || (leftHand == 0 && rightHand == 1) || leftHand == 2 && rightHand == 0) {
            return 2;
        } else {
            return 0;
        }
    }

    //The method sets the value of the "disable" boolean variable in the class to the given input value.
    public void disable(boolean disable){
        this.disable = disable;
    }

    public interface Handle{
        void handle(int leftHand, int rightHand);
    }
}



