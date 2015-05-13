package controller;

import view.MainFrame;
import model.GameEngineCallbackImplGUI;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class Controller {
//	@SuppressWarnings("unused")
	private GameEngine gameEngine;
	private MainFrame mainFrame;
//	private AddPlayerButtonListener addPlayerButtonListener;
	private PlaceBetButtonListener placeBetButtonListener;
	private BetTextFieldListener betTextFieldListener;
	private RollPlayerButtonListener rollPlayerButtonListener;
	private RollHouseButtonListener rollHouseButtonListener;
	private QuitButtonListener quitButtonListener;
	private ExitButtonListener exitButtonListener;
	private AddPointsButtonListener addPointsButtonListener;
	private AddPointsTextFieldListener addPointsTextFieldListener;
	private PlayerNameTextFieldListener playerNameTextFieldListener;
	private PlayerPointsTextFieldListener playerPointsTextFieldListener;
	private StartGameButtonListener startGameButtonListener;
	Player player = null;
	
	public Controller(GameEngine gameEngine, MainFrame mainFrame) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		gameEngine.addGameEngineCallback(new GameEngineCallbackImplGUI(mainFrame));
		
		// create listeners
//		addPlayerButtonListener = new AddPlayerButtonListener(gameEngine, mainFrame);
		placeBetButtonListener = new PlaceBetButtonListener(gameEngine, mainFrame);
		betTextFieldListener = new BetTextFieldListener(mainFrame);
		rollPlayerButtonListener = new RollPlayerButtonListener(gameEngine, mainFrame);
		rollHouseButtonListener = new RollHouseButtonListener(gameEngine, mainFrame);
		quitButtonListener = new QuitButtonListener(gameEngine, mainFrame);
		exitButtonListener = new ExitButtonListener(mainFrame);
		addPointsButtonListener = new AddPointsButtonListener(gameEngine, mainFrame);
		addPointsTextFieldListener = new AddPointsTextFieldListener(mainFrame);
		playerNameTextFieldListener = new PlayerNameTextFieldListener(mainFrame);
		playerPointsTextFieldListener = new PlayerPointsTextFieldListener(mainFrame);
		startGameButtonListener = new StartGameButtonListener(gameEngine, mainFrame);

		// register action listeners
//		this.mainFrame.getPlayerPanel().getAddPlayerButton().addActionListener(addPlayerButtonListener);
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
		
		// register key listeners
//		this.mainFrame.getPlayerPanel().getAddPlayerButton().addKeyListener(addPlayerButtonListener);
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
	}
	
//	protected void setCurrPlayer(Player player) {
//		this.player = player;
//	}
//	
//	protected Player getCurrPlayer() {
//		return player;
//	}
//	
//	protected void printCurrPlayerName() {
//		System.out.println(player.getPlayerName());
//	}
}
