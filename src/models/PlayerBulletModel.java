package models;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel{
    private static final int SPEED = 5;

    public PlayerBulletModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void fly(){
        y-=SPEED;
    }
}
