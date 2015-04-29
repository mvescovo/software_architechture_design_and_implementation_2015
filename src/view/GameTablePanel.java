package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class GameTablePanel extends JPanel {
	private static final long serialVersionUID = 6100878331293031579L;
	GameStatusPanel gameStatusPanel;
	DicePanel dicePanel;
	ToolBarPanel toolBar;
	
	GameTablePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(0, 153, 0));
		this.setBorder(null);
		
		add(Box.createRigidArea(new Dimension(0, 60)));
		
		gameStatusPanel = new GameStatusPanel();
		add(gameStatusPanel);
		
		add(Box.createRigidArea(new Dimension(0, 50)));
		
		dicePanel = new DicePanel();
		add(dicePanel);

		add(Box.createRigidArea(new Dimension(0, 20)));
		
		toolBar = new ToolBarPanel();
		add(toolBar);
	}
	
	public DicePanel getDicePanel() {
		return dicePanel;
	}
	
	public GameStatusPanel getGameStatusPanel() {
		return gameStatusPanel;
	}
	
	public ToolBarPanel getToolBar() {
		return toolBar;
	}
}
