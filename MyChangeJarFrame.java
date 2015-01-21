/**********************************************************************
Graphical representation of three ChangeJar objects. Includes 3
different MyChangeJarPanel classes running in one frame.

@author Conner Toney
@version GVSU Winter 2015
 *********************************************************************/
package package1;

import java.awt.*;
import javax.swing.*;

public class MyChangeJarFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Change Jar");
		frame.setLayout(new GridLayout(3, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyChangeJarPanel jar = new MyChangeJarPanel();
		JPanel panel = new JPanel();
		panel.add(jar);
		
		MyChangeJarPanel jar2 = new MyChangeJarPanel();
		JPanel panel2 = new JPanel();
		panel2.add(jar2);
		
		MyChangeJarPanel jar3 = new MyChangeJarPanel();
		JPanel panel3 = new JPanel();
		panel3.add(jar3);
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(panel2);
		frame.getContentPane().add(panel3);
		frame.pack();
		frame.setVisible(true);
	}
}
