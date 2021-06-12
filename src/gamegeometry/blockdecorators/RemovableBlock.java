// Yuval Uner 322558842

package gamegeometry.blockdecorators;

import game.eventlisteners.BlockRemover;
import objectbehavior.Counter;

/**
 * @author Yuval Uner
 * <h1> A decorator for blocks that can be removed from the game</h1>
 */
public class RemovableBlock extends BasicBlockDecorator {

    /**
     * Constructor.
     * Adds the removal listener to the block, sets the block's counted value,
     * and updates the remaining blocks counter if needed.
     *
     * @param block           the block to decorate.
     * @param blockRemover    the listener which removes blocks.
     * @param counted         boolean value determining if the ball is counted towards victory
     *                        or not.
     * @param remainingBlocks the game's remaining blocks counter.
     */
    public RemovableBlock(BlockDecorator block, BlockRemover blockRemover, boolean counted,
                          Counter remainingBlocks) {
        super(block);
        block.getBlock().addHitListener(blockRemover);
        setCounted(counted);
        // If the block is counted towards victory
        if (counted) {
            remainingBlocks.increase(1);
        }
    }
}
