import controllers.*;
import utils.GameInfo;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Thaotonto on 2/19/2017.
 */
public class GameWindow extends Frame{
    private int gameW;
    private int gameH;
    private BufferedImage backBufferImage;
    private Graphics backGraphics;
    Thread thread;
    Random random= new Random();
    PlayerPlaneController playerPlaneController;
    ArrayList<PlayerBulletController> playerBulletControllers ;
    ArrayList<EnemyPlaneController> enemyPlaneControllers;
    ArrayList<IslandController> islandControllers ;
    ArrayList<EnemyBulletController> enemyBulletControllers;
    ArrayList<MineController> mineControllers;
    ArrayList<PowerUpController> powerUpControllers;

    BackGroundController backGroundController;
    int delayIsland=0;
    int delayEnemyPlane1=0;
    int delayEnemyPlane2=0;
    int delayPowerUp=0;
    int delayMine=0;

    public GameWindow() throws IOException {
        gameH= GameInfo.gameHeight;
        gameW=GameInfo.gameWidth;
        setVisible(true);
        setSize(gameW, gameH);
        backGroundController = new BackGroundController(0,0);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Window Closing");
                System.exit(0);
            }
        });
        playerBulletControllers = new ArrayList<>();
        islandControllers = new ArrayList<>();
        enemyPlaneControllers = new ArrayList<>();
        enemyBulletControllers= new ArrayList<>();
        powerUpControllers=new ArrayList<>();
        mineControllers= new ArrayList<>();
        playerPlaneController = new PlayerPlaneController((gameW-GameInfo.playerPlaneWidth)/2,
                gameH-GameInfo.playerPlaneHeight,playerBulletControllers);
        // 1: Load image
        update(getGraphics());

        // 2: Draw image

        repaint();
        addKeyListener(new KeyAdapter() {

//            @Override
//            public void keyTyped(KeyEvent e) {
//                super.keyTyped(e);
//            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        playerPlaneController.setRight(true);
                        break;
                    case KeyEvent.VK_LEFT:
                        playerPlaneController.setLeft(true);
                        break;
                    case KeyEvent.VK_UP:
                        playerPlaneController.setUp(true);
                        break;
                    case KeyEvent.VK_DOWN:
                        playerPlaneController.setDown(true);
                        break;
                    case KeyEvent.VK_SPACE:
                        playerPlaneController.setShoot(true);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        playerPlaneController.setRight(false);
                        break;
                    case KeyEvent.VK_LEFT:
                        playerPlaneController.setLeft(false);
                        break;
                    case KeyEvent.VK_UP:
                        playerPlaneController.setUp(false);
                        break;
                    case KeyEvent.VK_DOWN:
                        playerPlaneController.setDown(false);
                        break;
                    case KeyEvent.VK_SPACE:
                        playerPlaneController.setShoot(false);
                        break;
                }
            }
        });

        thread= new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (playerPlaneController.isActive()) {
                        playerPlaneController.run();
                        delayIsland++;
                        delayEnemyPlane1++;
                        Iterator<PlayerBulletController> iterplayerBullet = playerBulletControllers.iterator();
                        Iterator<EnemyPlaneController> iterenemyPlane = enemyPlaneControllers.iterator();
                        Iterator<IslandController> iterIsland = islandControllers.iterator();
                        Iterator<MineController> iterMine = mineControllers.iterator();
                        Iterator<PowerUpController> iterPowerUp = powerUpControllers.iterator();
                        while (iterplayerBullet.hasNext()) {
                            PlayerBulletController playerBulletController = iterplayerBullet.next();
                            playerBulletController.run();
                            if (playerBulletController.isActive() == true)
                                for (EnemyPlaneController enemyPlaneController : enemyPlaneControllers) {
                                    if (enemyPlaneController.isActive() == true && enemyPlaneController.getExplode() == false) {
                                        playerBulletController.shoot(enemyPlaneController);
                                    }
                                }
                            for (MineController mineController : mineControllers) {
                                if (mineController.isActive() == true && mineController.isExplode() == false)
                                    playerBulletController.shoot(mineController);
                            }
                            if (playerBulletController.isActive() == false) iterplayerBullet.remove();
                        }
                        while (iterenemyPlane.hasNext()) {
                            EnemyPlaneController enemyPlaneController = iterenemyPlane.next();
                            enemyPlaneController.run();
                            playerPlaneController.contact(enemyPlaneController);
                            if (enemyPlaneController.isActive() == false)
                                iterenemyPlane.remove();
                        }
                        while (iterMine.hasNext()) {
                            MineController mineController = iterMine.next();
                            mineController.run();
                            playerPlaneController.contact(mineController);
                            if (mineController.isActive() == false)
                                iterMine.remove();
                        }
                        while (iterPowerUp.hasNext()) {
                            PowerUpController powerUpController = iterPowerUp.next();
                            powerUpController.run();
                            playerPlaneController.contact(powerUpController);
                            if (powerUpController.isActive() == false)
                                iterPowerUp.remove();
                        }
                        while (iterIsland.hasNext()) {
                            IslandController islandController = iterIsland.next();
                            islandController.run();
                            if (islandController.isActive() == false) iterIsland.remove();
                        }
                        if (delayEnemyPlane1 == GameInfo.delayEnemyPlane1time) {
                            EnemyPlaneController enemyPlaneController = new EnemyPlaneController(random.nextInt(gameW - GameInfo.enemyPlaneWidth), 0,
                                    enemyBulletControllers, 1);
                            enemyPlaneControllers.add(enemyPlaneController);
                            delayEnemyPlane1 = 0;
                        }
                        if (delayEnemyPlane2 == GameInfo.delayEnemyPlane2time) {
                            EnemyPlaneController enemyPlaneController = new EnemyPlaneController(0, 0,
                                    enemyBulletControllers, 2);
                            enemyPlaneControllers.add(enemyPlaneController);
                            delayEnemyPlane2 = 0;
                        }
                        delayEnemyPlane2++;
                        if (delayIsland == GameInfo.delayIslandtime) {
                            IslandController islandController = null;
                            switch (random.nextInt(2)) {
                                case 0:
                                    islandController = new IslandController((int) (Math.random() * GameInfo.gameWidth), 0, GameInfo.Island1Image);
                                    break;
                                case 1:
                                    islandController = new IslandController((int) (Math.random() * GameInfo.gameWidth), 0, GameInfo.Island2Image);
                                    break;
                            }
                            islandControllers.add(islandController);
                            delayIsland = 0;
                        }
                        for (EnemyPlaneController enemyPlaneController : enemyPlaneControllers) {
                            enemyPlaneController.shoot();
                            enemyPlaneController.setDelayBullet();
                        }
                        Iterator<EnemyBulletController> iterenemyBullet = enemyBulletControllers.iterator();
                        while (iterenemyBullet.hasNext()) {
                            EnemyBulletController enemyBulletController = iterenemyBullet.next();
                            enemyBulletController.run();
                            playerPlaneController.contact(enemyBulletController);
                            if (enemyBulletController.isActive() == false) iterenemyBullet.remove();
                        }
                        if (delayMine == GameInfo.delayMinetime) {
                            MineController mineController = new MineController(random.nextInt(GameInfo.gameWidth), 0);
                            mineControllers.add(mineController);
                            delayMine = 0;
                        }
                        delayMine++;
                        if (delayPowerUp == GameInfo.delayPowerUptime) {
                            PowerUpController powerUpController = new PowerUpController(random.nextInt(GameInfo.gameWidth), 0);
                            powerUpControllers.add(powerUpController);
                            delayPowerUp = 0;
                        }
                        delayPowerUp++;
                        repaint();
                    } //else getGraphics().drawImage(GameInfo.gameOverImage,0,0,GameInfo.gameWidth,GameInfo.gameHeight,null);
                }
            }
        });
        backBufferImage = new BufferedImage(gameW,gameH, BufferedImage.TYPE_INT_ARGB);
    }

    public void start(){
        thread.start();
    }

    @Override
    public void update(Graphics g) {
        if (backBufferImage!=null) {
            backGraphics = backBufferImage.getGraphics();
            backGroundController.draw(backGraphics);
            for(IslandController islandController: islandControllers){
                islandController.draw(backGraphics);
            }
            playerPlaneController.draw(backGraphics);
            for(EnemyPlaneController enemyPlaneController:enemyPlaneControllers){
                enemyPlaneController.draw(backGraphics);
            }
            for(EnemyBulletController enemyBulletController: enemyBulletControllers){
                enemyBulletController.draw(backGraphics);
            }
            for(PlayerBulletController playerBulletController:playerBulletControllers){
                playerBulletController.draw(backGraphics);
            }
            for(MineController mineController:mineControllers){
                mineController.draw(backGraphics);
            }
            for(PowerUpController powerUpController:powerUpControllers){
                powerUpController.draw(backGraphics);
            }
            g.drawImage(backBufferImage, 0, 0, null);
        }
    }

}
