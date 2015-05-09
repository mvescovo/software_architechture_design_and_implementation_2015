/**
 * 
 */
package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class ServerSideGameEngineCallback implements GameEngineCallback {

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngineCallback#intermediateResult(model.interfaces.Player, model.interfaces.DicePair, model.interfaces.GameEngine)
	 */
	@Override
	public void intermediateResult(Player player, DicePair dicePair,
			GameEngine engine) {
		int total = dicePair.getDice1() + dicePair.getDice2();
		// need to pass the info to the GUI callback on the client
		System.out.println(player.getPlayerName() + " ");
		System.out.println(total + "\n");
	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngineCallback#result(model.interfaces.Player, model.interfaces.DicePair, model.interfaces.GameEngine)
	 */
	@Override
	public void result(Player player, DicePair result, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngineCallback#intermediateHouseResult(model.interfaces.DicePair, model.interfaces.GameEngine)
	 */
	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see model.interfaces.GameEngineCallback#houseResult(model.interfaces.DicePair, model.interfaces.GameEngine)
	 */
	@Override
	public void houseResult(DicePair result, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

}
