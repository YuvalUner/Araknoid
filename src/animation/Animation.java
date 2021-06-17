// Yuval Uner 322558842

package animation;

import biuoop.DrawSurface;

/**
 * @author Yuval Uner
 * <h1> An interface for all classes which are to be animated</h1>
 * <p>
 * Describes the methods for performing a single frame of animation,
 * the method for determining when the animation should stop running,
 * and a method for getting the fps of the animation, which are needed
 * for running any animation in the context of this program.
 * </p>
 */
public interface Animation {
    /**
     * <p> Performs a single frame of animation. This method should usually be
     * looped until the shouldStop condition is hit.
     * </p>
     *
     * @param d the drawsurface to draw the frame on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return whether or not the animation should stop its running.
     */
    boolean shouldStop();

    /**
     * @return returns the fps value for the animation. Should normally be 60, but can be
     * adjusted for certain animations.
     */
    double getFps();
}
