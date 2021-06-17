// Yuval Uner 322558842

package basicgeometry;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Yuval Uner
 * <h1> A line in a 2 dimensional space </h1>
 * <p> A general line in 2d space, which can can be drawn, as well as
 * check intersections with other lines.</p>
 */
public class Line {

    private final Point start;
    private final Point end;

    /**
     * @param width  the maximum width (x value) of the GUI.
     * @param height the maximum height (y value) of the GUI.
     * @return returns a new random line within the x and y bounds.
     * <p>
     * Gets 2 random x and 2 random y values within the given width and
     * height ranges, and initializes a new line using them.
     * </p>
     */
    public static Line generateRandomLine(int width, int height) {
        Random rand = new Random();
        return new Line(rand.nextInt(width), rand.nextInt(height),
                rand.nextInt(width), rand.nextInt(height));
    }

    /**
     * Constructor.
     *
     * @param start The starting point of the line.
     * @param end   The end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     *
     * @param x1 The x value of the starting point.
     * @param y1 The y value of the starting point.
     * @param x2 the x value of the end point.
     * @param y2 the y value of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * @return returns the end point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * @return returns the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * @return returns the middle point of the line
     * <p>
     * Calculates the middle point's x value by adding
     * the start and end x values then halving the result, then
     * does the same for y.
     * Then constructs a new point with the calculated values and
     * returns it.
     * </p>
     */
    public Point middle() {
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * @param other the line to be compared to the current line.
     * @return true if 2 lines are equal, false otherwise.
     * <p>
     * Compares the start and end points using the equals method from point
     * If both the start and end points are the same, or they are the same
     * but defined in the opposite order, returns true.
     * </p>
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start())
                && this.end.equals(other.end()))
                || (this.end.equals(other.start())
                && this.start.equals(other.end())));
    }

    /**
     * @param const1     the constant of the first line.
     * @param const2     the constant of the second line.
     * @param slope      the slope of the first line.
     * @param slopeOther the slope of the second line.
     * @return returns a double representing the x value of the intersection.
     * <p>
     * Calculates the x value of the intersection point between
     * 2 lines using the mathematical formula.
     * The lines are assumed to be infinitely long for this calculation.
     * </p>
     */
    private double getIntersectionX(double const1, double const2,
                                    double slope, double slopeOther) {
        return (const2 - const1) / (slope - slopeOther);
    }

    /**
     * @param constant           the function's constant value
     * @param slope              the function's slope
     * @param xIntersectionPoint the x value of the intersection point
     * @return returns the y value at point x of a function
     */
    private double getIntersectionY(double constant, double slope,
                                    double xIntersectionPoint) {
        return slope * xIntersectionPoint + constant;
    }

    /**
     * @param line the line for which to check the way it was defined
     * @return returns true if the line's x values were put opposite to common
     * order (the smaller x value at the start, the bigger at the end), false
     * otherwise.
     */
    private boolean isOpposite(Line line) {
        return line.end().getX() < line.start().getX();
    }

    /**
     * @param line the line to be checked.
     * @return returns true if the line is horizontal, false otherwise.
     */
    private boolean isHorizontal(Line line) {
        return (line.end().getY() - line.start().getY()) == 0;
    }

    /**
     * @param line the line to be checked.
     * @return returns true if the line is vertical, false otherwise.
     */
    private boolean isVertical(Line line) {
        return (line.end().getX() - line.start().getX()) == 0;
    }

    /**
     * @param line  the line to get the constant (c) for.
     * @param slope the slope (m) value of the line.
     * @return returns the constant for use in the y = mx + c formula
     * <p>
     * Calculates constant of the line by reducing the mx value from a
     * known point with known x and y values, in this case, the starting
     * point.
     * </p>
     */
    private double getConstant(double slope, Line line) {
        return (line.start().getY() - slope * line.start().getX());
    }

    /**
     * @param other the line to be checked against
     * @return returns a point if the lines are a direct continuation of one
     * another, null otherwise.
     * <p>
     * Compares the x and y values of both start and end points, and if 1
     * directly matches the other, returns the point where they connect.
     * otherwise, returns null.
     * </p>
     */
    private Point continuation(Line other) {
        if ((this.start.getX() == other.end().getX()
                && this.start.getY() == other.end().getY())) {
            return new Point(this.start().getX(), this.start().getY());
        } else if ((this.end.getX() == other.start().getX()
                && this.end.getY() == other.start().getY())) {
            return new Point(this.end.getX(), this.end.getY());
        }
        return null;
    }

    /**
     * @param line the line to get the slope for
     * @return returns the slope of the function according to a mathematical
     * formula.
     */
    private double getSlope(Line line) {
        return (line.end().getY() - line.start().getY())
                / (line.end().getX() - line.start().getX());
    }

    /**
     * @param line               the first line to check
     * @param other              the second line to check
     * @param xIntersectionValue the x value to check for
     * @return true if x is within range of both segments, false otherwise.
     * <p>
     * Checks if a certain x value is in range of both line segments.
     * </p>
     */
    private boolean isXInRange(Line line, Line other, double xIntersectionValue) {
        /*
         * Checks the first line, and then second line, while taking into account
         * that they might be defined in a way where the x value of end is smaller
         * than the x value of start.
         * */
        double errorMargin = xIntersectionValue / 100000;
        if (!isOpposite(line)) {
            if (xIntersectionValue < line.start().getX()
                    || xIntersectionValue > line.end().getX()) {
                return false;
            }
        } else {
            if (xIntersectionValue > line.start().getX()
                    || xIntersectionValue < line.end().getX()) {
                return false;
            }
        }
        if (!isOpposite(other)) {
            if (xIntersectionValue < other.start().getX()
                    || xIntersectionValue > other.end().getX()) {
                return false;
            }
        } else {
            if (xIntersectionValue > other.start().getX()
                    || xIntersectionValue < other.end().getX()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param line               one of the lines to check for
     * @param other              the other line to check for
     * @param yIntersectionValue the y value of a point on both lines
     * @return true if the y value is within range of both lines, false otherwise.
     * <p>
     * Checks both lines to verify that a certain y value is within the range
     * of both segments.
     * </p>
     */
    private boolean isYInRange(Line line, Line other, double yIntersectionValue) {
        /*
         * Checks that the y value is within the range of line.
         * As the only methods which call this one always pass line as
         * vertical or horizontal, the slope check for y is skipped in this
         * part.
         * */
        if (!isOpposite(line)) {
            if (yIntersectionValue < line.start().getY()
                    || yIntersectionValue > line.end().getY()) {
                return false;
            }
        } else {
            if (yIntersectionValue > line.start().getY()
                    || yIntersectionValue < line.end().getY()) {
                return false;
            }
        }
        /*
         * Checking that y is within range for a line with unknown slope, by
         * accounting for both a line that was defined with end x value
         * being smaller than start x value, and the slope of the line.
         * */
        if (!isOpposite(other)) {
            if (getSlope(other) >= 0) {
                if (yIntersectionValue < other.start().getY()
                        || yIntersectionValue > other.end().getY()) {
                    return false;
                }
            } else {
                if (yIntersectionValue > other.start().getY()
                        || yIntersectionValue < other.end().getY()) {
                    return false;
                }
            }
        } else {
            if (getSlope(other) >= 0) {
                if (yIntersectionValue > other.start().getY()
                        || yIntersectionValue < other.end().getY()) {
                    return false;
                }
            } else {
                if (yIntersectionValue < other.start().getY()
                        || yIntersectionValue > other.end().getY()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param vertical the vertical line
     * @param other    the line to compare against
     * @return the intersection point if it exists, null otherwise
     * <p>
     * First, checks if the other line is also vertical.
     * If it isn't, check that it is defined for the x value of vertical.
     * If it is, check if the known intersection point of them both is
     * within the range of both segments.
     * If all conditions pass, return the intersection point.
     * Otherwise, null.
     * </p>
     */
    private Point verticalLineIntersection(Line vertical, Line other) {
        /*
         * Preceeded by the isContinuation check from intersectionWith.
         * Therefore, the lines either do not intersect, or are the same
         * line starting from a certain point
         * */
        if (isVertical(other)) {
            return null;
        }
        // Checking that x is within range of both segments
        if (isXInRange(vertical, other, vertical.start.getX())) {
            double slope = getSlope(other);
            double constant = getConstant(slope, other);
            double yIntersectionValue = slope * vertical.start().getX() + constant;
            // Checking that the intersection point is within range
            if (!isYInRange(vertical, other, yIntersectionValue)) {
                return null;
            }
            return new Point(vertical.start.getX(), yIntersectionValue);
        } else {
            return null;
        }
    }

    /**
     * @param horizontal the horizontal line
     * @param other      the line to check against
     * @return returns the intersection points between the 2 lines if it exists
     * and within the segment's range, null otherwise.
     * <p> First, checks if the other line is also horizontal. If it isn't,
     * checks that the other line is defined for the y value of the horizontal line.
     * then, checks if the known intersection point between the lines is within
     * the range of both line segments. If all conditions pass,
     * returns the intersection point. Otherwise, null.</p>
     */
    private Point horizontalLineIntersection(Line horizontal, Line other) {
        if (isHorizontal(other)) {
            return null;
        }
        // Checking that the y is within range
        if (isYInRange(horizontal, other, horizontal.start().getY())) {
            double slope = getSlope(other);
            double constant = getConstant(slope, other);
            double xIntersectionValue = (horizontal.start.getY() - constant) / slope;
            // Checking that the x is within range of both segments
            if (!isXInRange(horizontal, other, xIntersectionValue)) {
                return null;
            }
            return new Point(xIntersectionValue, horizontal.start.getY());
        } else {
            return null;
        }
    }

    /**
     * @param point the point to check
     * @return returns true if the point is on the line segment, false otherwise
     * <p>
     * Checks if the point's x and y values are within the line's x and y
     * value ranges, and if they both are, return true;
     * </p>
     */
    public boolean isPointOnLine(Point point) {
        // Checks that x is in range
        if (point.getX() >= this.start().getX() && point.getX() <= this.end().getX()) {
            // Checks the y according to the slope
            if (getSlope(this) >= 0) {
                if (point.getY() >= this.start().getY()
                        && point.getY() <= this.end().getY()) {
                    return true;
                } else {
                    return (point.getY() <= this.start().getY()
                            && point.getY() >= this.end().getY());
                }
            }
        }
        return false;
    }

    /**
     * @param horizontal the horizontal line
     * @param vertical   the vertical line
     * @return returns the intersection point of the 2 line segments if they
     * intersect.
     * <p>
     * If the intersection point of both lines on the infinite plane is within
     * the range of the segments, the point is returned.
     * Otherwise, null is returned.
     * </p>
     */
    private Point verticalAndHorizontalLineIntersection(Line vertical, Line horizontal) {
        // The intersection point of a horizontal and vertical line is known
        Point intersectionPoint = new Point(vertical.start().getX(), horizontal.start().getY());
        // Check if the point is on both line segments
        if (vertical.isPointOnLine(intersectionPoint)
                && horizontal.isPointOnLine(intersectionPoint)) {
            return intersectionPoint;
        }
        return null;
    }

    /**
     * @param other the line to be checked against
     * @return returns the intersection point if both lines intersect with one
     * another within the segments' range, null otherwise.
     * <p>
     * First, checks if the line is a direct continuation of another line.
     * Then, checks if any of the lines are horizontal or vertical,
     * and sends to a different method for those.
     * Otherwise, gets the intersection of the 2 lines, and finds if it is
     * within the scope of the segments.
     * </p>
     */
    public Point intersectionWith(Line other) {
        // Checking if one line is a direct continuation of the other
        Point isContinuation = continuation(other);
        // If continuation, return the only point where both lines connect.
        if (isContinuation != null) {
            return isContinuation;
        }
        double slope;
        double slopeOther;
        if (isVertical(this) && isHorizontal(other)) {
            return verticalAndHorizontalLineIntersection(this, other);
        } else if (isVertical(other) && isHorizontal(this)) {
            return verticalAndHorizontalLineIntersection(other, this);
        }
        if (isHorizontal(this)) {
            return horizontalLineIntersection(this, other);
        } else if (isHorizontal(other)) {
            return horizontalLineIntersection(other, this);
        } else if (isVertical(this)) {
            return verticalLineIntersection(this, other);
        } else if (isVertical(other)) {
            return verticalLineIntersection(other, this);
        } else {
            slope = getSlope(this);
            slopeOther = getSlope(other);
        }
        // Parallel lines will never intersect unless they are equal or continuations
        if (slope == slopeOther) {
            return null;
        }
        // Finding the constants of both lines
        double constantLine1 = getConstant(slope, this);
        double constantLine2 = getConstant(slopeOther, other);
        // Finding the x intersection value between the 2 lines
        double xIntersectionValue = getIntersectionX(constantLine1,
                constantLine2, slope, slopeOther);
        if (isXInRange(this, other, xIntersectionValue)) {
            return new Point(xIntersectionValue, getIntersectionY(constantLine1,
                    slope, xIntersectionValue));
        } else {
            return null;
        }
    }

    /**
     * @param other the line to be compared against.
     * @return returns true if the lines intersect within their given range,
     * false otherwise.
     * <p>
     * Runs the intersectionWith with method, and checks it's return value.
     * </p>
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    /**
     * @param rect the rectangle to check intersection against
     * @return returns the closest intersection point with the rectangle to the
     * start of the line, or null if there are no intersection points.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Getting all the intersection points with the rectangle
        ArrayList<Point> intersectionPoints = (ArrayList<Point>) rect.intersectionPoints(this);
        if (intersectionPoints == null || intersectionPoints.isEmpty()) {
            return null;
            // If there are intersection points
        } else {
            // First designate the first point as the minimum
            Point currentMinDistance = intersectionPoints.get(0);
            // Compare the distance of each point and set minimum as necessary
            for (int i = 1; i < intersectionPoints.size(); i++) {
                if (currentMinDistance.distance(this.start)
                        > intersectionPoints.get(i).distance(this.start)) {
                    currentMinDistance = intersectionPoints.get(i);
                }
            }
            return currentMinDistance;
        }
    }
}
