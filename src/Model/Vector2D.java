package Model;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 18/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public final static Vector2D zero = new Vector2D(0.0, 0.0);
}
