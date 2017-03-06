package models;

import utils.GameInfo;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel {
    private int type;

    {
        SPEED = GameInfo.speedPlayerBullet;
    }

    public PlayerBulletModel(int x, int y, int width, int height, int type) {
        super(x, y, width, height);
        this.type = type;
        switch (type){
            case 1:
                this.x= x + GameInfo.playerBulletWidth /2 - GameInfo.doubleBulletWidth/2;
                this.width = GameInfo.doubleBulletWidth;
                this.height = GameInfo.doubleBulletHeight;
                break;
            case 2:
                break;
        }
    }

    public int getType() {
        return type;
    }
}
