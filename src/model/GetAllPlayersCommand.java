/**
 * 
 */
package model;

import java.io.Serializable;

import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GetAllPlayersCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = 3947096863737072918L;

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		// TODO Auto-generated method stub
	}
}
