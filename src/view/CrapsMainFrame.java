package view;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.sun.glass.events.KeyEvent;

public class CrapsMainFrame extends JFrame {
	CrapsMenu crapsMenu;
	CrapsToolBarPanel crapsToolBar;
	RollingDicePanel dicePanel;
	
	public CrapsMainFrame() {
		crapsMenu = new CrapsMenu();
		crapsToolBar = new CrapsToolBarPanel();
		dicePanel = new RollingDicePanel();
		
		// enable menu bar
		setJMenuBar(crapsMenu);
		
		// add tool bar
		add(crapsToolBar, BorderLayout.NORTH);
		
		// add dice
		add(dicePanel, BorderLayout.SOUTH);
		
		// set details of craps frame
		setTitle("Craps Game");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public CrapsToolBarPanel getCrapsToolBar() {
		return crapsToolBar;
	}
}
