package cindy.example.cindy.laissezfaire.gridBoard;

/**
 * Grid Class
 * This class represent an individual grid
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class Grid {
    //Attributes/instance variables
    private double gridXCoordinate;
    private double gridYCoordinate;

    //Parameterized constructor
    public Grid(double gridXCoordinate, double gridYCoordinate) {
        this.gridXCoordinate = gridXCoordinate;
        this.gridYCoordinate = gridYCoordinate;
    }

    //Getter methods
    public double getGridXCoordinate() {
        return gridXCoordinate;
    }
    public double getGridYCoordinate() {
        return gridYCoordinate;
    }
}
