// Yuval Uner 322558842

package gamegeometry.basetypes;

/**
 * <h1> An interface representing a sprite</h1>
 * <p>
 * Contains the following methods:
 * 1. drawOn - draws the sprite to the screen
 * 2. timePassed - notify the sprite that time has passed
 * </p>
 */
public interface Sprite extends GameObject {
    /**
     * <p>
     * notify the sprite that time has passed.
     * </p>
     */
    void timePassed();
}
