// Yuval Uner 322558842

package gamegeometry.basetypes;

import game.levels.GameLevel;
import game.levels.LevelInformation;
import gamegeometry.basicgeometry.Point;
import gamegeometry.basicgeometry.Line;
import gamegeometry.basicgeometry.Rectangle;
import biuoop.DrawSurface;
import game.gameessentials.GameEnvironment;
import objectbehavior.CollisionInfo;
import objectbehavior.Velocity;


import java.awt.Color;

/**
 * @author Yuval Uner
 * <h1> A ball with a center and radius in 2d space </h1>
 * <p>
 * A class describing a ball in a 2d space, represented as a starting
 * point and a radius, as well as the ball's color.
 * In addition, also stores the starting point of the ball, the velocity
 * of the ball, and the frame the ball is restricted to (the 2d grid).
 * Contains the following public methods:
 * 1. Constructors
 * 2. Getters for the radius, the center point's x and y values,
 * the ball's color and it's velocity.
 * 3. setEnvironmentFrame - a method to define an Environment frame for the ball
 * , the borders of the screen it is on, via either an already created frame
 * object or 4 int values. If none is defined via this method, the ball will
 * have a default frame, of a square from (0,0) to (200,200).
 * 4. setVelocity - a method to set the velocity of the ball, via either
 * an already created velocity object or 2 double values.
 * 5. timePassed - a method for moving the center of the ball one step, according
 * to the velocity of the ball. Will move until the nearest collision point.
 * 6. drawOn - draws the ball on a drawsurface, according to it's center,
 * color and radius.
 * 7. addToGame - adds the ball to a game.
 * </p>
 */
public class Ball implements Sprite {

    private Point center;
    private final int r;
    private final Color color;
    private Velocity velocity;
    private GameEnvironment environment;
    private Line trajectory;
    private CollisionInfo closestCollision;
    public static final int DEFAULT_SPEED = 8;
    public static final int DEFAULT_RADIUS = 10;

    /**
     * Constructor.
     *
     * @param center the center point of the ball.
     * @param r      the ball's radius.
     * @param color  the ball's color.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructor.
     * <p>
     * Calls the previous constructor with a point created by an x and y
     * value pair.
     * </p>
     *
     * @param x     the center's x value.
     * @param y     the center's y value.
     * @param r     the ball's radius.
     * @param color the ball's color.
     */
    public Ball(int x, int y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * @return returns the center's x value as an int.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return returns the center's y value as an int.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return returns the ball's radius.
     */
    public int getSize() {
        return r;
    }

    /**
     * @return returns the ball's color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return returns the ball's velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * A setter for the velocity.
     * Also calls setTrajectory and setClosestCollision once the velocity
     * is determined.
     *
     * @param v the velocity to set the ball's velocity to.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
        setTrajectory();
        setClosestCollision();
    }

    /**
     * A setter for environment.
     *
     * @param gameEnvironment the gameEnvironment of the ball
     */
    public void setEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * <p>
     * Sets the ball's velocity to be a new velocity, determined by x and y
     * values.
     * </p>
     *
     * @param dx the velocity's dx value.
     * @param dy the velocity's dy value.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
        setTrajectory();
        setClosestCollision();
    }

    /**
     * <p> A method for setting the trajectory of the ball.
     * It sets the trajectory to be a pseudo infinite line starting
     * from the center, according to the velocity</p>
     */
    private void setTrajectory() {
        Rectangle window = environment.getWindow().getCollisionRectangle();
        // Artificially moving one step to avoid infinite loops
        // this.center = velocity.applyToPoint(center);
        if (center.getX() < 0) {
            center = new Point(0, center.getY());
        } else if (center.getX() > window.getWidth()) {
            center = new Point(window.getWidth(),
                    center.getY());
        }
        if (center.getY() < 0) {
            center = new Point(center.getX(), 0);
        } else if (center.getY() > window.getHeight()) {
            center = new Point(center.getX(),
                    window.getHeight());
        }
        /*
         * Trajectory should be acting as an infinite line, with only the
         * slope (the velocity vector) and its starting point mattering.
         * */
        double addedDx = center.getX() + Math.pow(10, 99) * velocity.getDx();
        double addedDy = center.getY() + Math.pow(10, 99) * velocity.getDy();
        trajectory = new Line(center, new Point(addedDx, addedDy));
    }

    /**
     * <p> A method for setting the closest collision point.</p>
     */
    private void setClosestCollision() {
        this.closestCollision = environment.getClosestCollision(trajectory);
    }

    /**
     * <p>
     * Draws the ball on a drawSurface, according to it's center point,
     * radius and color.
     * </p>
     *
     * @param d the drawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillCircle((int) center.getX(), (int) center.getY(), r);
    }

    /**
     * When a certain period of time passed, moves the ball one step.
     * If a collision occurred, use the collision object's hit method to change
     * the ball's velocity.
     */
    @Override
    public void timePassed() {
        // In case the collision is null, which may happen with newly spawned balls.
        if (closestCollision == null) {
            setTrajectory();
            setClosestCollision();
        }
        /*
         * Flooring to make the collisions look smoother, as it makes the ball
         * look less like it's "entering" the collision object when the step
         * takes it slightly inside.
         * */
        if (!(Math.floor(this.center.distance(closestCollision.collisionPoint())) > r)) {
            velocity = closestCollision.collisionObject().hit(this, closestCollision.collisionPoint(),
                    velocity);
        }
        this.center = velocity.applyToPoint(center);
        setTrajectory();
        setClosestCollision();
    }

    /**
     * <p> A method to add the current ball to a game.</p>
     *
     * @param level the level to add the ball to
     */
    @Override
    public void addToGame(LevelInformation level) {
        level.addSprite(this);
    }

    @Override
    public void removeFromGame(LevelInformation level) {
        level.removeSprite(this);
    }
}
