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
import java.util.HashMap;
import java.util.Map;

import model.Commands.Command;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class ServerSideGameEngineCallback implements GameEngineCallback {
	// socket variables
//	Socket socket;
	String serverName = "localhost";
//	int port = 0;
	boolean connected = false;
	
	Command command = null;
	
//	public ServerSideGameEngineCallback() {
//		//create new thread to try and connect to the client server (so this constructor returns)
//		HandleAServer task = new HandleAServer();
//		new Thread(task).start();
//	}
	
	Map<Player, ObjectOutputStream> hashMapObject = new HashMap<Player, ObjectOutputStream>();

//	Map<Player, GameEngineCallbackServer> hashMap = new HashMap<Player, GameEngineCallbackServer>();
//
//	public void addToMap(Player player, GameEngineCallbackServer gameEngineCallbackServer) {
//		this.hashMap.put(player, gameEngineCallbackServer);
//	}
	
	public void connectToServer(Player player, int port) {
		//create new thread to try and connect to the client server (so this constructor returns)
		HandleAServer task = new HandleAServer(player, port);
		new Thread(task).start();
	}
	
	@Override
	public void intermediateResult(Player player, DicePair dicePair,
			GameEngine gameEngine) {
		System.out.println("intermeidate result trying to pass to gameEngineCallbackServer");
//		hashMap.get(player).sendIntermediateResult(dicePair);
		command = Command.INTERMEDIATE_RESULT;
		
		try {
			hashMapObject.get(player).writeObject(command);
			hashMapObject.get(player).writeObject(player);
			hashMapObject.get(player).writeObject(dicePair);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void result(Player player, DicePair result, GameEngine engine) {
		System.out.println("result trying to pass to gameEngineCallbackServer");
//		hashMap.get(player).sendResult(result);
		command = Command.RESULT;
		
		try {
			hashMapObject.get(player).writeObject(command);
			hashMapObject.get(player).writeObject(player);
			hashMapObject.get(player).writeObject(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine) {
		Collection<Player> players = engine.getAllPlayers();
		command = Command.INTERMEDIATE_HOUSE_RESULT;
		
		for (Player player: players) {
			try {
				hashMapObject.get(player).writeObject(command);
				hashMapObject.get(player).writeObject(dicePair);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void houseResult(DicePair result, GameEngine engine) {
		Collection<Player> players = engine.getAllPlayers();
		command = Command.HOUSE_RESULT;
		
		for (Player player: players) {
			try {
				hashMapObject.get(player).writeObject(command);
				hashMapObject.get(player).writeObject(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class HandleAServer implements Runnable {
		Player player = null;
		Socket socket = null;
		int port = 0;
		
		// streams
//		PrintWriter toServer = null;
//		DataOutputStream toServerInt = null;
		ObjectOutputStream toServerObject = null;
//		ObjectInputStream fromServerObject = null;
//		BufferedReader fromServer = null;
		
		public HandleAServer(Player player, int port) {
			this.player = player;
			this.port = port;
		}

		@Override
		public void run() {
			while (!connected) {
				try {
					// connect to the server
					socket = new Socket(serverName, port);
					
					// setup streams
//					toServer = new PrintWriter(socket.getOutputStream(), true);
//					toServerInt = new DataOutputStream(socket.getOutputStream());
					toServerObject = new ObjectOutputStream(socket.getOutputStream());
					hashMapObject.put(player, toServerObject);
//					fromServerObject = new ObjectInputStream(socket.getInputStream());
//					fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
		
	}
}
