/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * This class is needed to create a custom icon that will be displayed on the 
 * menu bar next to the item for the color chooser. This icon will change colors 
 * depending on the selected color from the chooser. 
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public class ColorIcon implements Icon {
    
    /** The Width of the icon. */
    private static final int WIDTH = 15;
    
    /** The Height of the icon. */
    private static final int HEIGHT = 15;
    
    /** The Color of the icon. */
    private Color myColor;
    
    /**
     * Constructor of the color icon with default color black.
     */
    public ColorIcon() {
        myColor = Color.BLACK;
    }

    /**
     * Get the icons width. 
     * 
     * @return int : The Width of the Icon.
     */
    @Override
    public int getIconWidth() {
        return WIDTH;
    }

    /**
     * Get the icons height. 
     * 
     * @return int : The Height of the Icon. 
     */
    @Override
    public int getIconHeight() {
        return HEIGHT;
    }
    
    /**
     * Used to set the color of the icon after being selected from the chooser. 
     * 
     * @param theColor : The selected color. 
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }

    /**
     * Paints the icon the necessary color as well as setting a black border. 
     */
    @Override
    public void paintIcon(final Component theUnused, final Graphics theGraphics, 
                          final int theX, final int theY) {
        theGraphics.setColor(myColor);
        theGraphics.fillRect(theX, theY, WIDTH, HEIGHT);
        
        theGraphics.setColor(Color.BLACK);
        theGraphics.drawRect(theX, theY, WIDTH, HEIGHT);  
    }

}
