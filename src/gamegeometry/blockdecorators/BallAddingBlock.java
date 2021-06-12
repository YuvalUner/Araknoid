package gamegeometry.blockdecorators;

import game.eventlisteners.BallAdder;

public class BallAddingBlock extends BasicBlockDecorator{
    /**
     * Constructor.
     * Sets the block value of this class, and sets the decorator value of the
     * block that is being decorated.
     *
     * @param block the BlockDecorator that was used.
     */
    public BallAddingBlock(BlockDecorator block, BallAdder ballAdder) {
        super(block);
        block.getBlock().addHitListener(ballAdder);
    }
}
