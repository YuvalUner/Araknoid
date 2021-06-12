package animation;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.*;

public class AnimationRunner {
    private final GUI gui;
    private final Sleeper sleeper;

    public AnimationRunner(GUI gui){
        this.gui = gui;
        this.sleeper = new Sleeper();
    }

    public GUI getGui() {
        return gui;
    }

    public void run(Animation animation, Color backgroundColor) {
        int millisecondsPerFrame = (int) (1000 / animation.getFps());
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d, backgroundColor);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}