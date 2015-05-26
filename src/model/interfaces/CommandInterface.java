/**
 * 
 */
package model.interfaces;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public interface CommandInterface {
	public void execute(GameEngine gameEngine, model.HandleAClient handleAClient);
	public void execute(GameEngine gameEngine, model.HandleAClient2 handleAClient);
}
