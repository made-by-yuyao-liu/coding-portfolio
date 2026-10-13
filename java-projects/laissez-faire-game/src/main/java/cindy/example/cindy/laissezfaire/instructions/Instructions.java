package cindy.example.cindy.laissezfaire.instructions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Instructions Class
 * This class draws and controls the instructions section
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class Instructions {
    //Attributes/instance variables
    //coordinates, width and height
    private double instructionsFrameXCoordinate = 835;
    private double instructionsFrameYCoordinate = 310;
    private double instructionsFrameWidth = 255;
    private double instructionsFrameHeight = 285;

    //Default constructor
    public Instructions() {
    }

    /**
     * This method is used to draw the instruction section
     * Pre: the pen is passed in
     * Post: the instructions are drawn
     * @param pen This is the first parameter to draw method
     * @return void This does not return anything
     */
    public void draw(GraphicsContext pen) {
        //background color
        pen.setFill(Color.rgb(241, 218, 218));
        pen.fillRect(instructionsFrameXCoordinate, instructionsFrameYCoordinate, instructionsFrameWidth, instructionsFrameHeight);
        pen.setFont(new Font("Impact", 18));
        pen.setStroke(Color.BLACK);
        pen.setLineWidth(5);
        pen.strokeRect(instructionsFrameXCoordinate, instructionsFrameYCoordinate, instructionsFrameWidth, instructionsFrameHeight);

        //draw the instruction words
        pen.setLineWidth(1);
        pen.setFill(Color.BLACK);
        pen.fillText("To", 850, 340);
        pen.setFill(Color.GREEN);
        pen.fillText("WIN", 870, 340);
        pen.setFill(Color.BLACK);
        pen.fillText(",", 902, 340);
        pen.fillText("You need one of the three:", 850, 368);
        pen.setFill(Color.RED);
        pen.fillText("1.", 855, 395);
        pen.setFill(Color.BLACK);
        pen.fillText("25 barrels of oil", 880, 395);
        pen.setFill(Color.RED);
        pen.fillText("2.", 855, 425);
        pen.setFill(Color.BLACK);
        pen.fillText("$10000 in your", 880, 425);
        pen.fillText("bank account", 880, 445);
        pen.setFill(Color.RED);
        pen.fillText("3.", 855, 471);
        pen.setFill(Color.BLACK);
        pen.fillText("5 coffee shops and", 880, 471);
        pen.fillText("5 houses", 880, 491);

        pen.setFill(Color.BLACK);
        pen.setFont(new Font("Times New Roman", 38));
        pen.setFill(Color.GREEN);
        pen.fillText("Good", 860, 540);
        pen.fillText("Luck!", 960, 570);
    }
}
