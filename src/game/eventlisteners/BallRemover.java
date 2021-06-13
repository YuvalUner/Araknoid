// Yuval Uner 322558842

package game.eventlisteners;

import game.levels.GameLevel;
import game.levels.LevelInformation;
import gamegeometry.basetypes.Block;
import gamegeometry.blockdecorators.BlockDecorator;
import objectbehavior.Counter;
import gamegeometry.basetypes.Ball;

/**
 * @author Yuval Uner
 * <h1> A listener for removing balls when certain blocks are hit by that ball</h1>
 * <p> Contains a constructor, and a method to remove the ball that hit the block</p>
 */
public class BallRemover implements HitListener {

    private final LevelInformation level;
    private final Counter removedBalls;

    /**
     * Constructor.
     *
     * @param level         the level that is being run
     * @param removedBalls the counter keeping track of how many balls are left
     */
    public BallRemover(LevelInformation level, Counter removedBalls) {
        this.level = level;
        this.removedBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Removes the ball from the game and decrements the ball counter
        if (level.getSprites().getSpriteList().contains(hitter)) {
            removedBalls.decrease(1);
            hitter.removeFromGame(level);
        }
    }
}
