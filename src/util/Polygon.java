package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * === Polygon Class ===
 * A simple polygon class
 * @author Michael van Dyk
 */
public class Polygon {

    /** List of vertices of the polygon in counterclockwise order */
    private final List<Vertex> vs;

    /**
     * Given a list of vertices create a polygon
     * @param vs list of vertices
     */
    public Polygon(List<Vertex> vs) {
        //Check to see if any lines cross (kinda a planar check but not fully)
        //TODO

        // Makes the vertex ordering counterclockwise if not
        if (area(vs) > 0) Vertex.reverseList(vs);

        this.vs = vs;
    }

    /**
     * Given an array of vertices create a polygon
     * @param vs vararg array of vertices
     */
    public Polygon(Vertex... vs) {
        List<Vertex> _vs = Arrays.asList(vs);

        if (area(_vs) > 0) Vertex.reverseList(_vs);

        this.vs = _vs;
    }

    /**
     * @return area of this polygon
     */
    public double area() {
        return (area(vs));
    }

    /**
     *  Remove the vertex at the given index
     * @param v the index of the vertex to remove
     * @return the new polygon without that index
     */
    public Polygon remove(int v) {
        List<Vertex> new_vs = new ArrayList<Vertex>();
        for (int i=0; i<v; ++i) {
            new_vs.add(vs.get(i));
        }
        for (int i=v + 1; i<vs.size(); ++i) {
            new_vs.add(vs.get(i));
        }
        return (new Polygon(new_vs));
    }

    /**
     *  Gets the vertex at the given index, is circular for convenience
     * @param i index of vertex to get
     * @return the vertex at the given index
     */
    public Vertex get(int i) {
        return (vs.get(Util.mod(i, vs.size())));
    }

    /**
     *  Area of polygon given a list of vertices
     * @param vs the list of vertices
     * @return the area of the given polygon
     */
    public static double area(List<Vertex> vs){
        double area = 0.0;

        for (int i=0; i<vs.size() - 1; ++i) {
            area += vs.get(i).getX() * vs.get(i+1).getY() - vs.get(i).getY() * vs.get(i+1).getX();
        }
        area += vs.get(vs.size() - 1).getX() * vs.get(0).getY() - vs.get(vs.size() - 1).getY() * vs.get(0).getX();

        return (area/2.0);
    }


    /**
     *  Area of polygon given an array of vertices
     * @param vs the vararg array of vertices
     * @return the area of the given polygon
     */
    public static double area(Vertex... vs) {
        double area = 0.0;

        for (int i=0; i<vs.length - 1; ++i) {
            area += vs[i].getX() * vs[i+1].getY() - vs[i].getY() * vs[i+1].getX();
        }
        area += vs[vs.length - 1].getX() * vs[0].getY() - vs[vs.length - 1].getY() * vs[0].getX();

        return (area/2.0);
    }

    /**
     * @return vertex count, size of polygon
     */
    public int n() {
        return (vs.size());
    }
}
