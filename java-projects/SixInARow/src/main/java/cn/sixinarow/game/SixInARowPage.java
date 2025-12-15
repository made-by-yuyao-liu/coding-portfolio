package cn.sixinarow.game;

import cn.sixinarow.home.HomePage;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

/**
 * SixInARowPage Class
 * This is a class that represents a game of Six in a Row which is played on a 18x18 board,
 * where each player takes turns placing their pieces on the board, and the first player to
 * get six of their pieces in a row horizontally, vertically, or diagonally wins the game.
 * @author Cindy Liu
 * @version 05.06.2023
 */
public class SixInARowPage extends HBox {

    // Attributes/instance variables
    private final Board board = new Board(this::chess);
    private final Recorder recorder = new Recorder();
    private final RockPaperScissors rockPaperScissors = new RockPaperScissors(this::dice);
    private final StackPane rightPane = new StackPane();
    private final VBox gameRight = new VBox();
    private final VBox historyRight = new VBox();

    // Default constructor
    public SixInARowPage(){
        this.setBackground(new Background(new BackgroundFill(Color.rgb(232,236,240), CornerRadii.EMPTY, Insets.EMPTY)));
        rightPane.setAlignment(Pos.CENTER);
        gameRight.setAlignment(Pos.CENTER);
        gameRight.setSpacing(40);
        gameRight.prefHeight(500);
        gameRight.setPadding(new Insets(0, 50, 0, 50));
        Label label = new Label("Press on either of the hand icons \n to play Rock Paper Scissors!");

        Button undoPeg = new Button("Undo Peg");
        //undoPeg.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        undoPeg.setPrefSize(120, 40);
        Button restartGame = new Button("New Game");
        //restartGame.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        restartGame.setPrefSize(120, 40);
        Button gameRules = new Button("Game Rules");
        //gameRules.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        gameRules.setPrefSize(120, 40);
        gameRight.getChildren().addAll(label, rockPaperScissors, undoPeg, restartGame, gameRules);

        undoPeg.setOnMouseClicked(this::undo);
        restartGame.setOnMouseClicked(this::replay);
        gameRules.setOnMouseClicked(mouseEvent -> HomePage.gameRules());

        historyRight.setAlignment(Pos.CENTER);
        historyRight.setSpacing(40);
        historyRight.setPadding(new Insets(0, 50, 0, 50));

        Button pre = new Button("previous");
        pre.setPrefSize(120, 40);
        Button next = new Button("next");
        next.setPrefSize(120, 40);
        Button restartGame1 = new Button("New Game");
        restartGame1.setPrefSize(120, 40);
        Button gameRules1 = new Button("Game Rules");
        gameRules1.setPrefSize(120, 40);
        pre.setOnMouseClicked(this::pre);
        next.setOnMouseClicked(this::next);
        restartGame1.setOnMouseClicked(this::replay);
        gameRules1.setOnMouseClicked(mouseEvent -> HomePage.gameRules());

        historyRight.getChildren().addAll(pre, next, restartGame1, gameRules1);

        this.getChildren().addAll(board, rightPane);
        game();
    }

    ListIterator<Recorder.Piece> history = null;

    /**
     * This function displays the game history and disables user interaction with the game board
     * while allowing navigation through previous moves.
     */
    private void history(){
        rightPane.getChildren().clear();
        rightPane.getChildren().add(historyRight);
        rockPaperScissors.disable(true);
        board.disable(true);
        board.reset();
        history = recorder.history();
        if (history.hasNext()) {
            board.move(history.next());
        }
    }

    /**
     * This method moves the game board forward to the next recorded step in the game's history,
     * or displays an alert if there are no more recorded steps.
     * Post: moves the game board forward to the next recorded step
     * @param mouseEvent click of the mouse
     */
    private void next(MouseEvent mouseEvent) {
        if (history.hasNext()) {
            board.move(history.next());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("info");
            alert.setHeaderText(null);
            alert.setContentText("There are no further steps.");
            alert.showAndWait();
        }
    }

    /**
     * This method handles the functionality for going to the previous move in a game of Six-in-a-Row.
     * @param mouseEvent click of the mouse
     */
    private void pre(MouseEvent mouseEvent) {
        if (history.hasPrevious()) {
            board.undo(history.previous());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("info");
            alert.setHeaderText(null);
            alert.setContentText("There are no previous steps!");
            alert.showAndWait();
        }
    }

    /**
     * The game() function sets up the game board and enables the Rock-Paper-Scissors icon while disabling the game board.
     */
    private void game(){
        rightPane.getChildren().clear();
        history = null;
        rightPane.getChildren().add(gameRight);
        rockPaperScissors.disable(false);
        board.disable(true);
        board.reset();
    }

    private int leftHand;
    private int rightHand;

