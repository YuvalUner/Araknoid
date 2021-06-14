// Yuval Uner 322558842

package gamegeometry.blockdecorators;

import biuoop.DrawSurface;
import game.eventlisteners.HitListener;
import game.eventlisteners.HitNotifier;
import game.levels.LevelInformation;
import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.Collidable;
import gamegeometry.basetypes.Sprite;
import objectbehavior.Velocity;
import gamegeometry.basicgeometry.Point;
import gamegeometry.basicgeometry.Rectangle;

import java.util.List;

/**
 * @author Yuval Uner
 * <h1> The basic abstract block decorator, defining the base behaviors of all
 * the decorators which inherit from it</h1>
 * <p> Implements the basic interfaces for block, while delegating most
 * of their implementation to the already created block.</p>
 */
public abstract class BasicBlockDecorator implements Collidable, Sprite,
        HitNotifier, BlockDecorator {

    private final BlockDecorator block;
    /*
     * Removable blocks will initialize this value.
     * Placed here instead of in RemovableBlock class to not restrict the
     * order of decorators, as placing it there forces RemovableBlock to always be
     * the outermost decorator for this to work.
     * */
    private boolean counted;

    /**
     * Constructor.
     * Sets the block value of this class, and sets the decorator value of the
     * block that is being decorated.
     *
     * @param block the BlockDecorator that was used.
     */
    public BasicBlockDecorator(BlockDecorator block) {
        this.block = block;
        block.getBlock().setDecorator(this);
    }

    /**
     * @return the counted value of the decorated block.
     */
    public boolean isCounted() {
        return counted;
    }

    /**
     * @param newVal the value to set counted to
     */
    public void setCounted(boolean newVal) {
        this.counted = newVal;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return block.getBlock().getCollisionRectangle();
    }

    @Override
    public Block getBlock() {
        return this.block.getBlock();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        return block.getBlock().hit(hitter, collisionPoint, currentVelocity);
    }

    /**
     * <p> Adds the current BlockDecorator as both a sprite and a collidable to the game.</p>
     *
     * @param level the level to add the block to
     */
    @Override
    public void addToLevel(LevelInformation level) {
        level.addCollidable(this);
        level.addSprite(this);
    }

    /**
     * <p> Draws the block on the screen, according to its color and
     * dimensions First draws the block itself, then any additional effects
     * the decorators gave it.</p>
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        block.getBlock().drawOn(d);
        block.drawOn(d);
    }

    /***
     * Currently does nothing.
     */
    @Override
    public void timePassed() {

    }

    @Override
    public int getScoreValue() {
        return block.getScoreValue();
    }

    @Override
    public List<HitListener> getHitListeners() {
        return block.getHitListeners();
    }

    @Override
    public void removeFromLevel(LevelInformation level) {
        // Removes the decorator from the game
        level.removeCollidable(this);
        level.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        block.getBlock().addHitListener(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        block.getBlock().addHitListener(hl);
    }
}
