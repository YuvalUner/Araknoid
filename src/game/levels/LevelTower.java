package game.levels;

import game.eventlisteners.*;
import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;
import gamegeometry.basetypes.Block;
import gamegeometry.blockdecorators.*;
import gamegeometry.basicgeometry.Rectangle;
import java.awt.*;

public class LevelTower extends BaseLevel{

    private SpriteCollection spriteCollection;


    public LevelTower(GameEnvironment environment,
                      ScoreTrackingListener scoreTracker) {
        super(environment, 6, 200, "Tower", 2, 6, scoreTracker);
        initializeBlocks(scoreTracker);
    }

    private void initializeBlocks(ScoreTrackingListener scoreTracker){

        BlockRemover blockRemover = new BlockRemover(this, getRemainingBlocks());
        BallRemover ballRemover = new BallRemover(this, getRemainingBalls());
        BallAdder ballAdder = new BallAdder(this, getRemainingBalls());
        LifeAdder lifeAdder = new LifeAdder(getCurrentBalls(), getRemainingBalls());
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
            blockTopRow.addToGame(this);
            // Second row of blocks
            if (i + margin < window.getWidth() - borders) {
                if (counter != 8) {
                    RemovableBlock blockSecondRow = new RemovableBlock(new ScoredBlock(
                            new Block(i + margin, initialY + blockHeight, blockWidth,
                                    blockHeight, Color.orange), scoreTracker,
                            Block.DEFAULT_SCORE), blockRemover, true, getRemainingBlocks());
                    blockSecondRow.addToGame(this);
                    // Life block is added as the 8th block of the 2nd row
                } else {
                    LifeBlock lifeBlock = BlockBuilder.standardLifeBlock(i + margin,
                            initialY + blockHeight, blockWidth, blockHeight, blockRemover,
                            lifeAdder, getRemainingBlocks());
                    lifeBlock.addToGame(this);
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
                    blockThirdRow.addToGame(this);
                }
                else{
                    BallAddingBlock ballAddingBlock = BlockBuilder.standardBallAddingBlock(
                            i + margin * 2, initialY + blockHeight * 2, blockWidth,
                            blockHeight, blockRemover, ballAdder, getRemainingBlocks());
                    ballAddingBlock.addToGame(this);
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
                    blockFourthRow.addToGame(this);
                    // Kill block is added as the 5th block of the 4th row
                } else {
                    KillBlock killBlock = BlockBuilder.standardKillBlock(i + margin * 3,
                            initialY + blockHeight * 3, blockWidth, blockHeight,
                            blockRemover, ballRemover, getRemainingBlocks());
                    killBlock.addToGame(this);
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
                    blockFifthRow.addToGame(this);
                    // Life block is added as the 2nd block of the 5th row
                } else {
                    LifeBlock lifeBlock = BlockBuilder.standardLifeBlock(i + margin * 4,
                            initialY + blockHeight * 4, blockWidth, blockHeight,
                            blockRemover, lifeAdder, getRemainingBlocks());
                    lifeBlock.addToGame(this);
                }
                increaseNumberOfBlocksToRemove(1);
            }
            // Sixth row of blocks
            if (i + margin * 5 < window.getWidth() - borders) {
                RemovableBlock blockSixthRow = new RemovableBlock(new ScoredBlock(
                        new Block(i + margin * 5, initialY + blockHeight * 5,
                                blockWidth, blockHeight, Color.pink), scoreTracker,
                        Block.DEFAULT_SCORE), blockRemover, true, getRemainingBlocks());
                blockSixthRow.addToGame(this);
                increaseNumberOfBlocksToRemove(1);
            }
        }
    }
}
