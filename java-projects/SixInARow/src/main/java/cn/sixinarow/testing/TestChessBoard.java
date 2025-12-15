package cn.sixinarow.testing;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * SixInARowGame Class
 * This is a class that test the game of Six in a Row which is played on a 18x18 board,
 * where each player takes turns placing their pieces on the board, and the first player to
 * get six of their pieces in a row horizontally, vertically, or diagonally wins the game.
 * @author Cindy Liu
 * @version 05.07.2023
 */
public class TestChessBoard extends Application {

    // Attributes/instance variables
    private int currentPlayer = 1;
    private final int[][] board = new int[18][18];
    private static final int CELL_SIZE = 30;

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(0);
        root.setVgap(0);

        // Create board and buttons
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                Button button = new Button();

                // Create a Pane as a graphical node for the Button
                Pane graphic = new Pane();

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
                button.setOnAction(event -> move(finalI, finalJ, button));
                root.add(button, i, j);
            }
        }

        Scene scene = new Scene(root, CELL_SIZE * 18 + 28, CELL_SIZE * 18 + 28);
        stage.setScene(scene);
        stage.setTitle("Six in a Row Game");
        stage.show();
    }

    private void move(int x, int y, Button button) {
        if (board[x][y] != 0) {
            return; // If there are already pieces in this position, you cannot play
        }

        drawChess(x, y, button);
        board[x][y] = currentPlayer;

        if (checkWin(x, y)) {
            System.out.println("Player with " + (currentPlayer == 1?"white pegs":"black pegs") + " wins!");
            System.exit(0);
        }

        currentPlayer = currentPlayer == 1 ? 2 : 1;
    }

    private void drawChess(int x, int y, Button button) {
        Pane graphic = (Pane) button.getGraphic();
        Circle circle = new Circle(CELL_SIZE * 0.40);
        circle.setStroke(Color.BLACK);
        circle.setFill(currentPlayer == 1 ? Color.BLACK : Color.WHITE);
        circle.setCenterX(CELL_SIZE/2.0);
        circle.setCenterY(CELL_SIZE/2.0);
        graphic.getChildren().add(circle);
    }

    // Check if 6 pegs are in a row
    private boolean checkWin(int x, int y) {
        int player = board[x][y];

        // check horizontally
        int count = 0;
        for (int i = Math.max(0, x - 5); i <= Math.min(17, x + 5); i++) {
            if (board[i][y] == player) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 6) {
                return true;
            }
        }

        // check vertically
        count = 0;
        for (int j = Math.max(0, y - 5); j <= Math.min(17, y + 5); j++) {
            if (board[x][j] == player) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 6) {
                return true;
            }
        }

        // Check the diagonal top direction (top left to bottom right)
        int differ = Math.min(Math.min(5, x), Math.min(5, y));
        count = 0;
        for (int i = x - differ, j = y - differ; i <= Math.min(x + 5, 17) && j <= Math.min(y + 5, 17); i++, j++) {
            if (board[i][j] == player) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 6) {
                return true;
            }
        }

        // Check the diagonal downward direction (lower left to upper right)
        differ = Math.min(Math.min(5, x), y + 5 > 17?17-y:5);
        count = 0;
        for (int i = x - differ, j = y + differ; i <= Math.min(x + 5, 17) && j >= Math.max(y - 5, 0); i++, j--) {
            if (board[i][j] == player) {
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

    public static void main(String[] args) {
        launch(args);
    }
}
