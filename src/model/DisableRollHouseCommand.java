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
public class DisableRollHouseCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = -44237342306094052L;

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		// TODO Auto-generated method stub
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient2 handleAClient) {
		((GameEngineCallbackImplGUI)((GameEngineClientStub)gameEngine).getGameEngineCallback()).disableHouse();
	}
}
