package views;


import controllers.EnemyPlaneController;
import utils.GameInfo;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyBulletView extends GameView{
    private int delayImage;
    Image image1;
    Image image2;
    Image image3;
    public EnemyBulletView(Image image) {
        super(image);
    }

    public void updateImage(){
            delayImage++;
            switch (delayImage) {
                case 0:
                    image=image1;
                    break;
                case 3:
                    image=image2;
                    break;
                case 6:
                    image=image3;
                    delayImage = 0;
                    break;
            }
    }

    public void setImage(EnemyPlaneController.EnemyType type){
        switch (type){
            case YELLOW:
                image= GameInfo.rocket1Image;
                image1=GameInfo.rocket2Image;
                image2=GameInfo.rocket3Image;
                image3=GameInfo.rocket4Image;
                break;
            case WHITE:
                break;
        }
    }
}
