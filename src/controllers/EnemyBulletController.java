package controllers;

import models.EnemyBulletModel;
import models.GameModel;
import utils.GameInfo;
import views.EnemyBulletView;
import views.GameView;

import java.awt.*;


/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyBulletController extends GameController {

    public EnemyBulletController(GameModel model, GameView view) {
        super(model, view);
    }


    public EnemyBulletController(int x, int y, EnemyPlaneController.EnemyType type) {
        this(new EnemyBulletModel(x, y, GameInfo.enemyBulletWidth, GameInfo.enemyBulletHeight,type),
                new EnemyBulletView(GameInfo.enemyBulletImage));
    }

    public void run() {
        EnemyBulletModel enemyBulletModel = (EnemyBulletModel) model;
        ((EnemyBulletView)view).setImage(((EnemyBulletModel) model).getType());
        switch (enemyBulletModel.getType()){
            case WHITE:
                enemyBulletModel.moveDown();
                break;
            case YELLOW:
                ((EnemyBulletView)view).updateImage();
                enemyBulletModel.moveToPlayer();
                break;
        }
        if (model.getY() > GameInfo.gameHeight) active = false;
    }

    public void onContact(GameController other) {
        if (other instanceof PlayerPlaneController) {
            active = false;
        }

        if (other instanceof PlayerBulletController){
           if (model instanceof EnemyBulletModel){
               switch (((EnemyBulletModel) model).getType()){
                   case WHITE:
                       break;
                   case GREEN:
                       active=false;
                       break;
               }
            }
        }
    }
}
