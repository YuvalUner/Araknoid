// Yuval Uner 322558842

package game.gameessentials;

import biuoop.DrawSurface;
import gamegeometry.blockdecorators.BlockDecorator;
import gamegeometry.blockdecorators.BlockWithText;
import objectbehavior.Counter;

import java.awt.*;

/**
 * @author Yuval Uner
 * <h1> A score indicator for the game.</h1>
 * <p> Acts as a block with text, which is aware of the score counter of the game.</p>
 */
public class ScoreIndicator extends BlockWithText {

    private Counter scoreCounter;

    /**
     * Constructor.
     *
     * @param block        the block making the base of the score indicator.
     * @param text         the text needed for BlockWithText's constructor. Can be "".
     * @param textColor    the color of the text.
     * @param fontSize     the font size of the text.
     * @param scoreCounter the game's score counter.
     */
    public ScoreIndicator(BlockDecorator block, String text, Color textColor,
                          Counter scoreCounter, int fontSize, double x, double y) {
        super(block, text, textColor, fontSize, x, y);
        this.scoreCounter = scoreCounter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // Sets the block's text, then draws it.
        setText("Score: " + scoreCounter.getCurrentCount());
        super.drawOn(d);
    }
}
