/**
 * 
 */
package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class TableAndToolbarContainerPanel extends JPanel {
	private static final long serialVersionUID = -9048676903836093197L;
	GameTablePanel gameTablePanel;
	ToolBarPanel toolBarPanel;
	
	public TableAndToolbarContainerPanel() {
		setLayout(new BorderLayout());
		gameTablePanel = new GameTablePanel();
		add(gameTablePanel, BorderLayout.CENTER);
		toolBarPanel = new ToolBarPanel();
		add(toolBarPanel, BorderLayout.SOUTH);
	}
	
	public ToolBarPanel getToolBar() {
		return toolBarPanel;
	}
	
	public GameTablePanel getGameTablePanel() {
		return gameTablePanel;
	}
}
