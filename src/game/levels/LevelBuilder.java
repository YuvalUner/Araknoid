// Yuval Uner 322558842

package game.levels;

import game.eventlisteners.ScoreTrackingListener;
import game.gameessentials.GameEnvironment;
import gamegeometry.basetypes.Block;
import basicgeometry.Rectangle;
import objectbehavior.Counter;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Yuval Uner
 * <h1> Builds the game's levels, according to the args command line arguments.</h1>
 * <p> Only has the static method for building the levels. Exists solely to make the
 * code cleaner outside of it.</p>
 */
public class LevelBuilder {

    /**
     * Builds the levels of the game, according to the string arguments given.
     * If none are given, creates 4 levels in a set order.
     *
     * @return a list with all the LevelInformation classes the game should run.
     * @param args         the command line arguments given in a main method.
     * @param scoreTracker the game's score tracking listener.
     * @param lifeCounter  the game's life counter.
     */
    public static ArrayList<LevelInformation> levelBuilder(String[] args, ScoreTrackingListener scoreTracker,
                                                           Counter lifeCounter) {
        ArrayList<LevelInformation> levels = new ArrayList<>();
        Block window = new Block(new Rectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT),
                Color.gray);
        /*
         * If a string matches the requirements, creates a new level and adds it to
         * the list which will eventually be returned.
         * */
        for (String arg : args) {
            if (arg.equals("1")) {
                levels.add(new LevelBullseye(new GameEnvironment(window, 30, Color.GRAY),
                        scoreTracker, lifeCounter));
            } else if (arg.equals("2")) {
                levels.add(new LevelEasy(new GameEnvironment(window, 30, Color.GRAY),
                        scoreTracker, lifeCounter));
            } else if (arg.equals("3")) {
                levels.add(new LevelNormal(new GameEnvironment(window, 30, Color.GRAY),
                        scoreTracker, lifeCounter));
            } else if (arg.equals("4")) {
                levels.add(new LevelHard(new GameEnvironment(window, 30, Color.GRAY),
                        scoreTracker, lifeCounter));
            }
        }
        /*
         * If not arguments were given, or all of them were illegal arguments,
         * creates 4 levels in a set order.
         * */
        if (levels.isEmpty()) {
            levels.add(new LevelBullseye(new GameEnvironment(window, 30, Color.GRAY),
                    scoreTracker, lifeCounter));
            levels.add(new LevelEasy(new GameEnvironment(window, 30, Color.GRAY),
                    scoreTracker, lifeCounter));
            levels.add(new LevelNormal(new GameEnvironment(window, 30, Color.GRAY),
                    scoreTracker, lifeCounter));
            levels.add(new LevelHard(new GameEnvironment(window, 30, Color.GRAY),
                    scoreTracker, lifeCounter));
        }
        return levels;
    }
}
