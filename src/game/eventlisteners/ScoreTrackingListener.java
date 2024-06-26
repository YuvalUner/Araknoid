// Yuval Uner 322558842

package game.eventlisteners;

import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import objectbehavior.Counter;

/**
 * @author Yuval Uner
 * <h1> A hit listener for keeping track of the score throughout a game</h1>
 * <p> Contains a constructor which accepts an instance of Counter, and a method to
 * adjust the counter by the point value assigned to the block hit</p>
 */
public class ScoreTrackingListener implements HitListener {

    private final Counter scoreCounter;

    /**
     * Constructor.
     *
     * @param scoreCounter the score counter for the listener to update
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * @return returns the counter the listener uses.
     */
    public Counter getScoreCounter() {
        return scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Updates the score counter by the block's point value. Defaults to 5 if not changed.
        scoreCounter.increase(beingHit.getScoreValue());
    }
}
