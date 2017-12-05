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
		
		String filename = "example.txt"; //placeholder
		if (args.length > 0)
		{
			filename = args[0];
		} else
		{
			System.out.println("no filename specified, using default 'example.txt' file");
		}
		//method calls for total word count
		String totalArray[] = new String[5000];  //wanted to use a static array because it's harder to manipulate and I wanted the practice, I use ArrayLists later on.
		totalArray = totalWord(totalArray, filename);  //method call to create array
		int wordCount; 
		wordCount = counter(totalArray);  //calling method to count array elements
		
		//method calls for unique word count
		String uniqueArray[] = new String[wordCount];  //make new array of a minimal size
		uniqueArray = filterDuplicates(wordCount, filename);  //method call to filter out duplicates
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

			List<String> lineList = new ArrayList<>();
			lineList = createLineList(filename);  //create a list from input document, line by line
			//System.out.print(lineList);  //print list to verify it's being stored properly
			
			for(String s : lineList) //iterate through list
			{
				if (s.contains(searchString)) //do this if element contains searchString within
				{
					System.out.printf("%nLine number " + (lineList.indexOf(s)+1));  //print the index value +1
					System.out.println();
					System.out.println(s); //print current element

					//arrow printing
					String arrow = "^";
					String space = "";
					for ( int i = -1; (i = s.indexOf(searchString, i)) != -1; i++)  //iterate through the string to find word(s), if word is found (!= -1), do rest
					{
						space = String.format("%" + i + "s", " ");  //create a string that is %Xs where X is variable integer
						System.out.printf("%s%s", space, arrow);  //exception thrown when index is zero (first word)   TODO: FIX THIS, also, spacing is off on second result in line.
					} 
				} //end if statement
			} //end for loop		
			
		} while (!searchString.equals(terminateString));  //loop until terminate value is matched to input
	} //end main

	
	/*----------methods-----------*/
	
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
	public static String[] totalWord(String[] array, String path)
	{
		Scanner scan = null;
		try 
		{
			scan = new Scanner(new File(path)).useDelimiter("[^a-zA-Z0-9']+");  //delimiting non-alphanumeric, but exempted apostrophes also in case of contractions
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
	public static String[] filterDuplicates(int wordCount, String path)
	{
		Scanner scan = null;  //rescan document//  TO DO:  place another method call here and at line 102-110 to do this and just reuse that instead
		try 
		{
			scan = new Scanner(new File(path)).useDelimiter("[^a-zA-Z0-9']+");
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
		
		Collections.sort(stringList);
		
		/*  use this if fix sorter
		//get sorted array
		String[] sortedArray = new String[uniqueArray.length];
		sortedArray = sorter(uniqueArray, 1); 
		*/
		
		//convert List to string using stringbuilder
		//previously using loop to append, but decided stringbuilder is better for performance
		StringBuilder sb = new StringBuilder();
		for (String s : stringList)
		{
			sb.append(s);
			sb.append(", ");
		}
		String uniqueString = new String(sb.toString());
		return uniqueString;
	}
	
	//method to manually sort, nullointerexception being thrown, fix if there's time
/*	private static String[] sorter(String[] array, int position)
	{
		String[] aux = new String[array.length];

		char min = array[0].charAt(position);
		char max = array[0].charAt(position);
		for(int i = 1; i < array.length; i++)
		{
			if(array[i].charAt(position) < min) min = array[i].charAt(position);
			else if(array[i].charAt(position) > max) max = array[i].charAt(position);
		}

		int[] counts = new int[max - min + 1];

		for(int i = 0;  i < array.length; i++)
			counts[array[i].charAt(position) - min]++;

			counts[0]--;
		for(int i = 1; i < counts.length; i++)
			counts[i] = counts[i] + counts[i - 1];

		for(int i = array.length - 1; i >=0; i--)
			aux[counts[array[i].charAt(position) - min]--] = array[i];

		return aux;
  }  */
  
	//method to convert input file into a list of whole lines from doc
	public static List<String> createLineList(String path)
	{
		Scanner scan = null;
		try 
		{
			scan = new Scanner(new File(path));
		} catch (FileNotFoundException e) 
		{
			System.out.println(e);
			System.exit(1);
		} 		
		
		List<String> list = new ArrayList<String>();  //create new list in which to store inputs, using list instead of static array this time
		String temp = new String(); 
		while (scan.hasNextLine())  //while more data to be read on next line
		{ 
			temp = scan.nextLine();  //store next line's contents to temp string
			list.add(temp);  //add temp string to list
		}
		scan.close();
		return list;
	}
	
} //end class