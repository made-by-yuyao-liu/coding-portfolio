package cindy.example.cindy.laissezfaire.common;

/**
 * StatusControl Class
 * This class is the common state control class for recording and passing data
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class StatusControl {
    //Attributes/instance variables
    //the number on the dice
    private int dicePointNumber;
    //the status of the player
    private boolean playerMoved = false;
    //initial state of the dice
    private boolean diceInitState = true;
    //character movement
    private int playerMovedNum;
    //show reward boolean
    private boolean showReward;
    //add reward boolean
    private boolean rewardDistributed = true;
    //current amount of money
    private int justEarnedCash;
    //the number of coffee shops
    private int justEarnedCoffeeShopNum;
    //the number of house
    private int justEarnedHouseNum;
    //the number of oil barrels
    private int justEarnedOilBarrelNum;
    //the coordinates of the next oil barrel image
    private double nextOilImageXCoordinate =11;
    private double nextOilImageYCoordinate =50;
    //the position of the bank image
    private double bankTallyBoxXCoordinate = 377;
    private double bankTallyBoxYCoordinate = 50;
    //position of the next coffee shop image
    private double nextCoffeeImageXCoordinate =768;
    private double nextCoffeeImageYCoordinate =100;
    //position of the next house image
    private double nextHouseImageXCoordinate =768;
    private double nextHouseImageYCoordinate =220;

    //Default constructor
    public StatusControl() {
    }

    //Getter methods
    public double getNextOilImageXCoordinate() {
        return nextOilImageXCoordinate;
    }
    public double getNextOilImageYCoordinate() {
        return nextOilImageYCoordinate;
    }
    public double getBankTallyBoxXCoordinate() {
        return bankTallyBoxXCoordinate;
    }
    public double getBankTallyBoxYCoordinate() {
        return bankTallyBoxYCoordinate;
    }
    public double getNextCoffeeImageX() {
        return nextCoffeeImageXCoordinate;
    }
    public double getNextCoffeeImageYCoordinate() {
        return nextCoffeeImageYCoordinate;
    }
    public double getNextHouseImageXCoordinate() {
        return nextHouseImageXCoordinate;
    }
    public double getNextHouseImageYCoordinate() {
        return nextHouseImageYCoordinate;
    }
    public int getJustEarnedCash() {
        return justEarnedCash;
    }
    public int getJustEarnedCoffeeShopNum() {
        return justEarnedCoffeeShopNum;
    }
    public int getJustEarnedHouseNum() {
        return justEarnedHouseNum;
    }
    public int getJustEarnedOilBarrelNum() {
        return justEarnedOilBarrelNum;
    }
    public int getPlayerMovedNum() {
        return playerMovedNum;
    }
    public int getPointNumber() {
        return dicePointNumber;
    }

    //Setter methods
    public void setNextOilImageXCoordinate(double nextOilImageXCoordinate) {
        this.nextOilImageXCoordinate = nextOilImageXCoordinate;
    }
    public void setNextOilImageYCoordinate(double nextOilImageYCoordinate) {
        this.nextOilImageYCoordinate = nextOilImageYCoordinate;
    }
    public void setNextCoffeeImageX(double nextCoffeeImageX) {
        this.nextCoffeeImageXCoordinate = nextCoffeeImageX;
    }

    public void setNextHouseImageXCoordinate(double nextHouseImageXCoordinate) {
        this.nextHouseImageXCoordinate = nextHouseImageXCoordinate;
    }

    public void setRewardDistributed(boolean rewardDistributed) {
        this.rewardDistributed = rewardDistributed;
    }
    public void setJustEarnedCash(int justEarnedCash) {
        this.justEarnedCash = justEarnedCash;
    }
    public void setJustEarnedCoffeeShopNum(int justEarnedCoffeeShopNum) {
        this.justEarnedCoffeeShopNum = justEarnedCoffeeShopNum;
    }
    public void setJustEarnedHouseNum(int justEarnedHouseNum) {
        this.justEarnedHouseNum = justEarnedHouseNum;
    }
    public void setJustEarnedOilBarrelNum(int justEarnedOilBarrelNum) {
        this.justEarnedOilBarrelNum = justEarnedOilBarrelNum;
    }
    public void setShowReward(boolean showReward) {
        this.showReward = showReward;
    }
    public void setPlayerMovedNum(int playerMovedNum) {
        this.playerMovedNum = playerMovedNum;
    }
    public void setDiceInitState(boolean diceInitState) {
        this.diceInitState = diceInitState;
    }
    public void setPointNumber(int pointNumber) {
        this.dicePointNumber = pointNumber;
    }
    public void setPlayerMoved(boolean playerMoved) {
        this.playerMoved = playerMoved;
    }

    /**
     * This method is used to determine whether the reward is shown
     * Post: true or false
     * @return boolean This returns true or false
     */
    public boolean showReward() {
        return showReward;
    }

    /**
     * This method is used to determine whether the dice is initialized
     * Post: true or false
     * @return boolean This returns true or false
     */
    public boolean diceAtInitState() {
        return diceInitState;
    }

    /**
     * This method is used to determine whether the player has moved
     * Post: true or false
     * @return boolean This returns true or false
     */
    public boolean playerMoved() {
        return playerMoved;
    }

    /**
     * This method is used to determine whether the reward is distributed
     * Post: true or false
     * @return boolean This returns true or false
     */
    public boolean rewardDistributed() {
        return rewardDistributed;
    }
}
