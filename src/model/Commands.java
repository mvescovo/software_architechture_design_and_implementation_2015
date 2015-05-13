/**
 * 
 */
package model;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class Commands {
	public enum Command {
		ROLL_PLAYER, ROLL_HOUSE, ADD_PLAYER, REMOVE_PLAYER, CALCULATE_RESULT,
		ADD_GAME_ENGINE_CALLBACK, GET_ALL_PLAYERS, PLACE_BET, ADD_POINTS, QUIT, EXIT,
		SUCCESS, FAIL, INTERMEDIATE_RESULT, RESULT, HOUSE_RESULT,
		INTERMEDIATE_HOUSE_RESULT
	}
}
