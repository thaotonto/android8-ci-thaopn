package models;

import utils.GameInfo;



/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyPlaneModel extends GameModel {
    {
        SPEED = GameInfo.speedEnemyPlane;
    }


    public EnemyPlaneModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

}
