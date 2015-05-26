/**
 * 
 */
package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.interfaces.CommandInterface;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class ServerSideGameEngineCallback implements GameEngineCallback {
	CommandInterface commandObject = null;
	
	Map<Player, ObjectOutputStream> hashMapObject = new HashMap<Player, ObjectOutputStream>();
	Map<Player, Socket> hashMapSocket = new HashMap<Player, Socket>();
	
	public void connectToServer(Player player, int port) {
		//create new thread to try and connect to the client server 
		HandleAServer task = new HandleAServer(player, port, hashMapObject, hashMapSocket);
		new Thread(task).start();
	}
	
	public void disconnectFromServer(Player player) {
		try {
			hashMapObject.get(player).close();
			hashMapSocket.get(player).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void intermediateResult(Player player, DicePair dicePair,
			GameEngine gameEngine) {
		System.out.println("intermeidate result trying to pass to gameEngineCallbackServer");
		commandObject = new IntermediateResultCommand(player, dicePair);
		
		try {
			hashMapObject.get(player).writeObject(commandObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void result(Player player, DicePair result, GameEngine engine) {
		System.out.println("result trying to pass to gameEngineCallbackServer");
		commandObject = new ResultCommand(player, result);
		
		try {
			hashMapObject.get(player).writeObject(commandObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine) {
		Collection<Player> players = engine.getAllPlayers();
		commandObject = new IntermediateHouseResultCommand(dicePair);
		
		for (Player player: players) {
			try {
				hashMapObject.get(player).writeObject(commandObject);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void houseResult(DicePair result, GameEngine engine) {
		Collection<Player> players = engine.getAllPlayers();
		commandObject = new HouseResultCommand(result);
		
		for (Player player: players) {
			try {
				hashMapObject.get(player).writeObject(commandObject);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateResult(Player player, GameEngine gameEngine) {
		commandObject = new UpdateResultCommand(player);
		
		try {
			hashMapObject.get(player).reset();
			hashMapObject.get(player).writeObject(commandObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disableRollHouse(Player player) {
		commandObject = new DisableRollHouseCommand();
		
		try {
			hashMapObject.get(player).writeObject(commandObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
