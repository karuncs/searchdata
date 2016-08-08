package parser;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;


public class SimParser {
	
	public static String GLOSS = "GLOSS";
	
	public static String WUP = "WUP";
	
	public static String PATH_LENTH = "PATH_LENTH";

	public static String LIN = "LIN";

	public static void main(String[] args) {
		String s1 = "life";
		String s2 = "death";
		SimParser simParser = new SimParser();
		System.out.println(simParser.getSimScore(s1,s2,GLOSS));
		System.out.println(simParser.getSimScore(s1,s2,WUP));
		System.out.println(simParser.getSimScore(s1,s2,PATH_LENTH));
		System.out.println(simParser.getSimScore(s1,s2,LIN));
	}

	/**
	 * @param word1
	 * @param word2
	 * @param type
	 * @return
	 */
	public float getSimScore(String word1, String word2, String type) {
		String init = "is";
		float finalScore = 0f;
		String x = "http://marimba.d.umn.edu/cgi-bin/similarity/similarity.cgi?word1="+word1+"&senses1=all&word2="+word2+"&senses2=all&measure=vector";
		Parser parse;
		try {
			if (type.equals(GLOSS)) {
				x = "http://marimba.d.umn.edu/cgi-bin/similarity/similarity.cgi?word1="+word1+"&senses1=all&word2="+word2+"&senses2=all&measure=vector";
			}else if (type.equals(WUP)) {
				x = "http://marimba.d.umn.edu/cgi-bin/similarity/similarity.cgi?word1="+word1+"&senses1=all&word2="+word2+"&senses2=all&measure=wup&rootnode=yes";
			}else if (type.equals(PATH_LENTH)) {
				x = "http://marimba.d.umn.edu/cgi-bin/similarity/similarity.cgi?word1="+word1+"&senses1=all&word2="+word2+"&senses2=all&&measure=path&rootnode=yes";
			}else if (type.equals(LIN)) {
				x = "http://marimba.d.umn.edu/cgi-bin/similarity/similarity.cgi?word1="+word1+"&senses1=all&word2="+word2+"&senses2=all&&measure=lin&rootnode=yes";
			}

		parse = new Parser(x);
		NodeFilter filtro = new NodeClassFilter(ParagraphTag.class); 
		NodeList nodeList = parse.extractAllNodesThatMatch(filtro); 
		if (nodeList != null && nodeList.size() > 0)
		{
			ParagraphTag tag = null;
		   for (int itr=0; itr<nodeList.size(); itr++)
		   {
			  tag = (ParagraphTag)nodeList.elementAt(itr);
		      String idAttribute = tag.getAttribute("class");
		      if (idAttribute != null && idAttribute.equals("results")){
		    	 String textLine = tag.getChildrenHTML();
		    	String score = textLine.substring(textLine.indexOf(init)+2,textLine.length()-1);
		    	  finalScore = new Float(score.trim());		
		    	  
		    	  /*	for (int j = 0; j < tag.getChildCount(); j++) {
		    		  //relatedWords.add(tag.getLinkText());
		    		  
		    		  System.out.println("Related Tags :"+tag.getChildrenHTML());
		    		  System.out.println("Related Tags :"+tag.getClass());
				}*/
		      }
		  }
		}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		
		return finalScore;
	}
}

