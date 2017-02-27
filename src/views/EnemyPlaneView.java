package views;

import controllers.EnemyPlaneController;
import models.EnemyPlaneModel;
import utils.Utils;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyPlaneView {
    private Image image;
    private boolean exploded=false;

    public EnemyPlaneView(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics, EnemyPlaneModel model){
        if (exploded==false)
            graphics.drawImage(image,model.getX(),model.getY(),model.getWidth(),model.getHeight(),null);
        switch (model.getDelayExplosion()) {
            case 1:
                image = Utils.loadImageFromres("explosion1.png");
                break;
            case 2:
                image = Utils.loadImageFromres("explosion2.png");
                break;
            case 3:
                image = Utils.loadImageFromres("explosion3.png");
                break;
            case 4:
                image = Utils.loadImageFromres("explosion4.png");
                break;
            case 5:
                image = Utils.loadImageFromres("explosion5.png");
                break;
            case 6:
                image = Utils.loadImageFromres("explosion6.png");
                break;
            case 7:
                setExploded();
                break;
        }
    }

    private void setExploded() {
        this.exploded = true;
    }
}
