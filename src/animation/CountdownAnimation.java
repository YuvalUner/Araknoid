// Yuval Uner 322558842

package animation;

import biuoop.DrawSurface;
import game.gameessentials.Background;
import game.gameessentials.GameEnvironment;
import game.gameessentials.SpriteCollection;
import game.levels.GameLevel;

import java.awt.Color;

/**
 * @author Yuval Uner
 * <h1> A countdown animation preceeding each stage of the game</h1>
 * <p> An animation meant to count down from x to 0, through y seconds, while
 * displaying the level in the background.</p>
 */
public class CountdownAnimation implements Animation {

    private final SpriteCollection gameScreen;
    private final double numOfSeconds;
    private int countFrom;
    private final int fontSize;
    private boolean shouldStop;
    private final GameEnvironment environment;
    private final Background background;

    /**
     * Constructor.
     *
     * @param numOfSeconds the amount of seconds the animation should last.
     * @param countFrom    the number to count down to 0 from.
     * @param gameScreen   the sprites that should be displayed on the game's screen.
     * @param environment  the environment (collidables) that should be displayed
     *                     on the game's screen.
     * @param fontSize     the size of the font for the countdown text.
     * @param background   the background of the game's level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
                              int fontSize, GameEnvironment environment, Background background) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.fontSize = fontSize;
        this.environment = environment;
        this.background = background;
        this.shouldStop = false;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        // Draws everything that should appear on the game's screen.
        this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        this.environment.drawAllOn(d);
        d.setColor(Color.BLACK);
        // Display a count to 0
        if (countFrom > 0) {
            d.drawText(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, Integer.toString(countFrom), fontSize);
            this.countFrom--;
            // Instead of 0, display "GO!" on the screen.
        } else if (countFrom == 0) {
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
