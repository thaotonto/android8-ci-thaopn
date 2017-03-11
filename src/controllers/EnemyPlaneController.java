package controllers;

import controllers.strategies.MoveBehavior;
import controllers.strategies.MoveDownBehavior;
import controllers.strategies.MoveDownRightBehavior;
import models.EnemyPlaneModel;
import models.GameModel;
import utils.GameInfo;
import views.EnemyPlaneView;
import views.GameView;

import java.util.Random;


/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyPlaneController extends GameController {

    private static Random random= new Random();
    private int delayBullet = 0;
    private boolean explode = false;
    private int health;
    private EnemyType type;
    private int delay;
    private MoveBehavior moveBehavior;

    public enum EnemyType {
        GREEN,
        YELLOW,
        WHITE
    }


    public EnemyPlaneController(GameModel model, GameView view, EnemyType type) {
        super(model, view);
        this.type = type;
        switch (type) {
            case WHITE:
                health = 5;
                break;
            case YELLOW:
                health = 10;
                break;
        }
        ((EnemyPlaneView) view).setImageType(type);
    }


    public void run() {
        if (model instanceof EnemyPlaneModel) {
            if (view instanceof EnemyPlaneView) {
//                switch (type) {
//                    case 1:
//                        ((EnemyPlaneModel) model).moveDown();
//                        shoot();
//                        break;
//                    case 2:
//                        if (model.getX() > GameInfo.gameWidth / 2) delay++;
//                        if (delay == 0 || delay > 100) {
//                            ((EnemyPlaneModel) model).moveRight();
//                            ((EnemyPlaneModel) model).moveDown();
//
//                        }
//                        break;
//                }

                if (model.getY() > GameInfo.gameHeight) active = false;
                if (explode == true) {
                    ((EnemyPlaneView) view).explode();
                    if (((EnemyPlaneView) view).isExploded() == true) active = false;
                } else {
                    ((EnemyPlaneView) view).updateImage();
                    delayBullet++;

                }
                if (moveBehavior != null) {
                    moveBehavior.move(this.model);
                }
                shoot();
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


    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    public static EnemyPlaneController create(EnemyType type) {
        if (type == EnemyType.YELLOW) {
            EnemyPlaneController enemyPlaneController = new EnemyPlaneController(
                    new EnemyPlaneModel(0, 0, 25, 25),
                    new EnemyPlaneView(GameInfo.enemyPlaneyellow1Image),
                    EnemyType.YELLOW
            );
            enemyPlaneController.setMoveBehavior(new MoveDownRightBehavior());
            return enemyPlaneController;
        } else if (type == EnemyType.WHITE) {
            EnemyPlaneController enemyPlaneController = new EnemyPlaneController(
                    new EnemyPlaneModel(random.nextInt(GameInfo.gameWidth) - GameInfo.enemyPlaneWidth, 0, GameInfo.enemyPlaneWidth, GameInfo.enemyPlaneHeight),
                    new EnemyPlaneView(GameInfo.enemyPlanewhite1Image),
                    EnemyType.WHITE
            );
            enemyPlaneController.setMoveBehavior(new MoveDownBehavior());
            return enemyPlaneController;
        }
        return null;
    }
}
