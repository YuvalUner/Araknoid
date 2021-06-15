package game.levels;

import game.eventlisteners.BlockRemover;
import game.eventlisteners.ScoreTrackingListener;
import game.gameessentials.Background;
import game.gameessentials.GameEnvironment;
import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.Sprite;
import gamegeometry.blockdecorators.RemovableBlock;
import gamegeometry.blockdecorators.ScoredBlock;
import objectbehavior.Counter;
import objectbehavior.Velocity;

import java.awt.*;
import java.util.List;

public class LevelBullseye extends BaseLevel {

    private final Background background;

    public LevelBullseye(GameEnvironment environment,
                         ScoreTrackingListener scoreTracker,
                         Counter lifeCounter) {
        super(environment, 1, 100, "Bullseye", 8);
        initializeBlocks(scoreTracker, lifeCounter);
        this.background = new Background();
        initializeBackground();
        List<Velocity> velocities = initialBallVelocities();
        velocities.add(Velocity.fromAngleAndSpeed(135, Ball.DEFAULT_SPEED));
    }

    private void initializeBackground() {
        Block backgroundBlock = new Block(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT,
                new Color(207, 3, 252));
        this.background.addToBackground(backgroundBlock);
    }

    private void initializeBlocks(ScoreTrackingListener scoreTracker, Counter lifeCounter) {
        BlockRemover blockRemover = new BlockRemover(this, getRemainingBlocks());
        RemovableBlock singleBlock = new RemovableBlock(new ScoredBlock(new Block(GameLevel.WIDTH / 2 - 15,
                200, 30, 30, Color.red), scoreTracker, Block.DEFAULT_SCORE),
                blockRemover, true, getRemainingBlocks());
        singleBlock.addToLevel(this);
    }

    @Override
    public Sprite getBackground() {
        return background;
    }
}
