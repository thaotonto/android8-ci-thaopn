package controllers;

import models.GameModel;
import models.MineModel;
import utils.GameInfo;
import views.GameView;
import views.MineView;


/**
 * Created by Thaotonto on 3/2/2017.
 */
public class MineController extends GameController {
    private boolean active=true;
    private boolean explode=false;

    public MineController(GameModel model, GameView view) {
        super(model, view);
    }

    public MineController(int x, int y) {
        this(new MineModel(x,y, GameInfo.mineWidth,GameInfo.mineHeight),
                new MineView(GameInfo.mineImage));
    }

    public void run(){
        if (model instanceof MineModel){
            ((MineModel) model).move();
            if (model.getY()> GameInfo.gameHeight) active=false;
            if (explode==true) {
                ((MineView) view).explode();
                if (((MineView)view).isExploded()==true) setActive(false);
            }
        }
    }

    public GameModel getModel(){
        return model;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isExplode() {
        return explode;
    }

    public void setExplode() {
        this.explode = true;
    }
}
