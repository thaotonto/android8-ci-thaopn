import sun.awt.image.PixelConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Thaotonto on 2/19/2017.
 */
public class GameWindow extends Frame{
    private int gameW=400;
    private int gameH=600;
    Image backgroundImage;
    PlayerPlane playerPlane =new PlayerPlane();
    Thread thread;
    private BufferedImage backBufferImage;
    private Graphics backGraphics;
    ArrayList<PlayerBullet> playerBullets =new ArrayList<>();
    ArrayList<EnemyPlane> enemyPlanes = new ArrayList<>();
    ArrayList<EnemyBullet> enemyBullets= new ArrayList<>();
    Random random= new Random();
    public GameWindow() throws IOException {
        setVisible(true);
        setSize(gameW, gameH);
        playerPlane.speed=8;
        playerPlane.w=70;
        playerPlane.h=56;
        playerPlane.x=(gameW-playerPlane.w)/2;
        playerPlane.y=gameH-playerPlane.h;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Window Closing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                System.out.println("Window Closed");
                System.exit(0);
            }
        });

        // 1: Load image


        backgroundImage= loadImageFromres("background.png");
        playerPlane.image= loadImageFromres("plane2.png");
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
                        if (playerPlane.x + playerPlane.speed < gameW - (playerPlane.w-5)) {
                            playerPlane.x += playerPlane.speed;
                        }
                        System.out.println("PressedRight");
                        break;
                    case KeyEvent.VK_LEFT:
                        // TODO : MOVE PLANE TO LEFT
                        if (playerPlane.x - playerPlane.speed > 0) {
                            playerPlane.x -= playerPlane.speed;
                        }
                        System.out.println("PressedLeft");
                        break;
                    case KeyEvent.VK_UP:
                        // TODO : MOVE PLANE TO UP
                        if (playerPlane.y - playerPlane.speed > 20) {
                            playerPlane.y -= playerPlane.speed;
                        }
                        System.out.println("PressedUp");
                        break;
                    case KeyEvent.VK_DOWN:
                        // TODO : MOVE PLANE TO DOWN
                        if (playerPlane.y + playerPlane.speed < gameH - (playerPlane.h+10)) {
                            playerPlane.y += playerPlane.speed;
                        }
                        System.out.println("PressedDown");
                        break;
                    case KeyEvent.VK_SPACE:
                        PlayerBullet playerBullet = new PlayerBullet();
                        PlayerBullet preBullet = new PlayerBullet();
                        playerBullet.w=13;
                        playerBullet.h=33;
                        playerBullet.x= (playerPlane.x+playerPlane.w/2-playerBullet.w/2);
                        playerBullet.y= playerPlane.y;
                        playerBullet.speed=5;
                        playerBullet.image= loadImageFromres("bullet.png");
                        if (playerBullets.size()!=0){
                            preBullet=playerBullets.get(playerBullets.size()-1);
                            if (preBullet.y<(playerBullet.y-(playerBullet.speed+playerBullet.h)))
                                playerBullets.add(playerBullet);
                        } else playerBullets.add(playerBullet);

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
                    EnemyPlane enemyPlane= new EnemyPlane();
                    EnemyPlane pre_enemyPlane= new EnemyPlane();
                    enemyPlane.h=32;
                    enemyPlane.w=32;
                    enemyPlane.y=0;
                    enemyPlane.speed=1;
                    enemyPlane.image=loadImageFromres("enemy_plane_white_1.png");
                    enemyPlane.x=random.nextInt(400);
                    if (enemyPlanes.size()!=0){
                        if (enemyPlanes.size()<100){
                            pre_enemyPlane=enemyPlanes.get(enemyPlanes.size()-1);
                            if (pre_enemyPlane.x!=enemyPlane.x && pre_enemyPlane.y+pre_enemyPlane.h>enemyPlane.h*5){
                                enemyPlanes.add(enemyPlane);
                                EnemyBullet enemyBullet = new EnemyBullet();
                                enemyBullet.w=32;
                                enemyBullet.h=32;
                                enemyBullet.x= (enemyPlane.x+enemyPlane.w/2-enemyBullet.w/2);
                                enemyBullet.y= enemyPlane.y+enemyBullet.h;
                                enemyBullet.speed=3;
                                enemyBullet.image= loadImageFromres("enemy_bullet.png");
                                enemyBullets.add(enemyBullet);
                            }
                        }
                    } else {
                        enemyPlanes.add(enemyPlane);
                        EnemyBullet enemyBullet = new EnemyBullet();
                        enemyBullet.w=32;
                        enemyBullet.h=32;
                        enemyBullet.x= (enemyPlane.x+enemyPlane.w/2-enemyBullet.w/2);
                        enemyBullet.y= enemyPlane.y+enemyBullet.h;
                        enemyBullet.speed=3;
                        enemyBullet.image= loadImageFromres("enemy_bullet.png");
                        enemyBullets.add(enemyBullet);
                    }
                    for(EnemyBullet enemy_bullet: enemyBullets){
                        if (enemy_bullet!=null){
                            enemy_bullet.y+=enemy_bullet.speed;
                        }
                    }
                    for(EnemyPlane enemy_Plane: enemyPlanes){
                        if (enemy_Plane!=null){
                            enemy_Plane.y+=enemy_Plane.speed;
                        }
                    }
                    EnemyPlane enemyPlane1;
                    enemyPlane1=enemyPlanes.get(0);
                    if (enemyPlane1.y>gameH) enemyPlanes.remove(enemyPlane1);
                    repaint();
                    for(PlayerBullet playerBullet:playerBullets){
                    if (playerBullet!=null){
                        playerBullet.y-=playerBullet.speed;
                    }
                    }
                }
            }
        });

        backBufferImage = new BufferedImage(gameW,gameH, BufferedImage.TYPE_INT_ARGB);

    }
    public void start(){
        thread.start();
    }


    private Image loadImageFromres(String url){
        try {
            Image image = ImageIO.read(new File("resources/"+url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void update(Graphics g) {
        if (backBufferImage!=null) {
            backGraphics = backBufferImage.getGraphics();
            backGraphics.drawImage(backgroundImage, 0, 0, gameW, gameH, null);
            backGraphics.drawImage(playerPlane.image, playerPlane.x, playerPlane.y, playerPlane.w, playerPlane.h, null);
            for(EnemyPlane enemyPlane: enemyPlanes){
                if (enemyPlane!=null){
                    backGraphics.drawImage(enemyPlane.image,enemyPlane.x,enemyPlane.y,enemyPlane.w,enemyPlane.h,null);
                }
            }
            for (EnemyBullet enemyBullet: enemyBullets){
                if (enemyBullet!=null){
                    backGraphics.drawImage(enemyBullet.image,enemyBullet.x,enemyBullet.y,enemyBullet.w,enemyBullet.h,null);
                }
            }
            for (PlayerBullet playerBullet: playerBullets){
                if (playerBullet!=null){
                    backGraphics.drawImage(playerBullet.image,playerBullet.x,playerBullet.y,playerBullet.w,playerBullet.h,null);
                }
            }

            g.drawImage(backBufferImage, 0, 0, null);
        }



    }
}
