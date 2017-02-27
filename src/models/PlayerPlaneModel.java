package models;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerPlaneModel {
    private int x;
    private int y;
    private int width;
    private int height;
    private static final int SPEED =10;

    public PlayerPlaneModel(int x, int y, int width, int height) {
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
    public void moveLeft(){
        if (x-SPEED >= -5)
            x-=SPEED;
    }
    public void moveRight(int gameWidth){
        if (x+width+SPEED <= gameWidth+5)
            x+=SPEED;
    }
    public void moveUp(){
        if (y-SPEED>=5)
            y-=SPEED;
    }
    public void moveDown(int gameHeight){
        if (y+SPEED+height<=gameHeight)
            y+=SPEED;
    }
}
