package controllers.strategies;

import models.GameModel;

/**
 * Created by Thaotonto on 3/8/2017.
 */
public class MoveDownRightBehavior extends MoveBehavior {
    public void move (GameModel gameModel){
        gameModel.moveRightDown();
    }
}
