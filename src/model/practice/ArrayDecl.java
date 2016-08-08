package practice;


public class ArrayDecl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
char[] copyFrom={'a', 'b', 'c','d','e','f','g','i'};
char[] copyTo=new char[4];
System.arraycopy(copyFrom, 3, copyTo,0, 4);

System.out.println(new String(copyTo));

	}

}
