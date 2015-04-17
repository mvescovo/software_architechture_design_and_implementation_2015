package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.MainFrame;
import model.GameEngineCallbackImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class Controller {
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	private AddPlayerButtonListener addPlayerButtonListener;
	private PlaceBetButtonListener placeBetButtonListener;
	private BetTextFieldListener betTextFieldListener;
	private PlayerRollsButtonListener playerRollsButtonListener;
	private HouseRollsButtonListener houseRollsButtonListener;
	private DisplayResultsButtonListener displayResultsButtonListener;
	private QuitButtonListener quitButtonListener;
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
		quitButtonListener = new QuitButtonListener(mainFrame);

		// register action listeners
		this.mainFrame.getPlayerPanel().getAddPlayerButton().addActionListener(addPlayerButtonListener);
		this.mainFrame.getToolBar().getPlaceBetButton().addActionListener(placeBetButtonListener);
		this.mainFrame.getToolBar().getBetTextField().addActionListener(betTextFieldListener);
		this.mainFrame.getToolBar().getPlayerRollsButton().addActionListener(playerRollsButtonListener);
		this.mainFrame.getToolBar().getHouseRollsButton().addActionListener(houseRollsButtonListener);
		this.mainFrame.getToolBar().getDisplayResultsButton().addActionListener(displayResultsButtonListener);
		this.mainFrame.getToolBar().getQuitButton().addActionListener(quitButtonListener);
		// register key listeners
		this.mainFrame.getPlayerPanel().getAddPlayerButton().addKeyListener(addPlayerButtonListener);
		this.mainFrame.getToolBar().getPlayerRollsButton().addKeyListener(playerRollsButtonListener);
		this.mainFrame.getToolBar().getHouseRollsButton().addKeyListener(houseRollsButtonListener);
		this.mainFrame.getToolBar().getDisplayResultsButton().addKeyListener(displayResultsButtonListener);
		this.mainFrame.getToolBar().getQuitButton().addKeyListener(quitButtonListener);
	}
	
	protected void setCurrPlayer(Player player) {
		this.player = player;
	}
	
	protected Player getCurrPlayer() {
		return player;
	}
}
