package utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Thaotonto on 2/28/2017.
 */
public class GameInfo {
    public static int gameWidth=400;
    public static int gameHeight=600;
    public static int playerPlaneWidth=40;
    public static int playerPlaneHeight=25;
    public static int playerBulletWidth=7;
    public static int playerBulletHeight=20;
    public static int enemyPlaneWidth=22;
    public static int enemyPlaneHeight=22;
    public static int enemyBulletWidth=7;
    public static int enemyBulletHeight=20;
    public static int isLandWidth=64;
    public static int isLandHeight=60;
    public static int powerUpWidth=24;
    public static int powerUpHeight=25;
    public static int bombWidth=32;
    public static int bombHeight=32;
    public static int mineWidth=22;
    public static int mineHeight=22;
    public static int doubleBulletWidth=14;
    public static int doubleBulletHeight=30;
    public static int bulletLeftWidth=7;
    public static int bulletLeftHeight=7;
    public static int bulletRightWidth=7;
    public static int bulletRightHeight=7;
    public static int roundBulletWidth=6;
    public static int roundBulletHeight=6;
    public static int rocketWidth=10;
    public static int rocketHeight=40;
    public static Image BackGroundImage=Utils.loadImageFromres("blue.png");
    public static Image playerPlane1Image=Utils.loadImageFromres("playerShip1_red.png");
    public static Image playerPlane2Image=Utils.loadImageFromres("plane2.png");
    public static Image playerPlane3Image=Utils.loadImageFromres("plane3.png");
    public static Image playerPlane4Image=Utils.loadImageFromres("playerShip3_red.png");
    public static Image enemyPlanewhite1Image=Utils.loadImageFromres("enemy_plane_white_1.png");
    public static Image enemyPlanewhite2Image=Utils.loadImageFromres("enemy_plane_white_2.png");
    public static Image enemyPlanewhite3Image=Utils.loadImageFromres("enemy_plane_white_3.png");
    public static Image enemyPlaneyellow1Image=Utils.loadImageFromres("enemy_plane_yellow_1.png");
    public static Image enemyPlaneyellow2Image=Utils.loadImageFromres("enemy_plane_yellow_2.png");
    public static Image enemyPlaneyellow3Image=Utils.loadImageFromres("enemy_plane_yellow_3.png");
    public static Image enemyPlanegreen1Image=Utils.loadImageFromres("enemy-green-1.png");
    public static Image enemyPlanegreen2Image=Utils.loadImageFromres("enemy-green-2.png");
    public static Image enemyPlanegreen3Image=Utils.loadImageFromres("enemy-green-3.png");
    public static Image explosion6Image= Utils.loadImageFromres("explosion6.png");
    public static Image explosion5Image= Utils.loadImageFromres("explosion5.png");
    public static Image explosion4Image= Utils.loadImageFromres("explosion4.png");
    public static Image explosion3Image= Utils.loadImageFromres("explosion3.png");
    public static Image explosion2Image= Utils.loadImageFromres("explosion2.png");
    public static Image explosion1Image= Utils.loadImageFromres("explosion1.png");
    public static Image mineImage=Utils.loadImageFromres("mine.png");
    public static Image bombImage=Utils.loadImageFromres("bomb.png");
    public static Image powerUpImage=Utils.loadImageFromres("power-up.png");
    public static Image enemyBulletImage=Utils.loadImageFromres("laserRed13.png");
    public static Image bulletImage=Utils.loadImageFromres("laserBlue13.png");
    public static Image Island1Image=Utils.loadImageFromres("island.png");
    public static Image Island2Image=Utils.loadImageFromres("island-2.png");
    public static Image doubleBulletImage=Utils.loadImageFromres("bullet-double.png");
    public static Image bulletRoundImage=Utils.loadImageFromres("bullet-round.png");
    public static Image gameOverImage=Utils.loadImageFromres("wasted.png");
    public static Image rocket1Image=Utils.loadImageFromres("rocket-0.png");
    public static Image rocket2Image=Utils.loadImageFromres("rocket-1.png");
    public static Image rocket3Image=Utils.loadImageFromres("rocket-2.png");
    public static Image rocket4Image=Utils.loadImageFromres("rocket-3.png");
    public static int delayIslandtime=300;
    public static int delayEnemyPlane1time=150;
    public static int delayEnemyPlane2time=200;
    public static int delayEnemyPlane3time=550;
    public static int delayBullettime=100;
    public static int delayMinetime=400;
    public static int delayPowerUptime=500;
    public static int speedPlayerPlane=5;
    public static int speedEnemyPlane=2;
    public static int speedPlayerBullet=6;
    public static int speedEnemyBullet=3;
    public static int speedIsland=1;
    public static int speedMine=1;
    public static int speedPowerUp=1;

}
