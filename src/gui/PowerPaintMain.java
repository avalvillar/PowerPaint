/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package gui;

import java.awt.EventQueue;

/**
 * Runs PowerPaint by instantiation and starting the PowerPaint GUI.
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public final class PowerPaintMain {
    
    /**
     * Private constructor to prevent instantiation of this class.
     */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }

    /**
     * Main method that invokes the PowerPaint GUI. Command line arguments are ignored. 
     * 
     * @param theArgs : The command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PowerPaintGUI();
            }
        });

    }

}
