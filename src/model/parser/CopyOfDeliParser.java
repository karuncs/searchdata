package parser;

import org.htmlparser.Attribute;
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

public class CopyOfDeliParser {

	
	public static void main(String[] args) {
		int i=1;
		Parser parse;
		try {
			parse = new Parser("http://www.diigo.com/community/tag?tag=blog");
		NodeFilter filtro = new NodeClassFilter(Span.class); 
		NodeList nodeList = parse.extractAllNodesThatMatch(filtro); 
		if (nodeList != null && nodeList.size() > 0)
		{
			Span tag = null;
		   for (int itr=0; itr<nodeList.size(); itr++)
		   {
			  tag = (Span)nodeList.elementAt(itr);
			  System.out.println(tag);
			  String link = tag.getFirstChild().getText();
			  if (link.contains("photos/") && link.contains("title") ) {
				  System.out.println(link.substring(link.indexOf("photos/"),link.indexOf("/\"")));
			  }
		  }
		}
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
}

