package views;

import models.IslandModel;

import java.awt.*;

/**
 * Created by Thaotonto on 2/27/2017.
 */
public class IslandView {
    private Image image;

    public IslandView(Image image) {
        this.image = image;
    }
    public void draw(Graphics graphics, IslandModel model){
        graphics.drawImage(image,model.getX(), model.getY(), model.getWidth(), model.getHeight(),null);
    }
}
