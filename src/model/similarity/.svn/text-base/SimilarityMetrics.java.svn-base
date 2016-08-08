package similarity;

import Jama.Matrix;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.DiceSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.EuclideanDistance;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaccardSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.MatchingCoefficient;


public class SimilarityMetrics {
	
	public static void main(String[] args) {
		String tagSet1 = "excellent just excellent great soundtrack funny shit annoying";
		String tagSet2 = "amilies family keanu reeves cant remember k movie mysterious parcel ron howard";
		
	/*	10:41:29,875 INFO  [STDOUT] Running.. ALL_SEARCH_TYPE
		10:41:30,847 INFO  [STDOUT] User tags 5 for user id 3028
		10:41:31,932 INFO  [STDOUT] Relevant Items size 62 for user id 3028
		10:41:32,501 INFO  [STDOUT] comparing excellent excellent just excellent great soundtrack funny shit annoying  with scenic hostage meryl streep 
		10:41:33,247 INFO  [STDOUT] comparing excellent excellent just excellent great soundtrack funny shit annoying  with to see 19th century a. s. byatt adaptation victorian nudity (full frontal - notable) 
		10:41:33,788 INFO  [STDOUT] comparing excellent excellent just excellent great soundtrack funny shit annoying  with werewolf 
		10:41:34,327 INFO  [STDOUT] comparing excellent excellent just excellent great soundtrack funny shit annoying  with ensemble cast food multiple storylines 
		10:41:34,900 INFO  [STDOUT] comparing excellent excellent just excellent great soundtrack funny shit annoying  with sci-fi. dark clowns ghosts horror spielberg thriller 80s ghosts child in peril dogs epilepsy warning father daughter relationship haunted house mother daughter relationship paranormal smoking spirits storm swimming pool television toys cant remember tumeys dvds dont want to see craig t.nelson 70mm afi 100 (movie quotes) afi 100 (thrills) 
		10:41:35,483 INFO  [STDOUT] comparing excellent excellent just excellent great soundtrack funny shit annoying  with families family keanu reeves cant remember k movie mysterious parcel ron howard 
		10:41:36,047 INFO  [STDOUT] comparing excellent excellent just excellent great soundtrack funny shit annoying  with family bonds would see again young romance youth less than 300 ratings 
		ogma 95 in netflix queue chefs estate family gatherings father-son relationship forest freemason letters library vhs male nudity racism secrets siblings speech waitress dogma voto de castidade. bibliothek low budget nudity (topless) nudity (topless - brief) dogme95 sexual abuse birthday family incest suicide traumatic childhood not corv lib skinut*/ 
		
		System.out.println((float)SimilarityMetrics.computeDiceSimilarity(tagSet1, tagSet2));
		System.out.println((float)SimilarityMetrics.computeConsineSimilarity(tagSet1, tagSet2));
		System.out.println((float)SimilarityMetrics.computeJakardSimilarity(tagSet1, tagSet2));
		System.out.println((float)SimilarityMetrics.computeEuclidianSimilarity(tagSet1, tagSet2));
		System.out.println((float)SimilarityMetrics.computeMatchingCoefficientSimilarity(tagSet1, tagSet2));		
	}
	
	/**
	 * @param sourceDoc
	 * @param targetDoc
	 * @return
	 */
	public static double cosineSimilarity(Matrix sourceDoc, Matrix targetDoc) {
	    double dotProduct = sourceDoc.arrayTimes(targetDoc).norm1();
	    double eucledianDist = sourceDoc.normF() * targetDoc.normF();
	    return dotProduct / eucledianDist;
	  }
	
	/**
	 * It computes consine similarity between string vectors
	 * @param tagSet1
	 * @param tagSet2
	 * @return
	 */
	public static float computeConsineSimilarity(String tagSet1, String tagSet2){
		AbstractStringMetric cosineSimilarity = new CosineSimilarity();
		float simCosScore = cosineSimilarity.getSimilarity(tagSet1, tagSet2);
		return simCosScore;
	}
	
	/**
	 * @param tagSet1
	 * @param tagSet2
	 * @return
	 */
	public static float computeJakardSimilarity(String tagSet1, String tagSet2){
		AbstractStringMetric jakard = new JaccardSimilarity();
		float simJackcore = jakard.getSimilarity(tagSet1, tagSet2);
		return simJackcore;
	}
	 
	/**
	 * @param tagSet1
	 * @param tagSet2
	 * @return
	 */
	public static float computeDiceSimilarity(String tagSet1, String tagSet2){
		AbstractStringMetric dice = new DiceSimilarity();
		float simDiceScore = dice.getSimilarity(tagSet1, tagSet2);
		return simDiceScore;
	}
	
	/**
	 * @param tagSet1
	 * @param tagSet2
	 * @return
	 */
	public static float computeEuclidianSimilarity(String tagSet1, String tagSet2){
		AbstractStringMetric euclidian = new EuclideanDistance();
		float simEuScore = euclidian.getSimilarity(tagSet1, tagSet2);
		return simEuScore;
	}
	
	/**
	 * @param tagSet1
	 * @param tagSet2
	 * @return
	 */
	public static float computeMatchingCoefficientSimilarity(String tagSet1, String tagSet2){
		AbstractStringMetric matchingCoefficient = new MatchingCoefficient();
		float simMatchingScore = matchingCoefficient.getSimilarity(tagSet1, tagSet2);
		return simMatchingScore;
	}	

}
