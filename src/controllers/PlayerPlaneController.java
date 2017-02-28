package controllers;

import models.GameModel;
import models.PlayerPlaneModel;
import utils.Utils;
import views.GameView;
import views.PlayerPlaneView;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerPlaneController extends GameController {

    private boolean active = true;

    public PlayerPlaneController(GameModel model, GameView view) {
        super(model, view);
    }

    public PlayerPlaneController(int x,int y) {
        this( new PlayerPlaneModel(x,y,40,25), new PlayerPlaneView(Utils.loadImageFromres("plane2.png")));
    }

    public boolean isActive() {
        return active;
    }
    public void moveLeft(){
        ((PlayerPlaneModel)model).moveLeft();
    }
    public void moveRight(int gameWidth){
        ((PlayerPlaneModel)model).moveRight(gameWidth);
    }
    public void moveUp(){
        ((PlayerPlaneModel)model).moveUp();
    }
    public void moveDown(int gameHeight){
        ((PlayerPlaneModel)model).moveDown(gameHeight);
    }
    public int getX(){
        return model.getX();
    }
    public int getY(){
        return model.getY();
    }
    public int getWidth(){
        return model.getWidth();
    }
    public int getHeight(){
        return model.getHeight();
    }
}
