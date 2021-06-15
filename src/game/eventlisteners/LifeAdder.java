package game.eventlisteners;

import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import objectbehavior.Counter;

public class LifeAdder implements HitListener {

    private final Counter remainingLives;

    public LifeAdder(Counter remainingLives) {
        this.remainingLives = remainingLives;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingLives.increase(1);
    }
}
