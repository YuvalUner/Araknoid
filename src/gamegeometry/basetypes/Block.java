// Yuval Uner 322558842

package gamegeometry.basetypes;

import biuoop.DrawSurface;
import game.eventlisteners.HitListener;
import game.eventlisteners.HitNotifier;
import game.levels.LevelInformation;
import basicgeometry.Line;
import basicgeometry.Point;
import basicgeometry.Rectangle;
import gamegeometry.blockdecorators.BlockDecorator;
import objectbehavior.Velocity;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Yuval Uner
 * <h1> A collidable block in 2d space </h1>
 * <p> The Block class can act as either a static object in the game, or it can
 * act as a collidable, as well as have many possible attributes possible via
 * the various decorators made for the class. Most blocks are wrapped
 * with RemovableBlock.</p>
 */
public class Block implements Collidable, Sprite, HitNotifier, BlockDecorator {

    private final Rectangle shape;
    private final Color color;
    private final ArrayList<HitListener> hitListeners;
    private int scoreValue;
    private BlockDecorator decorator;
    public static final int DEFAULT_SCORE = 5;

    /**
     * Constructor.
     *
     * @param shape the shape of the block (currently always a rectangle)
     * @param color the block's color.
     */
    public Block(Rectangle shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param x      the x value of the upper left corner
     * @param y      the y value of the upper left corner
     * @param width  the width of the block
     * @param height the height of the block
     * @param color  the block's color
     */
    public Block(double x, double y, double width, double height, Color color) {
        this(new Rectangle(x, y, width, height), color);
    }

    /**
     * Sets the decorator field.
     *
     * @param newDecorator the decorator to set the field as.
     */
    public void setDecorator(BlockDecorator newDecorator) {
        this.decorator = newDecorator;
    }

    /**
     * @return the block's decorator.
     */
    public BlockDecorator getDecorator() {
        return decorator;
    }

    /**
     * @return the block's assigned score value.
     */
    public int getScoreValue() {
        return scoreValue;
    }

    /**
     * @param newValue the score value to give the block.
     */
    public void setScoreValue(int newValue) {
        this.scoreValue = newValue;
    }

    /**
     * @return returns the shape of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return shape;
    }

    /**
     * @return returns the color of the block.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the list of the block's hit listeners.
     */
    public ArrayList<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * <p> Notifies all the hit listeners that the block was hit.</p>
     *
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null) {
            return currentVelocity;
        }
        // Getting the 2 lines of the rectangle
        Line rectangleLeft = shape.getLeftLine();
        Line rectangleRight = shape.getRightLine();
        // Checking intersection with a corner
        if (collisionPoint.equals(rectangleLeft.start())
                || collisionPoint.equals(rectangleLeft.end())
                || collisionPoint.equals(rectangleRight.start())
                || collisionPoint.equals(rectangleRight.end())) {
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // Checking intersection with the horizontal edges
        if (rectangleLeft.isPointOnLine(collisionPoint)
                || rectangleRight.isPointOnLine(collisionPoint)) {
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            // Intersecting with the vertical edges
        } else {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }

    /**
     * <p> Adds the current block as both a sprite and a collidable to the game.</p>
     *
     * @param level the level to add the block to
     */
    @Override
    public void addToLevel(LevelInformation level) {
        level.addSprite(this);
        level.addCollidable(this);
    }

    /**
     * <p> Draws the rectangle on the screen, according to its color and
     * dimensions.</p>
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) shape.getUpperLeft().getX(),
                (int) shape.getUpperLeft().getY(),
                (int) shape.getWidth(), (int) shape.getHeight());
    }

    /***
     * Currently does nothing.
     */
    @Override
    public void timePassed() {

    }

    @Override
    public void removeFromLevel(LevelInformation level) {
        level.removeCollidable(this);
        level.removeSprite(this);
    }

    @Override
    public boolean isCounted() {
        return true;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override
    public Block getBlock() {
        return this;
    }
}
