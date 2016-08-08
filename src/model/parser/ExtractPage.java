package parser;


import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.HeadingTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;



/**
 * 
 * This class returns the content of a Html document.
 */

public class ExtractPage {
	
	public ExtractPage(){
		
		
	}
	

	/**
	 * this method is used to execute the class
	 * @param args
	 */
	public static void main(String[] args) {
        //creating the instance of this class
		ExtractPage extractPage = new ExtractPage();
        
		// calling the getHTMLTags method         
		//extractPage.getHTMLTags();
		
		// calling the computeClassTitle method
        //extractPage.computeClassTitle();
		
		// calling the computeClassH1 method
		//extractPage.computeClassH1();
		
		// calling the computeClassH2 method
		//extractPage.computeClassH2();
		
		// calling the computeClassH3 method
        // extractPage.computeClassH3();
		
		// calling the computeClassH4 method
        //extractPage.computeClassH4();
		
		// calling the computeClassH5 method
        // extractPage.computeClassH5();
		
		// calling the computeClassH6 method
        // extractPage.computeClassH6();
         
        // calling the computeClassLinking  method
         extractPage.computeClassLinking();
		
//		for (String tags :  extractPage.getRelatedTags("web")) {
//			System.out.println(tags);
//		}
	}
	
	static Set<String> relatedWords = new HashSet<String>();
	
	public boolean isRelatedTerm(String first, String second){
		boolean exist = false;
		Set<String> relatedTerms = this.getRelatedTags(first);
		if (relatedTerms.contains(second)) {
			exist = true;
		}
		return exist;
	}
	
	
	
