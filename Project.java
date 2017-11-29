// Timothy Smith
// CSCI 1152-102
// Project.java
// End-of-Semester Project, Text File Processing
// Tsmith145@cnm.edu
// 14 November 2017

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;
import java.nio.*;
import java.util.*;

public class Project
{
	
	public static void main(String[] args)
	{
		
		//method calls for total word count
		String totalArray[] = new String[5000];
		totalArray = totalWord(totalArray);
		int wordCount; 
		wordCount = counter(totalArray);
		
		//method calls for unique word count
		String uniqueArray[] = new String[wordCount];
		uniqueArray = filterDuplicates(wordCount);
		int uniqueCount;
		uniqueCount = counter(uniqueArray);
		
		
		
		//print 
		System.out.printf("%nThe total number of words in the file is: " +wordCount);
		System.out.printf("%nThe total number of different words in the file is: "+uniqueCount);
		System.out.printf("%nWords of the input file in ascending order without duplication%n" + printUniques(uniqueArray));
		System.out.println();

		
		String terminateString = ("EINPUT");
		String searchString = new String();
		//processing input
		do
		{
			Scanner input = new Scanner(System.in);
			System.out.println();
			System.out.print("Please enter a search pattern: ");
			searchString = input.next();
			if (searchString.equals(terminateString))
			{
				System.out.println("Bye!");
				System.exit(0);
			}
			
			//TODO//
			
			//call to method to sort input file by line, store line-sorted array
			//send line-sorted array to method to search for word(s), return index+1
			//print line#
			int lineNum = 1;
			System.out.printf("Line number " + lineNum);
			//send line-sorted array to method to iterate through array, store results in new array, return array to print
			//print array
			System.out.printf("%n" + uniqueArray[lineNum]);
			//get indexof for word, print with x spaces and ^
			
			
			
		} while (!searchString.equals(terminateString));
	} //end main

	
	//reusable method to count array elements with data
	public static int counter(String[] array)
	{
		int counter = 0; 
		for (int j = 0; j < array.length; j++) //loop to count number of arrays that contain data
			if (array[j] != null)
				counter++;
		return counter;
		
	}
	
	
	
	// read text file, delimit, place values into array
	public static String[] totalWord(String[] array)
	{
		Scanner scan = null;
		try 
		{
			scan = new Scanner(new File("example.txt")).useDelimiter("[^a-zA-Z0-9']+");;
		} catch (FileNotFoundException e) 
		{
			System.out.println(e);
			System.exit(1);
		}
		
		int i = 0;
		while (scan.hasNext())  //loop to place each word into array, then iterate to next index
		{
			array[i] = scan.next(); 
			i++;
		}
		scan.close();
		return array; //return the value
	}
	
	
	
	//method to filter unique words from array
	public static String[] filterDuplicates(int wordCount)
	{
		Scanner scan = null;
		try 
		{
			scan = new Scanner(new File("example.txt")).useDelimiter("[^a-zA-Z0-9']+");;
		} catch (FileNotFoundException e) 
		{
			System.out.println(e);
			System.exit(1);
		} 
		
		
		//instead of passing a 5000 element array, i figure it's easier to create a new one with size equal to total number of words
		String[] filteredArray = new String[wordCount];
		int i = 0;
		while (scan.hasNext())  
		{
			filteredArray[i] = scan.next(); 
			i++;
		}
		scan.close();
		
		//loop to iterate through array and check for duplicates.
		for (int k = 0; k < (wordCount - 1); k++)    
		{
			for (int m = k + 1; m < wordCount; m++)
			{
				if (filteredArray[k] != null && filteredArray[k].equals(filteredArray[m]))
				filteredArray[m] = null;
			}
		}			
		return filteredArray;	//return the filtered array
	}
	
	
	
	
	//method for printing ascending unique words
	public static String printUniques(String[] uniqueArray)
	{
		
		Arrays.sort(uniqueArray, Comparator.nullsLast(Comparator.naturalOrder()));
		
		String uniqueString = new String();
		uniqueString = " ";
		for(int i = 0; i < uniqueArray.length; i++)
		{
			if (uniqueArray[i] != null) 
			{
				uniqueString = (uniqueString + uniqueArray[i] + ", ");
			} else continue;
		}
		return uniqueString;
	}
	
	
	
/*
	//method for finding line
	public static int lineCheck(String inputString, String searchString)
	{
		;
		return test;
	} 
	
*/	
	//method for returning the contents of the index
	//public static String lineArrayCheck(
		
}
