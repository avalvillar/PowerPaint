/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package gui;

import java.awt.Color;
import java.awt.Shape;

/**
 * The Power Paint Shape class will create and store the necessary information for each 
 * shape that is drawn. Fields stored are Shape, Color, Stroke (thickness). 
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public class PowerPaintShape {
    
    /** The shape of the object. */
    private final Shape myShape;
    
    /** The color of the shape. */
    private final Color myColor;
    
    /** The stroke (thickness) of the shape. */
    private final int myStroke;

    /**
     * The Power Paint Shape constructor used to create a power paint shape with the passed
     * information about the current shape. 
     * 
     * @param theShape : The shape that was drawn. 
     * @param theColor : The color of the shape. 
     * @param theStroke : The stroke (thickness) of the shape.
     */
    public PowerPaintShape(final Shape theShape, 
                           final Color theColor, final int theStroke) {
        myShape = theShape;
        myColor = theColor;
        myStroke = theStroke;
    }
    
    /**
     * Get the shape that was drawn. 
     * 
     * @return Shape : The shape. 
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * Get the color of the shape. 
     * 
     * @return Color : The color of the shape. 
     */
    public Color getColor() {
        return myColor;
    }

    /**
     * Get the stroke (thickness) of the shape. 
     * 
     * @return int : The stroke of the shape. 
     */
    public int getStroke() {
        return myStroke;
    }

}
