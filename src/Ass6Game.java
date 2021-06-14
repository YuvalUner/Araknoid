// Yuval Uner 322558842

import java.awt.Color;
import java.util.ArrayList;

import animation.AnimationRunner;
import biuoop.GUI;
import game.eventlisteners.ScoreTrackingListener;
import game.levels.*;
import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;
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
        Counter scoreCounter = new Counter();
        Counter lifeCounter = new Counter();
        lifeCounter.increase(7);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(scoreCounter);
        LevelMoon levelOne = new LevelMoon(new GameEnvironment(window, 30, Color.GRAY),
                scoreTracker, lifeCounter);
        LevelBullseye levelTwo = new LevelBullseye(new GameEnvironment(window, 30, Color.GRAY),
                scoreTracker, lifeCounter);
        LevelWideEasy levelThree = new LevelWideEasy(new GameEnvironment(window, 30, Color.GRAY),
                scoreTracker, lifeCounter);
        AnimationRunner runner = new AnimationRunner(new GUI("Araknoid", GameLevel.WIDTH, GameLevel.HEIGHT));
        GameFlow gameRunner = new GameFlow(runner, runner.getGui().getKeyboardSensor(), lifeCounter, scoreCounter);
        ArrayList<LevelInformation> levels = new ArrayList<>();
        levels.add(levelThree);
        gameRunner.runLevels(levels);
    }
}
