/**
 * 
 */
package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class HandleAClient implements Runnable {
	private GameEngine gameEngine = null;
	private Socket clientSocket;
	private ObjectInputStream fromClientObject = null;
	private ObjectOutputStream toClientObject = null;
	private CommandInterface commandObject = null;
	private boolean quit = false;
	
	// create a new thread
	public HandleAClient(GameEngine gameEngine, Socket socket) {
		this.gameEngine = gameEngine;
		this.clientSocket = socket;
	}
	
	public ObjectOutputStream getObjectOutputStream() {
		return toClientObject;
	}
	
	public void closeSocket() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void quit() {
		quit = true;
	}
	
	@Override
	public void run() {
		try {
			// setup streams
			fromClientObject = new ObjectInputStream(clientSocket.getInputStream());
			toClientObject = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Read failed: " + e.getMessage());
			System.exit(-1);
		}

		// receive commands from clients
		do {
			try {
				commandObject = (CommandInterface)fromClientObject.readObject();
				commandObject.execute(gameEngine, this);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (quit == false);
		try {
			toClientObject.close();
			System.out.println("closed object stream");
			clientSocket.close();
			System.out.println("closed socket");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}