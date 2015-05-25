/**
 * 
 */
package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;

import model.Commands.Command;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GameEngineClientStub implements GameEngine {
	GameEngineCallback gameEngineCallback = null;
	GameEngineCallbackServer gameEngineCallbackServer = null;
	Player player = null;
	
	// socket variables for gameEngineServerStub
	Socket socket;
	String serverName = "localhost";
	int port = 10001;
	
	// streams for gameEngineServerStub
	DataOutputStream toServerInt = null;
	ObjectOutputStream toServerObject = null;
	ObjectInputStream fromServerObject = null;
	
	AddPlayerCommand addPlayerCommand = null;
	Command command = null;
	
	public GameEngineClientStub() {	
		// create a GameEngineCallback server
		gameEngineCallbackServer = new GameEngineCallbackServer(this);
		
		try {
			// connect to the server
			socket = new Socket(serverName, port);
			
			// setup streams
			toServerInt = new DataOutputStream(socket.getOutputStream());
			toServerObject = new ObjectOutputStream(socket.getOutputStream());
			fromServerObject = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Could not connect to server: " + e.getMessage());
			System.exit(-1);
		}
		
		addPlayerCommand = new AddPlayerCommand();
	}

	@Override
	public void rollPlayer(Player playerTest, int initialDelay, int finalDelay,
			int delayIncrement) {
		// send the roll to the server
		try {
			command = Command.ROLL_PLAYER;
			toServerObject.writeObject(command);
			toServerInt.writeInt(initialDelay);
			toServerInt.writeInt(finalDelay);
			toServerInt.writeInt(delayIncrement);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		// not used in client engine
	}

	@Override
	public void addPlayer(Player player) {
		// set local gameEngine player
		this.player = player;
//		boolean connected = false;
		
		// add player to the server
		try {
			command = Command.ADD_PLAYER;
			toServerObject.writeObject(command);
			toServerObject.reset();
			toServerObject.writeObject(player);
			toServerInt.writeInt(this.gameEngineCallbackServer.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean removePlayer(Player player) {
		boolean isSuccess = false;
		
		try {
			command = Command.REMOVE_PLAYER;
			toServerObject.writeObject(command);
			command = (Command)fromServerObject.readObject();
			
			if (command == Command.SUCCESS) {
				isSuccess = true;
			} else {
				isSuccess = false;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public void calculateResult() {
		// request server to calculate results (including roll house)
		try {
			command = Command.CALCULATE_RESULT;
			toServerObject.writeObject(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallback = gameEngineCallback;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		// TODO add a comment about this
		return null;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		boolean betOk = false;
		
		try {
			command = Command.PLACE_BET;
			toServerObject.writeObject(command);
			toServerInt.writeInt(bet);
			command = (Command)fromServerObject.readObject();
			
			if (command == Command.SUCCESS) {
				betOk = true;
			} else {
				betOk = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return betOk;
	}

	public Player getPlayer() {
		return player;
	}
	
	public void addPoints(int points) {
		player.setPoints(player.getPoints() + points);
		// send new version to server
		try {
			command = Command.ADD_POINTS;
			toServerObject.writeObject(command);
			toServerInt.writeInt(points);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addGameEngineCallbackToGameEngineCallbackServer() {
		gameEngineCallbackServer.addGameEngineCallback(gameEngineCallback);
	}
	
	public void exitGame() {
		command = Command.EXIT;
		
		try {
			toServerObject.writeObject(command);
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
