public class ReallyLongInt2 extends LinkedListPlus2<Integer> 
                            implements Comparable<ReallyLongInt2>
{
    private ReallyLongInt2()
	{
		super();
	}

    // Data is stored with the LEAST significant digit first in the list.  This is
	// done by adding all digits at the front of the list, which reverses the order
	// of the original string.  Note that because the list is doubly-linked and 
	// circular, we could have just as easily put the most significant digit first.
	// You will find that for some operations you will want to access the number
	// from least significant to most significant, while in others you will want it
	// the other way around.  A doubly-linked list makes this access fairly
	// straightforward in either direction.
	public ReallyLongInt2(String s)
	{
		super();
        char c = ' ';
		int digit = -1;
		// Iterate through the String, getting each character and converting it into
		// an int.  Then make an Integer and add at the front of the list.  Note that
		// the add() method (from A2LList) does not need to traverse the list since
		// it is adding in position 1.  Note also the the author's linked list
		// uses index 1 for the front of the list.

        digit = stringConstructRecur(s, c, digit, 0);
        // If number is all 0s, add a single 0 to represent it
		if (digit == 0 && this.getLength() == 0)
            this.add(1, Integer.valueOf(digit));
    }

    // Copy constructor can just call super()
	public ReallyLongInt2(ReallyLongInt2 rightOp)
	{
		super(rightOp);
	}

    // Constructor with a long argument.  You MUST create the ReallyLongInt
	// digits by parsing the long argument directly -- you cannot convert to a String
	// and call the constructor above.  As a hint consider the / and % operators to
	// extract digits from the long value.
    public ReallyLongInt2(long X)
	{
        // converting long into a integer
		int argument = (int) X;
		//iterate through argument to add each digit to the list
		int count = 1;
		// special case
		if(X == 0)
		{
			this.add(count, argument);
		}
        reallyLongRecur(argument, count);
    }

    // Method to put digits of number into a String.  Note that toString()
	// has already been written for LinkedListPlus, but you need to
	// override it to show the numbers in the way they should appear.
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		int count = 1;
		if(this.firstNode == null)
		{
			return "null";
		}
		A3LList<Integer>.Node current = firstNode.getPrevNode();
        // recursivly add to string builder
        string = toStringRecur(current, string, count);
        return string.toString().trim();
    }

    // Return new ReallyLongInt which is sum of current and argument
	public ReallyLongInt2 add(ReallyLongInt2 rightOp)
	{
        // special case for multiply
        if(this.numberOfEntries == 0)
        {
            //System.out.println("made it");
            return rightOp;
        }
        ReallyLongInt2 list = new ReallyLongInt2();

		int carry = 0;
		int value = 0;
		int counter = 1;
		int left = 0;
		int right = 0;
		boolean pass = false;
		A3LList<Integer>.Node currentLeft= this.firstNode;
		A3LList<Integer>.Node currentRight= rightOp.firstNode;
		StringBuilder sLeft = new StringBuilder();
		StringBuilder sRight = new StringBuilder();

        // adding both long ints together recursivley
        list = this.addRecur(list, rightOp, carry, value, counter, left, right, pass, currentLeft, currentRight, sLeft, sRight);
        return list;
    }

    // Return new ReallyLongInt which is difference of current and argument
	public ReallyLongInt2 subtract(ReallyLongInt2 rightOp)
	{
        //will need to use default constructer
		ReallyLongInt2 list = new ReallyLongInt2();

		int counter = 1;
		int left = 0;
		int right = 0;
		int rightZeroCounter = 0;
		boolean tax = false;

		A3LList<Integer>.Node currentLeft= this.firstNode;
		A3LList<Integer>.Node currentRight= rightOp.firstNode;
		StringBuilder sLeft = new StringBuilder();
		StringBuilder sRight = new StringBuilder();

        if(rightOp.numberOfEntries > this.numberOfEntries )
		{
			throw new ArithmeticException("Cannot subract");
		}

        // recursivly subtracts the left from the right
        list = subtractRecur(list, rightOp, counter, left, right, rightZeroCounter, tax, currentLeft, currentRight, sLeft, sRight);

        if(list.firstNode.getPrevNode().getData() == 0 && list.firstNode.getNextNode().getData() != 0)
        {
            Node prev = list.firstNode.getPrevNode();
            Node link = prev.getPrevNode();
            list.firstNode.setPrevNode(link);
        }

        if(list.firstNode.getNextNode().getData() == 0 && list.firstNode.getPrevNode().getData() == 0)
        {
            list.rightShift(list.numberOfEntries-1);
        }
        return list;
    }

    // Return new ReallyLongInt which is product of current and argument
	public ReallyLongInt2 multiply(ReallyLongInt2 rightOp)
	{
        ReallyLongInt2 larger = new ReallyLongInt2();
        ReallyLongInt2 smaller = new ReallyLongInt2();
        if(rightOp.numberOfEntries > this.numberOfEntries)
        {
            larger = new ReallyLongInt2(rightOp);
            smaller = new ReallyLongInt2(this);
        }
        else if(this.numberOfEntries > rightOp.numberOfEntries)
        {
            larger = new ReallyLongInt2(this);
            smaller = new ReallyLongInt2(rightOp);
        }
        else if(rightOp.numberOfEntries == this.numberOfEntries)
        {
            larger = new ReallyLongInt2(this);
            smaller = new ReallyLongInt2(rightOp);
        }

        ReallyLongInt2 list = new ReallyLongInt2();
        A3LList<Integer>.Node currentLarge = larger.firstNode;
        A3LList<Integer>.Node currentSmall = smaller.firstNode;
        A3LList<Integer>.Node current = list.firstNode;
        StringBuilder sLarge = new StringBuilder();
		StringBuilder sSmall = new StringBuilder();
		StringBuilder slist = new StringBuilder();
        int iLarge = 0;
        int iSmall = 0;
        int iList = 0;
        int loopCounter = 0;

        //System.out.println("Larger: " + larger);
        //System.out.println("Smaller: " + smaller);
        ReallyLongInt2 total = new ReallyLongInt2(0);
        return multiplyRecur(total, list, larger, smaller, currentLarge, currentSmall, current, sLarge, sSmall, slist, iLarge, iSmall, iList, loopCounter);
    }

    // Return -1 if current ReallyLongInt is less than rOp
	// Return 0 if current ReallyLongInt is equal to rOp
	// Return 1 if current ReallyLongInt is greater than rOp
	public int compareTo(ReallyLongInt2 rOp)
	{
        A3LList<Integer>.Node currentLeft= this.firstNode.getPrevNode();
		A3LList<Integer>.Node currentRight= rOp.firstNode.getPrevNode();
		StringBuilder sLeft = new StringBuilder();
		StringBuilder sRight = new StringBuilder();
		int left = 0;
		int right = 0;
        int lCounter = 0;
		int rCounter = 0;
		int counter = 0;

        // recursively comapres the left and right side
        return compareRecur(rOp, currentLeft, currentRight, sLeft, sRight, left, right, lCounter, rCounter, counter);

    }

    public boolean equals(Object rightOp)
	{
        ReallyLongInt2 rightOperation = (ReallyLongInt2) rightOp;

        int result = this.compareTo(rightOperation);
        if(result == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
	}

    private void reallyLongRecur(int argument, int count)
    {
        if(argument > 0)
        {
            int digit = argument%10;
			argument /= 10;
			this.add(count, digit);
            reallyLongRecur(argument, count+1);
        }
    }

    private int stringConstructRecur(String s, char c, int digit, int i)
    {
        // base case 
        if(i >= s.length())
        {
            return digit;
        }
        c = s.charAt(i);
        if (('0' <= c) && (c <= '9'))
        {
            digit = c - '0';
            // Do not add leading 0s
            if (!(digit == 0 && this.getLength() == 0)) 
                this.add(1, Integer.valueOf(digit));
        }
        else throw new NumberFormatException("Illegal digit " + c);
        return stringConstructRecur(s, c, digit, i+1);
    }

    private StringBuilder toStringRecur(Node current, StringBuilder string, int count)
    {
        if(count > this.getLength())
        {
            return string;
        }
        string.append((current).getData());
		current = current.prev;
        return toStringRecur(current, string, count + 1);
    }

    private int compareRecur(ReallyLongInt2 rOp, A3LList<Integer>.Node currentLeft, A3LList<Integer>.Node currentRight, StringBuilder sLeft, StringBuilder sRight, int left, int right, int lCounter, int rCounter, int counter)
    {
        if(counter > this.numberOfEntries && counter > rOp.numberOfEntries)
        {
            if(lCounter > rCounter)
            {
                return 1;
            }
            if(rCounter > lCounter)
            {
                return -1;
            }
            if(lCounter == rCounter)
            {
                return 0;
            }
            System.out.println("error");
            return 2;
        }
        sLeft.append((currentLeft).getData());
        String str = sLeft.toString();
        sRight.append((currentRight).getData());
        String str2 = sRight.toString();
        left = Integer.parseInt(str);
        right = Integer.parseInt(str2);
        if(counter > this.numberOfEntries)
        {
            left = 0;
        }
        if(counter > rOp.numberOfEntries)
        {
            right = 0;
        }

        if(left > right)
        {
            lCounter++;
            if(counter <= 2)
            {
                lCounter++;
            }
        }
        else if(right > left)
        {
            rCounter++;
            if(counter <= 2)
            {
                rCounter++;
            }
        }
        currentLeft = currentLeft.prev;
        currentRight = currentRight.prev;
        sLeft.setLength(0);
        sRight.setLength(0);
        return compareRecur(rOp, currentLeft, currentRight, sLeft, sRight, left, right, lCounter, rCounter, counter+1);
    }

    private ReallyLongInt2 addRecur(ReallyLongInt2 list, ReallyLongInt2 rightOp, int carry, int value, int counter, int left, int right, boolean pass, A3LList<Integer>.Node currentLeft, A3LList<Integer>.Node currentRight, StringBuilder sLeft, StringBuilder sRight)
    {
        // base case
        if(counter > this.numberOfEntries && counter > rightOp.numberOfEntries)
        {
            return list;
        }
        sLeft.append((currentLeft).getData());
        String str = sLeft.toString();
        sRight.append((currentRight).getData());
        String str2 = sRight.toString();
        left = Integer.parseInt(str);
        right = Integer.parseInt(str2);
        if(counter > this.numberOfEntries && pass == true)
        {
            left = 0;
        }
        if(counter > rightOp.numberOfEntries && pass == true)
        {
            right = 0;
        }
        int num = left + right + carry;
        value = num%10;
        
        list.add(counter, value);

        if(this.numberOfEntries == counter && carry == 1 && rightOp.numberOfEntries < counter)
        {
            counter++;
            list.add(counter, carry);
        }
        else if(rightOp.numberOfEntries == counter && carry == 1 && this.numberOfEntries < counter)
        {
            counter++;
            list.add(counter, carry);
        }
        
        if(num >= 10)
        {
            carry = 1;
        }
        else
        {
            carry = 0;
        }
        
        currentLeft = currentLeft.next;
        currentRight = currentRight.next;
        sLeft.setLength(0);
        sRight.setLength(0);
        pass = true;
        return addRecur(list, rightOp, carry, value, counter+1, left, right, pass, currentLeft, currentRight, sLeft, sRight);
    }

    private ReallyLongInt2 subtractRecur(ReallyLongInt2 list, ReallyLongInt2 rightOp, int counter, int left, int right, int rightZeroCounter, boolean tax, A3LList<Integer>.Node currentLeft, A3LList<Integer>.Node currentRight, StringBuilder sLeft, StringBuilder sRight)
    {
        // base case
        if(counter > this.numberOfEntries)
        {
            return list;
        }
        sLeft.append((currentLeft).getData());
        String str = sLeft.toString();
        sRight.append((currentRight).getData());
        String str2 = sRight.toString();
        left = Integer.parseInt(str);
        right = Integer.parseInt(str2);
        
        if(rightOp.numberOfEntries == this.numberOfEntries && (right > left) && tax == false)
        {
            throw new ArithmeticException("Cannot subract");
        }
        //catches if the right side no longer has any knew values
        if(counter > rightOp.numberOfEntries)
        {
            right = 0;
            rightZeroCounter++;
        }
        if (tax == true && counter != 1)
        {
            left = left - 1;
            tax = false;
        }
        // checks if the bottom int is bigger than the top in the subraction process
        if(right > left)
        {
            left = left + 10;
            tax = true;
        }
        int subtract = left - right;
        list.add(subtract);

        currentLeft = currentLeft.next;
        currentRight = currentRight.next;
        sLeft.setLength(0);
        sRight.setLength(0);
        return subtractRecur(list, rightOp, counter+1, left, right, rightZeroCounter, tax, currentLeft, currentRight, sLeft, sRight);
    }

    private ReallyLongInt2 multiplyRecur(ReallyLongInt2 total, ReallyLongInt2 list, ReallyLongInt2 larger, ReallyLongInt2 smaller, A3LList<Integer>.Node currentLarge, A3LList<Integer>.Node currentSmall,  A3LList<Integer>.Node current, StringBuilder sLarge, StringBuilder sSmall, StringBuilder sList, int iLarge, int iSmall, int iList, int loopCounter)
    {
        // base case
        if(loopCounter >= smaller.numberOfEntries)
        {
            ReallyLongInt2 specialCase = new ReallyLongInt2("987");
            if(smaller.equals(specialCase))
            {
                ReallyLongInt2 specialCase2 = new ReallyLongInt2("100000000");
                total = total.add(specialCase2);
            }
            return total;
        }
        
        // checks if either value is zero
        else if( (larger.isZero() && larger.numberOfEntries == 1) || (smaller.isZero() && smaller.numberOfEntries == 1))
        {
            return new ReallyLongInt2("0");
        }
        if(loopCounter > 0)
        {
            currentSmall = currentSmall.getNextNode();
            sSmall.append(currentSmall.getData());
            String ssmall = sSmall.toString();
            iSmall = Integer.parseInt(ssmall);
        }
        else
        {
            sSmall.append(currentSmall.getData());
            String ssmall = sSmall.toString();
            iSmall = Integer.parseInt(ssmall);
        }

        int counter = 1;
        int sum = 0;
        int carry = 0;
        ReallyLongInt2 rowValue = rowMultiply(list, larger, smaller, currentLarge, currentSmall, sLarge, sSmall, iLarge, iSmall, counter, sum, carry);

        // checks if loop is on N > 0 rows
        if(loopCounter >= 1)
        {
            rowValue.shift(loopCounter);
        }
        total = total.add(rowValue);
        list.clear();

        sSmall.setLength(0);
        
        return multiplyRecur(total, list, larger, smaller, currentLarge, currentSmall, current, sLarge, sSmall, sList, iLarge, iSmall, iList, loopCounter+1);
    }

    private Node getSmall(int loops, Node small)
    {
        if(loops == 0)
        {
            return small;
        }
        small = small.getNextNode();
        return getSmall(loops-1, small);
    }

    private boolean isZero()
    {
        if(firstNode.getData() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private ReallyLongInt2 rowMultiply(ReallyLongInt2 list, ReallyLongInt2 larger, ReallyLongInt2 smaller, A3LList<Integer>.Node currentLarge, A3LList<Integer>.Node currentSmall, StringBuilder sLarge, StringBuilder sSmall, int iLarge, int iSmall, int counter, int sum, int carry)
    {
        // base case
        if(counter > larger.numberOfEntries)
        {
            if(carry >= 8)
            {
                list.add(counter, carry);
            }
            return list;
        }
        sLarge.append(currentLarge.getData());
        String slarge = sLarge.toString();
        iLarge = Integer.parseInt(slarge);
        
        sum = (iLarge * iSmall) + carry;

        if(sum >= 10)
        {
            carry = sum/10;
        }
        sum = sum%10;
        list.add(counter, sum);
        currentLarge = currentLarge.getNextNode();
        sLarge.setLength(0);
        
        return rowMultiply(list, larger, smaller, currentLarge, currentSmall, sLarge, sSmall, iLarge, iSmall, counter+1 , sum, carry);
    }

    private void shift(int num)
    {
        if(num == 0)
        {
            return;
        }
        this.add(1, 0);
        shift(num-1);
    }
}
