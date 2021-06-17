// Yuval Uner 322558842

package game.gameessentials;

import biuoop.DrawSurface;
import gamegeometry.basetypes.Sprite;

import java.util.ArrayList;

/**
 * @author Yuval Uner
 * <h1> A collection of sprites</h1>
 * <p> The collection in which all the sprites for the game are stored.</p>
 * Contains all the functionality needed for a fully functional game.
 */
public class SpriteCollection {

    private ArrayList<Sprite> spriteList;

    /**
     * Constructor.
     * Initializes the spriteList
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * @return returns the list of sprites contained in the collection.
     */
    public ArrayList<Sprite> getSpriteList() {
        return this.spriteList;
    }

    /**
     * <p>Adds a sprite to the spriteList.</p>
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * <p> Calls timePassed on all the sprites.</p>
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> sprites = new ArrayList<>(spriteList);
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * <p> Calls drawOn for all sprites.</p>
     *
     * @param d the DrawSurface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Sprite> sprites = new ArrayList<>(spriteList);
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove.
     */
    public void remove(Sprite s) {
        spriteList.remove(s);
    }
}