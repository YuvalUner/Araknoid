package animation;

import biuoop.DrawSurface;
import game.gameessentials.Background;
import game.levels.GameLevel;
import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;
import gamegeometry.basetypes.Sprite;

import java.awt.*;


public class CountdownAnimation implements Animation{

    private final SpriteCollection gameScreen;
    private final double numOfSeconds;
    private int countFrom;
    private final int fontSize;
    private boolean shouldStop;
    private final GameEnvironment environment;
    private final Sprite background;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
                              int fontSize, GameEnvironment environment, Sprite background){
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.fontSize = fontSize;
        this.environment = environment;
        this.background = background;
    }



    @Override
    public void doOneFrame(DrawSurface d) {
        this.shouldStop = false;
        this.background.drawOn(d);
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
