package game.levels;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import objectbehavior.Counter;

import java.util.ArrayList;
import java.util.List;

public class GameFlow {

    private final AnimationRunner ar;
    private final KeyboardSensor ks;
    private final Counter lifeCounter;
    private final Counter scoreTracker;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter lifeCounter,
                    Counter scoreTracker){
        this.ar = ar;
        this.ks = ks;
        this.lifeCounter = lifeCounter;
        this.scoreTracker = scoreTracker;
    }

    public void runLevels(List<LevelInformation> levels){

        ArrayList<LevelInformation> levelsCopy = new ArrayList<>(levels);

        for (LevelInformation levelInfo: levelsCopy){

            GameLevel level = new GameLevel(levelInfo, this.ar, this.ks, lifeCounter, scoreTracker);
            level.initialize();
            level.run();
            levels.remove(levelInfo);
            if (lifeCounter.getCurrentCount() == 0){
                ar.getGui().close();
            }
            if (levels.isEmpty()){
                break;
            }
        }
    }
}
