package aau;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ValueChangeEvent;

import lucene.LuceneManagerService;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import util.PropertyUtil;

import com.ibm.icu.util.StringTokenizer;

import constant.IConstant;


/**
 * @author freddurao & Karun
 */
@Name("searchAction")
@Scope(ScopeType.CONVERSATION)
@Transactional
public class SearchAction {
	
	@Logger
	Log log;

	@In(create=true)
	private PersistenceService persistenceService;	
	
	@In(create=true)
	private LuceneManagerService luceneManagerService;
	
	@In(create=true)
	private ClusterService clusterService;		
	
	private String searchQuery;
	
	private List<SearchResult> searchResults;	

	private String expandedQuery;
	private List<SearchResult> searchResultsNeighbour;

	PropertyUtil propertyUtil = new PropertyUtil();

	private int START_VALUE = new Integer(propertyUtil.getPropertyValue("start.value"));
	
	private boolean showCluster;
	
	public static void main(String[] args) {
		SearchAction managerAction = new SearchAction();
		
		System.out.println(managerAction.START_VALUE);
	}

	/**
	 * Compute Search
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SearchResult>[] search1(){
		searchResults = luceneManagerService.performSearchTwitter(searchQuery,null,false,IConstant.SEARCH_SCORE);
		
		for (SearchResult searchResult : searchResults) {
			Resource resource = persistenceService.getResourceById(searchResult.getResource().getId());
			resource.setTagsText(resource.getTagString());
			searchResult.setResource(resource);
		}
		
		Collections.sort(searchResults);

		//perform search considering the neighbours as well
		//First, get the most frequent neighbours
		String newQuery = searchQuery; 
		StringTokenizer st = new StringTokenizer(searchQuery);
	    while (st.hasMoreTokens()) {
	        List<Object> neighbourList = persistenceService.getTagNeighbours(st.nextToken());	        
	        	        
	        String neighbourName = "";
	        float neighbourFreq = 0;
	        float firstFreq = ((Float)((Object[])neighbourList.get(0))[1]).floatValue();
	        for (int i = 0; i < neighbourList.size(); i++) {	        	
	        	Object[] obj = (Object[])neighbourList.get(i);

	        	neighbourName = obj[0].toString();
        		neighbourFreq = ((Float)obj[1]).floatValue();
        		
        		newQuery = newQuery + " " + neighbourName;
        		
        		if (neighbourFreq/firstFreq < 0.07) {
        			break;
        		}
	        }
	    }
	    
	    //Second, call Lucene search with the expanded query
	   searchResultsNeighbour = luceneManagerService.performSearchTwitter(newQuery,null,false,IConstant.SEARCH_SCORE);
		for (SearchResult searchResult : searchResultsNeighbour) {
			Resource resource = persistenceService.getResourceById(searchResult.getResource().getId());
			resource.setTagsText(resource.getTagString());
			searchResult.setResource(resource);
		}
		
		Collections.sort(searchResultsNeighbour);	   
	   
		
//		for (SearchResult searchResult : searchResults) {
//			System.out.println("Resource "+ searchResult.getResource().getTitle()+ " query "+ searchResult.getQuery()+ " score "+ searchResult.getSearchScore());
//		}
		System.out.println("ending computeSearch...");
		
		List<SearchResult>[] resultSet = (List<SearchResult>[]) new List[2];
		
		resultSet[0] = searchResults;
		resultSet[1] = searchResultsNeighbour;
		
		return resultSet;
		
	}
	
	
	/**
	 * @param query
	 * @return
	 */
	public List<SearchResult> regularsearch(String query){
		//List<SearchResult> regularSearchResults = luceneManagerService.performSearchMedworm(query,null,false,IConstant.SEARCH_SCORE);
		int i = 0;
		Set<String> uniqueURL = new HashSet<String>();
		List<SearchResult> regularSearchResults = luceneManagerService.performTopSearchMedworm(query,null,false,IConstant.SEARCH_SCORE);
		List<SearchResult> finalSearchResults = new ArrayList<SearchResult>();
		for (SearchResult searchResult : regularSearchResults) {
			Resource resource = persistenceService.getResourceById(searchResult.getResource().getId());
			if (resource!=null) {
				if (!uniqueURL.contains(resource.getLink())) {
					uniqueURL.add(resource.getLink());
					resource.setTagsText(resource.getTagString());
					//resource.setContexts(resource.getContexts());
					searchResult.setResource(resource);	
					searchResult.setRank(""+i++);
					finalSearchResults.add(searchResult);
				}
			}

		}
		Collections.sort(finalSearchResults);
		regularSearchResults = null;
		return finalSearchResults;
	}
	
	
	/*
	 search performance for Medworm data
	 */
	
