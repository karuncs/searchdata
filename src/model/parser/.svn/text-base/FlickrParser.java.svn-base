package parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import aau.Resource;
import aau.Tag;
import aau.User;

/**
 * 
 * This class returns the content of a Html document.
 */

public class FlickrParser {

	public static void main(String[] args) {
		
		for (Resource kiWiResource : extractKiWI()) {
			System.out.println(kiWiResource.getDescription());
			System.out.println(kiWiResource.getLink());
			System.out.println(kiWiResource.getAuthor().getName());
			for (Tag tag : kiWiResource.getTags()) {
				System.out.println(tag.getTagName());
			}
		}
	}
	
	public static Set<Resource> extractKiWI() {
		 Resource kiWiResource =null;
		 
		 Set<Resource> kiwiAllResources = new HashSet<Resource>();
		 
		 Set<String> repTags = new HashSet<String>();
	
		Set<String> links = extractLinks();

		Parser parse;
		try {
			for (String link : links) {
				//System.out.println(link);
				String user = link.substring(link.lastIndexOf("photos/"),link.lastIndexOf("/")).replace("photos/", "");
				User userKiWi = new User(user,user+"@email.com");
				kiWiResource = new Resource();
				kiWiResource.setAuthor(userKiWi);
				kiWiResource.setLink(link);
				parse = new Parser(link);
				
				NodeFilter filtroTiTle = new NodeClassFilter(TitleTag.class); 
				NodeList nodeListTitle = parse.extractAllNodesThatMatch(filtroTiTle); 
				if (nodeListTitle != null && nodeListTitle.size() > 0)
				{
					TitleTag titleTag = null;
					for (int itr=0; itr<nodeListTitle.size(); itr++)
					{
						titleTag = (TitleTag)nodeListTitle.elementAt(itr);
						String titleKiwi = titleTag.getStringText().replace("on Flickr - Photo Sharing!", "");
						kiWiResource.setDescription(titleKiwi);
					}
					
				}
				
				parse = new Parser(link);
				NodeFilter filtro = new NodeClassFilter(MetaTag.class); 
				NodeList nodeList = parse.extractAllNodesThatMatch(filtro); 
				if (nodeList != null && nodeList.size() > 0)
				{
					MetaTag tag = null;
					for (int itr=0; itr<nodeList.size(); itr++)
					{
						tag = (MetaTag)nodeList.elementAt(itr);
						String idAttribute = tag.getAttribute("name");
						if (idAttribute != null && idAttribute.equals("keywords")){
							if (!repTags.contains(tag.getMetaContent())) {
								StringTokenizer stringTokenizer = new StringTokenizer(tag.getMetaContent(),",");
								while (stringTokenizer.hasMoreElements()) {
									String tagUnitelem = (String) stringTokenizer.nextElement();
									kiWiResource.addTag(tagUnitelem.trim());
									repTags.add(tagUnitelem.trim());
								}
								
//								Tag tagKiWi = new Tag(tag.getMetaContent(),userKiWi);
//								tagsKiWi.add(tagKiWi);
							}
							//System.out.println(tag.getMetaContent());
						}
					}
					
				}
				
				 
				 kiwiAllResources.add(kiWiResource);
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return kiwiAllResources;
	}
	
	
	public static void extractTags() {
		
		
		Set<String> links = extractLinks();
		
		Parser parse;
		try {
			for (String link : links) {

				parse = new Parser(link);
				
				NodeFilter filtro = new NodeClassFilter(MetaTag.class); 
				NodeList nodeList = parse.extractAllNodesThatMatch(filtro); 
				if (nodeList != null && nodeList.size() > 0)
				{
					MetaTag tag = null;
					for (int itr=0; itr<nodeList.size(); itr++)
					{
						tag = (MetaTag)nodeList.elementAt(itr);
						String idAttribute = tag.getAttribute("name");
						if (idAttribute != null && idAttribute.equals("keywords")){
							System.out.println(tag.getMetaContent());
						}
					}
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @return
	 */
	public static Set<String> extractLinks () {
		Set<String> links = new HashSet<String>();
		Parser parse;
		
		List<String> ss = new ArrayList<String>(loatDigTags());
		try {
			for (String tagLoad : ss.subList(0,2)) {
			
				parse = new Parser("http://www.flickr.com/photos/tags/"+tagLoad);
			NodeFilter filtro = new NodeClassFilter(Span.class); 
			NodeList nodeList = parse.extractAllNodesThatMatch(filtro); 
			if (nodeList != null && nodeList.size() > 0)
			{
				Span tag = null;
			   for (int itr=0; itr<nodeList.size(); itr++)
			   {
				  tag = (Span)nodeList.elementAt(itr);
				  String link = tag.getFirstChild().getText();
				  if (link.contains("photos/") && link.contains("title") ) {
					  //System.out.println(link.substring(link.indexOf("photos/"),link.indexOf("/\"")));
					  links.add("http://www.flickr.com/" +link.substring(link.indexOf("photos/"),link.indexOf("/\"")));
				  }
			  }
			}
		
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return links;
	}
	
	
	/**
	 * @return
	 */
	public static Set<String> loatDigTags () {
		Set<String> tagsD = new HashSet<String>();
		tagsD.add("software");
		tagsD.add("internet");
		tagsD.add("book");
		tagsD.add("security");
		tagsD.add("collaboration");
		tagsD.add("education");
		tagsD.add("online");
		tagsD.add("tutorials");
		tagsD.add("social");
		tagsD.add("network");
		
		
		tagsD.add("tech");
		tagsD.add("research");
		tagsD.add("photo");
		tagsD.add("mobile");
		tagsD.add("microsoft");
		tagsD.add("website");
		tagsD.add("flash");
		tagsD.add("annotation");
		tagsD.add("technology");
		tagsD.add("php");
		tagsD.add("quickd");
		tagsD.add("webdesign");
		tagsD.add("networking");
		tagsD.add("library");
		tagsD.add("maps");
		
//		
		tagsD.add("blogging");
		tagsD.add("podcast");
		tagsD.add("culture");
		tagsD.add("life");
		tagsD.add("guide");
		tagsD.add("windows");
		tagsD.add("google");
		tagsD.add("search");
		tagsD.add("wiki");
		tagsD.add("graphics");
//		
		
		tagsD.add("howto");
		tagsD.add("programming");
		tagsD.add("health");
		tagsD.add("mp3");
		tagsD.add("flickr");
		tagsD.add("article");
		tagsD.add("javascript");
		tagsD.add("writing");
		tagsD.add("calendar");
		tagsD.add("audio");
		tagsD.add("tv");
		tagsD.add("download");
		tagsD.add("science");
		tagsD.add("government");
		tagsD.add("plugin");
		 
		
////		
////		
		tagsD.add("photography");
		tagsD.add("wordpress");
		tagsD.add("food");
		tagsD.add("learning");
		tagsD.add("photos");
		tagsD.add("resources");
		tagsD.add("productivity");
		tagsD.add("techcrunch");
		tagsD.add("recipes");
		tagsD.add("ipod");
		tagsD.add("election");
		tagsD.add("diet");
		tagsD.add("lifehacker");
		tagsD.add("inspiration");
		tagsD.add("blog");
		tagsD.add("design");
		tagsD.add("fashion");
		tagsD.add("html");
		tagsD.add("web");
		tagsD.add("opensource");
		tagsD.add("tools");
		tagsD.add("ajax");
		tagsD.add("java");
		tagsD.add("tutorial");
		tagsD.add("travel");
		tagsD.add("game");
		tagsD.add("politics");
		tagsD.add("community");
		tagsD.add("magazine");
		tagsD.add("review");
		tagsD.add("video");
		tagsD.add("storage");
		tagsD.add("css");
		tagsD.add("hosting");
		tagsD.add("mail");
		tagsD.add("computer");
		tagsD.add("entertainment");
		tagsD.add("mac");
		tagsD.add("forum");
		tagsD.add("shopping");
		tagsD.add("e-learning");
		tagsD.add("humor");
		tagsD.add("sports");
		tagsD.add("hardware");
		tagsD.add("hdr");
		tagsD.add("online");
		tagsD.add("photography");
		tagsD.add("photo");
		tagsD.add("tools");
		tagsD.add("gmail");
		tagsD.add("google");
		tagsD.add("themes");
		tagsD.add("design");
		tagsD.add("theme");
		tagsD.add("iphone");
		tagsD.add("javascript");
		tagsD.add("development");
		tagsD.add("programming");
		tagsD.add("library");
		tagsD.add("culture");
		tagsD.add("europe");
		tagsD.add("europa");
		tagsD.add("art");
		tagsD.add("library");
		tagsD.add("culture");
		tagsD.add("europe");
		tagsD.add("digital");
		tagsD.add("twitter");
		tagsD.add("search");
		tagsD.add("tagging");
		tagsD.add("tools");
		tagsD.add("twitter");
		tagsD.add("tools");
		tagsD.add("web2.0");
		tagsD.add("socialmedia");
		tagsD.add("social");
		tagsD.add("vista");
		tagsD.add("windows");
		tagsD.add("tweak");

		tagsD.add("utilities");
		tagsD.add("windows");
		tagsD.add("tips");
		tagsD.add("menu");
		tagsD.add("howto");
		tagsD.add("utilities");
		tagsD.add("religion");
		tagsD.add("humor");
		tagsD.add("funny");
		tagsD.add("atheism");
		tagsD.add("satire");
		tagsD.add("design");
		tagsD.add("blog");
		tagsD.add("video");
		
		
		tagsD.add("music");
		tagsD.add("programming");
		tagsD.add("webdesign");
		tagsD.add("reference");
		tagsD.add("tutorial");
		tagsD.add("art");
		tagsD.add("development");
		tagsD.add("google");
		tagsD.add("inspiration");
		tagsD.add("photography");
		tagsD.add("news");
		tagsD.add("food");
		tagsD.add("flash");
		tagsD.add("css");
		tagsD.add("blogs");
		tagsD.add("education");
		tagsD.add("business");
		tagsD.add("technology");
		tagsD.add("travel");

		tagsD.add("home");
		tagsD.add("plan");
		tagsD.add("agriculture");
		tagsD.add("cards");
		tagsD.add("sale");
		tagsD.add("opportunity");
		tagsD.add("management");
		tagsD.add("school");
		tagsD.add("supplies");
		tagsD.add("free");
		tagsD.add("bureau");
		tagsD.add("homebased");
		tagsD.add("new");
		tagsD.add("credit");
		tagsD.add("development");
		tagsD.add("start");
		tagsD.add("marketing");
		tagsD.add("loans");
		tagsD.add("directory");
		tagsD.add("insurance");
		tagsD.add("international");
		tagsD.add("welding");
		tagsD.add("technology");
		tagsD.add("letter");
		tagsD.add("analyst");
		tagsD.add("best");
		tagsD.add("manufacturing");
		tagsD.add("sell");
		tagsD.add("own");
		tagsD.add("retail");
		tagsD.add("travel");

		tagsD.add("solutions");
		tagsD.add("process");
		tagsD.add("printing");
		tagsD.add("intelligence");
		tagsD.add("ideas");
		tagsD.add("finance");
		tagsD.add("class");
		tagsD.add("administration");

		tagsD.add("creditcard");
		
		tagsD.add("email");
		tagsD.add("franchise");
		tagsD.add("grants");
		tagsD.add("hosting");
		tagsD.add("money");
		tagsD.add("name");
		tagsD.add("objects");
		tagsD.add("phone");
		tagsD.add("plans");
		tagsD.add("supply");
		tagsD.add("systems");
		tagsD.add("women");
		tagsD.add("man");
		tagsD.add("phone");
		tagsD.add("degree");
		tagsD.add("harvard");
		tagsD.add("webhosting");
		tagsD.add("tax");
		tagsD.add("studies");
		tagsD.add("strategy");
		tagsD.add("server");
		tagsD.add("review");
		tagsD.add("productivity");
		tagsD.add("law");
		tagsD.add("consulting");
		tagsD.add("consultants");
		tagsD.add("india");
		tagsD.add("writing");
		tagsD.add("banking");
		tagsD.add("ethics");
		tagsD.add("list");
		tagsD.add("machines");
		tagsD.add("network");
		tagsD.add("planning");
		tagsD.add("report");
		tagsD.add("search");
		tagsD.add("template");
		tagsD.add("training");
		tagsD.add("week");
		tagsD.add("writing");
		tagsD.add("banking");
		tagsD.add("training");
		tagsD.add("china");
		tagsD.add("analysis");
		tagsD.add("association");
		tagsD.add("communication");
		tagsD.add("financing");
		tagsD.add("forms");
		tagsD.add("idea");
		tagsD.add("letters");
		tagsD.add("lyrics");
		tagsD.add("magazine");
		tagsD.add("mail");
		tagsD.add("fice");
		tagsD.add("music");
		tagsD.add("credit");
		tagsD.add("grants");
		tagsD.add("insurance");
		tagsD.add("advertising");
		tagsD.add("analystjobs");
		tagsD.add("brokers");
		tagsD.add("cleaning");
		tagsD.add("continuity");
		tagsD.add("edition");
		tagsD.add("enterprise");
		tagsD.add("real");
		tagsD.add("leads");
		tagsD.add("market");
		tagsD.add("proposal");
		tagsD.add("trades");
		tagsD.add("website");
		tagsD.add("week");
		tagsD.add("global");
		tagsD.add("cleaning");
		tagsD.add("find");
		tagsD.add("selling");
		tagsD.add("mlm");
		tagsD.add("starting");
		tagsD.add("hotel");
		tagsD.add("cash");
		tagsD.add("local");		
		return tagsD;
	}
	
	/**
	 * @return
	 */
	public static Set<String> loatTags () {
		Set<String> uris = new HashSet<String>();
	
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
	
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.01733432092527054");
		                                                                                                                                       
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                                                                                                                                        
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.36007862759017006");
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                                                                                                                                        
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7192320341328932");
		                                                                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                                                                                                                                        
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8044467916460822");
		                                                                                                                                             
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                                                                                                                                        
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.027022955854742126");
		                                                                                                                                  
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                                                                                                                                        
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.41654435463449835");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.461781756089373");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.27803089397869174");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9101177328692639");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8791924982311842");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.11885278566016266");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8700903603642616");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.11823145369206989");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.2440028627706491");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.834441209242727");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3152809854037255");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7067287954409042");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5974633805409052");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8148366185492019");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.29445923029672094");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5515666483127698");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.025179042848588074");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081816250");
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8995399450894378");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.35674189937862144");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8687536044019672");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.752137015602824");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.10104059496555118");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5971586581385103");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9043500027582971");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		 
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081823593");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8023688734279862");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081823593");
		                                                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.13253601347534538");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081823593");
		                                                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8442764471256616");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081823593");
		                                                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.09332991045471539");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081823593");
		                                                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.12065462677230032");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081823593");
		                                                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5620889352762976");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081823593");
		                                                                                                     
		 
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8198492985729846");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.46849752595364047");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.01959782333953486");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5759102076928992");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.4919665101668569");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.6018221733457384");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.6446151816799813");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8808110457992652");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.27679378223534523");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.04610244219154558");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.4079402278342331");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.6468944124573127");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081825187");
		                                                                                         
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.21653744159964616");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3181255137647343");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8116689932677742");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.13110827052034502");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.10733177508582292");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.29066228775604486");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3335931702645908");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		 
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.6410527158950776");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.12262412524074617");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.054816716813386734");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.21141656674211518");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.6411078987705389");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.08293116667877776");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.19285632354922144");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8296213801828375");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9187066667704339");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.6020301373429319");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7753224536497442");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.33829180810109427");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		                                                                                                   
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.1502391443932649");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081829937");
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.21951032719497787");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8993437931880731");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.06182724418384522");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3755706141030115");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3026128978671314");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5660140042537286");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9348717790076638");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9685095538743901");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.31196154961386446");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5997407489840997");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5815489922565055");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7735288476778098");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9265855445896269");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8943137039046949");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.2862795329704405");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5020526077659866");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.02991774127849267");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.18177043242222435");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.42558890999591736");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.42926074152971205");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.2454337099473458");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5457834848974003");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.34568114741247435");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3792877891957652");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8649049313648643");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.059991538246729426");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.27645738223582184");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3763607718143229");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5298429948223711");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.21035194706155014");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3327537092619265");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5370698584404807");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.44388724764729126");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.35782245023884085");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                                           
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8543617559860195");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                                           
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3572445079794154");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                                           
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.02870747511862448");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                                           
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.6359368026982273");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                                           
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.637912912497643");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                                           
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.6714522300769523");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                                           
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7560274043318297");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                                           
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9760717313875725");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                                           
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.38512169337729685");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                                           
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.09616273644699935");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081841359");
		                                                                                           
		
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081843859");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7291843125537859");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081843859");
		                                                                                          
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7704767376509484");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081843859");
		                                                                                          
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7532942814572378");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081843859");
		                                                                                          
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.010876317515822964");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081843859");
		                                                                                          
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9388680742688092");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081843859");
		                                                                                          
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.07720597108626248");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081843859");
		                                                                                          
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9594321007870859");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081843859");
		                                                                                          
		
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8198703154094922");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.38312673809208286");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9537225692333926");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3690611024983348");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.44353083589742404");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.1924997890084018");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.226197572088085");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081847265");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8500214141267188");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081847265");
		                                                                                             
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.49182289077085084");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081847265");
		                                                                                             
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.6454125328566446");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081847265");
		                                                                                             
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8928407803830606");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081847265");
		                                                                                             
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8043355932928914");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081847265");
		                                                                                             
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3998828123459953");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081847265");
		                                                                                             
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.284476298580321");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081847265");
		                                                                                             
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7368177962480298");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081847265");
		                                                                                             
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.06629824421179231");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081847265");
		                                                                                             
		
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081849453");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7009967966590082");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081849453");
		                                                                                            
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.2493712097558508");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081849453");
		                                                                                            
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3544353439920901");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081849453");
		                                                                                            
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.39541974992737183");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081849453");
		                                                                                            
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.4391609874854485");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081849453");
		                                                                                            
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.1574895970694563");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081849453");
		                                                                                            
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.14936421242381626");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081849453");
		                                                                                            
		
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.6911139468132207");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.510201741223937");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.10443472029675427");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.002348307231574087");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.3799964188178995");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9110818864745153");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.10967288997873093");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.5931808277380671");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.4084457164935076");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081836656");
		                                                                                              
		
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9896480156223333");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.11414581772769317");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.4540605162852088");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.8581668158694561");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.40670481587553053");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7519727771330792");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.4872745426884749");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		
		
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.7880728830227529");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.09395569595064523");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.13728088261872218");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.46251729526375385");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.9986854381713255");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.12758109401726847");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_Tag0.13809207603473028");
		                                                                     
		uris.add("http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968");
		                                                                                                

	return uris;
	}
	
}

