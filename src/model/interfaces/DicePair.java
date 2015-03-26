package model.interfaces;

/**
 * Assignment interface for SADI representing Dice values
 * 
 * (setting dice values is handled by the implementing class constructor(s))
 * 
 * @author Caspar Ryan
 * 
 */
public interface DicePair
{
	/**
	 * @return value of dice 1
	 */
	public int getDice1();

	/**
	 * @return value of dice 2
	 */
	public int getDice2();

	/**
	 * @return return number of faces (standard casino dice has 6) Dungeons and
	 *         Dragons dice have more!
	 */
	public int getNumFaces();
}