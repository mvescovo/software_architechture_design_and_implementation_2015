/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GameEngineServerStub {
	GameEngine gameEngine;
	GameEngineCallback gameEngineCallback = new ServerSideGameEngineCallback();
	
	
	@SuppressWarnings("resource")
	public GameEngineServerStub(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		gameEngine.addGameEngineCallback(gameEngineCallback);
		ServerSocket serverSocket = null;
		int port = 10000;
		
		// create a server socket
		try {
			serverSocket = new ServerSocket(port);
		} catch(IOException e) {
			System.out.println("Could not listen on port " + port);
			System.exit(-1);
		}
		
		while (true) {
			try {
				// listen for a new connection from a client
				Socket clientSocket = serverSocket.accept();
				
				// create a new thread for the connection and start it
				HandleAClient task = new HandleAClient(clientSocket);
				new Thread(task).start();
			} catch (IOException e) {
				System.out.println("Accept failed: " + port);
				System.exit(-1);
			}
		}
	}
	
	private class HandleAClient implements Runnable {
		private Socket clientSocket;
		BufferedReader fromClient = null;
		PrintWriter toClient = null;
		String line;
		Player player;
		ObjectInputStream objectFromClient;
		int bet;
		int initialDelay;
		int finalDelay;
		int delayIncrement;
		
		// create a new thread
		public HandleAClient(Socket socket) {
			this.clientSocket = socket;
		}
		
		@Override
		public void run() {
			try {
				// create readers and writers
				fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				toClient = new PrintWriter(clientSocket.getOutputStream(), true);
				objectFromClient = new ObjectInputStream(clientSocket.getInputStream());
				
				// 1. add player
				try {
					System.out.println("waiting for client to add a player");
					player = (Player) objectFromClient.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				// add the player to the gameEngineImpl
				gameEngine.addPlayer(player);

				// 2. place a bet
				System.out.println("waiting for client to place a bet");
				bet = fromClient.read();
				System.out.println("received bet, plaing bet...");
				gameEngine.placeBet(player, bet);
				
				// 3. roll player
				System.out.println("waiting for player roll...");
				initialDelay = fromClient.read();
				finalDelay = fromClient.read();
				delayIncrement = fromClient.read();
				gameEngine.rollPlayer(player, initialDelay, finalDelay, delayIncrement);
				
			} catch (IOException e) {
				System.out.println("Read failed");
				System.exit(-1);
			}
		}
	}
}
