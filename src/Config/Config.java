package Config;

import Geometric.Vector2D;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 17/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public class Config {
    public final static double NANOSECONDPERSEC = 1000000000.0;

    public static class WindowProperties {
        public final static int WINDOW_WIDTH = 1200;
        public final static int WINDOW_HEIGHT = 640;
    }

    public static class PlayerProperties {
        public final static double WALKING_ACCELERATE = 1500;
        public final static double MAX_MOVE_SPEED = 500;
        public final static int WIDTH = 32;
        public final static int HEIGHT = 32;
    }
}
