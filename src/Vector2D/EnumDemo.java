package Vector2D;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 17/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public class EnumDemo {
    public enum Level{
        HIGH,MEDIUM,LOW
    }
    public static void main(String[] args) {
        Level level = Level.HIGH;
        System.out.println(level);
    }
}
