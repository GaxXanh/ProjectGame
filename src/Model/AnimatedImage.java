package Model;

import javafx.scene.image.Image;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 25/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public class AnimatedImage {
    public Image[] frames;
    public double duration;
    public boolean repeat = false;

    public AnimatedImage(Image[] images, double duration, boolean repeat) {
        this.frames = images;
        this.duration = duration;
        this.repeat = repeat;
    }

    public Image getFrame(double elapsedTime) {
        if (frames.length == 1) return frames[0];
        if (elapsedTime > frames.length * duration && !repeat) return frames[frames.length - 1];

        double elapsed = elapsedTime % (frames.length * duration);
        int index = (int) (elapsed / duration);
        return frames[index];
    }
}
