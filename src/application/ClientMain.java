package application;

import javax.swing.SwingUtilities;

import view.MainFrame;
import model.GameEngineClientStub;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
//Assignment 2 version
public class ClientMain {
	public static void main(String[] args) {
		// Launch the GUI from the EDT to avoid problems
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame(new GameEngineClientStub());
//				new MainFrame(new GameEngineImpl());
			}
		});
	}
}
