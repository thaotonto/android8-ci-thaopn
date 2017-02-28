package controllers;

import models.GameModel;
import models.PlayerBulletModel;
import utils.GameInfo;
import utils.Utils;
import views.GameView;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerBulletController extends GameController {

    private boolean active=true;

    public PlayerBulletController(GameModel model, GameView view) {
        super(model, view);
    }


    public PlayerBulletController(int x, int y){
        this(new PlayerBulletModel(x,y, GameInfo.playerBulletWidth,GameInfo.playerBulletHeight),
                new PlayerBulletView(Utils.loadImageFromres("bullet.png")));
    }

    public boolean isActive() {
        return active;
    }

    public void run(){
        ((PlayerBulletModel)model).fly();
        if (model.getY()<0) active = false;
    }

    public boolean checkSpawm(PlayerBulletController playerBulletController){
        if (this.model.getY()> playerBulletController.model.getY()+ playerBulletController.model.getHeight()+5)
            return true;
        else return false;
    }

    public int getX(){
        return model.getX();
    }

    public int getY(){
        return model.getY();
    }

    public void setActive() {
        this.active = false;
    }
}
