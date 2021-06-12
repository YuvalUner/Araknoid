// Yuval Uner 322558842

package gamegeometry.blockdecorators;

import game.eventlisteners.ScoreTrackingListener;

/**
 * @author Yuval Uner 322558842
 * <h1> A decorator for blocks that modify the player's score during the game</h1>
 */
public class ScoredBlock extends BasicBlockDecorator {

    /**
     * Constructor.
     *
     * @param block        the block (or blockDecorator) to decorate
     * @param scoreTracker the game's score tracking listener
     * @param score        the score assigned to the block. Can be negative, for creating
     *                     blocks that reduce the player's score.
     */
    public ScoredBlock(BlockDecorator block, ScoreTrackingListener scoreTracker, int score) {
        super(block);
        getBlock().addHitListener(scoreTracker);
        getBlock().setScoreValue(score);
    }
}
