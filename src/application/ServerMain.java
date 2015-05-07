/**
 * 
 */
package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
		ServerSocket serverSocket = null;
		Socket client = null;
		BufferedReader in = null;
		PrintWriter out = null;
		String line;
		
		// listen for a connection
		try {
			serverSocket = new ServerSocket(10000);
			
//			while (true) {
//				System.out.println("Game server waiting for client...");
//				Socket socket = serverSocket.accept();
//				System.out.println("Client connected to game server");
////				socket.close();
////				System.out.println("Socket closed");
//			}
		} catch(IOException e) {
			System.out.println("Could not listen on port 10000");
			System.exit(-1);
		}
		
		// accept a connection
		try {
			client = serverSocket.accept();
		} catch (IOException e) {
			System.out.println("Accept failed: 10000");
			System.exit(-1);
		}
		
		// listen for client printWriter
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Read failed");
			System.exit(-1);
		}
		
		// listen on the socket
		while (true) {
			try {
				line = in.readLine();
				// send data back to the client
				out.println(line);
			} catch (IOException e) {
				System.out.println("Read failed");
				System.exit(-1);
			}
		}
	}
}
