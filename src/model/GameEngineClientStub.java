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
	// gameEngine variables
	public GameEngineCallback gameEngineCallback = null;
	Player player = null;
	
	// socket variables for gameEngineServerStub
	Socket socket;
	String serverName = "localhost";
	int port = 10000;
	
	// socket variables for gameEngineCallbackServer
	Socket socketCallback;
	int portCallback = 10001;
	
	// streams for gameEngineServerStub
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
			socket = new Socket(serverName, port);
			
			// setup streams
			toServer = new PrintWriter(socket.getOutputStream(), true);
			toServerInt = new DataOutputStream(socket.getOutputStream());
			toServerObject = new ObjectOutputStream(socket.getOutputStream());
			fromServerObject = new ObjectInputStream(socket.getInputStream());
//			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("Could not connect to server: " + e.getMessage());
			System.exit(-1);
		}
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
		boolean connected = false;
		
		// add player to the server
		try {
			command = Command.ADD_PLAYER;
			toServerObject.writeObject(command);
			toServerObject.writeObject(player);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// connect to gameEngineCallbackServer
		while (!connected) {
			try {
				socketCallback = new Socket(serverName, portCallback);
				HandleServerCallback task = new HandleServerCallback(socket, this);
				new Thread(task).start();
				System.out.println("Connected to callbacksocket");
				connected = true;
			} catch (IOException e) {
				System.out.println("Could not connect to server: " + e.getMessage());
				try {
					System.out.println("Sleeping for 35 ms");
					Thread.sleep(35);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean removePlayer(Player player) {
		// TODO Auto-generated method stub
		return false;
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
	
	public void exitGame() {
		command = Command.EXIT;
		
		try {
			toServerObject.writeObject(command);
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public class HandleServerCallback implements Runnable {
		private Socket socket;
		GameEngine gameEngine = null;
		
		// streams for gameEngineCallbackServer
		PrintWriter toServerCallback = null;
		DataOutputStream toServerIntCallback = null;
		ObjectOutputStream toServerObjectCallback = null;
		ObjectInputStream fromServerObjectCallback = null;
//		BufferedReader fromServer = null;
		
		Command command = null;

		DicePair dicePair = null;
		
		// create a new thread
		public HandleServerCallback(Socket socket, GameEngine gameEngine) {
			this.socket = socket;
			this.gameEngine = gameEngine;
		}
		
		@Override
		public void run() {
			try {
				// setup streams
				toServerCallback = new PrintWriter(socketCallback.getOutputStream(), true);
				toServerIntCallback = new DataOutputStream(socketCallback.getOutputStream());
				toServerObjectCallback = new ObjectOutputStream(socketCallback.getOutputStream());
				fromServerObjectCallback = new ObjectInputStream(socketCallback.getInputStream());
//				fromServer = new BufferedReader(new InputStreamReader(socketCallback.getInputStream()));
				
			} catch (IOException e) {
				System.out.println("Read failed: " + e.getMessage());
				System.exit(-1);
			}

			// receive commands from server callback
			do {
				try {
					command = (Command)fromServerObjectCallback.readObject();
					
					switch (command) {
						case ADD_GAME_ENGINE_CALLBACK:
							break;
						case ADD_PLAYER:
							break;
						case ADD_POINTS:
							break;
						case CALCULATE_RESULT:
							break;
						case GET_ALL_PLAYERS:
							break;
						case PLACE_BET:
							break;
						case QUIT:
							break;
						case REMOVE_PLAYER:
							break;
						case ROLL_HOUSE:
							break;
						case ROLL_PLAYER:
							break;
						case INTERMEDIATE_RESULT:
							dicePair = (DicePair)fromServerObjectCallback.readObject();
							((GameEngineClientStub)gameEngine).gameEngineCallback.intermediateResult(player, dicePair, gameEngine);
							break;
						case RESULT:
							dicePair = (DicePair)fromServerObjectCallback.readObject();
							((GameEngineClientStub)gameEngine).gameEngineCallback.result(player, dicePair, gameEngine);
							break;
						case INTERMEDIATE_HOUSE_RESULT:
							dicePair = (DicePair)fromServerObjectCallback.readObject();
							((GameEngineClientStub)gameEngine).gameEngineCallback.intermediateHouseResult(dicePair, gameEngine);
							break;
						case HOUSE_RESULT:
							dicePair = (DicePair)fromServerObjectCallback.readObject();
							((GameEngineClientStub)gameEngine).gameEngineCallback.houseResult(dicePair, gameEngine);
							break;
						default:
							break;
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (true);
		}
	}
}
