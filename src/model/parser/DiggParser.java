package parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import sesame.Sesame;
import aau.Resource;
import aau.User;

/**
 * 
 * This class returns the content of a Html document.
 */

public class DiggParser {
	
	public static void main(String[] args) {
		System.out.println(parseDigg().size());
//		for (KiWiResource kiWiResource : parseDigg()) {
//			System.out.println("   ");
//			System.out.println(kiWiResource.getDesc());
//			System.out.println(kiWiResource.getLink());
//			System.out.println(kiWiResource.getAuthor().getName());
//			for (Tag tag : kiWiResource.getTags()) {
//				System.out.println(tag.getTagName());
//			}
//		}
	}
	

	public static Set<Resource> parseDigg() {
		
		Set<Resource> kiwiAllResources = new HashSet<Resource>();
		
		int i=0;
		int total=855;
		Parser parse;
		try {
			
			for (String tagDig  : loatDigTags2()) {
				if (loatDigTags().contains(tagDig)) {
					System.out.println("Tag discarded.......... "+ tagDig);
					continue;
				}
				String siteParser = "http://www.diigo.com/community/tag?tag="+tagDig;
				parse = new Parser(siteParser);
				NodeFilter filtro = new NodeClassFilter(Span.class); 
				NodeList nodeList = parse.extractAllNodesThatMatch(filtro); 
				if (nodeList != null && nodeList.size() > 0)
				{
					Span tag = null;
				   for (int itr=0; itr<nodeList.size(); itr++)
				   {
					  tag = (Span)nodeList.elementAt(itr);
					  String idAttribute = tag.getAttribute("class");
					  	if (idAttribute != null && idAttribute.equals("title")){
								String description = tag.getChild(1).toPlainTextString();
								String link = extractLink(tag.getChild(1).toHtml());
								Resource kiWiResource = extractTags(siteParser, description, i , link);
								if (kiWiResource!=null && kiWiResource.getTags()!=null && kiWiResource.getTags().size()>2  &&  !kiWiResource.getDescription().startsWith("http") && !kiWiResource.getDescription().contains("?")) {
									kiwiAllResources.add(kiWiResource);	
//									System.out.println("   ");
//									System.out.println(" Saving bookmark      "+siteParser);
//									System.out.println(kiWiResource.getDesc());
//									System.out.println(kiWiResource.getLink());
//									System.out.println(kiWiResource.getAuthor().getName());
//									for (Tag tags : kiWiResource.getTags()) {
//										System.out.println(tags.getTagName());
//									}
									System.out.println(" Bookmark number "+i +" tag "+tagDig+" bookmark "+kiWiResource.getDescription());
									i++;
									total = total+i;
									if (i==17) {
										i=0;
										Sesame.saveKiWiData(Sesame.getDiggRepository(), kiwiAllResources);
										System.out.println("Total total :" +(total));
										Sesame.getMyRepository().getConnection().close();
										kiwiAllResources.clear();
										System.out.println(kiwiAllResources.size());
									}
								}
						  }
				   }
				 }
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kiwiAllResources;
	}
	
	
	
	public static  String extractLink(String html){
		String extraLink = new String("");
  	  Parser parserN;
		try {
		  parserN = new Parser(html);
		  NodeFilter filter = new NodeClassFilter(LinkTag.class);
		  NodeList nodeListLink = parserN.extractAllNodesThatMatch(filter);
		  if (nodeListLink != null && nodeListLink.size() > 0){
			  LinkTag link = null;
			   for (int itr=0; itr<nodeListLink.size(); itr++)
			   {
				   link = (LinkTag)nodeListLink.elementAt(itr);
				   String idAttribute = link.getAttribute("href");
					  if (idAttribute != null){
						  extraLink = link.getLink();
					  }
			   }
		  }
		} catch (ParserException e) {
					e.printStackTrace();
        }
		return extraLink;
	}
	
	/**
	 * @param html
	 * @param site
	 * @param id
	 * @param link
	 * @return
	 */
	public static Resource extractTags(String html, String site, int id , String link){
		Resource kiwiResource = null;
		

		Parser parserN;
		try {
			parserN = new Parser(html);
			NodeFilter filter = new NodeClassFilter(ParagraphTag.class);
			NodeList nodeList = parserN.extractAllNodesThatMatch(filter);
			if (nodeList != null && nodeList.size() > 0){
				ParagraphTag parag = null;
				for (int itr=0; itr<nodeList.size(); itr++)
				{
					parag = (ParagraphTag)nodeList.elementAt(itr);
					String idAttribute = parag.getAttribute("id");
					if (idAttribute != null && idAttribute.endsWith("tags_"+id)){
						 Map<String, Set<String>> mapUserTags = extractLinkTag(parag.toHtml());
						 if (mapUserTags!=null && !mapUserTags.keySet().isEmpty()) {
							kiwiResource = new Resource();
							String key = (String)mapUserTags.keySet().toArray()[0];
							User user = new User((String)mapUserTags.keySet().toArray()[0],"user@digg.com");
							kiwiResource.setDescription(site);
							kiwiResource.setLink(link);
							kiwiResource.setAuthor(user);
							for (String tag : mapUserTags.get(key)) {
								kiwiResource.addTag(tag);
							}
	//					     System.out.println("User "+ extractLinkTag(parag.toHtml()).keySet().toArray()[0]+ " created site "+site +" number  "+ id 
	//					    		 + "  with tags   "  +extractLinkTag(parag.toHtml()).get(extractLinkTag(parag.toHtml()).keySet().toArray()[0]));
						}
					}
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return kiwiResource;
	}
	
	public static  String extractTagsToKiWi(String html, String site, int id , String link){
		String extraLink = new String("");
		Parser parserN;
		try {
			parserN = new Parser(html);
			NodeFilter filter = new NodeClassFilter(ParagraphTag.class);
			NodeList nodeList = parserN.extractAllNodesThatMatch(filter);
			if (nodeList != null && nodeList.size() > 0){
				ParagraphTag parag = null;
				for (int itr=0; itr<nodeList.size(); itr++)
				{
					parag = (ParagraphTag)nodeList.elementAt(itr);
					String idAttribute = parag.getAttribute("id");
					if (idAttribute != null && idAttribute.endsWith("tags_"+id)){
						System.out.println("User "+ extractLinkTag(parag.toHtml()).keySet().toArray()[0]+ " created site "+site +" number  "+ id 
								+ "  with tags   "  +extractLinkTag(parag.toHtml()).get(extractLinkTag(parag.toHtml()).keySet().toArray()[0]));
					}
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return extraLink;
	}
	
	/**
	 * @param html
	 * @return
	 */
	public static  Map<String, Set<String>> extractLinkTag(String html){
		String finalUser = null;
	 Map<String, Set<String>> mapUserTags =  new  HashMap<String, Set<String>>();
		Set<String>	 tags = new HashSet<String>();
  	  Parser parserN;
		try {
		  parserN = new Parser(html);
		  NodeFilter filter = new NodeClassFilter(LinkTag.class);
		  NodeList nodeListLink = parserN.extractAllNodesThatMatch(filter);
		  if (nodeListLink != null && nodeListLink.size() > 0){
			  LinkTag link = null;
			   for (int itr=0; itr<nodeListLink.size(); itr++)
			   {
				   link = (LinkTag)nodeListLink.elementAt(itr);
				   String idAttribute = link.getAttribute("href");
					  if (idAttribute != null){
						  if (link.getLinkText().length()>2 && !link.getLinkText().contains("?") && !link.getLinkText().contains("=") && !link.getLinkText().equals("About") && !link.getLinkText().startsWith("All")) {
							  tags.add(link.getLinkText());
							  if (finalUser==null) {
								  String linkUser = link.getLink();
								  String noUser = linkUser.replace("/user/","");
								  finalUser = noUser.replace("/"+link.getLinkText(),"");
							 }
						 }
					  }
			   }
			   if (finalUser!=null && !tags.isEmpty()) {
				   mapUserTags.put(finalUser, tags);
			   }
			   
		  }
		} catch (ParserException e) {
					e.printStackTrace();
        }
		return mapUserTags;
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
	
	public static Set<String> loatDigTags2 () {
		Set<String> tagsD = new HashSet<String>();


		tagsD.add("3d");
		tagsD.add("advertising");
		tagsD.add("ajax");
		tagsD.add("and");
		tagsD.add("animation");
		tagsD.add("api");
		tagsD.add("apple");
		tagsD.add("architecture");
		tagsD.add("art");
		tagsD.add("article");
		tagsD.add("articles");
		tagsD.add("artist");
		tagsD.add("audio");
		tagsD.add("blog");
		tagsD.add("blogging");
		tagsD.add("blogs");
		tagsD.add("book");
		tagsD.add("books");
		tagsD.add("browser");
		tagsD.add("business");
		tagsD.add("car");
		tagsD.add("cms");
		tagsD.add("code");
		tagsD.add("collaboration");
		tagsD.add("comics");
		tagsD.add("community");
		tagsD.add("computer");
		tagsD.add("converter");
		tagsD.add("cooking");
		tagsD.add("cool");
		tagsD.add("css");
		tagsD.add("culture");
		tagsD.add("data");
		tagsD.add("database");
		tagsD.add("design");
		tagsD.add("desktop");
		tagsD.add("development");
		tagsD.add("diy");
		tagsD.add("documentation");
		tagsD.add("download");
		tagsD.add("downloads");
		tagsD.add("drupal");
		tagsD.add("ebooks");
		tagsD.add("economics");
		tagsD.add("education");
		tagsD.add("electronics");
		tagsD.add("email");
		tagsD.add("entertainment");
		tagsD.add("environment");
		tagsD.add("fashion");
		tagsD.add("fic");
		tagsD.add("film");
		tagsD.add("finance");
		tagsD.add("firefox");
		tagsD.add("flash");
		tagsD.add("flex");
		tagsD.add("flickr");
		tagsD.add("food");
		tagsD.add("forum");
		tagsD.add("free");
		tagsD.add("freeware");
		tagsD.add("fun");
		tagsD.add("funny");
		tagsD.add("gallery");
		tagsD.add("game");
		tagsD.add("games");
		tagsD.add("geek");
		tagsD.add("google");
		tagsD.add("government");
		tagsD.add("graphics");
		tagsD.add("green");
		tagsD.add("health");
		tagsD.add("history");
		tagsD.add("home");
		tagsD.add("hosting");
		tagsD.add("house");
		tagsD.add("howto");
		tagsD.add("html");
		tagsD.add("humor");
		tagsD.add("icons");
		tagsD.add("illustration");
		tagsD.add("images");
		tagsD.add("imported");
		tagsD.add("information");
		tagsD.add("inspiration");
		tagsD.add("interactive");
		tagsD.add("interesting");
		tagsD.add("internet");
		tagsD.add("iphone");
		tagsD.add("japan");
		tagsD.add("java");
		tagsD.add("javascript");
		tagsD.add("jobs");
		tagsD.add("jquery");
		tagsD.add("kids");
		tagsD.add("language");
		tagsD.add("learning");
		tagsD.add("library");
		tagsD.add("linux");
		tagsD.add("list");
		tagsD.add("lists");
		tagsD.add("literature");
		tagsD.add("mac");
		tagsD.add("magazine");
		tagsD.add("management");
		tagsD.add("maps");
		tagsD.add("marketing");
		tagsD.add("math");
		tagsD.add("media");
		tagsD.add("microsoft");
		tagsD.add("mobile");
		tagsD.add("money");
		tagsD.add("movie");
		tagsD.add("movies");
		tagsD.add("mp3");
		tagsD.add("music");
		tagsD.add("network");
		tagsD.add("networking");
		tagsD.add("news");
		tagsD.add("online");
		tagsD.add("opensource");
		tagsD.add("osx");
		tagsD.add("people");
		tagsD.add("phone");
		tagsD.add("photo");
		tagsD.add("photography");
		tagsD.add("photos");
		tagsD.add("photoshop");
		tagsD.add("php");
		tagsD.add("plugin");
		tagsD.add("podcast");
		tagsD.add("politics");
		tagsD.add("portfolio");
		tagsD.add("privacy");
		tagsD.add("productivity");
		tagsD.add("programming");
		tagsD.add("psychology");
		tagsD.add("python");
		tagsD.add("radio");
		tagsD.add("rails");
		tagsD.add("realestate");
		tagsD.add("recipe");
		tagsD.add("recipes");
		tagsD.add("reference");
		tagsD.add("religion");
		tagsD.add("research");
		tagsD.add("resources");
		tagsD.add("reviews");
		tagsD.add("rss");
		tagsD.add("ruby");
		tagsD.add("rubyonrails");
		tagsD.add("school");
		tagsD.add("science");
		tagsD.add("search");
		tagsD.add("security");
		tagsD.add("seo");
		tagsD.add("shop");
		tagsD.add("shopping");
		tagsD.add("social");
		tagsD.add("socialnetworking");
		tagsD.add("software");
		tagsD.add("statistics");
		tagsD.add("streaming");
		tagsD.add("teaching");
		tagsD.add("tech");
		tagsD.add("technology");
		tagsD.add("tips");
		tagsD.add("todo");
		tagsD.add("tool");
		tagsD.add("tools");
		tagsD.add("toread");
		tagsD.add("travel");
		tagsD.add("tutorial");
		tagsD.add("tutorials");
		tagsD.add("tv");
		tagsD.add("twitter");
		tagsD.add("typography");
		tagsD.add("ubuntu");
		tagsD.add("usability");
		tagsD.add("video");
		tagsD.add("videos");
		tagsD.add("vim");
		tagsD.add("visualization");
		tagsD.add("web");
		tagsD.add("web2.0");
		tagsD.add("webdesign");
		tagsD.add("webdev");
		tagsD.add("wiki");
		tagsD.add("wikipedia");
		tagsD.add("windows");
		tagsD.add("wishlist");
		tagsD.add("wordpress");
		tagsD.add("work");
		tagsD.add("writing");
		tagsD.add("youtube");
		return tagsD;
}
	
	
}

