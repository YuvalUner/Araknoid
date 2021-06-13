package game.levels;

import game.eventlisteners.ScoreTrackingListener;
import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.Collidable;
import gamegeometry.basetypes.Sprite;
import objectbehavior.Counter;
import objectbehavior.Velocity;

import java.util.List;

public interface LevelInformation {
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();
    int paddleSpeed();
    int paddleWidth();
    // the level name will be displayed at the top of the screen.
    String levelName();
    // Returns a sprite with the background of the level
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();

    int getAmountOfBallsToSpawn();

    GameEnvironment getEnvironment();

    void addSprite(Sprite s);

    void removeSprite(Sprite s);

    SpriteCollection getSprites();

    void addCollidable(Collidable c);

    void removeCollidable(Collidable c);

    Counter getLifeCounter();

    ScoreTrackingListener getScoreTracker();

    Counter getRemainingBalls();

    Counter getRemainingBlocks();
}