    /**
     * This function updates the state of the game based on the result of a rock-paper-scissors game between two hands.
     * @param leftHand left-hand icon
     * @param rightHand right-hand icon
     */
    public void dice(int leftHand, int rightHand){
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        int win = RockPaperScissors.getWin(leftHand, rightHand);
        if (win > 0) { // Winners and losers have been decided, game ends
            rockPaperScissors.disable(true);
            board.setPlayer(win);
            board.disable(false);
        } else {
            rockPaperScissors.disable(false);
            board.disable(true);
        }
    }

    /** Every time you regret a move, you must be ready to drop it */
    private Recorder.Piece undo = null;

    /**
     * This method allows players to undo their moves and sets the game state back to the previous step.
     * Post: undo pegs
     * @param mouseEvent mouse click
     */
    public void undo(MouseEvent mouseEvent){
        if (this.undo != null) {
            recorder.pushLast(this.undo);
        }
        Recorder.Piece undo = recorder.undo();
        if (undo == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Rules");
            alert.setHeaderText(null);
            alert.setContentText("No more pegs to undo!");
            alert.showAndWait();
        }
        if (recorder.hasNext()) {
            this.undo = recorder.next();
            board.setPlayer(this.undo.getPlayer());
            board.undo(this.undo);
            rockPaperScissors.disable(true);
            board.disable(false);
        }
    }

    /**
     * The function of this class allows the player to place a chess piece on the board, records the move,
     * checks for a winning condition, and displays a dialog box for the player to choose their next action.
     * Pre: parameters passed in
     * Post: player places a chess piece on the board
     * @param x x-coordinate
     * @param y y-coordinate
     * @param player player of the game
     */
    public void chess(int x, int y, int player){
        this.undo = null;
        if (recorder.hasNext()) {
            Recorder.Piece next = recorder.next();
            rockPaperScissors.disable(true);
            board.setPlayer(next.getPlayer());
            board.disable(false);
        } else {
            rockPaperScissors.disable(false);
            board.setPlayer(0);
            board.disable(true);
        }
        recorder.add(new Recorder.Piece(x, y, player, leftHand, rightHand));
        if (checkWin(x, y)) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Player with " + (player == 1?"white pegs":"black pegs") + " wins!",
                    new ButtonType("New Round"),
                    new ButtonType("Watch Match Replay"),
                    new ButtonType("End Game"));

            // Get all buttons and add event handlers to them
            List<ButtonType> buttons = alert.getButtonTypes();
            buttons.forEach(buttonType -> {
                Button button = (Button) alert.getDialogPane().lookupButton(buttonType);
                if (button != null) {
                    button.addEventFilter(ActionEvent.ACTION, event -> {
                        // Manually close the dialog box at the click of a button
                        alert.setResult(buttonType);
                        alert.hide();
                    });
                }
            });

            // Display dialog box and wait for user response
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                // Processing according to the button clicked by the user
                if (result.get().getText().equals("New Round")) {
                    // Perform the actions associated with option 1
                    replay(null);
                } else if (result.get().getText().equals("Watch Match Replay")) {
                    // Perform the actions associated with option 2
                    history();
                } else if (result.get().getText().equals("End Game")) {
                    // Perform the actions associated with option 3
                    System.exit(0);
                }
            }
        }
    }

    /**
     * The replay method resets the game, clears the recorder, and returns the player to the game's initial state.
     * @param mouseEvent mouse pressed
     */
    public void replay(MouseEvent mouseEvent){
        recorder.clear();
        board.reset();
        rockPaperScissors.disable(false);
        board.setPlayer(0);
        board.disable(true);
        game();
    }

    /**
     * The function checks if there is a win condition in a game of Six in a Row by looking for a sequence of
     * six identical pieces horizontally, vertically, or diagonally on the game board.
     * Pre: passed in parameters
     * Post: check win or not
     * @return boolean
     */
    private boolean checkWin(int x, int y) {
        int player = board.getBoardValue(x, y);

        // check horizontal rows
        int count = 0;
        for (int i = Math.max(0, x - 5); i <= Math.min(17, x + 5); i++) {
            if (board.getBoardValue(i, y) == player) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 6) {
                return true;
            }
        }

        // check vertical rows
        count = 0;
        for (int j = Math.max(0, y - 5); j <= Math.min(17, y + 5); j++) {
            if (board.getBoardValue(x, j) == player) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 6) {
                return true;
            }
        }

        // check diagonal rows (top left to bottom right)
        int differ = Math.min(Math.min(5, x), Math.min(5, y));
        count = 0;
        for (int i = x - differ, j = y - differ; i <= Math.min(x + 5, 17) && j <= Math.min(y + 5, 17); i++, j++) {
            if (board.getBoardValue(i, j) == player) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 6) {
                return true;
            }
        }

        // check diagonal rows (top right to bottom left)
        differ = Math.min(Math.min(5, x), y + 5 > 17?17-y:5);
        count = 0;
        for (int i = x - differ, j = y + differ; i <= Math.min(x + 5, 17) && j >= Math.max(y - 5, 0); i++, j--) {
            if (board.getBoardValue(i, j) == player) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 6) {
                return true;
            }
        }
        return false;
    }
}
