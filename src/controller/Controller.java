package controller;

import view.MainFrame;
import model.GameEngineCallbackImpl;
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
	private HouseRollsButtonListener houseRollsButtonListener;
	private DisplayResultsButtonListener displayResultsButtonListener;
	private QuitButtonListener quitButtonListener;
	private ExitButtonListener exitButtonListener;
	Player player = null;
	
	public Controller(GameEngine gameEngine, MainFrame mainFrame) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl(mainFrame));
		addPlayerButtonListener = new AddPlayerButtonListener(gameEngine, mainFrame, this);
		placeBetButtonListener = new PlaceBetButtonListener(gameEngine, mainFrame);
		betTextFieldListener = new BetTextFieldListener(mainFrame);
		playerRollsButtonListener = new PlayerRollsButtonListener(gameEngine, mainFrame, this);
		houseRollsButtonListener = new HouseRollsButtonListener(gameEngine, mainFrame);
		displayResultsButtonListener = new DisplayResultsButtonListener(gameEngine, mainFrame, this);
		quitButtonListener = new QuitButtonListener(gameEngine, mainFrame, this);
		exitButtonListener = new ExitButtonListener(mainFrame);

		// register action listeners
		this.mainFrame.getPlayerPanel().getAddPlayerButton().addActionListener(addPlayerButtonListener);
		this.mainFrame.getToolBar().getPlaceBetButton().addActionListener(placeBetButtonListener);
		this.mainFrame.getToolBar().getBetTextField().addActionListener(betTextFieldListener);
		this.mainFrame.getToolBar().getPlayerRollsButton().addActionListener(playerRollsButtonListener);
		this.mainFrame.getToolBar().getHouseRollsButton().addActionListener(houseRollsButtonListener);
		this.mainFrame.getToolBar().getDisplayResultsButton().addActionListener(displayResultsButtonListener);
		this.mainFrame.getToolBar().getQuitButton().addActionListener(quitButtonListener);
		this.mainFrame.getToolBar().getExitButton().addActionListener(exitButtonListener);
		// register key listeners
		this.mainFrame.getPlayerPanel().getAddPlayerButton().addKeyListener(addPlayerButtonListener);
		this.mainFrame.getToolBar().getPlayerRollsButton().addKeyListener(playerRollsButtonListener);
		this.mainFrame.getToolBar().getHouseRollsButton().addKeyListener(houseRollsButtonListener);
		this.mainFrame.getToolBar().getDisplayResultsButton().addKeyListener(displayResultsButtonListener);
		this.mainFrame.getToolBar().getQuitButton().addKeyListener(quitButtonListener);
		this.mainFrame.getToolBar().getExitButton().addKeyListener(exitButtonListener);
		// menu listeners
		this.mainFrame.getMenu().getAddPlayerMenuItem().addActionListener(addPlayerButtonListener);
		this.mainFrame.getMenu().getplaceBetMenuItem().addActionListener(placeBetButtonListener);
		this.mainFrame.getMenu().getPlayerRollsDiceMenuItem().addActionListener(playerRollsButtonListener);
		this.mainFrame.getMenu().getHouseRollsDiceMenuItem().addActionListener(houseRollsButtonListener);
		this.mainFrame.getMenu().getDisplayResultsMenuItem().addActionListener(displayResultsButtonListener);
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
