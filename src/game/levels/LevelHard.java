// Yuval Uner 322558842

package game.levels;

import game.eventlisteners.BallAdder;
import game.eventlisteners.BallRemover;
import game.eventlisteners.BlockRemover;
import game.eventlisteners.LifeAdder;
import game.eventlisteners.ScoreTrackingListener;
import game.gameessentials.Background;
import game.gameessentials.GameEnvironment;
import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import gamegeometry.blockdecorators.BallAddingBlock;
import gamegeometry.blockdecorators.BlockBuilder;
import gamegeometry.blockdecorators.KillBlock;
import gamegeometry.blockdecorators.LifeBlock;
import gamegeometry.blockdecorators.RemovableBlock;
import gamegeometry.blockdecorators.ScoredBlock;
import objectbehavior.Counter;
import objectbehavior.Velocity;

import java.awt.Color;
import java.util.List;

/**
 * @author Yuval Uner
 * <h1> The game's hard level</h1>
 * <p> Features a medium sized paddle, 3 balls, lots of blocks, and 3 unremovable
 * kill blocks.</p>
 */
public class LevelHard extends BaseLevel {

    private final Background background;

    /**
     * Constructor.
     *
     * @param environment  the environment for the level.
     * @param scoreTracker the game's score tracker.
     * @param lifeCounter  the game's life counter.
     */
    public LevelHard(GameEnvironment environment,
                     ScoreTrackingListener scoreTracker,
                     Counter lifeCounter) {
        super(environment, 3, 200, "Hard Mode", 8);
        setBlocks(scoreTracker, lifeCounter);
        this.background = new Background();
        setBackground();
        List<Velocity> velocities = initialBallVelocities();
        velocities.add(Velocity.fromAngleAndSpeed(135, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(175, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(215, Ball.DEFAULT_SPEED));
    }

    /**
     * Sets the level's background to a custom design (Which in reality, is just
     * a plain color background. However, it is possible to design a more intricate
     * background with the appropriate time and effort).
     */
    private void setBackground() {
        Block backgroundBlock = new Block(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT,
                new Color(66, 197, 245));
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
        int margin = 100;
        BlockRemover blockRemover = new BlockRemover(this, getRemainingBlocks());
        BallRemover ballRemover = new BallRemover(this, getRemainingBalls());
        LifeAdder lifeAdder = new LifeAdder(lifeCounter);
        BallAdder ballAdder = new BallAdder(this, getRemainingBalls());
        for (int i = 0; i < 15; i++) {
            if (i != 5 && i != 11) {
                RemovableBlock blockFirstRow = new RemovableBlock(new ScoredBlock(
                        new Block(i * blockWidth + getEnvironment().getBorders(),
                                GameLevel.SCORE_INDICATOR_HEIGHT + margin, blockWidth - 1,
                                blockHeight, Color.red), scoreTracker, Block.DEFAULT_SCORE), blockRemover,
                        true, getRemainingBlocks());
                blockFirstRow.addToLevel(this);
            } else if (i == 5) {
                BallAddingBlock ballAddingBlock =
                        BlockBuilder.standardBallAddingBlock(i * blockWidth + getEnvironment().getBorders(),
                                GameLevel.SCORE_INDICATOR_HEIGHT + margin, blockWidth - 1, blockHeight,
                                blockRemover, ballAdder, getRemainingBlocks());
                ballAddingBlock.addToLevel(this);
            } else {
                KillBlock killBlock = BlockBuilder.standardNonRemovableKillBlock(
                        i * blockWidth + getEnvironment().getBorders(),
                        GameLevel.SCORE_INDICATOR_HEIGHT + margin, blockWidth - 1, blockHeight,
                        ballRemover);
                killBlock.addToLevel(this);
            }
            if (i != 13) {
                RemovableBlock blockSecondRow = new RemovableBlock(new ScoredBlock(
                        new Block(i * blockWidth + getEnvironment().getBorders(),
                                GameLevel.SCORE_INDICATOR_HEIGHT + margin + blockHeight, blockWidth - 1,
                                blockHeight, Color.gray), scoreTracker, Block.DEFAULT_SCORE), blockRemover,
                        true, getRemainingBlocks());
                blockSecondRow.addToLevel(this);
            } else {
                LifeBlock lifeBlock = BlockBuilder.standardLifeBlock(i * blockWidth + getEnvironment().getBorders(),
                        GameLevel.SCORE_INDICATOR_HEIGHT + margin + blockHeight, blockWidth - 1,
                        blockHeight, blockRemover, lifeAdder, getRemainingBlocks());
                lifeBlock.addToLevel(this);
            }
            if (i != 7) {
                RemovableBlock blockThirdRow = new RemovableBlock(new ScoredBlock(
                        new Block(i * blockWidth + getEnvironment().getBorders(),
                                GameLevel.SCORE_INDICATOR_HEIGHT + margin + blockHeight * 2, blockWidth - 1,
                                blockHeight, Color.yellow), scoreTracker, Block.DEFAULT_SCORE), blockRemover,
                        true, getRemainingBlocks());
                blockThirdRow.addToLevel(this);
            } else {
                KillBlock killBlock = BlockBuilder.standardNonRemovableKillBlock(
                        i * blockWidth + getEnvironment().getBorders(),
                        GameLevel.SCORE_INDICATOR_HEIGHT + margin + blockHeight * 2, blockWidth - 1, blockHeight,
                        ballRemover);
                killBlock.addToLevel(this);
            }
            RemovableBlock blockFourthRow = new RemovableBlock(new ScoredBlock(
                    new Block(i * blockWidth + getEnvironment().getBorders(),
                            GameLevel.SCORE_INDICATOR_HEIGHT + margin + blockHeight * 3, blockWidth - 1,
                            blockHeight, Color.green), scoreTracker, Block.DEFAULT_SCORE), blockRemover,
                    true, getRemainingBlocks());
            blockFourthRow.addToLevel(this);
            if (i != 0) {
                RemovableBlock blockFifthRow = new RemovableBlock(new ScoredBlock(
                        new Block(i * blockWidth + getEnvironment().getBorders(),
                                GameLevel.SCORE_INDICATOR_HEIGHT + margin + blockHeight * 4, blockWidth - 1,
                                blockHeight, Color.white), scoreTracker, Block.DEFAULT_SCORE), blockRemover,
                        true, getRemainingBlocks());
                blockFifthRow.addToLevel(this);
            } else {
                BallAddingBlock ballAddingBlock =
                        BlockBuilder.standardBallAddingBlock(i * blockWidth + getEnvironment().getBorders(),
                                GameLevel.SCORE_INDICATOR_HEIGHT + margin + blockHeight * 4,
                                blockWidth - 1, blockHeight, blockRemover, ballAdder, getRemainingBlocks());
                ballAddingBlock.addToLevel(this);
            }
            if (i != 1) {
                RemovableBlock blockSixthRow = new RemovableBlock(new ScoredBlock(
                        new Block(i * blockWidth + getEnvironment().getBorders(),
                                GameLevel.SCORE_INDICATOR_HEIGHT + margin + blockHeight * 5, blockWidth - 1,
                                blockHeight, Color.cyan), scoreTracker, Block.DEFAULT_SCORE), blockRemover,
                        true, getRemainingBlocks());
                blockSixthRow.addToLevel(this);
            } else {
                KillBlock killBlock = BlockBuilder.standardNonRemovableKillBlock(
                        i * blockWidth + getEnvironment().getBorders(),
                        GameLevel.SCORE_INDICATOR_HEIGHT + margin + blockHeight * 5, blockWidth - 1, blockHeight,
                        ballRemover);
                killBlock.addToLevel(this);
            }
        }
    }

    @Override
    public Background getBackground() {
        return this.background;
    }
}
