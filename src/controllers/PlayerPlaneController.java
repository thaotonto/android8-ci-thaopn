package controllers;

import models.PlayerPlaneModel;
import utils.Utils;
import views.PlayerPlaneView;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerPlaneController {
    private PlayerPlaneModel model;
    private PlayerPlaneView view;
    private boolean active = true;

    public PlayerPlaneController( PlayerPlaneModel model,PlayerPlaneView view) {
        this.view = view;
        this.model = model;
    }

    public PlayerPlaneController(int x,int y) {
        this( new PlayerPlaneModel(x,y,40,25), new PlayerPlaneView(Utils.loadImageFromres("plane2.png")));
    }

    public boolean isActive() {
        return active;
    }
    public void draw(Graphics graphics){
        view.draw(graphics,model);
    }
    public void moveLeft(){
        model.moveLeft();
    }
    public void moveRight(int gameWidth){
        model.moveRight(gameWidth);
    }
    public void moveUp(){
        model.moveUp();
    }
    public void moveDown(int gameHeight){
        model.moveDown(gameHeight);
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
