/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package tools;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

/**
 * The Ellipse tool class that will return its shape. 
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public class Ellipse extends AbstractTool {
    
    /**
     * Construction of an Ellipse tool that calls its own inner method. 
     */
    public Ellipse() {
        this(new Point(0, 0), new Point(0, 0));
    }
    
    /**
     * Private constructor that calls the abstract tool constructor with selected points. 
     * 
     * @param thePoint : New starting point. 
     * @param theEndPoint : New ending point.
     */
    private Ellipse(final Point thePoint, final Point theEndPoint) {
        super(thePoint, theEndPoint);
    }

    /**
     * Used to get the shape (in this case an Ellipse) that needs to be drawn on the JPanel.
     * 
     * @return Ellipse2D.Double : The Ellipse Shape.
     */
    @Override
    public Ellipse2D.Double getShape() {
        final Ellipse2D.Double ellipse = new Ellipse2D.Double();
        ellipse.setFrameFromDiagonal(getStartPoint().getX(), getStartPoint().getY(), 
                                     getEndPoint().getX(), getEndPoint().getY());
        return ellipse;
    }

}
