package math;

/**
 * This class encapsulates a BigInteger, i.e. a positive or negative integer with 
 * any number of digits, which overcomes the computer storage length limitation of 
 * an integer.
 * 
 */
public class BigInteger {

	/**
	 * True if this is a negative integer
	 */
	boolean negative;
	
	/**
	 * Number of digits in this integer
	 */
	int numDigits;
	
	/**
	 * Reference to the first node of this integer's linked list representation
	 * NOTE: The linked list stores the Least Significant Digit in the FIRST node.
	 * For instance, the integer 235 would be stored as:
	 *    5 --> 3  --> 2
	 */
	DigitNode front;
	
	/**
	 * Initializes this integer to a positive number with zero digits, in other
	 * words this is the 0 (zero) valued integer.
	 */
	public BigInteger() {
		negative = false;
		numDigits = 0;
		front = null;
	}
	
	/**
	 * Parses an input integer string into a corresponding BigInteger instance.
	 * A correctly formatted integer would have an optional sign as the first 
	 * character (no sign means positive), and at least one digit character
	 * (including zero). 
	 * Examples of correct format, with corresponding values
	 *      Format     Value
	 *       +0            0
	 *       -0            0
	 *       +123        123
	 *       1023       1023
	 *       0012         12  
	 *       0             0
	 *       -123       -123
	 *       -001         -1
	 *       +000          0
	 *       
	 * 
	 * @param integer Integer string that is to be parsed
	 * @return BigInteger instance that stores the input integer
	 * @throws IllegalArgumentException If input is incorrectly formatted
	 */
	public static BigInteger parse(String integer) throws IllegalArgumentException {
		// THE FOLLOWING LINE IS A PLACEHOLDER SO THE PROGRAM COMPILES
		// YOU WILL NEED TO CHANGE IT TO RETURN THE APPROPRIATE BigInteger
		boolean deleteZeros = true;
		boolean negative = false;
		integer = integer.trim();
		String result = "";
		
		if (integer.length() == 0)
		{
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < integer.length(); i++)
		{
			if (!(Character.isDigit(integer.charAt(i))))
			{
				if (i != 0)
				{
					throw new IllegalArgumentException();
				}
				
				else
				{
					if(integer.charAt(i) == '-')
					{
						negative = true;
					}
					else if(integer.charAt(i) == '+')
					{
						negative = false;
					}
					else
					{
						throw new IllegalArgumentException();
					}
				}
			}
			
			else
			{
				if (deleteZeros)
				{
					if (integer.charAt(i) != '0')
					{
						deleteZeros = false;
						result = result + integer.substring(i, i+1);
					}
					else if ((integer.charAt(i)) == '0' && (i == integer.length()-1))
					{
						result = result + integer.substring(i, i+1);
					}
					
				}
				else
				{
					result = result + integer.substring(i, i+1);
				}
			}
		}
		BigInteger number = new BigInteger();
		if (negative)
		{
			if (result.equals("0"))
			{
				number.negative = false;
			}
			else
			{
				number.negative = true;
			}
			
		}
		number.numDigits = result.length();
		number.front = new DigitNode(Character.getNumericValue(result.charAt(0)), null); 
		 
		for (int i = 1; i < result.length(); i++)
		{
			DigitNode ptr = new DigitNode(Character.getNumericValue(result.charAt(i)), number.front);
			number.front = ptr;
		}
		return number;
	}
	
