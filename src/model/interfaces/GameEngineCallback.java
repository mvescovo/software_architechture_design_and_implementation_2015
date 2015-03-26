package model.interfaces;

/**
 * Assignment interface for SADI to notify client of GameEngine events e.g.
 * ongoing dice roll
 * 
 * @author Caspar Ryan
 * 
 */
public interface GameEngineCallback
{
	/**
	 * called as the dice are rolling for a Player use this to update your
	 * display or log to console
	 * 
	 * @param player
	 *            the Player who rolled
	 * @param dicePair
	 *            the current (upfacing) values of the rolling dice
	 * @param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * @see model.interfaces.GameEngine
	 */
	public void intermediateResult(Player player, DicePair dicePair,
			GameEngine engine);

	/**
	 * 
	 * called when dice have stopped rolling
	 * 
	 * @param player
	 *            the Player who rolled
	 * @param result
	 *            the final resting state of the rolled dice
	 * @param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * @see model.interfaces.GameEngine
	 */
	public void result(Player player, DicePair result, GameEngine engine);

	/**
	 * called as the HOUSE dice are rolling use this to update your display or
	 * log to console
	 * 
	 * @param dicePair
	 *            the current (upfacing) values of the rolling dice
	 * @param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * @see model.interfaces.GameEngine
	 */
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine);

	/**
	 * 
	 * called when HOUSE dice have stopped rolling
	 * 
	 * @param result
	 *            the final resting state of the rolled dice
	 * @param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * @see model.interfaces.GameEngine
	 */
	public void houseResult(DicePair result, GameEngine engine);
}
