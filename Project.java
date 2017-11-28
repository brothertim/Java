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
		
		String inputString = "";
		String uniqueList = "";
		String ascendingWordList = "";
		uniqueList = new String();
		ascendingWordList = new String();
		int wordCount; 
		int uniqueCount;
		
		try  //try&catch block to validate input file and catch exceptions, exit program if exceptions occur
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
		
	//this section for more methods	
		
		
		
		
		
		
		
}



/* this is an example paragraph to process:

When I first brought my cat home from the humane society she was a mangy, 
pitiful animal. It cost a lot to adopt her: forty dollars. And then I had 
to buy litter, a litterbox, food, and dishes for her to eat out of. Two 
days after she came home with me she got taken to the pound by the animal
warden. There's a leash law for cats in Fort Collins. If they're not in your 
yard they have to be on a leash. Anyway, my cat is my best friend. I'm glad 
I got her. She sleeps under the covers with me when it's cold. Sometimes she 
meows a lot in the middle of the night and wakes me up, though. 
*/

/* this is the program output based on the above paragraph example

E:\java Project example.txt

The total number of words in the file is: 122
The total number of different words in the file is: 85
Words of the input file in ascending order without duplication
And Anyway Collins Fort I I'm If It She Sometimes There's Two When a adopt after and animal be best 
brought buy by came cat cats cold cost covers days dishes dollars eat first food for forty friend 
from glad got had have her home humane in is it's law leash litter litterbox lot mangy me meows middle
my night not of on out pitiful pound she sleeps society taken the then they they're though to under 
up wakes warden was when with yard your

E:\

*/

// to do:  add a prompt to allow user to input a word to search for.  EINPUT is command to terminate program
// ex:  Please enter a search pattern:  the <enter>  (in this case, user searches for the word "the")
// ex:  Line number:  1  (print which line number that word appears on)
// ex:  When I first brought my cat home from the humane society she was a mangy,  (print the contents of the line)
// ex:                                        ^   (print an arrow under the first letter of the word)
// ex:  Line number: 4  (since it appears on more than one line)
// ex:  days after she came home with me she got taken to the pound by the animal
// ex:                          			  ^
