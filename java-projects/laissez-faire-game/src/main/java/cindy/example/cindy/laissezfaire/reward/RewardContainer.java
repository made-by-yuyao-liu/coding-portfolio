package cindy.example.cindy.laissezfaire.reward;

import cindy.example.cindy.base.Matrix;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import cindy.example.cindy.laissezfaire.common.StatusControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RewardContainer Class
 * This class is the container for storing all the rewards
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class RewardContainer {
    //Attributes/instance variables
    private double rewardFrameXCoordinate = 560;
    private double rewardFrameYCoordinate = 310;
    private double rewardFrameWidth = 255;
    private double rewardFrameHeight = 285;

    private int randomNumber;

    //images
    private Image oilBarrelImage = new Image("oilBarrel.png");
    private Image coffeeShopImage = new Image("coffee.png");
    private Image houseImage = new Image("house.png");
    private Image oneHundredDollarBillImage = new Image("oneHundredDollarBill.png");
    private Image fiftyDollarBillImage = new Image("fiftyDollarBill.png");

    private double oilBarrelShiftX;
    private double oilBarrelShiftY;

    private double oilRatio = 0;

    private double fiftyDollarShiftX = 0;
    private double fiftyDollarShiftY = 0;

    private double fiftyDollarRatio = 0;

    private double oneHundredDollarShiftX = 0;
    private double oneHundredDollarShiftY = 0;

    private double oneHundredDollarRatio = 0;

    private double coffeeShiftX = 0;
    private double coffeeShiftY = 0;

    private double coffeeRatio = 0;

    private double houseShiftX = 0;
    private double houseShiftY = 0;
    private double houseRatio = 0;

    //put the rewards into the rewardList
    private List<Reward[]> rewardList = new ArrayList<>();

    //Default constructor
    public RewardContainer() {
    }

    /**
     * This method is used to load the rewards
     * Post:all reward combinations are added to the arrayList
     * @return void This does not return anything
     */
    public void loadRewards() {
        //the 12 different reward combinations
        rewardList.add(new Reward[]{new Reward("oilBarrelImage", 3, 3), new Reward("fiftyDollarBillImage", 1, 50)});
        rewardList.add(new Reward[]{new Reward("oilBarrelImage", 1, 1), new Reward("oneHundredDollarBillImage", 1, 100)});
        rewardList.add(new Reward[]{new Reward("oneHundredDollarBillImage", 2, 200), new Reward("coffeeImage", 1, 1)});
        rewardList.add(new Reward[]{new Reward("houseImage", 1, 1)});
        rewardList.add(new Reward[]{new Reward("oilBarrelImage", 2, 2), new Reward("oneHundredDollarBillImage", 1, 100)});
        rewardList.add(new Reward[]{new Reward("oneHundredDollarBillImage", 3, 300), new Reward("coffeeImage", 1, 1)});
        rewardList.add(new Reward[]{new Reward("oneHundredDollarBillImage", 10, 1000)});
        rewardList.add(new Reward[]{new Reward("oneHundredDollarBillImage", 2, 200), new Reward("houseImage", 1, 1)});
        rewardList.add(new Reward[]{new Reward("oneHundredDollarBillImage", 3, 300)});
        rewardList.add(new Reward[]{new Reward("oilBarrelImage", 3, 3), new Reward("fiftyDollarBillImage", 1, 50)});
        rewardList.add(new Reward[]{new Reward("oilBarrelImage", 2, 2), new Reward("oneHundredDollarBillImage", 1, 100)});
        rewardList.add(new Reward[]{new Reward("oilBarrelImage", 1, 1), new Reward("oneHundredDollarBillImage", 1, 100), new Reward("fiftyDollarBillImage", 1, 50)});
    }

    /**
     * This method is used to initialize the pixels and scaling
     * Post: the pixels and scaling are initialized
     * @return void This does not return anything
     */
    public void initialize() {
        //initialize oil refinery
        this.oilBarrelShiftX = 0;
        this.oilBarrelShiftY = 0;
        this.oilRatio = 0;
        //initialize the fifty dollar bill
        this.fiftyDollarShiftX = 0;
        this.fiftyDollarShiftY = 0;
        this.fiftyDollarRatio = 0;
        //initialize the hundred dollar bill
        this.oneHundredDollarShiftX = 0;
        this.oneHundredDollarShiftY = 0;
        this.oneHundredDollarRatio = 0;
        //initialize the coffee shops
        this.coffeeShiftX = 0;
        this.coffeeShiftY = 0;
        this.coffeeRatio = 0;
        //initialize the house
        this.houseShiftX = 0;
        this.houseShiftY = 0;
        this.houseRatio = 0;
    }

    /**
     * This method is used draw rewards
     * Pre: the pen, matrix and statusControl are passed in
     * Post: the rewards are drawn in the reward box
     * @param pen This is the first parameter to draw method
     * @param matrix This is the second parameter to draw method
     * @param statusControl This is the third parameter to draw method
     * @return void This does not return anything
     */
    public void draw(GraphicsContext pen, Matrix matrix, StatusControl statusControl) {
        //background color
        pen.setFill(Color.WHITE);
        pen.fillRect(rewardFrameXCoordinate, rewardFrameYCoordinate, rewardFrameWidth, rewardFrameHeight);
        //drawing the box frame
        pen.setFont(new Font("Impact", 20));
        pen.setLineWidth(5);
        pen.setFill(Color.BLACK);
        pen.strokeRect(rewardFrameXCoordinate, rewardFrameYCoordinate, rewardFrameWidth, rewardFrameHeight);

        //draw words
        pen.setFill(Color.BLACK);
        pen.fillText("You've earned:", 580, 340);

        //if there are rewards, the dice shows 1 and the random number is greater than 0
        if (statusControl.showReward() && statusControl.getPointNumber() == 0 && randomNumber >= 0) {
            pen.setLineWidth(2);
            //get the reward that match the number
            Reward[] rewards = rewardList.get(randomNumber);
            //loop to get the single rewards
            for (int i = 0; i < rewards.length; i++) {
                //get the name of the image for number i
                String name = rewards[i].getRewardImageName();
                //get the number of images for number i
                int number = rewards[i].getRewardNum();
                //draw the images based on their names
                switch (name) {
                    case "oilBarrelImage":
                        //draw oil barrels
                        pen.drawImage(oilBarrelImage, 580, 360 + 55 * i, 80, 80);
                        pen.strokeText("X", 710, 360 + 55 * i + 50);
                        pen.strokeText(number+"", 740, 360 + 55 * i + 50);
                        //set the original coordinates of the oil barrel
                        if (oilBarrelShiftX == 0) {
                            oilBarrelShiftX = 580;
                        }
                        if (oilBarrelShiftY == 0) {
                            oilBarrelShiftY = 360 + 55 * i;
                        }
                        //get the total length that the oil barrel is moving
                        double oilMovePath = Math.sqrt(Math.pow(580 - statusControl.getNextOilImageXCoordinate(), 2) + Math.pow(360 + 55 * i - statusControl.getNextOilImageYCoordinate(), 2));
                        //if rewardShowFlag is true, when the reward is displayed and when earnedRewardAddFlag is false
                        if (statusControl.showReward() && !statusControl.rewardDistributed()) {
                            //oil barrel image moves
                            matrix.push();
                            matrix.translate(oilBarrelShiftX, oilBarrelShiftY);
                            //the oil barrel image scales (becomes smaller)
                            matrix.scale(1 - oilRatio);
                            pen.drawImage(oilBarrelImage, 0, 0, 80, 80);
                            matrix.pop();

                            //calculate the next movement
                            if (oilBarrelShiftX > statusControl.getNextOilImageXCoordinate()) {
                                oilBarrelShiftX = oilBarrelShiftX + (statusControl.getNextOilImageXCoordinate() - 580) / 150;
                            }
                            if (oilBarrelShiftY > statusControl.getNextOilImageYCoordinate()) {
                                oilBarrelShiftY = oilBarrelShiftY + (statusControl.getNextOilImageYCoordinate() - (360 + 55 * i)) / 150;
                            }
                            //the length of the path that the oil has moved
                            double currentMovePath = Math.sqrt(Math.pow(580 - oilBarrelShiftX, 2) + Math.pow((360 + 55 * i) - oilBarrelShiftY, 2));
                            //the length that the oil barrel has moved already is divided by the total path the oil image has to move to get the scaling ratio
                            oilRatio = currentMovePath / oilMovePath;
                            if (oilRatio > 1) {
                                oilRatio = 1;
                            }
                            if (oilRatio < 0) {
                                oilRatio = 0;
                            }
                        }
                        break;
                    case "coffeeImage":
                        //draw the coffee houses
                        pen.drawImage(coffeeShopImage, 585, 360 + 75 * i, 80, 70);
                        pen.strokeText("X", 715, 360 + 75 * i + 60);
                        pen.strokeText(number+"", 745, 360 + 75 * i + 60);
                        //set the original coordinates of the coffee houses
                        if (coffeeShiftX == 0) {
                            coffeeShiftX = 585;
                        }
                        if (coffeeShiftY == 0) {
                            coffeeShiftY = 360 + 75 * i;
                        }
                        //get the total length that the coffee shop is moving
                        double coffeeMovePath = Math.sqrt(Math.pow(statusControl.getNextCoffeeImageX() - 585, 2) + Math.pow(360 + 75 * i - statusControl.getNextCoffeeImageYCoordinate(), 2));
                        //if rewardShowFlag is true, when the reward is displayed and when earnedRewardAddFlag is false
                        if (statusControl.showReward() && !statusControl.rewardDistributed()) {
                            //coffee shop image moves
                            matrix.push();
                            matrix.translate(coffeeShiftX, coffeeShiftY);
                            //the coffee shop image scales (becomes smaller)
                            matrix.scale(1 - coffeeRatio);
                            pen.drawImage(coffeeShopImage, 0, 0, 80, 70);
                            matrix.pop();

                            //calculate the next movement
                            if (coffeeShiftX < statusControl.getNextCoffeeImageX()) {
                                coffeeShiftX = coffeeShiftX + (statusControl.getNextCoffeeImageX() - 585) / 150;
                            }
                            if (coffeeShiftY > statusControl.getNextCoffeeImageYCoordinate()) {
                                coffeeShiftY = coffeeShiftY + (statusControl.getNextCoffeeImageYCoordinate() - (360 + 75 * i)) / 150;
                            }
                            //the length of the path that the coffee shop has moved
                            double currentMovePath = Math.sqrt(Math.pow(coffeeShiftX - 585, 2) + Math.pow(coffeeShiftY - (360 + 75 * i), 2));
                            //the length that the coffee shop has moved already is divided by the total path the coffee shop has to move to get the scaling ratio                            coffeeRatio = currentMovePath / coffeeMovePath;
                            if (coffeeRatio > 1) {
                                coffeeRatio = 1;
                            }
                            if (coffeeRatio < 0) {
                                coffeeRatio = 0;
                            }
                        }
                        break;
                    case "houseImage":
                        //draw house
                        pen.drawImage(houseImage, 585, 360 + 85 * i, 100, 70);
                        pen.strokeText("X", 735, 360 + 85 * i + 35);
                        pen.strokeText(number+"", 765, 360 + 85 * i + 35);
                        //set the original coordinates of the house
                        if (houseShiftX == 0) {
                            houseShiftX = 585;
                        }
                        if (houseShiftY == 0) {
                            houseShiftY = 360 + 85 * i;
                        }
                        //get the total length that the house is moving
                        double houseMovePath = Math.sqrt(Math.pow(statusControl.getNextHouseImageXCoordinate() - 585, 2) + Math.pow(360 + 85 * i - statusControl.getNextHouseImageYCoordinate(), 2));
                        //if rewardShowFlag is true, when the reward is displayed and when earnedRewardAddFlag is false
                        if (statusControl.showReward() && !statusControl.rewardDistributed()) {
                            //the house image moves
                            matrix.push();
                            matrix.translate(houseShiftX, houseShiftY);
                            //the house image scales (becomes smaller)
                            matrix.scale(1 - houseRatio);
                            pen.drawImage(houseImage, 0, 0, 100, 70);
                            matrix.pop();
                            //calculate the next movement
                            if (houseShiftX < statusControl.getNextHouseImageXCoordinate()) {
                                houseShiftX = houseShiftX + (statusControl.getNextHouseImageXCoordinate() - 585) / 150;
                            }
                            if (houseShiftY > statusControl.getNextHouseImageYCoordinate()) {
                                houseShiftY = houseShiftY + (statusControl.getNextHouseImageYCoordinate() - (360 + 85 * i)) / 150;
                            }
                            //the length of the path that the house has moved
                            double currentMovePath = Math.sqrt(Math.pow(houseShiftX - 585, 2) + Math.pow(houseShiftY - (360 + 85 * i), 2));
                            //the length that the house has moved already is divided by the total path the house has to move to get the scaling ratio
                            houseRatio = currentMovePath / houseMovePath;
                            if (houseRatio > 1) {
                                houseRatio = 1;
                            }
                            if (houseRatio < 0) {
                                houseRatio = 0;
                            }
                        }
                        break;
                    case "fiftyDollarBillImage":
                        //draw the fifty dollar bill
                        pen.drawImage(fiftyDollarBillImage, 580, 360 + 80 * i, 100, 65);
                        pen.strokeText("X", 730, 360 + 80 * i + 32.5);
                        pen.strokeText(number+"", 760, 360 + 80 * i + 32.5);
                        //set the original coordinates of the fifty dollar bill
                        if (fiftyDollarShiftX == 0) {
                            fiftyDollarShiftX = 580;
                        }
                        if (fiftyDollarShiftY == 0) {
                            fiftyDollarShiftY = 360 + 80 * i;
                        }
                        //get the total length that the fifty dollar bill is moving
                        double fiftyDollarMovePath = Math.sqrt(Math.pow(580 - statusControl.getBankTallyBoxXCoordinate(), 2) + Math.pow(360 + 80 * i - statusControl.getBankTallyBoxYCoordinate(), 2));
                        //if rewardShowFlag is true, when the reward is displayed and when earnedRewardAddFlag is false
                        if (statusControl.showReward() && !statusControl.rewardDistributed()) {
                            //fifty dollar bill moves
                            matrix.push();
                            matrix.translate(fiftyDollarShiftX, fiftyDollarShiftY);
                            //the fifty dollar bill image scales (becomes smaller)
                            matrix.scale(1 - fiftyDollarRatio);
                            pen.drawImage(fiftyDollarBillImage, 0, 0, 100, 65);
                            matrix.pop();
                            //calculate the next movement
                            if (fiftyDollarShiftX > statusControl.getBankTallyBoxXCoordinate()) {
                                fiftyDollarShiftX = fiftyDollarShiftX + (statusControl.getBankTallyBoxXCoordinate() - 580) / 150;
                            }
                            if (fiftyDollarShiftY > statusControl.getBankTallyBoxYCoordinate()) {
                                fiftyDollarShiftY = fiftyDollarShiftY + (statusControl.getBankTallyBoxYCoordinate() - (360 + 80 * i)) / 150;
                            }
                            //the length of the path that the fifty dollar bill has moved
                            double currentMovePath = Math.sqrt(Math.pow(fiftyDollarShiftX - 580, 2) + Math.pow((360 + 80 * i) - fiftyDollarShiftY, 2));
                            //the length that the fifty dollar bill has moved already is divided by the total path the fifty dollar bill has to move to get the scaling ratio
                            fiftyDollarRatio = currentMovePath / fiftyDollarMovePath;
                            if (fiftyDollarRatio > 1) {
                                fiftyDollarRatio = 1;
                            }
                            if (fiftyDollarRatio < 0) {
                                fiftyDollarRatio = 0;
                            }
                        }
                        break;
                    case "oneHundredDollarBillImage":
                        //draw the one hundred dollar bill
                        pen.drawImage(oneHundredDollarBillImage, 580, 360 + 80 * i, 100, 65);
                        pen.strokeText("X", 730, 360 + 80 * i + 32.5);
                        pen.strokeText(number+"", 760, 360 + 80 * i + 32.5);
                        //set the original coordinates of the one hundred dollar bill
                        if (oneHundredDollarShiftX == 0) {
                            oneHundredDollarShiftX = 580;
                        }
                        if (oneHundredDollarShiftY == 0) {
                            oneHundredDollarShiftY = 360 + 80 * i;
                        }
                        //get the total length hat the one hundred dollar bill has to move
                        double oneHundredDollarMovePath = Math.sqrt(Math.pow(580 - statusControl.getBankTallyBoxXCoordinate(), 2) + Math.pow(360 + 80 * i - statusControl.getBankTallyBoxYCoordinate(), 2));
                        //if rewardShowFlag is true, when the reward is displayed and when earnedRewardAddFlag is false
                        if (statusControl.showReward() && !statusControl.rewardDistributed()) {
                            //the one hundred dollar bill moves
                            matrix.push();
                            matrix.translate(oneHundredDollarShiftX, oneHundredDollarShiftY);
                            //the one hundred dollar bill scales (becomes smaller)
                            matrix.scale(1 - oneHundredDollarRatio);
                            pen.drawImage(oneHundredDollarBillImage, 0, 0, 100, 65);
                            matrix.pop();

                            //calculate the next movement
                            if (oneHundredDollarShiftX > statusControl.getBankTallyBoxXCoordinate()) {
                                oneHundredDollarShiftX = oneHundredDollarShiftX + (statusControl.getBankTallyBoxXCoordinate() - 580) / 150;
                            }
                            if (oneHundredDollarShiftY > statusControl.getBankTallyBoxYCoordinate()) {
                                oneHundredDollarShiftY = oneHundredDollarShiftY + (statusControl.getBankTallyBoxYCoordinate() - (360 + 80 * i)) / 150;
                            }
                            //the length of the path the one hundred dollar bill has moved
                            double currentMovePath = Math.sqrt(Math.pow(oneHundredDollarShiftX - 580, 2) + Math.pow((360 + 80 * i) - oneHundredDollarShiftY, 2));
                            //the length that the one hundred dollar bill has moved already is divided by the total path the one hundred dollar bill has to move to get the scaling ratio
                            oneHundredDollarRatio = currentMovePath / oneHundredDollarMovePath;
                            if (oneHundredDollarRatio > 1) {
                                oneHundredDollarRatio = 1;
                            }
                            if (oneHundredDollarRatio < 0) {
                                oneHundredDollarRatio = 0;
                            }
                        }
                        break;
                }
            }

            //if the scaling is 1 or 0, set the earnedRewardAddFlag to true
            if ((oilRatio == 1 || oilRatio == 0) && (coffeeRatio == 1 || coffeeRatio == 0) && (houseRatio == 1 || houseRatio == 0) && (fiftyDollarRatio == 1 || fiftyDollarRatio == 0) && (oneHundredDollarRatio == 1 || oneHundredDollarRatio == 0)) {
                statusControl.setRewardDistributed(true);
            }
        }
    }

    //Getter methods
    /**
     * This method is used to generate a random number between 1 and 11
     * Post: the random number is returned
     * @return int This returns random generated number
     */
    public int getRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(12);
        return randomNumber;
    }

    /**
     * This method is used to count the current reward earned
     * Pre: statusControl is  passed in
     * Post: the reward is counted
     * @param statusControl This is the first parameter to countEarnedReward method
     * @return void This does not return anything
     */
    public void countEarnedReward(StatusControl statusControl) {
        this.randomNumber = getRandomNumber();
        //get the corresponding reward combinations based on random numbers
        Reward[] rewards = rewardList.get(randomNumber);

        //count the initial number of rewards
        int earnedBillAmount = 0;
        int earnedCoffeeNumber = 0;
        int earnedHouseNumber = 0;
        int earnedOilNumber = 0;

        //loop the rewards
        for (int i = 0; i < rewards.length; i++) {
            //get the name of the "i"th image
            String name = rewards[i].getRewardImageName();
            //calculate the number of rewards based name of the picture
            if (name.equals("oilBarrelImage")) {
                earnedOilNumber = rewards[i].getTotalValue();
            } else if (name.equals("oneHundredDollarBillImage")) {
                earnedBillAmount += rewards[i].getTotalValue();
            } else if (name.equals("fiftyDollarBillImage")) {
                earnedBillAmount += rewards[i].getTotalValue();
            } else if (name.equals("coffeeImage")) {
                earnedCoffeeNumber = rewards[i].getTotalValue();
            } else if (name.equals("houseImage")) {
                earnedHouseNumber = rewards[i].getTotalValue();
            }
        }
        //when the calculation is complete, save them to the corresponding variable
        statusControl.setJustEarnedCash(earnedBillAmount);
        statusControl.setJustEarnedCoffeeShopNum(earnedCoffeeNumber);
        statusControl.setJustEarnedHouseNum(earnedHouseNumber);
        statusControl.setJustEarnedOilBarrelNum(earnedOilNumber);
    }

}
