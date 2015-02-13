package pakke;

import java.util.ArrayList;


class Vertex 
{
	public ArrayList<Vertex> 	adj = new ArrayList<Vertex>();
	public ArrayList<Integer> 	distList = new ArrayList<Integer>();
	public boolean 				known;
	public int 					dist;
	public Vertex 				path;
	int 						data;
	

	public Vertex(int d)
	{
		data = d;
	}
	
	public void add(Vertex ve, int weight)
	{
		adj.add(ve);
		distList.add(weight);
	}
	
	public void print()
	{
		if(path != null)
		{
			path.print();
			System.out.print("V" + data + " ");
		}
		else
			System.out.print("V" + data + " ");
	}

	
}


public class Dijkstra 
{
	
	private static final int INFINITY = 100000;

	public static void dijkstra(ArrayList<Vertex> vert, Vertex start)
	{
		// Initializing by setteing the distance to infinity and known to false
		for(int i = 0; i < vert.size(); i++)
		{
			vert.get(i).dist = INFINITY;
			vert.get(i).known = false;
		}
		//the selected path has a distance of 0
		start.dist = 0;

		boolean unknown = true;
		for(Vertex isbil : vert)
		{
			if(unknown)
				unknown = true;
			else
				unknown = !isbil.known;
		}
		
		
		while(unknown)
		{
			
			Vertex v = null;
			//find the smallest unknown distance vertex
			for(int i = 0; i < vert.size(); i++)
			{
				if(!(vert.get(i).known)) // if not known
				{
					if(v == null)
						v = vert.get(i); // if vert is null save to v
					else
					{
						if(vert.get(i).dist < v.dist)
							v = vert.get(i);
						else
							; // keep the v value
					}
				}
			}
			
			v.known = true;
			
			
			for(int j = 0; j < v.adj.size(); j++)
			{
				if(!(v.adj.get(j).known)) // if not known
				{
					int cvw = v.distList.get(j);
					if(cvw + v.dist< v.adj.get(j).dist)
					{
						v.adj.get(j).dist =  v.dist + cvw;
						v.adj.get(j).path = v;
					}
				}
			}
			unknown = false;
			for(Vertex isbil : vert)
			{
				if(unknown)
					unknown = true;
				else
					unknown = !isbil.known;
			}
		}
		
		
	}
	

	
	public static void main(String[] args){
		
		
		ArrayList<Vertex> list = new ArrayList<Vertex>();
		for(int i=0; i<7; i++)
		{
			list.add(new Vertex((i+1)));
		}
		//Figure 9.20 page 393 (Wiess)
		list.get(0).add(list.get(1),2);
		list.get(0).add(list.get(3),1);
		list.get(1).add(list.get(3),3);
		list.get(1).add(list.get(4),10);
		list.get(2).add(list.get(0),4);
		list.get(2).add(list.get(5),5);
		list.get(3).add(list.get(2),2);
		list.get(3).add(list.get(5),8);
		list.get(3).add(list.get(6),4);
		list.get(3).add(list.get(4),2);
		list.get(4).add(list.get(6),6);
		list.get(6).add(list.get(5),1);
		
		//Dijkstra test = new Dijkstra();
		dijkstra(list, list.get(0));
		
		int thisvertex = 5;
		System.out.print("The Shortest Path for " + "V" + thisvertex +  " is to go through these vertices: \n");
		list.get(thisvertex-1).print();
	}
	

}
