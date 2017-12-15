package util;


/**
 * === Goodman Class ===
 * Holds the peeling talked about by Goodman.
 * Very rough and does case by case. Likely room for much improvement.
 * @author Michael van Dyk
 */
public final class Goodman {
    public static Polygon peel(Polygon p) {
        if (p.n() == 4) {
            int reflex = -1;
            for (int i=0; i<p.n() && reflex == -1; ++i) {
                switch (whatTurn(p.get(i-1), p.get(i), p.get(i+1))) {
                    case LEFT:
                        reflex = i;
                        break;
                    case NO_TURN:
                        return (p.remove(i));
                    case RIGHT:
                }
            }

            if (reflex == -1) {
                return (p);
            } else {
                Vertex a = p.get(reflex - 1);
                Vertex b = p.get(reflex);
                Vertex c = p.get(reflex + 1);
                Vertex d = p.get(reflex + 2);

                Vertex e = new Line(a, d).intersect(new Line(b, c));
                Vertex f = new Line(c, d).intersect(new Line(b, a));

                System.out.println(e);
                System.out.println(f);

                return ((Math.abs(Polygon.area(a, d, f)) > Math.abs(Polygon.area(e, c, d)))
                        ? new Polygon(a, d, f)
                        : new Polygon(e, c, d));
            }
        } else if (p.n() == 5) {
            int reflex_a = -1;
            int reflex_b = -1;
            for (int i=0; i<p.n() && reflex_b == -1; ++i) {
                switch (whatTurn(p.get(i-1), p.get(i), p.get(i+1))) {
                    case LEFT:
                        if (reflex_a == -1) {
                            reflex_a = i;
                        } else {
                            reflex_b = i;
                        }
                        break;
                    case NO_TURN:
                        return (peel(p.remove(i)));
                    case RIGHT:
                }
            }

            if (reflex_a == -1) {
                // No reflex means convex
                return (p);
            } else if (reflex_b == -1) {
                // Only one reflex angle
                Polygon ret;

                Vertex a = p.get(reflex_a - 1);
                Vertex b = p.get(reflex_a);
                Vertex c = p.get(reflex_a + 1);
                Vertex d = p.get(reflex_a + 2);
                Vertex e = p.get(reflex_a + 3);

                Vertex f_ed = new Line(a, b).intersect(new Line(e, d));
                Vertex f_cd = new Line(a, b).intersect(new Line(c, d));

                Vertex g_ed = new Line(c, b).intersect(new Line(e, d));
                Vertex g_ae = new Line(c, b).intersect(new Line(a, e));

                if (Vertex.dist(f_ed, b) < Vertex.dist(f_cd, b)) {
                    if (Vertex.dist(g_ed, b) < Vertex.dist(g_ae, b)) {
                        Polygon eaf = new Polygon(e, a, f_ed);
                        Polygon dgc = new Polygon(d, g_ed, c);

                        ret = (Math.abs(eaf.area()) > Math.abs(dgc.area())) ? eaf : dgc;
                    } else {
                        Vertex h = new Line(d, b).intersect(new Line(a, e));

                        Polygon eaf = new Polygon(e, a, f_ed);
                        Polygon ehd = new Polygon(e, h, d);
                        Polygon egcd = new Polygon(e, g_ae, c, d);

                        double _eaf = Math.abs(eaf.area());
                        double _ehd = Math.abs(ehd.area());
                        double _egcd = Math.abs(egcd.area());

                        double max = Math.max(_eaf, Math.max(_ehd, _egcd));

                        if (max == _eaf) {
                            System.out.println("_eaf");
                            ret = eaf;
                        } else if (max == _ehd) {
                            System.out.println("_ehd");
                            ret = ehd;
                        } else {
                            System.out.println("_egcd");
                            ret = egcd;
                        }
                    }
                } else {
                    if (Vertex.dist(g_ed, b) < Vertex.dist(g_ae, b)) {
                        Vertex h = new Line(e, b).intersect(new Line(d, c));

                        Polygon eafd = new Polygon(e, a, f_cd, d);
                        Polygon ehd = new Polygon(e, h, d);
                        Polygon gcd = new Polygon(g_ed, c, d);

                        double _eafd = Math.abs(eafd.area());
                        double _ehd = Math.abs(ehd.area());
                        double _gcd = Math.abs(gcd.area());

                        double max = Math.max(_eafd, Math.max(_ehd, _gcd));

                        if (max == _eafd) {
                            System.out.println("eafd");
                            ret = eafd;
                        } else if (max == _ehd) {
                            System.out.println("ehd");
                            ret = ehd;
                        } else {
                            System.out.println("gcd");
                            ret = gcd;
                        }
                    } else {
                        Polygon eaf = new Polygon(e, a, f_cd, d);
                        Polygon dgc = new Polygon(d, e, g_ae, c);
                        Polygon equi = null;
                        //TODO equi-segment line polygon

                        Line fc = new Line(f_ed, c);

/*                        if (fc.parallel(new Line(e, a)) && (Math.abs())) {
                            equi = new Ver
                        }*/

                        if (equi == null) {
                            ret = (Math.abs(eaf.area()) > Math.abs(dgc.area())) ? eaf : dgc;
                        } else {
                            ret = null;
                        }
                    }
                }

                return (ret);
            } else {
                // Two reflex angles
                Polygon triangle;

                if (reflex_a + 1 == reflex_b || (reflex_b + 1) % 5 == reflex_a) {
                    //adjacent reflex angles
                    if ((reflex_b + 1) % 5 == reflex_a) {
                        int temp = reflex_b;
                        reflex_b = reflex_a;
                        reflex_a = temp;
                    }

                    Vertex a = p.get(reflex_a - 1);
                    Vertex b = p.get(reflex_a);
                    Vertex c = p.get(reflex_b);
                    Vertex d = p.get(reflex_b + 1);
                    Vertex e = p.get(reflex_b + 2);

                    Vertex f = new Line(b, c).intersect(new Line(a, e));
                    Vertex g = new Line(d, c).intersect(new Line(a, e));
                    Vertex h = new Line(b, c).intersect(new Line(d, e));
                    Vertex i = new Line(b, a).intersect(new Line(d, e));

                    Polygon aei = new Polygon(a, e, i);
                    Polygon feh = new Polygon(f, e, h);
                    Polygon ged = new Polygon(g, e, d);

                    double _aei = Math.abs(aei.area());
                    double _feh = Math.abs(feh.area());
                    double _ged = Math.abs(ged.area());

                    double max = Math.max(_aei, Math.max(_feh, _ged));

                    if (max == _aei) {
                        triangle = aei;
                    } else if (max == _feh) {
                        triangle = feh;
                    } else {
                        triangle = ged;
                    }
                } else {
                    //Non adjacent reflex, one vertex between
                    if (reflex_a + 2 != reflex_b) {
                        int temp = reflex_b;
                        reflex_b = reflex_a;
                        reflex_a = temp;
                    }

                    Vertex a = p.get(reflex_a - 1);
                    Vertex b = p.get(reflex_a);
                    Vertex c = p.get(reflex_b - 1);
                    Vertex d = p.get(reflex_b);
                    Vertex e = p.get(reflex_b + 1);

                    Vertex f = new Line(b, c).intersect(new Line(a, e));
                    Vertex g = new Line(c, d).intersect(new Line(a, e));
                    Vertex h = new Line(a, b).intersect(new Line(d, e));

                    if (Math.abs(Polygon.area(f, g, c)) > Math.abs(Polygon.area(a, e, h))) {
                        triangle = new Polygon(f, g, c);
                    } else {
                        triangle = new Polygon(a, e, h);
                    }
                }

                return (triangle);
            }
        } else {
            return (p);
        }
    }

    /**
     * Determines the turn type a->b->q
     * @param a first point
     * @param b second point
     * @param q last point
     * @return turn type
     */
    private static Turn whatTurn(Vertex a, Vertex b, Vertex q) {
        double sign = (b.getX() - a.getX()) * (q.getY() - a.getY()) - (q.getX() - a.getX()) * (b.getY() - a.getY());

        if (sign < 0) {
            return (Turn.RIGHT);
        } else if (sign > 0) {
            return  (Turn.LEFT);
        } else {
            return (Turn.NO_TURN);
        }
    }

    /**
     * Enum type for turn type
     */
    private enum Turn {
        LEFT, RIGHT, NO_TURN
    }
}
