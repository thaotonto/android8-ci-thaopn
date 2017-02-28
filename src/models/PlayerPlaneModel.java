package models;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerPlaneModel extends GameModel{
    private static final int SPEED =5;

    public PlayerPlaneModel(int x, int y, int width, int height) {
        super(x, y, width, height);
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
