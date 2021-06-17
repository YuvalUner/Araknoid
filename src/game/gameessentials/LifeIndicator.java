// Yuval Uner 322558842

package game.gameessentials;

import biuoop.DrawSurface;
import gamegeometry.blockdecorators.BlockDecorator;
import gamegeometry.blockdecorators.BlockWithText;
import objectbehavior.Counter;

import java.awt.Color;

/**
 * @author Yuval Uner
 * <h1> An indicator for the player's remaining lives</h1>
 */
public class LifeIndicator extends BlockWithText {

    private final Counter remainingLives;

    /**
     * Constructor.
     *
     * @param block          the block to decorate
     * @param text           the text assigned to the block.
     * @param textColor      the color of the text.
     * @param fontSize       the size of the font.
     * @param x              the x position of the text.
     * @param y              the y position of the text.
     * @param remainingLives lives counter for the game.
     */
    public LifeIndicator(BlockDecorator block, String text, Color textColor,
                         Counter remainingLives, int fontSize, double x, double y) {
        super(block, text, textColor, fontSize, x, y);
        this.remainingLives = remainingLives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        setText("Current Lives: " + remainingLives.getCurrentCount());
        super.drawOn(d);
    }
}
