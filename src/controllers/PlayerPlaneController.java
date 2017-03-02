package controllers;

import models.GameModel;
import models.PlayerBulletModel;
import models.PlayerPlaneModel;
import utils.GameInfo;
import views.GameView;
import views.PlayerPlaneView;


import java.util.ArrayList;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerPlaneController extends GameController {

    private boolean active = true;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isShoot = false;
    private int pow;
    private int time;

    ArrayList<PlayerBulletController> playerBulletControllers;


    public PlayerPlaneController(GameModel model, GameView view, ArrayList<PlayerBulletController> playerBulletControllers) {
        super(model, view);
        this.playerBulletControllers=playerBulletControllers;
    }

    public PlayerPlaneController(int x,int y,ArrayList<PlayerBulletController> playerBulletControllers) {
        this( new PlayerPlaneModel(x,y, GameInfo.playerPlaneWidth,GameInfo.playerPlaneHeight),
                new PlayerPlaneView(GameInfo.playerPlane1Image),
                playerBulletControllers);
    }

    public void shoot(){
        PlayerBulletController playerBulletController = new PlayerBulletController(model.getX()+model.getWidth()/2-GameInfo.playerBulletWidth/2,
                model.getY());
        if (time==0) pow=0;
        playerBulletController.setPow(pow);
        if (playerBulletControllers.size()!=0){
            PlayerBulletController prePlayerBulletController = playerBulletControllers.get(playerBulletControllers.size()-1);
            if (playerBulletController.checkSpawm(prePlayerBulletController)==true)
                playerBulletControllers.add(playerBulletController);
        } else  playerBulletControllers.add(playerBulletController);
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void run() {
        if (active==true) {
            if (isLeft == true)
                ((PlayerPlaneModel) model).moveLeft();
            if (isRight == true)
                ((PlayerPlaneModel) model).moveRight();
            if (isUp == true)
                ((PlayerPlaneModel) model).moveUp();
            if (isDown == true)
                ((PlayerPlaneModel) model).moveDown();
            if (isShoot == true)
                shoot();
            if (time!=0) time--;
        }
    }

    public int getX(){
        return model.getX();
    }

    public int getY(){
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

    public void contact(EnemyPlaneController enemyPlaneController) {
        if (model.checkContact(enemyPlaneController.getModel())==true) {
            enemyPlaneController.setExplode();
            active=false;
        }
    }

    public void contact(MineController mineController) {
        if (model.checkContact(mineController.getModel())==true) {
            mineController.setExplode();
            active=false;
        }
    }

    public void contact(EnemyBulletController enemyBulletController) {
        if (model.checkContact(enemyBulletController.getModel())==true) {
            enemyBulletController.setActive(false);
            active=false;
        }
    }

    public void contact(PowerUpController powerUpController) {
        if (model.checkContact(powerUpController.getModel())==true) {
            powerUpController.setActive(false);
            pow=1;
            time=500;
        }
    }
}
