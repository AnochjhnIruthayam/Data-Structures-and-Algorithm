public class Linkedlistdubremove<AnyType>
{
	private static class Node<AnyType>
	{
		AnyType elem;
		Node<AnyType> next, previous; // declare node
	}

	private Node<AnyType> head = null; // declare node to NULL
	private Node<AnyType> tail = null;
	private Node<AnyType> temp = null;
	
	private int counter = 0;
	public Linkedlistdubremove()
	{
		//constructor 
	}
	public int size()
	{
		return counter;
	}


	public void add(AnyType elem)
	{
		if(head == null) // if start of list, add element in head
		{
			head = tail = new Node<AnyType>(); // make new node to head and tail
			head.elem = elem; // add element to head. dot referring to static member
			head.next = tail; // tail is saved next to the head. 
			tail = head; // first element must be head and tail
		}
		else // if there already an element, add next to it
			{
			tail.next = new Node<AnyType>(); // next to the tail, make new node
			tail = tail.next; // the newly added tail, is the new tail
			tail.elem = elem; // insert the element to that tail
			}
		counter++; // count one element up!
		}
	
	public AnyType get(int index)
	{
		assert (index >= 0 && index < size()); // ensure that index is in the limit
		
		temp = head; // copy head into temp
		
		for(int i = 0; i < index; i++) // count "index" times up
			temp = temp.next; // steps "index" times up, when step ends, temp is saved
		return temp.elem; // return the element
	}
	public int indexof(int elem)
	{
		temp = head; // copy head into temp
		int k;
		for(k = 0;!(temp.elem).equals(elem) && temp != null; k++) // as long as current temp is NOT equal to the desired element and temp is NOT null, run the FOR loop
		{
			temp = temp.next; // save next temp into temp
		}
		if(k == size()) // if k is same size as the list, return -1
			return -1;
		return k; // else return index number of the element
	}
	
	
	public AnyType remove(int index)
	{
		assert(index >= 0 && index < size ()); // ensure index is in the limit
		temp = head; // copy head to temp
		
		if(index == 0) // if index is the first element aka the head
		{
			AnyType elem = head.elem; //copy head to elem
			head = head.next; // define the element next to head, to be the new head
			counter--; // decrease the counter
			return elem; // return element
		}
		
		else if(index == size()) // if the index is the last element, aka tail
		{
			AnyType elem = tail.elem; // copy tail to elem
			tail = tail.previous; // take the previous node, and make it to the new tail
			counter--; // decrease the counter
			return elem; // return the element
		}
		
		for(int i = 0; i < index-1; i++) // is index is between head and tail
			temp = temp.next; // stepping "index-1" times up and save it to temp
		Node<AnyType> two = temp.next; // get the value next to temp
		
		temp.next = two.next; // next of two // connect the next element
		AnyType elem = two.elem; // save two.elem to elem
		two = null; // two to NULL
		counter--; // decrease
		return elem; // return elem
	}
	
	public void print()
	{
		for (int i = 0; i < size(); i++)
		{
			System.out.println(get(i));
		}	
	}
	
	public void removedubs()
	{
		for(int j = 0; j < size(); j++)
		{
			for(int i = 0; i < size(); i++)
			{
				if(i > j) //index i may not be past the current index j
				{
					if(get(j) == get(i))
					{
						remove(i);
						i=0;
					}
				}
			}
		}
	}


public static void main(String[] args)
{
	Linkedlistdubremove<Integer> testlink = new Linkedlistdubremove<Integer>();
	System.out.println("Before: ");
	
	testlink.add(11);
	testlink.add(12);
	testlink.add(13);
	testlink.add(14);
	testlink.add(15);
	
	testlink.add(11);
	testlink.add(12);
	testlink.add(11);
	testlink.add(11);
	testlink.add(11);
	testlink.add(11);
	testlink.add(11);
	testlink.add(11);
	testlink.add(11);
	testlink.add(11);
	testlink.add(13);
	testlink.add(15);
	testlink.add(10);
	testlink.print();
	
	//testlink.print();
	testlink.removedubs();
	System.out.println("After: ");
	testlink.print();

	

	}
}