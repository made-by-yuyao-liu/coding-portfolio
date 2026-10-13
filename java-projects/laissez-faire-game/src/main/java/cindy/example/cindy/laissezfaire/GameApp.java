package cindy.example.cindy.laissezfaire;

import cindy.example.cindy.base.ProcessingFX;
import javafx.scene.canvas.GraphicsContext;

/**
 * GameApp Class
 * This class is the central control of the entire game
 * @author Cindy Liu
 * @version 05.16.2022
 */
public class GameApp extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }
    //Attributes/instance variables
    private String playerImageName;
    private GamePage gamePage;
    private HomePage homePage;
    //set the default of the currentPage as 1
    private int currentPage = 1;
    private GraphicsContext pen;

    /**
     * This method is used to set up the game
     * Pre: the pen has been passed in
     * Post: the game is set up
     * @param pen This is the first parameter to set up method
     * @return void This does not return anything
     */
    @Override
    protected void setup(GraphicsContext pen) {
        this.pen = pen;
        loadPage(currentPage);
    }

    /**
     * This method is used to load the chosen page
     * Pre: pageCode has been passed in
     * Post: the page is loaded
     * @param pageCode This is the first parameter to loadPage method
     * @return void This does not return anything
     */
    private void loadPage(int pageCode) {
        homePage = new HomePage();
        gamePage = new GamePage(playerImageName);

        switch (pageCode) {
            //If the home page is chosen
            case 1:
                //Clear the canvas
                clearCanvas();
                //Add a new canvas
                addCanvas();
                homePage.loadPage(root, window);
                //the code of the page is given to the current page
                currentPage = pageCode;
                break;
            //If the game page is chosen
            case 2:
                //Clear the canvas
                clearCanvas();
                //Add a new canvas
                addCanvas();
                gamePage.loadGamePage(window);
                //the code of the page is given to the current page
                currentPage = pageCode;
                break;

            //the default is to load the home page
            default:
                loadPage(1);
        }
    }

    /**
     * This method is used to draw the pages
     * Pre: the pen has been passed in
     * Post: pages are drawn
     * @param pen This is the first parameter to draw method
     * @return void This does not return anything
     */
    @Override
    protected void draw(GraphicsContext pen) {
        //The page is cleared
        pen.clearRect(0, 0, window.getWidth(), window.getHeight());
        switch (currentPage) {
            case 1:
                homePage.draw(pen);
                break;
            case 2:
                gamePage.draw(pen, matrix);
                break;
            default:
                homePage.draw(pen);
                break;
        }
    }

    /**
     * This method is used to control the mouse click
     * Post: the mouse click will result in a page
     * @return void This does not return anything
     */
    @Override
    public void mouseClicked() {
        switch (currentPage) {
            case 1:
                //choose character or watch video by clicking the mouse
                int clickedCode = homePage.mouseClicked(mouse);
                switch (clickedCode){
                    //when the Play ">" button is clicked, the tutorial is played
                    case 1:
                        homePage.playTutorial();
                        break;

                    //when the Pause "||" button is clicked, the tutorial is paused
                    case 2:
                        homePage.pauseTutorial();
                        break;

                    //when the pirate character is clicked, the pirate goes to the game page
                    case 3:
                        this.playerImageName = "Pirate.png";
                        this.gamePage = new GamePage(playerImageName);
                        loadPage(2);
                        break;

                    //when the knight image is clicked, the knight goes to the game page
                    case 4:
                        this.playerImageName = "Knight.png";
                        this.gamePage = new GamePage(playerImageName);
                        loadPage(2);
                        break;

                    //when the ninja image is clicked, the ninja goes to the game page
                    case 5:
                        this.playerImageName = "Ninja.png";
                        this.gamePage = new GamePage(playerImageName);
                        loadPage(2);
                        break;

                    //when the warrior image is clicked, the warrior goes to the game page
                    case 6:
                        this.playerImageName = "Warrior.png";
                        this.gamePage = new GamePage(playerImageName);
                        loadPage(2);
                        break;

                    //the default is the current page
                    default:
                        loadPage(currentPage);
                        break;
                }
                break;
            case 2:
                //choose button based on where the mouse is clicked on the game page
                int choose = gamePage.mouseClicked(mouse);
                if (choose == 1) {
                    //if 1 is chosen, then the dice is rolled
                    gamePage.diceControl();
                } else if (choose == 2) {
                    //if 2 is chosen, the game page is reloaded
                    this.gamePage = new GamePage(playerImageName);
                    loadPage(currentPage);
                } else if (choose == 3) {
                    //if 3 is chosen, the home page is loaded
                    loadPage(1);
                }
                break;

            //the default is to load page 1
            default:
                loadPage(1);
                break;
        }
    }

    /**
     * This method is used to detect the movement of the mouse
     * Post: the mouseMoved event is called on the home page
     * @return void This does not return anything
     */
    @Override
    public void mouseMoved() {
        //when the current page is 1 (home page), call the mouseMoved event
        if (currentPage == 1) {
            homePage.mouseMoved(mouse, pen);
        }
    }
}