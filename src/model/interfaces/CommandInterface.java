/**
 * 
 */
package model.interfaces;

import model.GameEngineServerStub.HandleAClient;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public interface CommandInterface {
	public void execute(GameEngine gameEngine, HandleAClient handleAClient);
}
