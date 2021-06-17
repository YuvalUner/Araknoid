// Yuval Uner 322558842

package objectbehavior;

import basicgeometry.Point;

/**
 * @author Yuval Uner
 * <h1> Describes an object's change rate in the x and y axis </h1>
 * <p> A standard velocity vector, used for the movement of an object on a 2d space.</p>
 */
public class Velocity {

    private final double dx;
    private final double dy;
    // Because we are to assume 0 degrees is up, a modifier is needed
    private static final double DEGREE_MODIFIER = 90;

    /**
     * Constructor.
     *
     * @param dx the dx value of the velocity.
     * @param dy the dy value of the velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return returns the velocity's dx value.
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return returns the velocity's dy value.
     */
    public double getDy() {
        return dy;
    }

    /**
     * @param angle the angle the velocity vector will take.
     * @param speed the size of the velocity vector.
     * @return returns a new Velocity object, with it's dx and dy values
     * being defined by angle and speed.
     * <p>
     * Calculates the appropriate dx and dy values according to the angle
     * and speed given, then creates a new Velocity object accordingly.
     * </p>
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle - DEGREE_MODIFIER)) * speed;
        double dy = Math.sin(Math.toRadians(angle - DEGREE_MODIFIER)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @param p the base point.
     * @return returns a new point, with values of (p.x + dx, p.y + dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}