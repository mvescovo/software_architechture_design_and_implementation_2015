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
public class ExitCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = 210125699551556016L;

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		handleAClient.quit();
		handleAClient.closeSocket();
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient2 handleAClient) {
		// TODO Auto-generated method stub
		
	}
}
