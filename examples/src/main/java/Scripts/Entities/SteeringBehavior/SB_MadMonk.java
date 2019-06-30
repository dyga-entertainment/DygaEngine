package Scripts.Entities.SteeringBehavior;

import com.dyga.Engine.Source.Components.Gameplay.GameplayScript;
import com.dyga.Engine.Source.Components.Physics.Transform;
import com.dyga.Engine.Source.Utils.Math.Position2D;

public class SB_MadMonk extends GameplayScript {

    @Override
    public void start() {
        System.out.println("Madmonk start");
    }

    @Override
    public void update() {
        Transform transform = this.entity.getComponent(Transform.class);

        Position2D currentPosition = transform.getPosition();
        Position2D newPosition = new Position2D(currentPosition.getX() + 2, currentPosition.getY());
        transform.setPosition(newPosition);

        // todo should trigger controller method here
    }

}
