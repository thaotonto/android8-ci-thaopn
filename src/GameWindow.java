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

/**
 * Created by Thaotonto on 2/19/2017.
 */
public class GameWindow extends Frame{
    private static final int SPEED =10 ;
    private static final int ENEMY_SPEED = 3;
    Image backgroundImage;
    Image plane2Image;
    Image bulletImage;
    Image enemyphanewhite1Image;
    private int gameW=400;
    private int gameH=600;
    private int planeW=70;
    private int planeH=56;
    private int planeX=(gameW-planeW)/2;
    private int planeY=gameH-planeH;
    Thread thread;
    PlayerBullet playerBullet;
    private BufferedImage backBufferImage;
    private Graphics backGraphics;
    private int enemyplaneW=32;
    private int enemyplanH=32;
    private int enemyplaneX=(gameW-enemyplaneW)/2;
    private int enemyplaneY=0;
    ArrayList<PlayerBullet> listplayerbullet =new ArrayList();
    //private int enemybullet=enemyplaneY+10;

    public GameWindow() throws IOException {
        setVisible(true);
        setSize(gameW, gameH);
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

        bulletImage= loadImageFromres("bullet.png");
        enemyphanewhite1Image= loadImageFromres("enemy_plane_white_1.png");
        backgroundImage= loadImageFromres("background.png");
        plane2Image= loadImageFromres("plane2.png");
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
                        if (planeX + SPEED < gameW - (planeW-5)) {
                            planeX += SPEED;
                        }
                        System.out.println("PressedRight");
                        break;
                    case KeyEvent.VK_LEFT:
                        // TODO : MOVE PLANE TO LEFT
                        if (planeX - SPEED > 0) {
                            planeX -= SPEED;
                        }
                        System.out.println("PressedLeft");
                        break;
                    case KeyEvent.VK_UP:
                        // TODO : MOVE PLANE TO UP
                        if (planeY - SPEED > 25) {
                            planeY -= SPEED;
                        }
                        System.out.println("PressedUp");
                        break;
                    case KeyEvent.VK_DOWN:
                        // TODO : MOVE PLANE TO DOWN
                        if (planeY + SPEED < gameH - 60) {
                            planeY += SPEED;
                        }
                        System.out.println("PressedDown");
                        break;
                    case KeyEvent.VK_SPACE:
                        playerBullet= new PlayerBullet();
                        listplayerbullet.add(playerBullet);
                        playerBullet.w=13;
                        playerBullet.h=33;
                        playerBullet.image= loadImageFromres("bullet.png");
                        playerBullet.x= (planeX+planeW/2-playerBullet.w/2);
                        playerBullet.y= planeY;
                        playerBullet.speed=5;
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
                    if(enemyplaneY>gameH) {
                        enemyplaneY=ENEMY_SPEED;
                    }
                    else enemyplaneY+=ENEMY_SPEED;
                    repaint();
                    if (playerBullet!=null){
                        playerBullet.y-=playerBullet.speed;
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
            backGraphics.drawImage(plane2Image, planeX, planeY, planeW, planeH, null);
            backGraphics.drawImage(enemyphanewhite1Image,enemyplaneX,enemyplaneY,enemyplaneW,enemyplanH,null);
            if (playerBullet!=null){
            backGraphics.drawImage(playerBullet.image,playerBullet.x,playerBullet.y,playerBullet.w,playerBullet.h,null);
            }
            g.drawImage(backBufferImage, 0, 0, null);
        }



    }
}
