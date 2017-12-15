# Potato-Peeling
Currently an implementation (not complete) of the Potato Peeling Problem solution presented by Jacob E. Goodman in his paper "On the Largest Convex Polygon Contained in a Non-Convex n-Gon, or How to Peel a Potato" for polygons with at most 5 vertices.

## How to Use
Simply run the main method. A window should appear in which you can adjust the positions of vertices by clicking and dragging. There are two polygons currently available to mess around with. A quadrilateral and a pentagon. To use the quadrilateral set the switch value in the main function to 4 and to use the pentagon set that value to 5.

## What is it doing?
It it finding the 'potato' or the largest convex polygon within the given simple polygon. You will see what it determines as the potato as a polygon drawn with red. The initial polygon will be drawn in black.

## Issues
If you construct something that is not planar it will have issues. Vertical lines are still an issue sometimes. It does not find the right potato for cases where the potato has an edge determined by the equi-segmental line determined by a reflex angle.
