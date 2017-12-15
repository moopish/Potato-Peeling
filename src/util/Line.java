package util;

/**
 * === Line Class ===
 * A simple line class
 * @author Michael van Dyk
 */
public class Line {

    /** Slope fo the line */
    private final double slope;

    /** The y intercept of the line */
    private final double yIntercept;

    /** Meant to help with vertical lines */
    private final double x;

    /**
     *  Creates a line given two vertices
     * @param a first vertex
     * @param b second vertex
     */
    public Line(Vertex a, Vertex b) {
        slope = (b.getY() - a.getY()) / (b.getX() - a.getX());
        yIntercept = b.getY() - slope * b.getX();
        x = b.getX();
    }

    /**
     * Evaluates line function at a given x
     * @param x x to evaluate at
     * @return the value of the function at the given x
     */
    public double eval(double x) {
        return (slope * x + yIntercept);
    }

    public boolean parallel(Line o) {
        return (o.slope == slope);
    }

    /**
     *  If this line intersects with the given line
     * @param o line to check against
     * @return point of intersection
     */
    public Vertex intersect(Line o) {
        if (slope == Double.POSITIVE_INFINITY || slope == Double.NEGATIVE_INFINITY) {
            return (new Vertex(x, o.eval(x)));
        } else if (o.slope == Double.POSITIVE_INFINITY || o.slope == Double.NEGATIVE_INFINITY) {
            return (new Vertex(x, eval(x)));
        }
        double x = (o.yIntercept - yIntercept)/(slope - o.slope);
        return (new Vertex(x, eval(x)));
    }
}
