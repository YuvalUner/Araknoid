// Yuval Uner 322558842

package gamegeometry.basetypes;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.levels.LevelInformation;
import basicgeometry.Point;
import basicgeometry.Rectangle;
import objectbehavior.Velocity;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Yuval Uner
 * <h1> A rectangular paddle for a player to move</h1>
 * <p> The player's movable character. Allows the player to interact and
 * bounce the balls around to clear the game.</p>
 */
public class Paddle implements Sprite, Collidable {

    private final KeyboardSensor keyboard;
    private Rectangle shape;
    private final Color color;
    private final ArrayList<Rectangle> regions;
    private final LevelInformation level;
    private final int paddleSpeed;

    /**
     * Constructor.
     *
     * @param keyboard    the keyBoard sensor for the paddle
     * @param shape       the shape of the paddle
     * @param color       the paddle's color
     * @param level       the game the paddle is a part of
     * @param paddleSpeed the speed of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle shape, Color color,
                  LevelInformation level, int paddleSpeed) {
        this.keyboard = keyboard;
        this.shape = shape;
        this.color = color;
        this.regions = new ArrayList<>();
        this.level = level;
        this.paddleSpeed = paddleSpeed;
        setRegions();
    }

    /**
     * <p> Sets the regions of the paddle.</p>
     */
    private void setRegions() {
        // Empty the current list of regions each time
        if (!regions.isEmpty()) {
            regions.clear();
        }
        // Set the regions to be 5 equal parts of the paddle
        for (double i = shape.getUpperLeft().getX();
             i < shape.getUpperLeft().getX() + shape.getWidth();
             i += shape.getWidth() / 5) {
            regions.add(new Rectangle(i, shape.getUpperLeft().getY(),
                    shape.getWidth() / 5, shape.getHeight()));
        }
    }

    /**
     * Changes the x value of the paddle's current location.
     *
     * @param x the x value to change the current location to.
     */
    public void setPosition(double x) {
        this.shape = new Rectangle(x, shape.getUpperLeft().getY(), shape.getWidth(), shape.getHeight());
    }

    /**
     * <p> When the left key is pressed, moves the paddle to the left.</p>
     */
    public void moveLeft() {
        // If the paddle is hitting the left corner, stops it from going further
        if (shape.getUpperLeft().getX() - level.getEnvironment().getBorders() <= 0) {
            this.shape = new Rectangle(level.getEnvironment().getBorders(),
                    shape.getUpperLeft().getY(), shape.getWidth(), shape.getHeight());
            // Otherwise, the paddle can proceed freely at its established speed
        } else {
            this.shape = new Rectangle(shape.getUpperLeft().getX() - this.paddleSpeed,
                    shape.getUpperLeft().getY(), shape.getWidth(), shape.getHeight());
        }
        // Re-setting the regions after movement
        setRegions();
    }

    /**
     * <p> Moves the paddle to the right when the right key is pressed.</p>
     */
    public void moveRight() {
        // If the paddle is at the right border
        if (shape.getUpperLeft().getX() + shape.getWidth() + level.getEnvironment().getBorders()
                >= level.getEnvironment().getWindow().getCollisionRectangle().getWidth()) {
            this.shape = new Rectangle(level.getEnvironment().getWindow()
                    .getCollisionRectangle().getWidth()
                    - level.getEnvironment().getBorders() - shape.getWidth(),
                    shape.getUpperLeft().getY(), shape.getWidth(), shape.getHeight());
            // Otherwise, freely moves the paddle to the right at the set speed
        } else {
            this.shape = new Rectangle(shape.getUpperLeft().getX() + this.paddleSpeed,
                    shape.getUpperLeft().getY(), shape.getWidth(), shape.getHeight());
        }
        // Re-setting the regions after the paddle moved
        setRegions();
    }

    /**
     * <p> When a certain period of time passed, checks if the relevant
     * movement keys were pressed, and if so, moves the ball.</p>
     */
    @Override
    public void timePassed() {
        // Left arrow or "a" for left movement
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) || keyboard.isPressed("a")) {
            moveLeft();
            // Right arrow or "d" for right movement
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) || keyboard.isPressed("d")) {
            moveRight();
        }
    }

    /**
     * <p> Draws the paddle on the screen.</p>
     *
     * @param d the DrawSurface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) shape.getUpperLeft().getX(),
                (int) shape.getUpperLeft().getY(),
                (int) shape.getWidth(), (int) shape.getHeight());
    }

    /**
     * @return returns the shape of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return shape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        /*
         * Checks over each region, and finds which was hit, by checking if the
         * collision point is within them, then adjusts the velocity
         * according to the region which was hit.
         * */
        for (int i = 0; i < regions.size(); i++) {
            if (regions.get(i).isPointInsideRectangle(collisionPoint)) {
                switch (i) {
                    case 0:
                        return Velocity.fromAngleAndSpeed(300, Ball.DEFAULT_SPEED);
                    case 1:
                        return Velocity.fromAngleAndSpeed(330, Ball.DEFAULT_SPEED);
                    case 2:
                        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                    case 3:
                        return Velocity.fromAngleAndSpeed(30, Ball.DEFAULT_SPEED);
                    case 4:
                        return Velocity.fromAngleAndSpeed(60, Ball.DEFAULT_SPEED);
                    default:
                        break;
                }
            }
        }
        // Default value, should only happen due to an unforeseen bug.
        return currentVelocity;
    }

    @Override
    public void addToLevel(LevelInformation levelInformation) {
        levelInformation.addCollidable(this);
        levelInformation.addSprite(this);
    }

    @Override
    public void removeFromLevel(LevelInformation levelInformation) {
    }
}
