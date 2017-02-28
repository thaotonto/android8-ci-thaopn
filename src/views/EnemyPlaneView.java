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
    }

    private void setExploded() {
        this.exploded = true;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
