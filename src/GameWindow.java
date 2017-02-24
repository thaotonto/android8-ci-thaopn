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
    private int gameW;
    private int gameH;
    BackGround backGround = new BackGround();
    PlayerPlane playerPlane =new PlayerPlane();
    Thread thread;
    private BufferedImage backBufferImage;
    private Graphics backGraphics;
    ArrayList<PlayerBullet> playerBullets =new ArrayList<>();
    ArrayList<EnemyPlane> enemyPlanes = new ArrayList<>();
    ArrayList<Island> islands= new ArrayList<>();
    Random random= new Random();
    int delayIsland=0;
    int delayEnemyPlane=0;
    int delayBullet=0;
    public GameWindow() throws IOException {
        gameH=600;
        gameW=400;
        backGround.w=400;
        backGround.h=600;
        backGround.x=0;
        backGround.y=0;
        setVisible(true);
        setSize(gameW, gameH);
        playerPlane.speed=8;
        playerPlane.w=50;
        playerPlane.h=30;
        playerPlane.x=(gameW-playerPlane.w)/2;
        playerPlane.y=gameH-playerPlane.h;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Window Closing");
                System.exit(0);
            }
        });

        // 1: Load image
        backGround.image=loadImageFromres("background.png");
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
                        break;
                    case KeyEvent.VK_LEFT:
                        // TODO : MOVE PLANE TO LEFT
                        if (playerPlane.x - playerPlane.speed > 0) {
                            playerPlane.x -= playerPlane.speed;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        // TODO : MOVE PLANE TO UP
                        if (playerPlane.y - playerPlane.speed > 10) {
                            playerPlane.y -= playerPlane.speed;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        // TODO : MOVE PLANE TO DOWN
                        if (playerPlane.y + playerPlane.speed < gameH - (playerPlane.h+10)) {
                            playerPlane.y += playerPlane.speed;
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        PlayerBullet playerBullet = new PlayerBullet();
                        PlayerBullet preBullet = new PlayerBullet();
                        playerBullet.w=7;
                        playerBullet.h=20;
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
                    if (delayEnemyPlane==150) {
                        EnemyPlane enemyPlane = new EnemyPlane();
                        enemyPlane.h = 22;
                        enemyPlane.w = 22;
                        enemyPlane.y = 0;
                        enemyPlane.speed = 2;
                        enemyPlane.image = loadImageFromres("enemy_plane_white_1.png");
                        enemyPlane.x = random.nextInt(gameW - enemyPlane.w);
                        enemyPlanes.add(enemyPlane);
                        delayEnemyPlane = 0;
                    }
                    if (delayBullet==100){
                        for (EnemyPlane enemy_Plane : enemyPlanes) {
                            if (enemy_Plane != null) {
                                if (enemy_Plane.y > 10) {
                                    EnemyBullet enemyBullet = new EnemyBullet();
                                    enemyBullet.w = 20;
                                    enemyBullet.h = 20;
                                    enemyBullet.x = (enemy_Plane.x + enemy_Plane.w / 2 - enemyBullet.w / 2);
                                    enemyBullet.y = enemy_Plane.y + enemyBullet.h - enemy_Plane.speed;
                                    enemyBullet.speed = 3;
                                    enemyBullet.image = loadImageFromres("enemy_bullet.png");
                                    enemy_Plane.enemyBullets.add(enemyBullet);
                                }
                            }
                        }
                        delayBullet=0;
                    }
                    delayBullet++;
                    for (EnemyPlane enemy_Plane : enemyPlanes) {
                        if (enemy_Plane != null) {
                            enemy_Plane.y += enemy_Plane.speed;
                        }
                    }
                    for(EnemyPlane enemyPlane1:enemyPlanes){
                        for(EnemyBullet enemy_bullet: enemyPlane1.enemyBullets){
                            if (enemy_bullet!=null){
                                enemy_bullet.y+=enemy_bullet.speed;
                            }
                        }
                        if (enemyPlane1.enemyBullets.size()!=0) {
                            EnemyBullet enemyBullet1;
                            enemyBullet1 = enemyPlane1.enemyBullets.get(0);
                            if (enemyBullet1.y + enemyBullet1.h > gameH) {
                                enemyPlane1.enemyBullets.remove(enemyBullet1);
                            }
                        }
                    }
                    if(enemyPlanes.size()!=0){
                        EnemyPlane enemyPlane1;
                        enemyPlane1 = enemyPlanes.get(0);
                        if (enemyPlane1.y + enemyPlane1.h > gameH)
                            enemyPlanes.remove(enemyPlane1);
                    }
                    for(PlayerBullet playerBullet:playerBullets){
                        if (playerBullet!=null){
                            playerBullet.y-=playerBullet.speed;
                        }
                    }
                    if (playerBullets.size()!=0){
                        PlayerBullet playerBullet;
                        do{
                                playerBullet = playerBullets.get(0);
                                if (playerBullet.y < 0)
                                    playerBullets.remove(playerBullet);
                        }while(playerBullet.y<0 && playerBullets.size()!=0);
                    }
                    delayIsland++;
                    delayEnemyPlane++;
                    if (delayIsland==300){
                        int islandImage= random.nextInt(2);
                        Island island=new Island();
                        switch (islandImage) {
                            case 0:
                               island.image = loadImageFromres("island.png");
                               break;
                            case 1:
                                island.image = loadImageFromres("island-2.png");
                                break;
                        }
                        island.y=0;
                        island.h=60;
                        island.w=64;
                        island.speed=1;
                        island.x=random.nextInt(gameW-island.w);
                        islands.add(island);
                        delayIsland=0;
                    }
                    for(Island island:islands){
                        island.y+=island.speed;
                    }
                    if (islands.size()!=0){
                        Island island;
                        island=islands.get(0);
                        if (island.y>gameH) islands.remove(island);
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
            backGraphics.drawImage(backGround.image, backGround.x, backGround.y, gameW, gameH, null);
            for(Island island:islands){
                if (island!=null)
                    backGraphics.drawImage(island.image,island.x,island.y,island.w,island.h,null);
            }
            backGraphics.drawImage(playerPlane.image, playerPlane.x, playerPlane.y, playerPlane.w, playerPlane.h, null);
            for(EnemyPlane enemyPlane: enemyPlanes){
                if (enemyPlane!=null){
                    backGraphics.drawImage(enemyPlane.image,enemyPlane.x,enemyPlane.y,enemyPlane.w,enemyPlane.h,null);
                }
                for (EnemyBullet enemyBullet: enemyPlane.enemyBullets){
                    if (enemyBullet!=null){
                        backGraphics.drawImage(enemyBullet.image,enemyBullet.x,enemyBullet.y,enemyBullet.w,enemyBullet.h,null);
                    }
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
