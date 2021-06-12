// Yuval Uner 322558842

package gamegeometry.blockdecorators;

import game.eventlisteners.BallAdder;
import game.eventlisteners.LifeAdder;

/**
 * @author Yuval Uner
 * <h1> A decorator for blocks that add lives (balls) to the player</h1>
 */
public class LifeBlock extends BasicBlockDecorator {

    /**
     * Constructor.
     * Adds the lifeAdder listener to the decorated block.
     *
     * @param block     the block to decorate.
     * @param lifeAdder the listener which adds lives on hit.
     */
    public LifeBlock(BlockDecorator block, LifeAdder lifeAdder) {
        super(block);
        getBlock().addHitListener(lifeAdder);
    }
}
