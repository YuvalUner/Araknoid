//Yuval Uner 322558842

package objectbehavior;

import gamegeometry.basetypes.Collidable;
import gamegeometry.basicgeometry.Point;

/**
 * @author Yuval Uner
 * <h1> A class with the info of a collision in 2d space</h1>
 * <p> Contains the following public methods:
 * 1. Constructor
 * 2. Getters for collisionPoint and collisionObject</p>
 */
public class CollisionInfo {

    private final Point collisionPoint;
    private final Collidable collidable;

    /**
     * Constructor.
     *
     * @param collisionPoint the point at which the collision occurred
     * @param collidable     the collidable object the collision occurred with
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collidable;
    }
}
