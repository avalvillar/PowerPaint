/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package tools;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

/**
 * The Pencil tool class that will return its shape. 
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public class Pencil extends AbstractTool {
    
    /** The path of the pencil. */
    private Path2D.Double myPath;
    
    /** The start of my pencil. */
    private Point myStartPoint;
    
    /** The end of my pencil. */
    private Point myEndPoint;
    
    /**
     * Construction of an Pencil tool that calls its own inner method. 
     */
    public Pencil() {
        this(new Point(0, 0), new Point(0, 0));
    }
    
    /**
     * Private constructor that calls the abstract tool constructor with selected points. 
     * 
     * @param thePoint : New starting point. 
     * @param theEndPoint : New ending point.
     */
    private Pencil(final Point thePoint, final Point theEndPoint) {
        super(thePoint, theEndPoint);
        myPath = new Path2D.Double();
        myStartPoint = (Point) thePoint.clone();
        myEndPoint = new Point(0, 0);
    }

    /**
     * Used to get the shape (in this case a pencil) that needs to be drawn on the JPanel.
     * 
     * @return Path2D.Double : The Pencil shape.
     */
    @Override
    public Path2D.Double getShape() {
        if (myStartPoint.x != getStartPoint().getX() 
                        || myStartPoint.y != getStartPoint().getY()) {
            myPath = new Path2D.Double();
            myPath.moveTo(getStartPoint().getX(), getStartPoint().getY());
            myEndPoint = new Point(getStartPoint().x, getStartPoint().y);
            myStartPoint = getStartPoint();
        }
        final Point endPoint = new Point(getEndPoint().x, getEndPoint().y);
        myPath.append(new Line2D.Double(myEndPoint, endPoint), false);
        myEndPoint = (Point) getEndPoint().clone();
        return myPath;
    }

}
