package cindy.example.cindy.base;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class represents the overall application window (Stage and Scene).
 *
 * Methods can be used to change the title, size, resizability and background color
 *
 * @author: Adam Drenth
 * @version: 12.12.2021
 */
public class Window {
    private static Window window;
    private static double width;
    private static double height;
    private Stage stage;
    private Scene scene;

    // Constructor
    public Window(Stage stage, Scene main) {
        window = this;
        this.stage = stage;
        scene = main;
        stage.setResizable(false);
    }

    // Modified Singleton method to ensure no duplicate Matrix objects can be created.
    public static Window getWindow() {
        if(window == null) {
            System.err.println("Invalid getWindow() call. It has not been initialized()");
            Platform.exit();
        }
        return window;
    }

    // Getters and Setters
    public double getWidth() { return stage.getWidth(); }
    public double getHeight() { return stage.getHeight(); }
    public void setWidth(double width) { stage.setWidth(width); }
    public void setHeight(double height) { stage.setHeight(height); }

    public void setResizable(boolean isResizable) { stage.setResizable(isResizable); }
    public void setTitle(String title) { stage.setTitle(title); }

    /**
     * Sets the background of the whole window
     * @param c the Color to be applied
     */
    public void setBackground(Color c) {
        scene.setFill(c);
    }

    /**
     * Initialization method for that ties P5.js/Processing-like window events with JavaFX Stage/Scene events
     * @param pfx The main application for the library
     * @param stage The main Stage that events will be attached to
     */
    private void init(ProcessingFX pfx, Stage stage) {
        stage.widthProperty().addListener(event -> {
            width = stage.getWidth();
            pfx.windowChanged();
        });
        stage.heightProperty().addListener(event -> {
            width = stage.getHeight();
            pfx.windowChanged();
        });
    }
}
