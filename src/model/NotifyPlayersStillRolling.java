/**
 * 
 */
package model;

import java.io.Serializable;

import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class NotifyPlayersStillRolling implements CommandInterface,
		Serializable {
	private static final long serialVersionUID = 3651071992447790620L;

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		((GameEngineCallbackImplGUI)((GameEngineClientStub)gameEngine).getGameEngineCallback()).notifyPlayersStillRolling();
	}
}
