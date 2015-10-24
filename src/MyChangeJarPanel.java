/**********************************************************************
Graphical representation of the ChangeJar object in a JPanel.

@author Conner Toney
@version GVSU Winter 2015
 *********************************************************************/

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyChangeJarPanel extends JPanel {
	/** ChangeJar object used in panel */
	private ChangeJar jar;
	
	/** button listener used in panel */
	private ButtonListener bListener;
	
	/** label for total quarter amount */
	private JLabel totalQ;
	
	/** label for total dime amount */
	private JLabel totalD;
	
	/** label for total nickel amount */
	private JLabel totalN;
	
	/** label for total penny amount */
	private JLabel totalP;
	
	/** label for total dollar amount */
	private JLabel totalDol;
	
	/** text area for input quarter amount */
	private JTextArea inputQ;
	
	/** text area for input dime amount */
	private JTextArea inputD;
	
	/** text area for input nickel amount */
	private JTextArea inputN;
	
	/** text area for input penny amount */
	private JTextArea inputP;
	
	/** text area for input dollar amount */
	private JTextArea inputDol;
	
	/** button used to add change */
	private JButton addChgBtn;
	
	/** button used to add dollar amount */
	private JButton addDolBtn;
	
	/** button used to subtract change */
	private JButton subChgBtn;
	
	/** button used to subtract dollar amount */
	private JButton subDolBtn;
	
	/** button used to allow/disallow mutation */
	private JButton mutateBtn;

	/******************************************************************
    Constructor used to set up the graphical representation of
    the ChangeJar object in a panel
	 *****************************************************************/
	public MyChangeJarPanel() {
		jar = new ChangeJar();
		bListener = new ButtonListener();
		totalQ = new JLabel("Q: 0");
		totalD = new JLabel("D: 0");
		totalN = new JLabel("N: 0");
		totalP = new JLabel("P: 0");
		totalDol = new JLabel("$0.00");
		inputQ = new JTextArea("0");
		inputD = new JTextArea("0");
		inputN = new JTextArea("0");
		inputP = new JTextArea("0");
		inputDol = new JTextArea("0.00");
		addChgBtn = new JButton("Add Chg");
		addChgBtn.addActionListener(bListener);
		addDolBtn = new JButton("Add Dol");
		addDolBtn.addActionListener(bListener);
		subChgBtn = new JButton("Sub Chg");
		subChgBtn.addActionListener(bListener);
		subDolBtn = new JButton("Sub Dol");
		subDolBtn.addActionListener(bListener);
		mutateBtn = new JButton("Mutate");
		mutateBtn.addActionListener(bListener);
		setLayout(new GridLayout(3, 5));
		add(totalQ);
		add(totalD);
		add(totalN);
		add(totalP);
		add(totalDol);
		add(inputQ);
		add(inputD);
		add(inputN);
		add(inputP);
		add(inputDol);
		add(addChgBtn);
		add(addDolBtn);
		add(subChgBtn);
		add(subDolBtn);
		add(mutateBtn);
	}

	/******************************************************************
    ButtonListener used to determine which buttons were selected
	 *****************************************************************/
	private class ButtonListener implements ActionListener {

		/**************************************************************
	    Determines which action to go through with, dependent on which
	    button is selected
	    @param e the action event
		 *************************************************************/
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addChgBtn) {
				int q;
				int d;
				int n;
				int p;
				
				//gathers input information
				q = Integer.parseInt(inputQ.getText());
				d = Integer.parseInt(inputD.getText());
				n = Integer.parseInt(inputN.getText());
				p = Integer.parseInt(inputP.getText());
				jar.add(q, d, n, p);
				
				//updates text for totals
				totalQ.setText("Q: " + jar.getQuarters());
				totalD.setText("D: " + jar.getDimes());
				totalN.setText("N: " + jar.getNickels());
				totalP.setText("P: " + jar.getPennies());
				totalDol.setText("$" + jar.dollarAmount());
			}
			if (e.getSource() == addDolBtn) {
				
				//gathers input information
				ChangeJar temp = new ChangeJar(inputDol.getText());
				jar.add(temp);
				
				//updates text for totals
				totalQ.setText("Q: " + jar.getQuarters());
				totalD.setText("D: " + jar.getDimes());
				totalN.setText("N: " + jar.getNickels());
				totalP.setText("P: " + jar.getPennies());
				totalDol.setText("$" + jar.dollarAmount());
			}
			if (e.getSource() == subChgBtn) {
				int q;
				int d;
				int n;
				int p;
				
				//gathers input information
				q = Integer.parseInt(inputQ.getText());
				d = Integer.parseInt(inputD.getText());
				n = Integer.parseInt(inputN.getText());
				p = Integer.parseInt(inputP.getText());
				jar.subtract(q, d, n, p);
				
				//updates text for totals
				totalQ.setText("Q: " + jar.getQuarters());
				totalD.setText("D: " + jar.getDimes());
				totalN.setText("N: " + jar.getNickels());
				totalP.setText("P: " + jar.getPennies());
				totalDol.setText("$" + jar.dollarAmount());
			}
			if (e.getSource() == subDolBtn) {
				
				//gathers information
				ChangeJar temp = new ChangeJar(inputDol.getText());
				jar.subtract(temp);
				
				//updates text for totals
				totalQ.setText("Q: " + jar.getQuarters());
				totalD.setText("D: " + jar.getDimes());
				totalN.setText("N: " + jar.getNickels());
				totalP.setText("P: " + jar.getPennies());
				totalDol.setText("$" + jar.dollarAmount());
			}
			if (e.getSource() == mutateBtn) {
				if (ChangeJar.getMutate())
					ChangeJar.mutate(false);
				else
					ChangeJar.mutate(true);
			}
		}
	}
}

