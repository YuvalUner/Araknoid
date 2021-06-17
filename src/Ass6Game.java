// Yuval Uner 322558842

import animation.AnimationRunner;
import biuoop.GUI;
import game.eventlisteners.ScoreTrackingListener;
import game.levels.GameFlow;
import game.levels.GameLevel;
import game.levels.LevelBuilder;
import game.levels.LevelInformation;
import objectbehavior.Counter;

import java.util.ArrayList;

/**
 * @author Yuval Uner
 * <h1>A class with only a main method to run the game</h1>
 * <p> Important note: It is possible to hit "m" when running the game to skip to
 * the next level. This was made for testing, and kept for ease of testing.
 * Feature should be removed if not in testing.</p>
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
        // Initializing everything needed for the game.
        Counter scoreCounter = new Counter();
        Counter lifeCounter = new Counter();
        lifeCounter.increase(7);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(scoreCounter);
        // Building the levels according to the command line arguments.
        ArrayList<LevelInformation> levels = LevelBuilder.levelBuilder(args, scoreTracker, lifeCounter);
        AnimationRunner runner = new AnimationRunner(new GUI("Araknoid", GameLevel.WIDTH, GameLevel.HEIGHT));
        GameFlow gameRunner = new GameFlow(runner, runner.getGui().getKeyboardSensor(), lifeCounter, scoreCounter);
        gameRunner.runLevels(levels);
    }
}
