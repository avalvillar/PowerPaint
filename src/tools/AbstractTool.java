/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package tools;

import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;

/**
 * Abstract class for all the common methods used for the sub class tools. 
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public abstract class AbstractTool implements Tool {
    
    /** The color of the tool. */
    private Color myColor;
    
    /** The stroke size for the tool. */
    private Stroke myStroke;
    
    /** The starting point. */
    private Point myStartPoint;
    
    /** The ending point. */
    private Point myEndPoint;
    
    /**
     * Constructor for a tool using a starting and ending point
     * and setting the default color to black.
     * 
     * @param thePoint : Starting point of the drawing. 
     * @param theEndPoint : Ending Point of the drawing.
     */
    public AbstractTool(final Point thePoint, final Point theEndPoint) {
        myColor = Color.BLACK;
        setStartPoint(new Point(thePoint.x - 1, thePoint.y - 1));
        setEndPoint(new Point(theEndPoint.x - 1, theEndPoint.y - 1));
    }

    /**
     * Used to set the color of the current tool. 
     * 
     * @param theColor : The selected color. 
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**
     * Used to get the color of the current tool. 
     * 
     * @return Color : The current tool color.
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * Used to set the stroke of the current tool. 
     * 
     * @param theStroke : The selected stroke. 
     */
    public void setStroke(final Stroke theStroke) {
        myStroke = theStroke;
    }
    
    /**
     * Used to get the stroke of the current tool. 
     * 
     * @return Stroke : The current tool stroke.
     */
    public Stroke getStroke() {
        return myStroke;
    }
    
    @Override
    public Point getStartPoint() {
        return (Point) myStartPoint.clone();
    }

    @Override
    public Point getEndPoint() {
        return (Point) myEndPoint.clone();
    }

    /**
     * Used to set the starting point. 
     * 
     * @param thePoint : The starting point. 
     */
    public void setStartPoint(final Point thePoint) {
        myStartPoint = (Point) thePoint.clone();
    }

    /**
     * Used to set the ending point. 
     * 
     * @param thePoint : The ending point. 
     */
    public void setEndPoint(final Point thePoint) {
        myEndPoint = (Point) thePoint.clone();
    }
    
    /**
     * Resets the points back to a negative value. This solved the last image being visible
     * even though it was not longer part of the collection after the Undo All button press.
     */
    public void reset() {
        setStartPoint(new Point(-1, -1));
        setEndPoint(new Point(-1, -1));
    }
    

}
