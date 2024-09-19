public class add {
    public ReallyLongInt2 add(ReallyLongInt2 rightOp)
	{
        // special case for multiply
        if(this.numberOfEntries == 0)
        {
            System.out.println("made it");
            return rightOp;
        }
        ReallyLongInt2 list = new ReallyLongInt2();

		int carry = 0;
		int value = 0;
		int counter = 1;
		int left = 0;
		int right = 0;
		boolean pass = false;
		A3LList<Integer>.Node currentLeft= this.firstNode.getPrevNode();
		A3LList<Integer>.Node currentRight= rightOp.firstNode.getPrevNode();
		StringBuilder sLeft = new StringBuilder();
		StringBuilder sRight = new StringBuilder();

        // adding both long ints together recursivley
        list = this.addRecur(list, rightOp, carry, value, counter, left, right, pass, currentLeft, currentRight, sLeft, sRight);
        return list;
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
            carry = 1;
            if(counter == rightOp.numberOfEntries)
            {
                left = 1;
            }
        }
        if(counter > rightOp.numberOfEntries && pass == true)
        {
            right = 0;
            carry = 1;
            if(counter == this.numberOfEntries)
            {
                right = 1;
            }
        }
        int num = left + right + carry;
        value = num%10;
        list.add(counter, value);
        if(num >= 10)
        {
            carry = 1;
        }
        else
        {
            carry = 0;
        }
        currentLeft = currentLeft.prev;
        currentRight = currentRight.prev;
        sLeft.setLength(0);
        sRight.setLength(0);
        pass = true;
        return addRecur(list, rightOp, carry, value, counter+1, left, right, pass, currentLeft, currentRight, sLeft, sRight);
    }
}
