package cindy.example.cindy.laissezfaire.oilRefinery;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import cindy.example.cindy.laissezfaire.common.StatusControl;

import java.util.ArrayList;
import java.util.List;

/**
 * OilRefinery Class
 * This class draws and calculates the number of oil barrels in the oil refinery
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class OilRefinery {
    //Attributes/instance variables
    private int totalOilBarrelNum = 25;
    private int alreadyEarnedOilBarrelNum;
    //arrayList to store oil barrels
    private List<Oil> oilBarrelList = new ArrayList<>();
    //the x, y coordinates and the dimensions of the oil refinery
    private double oilRefineryFrameXCoordinate = 11;
    private double oilRefineryFrameYCoordinate = 50;
    private double oilRefineryFrameWidth = 346;
    private double oilRefineryFrameHeight = 250;
    //the space between each oil barrel
    private double oilImageXInterval;
    private double oilImageYInterval;

    //the coordinates, width and height of the tally box
    private double oilRefineryTallyBoxXCoordinate = 210;
    private double oilRefineryTallyBoxYCoordinate = 237;
    private double oilRefineryTallyBoxWidth = 131;
    private double oilRefineryTallyBoxHeight = 50.5;
    //oil barrel image and its width and height
    private Image oilBarrelImage = new Image("oilBarrel.png");
    private double oilBarrelImageWidth;
    private double oilBarrelImageHeight;
    //the initial coordinates of the oil barrel, used as the base for subsequent ones
    private double oilBarrelXCoordinate = 11;
    private double oilBarrelYCoordinate = 50;

    //Default constructor
    public OilRefinery() {
        //calculate the width between the images
        this.oilImageXInterval = oilRefineryFrameWidth / 7 - 1;
        this.oilImageYInterval = oilRefineryFrameHeight / 4 - 2;
        //set width and height to the image
        this.oilBarrelImageWidth = oilImageXInterval;
        this.oilBarrelImageHeight = oilImageYInterval;
    }

    //Getter methods
    public int getTotalOilBarrelNum() {
        return totalOilBarrelNum;
    }
    public int getAlreadyEarnedOilBarrelNum() {
        return alreadyEarnedOilBarrelNum;
    }
    public List<Oil> getOilBarrelList() {
        return oilBarrelList;
    }

    /**
     * This method is used to load the oil refinery section
     * Post: the oil refinery section is loaded
     * @return void This does not return anything
     */
    public void loadOilRefinery() {
        //add every oil barrel's ID and coordinates into the arrayList
        oilBarrelList.add(new Oil(1, oilBarrelXCoordinate, oilBarrelYCoordinate));
        oilBarrelList.add(new Oil(2, oilBarrelXCoordinate + oilImageXInterval, oilBarrelYCoordinate));
        oilBarrelList.add(new Oil(3, oilBarrelXCoordinate + oilImageXInterval * 2, oilBarrelYCoordinate));
        oilBarrelList.add(new Oil(4, oilBarrelXCoordinate + oilImageXInterval * 3, oilBarrelYCoordinate));
        oilBarrelList.add(new Oil(5, oilBarrelXCoordinate + oilImageXInterval * 4, oilBarrelYCoordinate));
        oilBarrelList.add(new Oil(6, oilBarrelXCoordinate + oilImageXInterval * 5, oilBarrelYCoordinate));
        oilBarrelList.add(new Oil(7, oilBarrelXCoordinate + oilImageXInterval * 6, oilBarrelYCoordinate));

        oilBarrelList.add(new Oil(8, oilBarrelXCoordinate, oilBarrelYCoordinate + oilImageYInterval));
        oilBarrelList.add(new Oil(9, oilBarrelXCoordinate + oilImageXInterval, oilBarrelYCoordinate + oilImageYInterval));
        oilBarrelList.add(new Oil(10, oilBarrelXCoordinate + oilImageXInterval * 2, oilBarrelYCoordinate + oilImageYInterval));
        oilBarrelList.add(new Oil(11, oilBarrelXCoordinate + oilImageXInterval * 3, oilBarrelYCoordinate + oilImageYInterval));
        oilBarrelList.add(new Oil(12, oilBarrelXCoordinate + oilImageXInterval * 4, oilBarrelYCoordinate + oilImageYInterval));
        oilBarrelList.add(new Oil(13, oilBarrelXCoordinate + oilImageXInterval * 5, oilBarrelYCoordinate + oilImageYInterval));
        oilBarrelList.add(new Oil(14, oilBarrelXCoordinate + oilImageXInterval * 6, oilBarrelYCoordinate + oilImageYInterval));

        oilBarrelList.add(new Oil(15, oilBarrelXCoordinate, oilBarrelYCoordinate + oilImageYInterval * 2));
        oilBarrelList.add(new Oil(16, oilBarrelXCoordinate + oilImageXInterval, oilBarrelYCoordinate + oilImageYInterval * 2));
        oilBarrelList.add(new Oil(17, oilBarrelXCoordinate + oilImageXInterval * 2, oilBarrelYCoordinate + oilImageYInterval * 2));
        oilBarrelList.add(new Oil(18, oilBarrelXCoordinate + oilImageXInterval * 3, oilBarrelYCoordinate + oilImageYInterval * 2));
        oilBarrelList.add(new Oil(19, oilBarrelXCoordinate + oilImageXInterval * 4, oilBarrelYCoordinate + oilImageYInterval * 2));
        oilBarrelList.add(new Oil(20, oilBarrelXCoordinate + oilImageXInterval * 5, oilBarrelYCoordinate + oilImageYInterval * 2));
        oilBarrelList.add(new Oil(21, oilBarrelXCoordinate + oilImageXInterval * 6, oilBarrelYCoordinate + oilImageYInterval * 2));

        oilBarrelList.add(new Oil(22, oilBarrelXCoordinate, oilBarrelYCoordinate + oilImageYInterval * 3));
        oilBarrelList.add(new Oil(23, oilBarrelXCoordinate + oilImageXInterval, oilBarrelYCoordinate + oilImageYInterval * 3));
        oilBarrelList.add(new Oil(24, oilBarrelXCoordinate + oilImageXInterval * 2, oilBarrelYCoordinate + oilImageYInterval * 3));
        oilBarrelList.add(new Oil(25, oilBarrelXCoordinate + oilImageXInterval * 3, oilBarrelYCoordinate + oilImageYInterval * 3));
    }

    /**
     * This method is used to draw the oil refinery section
     * Pre: the pen and statusControl have been passed in
     * Post: the oil refinery section is drawn
     * @param pen This is the first parameter to draw method
     * @param statusControl This is the second parameter to draw method
     * @return void This does not return anything
     */
    public void draw(GraphicsContext pen, StatusControl statusControl) {
        //background color
        pen.setFill(Color.rgb(243, 229, 216));
        pen.fillRect(oilRefineryFrameXCoordinate, oilRefineryFrameYCoordinate, oilRefineryFrameWidth, oilRefineryFrameHeight);
        //draw the words "Oil Refinery"
        pen.setFill(Color.BLACK);
        pen.setFont(new Font("Impact", 22));
        pen.fillText("Oil Refinery", 11, 40);
        //draw the outer frame
        pen.setLineWidth(5);
        pen.setStroke(Color.BLACK);
        pen.strokeRect(oilRefineryFrameXCoordinate, oilRefineryFrameYCoordinate, oilRefineryFrameWidth, oilRefineryFrameHeight);
        //draw the frame of the tally box
        pen.setLineWidth(3);
        pen.setStroke(Color.GRAY);
        pen.strokeRect(oilRefineryTallyBoxXCoordinate, oilRefineryTallyBoxYCoordinate, oilRefineryTallyBoxWidth, oilRefineryTallyBoxHeight);
        //draw the words "____ barrels of oil" in the tally box
        pen.setFill(Color.GRAY);
        pen.setFont(new Font("Impact", 18));
        pen.fillText("barrels", 275, 260);
        pen.fillText("of oil", 275, 280);
        pen.fillText("____", 220, 277);

        //using the coordinates of the oil barrels to draw the rounded edged rectangles behind them
        pen.setFill(Color.rgb(248, 248, 255));
        for (Oil oil : oilBarrelList) {
            pen.fillRoundRect(oil.getOilBarrelXCoordinate() + 4, oil.getOilBarrelYCoordinate() + 4, oilBarrelImageWidth - 5, oilBarrelImageHeight - 5, 20, 20);
        }
        //if the earnedRewardAddFlag is true
        if (statusControl.rewardDistributed()) {
            //calculate the current number of oil barrels
            if (alreadyEarnedOilBarrelNum == 0) {
                alreadyEarnedOilBarrelNum = statusControl.getJustEarnedOilBarrelNum();
            } else {
                alreadyEarnedOilBarrelNum += statusControl.getJustEarnedOilBarrelNum();
            }
            //if there are more than 25 barrels of oil, only keep 25
            if (alreadyEarnedOilBarrelNum > totalOilBarrelNum) {
                alreadyEarnedOilBarrelNum = totalOilBarrelNum;
            }
            //after calculating, clear the number in statusControl
            statusControl.setJustEarnedOilBarrelNum(0);
        }

        //draw the number of oil barrels that is currently there in the tally box
        pen.setFill(Color.RED);
        pen.setFont(new Font("Impact", 30));
        pen.fillText(String.valueOf(alreadyEarnedOilBarrelNum), 225, 273);

        //based on the number in the tally box, draw the same number of oil barrels images
        for (int i = 0; i < alreadyEarnedOilBarrelNum; i++) {
            //get the number i oil from the arrayList
            Oil oil = oilBarrelList.get(i);
            //draw the oil barrels based on their coordinates
            pen.drawImage(oilBarrelImage, oil.getOilBarrelXCoordinate(), oil.getOilBarrelYCoordinate(), oilBarrelImageWidth, oilBarrelImageHeight);
            //get the coordinates of the next oil barrel
            if (i == 24) {
                continue;
            }
            Oil nextOil = oilBarrelList.get(i + 1);
            statusControl.setNextOilImageXCoordinate(nextOil.getOilBarrelXCoordinate());
            statusControl.setNextOilImageYCoordinate(nextOil.getOilBarrelYCoordinate());
        }
    }
}
