package controllers;

import models.EnemyPlaneModel;
import models.GameModel;
import models.PlayerBulletModel;
import utils.GameInfo;
import views.GameView;
import views.PlayerBulletView;


/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerBulletController extends GameController {
    private int pow;
    private int time;
    private boolean active=true;

    public PlayerBulletController(GameModel model, GameView view) {
        super(model, view);
    }


    public PlayerBulletController(int x, int y){
        this(new PlayerBulletModel(x,y, GameInfo.playerBulletWidth,GameInfo.playerBulletHeight),
                new PlayerBulletView(GameInfo.bulletImage));
    }

    public boolean isActive() {
        return active;
    }

    public void run(){
        ((PlayerBulletModel)model).fly();
        if (model.getY()<0) active = false;
        ((PlayerBulletView)view).setImageType(pow);
        if (pow!=0){
            time--;
            ((PlayerBulletModel)model).setWidth(GameInfo.doubleBulletWidth);
            ((PlayerBulletModel)model).setHeight(GameInfo.doubleBulletHeight);
            if (time==0) pow--;
        }
    }

    public boolean checkSpawm(PlayerBulletController playerBulletController){
        if (this.model.getY()> playerBulletController.model.getY()+ playerBulletController.model.getHeight()+5)
            return true;
        else return false;
    }

    public int getX(){
        return model.getX();
    }

    public int getY(){
        return model.getY();
    }

    public void setActive() {
        this.active = false;
    }

    public void shoot(EnemyPlaneController enemyPlaneController) {
        if (model.checkContact(enemyPlaneController.getModel())==true) {
            enemyPlaneController.setHealth();
            if (enemyPlaneController.getHealth()==0)
                enemyPlaneController.setExplode();
            active=false;
        }
    }

    public void shoot(MineController mineController) {
        if (model.checkContact(mineController.getModel())==true) {
            mineController.setExplode();
            active=false;
        }
    }


    public void setPow(int pow) {
        this.pow += pow;
        setTime(500);
    }

    public void setTime(int time) {
        this.time += time;
    }
}
