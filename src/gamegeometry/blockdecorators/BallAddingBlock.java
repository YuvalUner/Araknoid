// Yuval Uner 322558842

package gamegeometry.blockdecorators;

import game.eventlisteners.BallAdder;

/**
 * @author Yuval Uner.
 * <h1> A decorator for blocks that add a ball to the game upon hit.</h1>
 * */
public class BallAddingBlock extends BasicBlockDecorator {

    /**
     * Constructor.
     * Adds the correct listener to the block.
     *
     * @param block     the block to decorate.
     * @param ballAdder the ballAdder listener to add to the block.
     */
    public BallAddingBlock(BlockDecorator block, BallAdder ballAdder) {
        super(block);
        block.getBlock().addHitListener(ballAdder);
    }
}
