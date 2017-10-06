// Timothy Smith
// CSCI 1152-102
// Lab03.java
// Marathon Times calculator using arrays
// Tsmith145@cnm.edu
// 6 October 2017

public class Lab03
{
	public static void main(String[] args)
	{
		//create array of type String
		String[] names = {  
				"Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt", "Alex", "Emma",
				"John", "James", "Jane", "Emily", "Daniel", "Neda", "Aaron", "Kate"  };
		
		//create array of type int
		int[] times = { 341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 299, 343, 317, 265 };
		
		int firstPlace = fastestIndex(times); //call method fastestIndex to return a value, store as firstPlace
		int secondPlace = secondFastestIndex(times); //call method secondFastestIndex to return value, store as secondPlace
		
		//output header/title bar
		System.out.printf("%s%29s%n", "Name", "Time(minutes)"); //print list heading
		for(int x = 0; x < 33; x++)
			System.out.printf("%s", "_"); //print a line of underscores using a loop
		System.out.println();
		
		//output results
		System.out.printf("%s%23d%n", names[firstPlace], times[firstPlace]); //output array values based on returned results
		System.out.printf("%s%23d%n", names[secondPlace], times[secondPlace]); 
	}

	//create method fastestIndex using values from times array
	public static int fastestIndex(int[] times)
	{
		int fastestIndex = 0;
		
		for (int index = 0; index < times.length; index++) //create array loop
		{
			//check if array value at [loop increment] index is less than value at index [0], then loop for the rest
			if (times[index] < times[fastestIndex])
				fastestIndex = index;
		}
	return fastestIndex;
	}
	
	//create a method secondFastestIndex which calls fastestIndex to compare
	public static int secondFastestIndex(int[] times)
	{
		int fastestIndex = fastestIndex(times); //call fastestIndex method to initialize variable
		int secondFastestIndex = 0;
		
		for (int index = 0; index < times.length; index++)
		{	
			//find fastest time value not equal to fastestIndex value, therefore, finds second place
			//suppose i could also pull firstPlace variable here if instructions didnt 
			//specifically say to loop through fastestIndex again
			if (times[index] < times[secondFastestIndex] && index != fastestIndex) //instead of "!=" could also use ">"
				secondFastestIndex = index;
		}
		return secondFastestIndex;
	}
}//end class Lab03