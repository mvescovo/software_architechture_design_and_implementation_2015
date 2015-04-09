package test;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class TestPanels extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5395847951658785460L;

	public TestPanels() {
		ImageIcon diceIcon1 = new ImageIcon("image/diceIcon1.png");
		ImageIcon diceIcon2 = new ImageIcon("image/diceIcon2.jpg");
		JPanel p1 = new JPanel();
		JButton jbt = new JButton("Dice icon", diceIcon1);
		jbt.setRolloverIcon(diceIcon2);
		jbt.setIconTextGap(5);
		p1.setLayout(new GridLayout(4, 3));
		
		for (int i = 1; i <=9; i++) {
			p1.add(new JButton("" + i));
		}
		
		p1.add(new JButton("" + 0));
		p1.add(new JButton("Start"));
		p1.add(new JButton("Stop"));
		
		JPanel p2 = new JPanel(new BorderLayout());
		p2.add(new JTextField("Time to be displayed here"),
				BorderLayout.NORTH);
		p2.add(p1, BorderLayout.CENTER);
		
		add(p2, BorderLayout.EAST);
		add(jbt, BorderLayout.CENTER);
		
		Border b = new TitledBorder("Buttons");
		p1.setBorder(b);
		
		// playing around
		
		
		JButton jbtOK = new JButton("OK here is some text.");
		jbtOK.setBackground(Color.BLACK);
		jbtOK.setForeground(new Color(255, 255, 1));
		
		Font font1 = new Font("SansSerif", Font.BOLD, 16);
		jbtOK.setFont(font1);
		
		add(jbtOK, BorderLayout.NORTH);
		
		jbtOK.setToolTipText("Test tool tip");
		
		p1.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public static void main(String[] args) {
		TestPanels frame = new TestPanels();
		frame.setTitle("The Front View of a Microwave Oven");
		frame.setSize(400, 250);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
