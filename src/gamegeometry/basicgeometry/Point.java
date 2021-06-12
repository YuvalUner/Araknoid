// Yuval Uner 322558842

package gamegeometry.basicgeometry;

/**
 * @author Yuval Uner
 * <h1> A point in a 2 dimensional space </h1>
 * <p>
 * A class that represents a single point in a 2 dimensional space,
 * designated by an X and Y axis.
 * Its fields are the x coordinate and the y coordinate.
 * Contains the following public methods:
 * 1. Constructor to set the x and y values.
 * 2. Getters for x and y.
 * 3. distance - gets the difference from a different point.
 * 4. equals - checks equality with a different point.
 * </p>
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructor.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return returns the x value of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * @return returns the y value of the point.
     */
    public double getY() {
        return y;
    }

    /**
     * @param other the point to which distance from the current point
     *              is calculated.
     *              <p>
     *              Calculates the distance between 2 points using the mathematical
     *              formula for calculating the distance between 2 points by their
     *              x and y values.
     *              </p>
     * @return returns the distance between 2 points.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.y - other.getY(), 2)
                + Math.pow(this.x - other.getX(), 2));
    }

    /**
     * A method for setting x and y.a.
     *
     * @param newX the new x value of the point
     * @param newY the new y value of the point
     */
    public void setPoint(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    /**
     * @param other the point to compare the current point to.
     *              <p>
     *              Compares the x and y coordinates of 2 points, and if both are the
     *              same, returns true. False otherwise.
     *              </p>
     * @return Returns true if 2 points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return x == other.getX() && y == other.getY();
    }
}
