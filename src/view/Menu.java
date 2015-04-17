package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
	private static final long serialVersionUID = 2401343171737574518L;
	private JMenu menu1;
	private JMenuItem addPlayer;
	private JMenuItem placeBet;
	private JMenuItem playerRolls;
	private JMenuItem houseRolls;
	private JMenuItem displayResults;
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

		playerRolls = new JMenuItem("Player roll dice");
		playerRolls.setEnabled(false);
		menu1.add(playerRolls);
		
		houseRolls = new JMenuItem("House roll dice");
		houseRolls.setEnabled(false);
		menu1.add(houseRolls);
		
		displayResults = new JMenuItem("Display results");
		displayResults.setEnabled(false);
		menu1.add(displayResults);
		
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
	
	public JMenuItem getPlayerRollsDiceMenuItem() {
		return playerRolls;
	}
	
	public JMenuItem getHouseRollsDiceMenuItem() {
		return houseRolls;
	}
	
	public JMenuItem getDisplayResultsMenuItem() {
		return displayResults;
	}
	
	public JMenuItem getQuitMenuItem() {
		return quit;
	}
	
	public JMenuItem getExitMenuItem() {
		return exit;
	}
	
	public void enableAddPlayerMenu() {
		addPlayer.setEnabled(true);
	}
	
	public void disableAddPlayerMenu() {
		addPlayer.setEnabled(false);
	}
	
	public void enablePlaceBetMenu() {
		placeBet.setEnabled(true);
	}
	
	public void disablePlaceBetMenu() {
		placeBet.setEnabled(false);
	}
	
	public void enablePlayerRollsMenu() {
		playerRolls.setEnabled(true);
	}
	
	public void disablePlayerRollsMenu() {
		playerRolls.setEnabled(false);
	}
	
	public void enableHouseRollsMenu() {
		houseRolls.setEnabled(true);
	}
	
	public void disableHouseRollsMenu() {
		houseRolls.setEnabled(false);
	}
	
	public void enableDisplayResultsMenu() {
		displayResults.setEnabled(true);
	}
	
	public void disableDisplayResultsMenu() {
		displayResults.setEnabled(false);
	}
	
	public void enableQuitMenu() {
		quit.setEnabled(true);
	}
	
	public void disableQuitMenu() {
		quit.setEnabled(false);
	}
}
