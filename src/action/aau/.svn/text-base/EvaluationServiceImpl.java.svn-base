package aau;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

import lucene.LuceneManagerService;
import lucene.LuceneManagerServiceImpl;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import util.FileUtils;
import util.PropertyUtil;
import constant.IConstant;
import evaluation.Performance;

@Stateless
@Name("evaluationService")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class EvaluationServiceImpl implements EvaluationService {

	@Logger
	private static Log log;

	@In
	FacesMessages facesMessages;

	// the entity manager used by this KiWi system
	@In
	private EntityManager entityManager;

	@In(create = true)
	private PersistenceService persistenceService;

	@In(create = true)
	private LuceneManagerService luceneManagerService;

	PropertyUtil propertyUtil = new PropertyUtil();

	private String SEARCH_RESULT_DIR = propertyUtil
			.getPropertyValue("search.result.dir");

	private String SEARCH_SERVER_RESULT_DIR = propertyUtil
			.getPropertyValue("search.server.result.dir");

	private String RECOMMENDATION_RESULT_DIR = propertyUtil
			.getPropertyValue("recommendation.result.dir");

	public int nextInt(Random r, int lower, int higher) {
		return r.nextInt((higher - lower + 1)) + lower;
	}

	public static void main(String[] args) {
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		SearchResult searchResult = new SearchResult();
		searchResult.setQuery("first");
		searchResult.setPersonalizedSearchScore(1f);
		searchResult.setSearchScore(3f);
		
		
		SearchResult searchResult2 = new SearchResult();
		searchResult2.setQuery("second");
		searchResult2.setPersonalizedSearchScore(2f);
		searchResult2.setSearchScore(2f);
		
		
		SearchResult searchResult3 = new SearchResult();
		searchResult3.setQuery("third");
		searchResult3.setPersonalizedSearchScore(3f);
		searchResult3.setSearchScore(1f);
		
		SearchResult searchResult4 = new SearchResult();
		searchResult4.setQuery("fourth");
		searchResult4.setPersonalizedSearchScore(2.5f);
		searchResult4.setSearchScore(2.45f);		
		
		searchResults.add(searchResult4);
		searchResults.add(searchResult);
		searchResults.add(searchResult2);
		searchResults.add(searchResult3);
		
		
		Collections.sort(searchResults);
		
		for (SearchResult searchResultItem : searchResults) {
			System.out.println(searchResultItem.getQuery()+"   "+searchResultItem.getSearchScore());
		}
		
		System.out.println(" ");
		
		Collections.sort(searchResults, SearchResult.PersonalizedSearchScoreComparator);
		
		for (SearchResult searchResultItem : searchResults) {
			System.out.println(searchResultItem.getQuery()+"   "+searchResultItem.getPersonalizedSearchScore());
		}
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aau.EvaluationService#evaluateRecommendation()
	 */
	public void evaluateRecommendation() {

		List<Ranking> rankings = new ArrayList<Ranking>();

		List<User> users = null;// getAllUsers();

		Map<User, List<Ranking>> resultMaps = new HashMap<User, List<Ranking>>();

		for (User user : users) {
			/*
			 * if (user.getLogin().equals("user12")|| continue; }
			 */
			// List<Resource> userHistorys = new
			// ArrayList<Resource>(listVisitsByUser(user));
			List<Resource> userHistorys = new ArrayList<Resource>();

			List<Resource> userRelevantItems = null; // listRelevantItemsByUser

			rankings = new ArrayList<Ranking>();

			for (Resource lastVisitedPage : userHistorys) {

				Ranking ranking = new Ranking();

				// List<Resource> recommendedItems =
				// recommendationService.getRecommendations(lastVisitedPage,
				// user);
				List<Resource> recommendedItems = null; // model.getRecommendationsByUser(user);

				ranking.setQuery("" + lastVisitedPage.getId());

				ranking.setUser(user);

				rankings.add(recommendationPerformance(recommendedItems,userRelevantItems, ranking));

			}

			resultMaps.put(user, rankings);

		}

		printPersonalizedResults(resultMaps,null);
	}

	/**
	 * @param searchResults
	 * @param relevantItems
	 * @param searchRanking
	 * @return
	 */
	public Ranking searchPerformance(List<SearchResult> searchResults, List<Resource> relevantItems, Ranking searchRanking) {
		List<Resource> retrievedItems = new ArrayList<Resource>();
		int count = 0;
		searchRanking.setMax(searchResults.size());
		
		for (SearchResult searchResult : searchResults) {
			Resource resource = searchResult.getResource();
			retrievedItems.add(resource);
			if (persistenceService.getAverageRatingByResource(resource) > 4d) { 
				count++;
				relevantItems.add(resource);
			}
		}
		
		
		try {
			Performance.processResourceMetrics(retrievedItems, relevantItems, searchRanking);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		searchRanking.setRetrieved(count);
		searchRanking.setScore(searchRanking.getRetrieved()/ searchRanking.getMax());
		return searchRanking;
	}
	
	/**
	 * @param searchResults
	 * @param relevantItems
	 * @param searchRanking
	 * @return
	 */
	public Ranking searchPerformance(List<SearchResult> searchResults, List<Resource> relevantItems, Ranking searchRanking, String searchType, int subList) {
		Ranking searchScoreRanking = null;
		if (searchType.equals(IConstant.ALL_SEARCH_TYPE)|| searchType.equals(IConstant.SEARCH_SCORE)) {
//			Collections.sort(searchResults, getComparator(searchType));
//			List<SearchResult> searchRegularResults = searchResults.subList(0, subList);
//			searchScoreRanking = searchPerformance(searchRegularResults, relevantItems, searchRanking);
//			searchRanking.setPrecision(searchScoreRanking.getPrecision());	
		}
		if (searchType.equals(IConstant.ALL_SEARCH_TYPE)|| searchType.equals(IConstant.DICE_SIMILARITY)) {
//			Collections.sort(searchResults, getComparator(searchType));
//			List<SearchResult> stringDiceSimilaritySearchResults = searchResults.subList(0, subList);
//			searchScoreRanking = searchPerformance(stringDiceSimilaritySearchResults, relevantItems, searchRanking);
//			searchRanking.setDiceSimilaritySearchPrecision(searchScoreRanking.getPrecision());	
		}
		if (searchType.equals(IConstant.ALL_SEARCH_TYPE)|| searchType.equals(IConstant.EUCLIDIAN_SIMILARITY)) {
//			Collections.sort(searchResults, getComparator(searchType));
//			List<SearchResult> stringEuclidianSearchResults = searchResults.subList(0, subList);
//			searchScoreRanking = searchPerformance(stringEuclidianSearchResults, relevantItems, searchRanking);
//			searchRanking.setEuclidianSimilaritySearchPrecision(searchScoreRanking.getPrecision());	
		}
		if (searchType.equals(IConstant.ALL_SEARCH_TYPE)|| searchType.equals(IConstant.JAKARD_SIMILARITY)) {
//			Collections.sort(searchResults, getComparator(searchType));
//			List<SearchResult> jakardSearchResults = searchResults.subList(0, subList);
//			searchScoreRanking = searchPerformance(jakardSearchResults, relevantItems, searchRanking);
//			searchRanking.setJakardSearchPrecision(searchScoreRanking.getPrecision());
		}
		if (searchType.equals(IConstant.ALL_SEARCH_TYPE)|| searchType.equals(IConstant.MATCHING_COEFFICIENT_SIMILARITY)) {
//			Collections.sort(searchResults, getComparator(searchType));
//			List<SearchResult> matchingCoefficientSimilaritySearchResults = searchResults.subList(0, subList);
//			searchScoreRanking = searchPerformance(matchingCoefficientSimilaritySearchResults, relevantItems, searchRanking);
//			searchRanking.setMatchingCoefficientSimilaritySearchPrecision(searchScoreRanking.getPrecision());
		}
		if (searchType.equals(IConstant.ALL_SEARCH_TYPE)|| searchType.equals(IConstant.PERSONALIZED_SEARCH_SCORE)) {
			Collections.sort(searchResults, getComparator(searchType));
			List<SearchResult> personalizedSearchResults = searchResults.subList(0, subList);
			searchScoreRanking = searchPerformance(personalizedSearchResults, relevantItems, searchRanking);
			searchRanking.setPersonalizedSearchPrecision(searchScoreRanking.getPrecision());
		}
		if (searchType.equals(IConstant.ALL_SEARCH_TYPE)|| searchType.equals(IConstant.STRING_COSINE_SIMILARITY)) {
			Collections.sort(searchResults, getComparator(searchType));
			List<SearchResult> stringCosineSimilaritySearchResults = searchResults.subList(0, subList);
			searchScoreRanking = searchPerformance(stringCosineSimilaritySearchResults, relevantItems, searchRanking);
			searchRanking.setStringConsineSimilaritySearchPrecision(searchScoreRanking.getPrecision());
		}
		return searchRanking;
	}	

	/**
	 * @param recommendedItems
	 * @param relevantItems
	 * @param ranking
	 * @return
	 */
	public Ranking recommendationPerformance(List<Resource> recommendedItems,
			List<Resource> relevantItems, Ranking ranking) {
		List<Resource> results = recommendedItems;
		List<Resource> retrievedItems = new ArrayList<Resource>();
		int count = 0;
		ranking.setMax(results.size());
		for (Resource contentItem : results) {
			if (contentItem.getRates().size() >= 3) {
				retrievedItems.add(contentItem);
				count++;
			}
		}
		try {
			Performance.processResourceMetrics(retrievedItems, relevantItems,
					ranking);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ranking.setRetrieved(count);
		ranking.setScore(ranking.getRetrieved() / ranking.getMax());
		return ranking;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aau.EvaluationService#evaluateSearch()
	 */

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void evaluateSearch() {

		LuceneManagerServiceImpl luceneManager = new LuceneManagerServiceImpl();
		// enhanced queries
		// for (long i = 206; i < 304; i++) {

		// regular queries
		for (long i = 503; i < 602; i++) {
			RelevantItem relevantItem = persistenceService
					.getRelevantItemById(i);

			if (relevantItem == null) {
				continue;
			}

			String searchQuery = relevantItem.getQuery();

			Ranking searchRanking = new Ranking();

			searchRanking.setQuery(searchQuery);

			List<Tag> tags = (List<Tag>) persistenceService
					.getTagsByName(searchQuery);

			// System.out.println("tags size "+ tags.size());
			if (tags == null || tags.isEmpty()) {
				continue;
			}

			Tag tag = ((Tag) tags.get(0));

			searchRanking.setExpandedQuery(expandQuerySearch(searchQuery, tag
					.getTagNeighbours()));

			searchRanking.setExpandedQuery(searchQuery);

			List<SearchResult> searchResults = luceneManager
					.topSearch(searchRanking.getExpandedQuery());

			List<Resource> relevantResources = relevantItem.getResources();

			if (relevantResources != null && !relevantResources.isEmpty()) {

				searchPerformance(searchResults, relevantResources,
						searchRanking);

				String text = "avg. precision	" + searchRanking.getPrecision()
						+ "	avg .recall	" + searchRanking.getRecall()
						+ "	query	" + searchRanking.getQuery() + "	queryID	"
						+ tag.getId();

				System.out.println(text);

				FileUtils.writeApendResults(text, SEARCH_RESULT_DIR, "txt");

			}

		}

	}

	/**
	 * @param searchResults
	 * @param query
	 */
	public void printSearchResults(List<SearchResult> searchResults,
			String query) {
		for (SearchResult searchResult : searchResults) {
			Resource resource = persistenceService.getResourceById(searchResult
					.getResource().getId());
			String text = "query	" + query + "	id	" + resource.getId()
					+ "	link	<a href='" + resource.getLink()
					+ " target=\"_blank\" '> <br><br> ";
			FileUtils.writeApendResults(text, SEARCH_RESULT_DIR, "txt");
		}
	}

	/**
	 * @param initialQuery
	 * @param tagNeibours
	 * @return
	 */
	public String expandQuerySearch(String initialQuery,
			List<String> tagNeibours) {
		if (tagNeibours.isEmpty()) {
			return initialQuery;
		}
		List<String> bestNeighbours = getBestNeighbours(tagNeibours);
		StringBuilder expandedQuery = new StringBuilder();
		expandedQuery.append("\"" + initialQuery + "\"");
		for (String neibours : bestNeighbours) {
			// expandedQuery.append(" AND ");
			expandedQuery.append(" OR ");
			expandedQuery.append("\"" + neibours + "\"");

		}
		return expandedQuery.toString();
	}

	/**
	 * @param tagNeibours
	 * @return
	 */
	public List<String> getBestNeighbours(List<String> tagNeibours) {
		Map<String, Float> tagMaps = new HashMap<String, Float>();
		List<String> bestNeighbours = new ArrayList<String>();
		Float highestValue = 0f;

		for (String tagNeighbour : tagNeibours) {
			// System.out.println(tagNeighbour);
			if (tagMaps.containsKey(tagNeighbour)) {
				Float currentValue = (Float) tagMaps.get(tagNeighbour);
				tagMaps.remove(tagNeighbour);
				Float finalValue = currentValue = currentValue + 1;
				tagMaps.put(tagNeighbour, finalValue);
			} else {
				tagMaps.put(tagNeighbour, 1f);
			}
		}

		for (String tagNeighbour : tagNeibours) {
			if ((Float) tagMaps.get(tagNeighbour) >= highestValue) {
				highestValue = (Float) tagMaps.get(tagNeighbour);
			}
		}

		for (String tagKey : tagMaps.keySet()) {
			float tagValue = (Float) tagMaps.get(tagKey).floatValue();
			if (tagValue == highestValue) {
				bestNeighbours.add(tagKey);
			}
		}

		return bestNeighbours;
	}

	/**
	 * @param query
	 * @return
	 */
	private List<Resource> getRelevantItemsByQuery(String query) {

		List<Resource> relevantResources = new ArrayList<Resource>();

		LuceneManagerServiceImpl luceneManager = new LuceneManagerServiceImpl();

		List<SearchResult> searchResults = luceneManager.search(query);

		for (SearchResult searchResult : searchResults) {
			Resource resource = persistenceService.getResourceById(searchResult
					.getResource().getId());
			if (resource.getTags() != null && !resource.getTags().isEmpty()) {
				for (Tag tag : resource.getTags()) {
					if (tag.getTagName().toString().equalsIgnoreCase(query)) {
						relevantResources.add(resource);
					}
				}
			}

		}
		System.out.println("only " + relevantResources.size() + " found ");

		return relevantResources;
	}

	/**
	 * @param query
	 * @return
	 */
	private List<Resource> getRelevantItemsByQueryAndTag(
			List<SearchResult> searchResults, String query) {

		List<Resource> relevantResources = new ArrayList<Resource>();

		for (SearchResult searchResult : searchResults) {
			Resource resource = persistenceService.getResourceByIdAndTag(
					searchResult.getResource().getId(), query);
			if (resource != null) {
				relevantResources.add(resource);
			}
		}

		System.out.println("only " + relevantResources.size() + " found ");

		return relevantResources;
	}

	/**
	 * @param query
	 * @return
	 */
	private List<Resource> getRelevantItemsByTitle(
			List<SearchResult> searchResults, String query,
			List<String> tagNeibours) {
		List<Resource> relevantResources = new ArrayList<Resource>();
		tagNeibours.add(query);
		Resource resource = null;
		for (SearchResult searchResult : searchResults) {
			for (String tagNeibour : tagNeibours) {
				resource = persistenceService.getResourceByIdAndFlexibleTitle(
						searchResult.getResource().getId(), tagNeibour);
				if (resource != null) {
					// System.out.println("title "+resource.getTitle());
					relevantResources.add(resource);
				}
			}
		}
		// System.out.println("only "+relevantResources.size()+" found ");
		return relevantResources;
	}

	/**
	 * 
	 * @return
	 */
	private static List<String> getQuery() {
		List<String> query = new ArrayList<String>();
		query.add("family");
		query.add("father");
		query.add("town");
		query.add("school");
		query.add("earth");
		query.add("band");
		return query;
	}

	/**
	 * @return
	 */
	private static List<Long> getUserIds() {
		List<Long> userIds = new ArrayList<Long>();
		/*
		userIds.add(5161l);
		userIds.add(4650l);
		userIds.add(7088l);
	    userIds.add(2514l);
		userIds.add(3304l);
		userIds.add(20l);
		userIds.add(3844l);		
		userIds.add(33585l);
	    userIds.add(2477l);
		userIds.add(5956l);		
		userIds.add(3301l);
		userIds.add(50160l);
		userIds.add(4517l);	
		userIds.add(3825l);
		userIds.add(692l);
		userIds.add(477l);		
		userIds.add(7181l);
		userIds.add(3392l);
		userIds.add(190l);	
		userIds.add(8302l);		
		userIds.add(636l);
	    userIds.add(2978l);
	    userIds.add(713l);
		userIds.add(528l);
		userIds.add(2606l);
		userIds.add(4856l);
		userIds.add(374l);
		userIds.add(3017l);
		userIds.add(1663l);
		userIds.add(49l);
		userIds.add(1282l);
		userIds.add(3743l);
		userIds.add(7287l);
		userIds.add(1355l);
		userIds.add(1224l);
		userIds.add(3489l);
		userIds.add(1056l);
		userIds.add(1282l);
		userIds.add(3743l);
		userIds.add(5739l);
		
		
		
		userIds.add(2024l);
		userIds.add(8604l);
		userIds.add(626l);
		userIds.add(4728l);
		
		*/	
		userIds.add(1049l);
		userIds.add(1806l);
		userIds.add(1017l);
		userIds.add(4397l);
		userIds.add(32011l);
		userIds.add(1504l);
		userIds.add(3630l);
		userIds.add(1436l);
		userIds.add(6311l);
		
		/*		
		
		userIds.add(31l);
		userIds.add(3490l);
		userIds.add(2953l);
		userIds.add(4011l);
		userIds.add(3386l);
		userIds.add(2429l);
		userIds.add(1920l);
		userIds.add(5228l);
		userIds.add(1081l);
		userIds.add(533l);
		userIds.add(1698l);
		userIds.add(181l);
		userIds.add(5917l);
		userIds.add(845l);
		userIds.add(27685l);
		userIds.add(33880l);
		userIds.add(26704l);
		userIds.add(61240l);
		userIds.add(7885l);
		userIds.add(4078l);
		userIds.add(51086l);
		userIds.add(39l);
		userIds.add(57274l);
		userIds.add(233l);
		userIds.add(8777l);
		userIds.add(6038l);
		userIds.add(299l);
		userIds.add(7369l);
		userIds.add(2471l);
		userIds.add(1682l);
		userIds.add(538l);
		userIds.add(6400l);
		userIds.add(1757l);
		userIds.add(1915l);
		userIds.add(1164l);
		userIds.add(5899l);
		userIds.add(7939l);
		userIds.add(48326l);
		userIds.add(26734l);
		userIds.add(5494l);
		userIds.add(2346l);
		userIds.add(32792l);
		userIds.add(1225l);
		
		*/
		return userIds;
	}
	

	  

	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see aau.EvaluationService#evaluatePersonalizedSearch()
	 */
	public void evaluatePersonalizedSearch(String similarityType) {

		List<Ranking> searchRankings = new ArrayList<Ranking>();

		Map<User, List<Ranking>> searchResultMaps = new HashMap<User, List<Ranking>>();

		List<SearchResult> searchResults = new ArrayList<SearchResult>();

		List<Resource> relevantItemsByUser = new ArrayList<Resource>();

		// Random userRandomId = new Random();

		Random queryRandomId = new Random();

		// for (long i = 1; i < 512; i++) { // persistenceService.getAllUsers();
		// http://www.ehow.com/how_5328613_choose-appropriate-sample-size.html x
		// users from a sample size
		//for (long i = 1; i < 11; i++) {
	    for (Long i : getUserIds()) {
			// long userRandomValue = (long)nextInt(userRandomId, 1, 71566);

			User user = ((EvaluationUser) persistenceService.getEvaluationUserByUserId(i)).getUser();

			// User user = persistenceService.getUserById(userRandomValue);

			// if user has no tags then do not proceed.
			List<Tag> userTags = persistenceService.getTagsFromUserPreferenceByUser(user);
			if ((userTags == null) || (userTags.isEmpty())) {
				continue;
			}
			System.out.println("User tags " + userTags.size() + " for user id "
					+ user.getId());

			
			
			
			// if user has no relevant items, it is impossible to compute
			// precision and recall
			/*
			relevantItemsByUser = persistenceService.getRelevantItemsByRatings(user);
			if ((relevantItemsByUser == null)|| (relevantItemsByUser.isEmpty())) {
				continue;
			}
			System.out.println("Relevant Items size "+ relevantItemsByUser.size() + " for user id "+ user.getId());
			*/
			
			

			searchRankings = new ArrayList<Ranking>();

			/*
			 * for (long j = 0; j < 10; j++) {
			 * 
			 * long queryRandomValue = (long)nextInt(queryRandomId, 574181,
			 * 595223);
			 * 
			 * IndexTerm indexTerm =
			 * persistenceService.getIndexTermById(queryRandomValue); if
			 * (indexTerm==null) { continue; }
			 */

			for (String query : getQuery()) {

				// String searchQuery = indexTerm.getTerm();//
				// http://en.wikipedia.org/wiki/Zipf%27s_law how many?
				String searchQuery = query;

				// searchResults =
				// luceneManagerService.performSearchMovieLens(searchQuery,user,true);
				if (similarityType.equals(IConstant.SEARCH_SCORE)) {
					searchResults = luceneManagerService.performTopSearchMovieLens(searchQuery, user, false, 30,similarityType);					
				}else{
					searchResults = luceneManagerService.performTopSearchMovieLens(searchQuery, user, true, 30, similarityType);
				}

				if ((searchResults == null) || (searchResults.isEmpty())) {
					continue;
				}

				//System.out.println("Search Results size " + searchResults.size());

				Ranking searchRanking = new Ranking();
				searchRanking.setQuery(searchQuery);
				searchRanking.setUser(user);
				searchRankings.add(searchPerformance(searchResults, relevantItemsByUser, searchRanking, similarityType, 10));

			}
			searchResultMaps.put(user, searchRankings);
			printPersonalizedResults(searchResultMaps,similarityType);
		}
		printPersonalizedResults(searchResultMaps,similarityType);
	}

	
	/**
	 * @param similarityType
	 * @return
	 */
	private Comparator<SearchResult> getComparator(String similarityType){
		if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.COSINE_SIMILARITY)) {
			return SearchResult.CosineSimilarityScoreComparator;
		}else if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.DICE_SIMILARITY)) {
			return SearchResult.DiceSimilarityScoreComparator;
		}else if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.EUCLIDIAN_SIMILARITY)) {
			return SearchResult.EuclidianSimilarityScoreComparator;
		}else if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.JAKARD_SIMILARITY)) {
			return SearchResult.JakardSimilarityScoreComparator;
		}else if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.MATCHING_COEFFICIENT_SIMILARITY)) {
			return SearchResult.MatchingCoefficientSimilarityComparator;
		}else if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.PERSONALIZED_SEARCH_SCORE)) {
			return SearchResult.PersonalizedSearchScoreComparator;
		}else if (similarityType.equals(IConstant.ALL_SEARCH_TYPE)|| similarityType.equals(IConstant.SEARCH_SCORE)) {
			return SearchResult.SearchScoreComparator;
		}else {
			System.out.println(" DEFAUTL StringCosineSimilarityScoreComparator under usage ");
			return SearchResult.StringCosineSimilarityScoreComparator;
		}
	}
	
	private void printJakard(List<SearchResult> searchResults){
		for (SearchResult sr : searchResults) {
			System.out.println(sr.getJakardSimilarityScore()+"     "+  sr.getResource().getTitle());
		}	
	}
	
	private void printScore(List<SearchResult> searchResults){
		for (SearchResult sr : searchResults) {
			System.out.println(sr.getSearchScore()+"     "+  sr.getResource().getTitle());
		}	
	}	

	/**
	 * @param searchRankingMaps
	 */
	private void printResults(List<Ranking> searchRankingResults) {
		float aveScore = 0f;
		float avePrec = 0f;
		float aveRecall = 0f;
		int promo = 0;
		float maxSize = 0f;
		List<Ranking> searchResultRankingFinals = new ArrayList<Ranking>();

		for (Ranking searchRanking : searchRankingResults) {
			aveScore = aveScore + searchRanking.getScore();
			avePrec = avePrec + searchRanking.getPrecision();
			aveRecall = aveRecall + searchRanking.getRecall();
			promo = promo + searchRanking.getPromotions();
			// System.out.println("User	"+user.getLogin()+"	query	"+searchRanking.getQuery()+"	score	"+searchRanking.getScore()+"	precision	"+searchRanking.getPrecision()+"	recall	"+searchRanking.getRecall());
		}
		Ranking searchRankingFinal = new Ranking();
		searchRankingFinal.setAveScore(aveScore / maxSize);
		searchRankingFinal.setAveRecall(aveRecall / maxSize);
		searchRankingFinal.setAvePrecision(avePrec / maxSize);
		searchRankingFinal.setPromotions(promo);
		searchResultRankingFinals.add(searchRankingFinal);
		aveScore = 0f;

		avePrec = 0f;
		aveRecall = 0f;
		maxSize = 0f;
		promo = 0;

		StringBuffer sb = new StringBuffer();
		for (Ranking ranking : searchResultRankingFinals) {
			System.out.println(" avg. score	" + ranking.getAveScore()
					+ "	avg. precision	" + ranking.getAvePrecision()
					+ "	avg .recall	" + ranking.getAveRecall()
					+ "	total promotions	" + ranking.getPromotions());
			String text = "	avg. score	" + ranking.getAveScore()
					+ "	avg. precision	" + ranking.getAvePrecision()
					+ "	avg .recall	" + ranking.getAveRecall()
					+ "	total promotions	" + ranking.getPromotions();
			sb.append(text);
			sb.append("\n");
		}

		FileUtils.writeApendResults(sb.toString(), SEARCH_RESULT_DIR, "txt");
	}

	/**
	 * @param searchRankingMaps
	 */
	private void printPersonalizedResults(
			Map<User, List<Ranking>> searchRankingMaps, String textFileName) {
		float aveScore = 0f;
		float avePrec = 0f;
		float aveRecall = 0f;
		int promo = 0;
		
		float maxSize = 0f;
		   
	    float jakardAveSearchPrecision= 0f;
	    float diceAveSimilaritySearchPrecision= 0f;
	    float stringAveConsineSimilaritySearchPrecision= 0f;
	    float matchingAveCoefficientSimilaritySearchPrecision= 0f;
	    float euclidianAveSimilaritySearchPrecision= 0f;
	    float personalizedAveSearchPrecision= 0f;			
		
		Map<User, Ranking> searchResultFinalMaps = new HashMap<User, Ranking>();
		for (User user : searchRankingMaps.keySet()) {
			maxSize = (float) searchRankingMaps.get(user).size();
			for (Ranking searchRanking : searchRankingMaps.get(user)) {
				aveScore = aveScore + searchRanking.getScore();
				avePrec = avePrec + searchRanking.getPrecision();
				aveRecall = aveRecall + searchRanking.getRecall();
				promo = promo + searchRanking.getPromotions();
				
				
				//jakardAveSearchPrecision= jakardAveSearchPrecision + searchRanking.getJakardSearchPrecision();
				//diceAveSimilaritySearchPrecision= diceAveSimilaritySearchPrecision + searchRanking.getJakardSearchPrecision();
			    personalizedAveSearchPrecision= personalizedAveSearchPrecision + searchRanking.getPersonalizedSearchPrecision();
			    //euclidianAveSimilaritySearchPrecision= euclidianAveSimilaritySearchPrecision + searchRanking.getEuclidianSimilaritySearchPrecision();			    
			    //matchingAveCoefficientSimilaritySearchPrecision= matchingAveCoefficientSimilaritySearchPrecision + searchRanking.getMatchingCoefficientSimilaritySearchPrecision();
			    stringAveConsineSimilaritySearchPrecision= stringAveConsineSimilaritySearchPrecision + searchRanking.getStringConsineSimilaritySearchPrecision();			    
			    
				
				
				
				// System.out.println("User	"+user.getLogin()+"	query	"+searchRanking.getQuery()+"	score	"+searchRanking.getScore()+"	precision	"+searchRanking.getPrecision()+"	recall	"+searchRanking.getRecall());
			}
			Ranking searchRankingFinal = new Ranking();
			searchRankingFinal.setAveScore(aveScore / maxSize);
			searchRankingFinal.setAveRecall(aveRecall / maxSize);
			searchRankingFinal.setAvePrecision(avePrec / maxSize);
			searchRankingFinal.setPromotions(promo);
			
			
			searchRankingFinal.setJakardAveSearchPrecision(jakardAveSearchPrecision/maxSize);
			searchRankingFinal.setDiceAveSimilaritySearchPrecision(diceAveSimilaritySearchPrecision/maxSize);			
			searchRankingFinal.setPersonalizedAveSearchPrecision(personalizedAveSearchPrecision/maxSize);
			searchRankingFinal.setEuclidianAveSimilaritySearchPrecision(euclidianAveSimilaritySearchPrecision/maxSize);
			searchRankingFinal.setMatchingAveCoefficientSimilaritySearchPrecision(matchingAveCoefficientSimilaritySearchPrecision/maxSize);
			searchRankingFinal.setStringAveConsineSimilaritySearchPrecision(stringAveConsineSimilaritySearchPrecision/maxSize);			

			searchResultFinalMaps.put(user, searchRankingFinal);
			aveScore = 0f;
			avePrec = 0f;
			aveRecall = 0f;
			maxSize = 0f;
			promo = 0;
		}

		StringBuffer sb = new StringBuffer();
		for (User user : searchResultFinalMaps.keySet()) {
			Ranking searchRankingFinal = searchResultFinalMaps.get(user);
			// System.out.println("User	"+user.getLogin()+"	avg. score	"+searchRankingFinal.getAveScore()+"	avg. precision	"+searchRankingFinal.getAvePrecision()+"	avg .recall	"+searchRankingFinal.getAveRecall());
			/*
			System.out.println("User	" + user.getName() + "	avg. score	"
					+ searchRankingFinal.getAveScore() + "	avg. precision	"
					+ searchRankingFinal.getAvePrecision() + "	avg .recall	"
					+ searchRankingFinal.getAveRecall() + "	total promotions	"
					+ searchRankingFinal.getPromotions());
			String text = "User	" + user.getName() + "	avg. score	"
					+ searchRankingFinal.getAveScore() + "	avg. precision	"
					+ searchRankingFinal.getAvePrecision() + "	avg .recall	"
					+ searchRankingFinal.getAveRecall() + "	total promotions	"
					+ searchRankingFinal.getPromotions();
					*/
			
			
			/*
			System.out.println("User	" + user.getName() + "	avg. precision	"+ searchRankingFinal.getAvePrecision() );
			String text = "User	" + user.getName() +
			"	avg. precision	"+ searchRankingFinal.getAvePrecision()+

			"	avg. personalizedAveSearchPrecision	"+ searchRankingFinal.getPersonalizedAveSearchPrecision()+
			"	avg. diceAveSimilaritySearchPrecision	"+ searchRankingFinal.getDiceAveSimilaritySearchPrecision()+
			"	avg. jakardAveSearchPrecision	"+ searchRankingFinal.getJakardAveSearchPrecision()+
			"	avg. euclidianAveSimilaritySearchPrecision	"+ searchRankingFinal.getEuclidianAveSimilaritySearchPrecision()+
			"	avg. matchingAveCoefficientSimilaritySearchPrecision	"+ searchRankingFinal.getMatchingAveCoefficientSimilaritySearchPrecision()+			
			"	avg. stringAveConsineSimilaritySearchPrecision	"+ searchRankingFinal.getStringAveConsineSimilaritySearchPrecision();
			
			*/
			
			System.out.println("Results finally computed !");
			
			String text = "User	" + user.getName() +
			"	"+ searchRankingFinal.getAvePrecision()+
			"	"+ searchRankingFinal.getPersonalizedAveSearchPrecision()+
			"	"+ searchRankingFinal.getStringAveConsineSimilaritySearchPrecision()+			
			"	"+ searchRankingFinal.getDiceAveSimilaritySearchPrecision()+
			"	"+ searchRankingFinal.getJakardAveSearchPrecision()+
			"	"+ searchRankingFinal.getEuclidianAveSimilaritySearchPrecision()+
			"	"+ searchRankingFinal.getMatchingAveCoefficientSimilaritySearchPrecision();			
			sb.append(text);
			sb.append("\n");
		}
		if (textFileName==null ) {
			textFileName = "PERSONALIZED_SEARCH_RESULT";
		}
		FileUtils.writeApendResults(sb.toString(), FileUtils
				.getAvailableSearchResultDirectory()
				+ File.separator + textFileName, "txt");
	}

}
