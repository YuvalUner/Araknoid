// Yuval Uner 322558842

package game.gameessentials;

import biuoop.DrawSurface;
import gamegeometry.basetypes.GameObject;

import java.util.ArrayList;

/**
 * @author Yuval Uner
 * <h1> A background for the game's levels</h1>
 * <p> A collection of static game objects to be drawn on the screen, serving as the
 * background for the levels of the game.</p>
 */
public class Background {

    private ArrayList<GameObject> backgroundMakeup;

    /**
     * Constructor.
     * Initializes the list in which the background's objects are stored.
     */
    public Background() {
        this.backgroundMakeup = new ArrayList<>();
    }

    /**
     * Adds a game object to the background.
     *
     * @param gameObject the game object to add.
     */
    public void addToBackground(GameObject gameObject) {
        this.backgroundMakeup.add(gameObject);
    }

    /**
     * Draws the background on the screen.
     *
     * @param d the Drawsurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        for (GameObject object : backgroundMakeup) {
            object.drawOn(d);
        }
    }
}
