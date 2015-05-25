/**
 * 
 */
package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Commands.Command;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GameEngineCallbackServer {
	GameEngineCallback gameEngineCallback;
	GameEngine gameEngine;
	
	int port = 0;
	ServerSocket serverSocket = null;
	
	public GameEngineCallbackServer(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		// create a new thread to listen for a connection and handle the client, then start it
		HandleAClient task = new HandleAClient();
		new Thread(task).start();
	}
	
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallback = gameEngineCallback;
	}
	
	public int getPort() {
		return port;
	}
	
	private class HandleAClient implements Runnable {
		private Socket clientSocket = null;
		
		// streams
//		BufferedReader fromClient = null;
//		DataInputStream fromClientInt = null;
		ObjectInputStream fromClientObject = null;
//		ObjectOutputStream toClientObject = null;
//		DataOutputStream toClientInt = null;
//		PrintWriter toClient = null;
		
		Command command = null;
		
		Player player = null;
		DicePair dicePair = null;
		
		
		boolean quit = false;

		@Override
		public void run() {
			// create a server socket
			try {
				serverSocket = new ServerSocket(port);
				port = serverSocket.getLocalPort();
			} catch(IOException e) {
				System.out.println("Could not listen on port " + port);
				System.exit(-1);
			}
			
			try {
				// listen for a new connection from the server callback
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("Accept failed: " + port);
				System.exit(-1);
			}
			
			// setup streams
//			fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//			fromClientInt = new DataInputStream(clientSocket.getInputStream());
			try {
				fromClientObject = new ObjectInputStream(clientSocket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
//			toClientObject = new ObjectOutputStream(clientSocket.getOutputStream());
//			toClientInt = new DataOutputStream(clientSocket.getOutputStream());
//			toClient = new PrintWriter(clientSocket.getOutputStream(), true);
			
			// receive commands from server
			do {
				try {
					command = (Command)fromClientObject.readObject();
					
					switch (command) {
					case ADD_GAME_ENGINE_CALLBACK:
						break;
					case ADD_PLAYER:
						break;
					case ADD_POINTS:
						break;
					case CALCULATE_RESULT:
						break;
					case EXIT:
						break;
					case FAIL:
						break;
					case GET_ALL_PLAYERS:
						break;
					case HOUSE_RESULT:
						dicePair = (DicePair)fromClientObject.readObject();
						gameEngineCallback.houseResult(dicePair, gameEngine);
						break;
					case INTERMEDIATE_HOUSE_RESULT:
						dicePair = (DicePair)fromClientObject.readObject();
						gameEngineCallback.intermediateHouseResult(dicePair, gameEngine);
						break;
					case INTERMEDIATE_RESULT:
						player = (Player)fromClientObject.readObject();
						dicePair = (DicePair)fromClientObject.readObject();
						gameEngineCallback.intermediateResult(player, dicePair, gameEngine);
						break;
					case PLACE_BET:
						break;
					case QUIT:
						break;
					case REMOVE_PLAYER:
						break;
					case RESULT:
						player = (Player)fromClientObject.readObject();
						dicePair = (DicePair)fromClientObject.readObject();
						gameEngineCallback.result(player, dicePair, gameEngine);
						break;
					case ROLL_HOUSE:
						break;
					case ROLL_PLAYER:
						break;
					case SUCCESS:
						break;
					case UPDATE_RESULT:
						player = (Player)fromClientObject.readObject();
						((GameEngineCallbackImplGUI)gameEngineCallback).updateResult(player);
						break;
					case DISABLE_HOUSE:
						((GameEngineCallbackImplGUI)gameEngineCallback).disableHouse();
						System.out.println("trying to disable the house button");
						break;
					default:
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} while (quit == false);
		}
	}
}
