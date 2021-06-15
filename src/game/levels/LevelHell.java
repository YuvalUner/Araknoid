package game.levels;

import game.eventlisteners.*;
import game.gameessentials.Background;
import game.gameessentials.GameEnvironment;
import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.Sprite;
import gamegeometry.blockdecorators.*;
import objectbehavior.Counter;
import objectbehavior.Velocity;

import java.awt.*;
import java.util.List;

public class LevelHell extends BaseLevel {

    private final Background background;

    public LevelHell(GameEnvironment environment,
                     ScoreTrackingListener scoreTracker,
                     Counter lifeCounter) {
        super(environment, 3, 200, "Hell", 8);
        initializeBlocks(scoreTracker, lifeCounter);
        this.background = new Background();
        initializeBackground();
        List<Velocity> velocities = initialBallVelocities();
        velocities.add(Velocity.fromAngleAndSpeed(125, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(185, Ball.DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(225, Ball.DEFAULT_SPEED));
    }

    private void initializeBackground() {
        Block backgroundBlock = new Block(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT,
                new Color(66, 197, 245));
        this.background.addToBackground(backgroundBlock);
    }

    private void initializeBlocks(ScoreTrackingListener scoreTracker, Counter lifeCounter) {
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
    public Sprite getBackground() {
        return this.background;
    }
}
