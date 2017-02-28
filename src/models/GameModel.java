package models;

/**
 * Created by Thaotonto on 2/28/2017.
 */
public class GameModel {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int SPEED;

    public GameModel(int x, int y, int width, int height) {
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
}
