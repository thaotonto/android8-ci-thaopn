package controllers;

import models.EnemyPlaneModel;
import models.GameModel;
import utils.GameInfo;
import views.EnemyPlaneView;
import views.GameView;

import java.util.ArrayList;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyPlaneController extends GameController {

    private int delayBullet = 0;
    private boolean explode = false;
    private int health;
    private int type;
    private int delay;


    public EnemyPlaneController(GameModel model, GameView view, int type) {
        super(model, view);
        this.type = type;
        switch (type) {
            case 1:
                health = 1;
                break;
            case 2:
                health = 3;
                break;
        }
        ((EnemyPlaneView) view).setImageType(type);
    }

    public EnemyPlaneController(int x, int y, int type) {
        this(new EnemyPlaneModel(x, y, GameInfo.enemyPlaneWidth, GameInfo.enemyPlaneHeight),
                new EnemyPlaneView(GameInfo.enemyPlanewhite3Image), type);
    }

    public void run() {
        if (model instanceof EnemyPlaneModel) {
            if (view instanceof EnemyPlaneView) {
                switch (type) {
                    case 1:
                        ((EnemyPlaneModel) model).moveDown();
                        shoot();
                        break;
                    case 2:
                        if (model.getX()> GameInfo.gameWidth/2) delay++;
                        if (delay==0 || delay > 100) {
                            ((EnemyPlaneModel) model).moveRight();
                            ((EnemyPlaneModel) model).moveDown();
                            shoot();
                        }
                        break;
                }

                if (model.getY() > GameInfo.gameHeight) active = false;
                if (explode == true) {
                    ((EnemyPlaneView) view).explode();
                    if (((EnemyPlaneView) view).isExploded() == true) active = false;
                } else {
                    ((EnemyPlaneView) view).updateImage();
                    delayBullet++;

                }
            }
        }

    }

    public void shoot() {
        if (delayBullet == GameInfo.delayBullettime && explode == false) {
            EnemyBulletController enemyBulletController = new EnemyBulletController(model.getX() + model.getWidth() / 2 - GameInfo.enemyBulletWidth / 2,
                    model.getY() + 5, type);
            ControllerManager.addGameController(enemyBulletController);
            delayBullet = 0;
        }
    }

    public void onContact(GameController other) {
    }

    public void getHit(int damage) {
        health -= damage;
        if (health <= 0) explode = true;
    }
}
