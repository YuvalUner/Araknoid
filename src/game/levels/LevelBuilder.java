package game.levels;

import game.eventlisteners.ScoreTrackingListener;
import game.gameessentials.GameEnvironment;
import gamegeometry.basetypes.Block;
import gamegeometry.basicgeometry.Rectangle;
import objectbehavior.Counter;

import java.awt.*;
import java.util.ArrayList;


public class LevelBuilder {

    public static ArrayList<LevelInformation> levelBuilder(String[] args, ScoreTrackingListener scoreTracker,
                                                           Counter lifeCounter) {
        ArrayList<LevelInformation> levels = new ArrayList<>();
        Block window = new Block(new Rectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT),
                Color.gray);
        for (String arg : args) {
            if (arg.equals("1")) {
                levels.add(new LevelBullseye(new GameEnvironment(window, 30, Color.GRAY),
                        scoreTracker, lifeCounter));
            } else if (arg.equals("2")) {
                levels.add(new LevelWideEasy(new GameEnvironment(window, 30, Color.GRAY),
                        scoreTracker, lifeCounter));
            } else if (arg.equals("3")) {
                levels.add(new LevelMoon(new GameEnvironment(window, 30, Color.GRAY),
                        scoreTracker, lifeCounter));
            } else if (arg.equals("4")) {
                levels.add(new LevelHell(new GameEnvironment(window, 30, Color.GRAY),
                        scoreTracker, lifeCounter));
            }
        }
        if (levels.isEmpty()) {
            levels.add(new LevelBullseye(new GameEnvironment(window, 30, Color.GRAY),
                    scoreTracker, lifeCounter));
            levels.add(new LevelWideEasy(new GameEnvironment(window, 30, Color.GRAY),
                    scoreTracker, lifeCounter));
            levels.add(new LevelMoon(new GameEnvironment(window, 30, Color.GRAY),
                    scoreTracker, lifeCounter));
            levels.add(new LevelHell(new GameEnvironment(window, 30, Color.GRAY),
                    scoreTracker, lifeCounter));
        }
        return levels;
    }
}
