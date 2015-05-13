/**
 * 
 */
package model;

import java.util.HashMap;
import java.util.Map;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class ServerSideGameEngineCallback implements GameEngineCallback {
	Map<Player, GameEngineCallbackServer> hashMap = new HashMap<Player, GameEngineCallbackServer>();

	public void addToMap(Player player, GameEngineCallbackServer gameEngineCallbackServer) {
	this.hashMap.put(player, gameEngineCallbackServer);
}
	
	@Override
	public void intermediateResult(Player player, DicePair dicePair,
			GameEngine gameEngine) {
		hashMap.get(player).sendIntermediateResult(dicePair);
//		try {
//			command = Command.INTERMEDIATE_RESULT;
//			hashMapObject.get(player).writeObject(command);
//			hashMapObject.get(player).writeObject(dicePair);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void result(Player player, DicePair result, GameEngine engine) {
//		try {
//			command = Command.RESULT;
//			hashMapObject.get(player).writeObject(command);
//			hashMapObject.get(player).writeObject(result);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine) {
//		Collection<Player> players = engine.getAllPlayers();
//		
//		for (Player player: players) {
//			try {
//				command = Command.INTERMEDIATE_HOUSE;
//				hashMapObject.get(player).writeObject(command);
//				hashMapObject.get(player).writeObject(dicePair);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}

	@Override
	public void houseResult(DicePair result, GameEngine engine) {
//		Collection<Player> players = engine.getAllPlayers();
//		
//		for (Player player: players) {
//			try {
//				command = Command.HOUSE_RESULT;
//				hashMapObject.get(player).writeObject(command);
//				hashMapObject.get(player).writeObject(result);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
