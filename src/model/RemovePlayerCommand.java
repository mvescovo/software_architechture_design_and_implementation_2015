/**
 * 
 */
package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class RemovePlayerCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = -4252525526679231560L;
	private String playerId = null;
	private Response response = null;
	private Collection<Player> players = null;

	
	public RemovePlayerCommand(String playerId) {
		this.playerId = playerId;
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		players = gameEngine.getAllPlayers();

		for (Player player: players) {
			if (player.getPlayerId().contentEquals(playerId)) {
				// found the player, remove them
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
				break;
			}
		}
	}
}
