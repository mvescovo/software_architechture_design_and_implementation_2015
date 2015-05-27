/**
 * 
 */
package model;

import java.io.Serializable;

import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class AddPointsCommand implements CommandInterface, Serializable {
	private static final long serialVersionUID = -8898436970611347884L;
	private Player player;
	private int points;
	
	public AddPointsCommand(Player player, int points) {
		this.player = player;
		this.points = points;
	}
	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		((GameEngineImpl)gameEngine).addPoints(player, points);
//		// TODO may need to check what happens if doing this during a round etc
	}
}
