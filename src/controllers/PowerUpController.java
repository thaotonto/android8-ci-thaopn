package controllers;

import models.GameModel;
import models.PowerUpModel;
import utils.GameInfo;
import views.GameView;
import views.PowerUpView;

import java.awt.*;

/**
 * Created by Thaotonto on 3/2/2017.
 */
public class PowerUpController extends GameController {
    private boolean active=true;
    public PowerUpController(GameModel model, GameView view) {
        super(model, view);
    }

    public PowerUpController(int x, int y) {
        this(new PowerUpModel(x,y, GameInfo.powerUpWidth,GameInfo.powerUpHeight),
                new PowerUpView(GameInfo.powerUpImage));
    }

    public void run(){
        if (model instanceof PowerUpModel){
            ((PowerUpModel) model).move();
            if (model.getY()> GameInfo.gameHeight) active=false;
        }
    }

    public boolean isActive() {
        return active;
    }

    public GameModel getModel(){
        return model;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
