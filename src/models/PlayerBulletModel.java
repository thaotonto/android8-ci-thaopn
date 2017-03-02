package models;

import utils.GameInfo;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel{

    private static final int SPEED = GameInfo.speedPlayerBullet;

    public PlayerBulletModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void fly(){
        y-=SPEED;
    }

    public void setWidth(int width){
        super.width=width;
    }

    public void setHeight(int height){
        super.height=height;
    }
}
