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
import basicgeometry.Rectangle;
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
 * <h1> The game's normal level</h1>
 * <p> Features a small paddle, 2 balls, a moderate amount of balls, and
 * some special removable blocks.</p>
 */
public class LevelNormal extends BaseLevel {

    private final Background background;

    /**
     * Constructor.
     *
     * @param environment  the environment for the level.
     * @param scoreTracker the game's score tracker.
     * @param lifeCounter  the game's life counter.
     */
    public LevelNormal(GameEnvironment environment,
                       ScoreTrackingListener scoreTracker,
                       Counter lifeCounter) {
        super(environment, 2, 150, "Normal Mode", 8);
        setBlocks(scoreTracker, lifeCounter);
        this.background = new Background();
        setBackground();
        List<Velocity> velocities = initialBallVelocities();
        velocities.add(Velocity.fromAngleAndSpeed(145, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(200, Ball.DEFAULT_SPEED));
    }

    /**
     * Sets the level's background to a custom design (Which in reality, is just
     * a plain color background. However, it is possible to design a more intricate
     * background with the appropriate time and effort).
     */
    private void setBackground() {
        Block backgroundBlock = new Block(0, 0, GameLevel.WIDTH,
                GameLevel.HEIGHT, Color.green);
        this.background.addToBackground(backgroundBlock);
    }

    /**
     * Sets the level's blocks to their appropriate positions.
     *
     * @param scoreTracker the game's score tracker.
     * @param lifeCounter  the game's life counter.
     */
    private void setBlocks(ScoreTrackingListener scoreTracker,
                           Counter lifeCounter) {
        BlockRemover blockRemover = new BlockRemover(this, getRemainingBlocks());
        BallRemover ballRemover = new BallRemover(this, getRemainingBalls());
        BallAdder ballAdder = new BallAdder(this, getRemainingBalls());
        LifeAdder lifeAdder = new LifeAdder(lifeCounter);
        Rectangle window = getEnvironment().getWindow().getCollisionRectangle();
        int blockHeight = 24;
        int blockWidth = 48;
        int initialY = 150;
        double margin = window.getWidth() / 16;
        int counter = 0;
        double borders = getEnvironment().getBorders();
        /*
         * Adding 6 rows of blocks, all of the same size.
         * The game is won when all blocks were hit, except for the special types
         * of blocks, such as life blocks and kill blocks, which do not need to be
         * hit to win.
         * */
        for (double i = margin * 3 + borders * 2 / 3;
             i + getEnvironment().getBorders() < window.getWidth() - getEnvironment().getBorders();
             i += margin) {
            counter++;
            // First row of blocks
            RemovableBlock blockTopRow = new RemovableBlock(new ScoredBlock(
                    new Block(i, initialY, blockWidth, blockHeight, Color.red),
                    scoreTracker, Block.DEFAULT_SCORE), blockRemover,
                    true, getRemainingBlocks());
            increaseNumberOfBlocksToRemove(1);
            blockTopRow.addToLevel(this);
            // Second row of blocks
            if (i + margin < window.getWidth() - borders) {
                if (counter != 8) {
                    RemovableBlock blockSecondRow = new RemovableBlock(new ScoredBlock(
                            new Block(i + margin, initialY + blockHeight, blockWidth,
                                    blockHeight, Color.orange), scoreTracker,
                            Block.DEFAULT_SCORE), blockRemover, true, getRemainingBlocks());
                    blockSecondRow.addToLevel(this);
                    // Life block is added as the 8th block of the 2nd row
                } else {
                    LifeBlock lifeBlock = BlockBuilder.standardLifeBlock(i + margin,
                            initialY + blockHeight, blockWidth, blockHeight, blockRemover,
                            lifeAdder, getRemainingBlocks());
                    lifeBlock.addToLevel(this);
                }
                increaseNumberOfBlocksToRemove(1);
            }
            // Third row of blocks
            if (i + margin * 2 < window.getWidth() - borders) {
                if (counter != 1) {
                    RemovableBlock blockThirdRow = new RemovableBlock(new ScoredBlock(
                            new Block(i + margin * 2, initialY + blockHeight * 2,
                                    blockWidth, blockHeight, Color.yellow), scoreTracker,
                            Block.DEFAULT_SCORE), blockRemover, true, getRemainingBlocks());
                    blockThirdRow.addToLevel(this);
                } else {
                    BallAddingBlock ballAddingBlock = BlockBuilder.standardBallAddingBlock(
                            i + margin * 2, initialY + blockHeight * 2, blockWidth,
                            blockHeight, blockRemover, ballAdder, getRemainingBlocks());
                    ballAddingBlock.addToLevel(this);
                }
                increaseNumberOfBlocksToRemove(1);
            }
            // Fourth row of blocks
            if (i + margin * 3 < window.getWidth() - borders) {
                if (counter != 5) {
                    RemovableBlock blockFourthRow = new RemovableBlock(new ScoredBlock(
                            new Block(i + margin * 3, initialY + blockHeight * 3,
                                    blockWidth, blockHeight, Color.gray), scoreTracker,
                            Block.DEFAULT_SCORE), blockRemover, true, getRemainingBlocks());
                    blockFourthRow.addToLevel(this);
                    // Kill block is added as the 5th block of the 4th row
                } else {
                    KillBlock killBlock = BlockBuilder.standardKillBlock(i + margin * 3,
                            initialY + blockHeight * 3, blockWidth, blockHeight,
                            blockRemover, ballRemover, getRemainingBlocks());
                    killBlock.addToLevel(this);
                }
                increaseNumberOfBlocksToRemove(1);
            }
            // Fifth row of blocks
            if (i + margin * 4 < window.getWidth() - borders) {
                if (counter != 2) {
                    RemovableBlock blockFifthRow = new RemovableBlock(new ScoredBlock(
                            new Block(i + margin * 4, initialY + blockHeight * 4,
                                    blockWidth, blockHeight, Color.white), scoreTracker,
                            Block.DEFAULT_SCORE), blockRemover, true, getRemainingBlocks());
                    blockFifthRow.addToLevel(this);
                    // Life block is added as the 2nd block of the 5th row
                } else {
                    LifeBlock lifeBlock = BlockBuilder.standardLifeBlock(i + margin * 4,
                            initialY + blockHeight * 4, blockWidth, blockHeight,
                            blockRemover, lifeAdder, getRemainingBlocks());
                    lifeBlock.addToLevel(this);
                }
                increaseNumberOfBlocksToRemove(1);
            }
            // Sixth row of blocks
            if (i + margin * 5 < window.getWidth() - borders) {
                RemovableBlock blockSixthRow = new RemovableBlock(new ScoredBlock(
                        new Block(i + margin * 5, initialY + blockHeight * 5,
                                blockWidth, blockHeight, Color.pink), scoreTracker,
                        Block.DEFAULT_SCORE), blockRemover, true, getRemainingBlocks());
                blockSixthRow.addToLevel(this);
                increaseNumberOfBlocksToRemove(1);
            }
        }
    }

    @Override
    public Background getBackground() {
        return background;
    }
}
