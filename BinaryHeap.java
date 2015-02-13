// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the binary heap.
     */
    public BinaryHeap( )
    {
        this( DEFAULT_CAPACITY );
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ capacity + 1 ];
    }
    
    /**
     * Construct the binary heap given an array of items.
     */
    public BinaryHeap( AnyType [ ] items )
    {
            currentSize = items.length;
            array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];

            int i = 1;
            for( AnyType item : items )
                array[ i++ ] = item;
            buildHeap( );
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

            // Percolate up
        int hole = ++currentSize;
        for( array[ 0 ] = x; x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }


    private void enlargeArray( int newSize )
    {
            AnyType [] old = array;
            array = (AnyType []) new Comparable[ newSize ];
            for( int i = 0; i < old.length; i++ )
                array[ i ] = old[ i ];        
    }
    
    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            return null;
        return array[ 1 ];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            return null;

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;      // Number of elements in heap
    private AnyType [ ] array; // The heap array

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {
        int child;
        AnyType tmp = array[ hole ];

        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
                    array[ child + 1 ].compareTo( array[ child ] ) < 0 )
                child++;
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
    
    
    public void twonumsum(int num)
	{
		int K = num;
		//declare array
		int[] liste;
		
		//allocate memory
		int N = 4;
		liste = new int[N];
		
		//insert numbers
		liste[0] = 8;
		liste[1] = 4;
		liste[2] = 1;
		liste[3] = 6;
		
		for(int i = 0; i < (liste.length) ; i++)
		{
			for(int j = 0; (j < liste.length); j++)
			{
				if(liste[i]+liste[j] == K)
				{
					System.out.print(liste[i] + " + " + liste[j] + " = " + K + "\n");
				}
			}
		}
	}
    
	public void sorting(Integer value)
	{
		int i =0;
		int j = (Integer) currentSize;
		int res = 0;
		int temp_i;
		int temp_j;
		while(res != value)
		{
			temp_i = (Integer) array[i];
			temp_j = (Integer) array[j];
			res = temp_j + temp_i;
			
			if(res < value)
				i++;
			else if(res > value)
				j--;
			else if(res == value)
				System.out.println("First number: " + array[i] + "\nSecond number: " + array[j] + "\nEqual to " + value );
			else
				System.out.println("ERROR: no cant do");
		}
		
	}

        // Test program
    public static void main( String [ ] args )
    {
    	BinaryHeap<Integer> test = new BinaryHeap<Integer>();
    	
    	System.out.print("Method O(N^2): \n");
    	long start,stop;
    	start = System.nanoTime();
    	test.twonumsum(10);
    	stop = System.nanoTime();
    	long stop1 = (stop-start);
    	System.out.print("Runtime for O(N^2): " + stop1 + " nanosec.\n\n");
    	
    	System.out.print("Method O(N*logN): \n");
    	test.insert(8);
    	test.insert(4);
    	test.insert(1);
    	test.insert(6);
    	start = System.nanoTime();
    	test.sorting(10);
    	stop = System.nanoTime();
    	long stop2 = (stop-start);
    	System.out.print("Runtime for O(N*logN): " + stop2 + " nanosec.\n");
    	System.out.print("Difference in runtime: " + (stop1-stop2) + " nanosec.\n");
    	
    	
    	
    	/*
    	 
        int numItems = 10000;
        BinaryHeap<Integer> h = new BinaryHeap( );
        int i = 37;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            h.insert( i );
        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );
        
        */
    }
}