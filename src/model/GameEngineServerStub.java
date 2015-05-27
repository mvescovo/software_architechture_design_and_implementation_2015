/**
 * 
 */
package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GameEngineServerStub {
	GameEngine gameEngine = null;
	int port = 10000;
	ServerSocket serverSocket = null;
	Socket clientSocket = null;
	
	public GameEngineServerStub(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		gameEngine.addGameEngineCallback(new ServerSideGameEngineCallback());

		// create a server socket
		try {
			serverSocket = new ServerSocket(port);
		} catch(IOException e) {
			System.out.println("Could not listen on port " + port);
			System.exit(-1);
		}
		
		while (true) {
			try {
				// listen for a new connection from a client
				System.out.println("Server listening for connection...");
				clientSocket = serverSocket.accept();
				System.out.println("Server accepted a client connection");

				// create a new thread for the connection and start it
				HandleAClient task = new HandleAClient(gameEngine, clientSocket);
				new Thread(task).start();
			} catch (IOException e) {
				System.out.println("Accept failed: " + port);
				System.exit(-1);
			}
		}
	}
}
