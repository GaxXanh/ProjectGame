package GameScene;

import Config.Config;
import javafx.animation.AnimationTimer;
import javafx.beans.NamedArg;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 17/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public class GameScene extends Scene {
    public Group root;
    public Canvas canvas;
    AnimationTimer mainLoopManager;

    /**
     * Creates a Scene for a specific root Node.
     *
     * @throws NullPointerException if root is null
     */

    public GameScene() {
        super(new Group());
        this.setupGameLoop();
        this.newLevel();
    }

    public void setupGameLoop() {
        this.root = (Group) super.getRoot();
        this.canvas = new Canvas(Config.WindowProperties.WINDOW_WIDTH, Config.WindowProperties.WINDOW_HEIGHT);
        this.root.getChildren().add(canvas);

        ArrayList<String> input = new ArrayList<>();
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = event.getCode().toString();

                if (!input.contains(code)) {
                    input.add(code);
                }
            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = event.getCode().toString();
                input.remove(code);
            }
        });

        mainLoopManager = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                handleEvents(input);
                update(currentTime);
                render(canvas.getGraphicsContext2D());
            }
        };

        mainLoopManager.start();
    }

    public void handleEvents(List<String> input) {

    }

    public void update(long currentTime) {

    }

    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, Config.WindowProperties.WINDOW_WIDTH, Config.WindowProperties.WINDOW_HEIGHT);

    }

    private void newLevel() {

    }

}
