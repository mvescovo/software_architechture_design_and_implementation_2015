/**
 * 
 */
package application;

import model.GameEngineImpl;
import model.GameEngineServerStub;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class ServerMain {
	public static void main(String[] args) {
		new GameEngineServerStub(new GameEngineImpl());
	}
}