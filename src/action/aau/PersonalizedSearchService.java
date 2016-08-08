package aau;

/**
 * @author Fred Durao
 *
 */
public interface PersonalizedSearchService {

	/**
	 * @param searchResult
	 * @param similarityType
	 * @return
	 */
	public SearchResult runPersonilazedSearch(SearchResult searchResult, String similarityType);
}
