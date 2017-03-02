package controllers;

import models.EnemyBulletModel;
import models.GameModel;
import utils.GameInfo;
import views.EnemyBulletView;
import views.GameView;


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
                new EnemyBulletView(GameInfo.enemyBulletImage));
    }

    public boolean isActive() {
        return active;
    }

    public void run(){
        EnemyBulletModel enemyBulletModel=(EnemyBulletModel)model;
        enemyBulletModel.fly();
        if (model.getY()>GameInfo.gameHeight) active = false;
    }

    public GameModel getModel(){
        return model;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
