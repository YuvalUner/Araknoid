// Yuval Uner 322558842

package gamegeometry.blockdecorators;

import basicgeometry.Rectangle;
import game.eventlisteners.HitListener;
import gamegeometry.basetypes.Block;
import gamegeometry.basetypes.GameObject;

import java.util.List;

/**
 * @author Yuval Uner
 * <h1>An interface defining the basic behavior of all block decorators</h1>
 * <p>Includes the signatures for getBlock - a method for getting the block
 * that is being decorated, and isCounted - checks if the block is counted
 * towards victory in the game or not.</p>
 */
public interface BlockDecorator extends GameObject {

    /**
     * @return returns the block that is being decorated.
     */
    Block getBlock();

    /**
     * @return returns whether or not the block is counted towards victory.
     */
    boolean isCounted();

    /**
     * @return the block decorator's hit listeners.
     */
    List<HitListener> getHitListeners();

    /**
     * Removes a hit listener from the block decorator's list of hit listeners.
     *
     * @param hitListener the hit listener to remove.
     */
    void removeHitListener(HitListener hitListener);

    /**
     * @return the block decorator's collision rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * @return the score value of the block decorator.
     */
    int getScoreValue();
}
