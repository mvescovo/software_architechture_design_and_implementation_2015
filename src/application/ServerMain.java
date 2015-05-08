/**
 * 
 */
package application;

import model.GameEngineImpl;
import model.GameEngineServerStub;
import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class ServerMain {
	public static void main(String[] args) {
		GameEngine gameEngine = new GameEngineImpl();
		
		new GameEngineServerStub(gameEngine);
	}
}