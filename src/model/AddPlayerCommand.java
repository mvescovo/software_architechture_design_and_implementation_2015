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
public class AddPlayerCommand implements CommandInterface, Serializable {
	private static final long serialVersionUID = -2908872413858147838L;
	private Player player = null;
	private int port;
	
	public AddPlayerCommand(Player player, int port) {
		this.player = player;
		this.port = port;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		// update player ID so it's unique on the server
		((SimplePlayer)player).setPlayerId(((GameEngineImpl)gameEngine).takeNextAvailableId());
		
		// send updated player back to client
		try {
			handleAClient.getObjectOutputStream().writeObject(player);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// connect the player callbackserver and add player to the server
		((GameEngineImpl)gameEngine).connectCallbackServer(player, port);
		gameEngine.addPlayer(player);
	}
}
