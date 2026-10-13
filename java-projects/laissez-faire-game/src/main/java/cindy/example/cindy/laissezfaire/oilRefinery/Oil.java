package cindy.example.cindy.laissezfaire.oilRefinery;

/**
 * Oil Class
 * This class contains data for each individual oil barrel
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class Oil {
    //Attributes/instance variables
    //Oil ID
    private int oilBarrelId;
    //x-coordinates of the oil barrel
    private double oilBarrelXCoordinate;
    //y-coordinates of the oil barrel
    private double oilBarrelYCoordinate;

    //Parameterized constructor
    public Oil(int oilBarrelId, double oilBarrelXCoordinate, double oilBarrelYCoordinate) {
        this.oilBarrelId = oilBarrelId;
        this.oilBarrelXCoordinate = oilBarrelXCoordinate;
        this.oilBarrelYCoordinate = oilBarrelYCoordinate;
    }

    //Getter methods
    public double getOilBarrelXCoordinate() {
        return oilBarrelXCoordinate;
    }
    public double getOilBarrelYCoordinate() {
        return oilBarrelYCoordinate;
    }

}
