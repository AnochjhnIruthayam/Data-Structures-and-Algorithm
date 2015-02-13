package MyFiles;

import java.io.File;

public class MyFile {
	public static void main(String[] args){
		int antal = findFileTypeAntal(".mkv", new File("D:\\"));
		
		System.out.println(antal);
	}
	public static int findFileTypeAntal (String type, File folders)
	{
		int sum=0;
		try
		{	
			File[] myfiles = folders.listFiles();
			for(File fil : myfiles)
			{
				if(fil.isDirectory())
				{
					sum +=findFileTypeAntal(type, fil);
				}
				else if(fil.getName().endsWith(type))
				{
					System.out.println(fil.getName());
					sum++;
				}
			}
			
		}
		
		
		
		catch(Exception e){}
		return sum;
	}
}

