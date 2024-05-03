package game;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import states.GameStateManager;
import util.KeyHandler;
import util.MouseHandler;

public class GameLauncher extends Application {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private static final int TARGET_FPS = 60;
    private GraphicsContext graphicsContext;
    private MouseHandler mouseInputHandler;
    private KeyHandler keyboardInputHandler;
    private GameStateManager gameStateManager;

    private long lastSecondTime = System.nanoTime() / 1000000000;
    private int frameCount = 0;
    private void startGameLoop() {
        final Duration frameTime = Duration.millis(1000.0 / TARGET_FPS);
        KeyFrame gameFrame = new KeyFrame(frameTime, event -> {
            update();
            input(mouseInputHandler, keyboardInputHandler);
            render();

            frameCount++;
            long now = System.nanoTime() / 1000000000;
            if (now > lastSecondTime + 1) { // Check if one second has passed
                System.out.println("FPS: " + frameCount);
                frameCount = 0;
                lastSecondTime = now;
            }
        });

        Timeline timeline = new Timeline(gameFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Starting Project Witch...");
        primaryStage.setTitle("Project Witch");
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        canvas.getGraphicsContext2D().setImageSmoothing(false);
        graphicsContext = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
        mouseInputHandler = new MouseHandler(canvas);
        keyboardInputHandler = new KeyHandler(canvas);
        gameStateManager = new GameStateManager();
        startGameLoop();
    }

    private void update() {
        gameStateManager.update();
    }

    private void input(MouseHandler mouse, KeyHandler key) {
        gameStateManager.input(mouse, key);
    }

    private void render() {
        graphicsContext.setFill(Color.rgb(66, 34, 244));
        graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);
        gameStateManager.render(graphicsContext);
    }

}
