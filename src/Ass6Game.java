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
        Counter scoreCounter = new Counter();
        Counter lifeCounter = new Counter();
        lifeCounter.increase(7);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(scoreCounter);
        ArrayList<LevelInformation> levels = LevelBuilder.levelBuilder(args, scoreTracker, lifeCounter);
        AnimationRunner runner = new AnimationRunner(new GUI("Araknoid", GameLevel.WIDTH, GameLevel.HEIGHT));
        GameFlow gameRunner = new GameFlow(runner, runner.getGui().getKeyboardSensor(), lifeCounter, scoreCounter);
        gameRunner.runLevels(levels);
    }
}
