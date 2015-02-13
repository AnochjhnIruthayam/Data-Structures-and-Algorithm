
import java.awt.Point;





public class colinear {
	
	public void swap(double[] array, int swp1, int swp2){
		double temp = array[swp1];
		array[swp1] = array[swp2];
		array[swp2] = temp;
	}
	
	public int getPivot(double []array, int start, int end){
		int pivot;
		int middle = ((start + (end-start))/2); //also pivot value
		
		double max = 1;
		if(array[middle] > array[start])
			max = 2;
		if(array[end] > array[start] && array[end] > array[middle])
			max = 3;
		
		double min = 1;
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
	public void Quicksort(double[] array){
	    // Check for empty or null array
	    if (array ==null || array.length==0){
	      return;
	    }
	    double number = array.length;
	    Quicksort(array, 0, (int) (number - 1));
	}
	
	public void Quicksort(double []array, int start, int end)
	{
		int i = start;
		int j = end;
		int pivot_index = getPivot(array, start, end);
		double pivot_val = array[pivot_index];
		
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
	
	
	Point[] pointer = new Point[18];
	
	public void createPoints()
	{	


		pointer[0] = new Point(1,1);
		pointer[1] = new Point(1,2);
		pointer[2] = new Point(1,3);
		pointer[3] = new Point(1,4);
		pointer[4] = new Point(1,5);
		pointer[5] = new Point(1,6);
		pointer[6] = new Point(2,1);
		pointer[7] = new Point(2,4);
		pointer[8] = new Point(3,2);
		pointer[9] = new Point(3,3);
		pointer[10] = new Point(4,2);
		pointer[11] = new Point(4,3);
		pointer[12] = new Point(4,4);
		pointer[13] = new Point(4,5);
		pointer[14] = new Point(5,1);
		pointer[15] = new Point(5,4);
		pointer[16] = new Point(6,4);
		pointer[17] = new Point(4,1);
		
	}
	
	int collinear=0;// declare colinercount to 0
	 public void findSlope()
	 {
		 double[] slope; 	// declare slope array
		 slope = new double[pointer.length-1]; 	//size as the amount of points
		 for(int i = 0; i < pointer.length-1; i++) // start with one selected point
		 {
			 int k = 0; // index for the slope array
			 // for point i, find all points (omit i by i+1)
			 for(int j = i+1; j < pointer.length-1; j++)
			 {
				 if(pointer[j].getX()-pointer[i].getX() != 0) // checks if the dominator is zero to prevent division by zero
				 {
					 // for point i, find all slope
					 slope[k] = (( pointer[j].getY()-pointer[i].getY() ) / ( pointer[j].getX()-pointer[i].getX() )); // calculate slope a from ax+b
					 k++;
				 }
			 }
			 
			 Quicksort(slope, 0, slope.length-1-i); // sort the array
			 
			 for(int  j = 0; j < slope.length-3-i;  j++) // the reason for -3: We are gonna look through the array 3 index in. to prevent ArrayOutOfBound.
			 {
				 //find the same slope value for 4 points
				 if(slope[j]==slope[j+1] && slope[j+1] == slope[j+2] && slope[j+2] == slope[j+3])
				 {
					 //if condition is true, then add to collinear count.
					 collinear++;
					 }
				 }
			 }	
		 }
	 public void running()
	 {
		 
		 createPoints();
		 findSlope();
	 }
	 

	public void printout()
	{
		System.out.print("Points in the plane:\n");
		for(int i = 0 ; i < pointer.length; i++)
			System.out.print("(" + pointer[i].getX() + ", " + pointer[i].getY() + ")\n");
		System.out.print("Collinear: " + collinear + "\n");
	}
	
	
	public static void main( String [ ] args )
	{
		colinear test = new colinear();
		
		test.running();
		test.printout();

		
	}

}
