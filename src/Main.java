import util.Polygon;
import util.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * === Main Class ===
 * Runs the program.
 * Replace the value in the switch to 4 for a quadrilateral.
 * 5 for a pentagon.
 * @author Michael van Dyk
 */
public class Main {
    public static void main(String args[]) {
        List<Vertex> vs = new ArrayList<Vertex>();

        switch (5) {
            //n == 4
            case 4:
            vs.add(new Vertex(150.0, 150.0));
            vs.add(new Vertex(150.0, 450.0));
            vs.add(new Vertex(450.0, 450.0));
            vs.add(new Vertex(450.0, 150.0));
            break;

            //n == 5
            case 5:
            vs.add(new Vertex(300.0, 150.0));
            vs.add(new Vertex(450.0, 275.0));
            vs.add(new Vertex(375.0, 450.0));
            vs.add(new Vertex(225.0, 450.0));
            vs.add(new Vertex(150.0, 275.0));
            break;

            default:
                System.err.println("error in switch");
                System.exit(-1);
        }

        // Is for testing
        for (Vertex v : vs) System.out.println(v);
        System.out.println(Polygon.area(vs));

        Display ds = new Display(vs);
        ds.draw();
    }
}
