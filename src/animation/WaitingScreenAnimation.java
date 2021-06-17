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
 * <h1> A waiting screen for when the player loses a life</h1>
 * <p> Keeps the game paused and visible, until the player moves the paddle again.
 * Should always be warpped by KeyPressStoppableAnimation.</p>
 */
public class WaitingScreenAnimation implements Animation {

    private final SpriteCollection gameScreen;
    private final GameEnvironment environment;
    private final int fontSize;
    private final Background background;

    /**
     * Constructor.
     *
     * @param gameScreen      the sprites that should be displayed on screen.
     * @param gameEnvironment the environment (collidables) which should be
     *                        displayed on screen.
     * @param fontSize        font size to use.
     * @param background      the level's background.
     */
    public WaitingScreenAnimation(SpriteCollection gameScreen,
                                  GameEnvironment gameEnvironment, int fontSize, Background background) {
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
