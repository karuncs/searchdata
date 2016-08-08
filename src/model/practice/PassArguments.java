package practice;

public class PassArguments {
	
	// compute interest
	 public void computePayment(double loanAmt, double rate, double futureValue, double numPeriods ){
		 
		 	double interest = rate / 100.0;
		    double partial1 = Math.pow((1 + interest), -numPeriods);
		    double denominator = (1 - partial1) / interest;
		    double answer = (-loanAmt / denominator) - ((futureValue * partial1) / denominator);
		    //return answer;
		   System.out.println("final output is"+ answer);
		
	 }
//	int i, j;
//
//	void function(int i) {
//		i = i + 1;
//		j = i + 1;
//		System.out.println(i);
//		System.out.println(j);
//	}

//	PassArguments(double loanAmt, double rate, double futureValue,
//			int numPeriods) {
//		double interest = rate / 100.0;
//		double partial1 = Math.pow((1 + interest), -numPeriods);
//		double denominator = (1 - partial1) / interest;
//		double answer = (-loanAmt / denominator)
//				- ((futureValue * partial1) / denominator);
//	}

	public static void main(String[] args) {
//		PassArguments p1 = new PassArguments();
//		p1.function(6);
//		System.out.println(p1.i);
//		System.out.println(p1.j);
		PassArguments p1 = new PassArguments();
		p1.computePayment(1000,5,200,12);
		 
	}
	
	 
		 
	 }


