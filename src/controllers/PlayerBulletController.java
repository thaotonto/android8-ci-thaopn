package controllers;

import models.PlayerBulletModel;
import utils.Utils;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerBulletController {
    private PlayerBulletModel model;
    private PlayerBulletView view;
    private boolean active=true;

    public PlayerBulletController(PlayerBulletModel model, PlayerBulletView view) {
        this.model = model;
        this.view = view;
    }

    public PlayerBulletController(int x, int y){
        this(new PlayerBulletModel(x,y,7,20),
                new PlayerBulletView(Utils.loadImageFromres("bullet.png")));
    }

    public boolean isActive() {
        return active;
    }

    public void run(){
        model.fly();
        if (model.getY()<0) active = false;
    }

    public void draw(Graphics graphics){
        view.draw(graphics, model);
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
