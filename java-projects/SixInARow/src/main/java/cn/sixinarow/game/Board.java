package cn.sixinarow.game;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.List;

/**
 * Board Class
 * This class extends the GridPane class and contains variables and methods for managing
 * a game board with cells of a specific size, current player, and a two-dimensional array
 * representing the game board.
 * @author Cindy Liu
 * @version 05.4.2023
 */
public class Board extends GridPane {

    // Attributes/instance variables
    private boolean disable = true;
    final Handle handle;
    private int currentPlayer = 1;
    private int[][] board = new int[18][18];
    public static final int CELL_SIZE = 30;

    // Parameterized constructor
    public Board(Handle chess){
        this.handle = chess;
        this.setAlignment(Pos.CENTER);
        this.setHgap(0);
        this.setVgap(0);
        reset();
    }

    /**
     * This method returns the value (an integer) of the cell in the two-dimensional "board"
     * array at the specified x and y coordinates.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return int the value (an integer) of the cell in the two-dimensional "board" array
     */
    public int getBoardValue(int x, int y){
        return board[x][y];
    }

    /**
     * This method handles the logic for placing a player's peg/button (a circle of the current
     * player's color) on the game board at the specified x and y coordinates if the cell is empty,
     * and updates the board array with the player's number (1 or 2) to indicate the placement.
     * Pre: x, y, button are passed in
     * Post: peg is placed
     * @param x x-coordinate
     * @param y y-coordinate
     * @param button player's peg
     */
    private void move(int x, int y, Button button) {
        if (board[x][y] != 0) {
            return; // If there is already a piece in this position, you cannot play it again
        }
        Pane graphic = (Pane) button.getGraphic();
        Circle circle = new Circle(CELL_SIZE * 0.40);
        circle.setStroke(Color.BLACK);
        circle.setFill(currentPlayer == 1 ?  Color.WHITE : Color.BLACK);
        circle.setCenterX(CELL_SIZE/2.0);
        circle.setCenterY(CELL_SIZE/2.0);
        graphic.getChildren().add(circle);
        board[x][y] = currentPlayer;
    }

    /**
     * This method performs the undo operation by taking a "Piece" object, finding the corresponding
     * button on the game board, and calling the "move" method with the button and player information
     * stored in the "Piece" object to restore the game state before the last move.
     * Post: piece undo
     * @param undo undo button
     */
    public void move(Recorder.Piece undo){
        Node node = null;

        List<Node> nodesList = this.getChildren();
        for (Node child : nodesList) {
            Integer columnIndex = GridPane.getColumnIndex(child);
            Integer rowIndex = GridPane.getRowIndex(child);
            if (columnIndex != null && rowIndex != null && columnIndex == undo.getX() && rowIndex == undo.getY()) {
                node = child;
                break;
            }
        }
        Button button = (Button) node;
        currentPlayer = undo.getPlayer();
        move(undo.getX(), undo.getY(), button);
    }


    /**
     * This method resets the game board by clearing the current pegs, creating a new
     * two-dimensional board array with empty values, and creating a new set of buttons with line
     * graphics as children that form a 18x18 grid, and then adding the buttons to the game board.
     * Post: game board is reset
     */
    public void reset(){
        this.getChildren().clear();
        board = new int[18][18];
        // Create boards and buttons
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                Button button = new Button();

                // Create a Pane as a graphical node for the Button
                Pane graphic = new Pane();
                graphic.setBackground(new Background(new BackgroundFill(Color.rgb(246,224,185), CornerRadii.EMPTY, Insets.EMPTY)));

                // Create two intersecting lines and set the properties
                Line horizontalLine = new Line(0, CELL_SIZE/2.0, CELL_SIZE, CELL_SIZE/2.0);
                Line verticalLine = new Line(CELL_SIZE/2.0, 0, CELL_SIZE/2.0, CELL_SIZE);
                if (j == 0) {
                    verticalLine.setStartY(CELL_SIZE/2.0);
                } else if (j == 17) {
                    verticalLine.setEndY(CELL_SIZE/2.0);
                }
                if (i == 0) {
                    horizontalLine.setStartX(CELL_SIZE/2.0);
                } else if (i == 17) {
                    horizontalLine.setEndX(CELL_SIZE/2.0);
                }
                horizontalLine.setStrokeWidth(1);
                verticalLine.setStrokeWidth(1);

                // Add two lines to the graph node
                graphic.getChildren().addAll(horizontalLine, verticalLine);

                // Adding graphic nodes to a Button
                button.setGraphic(graphic);

                button.setStyle("-fx-background-color: transparent;");
                button.setPadding(new Insets(0));
                button.setPrefSize(CELL_SIZE, CELL_SIZE);
                int finalI = i;
                int finalJ = j;
                button.setOnAction(event -> {
                    if (!disable) {
                        move(finalI, finalJ, button);
                        handle.handle(finalI, finalJ, currentPlayer);
                    }
                });
                this.add(button, i, j);
            }
        }
    }

    /**
     * This method sets the current player to the specified player number (1 or 2), if the input is valid.
     * Pre: currentPlayer is passed in
     * Post: sets the current player to the specified player number
     * @param currentPlayer the current player
     */
    public void setPlayer(int currentPlayer){
        if (currentPlayer == 1 || currentPlayer == 2) {
            this.currentPlayer = currentPlayer;
        }
    }

    /**
     * This method enables or disables user interaction with the game board based on the boolean input value.
      * Post: disable is passed in
     * @param disable boolean button
     */
    public void disable(boolean disable){
        this.disable = disable;
    }


    /**
     * The function of this method is to undo a move by removing the piece from the specified
     * button and resetting the board state.
     * Post: undo a move by removing the piece from the specified button
     * @param undo undo button
     */
    public void undo(Recorder.Piece undo) {
        Node node = null;

        List<Node> nodesList = this.getChildren();
        for (Node child : nodesList) {
            Integer columnIndex = GridPane.getColumnIndex(child);
            Integer rowIndex = GridPane.getRowIndex(child);
            if (columnIndex != null && rowIndex != null && columnIndex == undo.getX() && rowIndex == undo.getY()) {
                node = child;
                break;
            }
        }
        Button button = (Button) node;
        assert button != null;
        Pane graphic = (Pane) button.getGraphic();
        ObservableList<Node> children = graphic.getChildren();
        children.removeIf(element -> element instanceof Circle);
        board[undo.getX()][undo.getY()] = 0;
    }

    /**
     * This is an interface with a method signature handle(int x, int y, int player) that
     * takes in the current move's x and y coordinates and the current player's number than arguments.
     */
    public interface Handle{void handle(int x, int y, int player);}
}
