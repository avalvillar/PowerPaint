/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package tools;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 * The Rectangle tool class that will return its shape. 
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public class Rectangle extends AbstractTool {
    
    /**
     * Construction of an Rectangle tool that calls its own inner method. 
     */
    public Rectangle() {
        this(new Point(0, 0), new Point(0, 0));
    }
    
    /**
     * Private constructor that calls the abstract tool constructor with selected points. 
     * 
     * @param thePoint : New starting point. 
     * @param theEndPoint : New ending point.
     */
    private Rectangle(final Point thePoint, final Point theEndPoint) {
        super(thePoint, theEndPoint);
    }

    /**
     * Used to get the shape (in this case a rectangle) that needs to be drawn on the JPanel.
     * 
     * @return Rectangle2D.Double : The Rectangle shape.
     */
    @Override
    public Rectangle2D.Double getShape() {
        final Rectangle2D.Double rectangle = new Rectangle2D.Double();
        rectangle.setFrameFromDiagonal(getStartPoint().getX(), getStartPoint().getY(), 
                                       getEndPoint().getX(), getEndPoint().getY());
        return rectangle;
    }

}