	@SuppressWarnings("unchecked")
	public List<SearchResult>[] search(){
		searchResults = regularsearch(searchQuery);

		if (searchResults.isEmpty()) {
			List<SearchResult>[] resultSet = (List<SearchResult>[]) new List[2];
			resultSet[0] = searchResults;
			resultSet[1] = searchResultsNeighbour;			
			FacesMessages.instance().add("No results available");
			return resultSet;
		}		
		//perform search considering the neighbours as well
		//First, get the most frequent neighbours
		String newQuery = expandQuery(searchQuery);
	    expandedQuery = "Search results of expanded query: " + newQuery;
	    
	    //Second, call Lucene search with the expanded query
	    if (!newQuery.equals(searchQuery)) {
	    	searchResultsNeighbour = regularsearch(newQuery);
		}else{
			searchResultsNeighbour = searchResults;
		}
			
//		for (SearchResult searchResult : searchResults) {
//			System.out.println("Resource "+ searchResult.getResource().getTitle()+ " query "+ searchResult.getQuery()+ " score "+ searchResult.getSearchScore());
//		}
		System.out.println("ending computeSearch...");
		
		List<SearchResult>[] resultSet = (List<SearchResult>[]) new List[2];
		
		resultSet[0] = searchResults;
		resultSet[1] = searchResultsNeighbour;
		
		return resultSet;
		
	}

	/**
	 * @param entryQuery
	 * @return
	 */
	private String expandQuery(String entryQuery) {
		String expandedQuery = entryQuery;
		StringTokenizer st = new StringTokenizer(entryQuery);
	    while (st.hasMoreTokens()) {
	        List<Object> neighbourList = persistenceService.getTagNeighbours(st.nextToken());
	        if (!neighbourList.isEmpty()) {
		        String neighbourName = "";
		        float neighbourFreq = 0;
		        float firstFreq = ((Float)((Object[])neighbourList.get(0))[1]).floatValue();
		        for (int i = 0; i < neighbourList.size(); i++) {	        	
		        	Object[] obj = (Object[])neighbourList.get(i);
	
		        	neighbourName = obj[0].toString();
	        		neighbourFreq = ((Float)obj[1]).floatValue();
	        		
	        		expandedQuery = expandedQuery + " " + neighbourName;
	        		
	        		if (neighbourFreq/firstFreq < 0.70) {
	        			break;
	        		}
		        }
	        }
	    }
		return expandedQuery.trim();
	}
	public String getSearchQuery() {
		return searchQuery;
	}


	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}


	public List<SearchResult> getSearchResults() {
		return searchResults;
	}


	public void setSearchResults(List<SearchResult> searchResults) {
		this.searchResults = searchResults;
	}

	public List<SearchResult> getSearchResultsNeighbour() {
		return searchResultsNeighbour;
	}

	public void setSearchResultsNeighbour(List<SearchResult> searchResultsNeighbour) {
		this.searchResultsNeighbour = searchResultsNeighbour;
	}

	public boolean getShowCluster() {
		return showCluster;
	}

	public void setShowCluster(boolean showCluster) {
		if (showCluster) {
			List<SearchResult> searchResults = regularsearch(this.searchQuery);
			if (!searchResults.isEmpty()) {
				clusterService.calculateSpectralClusterFromSearch(searchResults);
			}
		}
		this.showCluster = showCluster;
	}

	public String getExpandedQuery() {
		return expandedQuery;
	}

	public void setExpandedQuery(String expandedQuery) {
		this.expandedQuery = expandedQuery;
	}	
	
	 /**
	   * Listener method called when a checkbox on the page is changed
	   * The method will update the current checkbox count
	   */
	 public void changeSelection(ValueChangeEvent event) {
//			if (!event.getPhaseId().equals(PhaseId.INVOKE_APPLICATION)) {
//				event.setPhaseId(PhaseId.INVOKE_APPLICATION);
//				event.queue();
//	 			return true;
//	 		}
		 
	   System.out.println(Boolean.valueOf(event.getNewValue().toString()).booleanValue());
	   this.setShowCluster(Boolean.valueOf(event.getNewValue().toString()).booleanValue());
	 }
	
	

	
}
