// Yuval Uner 322558842

package game.levels;

import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.Collidable;
import gamegeometry.basetypes.Sprite;
import objectbehavior.Counter;
import objectbehavior.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuval Uner
 * <h1> The basic functionality for all levels</h1>
 * <p> Handles most of the the many getters of LevelInformation in a universal manner.</p>
 */
public abstract class BaseLevel implements LevelInformation {

    private final GameEnvironment environment;
    private final SpriteCollection spriteCollection;
    private final int numberOfBalls;
    private final int paddleWidth;
    private final String levelName;
    private int numberOfBlocksToRemove = 0;
    private final int paddleSpeed;
    private ArrayList<Block> blocks;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final ArrayList<Velocity> initialBallVelocities;

    /**
     * Constructor.
     * Initializes all the fields needed for full functionality, as well as
     * sets most of the level specific fields.
     *
     * @param environment   the environment for the game level to use.
     * @param numberOfBalls the amount of balls to spawn during the level.
     * @param paddleWidth   the width of the paddle during the current level.
     * @param levelName     the name of the level.
     * @param paddleSpeed   the speed of the paddle.
     */
    public BaseLevel(GameEnvironment environment, int numberOfBalls,
                     int paddleWidth, String levelName, int paddleSpeed) {
        this.environment = environment;
        this.spriteCollection = new SpriteCollection();
        this.numberOfBalls = numberOfBalls;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.paddleSpeed = paddleSpeed;
        this.initialBallVelocities = new ArrayList<>();
        this.remainingBalls = new Counter();
        this.remainingBlocks = new Counter();
    }


    @Override
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    @Override
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * Increases the value of blocks that need to be removed to win the game.
     *
     * @param amount the amount to increase by.
     */
    public void increaseNumberOfBlocksToRemove(int amount) {
        this.numberOfBlocksToRemove += amount;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public SpriteCollection getSprites() {
        return this.spriteCollection;
    }

    @Override
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    @Override
    public void addSprite(Sprite s) {
        this.spriteCollection.addSprite(s);
    }

    @Override
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }

    @Override
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    @Override
    public void removeCollidable(Collidable c) {
        this.environment.remove(c);
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }
}
