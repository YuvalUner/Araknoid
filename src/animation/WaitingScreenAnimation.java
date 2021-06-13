package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.levels.GameLevel;
import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;
import gamegeometry.basetypes.Sprite;

import java.awt.Color;

public class WaitingScreenAnimation implements Animation{

    private final double fps;
    private final SpriteCollection gameScreen;
    private final GameEnvironment environment;
    private final int fontSize;
    private boolean stop;
    private final KeyboardSensor keyboard;
    private final Sprite background;

    public WaitingScreenAnimation(double fps, SpriteCollection gameScreen,
                                  GameEnvironment gameEnvironment, int fontSize,
                                  KeyboardSensor keyboard, Sprite background){
        this.fps = fps;
        this.gameScreen = gameScreen;
        this.environment = gameEnvironment;
        this.fontSize = fontSize;
        this.keyboard = keyboard;
        this.background = background;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.stop = false;
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                || this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                || this.keyboard.isPressed("a")
                || this.keyboard.isPressed("d")){
            this.stop = true;
        }
        this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        this.environment.drawAllOn(d);
        d.setColor(Color.BLACK);
        d.drawText(GameLevel.WIDTH / 8, (int) (GameLevel.HEIGHT / 1.7), "Press any of the movement keys to continue",
                fontSize);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public double getFps() {
        return this.fps;
    }
}
