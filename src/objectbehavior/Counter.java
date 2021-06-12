// Yuval Uner 322558842

package objectbehavior;

/**
 * @author Yuval Uner
 * <h1> A basic counter</h1>
 * <p> Contains the following public methods:
 * 1. Constructor
 * 2. Increase the counter's value by n
 * 3. Decrease the counter's value by n
 * 4. Get the counter's current value.</p>
 */
public class Counter {

    private int currentCount;

    /**
     * Constructor.
     * Initializes a counter at 0.
     */
    public Counter() {
        this.currentCount = 0;
    }

    /**
     * Increases the current counter by a number.
     *
     * @param number the number to increase the counter by.
     */
    public void increase(int number) {
        currentCount += number;
    }

    /**
     * Decreases the current counter by a number.
     *
     * @param number the number to decrease the counter by.
     */
    public void decrease(int number) {
        currentCount -= number;
    }

    /**
     * @return the current counter's value.
     */
    public int getCurrentCount() {
        return currentCount;
    }
}
