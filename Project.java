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
import java.io.*;
import java.nio.*;
import java.util.*;

public class Project
{
	public static void main(String[] args)
	{
		//try&catch block to validate input file and catch exceptions, exit program if exceptions occur
		String inputString = "";
		String uniqueList = "";
		String ascendingWordList = "";
		uniqueList = new String();
		ascendingWordList = new String();
		int wordCount; 
		int uniqueCount;
		
		try 
		{
			inputString = new String(Files.readAllBytes(Paths.get(args[0])));
			//System.out.print(s1);
		}
		catch (IOException ioException)
		{
			System.out.println("IOException, please try again");
			System.exit(1);
		}
		
		
		
		//method calls
		wordCount = wordCounter(inputString);
		uniqueCount = uniqueWordCounter(inputString);
		ascendingWordList = ascendingList(inputString);
		
/*ask*/	//String ascendingList = String.join(", ", ascendingWordList); //convert a list to a string, comma separated
		// int uniqueWordCount = uniqueList.size(); //get size of hashset and assign to uniqueWordCount for later printing
		
		
		
		//print 
		System.out.printf("%nThe total number of words in the file is: " +wordCount);
		System.out.printf("%nThe total number of different words in the file is: "+uniqueCount);
		System.out.printf("%nWords of the input file in ascending order without duplication%n"+ascendingWordList);
		System.out.println();
		
/* to do
		//search prompt
		System.out.print("Please enter a search pattern: ");
		Scanner searchpattern = new Scanner(System.in)
		String terminationValue = 
		while (searchpattern != terminationValue ) 
*/
		
	
	}

	
	//method to convert string to a list, then count the number of elements, and convert that number to a string and return it
	public static int wordCounter(String inputString)
	{
		List<String> wordList = new ArrayList<String>(Arrays.asList(inputString.split("[^a-zA-Z0-9']+"))); //create a list, then split inputString at delimiter (non-alphanumeric) using regular expression
		int counter = wordList.size(); //set counter to be the total list size
		return counter; //return the count
	}
	
	
	//method to convert string to HashSet with no duplicates, return cound of elements
	public static int uniqueWordCounter(String inputString)
	{
		List<String> wordList = new ArrayList<String>(Arrays.asList(inputString.split("[^a-zA-Z0-9']+"))); 
		List<String> uniqueList = new ArrayList<String>(new HashSet<String>(wordList)); //section 16.10, wrap wordlist into a hashset to filter duplicates, and wrap set in a list again to make printing easier
		int listSize = uniqueList.size();
		//List<String> ascendingWordList = new ArrayList<String>(uniqueList);
		//String[] uniqueStringList = new String[uniqueList.size];
		//uniqueStringList = uniqueList.toArray(uniqueStringList);
		return listSize;	//return the list
	}
		
	
	public static String ascendingList(String inputString)
	{
		List<String> wordList = new ArrayList<String>(Arrays.asList(inputString.split("[^a-zA-Z0-9']+"))); 
		List<String> uniqueList = new ArrayList<String>(new HashSet<String>(wordList));
		Collections.sort(uniqueList);
		String ascendingWordList = String.join(" ", uniqueList);
		return ascendingWordList;
	}
		
		
		
		
		
		
		
		
		
}