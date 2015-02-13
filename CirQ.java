
public class CirQ {
	
	int CirSize = 4;
	
	private int[] cQ = new int[CirSize];
	
	int front = 0;
	int back = 0;
	int currentSize = 0;
	
	
	public void ensureCap()
	{
		if(currentSize == cQ.length -1) // if queue full, double queuesize
		{
			int[] newQ = new int[cQ.length*2];
			int j = 0;
			for(int i = front; i < cQ.length-1; i++ )
			{
				newQ[j++] = cQ[i];
					
			}
			for(int i=0; i<front; i++){
				newQ[j++] = cQ[i];
			}
			//newQ[j++] = cQ[0]; // wraparound
			front = 0;
			back = cQ.length-1;
			cQ = newQ;
		}
	}
	
	public boolean isEmpty()
	{
		if(currentSize == 0)
		{
			return true;
		}
		else
			return false;
		
	}
	
	public int dequeue()
	{
		int retur = cQ[front];
		cQ[front] = 0;
		if ( CirSize == cQ.length ) // if front is on the last spot on the array, return to 0
		{
			front = 0;
		}
		front++;
		currentSize--; //remove one item from queue.
		return retur;
		
	}
	
	public void enqueue(int item)
	{
		ensureCap();
		if(back == cQ.length)
		{
			back = 0;
		}
		cQ[back] = item;
		back++;
		currentSize++;
	}
	
	public static void main(String[] args)
	{
		CirQ testQ = new CirQ();
		
		for(int i=0; i<1000; i++){
			testQ.enqueue((int)(Math.random()*200));
			if(Math.random()<0.5){
				testQ.dequeue();
			}
		}


		
		//testQ.dequeue();
		//testQ.dequeue();
		
		for (int i = 0; i < testQ.cQ.length; i++)
		{
			System.out.print(" "+testQ.cQ[i]);
			
		}
	}

}