import java.util.Random;



public class BallsandBins 
{
	public static int RandomNumber(int x)
	{
		int rand;
		Random r = new Random();
		rand = r.nextInt(x);
		return rand;
	}
	
	public static int binball(int tableSize)
	{
		int binidx=0;
		int[] bin;
		bin = new int[(tableSize-1)];
	
		for( int i = (tableSize-1); i>0; i-- )
		{
			binidx = RandomNumber((tableSize-1));
			bin[binidx]++;
		}
		
		int maxBin = 0;
		for(int j = 0; j<(tableSize-1); j++)
		{
			if(bin[j]>maxBin)
			{
				maxBin = bin[j];
			}
			
		}
		return maxBin;
			
	}
	
	
	public static void main(String[] args)
	{
		System.out.println("Balls and bin problem expressed mathematically: log(N)/log(N)log(N)");
		int tableSize = 10007;
		System.out.println("\nTablesize: " + tableSize + "\nBin with largest amount of balls have: " + binball(tableSize));
		tableSize = 1000000;
		System.out.println("\nTablesize: " + tableSize + "\nBin with largest amount of balls have: " + binball(tableSize));
		
		
	}


}
