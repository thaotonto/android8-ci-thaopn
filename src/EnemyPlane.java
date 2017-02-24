import com.Exploxe;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Thaotonto on 2/21/2017.
 */
public class EnemyPlane {
    public int x;
    public int y;
    public int w;
    public int h;
    public Image image;
    public int speed;
    public ArrayList<EnemyBullet> enemyBullets= new ArrayList<>();
    int delayExplode=0;
}
