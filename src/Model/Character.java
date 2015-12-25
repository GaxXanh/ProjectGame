package Model;

import Geometric.Vector2D;
import javafx.scene.image.Image;
import xmlwise.Plist;
import xmlwise.XmlParseException;

import java.io.FileInputStream;
import java.io.IOException;
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
    protected int characterDirection;
    protected AnimatedImage animation;
    protected double timeElapsedSinceStartAnimation;
    protected boolean isMoving = false;
    protected Vector2D velocity = Vector2D.zero;

    public Character(String imageName){
        super(imageName);

        this.characterState = CharacterState.STANDING;
        frameDictionary = new HashMap<>();
        this.timeElapsedSinceStartAnimation = 0;
        this.loadAnimations();
    }

    protected void changeState(CharacterState newState){
        if (newState == this.characterState) return;
        this.characterState = newState;
        this.timeElapsedSinceStartAnimation = 0;
    }

    protected void updateAnimation(double dt){
        timeElapsedSinceStartAnimation += dt;
        animation = frameDictionary.get(characterState);
        animation.repeat = isMoving;
        this.setTexture(animation.getFrame(timeElapsedSinceStartAnimation));
    }

    protected AnimatedImage loadAnimations(String animationName, boolean repeat)
    {
        String className = this.getClassName();
        try {
            Map<String, Object> root = Plist.load("data/" + className + ".plist");
            Map<String, Object> properties = (Map<String, Object>) root.get(animationName);
            String[] imageNames = properties.get("animationFrames").toString().split(",");
            double duration = Double.valueOf(properties.get("delay").toString());

            Image[] images = new Image[imageNames.length];
            for (int i = 0; i < images.length; i++) {
                images[i] = new Image(new FileInputStream("sprites/" + className + imageNames[i] + ".png"));
            }
            AnimatedImage result = new  AnimatedImage(images, duration, repeat);
            return result;
        } catch (XmlParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    abstract void loadAnimations();
    abstract void updateStateAndAcceleratorAndVelocity(double dt);
    abstract void controlVelocityAndPosition(double dt);

    public enum CharacterState
    {
        STANDING, MOVE_UP, MOVE_RIGHT, MOVE_DOWN, MOVE_LEFT
    }

    public class CharacterDirection {
        public static final int NONE = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
        public static final int LEFT = 4;
    }
}
