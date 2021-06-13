// Yuval Uner 322558842

package game.eventlisteners;

import gamegeometry.basetypes.Ball;
import gamegeometry.basetypes.Block;
import gamegeometry.blockdecorators.BlockDecorator;

/**
 * @author Yuval Uner
 * <h1> An interface defining all hit listners for the game</h1>
 * <p> Describes the one method all hit listeners should perform, that upon
 * a block being hit, they perform an action relevant to the listener type</p>
 */
public interface HitListener {
    /**
     * <p>This method is called whenever the beingHit object is hit, and will
     * perform the relevant action to the class that should be performed
     * when the beingHit object is hit.</p>
     *  @param beingHit the block that was hit
     * @param hitter   the ball that hit the block*/
    void hitEvent(Block beingHit, Ball hitter);
}
