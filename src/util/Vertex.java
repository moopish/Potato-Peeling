package util;

import java.util.List;

/**
 * === Vertex Class ===
 * Quick class for a vertex and vertex related methods
 * @author Michael van Dyk
 */
public final class Vertex {

    /** x position of vertex */
    private double x;

    /** y position of vertex */
    private double y;

    /**
     *  Creates a vertex given an x and y position
     * @param x x position
     * @param y y position
     */
    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     *  Creates a copy of a given vertex
     * @param v vertex to copy
     */
    public Vertex(Vertex v) {
        this(v.x, v.y);
    }

    /**
     *  Determines if two vertices are the same
     * @param o the vertex to check against
     * @return t/f if the vertices are the same in position
     */
    public boolean equals(Vertex o) {
        return (x == o.x && y == o.y);
    }

    /**
     * @return this vertex's x position
     */
    public double getX() {
        return (x);
    }

    /**
     * @return this vertex's y position
     */
    public double getY() {
        return (y);
    }

    /**
     *  Euclidean distance of two vertices
     * @param a first vertex
     * @param b second vertex
     * @return the Euclidean distance between a and b
     */
    public static double dist(Vertex a, Vertex b) {
        return (Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2)));
    }

    /**
     *  Moves the vertex (FOR DRAWING ONLY)
     * @param x new x
     * @param y new y
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return ("(" + x + ", " + y + ")");
    }

    /**
     *  Reverses the given list
     * @param vs list to reverse
     */
    public static void reverseList(List<Vertex> vs) {
        for (int i=0; i<vs.size()/2; ++i) {
            Vertex temp = vs.get(i);
            vs.set(i, vs.get(vs.size() - 1 - i));
            vs.set(vs.size() - 1 - i, temp);
        }
    }
}
