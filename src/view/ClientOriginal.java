package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameEngineCallbackImpl;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * Simple console client for SADI assignment 1, 2015
 * 
 * @author Caspar Ryan
 * 
 */
public class ClientOriginal
{
	private static Logger logger = Logger.getLogger("assignment1");

	public static void main(String args[])
	{
		final GameEngine gameEngine = new GameEngineImpl();

		// create two test players
		Player[] players = new Player[]
		{ new SimplePlayer("1", "The Roller", 1000),
				new SimplePlayer("2", "The Loser", 500) };

		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

		// main loop to add player place a bet and roll
		for (Player player : players)
		{
			player.placeBet(100);
			gameEngine.addPlayer(player);
			gameEngine.rollPlayer(player, 1, 100, 20);
		}

		// all players have rolled so now house rolls (GameEngineCallBack is
		// called)
		// and results are calculated
		gameEngine.calculateResult();

		// log final points balances so we can check correctness
		for (Player player : gameEngine.getAllPlayers())
			logger.log(Level.INFO, player.toString());
	}
}