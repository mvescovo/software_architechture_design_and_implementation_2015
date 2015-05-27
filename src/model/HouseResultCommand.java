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
public class HouseResultCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = 2831773661962563318L;
	private DicePair dicePair = null;
	
	public HouseResultCommand(DicePair dicePair) {
		this.dicePair = dicePair;
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		((GameEngineClientStub)gameEngine).getGameEngineCallback().houseResult(dicePair, gameEngine);
	}
}
