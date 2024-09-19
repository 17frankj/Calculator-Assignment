// CS 0445 Spring 2024
// This code is a modified version of A2LList from Assignment 2.  In this version
// there is no iteration -- all methods are done via recursive calls.  A careful
// examination of the code reveals that in order to do this only 2 recursive methods
// are needed: a recursive version of contains() and a recursive version of
// getNodeAt().  Note that most of the methods requiring a traversal of the list
// utilize the getNodeAt() method. Since that method is recursive, methods calling
// it also utilize recursion.  See comments below on contains() and getNodeAt().

// NOTE: The getNodeAt() method is STILL private and thus you may NOT call this 
// method in your subclasses.  See more details on implementation restrictions
// in the Assignment 3 document.

// Note that this list is circular and NOT null-terminated.  This causes us to
// re-think many of the methods -- especially those that iterate through the list.

/**
   A linked implementation of the ADT list.
   @author Frank M. Carrano
*/
public class A3LList<T> 
{
	protected Node firstNode;     // reference to first node
	protected int numberOfEntries; 

	public A3LList()
	{
		clear();
	} // end default constructor
	
	public final void clear() // note the final method
	{
		firstNode = null;
		numberOfEntries = 0;
	} // end clear
	
	public int getLength()
	{
		return numberOfEntries;
	}
	
	public void add(T newEntry)  // add at end of list
	{
   		Node newNode = new Node(newEntry);
		
		// Special case for empty list -- node points to itself
		// in both directions.
   		if (isEmpty())
   		{
      		firstNode = newNode;
      		firstNode.setPrevNode(firstNode);
      		firstNode.setNextNode(firstNode);
      	}
   		else	// add to end of non-empty list.  Note that because
				// it is doubly-linked and circular, we can get to
				// the end Node easily.
   		{
      		Node lastNode = firstNode.getPrevNode();
      		lastNode.setNextNode(newNode); 
      		newNode.setPrevNode(lastNode);
      		newNode.setNextNode(firstNode);
      		firstNode.setPrevNode(newNode);
   		} // end if	
   
   		numberOfEntries++;
	}  // end add
	
	// Because we are using a circular, doubly linked list, we can easily
  	// add to the front or back of the list without traversing.  These
  	// special cases are handled below.
	public boolean add(int newPosition, T newEntry)	                                                 
	{
   		boolean isSuccessful = true;

   		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) 
   		{	
   			if (newPosition == numberOfEntries + 1) // add at back
      			add(newEntry);   // Call other version (it does not
      					         // have to iterate due to circular list)
      		else  // add anywhere else
      		{
      			Node newNode = new Node(newEntry);

				// Special case for empty list -- node points to itself
				// in both directions.  Note: This case should not actually
				// occur since adding to an empty list would necessarily be
				// at the back and covered by the case above. However, it is
				// included for logical completeness.
   				if (isEmpty())
   				{
      				firstNode = newNode;
      				firstNode.setPrevNode(firstNode);
      				firstNode.setNextNode(firstNode);
      			}
      			else if (newPosition == 1)   // Special case for adding at front
      			{
      				Node lastNode = firstNode.getPrevNode();
         			newNode.setNextNode(firstNode);
         			firstNode.setPrevNode(newNode);
         			newNode.setPrevNode(lastNode);
         			lastNode.setNextNode(newNode);				
         			firstNode = newNode;
      			}
       			else	// Adding in the middle -- must iterate here					
      			{                                
         			Node nodeBefore = getNodeAt(newPosition - 1);
         			Node nodeAfter = nodeBefore.getNextNode();
         			newNode.setNextNode(nodeAfter);
         			nodeBefore.setNextNode(newNode);
         			nodeAfter.setPrevNode(newNode);
         			newNode.setPrevNode(nodeBefore);
      			} // end if	
   
      			numberOfEntries++;
      		}
   		}
   		else
      		isSuccessful = false;
      
