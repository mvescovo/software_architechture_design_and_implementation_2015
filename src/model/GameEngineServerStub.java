/**
 * 
 */
package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.interfaces.CommandInterface;
import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GameEngineServerStub {
	GameEngine gameEngine;
	int callbackPort = 10001;
	
	@SuppressWarnings("resource")
	public GameEngineServerStub(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		gameEngine.addGameEngineCallback(new ServerSideGameEngineCallback());
		ServerSocket serverSocket = null;
		int port = 10001;
		
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
				System.out.println("Server listening for connection..");
				Socket clientSocket = serverSocket.accept();

				// create a new thread for the connection and start it
				HandleAClient task = new HandleAClient(clientSocket, callbackPort);
				callbackPort++;
				new Thread(task).start();
			} catch (IOException e) {
				System.out.println("Accept failed: " + port);
				System.exit(-1);
			}
		}
	}
	
	public class HandleAClient implements Runnable {
		private Socket clientSocket;
		private ObjectInputStream fromClientObject = null;
		private ObjectOutputStream toClientObject = null;
		private CommandInterface commandObject = null;
		
		boolean quit = false;
		
		// create a new thread
		public HandleAClient(Socket socket, int callbackPort) {
			this.clientSocket = socket;
//			this.callbackPort = callbackPort;
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
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				commandObject.execute(gameEngine, this);
				
			} while (quit == false);
			
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
}
