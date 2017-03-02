package views;

import utils.GameInfo;

import java.awt.*;

/**
 * Created by Thaotonto on 3/2/2017.
 */
public class MineView extends GameView{
    private int delayExplosion=0;
    private boolean exploded=false;

    public MineView(Image image) {
        super(image);
    }

    public boolean isExploded() {
        return exploded;
    }

    public void explode(){
        delayExplosion++;
        switch (delayExplosion) {
            case 1:
                image= GameInfo.explosion6Image;
                break;
            case 3:
                image=GameInfo.explosion5Image;
                break;
            case 5:
                image=GameInfo.explosion4Image;
                break;
            case 7:
                image=GameInfo.explosion3Image;
                break;
            case 9:
                image=GameInfo.explosion2Image;
                break;
            case 11:
                image=GameInfo.explosion1Image;
                break;
            case 13:
                exploded=true;
                break;
        }
    }
}
