package animation;

import biuoop.DrawSurface;
import game.levels.GameLevel;
import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;

import java.awt.*;


public class CountdownAnimation implements Animation{

    private final SpriteCollection gameScreen;
    private final double numOfSeconds;
    private int countFrom;
    private final int fontSize;
    private boolean shouldStop;
    private final GameEnvironment environment;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
                              int fontSize, GameEnvironment environment){
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.fontSize = fontSize;
        this.environment = environment;
    }



    @Override
    public void doOneFrame(DrawSurface d, Color backgroundColor) {
        this.shouldStop = false;
        d.setColor(backgroundColor);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        this.gameScreen.drawAllOn(d);
        this.environment.drawAllOn(d);
        d.setColor(Color.BLACK);
        if (countFrom > 0){
            d.drawText(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, Integer.toString(countFrom), fontSize);
            this.countFrom--;
        }
        else if (countFrom == 0){
            d.drawText(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, "GO!", fontSize);
            this.shouldStop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }

    @Override
    public double getFps() {
        return (this.countFrom / this.numOfSeconds);
    }
}
