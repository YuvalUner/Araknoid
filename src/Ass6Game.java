// Yuval Uner 322558842

import java.awt.Color;

import animation.AnimationRunner;
import biuoop.GUI;
import game.eventlisteners.ScoreTrackingListener;
import game.levels.GameLevel;
import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;
import game.levels.LevelMoon;
import gamegeometry.basetypes.Block;
import gamegeometry.basicgeometry.Rectangle;
import objectbehavior.Counter;

/**
 * @author Yuval Uner
 * A class with only a main method to run the game
 */
public class Ass6Game {

    /**
     * A main method.
     * <p>
     * Initializes the game and its environment, then runs it.
     * </p>
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Block window = new Block(new Rectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT),
                Color.BLUE);
        /*
         * Creating an environment that covers the whole gui's screen, with
         * gray borders of size 30
         * */
        GameEnvironment environment = new GameEnvironment(window, 30, Color.GRAY);
        Counter scoreCounter = new Counter();
        Counter lifeCounter = new Counter();
        lifeCounter.increase(7);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(scoreCounter);
        LevelMoon levelOne = new LevelMoon(environment, scoreTracker, lifeCounter);
        AnimationRunner runner = new AnimationRunner(new GUI("Araknoid", GameLevel.WIDTH, GameLevel.HEIGHT));
        GameLevel gameLevel = new GameLevel(levelOne, runner);
        gameLevel.run();
    }
}
