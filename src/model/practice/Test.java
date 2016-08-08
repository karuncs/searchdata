package practice;
//import java.util.Date;

public class Test {
	public static void main(String[] args){
		StringBuffer a=new StringBuffer("A");
		StringBuffer b=new StringBuffer("B");
		operate(a,b);
		System.out.println(a+","+b);
		
	}
	
static void operate(StringBuffer x, StringBuffer y){
	x.append(y);
	
	y=x;
	int i=23345;
	int j=~i;
	System.out.println(Math.floor(-4.7));
	System.out.println(Math.round(-4.7));
	System.out.println(Math.ceil(-4.7));
	System.out.println(j);
	//System.out.println(Math.min(-4.7));
//	Date d1 = new Date (99, 11, 31);
//
//    Date d2 = new Date (99, 11, 31);
//
//    method(d1, d2);
//
//    System.out.println("d1 is " + d1 
//
//                + "\nd2 is " + d2);
//    
}

//public static void method(Date d1, Date d2) {
//
//    d2.setYear (100);
//
//    d1 = d2;
    
    
   
   

}




