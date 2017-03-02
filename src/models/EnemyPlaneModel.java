package models;

import controllers.EnemyBulletController;
import utils.GameInfo;

import java.util.Random;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyPlaneModel extends GameModel{
    private int health;
    private int type;
    private Random random= new Random();
    private static final int SPEED = GameInfo.speedEnemyPlane;
    int move=0;

    public EnemyPlaneModel(int x, int y, int width, int height,int type) {
        super(x, y, width, height);
        this.type=type;
        switch (type){
            case 1:
                health=1;
                break;
            case 2:
                health=3;
                break;
            case 3:
                health=10;
                break;
        }
    }

    public void fly(){
        switch (type){
            case 1:
                y+=SPEED;
                break;
            case 2:
                y+=SPEED/2;
                if (x>GameInfo.gameWidth) {
                    move = 1;
                }
                switch (move){
                    case 0:
                        x+=SPEED;
                        break;
                    case 1:
                        x-=SPEED;
                        break;
                }
                break;
            case 3:
                y+=SPEED;
                break;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth() {
        this.health -=1;
    }

    public int getType() {
        return type;
    }
}
