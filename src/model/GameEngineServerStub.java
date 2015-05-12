/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
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
	
	@SuppressWarnings("resource")
	public GameEngineServerStub(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		gameEngine.addGameEngineCallback(new ServerSideGameEngineCallback());
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
		Player simplePlayer;
		ObjectInputStream objectFromClient;
		ObjectOutputStream objectToClient;
		int bet;
		int initialDelay;
		int finalDelay;
		int delayIncrement;
		DataInputStream fromClientInt;
		
		// create a new thread
		public HandleAClient(Socket socket) {
			this.clientSocket = socket;
		}
		
		@Override
		public void run() {
			try {
				// create readers and writers
//				fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//				toClient = new PrintWriter(clientSocket.getOutputStream(), true);
				objectFromClient = new ObjectInputStream(clientSocket.getInputStream());
				objectToClient = new ObjectOutputStream(clientSocket.getOutputStream());
				fromClientInt = new DataInputStream(clientSocket.getInputStream());
				
				//testing reading an int from client

//				System.out.println("got this int from client: " + fromClient.readLine());
				System.out.println("" + fromClientInt.readInt());
				
				// 1. add player
				try {
					System.out.println("client connected. waiting for client to add a player...");
					simplePlayer = (SimplePlayer)objectFromClient.readObject();
					
					// test sending player back
//					objectToClient.writeObject(simplePlayer);
//					System.out.println("sent player back to client");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				// set hashmap to map player to socket in the server side callback
				((ServerSideGameEngineCallback)((GameEngineImpl)gameEngine).getGameEngineCallback()).addToMap(simplePlayer, objectToClient);
				
				// add the player to the gameEngineImpl
				gameEngine.addPlayer(simplePlayer);
				
//				// 2. place a bet
				System.out.println("waiting for client to place a bet");
				bet = fromClientInt.readInt();
				System.out.println("received bet, placing bet...");
				gameEngine.placeBet(simplePlayer, bet);
//				
//				// 3. roll player
				System.out.println("waiting for player roll...");
				initialDelay = fromClientInt.readInt();
				finalDelay = fromClientInt.readInt();
				delayIncrement = fromClientInt.readInt();
				System.out.println("got roll. trying to roll...");
				gameEngine.rollPlayer(simplePlayer, initialDelay, finalDelay, delayIncrement);
//				
			} catch (IOException e) {
				System.out.println("Read failed: " + e.getMessage());
				System.exit(-1);
			}
		}
	}
}
