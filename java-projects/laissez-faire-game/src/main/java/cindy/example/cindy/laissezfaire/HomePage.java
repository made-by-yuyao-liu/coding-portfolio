package cindy.example.cindy.laissezfaire;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import cindy.example.cindy.base.Mouse;
import cindy.example.cindy.base.Window;

import java.net.URL;


/**
 * HomePage Class
 * This class represents and controls the home page of the game
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class HomePage {
    //Attributes/instance variables
    private Image crudeOilDropImage = new Image("crudeOilDrop.png");

    private Image firstPlayerImage = new Image("Pirate.png");
    private double firstPlayerXCoordinate = 25;
    private double firstPlayerYCoordinate = 520;
    private double firstPlayerWidth = 200;
    private double firstPlayerHeight = 120;

    private Image secondPlayerImage = new Image("Knight.png");
    private double secondPlayerXCoordinate = 190;
    private double secondPlayerYCoordinate = 525;
    private double secondPlayerWidth = 150;
    private double secondPlayerHeight = 110;

    private Image thirdPlayerImage = new Image("Ninja.png");
    private double thirdPlayerXCoordinate = 315;
    private double thirdPlayerYCoordinate = 520;
    private double thirdPlayerWidth = 200;
    private double thirdPlayerHeight = 120;

    private Image fourthPlayerImage = new Image("Warrior.png");
    private double fourthPlayerXCoordinate = 390;
    private double fourthPlayerYCoordinate = 525;
    private double fourthPlayerWidth = 300;
    private double fourthPlayerHeight = 120;
    private MediaPlayer mediaPlayer;

    //Default constructor
    public HomePage() {
    }


    /**
     * This method is used to load the home page
     * Pre: the root and window have been passed in
     * Post: the home page is loaded
     * @param root This is the first parameter to loadPage method
     * @param window This is the second parameter to loadPage method
     * @return void This does not return anything
     */
    public void loadPage(Group root, Window window) {
        loadMedia(root);
        window.setBackground(Color.CORNSILK);
    }

    /**
     * This method is used to load the media
     * Pre: the root has been passed in
     * Post: the media is loaded
     * @param root This is the first parameter to loadMedia method
     * @return void This does not return anything
     * References:
     *      "Class MediaPlayer." docs.oracle.com. Accessed May 16, 2022.
     *      https://docs.oracle.com/javase/8/javafx/api/javafx/scene/media/MediaPlayer.html.
     *      "Class MediaView." docs.oracle.com. Accessed May 16, 2022.
     *      https://docs.oracle.com/javase/8/javafx/api/javafx/scene/media/MediaView.html.
     *      "MediaPlayer | JavaFX GUI Tutorial for Beginners." Video. YouTube.
     *      https://youtu.be/evd2mLwMjho.
     */
    private void loadMedia(Group root) {
        //load the url for the video tutorial
        URL url = this.getClass().getClassLoader().getResource("videoTutorial.mp4");
        Media media = new Media(url.toExternalForm());
        this.mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        //do not preserve the media aspect ratio when scaling
        mediaView.setPreserveRatio(false);
        mediaView.setFitWidth(420);
        mediaView.setFitHeight(210);
        mediaView.setX(630);
        mediaView.setY(430);
        //turn off autoplay
        mediaPlayer.setAutoPlay(false);
        root.getChildren().add(new Pane(mediaView));
    }

    /**
     * This method is used to draw the home page
     * Pre: the pen has been passed in
     * Post: the home page is drawn
     * @param pen This is the first parameter to draw method
     * @return void This does not return anything
     */
    public void draw(GraphicsContext pen) {
        //draw the logo
        pen.setStroke(Color.BLACK);
        //big circle in the logo
        pen.setFill(Color.GREEN);
        pen.fillOval(400, 50, 300, 300);
        pen.setLineWidth(3);
        pen.strokeOval(400, 50, 300, 300);
        //fill colors in the cans
        pen.setFill(Color.DARKSEAGREEN);
        pen.fillRect(507.5, 170, 84.5, 25);
        pen.fillRect(507.5, 220, 84.5, 25);
        pen.fillRect(507.5, 270, 84.5, 25);
        pen.setFill(Color.INDIANRED);
        pen.fillRect(507.5, 195, 84.5, 25);
        pen.fillRect(507.5, 245, 84.5, 25);
        pen.fillRect(507.5, 295, 84.5, 25);

        //draw the oil can
        pen.strokeRect(507.5, 170, 85, 150);
        pen.strokeLine(385, 145, 715, 145);

        //draw lines in the oil can
        pen.strokeLine(507.5, 195, 592, 195);
        pen.strokeLine(507.5, 220, 592, 220);
        pen.strokeLine(507.5, 245, 592, 245);
        pen.strokeLine(507.5, 270, 592, 270);
        pen.strokeLine(507.5, 295, 592, 295);

        //draw the oil drop image
        pen.drawImage(crudeOilDropImage, 508, 187, 85, 120);

        pen.setFill(Color.FIREBRICK);

        //draw the polygon banner
        pen.fillPolygon(new double[]{385, 415, 385, 716, 687, 716}, new double[]{86, 118, 145, 145, 118, 86}, 6);
        pen.setLineWidth(4);
        pen.strokePolygon(new double[]{385, 415, 385, 716, 687, 716}, new double[]{86, 118, 145, 145, 118, 86}, 6);

        //draw thr title of the game
        pen.setFont(new Font("Helvetica", 38));
        pen.setFill(Color.BLACK);
        pen.fillText("LAISSEZ-faire", 430, 140);

        //draw the frame for the characters box
        pen.setLineWidth(4);
        pen.setStroke(Color.BLACK);
        pen.strokeRect(50, 430, 550, 230);

        //draw the words "Pick your character:"
        pen.setFont(new Font("Impact", 24));
        pen.fillText("Pick your character:", 75, 485);

        //draw the characters
        pen.drawImage(firstPlayerImage, firstPlayerXCoordinate, firstPlayerYCoordinate, firstPlayerWidth, firstPlayerHeight);
        pen.drawImage(secondPlayerImage, secondPlayerXCoordinate, secondPlayerYCoordinate, secondPlayerWidth, secondPlayerHeight);
        pen.drawImage(thirdPlayerImage, thirdPlayerXCoordinate, thirdPlayerYCoordinate, thirdPlayerWidth, thirdPlayerHeight);
        pen.drawImage(fourthPlayerImage, fourthPlayerXCoordinate, fourthPlayerYCoordinate, fourthPlayerWidth, fourthPlayerHeight);

        //draw the outer frame for the video tutorial
        pen.strokeRect(630, 430, 420, 230);
        pen.setLineWidth(3);

        //draw the start and pause buttons for the video tutorial
        pen.setLineWidth(2);
        pen.strokeRoundRect(645, 642, 60, 15, 10, 10);
        pen.strokeRoundRect(710, 642, 60, 15, 10, 10);
        pen.setLineWidth(1);
        pen.strokePolygon(new double[]{665, 665, 685}, new double[]{643, 657, 650}, 3);
        pen.beginPath();
        pen.moveTo(733, 645);
        pen.lineTo(733, 655);
        pen.moveTo(747, 645);
        pen.lineTo(747, 655);
        pen.stroke();
    }

    /**
     * This method is used to play the video tutorial
     * Post: the video tutorial is played
     * @return void This does not return anything
     */
    public void playTutorial() {
        //compare the current start time with the stop time
        if (mediaPlayer.getCurrentTime().equals(mediaPlayer.getStopTime())) {
            //stop the video
            mediaPlayer.stop();
        }
        //play the video
        mediaPlayer.play();
    }

    /**
     * This method is used to pause the video tutorial
     * Post: the video is paused
     * @return void This does not return anything
     */
    public void pauseTutorial() {
        //the video is paused
        mediaPlayer.pause();
    }

    /**
     * This method is used to control the mouse click
     * Pre: the mouse has been passed in
     * Post: a number between 0-6 is returned
     * @param mouse This is the first parameter to mouseClicked method
     * @return int This returns a number between 0-6
     */
    public int mouseClicked(Mouse mouse) {
        //get the x and y coordinates of the mouse
        double x = mouse.x;
        double y = mouse.y;

        //determine whether the mouse click is within the play button
        boolean mediaPlayFlag = (x > 645 && x < 710) && (y > 642 && y < 657);
        if (mediaPlayFlag) {
            //if it is within the play button, return 1
            return 1;
        }
        //determine whether the mouse click is within the pause button
        boolean mediaPauseFlag = (x > 710 && x < 770) && (y > 642 && y < 657);
        if (mediaPauseFlag) {
            //if it is within the pause button, return 2
            return 2;
        }

        //determine whether the mouse click is within the width and height of player 1
        boolean firstPlayerSelectedFlag = (x > 65 && x < 185) && (y > 520 && y < 640);
        //determine whether the mouse click is within the width and height of player 2
        boolean secondPlayerSelectedFlag = (x > 205 && x < 327) && (y > 520 && y < 640);
        //determine whether the mouse click is within the width and height of player 3
        boolean thirdPlayerSelectedFlag = (x > 345 && x < 465) && (y > 520 && y < 640);
        //determine whether the mouse click is within the width and height of player 4
        boolean fourthPlayerSelectedFlag = (x > 450 && x < 570) && (y > 520 && y < 640);

        //return the number corresponding to the selected player
        if (firstPlayerSelectedFlag) {
            return 3;
        } else if (secondPlayerSelectedFlag) {
            return 4;
        } else if (thirdPlayerSelectedFlag) {
            return 5;
        } else if (fourthPlayerSelectedFlag) {
            return 6;
        }
        return 0;
    }
    /**
     * This method is used to control the moving of the mouse
     * Pre: the mouse and pen have been passed in
     * Post: if the mouse slides over a character, there will be a red box over that character
     * @param mouse This is the first parameter to mouseMoved method
     * @param pen This is the second parameter to mouseMoved method
     * @return void This does not return anything
     */
    public void mouseMoved(Mouse mouse, GraphicsContext pen) {
        //get the x and y coordinates of the mouse
        double x = mouse.x;
        double y = mouse.y;

        //determine whether the mouse is sliding over the first character
        boolean firstPlayerSelectedFlag = (x > firstPlayerXCoordinate && x < firstPlayerXCoordinate + firstPlayerWidth) && (y > firstPlayerYCoordinate && y < firstPlayerYCoordinate + firstPlayerHeight);
        //determine whether the mouse is sliding over the second character
        boolean secondPlayerSelectedFlag = (x > secondPlayerXCoordinate && x < secondPlayerXCoordinate + secondPlayerWidth) && (y > secondPlayerYCoordinate && y < secondPlayerYCoordinate + secondPlayerHeight);
        //determine whether the mouse is sliding over the third character
        boolean thirdPlayerSelectedFlag = (x > thirdPlayerXCoordinate && x < thirdPlayerXCoordinate + thirdPlayerWidth) && (y > thirdPlayerYCoordinate && y < thirdPlayerYCoordinate + thirdPlayerHeight);
        //determine whether the mouse is sliding over the fourth character
        boolean fourthPlayerSelectedFlag = (x > fourthPlayerXCoordinate && x < fourthPlayerXCoordinate + fourthPlayerWidth) && (y > fourthPlayerYCoordinate && y < fourthPlayerYCoordinate + fourthPlayerHeight);

        //the player that the mouse slides over will have a red box around it
        if (firstPlayerSelectedFlag) {
            pen.setStroke(Color.RED);
            pen.strokeRect(65, 520, 120, 120);
        } else if (secondPlayerSelectedFlag) {
            pen.setStroke(Color.RED);
            pen.strokeRect(205, 520, 122, 120);
        } else if (thirdPlayerSelectedFlag) {
            pen.setStroke(Color.RED);
            pen.strokeRect(345, 520, 120, 120);
        } else if (fourthPlayerSelectedFlag) {
            pen.setStroke(Color.RED);
            pen.strokeRect(470, 520, 120, 120);
        }
    }

}
