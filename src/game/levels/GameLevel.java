// Yuval Uner 322558842

package game.levels;

import animation.*;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;
import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Collidable;
import gamegeometry.basetypes.Sprite;
import gamegeometry.basetypes.Paddle;
import objectbehavior.Counter;
import objectbehavior.Velocity;

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
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter currentBalls;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final double fps;
    private Paddle paddle;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int SCORE_INDICATOR_HEIGHT = 30;
    private final BaseLevel level;

    /**
     * Constructor.
     */
    public GameLevel(BaseLevel level, AnimationRunner runner) {
        this.level = level;
        this.sprites = level.getSprites();
        this.environment = level.getEnvironment();
        this.runner = runner;
        this.keyboard = runner.getGui().getKeyboardSensor();
        this.remainingBlocks = level.getRemainingBlocks();
        this.remainingBalls = level.getRemainingBalls();
        this.currentBalls = level.getCurrentBalls();
        this.fps = 60;
    }

    /**
     * @return the game's environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * <p>
     * A method for initializing the game.
     * Sets the game's gui, the ball, the paddle, and the blocks
     * that the game will use.
     * </p>
     */
    public void initialize() {

    }

    /**
     * <p> Runs the game and the game's animation loop.</p>
     */
    public void run() {
        this.initialize();
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites,
                30, this.environment), this.environment.getBackgroundColor());
        this.runner.run(this, this.environment.getBackgroundColor());
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Removes a collidable from the game.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.remove(c);
    }

    @Override
    public void doOneFrame(DrawSurface d, Color backgroundColor) {
        if (this.keyboard.isPressed("p")){
            this.runner.run(new PauseScreen(this.keyboard), backgroundColor);
        }
        d.setColor(backgroundColor);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        this.environment.drawAllOn(d);
        this.sprites.drawAllOn(d);
        // Make all sprites perform the actions they should perform
        this.sprites.notifyAllTimePassed();
        if (remainingBalls.getCurrentCount() == 0 || remainingBlocks.getCurrentCount() == 0) {
            this.running = false;
        }
        else if (remainingBalls.getCurrentCount() == currentBalls.getCurrentCount() - 1){
            Ball ball = new Ball(WIDTH / 2 + 60, 450, Ball.DEFAULT_RADIUS, Color.black);
            ball.setEnvironment(this.environment);
            ball.setVelocity(Velocity.fromAngleAndSpeed(180, Ball.DEFAULT_SPEED));
            ball.addToGame(this.level);
            currentBalls.decrease(1);
            paddle.setPosition(WIDTH/ 2);
            this.runner.run(new WaitingScreenAnimation(60, this.sprites, this.environment,
                    30, this.keyboard), backgroundColor);
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