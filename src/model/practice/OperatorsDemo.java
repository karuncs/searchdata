package practice;

public class OperatorsDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		// ////////////////////////////////////////////////
		System.out.println("logical starts............");
		int result = 1 + 3;
		System.out.println(result);

		int result1 = result * 3;
		System.out.println(result1);

		int result2 = result1 / result;
		System.out.println(result2);

		// ////////////////////////////////////////////////
		System.out.println("unary operators............");
		result2++;
		System.out.println(result2);
		++result2;
		System.out.println(result2);
		result2++;
		System.out.println(result2);
		result2--;
		System.out.println(result2);
		int result3 = result2 % 4;
		System.out.println(result3);
		// ////////////////////////////////////////////////
		System.out.println("conditional starts.............");
		if (result > result1) {

			System.out.println(result);
		} else if (result == result1 && result < result1) {

			System.out.println(result1);
		} else if (result == result1 || result < result1) {

			System.out.println(result1);
		} else

			System.out.println("not true");
		// ////////////////////////////////////////
		System.out.println("if else starts here...");
		int passmarks = 65;
		char grade;

		if (passmarks >= 80) {
			grade = 'A';

		} else if (passmarks >= 70) {
			grade = 'B';

		} else if (passmarks >= 60) {
			grade = 'C';

		} else {
			grade = 'F';
		}

		System.out.println("obtain grade is: " + grade);
		// ////////////////////////////////////////////////////////
		System.out.println("swith statement starts here...");
		int personId = 32;
		switch (personId) {
		case 31:
			System.out.println("fred");
			break;
		case 32:
			System.out.println("karun");
			break;
		case 33:
			System.out.println("rong");
			break;
		case 34:
			System.out.println("ricardo");
			break;
		case 35:
			System.out.println("guandong");
			break;
		default:
			System.out.println("peter");
			break;
		}

		// ///////////////////////////

		int month = 1;
		int year = 2000;
		int numnerOfDays = 0;
		switch (month) {
		case 0:
		case 3:
		case 5:

		case 7:
		case 8:
		case 9:

		case 11:
			numnerOfDays = 31;
			break;

		case 4:
		case 6:
		case 10:
		case 2:
			numnerOfDays = 30;
			break;

		case 1:
			if ((year % 4 == 0) && !(year % 100 == 0) || (year % 400 == 0))
				numnerOfDays = 29;
			else
				numnerOfDays = 28;
			break;
		default:
			System.out.println("the month is invalid!!");
			break;

		}
		System.out.println("given month days are:" + numnerOfDays);

		// ///////////////////////////////////////////// int
		// array[]={'1',,2,2,4}
		System.out.println("do while goes here..");
		int i = 1;
		do {
			System.out.println("valaue of is:" + i);
			i++;

		} while (i <= 11);
		System.out.println("for loop goes here..");
		for (int j = 1; j < 11; j++) {
			System.out.println("Count is: " + j);
		}

		String[] names = { "rong", "xu", "peter", "fred", "karun", "rico" };
		for (String item : names) {
			System.out.println("names are: " + item);
		}

		// /////////////////////////////////////////////////////////////
		System.out.println("break statemtn goes here..");

		int[] numbers = { 1, 34, 56, 78, 8, 912, 45, 6 };
		int finNumber = 8;
		boolean isfound = false;
		int m;
		for (m = 0; m < numbers.length; m++) {
			if (numbers[m] == finNumber) {
				isfound = true;
				break;
			}
		}
		if (isfound) {
			System.out.println("finding number is: " + finNumber
					+ "  and it's index is:" + m);
		} else

			System.out.println("number does not find");

		// return statement return only when a method declared void and returns
		// nothing
		return;

	}
}
