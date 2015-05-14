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
	Socket socket;
	String serverName = "localhost";
	int port = 8000;
	boolean connected = false;
	
	// streams
	PrintWriter toServer = null;
	DataOutputStream toServerInt = null;
	ObjectOutputStream toServerObject = null;
	ObjectInputStream fromServerObject = null;
//	BufferedReader fromServer = null;
	
	Command command = null;
	
	public ServerSideGameEngineCallback() {
		//create new thread to try and connect to the client server (so this constructor returns)
		HandleAServer task = new HandleAServer();
		new Thread(task).start();
	}
	
	
//	Map<Player, GameEngineCallbackServer> hashMap = new HashMap<Player, GameEngineCallbackServer>();
//
//	public void addToMap(Player player, GameEngineCallbackServer gameEngineCallbackServer) {
//		this.hashMap.put(player, gameEngineCallbackServer);
//	}
	
	@Override
	public void intermediateResult(Player player, DicePair dicePair,
			GameEngine gameEngine) {
//		hashMap.get(player).sendIntermediateResult(dicePair);
	}

	@Override
	public void result(Player player, DicePair result, GameEngine engine) {
//		hashMap.get(player).sendResult(result);
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine) {
//		Collection<Player> players = engine.getAllPlayers();
//		
//		for (Player player: players) {
//			hashMap.get(player).sendIntermediateHouseResult(dicePair);
//		}
	}

	@Override
	public void houseResult(DicePair result, GameEngine engine) {
//		Collection<Player> players = engine.getAllPlayers();
//		
//		for (Player player: players) {
//			hashMap.get(player).sendHouseResult(result);
//		}
	}
	
	private class HandleAServer implements Runnable {


		@Override
		public void run() {
			while (!connected) {
				try {
					// connect to the server
					socket = new Socket(serverName, port);
					
					// setup streams
//					toServer = new PrintWriter(socket.getOutputStream(), true);
//					toServerInt = new DataOutputStream(socket.getOutputStream());
//					toServerObject = new ObjectOutputStream(socket.getOutputStream());
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
