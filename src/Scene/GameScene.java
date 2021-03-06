package Scene;

import Config.Config;
import Geometric.Vector2D;
import Map.TileMap;
import Model.Player;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 23/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public class GameScene extends Scene {
    private Group root;
    private Canvas canvas;
    private ArrayList<String> inputKey;
    AnimationTimer mainLoopManager;

    int debugInterval = 0;
    int fps;
    long lastUpdateTime = 0;

    // variables
    private TileMap map;
    Player player;

    public GameScene() {
        super(new Group());
        setupGameLoop();
        newGame();
    }

    private void setupGameLoop() {
        setProperties();
        setOnKey();
        setAnimationTimer();

        mainLoopManager.start();
    }

    private void setProperties() {
        this.root = (Group) super.getRoot();
        this.canvas = new Canvas(Config.WindowProperties.WINDOW_WIDTH, Config.WindowProperties.WINDOW_HEIGHT);
        this.root.getChildren().add(canvas);
    }

    private void setOnKey() {
        this.inputKey = new ArrayList<>();
        this.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        // only add once... prevent duplicates
                        if (!inputKey.contains(code)) {
                            inputKey.add(code);
                        }
                    }
                });

        this.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        inputKey.remove(code);
                    }
                });
    }

    private void setAnimationTimer() {
        mainLoopManager = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                handleEvents(inputKey);
                update(currentTime);
                render(canvas.getGraphicsContext2D());
            }
        };
    }

    private void handleEvents(List<String> input) {
        if (input.contains("LEFT")) this.player.moveLeft();
        else if (input.contains("RIGHT")) this.player.moveRight();
        else if (input.contains("DOWN")) this.player.moveDown();
        else if (input.contains("UP")) this.player.moveUp();
        else this.player.stopMove();
    }

    private void update(long currentTime) {
        double dt = (currentTime - lastUpdateTime) / Config.NANOSECONDPERSEC;
        if (dt > 0.03) dt = 0.03;
        lastUpdateTime = currentTime;

        // logic code come here
        player.update(dt);

        // debug
        if (debugInterval >= 30) {
            debugInterval = 0;
            this.fps = (int) (1 / dt);
        }
        debugInterval++;
    }

    private void render(GraphicsContext gc) {
        // clear canvas
        gc.fillRect(0, 0, Config.WindowProperties.WINDOW_WIDTH, Config.WindowProperties.WINDOW_HEIGHT);

        // our code will come here
        map.render(gc);
        player.render(gc);

        // for debug purpose
        gc.setStroke(Color.AQUA);
        gc.strokeText("FPS: " + String.valueOf(this.fps), this.getWidth() - 80, this.getHeight() - 30);
    }

    // This method specifies map, player for new game
    private void newGame() {
        map = new TileMap(this, 5);
        player = new Player("sprites/Player00.png");
        player.setPosition(new Vector2D(100, 100));
    }
}
