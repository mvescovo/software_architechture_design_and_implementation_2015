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
	private GameEngine gameEngine = null;
	private GameEngineCallback gameEngineCallback = null;
	private int port = 0;
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	
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
		
		// create new thread to wait for connection so the method returns
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {			
				try {
					// listen for a new connection from the server callback
					System.out.println("Server listening for connection..");
					clientSocket = serverSocket.accept();
					
					// create a new thread for the connection and start it
					HandleAClient task = new HandleAClient(gameEngine, clientSocket);
					new Thread(task).start();
				} catch (IOException e) {
					System.out.println("Accept failed: " + port);
					System.exit(-1);
				}
			}
		});
		
		thread.start();
	}
	
	public void stopServer() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		port = 0;
	}
	
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallback = gameEngineCallback;
	}
	
	public int getPort() {
		return port;
	}

	public GameEngineCallback getGameEngineCallback() {
		return gameEngineCallback;
	}
}
