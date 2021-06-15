package game.gameessentials;

import biuoop.DrawSurface;
import gamegeometry.blockdecorators.BlockDecorator;
import gamegeometry.blockdecorators.BlockWithText;
import objectbehavior.Counter;

import java.awt.*;

public class LifeIndicator extends BlockWithText {

    private final Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param block     the block to decorate
     * @param text      the text assigned to the block.
     * @param textColor the color of the text.
     * @param fontSize  the size of the font.
     * @param x         the x position of the text.
     * @param y         the y position of the text.
     */
    public LifeIndicator(BlockDecorator block, String text, Color textColor,
                         Counter remainingBalls, int fontSize, double x, double y) {
        super(block, text, textColor, fontSize, x, y);
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void drawOn(DrawSurface d) {
        setText("Current Lives: " + remainingBalls.getCurrentCount());
        super.drawOn(d);
    }
}
