package cindy.example.cindy.laissezfaire.reward;


/**
 * Reward Class
 * This class controls the reward
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class Reward {
    //Attributes/instance variables
    private String rewardImageName;
    private int rewardNum;
    private int totalValue;

    //Getter methods
    public String getRewardImageName() {
        return rewardImageName;
    }
    public int getRewardNum() {
        return rewardNum;
    }
    public int getTotalValue() {
        return totalValue;
    }

    //Parameterized constructor
    public Reward(String rewardImageName, int rewardNum, int totalValue) {
        this.rewardImageName = rewardImageName;
        this.rewardNum = rewardNum;
        this.totalValue = totalValue;
    }
}
