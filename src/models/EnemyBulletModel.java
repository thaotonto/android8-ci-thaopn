package models;

import controllers.PlayerPlaneController;
import utils.GameInfo;

/**
 * Created by Thaotonto on 2/27/2017.
 */
public class EnemyBulletModel extends GameModel {
    private int type;

    {
        SPEED = GameInfo.speedEnemyBullet;
    }

    public EnemyBulletModel(int x, int y, int width, int height, int type) {
        super(x, y, width, height);
        this.type = type;
        switch (type){
            case 2:
                this.x= x + GameInfo.enemyBulletWidth /2 - GameInfo.rocketWidth/2;
                this.width = GameInfo.rocketWidth;
                this.height = GameInfo.rocketHeight;
                break;
            case 1:
                break;
        }
    }

    public void moveRightDown() {
        x += (SPEED - 3);
        y += SPEED;
    }

    public void moveLeftDown() {
        x -= (SPEED - 3);
        y += SPEED;
    }

    public void moveToPlayer() {
        double dist = Math.sqrt(Math.pow(PlayerPlaneController.getPlayerY() - y, 2.0) + Math.pow(x - PlayerPlaneController.getPlayerX(), 2.0));
        double tempX = SPEED/2 * (x - PlayerPlaneController.getPlayerX()) / dist;

        double tempY = SPEED/2 * (PlayerPlaneController.getPlayerY() - y) / dist;
        x -= tempX;
        y += tempY;
    }

    public int getType() {
        return type;
    }
}
