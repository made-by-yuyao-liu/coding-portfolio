package cindy.example.cindy.laissezfaire;

import cindy.example.cindy.base.Matrix;
import cindy.example.cindy.base.Mouse;
import cindy.example.cindy.base.Window;
import cindy.example.cindy.laissezfaire.bank.Bank;
import cindy.example.cindy.laissezfaire.common.StatusControl;
import cindy.example.cindy.laissezfaire.gridBoard.Dice;
import cindy.example.cindy.laissezfaire.gridBoard.GridBoard;
import cindy.example.cindy.laissezfaire.instructions.Instructions;
import cindy.example.cindy.laissezfaire.oilRefinery.OilRefinery;
import cindy.example.cindy.laissezfaire.reward.RewardContainer;
import cindy.example.cindy.laissezfaire.town.Town;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

/**
 * GamePage Class
 * This class draws and controls the game page of this game
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class GamePage {
    //Attributes/instance variables
    private String playerImageName;
    private GridBoard gridBoard;
    private OilRefinery oilRefinery = new OilRefinery();
    private Bank bank = new Bank();
    private Dice dice = new Dice();
    private Town town = new Town();
    private RewardContainer rewardContainer = new RewardContainer();
    private Instructions instructions = new Instructions();
    private StatusControl statusControl = new StatusControl();
    private int endFrameLoopNumber = 480;
    private boolean gameOverFlag = false;
    private boolean gameSuccessFlag = false;
    private String gameSuccessConditions;
    private boolean gameFailureFlag = false;

    //Parameterized constructor
    public GamePage(String playerImageName) {
        this.playerImageName = playerImageName;
    }

    /**
     * This method is used load the game page
     * Pre: the window has been passed in
     * Post: the game page is loaded
     * @param window This is the first parameter to loadPage method
     * @return void This does not return anything
     */
    public void loadGamePage(Window window) {
        gridBoard = new GridBoard(playerImageName);
        window.setBackground(Color.CORNSILK);
        //load the oil refinery section
        oilRefinery.loadOilRefinery();
        //load the grid board
        gridBoard.loadGridBoard();
        //load the 12 different reward combinations
        rewardContainer.loadRewards();
    }

    /**
     * This method is used to draw the back button
     * Pre: the pen is passed in
     * Post: the back button is drawn
     * @param pen This is the first parameter to drawBackButton method
     * @return void This does not return anything
     */
    private void drawBackButton(GraphicsContext pen) {
        //draw the circle
        pen.setFill(Color.GREEN);
        pen.fillArc(340, 620, 75, 75, 0, 360, ArcType.CHORD);

        //draw the words "Home Page"
        pen.setFill(Color.BLACK);
        pen.setFont(new Font("Impact", 18));
        pen.setLineWidth(1);
        pen.fillText("Home", 355, 657);
        pen.fillText("Page", 355, 678);
    }

    /**
     * This method is used to draw the replay button
     * Pre: the pen has been passed in
     * Post: the replay button is drawn
     * @param pen This is the first parameter to drawReplayButton method
     * @return void This does not return anything
     */
    private void drawReplayButton(GraphicsContext pen) {
        //draw the pentagon
        pen.setFill(Color.PINK);
        pen.fillPolygon(new double[]{275, 240, 255, 295, 310}, new double[]{625, 660, 695, 695, 660}, 5);

        //draw the word "Replay"
        pen.setFill(Color.BLACK);
        pen.setFont(new Font("Impact", 18));
        pen.setLineWidth(1);
        pen.fillText("Replay", 250, 670);
    }

    /**
     * This method is used to draw the roll dice button
     * Pre: the pen has been passed in
     * Post: the roll dice button is drawn
     * @param pen This is the first parameter to drawDiceButton method
     * @return void This does not return anything
     */
    private void drawDiceButton(GraphicsContext pen) {
        //draw the square
        pen.setFill(Color.GREEN);
        pen.fillRect(140, 625, 70, 70);

        //draw the words "Roll dice"
        pen.setFill(Color.BLACK);
        pen.setFont(new Font("Impact", 16));
        pen.setLineWidth(1);
        pen.fillText("Roll dice", 146, 643);
    }

    /**
     * This method is used to draw the game page
     * Pre: the pen and matrix have been passed in
     * Post: the game page has been drawn
     * @param pen This is the first parameter to draw method
     * @param matrix This is the second parameter to draw method
     * @return void This does not return anything
     */
    public void draw(GraphicsContext pen, Matrix matrix) {
        //draw the oil refinery
        oilRefinery.draw(pen, statusControl);
        //draw the bank
        bank.draw(pen, statusControl);
        //draw the town
        town.draw(pen, statusControl);
        //draw the gridBoard
        gridBoard.draw(pen, statusControl);
        //draw the reward
        rewardContainer.draw(pen, matrix, statusControl);
        //draw the instructions
        instructions.draw(pen);
        //draw the dice and the dice button
        drawDiceButton(pen);
        dice.draw(pen, statusControl);
        //draw the replay button
        drawReplayButton(pen);
        //draw the back (home page) button
        drawBackButton(pen);
        //judge whether the game is continuing
        gameContinued(statusControl);
        //draw game over page when the game ends
        if (gameOverFlag) {
            loadEndPage(pen);
        }
    }

    /**
     * This method is used to judge whether the game is continuing
     * Pre: the statusControl is passed in
     * Post: determine whether the player is still playing
     * @param statusControl This is the first parameter to isGameContinue method
     * @return int This returns sum of numA and numB.
     */
    public void gameContinued(StatusControl statusControl) {
        //get the player's position on the grid
        double playerCenterX = gridBoard.getPlayerCenterXCoordinate();
        double playerCenterY = gridBoard.getPlayerCenterYCoordinate();
        //get the horizontal and vertical intervals of the grid
        int xInterval = gridBoard.getEachGridWidth();
        int yInterval = gridBoard.getEachGridHeight();
        //determine if the player has reached the end of the grid. playerEnd is true when the player has reached the end of the grid
        boolean playerEnd = playerCenterX > 20 + xInterval * 12 && (playerCenterY >= 611 - yInterval * 9 && playerCenterY < 611 - yInterval * 8);
        //get the current number of oil barrels
        int oilNum = oilRefinery.getAlreadyEarnedOilBarrelNum();
        //get the current number of money
        int amount = bank.getCurrentCash();
        //get the current number of coffee shops
        int coffeeNum = town.getAlreadyEarnedCoffeeShopNum();
        //get the current number of houses
        int houseNum = town.getAlreadyEarnedHouseNum();
        // if total oil barrels is 25, or there is $10 000, or there are 5 houses and 5 coffee shops, rewardEnd is true and the player has won
        boolean rewardEnd = oilNum == 25 || amount == 10000 || (coffeeNum == 5 && houseNum == 5);

        if (rewardEnd) {
            if (oilNum == 25) {
                //if the player won by collecting 25 oil barrels, this is printed
                gameSuccessConditions = "You got 25 barrels of oil!";
            } else if (amount == 10000) {
                //if the player won by collecting $10 000, this is printed
                gameSuccessConditions = "You got $10000 in your bank account!";
            } else if (coffeeNum == 5 && houseNum == 5) {
                //if the player won by collecting 5 coffee shops and 5 houses, this is printed
                gameSuccessConditions = "You got 5 coffee shops and 5 houses!";
            }

            //the gameSuccessFlag is set as true
            this.gameSuccessFlag = true;
            //set gameOverFlag to true
            this.gameOverFlag = true;
        } else if (playerEnd && !rewardEnd) {
            //if the player has reached the end of the grid and none of the requirements were meet
            //the player loses
            this.gameFailureFlag = true;
            //the game is over
            this.gameOverFlag = true;
        }

    }

    /**
     * This method is used to load the end result
     * Pre: the pen has been passed in
     * Post: the end page is loaded
     * @param pen This is the first parameter to loadEndPage method
     * @return void This does not return anything
     */
    private void loadEndPage(GraphicsContext pen) {
        //draw rectangle
        pen.setFill(Color.WHITE);
        pen.fillRect(300, 200, 500, 300);

        //draw words
        pen.setFill(Color.BLACK);
        pen.setFont(new Font("Impact", 72));
        //if the player won
        if (gameSuccessFlag) {
            //draw ""YOU WIN!!!"
            pen.fillText("YOU WIN!!!", 340, 350);
            pen.setFill(Color.GRAY);
            pen.setFont(new Font("Impact", 24));
            pen.fillText(gameSuccessConditions, 340, 400);
        } else if (gameFailureFlag) {
            //if the player lost, draw ""YOU LOSE"
            pen.fillText("YOU LOSE", 360, 370);
        }

        pen.setLineWidth(10);
        pen.beginPath();

        //draw the dotted top horizontal outline
        if (endFrameLoopNumber % 4 == 0) {
            pen.setStroke(Color.PINK);
        } else {
            pen.setStroke(Color.BLACK);
        }
        pen.moveTo(300, 220);
        pen.lineTo(320, 220);

        pen.moveTo(340, 220);
        pen.lineTo(360, 220);

        pen.moveTo(380, 220);
        pen.lineTo(400, 220);

        pen.moveTo(420, 220);
        pen.lineTo(440, 220);

        pen.moveTo(460, 220);
        pen.lineTo(480, 220);

        pen.moveTo(500, 220);
        pen.lineTo(520, 220);

        pen.moveTo(540, 220);
        pen.lineTo(560, 220);

        pen.moveTo(580, 220);
        pen.lineTo(600, 220);

        pen.moveTo(620, 220);
        pen.lineTo(640, 220);

        pen.moveTo(660, 220);
        pen.lineTo(680, 220);

        pen.moveTo(700, 220);
        pen.lineTo(720, 220);

        pen.moveTo(740, 220);
        pen.lineTo(760, 220);

        pen.moveTo(780, 220);
        pen.lineTo(800, 220);

        //draw the dotted right vertical outline
        if (endFrameLoopNumber % 4 == 1) {
            pen.setStroke(Color.INDIANRED);
        } else {
            pen.setStroke(Color.BLACK);
        }
        pen.moveTo(800, 220);
        pen.lineTo(800, 240);

        pen.moveTo(800, 260);
        pen.lineTo(800, 280);

        pen.moveTo(800, 300);
        pen.lineTo(800, 320);

        pen.moveTo(800, 340);
        pen.lineTo(800, 360);

        pen.moveTo(800, 380);
        pen.lineTo(800, 400);

        pen.moveTo(800, 420);
        pen.lineTo(800, 440);

        pen.moveTo(800, 460);
        pen.lineTo(800, 480);

        //draw the dotted left vertical outline
        if (endFrameLoopNumber % 4 == 2) {
            pen.setStroke(Color.INDIANRED);
        } else {
            pen.setStroke(Color.BLACK);
        }
        pen.moveTo(300, 220);
        pen.lineTo(300, 240);

        pen.moveTo(300, 260);
        pen.lineTo(300, 280);

        pen.moveTo(300, 300);
        pen.lineTo(300, 320);

        pen.moveTo(300, 340);
        pen.lineTo(300, 360);

        pen.moveTo(300, 380);
        pen.lineTo(300, 400);

        pen.moveTo(300, 420);
        pen.lineTo(300, 440);

        pen.moveTo(300, 460);
        pen.lineTo(300, 480);

        //draw the dotted bottom horizontal outline
        if (endFrameLoopNumber % 4 == 3) {
            pen.setStroke(Color.INDIANRED);
        } else {
            pen.setStroke(Color.BLACK);
        }
        pen.moveTo(300, 500);
        pen.lineTo(320, 500);

        pen.moveTo(340, 500);
        pen.lineTo(360, 500);

        pen.moveTo(380, 500);
        pen.lineTo(400, 500);

        pen.moveTo(420, 500);
        pen.lineTo(440, 500);

        pen.moveTo(460, 500);
        pen.lineTo(480, 500);

        pen.moveTo(500, 500);
        pen.lineTo(520, 500);

        pen.moveTo(540, 500);
        pen.lineTo(560, 500);

        pen.moveTo(580, 500);
        pen.lineTo(600, 500);

        pen.moveTo(620, 500);
        pen.lineTo(640, 500);

        pen.moveTo(660, 500);
        pen.lineTo(680, 500);

        pen.moveTo(700, 500);
        pen.lineTo(720, 500);

        pen.moveTo(740, 500);
        pen.lineTo(760, 500);

        pen.moveTo(780, 500);
        pen.lineTo(800, 500);
        pen.stroke();

        //control the color of the dotted outline
        if (endFrameLoopNumber > 0) {
            endFrameLoopNumber--;
        }
    }

    /**
     * This method is used to control the mouse click
     * Pre: the mouse has been passed in
     * Post: a number between 0 and 3 is returned
     * @param mouse This is the first parameter to mouseClicked method
     * @return int This returns a number between 0 and 3
     */
    public int mouseClicked(Mouse mouse) {
        double x = mouse.x;
        double y = mouse.y;

        boolean diceClicked = (x > 140 && x < 210) && (y > 625 && y < 695);
        boolean replayClicked = (x > 245 && x < 305) && (y > 650 && x < 670);
        boolean backClicked = (x > 340 && x < 425) && (y > 620 && x < 695);
        if (diceClicked) {
            //if the dice is clicked, the number 1 is returned
            return 1;
        } else if (replayClicked) {
            //if the replay button is clicked, the number 2 is returned
            return 2;
        } else if (backClicked) {
            //if the back button is clicked, the number 3 is returned
            return 3;
        }
        return 0;
    }

    /**
     * This method is used to control the dice roll
     * Post: the dice is rolled
     * @return void This does not return anything
     */
    public void diceControl() {
        if (!gameOverFlag && !statusControl.playerMoved() && statusControl.rewardDistributed()) {
            //if the game has not ended, the player is not moving and the reward has been distributed
            //get the number of points on the dice
            int dicePointNum = dice.rollDice();
            statusControl.setPointNumber(dicePointNum);
            //set the PlayerMovedNum back to 50
            statusControl.setPlayerMovedNum(50);
            //count and store the reward earned
            rewardContainer.countEarnedReward(statusControl);
            //initialize rewards
            rewardContainer.initialize();
            //the reward is not shown when the dice is rolling
            statusControl.setShowReward(false);
        }

        //set the initial state of the dice to false
        statusControl.setDiceInitState(false);
        //the rewards are not distributed into their sections right away, they are distributed after the translating of the images
        statusControl.setRewardDistributed(false);
    }
}
