
import java.util.Stack;

public class CheckbalPar 
{
	public static boolean balPar(String text)
	{

		Stack<String> lifo = new Stack<String>();
		for(int i=0; i < text.length(); i++) // length fortælller lændgen af string
		{
			if(text.charAt(i) == '(') // tager et tegn af gangen, sammen ligner med '('
			{	
				lifo.push("x");			//hvis true, PUSH i stack
			}
			if(text.charAt(i) == ')')
			{
				if(lifo.isEmpty())		//hvis den er tom, så false
				{
					return false;
				}
				else
					lifo.pop();				//hvis ')' POP x'et fra stack
			}
		}
		if(lifo.isEmpty())
		{
			return true;
		}
		else
			return false;
		
	}
	
	
	public static void main(String[] args)
	{
			System.out.println (balPar("(()(()()()()((())))))())((())))(())(()(()(()((((())))())())()))((())()"));

	}
}



