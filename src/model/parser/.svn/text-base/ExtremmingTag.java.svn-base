package parser;


import java.util.HashSet;
import java.util.Set;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 
 * This class returns the content of a Html document.
 */

public class ExtremmingTag {
	
	public ExtremmingTag(){
		
	}
	
	
	public static void main(String[] args) {

	}
	
	static Set<String> relatedWords = new HashSet<String>();
	
	/**
	 * @param first
	 * @param second
	 * @return
	 */
	public boolean isRelatedTerm(String first, String second){
		boolean exist = false;
		Set<String> relatedTerms = this.getRelatedTags(first);
		if (relatedTerms.contains(second)) {
			exist = true;
		}
		return exist;
	}
	
	
	
	public Set<String> getRelatedTags(String query) {
		//String tagQuery = "product";
	//}
	
	//public static void main(String[] args) {
		String tagQuery = query;
		Parser parse;
		try {
			parse = new Parser("http://delicious.com/popular/"+tagQuery);
		NodeFilter filtro = new NodeClassFilter(LinkTag.class); 
		NodeList nodeList = parse.extractAllNodesThatMatch(filtro); 
		if (nodeList != null && nodeList.size() > 0)
		{
			LinkTag tag = null;
		   for (int itr=0; itr<nodeList.size(); itr++)
		   {
			  tag = (LinkTag)nodeList.elementAt(itr);
		      String idAttribute = tag.getAttribute("class");
		      if (idAttribute != null && idAttribute.equals("showTag")){
		    	  for (int j = 0; j < tag.getChildCount(); j++) {
		    		  relatedWords.add(tag.getLinkText());
		    		  //System.out.println("Related Tags :"+tag.getLinkText());
				}
		      }
		  }
		}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		//System.out.println(tagQuery +" is related to tags "+relatedWords.toString());
		return relatedWords;
	}
}

