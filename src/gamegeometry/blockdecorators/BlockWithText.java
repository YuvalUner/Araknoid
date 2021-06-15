// Yuval Uner 322558842

package gamegeometry.blockdecorators;

import biuoop.DrawSurface;

import java.awt.*;

/**
 * @author Yuval Uner
 * <h1> A decorator for blocks that contain and are drawn with text</h1>
 */
public class BlockWithText extends BasicBlockDecorator {

    private String text;
    private final Color textColor;
    private final int fontSize;
    private final double x;
    private final double y;

    /**
     * Constructor.
     *
     * @param block     the block to decorate
     * @param text      the text assigned to the block.
     * @param textColor the color of the text.
     * @param fontSize  the size of the font.
     */
    public BlockWithText(BlockDecorator block, String text, Color textColor, int fontSize,
                         double x, double y) {
        super(block);
        this.text = text;
        this.textColor = textColor;
        this.fontSize = fontSize;
        this.x = x;
        this.y = y;
    }

    /**
     * @return the text assigned to the block.
     */
    public String getText() {
        return text;
    }

    /**
     * @param newText the string to change the text into.
     */
    public void setText(String newText) {
        this.text = newText;
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        /*
         * Makes the text relatively centralized vertically, and stick to the right
         * corner of the block.
         * */
        d.setColor(textColor);
        d.drawText((int) this.x, (int) this.y, this.text, fontSize);
    }
}
