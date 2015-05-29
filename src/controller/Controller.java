package controller;

import view.MainFrame;
import model.GameEngineCallbackImplGUI;
import model.interfaces.GameEngine;

public class Controller {
	private MainFrame mainFrame = null;
	private PlaceBetButtonListener placeBetButtonListener = null;
	private BetTextFieldListener betTextFieldListener = null;
	private RollPlayerButtonListener rollPlayerButtonListener = null;
	private RollHouseButtonListener rollHouseButtonListener = null;
	private QuitButtonListener quitButtonListener = null;
	private ExitButtonListener exitButtonListener = null;
	private AddPointsButtonListener addPointsButtonListener = null;
	private AddPointsTextFieldListener addPointsTextFieldListener = null;
	private PlayerNameTextFieldListener playerNameTextFieldListener = null;
	private PlayerPointsTextFieldListener playerPointsTextFieldListener = null;
	private StartGameButtonListener startGameButtonListener = null;
	private RefreshButtonListener refreshButtonListener = null;
	
	public Controller(GameEngine gameEngine, MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		gameEngine.addGameEngineCallback(new GameEngineCallbackImplGUI(mainFrame));
		
		// create listeners
		placeBetButtonListener = new PlaceBetButtonListener(gameEngine, mainFrame);
		betTextFieldListener = new BetTextFieldListener(mainFrame);
		rollPlayerButtonListener = new RollPlayerButtonListener(gameEngine, mainFrame);
		rollHouseButtonListener = new RollHouseButtonListener(gameEngine, mainFrame);
		quitButtonListener = new QuitButtonListener(gameEngine, mainFrame);
		exitButtonListener = new ExitButtonListener(gameEngine, mainFrame);
		addPointsButtonListener = new AddPointsButtonListener(gameEngine, mainFrame);
		addPointsTextFieldListener = new AddPointsTextFieldListener(mainFrame);
		playerNameTextFieldListener = new PlayerNameTextFieldListener(mainFrame);
		playerPointsTextFieldListener = new PlayerPointsTextFieldListener(mainFrame);
		startGameButtonListener = new StartGameButtonListener(gameEngine, mainFrame);
		refreshButtonListener = new RefreshButtonListener();

		// register action listeners
		this.mainFrame.getTableAndToolbarContainerPanel().getToolBar().getPlaceBetButton().addActionListener(placeBetButtonListener);
		this.mainFrame.getTableAndToolbarContainerPanel().getToolBar().getBetTextField().addActionListener(betTextFieldListener);
		this.mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollPlayerButton().addActionListener(rollPlayerButtonListener);
		this.mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().addActionListener(rollHouseButtonListener);
		this.mainFrame.getTableAndToolbarContainerPanel().getToolBar().getQuitButton().addActionListener(quitButtonListener);
		this.mainFrame.getTableAndToolbarContainerPanel().getToolBar().getExitButton().addActionListener(exitButtonListener);
		this.mainFrame.getStartGamePanel().getQuitGameButton().addActionListener(exitButtonListener);
		this.mainFrame.getPlayerPanel().getAddPointsButton().addActionListener(addPointsButtonListener);
		this.mainFrame.getPlayerPanel().getAddPointsTextField().addActionListener(addPointsTextFieldListener);
		this.mainFrame.getStartGamePanel().getPlayerNameTextField().addActionListener(playerNameTextFieldListener);
		this.mainFrame.getStartGamePanel().getPlayerPointsTextField().addActionListener(playerPointsTextFieldListener);
		this.mainFrame.getStartGamePanel().getStartGameButton().addActionListener(startGameButtonListener);
		this.mainFrame.getPlayerPanel().getRefreshButton().addActionListener(refreshButtonListener);
		
		// register key listeners
		this.mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollPlayerButton().addKeyListener(rollPlayerButtonListener);
		this.mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().addKeyListener(rollHouseButtonListener);
		this.mainFrame.getTableAndToolbarContainerPanel().getToolBar().getQuitButton().addKeyListener(quitButtonListener);
		this.mainFrame.getTableAndToolbarContainerPanel().getToolBar().getExitButton().addKeyListener(exitButtonListener);
		this.mainFrame.getStartGamePanel().getQuitGameButton().addKeyListener(exitButtonListener);
		this.mainFrame.getPlayerPanel().getAddPointsButton().addKeyListener(addPointsButtonListener);
		this.mainFrame.getStartGamePanel().getStartGameButton().addKeyListener(startGameButtonListener);
		
		// menu listeners
		this.mainFrame.getMenu().getStartGameMenuItem().addActionListener(startGameButtonListener);
		this.mainFrame.getMenu().getplaceBetMenuItem().addActionListener(placeBetButtonListener);
		this.mainFrame.getMenu().getRollPlayerMenuItem().addActionListener(rollPlayerButtonListener);
		this.mainFrame.getMenu().getRollHouseMenuItem().addActionListener(rollHouseButtonListener);
		this.mainFrame.getMenu().getQuitMenuItem().addActionListener(quitButtonListener);
		this.mainFrame.getMenu().getExitMenuItem().addActionListener(exitButtonListener);
		
		// window listener
		this.mainFrame.addWindowListener(exitButtonListener);
	}
}
