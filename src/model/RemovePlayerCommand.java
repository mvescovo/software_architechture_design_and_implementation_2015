/**
 * 
 */
package model;

import java.io.IOException;
import java.io.Serializable;

import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class RemovePlayerCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = -4252525526679231560L;
	private Player player = null;
	private Response response = null;
	
	public RemovePlayerCommand(Player player) {
		this.player = player;
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		try {
			if (gameEngine.removePlayer(player)) {
				((GameEngineImpl)gameEngine).disconnectCallbackServer(player);
				response = new Response(true);
				handleAClient.getObjectOutputStream().writeObject(response);
			} else {
				response = new Response(false);
				handleAClient.getObjectOutputStream().writeObject(response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient2 handleAClient) {
		// TODO Auto-generated method stub
		
	}
}
