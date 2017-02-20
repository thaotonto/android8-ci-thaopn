import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by Thaotonto on 2/19/2017.
 */
public class GameWindow extends Frame{
    Image backgroundImage;
    Image plane2Image;
//    Image bulletImage;
//    Image enemyphanewhite1Image;
    private int planeX=(400-70)/2;
    private int planeY=600-65;
    private int gameW=400;
    private int gameH=600;

    public GameWindow() throws IOException {
        setVisible(true);
        setSize(400, 600);
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
//        try{
//        backgroundImage = ImageIO.read (new File("resources/background.png"));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//        update(getGraphics());
//        try{
//            plane2Image = ImageIO.read (new File("resources/plane2.png"));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        try{
//            bulletImage = ImageIO.read (new File("resources/bullet.png"));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        try{
//            enemyphanewhite1Image = ImageIO.read (new File("resources/enemy_plane_white_1.png"));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        backgroundImage= loadImageFromres("background.png");
        plane2Image= loadImageFromres("plane2.png");
//        bulletImage= loadImageFromres("bullet.png");
//        enemyphanewhite1Image=loadImageFromres("enemy_plane_white_1.png");
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
                        // TO DO : MOVE PLANE TO RIGHT
                        if (planeX + 10 < gameW - 70) {
                            planeX += 10;
                            repaint();
                        }
                        System.out.println("PressedRight");
                        break;
                    case KeyEvent.VK_LEFT:
                        // TO DO : MOVE PLANE TO LEFT
                        if (planeX - 10 > 0) {
                            planeX -= 10;
                            repaint();
                        }
                        System.out.println("PressedLeft");
                        break;
                    case KeyEvent.VK_UP:
                        // TO DO : MOVE PLANE TO UP
                        if (planeY - 10 > 25) {
                            planeY -= 10;
                            repaint();
                        }
                        System.out.println("PressedUp");
                        break;
                    case KeyEvent.VK_DOWN:
                        // TO DO : MOVE PLANE TO DOWN
                        if (planeY + 10 < gameH - 60) {
                            planeY += 10;
                            repaint();
                        }
                        System.out.println("PressedDown");
                        break;
                }
            }

//            @Override
//            public void keyReleased(KeyEvent e) {
//                super.keyReleased(e);
//            }
        });
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
        g.drawImage(backgroundImage,0,0,gameW, gameH ,null);
        g.drawImage(plane2Image,planeX,planeY,70,56,null);
//        g.drawImage(bulletImage,180,450,7,17,null);
//        g.drawImage(enemyphanewhite1Image,180,200,16,16,null);

    }
}
