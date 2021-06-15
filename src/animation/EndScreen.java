package animation;

import biuoop.DrawSurface;
import game.levels.GameLevel;

import java.awt.*;

public class EndScreen implements Animation {

    private final int score;
    private final String text;

    public EndScreen(String text, int score) {
        this.text = text;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.red);
        d.drawText(GameLevel.WIDTH / 2 - 100, 150, this.text, 40);
        d.setColor(Color.black);
        d.drawText(GameLevel.WIDTH / 3 + 50, 200, "Final Score: " + score, 20);
        d.setColor(Color.BLUE);
        d.drawText(GameLevel.WIDTH / 3, 500, "Press space to exit", 30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public double getFps() {
        return 60;
    }
}
