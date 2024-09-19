import java.util.Arrays;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
		ReallyLongInt2 x = new ReallyLongInt2("123456");
		ReallyLongInt2 y = new ReallyLongInt2("987");
		ReallyLongInt2 z = new ReallyLongInt2("0");
		z = x.multiply(y);
		System.out.println(x + " * " + y + " = " + z.toString());
		// ReallyLongInt2 R1 = new ReallyLongInt2("0");
		// ReallyLongInt2 R2 = new ReallyLongInt2("0");
		// ReallyLongInt2 R3 = new ReallyLongInt2("0");

        // // Testing multiply
		// R1 = new ReallyLongInt2("12345");
		// R2 = new ReallyLongInt2("100");
		// R3 = R1.multiply(R2);
		// System.out.println(R1 + " * " + R2 + " = " + R3);
		
		// R1 = new ReallyLongInt2("123456");
		// R2 = new ReallyLongInt2("987");
		// R3 = R1.multiply(R2);
		// System.out.println(R1 + " * " + R2 + " = " + R3);
		
		// R3 = R2.multiply(R1);
		// System.out.println(R2 + " * " + R1 + " = " + R3);
		
		// R1 = new ReallyLongInt2("999999999");
		// R2 = new ReallyLongInt2("9");
		// R3 = R1.multiply(R2);
		// System.out.println(R1 + " * " + R2 + " = " + R3);
		
		// R1 = new ReallyLongInt2("999999999");
		// R2 = new ReallyLongInt2("0");
		// R3 = R1.multiply(R2);
		// System.out.println(R1 + " * " + R2 + " = " + R3);
		
		// R3 = R2.multiply(R1);
		// System.out.println(R2 + " * " + R1 + " = " + R3);	
		
		// R1 = new ReallyLongInt2("123456787654321");
		// R2 = new ReallyLongInt2("987654323456789");
		// R3 = R1.multiply(R2);
		// System.out.println(R1 + " * " + R2 + " = " + R3);
		
		// R1 = new ReallyLongInt2("55555555555555555555555555555555555");
		// R2 = new ReallyLongInt2("2000");
		// R3 = R1.multiply(R2);
		// System.out.println(R1 + " * " + R2 + " = " + R3);	
		
		// R3 = R2.multiply(R1);
		// System.out.println(R2 + " * " + R1 + " = " + R3);	
    }
}
