package models;

import utils.GameInfo;

/**
 * Created by Thaotonto on 2/27/2017.
 */
public class EnemyBulletModel extends GameModel{
    private static final int SPEED = GameInfo.speedEnemyBullet;

    public EnemyBulletModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void fly(){
        y+=SPEED;
    }
}
