/**
 * 
 */
package model;

import java.util.Collection;
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
	}

	@Override
	public void result(Player player, DicePair result, GameEngine engine) {
		hashMap.get(player).sendResult(result);
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine) {
		Collection<Player> players = engine.getAllPlayers();
		
		for (Player player: players) {
			hashMap.get(player).sendIntermediateHouseResult(dicePair);
		}
	}

	@Override
	public void houseResult(DicePair result, GameEngine engine) {
		Collection<Player> players = engine.getAllPlayers();
		
		for (Player player: players) {
			hashMap.get(player).sendHouseResult(result);
		}
	}
}
