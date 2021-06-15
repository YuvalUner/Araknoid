// Yuval Uner 322558842

package game.eventlisteners;

import game.levels.LevelInformation;
import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import objectbehavior.Counter;

import java.util.ArrayList;

/**
 * @author Yuval Uner 322558842
 * <h1> A listener for removing blocks when they are hit</h1>
 * <p> Contains a constructor, as well as a method to remove the hit blocks</p>
 */
public class BlockRemover implements HitListener {

    private final LevelInformation level;
    private final Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param level         the level the blocks belong to, nessecary for block removal
     * @param removedBlocks the counter keeping track of how many blocks are
     *                      still in the game
     */
    public BlockRemover(LevelInformation level, Counter removedBlocks) {
        this.level = level;
        this.remainingBlocks = removedBlocks;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.getDecorator().removeFromLevel(level);
        /*
         * Some special blocks may not need to be hit to win the game, and they
         * are not counted by the block counter when added.
         * Therefore, they need to not decrement the counter when removed either.
         * */
        if (beingHit.getDecorator().isCounted()) {
            remainingBlocks.decrease(1);
        }
        /*
         * Removing all listeners from the blocks, as some special blocks can
         * cause issues if not removed right away (since the list that is being
         * looped over is a copy of the sprite list, and not the sprite list itself).
         * */
        ArrayList<HitListener> hitListeners = new ArrayList<>(beingHit.getHitListeners());
        for (HitListener hitListener : hitListeners) {
            beingHit.removeHitListener(hitListener);
        }
    }
}
