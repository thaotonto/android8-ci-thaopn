package controllers;

import models.GameModel;
import models.PlayerBulletModel;
import utils.GameInfo;
import views.GameView;
import views.PlayerBulletView;


/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerBulletController extends GameController {

    public PlayerBulletController(GameModel model, GameView view) {
        super(model, view);
    }


    public PlayerBulletController(int x, int y, int type) {
        this(new PlayerBulletModel(x, y, GameInfo.playerBulletWidth, GameInfo.playerBulletHeight, type),
                new PlayerBulletView(GameInfo.bulletImage));
    }

    public void run() {
        ((PlayerBulletModel) model).moveUp();
        if (model.getY() < 0) active = false;
        ((PlayerBulletView) view).setImage(((PlayerBulletModel)model).getType());
    }

    public int getX() {
        return model.getX();
    }

    public int getY() {
        return model.getY();
    }

    public void onContact(GameController other) {
        if (other instanceof EnemyPlaneController) {
            ((EnemyPlaneController) other).getHit(1);
            active = false;
        }

        if (other instanceof MineController) {
            active = false;
        }
    }

}
