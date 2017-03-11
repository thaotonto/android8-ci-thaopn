package controllers.strategies;

import models.GameModel;

/**
 * Created by Thaotonto on 3/8/2017.
 */
public class MoveDownBehavior extends MoveBehavior {
    public void move (GameModel gameModel){
        gameModel.moveDown();
    }
}
