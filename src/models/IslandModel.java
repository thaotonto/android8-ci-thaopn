package models;

/**
 * Created by Thaotonto on 2/27/2017.
 */
public class IslandModel {
    private int x;
    private int y;
    private int width;
    private int height;
    private static final int SPEED = 1;

    public IslandModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public void move(){
        y+=SPEED;
    }
}
