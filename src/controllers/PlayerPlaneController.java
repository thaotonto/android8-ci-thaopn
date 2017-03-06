package controllers;

import models.GameModel;
import models.PlayerPlaneModel;
import utils.GameInfo;
import views.GameView;
import views.PlayerPlaneView;


import java.util.ArrayList;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerPlaneController extends GameController {

    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isShoot = false;
    private int time;
    private int delay = 0;
    private int type = 1;
    private int bulletType = 0;
    private static int playerX;
    private static int playerY;


    public PlayerPlaneController(GameModel model, GameView view) {
        super(model, view);
    }

    public PlayerPlaneController(int x, int y) {
        this(new PlayerPlaneModel(x, y, GameInfo.playerPlaneWidth, GameInfo.playerPlaneHeight),
                new PlayerPlaneView(GameInfo.playerPlane1Image));
    }

    public void shoot() {
        PlayerBulletController playerBulletController = new PlayerBulletController(model.getX() + model.getWidth() / 2 - GameInfo.playerBulletWidth / 2,
                model.getY(), bulletType);
        ControllerManager.addGameController(playerBulletController);
    }

    @Override
    public void run() {
        if (active == true) {
            if (isLeft == true)
                ((PlayerPlaneModel) model).moveLeft();
            if (isRight == true)
                ((PlayerPlaneModel) model).moveRight();
            if (isUp == true)
                ((PlayerPlaneModel) model).moveUp();
            if (isDown == true)
                ((PlayerPlaneModel) model).moveDown();
            if (isShoot == true && delay > 8) {
                delay = 0;
                shoot();
            }
            delay++;
            if (time != 0) time--;
            else {
                ((PlayerPlaneView) view).setImage(1);
                bulletType=0;
                type=1;
            }
            playerX=model.getX() + model.getWidth() / 2;
            playerY=model.getY();
        }
    }

    public  int getX() {
        return model.getX();
    }

    public int getY() {
        return model.getY();
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public void setShoot(boolean shoot) {
        isShoot = shoot;
    }

    public static int getPlayerX() {
        return  playerX;
    }

    public static  int getPlayerY() {
        return playerY;
    }


    public void onContact(GameController other) {
        if (other instanceof PowerUpController) {
            switch (type) {
                case 1:
                    ((PlayerPlaneView) view).setImage(4);
                    type=4;
                    time = 600;
                    break;
                case 4:
                    bulletType = 1;
                    time = 300;
                    break;
            }
        }

        if (other instanceof EnemyPlaneController){
            ((EnemyPlaneController) other).getHit(100);
            switch (type){
                case 1:
                    active=false;
                    break;
                case 4:
                    break;
            }

        }

        if (other instanceof EnemyBulletController){
            switch (type){
                case 1:
                    active=false;
                    break;
                case 4:
                    break;
            }
        }

        if (other instanceof MineController){
            switch (type){
                case 1:
                    active=false;
                    break;
                case 4:
                    break;
            }
        }
    }
}
