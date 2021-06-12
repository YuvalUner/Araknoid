// Yuval Uner 322558842

package game.eventlisteners;

import game.levels.LevelInformation;
import gamegeometry.blockdecorators.BlockDecorator;
import objectbehavior.Counter;
import game.levels.GameLevel;
import gamegeometry.basetypes.Ball;
import objectbehavior.Velocity;

import java.awt.Color;

/**
 * @author Yuval Uner
 * <h1> A listener for adding balls to the game upon hit</h1>
 */
public class BallAdder implements HitListener {

    private final LevelInformation game;
    private final Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param level           the level the listener corresponds to.
     * @param remainingBalls the level's remainingBalls counter.
     */
    public BallAdder(LevelInformation level, Counter remainingBalls) {
        this.game = level;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(BlockDecorator beingHit, Ball hitter) {
        // Heading a ball heading downward from the location the block was at.
        Ball newBall = new Ball((int) (beingHit.getCollisionRectangle().getUpperLeft().getX()
                + beingHit.getCollisionRectangle().getWidth() / 2),
                (int) (beingHit.getCollisionRectangle().getUpperLeft().getY()
                        + beingHit.getCollisionRectangle().getHeight() / 2), Ball.DEFAULT_RADIUS, Color.BLACK);
        newBall.setEnvironment(game.getEnvironment());
        newBall.setVelocity(Velocity.fromAngleAndSpeed(180, Ball.DEFAULT_SPEED));
        remainingBalls.increase(1);
        newBall.addToGame(game);
    }
}