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
public class AddPlayerCommand implements CommandInterface, Serializable {
	private static final long serialVersionUID = -2908872413858147838L;
	Player player = null;
	int port;
	
	public AddPlayerCommand(Player player, int port) {
		this.player = player;
		this.port = port;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		((GameEngineImpl)gameEngine).connectCallbackServer(player, port);
		System.out.println("add player connected call back server and will now try to add player");
		gameEngine.addPlayer(player);
	}

	@Override
	public void execute(GameEngine gameEngine, HandleAClient2 handleAClient) {
		// TODO Auto-generated method stub
	}
}
