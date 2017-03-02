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
public class EnemyPlaneController extends GameController{

    private boolean active=true;
    private int delayBullet=0;
    private boolean explode=false;
    ArrayList<EnemyBulletController> enemyBulletControllers;

    public EnemyPlaneController(GameModel model, GameView view,ArrayList<EnemyBulletController> enemyBulletControllers,int type) {
        super(model, view);
        this.enemyBulletControllers=enemyBulletControllers;
        ((EnemyPlaneView)view).setImageType((EnemyPlaneModel)model);
    }

    public EnemyPlaneController(int x,int y,ArrayList<EnemyBulletController> enemyBulletControllers,int type) {
        this(new EnemyPlaneModel(x,y, GameInfo.enemyPlaneWidth,GameInfo.enemyPlaneHeight,type),
                new EnemyPlaneView(GameInfo.enemyPlanewhite3Image),enemyBulletControllers,type);
    }

    public boolean isActive() {
        return active;
    }

    public void run(){
        if (model instanceof EnemyPlaneModel){
            if(view instanceof EnemyPlaneView){
                ((EnemyPlaneModel) model).fly();
                if (model.getY()>GameInfo.gameHeight) active=false;
                if (explode==true) {
                    ((EnemyPlaneView) view).explode();
                    if (((EnemyPlaneView)view).isExploded()==true) setActive();
                }
                else
                ((EnemyPlaneView)view).updateImage();
            }
        }

    }

    public void shoot(){
        if (delayBullet==GameInfo.delayBullettime && explode==false) {
            EnemyBulletController enemyBulletController = new EnemyBulletController(model.getX() + model.getWidth() / 2 - GameInfo.enemyBulletWidth / 2,
                    model.getY() + 5);
            enemyBulletControllers.add(enemyBulletController);
            setDelayBullet(0);
        }
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

//    public void isExplode(PlayerBulletController playerBulletController) {
//        if (model.checkContact(playerBulletController.getModel())==true) {
//            ((EnemyPlaneModel)model).setHealth();
//            if (((EnemyPlaneModel) model).getHealth()==0)
//                explode = true;
//            playerBulletController.setActive();
//        }
//    }

    public GameModel getModel(){
        return model;
    }

    public void setExplode(){
        explode=true;
    }

    public boolean getExplode(){
        return explode;
    }

    public void setActive() {
        this.active = false;
    }

    public int getHealth(){
        return ((EnemyPlaneModel)model).getHealth();
    }

    public void setHealth(){
        ((EnemyPlaneModel)model).setHealth();
    }

}
