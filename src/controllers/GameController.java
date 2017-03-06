package controllers;

import models.GameModel;
import views.GameView;

import java.awt.*;

/**
 * Created by Thaotonto on 2/28/2017.
 */
public class GameController {
    protected GameModel model;
    protected GameView view;
    protected boolean active = true;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
    }

    public void draw(Graphics graphics) {
        view.draw(graphics, model);
    }

    public boolean isActive() {
        return active;
    }

    public void onContact(GameController other) {
    }

    public boolean checkContact(GameController other) {
        return model.checkContact(other.model);
    }
}
