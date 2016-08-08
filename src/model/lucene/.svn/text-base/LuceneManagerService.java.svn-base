package lucene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;

import aau.IndexTerm;
import aau.PersistenceService;
import aau.SearchResult;
import aau.Tag;
import aau.User;

public interface LuceneManagerService  {
	

	/**
	 * @param qry
	 * @param analyzer
	 * @param conn
	 * @throws ParseException 
	 * @throws CorruptIndexException
	 * @throws IOException
	 * @throws ParseException
	 * @throws SQLException
	 */
	public List<SearchResult> performSearchTwitter(String qry, User user, boolean isPersonalizedSearch, String similarityType);	
	
	
	/**
	 * @param qry
	 * @param analyzer
	 * @param conn
	 * @throws ParseException 
	 * @throws CorruptIndexException
	 * @throws IOException
	 * @throws ParseException
	 * @throws SQLException
	 */
	public List<SearchResult> performSearchMedworm(String qry, User user, boolean isPersonalizedSearch, String similarityType);	
	
	/**
	 * @param qry
	 * @param analyzer
	 * @param conn
	 * @throws ParseException 
	 * @throws CorruptIndexException
	 * @throws IOException
	 * @throws ParseException
	 * @throws SQLException
	 */
	public List<SearchResult> performSearchMovieLens(String qry, User user, boolean isPersonalizedSearch, String similarityType);
	

	/**
	 * @param qry
	 * @param user
	 * @param isPersonalizedSearch
	 * @param topItems
	 * @param similarityType
	 * @return
	 */
	public List<SearchResult> performTopSearchMovieLens(String qry, User user, boolean isPersonalizedSearch, int topItems, String similarityType);

	/**
	 * @return
	 */
	public List<IndexTerm> getTermFrequency();
	
	/**
	 * @param userQuery
	 * @return
	 */
	public List<SearchResult> search(String userQuery);
	
	
	/**
	 * Get all neighbours of the root and add to it as simblings
	 * @param tagRoot
	 * @param indexFieldSearch
	 */
	public void getNeighboursByTag(Tag tagRoot, String indexFieldSearch, PersistenceService persistenceService);
	
	public List<SearchResult> performTopSearchMedworm(String qry, User user, boolean isPersonalizedSearch, String similarityType) ;
}