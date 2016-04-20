/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package tools;

import java.awt.Point;
import java.awt.geom.Line2D;

/**
 * The Line tool class that will return its shape. 
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public class Line extends AbstractTool {
    
    
    /**
     * Construction of an Line tool that calls its own inner method. 
     */
    public Line() {
        this(new Point(0, 0), new Point(0, 0));
    }
    
    /**
     * Private constructor that calls the abstract tool constructor with selected points. 
     * 
     * @param thePoint : New starting point. 
     * @param theEndPoint : New ending point.
     */
    private Line(final Point thePoint, final Point theEndPoint) {
        super(thePoint, theEndPoint);
    }

    /**
     * Used to get the shape (in this case a line) tool that needs to be drawn on the JPanel.
     * 
     * @return Line2D.Double : The Line shape.
     */
    @Override
    public Line2D.Double getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }

}
