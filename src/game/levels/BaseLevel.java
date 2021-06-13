package game.levels;

import game.gameessentials.*;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.Collidable;
import gamegeometry.basetypes.Sprite;
import objectbehavior.Counter;
import objectbehavior.Velocity;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseLevel implements  LevelInformation {

    private final GameEnvironment environment;
    private final SpriteCollection spriteCollection;
    private final int numberOfBalls;
    private final int paddleWidth;
    private final String levelName;
    private final int amountOfBallsToSpawn;
    private int numberOfBlocksToRemove = 0;
    private final int paddleSpeed;
    private ArrayList<Block> blocks;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final ArrayList<Velocity> initialBallVelocities;

    public BaseLevel(GameEnvironment environment, int numberOfBalls,
                     int paddleWidth, String levelName,
                     int amountOfBallsToSpawn, int paddleSpeed){
        this.environment = environment;
        this.spriteCollection = new SpriteCollection();
        this.numberOfBalls = numberOfBalls;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.amountOfBallsToSpawn = amountOfBallsToSpawn;
        this.paddleSpeed = paddleSpeed;
        this.initialBallVelocities = new ArrayList<>();
        this.remainingBalls = new Counter();
        this.remainingBlocks = new Counter();
    }

    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    public void increaseNumberOfBlocksToRemove(int amount){
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
    public SpriteCollection getSprites(){
        return this.spriteCollection;
    }

    @Override
    public GameEnvironment getEnvironment(){
        return this.environment;
    }

    @Override
    public void addSprite(Sprite s){
        this.spriteCollection.addSprite(s);
    }

    @Override
    public void removeSprite(Sprite s){
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

    @Override
    public int getAmountOfBallsToSpawn() {
        return amountOfBallsToSpawn;
    }
}
