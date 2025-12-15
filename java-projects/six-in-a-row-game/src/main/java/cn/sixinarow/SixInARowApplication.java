package cn.sixinarow;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * SixInARowApplication Class
 * This class is the main entry point of the SixInARow application that sets up the stage and displays the game menu.
 * @author Cindy Liu
 * @version 05.04.2023
 */
public class SixInARowApplication extends Application {

    //The start method initializes the Menu object, sets it to the home page, displays it, and initializes the JavaFX application.
    @Override
    public void start(Stage stage) {
        Menu menu = new Menu(stage);
        menu.home();
        stage.show();
    }

}
