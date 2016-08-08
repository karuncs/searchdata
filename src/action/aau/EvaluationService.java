
package aau;


/**
 * @author Fred
 *
 */
public interface EvaluationService {
	
	public void evaluateSearch();

	public void evaluateRecommendation();	
	
	public void evaluatePersonalizedSearch(String similarityType);	
	

}
