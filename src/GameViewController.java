import Scene.GameScene;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 20/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public class GameViewController extends Application{

    // Properties
    GameScene gameScene;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Project X");
        gameScene = new GameScene();
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
