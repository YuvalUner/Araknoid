// Yuval Uner 322558842

package game.levels;

import game.eventlisteners.BlockRemover;
import game.eventlisteners.ScoreTrackingListener;
import game.gameessentials.Background;
import game.gameessentials.GameEnvironment;
import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import gamegeometry.blockdecorators.RemovableBlock;
import gamegeometry.blockdecorators.ScoredBlock;
import objectbehavior.Counter;
import objectbehavior.Velocity;

import java.awt.Color;
import java.util.List;

/**
 * @author Yuval Uner
 * <h1> The bullseye level of the game</h1>
 * <p> A level with a single block in it, which must be hit to move on to the next level.</p>
 */
public class LevelBullseye extends BaseLevel {

    private final Background background;

    /**
     * Constructor.
     *
     * @param environment  the environment for the level.
     * @param scoreTracker the game's score tracker.
     * @param lifeCounter  the game's life counter.
     */
    public LevelBullseye(GameEnvironment environment,
                         ScoreTrackingListener scoreTracker,
                         Counter lifeCounter) {
        super(environment, 1, 150, "Bullseye", 8);
        setBlocks(scoreTracker, lifeCounter);
        this.background = new Background();
        setBackground();
        List<Velocity> velocities = initialBallVelocities();
        velocities.add(Velocity.fromAngleAndSpeed(165, Ball.DEFAULT_SPEED));
    }

    /**
     * Sets the level's background to a custom design (Which in reality, is just
     * a plain color background. However, it is possible to design a more intricate
     * background with the appropriate time and effort).
     */
    private void setBackground() {
        Block backgroundBlock = new Block(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT,
                new Color(207, 3, 252));
        this.background.addToBackground(backgroundBlock);
    }

    /**
     * Sets the level's blocks to their appropriate positions.
     *
     * @param scoreTracker the game's score tracker.
     * @param lifeCounter  the game's life counter.
     */
    private void setBlocks(ScoreTrackingListener scoreTracker, Counter lifeCounter) {
        BlockRemover blockRemover = new BlockRemover(this, getRemainingBlocks());
        RemovableBlock singleBlock = new RemovableBlock(new ScoredBlock(new Block(GameLevel.WIDTH / 2 - 15,
                200, 30, 30, Color.red), scoreTracker, Block.DEFAULT_SCORE),
                blockRemover, true, getRemainingBlocks());
        singleBlock.addToLevel(this);
    }

    @Override
    public Background getBackground() {
        return background;
    }
}
