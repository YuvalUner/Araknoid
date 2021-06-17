// Yuval Uner 322558842

package animation;

import biuoop.DrawSurface;
import game.levels.GameLevel;

import java.awt.Color;

/**
 * @author Yuval Uner
 * <h1> An end screen animation for the game</h1>
 * <p> Displays at the end of the game, showing whether the player won or lost,
 * as well as their final score. This class should always be wrapped by
 * keyPressStoppableAnimation.</p>
 */
public class EndScreen implements Animation {

    private final int score;
    private final String text;

    /**
     * Constructor.
     *
     * @param text  the text to display at the end of the game. Should usually be
     *              "Game Over" or "You Won!".
     * @param score the player's end score.
     */
    public EndScreen(String text, int score) {
        this.text = text;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.red);
        d.drawText(GameLevel.WIDTH / 2 - 100, 150, this.text, 40);
        d.setColor(Color.black);
        d.drawText(GameLevel.WIDTH / 3 + 50, 200, "Final Score: " + score, 20);
        d.setColor(Color.BLUE);
        d.drawText(GameLevel.WIDTH / 3, 500, "Press space to exit", 30);
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
