/**
 * 
 */
package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GameEngineCallbackServer {
	GameEngine gameEngine = null;
	GameEngineCallback gameEngineCallback = null;
	int port = 0;
	ServerSocket serverSocket = null;
	Socket clientSocket = null;
	
	public GameEngineCallbackServer(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}
	public void startServer() {
		// create a server socket
		try {
			serverSocket = new ServerSocket(port);
			port = serverSocket.getLocalPort();
		} catch(IOException e) {
			System.out.println("Could not listen on port " + port);
			System.exit(-1);
		}
		
		// wait for connection in new thread so the method returns
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {			
				try {
					// listen for a new connection from the server callback
					System.out.println("Server listening for connection..");
					clientSocket = serverSocket.accept();
					
					// create a new thread for the connection and start it
					HandleAClient2 task = new HandleAClient2(gameEngine, clientSocket);
					new Thread(task).start();
				} catch (IOException e) {
					System.out.println("Accept failed: " + port);
					System.exit(-1);
				}
			}
		});
		
		thread.start();
	}
	
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallback = gameEngineCallback;
	}
	
	public int getPort() {
		return port;
	}
}
