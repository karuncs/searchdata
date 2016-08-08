package aau;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Transient;



@SuppressWarnings("unchecked")
public class SearchResult implements Comparable<SearchResult>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchResult() {
	}
	
	private String query;
	
	private String highlight;
	
	private User user;
	
	@Transient
	private String rank;
	
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	private String expandedQuery;	
	
	public String getExpandedQuery() {
		return expandedQuery;
	}

	public void setExpandedQuery(String expandedQuery) {
		this.expandedQuery = expandedQuery;
	}

	private float searchScore;
	
	private float personalizedSearchScore;


	private float cosineSimilarityScore;
	
	private float stringCosineSimilarityScore;	
	
	private float jakardSimilarityScore;
	
	private float diceSimilarityScore;
	
	private float euclidianSimilarityScore;
	
	private float matchingCoefficientSimilarityScore;

		private Resource resource;	
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public float getSearchScore() {
		return searchScore;
	}

	public void setSearchScore(float searchScore) {
		this.searchScore = searchScore;
	}
	

	public float getCosineSimilarityScore() {
		return cosineSimilarityScore;
	}

	public void setCosineSimilarityScore(float cosineSimilarityScore) {
		this.cosineSimilarityScore = cosineSimilarityScore;
	}

	public float getStringCosineSimilarityScore() {
		return stringCosineSimilarityScore;
	}

	public void setStringCosineSimilarityScore(float stringCosineSimilarityScore) {
		this.stringCosineSimilarityScore = stringCosineSimilarityScore;
	}

	public float getJakardSimilarityScore() {
		return jakardSimilarityScore;
	}

	public void setJakardSimilarityScore(float jakardSimilarityScore) {
		this.jakardSimilarityScore = jakardSimilarityScore;
	}

	public float getDiceSimilarityScore() {
		return diceSimilarityScore;
	}

	public void setDiceSimilarityScore(float diceSimilarityScore) {
		this.diceSimilarityScore = diceSimilarityScore;
	}

	public float getEuclidianSimilarityScore() {
		return euclidianSimilarityScore;
	}

	public void setEuclidianSimilarityScore(float euclidianSimilarityScore) {
		this.euclidianSimilarityScore = euclidianSimilarityScore;
	}

	public float getMatchingCoefficientSimilarityScore() {
		return matchingCoefficientSimilarityScore;
	}

	public void setMatchingCoefficientSimilarityScore(
			float matchingCoefficientSimilarityScore) {
		this.matchingCoefficientSimilarityScore = matchingCoefficientSimilarityScore;
	}	


	public int compareTo(SearchResult searchResult) {
        if (this.searchScore == searchResult.getSearchScore())
            return 0;
        else if (this.searchScore < searchResult.getSearchScore())
            return 1;
        else
            return -1;
    }
	
	@Override
    public boolean equals(Object obj) {
       if (obj == null) {
            return false;
        }
        
        if(! (obj instanceof SearchResult)) {
            return false;
        }
        
        final SearchResult other = (SearchResult) obj;

        if (this.getResource().getId() != other.getResource().getId() &&  this.getQuery().equals(other.getQuery())) {
            return false;
        }
        return true;
    }

	public float getPersonalizedSearchScore() {
		return personalizedSearchScore;
	}

	public void setPersonalizedSearchScore(float personalizedSearchScore) {
		this.personalizedSearchScore = personalizedSearchScore;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	

	
	public static Comparator<SearchResult> SearchScoreComparator = new Comparator() {
	    public int compare(Object searchResult, Object anotherSearchResult) {
	      float searchScore1 = ((SearchResult) searchResult).getSearchScore();
	      float searchScore2 = ((SearchResult) anotherSearchResult).getSearchScore();
	      return new Float(searchScore2).compareTo(searchScore1);
	    }
	  };
	/**
	 * 
	 */
	public static Comparator<SearchResult> PersonalizedSearchScoreComparator = new Comparator() {
	    public int compare(Object searchResult, Object anotherSearchResult) {
	      float searchScore1 = ((SearchResult) searchResult).getPersonalizedSearchScore();
	      float searchScore2 = ((SearchResult) anotherSearchResult).getPersonalizedSearchScore();
	      return new Float(searchScore2).compareTo(searchScore1);
	    }
	  };
	  
	public static Comparator<SearchResult> CosineSimilarityScoreComparator = new Comparator() {
	    public int compare(Object searchResult, Object anotherSearchResult) {
	      float searchScore1 = ((SearchResult) searchResult).getCosineSimilarityScore();
	      float searchScore2 = ((SearchResult) anotherSearchResult).getCosineSimilarityScore();
	      return new Float(searchScore2).compareTo(searchScore1);
	    }
	  };
  
	public static Comparator<SearchResult> DiceSimilarityScoreComparator = new Comparator() {
	    public int compare(Object searchResult, Object anotherSearchResult) {
	      float searchScore1 = ((SearchResult) searchResult).getDiceSimilarityScore();
	      float searchScore2 = ((SearchResult) anotherSearchResult).getDiceSimilarityScore();
	      return new Float(searchScore2).compareTo(searchScore1);
	    }
	  };
	  
	public static Comparator<SearchResult> EuclidianSimilarityScoreComparator = new Comparator() {
	    public int compare(Object searchResult, Object anotherSearchResult) {
	      float searchScore1 = ((SearchResult) searchResult).getEuclidianSimilarityScore();
	      float searchScore2 = ((SearchResult) anotherSearchResult).getEuclidianSimilarityScore();
	      return new Float(searchScore2).compareTo(searchScore1);
	    }
	  };
		  
	public static Comparator<SearchResult> JakardSimilarityScoreComparator = new Comparator() {
	    public int compare(Object searchResult, Object anotherSearchResult) {
	      float searchScore1 = ((SearchResult) searchResult).getJakardSimilarityScore();
	      float searchScore2 = ((SearchResult) anotherSearchResult).getJakardSimilarityScore();
	      return new Float(searchScore2).compareTo(searchScore1);
	    }
	  };
	  
	public static Comparator<SearchResult> MatchingCoefficientSimilarityComparator = new Comparator() {
	    public int compare(Object searchResult, Object anotherSearchResult) {
	      float searchScore1 = ((SearchResult) searchResult).getMatchingCoefficientSimilarityScore();
	      float searchScore2 = ((SearchResult) anotherSearchResult).getMatchingCoefficientSimilarityScore();
	      return new Float(searchScore2).compareTo(searchScore1);
	    }
	  };	  

	  public static Comparator<SearchResult> StringCosineSimilarityScoreComparator = new Comparator() {
	    public int compare(Object searchResult, Object anotherSearchResult) {
	      float searchScore1 = ((SearchResult) searchResult).getStringCosineSimilarityScore();
	      float searchScore2 = ((SearchResult) anotherSearchResult).getStringCosineSimilarityScore();
	      return new Float(searchScore2).compareTo(searchScore1);
	    }
	  };

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}	  

  }
