package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.MainFrame;

public class ExitButtonListener implements ActionListener, KeyListener {
	MainFrame mainFrame;
	
	public ExitButtonListener (MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame exit = new JFrame();
		int n = JOptionPane.showConfirmDialog(exit, "Exit the application?", "Exit application confirmation", JOptionPane.YES_NO_OPTION);
		if (n == 0)
			System.exit(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mainFrame.getToolBar().clickExit();;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
