package com.dyga.Engine.Source.Components.Physics;

import com.dyga.Engine.Source.Components.Component;
import com.dyga.Engine.Source.Utils.Math.Position2D;

public class Transform extends Component {

    private Position2D position;
    private Position2D scale;
    private Position2D rotation;

    public Transform() {
        this(new Position2D(0,0), new Position2D(1,1), new Position2D(0,0));
    }

    public Transform(Position2D position) {
        this(position, new Position2D(1, 1), new Position2D(0, 0));
    }

    public Transform(Position2D position, Position2D scale) {
        this(position, scale, new Position2D(0,0));
    }

    public Transform(Position2D position, Position2D scale, Position2D rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }

    public Position2D getPosition() {
        return position;
    }

    public void setPosition(Position2D position) {
        this.position = position;
    }

    public Position2D getScale() {
        return this.scale;
    }

    public void setScale(Position2D scale) {
        this.scale = scale;
    }

    public Position2D getRotation() {
        return this.rotation;
    }

    public void setRotation(Position2D rotation) {
        this.rotation = rotation;
    }
}
