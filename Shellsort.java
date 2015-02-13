package sort;
import java.util.Random;
import static java.lang.Math.*;

enum gapmethod {SHELL, HIBBARD};

public class Shellsort{
	
	public int RandomNumber(int x)
	{
		int rand;
		Random r = new Random();
		rand = r.nextInt(x);
		return rand;
	}
	
	
	void shellsort(int []array, int []gap)
	{
		int j;
		
		for(int k = 0; k < gap.length; k++) // choosing gap number
		{
			int currentgap = gap[k];
			for(int i = currentgap; i < array.length; i++)
			{
				int temp = array[i];
				for(j = i; (j >= currentgap) && (temp < array[j-currentgap]); j -= currentgap) //statement true as long as index j is greater than current gap and current element is 
				{
					
					array[j] = array[j - currentgap];
				}
				array[j] = temp;
			}
		}
	}
	
	public int[] ShellGap(int size)
	{
		 int count = 0;
		 int i = size;
		 int[] gap;
		 
		 while ( i != 0)
		 {
			 i /= 2;
			 count++;
		 }
		 gap = new int[count-1];
		 int l = 0;
		 i = size;
		 while ( i != 0)
		 {
			 i /= 2;
			 if(i !=0){
				 gap[l] = i;
				 l++;
			 }
		 }
		 return gap;
	}
	
	public int[] HibbardGap(int size)
	{
		int count = 0;
		int[] gap;
		int k=0;
		int j = 1;
		while (k < size )
		{
			k = (int ) pow(2,j)-1;
			if( k < size)
			{
				count++;
			}
			j++;
		}
		gap = new int[count];
		int n = 0;
		for (  j = count; 0 < j; j--)
		{
			gap[n] =  (int) (pow(2,j)-1);
			n++;
		}
		
		return gap;
	}


	 public static void main(String[] args)
	 {
		 
		Shellsort test = new Shellsort();
		
		// SELECT SIZE
		int size = 1000;
		
		// SELECT INCREMENT METHOD: SHELL OR HIBBARD 
		//***TYPE-> gapmethod method = gapmethod.<SELECTED METHOD>  *******
		gapmethod method = gapmethod.HIBBARD;
		
		long start,stop;
		int[] gap = null;
		int[] array;
		
		array = new int[size];
		
		// Generate random numbers
		for(int i = 0 ; i < size; i++)
		{
			array[i] = test.RandomNumber(100);
		}
		
		if(method == gapmethod.SHELL)
		{
			gap = test.ShellGap(size);
		}
		else if(method == gapmethod.HIBBARD)
		{
			gap = test.HibbardGap(size);
		}
		
		
		 
		System.out.print("Before ShellSorting:\t"); // print
		for(int n = 0; n < array.length; n++)
		{
			System.out.print(array[n] + " "); // print
		}
		
		start = System.nanoTime();
		test.shellsort(array, gap);
		stop = System.nanoTime();
		 
		System.out.print("\nAfter ShellSorting:\t"); // print
		for(int n = 0; n < array.length; n++)
		{
			System.out.print(array[n] + " "); // print
		}
		if(method == gapmethod.SHELL)
		{
			System.out.print("\nUsed Gaps \n(Shells increment O(N^2)):\t"); // print
			for(int n = 0; n < gap.length; n++)
			{
				System.out.print(gap[n] + " "); // print
			}
		}
		else if(method == gapmethod.HIBBARD)
		{
			System.out.print("\nUsed Gaps \n(Hibbard increment O(N^(3/2)):\t"); // print
			for(int n = 0; n < gap.length; n++)
			{
				System.out.print(gap[n] + " "); // print
			}	
		}
		System.out.print("\nRuntime:\t\t" + (stop-start) + " nanosec.");
			
	 }
}
