package test;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

// Programming Principles 1B
// Chapter 6 Lecture Notes Code

public class JHelloWorld extends JFrame
{

   /**
	 * 
	 */
	private static final long serialVersionUID = -5323234856680237976L;
JLabel greeting = new JLabel("Hello world!");

   public JHelloWorld()
   {
      Container c = getContentPane();
      c.setLayout(new FlowLayout());
      c.add(greeting);
      setSize(400,  300);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setLocationRelativeTo(null);
      setVisible(true);
   }

   public static void main(String args[])
   {
      new JHelloWorld();
   }
}