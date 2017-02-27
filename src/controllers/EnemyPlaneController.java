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
    private ArrayList<EnemyBulletController> enemyBulletControllers= new ArrayList<>();
    private boolean explode=false;

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

    public void genBullet(){
        EnemyBulletController enemyBulletController= new EnemyBulletController(model.getX()+model.getWidth()/2-22/2,model.getY()+5);
        enemyBulletControllers.add(enemyBulletController);
        delayBullet=0;
    }

    public ArrayList<EnemyBulletController> getEnemyBulletControllers() {
        return enemyBulletControllers;
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

    public void setDelayExplosion(){
        model.setDelayExplosion();
    }

    public int getDelayExplosion(){
        return model.getDelayExplosion();
    }

    public void setActive() {
        this.active = false;
    }
}
