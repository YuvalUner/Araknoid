package game.levels;

import game.gameessentials.GameEnvironment;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.Sprite;
import objectbehavior.Velocity;

import java.util.List;

public class LevelBullseye implements LevelInformation {

    int numberOfBalls = 3;
    int paddleWidth = 100;
    String levelName = "Bullseye";
    int amountOfBallsToSpawn = 1;
    int numberOfBlocksToRemove = 0;
    GameEnvironment environment;




    @Override
    public int numberOfBalls() {
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return 0;
    }

    @Override
    public int paddleWidth() {
        return 0;
    }

    @Override
    public String levelName() {
        return null;
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 0;
    }

    @Override
    public int getAmountOfBallsToSpawn() {
        return 0;
    }
}
