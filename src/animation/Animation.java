package animation;

import biuoop.DrawSurface;
import java.awt.Color;

public interface Animation {
    void doOneFrame(DrawSurface d, Color backgroundColor);
    boolean shouldStop();
    double getFps();
}
