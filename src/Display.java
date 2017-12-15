import util.*;
import util.Polygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * === Display Class ===
 * Quick display for demonstrating Goodman's peeling,
 * most of this code is adapted from an AI assignment I did.
 *
 * A lot of this is uncommented since GUI drawing and such.
 * @author Michael van Dyk
 */
class Display extends JFrame {
    private static final int VERTEX_RADIUS = 10;

    /** The thickness of the lines **/
    private static final BasicStroke CIRCLE_STROKE = new BasicStroke(2);
    /** The font used for the game **/
    private static final Font COUNT_FONT = new Font("Dialog", Font.BOLD, 30);

    /** The image to get drawn to, a buffer for the display **/
    private final BufferedImage draw;
    /** The graphic interface to actually draw to the image **/
    private final Graphics2D g2d;

    /** The display of the game, the image is displayed on this **/
    private final JLabel display;

    /** Determines the selected vertex of the polygon, or if no vertex is selected (-1)**/
    private int selected = -1;

    /** List of vertices of the polygon **/
    private final java.util.List<Vertex> vertices;

    Display(java.util.List<Vertex> init) {
        super("Polygon");

        vertices = init;

        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setLayout(null);

        draw = new BufferedImage(600, 600, BufferedImage.TYPE_4BYTE_ABGR);
        g2d = draw.createGraphics();
        g2d.setFont(COUNT_FONT);
        g2d.setStroke(CIRCLE_STROKE);

        this.pack();
        Insets insets = this.getInsets();
        this.setSize(draw.getWidth() + insets.left + insets.right, draw.getHeight() + insets.top + insets.bottom);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dimension.width/2 - getWidth()/2, dimension.height/2 - getHeight()/2);

        display = new JLabel();
        display.setSize(draw.getWidth(), draw.getHeight());
        display.setLocation(0,0);
        add(display);

        // Allows for updating the screen when moving the mouse when a vertex is selected.
        // Also updates the position of the vertex for drawing and peeling
        display.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selected != -1) {
                    vertices.get(selected).move(e.getX(), e.getY());
                    draw();
                }
            }
        });

        display.addMouseListener(new MouseAdapter() {
            /**
             * Determines if a vertex is selected.
             * @param e MouseEvent gives details on the mouse press
             */
            @Override
            public void mousePressed(MouseEvent e) {
                if (selected == -1) {
                    for (int i=0; i<vertices.size(); ++i) {
                        Vertex v = vertices.get(i);

                        if (v.getX() - VERTEX_RADIUS <= e.getX() && v.getX() + VERTEX_RADIUS >= e.getX()) {
                            if (v.getY() - VERTEX_RADIUS <= e.getY() && v.getY() + VERTEX_RADIUS >= e.getY()) {
                                selected = i;
                                break;
                            }
                        }
                    }
                }
                System.out.println(selected + " " + e.getX() + " " + e.getY());
            }


            /**
             * Release of mouse
             * @param e MouseEvent gives details on the mouse press
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                selected = -1;
                draw();
                System.out.println("released");
            }
        });

        this.setVisible(true);
    }

    void draw() {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, draw.getWidth(), draw.getHeight());

        g2d.setColor(Color.BLACK);

        //Draws the polygon pre peeling
        for (int i=0; i<vertices.size(); ++i) {
            if (selected == i) {
                g2d.setColor(Color.GRAY);
            }

            g2d.fillOval(
                    (int)vertices.get(i).getX() - VERTEX_RADIUS,
                    (int)vertices.get(i).getY() - VERTEX_RADIUS,
                    2 * VERTEX_RADIUS,
                    2 * VERTEX_RADIUS
            );

            g2d.setColor(Color.BLACK);

            g2d.drawLine(
                    (int)vertices.get(i).getX(),
                    (int)vertices.get(i).getY(),
                    (int)vertices.get((i + 1) % vertices.size()).getX(),
                    (int)vertices.get((i + 1) % vertices.size()).getY()
            );
        }

        // Runs goodman peeling to determine potato
        util.Polygon goodman = Goodman.peel(new Polygon(new ArrayList<Vertex>(vertices)));

        g2d.setColor(Color.RED);

        // Draws the potato
        for (int i=0; i<goodman.n(); ++i) {
            g2d.drawLine(
                    (int)goodman.get(i).getX(),
                    (int)goodman.get(i).getY(),
                    (int)goodman.get(i + 1).getX(),
                    (int)goodman.get(i + 1).getY()
            );
        }

        display.setIcon(new ImageIcon(draw));
    }
}
