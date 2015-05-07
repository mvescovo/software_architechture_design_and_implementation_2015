/**
 * 
 */
package model;

import java.net.Socket;
import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GameEngineClientStub implements GameEngine {
	Socket socket;
	
	public GameEngineClientStub() {
		System.out.println("This is the client trying to connect to the server...");
		
		try {
			socket = new Socket("localhost", 10000);
			System.out.println("This is the client saying I connected");
		} catch(Exception e) {
			System.out.println("This is the client saying I didn't connect");
		}
	}
	
	/* (non-Javadoc)
	 * @see model.interfaces.GameEngine#rollPlayer(model.interfaces.Player, int, int, int)
	 */
	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay,
			int delayIncrement) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngine#rollHouse(int, int, int)
	 */
	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngine#addPlayer(model.interfaces.Player)
	 */
	@Override
	public void addPlayer(Player player) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngine#removePlayer(model.interfaces.Player)
	 */
	@Override
	public boolean removePlayer(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngine#calculateResult()
	 */
	@Override
	public void calculateResult() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngine#addGameEngineCallback(model.interfaces.GameEngineCallback)
	 */
	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngine#getAllPlayers()
	 */
	@Override
	public Collection<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngine#placeBet(model.interfaces.Player, int)
	 */
	@Override
	public boolean placeBet(Player player, int bet) {
		// TODO Auto-generated method stub
		return false;
	}

}
