package cindy.example.cindy.laissezfaire.gridBoard;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import cindy.example.cindy.laissezfaire.common.StatusControl;

import java.util.ArrayList;
import java.util.List;

/**
 * GridBoard Class
 * This class draws and controls the grid board
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class GridBoard {
    //Attributes/instance variables
    //dimensions of the grid
    private double gridBoardWidth = 500;
    private double gridBoardHeight = 315;

    //the start coordinates of the grid
    private double girdBoardXCoordinate = 20;
    private double gridBoardYCoordinate = 320;

    //the width and height of the grid
    private int eachGridWidth;
    private int eachGridHeight;

    //arrayList to store the grids
    private List<Grid> gridList = new ArrayList<>();

    //the name of the character and the name of the character
    private String playerImageName;
    private Image playerImage;

    //the current coordinates and dimensions of the player
    private double playerImageXCoordinate;
    private double playerImageYCoordinate;
    private double playerImageWidth;
    private double playerImageHeight;

    //the center coordinates of the character
    private double playerCenterXCoordinate;
    private double playerCenterYCoordinate;

    //Getter methods
    public double getPlayerCenterXCoordinate() {
        return playerCenterXCoordinate;
    }
    public double getPlayerCenterYCoordinate() {
        return playerCenterYCoordinate;
    }
    public int getEachGridWidth() {
        return eachGridWidth;
    }
    public int getEachGridHeight() {
        return eachGridHeight;
    }


    //Parameterized constructor
    public GridBoard(String playerImageName) {
        this.eachGridWidth = (int) Math.round(gridBoardWidth / 12);
        this.eachGridHeight = (int) Math.round(gridBoardHeight / 10);
        this.playerImageName = playerImageName;
        this.playerImage = new Image(playerImageName);
    }

    /**
     * This method is used to load the grid board
     * Post: the gird board is created
     * @return void This does not return anything
     */
    public void loadGridBoard() {
        //add grids into the arrayList
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                if (i >= 9 && j >= 1) {
                    continue;
                }
                gridList.add(new Grid(girdBoardXCoordinate + eachGridWidth * j, gridBoardYCoordinate + eachGridHeight * i));
            }
        }


        //Based on the player's name, set picture coordinates and coordinates of width, height and center position of the players
        if (playerImageName.equals("Knight.png")) {
            this.playerImageXCoordinate = 13.5;
            this.playerImageYCoordinate = 590;
            this.playerImageWidth = 50;
            this.playerImageHeight = 47;

            this.playerCenterXCoordinate = 38.5;
            this.playerCenterYCoordinate = 613.5;
        } else if (playerImageName.equals("Pirate.png")) {
            this.playerImageXCoordinate = 5;
            this.playerImageYCoordinate = 590;
            this.playerImageWidth = 75;
            this.playerImageHeight = 50;

            this.playerCenterXCoordinate = 42.5;
            this.playerCenterYCoordinate = 615;
        } else if (playerImageName.equals("Ninja.png")) {
            this.playerImageXCoordinate = 10;
            this.playerImageYCoordinate = 590;
            this.playerImageWidth = 65;
            this.playerImageHeight = 50;

            this.playerCenterXCoordinate = 42.5;
            this.playerCenterYCoordinate = 615;
        } else if (playerImageName.equals("Warrior.png")) {
            this.playerImageXCoordinate = 0;
            this.playerImageYCoordinate = 593;
            this.playerImageWidth = 87;
            this.playerImageHeight = 50;

            this.playerCenterXCoordinate = 43.5;
            this.playerCenterYCoordinate = 618;
        }
    }

    /**
     * This method is used to draw the grid board
     * Pre: the pen and statusControl have been passed in
     * Post: the grid board is drawn
     * @param pen This is the first parameter to draw method
     * @param statusControl This is the second parameter to draw method
     * @return void This does not return anything
     */
    public void draw(GraphicsContext pen, StatusControl statusControl) {
        pen.setFont(new Font("Impact", 18));
        pen.setLineWidth(5);

        //draw the grid board using loops
        pen.setFill(Color.GREEN);
        for (int i = 0; i < gridList.size(); i++) {
            //Based on conditions, color alternation is achieved
            if (i == 12) {
                pen.setFill(Color.INDIANRED);
            }
            if (i == 24) {
                pen.setFill(Color.GREEN);
            }
            if (i == 36) {
                pen.setFill(Color.INDIANRED);
            }
            if (i == 48) {
                pen.setFill(Color.GREEN);
            }
            if (i == 60) {
                pen.setFill(Color.INDIANRED);
            }
            if (i == 72) {
                pen.setFill(Color.GREEN);
            }
            if (i == 84) {
                pen.setFill(Color.INDIANRED);
            }
            if (i == 96) {
                pen.setFill(Color.GREEN);
            }
            if (i == 108) {
                pen.setFill(Color.INDIANRED);
            }
            //get number i out of the arrayList
            Grid grid = gridList.get(i);
            //set the color of the grid
            if (pen.getFill().equals(Color.INDIANRED)) {
                pen.setFill(Color.GREEN);
            } else {
                pen.setFill(Color.INDIANRED);
            }

            //take the coordinates and interval and draw grid
            pen.fillRect(grid.getGridXCoordinate(), grid.getGridYCoordinate(), eachGridWidth, eachGridHeight);
            pen.setStroke(Color.BLACK);
            //draw the grid
            pen.strokeRect(grid.getGridXCoordinate(), grid.getGridYCoordinate(), eachGridWidth, eachGridHeight);
        }
        //draw "Start"
        pen.setFont(new Font("Impact", 18));
        pen.setLineWidth(1);
        pen.strokeText("Start", 20, 660);

        //get the number of pips on the dice
        int dicePointNumber = statusControl.getPointNumber();
        //get the grid the player still has left
        int playerMoveNum = statusControl.getPlayerMovedNum();

        //If the pip is greater than 0, and the player moves, and the remaining number of moves by the player is the same as the number of dice points
        if (dicePointNumber > 0 && statusControl.playerMoved() && playerMoveNum == dicePointNumber) {
            //the number of moves by the player is 50
            playerMoveNum = 50;
            //determine the position of the player, get the  position of the player's next step
            if ((playerCenterXCoordinate > 20 && playerCenterXCoordinate < 20 + eachGridWidth) && (playerCenterYCoordinate < 611 + eachGridHeight && playerCenterYCoordinate > 611)) {
                this.playerImageXCoordinate = playerImageXCoordinate;
                this.playerImageYCoordinate = playerImageYCoordinate - eachGridHeight;
            } else if ((playerCenterXCoordinate > 20 && playerCenterXCoordinate < 20 + eachGridWidth * 11) && (playerCenterYCoordinate < 611 && playerCenterYCoordinate > 611 - eachGridHeight)) {
                this.playerImageXCoordinate = playerImageXCoordinate + eachGridWidth;
                this.playerImageYCoordinate = this.playerImageYCoordinate;
            } else if ((playerCenterXCoordinate > 20 + eachGridWidth * 11 && playerCenterXCoordinate < 20 + eachGridWidth * 12) && (playerCenterYCoordinate < 611 && playerCenterYCoordinate > 611 - eachGridHeight)) {
                this.playerImageXCoordinate = this.playerImageXCoordinate;
                this.playerImageYCoordinate = this.playerImageYCoordinate - eachGridHeight;
            } else if ((playerCenterXCoordinate > 20 + eachGridWidth && playerCenterXCoordinate < 20 + eachGridWidth * 12) && (playerCenterYCoordinate > 611 - eachGridHeight * 2 && playerCenterYCoordinate < 611 - eachGridHeight)) {
                this.playerImageXCoordinate = playerImageXCoordinate - eachGridWidth;
                this.playerImageYCoordinate = playerImageYCoordinate;
            } else if ((playerCenterXCoordinate > 20 && playerCenterXCoordinate < 20 + eachGridWidth) & (playerCenterYCoordinate > 611 - eachGridHeight * 2 && playerCenterYCoordinate < 611 - eachGridHeight)) {
                this.playerImageXCoordinate = playerImageXCoordinate;
                this.playerImageYCoordinate = playerImageYCoordinate - eachGridHeight;
            } else if ((playerCenterXCoordinate > 20 && playerCenterXCoordinate < 20 + eachGridWidth * 11) && (playerCenterYCoordinate > 611 - eachGridHeight * 3 && playerCenterYCoordinate < 611 - eachGridHeight * 2)) {
                this.playerImageXCoordinate = playerImageXCoordinate + eachGridWidth;
                this.playerImageYCoordinate = playerImageYCoordinate;
            } else if ((playerCenterXCoordinate > 20 + eachGridWidth * 11 && playerCenterXCoordinate < 20 + eachGridWidth * 12) && (playerCenterYCoordinate > 611 - eachGridHeight * 3 && playerCenterYCoordinate < 611 - eachGridHeight * 2)) {
                this.playerImageXCoordinate = playerImageXCoordinate;
                this.playerImageYCoordinate = playerImageYCoordinate - eachGridHeight;
            } else if ((playerCenterXCoordinate > 20 + eachGridWidth && playerCenterXCoordinate < 20 + eachGridWidth * 12) && (playerCenterYCoordinate > 611 - eachGridHeight * 4 && playerCenterYCoordinate < 611 - eachGridHeight * 3)) {
                this.playerImageXCoordinate = playerImageXCoordinate - eachGridWidth;
                this.playerImageYCoordinate = playerImageYCoordinate;
            } else if ((playerCenterXCoordinate > 20 && playerCenterXCoordinate < 20 + eachGridWidth) && (playerCenterYCoordinate > 611 - eachGridHeight * 4 && playerCenterYCoordinate < 611 - eachGridHeight * 3)) {
                this.playerImageXCoordinate = playerImageXCoordinate;
                this.playerImageYCoordinate = playerImageYCoordinate - eachGridHeight;
            } else if ((playerCenterXCoordinate > 20 && playerCenterXCoordinate < 20 + eachGridWidth * 11) && (playerCenterYCoordinate > 611 - eachGridHeight * 5 && playerCenterYCoordinate < 611 - eachGridHeight * 4)) {
                this.playerImageXCoordinate = playerImageXCoordinate + eachGridWidth;
                this.playerImageYCoordinate = playerImageYCoordinate;
            } else if ((playerCenterXCoordinate > 20 + eachGridWidth * 11 && playerCenterXCoordinate < 20 + eachGridWidth * 12) && (playerCenterYCoordinate > 611 - eachGridHeight * 5 && playerCenterYCoordinate < 611 - eachGridHeight * 4)) {
                this.playerImageXCoordinate = playerImageXCoordinate;
                this.playerImageYCoordinate = playerImageYCoordinate - eachGridHeight;
            } else if ((playerCenterXCoordinate > 20 + eachGridWidth && playerCenterXCoordinate < 20 + eachGridWidth * 12) && (playerCenterYCoordinate > 611 - eachGridHeight * 6 && playerCenterYCoordinate < 611 - eachGridHeight * 5)) {
                this.playerImageXCoordinate = playerImageXCoordinate - eachGridWidth;
                this.playerImageYCoordinate = playerImageYCoordinate;
            } else if ((playerCenterXCoordinate > 20 && playerCenterXCoordinate < 20 + eachGridWidth) && (playerCenterYCoordinate > 611 - eachGridHeight * 6 && playerCenterYCoordinate < 611 - eachGridHeight * 5)) {
                this.playerImageXCoordinate = playerImageXCoordinate;
                this.playerImageYCoordinate = playerImageYCoordinate - eachGridHeight;
            } else if ((playerCenterXCoordinate > 20 && playerCenterXCoordinate < 20 + eachGridWidth * 11) && (playerCenterYCoordinate > 611 - eachGridHeight * 7 && playerCenterYCoordinate < 611 - eachGridHeight * 6)) {
                this.playerImageXCoordinate = playerImageXCoordinate + eachGridWidth;
                this.playerImageYCoordinate = playerImageYCoordinate;
            } else if ((playerCenterXCoordinate > 20 + eachGridWidth * 11 && playerCenterXCoordinate < 20 + eachGridWidth * 12) && (playerCenterYCoordinate > 611 - eachGridHeight * 7 && playerCenterYCoordinate < 611 - eachGridHeight * 6)) {
                this.playerImageXCoordinate = playerImageXCoordinate;
                this.playerImageYCoordinate = playerImageYCoordinate - eachGridHeight;
            } else if ((playerCenterXCoordinate > 20 + eachGridWidth && playerCenterXCoordinate < 20 + eachGridWidth * 12) && (playerCenterYCoordinate > 611 - eachGridHeight * 8 && playerCenterYCoordinate < 611 - eachGridHeight * 7)) {
                this.playerImageXCoordinate = playerImageXCoordinate - eachGridWidth;
                this.playerImageYCoordinate = playerImageYCoordinate;
            } else if ((playerCenterXCoordinate > 20 && playerCenterXCoordinate < 20 + eachGridWidth) && (playerCenterYCoordinate > 611 - eachGridHeight * 8 && playerCenterYCoordinate < 611 - eachGridHeight * 7)) {
                this.playerImageXCoordinate = playerImageXCoordinate;
                this.playerImageYCoordinate = playerImageYCoordinate - eachGridHeight;
            } else if ((playerCenterXCoordinate > 20 && playerCenterXCoordinate < 20 + eachGridWidth * 12) && (playerCenterYCoordinate > 611 - eachGridHeight * 9 && playerCenterYCoordinate < 611 - eachGridHeight * 8)) {
                this.playerImageXCoordinate = playerImageXCoordinate + eachGridWidth;
                this.playerImageYCoordinate = playerImageYCoordinate;
            } else if (playerCenterXCoordinate > 20 + eachGridWidth * 12 && (playerCenterYCoordinate > 611 - eachGridHeight * 9 && playerCenterYCoordinate < 611 - eachGridHeight * 8)) {
                this.playerImageXCoordinate = playerImageXCoordinate;
                this.playerImageYCoordinate = playerImageYCoordinate;
                //When the player moves outside the last grid, leave the player where it is
                playerMoveNum = 0;
            }

            //gets the player's center point coordinates based on the player's latest location
            this.playerCenterXCoordinate = playerImageXCoordinate + (playerImageWidth / 2);
            this.playerCenterYCoordinate = playerImageYCoordinate + (playerImageHeight / 2);
            //each time the character moves, subtract 1 from dice point
            --dicePointNumber;
        }

        //if the movement of the player is greater than 0, it by 1
        if (playerMoveNum > 0) {
            playerMoveNum--;
        }

        //save the number of times the player moved
        statusControl.setPlayerMovedNum(playerMoveNum);
        //save the number of pips on the dice
        statusControl.setPointNumber(dicePointNumber);
        //draw the player based on the coordinates
        pen.drawImage(playerImage, playerImageXCoordinate, playerImageYCoordinate, playerImageWidth, playerImageHeight);

        //if the dice point is 0 and the player moved
        if (dicePointNumber == 0 && statusControl.playerMoved()) {
            //set reward true to start displaying rewards
            statusControl.setShowReward(true);
            //player cannot move
            statusControl.setPlayerMoved(false);
        }

    }
}
