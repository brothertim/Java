// Timothy Smith
// CSCI 1152-102
// Lab04.java
// Number Systems Conversion
// Tsmith145@cnm.edu
// 7 November 2017
import java.util.Arrays;


public class Lab04
{
	public static void main(String[] args)
	{
		String d_s = new String(); //stores command line argument
		String b_s = new String(); //stores binary conversion result
		
		b_s = decimalToBinary(decimalString); //decimalString from command argument
		d_s = binaryToDecimal(binaryString); //binary string from command argument
		
		System.out.printf("%nThe conversion of positive decimal number %d to unsigned binary is %d", d_s, decimalString);
		System.out.printf("%nThe conversion of binary number %d to positive decimal is %d", b_s, binaryString);
	}
	
//this is what i'm working on currently. trying to make it work with arrays	
	public static String decimalToBinary(String decimal)
	{
		int b[] = new int[10];
		int i = 0;

		while (d_s != 0)
		{
			i++;
			b[i] = d_s % 2;
			d_s = d_s / 2;
			String decimalString = "" + b[i];
		}		
		
		return decimalString;
	}
}
	/*public static String binaryToDecimal(String binary)
	{
		
		
		
		
		
	} */