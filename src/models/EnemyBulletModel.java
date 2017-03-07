package models;

import controllers.PlayerPlaneController;
import utils.GameInfo;

/**
 * Created by Thaotonto on 2/27/2017.
 */
public class EnemyBulletModel extends GameModel {
    private int type;
    private int flag;
    private int playerx;
    private int playery;


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
        if (flag==0){
            playerx=PlayerPlaneController.getPlayerX();
            playery=PlayerPlaneController.getPlayerY();
        }
        flag++;
        if (y < playery) {
            double dist = Math.sqrt(Math.pow(playery - y, 2.0) + Math.pow(x - playerx, 2.0));
            double tempX = SPEED * ((x - playerx)) / dist;
            double tempY = SPEED * ((playery - y)) / dist;
            x -= tempX;
            y += tempY;
        }
        else moveDown();
    }

    public int getType() {
        return type;
    }
}
