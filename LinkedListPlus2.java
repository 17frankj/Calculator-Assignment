public class LinkedListPlus2<T> extends A3LList<T>
{
    // Default constructor simply calls super()
	public LinkedListPlus2()
	{
		super();
	}

    public LinkedListPlus2(LinkedListPlus2<T> oldList)
	{
        super();
        if (oldList.getLength() > 0)
		{
            // Special case for first Node since we need to set the
			// firstNode instance variable.
			Node temp = oldList.firstNode;
			Node newNode = new Node(temp.data);
			firstNode = newNode;

            // Now we traverse the old list, appending a new Node with
			// the correct data to the end of the new list for each Node
			// in the old list.  Note how the loop is done and how the
			// Nodes are linked.
			Node currNode = firstNode;
			temp = temp.next;
            int count = 1;
            // recursivly copy
            currNode = copyRecurLLP2(oldList, newNode, temp, currNode, count);

            currNode.next = firstNode;  // currNode is now at the end of the list.
			firstNode.prev = currNode;	// link to make the list circular
			numberOfEntries = oldList.numberOfEntries;
        }
    }

    public String toString()
	{
		StringBuilder b = new StringBuilder();
		Node curr = firstNode;
		int i = 0;
        // recursivily 
        toStringRecurs(b, curr, i);
        return b.toString();
    }

    // Remove num items from the front of the list
	// Note: This code was developed / discussed in In-Class Exercise 3 during
	// Lecture 10.  See solution from that exercise.
	public void leftShift(int num)
	{
        if (num <= 0)
			return;
		else if (num >= numberOfEntries)
		{
			firstNode = null;
			numberOfEntries = 0;
		}
        else
		{
			Node lastNode = firstNode.getPrevNode();
            firstNode = forFRecur(1, num, firstNode);
            firstNode.setPrevNode(lastNode);
			lastNode.setNextNode(firstNode);
			numberOfEntries -= num;
        }
    }

    // Remove num items from the end of the list
	public void rightShift(int num)
	{
        if (num <= 0)
			return;
		else if (num >= numberOfEntries)
		{
			firstNode = null;
			numberOfEntries = 0;
		}
		else
		{
			Node lastNode = firstNode.getPrevNode();
            lastNode = forLRecur(1, num, lastNode);
            firstNode.setPrevNode(lastNode);
			lastNode.setNextNode(firstNode);
			numberOfEntries -= num;
        }
    }

    // Rotate to the left num locations in the list.  No Nodes
	// should be created or destroyed.
	public void leftRotate(int num)
	{
        //special case
        if(num < 0)
        {
            num = Math.abs(num);
            rightRotate(num);
            return;
        }

        if(num ==0 || firstNode == null)
        {
            return;
        }

        // Move num positions forward
        firstNode = forFRecur(1, num, firstNode);
	}

    // Rotate to the right num locations in the list.  No Nodes
	// should be created or destroyed.
	public void rightRotate(int num)
	{
        //special case
        if(num < 0)
        {
            num = Math.abs(num);
            leftRotate(num);
            return;
        }
        if(num ==0 || firstNode == null)
        {
            return;
        }

        firstNode = forLRecur(1, num, firstNode);
	}	

    
    private Node forLRecur(int i, int num, Node node)
    {
        if(i <= num)
        {
            node = node.getPrevNode();
            return forLRecur(i+1, num, node);
        }
        return node;
    }
    private Node forFRecur(int i, int num, Node node)
    {
        if(i <= num)
        {
            node = node.getNextNode();
            return forFRecur(i+1, num, node);
        }
        return node;
    }

    private StringBuilder toStringRecurs(StringBuilder b, Node curr, int i)
    {
        if(i >= this.getLength())
        {
            return b;
        }
        b.append(curr.data.toString());
		b.append(" ");
        return toStringRecurs(b, curr.next, i+1);
    }

    private Node copyRecurLLP2(LinkedListPlus2<T> oldList, Node newNode, Node temp, Node currNode, int count)
    {
        // base case
        if(count < oldList.getLength())
        {
            newNode = new Node(temp.data);
            currNode.next = newNode;
            newNode.prev = currNode;
            temp = temp.next;
			currNode = currNode.next;

            return copyRecurLLP2(oldList, newNode, temp, currNode, count+1);
        }
        return currNode;
    }
}
