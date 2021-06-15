package animation;

import biuoop.DrawSurface;
import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;
import game.levels.GameLevel;
import gamegeometry.basetypes.Sprite;

import java.awt.*;

public class WaitingScreenAnimation implements Animation {

    private final SpriteCollection gameScreen;
    private final GameEnvironment environment;
    private final int fontSize;
    private final Sprite background;

    public WaitingScreenAnimation(SpriteCollection gameScreen,
                                  GameEnvironment gameEnvironment, int fontSize, Sprite background) {
        this.gameScreen = gameScreen;
        this.environment = gameEnvironment;
        this.fontSize = fontSize;
        this.background = background;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        this.environment.drawAllOn(d);
        d.setColor(Color.BLACK);
        d.drawText(GameLevel.WIDTH / 8, (int) (GameLevel.HEIGHT / 1.7), "Press any of the movement keys to continue",
                fontSize);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public double getFps() {
        return 60;
    }
}
