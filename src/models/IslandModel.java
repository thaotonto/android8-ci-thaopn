package models;

/**
 * Created by Thaotonto on 2/27/2017.
 */
public class IslandModel extends GameModel {

    private static final int SPEED = 1;

    public IslandModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public static int getSPEED() {
        return SPEED;
    }

    public void move(){
        y+=SPEED;
    }
}
