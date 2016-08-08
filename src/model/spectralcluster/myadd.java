package spectralcluster;

import com.mathworks.toolbox.javabuilder.*;

import com.mathworks.toolbox.javabuilder.MWNumericArray;

import myadd.myaddclass;

public class myadd {

	public static void main (String[] args) {
		   
        MWNumericArray a = null;    /* Stores input values a */
        MWNumericArray b = null;    /* Stores input values b */
        Object[] result = null;     /* Stores the result */
        myaddclass myAdd = null;      /* Stores myadd class instance */
        try
        {
            /* test the input variable */
            if (args.length != 2)
            {
                System.out.println("Error: must input 2 numbers!");
                return;
            }
           
            /* input variable */
            a = new MWNumericArray(Double.valueOf(args[0]),MWClassID.DOUBLE);
            b = new MWNumericArray(Double.valueOf(args[1]),MWClassID.DOUBLE);
            /* create myaddclass */
            myAdd = new myaddclass();
           
            /* compute and output result  */
            result = myAdd.myadd(1, 6, 4);
            System.out.print("\nThe sum of " + a.toString() + " and " + b.toString() + " is: ");
            System.out.println(result[0]);
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.toString());
        }
       
        finally
        {
            /* Free native resources */
            MWArray.disposeArray(a);
            MWArray.disposeArray(b);
            MWArray.disposeArray(result);
            if (myAdd != null)
                myAdd.dispose();
        }
	}
	
}
