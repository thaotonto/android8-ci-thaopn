import controllers.*;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
    ArrayList<PlayerBulletController> playerBulletControllers = new ArrayList<>();
    ArrayList<EnemyPlaneController> enemyPlaneControllers = new ArrayList<>();
    ArrayList<IslandController> islandControllers = new ArrayList<>();
    PlayerPlaneController playerPlaneController;
    int delayIsland=0;
    int delayEnemyPlane=0;

    BackGround backGround = new BackGround();

    public GameWindow() throws IOException {
        gameH=600;
        gameW=400;
        setVisible(true);
        setSize(gameW, gameH);
        backGround.w=400;
        backGround.h=600;
        backGround.x=0;
        backGround.y=0;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Window Closing");
                System.exit(0);
            }
        });
        playerPlaneController = new PlayerPlaneController((gameW-50)/2,gameH-30);
        // 1: Load image
        backGround.image=Utils.loadImageFromres("background.png");
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
                        // TODO : MOVE PLANE TO RIGHT
                        playerPlaneController.moveRight(gameW);
                        break;
                    case KeyEvent.VK_LEFT:
                        // TODO : MOVE PLANE TO LEFT
                        playerPlaneController.moveLeft();
                        break;
                    case KeyEvent.VK_UP:
                        // TODO : MOVE PLANE TO UP
                        playerPlaneController.moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        // TODO : MOVE PLANE TO DOWN
                        playerPlaneController.moveDown(gameH);
                        break;
                    case KeyEvent.VK_SPACE:
                        // TODO : FIRE BULLET
                        PlayerBulletController playerBulletController = new PlayerBulletController((playerPlaneController.getX()+playerPlaneController.getWidth()/2-7/2),
                                playerPlaneController.getY());
                        if (playerBulletControllers.size()!=0){
                            PlayerBulletController prePlayerBulletController = playerBulletControllers.get(playerBulletControllers.size()-1);
                            if (playerBulletController.checkSpawm(prePlayerBulletController)==true)
                                playerBulletControllers.add(playerBulletController);
                        } else  playerBulletControllers.add(playerBulletController);
                        break;
                }
            }

//            @Override
//            public void keyReleased(KeyEvent e) {
//                super.keyReleased(e);
//            }

        });

        thread= new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Iterator<PlayerBulletController> iterplayerBullet = playerBulletControllers.iterator();
                    Iterator<EnemyPlaneController> iterenemyPlane = enemyPlaneControllers.iterator();
                    Iterator<IslandController> iterIsland = islandControllers.iterator();
                    while (iterplayerBullet.hasNext()){
                        PlayerBulletController playerBulletController= iterplayerBullet.next();
                        playerBulletController.run();
                        if (playerBulletController.isActive()==false) iterplayerBullet.remove();
                    }
                    while (iterenemyPlane.hasNext()){
                        EnemyPlaneController enemyPlaneController= iterenemyPlane.next();
                        enemyPlaneController.run(gameH);
                        if (enemyPlaneController.isActive()==false && enemyPlaneController.getEnemyBulletControllers().size()==0)
                            iterenemyPlane.remove();
                    }
                    while (iterIsland.hasNext()){
                        IslandController islandController = iterIsland.next();
                        islandController.run(gameH);
                        if (islandController.isActive()==false) iterIsland.remove();
                    }
                    if (delayEnemyPlane==70){
                        EnemyPlaneController enemyPlaneController = new EnemyPlaneController(random.nextInt(gameW - 22),0);
                        enemyPlaneControllers.add(enemyPlaneController);
                        delayEnemyPlane = 0;
                    }
                    if (delayIsland==300){
                        IslandController islandController=new IslandController(gameW-64,0);
                        islandControllers.add(islandController);
                        delayIsland=0;
                    }
                    delayIsland++;
                    delayEnemyPlane++;
                    for(EnemyPlaneController enemyPlaneController: enemyPlaneControllers){
                        if (enemyPlaneController.getDelayBullet()==100 && enemyPlaneController.getExplode()==false){
                            enemyPlaneController.genBullet();
                        }
                        enemyPlaneController.setDelayBullet();
                        Iterator<EnemyBulletController> iterenemyBullet= enemyPlaneController.getEnemyBulletControllers().iterator();
                        while(iterenemyBullet.hasNext()){
                            EnemyBulletController enemyBulletController= iterenemyBullet.next();
                            enemyBulletController.run(gameH);
                            if (enemyBulletController.isActive()==false) iterenemyBullet.remove();
                        }
                    }
                    for(PlayerBulletController playerBulletController: playerBulletControllers){
                        if (playerBulletController.isActive()==true)
                            for(EnemyPlaneController enemyPlaneController:enemyPlaneControllers){
                                if (enemyPlaneController.isActive()==true && enemyPlaneController.getExplode()==false &&playerBulletController.isActive()==true) {
                                    enemyPlaneController.isExplode(playerBulletController);
                                }
                                if (enemyPlaneController.getExplode()==true) {
                                    enemyPlaneController.setDelayExplosion();
                                    if (enemyPlaneController.getDelayExplosion()==7) enemyPlaneController.setActive();
                                }
                            }
                    }
                    repaint();
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
            backGraphics.drawImage(backGround.image, backGround.x, backGround.y, gameW, gameH, null);
            for(IslandController islandController: islandControllers){
                islandController.draw(backGraphics);
            }
            playerPlaneController.draw(backGraphics);
            for(EnemyPlaneController enemyPlaneController:enemyPlaneControllers){
                enemyPlaneController.draw(backGraphics);
                for(EnemyBulletController enemyBulletController:enemyPlaneController.getEnemyBulletControllers()){
                    enemyBulletController.draw(backGraphics);
                }
            }
            for(PlayerBulletController playerBulletController:playerBulletControllers){
                playerBulletController.draw(backGraphics);
            }
            g.drawImage(backBufferImage, 0, 0, null);
        }
    }

}
