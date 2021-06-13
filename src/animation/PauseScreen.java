package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;
    private final double fps;

    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
        this.fps = 60;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public double getFps() {
        return this.fps;
    }
}
