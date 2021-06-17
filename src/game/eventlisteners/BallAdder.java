// Yuval Uner 322558842

package game.eventlisteners;

import game.levels.LevelInformation;
import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import objectbehavior.Counter;
import objectbehavior.Velocity;

import java.awt.Color;

/**
 * @author Yuval Uner
 * <h1> A listener for adding balls to the game upon hit</h1>
 * <p> Any blocks with this listener will add a ball to the game, heading downwards
 * from the position of the block.</p>
 */
public class BallAdder implements HitListener {

    private final LevelInformation game;
    private final Counter currentBalls;

    /**
     * Constructor.
     *
     * @param level        the level the listener corresponds to.
     * @param currentBalls the level's remainingBalls counter.
     */
    public BallAdder(LevelInformation level, Counter currentBalls) {
        this.game = level;
        this.currentBalls = currentBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Adding a ball heading downward from the location the block was at.
        Ball newBall = new Ball((int) (beingHit.getCollisionRectangle().getUpperLeft().getX()
                + beingHit.getCollisionRectangle().getWidth() / 2),
                (int) (beingHit.getCollisionRectangle().getUpperLeft().getY()
                        + beingHit.getCollisionRectangle().getHeight() / 2), Ball.DEFAULT_RADIUS, Color.WHITE);
        newBall.setEnvironment(game.getEnvironment());
        newBall.setVelocity(Velocity.fromAngleAndSpeed(180, Ball.DEFAULT_SPEED));
        currentBalls.increase(1);
        newBall.addToLevel(game);
    }
}