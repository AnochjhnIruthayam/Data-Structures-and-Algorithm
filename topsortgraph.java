package main;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


class Vertex 
{
	public ArrayList<Vertex> nextVertices = new ArrayList<Vertex>();
	public int indegree = 0;
	public int topNum = 0;
	int data = 0;
	

	public Vertex(int d)
	{
		data = d;
	}
	
	public void addVertex(Vertex v)
	{
		nextVertices.add(v);
		v.indegree++;
	} 
}


public class topsortgraph 
{
	public static void topo(ArrayList<Vertex> vertices) throws Exception
	{
		Queue<Vertex> q = new LinkedList<Vertex>();
		
		int count = 0;
		for(int i = 0; i < vertices.size(); i++ )
		{
			if(vertices.get(i).indegree == 0)
			{
				q.add(vertices.get(i)); //find all vertices with zero indegree and save to queue
			}
		}
		
		while(!q.isEmpty()) // while the queue is NOT empty
		{
			Vertex v = q.remove();	// remove and save to v
			v.topNum = ++count; 	// Assign next number
			
			for(int i = 0; i < v.nextVertices.size(); i++) // for each vertex w adjacent to v
			{
				Vertex w = v.nextVertices.get(i); // get data from adjacent
				w.indegree--; // decrement
				if(w.indegree == 0)
				{
					q.add(w);
				}
			}
		}

		if(count != vertices.size())
		{
			throw new Exception("Cycle Found");
		}
	}

	
	public static void main(String[] args) throws Exception
	{
		ArrayList<Vertex> list = new ArrayList<Vertex>();
		for(int i=0; i<7;i++)
		{
			list.add(new Vertex(i+1));
		}
		
		// figure 9.4 page 363 (Weiss):
		//offset on -1
		list.get(1-1).addVertex(list.get(2-1)); // v1-->v2
		list.get(1-1).addVertex(list.get(3-1)); // v1-->v3
		list.get(1-1).addVertex(list.get(4-1)); // v1-->v4
		
		list.get(2-1).addVertex(list.get(4-1)); // v2-->v4
		list.get(2-1).addVertex(list.get(5-1)); // v2-->v5
		
		list.get(3-1).addVertex(list.get(6-1)); // v3-->6
		
		list.get(4-1).addVertex(list.get(3-1)); // v4-->v3
		list.get(4-1).addVertex(list.get(6-1)); // v4-->v6
		list.get(4-1).addVertex(list.get(7-1)); // v4-->v7
		
		list.get(5-1).addVertex(list.get(4-1)); // v5-->v4
		list.get(5-1).addVertex(list.get(7-1)); // v5-->v7
		
		list.get(7-1).addVertex(list.get(6-1)); // v7-->v6

		
		topo(list);

		for(Vertex v : list){
			System.out.print(v.topNum + " ");
		}
	}
}