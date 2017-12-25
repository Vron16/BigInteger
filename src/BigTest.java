package math;

import java.io.IOException;
import java.util.Scanner;

public class BigTest {

	static Scanner sc;
	
	public static void parse() 
	throws IOException {
		System.out.print("\tEnter integer => ");
		String integer = sc.nextLine();
		try {
			BigInteger bigInteger = BigInteger.parse(integer);
			System.out.println("\t\tValue = " + bigInteger);
		} catch (IllegalArgumentException e) {
			System.out.println("\t\tIncorrect Format");
		}
	}
	
	public static void add() 
	throws IOException {
		System.out.print("\tEnter first integer => ");
		String integer = sc.nextLine();
		BigInteger firstBigInteger = BigInteger.parse(integer);
		
		System.out.print("\tEnter second integer => ");
		integer = sc.nextLine();
		BigInteger secondBigInteger = BigInteger.parse(integer);
		
		System.out.println("\t\tSum: " + firstBigInteger.add(secondBigInteger));
		/*System.out.println(firstBigInteger.numDigits);
		System.out.println(secondBigInteger.numDigits);*/
	}
	
	public static void multiply() 
	throws IOException {
		System.out.print("\tEnter first integer => ");
		String integer = sc.nextLine();
		BigInteger firstBigInteger = BigInteger.parse(integer);
		
		System.out.print("\tEnter second integer => ");
		integer = sc.nextLine();
		BigInteger secondBigInteger = BigInteger.parse(integer);
		
		System.out.println("\t\tProduct: " + firstBigInteger.multiply(secondBigInteger));
		
	}
	
	public static void main(String[] args) 
	throws IOException {
		
		// TODO Auto-generated method stub
		sc = new Scanner(System.in);
		BigInteger test = new BigInteger();
		BigInteger test2 = new BigInteger();
		DigitNode ptr = test.front;
		test.front = new DigitNode(6, null);
		test.front.next = new DigitNode(1, null);
		test.front.next.next = new DigitNode(3, null);
		test2.front = new DigitNode(3, null);
		DigitNode ptr2 = new DigitNode(1, test2.front);
		test2.front = ptr2;
		DigitNode ptr3 = new DigitNode (7, test2.front);
		test2.front = ptr3;
		System.out.print(test.add(test2));
		
		
		
		char choice;
		while ((choice = getChoice()) != 'q') {
			switch (choice) {
				case 'p' : parse(); break;
				case 'a' : add(); break;
				case 'm' : multiply(); break;
				default: System.out.println("Incorrect choice"); 
			}
		}
	}

	private static char getChoice() {
		System.out.print("\n(p)arse, (a)dd, (m)ultiply, or (q)uit? => ");
		String in = sc.nextLine();
		char choice;
		if (in == null || in.length() == 0) {
			choice = ' ';
		} else {
			choice = in.toLowerCase().charAt(0);
		}
		return choice;
	}

}