	/**
	 * Adds an integer to this integer, and returns the result in a NEW BigInteger object. 
	 * DOES NOT MODIFY this integer.
	 * NOTE that either or both of the integers involved could be negative.
	 * (Which means this method can effectively subtract as well.)
	 * 
	 * @param other Other integer to be added to this integer
	 * @return Result integer
	 */
	public BigInteger add(BigInteger other){
		// THE FOLLOWING LINE IS A PLACEHOLDER SO THE PROGRAM COMPILES
		// YOU WILL NEED TO CHANGE IT TO RETURN THE APPROPRIATE BigInteger
		BigInteger sum = new BigInteger();
		/*if (this.front == null || other.front == null)
		{
			throw new IllegalArgumentException();
		}*/
		if (((this.negative == false) && (other.negative == false)) || ((this.negative==true) && (other.negative == true)))
		{
			boolean carry = false;
			int s = 0;
			sum.negative = this.negative;
			DigitNode ptr = this.front;
			DigitNode ptr2 = other.front;
				
			while (!((ptr == null) && (ptr2 == null)))
			{
				if (ptr != null && ptr2 != null)
				{
					if (carry)
					{
						s = ptr.digit + ptr2.digit + 1;
					}
					else
					{
						s = ptr.digit + ptr2.digit;
					}
				}
					
				else if (ptr == null && ptr2 != null)
				{
					if (carry)
					{
						s = ptr2.digit + 1;
					}
					else
					{
						s = ptr2.digit;
					}
				}
				
				else if (ptr != null && ptr2 == null)
				{
					if (carry)
					{
						s = ptr.digit + 1;
					}
					else
					{
						s = ptr.digit;
					}
				}
					
				if (s > 9)
				{
					carry = true;
					s = s - 10;
				}
				else
				{
					carry = false;
				}
					
				if (sum.front == null)
				{
					sum.front = new DigitNode(s, null);
				}
					
				else
				{
					for (DigitNode ptr3 = sum.front; ptr3 != null; ptr3 = ptr3.next)
					{
						if (ptr3.next == null)
						{
							ptr3.next = new DigitNode(s, null);
							break;
						}
					}
				}
				if (ptr != null)
				{
					ptr = ptr.next;
				}
				else
				{
					ptr = null;
				}
				if (ptr2 != null)
				{
					ptr2 = ptr2.next;
				}
				else
				{
					ptr2 = null;
				}
				
				if ((ptr == null) && (ptr2 == null))
				{
					if (carry)
					{
						for (DigitNode ptr3 = sum.front; ptr3 != null; ptr3 = ptr3.next)
						{
							if (ptr3.next == null)
							{
								ptr3.next = new DigitNode(1, null);
								break;
							}
						}
					}
				}
				
			}
			
		}
		
		else if (((this.negative == true) && (other.negative == false)) || ((this.negative == false) && (other.negative == true)))
		{
			DigitNode ptr = this.front;
			DigitNode ptr2 = other.front;
			boolean isGreater = true;
			int s = 0;
			while (!((ptr == null) && (ptr2 == null)))
			{
				if ((ptr != null) && (ptr2 != null))
				{
					if (ptr.digit > ptr2.digit)
					{
						isGreater = true;
					}
					else if (ptr.digit < ptr2.digit)
					{
						isGreater = false;
					}
				}
				else if ((ptr == null) && (ptr2 != null))
				{
					isGreater = false;
					break;
				}
				else if ((ptr != null) && (ptr2 == null))
				{
					isGreater = true;
					break;
				}
				
				if (ptr != null)
				{
					ptr = ptr.next;
				}
				if (ptr2 != null)
				{
					ptr2 = ptr2.next;
				}
			}
			
			if (isGreater)
			{
				ptr = this.front;
				ptr2 = other.front;
				sum.negative = this.negative;
			}
			else
			{
				ptr = other.front;
				ptr2 = this.front;
				sum.negative = other.negative;
			}
			
			while (!((ptr == null) && (ptr2 == null)))
			{
				if ((ptr != null) && (ptr2 != null))
				{
					if (ptr.digit >= ptr2.digit)
					{
						s = ptr.digit - ptr2.digit;
					}
					
					else
					{
						DigitNode borrow = ptr.next;
						while (borrow != null)
						{
							if (borrow.digit > 0)
							{
								borrow.digit = borrow.digit - 1;
								break;
							}
							else
							{
								borrow.digit = 9;
								borrow = borrow.next;
							}
						}
						s = ptr.digit + 10 - ptr2.digit;
					}
				}
				
				else if ((ptr != null) && (ptr2 == null))
				{
					s = ptr.digit;
				}
				
				if (sum.front == null)
				{
					sum.front = new DigitNode(s, null);
				}
				else
				{
					for (DigitNode ptr3 = sum.front; ptr3 != null; ptr3 = ptr3.next)
					{
						if (ptr3.next == null)
						{
							ptr3.next = new DigitNode(s, null);
							break;
						}
					}
				}
				if (ptr != null)
				{
					ptr = ptr.next;
				}
				if (ptr2 != null)
				{
					ptr2 = ptr2.next;
				}
			}
		}
		sum = sum.parse(sum.toString());
		return sum;
	}
	
