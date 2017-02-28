package models;

import controllers.EnemyBulletController;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyPlaneModel extends GameModel{

    private static final int SPEED = 2;

    public EnemyPlaneModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public static int getSPEED() {
        return SPEED;
    }

    public void fly(){
        y+=SPEED;
    }

}