	/**
	 * It parses the URL, get the node form the page, and print out all the nodes.
	 * @param query
	 */
	public void getHTMLTags() {

		Parser parse;
		try {
			parse = new Parser("http://www.cs.aau.dk/en/welcome/");

			Node node;
		
			NodeIterator nodeListIterator = (NodeIterator)parse.elements(); 
			
			while ( nodeListIterator.hasMoreNodes()) {
				node = (Node)nodeListIterator.nextNode();
			    System.out.println(node.toHtml());
			}			
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * It parses the URL, get the element of "title" form the HTML page, and print out all the nodes.	 
	 */
	public int computeClassTitle() {
		Parser parse;
		int amountTitle= 0;
		int amountMeta= 0;
		int amountMetaKeywords= 0;
		NodeFilter filter = null; 
		NodeList nodeList = null; 		
		try {
			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
			
			filter = new NodeClassFilter(TitleTag.class); 
			nodeList = parse.extractAllNodesThatMatch(filter); 
			
			amountTitle= nodeList.size();
			
			if (nodeList != null && nodeList.size() > 0){
			   for (int itr=0; itr<nodeList.size(); itr++){
				  TitleTag titleTag = (TitleTag)nodeList.elementAt(itr);
				    //System.out.println(titleTag);
				    //System.out.println(titleTag.toHtml());
			  }
			}
			
		
			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
			filter = new NodeClassFilter(MetaTag.class); 
			nodeList = parse.extractAllNodesThatMatch(filter); 
			
			amountMeta = nodeList.size();
			int keywordCounter = 0;
			if (nodeList != null && nodeList.size() > 0){
			   for (int itr=0; itr<nodeList.size(); itr++){
				   MetaTag metaTag = (MetaTag)nodeList.elementAt(itr);
				   String keyword = metaTag.getAttribute("keyword");
				   if (keyword!=null) {
					   keywordCounter++;
				   }
				    //System.out.println(metaTag);
				    //System.out.println(metaTag.toHtml());
			  }
			}			
		
			System.out.println("We have "+amountTitle+" title "+ amountMeta+" meta and "+ keywordCounter +" keywords.");
		
		
		    } catch (ParserException e) {
				e.printStackTrace();
			}
		

		return amountTitle;
	}
	
	
	
	/**
	 * It parses the URL, get the element of "H1" form the HTML page, and print out all the nodes.	 
	 */
	public int computeClassH1() {
		Parser parse;
		int amountOfH1 = 0;
		int amountMeta= 0;
		int amountMetaKeywords= 0;
		NodeFilter filter = null; 
		NodeList nodeList = null; 		
		try {
			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
			
			filter = new NodeClassFilter(HeadingTag.class); 
			nodeList = parse.extractAllNodesThatMatch(filter); 
			
			amountOfH1 = nodeList.size();
			int counterH1 = 0;
			int counterH2 = 0;
			if (nodeList != null && nodeList.size() > 0){
			   for (int itr=0; itr<nodeList.size(); itr++){
				   HeadingTag headingTag = (HeadingTag)nodeList.elementAt(itr);
				   String[] ids= headingTag.getIds();
				   for (int i = 0; i < ids.length; i++) {
					   if (ids[i].equals("H1")) {
						   counterH1++;
					   }else if (ids[i].equals("H2")) {
						   counterH2++;
					   }
					System.out.println(ids[i]);
				}
				   
				    //System.out.println(headingTag.toHtml());
			  }
			}
			
		
			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
			filter = new NodeClassFilter(MetaTag.class); 
			nodeList = parse.extractAllNodesThatMatch(filter); 
			
			amountMeta = nodeList.size();
			int keywordCounter = 0;
			if (nodeList != null && nodeList.size() > 0){
			   for (int itr=0; itr<nodeList.size(); itr++){
				   MetaTag metaTag = (MetaTag)nodeList.elementAt(itr);
				   String keyword = metaTag.getAttribute("keyword");
				   if (keyword!=null) {
					   keywordCounter++;
				   }
				    //System.out.println(metaTag);
				    //System.out.println(metaTag.toHtml());
			  }
			}			
		
			System.out.println("We have "+amountOfH1 +" head "+ amountMeta+" meta and "+ keywordCounter +" keywords.");
		
		
		    } catch (ParserException e) {
				e.printStackTrace();
			}
		

		return amountOfH1 ;
	}
	
	
	
//	public int computeClassH2() {
//		Parser parse;
//		int amountOfH2 = 0;
//		int amountMeta= 0;
//		int amountMetaKeywords= 0;
//		NodeFilter filter = null; 
//		NodeList nodeList = null; 		
//		try {
//			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
//			
//			filter = new NodeClassFilter(HeadingTag.class); 
//			nodeList = parse.extractAllNodesThatMatch(filter); 
//			
//			amountOfH2 = nodeList.size();
//			int counterH2 = 0;
//			if (nodeList != null && nodeList.size() > 0){
//			   for (int itr=0; itr<nodeList.size(); itr++){
//				   HeadingTag headingTag = (HeadingTag)nodeList.elementAt(itr);
//				   String[] ids= headingTag.getIds();
//				   for (int i = 0; i < ids.length; i++) {
//					   if (ids[i].equals("H1")) {
//						   counterH2++;
//		//			   }else if (ids[i].equals("H2")) {
//		//				   counterH2++;
//					   }
//					System.out.println(ids[i]);
//				}
//				   
//				    //System.out.println(headingTag.toHtml());
//			  }
//			
//			
//		
//			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
//			filter = new NodeClassFilter(MetaTag.class); 
//			nodeList = parse.extractAllNodesThatMatch(filter); 
//			
//			amountMeta = nodeList.size();
//			int keywordCounter = 0;
//			if (nodeList != null && nodeList.size() > 0){
//			   for (int itr=0; itr<nodeList.size(); itr++){
//				   MetaTag metaTag = (MetaTag)nodeList.elementAt(itr);
//				   String keyword = metaTag.getAttribute("keyword");
//				   if (keyword!=null) {
//					   keywordCounter++;
//				   }
//				    //System.out.println(metaTag);
//				    //System.out.println(metaTag.toHtml());
//			  }
//			}			
//		
//			System.out.println("We have "+amountOfH2 +" H2 ");
//		
//		
//		    } catch (ParserException e) {
//				e.printStackTrace();
//			}
//		
//
//		return amountOfH2 ;
//	}
	
	
	
	
	/**
	 * It parses the URL, get the element of "H3" form the HTML page, and print out all the nodes.
	 * @return
	 */
	public int computeClassH3() {
		Parser parse;
		int amountOfH3 = 0;
		int amountMeta= 0;
		int amountMetaKeywords= 0;
		NodeFilter filter = null; 
		NodeList nodeList = null; 		
		try {
			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
			
			filter = new NodeClassFilter(HeadingTag.class); 
			nodeList = parse.extractAllNodesThatMatch(filter); 
			
			amountOfH3 = nodeList.size();
			int counterH3 = 0;
		//	int counterH2 = 0;
			if (nodeList != null && nodeList.size() > 0){
			   for (int itr=0; itr<nodeList.size(); itr++){
				   HeadingTag headingTag = (HeadingTag)nodeList.elementAt(itr);
				   String[] ids= headingTag.getIds();
				   for (int i = 0; i < ids.length; i++) {
					   if (ids[i].equals("H1")) {
						   counterH3++;
		//			   }else if (ids[i].equals("H2")) {
		//				   counterH3++;
					   }
					System.out.println(ids[i]);
				}
				   
				    //System.out.println(headingTag.toHtml());
			  }
			}
			
		
			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
			filter = new NodeClassFilter(MetaTag.class); 
			nodeList = parse.extractAllNodesThatMatch(filter); 
			
			amountMeta = nodeList.size();
			int keywordCounter = 0;
			if (nodeList != null && nodeList.size() > 0){
			   for (int itr=0; itr<nodeList.size(); itr++){
				   MetaTag metaTag = (MetaTag)nodeList.elementAt(itr);
				   String keyword = metaTag.getAttribute("keyword");
				   if (keyword!=null) {
					   keywordCounter++;
				   }
				    //System.out.println(metaTag);
				    //System.out.println(metaTag.toHtml());
			  }
			}			
		
			System.out.println("We have "+amountOfH3 +" H3 ");
		
		
		    } catch (ParserException e) {
				e.printStackTrace();
			}
		

		return amountOfH3 ;
	}
	
//	
//	public int computeClassH4() {
//		Parser parse;
//		int amountOfH4 = 0;
//		int amountMeta= 0;
//		int amountMetaKeywords= 0;
//		NodeFilter filter = null; 
//		NodeList nodeList = null; 		
//		try {
//			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
//			
//			filter = new NodeClassFilter(HeadingTag.class); 
//			nodeList = parse.extractAllNodesThatMatch(filter); 
//			
//			amountOfH4 = nodeList.size();
//			int counterH4 = 0;
//		//	int counterH2 = 0;
//			if (nodeList != null && nodeList.size() > 0){
//			   for (int itr=0; itr<nodeList.size(); itr++){
//				   HeadingTag headingTag = (HeadingTag)nodeList.elementAt(itr);
//				   String[] ids= headingTag.getIds();
//				   for (int i = 0; i < ids.length; i++) {
//					   if (ids[i].equals("H1")) {
//						   counterH4++;
//				//	   }else if (ids[i].equals("H2")) {
//				//		   counterH4++;
//					   }
//					System.out.println(ids[i]);
//				}
//				   
//				    //System.out.println(headingTag.toHtml());
//			  }
//			}
//			
//		
//			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
//			filter = new NodeClassFilter(MetaTag.class); 
//			nodeList = parse.extractAllNodesThatMatch(filter); 
//			
//			amountMeta = nodeList.size();
//			int keywordCounter = 0;
//			if (nodeList != null && nodeList.size() > 0){
//			   for (int itr=0; itr<nodeList.size(); itr++){
//				   MetaTag metaTag = (MetaTag)nodeList.elementAt(itr);
//				   String keyword = metaTag.getAttribute("keyword");
//				   if (keyword!=null) {
//					   keywordCounter++;
//				   }
//				    //System.out.println(metaTag);
//				    //System.out.println(metaTag.toHtml());
//			  }
//			}			
//		
//			System.out.println("We have "+amountOfH4 +" H4 ");
//		
//		
//		    } catch (ParserException e) {
//				e.printStackTrace();
//			}
//		
//
//		return amountOfH4 ;
//	}
//	
//	
//	public int computeClassH5() {
//		Parser parse;
//		int amountOfH5 = 0;
//		int amountMeta= 0;
//		int amountMetaKeywords= 0;
//		NodeFilter filter = null; 
//		NodeList nodeList = null; 		
//		try {
//			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
//			
//			filter = new NodeClassFilter(HeadingTag.class); 
//			nodeList = parse.extractAllNodesThatMatch(filter); 
//			
//			amountOfH5 = nodeList.size();
//			int counterH5 = 0;
//		//	int counterH2 = 0;
//			if (nodeList != null && nodeList.size() > 0){
//			   for (int itr=0; itr<nodeList.size(); itr++){
//				   HeadingTag headingTag = (HeadingTag)nodeList.elementAt(itr);
//				   String[] ids= headingTag.getIds();
//				   for (int i = 0; i < ids.length; i++) {
//					   if (ids[i].equals("H1")) {
//						   counterH5++;
//			//		   }else if (ids[i].equals("H2")) {
//			//			   counterH2++;
//					   }
//					System.out.println(ids[i]);
//				}
//				   
//				    //System.out.println(headingTag.toHtml());
//			  }
//			}
//			
//		
//			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
//			filter = new NodeClassFilter(MetaTag.class); 
//			nodeList = parse.extractAllNodesThatMatch(filter); 
//			
//			amountMeta = nodeList.size();
//			int keywordCounter = 0;
//			if (nodeList != null && nodeList.size() > 0){
//			   for (int itr=0; itr<nodeList.size(); itr++){
//				   MetaTag metaTag = (MetaTag)nodeList.elementAt(itr);
//				   String keyword = metaTag.getAttribute("keyword");
//				   if (keyword!=null) {
//					   keywordCounter++;
//				   }
//				    //System.out.println(metaTag);
//				    //System.out.println(metaTag.toHtml());
//			  }
//			}			
//		
//			System.out.println("We have "+amountOfH5 +" H5 ");
//		
//		
//		    } catch (ParserException e) {
//				e.printStackTrace();
//			}
//		
//
//		return amountOfH5 ;
//	}
//	
//	
//	
	public int computeClassH6() {
		Parser parse;
		int amountOfH6 = 0;
		int amountMeta= 0;
		int amountMetaKeywords= 0;
		NodeFilter filter = null; 
		NodeList nodeList = null; 		
		try {
			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
			
			filter = new NodeClassFilter(HeadingTag.class); 
			nodeList = parse.extractAllNodesThatMatch(filter); 
			
			amountOfH6 = nodeList.size();
			int counterH6 = 0;
		//	int counterH2 = 0;
			if (nodeList != null && nodeList.size() > 0){
			   for (int itr=0; itr<nodeList.size(); itr++){
				   HeadingTag headingTag = (HeadingTag)nodeList.elementAt(itr);
				   String[] ids= headingTag.getIds();
				   for (int i = 0; i < ids.length; i++) {
					   if (ids[i].equals("H6")) {
						   counterH6++;
		//			   }else if (ids[i].equals("H2")) {
		//				   counterH2++;
					   }
					System.out.println(ids[i]);
				}
				   
				    //System.out.println(headingTag.toHtml());
			  }
			}
			
		
			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
			filter = new NodeClassFilter(MetaTag.class); 
			nodeList = parse.extractAllNodesThatMatch(filter); 
			
			amountMeta = nodeList.size();
			int keywordCounter = 0;
			if (nodeList != null && nodeList.size() > 0){
			   for (int itr=0; itr<nodeList.size(); itr++){
				   MetaTag metaTag = (MetaTag)nodeList.elementAt(itr);
				   String keyword = metaTag.getAttribute("keyword");
				   if (keyword!=null) {
					   keywordCounter++;
				   }
				    //System.out.println(metaTag);
				    //System.out.println(metaTag.toHtml());
			  }
			}			
		
			System.out.println("We have "+amountOfH6 +" H6 ");
		
		
		    } catch (ParserException e) {
				e.printStackTrace();
			}
		

		return amountOfH6 ;
	}
	
	
	
	
	
	public int computeClassLinking() {
		Parser parse;
		int amountOfLinking = 0;
		NodeFilter filter = null; 
		NodeList nodeList = null; 		
		try {
			parse = new Parser("http://www.cs.aau.dk/en/welcome/");
			
			filter = new NodeClassFilter(LinkTag.class); 
			nodeList = parse.extractAllNodesThatMatch(filter); 
			
			amountOfLinking = nodeList.size();
				
			System.out.println("We have "+amountOfLinking + " Linking. " );
		
		
		    } catch (ParserException e) {
				e.printStackTrace();
			}
		

		return amountOfLinking ;
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

