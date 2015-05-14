/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Commands.Command;
import model.interfaces.DicePair;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class GameEngineCallbackServerOld {
	ServerSocket serverSocket = null;
	int callbackPort = 0;
	Socket clientSocket = null;
	
	// streams
	private BufferedReader fromClient = null;
	private DataInputStream fromClientInt = null;
	private ObjectInputStream fromClientObject = null;
	private ObjectOutputStream toClientObject = null;
//	DataOutputStream toClientInt = null;
//	PrintWriter toClient = null;

	Command command = null;
	
	public GameEngineCallbackServerOld(int callbackPort) {
		this.callbackPort = callbackPort;
		
		// create a server socket
		try {
			serverSocket = new ServerSocket(callbackPort);
		} catch(IOException e) {
			System.out.println("Could not listen on port " + callbackPort);
			System.exit(-1);
		}
		
		try {
			// listen for a new connection from a client
			clientSocket = serverSocket.accept();
			
			// setup streams
			fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			fromClientInt = new DataInputStream(clientSocket.getInputStream());
			fromClientObject = new ObjectInputStream(clientSocket.getInputStream());
			toClientObject = new ObjectOutputStream(clientSocket.getOutputStream());
//			toClientInt = new DataOutputStream(clientSocket.getOutputStream());
//			toClient = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Accept failed: " + callbackPort);
			System.exit(-1);
		}
	}
	
	public void sendIntermediateResult(DicePair dicePair) {
		try {
			command = Command.INTERMEDIATE_RESULT;
			toClientObject.writeObject(command);
			toClientObject.writeObject(dicePair);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendResult(DicePair dicePair) {
		try {
			command = Command.RESULT;
			toClientObject.writeObject(command);
			toClientObject.writeObject(dicePair);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendIntermediateHouseResult(DicePair dicePair) {
		try {
			command = Command.INTERMEDIATE_HOUSE_RESULT;
			toClientObject.writeObject(command);
			toClientObject.writeObject(dicePair);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendHouseResult(DicePair dicePair) {
		try {
			command = Command.HOUSE_RESULT;
			toClientObject.writeObject(command);
			toClientObject.writeObject(dicePair);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