	/**
	 * Returns the BigInteger obtained by multiplying the given BigInteger
	 * with this BigInteger - DOES NOT MODIFY this BigInteger
	 * 
	 * @param other BigInteger to be multiplied
	 * @return A new BigInteger which is the product of this BigInteger and other.
	 */
	public BigInteger multiply(BigInteger other) {
		// THE FOLLOWING LINE IS A PLACEHOLDER SO THE PROGRAM COMPILES
		// YOU WILL NEED TO CHANGE IT TO RETURN THE APPROPRIATE BigInteger
		BigInteger product = new BigInteger();
		product.front = new DigitNode(0, null);
		BigInteger row;
		DigitNode ptr = this.front;
		DigitNode ptr2 = other.front;
		int p = 0;
		int count = 0;
		boolean carry = false;
		boolean ptrthis = true;
		int add = 0;
	
		if (this.numDigits >= other.numDigits)
		{
			ptr = this.front;
			ptr2 = other.front;
			ptrthis = true;
		}
		else
		{
			ptr = other.front;
			ptr2 = this.front;
			ptrthis = false;
		}
		
		while (ptr2 != null)
		{
			carry = false;
			add = 0;
			row = new BigInteger();
			int numberOfZeros = count;
			
			
			if (numberOfZeros > 0)
			{
				if (row.front == null)
				{
					row.front = new DigitNode(0, null);
				}
				while (numberOfZeros > 1)
				{
					for (DigitNode ptr4 = row.front; ptr4 != null; ptr4 = ptr4.next)
					{
						if (ptr4.next == null)
						{
							ptr4.next = new DigitNode(0, null);
							break;
						}
					}
					numberOfZeros = numberOfZeros - 1;
				}
			}
			/*if (count > 0)
			{
				row.front = new DigitNode(0, null);
				if (count > 1)
				{
					ptr4 = row.front.next;
					for (int i = count; i > 1; i--)
					{
						ptr4 = new DigitNode(0, null);
						ptr4 = ptr4.next;
					}
				}
			}*/
			while (ptr != null)
			{
				if (carry)
				{
					p = (ptr.digit * ptr2.digit) + add;
				}
				else
				{
					p = ptr.digit * ptr2.digit;
				}
				if (p >= 10)
				{
					add = (p) / 10;
					p = p % 10;
					carry = true;
				}
				else
				{
					carry = false;
					add = 0;
				}
				
				if (row.front == null)
				{
					row.front = new DigitNode(p, null);
				}
				else
				{
					for (DigitNode ptr3 = row.front; ptr3 != null; ptr3 = ptr3.next)
					{
						if (ptr3.next == null)
						{
							ptr3.next = new DigitNode(p, null);
							break;
						}
					}
				}
				ptr = ptr.next;
				
				if ((ptr == null))
				{
					if (carry)
					{
						for (DigitNode ptr3 = row.front; ptr3 != null; ptr3 = ptr3.next)
						{
							if (ptr3.next == null)
							{
								ptr3.next = new DigitNode(add, null);
								break;
							}
						}
					}
				
				}
			}
			product = product.add(row);
			ptr2 = ptr2.next;
			if (ptrthis)
			{
				ptr = this.front;
			}
			else
			{
				ptr = other.front;
			}
			count++;
		}
		if (((this.negative == false) && (other.negative == false)) || ((this.negative == true) && (other.negative == true)))
		{
			product.negative = false;
		}
		else if (((this.negative == true) && (other.negative == false)) || ((this.negative == false) && (other.negative == true)))
		{
			product.negative = true;
		}
		product = product.parse(product.toString());
		return product;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (front == null) {
			return "0";
		}
		
		String retval = front.digit + "";
		for (DigitNode curr = front.next; curr != null; curr = curr.next) {
				retval = curr.digit + retval;
		}
		
		if (negative) {
			retval = '-' + retval;
		}
		
		return retval;
	}
	
}
