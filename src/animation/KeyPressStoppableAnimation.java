package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {

    private final KeyboardSensor keyboard;
    private final String[] keys;
    private final Animation animation;
    private boolean shouldStop;
    private boolean isAlreadyPressed;

    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String[] keys, Animation animation) {
        this.keyboard = keyboard;
        this.keys = keys;
        this.animation = animation;
        this.shouldStop = false;
        this.isAlreadyPressed = true;
    }

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
        if (isAlreadyPressed) {
            shouldContinue();
        }
        animation.doOneFrame(d);
        for (String key : keys) {
            if (keyboard.isPressed(key) && !isAlreadyPressed) {
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
