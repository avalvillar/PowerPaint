/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package tools;

import com.sun.glass.events.KeyEvent;
import gui.PowerPaintPanel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 * The Ellipse Action class extend Abstract Action. 
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public class EllipseAction extends AbstractAction {
   
    
    /** Generated serial number. */
    private static final long serialVersionUID = 7985226247478523799L;
    
    /** Used for the passed in panel that will need to have the tool applied. */
    private final PowerPaintPanel myPanel;
    
    /**
     * Ellipse action constructor that sets the name and icon image on the button/item 
     * and also sets the mnemonic key. While adding it to a grouping to have the same 
     * buttons not conflict with each other by working together. 
     * 
     * @param thePanel : The Power Paint Panel this tool will be using.
     */
    public EllipseAction(final PowerPaintPanel thePanel) {
        super("Ellipse", new ImageIcon("./images/ellipse_bw.gif"));
        myPanel = thePanel;
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(Action.SELECTED_KEY, false);
    }

    /**
     * Action performed method will create the new tool if the button/item is selected.
     * 
     * @param theEvent : The button/item select that chooses a tool.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setTool(new Ellipse());
    }

}