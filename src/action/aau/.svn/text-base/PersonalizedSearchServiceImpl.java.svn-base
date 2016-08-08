package aau;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import similarity.SimilarityMetrics;
import Jama.Matrix;
import constant.IConstant;

/**
 * @author Fred Durao
 *
 */
@Stateless
@Name("personalizedSearchService")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class PersonalizedSearchServiceImpl implements PersonalizedSearchService {
	
	@In(create=true)
	private PersistenceService persistenceService;	
	
	public static void main(String[] args) {
		
		List<String> resultList = new ArrayList<String>(4);
		resultList.add("a");
		resultList.add("d");
		resultList.add("c");
		resultList.add("u");
		
		List<String> userList = new ArrayList<String>(3);
		userList.add("a");
		userList.add("d");
		userList.add("f");
		
		Set<String> unifiedVector = new LinkedHashSet<String>();
		unifiedVector.addAll(resultList);
		unifiedVector.addAll(userList);
		
		double[] resultVector = new double[unifiedVector.size()];
		double[] userVector = new double[unifiedVector.size()];
		
		for (int i = 0; i < unifiedVector.size(); i++) {
			if (resultList.contains(unifiedVector.toArray()[i])) {
				resultVector[i] = 1d;	
			}else{
				resultVector[i] = 0d;
			}
			
			if (userList.contains(unifiedVector.toArray()[i])) {
				userVector[i] = 0.8d;//userPreference.getTagFrequencyByUserAndTag();	
			}else{
				userVector[i] = 0d;			
			}			
		}
		//0.57735026
		for (int i = 0; i < resultVector.length; i++) {
			System.out.print(resultVector[i]);
			System.out.print(" ");
		}
		System.out.println();
		
		for (int i = 0; i < userVector.length; i++) {
			System.out.print(userVector[i]);
			System.out.print(" ");
		}
		
		System.out.print(" ");
		
		System.out.print(" ");
		
		
		
		System.out.println((float)SimilarityMetrics.cosineSimilarity(new Matrix(resultVector,resultVector.length),
				new Matrix(userVector,resultVector.length)));
		
		
	}

	/**
	 * @param searchResult
	 * @param user
	 * @return
	 */
	public SearchResult runPersonilazedSearch(SearchResult searchResult, String similarityType){
		SearchResult personalizedSearchResult = searchResult;
		
		List<Tag> userTags = persistenceService.getTagsFromUserPreferenceByUser(searchResult.getUser());
		
		//userTags.addAll(persistenceService.getTagLabelsFromRatedResourcesAndUser(3,searchResult.getUser());
		if (userTags!=null && !userTags.isEmpty()) {
			
			List<Tag> resourceTags = persistenceService.getTagsByResourceId(searchResult.getResource().getId());
			if (resourceTags!=null && !resourceTags.isEmpty()) {
				updateScore(similarityType, personalizedSearchResult, userTags, resourceTags);
				//updateScoreBasedOnUsersTag(searchResult,0.3d);
			}
		}
		return personalizedSearchResult;
	}	
	
	/**
	 * @param similarityType
	 * @param personalizedSearchResult
	 * @param userTags
	 * @param resourceTags
	 */
	private void updateScore(String similarityType, SearchResult personalizedSearchResult, List<Tag> userTags, List<Tag> resourceTags ){
		String userTagString = util.StringUtils.getTagString(userTags);	
		String resourceTagString = util.StringUtils.getTagString(resourceTags);
		//System.out.println("comparing "+userTagString+" with "+  resourceTagString);
	
		if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)||similarityType.equals(IConstant.DICE_SIMILARITY)) {
			//updateScoreBasedOnDiceSimilarity(personalizedSearchResult,SimilarityMetrics.computeDiceSimilarity(resourceTagString, userTagString));
		}
		if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.EUCLIDIAN_SIMILARITY)) {
			//updateScoreBasedOnEuclidianSimilarity(personalizedSearchResult,SimilarityMetrics.computeEuclidianSimilarity(resourceTagString, userTagString));
		}
		if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.JAKARD_SIMILARITY)) {
			//updateScoreBasedOnJacardSimilarity(personalizedSearchResult,SimilarityMetrics.computeJakardSimilarity(resourceTagString, userTagString));
		}
		if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.MATCHING_COEFFICIENT_SIMILARITY)) {
			//updateScoreBasedOnMatchingCoefficientSimilarity(personalizedSearchResult,SimilarityMetrics.computeMatchingCoefficientSimilarity(resourceTagString, userTagString));
		}
		if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.PERSONALIZED_SEARCH_SCORE)) {
			//updateScoreBasedOnUsersTag(personalizedSearchResult,computeSimilarity(new ArrayList(resourceTags), new ArrayList(userTags), personalizedSearchResult.getUser()));
			userTags.addAll(persistenceService.getTagLabelsFromRatedResourcesAndUser(4f,personalizedSearchResult.getUser()));
			updateScoreBasedOnExtendedConsineSimilarity(personalizedSearchResult,SimilarityMetrics.computeConsineSimilarity(resourceTagString, userTagString));
			//updateScoreBasedOnUsersTag(personalizedSearchResult,computeSimilarity(new ArrayList(resourceTags), new ArrayList(userTags), personalizedSearchResult.getUser()));
		}
		if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.STRING_COSINE_SIMILARITY)) {
			updateScoreBasedOnStringConsineSimilarity(personalizedSearchResult,SimilarityMetrics.computeConsineSimilarity(resourceTagString, userTagString));			
		} else {
			System.out.println(" DEFAUTL StringConsineSimilarity under usage ");
			updateScoreBasedOnStringConsineSimilarity(personalizedSearchResult,SimilarityMetrics.computeConsineSimilarity(resourceTagString, userTagString));			
		}
	}		
	
	/**
	 * It compares the user's tag and the tags of content item which is in
	 * user's watch list and updates the score
	 * 
	 * @param searchResult ,tag
	 * 
	 */
	private void updateScoreBasedOnUsersTag(SearchResult searchResult, double tagScore) {
		searchResult.setPersonalizedSearchScore(searchResult.getPersonalizedSearchScore()+searchResult.getSearchScore()+ (float)tagScore);
		//searchResult.setSearchScore(searchResult.getPersonalizedSearchScore());
	}
	
	/**
	 * @param searchResult
	 * @param diceTagScore
	 */
	private void updateScoreBasedOnDiceSimilarity(SearchResult searchResult, double diceTagScore) {
		searchResult.setDiceSimilarityScore(searchResult.getDiceSimilarityScore()+searchResult.getSearchScore()+ (float)diceTagScore);
		//searchResult.setSearchScore(searchResult.getDiceSimilarityScore());
	}
	
	/**
	 * @param searchResult
	 * @param jacakardTagScore
	 */
	private void updateScoreBasedOnJacardSimilarity(SearchResult searchResult, double jacakardTagScore) {
		searchResult.setJakardSimilarityScore(searchResult.getJakardSimilarityScore()+searchResult.getSearchScore()+ (float)jacakardTagScore);
		//searchResult.setSearchScore(searchResult.getJakardSimilarityScore());
	}
	
	/**
	 * @param searchResult
	 * @param euclidianTagScore
	 */
	private void updateScoreBasedOnEuclidianSimilarity(SearchResult searchResult, double euclidianTagScore) {
		searchResult.setEuclidianSimilarityScore(searchResult.getEuclidianSimilarityScore()+searchResult.getSearchScore()+ (float)euclidianTagScore);
		//searchResult.setSearchScore(searchResult.getEuclidianSimilarityScore());
		
	}
	
	/**
	 * @param searchResult
	 * @param matching
	 */
	private void updateScoreBasedOnMatchingCoefficientSimilarity(SearchResult searchResult, double matching) {
		searchResult.setMatchingCoefficientSimilarityScore(searchResult.getMatchingCoefficientSimilarityScore()+searchResult.getSearchScore()+ (float)matching);
		//searchResult.setSearchScore(searchResult.getMatchingCoefficientSimilarityScore());
		
	}
	/**
	 * @param searchResult
	 * @param stringConsine
	 */
	private void updateScoreBasedOnStringConsineSimilarity(SearchResult searchResult, double stringConsine) {
		searchResult.setStringCosineSimilarityScore(searchResult.getStringCosineSimilarityScore()+searchResult.getSearchScore()+ (float)stringConsine);
		//searchResult.setSearchScore(searchResult.getMatchingCoefficientSimilarityScore());
		
	}	
	
	/**
	 * @param searchResult
	 * @param stringConsine
	 */
	private void updateScoreBasedOnExtendedConsineSimilarity(SearchResult searchResult, double stringConsine) {
		searchResult.setPersonalizedSearchScore(searchResult.getPersonalizedSearchScore()+searchResult.getSearchScore()+ (float)stringConsine);
		//searchResult.setSearchScore(searchResult.getMatchingCoefficientSimilarityScore());
		
	}	

	/**
	 * @param searchResult
	 * @param coreTermScore
	 */
	private void updateScoreBasedOnCoreTerm(SearchResult searchResult, double coreTermScore) {
		searchResult.setPersonalizedSearchScore(searchResult.getSearchScore()+ (float)coreTermScore);
	}
	
	/**
	 * @param searchTags
	 * @param userTags
	 * @return
	 */
	private double computeSimilarity(List<Tag> searchTags, List<Tag> userTags, User user) {
		User currentUser = user;
		List<Tag> resultList = searchTags;
		List<Tag> userList = userTags;
		Set<Tag> unifiedVector = new LinkedHashSet<Tag>();
		unifiedVector.addAll(resultList);
		unifiedVector.addAll(userList);		
		
		double[] resultVector = new double[unifiedVector.size()];
		double[] userVector = new double[unifiedVector.size()];
		
		for (int i = 0; i < unifiedVector.size(); i++) {
			if (resultList.contains(unifiedVector.toArray()[i])) {
				resultVector[i] = 1f;	
			}else{
				resultVector[i] = 0f;
			}
			
			if (userList.contains(unifiedVector.toArray()[i])) {
				List<UserPreference> userPreferences = persistenceService.getUserPreferenceByTagAndUser((Tag)unifiedVector.toArray()[i], currentUser);				
				if (userPreferences==null || userPreferences.isEmpty()) {
					userVector[i] = 0f;
				}else{
					float tagFreq =0;
					for (UserPreference userPreference2 : userPreferences) {
							tagFreq=tagFreq+userPreference2.getTagFrequency();
					}
					userVector[i] = tagFreq;	
				}
				
			}else{
				userVector[i] = 0f;			
			}			
		}


		return SimilarityMetrics.cosineSimilarity(new Matrix(resultVector,resultVector.length), new Matrix(userVector,userVector.length));
	}	
	
	
	private void printVectors(double[] resultVector,double[] userVector){
		
//		for (int i = 0; i < resultVector.length; i++) {
//			System.out.print(resultVector[i]);
//			System.out.print(" ");
//		}
//		System.out.println();
//		
//		for (int i = 0; i < userVector.length; i++) {
//			System.out.print(userVector[i]);
//			System.out.print(" ");
//		}
//		System.out.println();
	}
	/**
	 * @param sourceDoc
	 * @param targetDoc
	 * @return
	 */
	
}
