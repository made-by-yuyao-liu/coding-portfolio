package cn.sixinarow.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * HomePage Class
 * This class represents the home page of a game and contains buttons to start the game and display game rules.
 * @author Cindy Liu
 * @version 05.05.2023
 */
public class HomePage extends VBox {

    // Attributes/instance variables
    private final Button startGame = new Button("Start Game");
    private final Button gameRules = new Button("Game Rules");

    // Parameterized constructor
    public HomePage(StartGame startGameHandle){
        this.setBackground(new Background(new BackgroundFill(Color.rgb(232,236,240), CornerRadii.EMPTY, Insets.EMPTY)));
        startGame.setPrefSize(150, 50);
        gameRules.setPrefSize(150, 50);
        this.getChildren().addAll(startGame, gameRules);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        startGame.setOnMouseClicked(mouseEvent -> startGameHandle.handle());
        gameRules.setOnMouseClicked(mouseEvent -> gameRules());
    }

    /**
     * This is a static method that displays the game rules in an Alert dialog box.
     */
    public static void gameRules(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Rules");
        alert.setHeaderText(null);
        alert.setContentText(
                "1. This is a TWO-player game.\n" +
                "2. Players select either black or white as their color.\n" +
                "3. Game starts with clicking either of the hand icons.\n" +
                "4. The RockPaperScissors winner places a peg on the board.\n" +
                "5. To undo and relay a peg, players click the 'Undo Peg' button.\n" +
                "6. The first player to connect 6 pegs horizontally, vertically, or diagonally wins the game!\n" +
                "7. If the board fills up without a player connecting 6 pegs, it's a tie.\n" +
                "8. After game ends, players can start new round, watch replay, or end game.\n"
                );
        alert.showAndWait();
    }

    public interface StartGame{
        void handle();
    }

}
