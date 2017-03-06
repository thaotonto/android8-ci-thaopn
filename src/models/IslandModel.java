package models;

import utils.GameInfo;

/**
 * Created by Thaotonto on 2/27/2017.
 */
public class IslandModel extends GameModel {

    {
        SPEED = GameInfo.speedIsland;
    }

    public IslandModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

}
