package parser;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 
 * This class returns the content of a Html document.
 */

public class DeliParser {

	
	public static void main(String[] args) {
		int i=1;
		Parser parse;
		try {
			parse = new Parser("http://delicious.com/tag/filesystem");
		NodeFilter filtro = new NodeClassFilter(LinkTag.class); 
		NodeList nodeList = parse.extractAllNodesThatMatch(filtro); 
		if (nodeList != null && nodeList.size() > 0)
		{
			LinkTag tag = null;
		   for (int itr=0; itr<nodeList.size(); itr++)
		   {
			  tag = (LinkTag)nodeList.elementAt(itr);
		      String idAttribute = tag.getAttribute("class");
		      if (idAttribute != null && idAttribute.equals("taggedlink")){
		    	  i=1;
		    	  System.out.println(tag.getLink());
		    	  System.out.println(tag.getStringText());
		      }
		      if (idAttribute != null && (idAttribute.equals("user")||idAttribute.equals("user user-tag"))){
		    	  if (idAttribute.equals("user")){
		    		  System.out.println(tag.getChild(1).getText());  
		    	  }else{
			    	  Span span = (Span)tag.getFirstChild();
			    	  System.out.println(span.getFirstChild().getText().trim());		    		  
		    	  }
		      }
		      if (idAttribute != null && idAttribute.equals("tag-chain-item-link")){
		    	  Span span = (Span)tag.getFirstChild();
		    	  System.out.println("Tag  "+i+"  "+span.getFirstChild().getText().trim());
		    	  i++;
		      }		      
		  }
		}
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
}

