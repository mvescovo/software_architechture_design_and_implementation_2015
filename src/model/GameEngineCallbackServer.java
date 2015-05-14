/**
 * 
 */
package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.Commands.Command;
import model.interfaces.GameEngineCallback;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GameEngineCallbackServer {
	GameEngineCallback gameEngineCallback;
	GameEngineClientStub gameEngineClietStub;
	
	int port = 8000;
	ServerSocket serverSocket = null;
	
	public GameEngineCallbackServer() {
		// create a new thread to listen for a connection and handle the client, then start it
		HandleAClient task = new HandleAClient();
		new Thread(task).start();
	}
	
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallback = gameEngineCallback;
	}
	
	private class HandleAClient implements Runnable {
		private Socket clientSocket = null;
		
		// streams
//		BufferedReader fromClient = null;
//		DataInputStream fromClientInt = null;
//		ObjectInputStream fromClientObject = null;
//		ObjectOutputStream toClientObject = null;
//		DataOutputStream toClientInt = null;
//		PrintWriter toClient = null;
		
		Command command = null;
		
		boolean quit = false;

		@Override
		public void run() {
			// create a server socket
			try {
				serverSocket = new ServerSocket(port);
				System.out.println(serverSocket.getLocalPort());
			} catch(IOException e) {
				System.out.println("Could not listen on port " + port);
				System.exit(-1);
			}
			
			try {
				// listen for a new connection from the server callback
				System.out.println("client server listening for connection");
				while(true) {
					clientSocket = serverSocket.accept();
				}
				
			} catch (IOException e) {
				System.out.println("Accept failed: " + port);
				System.exit(-1);
			}
			
			// setup streams
//			fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//			fromClientInt = new DataInputStream(clientSocket.getInputStream());
//			fromClientObject = new ObjectInputStream(clientSocket.getInputStream());
//			toClientObject = new ObjectOutputStream(clientSocket.getOutputStream());
//			toClientInt = new DataOutputStream(clientSocket.getOutputStream());
//			toClient = new PrintWriter(clientSocket.getOutputStream(), true);
			
			// receive command from server
			do {
//				try {
//					command = (Command)fromServerObject.readObject();
//					
//					switch (command) {
//						default:
//							break;
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			} while (quit == false);
		}
	}
}
