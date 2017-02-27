package views;

import models.PlayerPlaneModel;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class PlayerPlaneView {
    private Image image;

    public PlayerPlaneView(Image image) {
        this.image = image;
    }
    public void draw(Graphics graphics, PlayerPlaneModel model){
        graphics.drawImage(image,model.getX(), model.getY(), model.getWidth(), model.getHeight(),null);
    }
}
