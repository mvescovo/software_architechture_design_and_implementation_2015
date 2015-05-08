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
		// TODO Auto-generated method stub
		
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
