package controllers;

import models.BackGroundModel;
import models.GameModel;
import utils.GameInfo;
import views.BackGroundView;
import views.GameView;

import java.awt.*;

/**
 * Created by Thaotonto on 3/2/2017.
 */
public class BackGroundController extends GameController {
    public BackGroundController(GameModel model, GameView view) {
        super(model, view);
    }

    public BackGroundController(int x, int y) {
        this(new BackGroundModel(x,y, GameInfo.gameWidth,GameInfo.gameHeight),
                new BackGroundView(GameInfo.BackGroundImage));
    }
}
