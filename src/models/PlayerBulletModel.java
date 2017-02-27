package models;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerBulletModel {
    private int x;
    private int y;
    private int width;
    private int height;
    private static final int SPEED = 5;

    public PlayerBulletModel(int x, int y, int width, int height) {
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

    public void fly(){
        y-=SPEED;
    }
}
