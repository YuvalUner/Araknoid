// Yuval Uner 322558842

package gamegeometry.basetypes;

import gamegeometry.basicgeometry.Rectangle;
import gamegeometry.basicgeometry.Point;
import objectbehavior.Velocity;

/**
 * @author Yuval Uner
 * <h1> An interface for collidable objects</h1>
 * <p>Used to describe objects that can be collided with.
 * Enforces the creation of methods:
 * 1. getCollisionRectangle - gets the "shape" of the collidable objects (rectangular)
 * 2. hit - changes the velocity of an object that collided with a collidable
 * object, based on the force the object inflicted</p>
 */
public interface Collidable extends GameObject {

    /**
     * @return returns the "collidable shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint  the point at which the two objects collided
     * @param currentVelocity the current velocity of the object
     * @param hitter          the ball that hit the collidable.
     * @return the new velocity expected after the hit
     * <p>Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).</p>
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}