package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
	private static final long serialVersionUID = 2401343171737574518L;
	private JMenu menu1;
	private JMenuItem addPlayer;
	private JMenuItem placeBet;
	private JMenuItem rollPlayer;
	private JMenuItem rollHouse;
	private JMenuItem quit;
	private JMenuItem exit;
	
	public Menu() {
		
		// menu 1
		menu1 = new JMenu("Menu");
		add(menu1);
		
		// group of menu items
		addPlayer = new JMenuItem("Add player");
		menu1.add(addPlayer);
		
		placeBet = new JMenuItem("Place bet");
		placeBet.setEnabled(false);
		menu1.add(placeBet);

		rollPlayer = new JMenuItem("Roll Player");
		rollPlayer.setEnabled(false);
		menu1.add(rollPlayer);
		
		rollHouse = new JMenuItem("Roll House");
		rollHouse.setEnabled(false);
		menu1.add(rollHouse);
		
		quit = new JMenuItem("Quit");
		quit.setEnabled(false);
		menu1.add(quit);
		
		exit = new JMenuItem("Exit");
		menu1.add(exit);
	}
	
	public JMenuItem getAddPlayerMenuItem() {
		return addPlayer;
	}
	
	public JMenuItem getplaceBetMenuItem() {
		return placeBet;
	}
	
	public JMenuItem getRollPlayerMenuItem() {
		return rollPlayer;
	}
	
	public JMenuItem getRollHouseMenuItem() {
		return rollHouse;
	}
	
	public JMenuItem getQuitMenuItem() {
		return quit;
	}
	
	public JMenuItem getExitMenuItem() {
		return exit;
	}
}
