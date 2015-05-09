/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
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
	int port = 10000;
	String serverName = "localhost";
	BufferedReader fromServer;
	PrintWriter toServer;
	ObjectOutputStream toServerObject;
	String line;
	Player player = null;
	
	public GameEngineClientStub() {	
		try {
			// connect to the server
			socket = new Socket(serverName, port);
			
			// setup reader and writer
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			toServer = new PrintWriter(socket.getOutputStream(), true);
			toServerObject = new ObjectOutputStream(socket.getOutputStream());

		} catch (UnknownHostException e) {
			System.out.println("Unknown host: " + serverName);
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("No I/O");
			System.exit(-1);
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
	public void rollPlayer(Player player, int initialDelay, int finalDelay,
			int delayIncrement) {
		// send the roll to the server
		System.out.println("sendig roll to the server");
		toServer.print(initialDelay);
		toServer.print(finalDelay);
		toServer.print(delayIncrement);
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
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		System.out.println("trying to place bet with server");
		toServer.print(bet);
		
		return true;
	}

}
