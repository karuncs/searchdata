package lucene;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.index.TermPositions;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TopDocCollector;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.openrdf.repository.RepositoryException;

import parser.HTMLPageExtractor;
import sesame.Sesame;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity;
import util.PropertyUtil;
import aau.IndexField;
import aau.IndexTerm;
import aau.PersistenceService;
import aau.PersonalizedSearchService;
import aau.Resource;
import aau.SearchResult;
import aau.Tag;
import aau.User;

import com.ibm.icu.util.StringTokenizer;


@Stateless
@Name("luceneManagerService")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class LuceneManagerServiceImpl implements LuceneManagerService {

	private static final String TXT_EXTENSION = ".txt";

	private static String CONTENT = "content";

	private static String FILE_NAME = "fileName";

	private static String RESOURCE_TITLE = "RESOURCE_TITLE";

	private static String RESOURCE_ID = "RESOURCE_ID";
	
	private static String UNDERLINE = "_";

	private static int MAX_DOC = 20;
	
	PropertyUtil propertyUtil = new PropertyUtil();

	private String INDEX_DIR = propertyUtil.getPropertyValue("index.dir");
	
	private String DATA_DIR = propertyUtil.getPropertyValue("data.dir");	
	
	private String INDEX_SERVER_DIR = propertyUtil.getPropertyValue("index.server.dir");
	
	private String CONTENT_FIELD = propertyUtil.getPropertyValue("content.field");
	
	private String TITLE_FIELD = propertyUtil.getPropertyValue("title.field");
	
	private String ID_FIELD = propertyUtil.getPropertyValue("id.field");

	
	@In(create=true)
	private PersonalizedSearchService personalizedSearchService;
	
	//@In(create=true)
	//private PersistenceService persistenceService;		

	private Connection conn = null;
	public static void main(String[] args) {
		
		LuceneManagerServiceImpl lucene = new LuceneManagerServiceImpl();
		
		try {
			//lucene.indexMECOData();
			lucene.generateTagNeighbors();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		//String test = "October 21/07 Six months later";
		//System.out.println(test.replace("/",UNDERLINE));
//		System.out.println(test);
//		System.out.println(test.replace("\"",UNDERLINE));
//		//System.out.println(test.replace("/[^a-zA-Z 0-9]+/g",UNDERLINE));
//		System.out.println(test.replace(": ",UNDERLINE));
//		System.out.println(test.replace("!",UNDERLINE));
		// WR.indexDeliciousData();
		
		/*String s2 = "gag";
	    Pattern word = Pattern.compile("\\w+\\W+(?="+s2+")");//("(?<=test)\\W+\\w+");

	    String s = "here we go. the one has arrived in afghanistan on the first leg of his “victory tour.arrogant you say?gag me with a spoonit will be a lonnnngggggg week.previous: leave a replyis good for you ";
	    
	    
	    Matcher mat = word.matcher(s);
	    mat.find();
	    
	    System.out.println(s.substring(mat.start(), mat.end()));*/
	    
		//LuceneManagerServiceImpl wr = new LuceneManagerServiceImpl();
		
		//Analyzer analyzer = new StandardAnalyzer();
		//File indexDir = new File(wr.INDEX_DIR);

		try {
//			wr.indexMECOData();
//			if (!indexDir.isFile() && indexDir.listFiles().length == 0) {
//				//wr.indexData();
//			} else if (!indexDir.isFile()) {
//				//wr.getTermFrequency();
//				//wr.topSearch(termQuery);
//				//wr.search(termQuery);
//				//wr.searchAndScore(analyzer, indexDir, termQuery);
//			}
//			// Directory directory = FSDirectory.getDirectory(indexDir);
//			// WR.getTermOcureness(directory);*/
		} catch (Exception e) {
			System.out.println("Inexistent index or data dir");
			e.printStackTrace();
		}
	}
	
	/*
	 * This function should be called in a stand-alone java app. only. It also only works with postgres database
	 */
	public void generateTagNeighbors () throws ClassNotFoundException, SQLException, CorruptIndexException, IOException {
		String indexFieldSearch = "content";
		
		this.getConnection();
		
		PreparedStatement psGetNextTagWithoutNeighbor = conn.prepareStatement("" +
				"select * from tag where tagroot_id is null and hasneighbours is null limit 1");
		PreparedStatement psUpdateTagRoot = conn.prepareStatement("" +
				"UPDATE tag set hasneighbours = ? where id = ?");
		PreparedStatement psInsertNeighbor = conn.prepareStatement("" +
				"INSERT INTO tag (tagname, weight, tagfrequency, resourceid, tagroot_id, hasneighbours)" +
				" VALUES (?, ?, ?, ?, ?, ?)");
		
		ResultSet rs = psGetNextTagWithoutNeighbor.executeQuery();
		
		while (rs.next()) { //while there is tag root without neighbor
			String tagName = rs.getString("tagname");
			Long tagRootId = rs.getLong("id");
			System.out.print(new Date() + " Generating neighbors for tag '(" + Long.toString(tagRootId) + ") "  + tagName + "'...");
			
			Hashtable<String, Tag> tagNeighbors = new Hashtable<String, Tag>();
			
			Hits hits = null;
			try {
				hits = this.searchIndex(indexFieldSearch, this.INDEX_DIR, this.cleanNeibours(tagName));
			}
			catch (Exception e) {
				System.out.println("Error. Could not search the index for the query '" + this.cleanNeibours(tagName) + "': " + e.getMessage());
				continue;
			}
			
			//Search only the iLen first documents to increase performance
			int iLen = 300;
			if (hits.length() < iLen) {
				iLen = hits.length() - 1;
			}			
			for (int i = 0; i < iLen; i++) {
				String keyword = tagName;
				
				Document doc = hits.doc(i);
				
				Field field = doc.getField(indexFieldSearch);
				
				long idValue = 0;
				try {
					idValue = new Long(doc.getField(RESOURCE_ID).stringValue());
				}
				catch (Exception e) {
					System.out.println("Error. Couldn't get resource ID in Lucene: " + e.getMessage());
					continue;
				}
				
				if (field.stringValue().length() > 800000) {
					continue;
				}
				String text = new String(field.stringValue().getBytes(), "UTF-8");				
				int offset = 0;
				
				//Remove stop words
				//String regex = "\\b(I|a|about|an|and|are|as|at|be|but|by|com|for|from|how|in|is|it|of|not|on|or|that|the|this|to|was|what|when|where|who|will|with|www)\\b";
				String regex = "\\b(eu|ele|eles|ela|elas|e|as|ao|a|o|os|de|da|do|das|dos|em|nele|nela|neles|nelas|sob|sobre|disto|no|na|nos|nas|que|quando|qual|quem|com|por|www|http)\\b";
				text = this.removeDiacriticalMarks(text);
				text = text.replaceAll(regex, "");
				
				while (text.toLowerCase().indexOf(keyword, offset) > 0) {
					//Get the neighbors before and after the word
					String wordBefore = keyword;
					String wordAfter = keyword;
					int nNeighbors = 8;
					for (int j = 0; j < nNeighbors; j++) {
						try {
							Pattern pWordBefore = Pattern.compile("\\w+\\W+(?="+Pattern.quote(wordBefore)+")"); //Get word immediately before string
							Pattern pWordAfter = Pattern.compile("(?<="+Pattern.quote(wordAfter)+")\\W+\\w+"); //Get word immediately after string
						
							Matcher matBefore = pWordBefore.matcher(text.toLowerCase());
							try {
								wordBefore = this.addNeighbor(matBefore, offset, text, tagNeighbors, tagRootId, idValue, nNeighbors, j) + wordBefore;
							}
							catch (java.lang.IllegalStateException e) {
								//Ignore error
							}
							
							Matcher matAfter = pWordAfter.matcher(text.toLowerCase());
							try {
								wordAfter += this.addNeighbor(matAfter, offset, text, tagNeighbors, tagRootId, idValue, nNeighbors, j);
							} 
							catch (java.lang.IllegalStateException e) {
								//Ignore error
							}
						}
						catch (java.util.regex.PatternSyntaxException e) {
							System.out.println(e.getMessage());	
						}     		
					}			
					offset = text.toLowerCase().indexOf(keyword, offset) + 1;
				}	

			}
			
			Enumeration<Tag> e = tagNeighbors.elements();
			while (e.hasMoreElements()) {
				Tag neighbor = e.nextElement();
			    //Add neighbor to database
				psInsertNeighbor.setString(1, neighbor.getTagName()); //tagname
				psInsertNeighbor.setFloat(2, neighbor.getWeight()/neighbor.getTagFrequency()); //average weight
				psInsertNeighbor.setFloat(3, neighbor.getTagFrequency()); //normalized tag frequency
				psInsertNeighbor.setLong(4, neighbor.getResourceId()); //resource id
				psInsertNeighbor.setLong(5, neighbor.getTagRootId());
				psInsertNeighbor.setBoolean(6, false); //hasNeighbours
				
				try {
					psInsertNeighbor.executeUpdate();
				}
				catch (Exception ex) {
					System.out.println("Error inserting neighbor " + neighbor.getTagName() + ": " + ex.getMessage());
				}
			}
			
			if (tagNeighbors.size() > 0) {
				psUpdateTagRoot.setBoolean(1, true);
			} else {
				psUpdateTagRoot.setBoolean(1, false);
			}
			psUpdateTagRoot.setLong(2, tagRootId);
			psUpdateTagRoot.executeUpdate();
			
			System.out.println(Integer.toString(tagNeighbors.size()) + " neighbors processed.");
			
			rs = psGetNextTagWithoutNeighbor.executeQuery();
		}
		psInsertNeighbor.close();
		psGetNextTagWithoutNeighbor.close();
	}

	public String removeDiacriticalMarks(String string) {
	    return Normalizer.normalize(string, Form.NFD)
	        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	
	
	private String addNeighbor (Matcher matcher, int offset, String text, Hashtable<String, Tag> tagNeighbors, Long tagRootId, Long resourceId, int nNeighbors, int currNeighbor){
			matcher.find(offset);
			String neighborBefore = this.cleanNeibours(text.substring(matcher.start(), matcher.end()));
			
			String neighborName = neighborBefore.trim().toLowerCase();
			Tag neighbor = tagNeighbors.get(neighborName);
			if (neighbor == null) {
				Tag newNeighbour = new Tag();
				newNeighbour.setTagName(neighborName);
        		newNeighbour.setTagRootId(tagRootId);
        		newNeighbour.setResourceId(resourceId);
        		newNeighbour.setWeight(nNeighbors/(currNeighbor+1));
        		newNeighbour.setTagFrequency(1);
        		newNeighbour.setHasNeighbours(false);
        		
        		tagNeighbors.put(neighborName, newNeighbour);
			} else {
				neighbor.setTagFrequency(neighbor.getTagFrequency()+1);
				neighbor.setWeight(neighbor.getWeight() + (nNeighbors/(currNeighbor+1)));
			}
		return text.substring(matcher.start(), matcher.end());
	}

	/**
	 * it creates index from a given data directory
	 */
	public void indexData() {
		Analyzer analyzer = new StandardAnalyzer();
		File indexDir = new File(INDEX_DIR);
		try {
			File dataDir = new File(DATA_DIR);
			IndexWriter writer = new IndexWriter(indexDir, analyzer, true);
			indexDirectory(writer, dataDir);
			writer.close();
			// for (Tag tag : kiWiResource.getTags()) {
			//					
			// if (!tag.getTagName().startsWith("*")&&
			// !tag.getTagName().trim().equals("") &&
			// !tag.getTagName().contains("!")) {
			// System.out.println("Tag "+tag.getTagName()+" tem valor "+
			// tag.getWeight());
			// //searchAndScore(analyzer, indexDir,tag);
			// }else{
			// System.out.println("Found special char in "+tag.getTagName());
			// }
			//						
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param resource
	 */
	public void indexMECOData(Resource resource) {
		Analyzer analyzer = new StandardAnalyzer();
		File indexDir = new File(INDEX_DIR);
		try {
			File dataDir = new File(DATA_DIR);
			IndexWriter writer = new IndexWriter(indexDir, analyzer, false);
			String fileName = dataDir.getPath() + File.separator
					+ HTMLPageExtractor.getURLName(resource.getLink());
			HTMLPageExtractor.extractContentAndSave(resource.getLink(),
					fileName);
			indexMECODirectory(writer, dataDir);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @param resource
	 */
	public void indexMECOData() {
		IndexWriter writer = null;
		Analyzer analyzer = new StandardAnalyzer();
		File indexDir = new File(INDEX_DIR);	
		File dataDir = new File(DATA_DIR);		
		try {
				writer = new IndexWriter(indexDir, analyzer);
				indexMECODirectory(writer, dataDir);
				writer.close();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * mecodata 07_iwis.txt (id_title.txt)
	 * @param resource
	 */
	public void saveMecoData(List<Resource> resources) {
		try {
		File dataDir = new File(DATA_DIR);
			for (Resource resource : resources) {
				if (resource.getLink().endsWith(".pdf") || resource.getLink().endsWith(".mp3") || resource.getLink().endsWith(".mp4") || resource.getLink().endsWith(".mov")) {
					continue;
				}
				String fileName = resource.getId()+UNDERLINE+HTMLPageExtractor.getURLName(resource.getLink())+TXT_EXTENSION;
				String filePath = dataDir.getPath() + File.separator + fileName;
				String link = HTMLPageExtractor.decodeMecoURL(resource.getLink());
				//System.out.println(link);
				if (HTMLPageExtractor.exists(link)   && !FSDirectory.getDirectory(dataDir).fileExists(fileName)) {
						HTMLPageExtractor.extractContentAndSave(link,
								filePath);
					}else{
						System.out.println("File "+fileName+ " already exists in data directory");
					}
			} 					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		

	/**
	 * @param analyzer
	 * @param indexDir
	 * @param query
	 * @throws CorruptIndexException
	 * @throws IOException
	 * @throws ParseException
	 */
	private void searchAndScore(Analyzer analyzer, File indexDir, Tag tag)
			throws CorruptIndexException, IOException, ParseException {
		IndexSearcher isearcher = new IndexSearcher(indexDir.getPath());

		QueryParser parser = new QueryParser(CONTENT, analyzer);

		Query query = parser.parse(tag.getTagName());

		Hits hits = isearcher.search(query);

		for (int i = 0; i < hits.length(); i++) {
			Document doc = hits.doc(i);
			System.out.println(doc.get(FILE_NAME));
			System.out.println("SCORE  " + hits.score(i) + "  for the tag \b"
					+ tag.getTagName());
			System.out.println("  ");
			persistScoreInTagWeight(hits.score(i), tag.getUri());
			System.out.println(doc.get(FILE_NAME));
			// Explanation explanation = isearcher.explain(query, hits.id(i));
			// System.out.println(explanation.toString());
		}

		isearcher.close();
	}
	
	
	

	private void searchAndScore(Analyzer analyzer, File indexDir,
			String string) throws CorruptIndexException, IOException,
			ParseException {
		String previous = null;
		String next = null;
		String term = null;
		boolean foundPrevious = false;
		boolean foundNext = false;
		boolean foundTerm = false;
		IndexSearcher isearcher = new IndexSearcher(indexDir.getPath());

		QueryParser parser = new QueryParser(CONTENT, analyzer);

		IndexReader reader = IndexReader.open(indexDir.getPath());

		Query query = parser.parse(string);

		query = query.rewrite(reader);

		Hits hits = isearcher.search(query);

		Highlighter highlighter = new Highlighter(new QueryScorer(query));

		Fragmenter fragmenter = new SimpleFragmenter(40);
		highlighter.setTextFragmenter(fragmenter);

		// http://www.gossamer-threads.com/lists/lucene/java-user/42239

		for (int i = 0; i < hits.length(); i++) {
			Document doc = hits.doc(i);
			System.out.println(doc.get(FILE_NAME));
			// System.out.println("SCORE  "+hits.score(i)+
			// "  for the tag \b"+string);
			Field field = doc.getField(CONTENT);

			TokenStream tokenStream = analyzer.tokenStream(CONTENT,
					new StringReader(field.toString()));

			String result = highlighter.getBestFragments(tokenStream, field
					.toString(), 8, "..");

			StringTokenizer stringTokenizer = new StringTokenizer(result, " ");

			while (stringTokenizer.hasMoreElements()) {

				String element = ((String) stringTokenizer.nextElement()).trim().toLowerCase();

				if (element.equals("<B>" + string + "</B>")) {
					foundTerm = true;
					term = element.replace("<B>", "");
					term = term.replace("</B>", "");
				} else if (!foundTerm && !foundNext && (element.length() > 2)
						&& (!element.equals("at")) && (!element.equals("of"))
						&& (!element.equals("off")) && (!element.equals("to"))
						&& (!element.equals("on")) && (!element.equals("in"))
						&& (!element.equals("is"))) {
					previous = element;
					foundPrevious = true;
				} else if (foundTerm && foundPrevious && element.length() > 2
						&& (!element.equals("at")) && (!element.equals("of"))
						&& (!element.equals("off")) && (!element.equals("to"))
						&& (!element.equals("on")) && (!element.equals("in"))
						&& (!element.equals("is"))) {
					next = element;
					foundNext = true;

					System.out.println("Term query  " + term
							+ " has previous neibour      " + previous
							+ "              and next neibour              "
							+ next);

					foundPrevious = false;
					foundNext = false;
					foundTerm = false;

					break;
				}
			}

			// System.out.println(document.getFields().toString());

			// System.out.println(doc.get(FILE_NAME));
			// Explanation explanation = isearcher.explain(query, hits.id(i));
			// System.out.println(explanation.toString());
		}

		isearcher.close();
	}

	/**
	 * @param indexDir
	 * @param string
	 * @return
	 */
	public List<String> getNeighboursByTag(String string) {
		return getNeighboursByTag(string, CONTENT);
	}
	
	public List<String> getNeighboursByTag(String string, String indexFieldSearch) {

		Set<String> tags = new HashSet<String>();
		try {
			Analyzer analyzer = new StandardAnalyzer();

			String previous = null;
			String next = null;

			String term = null;

			boolean foundPrevious = false;

			boolean foundNext = false;

			boolean foundTerm = false;

			IndexSearcher isearcher = new IndexSearcher(this.INDEX_DIR);

			QueryParser parser = new QueryParser(indexFieldSearch, analyzer);

			IndexReader reader = IndexReader.open(this.INDEX_DIR);

			Query query = parser.parse(string);

			query = query.rewrite(reader);

			Hits hits = isearcher.search(query);

			Highlighter highlighter = new Highlighter(new QueryScorer(query));

			Fragmenter fragmenter = new SimpleFragmenter(40);
			highlighter.setTextFragmenter(fragmenter);

			// http://www.gossamer-threads.com/lists/lucene/java-user/42239

			for (int i = 0; i < hits.length(); i++) {
				Document doc = hits.doc(i);

				Field field = doc.getField(indexFieldSearch);

				TokenStream tokenStream = analyzer.tokenStream(indexFieldSearch,
						new StringReader(field.toString()));

				String result = highlighter.getBestFragments(tokenStream, field
						.toString(), 8, "..");

				StringTokenizer stringTokenizer = new StringTokenizer(result," ");

				while (stringTokenizer.hasMoreElements()) {
					String element = ((String) stringTokenizer.nextElement());
					if (element.equals("<B>" + string + "</B>")) {
						foundTerm = true;
						term = element.replace("<B>", "");
						term = term.replace("</B>", "");
					} else if (!foundTerm && !foundNext && isValid(element)) {
						previous = element;
						foundPrevious = true;
					} else if (foundTerm && foundPrevious && isValid(element)) {
						next = element;
						foundNext = true;
						if (!previous.trim().equals("")) {
							tags.add(this.cleanNeibours(previous));
						}
						if (!next.trim().equals("")) {
							tags.add(this.cleanNeibours(next));							
						}						

						// System.out.println("Term query  "+term+" has previous neighbour      "+previous+"              and next neibour              "+next);
						foundPrevious = false;
						foundNext = false;
						foundTerm = false;

						break;
					}
				}

				// System.out.println(document.getFields().toString());

				// System.out.println(doc.get(FILE_NAME));
				// Explanation explanation = isearcher.explain(query,
				// hits.id(i));
				// System.out.println(explanation.toString());
			}

			isearcher.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<String>(tags);
	}

	public void getNeighboursByTag(Tag tagRoot, String indexFieldSearch, PersistenceService persistenceService) {
		
		List<Tag> tagNeighbours = new ArrayList<Tag>();
		try {
			Analyzer analyzer = new StandardAnalyzer();
			
			String previous = null;
			String next = null;
			
			String term = null;
			
			boolean foundPrevious = false;
			
			boolean foundNext = false;
			
			boolean foundTerm = false;
			
			IndexSearcher isearcher = new IndexSearcher(this.INDEX_DIR);
			
			QueryParser parser = new QueryParser(indexFieldSearch, analyzer);
			
			IndexReader reader = IndexReader.open(this.INDEX_DIR);
			
			String string = tagRoot.getTagName().trim().toLowerCase();
			
			Query query = parser.parse(string);
			
			query = query.rewrite(reader);
			
			Hits hits = isearcher.search(query);
			
			Highlighter highlighter = new Highlighter(new QueryScorer(query));
			
			Fragmenter fragmenter = new SimpleFragmenter(40);
			highlighter.setTextFragmenter(fragmenter);
			
			// http://www.gossamer-threads.com/lists/lucene/java-user/42239
			
			for (int i = 0; i < hits.length(); i++) {
				Document doc = hits.doc(i);
				
				Field field = doc.getField(indexFieldSearch);
				long idValue = new Long(doc.getField("id").stringValue());
				//Resource neighbourResource = persistenceService.getResourceById(idValue);
				
				TokenStream tokenStream = analyzer.tokenStream(indexFieldSearch,
						new StringReader(field.stringValue()));
				
				String result = highlighter.getBestFragments(tokenStream, field
						.stringValue(), 8, "..");
				
				StringTokenizer stringTokenizer = new StringTokenizer(result," ");
				
				while (stringTokenizer.hasMoreElements()) {
					String element = ((String) stringTokenizer.nextElement());
					if (element.equals("<B>" + string + "</B>")) {
						foundTerm = true;
						term = element.replace("<B>", "");
						term = term.replace("</B>", "");
					} else if (!foundTerm && !foundNext && isValid(element)) {
						previous = element;
						foundPrevious = true;
					} else if (foundTerm && foundPrevious && isValid(element)) {
						next = element;
						foundNext = true;
						String neighbourName = "";
						if (!previous.trim().equals("")) {
							neighbourName = this.cleanNeibours(previous);
						}
						if (!next.trim().equals("")) {
							neighbourName = this.cleanNeibours(next);							
						}						
	            		
						Tag newNeighbour = new Tag(neighbourName, tagRoot.getTagAuthor());
	            		newNeighbour.setTagRoot(tagRoot);
	            		newNeighbour.setResourceId(idValue);
	            		newNeighbour.setHasNeighbours(false);
	            		
	            		tagNeighbours.add(newNeighbour);
						
						// System.out.println("Term query  "+term+" has previous neighbour      "+previous+"              and next neibour              "+next);
						foundPrevious = false;
						foundNext = false;
						foundTerm = false;
						
						break;
					}
				}

				// System.out.println(document.getFields().toString());
				
				// System.out.println(doc.get(FILE_NAME));
				// Explanation explanation = isearcher.explain(query,
				// hits.id(i));
				// System.out.println(explanation.toString());
			}
			
			if (tagNeighbours.isEmpty()) {
				tagRoot.setHasNeighbours(false);
			} else {
				tagRoot.setHasNeighbours(true);
				tagRoot.setTagSimblings(tagNeighbours);
			}
			
			isearcher.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private ArrayList<Tag> getTagNeighbors(String text, Tag tagRoot, int nNeighbors, Long resourceId) {
		ArrayList<Tag> tagNeighbours = new ArrayList<Tag>();
		
		String keyword = tagRoot.getTagName();
		int offset = 0;
		while (text.toLowerCase().indexOf(keyword, offset) > 0) {
			//Get the neighbors before and after the word
			String wordBefore = keyword;
			String wordAfter = keyword;
			for (int i = 0; i < nNeighbors; i++) {
				try {
					Pattern pWordBefore = Pattern.compile("\\w+\\W+(?="+Pattern.quote(wordBefore)+")"); //Get word immediately before string
					Pattern pWordAfter = Pattern.compile("(?<="+Pattern.quote(wordAfter)+")\\W+\\w+"); //Get word immediately after string
				
					Matcher matBefore = pWordBefore.matcher(text.toLowerCase());
					
					//Try to add neighbor before
					try {
						matBefore.find(offset);
						String neighborBefore = this.cleanNeibours(text.substring(matBefore.start(), matBefore.end()));
						
						wordBefore = neighborBefore;
						
					    //Add neighbors to arraylist
						Tag newNeighbour = new Tag(neighborBefore.trim().toLowerCase(), tagRoot.getTagAuthor());
		        		newNeighbour.setTagRoot(tagRoot);
		        		newNeighbour.setResourceId(resourceId);
		        		newNeighbour.setHasNeighbours(false);
		        		newNeighbour.setWeight(nNeighbors/(i+1));       		
		        		
		        		if (newNeighbour == null) {
		        			System.out.println("test");
		        		}
		        		tagNeighbours.add(newNeighbour);
		        	
					}
					catch (java.lang.IllegalStateException e) {
						//Ignore error, neighbor not found
					}
					
					//Try to add neighbor after
					Matcher matAfter = pWordAfter.matcher(text.toLowerCase());
					try {
						matAfter.find(offset);
					    String neighborAfter = this.cleanNeibours(text.substring(matAfter.start(), matAfter.end()));			    
					    
					    wordAfter = neighborAfter;		    
		       		
						Tag newNeighbour = new Tag(neighborAfter.trim().toLowerCase(), tagRoot.getTagAuthor());
		        		newNeighbour.setTagRoot(tagRoot);
		        		newNeighbour.setResourceId(resourceId);
		        		newNeighbour.setHasNeighbours(false);
		        		newNeighbour.setWeight(nNeighbors/(i+1));       		
		        		
		        		if (newNeighbour == null) {
		        			System.out.println("test");
		        		}
		        		tagNeighbours.add(newNeighbour);
					}
					catch (java.lang.IllegalStateException e) {
						//Ignore error, neighbor not found
					}
				}
				catch (java.util.regex.PatternSyntaxException e) {
					System.out.println(e.getMessage());	
				}     		
			}			
			offset = text.toLowerCase().indexOf(keyword, offset) + 1;
		}	

		return tagNeighbours;
	}
	
	/*
	 * Get neighbors weighted by their distance from the tagRoot.
	 */
	public void getWeightedNeighboursByTag(Tag tagRoot, String indexFieldSearch, PersistenceService persistenceService) {
		
		List<Tag> tagNeighbours = new ArrayList<Tag>();
		try {
			Analyzer analyzer = new StandardAnalyzer();
			IndexSearcher isearcher = new IndexSearcher(this.INDEX_DIR);			
			QueryParser parser = new QueryParser(indexFieldSearch, analyzer);			
			IndexReader reader = IndexReader.open(this.INDEX_DIR);
			
			String string = tagRoot.getTagName().trim().toLowerCase();
			
			Query query = parser.parse(string);			
			query = query.rewrite(reader);			
			Hits hits = isearcher.search(query);
			
			int iLen = 1000;
			if (hits.length() < iLen) {
				iLen = hits.length() - 1;
			}
			
			for (int i = 0; i < iLen; i++) {
				Document doc = hits.doc(i);
				Field field = doc.getField(indexFieldSearch);
				long idValue = new Long(doc.getField("RESOURCE_ID").stringValue());
				String s = new String(field.stringValue().getBytes(), "UTF-8");//				
				ArrayList<Tag> neighborsList = this.getTagNeighbors(s, tagRoot, 8, idValue);
				if (neighborsList == null) {
					System.out.println("test");
				}
				tagNeighbours.addAll(neighborsList);
			}
			
			if (tagNeighbours.isEmpty()) {
				tagRoot.setHasNeighbours(false);
			} else {
				tagRoot.setHasNeighbours(true);
				tagRoot.setTagSimblings(tagNeighbours);
			}
			
			isearcher.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private Hits searchIndex (String indexFieldSearch, String indexDir, String queryString) throws Exception { //this.INDEX_DIR //tagRoot.getTagName().trim().toLowerCase()
		
			Analyzer analyzer = new StandardAnalyzer();
			IndexSearcher isearcher;
			Hits hits = null;

				isearcher = new IndexSearcher(indexDir);
				QueryParser parser = new QueryParser(indexFieldSearch, analyzer);			
				IndexReader reader = IndexReader.open(indexDir);
				
				Query query = parser.parse(queryString);			
				query = query.rewrite(reader);			
				hits = isearcher.search(query);				
			
			return hits;
	}
	
	/**
	 * @param element
	 * @return
	 */
	private boolean isValid(String element){
		boolean isValid = false;
		
		if (element.length() > 2
				&& (!element.equals(" "))
				&& (!element.equals("the"))
				&& (!element.equals("off"))
				&& (!element.equals("for"))
				&& (!element.equals("what"))
				&& (!element.equals("that"))
				&& (!element.equals("off"))
				&& (!element.equals("that"))
				&& (!element.equals("and"))
				&& (!element.equals("was"))
				&& (!element.equals("who"))
				&& (!element.equals("are"))
				&& (!element.equals("from"))
				&& (!element.equals("will"))
				&& (!element.equals("have"))
				&& (!element.equals("had"))
		
		) {
			isValid = true;
		}
		return isValid;
	}
	
	
	/**
	 * @param durtyTerm
	 * @return
	 */
	private String cleanNeibours(String durtyTerm){
		String cleanNeibour = durtyTerm;
		if (durtyTerm.contains("\"<b\">")) {
			cleanNeibour = cleanNeibour.replace("\"<b\">", "");	
		}
		if (durtyTerm.contains("</b>")) {
			cleanNeibour = cleanNeibour.replace("</b>","");
		}
		
		if (durtyTerm.contains(".")) {
			cleanNeibour = cleanNeibour.replace(".", "");
		}
		if (durtyTerm.contains("*")) {
			cleanNeibour = cleanNeibour.replace("*", "");
		}
		if (durtyTerm.contains("\"")) {
			cleanNeibour = cleanNeibour.replace("\"", "");
		}
		if (durtyTerm.contains("&nbsp;")) {
			cleanNeibour = cleanNeibour.replace("&nbsp;", "");
		}
		if (durtyTerm.contains("?")) {
			cleanNeibour = cleanNeibour.replace("?", "");
		}
		if (durtyTerm.contains("!")) {
			cleanNeibour = cleanNeibour.replace("!", "");
		}
		if (durtyTerm.contains("]")) {
			cleanNeibour = cleanNeibour.replace("]", "");
		}
		if (durtyTerm.contains("[")) {
			cleanNeibour = cleanNeibour.replace("[", "");
		}
		if (durtyTerm.contains("'")) {
			cleanNeibour = cleanNeibour.replace("'", "");
		}
		if (durtyTerm.contains("(")) {
			cleanNeibour = cleanNeibour.replace("(", "");
		}
		if (durtyTerm.contains(")")) {
			cleanNeibour = cleanNeibour.replace(")", "");
		}
		if (durtyTerm.contains(":")) {
			cleanNeibour = cleanNeibour.replace(":", "");
		}
		if (durtyTerm.contains("|")) {
			cleanNeibour = cleanNeibour.replace("|", "");
		}
		if (durtyTerm.contains(";")) {
			cleanNeibour = cleanNeibour.replace(";", "");
		}
		if (durtyTerm.contains(",")) {
			cleanNeibour = cleanNeibour.replace(",", "");
		}
		if (durtyTerm.contains("\")")) {
			cleanNeibour = cleanNeibour.replace("\")", "");
		}		
		
		return cleanNeibour.toString();
	}
	
	/**
	 * @param indexDir
	 * @param userQuery
	 * @return
	 */
	public List<SearchResult> topSearch(String userQuery) {

		Set<SearchResult> searchResults = new HashSet<SearchResult>();
		Set<String> searchUnique = new HashSet<String>();

		try {
			Analyzer analyzer = new StandardAnalyzer();

			IndexSearcher isearcher = new IndexSearcher(INDEX_DIR);

			QueryParser parser = new QueryParser(CONTENT, analyzer);

			TopDocCollector topDocCollector = new TopDocCollector(MAX_DOC);

			Query query = parser.parse(userQuery);

//			Hits hits = isearcher.search(query,topDocCollector);
			isearcher.search(query,topDocCollector);

			ScoreDoc[] hits = topDocCollector.topDocs().scoreDocs;

			for (int i = 0; i < hits.length ; i++) {
				int  docId = hits[i].doc;
				Document doc = isearcher.doc(docId);;
				Resource resource = new Resource();
				resource.setTitle(doc.get(FILE_NAME));
				SearchResult searchResult = new SearchResult();
				searchResult.setSearchScore(hits[i].score);
				resource.setId(new Long(doc.get(RESOURCE_ID)));
				resource.setTitle(doc.get(RESOURCE_TITLE));
				searchResult.setResource(resource);
				searchResult.setQuery(userQuery);
				String uniqueItem = new Long(searchResult.getResource().getId()).toString().concat(searchResult.getQuery());
				if (!searchUnique.contains(uniqueItem)) {
					searchResults.add(searchResult);
					searchUnique.add(uniqueItem);
				}
			}
			
			isearcher.close();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<SearchResult>(searchResults);
	}	

	/**
	 * @param indexDir
	 * @param userQuery
	 * @return
	 */
	public List<SearchResult> search(String userQuery) {

		Set<SearchResult> searchResults = new HashSet<SearchResult>();
		Set<String> searchUnique = new HashSet<String>();

		try {
			Analyzer analyzer = new StandardAnalyzer();

			IndexSearcher isearcher = new IndexSearcher(this.getAvailableIndexDirectory());

			QueryParser parser = new QueryParser(CONTENT, analyzer);

			Query query = parser.parse(userQuery);

			Hits hits = isearcher.search(query);

			for (int i = 0; i < hits.length(); i++) {
				Document doc = hits.doc(i);
				Resource resource = new Resource();
				resource.setTitle(doc.get(FILE_NAME).trim().replace("<B>", ""));
				SearchResult searchResult = new SearchResult();
				searchResult.setSearchScore(hits.score(i));
				resource.setId(new Long(doc.get(RESOURCE_ID)));
				resource.setTitle(doc.get(RESOURCE_TITLE));
				searchResult.setResource(resource);
				searchResult.setQuery(userQuery);
				String uniqueItem = new Long(searchResult.getResource().getId()).toString().concat(searchResult.getQuery());
				if (!searchUnique.contains(uniqueItem)) {
					searchResults.add(searchResult);
					searchUnique.add(uniqueItem);
				}
			}
			
			isearcher.close();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<SearchResult>(searchResults);
	}

	
	/**
	 * @param indexDir
	 * @param userQuery
	 * @return
	 */
	public List<SearchResult> search(String userQuery, User user) {

		Set<SearchResult> searchResults = new HashSet<SearchResult>();
		Set<String> searchUnique = new HashSet<String>();

		try {
			Analyzer analyzer = new StandardAnalyzer();

			IndexSearcher isearcher = new IndexSearcher(INDEX_DIR);

			QueryParser parser = new QueryParser(CONTENT, analyzer);

			Query query = parser.parse(userQuery);

			Hits hits = isearcher.search(query);

			for (int i = 0; i < hits.length(); i++) {
				Document doc = hits.doc(i);
				Resource resource = new Resource();
				resource.setTitle(doc.get(FILE_NAME));
				SearchResult searchResult = new SearchResult();
				searchResult.setSearchScore(hits.score(i));
				resource.setId(new Long(doc.get(RESOURCE_ID)));
				resource.setTitle(doc.get(RESOURCE_TITLE));
				searchResult.setResource(resource);
				searchResult.setQuery(userQuery);
				String uniqueItem = new Long(searchResult.getResource().getId()).toString().concat(searchResult.getQuery());
				if (!searchUnique.contains(uniqueItem)) {
					searchResults.add(searchResult);
					searchUnique.add(uniqueItem);
				}
			}
			
			isearcher.close();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<SearchResult>(searchResults);
	}	
	public static void persistScoreInTagWeight(float floatWeight, String tagURI) {
		try {
			Sesame.updateTagFrequency(Sesame.getRepository().getConnection(),
					floatWeight, tagURI);
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * @param index
	 * @param term
	 */
	public static void getTermPositions(IndexReader index, Term term) {
		try {
			TermPositions iterator = index.termPositions(term);

			while (iterator.next()) {
				int docNr = iterator.doc();
				int freq = iterator.freq();
				int position = iterator.nextPosition();

				System.out.println(term.text() + "  " + docNr + "  " + position
						+ "  " + freq + "  ");

				getNeighboursByDocumentNumberAndTermPositions(docNr, position);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param index
	 * @param term
	 */
	public List<IndexTerm> getTermFrequency() {
		int contIDF = 0;
		Map<String, Integer> termFreqs = new LinkedHashMap<String, Integer>();
		Map<String, Integer> termIDF = new LinkedHashMap<String, Integer>();
		
		List<IndexTerm>  terms = new ArrayList<IndexTerm>();
		try {
			IndexReader reader = IndexReader.open(getAvailableIndexDirectory());
			for (int i = 1; i < reader.maxDoc(); i++) {
				TermFreqVector vector = reader.getTermFreqVector(i,"description");
				if (vector!=null) {
				  String[] vTerms = vector.getTerms();
		          int [] freqs = vector.getTermFrequencies();
		          for (int j = 0; (vTerms!=null) && (j < vTerms.length); j++){ 
		        	  if (!termFreqs.keySet().contains((String)vTerms[j])) {
		        		  termFreqs.put((String)vTerms[j], (int)freqs[j]);
		        		  termIDF.put((String)vTerms[j], (int)1);
					  }else{
						  Integer before = (Integer)termFreqs.get(vTerms[j]);
						  Integer now = (int)freqs[j];
						  Integer total = before.intValue()+now.intValue();
						  termFreqs.remove((String)vTerms[j]);
						  termFreqs.put((String)vTerms[j], total);
						  
	        			  contIDF = termIDF.get((String)vTerms[j]);
	        			  termIDF.remove(termIDF.get((String)vTerms[j]));
	        			  contIDF = contIDF+1;
	        			  termIDF.put((String)vTerms[j], contIDF);						  
					  }
		          }
				}
			}
			
			
			for (String key : termFreqs.keySet()) {
				if (key!=null) {
					IndexTerm term = new IndexTerm();
					term.setTerm(key);
					term.setDocFrequency((float)termIDF.get(key));
					term.setTermFrequency(((float)(float)(termFreqs.get(key)/(float)termIDF.get(key))));
					terms.add(term);
				}
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return terms;
	}	


	
	public static void getNeighboursByDocumentNumberAndTermPositions(
			int currentDocNr, int currentTermPposition) {

	}

	/**
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	public static void indexMemoryDirectory() throws CorruptIndexException,
			IOException {
		Analyzer analyzer = new StandardAnalyzer();

		Directory directory = new RAMDirectory();


		IndexWriter iwriter = new IndexWriter(directory, analyzer, true);

		iwriter.setMaxFieldLength(25000);

		Document doc = new Document();

		String text = new String(
				"fred CASA fred CASA DURAO IMARIA HOJE TEM AVIAO CASA AVIAO HJ TEM MARIA CASA AVIAO MAIS COISA");

		doc.add(new Field(CONTENT, text, Field.Store.YES,
				Field.Index.TOKENIZED, Field.TermVector.YES));

		iwriter.addDocument(doc);

		iwriter.close();
	}

	/**
	 * @param writer
	 * @param dir
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	public static void indexDirectory(IndexWriter writer, File dir)
			throws CorruptIndexException, IOException {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				Document doc = new Document();
				doc.add(new Field(FILE_NAME, f.getName(), Field.Store.YES,
						Field.Index.TOKENIZED, Field.TermVector.YES));
				addFileContent(doc, f);
				writer.addDocument(doc);
			}
		} else {
			Document doc = new Document();
			doc.add(new Field(FILE_NAME, dir.getName(), Field.Store.YES,
					Field.Index.TOKENIZED, Field.TermVector.YES));
			addFileContent(doc, dir);
			writer.addDocument(doc);
		}
	}

	private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }	
	
	/**
	 * @param writer
	 * @param dir
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	public static void indexMECODirectory(IndexWriter writer, File dir) throws CorruptIndexException, IOException {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			System.out.println(getDateTime() + " Starting indexing...");
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				Document doc = new Document();
				doc.add(new Field(FILE_NAME, f.getName(), Field.Store.YES,
						Field.Index.TOKENIZED, Field.TermVector.YES));
				if(f.getName().contains(UNDERLINE) && f.getName().endsWith(TXT_EXTENSION)){
					String title = f.getName().substring(f.getName().indexOf(UNDERLINE)+1,f.getName().length());
					doc.add(new Field(RESOURCE_TITLE, title.replace(TXT_EXTENSION, ""),
							Field.Store.YES, Field.Index.TOKENIZED,
							Field.TermVector.YES));
					
					String id = f.getName().substring(0,f.getName().indexOf(UNDERLINE));				
					doc.add(new Field(RESOURCE_ID, id,
							Field.Store.YES, Field.Index.TOKENIZED,
							Field.TermVector.YES));
				}
				addFileContent(doc, f);
				writer.addDocument(doc);
				
				if (i % 1000 == 0) {
					System.out.println(getDateTime() + Integer.toString(i));
				}
			}
		} else {
			Document doc = new Document();
			doc.add(new Field(FILE_NAME, dir.getName(), Field.Store.YES,
					Field.Index.TOKENIZED, Field.TermVector.YES));
			String title = dir.getName().substring(dir.getName().indexOf(UNDERLINE)+1,dir.getName().length());			
			doc.add(new Field(RESOURCE_TITLE, title.replace(TXT_EXTENSION, ""),
					Field.Store.YES, Field.Index.TOKENIZED,
					Field.TermVector.YES));
			String id = dir.getName().substring(0,dir.getName().indexOf(UNDERLINE));			
			doc.add(new Field(RESOURCE_ID, id,
					Field.Store.YES, Field.Index.TOKENIZED,
					Field.TermVector.YES));
			addFileContent(doc, dir);
			writer.addDocument(doc);
		}
		System.out.println(getDateTime() + " Indexing finished...");
	}

	/**
	 * @param doc
	 * @param f
	 * @throws IOException
	 */
	private static void addFileContent(Document doc, File f) throws IOException {
		if (f.isHidden() || !f.exists() || !f.canRead()) {
			return;
		}
		FileInputStream fis = new FileInputStream(f);
		int x = fis.available();
		byte b[] = new byte[x];
		fis.read(b);
		String content = new String(b);
		doc.add(new Field(CONTENT, content, Field.Store.YES,
				Field.Index.TOKENIZED, Field.TermVector.YES));
	}

	/**
	 * @param conn
	 * @param jdbc
	 * @param sql
	 * @return
	 */
	public void connectLuceneDB(String sql){
		try{
		   StandardAnalyzer analyzer = new StandardAnalyzer();
		   IndexWriter writer = new IndexWriter(getAvailableIndexDirectory(), analyzer, true);
		   System.out.println("Indexing to directory '" + getAvailableIndexDirectory() + "'...");
		   indexDocsFromDB(writer, this.getConnection(), sql);
		   writer.optimize();
		   writer.close();

		} catch (Exception e) {
		   e.printStackTrace();
		}
		
	}
	
	public void indexData(String sql, IndexField[] fieldList){
		try{
		   StandardAnalyzer analyzer = new StandardAnalyzer();
		   IndexWriter writer = new IndexWriter(getAvailableIndexDirectory(), analyzer, true);
		   System.out.println("Indexing to directory '" + getAvailableIndexDirectory() + "'...");
		   indexDocsFromDB(writer, this.getConnection(), sql, fieldList);
		   writer.optimize();
		   writer.close();

		} catch (Exception e) {
		   e.printStackTrace();
		}
		
	}
	
	/**
	 * @param tagSet1
	 * @param tagSet2
	 * @return
	 */
	public float computePersonalizedSearch(String tagSet1, String tagSet2){
		AbstractStringMetric cosineSimilarity = new CosineSimilarity();
		float simScore = cosineSimilarity.getSimilarity(tagSet1, tagSet2);
		return simScore;
	}
	
	/**
	 * @return
	 */
	private Connection getConnection() {
		String pass = "iwis";		
		String login = "postgres";
		String server_login = "iwis";	
		String db_url = "jdbc:postgresql://localhost/medworm";//"jdbc:postgresql://localhost/dilma";		
		String jdbc = "org.postgresql.Driver";		
		
		if (this.conn == null) {
			try {
				Class.forName(jdbc);
				conn = DriverManager.getConnection(db_url, login, pass);
			} catch (Exception e) {
				/*if (conn==null) {
					try {
						conn = DriverManager.getConnection(db_url, server_login, pass);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}*/
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	/**
	 * @param qry
	 * @param analyzer
	 * @param conn
	 * @throws ParseException 
	 * @throws CorruptIndexException
	 * @throws IOException
	 * @throws ParseException
	 * @throws SQLException
	 */
	public List<SearchResult> performSearchTwitter(String qry, User user, boolean isPersonalizedSearch, String similarityType)  {
		System.out.println("performing Twitter Search...");
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		StandardAnalyzer analyzer = new StandardAnalyzer();
		try {

		Searcher searcher = new IndexSearcher(IndexReader.open(getAvailableIndexDirectory()));

		Query query = new QueryParser("description",analyzer).parse(qry);
		
		Hits hits = searcher.search(query);

		for (int i = 0; i < hits.length(); i++){
		   int id = Integer.parseInt(hits.doc(i).get("id"));
			Resource resource = new Resource();
			resource.setId(id);
			resource.setDescription(hits.doc(i).get("description"));
			SearchResult searchResult = new SearchResult();
			searchResult.setSearchScore(hits.score(i));
			searchResult.setResource(resource);
			searchResult.setQuery(qry);
			searchResult.setUser(user);
			if (isPersonalizedSearch) {
				personalizedSearchService.runPersonilazedSearch(searchResult,similarityType);
			}
			searchResults.add(searchResult);
			
			if (i==20) {
				break;
			}
		}

		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return searchResults;
	}
	
	

		
		public List<SearchResult> performTopSearchMedworm(String qry, User user, boolean isPersonalizedSearch, String similarityType)  {
			System.out.println("performing Medworm Search...");
			List<SearchResult> searchResults = new ArrayList<SearchResult>();
			StandardAnalyzer analyzer = new StandardAnalyzer();
			try {

			Searcher searcher = new IndexSearcher(IndexReader.open(getAvailableIndexDirectory()));

			Query query = new QueryParser(CONTENT_FIELD,analyzer).parse(qry);
			
			Highlighter highlighter = new Highlighter(new QueryScorer(query));
			Fragmenter fragmenter = new SimpleFragmenter(30);
			highlighter.setTextFragmenter(fragmenter);
			
			TopDocCollector topDocCollector = new TopDocCollector(MAX_DOC);

			searcher.search(query,topDocCollector);

			ScoreDoc[] hits = topDocCollector.topDocs().scoreDocs;

			for (int i = 0; i < hits.length ; i++) {
				int  docId = hits[i].doc;
				Document doc = searcher.doc(docId);;				
  			    int id = Integer.parseInt(doc.get(ID_FIELD));  				

				Resource resource = new Resource();
				resource.setId(id);
				resource.setDescription(doc.get(CONTENT_FIELD));
				SearchResult searchResult = new SearchResult();
				searchResult.setSearchScore(hits[i].score);
				
				Field field = doc.getField(CONTENT_FIELD);
  				TokenStream tokenStream = analyzer.tokenStream(CONTENT_FIELD,
  							new StringReader(field.stringValue()));
  				
  				String result = highlighter.getBestFragments(tokenStream, field
  						.stringValue(), 8, "..");
  				byte[] n = result.getBytes("UTF-8");
  				result = new String(n);
  				
  				searchResult.setHighlight(result);
				
				searchResult.setResource(resource);
				searchResult.setQuery(qry);
				searchResult.setUser(user);
				searchResult.setRank(""+i);
				if (isPersonalizedSearch) {
					personalizedSearchService.runPersonilazedSearch(searchResult,similarityType);
				}
				searchResults.add(searchResult);
			}

			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			return searchResults;
		}		
		
	
	public List<SearchResult> performSearchMedworm(String qry, User user, boolean isPersonalizedSearch, String similarityType)  {
		System.out.println("performing Medworm Search...");
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		StandardAnalyzer analyzer = new StandardAnalyzer();
		try {

		Searcher searcher = new IndexSearcher(IndexReader.open(getAvailableIndexDirectory()));

		Query query = new QueryParser(CONTENT_FIELD,analyzer).parse(qry);
		
		Hits hits = searcher.search(query);
		for (int i = 0; i < hits.length(); i++){
		   int id = Integer.parseInt(hits.doc(i).get(ID_FIELD));
			Resource resource = new Resource();
			resource.setId(id);
			resource.setDescription(hits.doc(i).get(CONTENT_FIELD));
			SearchResult searchResult = new SearchResult();
			searchResult.setSearchScore(hits.score(i));
			searchResult.setResource(resource);
			searchResult.setQuery(qry);
			searchResult.setUser(user);
			searchResult.setRank(""+i);
			if (isPersonalizedSearch) {
				personalizedSearchService.runPersonilazedSearch(searchResult,similarityType);
			}
			searchResults.add(searchResult);
			
			if (i==20) {
				break;
			}
		}

		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return searchResults;
	}
	/**
	 * @param qry
	 * @param analyzer
	 * @param conn
	 * @throws ParseException 
	 * @throws CorruptIndexException
	 * @throws IOException
	 * @throws ParseException
	 * @throws SQLException
	 */
	public List<SearchResult> performSearchMovieLens(String qry, User user, boolean isPersonalizedSearch, String similarityType)  {
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		StandardAnalyzer analyzer = new StandardAnalyzer();
		try {

		Searcher searcher = new IndexSearcher(IndexReader.open(getAvailableIndexDirectory()));

		Query query = new QueryParser(CONTENT_FIELD,analyzer).parse(qry);
		
		Hits hits = searcher.search(query);

		for (int i = 0; i < hits.length(); i++){
		   int id = Integer.parseInt(hits.doc(i).get(ID_FIELD));
			Resource resource = new Resource();
			resource.setId(id);
			resource.setTitle(hits.doc(i).get(TITLE_FIELD));
			SearchResult searchResult = new SearchResult();
			searchResult.setSearchScore(hits.score(i));
			searchResult.setResource(resource);
			searchResult.setQuery(qry);
			searchResult.setUser(user);
			if (isPersonalizedSearch) {
				personalizedSearchService.runPersonilazedSearch(searchResult,similarityType);
			}
			searchResults.add(searchResult);
			
//			if (i==3) {
//				break;
//			}
		}

		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return searchResults;
	}
	
	
	/**
	 * @param qry
	 * @param user
	 * @param isPersonalizedSearch
	 * @param topItems
	 * @return
	 */
	public List<SearchResult> performTopSearchMovieLens(String qry, User user, boolean isPersonalizedSearch, int topItems, String similarityType)  {
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		StandardAnalyzer analyzer = new StandardAnalyzer();
		try {

		IndexSearcher isearcher = new IndexSearcher(getAvailableIndexDirectory());

		QueryParser parser = new QueryParser(CONTENT_FIELD, analyzer);

		TopDocCollector topDocCollector = new TopDocCollector(topItems);

		Query query = parser.parse(qry);

		isearcher.search(query,topDocCollector);

		ScoreDoc[] hits = topDocCollector.topDocs().scoreDocs;		

		for (int i = 0; i < hits.length; i++){
			int  docId = hits[i].doc;
			Document doc = isearcher.doc(docId);
		    int id = Integer.parseInt(doc.get(ID_FIELD));
			Resource resource = new Resource();
			resource.setId(id);
			resource.setTitle(doc.get(TITLE_FIELD));
			SearchResult searchResult = new SearchResult();
			searchResult.setSearchScore(hits[i].score);
			searchResult.setResource(resource);
			searchResult.setQuery(qry);
			searchResult.setUser(user);
			if (isPersonalizedSearch) {
				personalizedSearchService.runPersonilazedSearch(searchResult,similarityType);
			}
			searchResults.add(searchResult);
			
//			if (i==3) {
//				break;
//			}
		}

		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return searchResults;
	}	
	
	/**
	 * @param qry
	 * @param analyzer
	 * @param conn
	 * @throws CorruptIndexException
	 * @throws IOException
	 * @throws ParseException
	 * @throws SQLException
	 */
	public void performSearchMovieLens(String qry) throws CorruptIndexException, IOException, ParseException, SQLException {
		StandardAnalyzer analyzer = new StandardAnalyzer();
		Searcher searcher = new IndexSearcher(IndexReader.open(getAvailableIndexDirectory()));
		Query query = new QueryParser(CONTENT_FIELD,analyzer).parse(qry);
		Hits hits = searcher.search(query);
		
		String sql = "select title, description from resources where id = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < hits.length(); i++){
		   int id = Integer.parseInt(hits.doc(i).get(ID_FIELD));
		    pstmt.setInt(1, id);
		    ResultSet rs = pstmt.executeQuery();
			Resource resource = new Resource();
			resource.setTitle(rs.getString(TITLE_FIELD));
			resource.setDescription(rs.getString(CONTENT_FIELD));
			resource.setId(id);
			SearchResult searchResult = new SearchResult();
			searchResult.setSearchScore(hits.score(i));
			searchResult.setResource(resource);
			searchResult.setQuery(qry);
			displayResults(pstmt);
		}
	}
	
	/**
	 * @return
	 */
	private String getAvailableIndexDirectory(){
		String availableIndexDirectory =  "";
		if (new File(INDEX_SERVER_DIR).exists()) {
			availableIndexDirectory = INDEX_SERVER_DIR;
		}else if (new File(INDEX_DIR).exists()) {
			availableIndexDirectory = INDEX_DIR;
		}
		
		if (INDEX_SERVER_DIR.equals(INDEX_DIR)) {
			new Exception("INDEX_SERVER_DIR equals to INDEX_DIR !");
		}
		return availableIndexDirectory;
	}
	


	/**
	 * @param pstmt
	 */
	void displayResults(PreparedStatement pstmt) {
	   try {
	      ResultSet rs = pstmt.executeQuery();
	      while (rs.next()) {
	         System.out.println(rs.getString(TITLE_FIELD));
	         System.out.println(rs.getString(CONTENT_FIELD)+"\n");
	      }
	   } catch (SQLException e) {
	      e.printStackTrace();
	   }
	}
	
	

	/**
	 * @param writer
	 * @param conn
	 * @throws Exception
	 */
	void indexDocsFromDB(IndexWriter writer, Connection conn, String sql) throws Exception {
		  Statement stmt = conn.createStatement();
		  ResultSet rs = stmt.executeQuery(sql);
		  while (rs.next()) {
		     Document d = new Document();
		     d.add(new Field(ID_FIELD, rs.getString(ID_FIELD), Field.Store.YES, Field.Index.NO));
		     d.add(new Field(TITLE_FIELD, rs.getString(TITLE_FIELD), Field.Store.YES, Field.Index.TOKENIZED));
		     d.add(new Field(CONTENT_FIELD, rs.getString(CONTENT_FIELD), Field.Store.YES, Field.Index.TOKENIZED,Field.TermVector.YES));
		     writer.addDocument(d);
		 }
		}
	
	/**
	 * @param writer
	 * @param conn
	 * @param fieldList List of fields to be indexed 
	 * @throws Exception
	 */
	void indexDocsFromDB(IndexWriter writer, Connection conn, String sql, IndexField[] fieldList) throws Exception {
		  Statement stmt = conn.createStatement();
		  ResultSet rs = stmt.executeQuery(sql);
		  while (rs.next()) {
		     Document d = new Document();
		     
		     for (IndexField field : fieldList) {	    	 
		    	 if (field.isTermVectorSet()) {
		    		 d.add(new Field(field.getName(), rs.getString(field.getDbName()), field.getFieldStore(), field.getFieldIndex(), field.getFieldTermVector()));
		    	 } else {
		    		 d.add(new Field(field.getName(), rs.getString(field.getDbName()), field.getFieldStore(), field.getFieldIndex()));
		    	 }
		     }
		     writer.addDocument(d);
		 }
	}	
	
}