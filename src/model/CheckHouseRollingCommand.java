/**
 * 
 */
package model;

import java.io.IOException;
import java.io.Serializable;

import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class CheckHouseRollingCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = -8201161496001261313L;
	Response response = null;

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		if (((GameEngineImpl)gameEngine).isHouseRolling()) {
			response = new Response(true);
			try {
				handleAClient.getObjectOutputStream().writeObject(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			response = new Response(false);
			try {
				handleAClient.getObjectOutputStream().writeObject(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
