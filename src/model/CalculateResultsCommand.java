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
public class CalculateResultsCommand implements Serializable, CommandInterface {
	private static final long serialVersionUID = -3136688335648899058L;

	@Override
	public void execute(GameEngine gameEngine, HandleAClient handleAClient) {
		gameEngine.calculateResult();
	}
}
