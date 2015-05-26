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
public class RollPlayerCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = -2847156363711340833L;
	private Player player = null;
	private int initialDelay;
	private int finalDelay;
	private int delayIncrement;
	
	public RollPlayerCommand(Player player, int initialDelay, int finalDelay,
			int delayIncrement) {
		this.player = player;
		this.initialDelay = initialDelay;
		this.finalDelay = finalDelay;
		this.delayIncrement = delayIncrement;
	}
	
	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		gameEngine.rollPlayer(player, initialDelay, finalDelay, delayIncrement);
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient2 handleAClient) {
		// TODO Auto-generated method stub
		
	}
}
