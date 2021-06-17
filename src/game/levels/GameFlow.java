// Yuval Uner 322558842

package game.levels;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import objectbehavior.Counter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuval Uner
 * <h1> A class to handle the game's flow through the levels.</h1>
 */
public class GameFlow {

    private final AnimationRunner ar;
    private final KeyboardSensor ks;
    private final Counter lifeCounter;
    private final Counter scoreTracker;

    /**
     * Constructor.
     *
     * @param ar           the animation runner to use.
     * @param ks           the keyboard sensor to use. Should fit ar's gui.
     * @param lifeCounter  the life counter to be used throughout the game.
     * @param scoreTracker the score tracker to be used throughout the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter lifeCounter,
                    Counter scoreTracker) {
        this.ar = ar;
        this.ks = ks;
        this.lifeCounter = lifeCounter;
        this.scoreTracker = scoreTracker;
    }

    /**
     * Runs all the levels of the game, one after the other.
     *
     * @param levels a list containing the game's levels.
     */
    public void runLevels(List<LevelInformation> levels) {

        // Copying the list before modifying it
        ArrayList<LevelInformation> levelsCopy = new ArrayList<>(levels);

        /*
         * For every level in the list - initialize a GameLevel according to it,
         * then run it.
         * */
        for (LevelInformation levelInfo : levelsCopy) {

            GameLevel level = new GameLevel(levelInfo, this.ar, this.ks, lifeCounter, scoreTracker);
            level.initialize();
            level.run();
            levels.remove(levelInfo);
            // If the player is out of lives, display the "Game Over" screen.
            if (lifeCounter.getCurrentCount() == 0) {
                ar.run(new KeyPressStoppableAnimation(ks, new String[]{KeyboardSensor.SPACE_KEY},
                        new EndScreen("Game Over", scoreTracker.getCurrentCount())));
                ar.getGui().close();
                break;
                // If there are no more levels, the player won.
            } else if (levels.isEmpty()) {
                ar.run(new KeyPressStoppableAnimation(ks, new String[]{KeyboardSensor.SPACE_KEY},
                        new EndScreen("You Win!", scoreTracker.getCurrentCount())));
                ar.getGui().close();
                break;
            }
        }
    }
}
