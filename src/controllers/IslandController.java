package controllers;

import models.GameModel;
import models.IslandModel;
import utils.GameInfo;
import views.GameView;
import views.IslandView;

import java.awt.*;

/**
 * Created by Thaotonto on 2/27/2017.
 */
public class IslandController extends GameController{
    private boolean active=true;

    public IslandController(GameModel model, GameView view) {
        super(model, view);
    }


    public IslandController(int x, int y,Image image) {
        this(new IslandModel(x,y, GameInfo.isLandWidth,GameInfo.isLandHeight),
                new IslandView(image));
    }

    public void run(){
        if (model instanceof IslandModel){
            ((IslandModel) model).move();
            if (model.getY()> GameInfo.gameHeight) active=false;
        }
    }

    public boolean isActive() {
        return active;
    }
}
