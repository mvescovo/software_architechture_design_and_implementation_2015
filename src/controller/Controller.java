package controller;

import view.MainFrame;
import model.GameEngineCallbackImpl;
import model.GameEngineCallbackImplGUI;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class Controller {
	@SuppressWarnings("unused")
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	private AddPlayerButtonListener addPlayerButtonListener;
	private PlaceBetButtonListener placeBetButtonListener;
	private BetTextFieldListener betTextFieldListener;
	private PlayerRollsButtonListener playerRollsButtonListener;
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
		addPlayerButtonListener = new AddPlayerButtonListener(gameEngine, mainFrame, this);
		placeBetButtonListener = new PlaceBetButtonListener(gameEngine, mainFrame);
		betTextFieldListener = new BetTextFieldListener(mainFrame);
		playerRollsButtonListener = new PlayerRollsButtonListener(gameEngine, mainFrame, this);
		quitButtonListener = new QuitButtonListener(gameEngine, mainFrame, this);
		exitButtonListener = new ExitButtonListener(mainFrame);
		addPointsButtonListener = new AddPointsButtonListener(gameEngine, mainFrame);
		addPointsTextFieldListener = new AddPointsTextFieldListener(mainFrame);
		playerNameTextFieldListener = new PlayerNameTextFieldListener(mainFrame);
		playerPointsTextFieldListener = new PlayerPointsTextFieldListener(mainFrame);
		startGameButtonListener = new StartGameButtonListener(gameEngine, mainFrame, this);

		// register action listeners
		this.mainFrame.getPlayerPanel().getAddPlayerButton().addActionListener(addPlayerButtonListener);
		this.mainFrame.getgameTablePanel().getToolBar().getPlaceBetButton().addActionListener(placeBetButtonListener);
		this.mainFrame.getgameTablePanel().getToolBar().getBetTextField().addActionListener(betTextFieldListener);
		this.mainFrame.getgameTablePanel().getToolBar().getPlayerRollsButton().addActionListener(playerRollsButtonListener);
		this.mainFrame.getgameTablePanel().getToolBar().getQuitButton().addActionListener(quitButtonListener);
		this.mainFrame.getgameTablePanel().getToolBar().getExitButton().addActionListener(exitButtonListener);
		this.mainFrame.getPlayerPanel().getAddPointsButton().addActionListener(addPointsButtonListener);
		this.mainFrame.getPlayerPanel().getAddPointsTextField().addActionListener(addPointsTextFieldListener);
		this.mainFrame.getStartGamePanel().getPlayerNameTextField().addActionListener(playerNameTextFieldListener);
		this.mainFrame.getStartGamePanel().getPlayerPointsTextField().addActionListener(playerPointsTextFieldListener);
		this.mainFrame.getStartGamePanel().getStartGameButton().addActionListener(startGameButtonListener);
		// register key listeners
		this.mainFrame.getPlayerPanel().getAddPlayerButton().addKeyListener(addPlayerButtonListener);
		this.mainFrame.getgameTablePanel().getToolBar().getPlayerRollsButton().addKeyListener(playerRollsButtonListener);
		this.mainFrame.getgameTablePanel().getToolBar().getQuitButton().addKeyListener(quitButtonListener);
		this.mainFrame.getgameTablePanel().getToolBar().getExitButton().addKeyListener(exitButtonListener);
		this.mainFrame.getPlayerPanel().getAddPointsButton().addKeyListener(addPointsButtonListener);
		this.mainFrame.getStartGamePanel().getStartGameButton().addKeyListener(startGameButtonListener);
		// menu listeners
		this.mainFrame.getMenu().getAddPlayerMenuItem().addActionListener(addPlayerButtonListener);
		this.mainFrame.getMenu().getplaceBetMenuItem().addActionListener(placeBetButtonListener);
		this.mainFrame.getMenu().getPlayerRollsDiceMenuItem().addActionListener(playerRollsButtonListener);
		this.mainFrame.getMenu().getQuitMenuItem().addActionListener(quitButtonListener);
		this.mainFrame.getMenu().getExitMenuItem().addActionListener(exitButtonListener);
	}
	
	protected void setCurrPlayer(Player player) {
		this.player = player;
	}
	
	protected Player getCurrPlayer() {
		return player;
	}
	
	protected void printCurrPlayerName() {
		System.out.println(player.getPlayerName());
	}
}
