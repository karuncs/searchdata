package cluster;

import javax.vecmath.GMatrix;
import javax.vecmath.GVector;

import com.gregdennis.drej.Kernel;
import com.gregdennis.drej.LinearKernel;
import com.gregdennis.drej.Matrices;
import com.gregdennis.drej.Regression;
import com.gregdennis.drej.Representer;




/*************************************************************************
 *  Compilation:  javac LinearRegression.java StdIn.java
 *  Execution:    java LinearRegression < data.txt
 *  
 *  Reads in a sequence of pairs of real numbers and computes the
 *  best fit (least squares) line y  = ax + b through the set of points.
 *  Also computes the correlation coefficient and the standard errror
 *  of the regression coefficients.
 *
 *  Note: the two-pass formula is preferred for stability.
 *
 *************************************************************************/

public class LinearRegression { 
	
	public static void main(String[] args) {
		
	
	// Initialize x values
	//double[] xvalues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		double[] xvalues = {1, 2, 3, 4};

	// Initialize y values
		//double[] yvalues = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
		double[] yvalues = {1,2};

	 GMatrix data = new GMatrix(2,2,xvalues);
	 GVector values = new GVector(yvalues);
	 System.out.println(data.toString());
	 System.out.println(values.toString());
	 if (xvalues.length==yvalues.length) {
		System.out.println("equallllll");
	}

	 // here you would put all your data points into the data matrix
	 // each data point goes into a column of the matrix
	 // put the actual values for those data points in the values vector
	 // the data point in the ith column of the data matrix should have
	 // the value in the ith entry in the values vector.
	 // I believe some kernels only work when your range of possible values has
	 // zero as a midpoint. for instance, if you're classifying data points into "yes"
	 // and "no", best to choose their values as 1 and -1, as opposed to 1 and 0.

	 // construct the kernel you want to use:

	 Kernel kernel = LinearKernel.KERNEL;

	 // choose a penalty factor on the complexity of the solution
	 // this helps to prevent overfitting the data
	 // I was told me this number should be between
	 // 10^-3 and 1, I often choose 0.5, but you can play with it
	 double lambda = 0.5;

	 // do the regression, which returns a function fit to the data
	 Representer representer = Regression.solve(data, values, kernel, lambda);

	 //That's basically it. What happens next depends on what you want to use it for.
	 //If you'd like to use the regression to predict the value of a data point y, just feed y into the representer function:
	 double[] yy = {1, 2};
	 GVector y = new GVector(yy);
	 
	 double predictedValue = representer.eval(y);
	 System.out.println(predictedValue);
	 //If you'd like to calculate how well the function fits the data, you can first calculate the vector of values the representer would predict for your data points, subtract from that the vector of actual values, and take the norm squared of that difference. Let's call this the "cost". The lower the cost, the better the function fits the data. You can try out different kernels, and see which one yields the best-fit curve (the lowest cost):

	 GVector predictedValues = Matrices.mapCols(representer, data);
	 predictedValues.sub(values);
	 double cost = predictedValues.normSquared();
	 System.out.println(cost);

	}    
}
