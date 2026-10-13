package cindy.example.cindy.laissezfaire.town;

import cindy.example.cindy.laissezfaire.common.StatusControl;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Town Class
 * This class draws and calculates the number of houses and coffee shops in the town section
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class Town {
    //Attributes/instance variables
    private int totalCoffeeShopNum = 5;
    private int alreadyEarnedCoffeeShopNum;
    private int totalHouseNum = 5;
    private int alreadyEarnedHouseNum;

    private double townFrameXCoordinate = 743;
    private double townFrameYCoordinate = 50;
    private double townFrameWidth = 346;
    private double townFrameHeight = 250;

    private double coffeeShopInnerBoxXCoordinate = 763;
    private double coffeeShopInnerBoxYCoordinate = 95;
    private double coffeeShopInnerBoxWidth = 306;
    private double coffeeShopInnerBoxHeight = 60;

    private double coffeeShopTitleXCoordinate = 763;
    private double coffeeShopTitleYCoordinate = 85;
    private double coffeeShopTitleWidth = 230.7;

    private double houseInnerBoxYCoordinate = 210;
    private double houseInnerBoxWidth = 306;
    private double houseInnerBoxHeight = 60;

    private double houseTitleXCoordinate = 763;
    private double houseTitleYCoordinate = 200;
    private double houseTitleWidth = 153;

    private Image coffeeShopImage = new Image("coffee.png");
    private double coffeeShopImageXCoordinate = 768;
    private double coffeeShopImageYCoordinate = 100;
    private double coffeeShopImageWidth = 51.2;
    private double coffeeShopImageHeight = 50;

    private Image houseImage = new Image("house.png");
    private double houseImageXCoordinate = 768;
    private double houseImageYCoordinate = 220;
    private double houseImageWidth = 51.2;
    private double houseImageHeight = 50;

    //Default constructor
    public Town() {
    }

    //Getter methods
    public int getAlreadyEarnedHouseNum() {
        return alreadyEarnedHouseNum;
    }
    public int getAlreadyEarnedCoffeeShopNum() {
        return alreadyEarnedCoffeeShopNum;
    }


    /**
     * This method is used to draw the town section
     * Pre: the pen and statusControl have been passed in
     * Post: the town area is drawn
     * @param pen This is the first parameter to draw method
     * @param statusControl This is the second parameter to draw method
     * @return void This does not return anything
     */
    public void draw(GraphicsContext pen, StatusControl statusControl) {
        //background color
        pen.setFill(Color.rgb(255, 235, 204));
        pen.fillRect(townFrameXCoordinate, townFrameYCoordinate, townFrameWidth, townFrameHeight);

        //draw the word "Town"
        pen.setFill(Color.BLACK);
        pen.setFont(new Font("Impact", 22));
        pen.fillText("Town", 743, 40);
        //draw the box frame
        pen.setLineWidth(5);
        pen.setStroke(Color.BLACK);
        pen.strokeRect(townFrameXCoordinate, townFrameYCoordinate, townFrameWidth, townFrameHeight);
        //draw the words "Coffee Shops : _____"
        pen.setLineWidth(2);
        pen.setStroke(Color.GRAY);
        pen.setFont(new Font("Impact", 27));
        pen.strokeText("Coffee Shops : _____", coffeeShopTitleXCoordinate, coffeeShopTitleYCoordinate, coffeeShopTitleWidth);
        //draw the box frame for houses
        pen.setFont(new Font("Impact", 27));
        pen.setLineWidth(2);
        pen.setStroke(Color.GRAY);
        pen.strokeText("Houses : _____", houseTitleXCoordinate, houseTitleYCoordinate, houseTitleWidth);

        //if a town reward has been earned
        if (statusControl.rewardDistributed()) {
            //calculate the number of coffee shops
            if (alreadyEarnedCoffeeShopNum == 0) {
                alreadyEarnedCoffeeShopNum = statusControl.getJustEarnedCoffeeShopNum();
            } else {
                alreadyEarnedCoffeeShopNum += statusControl.getJustEarnedCoffeeShopNum();
            }
            if (alreadyEarnedCoffeeShopNum > totalCoffeeShopNum) {
                alreadyEarnedCoffeeShopNum = totalCoffeeShopNum;
            }
            //calculate the number of houses
            if (alreadyEarnedHouseNum == 0) {
                alreadyEarnedHouseNum = statusControl.getJustEarnedHouseNum();
            } else {
                alreadyEarnedHouseNum += statusControl.getJustEarnedHouseNum();
            }
            if (alreadyEarnedHouseNum > totalHouseNum) {
                alreadyEarnedHouseNum = totalHouseNum;
            }
            //clear after done calculations
            statusControl.setJustEarnedCoffeeShopNum(0);
            statusControl.setJustEarnedHouseNum(0);
        }
        //draw the number of coffee shops
        pen.setFill(Color.RED);
        pen.setFont(new Font("Impact", 28));
        pen.fillText(String.valueOf(alreadyEarnedCoffeeShopNum), 940, 85);

        //draw the number of houses
        pen.setFill(Color.RED);
        pen.setFont(new Font("Impact", 28));
        pen.fillText(String.valueOf(alreadyEarnedHouseNum), 863, 200);

        //draw the round edged squares for coffee shops and houses
        pen.setLineWidth(2);
        pen.setStroke(Color.SILVER);
        double widthInterval = coffeeShopInnerBoxWidth / 5;
        for (int i = 0; i < 5; i++) {
            double v = coffeeShopInnerBoxXCoordinate + i * widthInterval;
            pen.strokeRoundRect(v, coffeeShopInnerBoxYCoordinate, coffeeShopInnerBoxWidth / 5, coffeeShopInnerBoxHeight, 20, 20);
            pen.strokeRoundRect(v, houseInnerBoxYCoordinate, houseInnerBoxWidth / 5, houseInnerBoxHeight, 20, 20);
        }
        //draw coffee shop images based on the number of coffee shops calculated
        for (int i = 0; i < alreadyEarnedCoffeeShopNum; i++) {
            pen.drawImage(coffeeShopImage, coffeeShopImageXCoordinate + coffeeShopInnerBoxWidth / 5 * i, coffeeShopImageYCoordinate, coffeeShopImageWidth, coffeeShopImageHeight);
            //save the x-coordinates of the next coffee shop image
            statusControl.setNextCoffeeImageX(houseImageXCoordinate + coffeeShopInnerBoxWidth / 5 * (i + 1));
        }
        //draw house images based on the number of houses calculated
        for (int i = 0; i < alreadyEarnedHouseNum; i++) {
            pen.drawImage(houseImage, houseImageXCoordinate + houseInnerBoxWidth / 5 * i, houseImageYCoordinate, houseImageWidth, houseImageHeight);
            //save the x-coordinates of the next house image
            statusControl.setNextHouseImageXCoordinate(houseImageXCoordinate + houseInnerBoxWidth / 5 * (i + 1));
        }
    }
}
