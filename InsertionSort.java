package main;

import java.util.Random;


public class InsertionSort 
{
	public int RandomNumber(int x)
	{
		int rand;
		Random r = new Random();
		rand = r.nextInt(x);
		return rand;
	}
	
	
	void insertionSort(int[] array)
	{
		int j;

		
		for( int p = 1; p < array.length; p++)
		{
			int tmp = array[p];
			j = p;
			while( j > 0 && (array[j - 1] > tmp))
			{
				array[j] = array[j - 1];
				j = j - 1;
			}
			array[j] = tmp;
		}
	}
	
	
	public static void main( String[] args )
	{
		InsertionSort test = new InsertionSort();
		
		 //SELECT ARRAY SIZE
		 int size = 1000;
		int[] array;
		
		array = new int[size];
		
		// Generate random numbers
		for(int i = 0 ; i < size; i++)
		{
			array[i] = test.RandomNumber(100);
		}
		 
		 
		 System.out.print("Before\n"); // print
		for(int n = 0; n < array.length; n++)
		{
			System.out.print(array[n] + " "); // print
		}
		
		long start,stop;
		
		
		start = System.nanoTime();
		 test.insertionSort(array);
		 stop = System.nanoTime();
		 
		 System.out.print("\nAfter\n"); // print
		for(int n = 0; n < array.length; n++)
		{
			System.out.print(array[n] + " "); // print
		}
		System.out.print("\nRuntime:\t\t" + (stop-start) + " nanosec.");
		 
		
		
	}
}
