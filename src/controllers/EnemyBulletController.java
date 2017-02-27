package controllers;

import models.EnemyBulletModel;
import utils.Utils;
import views.EnemyBulletView;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyBulletController {
    private EnemyBulletModel model;
    private EnemyBulletView view;
    private boolean active = true;
    public EnemyBulletController(EnemyBulletModel model, EnemyBulletView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyBulletController(int x,int y) {
        this(new EnemyBulletModel(x,y,22,22),
                new EnemyBulletView(Utils.loadImageFromres("enemy_bullet.png")));
    }

    public boolean isActive() {
        return active;
    }

    public void run(int gameHeight){
        model.fly();
        if (model.getY()>gameHeight) active = false;
    }

    public void draw(Graphics graphics){
        view.draw(graphics, model);
    }

}
