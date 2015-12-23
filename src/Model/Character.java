package Model;

import Geometric.Vector2D;
import javafx.scene.image.Image;
import xmlwise.Plist;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 23/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public abstract class Character extends GameObject{

    protected Map<CharacterState, AnimatedImage> frameDictionary;
    protected CharacterState characterState;
    protected double timeElapsedSinceStartAnimation;
    protected AnimatedImage animation;
    protected int characterDirection;

    public Vector2D velocity = Vector2D.zero;

    protected boolean isMoving = false;

    public Character(String imageName){
        super(imageName);
        this.characterState = CharacterState.STANDING;
        this.frameDictionary = new HashMap<>();
        this.timeElapsedSinceStartAnimation = 0;
        this.loadAnimations();
    }

    public void loadAnimations() {}

    protected AnimatedImage loadAnimations(String className, String animationName, boolean repeat, String object) {
        try {
            Map<String, Object> root = Plist.load("data/" + className + ".plist");
            Map<String, Object> properties = (Map<String, Object>)root.get(animationName);
            String[] imageNames = properties.get("animationFrames").toString().split(",");
            double duration = Double.valueOf(properties.get("delay").toString());

            Image[] images = new Image[imageNames.length];
            for (int i = 0; i < images.length; i++) {
                images[i] = new Image(new FileInputStream("sprites/" + object + imageNames[i] + ".png"));
            }
            AnimatedImage result = new AnimatedImage(images, duration, repeat);
            return result;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    protected AnimatedImage loadAnimations(String animationName, boolean repeat, String object) {
        return loadAnimations(this.getClassName(), animationName, repeat, object);
    }

    public enum CharacterState{
        STANDING, MOVE_UP, MOVE_RIGHT, MOVE_DOWN, MOVE_LEFT
    }

    @Override
    public void update(double dt) {
        updateAnimation(dt);
    }

    protected void updateState(double dt) {

    }

    protected void updateAnimation(double dt){
        this.timeElapsedSinceStartAnimation += dt;
        double elapsedTime = (double)(this.timeElapsedSinceStartAnimation);
        animation = frameDictionary.get(this.characterState);
        animation.repeat = isMoving;

        this.setTexture(animation.getFrame(elapsedTime));
    }

    public void changeState(CharacterState newState) {
        if (newState == this.characterState) return;
        this.characterState = newState;
        this.timeElapsedSinceStartAnimation = 0;
        animation = frameDictionary.get(this.characterState);
    }

    public class CharacterDirection {
        public static final int NONE = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
        public static final int LEFT = 4;
    }
}
