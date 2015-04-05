package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CrapsMenu extends JMenuBar {
	public CrapsMenu() {
		JMenu menu1;
		JMenuItem menuItem;
		
		// menu 1
		menu1 = new JMenu("Menu 1");
		add(menu1);
		
		// group of menu items
		menuItem = new JMenuItem("Add player");
		menu1.add(menuItem);
		
		menuItem = new JMenuItem("Place bet");
		menu1.add(menuItem);

		menuItem = new JMenuItem("Player roll dice");
		menu1.add(menuItem);
		
		menuItem = new JMenuItem("House roll dice");
		menu1.add(menuItem);
		
		menuItem = new JMenuItem("Display results");
		menu1.add(menuItem);
	}
}
