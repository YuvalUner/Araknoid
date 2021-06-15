package animation;

import biuoop.DrawSurface;

public class PauseScreen implements Animation {

    public PauseScreen() {
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    public boolean shouldStop() {
        return false;
    }

    @Override
    public double getFps() {
        return 60;
    }
}
