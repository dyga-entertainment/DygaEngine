package Source.MVC.View.Game;

import javax.swing.*;
import com.dyga.Engine.Source.MVC.Model.Game.EntityModel;
import com.dyga.Engine.Source.Utils.Math.Position2D;
import java.awt.*;

public class EntityView extends JComponent {

    EntityModel entityModel;

    public EntityView(){
        this.entityModel = new EntityModel();
    }

    public EntityView(EntityModel entityModel){
        this.entityModel = entityModel;
    }

    public void paint(Graphics g, Container im) {
        Position2D position = this.entityModel.getPosition();
        g.drawImage(this.entityModel.getSprite(), (int)position.getX(), (int)position.getY(), 50, 50, im) ;
    }
}
