package Model;

import Geometric.Size;
import Geometric.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 23/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public class Sprite {
    protected Vector2D position = Vector2D.zero;
    protected Size size;
    protected boolean flipX;

    protected Image image;

    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    public Sprite(String imageName) {
        try {
            this.image = new Image(new FileInputStream(imageName));
            this.position = Vector2D.zero;
            this.size = new Size(image.getWidth(), image.getHeight());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Sprite() { }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void render(GraphicsContext gc) {
        if (flipX){
            gc.drawImage(this.image,
                    position.x + size.width - size.width / 2, position.y - size.height / 2,
                    -size.width, size.height);
        }
        else
            gc.drawImage(this.image,
                    position.x - size.width / 2, position.y  - size.height / 2,
                    size.width, size.height);

    }

    public void update(double dt) {

    }

    public void setTexture(Image newImage) {
        this.image = newImage;
        this.size = new Size(newImage.getWidth(), newImage.getHeight());
    }
}
