// Yuval Uner 322558842

package game.eventlisteners;

/**
 * @author Yuval Uner
 * <h1> An interface for all classes that employ hit listners</h1>
 * <p> Cotntains the signatures for the methods for adding a hit listener
 * and removing a hit listener</p>
 */
public interface HitNotifier {
    /**
     * <p> Adds a hit listener to an object's instance.</p>
     *
     * @param hl the hit listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * <p> Removes a hit listener from an object's instance hit listeners list.</p>
     *
     * @param hl the hit listener to remove
     */
    void removeHitListener(HitListener hl);
}
