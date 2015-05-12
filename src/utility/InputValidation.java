/**
 * Class to do some basic input validation
 */
package utility;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class InputValidation {
	public boolean checkJTextFieldPositiveInt(JTextField textField, JFrame frame) {
		int num = 0;
		boolean numOk = true;	
		
		if (!textField.getText().isEmpty()) {
			try {
				num = Integer.parseInt(textField.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame, "Input must be a number.", "Invalid input", 0);
				textField.setText(null);
				textField.requestFocusInWindow();
				numOk = false;
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Input must not be empty", "Invalid input", 0);
			textField.setText(null);
			textField.requestFocusInWindow();
			numOk = false;
		}
		
		if ((numOk == true) && num <= 0) {
			JOptionPane.showMessageDialog(frame, "Input must be greater than 0.", "Invalid input", 0);
			textField.setText(null);
			textField.requestFocusInWindow();
			numOk = false;
		}
			
		return numOk; 
	}
	
	public boolean checkJTextFieldNonEmptyString(JTextField textField, JFrame frame) {
		boolean ok = true;
		
		return ok;
	}
}