/**
 * 
 */
package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import model.interfaces.CommandInterface;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class HandleAClient2 implements Runnable {
	GameEngine gameEngine = null;
	private Socket clientSocket = null;
	ObjectInputStream fromClientObject = null;
	CommandInterface commandObject = null;
	Player player = null;
	DicePair dicePair = null;
	boolean quit = false;

	// create a new thread
	public HandleAClient2(GameEngine gameEngine, Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void run() {
		// setup streams
		try {
			fromClientObject = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// receive commands from server
		do {
			try {
				commandObject = (CommandInterface)fromClientObject.readObject();
				commandObject.execute(gameEngine, this);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} while (quit == false);
	}
}