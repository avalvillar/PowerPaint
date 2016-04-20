/*
 * TCSS 305, Fowler
 * Spring 2015
 * Assignment 5 : Power Paint
 */

package gui;

import com.sun.glass.events.KeyEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tools.EllipseAction;
import tools.LineAction;
import tools.PencilAction;
import tools.RectangleAction;

/**
 * The Power Paint GUI class will create and control what happens in the Power Paint 
 * assignment. 
 * 
 * @author Antonio V. Alvillar
 * @version 20/May/2015
 */
public class PowerPaintGUI implements PropertyChangeListener {
    
    /** Max thickness allowed on the JSlider. */
    private static final int JSLIDER_MAX = 20;
    
    /** Minimum thickness allowed on the JSlider. (no drawing at minimum). */
    private static final int JSLIDER_MIN = 0;
    
    /** Starting point for the thickness indication on the JSlider. */
    private static final int JSLIDER_DEFAULT = 1;

    /** Major tick mark on the JSlider. (this is labeled). */
    private static final int JSLIDER_MAJOR_TICK = 5;
    
    /** Minor tick mark on the JSlider. (not labeled). */
    private static final int JSLIDER_MINOR_TICK = 1;
    
    /** An array of Actions that will hold each action/action tool. */
    private final List<Action> myActions = new ArrayList<Action>();
    
    /** JFrame to be used during the program. */
    private final JFrame myFrame;

    /** JPanel to be added to the JFrame. */
    private final PowerPaintPanel myPanel;
    
    /** JMenuBar to be used to gather the menu items/buttons then added to the frame. */
    private final JMenuBar myMenuBar;
    
    /** JToolBar to be added to the bottom of the frame with certain toggle buttons. */
    private final JToolBar myToolBar;
    
    /** JMenu which will be added to the menu bar with necessary items/buttons. */
    private JMenu myMenu;
    
    /** JMenuItem which will be each option/button added to a menu then the menu bar. */
    private JMenuItem myMenuItem;
    
    /** The main undo button that will wipe out all shapes. */
    private JMenuItem myUndoAll;
    
    /** The undo button that will remove the last drawn shape (repeatedly). */
    private JMenuItem myUndo;
    
    /** The redo button that will redrawn the last shape removed (repeatedly).  */
    private JMenuItem myRedo;
    
    /**
     * Creates a new JFrame with the title "PowerPaint".
     */
    public PowerPaintGUI() {
        myFrame = new JFrame("PowerPaint");
        myMenuBar = new JMenuBar();
        myToolBar = new JToolBar();
        myPanel = new PowerPaintPanel();
        start();
    }

