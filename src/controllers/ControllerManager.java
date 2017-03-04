package controllers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Thaotonto on 2/28/2017.
 */
public class ControllerManager {
    private  ArrayList<GameController> gameControllers=new ArrayList<>();

    public void run(){
        Iterator<GameController> iterator=gameControllers.iterator();
        while (iterator.hasNext()){
            GameController gameController= iterator.next();
            if (gameController.isActive()==false) iterator.remove();
        }
    }

    public void draw(Graphics graphics){
        for(GameController gameController: gameControllers){
            gameController.draw(graphics);
        }
    }

    public void addGameController(GameController gameController){
        gameControllers.add(gameController);
    }

}
