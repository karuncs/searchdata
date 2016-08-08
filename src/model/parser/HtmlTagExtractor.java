package parser;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 
 * This class returns the content of a Html document.
 */

public class HtmlTagExtractor {
	
	public static void main(String[] args) {
		HtmlTagExtractor urbanParser = new HtmlTagExtractor();
		System.out.println(urbanParser.getRelatedTags("politics"));
	}

	
	public HtmlTagExtractor(){
		
	}
	
	static Set<String> relatedWords = new HashSet<String>();
	
		
	public Set<String> getRelatedTags(String query) {


		Parser parse;
		try {
					parse = new Parser("http://www.urbandictionary.com");

				NodeFilter filtro = new NodeClassFilter(Span.class); 
				NodeList nodeList = parse.extractAllNodesThatMatch(filtro); 
				if (nodeList != null && nodeList.size() > 0)
				{
					Span span = null;

				   for (int itr=0; itr<nodeList.size(); itr++)
				   {
					  span = (Span)nodeList.elementAt(itr);
				      String idAttribute = span.getAttribute("id");
				      if (idAttribute != null && idAttribute.equals("tags")){
				    	  System.out.println(span.toHtml());
				    	 System.out.println(span.getChild(itr).getText());
				    	  NodeList nodeListLink = span.getChildren();
				    		if (nodeListLink != null && nodeListLink.size() > 0){
				    			 for (int itr2=0; itr2<nodeListLink.size(); itr2++){
				    				 String term = nodeListLink.elementAt(itr2).toPlainTextString().toLowerCase().trim();
				    				 if (!term.equals("")) {
				    					 relatedWords.add(term);	
				    				 }
				    				 
				    			 }
				    		}
				      }
				}
				}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return relatedWords;
	}
}


