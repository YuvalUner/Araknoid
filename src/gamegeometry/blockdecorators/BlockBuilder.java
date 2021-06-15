// Yuval Uner 322558842

package gamegeometry.blockdecorators;

import game.eventlisteners.BallAdder;
import game.eventlisteners.BallRemover;
import game.eventlisteners.BlockRemover;
import game.eventlisteners.LifeAdder;
import gamegeometry.basetypes.Block;
import objectbehavior.Counter;

import java.awt.*;

/**
 * @author Yuval Uner
 * <h1> A builder for several types of commonly used block decorator combinations</h1>
 * <p> Can build standard life blocks, standard kill blocks and standard scored blocks.</p>
 */
public class BlockBuilder {

    /**
     * @param x               the block's initial x value.
     * @param y               the block's initial y value.
     * @param height          the block's height.
     * @param width           the block's width.
     * @param lifeAdder       the listener for the addition of balls to the game.
     * @param blockRemover    the listener for removing the block upon hit.
     * @param remainingBlocks the game's remainingBlocks counter.
     * @return returns a new removable uncounted life block with standard green
     * color and standard "Life Block" text to it.
     */
    public static LifeBlock standardLifeBlock(double x, double y,
                                              double width, double height,
                                              BlockRemover blockRemover,
                                              LifeAdder lifeAdder,
                                              Counter remainingBlocks) {
        return new LifeBlock(new RemovableBlock(new BlockWithText(
                new Block(x, y, width, height, Color.green), "Life Block",
                Color.black, 11, x, y + height / 1.5),
                blockRemover, false, remainingBlocks), lifeAdder);
    }

    /**
     * @param x               the block's initial x value.
     * @param y               the block's initial y value.
     * @param height          the block's height.
     * @param width           the block's width.
     * @param ballRemover     the listener for the removal of balls to the game.
     * @param blockRemover    the listener for removing the block upon hit.
     * @param remainingBlocks the game's remainingBlocks counter.
     * @return returns a new removable uncounted kill block with standard black
     * color and standard "Kill Block" text to it.
     */
    public static KillBlock standardKillBlock(double x, double y, double width,
                                              double height, BlockRemover blockRemover,
                                              BallRemover ballRemover,
                                              Counter remainingBlocks) {
        return new KillBlock(new RemovableBlock(new BlockWithText(new Block(x,
                y, width, height, Color.black), "Kill Block",
                Color.white, 11, x, y + height / 1.5),
                blockRemover, false, remainingBlocks), ballRemover);
    }

    public static BallAddingBlock standardBallAddingBlock(double x, double y, double width,
                                                          double height, BlockRemover blockRemover,
                                                          BallAdder ballAdder,
                                                          Counter remainingBlocks) {
        return new BallAddingBlock(new RemovableBlock(new BlockWithText(new Block(x,
                y, width, height, Color.magenta), "Extra Ball", Color.black, 11, x,
                y + height / 1.5), blockRemover, false, remainingBlocks), ballAdder);
    }

    public static KillBlock standardNonRemovableKillBlock(double x, double y, double width,
                                                          double height, BallRemover ballRemover) {
        return new KillBlock(new BlockWithText(new Block(x, y, width, height, Color.black),
                "Kill Block", Color.white, 11, x, y + height / 1.5), ballRemover);
    }
}
