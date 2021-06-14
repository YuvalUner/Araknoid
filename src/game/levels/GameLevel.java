// Yuval Uner 322558842

package game.levels;

import animation.*;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.eventlisteners.BallRemover;
import game.gameessentials.LifeIndicator;
import game.gameessentials.ScoreIndicator;
import gamegeometry.basetypes.*;
import gamegeometry.basicgeometry.Point;
import gamegeometry.basicgeometry.Rectangle;
import gamegeometry.blockdecorators.BlockWithText;
import gamegeometry.blockdecorators.KillBlock;
import objectbehavior.Counter;

import java.awt.Color;

/**
 * @author Yuval Uner
 * <h1> A class representing a game</h1>
 * <p>A game with a collection of sprites and an environment which contains
 * collidable objects.
 * Contains the following public methods:
 * 1. Constructor
 * 2. Getters for the game's environment and sprite collection
 * 3. Methods to add sprites or collidables to the game
 * 4. Initialize - initalizes the game and its environment
 * 5. Run - runs the game</p>
 */
public class GameLevel implements Animation {
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final double fps;
    private Paddle paddle;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int SCORE_INDICATOR_HEIGHT = 30;
    private final LevelInformation level;
    private final Counter lifeCounter;
    private final Counter scoreTracker;

    /**
     * Constructor.
     */
    public GameLevel(LevelInformation level, AnimationRunner runner, KeyboardSensor ks,
                     Counter lifeCounter, Counter scoreTracker) {
        this.level = level;
        this.runner = runner;
        this.keyboard = ks;
        this.fps = 60;
        this.lifeCounter = lifeCounter;
        this.scoreTracker = scoreTracker;
    }

    /**
     * <p>
     * A method for initializing the game.
     * Sets the game's gui, the ball, the paddle, and the blocks
     * that the game will use.
     * </p>
     */
    public void initialize() {
        KillBlock downKillBlock = new KillBlock(new Block(0, GameLevel.HEIGHT
                , GameLevel.WIDTH, 1 , Color.black), new BallRemover(level,
                level.getRemainingBalls()));
        downKillBlock.addToLevel(level);
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Block(0, 0,
                WIDTH / 3, GameLevel.SCORE_INDICATOR_HEIGHT,
                Color.white), "", Color.black,
                this.scoreTracker, 14, 0,
                GameLevel.SCORE_INDICATOR_HEIGHT / 1.3);
        scoreIndicator.addToLevel(level);
        BlockWithText levelIndicator = new BlockWithText(new Block(WIDTH / 3, 0,
                        WIDTH / 3 + 20, GameLevel.SCORE_INDICATOR_HEIGHT, Color.white),
                "Current Level: " + level.levelName(), Color.black, 14,
                WIDTH / 3 + 30, GameLevel.SCORE_INDICATOR_HEIGHT / 1.3);
        levelIndicator.addToLevel(level);
        LifeIndicator lifeIndicator = new LifeIndicator(new Block(2 * WIDTH / 3, 0,
                        WIDTH / 3, GameLevel.SCORE_INDICATOR_HEIGHT,
                        Color.white), "", Color.black, this.lifeCounter, 14 ,
                        GameLevel.WIDTH - 130, GameLevel.SCORE_INDICATOR_HEIGHT / 1.3);
        lifeIndicator.addToLevel(level);
        Rectangle rectangle = new Rectangle(new Point(WIDTH / 2 - level.paddleWidth() / 2,
                        550), level.paddleWidth(), 15);
        this.paddle = new Paddle(keyboard, rectangle, Color.ORANGE, level);
        this.paddle.addToLevel(level);
        initializeBalls();
    }

    private void initializeBalls(){
        for (int i = 0; i < level.numberOfBalls(); i ++) {
            Ball ball = new Ball(WIDTH / 2, 500, Ball.DEFAULT_RADIUS, Color.white);
            ball.setEnvironment(level.getEnvironment());
            ball.setVelocity(level.initialBallVelocities().get(i));
            ball.addToLevel(level);
            level.getRemainingBalls().increase(1);
        }
    }

    /**
     * <p> Runs the game and the game's animation loop.</p>
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, level.getSprites(),
                30, level.getEnvironment(), level.getBackground()));
        this.runner.run(this);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")){
            this.runner.run(new PauseScreen(this.keyboard));
        }
        level.getBackground().drawOn(d);
        level.getEnvironment().drawAllOn(d);
        level.getSprites().drawAllOn(d);
        // Make all sprites perform the actions they should perform
        level.getSprites().notifyAllTimePassed();
        if (level.getRemainingBlocks().getCurrentCount() == 0) {
            this.scoreTracker.increase(100);
            this.running = false;
        }
        if (this.lifeCounter.getCurrentCount() == 0){
            this.running = false;
        }
        if (keyboard.isPressed("m")){
            this.scoreTracker.increase(100);
            this.running = false;
        }
        else if (level.getRemainingBalls().getCurrentCount() == 0){
            initializeBalls();
            this.lifeCounter.decrease(1);
            paddle.setPosition(WIDTH / 2 - level.paddleWidth() / 2);
            if (this.lifeCounter.getCurrentCount() > 0) {
                this.runner.run(new WaitingScreenAnimation(60, level.getSprites(),
                        level.getEnvironment(), 30, this.keyboard, level.getBackground()));
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public double getFps() {
        return this.fps;
    }
}