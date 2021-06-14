// Yuval Uner 322558842

package gamegeometry.basetypes;

import biuoop.DrawSurface;
import game.levels.LevelInformation;

/**
 * @author Yuval Uner
 * <h1> An interface defining the base behavior of all objects in the game</h1>
 */
public interface GameObject {
    /**
     * A method to add the current collidable to a game.
     *
     * @param level the level to add the current collidable to
     */
    void addToLevel(LevelInformation level);

    /**
     * A method to draw the collidable on the screen.
     *
     * @param d the DrawSurface to draw on
     */
    void drawOn(DrawSurface d);


    /**
     * A method to remove the object from the game.
     *
     * @param level the level to remove the object from.
     */
    void removeFromLevel(LevelInformation level);
}
