package cindy.example.cindy.laissezfaire.bank;

import cindy.example.cindy.laissezfaire.common.StatusControl;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Bank Class
 * This class draws and calculates the money in the bank section
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class Bank {
    //Attributes/instance variables
    private int totalCash = 10000;
    private int currentCash;

    //the coordinates, height and width of the bank image
    private double bankXCoordinate = 377;
    private double bankYCoordinate = 50;
    private double bankFrameWidth = 346;
    private double bankFrameHeight = 250;

    //the coordinates, height and width of the tally box
    private double bankTallyBoxXCoordinate = 387;
    private double bankTallyBoxYCoordinate = 60;
    private double tallyBoxWidth = 130;
    private double tallyBoxHeight = 55.5;

    //image of the bank and its x, y coordinates nad with width and height
    private Image bankImage = new Image("bank.png");
    private double bankImageXCoordinate = 425;
    private double bankImageYCoordinate = 107;
    private double bankImageWidth = 310;
    private double bankImageHeight = 200;

    //Getter method
    public int getCurrentCash() {
        return currentCash;
    }

    /**
     * This method is used to draw the bank section
     * Pre: the pen and statusControl have been passed in
     * Post: the bank section ios drawn
     * @param pen This is the first parameter to draw method
     * @param statusControl This is the second parameter to draw method
     * @return void This does not return anything
     */
    public void draw(GraphicsContext pen, StatusControl statusControl) {
        //background color
        pen.setFill(Color.rgb(224, 235, 235));
        pen.fillRect(bankXCoordinate, bankYCoordinate, bankFrameWidth, bankFrameHeight);

        //draw the word "Bank"
        pen.setFill(Color.BLACK);
        pen.setFont(new Font("Impact", 22));
        pen.fillText("Bank",377,40);

        //draw the box frame
        pen.setLineWidth(5);
        pen.setStroke(Color.BLACK);
        pen.strokeRect(bankXCoordinate, bankYCoordinate, bankFrameWidth, bankFrameHeight);

        //draw the bank image
        pen.drawImage(bankImage, bankImageXCoordinate, bankImageYCoordinate, bankImageWidth, bankImageHeight);

        //draw the tally box
        pen.setLineWidth(3);
        pen.setStroke(Color.GRAY);
        pen.strokeRect(bankTallyBoxXCoordinate, bankTallyBoxYCoordinate, tallyBoxWidth, tallyBoxHeight);

        //draw "$____"
        pen.setFont(new Font("Impact", 36));
        pen.strokeText("$", 390, 100);
        pen.strokeText("_____", 410, 100);

        //if earn reward is true，add the reward
        if (statusControl.rewardDistributed()) {
            //if the current amount is 0, the current mount is equal to the added amount
            if (currentCash == 0) {
                currentCash = statusControl.getJustEarnedCash();
            } else {
                //if the current amount is not 0, the added amount is added t the current amount
                currentCash += statusControl.getJustEarnedCash();
            }
            //if the current amount is greater than 10 000, then it stays at 10 000
            if (currentCash > totalCash) {
                currentCash = totalCash;
            }
            //after tallying the current reward, clear the EarnedBillAmount
            statusControl.setJustEarnedCash(0);
        }

        //draw the amount of cash
        pen.setFill(Color.GREEN);
        pen.setFont(new Font("Impact", 40));
        pen.fillText(String.valueOf(currentCash), 420, 100);
    }
}