    /**
     * Start method. This will set a lot of the functionality of the frame and adds the 
     * necessary components. 
     */
    private void start() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setIconImage(new ImageIcon("./images/fireflyIcon.gif").getImage());
        myActions.add(new PencilAction(myPanel));
        myActions.add(new LineAction(myPanel));
        myActions.add(new RectangleAction(myPanel));
        myActions.add(new EllipseAction(myPanel));
        createToolBar();
        createMenuBar();
        myFrame.add(myPanel, BorderLayout.CENTER);
        myFrame.add(myToolBar, BorderLayout.SOUTH);
        myPanel.addPropertyChangeListener(this);
        myFrame.pack();
        myFrame.setMinimumSize(myFrame.getSize());
        /** A ToolKit used to set frame location relative to the current screen (center). */
        final Toolkit kit = Toolkit.getDefaultToolkit();
        myFrame.setLocation(
            (int) (kit.getScreenSize().getWidth() / 2 - myFrame.getWidth() / 2),
            (int) (kit.getScreenSize().getHeight() / 2 - myFrame.getHeight() / 2));
        myFrame.setVisible(true);
    }
    
    /**
     * This method will construct the entire JMenuBar to be added to the frame 
     * by using helper methods for each Menu and then adds the bar to the frame.
     */
    private void createMenuBar() {
        myMenuBar.add(createFileMenu());
        myMenuBar.add(createOptionsMenu());
        myMenuBar.add(createToolsMenu());
        myMenuBar.add(createHelpMenu());
        myFrame.setJMenuBar(myMenuBar);
    }
    
    /**
     * Creates the File Menu and the necessary item/buttons/functionality.
     * The property change listener will be used in conjunction with the buttons below, 
     * UndoAll, Undo, and Redo. They also have accelerator keys. 
     * 
     * @return JMenu : The new JMenu that will be added to the Menu Bar.
     */
    private JMenu createFileMenu() {
        myMenu = new JMenu("File");
        myMenu.setMnemonic(KeyEvent.VK_F);
        myUndoAll = new JMenuItem("Undo all changes", KeyEvent.VK_U);
        myUndoAll.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanel.clearShapes();
                myPanel.repaint();
            }
        });
        myUndo = new JMenuItem("Undo", KeyEvent.VK_N);
        myUndo.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanel.undo();
                myPanel.repaint();
            }
        });
        myUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 
                                                          ActionEvent.CTRL_MASK));
        myRedo = new JMenuItem("Redo", KeyEvent.VK_R);
        myRedo.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanel.redo();
                myPanel.repaint();
            }
        });
        myRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 
                                                          ActionEvent.CTRL_MASK));
        myUndoAll.setEnabled(false);
        myMenu.add(myUndoAll);
        myMenu.addSeparator();
        myUndo.setEnabled(false);
        myMenu.add(myUndo);
        myRedo.setEnabled(false);
        myMenu.add(myRedo);
        myMenu.addSeparator();
        myMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        myMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myFrame.dispatchEvent(new WindowEvent(myFrame,
                                                      WindowEvent.WINDOW_CLOSING));
            }
        });
        myMenu.add(myMenuItem);
        return myMenu;
    }
    
    /**
     * Creates the Options Menu and the necessary item/buttons/functionality.
     * Including a call to another method that will create a sub menu. 
     * 
     * @return JMenu : The new JMenu that will be added to the bar.
     */
    private JMenu createOptionsMenu() {
        myMenu = new JMenu("Options");
        myMenu.setMnemonic(KeyEvent.VK_O);
        myMenuItem = new JCheckBoxMenuItem("Grid");
        myMenuItem.setMnemonic(KeyEvent.VK_G);
        myMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanel.setGrid();
            }
        });
        myMenu.add(myMenuItem);
        myMenu.addSeparator();
        myMenu.add(createSubMenu()); 
        final ColorIcon icon = new ColorIcon();
        myMenuItem = new JMenuItem("Color...", KeyEvent.VK_C);
        myMenuItem.setIcon(icon);
        myMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                final Color color = JColorChooser.showDialog(myFrame, 
                                                             "Pick a Color!", Color.BLACK);
                if (color != null) {
                    myPanel.setToolColor(color);
                    icon.setColor(color);
                }
            }
        });
        myMenu.addSeparator();
        myMenu.add(myMenuItem);
        return myMenu;
    }
    
    /**
     * Creates the sub Menu for the Thickness Slider and adds a listener for the change 
     * on the slider in order to adjust the thickness being used by the tools. 
     * 
     * @return JMenu : A new sub menu that will be added to Options Menu.
     */
    private JMenu createSubMenu() {
        final JMenu subMenu = new JMenu("Thickness");
        subMenu.setMnemonic(KeyEvent.VK_T);
        final JSlider slider = new JSlider(JSlider.HORIZONTAL, 
                                           JSLIDER_MIN, JSLIDER_MAX, JSLIDER_DEFAULT);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent theEvent) {
                final JSlider source = (JSlider) theEvent.getSource();
                final int thickness = (int) source.getValue();
                myPanel.setThickness(thickness);
            }
        });
        slider.setMajorTickSpacing(JSLIDER_MAJOR_TICK);
        slider.setMinorTickSpacing(JSLIDER_MINOR_TICK);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        subMenu.add(slider);
        return subMenu;
    }
    
    /**
     * Creates the Tools Menu and the necessary item/buttons/functionality and adds them to 
     * a button group for exclusive selection.
     * 
     * @return JMenu : A new JMenu to be added to the menu bar.
     */
    private JMenu createToolsMenu() {
        myMenu = new JMenu("Tools");
        myMenu.setMnemonic(KeyEvent.VK_T);
        final ButtonGroup buttonGroup = new ButtonGroup();
        for (final Action action : myActions) {
            final JRadioButtonMenuItem radioButton = new JRadioButtonMenuItem(action);
            buttonGroup.add(radioButton);
            myMenu.add(radioButton);
        }
        return myMenu;
    }
    
    /**
     * Creates the Help menu and the About JOptionPane dialog box. 
     * 
     * @return JMenu : A new JMenu to be added to the menu bar.
     */
    private JMenu createHelpMenu() {
        myMenu = new JMenu("Help");
        myMenu.setMnemonic(KeyEvent.VK_H);
        myMenuItem = new JMenuItem("About...", KeyEvent.VK_A);
        myMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, "TCSS 305 PowerPaint, Spring 2015\n"
                                + "\"Well, my time of not taking you "
                                + "seriously is coming to a middle.\" \n - Mal", 
                                "ABOUT", 
                                JOptionPane.INFORMATION_MESSAGE, 
                                new ImageIcon("./images/firefly.gif"));
            }
        });
        myMenu.add(myMenuItem);
        return myMenu;
    }
    
    /**
     * This method constructs the ToolBar completely and add the buttons to a button group
     * for exclusive selection.
     */
    private void createToolBar() {
        final ButtonGroup buttonGroup = new ButtonGroup();
        for (final Action action : myActions) {
            final JToggleButton button = new JToggleButton(action);
            buttonGroup.add(button);
            myToolBar.add(button);
        }
        
    }
    
    /**
     * Property change listener for the panel. This will check whether a shape has been drawn 
     * on the panel and also if a shape has been remove. This factors will determine whether 
     * or not the UndoAll, Undo and Redo buttons should be enabled. 
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("shape".equals(theEvent.getPropertyName())) {
            if ((int) theEvent.getNewValue() > 0) {
                myUndoAll.setEnabled(true);
                myUndo.setEnabled(true);
                
            } else {
                myUndoAll.setEnabled(false);
                myUndo.setEnabled(false);
                myRedo.setEnabled(false);
            }
        } else if ("undo".equals(theEvent.getPropertyName()) 
                        || "redo".equals(theEvent.getPropertyName())) {
            if ((int) theEvent.getNewValue() > 0) {
                myRedo.setEnabled(true);
                
            } else {
                myRedo.setEnabled(false);
            }
        } 
    }
}
