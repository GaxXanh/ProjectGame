package Config;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 17/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public class Config {
    public final static double NANOSECONDPERSEC = 1000000000.0;

    public static class WindowProperties {
        public final static int WINDOW_WIDTH = 900;
        public final static int WINDOW_HEIGHT = 576;
    }

    public static class PlayerProperties {
        public final static double JumpForce = 400;
        public final static double JumpCutOff = 200;
        public final static double MaxMoveSpeed = 500;
        public final static double WalkingAccelerate = 1500;

        public final static int Width = 32;
        public final static int Height = 32;
    }
}
