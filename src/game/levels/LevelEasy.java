// Yuval Uner 322558842

package game.levels;

import game.eventlisteners.BlockRemover;
import game.eventlisteners.LifeAdder;
import game.eventlisteners.ScoreTrackingListener;
import game.gameessentials.Background;
import game.gameessentials.GameEnvironment;
import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import gamegeometry.blockdecorators.BlockBuilder;
import gamegeometry.blockdecorators.LifeBlock;
import gamegeometry.blockdecorators.RemovableBlock;
import gamegeometry.blockdecorators.ScoredBlock;
import objectbehavior.Counter;
import objectbehavior.Velocity;

import java.awt.Color;
import java.util.List;

/**
 * @author Yuval Uner
 * <h1> The game's easy level</h1>
 * <p> Features few blocks, a very wide paddle, and many balls.</p>
 */
public class LevelEasy extends BaseLevel {

    private final Background background;

    /**
     * Constructor.
     *
     * @param environment  the environment for the level.
     * @param scoreTracker the game's score tracker.
     * @param lifeCounter  the game's life counter.
     */
    public LevelEasy(GameEnvironment environment,
                     ScoreTrackingListener scoreTracker,
                     Counter lifeCounter) {
        super(environment, 8, GameLevel.WIDTH - 200, "Easy Mode", 2);
        setBlocks(scoreTracker, lifeCounter);
        this.background = new Background();
        setBackground();
        List<Velocity> velocities = initialBallVelocities();
        velocities.add(Velocity.fromAngleAndSpeed(105, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(125, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(145, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(165, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(185, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(205, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(225, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(245, Ball.DEFAULT_SPEED));
    }

    /**
     * Sets the level's background to a custom design (Which in reality, is just
     * a plain color background. However, it is possible to design a more intricate
     * background with the appropriate time and effort).
     */
    private void setBackground() {
        Block backgroundBlock = new Block(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT, Color.MAGENTA);
        this.background.addToBackground(backgroundBlock);
    }

    /**
     * Sets the level's blocks to their appropriate positions.
     *
     * @param scoreTracker the game's score tracker.
     * @param lifeCounter  the game's life counter.
     */
    private void setBlocks(ScoreTrackingListener scoreTracker, Counter lifeCounter) {
        double blockWidth = (GameLevel.WIDTH - getEnvironment().getBorders() * 2) / 15;
        int blockHeight = 24;
        Color color;
        BlockRemover blockRemover = new BlockRemover(this, getRemainingBlocks());
        for (int i = 0; i < 15; i++) {
            if (i < 2) {
                color = Color.red;
            } else if (i < 4) {
                color = Color.orange;
            } else if (i < 6) {
                color = Color.yellow;
            } else if (i < 9) {
                color = Color.green;
            } else if (i < 11) {
                color = Color.blue;
            } else if (i < 13) {
                color = Color.pink;
            } else {
                color = Color.cyan;
            }
            if (i != 7) {
                RemovableBlock block = new RemovableBlock(new ScoredBlock(
                        new Block(i * blockWidth + getEnvironment().getBorders(), 200,
                                blockWidth - 1, blockHeight, color), scoreTracker, Block.DEFAULT_SCORE),
                        blockRemover, true, getRemainingBlocks());
                block.addToLevel(this);
            } else {
                LifeBlock lifeBlock = BlockBuilder.standardLifeBlock(
                        i * blockWidth + getEnvironment().getBorders(), 200, blockWidth - 1, blockHeight,
                        blockRemover, new LifeAdder(lifeCounter), getRemainingBlocks());
                lifeBlock.addToLevel(this);
            }
        }
    }

    @Override
    public Background getBackground() {
        return background;
    }
}
