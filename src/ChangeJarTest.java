/**********************************************************************
JUnit Testing for the ChangeJar object.

@author Conner Toney
@version Winter 2015
 *********************************************************************/

import static org.junit.Assert.*;
import org.junit.Test;

public class ChangeJarTest {
	
	//tests mutate() by changing s1 after mut = false
	@Test
	public void testMutate1() {
		ChangeJar s1 = new ChangeJar();
		s1.add(1, 2, 3, 4);
		ChangeJar s2 = new ChangeJar();
		s2.add(1, 2, 3, 4);
		ChangeJar.mutate(false);
		s1.add(10, 10, 10, 10);
		ChangeJar.mutate(true);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests default constructor
	@Test
	public void testBlankConstructor1() {
		ChangeJar s = new ChangeJar();
		assertEquals(s, new ChangeJar("0.00"));
	}
	
	//tests illegal argument in constructor
	@Test(expected = IllegalArgumentException.class)
	public void testNegConstructor1() {
		@SuppressWarnings("unused")
		ChangeJar s = new ChangeJar(-1);
	}
	
	//tests bad format in constructor
	@Test(expected = NumberFormatException.class)
	public void testBadFormatConstructor1() {
		@SuppressWarnings("unused")
		ChangeJar s = new ChangeJar("5.k");
	}
	
	//tests bad format in constructor
	@Test(expected = NumberFormatException.class)
	public void testBadFormatConstructor2() {
		@SuppressWarnings("unused")
		ChangeJar s = new ChangeJar("$$");
	}
	
	//tests #.# double value in constructor
	@Test
	public void testDoubleConstructor1() {
		ChangeJar s = new ChangeJar(5.4);
		assertEquals(s, new ChangeJar("5.40"));
	}
	
	//tests #.## double value in constructor
	@Test
	public void testDoubleConstructor2() {
		ChangeJar s = new ChangeJar(5.40);
		assertEquals(s, new ChangeJar("5.40"));
	}
	
	//tests .# double value in constructor
	@Test
	public void testDoubleConstructor3() {
		ChangeJar s = new ChangeJar(.5);
		assertEquals(s, new ChangeJar("0.50"));
	}
	
	//tests 0.# double value in constructor
	@Test
	public void testDoubleConstructor4() {
		ChangeJar s = new ChangeJar(0.5);
		assertEquals(s, new ChangeJar("0.50"));
	}
	
	//tests 0.#0 double value in constructor
	@Test
	public void testDoubleConstructor5() {
		ChangeJar s = new ChangeJar(0.50);
		assertEquals(s, new ChangeJar("0.50"));
	}
	
	//tests 0 double value in constructor
	@Test
	public void testDoubleConstructor6() {
		ChangeJar s = new ChangeJar(0);
		assertEquals(s, new ChangeJar("0.00"));
	}
	
	//tests if #.## equals #.##
	@Test
	public void testEquals1() {
		ChangeJar s1 = new ChangeJar(5.50);
		ChangeJar s2 = new ChangeJar(5.50);
		assertTrue(s1.equals(s2));
	}
	
	//tests if equals method returns false for unequal jars
	@Test
	public void testEquals2() {
		ChangeJar s1 = new ChangeJar(5.50);
		ChangeJar s2 = new ChangeJar(4.50);
		assertFalse(s1.equals(s2));
	}
	
	//tests if #.00 equals #
	@Test
	public void testEquals3() {
		ChangeJar s1 = new ChangeJar(5.00);
		ChangeJar s2 = new ChangeJar(5);
		assertTrue(s1.equals(s2));
	}
	
	//tests static method equals()
	@Test
	public void testEquals4() {
		ChangeJar s1 = new ChangeJar(5.50);
		ChangeJar s2 = new ChangeJar(5.50);
		assertTrue(ChangeJar.equals(s1, s2));
	}
	
	//makes sure static equals() returns false when actually false
	@Test
	public void testEquals5() {
		ChangeJar s1 = new ChangeJar(5.50);
		ChangeJar s2 = new ChangeJar(1.00);
		assertFalse(ChangeJar.equals(s1, s2));
	}
	
	//tests if compareTo s1 > s2 returns 1
	@Test
	public void testCompareTo1() {
		ChangeJar s1 = new ChangeJar(1.00);
		ChangeJar s2 = new ChangeJar(0);
		assertEquals(s1.compareTo(s2), 1);
	}
	
	//tests if compareTo s1 < s2 returns -1
	@Test 
	public void testCompareTo2() {
		ChangeJar s1 = new ChangeJar();
		ChangeJar s2 = new ChangeJar(1.00);
		assertEquals(s1.compareTo(s2), -1);
	}
	
	//tests if compareTo s1 = s2 returns 0
	@Test
	public void testCompareTo3() {
		ChangeJar s1 = new ChangeJar();
		ChangeJar s2 = new ChangeJar();
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests if compareTo s1 = s2 returns 0
	@Test
	public void testCompareTo4() {
		ChangeJar s1 = new ChangeJar(1.57);
		ChangeJar s2 = new ChangeJar(1.57);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests if subtract() works
	@Test
	public void testSubtract1() {
		ChangeJar s1 = new ChangeJar(0.67);
		ChangeJar s2 = new ChangeJar();
		s1.subtract(2, 1, 1, 2);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests if subtract() works for pennies
	@Test
	public void testSubtract2() {
		ChangeJar s1 = new ChangeJar();
		s1.add(0, 0, 0, 10);
		ChangeJar s2 = new ChangeJar();
		s2.add(0, 0, 0, 3);
		s1.subtract(0, 0, 0, 7);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests if subtract() works for pennies when subtracted below 0
	@Test
	public void testSubtract3() {
		ChangeJar s1 = new ChangeJar();
		s1.add(0, 0, 0, 10);
		ChangeJar s2 = new ChangeJar();
		s1.subtract(0, 0, 0, 11);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests if subtract() works for nickels
	@Test
	public void testSubtract4() {
		ChangeJar s1 = new ChangeJar();
		s1.add(0, 0, 10, 0);
		ChangeJar s2 = new ChangeJar();
		s2.add(0, 0, 3, 0);
		s1.subtract(0, 0, 7, 0);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests if subtract() works for nickels when subtracted below 0
	@Test
	public void testSubtract5() {
		ChangeJar s1 = new ChangeJar();
		s1.add(0, 0, 10, 0);
		ChangeJar s2 = new ChangeJar();
		s1.subtract(0, 0, 11, 0);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests if subtract() works for dimes
	@Test
	public void testSubtract6() {
		ChangeJar s1 = new ChangeJar();
		s1.add(0, 10, 0, 0);
		ChangeJar s2 = new ChangeJar();
		s2.add(0, 3, 0, 0);
		s1.subtract(0, 7, 0, 0);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests if subtract() works for dimes when subtracted below 0
	@Test
	public void testSubtract7() {
		ChangeJar s1 = new ChangeJar();
		s1.add(0, 10, 0, 0);
		ChangeJar s2 = new ChangeJar();
		s1.subtract(0, 11, 0, 0);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests if subtract() works for quarters
	@Test
	public void testSubtract8() {
		ChangeJar s1 = new ChangeJar();
		s1.add(10, 0, 0, 0);
		ChangeJar s2 = new ChangeJar();
		s2.add(3, 0, 0, 0);
		s1.subtract(7, 0, 0, 0);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests if subtract() works for quarters when subtracted below 0
	@Test
	public void testSubtract9() {
		ChangeJar s1 = new ChangeJar();
		s1.add(10, 0, 0, 0);
		ChangeJar s2 = new ChangeJar();
		s1.subtract(11, 0, 0, 0);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests subtract() when subtracting jars
	@Test
	public void testSubtract10() {
		ChangeJar s1 = new ChangeJar();
		s1.add(10, 10, 10, 10);
		ChangeJar s2 = new ChangeJar();
		s2.add(1, 2, 3, 4);
		ChangeJar s3 = new ChangeJar();
		s3.add(9, 8, 7, 6);
		s1.subtract(s2);
		assertEquals(s1.compareTo(s3), 0);
	}
	
	//tests subtract() when subtracting jars
	@Test
	public void testSubtract11() {
		ChangeJar s1 = new ChangeJar();
		s1.add(10, 10, 10, 10);
		ChangeJar s2 = new ChangeJar();
		s2.add(11, 11, 11, 11);
		ChangeJar s3 = new ChangeJar();
		s1.subtract(s2);
		assertEquals(s1.compareTo(s3), 0);
	}
	
	//tests dec() by comparing two jars
	@Test
	public void testDec1() {
		ChangeJar s1 = new ChangeJar();
		s1.add(0, 0, 0, 10);
		ChangeJar s2 = new ChangeJar();
		s2.add(0, 0, 0, 7);
		s1.dec();
		s1.dec();
		s1.dec();
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests how dec() reacts at 0
	@Test
	public void testDec2() {
		ChangeJar s1 = new ChangeJar(0.02);
		ChangeJar s2 = new ChangeJar();
		s1.dec();
		s1.dec();
		s1.dec();
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests how dec() reacts with nickels and no pennies
	@Test
	public void testDec3() {
		ChangeJar s1 = new ChangeJar(0.05);
		ChangeJar s2 = new ChangeJar(0.05);
		s1.dec();
		s1.dec();
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests how dec() reacts with dimes and no pennies
	@Test
	public void testDec4() {
		ChangeJar s1 = new ChangeJar(0.10);
		ChangeJar s2 = new ChangeJar(0.10);
		s1.dec();
		s1.dec();
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests how dec() reacts with quarters and no pennies
	@Test
	public void testDec5() {
		ChangeJar s1 = new ChangeJar(0.25);
		ChangeJar s2 = new ChangeJar(0.25);
		s1.dec();
		s1.dec();
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests add() by comparing to a different jar
	@Test
	public void testAdd1() {
		ChangeJar s1 = new ChangeJar();
		s1.add(1, 1, 1, 1);
		ChangeJar s2 = new ChangeJar(0.41);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests inc() by comparing to another jar
	@Test
	public void testInc1() {
		ChangeJar s1 = new ChangeJar();
		s1.inc();
		s1.inc();
		ChangeJar s2 = new ChangeJar(0.02);
		assertEquals(s1.compareTo(s2), 0);
	}
	
	//tests singular toString()
	@Test
	public void testToString1() {
		ChangeJar s1 = new ChangeJar(0.41);
		String str = "1 quarter, 1 dime, 1 nickel, 1 penny";
		assertEquals(s1.toString(), str);
	}
	
	//tests plural toString()
	@Test
	public void testToString2() {
		ChangeJar s1 = new ChangeJar();
		s1.add(2, 2, 2, 2);
		String str = "2 quarters, 2 dimes, 2 nickels, 2 pennies";
		assertEquals(s1.toString(), str);
	}
	
	//tests save() and load() methods by comparing to other jars
	@Test
	public void testSaveLoad() {
		ChangeJar s1 = new ChangeJar();
		s1.add(1, 2, 3, 4);
		ChangeJar s2 = new ChangeJar();
		s2.add(1, 2, 3, 4);
		s1.save("testing-save-load.txt");
		s1.add(5, 5, 5, 5);
		s1.load("testing-save-load.txt");
		assertEquals(s1.compareTo(s2), 0);
	}
}