   		return isSuccessful;
	} // end add
	
	public boolean isEmpty()
	{
   		boolean result;
      
   		if (numberOfEntries == 0) // or getLength() == 0
   		{
      		result = true;
   		}
   		else
   		{
      		result = false;
   		} // end if
      
   		return result;
	} // end isEmpty
  
  	// Because we are using a circular, doubly linked list, we can easily
  	// remove from the front or back of the list without traversing.  These
  	// special cases are handled below.
	public T remove(int givenPosition)
	{
   		T result = null;                           // return value

   		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
   		{
			if (numberOfEntries == 1)	// case 1: remove only entry -- this is
			{							// special because of the circular list.
				result = firstNode.getData();
				firstNode = null;
			}	
      		else if (givenPosition == 1)             // case 2: remove first entry
      		{
         		result = firstNode.getData();        // save entry to be removed 
         		Node lastNode = firstNode.getPrevNode();
         		firstNode = firstNode.getNextNode(); // move firstNode down
         		lastNode.setNextNode(firstNode);	//  reset neighbors
         		firstNode.setPrevNode(lastNode);
      		}
      		else if (givenPosition == numberOfEntries)  // case 3: remove last
      		{
      			Node lastNode = firstNode.getPrevNode();
      			result = lastNode.getData();
      			Node nodeBefore = lastNode.getPrevNode();
      			nodeBefore.setNextNode(firstNode);
      			firstNode.setPrevNode(nodeBefore);		
      		}
      		else                                    // case 4: not first
      		{  										// or last entry
         		Node nodeBefore = getNodeAt(givenPosition - 1);
         		Node nodeToRemove = nodeBefore.getNextNode();
         		Node nodeAfter = nodeToRemove.getNextNode();
         		nodeBefore.setNextNode(nodeAfter);
         		nodeAfter.setPrevNode(nodeBefore);  
         		result = nodeToRemove.getData();    // save entry to be removed
      		} // end if

      		numberOfEntries--;
   		} // end if

   		return result;                            // return removed entry, or 
                                             // null if operation fails
	} // end remove

	public boolean replace(int givenPosition, T newEntry)
	{
   		boolean isSuccessful = true;

   		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
   		{   
      		assert !isEmpty();

      		Node desiredNode = getNodeAt(givenPosition);
      		desiredNode.setData(newEntry);
   		}    
   		else
      		isSuccessful = false;
      
   		return isSuccessful;
	} // end replace

// This method is intentionally commented out and you may not use it in your
// LinkedListPlus2 or ReallyLongInt2 classes.  However, you can uncomment it
// during development / for testing as long as the comments are back in your
// final submitted version.
/*
	public T getEntry(int givenPosition)
	{
   		T result = null;  // result to return
   
   		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
   		{
      		result = getNodeAt(givenPosition).getData();
   		} // end if
   
   		return result;
	} // end getEntry
*/
	
	// Instead of counting nodes to detect the end of the list, this method
	// takes the following approach:
	// 1) If the list is empty return false
	// 2) else if the item is found in the front return true
	// 3) else recursively search the rest of the list
	// Checking the first node prior to recursing allows us to determine the
	// end of the list by circling back to the front.  Note that this approach
	// is only possible because we have a circular list.
	public boolean contains(T anEntry)
	{
   		Node currentNode = firstNode;
   		if (currentNode == null)
   			return false;  // list is empty
   		else if (anEntry.equals(currentNode.data))
   			return true;   // anEntry matches first value
   		else
   			return containsRec(anEntry, currentNode.getNextNode());
   					// call recursive method to search rest of list
	} // end contains
	
	// Recursive method to search a list.
	private boolean containsRec(T anEntry, Node curr)
	{
		if (curr == firstNode)
			return false;  // back to beginning so not found
		else if (anEntry.equals(curr.data))
			return true;   // found it 
		else
			return containsRec(anEntry, curr.getNextNode());
	}						// recurse to next nodes

   // NOTE:
   // The method below is private and must remain private -- thus you cannot call
   // it from your subclasses.  You may NOT change this method from its private
   // status
   
   // Returns a reference to the node at a given position.
   // Precondition: List is not empty;
   //               1 <= givenPosition <= numberOfEntries	   
	private Node getNodeAt(int givenPosition)
	{
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		return getNodeRec(givenPosition, 1, currentNode);
      
	} // end getNodeAt
	
	private Node getNodeRec(int givenPosition, int currPosition, Node curr)
	{
		if (givenPosition == currPosition)  // reached correct position so return Node
			return curr;
		else				// move down to next position recursively
			return getNodeRec(givenPosition, currPosition+1, curr.getNextNode());
	}

	// Note that this class is protected so you can access it directly from
	// your LinkedListPlus and ReallyLongInt classes.  However, in case you
	// prefer using accessors and mutators, those are also provided here.
   protected class Node
   {
      protected T data; 	// entry in list
      protected Node next; 	// link to next node
      protected Node prev;  // link to prev node
      
      protected Node(T dataPortion)
      {
         this(dataPortion, null, null);
      } // end constructor
      
      protected Node(T dataPortion, Node nextNode, Node prevNode)
      {
         data = dataPortion;
         next = nextNode;
         prev = prevNode;
      } // end constructor
      
      protected T getData()
      {
         return data;
      } // end getData
      
      protected void setData(T newData)
      {
         data = newData;
      } // end setData
      
      protected Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      protected void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
      
      protected Node getPrevNode()
      {
      	return prev;
      }
      
      protected void setPrevNode(Node prevNode)
      {
      	prev = prevNode;
      }
   } // end Node
} // end A2LList