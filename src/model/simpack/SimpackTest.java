package simpack;

import simpack.accessor.string.StringAccessor;
import simpack.measure.external.simmetrics.CosineSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;


public class SimpackTest {
	public static void main(String[] args) {
		
	
		//StringAccessor stringAccessor = new StringAccessor("fred");

		//System.out.println(stringAccessor.getSize());
	//System.out.println(stringAccessor.getElementFrequencies().toString());
		
		//StringAccessor stringAccessor2 = new StringAccessor("a b c d d s");

		//System.out.println(stringAccessor2.getSize());
		//System.out.println(stringAccessor2.getElementFrequencies().toString());
		
		AbstractStringMetric cosineSimilarity = new uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity();
		System.out.println(cosineSimilarity.getSimilarity("a b c", "s a b"));
		
	
		
	}
	
    
	
}
