/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import model.Commands.Command;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GameEngineServerStub {
	GameEngine gameEngine;
	int callbackPort = 10001;
	
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
				HandleAClient task = new HandleAClient(clientSocket, callbackPort);
				callbackPort++;
				new Thread(task).start();
			} catch (IOException e) {
				System.out.println("Accept failed: " + port);
				System.exit(-1);
			}
		}
	}
	
	private class HandleAClient implements Runnable {
		private Socket clientSocket;
		private int callbackPort;
		
		// streams
		BufferedReader fromClient = null;
		DataInputStream fromClientInt = null;
		ObjectInputStream fromClientObject = null;
		ObjectOutputStream toClientObject = null;
//		DataOutputStream toClientInt = null;
//		PrintWriter toClient = null;

		Command command = null;
		Player player;
		int bet = 0;
		int initialDelay;
		int finalDelay;
		int delayIncrement;
		int pointsToAdd = 0;
		
		boolean quit = false;
		
		// create a new thread
		public HandleAClient(Socket socket, int callbackPort) {
			this.clientSocket = socket;
			this.callbackPort = callbackPort;
		}
		
		@Override
		public void run() {
			try {
				// setup streams
				fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				fromClientInt = new DataInputStream(clientSocket.getInputStream());
				fromClientObject = new ObjectInputStream(clientSocket.getInputStream());
				toClientObject = new ObjectOutputStream(clientSocket.getOutputStream());
//				toClientInt = new DataOutputStream(clientSocket.getOutputStream());
//				toClient = new PrintWriter(clientSocket.getOutputStream(), true);
			} catch (IOException e) {
				System.out.println("Read failed: " + e.getMessage());
				System.exit(-1);
			}

			// receive commands from clients
			do {
				try {
					command = (Command)fromClientObject.readObject();
					
					switch (command) {
						case ADD_GAME_ENGINE_CALLBACK:
							break;
						case ADD_PLAYER:
							player = (Player)fromClientObject.readObject();
							gameEngine.addPlayer(player);
							// set hashmap to map player to socket in the server side callback
							((ServerSideGameEngineCallback)((GameEngineImpl)gameEngine).getGameEngineCallback()).addToMap(player, new GameEngineCallbackServer(callbackPort));
							break;
						case ADD_POINTS:
							pointsToAdd = fromClientInt.readInt();
							player.setPoints(player.getPoints() + pointsToAdd);
							// TODO may need to check what happens if doing this during a round etc
							break;
						case CALCULATE_RESULT:
							gameEngine.calculateResult();
							System.out.println("new points before sending back: " + player.getPoints());
							// TODO for some reason this object isn't getting sent but no errors either
							toClientObject.reset();
							toClientObject.writeObject(player);
							break;
						case GET_ALL_PLAYERS:
							break;
						case PLACE_BET:
							bet = fromClientInt.readInt();
							
							if (gameEngine.placeBet(player, bet)) {
								command = Command.SUCCESS;
								toClientObject.writeObject(command);
							} else {
								command = Command.FAIL;
								toClientObject.writeObject(command);
							}
							break;
						case QUIT:
//							quit = true;
							System.out.println("quit");

							break;
						case EXIT:
							System.out.println("exit pressed");
							quit = true;
							clientSocket.close();
							break;
						case REMOVE_PLAYER:
							break;
						case ROLL_HOUSE:
							gameEngine.calculateResult();
							break;
						case ROLL_PLAYER:
							initialDelay = fromClientInt.readInt();
							finalDelay = fromClientInt.readInt();
							delayIncrement = fromClientInt.readInt();
							gameEngine.rollPlayer(player, initialDelay, finalDelay, delayIncrement);
							break;
						default:
							break;
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (quit == false);
			
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
}
