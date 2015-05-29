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
public class PlaceBetCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = -2729819723187910449L;
	private String playerId = null;
	private int bet;
	private Response response = null;
	private Collection<Player> players = null;

	
	public PlaceBetCommand(String playerId, int bet) {
		this.playerId = playerId;
		this.bet = bet;
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		players = gameEngine.getAllPlayers();

		for (Player player: players) {
			if (player.getPlayerId().contentEquals(playerId)) {
				// found the player, place bet for them
				try {
					if (gameEngine.placeBet(player, bet)) {
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
