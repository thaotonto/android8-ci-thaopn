package controllers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Thaotonto on 2/28/2017.
 */
public class ControllerManager {
    private static ArrayList<GameController> gameControllers = new ArrayList<>();
    private static Iterator<GameController>  iterator;

    public synchronized void run() {
        iterator=gameControllers.iterator();
        while (iterator.hasNext()) {
            GameController gameController = iterator.next();
            if (!gameController.isActive()) iterator.remove();
            else gameController.run();
        }

        for (int i = 0; i < gameControllers.size() - 1; i++) {
            for (int j = i + 1; j < gameControllers.size(); j++) {
                GameController gi = gameControllers.get(i);
                GameController gj = gameControllers.get(j);
                if (gi.checkContact(gj)){
                    gi.onContact(gj);
                    gj.onContact(gi);
                }
            }
        }
    }

    public void draw(Graphics graphics) {
        Iterator<GameController> iterator = gameControllers.iterator();
        while (iterator.hasNext()) {
            GameController gameController = iterator.next();
            if (gameController.isActive()) gameController.draw(graphics);
        }
    }

    public static void addGameController(GameController gameController) {
        gameControllers.add(gameController);
        iterator = gameControllers.iterator();
    }

}
