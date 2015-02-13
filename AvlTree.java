// AvlTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is present
// boolean remove( x )    --> Return true if x was present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class AvlTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public AvlTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

       
    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<AnyType> remove( AnyType x, AvlNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return balance( t );
    }
    
    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
           return null;
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            return null;
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if x is found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    private static final int ALLOWED_IMBALANCE = 1;
    
    // Assume t is either balanced or within one of being balanced
    private AvlNode<AnyType> balance( AvlNode<AnyType> t )
    {
        if( t == null )
            return t;
        
        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    
    public void checkBalance( )
    {
        checkBalance( root );
    }
    
    private int checkBalance( AvlNode<AnyType> t )
    {
        if( t == null )
            return -1;
        
        if( t != null )
        {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                    height( t.left ) != hl || height( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }
        
        return height( t );
    }
    
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<AnyType> insert( AnyType x, AvlNode<AnyType> t )
    {
        if( t == null )
            return new AvlNode<AnyType>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
        
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return balance( t );
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private AvlNode<AnyType> findMin( AvlNode<AnyType> t )
    {
        if( t == null )
            return t;

        while( t.left != null )
            t = t.left;
        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private AvlNode<AnyType> findMax( AvlNode<AnyType> t )
    {
        if( t == null )
            return t;

        while( t.right != null )
            t = t.right;
        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return true if x is found in subtree.
     */
    private boolean contains( AnyType x, AvlNode<AnyType> t )
    {
        while( t != null )
        {
            int compareResult = x.compareTo( t.element );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else
                return true;    // Match
        }

        return false;   // No match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( AvlNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height( AvlNode<AnyType> t )
    {
        return t == null ? -1 : t.height;
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> rotateWithLeftChild( AvlNode<AnyType> k2 )
    {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> rotateWithRightChild( AvlNode<AnyType> k1 )
    {
        AvlNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleWithLeftChild( AvlNode<AnyType> k3 )
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleWithRightChild( AvlNode<AnyType> k1 )
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }

    private static class AvlNode<AnyType>
    {
            // Constructors
        AvlNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        AvlNode( AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
        }

        AnyType           element;      // The data in the node
        AvlNode<AnyType>  left;         // Left child
        AvlNode<AnyType>  right;        // Right child
        int               height;       // Height
    }

      /** The tree root. */
    private AvlNode<AnyType> root;


        // Test program
    
    public int nodeCount()
    {
    	return nodeCount(root);
    }
    
    private int nodeCount(AvlNode<AnyType> t)
    {
    	int nodeCounter=0;
    	if(t.left != null)
    	{
    		nodeCounter += nodeCount(t.left);
    	}
    	if(t.right != null)
    	{
    		nodeCounter += nodeCount(t.right);
    	}
    	
    	if((t.left == null) && (t.right == null))
    	{
    		return 1; // return 1 leaf
    	}
    	else
    	{
    		return nodeCounter + 1;
    	}
    }
    
    
    public int nodeFullCount()
    {
    	return nodeFullCount(root);
    }
    
    private int nodeFullCount(AvlNode<AnyType> t)
    {
    	int nodeFullCounter=0;
    	if(t.left != null)
    	{
    		nodeFullCounter += nodeFullCount(t.left);
    	}
    	if(t.right != null)
    	{
    		nodeFullCounter += nodeFullCount(t.right);
    	}
    	
    	if((t.left == null) && (t.right == null))
    	{
    		return 0; // return 0 leaves
    	}
    	else if((t.left != null) && (t.right != null))
    	{
    		return nodeFullCounter + 1;
    	}
    	else return nodeFullCounter;
    }
    
    
    
    
    public int leafCount()
    {
    	return leafCount(root);
    }
    
    private int leafCount(AvlNode<AnyType> t)
    {
    	int leafCounter=0;
    	if(t.left != null)
    	{
    		leafCounter += leafCount(t.left);
    	}
    	if(t.right != null)
    	{
    		leafCounter += leafCount(t.right);
    	}
    	
    	
    	if((t.left == null) && (t.right == null))
    	{
    		return leafCounter +1; // return 1 leaf
    	}
    	else
    	{
    		return leafCounter;
    	}
    	
    }
    
    public void preorder()
    {
    	preorder(root);
    }
    
    private void preorder(AvlNode<AnyType> t)
    {
    	System.out.print((t.element) + " "); // print
    	if(t.left != null)
    	{
    		preorder(t.left);
    	}
    	if(t.right != null)
    	{
    		preorder(t.right);
    	}
    }
    
    
    public void postorder()
    {
    	postorder(root);
    }
    
    private void postorder(AvlNode<AnyType> t)
    {
    	
    	if(t.left != null)
    	{
    		postorder(t.left);
    	}
    	if(t.right != null)
    	{
    		postorder(t.right);
    	}
    	System.out.print((t.element) + " "); // print
    }
    
    
    
    
    public static void main(String[] args)
	{
    	AvlTree<Integer> testAVL = new AvlTree<Integer>();
    	testAVL.insert(6);
    	testAVL.insert(4);
    	testAVL.insert(7);
    	testAVL.insert(2);
    	testAVL.insert(1);
    	//testAVL.printTree();

    	long start,stop;
    	
    	System.out.print("Leaves:\t\t");
    	start = System.nanoTime();
    	System.out.print(testAVL.leafCount());
    	stop = System.nanoTime();
    	System.out.print("\tRuntime: " + (stop-start) + " nanosec.");
    	System.out.print("\n");
    	
    	
    	System.out.print("Nodes:\t\t");
    	start = System.nanoTime();
    	System.out.print(testAVL.nodeCount());
    	stop = System.nanoTime();
    	System.out.print("\tRuntime: " + (stop-start) + " nanosec.");
    	System.out.print("\n");
    	
    	
    	System.out.print("Full nodes:\t");
    	start = System.nanoTime();
    	System.out.print(testAVL.nodeFullCount());
    	stop = System.nanoTime();
    	System.out.print("\tRuntime: " + (stop-start) + " " + "nanosec.\n");
    	
    	System.out.print("Preorder traverse:\t");
    	testAVL.preorder();
    	System.out.print("\nPostorder traverse:\t");
    	testAVL.postorder();
    	
		

			
		
	}
    
}


