/**
 * 
 */
package model;

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
	Map<Player, ObjectOutputStream> hashMap = new HashMap<Player, ObjectOutputStream>();

	public void addToMap(Player player, ObjectOutputStream objectOutputStream) {
		this.hashMap.put(player, objectOutputStream);
	}
	
	@Override
	public void intermediateResult(Player player, DicePair dicePair,
			GameEngine gameEngine) {
		int total = dicePair.getDice1() + dicePair.getDice2();
		// need to pass the info to the GUI callback on the client
		try {
			hashMap.get(player).writeObject(player);
			hashMap.get(player).writeObject(dicePair);
			System.out.println("passed intermediate GUI refresh to client");
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
