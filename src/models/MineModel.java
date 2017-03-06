package models;

import utils.GameInfo;

/**
 * Created by Thaotonto on 3/2/2017.
 */
public class MineModel extends GameModel {
    {
        SPEED = GameInfo.speedMine;
    }

    public MineModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}
