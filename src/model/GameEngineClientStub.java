/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
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
	// gameEngine variables
	GameEngineCallback gameEngineCallback = null;
	Player player = null;
	
	// socket variables
	Socket socket;
	String serverName = "localhost";
	int port = 10000;
	
	// streams
	PrintWriter toServer = null;
	DataOutputStream toServerInt = null;
	ObjectOutputStream toServerObject = null;
	ObjectInputStream fromServerObject = null;
//	BufferedReader fromServer = null;

	
	Command command = null;
//	String line;
	
	public GameEngineClientStub() {	
		try {
			// connect to the server
			try {
				socket = new Socket(serverName, port);
			} catch (IOException e) {
				System.out.println("Could not connect to server: " + e.getMessage());
				System.exit(-1);
			}
			
			// setup streams
			toServer = new PrintWriter(socket.getOutputStream(), true);
			toServerInt = new DataOutputStream(socket.getOutputStream());
			toServerObject = new ObjectOutputStream(socket.getOutputStream());
			fromServerObject = new ObjectInputStream(socket.getInputStream());
//			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (UnknownHostException e) {
			System.out.println("Unknown host: " + serverName);
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("No I/O");
			System.exit(-1);
		}
	}
	
	@Override
	public void rollPlayer(Player playerTest, int initialDelay, int finalDelay,
			int delayIncrement) {
		DicePair dicePair = null;
		
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

		// get dice roll from server and pass to GUI callback
		try {
			for (int i = 0; i < 10; i++) {
				dicePair = (DicePair)fromServerObject.readObject();
				gameEngineCallback.intermediateResult(this.player, dicePair, this);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPlayer(Player player) {
		// set local gameEngine player
		this.player = player;
		
		// add player to the server
		try {
			command = Command.ADD_PLAYER;
			toServerObject.writeObject(command);
			toServerObject.writeObject(player);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean removePlayer(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void calculateResult() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallback = gameEngineCallback;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		
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
		
	}
}
