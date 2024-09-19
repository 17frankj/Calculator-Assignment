// CS 0445 Summer 2023
// Test program for your ReallyLongInt2 class -- for full credit you CANNOT MODIFY
// this code in ANY WAY.

// This program should execute without error and produce output identical to
// the output shown on the Web site.  If your output does not match mine, think
// carefully about what your operations are doing and trace them to find the
// problem.

// If your output does not match mine, or if you must change this file to get
// your code to work, you will lose credit, but you can still get PARTIAL
// credit for your work, so be sure to turn something in no matter what!

// NOTE: This program is identical to RLITest.java, except that the
// ReallyLongInt2 type has been globally substituted for ReallyLongInt.  The
// output should be the same as before.

import java.util.*;

public class RLITest2
{
	public static void main (String [] args)
	{
		// Test of String constructor and toString() method
		ReallyLongInt2 R1 = new ReallyLongInt2("123456789");
		ReallyLongInt2 R2 = new ReallyLongInt2("987654321");
		ReallyLongInt2 leadZero1 = new ReallyLongInt2("0000000000");
		ReallyLongInt2 leadZero2 = new ReallyLongInt2("0000012300");
		System.out.println("R1 = " + R1.toString());
		System.out.println("R2 = " + R2.toString());
		System.out.println("leadZero1 = " + leadZero1.toString());
		System.out.println("leadZero2 = " + leadZero2.toString());
		System.out.println();
		
		// Testing long constructor
		ReallyLongInt2 L = new ReallyLongInt2(0);
		System.out.println("L = " + L.toString());
		L = new ReallyLongInt2(123456);
		System.out.println("L = " + L.toString());
		L = new ReallyLongInt2(10000000);
		System.out.println("L = " + L.toString());
		System.out.println();
		
		// Testing add method.
		ReallyLongInt2 R3 = R1.add(R2);
		System.out.println(R1 + " + " + R2 + " = " + R3);
		R1 = new ReallyLongInt2("1");
		R2 = new ReallyLongInt2("9999999999999999999999999999");
		R3 = R1.add(R2);
		ReallyLongInt2 R4 = R2.add(R1);
		System.out.println(R1 + " + " + R2 + " = " + R3);
		System.out.println(R2 + " + " + R1 + " = " + R4);
		System.out.println();
		
		// Testing subtract method
		R1 = new ReallyLongInt2("23456");
		R2 = new ReallyLongInt2("4567");
		R3 = R1.subtract(R2);
		System.out.println(R1 + " - " + R2 + " = " + R3);
		R1 = new ReallyLongInt2("1000000000000000000000000000000");
		R2 = new ReallyLongInt2("1");
		R3 = R1.subtract(R2);
		System.out.println(R1 + " - " + R2 + " = " + R3);
		R1 = new ReallyLongInt2("2111112");
		R2 = new ReallyLongInt2("1111111");
		R3 = R1.subtract(R2);
		System.out.println(R1 + " - " + R2 + " = " + R3);
		R1 = new ReallyLongInt2("12345678987654321");
		R2 = new ReallyLongInt2("12345678987654320");
		R3 = R1.subtract(R2);
		System.out.println(R1 + " - " + R2 + " = " + R3);
		R1 = new ReallyLongInt2("123456");
		R2 = new ReallyLongInt2("123456");
		R3 = R1.subtract(R2);
		System.out.println(R1 + " - " + R2 + " = " + R3);
		R1 = new ReallyLongInt2("123456");
		R2 = new ReallyLongInt2("123457");
		try
		{
			R3 = R1.subtract(R2);
		}
		catch (ArithmeticException e)
		{
			System.out.println(R2 + " > " + R1 + ": Cannot subtract");
			//System.out.println(e);
		}
		System.out.println();

		// Testing copy constructor
		ReallyLongInt2 R5 = new ReallyLongInt2(R4);
		System.out.println("Copy of " + R4.toString() + " = " + R5.toString());
		System.out.println();
		
		// Testing compareTo
		ReallyLongInt2 [] C = new ReallyLongInt2[5];
		C[0] = new ReallyLongInt2("844444444444444");
		C[1] = new ReallyLongInt2("744444444444444");
		C[2] = new ReallyLongInt2("844444445444444");
		C[3] = new ReallyLongInt2("9444");
		C[4] = new ReallyLongInt2("744444444444445");
		for (int i = 0; i < C.length; i++)
		{
			for (int j = 0; j < C.length; j++)
			{
				int ans = C[i].compareTo(C[j]);
				if (ans < 0)
					System.out.println(C[i] + " < " + C[j]);
				else if (ans > 0)
					System.out.println(C[i] + " > " + C[j]);
				else
					System.out.println(C[i] + " == " + C[j]);
			}
		}
		System.out.println();
		Arrays.sort(C);
		System.out.println("Here is the sorted array: ");
		for (ReallyLongInt2 R: C)
			System.out.println(R);
		System.out.println();

		// Testing equals
		R1 = new ReallyLongInt2("12345678987654321");
		R2 = new ReallyLongInt2("12345678987654321");
		R3 = new ReallyLongInt2("12345678907654321");
		if (R1.equals(R2))
			System.out.println(R1 + " equals " + R2);
		if (!R1.equals(R3))
			System.out.println(R1 + " does not equal " + R3);
		System.out.println();

		// Testing multiply
		R1 = new ReallyLongInt2("12345");
		R2 = new ReallyLongInt2("100");
		R3 = R1.multiply(R2);
		System.out.println(R1 + " * " + R2 + " = " + R3);
		
		R1 = new ReallyLongInt2("123456");
		R2 = new ReallyLongInt2("987");
		R3 = R1.multiply(R2);
		System.out.println(R1 + " * " + R2 + " = " + R3);
		
		R3 = R2.multiply(R1);
		System.out.println(R2 + " * " + R1 + " = " + R3);
		
		R1 = new ReallyLongInt2("999999999");
		R2 = new ReallyLongInt2("9");
		R3 = R1.multiply(R2);
		System.out.println(R1 + " * " + R2 + " = " + R3);
		
		R1 = new ReallyLongInt2("999999999");
		R2 = new ReallyLongInt2("0");
		R3 = R1.multiply(R2);
		System.out.println(R1 + " * " + R2 + " = " + R3);
		
		R3 = R2.multiply(R1);
		System.out.println(R2 + " * " + R1 + " = " + R3);	
		
		R1 = new ReallyLongInt2("123456787654321");
		R2 = new ReallyLongInt2("987654323456789");
		R3 = R1.multiply(R2);
		System.out.println(R1 + " * " + R2 + " = " + R3);
		
		R1 = new ReallyLongInt2("55555555555555555555555555555555555");
		R2 = new ReallyLongInt2("2000");
		R3 = R1.multiply(R2);
		System.out.println(R1 + " * " + R2 + " = " + R3);	
		
		R3 = R2.multiply(R1);
		System.out.println(R2 + " * " + R1 + " = " + R3);	
	}
}