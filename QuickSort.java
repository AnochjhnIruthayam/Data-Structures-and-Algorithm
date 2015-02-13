package sort;

import java.util.Random;

public class QuickSort {
	
	public int RandomNumber(int x)
	{
		int rand;
		Random r = new Random();
		rand = r.nextInt(x);
		return rand;
	}
	
	public void swap(int[] array, int swp1, int swp2){
		int temp = array[swp1];
		array[swp1] = array[swp2];
		array[swp2] = temp;
	}
	
	public int getPivot(int []array, int start, int end){
		int pivot;
		int middle = ((start + (end-start))/2); //also pivot value
		
		int max = 1;
		if(array[middle] > array[start])
			max = 2;
		if(array[end] > array[start] && array[end] > array[middle])
			max = 3;
		
		int min = 1;
		if(array[middle] < array[start])
			min = 2;
		if(array[end] < array[start] && array[end]< array[middle])
			min = 3;
		
		if(min*max == 2)
			pivot = end;
		else if(min*max == 3)
			pivot = (start+end)/2;
		else if(min*max == 6)
			pivot = start;
		else
			pivot = 1;
			

		return pivot;
	}
	
	// Quicksort driver
	public void Quicksort(int[] array){
	    // Check for empty or null array
	    if (array ==null || array.length==0){
	      return;
	    }
	    int number = array.length;
	    Quicksort(array, 0, number - 1);
	}
	
	public void Quicksort(int []array, int start, int end)
	{
		int i = start;
		int j = end;
		int pivot_index = getPivot(array, start, end);
		int pivot_val = array[pivot_index];
		
		while(i <= j)
		{
			while(array[i] < pivot_val)
			{
				i++;
			}
			while(array[j] > pivot_val)
			{
				j--;
			}
			
			if( i <= j)
			{
				swap(array,i,j);
				i++;
				j--;
			}
		}
		
		if(start < j)
			Quicksort(array,start,j);
		if(i < end)
			Quicksort(array,i,end-1);
		
		
		
	}
	
	
	
	
	 public static void main(String[] args)
	 {
		 QuickSort test = new QuickSort();
		 //int array[] = {8,1,4,9,6,3,5,2,7,0};
		
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
		 test.Quicksort(array);
		 stop = System.nanoTime();
		 
		 System.out.print("\nAfter\n"); // print
		for(int n = 0; n < array.length; n++)
		{
			System.out.print(array[n] + " "); // print
		}
		System.out.print("\nRuntime:\t\t" + (stop-start) + " nanosec.");
	 }
}
