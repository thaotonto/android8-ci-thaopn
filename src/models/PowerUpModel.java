package models;

import utils.GameInfo;

/**
 * Created by Thaotonto on 3/2/2017.
 */
public class PowerUpModel extends GameModel {
    {
        SPEED = GameInfo.speedPowerUp;
    }

    public PowerUpModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

}
