package views;

import models.PlayerBulletModel;
import utils.GameInfo;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerBulletView extends GameView{

    public PlayerBulletView(Image image) {
        super(image);
    }

    public void setImage(int type){
        switch (type){
            case 0:
                image=GameInfo.bulletImage;
                break;
            case 1:
                image= GameInfo.doubleBulletImage;
                break;
            case 2:
                image= GameInfo.bulletRoundImage;
                break;
        }
    }
}
