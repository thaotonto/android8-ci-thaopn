package views;

import models.EnemyPlaneModel;
import utils.GameInfo;

import java.awt.*;

/**
 * Created by Thaotonto on 2/26/2017.
 */
public class EnemyPlaneView extends GameView{
    private boolean exploded=false;
    private int delayExplosion=0;
    private int delayImage=0;
    Image image1;
    Image image2;
    Image image3;

    public EnemyPlaneView(Image image) {
        super(image);
    }

    public boolean isExploded() {
        return exploded;
    }

    public void explode(){
        delayExplosion++;
        switch (delayExplosion) {
            case 1:
                image=GameInfo.explosion6Image;
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

    public void updateImage(){
        if (exploded==false) {
            delayImage++;
            switch (delayImage) {
                case 0:
                    image=image3;
                    break;
                case 3:
                    image=image2;
                    break;
                case 6:
                    image=image1;
                    delayImage = 0;
                    break;
            }
        }
    }

    public void setImageType(EnemyPlaneModel model){
        switch (model.getType()){
            case 1:
                image=GameInfo.enemyPlanewhite1Image;
                image1=GameInfo.enemyPlanewhite1Image;
                image2=GameInfo.enemyPlanewhite2Image;
                image3=GameInfo.enemyPlanewhite3Image;
                break;
            case 2:
                image=GameInfo.enemyPlaneyellow1Image;
                image1=GameInfo.enemyPlaneyellow1Image;
                image2=GameInfo.enemyPlaneyellow2Image;
                image3=GameInfo.enemyPlaneyellow3Image;
                break;
        }
    }

}
