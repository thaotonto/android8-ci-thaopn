package controllers;

import models.EnemyPlaneModel;
import utils.Utils;
import views.EnemyPlaneView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyPlaneController {
    private EnemyPlaneModel model;
    private EnemyPlaneView view;
    private boolean active=true;
    private int delayBullet=0;
    private boolean explode=false;
    private int delayExplosion=0;

    public EnemyPlaneController(EnemyPlaneModel model, EnemyPlaneView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyPlaneController(int x,int y) {
        this(new EnemyPlaneModel(x,y,22,22),new EnemyPlaneView(Utils.loadImageFromres("enemy_plane_white_1.png")));
    }

    public boolean isActive() {
        return active;
    }

    public void run(int gameHeight){
        model.fly();
        if (model.getY()>gameHeight) active=false;
        if (explode==true) delayExplosion++;
        switch (delayExplosion) {
            case 1:
                view.setImage(Utils.loadImageFromres("explosion6.png"));
                break;
            case 2:
                view.setImage(Utils.loadImageFromres("explosion5.png"));
                break;
            case 3:
                view.setImage(Utils.loadImageFromres("explosion4.png"));
                break;
            case 4:
                view.setImage(Utils.loadImageFromres("explosion3.png"));
                break;
            case 5:
                view.setImage(Utils.loadImageFromres("explosion2.png"));
                break;
            case 6:
                view.setImage(Utils.loadImageFromres("explosion1.png"));
                break;
            case 7:
                setActive();
                break;
        }
    }

    public void draw(Graphics graphics){
        view.draw(graphics, model);
    }

    public int getDelayBullet() {
        return delayBullet;
    }

    public void setDelayBullet() {
        this.delayBullet += 1;
    }

    public void setDelayBullet(int delayBullet) {
        this.delayBullet = delayBullet;
    }

    public int getX(){
        return model.getX();
    }

    public int getY(){
        return model.getY();
    }

    public int getWidth(){
        return model.getWidth();
    }

    public void isExplode(PlayerBulletController playerBulletController) {
        if ((playerBulletController.getY() >= model.getY() && playerBulletController.getY() <= model.getY() + model.getHeight())
                && (playerBulletController.getX() >= model.getX() && playerBulletController.getX() <= model.getX() + model.getWidth())) {
            explode = true;
            playerBulletController.setActive();
        }
    }

    public boolean getExplode(){
        return explode;
    }

    public void setActive() {
        this.active = false;
    }
}
