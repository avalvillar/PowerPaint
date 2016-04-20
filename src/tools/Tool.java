/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package tools;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * The Tool interface which establishes the rules/methods needs for the AbstractTool class
 * and sub classes. 
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public interface Tool {
    
    /**
     * Used to get the current shape.
     * @return Shape : The current shape.
     */
    Shape getShape();
    
    /**
     * 
     * @return Color : The current color.
     */
    Color getColor();

    /**
     * 
     * @return Stroke : The current stroke.
     */
    Stroke getStroke();
    
    /**
     * Used to get the starting point (where mouse was first pressed).
     * 
     * @return getStartPoint : Starting point of a mouse press.
     */
    Point getStartPoint();
    
    /**
     * Used to get the ending point (where mouse was released).
     * 
     * @return getEndPoint : Ending point of the mouse.
     */
    Point getEndPoint();
    
    /**
     * Used to set the starting point (at mouse pressed/clicked).
     * 
     * @param thePoint : Point where the mouse was pressed/clicked.
     */
    void setStartPoint(Point thePoint);
    
    /**
     * Used to set the ending point (at mouse released).
     * 
     * @param thePoint : Point where the mouse as released.
     */
    void setEndPoint(Point thePoint);
    
    /**
     * Used to reset the tool location. 
     */
    void reset();

}
