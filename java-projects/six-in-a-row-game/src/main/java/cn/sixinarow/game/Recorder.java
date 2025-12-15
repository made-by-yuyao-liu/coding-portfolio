package cn.sixinarow.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Recorder Class
 * This class stores the history of pieces added to a board game and allows the undo and clear
 * of moves, as well as the creation of an iterator to navigate through the history.
 * @author Cindy Liu
 * @version 05.05.2023
 */
public class Recorder implements Iterator<Recorder.Piece> {

    // Attributes/instance variables
    private final LinkedList<Piece> history = new LinkedList<>();
    private final LinkedList<Piece> history_1 = new LinkedList<>();

    //This method adds a "Piece" object to a linked list of previous moves called "history".
    public void add(Piece piece){
        history.addLast(piece);
    }

    //The undo method removes the last piece added to the history list and returns it,
    // adding it to a separate list for future access.
    public Piece undo(){
        if (history.isEmpty()) {
            return null;
        }
        Piece piece = history.pollLast();
        history_1.addLast(piece);
        return piece;
    }

    //This method clears the recorded history of the game.
    public void clear(){
        history_1.clear();
        history.clear();
    }

    @Override
    public boolean hasNext() {
        return !history_1.isEmpty();
    }

    @Override
    public Piece next() {
        return history_1.removeLast();
    }

    //Returns an iterator that looks at the elements before and after
    public ListIterator<Piece> history(){
        return history.listIterator();
    }

    public void pushLast(Piece undo) {
        history_1.addLast(undo);
    }


    /**
     * Piece Class
     * This is a nested class called Piece with properties and methods for managing a piece on a game board.
     * @author Cindy Liu
     * @version 05.05.2023
     */
    public static class Piece {

        // Attributes/instance variables
        private int x;
        private int y;
        private int player;

        private int imgPlayer1;
        private int imgPlayer2;

        // Parameterized constructor
        public Piece(int x, int y, int player, int imgPlayer1, int imgPlayer2) {
            this.x = x;
            this.y = y;
            this.player = player;
            this.imgPlayer1 = imgPlayer1;
            this.imgPlayer2 = imgPlayer2;
        }

        // getters and setters
        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getPlayer() {
            return player;
        }

    }
}
