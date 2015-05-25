/**
 * 
 */
package model;

import java.io.Serializable;

import model.GameEngineServerStub.HandleAClient;
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
	
//	public void setPlayer(Player player) {
//		this.player = player;
//	}
//	
	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
//		addPlayerOnServerStub(player, port);
		System.out.println("got add player object");
		((GameEngineImpl)gameEngine).connectCallbackServer(player, port);
		gameEngine.addPlayer(player);
		
	}
}
