// Yuval Uner 322558842

package gamegeometry.basicgeometry;

import java.util.ArrayList;

/**
 * @author Yuval Uner
 * <h1> A rectangle in 2d space </h1>
 * <p> A class representing an instance of a rectangle in 2 dimensional
 * space. It contains the following public methods:
 * 1. Constructors
 * 2. Getters for its upper left points, the width and the height
 * 3. A method for finding all intersection points of a line with the rectangle
 * 4. Methods to return each of the lines that define the rectangle as
 * a Line object.
 * </p>
 */
public class Rectangle {

    private final Point upperLeft;
    private final double width;
    private final double height;


    /**
     * Constructor.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor.
     *
     * @param x      the x value of the upper left point
     * @param y      the y value of the upper left point
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    /**
     * @return returns the left line of the rectangle
     */
    public Line getLeftLine() {
        return new Line(upperLeft, new Point(upperLeft.getX(),
                upperLeft.getY() + height));
    }

    /**
     * @return returns the right line of the rectangle
     */
    public Line getRightLine() {
        return new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }

    /**
     * @return returns the top line of the rectangle
     */
    public Line getTopLine() {
        return new Line(upperLeft, new Point(upperLeft.getX() + width,
                upperLeft.getY()));
    }

    /**
     * @return returns the bottom line of the rectangle
     */
    public Line getBottomLine() {
        return new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }

    /**
     * @param line the line to check intersections wtih
     * @return returns a list of all the intersection points
     * <p> This method makes coverts the 4 lines which make up a rectangle,
     * and then compares the intersection of these lines with the line
     * to be compared against.
     * If there are intersection points, they are returned in a list.
     * Otherwise, null is returned
     * </p>
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // Initializing the 4 lines which make up a rectangle
        Line leftLine = getLeftLine();
        Line topLine = getTopLine();
        Line rightLine = getRightLine();
        Line bottomLine = getBottomLine();
        // Using the pre-existing intersectionWith method to get intersection points
        Point intersectionLeft = line.intersectionWith(leftLine);
        Point intersectionTop = line.intersectionWith(topLine);
        Point intersectionRight = line.intersectionWith(rightLine);
        Point intersectionBottom = line.intersectionWith(bottomLine);
        ArrayList<Point> intersectionPoints = new ArrayList<>();
        /*
         * Using boolean values to avoid duplicate points in the list, as a
         * result of intersection with corners.
         * */
        boolean sameIntersectionLeftBottom = false;
        boolean sameIntersectionLeftTop = false;
        boolean sameIntersectionRightTop = false;
        boolean sameIntersectionRightBottom = false;
        /*
         * Checking if the line intersects with the lines of the rectangle,
         * and if any 2 points are equal, only adding one of them to the
         * list.
         * */
        if (intersectionLeft != null) {
            if (intersectionBottom != null
                    && intersectionLeft.equals(intersectionBottom)) {
                sameIntersectionLeftBottom = true;
            } else if (intersectionTop != null
                    && intersectionLeft.equals(intersectionTop)) {
                sameIntersectionLeftTop = true;
            }
            intersectionPoints.add(intersectionLeft);
        }
        if (intersectionRight != null) {
            if (intersectionBottom != null
                    && intersectionRight.equals(intersectionBottom)) {
                sameIntersectionRightBottom = true;
            } else if (intersectionTop != null
                    && intersectionRight.equals(intersectionTop)) {
                sameIntersectionRightTop = true;
            }
            intersectionPoints.add(intersectionRight);
        }
        if (intersectionTop != null && (!sameIntersectionLeftTop || !sameIntersectionRightTop)) {
            intersectionPoints.add(intersectionTop);
        }
        if (intersectionBottom != null && (!sameIntersectionLeftBottom || !sameIntersectionRightBottom)) {
            intersectionPoints.add(intersectionBottom);
        }
        if (intersectionPoints.isEmpty()) {
            return null;
        } else {
            return intersectionPoints;
        }
    }

    /**
     * @param point the point to check
     * @return true if the point is within the rectangle, false otherwise
     */
    public boolean isPointInsideRectangle(Point point) {
        // Checks if the point is within the area covered by the rectangle
        return point.getX() >= upperLeft.getX() && point.getX() <= width + upperLeft.getX()
                && point.getY() >= upperLeft.getY() && point.getY() <= height + upperLeft.getY();
    }

    /**
     * @return returns the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return returns the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return returns the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
}
