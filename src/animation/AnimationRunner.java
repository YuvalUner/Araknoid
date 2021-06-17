// Yuval Uner 322558842

package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Yuval Uner
 * <h1> A class for running animations </h1>
 * <p> This class runs an animation loop for any given animation, using
 * the run method</p>
 */
public class AnimationRunner {
    private final GUI gui;
    private final Sleeper sleeper;

    /**
     * Constructor.
     *
     * @param gui the GUI to run the animations on.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.sleeper = new Sleeper();
    }

    /**
     * @return returns the GUI on which to run the animations.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Runs an animation until the stop condition for that animation is reached.
     *
     * @param animation the animation to run.
     */
    public void run(Animation animation) {
        // Timing the animation according to given fps of the animation.
        int millisecondsPerFrame = (int) (1000 / animation.getFps());
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
