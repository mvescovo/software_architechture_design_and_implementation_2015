/**
 * 
 */
package application;

import java.net.ServerSocket;
import java.net.Socket;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class ServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final GameEngine gameEngine = new GameEngineImpl();
		
		ServerSocket serverSocket;
		
		try {
			serverSocket = new ServerSocket(10000);
			
			while (true) {
				System.out.println("Game server waiting for client...");
				Socket socket = serverSocket.accept();
				System.out.println("Client connected to game server");
//				socket.close();
//				System.out.println("Socket closed");
			}
		} catch(Exception e) {
			
		}
	}
}
