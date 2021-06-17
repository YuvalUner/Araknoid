// Yuval Uner 322558842

package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Yuval Uner
 * <h1> A decorator for animations the can be stopped by a key press</h1>
 * <p> For all animations that should be stoppable by a key press, this decorator
 * must wrap them. Otherwise, those animations will become permanent loops.</p>
 */
public class KeyPressStoppableAnimation implements Animation {

    private final KeyboardSensor keyboard;
    private final String[] keys;
    private final Animation animation;
    private boolean shouldStop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     *
     * @param keyboard  the keyboard sensor to use.
     * @param keys      the keys which stop the animation upon being pressed.
     * @param animation the animation to wrap.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String[] keys, Animation animation) {
        this.keyboard = keyboard;
        this.keys = keys;
        this.animation = animation;
        this.shouldStop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Changes the isAlreadyPressed boolean if none of the keys that stop the
     * animation are pressed. Meant to prevent accidentally ending animations
     * because the key is already pressed as a result of a previous animation.
     */
    private void shouldContinue() {
        int count = 0;
        for (String key : keys) {
            if (!keyboard.isPressed(key)) {
                count++;
            }
        }
        if (count == keys.length) {
            isAlreadyPressed = false;
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Check if isAlreadyPressed should be set to false.
        if (isAlreadyPressed) {
            shouldContinue();
        }
        animation.doOneFrame(d);
        // Checks if the key is pressed and not pressed as a result of a previous animation.
        for (String key : keys) {
            if (!isAlreadyPressed && keyboard.isPressed(key)) {
                this.shouldStop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }

    @Override
    public double getFps() {
        return 60;
    }
}
