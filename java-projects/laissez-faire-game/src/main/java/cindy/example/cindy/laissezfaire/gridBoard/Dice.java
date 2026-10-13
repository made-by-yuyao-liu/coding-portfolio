package cindy.example.cindy.laissezfaire.gridBoard;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import cindy.example.cindy.laissezfaire.common.StatusControl;

import java.util.Random;

/**
 * Dice Class
 * This class draws and controls the function of the dice
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class Dice {
    //Attributes/instance variables
    //the current pip shown on the die, the default set as 1
    private int currentDicePoint = 1;

    //the number of times the dice rolls
    private int rollNumber = 50;

    //image of pips for each of the 6 faces of a die
    private Image onePointImage = new Image("pointOneImage.png");
    private Image twoPointsImage = new Image("pointTwoImage.png");
    private Image threePointsImage = new Image("pointThreeImage.png");
    private Image fourPointsImage = new Image("pointFourImage.png");
    private Image fivePointsImage = new Image("pointFiveImage.png");
    private Image sixPointsImage = new Image("pointSixImage.png");

    //images for the rolling die
    private Image rollingDiceImage1 = new Image("rollingDiceImage1.png");
    private Image rollingDiceImage2 = new Image("rollingDiceImage2.png");
    private Image rollingDiceImage3 = new Image("rollingDiceImage3.png");
    private Image rollingDiceImage4 = new Image("rollingDiceImage4.png");
    private Image rollingDiceImage5 = new Image("rollingDiceImage5.png");

    /**
     * This method is used to draw the dice
     * Pre: the pen and statusControl have been passed in
     * Post: the dice is drawn
     * @param pen This is the first parameter to draw method
     * @param statusControl This is the second parameter to draw method
     * @return void This does not return anything
     */
    public void draw(GraphicsContext pen, StatusControl statusControl) {
        //if the dice is in its default state
        if (statusControl.diceAtInitState()) {
            //draw the one pip image
            pen.drawImage(onePointImage, 146, 643, 57, 57);
            return;
        }

            //If the number of remaining spins is the same as the number of pips, stop the spinning and start drawing the dice
        if (rollNumber == currentDicePoint) {
            switch (currentDicePoint) {
                case 1:
                    pen.drawImage(onePointImage, 146, 643, 57, 57);
                    break;
                case 2:
                    pen.drawImage(twoPointsImage, 146, 643, 57, 57);
                    break;
                case 3:
                    pen.drawImage(threePointsImage, 146, 643, 57, 57);
                    break;
                case 4:
                    pen.drawImage(fourPointsImage, 146, 643, 57, 57);
                    break;
                case 5:
                    pen.drawImage(fivePointsImage, 146, 643, 57, 57);
                    break;
                case 6:
                    pen.drawImage(sixPointsImage, 146, 643, 57, 57);
                    break;
                default:
                    pen.drawImage(onePointImage, 146, 643, 57, 57);
                    break;
            }
        } else if (rollNumber > 0) {

            //If the number of remaining spins of the dice is greater than 0
            //subtract 1 from the number of remaining spins of the dice
            rollNumber--;

            //divide the number of rotations by 5 and take the remainder
            int num = rollNumber % 5;
            //draw the corresponding rotated picture according to the remainder
            switch (num) {
                case 0:
                    pen.drawImage(rollingDiceImage1, 146, 643, 57, 57);
                    break;
                case 1:
                    pen.drawImage(rollingDiceImage2, 146, 643, 57, 57);
                    break;
                case 2:
                    pen.drawImage(rollingDiceImage3, 146, 643, 57, 57);
                    break;
                case 3:
                    pen.drawImage(rollingDiceImage4, 146, 643, 57, 57);
                    break;
                case 4:
                    pen.drawImage(rollingDiceImage5, 146, 643, 57, 57);
                    break;
                default:
                    pen.drawImage(rollingDiceImage1, 146, 643, 57, 57);
                    break;
            }

            //if the number of remaining spins is equal to the current number of dice, set the player status to true
            if (rollNumber == currentDicePoint) {
                statusControl.setPlayerMoved(true);
            }
        }
    }

    //Get a random number from 1-6
    private int getRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(6) + 1;
        return randomNumber;
    }

    /**
     * This method is used to roll the dice
     * Post: the dice is rolled
     * @return int This returns the number of pips shown on the dice
     */
    public int rollDice() {
        //get the number of pips on the dice
        int point = getRandomNumber();
        // give the number of pips to currentDicePoint
        this.currentDicePoint = point;
        //set the number of dice spins to 50
        this.rollNumber = 50;
        return point;
    }
}
