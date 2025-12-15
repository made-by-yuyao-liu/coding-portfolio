package cn.sixinarow;

import cn.sixinarow.game.SixInARowPage;
import cn.sixinarow.home.HomePage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Menu Class
 * This class manages the menu of the game and allows players to switch between the home page and the game page.
 * @author Cindy Liu
 * @version 05.04.2023
 */
public class Menu {

    // Attributes/instance variables
    private final Stage stage;
    private final BorderPane root = new BorderPane();
    private final HomePage homePage = new HomePage(this::game);
    private final SixInARowPage sixInARowPage = new SixInARowPage();

// Parameterized constructor
    public Menu(Stage stage) {
        this.stage = stage;
        Scene scene = new Scene(root, 800, 550);
        stage.setScene(scene);
    }

    public void home(){
        stage.setTitle("Six in a Row");
        root.setCenter(homePage);
    }

    public void game(){
        stage.setTitle("Game Page");
        root.setCenter(sixInARowPage);
    }
}
