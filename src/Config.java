import java.awt.*;

public class Config {
    public static final int SIZE = 10;
    public static final int WIDTH = 75;
    public static final int HEIGHT = 50;
    public static final int SLEEPMS = 1000;

    public static Color getColor(Status status) {
        switch (status) {

            default -> { return null; }

            case NONE -> {
                return Color.BLACK;
            }
            case BORN -> {
                return Color.GREEN;
            }
            case LIFE -> {
                return Color.WHITE;
            }
            case DIED -> {
                return Color.RED;
            }
        }
    }
}
