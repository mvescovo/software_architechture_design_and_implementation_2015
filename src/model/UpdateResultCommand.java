/**
 * 
 */
package model;

import java.io.Serializable;

import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class UpdateResultCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = -5307173911635236833L;
	private Player player = null;
	
	public UpdateResultCommand(Player player) {
		this.player = player;
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		// TODO Auto-generated method stub
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient2 handleAClient) {
		((GameEngineCallbackImplGUI)((GameEngineClientStub)gameEngine).getGameEngineCallback()).updateResult(player);
	}
}
