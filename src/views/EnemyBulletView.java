package views;

import models.EnemyBulletModel;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyBulletView {
    private Image image;

    public EnemyBulletView(Image image) {
        this.image = image;
    }
    public void draw(Graphics graphics, EnemyBulletModel model) {
        graphics.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);
    }
}
