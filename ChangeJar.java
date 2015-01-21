/**********************************************************************
Representation of a change jar.  Keeps track of the money contained
in the jar after deposits/withdrawal.

@author Conner Toney
@version GVSU Winter 2015
 *********************************************************************/

package package1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ChangeJar {

	/** number of quarters */
	private int quarters;

	/** number of dimes */
	private int dimes;

	/** number of nickels */
	private int nickels;

	/** number of pennies */
	private int pennies;

	/** number of dollars */
	private double dollars;

	/** determines if jar is allowed to change/mutate */
	public static boolean mut = true;

	/******************************************************************
    Constructor creates an empty change jar
	 *****************************************************************/
	public ChangeJar() {
		if (mut) {
			quarters = 0;
			dimes = 0;
			nickels = 0;
			pennies = 0;
		}
	}

	/******************************************************************
    Constructor creates a change jar set to the given amount
    @param amount input dollar amount
	 *****************************************************************/
	public ChangeJar(double amount) {
		if (mut) {
			if (amount < 0)
				throw new IllegalArgumentException();
			dollars = amount;
			convertFromDollars(dollars);
		}
	}

	/******************************************************************
    Constructor creates a change jar to the amount of the 'other'
    change jar
    @param other input ChangeJar object
	 *****************************************************************/
	public ChangeJar(ChangeJar other) {
		if (mut) {
			quarters = other.quarters;
			dimes = other.dimes;
			nickels = other.nickels;
			pennies = other.pennies;
		}
	}

	/******************************************************************
    Constructor creates a change jar based on the input String
    @param amount input String dollar amount
	 *****************************************************************/
	public ChangeJar(String amount) {
		if (mut) {
			dollars = Double.parseDouble(amount);
			if (dollars < 0)
				throw new IllegalArgumentException();
			convertFromDollars(dollars);
		}
	}

	/******************************************************************
    Determines if 'this' ChangeJar equals the 'other' Object
    @param other input Object compared to 'this' ChangeJar
    @return true if equal, false if not
	 *****************************************************************/
	public boolean equals(Object other) {
		ChangeJar o = (ChangeJar) other;
		if (quarters == o.quarters && dimes == o.dimes 
				&& nickels == o.nickels
				&& pennies == o.pennies)
			return true;
		else
			return false;
	}

	/******************************************************************
    Determines if 'this' ChangeJar equals the 'other' ChangeJar
    @param other input ChangeJar compared to 'this' ChangeJar
    @return true if equal, false if not
	 *****************************************************************/
	public boolean equals(ChangeJar other) {
		if (quarters == other.quarters && dimes == other.dimes
				&& nickels == other.nickels 
				&& pennies == other.pennies)
			return true;
		else
			return false;
	}

	/******************************************************************
    Determines if one ChangeJar equals another ChangeJar
    @param jar1 first input ChangeJar used for comparison
    @param jar2 second input ChangeJar used for comparison
    @return true if equal, false if not
	 *****************************************************************/
	public static boolean equals(ChangeJar jar1, ChangeJar jar2) {
		if (jar1.quarters == jar2.quarters && jar1.dimes == jar2.dimes
				&& jar1.nickels == jar2.nickels 
				&& jar1.pennies == jar2.pennies)
			return true;
		else
			return false;
	}

	/******************************************************************
    Compares 'this' ChangeJar to 'other' ChangeJar
    @param other input ChangeJar used for comparison
    @return 1 if 'this' > 'other', -1 if 'this' < 'other', or 0 if
    'this' = 'other'
	 *****************************************************************/
	public int compareTo(ChangeJar other) {
		int total1;
		int total2;
		
		//totals 'this' jar's change
		total1 = ((quarters * 25) + (dimes * 10) 
				+ (nickels * 5) + pennies);
		
		//totals 'other' jar's change
		total2 = ((other.quarters * 25) + (other.dimes * 10)
				+ (other.nickels * 5) + other.pennies);
		
		if (total1 > total2)
			return 1;
		if (total1 < total2)
			return -1;
		else
			return 0;
	}

	/******************************************************************
    Compares two ChangeJar objects to each other
    @param jar1 first input ChangeJar used for comparison
    @param jar2 second input ChangeJar used for comparison
    @return 1 if jar1 > jar2, -1 if jar1 < jar2, or 0 if jar1 = jar2
	 *****************************************************************/
	public static int compareTo(ChangeJar jar1, ChangeJar jar2) {
		int total1;
		int total2;
		
		//totals jar1 change jar
		total1 = ((jar1.quarters * 25) + (jar1.dimes * 10) 
				+ (jar1.nickels * 5) + (jar1.pennies));
		
		//total jar2 change jar
		total2 = ((jar2.quarters * 25) + (jar2.dimes * 10) 
				+ (jar2.nickels * 5) + (jar2.pennies));
		
		if (total1 > total2)
			return 1;
		if (total1 < total2)
			return -1;
		else
			return 0;
	}

	/******************************************************************
    Subtracts the given change from this ChangeJar
    @param q input quarters for subtraction
    @param d input dimes for subtraction
    @param n input nickels for subtraction
    @param p input pennies for subtraction
	 *****************************************************************/
	public void subtract(int q, int d, int n, int p) {
		if (mut) {
			if (q < quarters)
				quarters -= q;
			else
				quarters = 0;
			if (d < dimes)
				dimes -= d;
			else
				dimes = 0;
			if (n < nickels)
				nickels -= n;
			else
				nickels = 0;
			if (p < pennies)
				pennies -= p;
			else
				pennies = 0;
		}
	}

	/******************************************************************
    Subtracts the amount of change of the 'other' ChangeJar from 'this'
    ChangeJar object
    @param other input ChangeJar object used for subtraction
	 *****************************************************************/
	public void subtract(ChangeJar other) {
		if (mut) {
			if (other.getQuarters() < quarters)
				quarters -= other.getQuarters();
			else
				quarters = 0;
			if (other.getDimes() < dimes)
				dimes -= other.getDimes();
			else
				dimes = 0;
			if (other.getNickels() < nickels)
				nickels -= other.getNickels();
			else
				nickels = 0;
			if (other.getPennies() < pennies)
				pennies -= other.getPennies();
			else
				pennies = 0;
		}
	}

	/******************************************************************
	Decreases the number of pennies by 1
	 *****************************************************************/
	public void dec() {
		if (mut) {
			if (pennies > 0)
				pennies--;
		}
	}

	/******************************************************************
	Adds the given amount of change to this ChangeJar
    @param q input quarters to be added
    @param d input dimes to be added
    @param n input nickels to be added
    @param p input pennies to be added
	 *****************************************************************/
	public void add(int q, int d, int n, int p) {
		if (mut) {
			quarters += q;
			dimes += d;
			nickels += n;
			pennies += p;
		}
	}

	/******************************************************************
    Adds the amount of the 'other' ChangeJar to 'this' ChangeJar
    @param other input ChangeJar object used for addition
	 *****************************************************************/
	public void add(ChangeJar other) {
		if (mut) {
			quarters += other.getQuarters();
			dimes += other.getDimes();
			nickels += other.getNickels();
			pennies += other.getPennies();
		}
	}

	/******************************************************************
    Increases the amount of pennies by 1
	 *****************************************************************/
	public void inc() {
		if (mut) {
			pennies++;
		}
	}

	/******************************************************************
    Displays a String representation of the ChangeJar object
    @return String representation of ChangeJar
	 *****************************************************************/
	public String toString() {
		String changeStr;
		if (quarters == 1)
			changeStr = (quarters + " quarter, ");
		else
			changeStr = (quarters + " quarters, ");
		if (dimes == 1)
			changeStr += (dimes + " dime, ");
		else
			changeStr += (dimes + " dimes, ");
		if (nickels == 1)
			changeStr += (nickels + " nickel, ");
		else
			changeStr += (nickels + " nickels, ");
		if (pennies == 1)
			changeStr += (pennies + " penny");
		else
			changeStr += (pennies + " pennies");
		return changeStr;
	}

	/******************************************************************
    Saves the ChangeJar object to a specified file
    @param fileName file name where ChangeJar will be saved to
	 *****************************************************************/
	public void save(String fileName) {
		PrintWriter out = null;

		try {
			out = new PrintWriter(new BufferedWriter(
					new FileWriter(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//prints to file in format q/d/n/p
		out.println(quarters + "/" + dimes + 
				"/" + nickels + "/" + pennies);
		out.close();
	}

	/******************************************************************
    Loads a ChangeJar object from a specified file
    @param fileName name of file to be loaded
	 *****************************************************************/
	public void load(String fileName) {
		try {
			FileReader inFile = new FileReader(fileName);
			BufferedReader bufReader = new BufferedReader(inFile);
			String inputTime;

			//reads line until null, then breaks down one whole string
			//into the correct coinage
			while ((inputTime = bufReader.readLine()) != null) {
				String[] tokens = inputTime.split("/");
				quarters = Integer.parseInt(tokens[0].trim());
				dimes = Integer.parseInt(tokens[1].trim());
				nickels = Integer.parseInt(tokens[2].trim());
				pennies = Integer.parseInt(tokens[3].trim());
			}
			bufReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/******************************************************************
    Allows or disallows ChangeJar to change
    @param on true = object can change, false = object can't change
	 *****************************************************************/
	public static void mutate(Boolean on) {
		ChangeJar.mut = on;
	}

	/******************************************************************
    Returns the status of mutate
    @return status of mutate
	 *****************************************************************/
	public static Boolean getMutate() {
		return ChangeJar.mut;
	}

	/******************************************************************
    Determines dollar representation of change jar
    @return dollar representation of change jar
	 *****************************************************************/
	public double dollarAmount() {
		int intTotal;
		double dollarAmt;
		double doubTotal;
		intTotal = (quarters * 25) + (dimes * 10) 
				+ (nickels * 5) + pennies;
		doubTotal = (double) intTotal;
		dollarAmt = doubTotal / 100;
		return dollarAmt;
	}

	/******************************************************************
    Converts to dollars dependent on values of individual coins
    @param q quarter amount
    @param d dime amount
    @param n nickel amount
    @param p penny amount
	 *****************************************************************/
	public void convertToDollars(int q, int d, int n, int p) {
		int intTotal;
		intTotal = (q * 25) + (d * 10) + (n * 5) + p;
		double doubTotal;
		doubTotal = (double) intTotal;
		dollars = doubTotal / 100;
	}

	/******************************************************************
    Converts from dollars to change
    @param pdollars dollar amount
	 *****************************************************************/
	public void convertFromDollars(double pdollars) {
		int amount = (int) (pdollars * 100);
		if (amount >= 25) {
			quarters = amount / 25;
			amount = amount - (quarters * 25);
		} else
			quarters = 0;
		if (amount >= 20) {
			dimes = 2;
			amount = amount - 20;
		} else if (amount >= 10) {
			dimes = 1;
			amount = amount - 10;
		} else
			dimes = 0;
		if (amount >= 5) {
			nickels = amount / 5;
			amount = amount - (nickels * 5);
		} else
			nickels = 0;
		if (amount > 0) {
			pennies = amount;
			amount = 0;
		} else
			pennies = 0;
	}

	/******************************************************************
    Gets quarter amount
    @return quarter amount
	 *****************************************************************/
	public int getQuarters() {
		return quarters;
	}

	/******************************************************************
    Sets quarter amount
    @param quarters input specified quarter amount
	 *****************************************************************/
	public void setQuarters(int quarters) {
		this.quarters = quarters;
	}

	/******************************************************************
    Gets dime amount
    @return dime amount
	 *****************************************************************/
	public int getDimes() {
		return dimes;
	}

	/******************************************************************
    Sets dime amount
    @param dimes input specified dime amount
	 *****************************************************************/
	public void setDimes(int dimes) {
		this.dimes = dimes;
	}
	
	/******************************************************************
    Gets nickel amount
    @return nickel amount
	 *****************************************************************/
	public int getNickels() {
		return nickels;
	}

	/******************************************************************
    Sets nickel amount
    @param nickels input specified nickel amount
	 *****************************************************************/
	public void setNickels(int nickels) {
		this.nickels = nickels;
	}

	/******************************************************************
    Gets penny amount
    @return penny amount
	 *****************************************************************/
	public int getPennies() {
		return pennies;
	}

	/******************************************************************
    Sets penny amount
    @param pennies input specified penny amount
	 *****************************************************************/
	public void setPennies(int pennies) {
		this.pennies = pennies;
	}

	/******************************************************************
    Gets dollar amount
    @return dollar amount
	 *****************************************************************/
	public double getDollars() {
		return dollars;
	}

	/******************************************************************
    Sets dollar amount
    @param dollars input specified dollar amount
	 *****************************************************************/
	public void setDollars(double dollars) {
		this.dollars = dollars;
	}

	/******************************************************************
    MAIN METHOD - USED FOR TESTING
	 *****************************************************************/
	public static void main(String[] args) {
		ChangeJar a = new ChangeJar();
		System.out.println(a);

		ChangeJar b = new ChangeJar(0.66);
		System.out.println(b);

		ChangeJar c = new ChangeJar("0.66");
		System.out.println(c);

		if (b.equals(c))
			System.out.println("b equals c");
		else
			System.out.println("error");

		if (a.compareTo(b) == -1)
			System.out.println("b is greater than a");
		else
			System.out.println("error");

		c.subtract(1, 3, 0, 1);
		System.out.println(c);

		ChangeJar d = new ChangeJar();
		d.add(1, 3, 0, 1);
		System.out.println(d);

		b.subtract(d);
		System.out.println(b);

		ChangeJar e = new ChangeJar();
		e.add(1, 2, 3, 3);
		e.dec();
		System.out.println(e);
		e.inc();
		System.out.println(e);
		e.dec();
		e.dec();
		e.dec();
		e.dec();
		System.out.println(e);

		ChangeJar f = new ChangeJar();
		f.add(e);
		System.out.println(f);

		ChangeJar g = new ChangeJar();
		g.add(1, 2, 3, 4);
		g.save("change-jar-text.txt");

		ChangeJar h = new ChangeJar();
		h.load("change-jar-text.txt");
		System.out.println(h);
	}
}
