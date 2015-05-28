/**
 * 
 */
package model;

import java.io.Serializable;

import model.interfaces.CommandInterface;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class IntermediateHouseResultCommand implements Serializable,
		CommandInterface {
	private static final long serialVersionUID = 7765390502907718761L;
	private DicePair dicePair = null;
	private Player player = null;
	
	public IntermediateHouseResultCommand(DicePair dicePair) {
		this.dicePair = dicePair;
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		player = ((GameEngineClientStub)gameEngine).getPlayer();
		System.out.println("intermediateHouseResultCommand message");
		System.out.println(player.getPlayerName() + " participating: " + ((SimplePlayer)player).getIsParticipatingInRound());
		
		((GameEngineCallbackImplGUI)((GameEngineClientStub)gameEngine).getGameEngineCallback()).showSittingPlayerMessage(player);
		((GameEngineClientStub)gameEngine).getGameEngineCallback().intermediateHouseResult(dicePair, gameEngine);
	}
}
