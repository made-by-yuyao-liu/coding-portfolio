package cn.sixinarow.testing;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * SixInARowMain Class
 * This class tests the stack that is used to undo and relay pegs.
 * @author Cindy Liu
 * @version 05.04.2023
 */
public class TestUndoStack {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isLastMoveNext = true; // Mark the last forward or backward operation
        String lastSelection = null; // Mark the last selected element

        // Create a new linked list object
        LinkedList<String> list = new LinkedList<>();

        // Add some data items to the lost
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");
        list.add("Element 4");

        // Set the iterator object and set its initial position to the first element
        ListIterator<String> iterator = list.listIterator();
        String currentSelection = null;

        // Output the currently selected element
        if (iterator.hasNext()) {
            currentSelection = iterator.next();
            System.out.println("Currently selected: " + currentSelection);
        }

        // Read user input and get the next or previous element
        while (true) {
            System.out.print("Please enter \"next\" or \"previous\"View the next or previous element: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("next")) {
                if (!isLastMoveNext) {
                    iterator.next();
                }

                if (iterator.hasNext()) {
                    currentSelection = iterator.next();
                    isLastMoveNext = true;
                } else {
                    System.out.println("You have reached the end of the list.");
                    continue;
                }
            } else if (input.equalsIgnoreCase("pre")) {
                if (isLastMoveNext) {
                    iterator.previous();
                }

                if (iterator.hasPrevious()) {
                    currentSelection = iterator.previous();
                    isLastMoveNext = false;
                } else {
                    System.out.println("There are no more pegs in front.");
                    continue;
                }
            } else {
                System.out.println("Invalid input, please re-enter!");
                continue;
            }

            // Update tag data
            lastSelection = currentSelection;
            System.out.println("Currently selected: " + currentSelection);
        }
    }

}
