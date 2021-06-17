// Yuval Uner 322558842

package game.levels;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import animation.WaitingScreenAnimation;
import basicgeometry.Point;
import basicgeometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.eventlisteners.BallRemover;
import game.gameessentials.LifeIndicator;
import game.gameessentials.ScoreIndicator;
import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.Paddle;
import gamegeometry.blockdecorators.BlockWithText;
import gamegeometry.blockdecorators.KillBlock;
import objectbehavior.Counter;

import java.awt.Color;

/**
 * @author Yuval Uner
 * <h1> A class that runs a single level of the game</h1>
 * <p> This class handles the functionality of running a single level of the game,
 * via the information it recieves from a class that implements LevelInformation.</p>
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
     * All parameters are given via the GameFlow class.
     *
     * @param level        the LevelInformation containing all the info for running a single level.
     * @param runner       the animation runner for the game.
     * @param ks           the keyboard sensor for the game.
     * @param lifeCounter  the game's life counter.
     * @param scoreTracker the game's score counter.
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
     * Initializes the kill block at the bottom of the screen, and the indicators
     * at the top of the screen, as well as the paddle for the current level.
     * </p>
     */
    public void initialize() {
        // The kill block on the bottom of the screen.
        KillBlock downKillBlock = new KillBlock(new Block(0, GameLevel.HEIGHT,
                GameLevel.WIDTH, 1, Color.black), new BallRemover(level,
                level.getRemainingBalls()));
        downKillBlock.addToLevel(level);
        // The score indicator on the top.
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Block(0, 0,
                WIDTH / 3, GameLevel.SCORE_INDICATOR_HEIGHT,
                Color.white), "", Color.black,
                this.scoreTracker, 14, 0,
                GameLevel.SCORE_INDICATOR_HEIGHT / 1.3);
        scoreIndicator.addToLevel(level);
        // The level indicator on the top.
        BlockWithText levelIndicator = new BlockWithText(new Block(WIDTH / 3, 0,
                WIDTH / 3 + 20, GameLevel.SCORE_INDICATOR_HEIGHT, Color.white),
                "Current Level: " + level.levelName(), Color.black, 14,
                WIDTH / 3 + 30, GameLevel.SCORE_INDICATOR_HEIGHT / 1.3);
        levelIndicator.addToLevel(level);
        // The life indicator on the top.
        LifeIndicator lifeIndicator = new LifeIndicator(new Block(2 * WIDTH / 3, 0,
                WIDTH / 3, GameLevel.SCORE_INDICATOR_HEIGHT,
                Color.white), "", Color.black, this.lifeCounter, 14,
                GameLevel.WIDTH - 130, GameLevel.SCORE_INDICATOR_HEIGHT / 1.3);
        lifeIndicator.addToLevel(level);
        Rectangle rectangle = new Rectangle(new Point(WIDTH / 2 - level.paddleWidth() / 2,
                550), level.paddleWidth(), 15);
        // The game's paddle.
        this.paddle = new Paddle(keyboard, rectangle, Color.ORANGE, level, level.paddleSpeed());
        this.paddle.addToLevel(level);
        initializeBalls();
    }

    /**
     * Creates all the balls the level should have, and initializes them with
     * the correct velocities, as well as updates the remainingBalls counter.
     */
    private void initializeBalls() {
        for (int i = 0; i < level.numberOfBalls(); i++) {
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
        // Triggers the pause animation
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, new String[]{KeyboardSensor.SPACE_KEY},
                    new PauseScreen()));
        }
        // Draws all objects on the screen.
        level.getBackground().drawOn(d);
        level.getEnvironment().drawAllOn(d);
        level.getSprites().drawAllOn(d);
        // Make all sprites perform the actions they should perform
        level.getSprites().notifyAllTimePassed();
        if (level.getRemainingBlocks().getCurrentCount() == 0) {
            this.scoreTracker.increase(100);
            this.running = false;
        }
        if (this.lifeCounter.getCurrentCount() == 0) {
            this.running = false;
        } else if (level.getRemainingBalls().getCurrentCount() == 0) {
            paddle.setPosition(WIDTH / 2 - level.paddleWidth() / 2);
            initializeBalls();
            this.lifeCounter.decrease(1);
            if (this.lifeCounter.getCurrentCount() > 0) {
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard, new String[]{KeyboardSensor.RIGHT_KEY,
                        KeyboardSensor.LEFT_KEY, "a", "d"}, new WaitingScreenAnimation(level.getSprites(),
                        level.getEnvironment(), 30, level.getBackground())));
            }
        }
        // A master key used for testing. Should be removed when not in testing.
        if (keyboard.isPressed("m")) {
            this.running = false;
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