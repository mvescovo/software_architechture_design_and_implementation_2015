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
public class PlaceBetCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = -2729819723187910449L;
	private Player player = null;
	private int bet;
	private Response response = null;
	
	public PlaceBetCommand(Player player, int bet) {
		this.player = player;
		this.bet = bet;
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
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
	}
}
