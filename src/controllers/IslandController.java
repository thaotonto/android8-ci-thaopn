package controllers;

import models.IslandModel;
import utils.GameInfo;
import utils.Utils;
import views.IslandView;

import java.awt.*;

/**
 * Created by Thaotonto on 2/27/2017.
 */
public class IslandController {
    private IslandModel model;
    private IslandView view;
    private boolean active=true;
    public IslandController(IslandModel model, IslandView view) {
        this.model = model;
        this.view = view;
    }

    public IslandController(int x, int y) {
        int randomImage= (int) (Math.random()*2);
        switch (randomImage) {
            case 0:
                model= new IslandModel(x,y, GameInfo.isLandWidth,GameInfo.isLandHeight);
                view= new IslandView(Utils.loadImageFromres("island.png"));
                break;
            case 1:
                model= new IslandModel(x,y,GameInfo.isLandWidth,GameInfo.isLandHeight);
                view= new IslandView(Utils.loadImageFromres("island-2.png"));
                break;
        }
    }

    public void run(int gameHeight){
        model.move();
        if (model.getY()>gameHeight) active=false;
    }

    public void draw(Graphics graphics){
        view.draw(graphics, model);
    }

    public boolean isActive() {
        return active;
    }
}
