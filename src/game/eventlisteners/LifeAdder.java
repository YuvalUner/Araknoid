// Yuval Uner 322558842

package game.eventlisteners;

import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import objectbehavior.Counter;

/**
 * @author Yuval Uner
 * <h1> A hit listener for adding lives to the player upon hitting a block </h1>
 */
public class LifeAdder implements HitListener {

    private final Counter remainingLives;

    /**
     * Constructor.
     *
     * @param remainingLives the player's remaining lives counter.
     */
    public LifeAdder(Counter remainingLives) {
        this.remainingLives = remainingLives;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingLives.increase(1);
    }
}
