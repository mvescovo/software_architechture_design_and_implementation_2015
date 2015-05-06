package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class GameTablePanel extends JPanel {
	private static final long serialVersionUID = 6100878331293031579L;
	GameStatusPanel gameStatusPanel;
	DicePanel dicePanel;
	
	public GameTablePanel() {
		setLayout(new BorderLayout());
		setBackground(new Color(0, 153, 0));
		
		gameStatusPanel = new GameStatusPanel();
		add(gameStatusPanel, BorderLayout.CENTER);
		
		dicePanel = new DicePanel();
		add(dicePanel, BorderLayout.SOUTH);
	}
	
	public DicePanel getDicePanel() {
		return dicePanel;
	}
	
	public GameStatusPanel getGameStatusPanel() {
		return gameStatusPanel;
	}
}
