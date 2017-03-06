package views;

import utils.GameInfo;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerPlaneView extends GameView {

    public PlayerPlaneView(Image image) {
        super(image);
    }

    public void setImage(int type) {
        switch (type) {
            case 1:
                image = GameInfo.playerPlane1Image;
                break;
            case 4:
                image = GameInfo.playerPlane4Image;
                break;
        }
    }

}
