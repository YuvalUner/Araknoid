// Yuval Uner 322558842

package game.gameessentials;

import biuoop.DrawSurface;
import game.levels.GameLevel;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.Collidable;
import gamegeometry.basicgeometry.Line;
import gamegeometry.basicgeometry.Point;
import objectbehavior.CollisionInfo;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Yuval Uner
 * <h1> A class for a game enviornment that contains collidable objects within it</h1>
 * <p>Contains the following public methods:
 * 1. addCollidable - adds a collidable object to the game enviornment
 * 2. getClosestCollision - finds the closest collision to an object, by the
 * trajectory of the object</p>
 * 3. Getters for borders and window
 * 4. drawAllOn - a method to draw all collidable objects on the screen
 */
public class GameEnvironment {

    private ArrayList<Collidable> collidables;
    private final Block window;
    private final double borders;

    /**
     * Constructor.
     *
     * @param window      - the window the game environment is set in
     * @param borders     the size of the screen's borders.
     *                    Set to 0 or negative for no visible borders (window
     *                    will act as the screen's borders).
     * @param borderColor the color of the borders
     */
    public GameEnvironment(Block window, double borders, Color borderColor) {
        collidables = new ArrayList<>();
        this.window = window;
        if (borders < 0) {
            borders = 0;
        }
        this.borders = borders;
        setBorders(borderColor);
    }

    /**
     * @return returns the size of the environment's borders
     */
    public double getBorders() {
        return borders;
    }

    /**
     * @return returns the game environment's window.
     */
    public Block getWindow() {
        return window;
    }

    /**
     * <p> A method for setting the 4 borders of the game, if they
     * are made to be visible.</p>
     *
     * @param borderColor the color of the borders
     */
    private void setBorders(Color borderColor) {
        // Sets the borders for the top, left and right.
        Block borderBlockLeft = new Block(0, GameLevel.SCORE_INDICATOR_HEIGHT, borders,
                window.getCollisionRectangle().getHeight(), borderColor);
        Block borderBlockRight = new Block(window.getCollisionRectangle().getWidth() - borders,
                GameLevel.SCORE_INDICATOR_HEIGHT,
                borders, window.getCollisionRectangle().getHeight(), borderColor);
        Block borderBlockTop = new Block(borders, GameLevel.SCORE_INDICATOR_HEIGHT,
                window.getCollisionRectangle().getWidth() - borders, borders, borderColor);
        addCollidable(borderBlockLeft);
        addCollidable(borderBlockRight);
        addCollidable(borderBlockTop);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * @param trajectory the straight line trajectory of an object
     * @return returns the collision info of the object, if any exists,
     * for its nearest collision.
     * <p>
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * </p>
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<CollisionInfo> collisions = new ArrayList<>();
        /*
         * Gets all the collisions of the object with every collidable object
         * in the game environment.
         * */
        for (Collidable collidable : collidables) {
            Point collisionPoint =
                    trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collisionPoint != null) {
                collisions.add(new CollisionInfo(collisionPoint, collidable));
            }
        }
        if (collisions.isEmpty()) {
            return null;
            /*
             * If there are collisions, check which is closest to
             * to our object.
             * */
        } else {
            Point minDist = collisions.get(0).collisionPoint();
            Collidable collisionObject = collisions.get(0).collisionObject();
            for (int i = 1; i < collisions.size(); i++) {
                if (minDist.distance(trajectory.start())
                        > collisions.get(i).collisionPoint().distance(trajectory.start())) {
                    minDist = collisions.get(i).collisionPoint();
                    collisionObject = collisions.get(i).collisionObject();
                }
            }
            // Return the closest collision and the shape of the collidable object
            return new CollisionInfo(minDist, collisionObject);
        }
    }


    /**
     * <p> Draws all the collidable objects in the environment on the screen.</p>
     *
     * @param d the DrawSurface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Collidable> collidableList = new ArrayList<>(collidables);
        for (Collidable collidable : collidableList) {
            collidable.drawOn(d);
        }
    }

    /**
     * Removes a collidable from the environment.
     *
     * @param c the collidable to remove.
     */
    public void remove(Collidable c) {
        collidables.remove(c);
    }

}