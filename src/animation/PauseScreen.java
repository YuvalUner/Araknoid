// Yuval Uner 322558842

package animation;

import biuoop.DrawSurface;

/**
 * @author Yuval Uner
 * <h1> A pause screen animation</h1>
 * <p> Displays a simple animation while the game is suspended, when the player
 * hits the appropriate key. Should always be wrapped by KeyPressStoppableAnimation.</p>
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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
