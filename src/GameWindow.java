import controllers.*;
import utils.GameInfo;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Thaotonto on 2/19/2017.
 */
public class GameWindow extends Frame {
    private BufferedImage backBufferImage;
    private Graphics backGraphics;
    Thread thread;
    Random random = new Random();
    ControllerManager controllerManager;
    PlayerPlaneController playerPlaneController;
    BackGroundController backGroundController;
    int delayIsland = 0;
    int delayEnemyPlane1 = 0;
    int delayEnemyPlane2 = 0;
    int delayPowerUp = 0;
    int delayMine = 0;

    public GameWindow() throws IOException {
        setVisible(true);
        setSize(GameInfo.gameWidth, GameInfo.gameHeight);
        controllerManager = new ControllerManager();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Window Closing");
                System.exit(0);
            }
        });

        backGroundController = new BackGroundController(0, 0);
        playerPlaneController = new PlayerPlaneController((GameInfo.gameWidth - GameInfo.playerPlaneWidth) / 2,
                GameInfo.gameHeight - GameInfo.playerPlaneHeight);

        controllerManager.addGameController(backGroundController);
        controllerManager.addGameController(playerPlaneController);

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

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (playerPlaneController.isActive()) {
                        controllerManager.run();

                        delayIsland++;
                        delayEnemyPlane1++;
                        delayEnemyPlane2++;
                        delayPowerUp++;
                        delayMine++;

                        if (delayMine == GameInfo.delayMinetime) {
                            MineController mineController = new MineController(random.nextInt(GameInfo.gameWidth), 0);
                            controllerManager.addGameController(mineController);
                            delayMine = 0;
                        }

                        if (delayPowerUp == GameInfo.delayPowerUptime) {
                            PowerUpController powerUpController = new PowerUpController(random.nextInt(GameInfo.gameWidth), 0);
                            controllerManager.addGameController(powerUpController);
                            delayPowerUp = 0;
                        }

                        if (delayIsland == GameInfo.delayIslandtime) {
                            IslandController islandController = null;
                            switch (random.nextInt(2)) {
                                case 0:
                                    islandController = new IslandController(random.nextInt(GameInfo.gameWidth), 0, GameInfo.Island1Image);
                                    break;
                                case 1:
                                    islandController = new IslandController(random.nextInt(GameInfo.gameWidth), 0, GameInfo.Island2Image);
                                    break;
                            }
                            controllerManager.addGameController(islandController);
                            delayIsland = 0;
                        }

                        if (delayEnemyPlane1 == GameInfo.delayEnemyPlane1time) {
                            EnemyPlaneController enemyPlaneController = EnemyPlaneController.create(EnemyPlaneController.EnemyType.WHITE);
                            controllerManager.addGameController(enemyPlaneController);
                            delayEnemyPlane1 = 0;
                        }

                        if (delayEnemyPlane2 == GameInfo.delayEnemyPlane2time) {
                            EnemyPlaneController enemyPlaneController = EnemyPlaneController.create(EnemyPlaneController.EnemyType.YELLOW);
                            controllerManager.addGameController(enemyPlaneController);
                            delayEnemyPlane2 = 0;
                        }

                        repaint();
                    }
                }
            }
        });
        backBufferImage = new BufferedImage(GameInfo.gameWidth, GameInfo.gameHeight, BufferedImage.TYPE_INT_ARGB);
    }

    public void start() {
        thread.start();
    }

    @Override
    public void update(Graphics g) {
        if (backBufferImage != null) {
            backGraphics = backBufferImage.getGraphics();
            controllerManager.draw(backGraphics);
            playerPlaneController.draw(backGraphics);
            g.drawImage(backBufferImage, 0, 0, null);
        }
    }

}
