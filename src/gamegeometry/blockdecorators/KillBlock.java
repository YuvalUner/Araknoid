// Yuval Uner 322558842

package gamegeometry.blockdecorators;

import game.eventlisteners.BallRemover;

/**
 * @author Yuval Uner
 * <h1> A decorator for blocks that kill the player (remove balls from the game)</h1>
 */
public class KillBlock extends BasicBlockDecorator {

    /**
     * Constructor.
     * Adds the listener to the decorated block.
     *
     * @param block       the block to decorate
     * @param ballRemover the listener for ball removal
     */
    public KillBlock(BlockDecorator block, BallRemover ballRemover) {
        super(block);
        getBlock().addHitListener(ballRemover);
    }
}
