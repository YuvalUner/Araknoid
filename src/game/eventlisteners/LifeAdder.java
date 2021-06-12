package game.eventlisteners;

import gamegeometry.basetypes.Ball;
import gamegeometry.blockdecorators.BlockDecorator;
import objectbehavior.Counter;

public class LifeAdder implements HitListener{

    private final Counter currentBalls;
    private final Counter remainingBalls;

    public LifeAdder(Counter currentBalls, Counter remainingBalls){
        this.currentBalls = currentBalls;
        this.remainingBalls = remainingBalls;
    }


    @Override
    public void hitEvent(BlockDecorator beingHit, Ball hitter) {
        currentBalls.increase(1);
        remainingBalls.increase(1);
    }
}
