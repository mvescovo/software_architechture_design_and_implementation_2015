/**
 * 
 */
package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
	Map<Player, DataOutputStream> hashMapInt = new HashMap<Player, DataOutputStream>();
	Map<Player, ObjectOutputStream> hashMapObject = new HashMap<Player, ObjectOutputStream>();

	public void addToMapInt(Player player, DataOutputStream dataOutputStream) {
		this.hashMapInt.put(player, dataOutputStream);
	}
	
	public void addToMapObject(Player player, ObjectOutputStream objectOutputStream) {
		this.hashMapObject.put(player, objectOutputStream);
	}
	
	@Override
	public void intermediateResult(Player player, DicePair dicePair,
			GameEngine gameEngine) {
		int total = dicePair.getDice1() + dicePair.getDice2();

		try {
			hashMapObject.get(player).writeObject(dicePair);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void result(Player player, DicePair result, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void houseResult(DicePair result, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}
}
