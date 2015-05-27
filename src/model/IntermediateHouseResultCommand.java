/**
 * 
 */
package model;

import java.io.Serializable;

import model.interfaces.CommandInterface;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class IntermediateHouseResultCommand implements Serializable,
		CommandInterface {
	private static final long serialVersionUID = 7765390502907718761L;
	private DicePair dicePair = null;
	
	public IntermediateHouseResultCommand(DicePair dicePair) {
		this.dicePair = dicePair;
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		((GameEngineClientStub)gameEngine).getGameEngineCallback().intermediateHouseResult(dicePair, gameEngine);
	}
}
