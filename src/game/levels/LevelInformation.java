// Yuval Uner 322558842

package game.levels;

import game.gameessentials.Background;
import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.Collidable;
import gamegeometry.basetypes.Sprite;
import objectbehavior.Counter;
import objectbehavior.Velocity;

import java.util.List;

/**
 * @author Yuval Uner
 * <h1> An interface describing the base behavior of all classes which store
 * information about the different levels of the game.</h1>
 */
public interface LevelInformation {

    /**
     * @return the amount of balls that the level should have initially.
     */
    int numberOfBalls();

    /**
     * @return a list containing the initial velocities of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed value of the paddle for the specific level.
     */
    int paddleSpeed();

    /**
     * @return the width value of the paddle for the specific level.
     */
    int paddleWidth();

    /**
     * @return the level's name.
     */
    String levelName();

    /**
     * @return the level's background object.
     */
    Background getBackground();

    /**
     * @return the list of blocks the level uses.
     */
    List<Block> blocks();

    /**
     * @return the amount of blocks that need to be removed to win the level.
     */
    int numberOfBlocksToRemove();

    /**
     * @return the level's environment.
     */
    GameEnvironment getEnvironment();

    /**
     * Adds a sprite to the level.
     *
     * @param s the sprite to add.
     */
    void addSprite(Sprite s);

    /**
     * Removes a sprite from the level.
     *
     * @param s the sprite to remove.
     */
    void removeSprite(Sprite s);

    /**
     * @return the level's sprite collection.
     */
    SpriteCollection getSprites();

    /**
     * Adds a collidable to the level.
     *
     * @param c the collidable to add.
     */
    void addCollidable(Collidable c);

    /**
     * Removes a collidable from the level.
     *
     * @param c the collidable to remove.
     */
    void removeCollidable(Collidable c);

    /**
     * @return the counter for the balls currently on screen.
     */
    Counter getRemainingBalls();

    /**
     * @return the counter for how many blocks still remain in the level.
     */
    Counter getRemainingBlocks();

}