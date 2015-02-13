
public class truefalseelement {
	
	public void sort()
	{
		boolean swp1;
		boolean swp2;
		
		int i;
		int j;
		int k;
		boolean[] array;
		array = new boolean[8];
		
		array[0] = true;
		array[1] = false;
		array[2] = true;
		array[3] = false;
		array[4] = true;
		array[5] = false;
		array[6] = true;
		array[7] = false;
		
		i = 0;
		j = array.length-1;
		
		while(i!= j)
		{		
			if(array[i] == false)
				++i;
			
			else
				if(array[j] == true)
					--j;
		
			if(array[i] != false)
				if(array[j] != true)
				{
					swp1 = array[i];
					swp2 = array[j];
					array[j] = swp1;
					array[i] = swp2;
					++i;
					--j;
				}
		}
		for(k = 0; k < array.length; k++)
			System.out.println(array[k]);
	}
	
	public static void main( String[] args )
	{
		truefalseelement test = new truefalseelement();
		test.sort();
	}

}
