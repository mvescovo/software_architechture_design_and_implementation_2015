package model.interfaces;

import java.util.Collection;

/**
 * Assignment interface for SADI providing main Dice Game model functionality
 * 
 * @author Caspar Ryan
 * 
 */
public interface GameEngine
{

	/**
	 * set the default size of the dice
	 */
	public static int NUM_FACES = 6;

	/**
	 * roll the dice progressing from the initialDelay to the finalDelay in
	 * increments of delayIncrement, delays are in milliseconds (ms)
	 * 
	 * 1. start at initialDelay then increment the delay each time a new number
	 * is shown on the die faces 2. call
	 * GameEngineCallback.intermediateResult(...) or
	 * intermediateHouseResult(...) each time a pair of new dice faces are shown
	 * until delay >= finalDelay 3. call GameEngineCallback.result(...) or
	 * houseResult(...) with final result for player or house 4. make sure you
	 * update the player with final result so it can be retrieved later
	 * 
	 * @param player
	 *            the player who is rolling and will have their rollResult set
	 *            at the end of the roll
	 * @param initialDelay
	 *            the starting delay in ms between updates (based on how fast
	 *            dice are rolling)
	 * @param finalDelay
	 *            the final delay in ms between updates when the dice stop
	 *            rolling
	 * @param delayIncrement
	 *            how much the dice slow down (delay gets longer) after each
	 *            roll/tumble
	 * 
	 * @see model.interfaces.GameEngineCallback
	 * 
	 */
	public abstract void rollPlayer(Player player, int initialDelay,
			int finalDelay, int delayIncrement);

	/**
	 * Same as rollPlayer() but rolls for the house and calls the house versions
	 * of the callback methods on GameEngineCallback, no player parameter is
	 * required
	 * 
	 * @param initialDelay
	 * @param finalDelay
	 * @param delayIncrement
	 * 
	 * @see GameEngine#rollPlayer(Player, int, int, int)
	 */
	public abstract void rollHouse(int initialDelay, int finalDelay,
			int delayIncrement);

	/**
	 * @param player
	 *            to add to game
	 */
	public abstract void addPlayer(Player player);

	/**
	 * @param player
	 *            to remove from game
	 * @return true if the player existed
	 */
	public abstract boolean removePlayer(Player player);

	/**
	 * this method rolls for the house then goes through all players and applies
	 * win or loss to update betting points. Callback should be called for house
	 * roll to allow GUI/client updates
	 * 
	 * @see model.interfaces.GameEngineCallback
	 */
	public abstract void calculateResult();

	/**
	 * @param gameEngineCallback
	 *            a client specific implementation of GameEngineCallback used to
	 *            perform display updates etc.
	 * 
	 *            you will use a different implementation for GUI and console
	 *            versions
	 */
	public abstract void addGameEngineCallback(
			GameEngineCallback gameEngineCallback);

	/**
	 * 
	 * @return an unmodifiable collection of all Players
	 * @see model.interfaces.Player
	 */
	public abstract Collection<Player> getAllPlayers();

	/**
	 * the implementation should forward the call to the player class to handle
	 * (this is required for assignment 2 but should be implemented in
	 * assignment 1)
	 * 
	 * @param bet
	 *            the bet in points
	 * @return true if the player had sufficient points and the bet was placed
	 */
	public abstract boolean placeBet(Player player, int bet);
}