package java.Utils.helper;
import javax.imageio.* ;
import java.awt.image.* ;
import java.io.* ;
import java.net.URL;

public class ImagesMadMonk {

    public static BufferedImage[] moveRight ;
    public static BufferedImage[] moveLeft ;
    public static BufferedImage[] climbLadder ;
    public static BufferedImage[] fall ;

    public BufferedImage get(String imgfile) {
        try {
            // Read from a file
            URL url;
            url = Thread.currentThread().getContextClassLoader().getResource(imgfile);
            return ImageIO.read(url);
        } catch (IOException e) {
            System.err.println("Le fichier " + imgfile + " est introuvable.");
            System.exit(1);
            return null;
        }
    }

    public ImagesMadMonk() {
        moveRight = new BufferedImage[3];
        moveLeft = new BufferedImage[3];
        fall = new BufferedImage[2];
        climbLadder = new BufferedImage[2];
        for (int i = 0; i < moveRight.length; i++) {
            //moveRight[i] = get(ResourcesPaths.ANIM_ENEMY_STEERING_WALK_PATH + "mad_monk_walk" + (i + 1) + "_right.png");
            //moveLeft[i] = get(ResourcesPaths.ANIM_ENEMY_STEERING_WALK_PATH + "mad_monk_walk" + (i + 1) + "_left.png");
        }
        for (int i=0; i < fall.length; i++) {
            //fall[i] = get(ResourcesPaths.ANIM_ENEMY_FALLS_PATH + "mad_monk_fall" + (i + 1) + ".png");
            //climbLadder[i] = get(ResourcesPaths.ANIM_ENEMY_STEERING_CLIMB_PATH + "mad_monk_ladder" + (i + 1) + ".png");
        }
    }

}
