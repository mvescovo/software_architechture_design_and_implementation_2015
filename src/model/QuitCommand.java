/**
 * 
 */
package model;

import java.io.Serializable;

import model.GameEngineServerStub.HandleAClient;
import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class QuitCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = 2008748327965637209L;

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		handleAClient.quit();
	}
}
