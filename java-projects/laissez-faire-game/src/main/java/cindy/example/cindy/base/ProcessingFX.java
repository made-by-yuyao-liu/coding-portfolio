package cindy.example.cindy.base;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The root class that organizes all the relationships between JavaFX, the Keyboard, the Mouse, the Screen and the ability
 * to draw on the window.
 *
 * @author: Adam Drenth
 * @version: 12.12.2021
 */
public abstract class ProcessingFX extends Application {
    // MyExampleApp类可以访问这些变量
    protected Window window;    // 维护窗口本身的状态和控制，包括大小和标题
    protected Matrix matrix;    // 跟踪和管理Convas上的转换
    protected Mouse mouse;      // 跟踪并维护鼠标的状态
    protected Keyboard key;     // 跟踪并维护键盘的状态
    protected Group root;

    /*//视频
    protected MediaView mediaView;
    protected MediaPlayer mediaPlayer;
*/
    // 这些变量对于所有其他类和对象都是隐藏的。
    private Scene scene;     // 窗口的根节点
    private Canvas canvas;   // 绘制所有元素的主要组件
    private Timeline drawLoop = new Timeline(); // 调用draw（）函数的动画循环

    // Default size
    private final int DEFAULT_WIDTH = 1100;
    private final int DEFAULT_HEIGHT = 700;

    /**
     * The "Main method" for all JavaFX programs.  It is launched after the JavaFX components are initialized.
     * This method is used to attached all the required components to the application window and it works like Russian
     * dolls.
     * <p>
     * Stage represents the application window. It holds everything and have the overall visual size
     * Scene is everything is current present in the window.  Everything MUST be attached to the scene to be visible
     * Group root organzes everything to be attached to the Scene.  Everything MUST be attached to the root or something
     * else that is ultimately attached to the root to be visible
     * <p>
     * Stage <- Scene <- root <- {Everything else} <- ...
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        // Create and stack everything together: scene <- root <- canvas
        root = new Group();
        canvas = new Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Setup and Initialize the objects that watch the state of the application (window, keyboard, mouse)
        window = new Window(stage, scene);
        mouse = new Mouse(this, scene);
        key = new Keyboard(this, scene);

        // Add the scene to the scene and make the application visible
        stage.setScene(scene);
        stage.show();

        // Starts the Processing-like or P5.js Processes where setup() is called once and draw() repeatedly afterwards
        startProcessingProcess();
    }

    /**
     * The proper way to shut down a JavaFX application. It ensures all required shut down methods are called.
     */
    protected void exit() {
        Platform.exit();
    }

    /**
     * Emulates P5.js or Processing Functionality by calling setup() at the start.  draw() is called over and over again
     */
    private void startProcessingProcess() {
        matrix = new Matrix(canvas);    // Matrix is watches and modifies the Canvas with transformations.

        // Call setup()
        GraphicsContext pen = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);     // 把画笔所在的图层放到最上面
        setup(pen);  // 加载地图
        root.getChildren().remove(canvas);  // 把画笔所在的图层放到最上面
        root.getChildren().add(canvas);     // 把画笔所在的图层放到最上面
        matrix.reset();

        // Call draw() an infinite number of times
        drawLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frames = new KeyFrame(
                Duration.seconds(0.017),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // Emulation of a draw method being called
                        GraphicsContext pen = canvas.getGraphicsContext2D();
                        draw(pen); // 鼠标比较
                        matrix.reset();
                    }
                }
        );
        // Add KeyFrame to the loop and start the animation
        drawLoop.getKeyFrames().add(frames);
        drawLoop.play();
    }


    /**
     * 安装方法必须在应用程序中实现。它沿着一个对象传递，允许您直接在画布上绘制。它只在开始时被调用一次。
     *
     * @param pen 用于在画布上绘制的对象
     */
    protected abstract void setup(GraphicsContext pen);

    /**
     * 安装方法必须在应用程序中实现。它沿着一个对象传递，允许您直接在画布上绘制。它在setup（）之后被调用，并以固定的时间间隔反复无限次地调用
     *
     * @param pen 用于在画布上绘制的对象
     */
    protected abstract void draw(GraphicsContext pen);

    /**
     * 为鼠标事件重载的可选方法
     */
    public void mouseClicked() {
    }   // 在特定时间范围内按下并释放鼠标时发生

    public void mousePressed() {
    }   // 在按下鼠标按钮时发生

    public void mouseReleased() {
    }  // 释放鼠标按钮时发生

    public void mouseDragged() {
    }   // 在按下鼠标并移动时发生

    public void mouseMoved() {
    }     // 当鼠标在窗口内移动时发生

    public void mouseWheel() {
    }     // 在鼠标滚动时发生

    /**
     * 为键盘事件重载的可选方法
     */
    public void keyTyped() {
    }       // 在特定时间范围内按下并释放某个键时发生。不包括特殊键（如箭头等）

    public void keyPressed() {
    }     // 在按下任何键时发生。按压时可能会反复发生。

    public void keyReleased() {
    }    // 在释放任何键时发生。

    /**
     * 为窗口事件重载的可选方法
     */
    public void windowChanged() {
    }  // 在调整阶段大小（主应用程序窗口）时发生

    protected void clearCanvas() {
        root.getChildren().clear();
    }

    protected void addCanvas() {
        root.getChildren().add(canvas);
    }

}
