package Model;

import Config.Config;
import Geometric.Vector2D;
import Geometric.Vector2DHelper;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 25/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public class Player extends Character {

    public Player(String imageName) {
        super(imageName);
//        characterState = CharacterState.MOVE_RIGHT;
    }

    @Override
    void loadAnimations() {
        frameDictionary.put(CharacterState.MOVE_UP, this.loadAnimations("moveUpAnim", true));
        frameDictionary.put(CharacterState.MOVE_RIGHT, this.loadAnimations("moveRightAnim", true));
        frameDictionary.put(CharacterState.MOVE_DOWN, this.loadAnimations("moveDownAnim", true));
        frameDictionary.put(CharacterState.MOVE_LEFT, this.loadAnimations("moveLeftAnim", true));
        frameDictionary.put(CharacterState.STANDING, this.loadAnimations("standingAnim",true));
    }

    @Override
    void updateStateAndAcceleratorAndVelocity(double dt) {
        CharacterState currentState = this.characterState;
        Vector2D joyForce = Vector2D.zero;

        isMoving = true;
        switch (characterDirection) {
            case CharacterDirection.NONE: {
                isMoving = false;
                break;
            }
            case CharacterDirection.UP: {
                joyForce = new Vector2D(0, -Config.PlayerProperties.WALKING_ACCELERATE);
                currentState = CharacterState.MOVE_UP;
                break;
            }
            case CharacterDirection.RIGHT: {
                joyForce = new Vector2D(+Config.PlayerProperties.WALKING_ACCELERATE, 0);
                currentState = CharacterState.MOVE_RIGHT;
                break;
            }
            case CharacterDirection.DOWN: {
                joyForce = new Vector2D(0, +Config.PlayerProperties.WALKING_ACCELERATE);
                currentState = CharacterState.MOVE_DOWN;
                break;
            }
            case CharacterDirection.LEFT: {
                joyForce = new Vector2D(-Config.PlayerProperties.WALKING_ACCELERATE, 0);
                currentState = CharacterState.MOVE_LEFT;
            }
        }

        Vector2D joyForceStep = Vector2DHelper.multiByScalar(joyForce, dt);
        this.velocity = Vector2DHelper.addVector(this.velocity, joyForceStep);

        changeState(currentState);
    }

    @Override
    void controlVelocityAndPosition(double dt) {
        this.velocity = new Vector2D(this.velocity.x * 0.85, this.velocity.y * 0.85);
        Vector2D velocityStep = Vector2DHelper.multiByScalar(this.velocity, dt);
        this.position = Vector2DHelper.addVector(this.position, velocityStep);
    }

    @Override
    public void update(double dt) {
        updateStateAndAcceleratorAndVelocity(dt);
        controlVelocityAndPosition(dt);
        updateAnimation(dt);
    }


    public void moveLeft() {
        this.characterDirection = CharacterDirection.LEFT;
    }

    public void moveRight() {
        this.characterDirection = CharacterDirection.RIGHT;
    }

    public void moveDown() {
        this.characterDirection = CharacterDirection.DOWN;
    }

    public void moveUp() {
        this.characterDirection = CharacterDirection.UP;
    }

    public void stopMove() {
        this.characterDirection = CharacterDirection.NONE;
    }
}
