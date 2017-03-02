package models;

import utils.GameInfo;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerPlaneModel extends GameModel{
    private static final int SPEED = GameInfo.speedPlayerPlane;

    public PlayerPlaneModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void moveLeft(){
        if (x-SPEED >= -5)
            x-=SPEED;
    }
    public void moveRight(){
        if (x+width+SPEED <= GameInfo.gameWidth+5)
            x+=SPEED;
    }
    public void moveUp(){
        if (y-SPEED>=5)
            y-=SPEED;
    }
    public void moveDown(){
        if (y+SPEED+height<=GameInfo.gameHeight)
            y+=SPEED;
    }
}
