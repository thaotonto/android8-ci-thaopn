package controllers;

import models.EnemyBulletModel;
import models.GameModel;
import utils.GameInfo;
import utils.Utils;
import views.EnemyBulletView;
import views.GameView;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyBulletController extends GameController{

    private boolean active = true;

    public EnemyBulletController(GameModel model, GameView view) {
        super(model, view);
    }


    public EnemyBulletController(int x,int y) {
        this(new EnemyBulletModel(x,y, GameInfo.enemyBulletWidth,GameInfo.enemyBulletHeight),
                new EnemyBulletView(Utils.loadImageFromres("enemy_bullet.png")));
    }

    public boolean isActive() {
        return active;
    }

    public void run(int gameHeight){
        EnemyBulletModel enemyBulletModel=(EnemyBulletModel)model;
        enemyBulletModel.fly();
        if (model.getY()>gameHeight) active = false;
    }

    public void draw(Graphics graphics){
        view.draw(graphics, model);
    }

}
