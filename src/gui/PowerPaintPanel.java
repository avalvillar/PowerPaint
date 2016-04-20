/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.swing.JPanel;

import tools.Pencil;
import tools.Tool;

/**
 * The Power Paint Panel class will be the man canvas to be used during the program
 * and panel will also be passed to the actions for use as that selected tool is in use. 
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public class PowerPaintPanel extends JPanel {
    

    /** Generated serial number. */
    private static final long serialVersionUID = -5134352287025346453L;

    /** The JPanel's preferred size as a constant for easy changing. */
    private static final Dimension DEFAULT_SIZE = new Dimension(400, 200);

    /** The difference between lines of the grid. Stored as a constant for flexibility. */
    private static final int PIXEL_DIFFERENCE = 10;
    
    /** An array of all the shapes. */
    private final List<PowerPaintShape> myShapes;
    
    /**A Deque to hold shapes for the Undo/redo buttons. */
    private final Deque<PowerPaintShape> myRemovedShapes;
    
    /** Status of whether the Grid button on the menu was selected. */
    private boolean myGrid;
    
    /** The thickness of the strokes of the tools. */
    private int myThickness = 1;
    
    /** The Tool that will be used/selected at any given time. */
    private Tool myTool;

    /** The Tool's color which changes what color is being applied to the panel. */
    private Color myToolColor;

    /**
     * Constructor of the paint panel with certain defaults as well as initializing
     * the tool and tool color. Also, adding the listeners and adjusting the cross hair.
     */
    protected PowerPaintPanel() {
        super();
        myTool = new Pencil();
        myToolColor = Color.BLACK;
        myShapes = new ArrayList<PowerPaintShape>();
        myRemovedShapes = new ArrayDeque<PowerPaintShape>();
        setBackground(Color.WHITE);
        setPreferredSize(DEFAULT_SIZE);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        addTheListener();
    }
    
    /**
     * Method to add the two needed listeners to the panel. Mouse and Mouse motion 
     * in order to keep track of button pressing and dragging. 
     */
    private void addTheListener() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent theEvent) {
                myTool.setStartPoint(theEvent.getPoint());
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(final MouseEvent theEvent) {
                myTool.setEndPoint(theEvent.getPoint());
                repaint();
            }
        });
        
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent theEvent) {
                myTool.setEndPoint(theEvent.getPoint());
                if (myThickness > 0) {
                    myShapes.add(new PowerPaintShape(myTool.getShape(), 
                                                     myToolColor, myThickness));
                    myTool.reset();
                }
                repaint();
            }
        });
        
    }
    
    /**
     * Method to set the myGrid to TRUE and call repaint to place the grid on the panel.
     */
    public void setGrid() {
        myGrid ^= true;
        repaint();
    }
    
    /**
     * Method to change/set the tool being currently used. 
     * 
     * @param theTool : The selected tool. 
     */
    public void setTool(final Tool theTool) {
        myTool = theTool;
    }
    
    /**
     * Method to change/set the color of the currently being used
     * tool in order to apply other colors to the JPanel.
     * 
     * @param theColor : The selected color. 
     */
    public void setToolColor(final Color theColor) {
        myToolColor = theColor;
        
    }
    
    /**
     * Sets the new stroke thickness for the drawing tool by way of the JSlider.
     * 
     * @param theThickness : The Thickness selected.
     */
    public void setThickness(final int theThickness) {
        myThickness = theThickness;
    }
    
    /**
     * Method to empty the array of shapes. Then repaint the panel without shapes (empty). 
     */
    public void clearShapes() {
        myShapes.clear();
        myRemovedShapes.clear();
        repaint();
    }
    
    /**
     * Remove the last shape added to myShapes and place it in the myRemovedShapes deque.
     */
    protected void undo() {
        final PowerPaintShape lastDrawnShape = myShapes.get(myShapes.size() - 1);
        myRemovedShapes.add(lastDrawnShape);
        myShapes.remove(lastDrawnShape);
        firePropertyChange("undo", null, myRemovedShapes.size());
    }
    
    /**
     * Remove the last shape added to the myRemovedShapes deque and add it back to myShapes.
     */
    protected void redo() {
        final PowerPaintShape lastAddedShape = myRemovedShapes.getLast();
        myShapes.add(lastAddedShape);
        myRemovedShapes.remove(lastAddedShape);
        firePropertyChange("redo", null, myRemovedShapes.size());
    }

    /**
     * The PaintComponent method that will actually apply the drawing
     * painting being done on the JPanel. Also, set Anti-Aliasing for smoother lines, 
     * sets the color, the stroke and then draws the shapes. 
     * 
     * @param theGraphics : The Graphics object passed in during painting. (see repaint());
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        for (final PowerPaintShape shapes : myShapes) {
            g2d.setColor(shapes.getColor());
            g2d.setStroke(new BasicStroke(shapes.getStroke())); 
            g2d.draw(shapes.getShape());
        }
        if (myThickness > 0) {
            g2d.setColor(myToolColor);
            g2d.setStroke(new BasicStroke(myThickness));
            g2d.draw(myTool.getShape());
        }
        if (myGrid) {
            g2d.setStroke(new BasicStroke(1));
            g2d.setColor(Color.GRAY);
            for (int i = 1; i < getWidth(); i++) { 
                g2d.drawLine(i * PIXEL_DIFFERENCE, 0, i * PIXEL_DIFFERENCE, getHeight());
                g2d.drawLine(0, i * PIXEL_DIFFERENCE, getWidth(), i * PIXEL_DIFFERENCE);
            }
        }
        firePropertyChange("shape", null, myShapes.size());
    }
}
