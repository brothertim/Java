// Timothy Smith
// CSCI 1152-102
// Project.java
// End-of-Semester Project, Text File Processing
// Tsmith145@cnm.edu
// 14 November 2017




//TO DO: clean out unusedpackages
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
		String totalArray[] = new String[5000];  //wanted to use a static array because it's harder to manipulate and I wanted the practice, I use ArrayLists later on.
		totalArray = totalWord(totalArray);  //method call to create array
		int wordCount; 
		wordCount = counter(totalArray);  //calling method to count array elements
		
		//method calls for unique word count
		String uniqueArray[] = new String[wordCount];  //make new array of a minimal size
		uniqueArray = filterDuplicates(wordCount);  //method call to filter out duplicates
		int uniqueCount;
		uniqueCount = counter(uniqueArray);  //calling counter method again
		
		
		
		//print counts
		System.out.printf("%nThe total number of words in the file is: " +wordCount);
		System.out.printf("%nThe total number of different words in the file is: "+uniqueCount);
		System.out.printf("%nWords of the input file in ascending order without duplication%n" + printUniques(uniqueArray));  //call to sort uniqueArray and print results
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
			if (searchString.equals(terminateString))  // if searchString matches terminate string, do this
			{
				System.out.println("Bye!");
				System.exit(0);
			}
			
			
			
			
			//TODO//
			List lineList = new ArrayList();
			lineList = createLineList();  //create a list with each element being an entire line from input document
			if (Arrays.asList(lineList).contains(searchString))  //if loop to see if the list contains the searchString
			{
				//if a match is found, print the line number below, 
				//figure out way to return more values if more than one match is found
			int lineNum = 1;  //placeholder to check formatting, this is where i'd print the index where the match was found
			System.out.printf("%n" + uniqueArray[lineNum]);  //placeholder to print the entire line in which the match was found
			
			//int space = something.get(indexOf[searchString])..... (placeholder)
			//search returned element for index of matched word, use index as spacing for arrow below
			String arrow = new String;
			arrow = ("%d");
			arrow += space; //append the space after the %d
			arrow += "s";  //append s to end of string, to end up with %ds, where d=index where arrow will be, i.e. %15s
			System.out.printf("%s", arrow )  //create an arrow at the index matching the first letter of the matched results.
			
			
			
			
		} while (!searchString.equals(terminateString));  //loop until terminate value is matched to input
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
			scan = new Scanner(new File("example.txt")).useDelimiter("[^a-zA-Z0-9']+");  //delimiting non-alphanumeric, but exempted apostrophes also in case of contractions
		} catch (FileNotFoundException e)   //if file not found, exit
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
		return array; //return the array
	}
	
	
	
	//method to filter unique words from array
	public static String[] filterDuplicates(int wordCount)
	{
		Scanner scan = null;  //rescan document//  TO DO:  place another method call here and at line 102-110 to do this and just reuse that instead
		try 
		{
			scan = new Scanner(new File("example.txt")).useDelimiter("[^a-zA-Z0-9']+");
		} catch (FileNotFoundException e) 
		{
			System.out.println(e);
			System.exit(1);
		} 
		
		
		//instead of passing a 5000 element array, i figure it's better to create a new one with size equal to total number of words
		//could've easily used ArrayList, but didn't want to yet.
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
				filteredArray[m] = null;  //TO DO: maybe change this to a non-null value, since null values cause errors when using .compareTo()
			}
		}			
		return filteredArray;	//return the filtered array
	}
	
	
	
	
	//method for printing ascending unique words
	public static String printUniques(String[] uniqueArray)
	{
		
		//Arrays.sort(uniqueArray, Comparator.nullsLast(Comparator.naturalOrder()));  REMOVED: DONT USE THIS (was just a placeholder)
		
		List<String> stringList = new ArrayList<String>(Arrays.asList(uniqueArray)); //convert Array to ArrayList for sorting
		for (int i = 0; i < stringList.size(); i ++)
		{
			if (stringList.get(i) == null)
			{
				stringList.remove(i);
				i --;
			}
		}
		
		//convert List to string using stringbuilder
		//previously using loop to append, but decided stringbuilder is better for performance
		StringBuilder sb = new StringBuilder();
		for (String s : stringList)
		{
			sb.append(s);
			sb.append("\t");
		}
		String uniqueString = new String(sb.toString());
		return uniqueString;
	}
	
	
	//method to convert input file into a list of whole lines from doc, using an ArrayList because I'd rather not use a fixed size list again for an unknown array size
	public static String[] createLineList()
	{
		Scanner scan = null;  //TODO:  same as line 127,  this could clean up the code more and put one single place to change "example.txt" to whatever the file will be called
		try 
		{
			scan = new Scanner(new File("example.txt")).useDelimiter("[^a-zA-Z0-9']+");
		} catch (FileNotFoundException e) 
		{
			System.out.println(e);
			System.exit(1);
		} 		
		
		List<String> list = new ArrayList<String>();  //create new list in which to store inputs
		String temp = new String; 
		while (scan.hasNextLine())  //while more data to be read on next line
		{ 
			temp = scan.nextLine();  //store next line's contents to temp string
			list.add(temp);  //add temp string to list
		}
		scan.close()
		return list;
		//use linear search CH19 powerpoint to find array element matching search string
	}

	
	
}