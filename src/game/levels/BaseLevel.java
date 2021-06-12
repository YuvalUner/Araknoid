package game.levels;

import game.eventlisteners.ScoreTrackingListener;
import game.gameessentials.*;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.Collidable;
import gamegeometry.basetypes.Sprite;
import gamegeometry.blockdecorators.BlockWithText;
import objectbehavior.Counter;
import objectbehavior.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseLevel implements  LevelInformation {

    private GameEnvironment environment;
    private SpriteCollection spriteCollection;
    private final int numberOfBalls;
    private final int paddleWidth;
    private final String levelName;
    private final int amountOfBallsToSpawn;
    private int numberOfBlocksToRemove = 0;
    private final int paddleSpeed;
    private ArrayList<Block> blocks;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter currentBalls;
    private ArrayList<Velocity> initialBallVelocities;
    private Background background;


    public BaseLevel(GameEnvironment environment, int numberOfBalls,
                     int paddleWidth, String levelName,
                     int amountOfBallsToSpawn, int paddleSpeed,
                     ScoreTrackingListener scoreTracker){
        this.environment = environment;
        this.spriteCollection = new SpriteCollection();
        this.numberOfBalls = numberOfBalls;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.amountOfBallsToSpawn = amountOfBallsToSpawn;
        this.paddleSpeed = paddleSpeed;
        this.remainingBalls = new Counter();
        this.remainingBlocks = new Counter();
        this.currentBalls = new Counter();
        initializeIndicators(scoreTracker.getScoreCounter());
    }

    private void initializeIndicators(Counter scoreCounter){
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Block(0, 0,
                GameLevel.WIDTH / 3, GameLevel.SCORE_INDICATOR_HEIGHT,
                Color.white), "", Color.black,
                scoreCounter, 14, 0, GameLevel.SCORE_INDICATOR_HEIGHT / 1.3);
        scoreIndicator.addToGame(this);
        BlockWithText levelIndicator =
                new BlockWithText(new Block(GameLevel.WIDTH / 3, 0,
                        GameLevel.WIDTH / 3, GameLevel.SCORE_INDICATOR_HEIGHT
                        , Color.white), "Current Level: " + this.levelName,
                Color.black, 14, GameLevel.WIDTH + 30,
                        GameLevel.SCORE_INDICATOR_HEIGHT / 1.3);
        levelIndicator.addToGame(this);
        LifeIndicator lifeIndicator =
                new LifeIndicator(new Block(2 * GameLevel.WIDTH / 3, 0,
                GameLevel.WIDTH / 3, GameLevel.SCORE_INDICATOR_HEIGHT,
                Color.white), "", Color.black, remainingBalls, 14 ,
                        GameLevel.WIDTH - 130,
                        GameLevel.SCORE_INDICATOR_HEIGHT / 1.3);
        lifeIndicator.addToGame(this);
    }

    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    public Counter getCurrentBalls() {
        return this.currentBalls;
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
    public Background getBackground() {
        return background;
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
