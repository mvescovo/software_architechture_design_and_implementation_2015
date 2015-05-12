/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GameEngineClientStub implements GameEngine {
	// socket variables
	Socket socket;
	int port = 10000;
	String serverName = "localhost";
	BufferedReader fromServer;
	PrintWriter toServer;
	ObjectOutputStream toServerObject;
	ObjectInputStream fromServerObject;
	String line;
	DataOutputStream toServerInt;
	
	// gameEngine variables
	GameEngineCallback gameEngineCallback = null;
	Player player = null;
	
	public GameEngineClientStub() {	
		try {
			// connect to the server
			socket = new Socket(serverName, port);
			System.out.println("connected to server. waiting for user to add a player...");
			
			// setup reader and writer
//			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			toServer = new PrintWriter(socket.getOutputStream(), true);
			toServerObject = new ObjectOutputStream(socket.getOutputStream());
			fromServerObject = new ObjectInputStream(socket.getInputStream());
			toServerInt = new DataOutputStream(socket.getOutputStream());

		} catch (UnknownHostException e) {
			System.out.println("Unknown host: " + serverName);
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("No I/O");
			System.exit(-1);
		}
		
		// test send int to server
		try {
			toServerInt.writeInt(5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// send data to the server
//		int localPort = socket.getLocalPort();
//		line = " Hi I'm the client. My local port is: " + localPort;
//		toServer.println(line);	
		
		// display message from server
//		try {
//			line = fromServer.readLine();
//			System.out.println("client says message from server is: " + line);
//		} catch (IOException e) {
//			System.out.println("Read failed");
//			System.exit(1);
//		}
	}
	
	/* (non-Javadoc)
	 * @see model.interfaces.GameEngine#rollPlayer(model.interfaces.Player, int, int, int)
	 */
	@Override
	public void rollPlayer(Player playerTest, int initialDelay, int finalDelay,
			int delayIncrement) {
		DicePair dicePair = null;
		// send the roll to the server
		try {
			toServerInt.writeInt(initialDelay);
			toServerInt.writeInt(finalDelay);
			toServerInt.writeInt(delayIncrement);
			System.out.println("sending roll to the server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get dice roll from server and pass to GUI callback
		try {
			for (int i = 0; i < 10; i++) {
				this.player = (Player)fromServerObject.readObject();
				dicePair = (DicePair)fromServerObject.readObject();
				gameEngineCallback.intermediateResult(this.player, dicePair, this);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		// set local gameEngine player
		this.player = player;
		
		// add player to the server
		try {
			toServerObject.writeObject(player);
			System.out.println("sent player to server. waiting for user to place bet...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// test receiving player back from server
//		try {
//			player = (Player) fromServerObject.readObject();
//			System.out.println("received player back from server");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
		this.gameEngineCallback = gameEngineCallback;
	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngine#getAllPlayers()
	 */
	@Override
	public Collection<Player> getAllPlayers() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngine#placeBet(model.interfaces.Player, int)
	 */
	@Override
	public boolean placeBet(Player player, int bet) {
//		try {
//			toServerObject.writeObject(player);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("bet to place is: " + bet);
		try {
			toServerInt.writeInt(bet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("placed bet with server. waiting for user to roll...");
		
		return true;
	}

}
