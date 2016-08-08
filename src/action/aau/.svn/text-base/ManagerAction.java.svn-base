package aau;


import importer.MovieLensImporter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

import lucene.HelloLucene;
import lucene.LuceneManagerService;
import lucene.LuceneManagerServiceImpl;
import mockup.Mockup;

import org.apache.lucene.document.Field;
import org.hibernate.NonUniqueObjectException;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.log.Log;
import org.jboss.seam.transaction.Transaction;
import org.jboss.seam.transaction.UserTransaction;

import parser.HTMLPageExtractor;
import sesame.Sesame;
import similarity.SimilarityMetrics;
import util.FileUtils;
import util.PropertyUtil;
import xml.XmlReader;
import constant.IConstant;
import data.Delicious;
import data.MecoData;


/**
 * @author freddurao, kreddy
 */
@Name("managerAction")
@Scope(ScopeType.CONVERSATION)
@Transactional
public class ManagerAction {
	
	@Logger
	Log log;

	@In(create=true)
	private PersistenceService persistenceService;	
	
	@In(create=true)
	private EvaluationService evaluationService;
	
	@In(create=true)
	private LuceneManagerService luceneManagerService;

	@In(create=true)
	private EntityManager entityManager;

	PropertyUtil propertyUtil = new PropertyUtil();

	private String USER_PREFERENCE_DIR = propertyUtil.getPropertyValue("user.preference.dir");
	
	private int START_VALUE = new Integer(propertyUtil.getPropertyValue("start.value"));
	
	public static void main(String[] args) {
		ManagerAction managerAction = new ManagerAction();
		
		System.out.println(managerAction.START_VALUE);
	}

	/**
	 * @return
	 */
	public String saveDeli(){
		Set<Resource> kiwiAllResources = new HashSet<Resource>();
		Set<Resource> kiwiAllResources2 = new HashSet<Resource>(); 
		List<Resource> listK = new ArrayList<Resource>(Sesame.loadKiWiData(Sesame.getFisicalRepository()));
		kiwiAllResources.addAll(listK);

		int c=0;
			for (Resource kiWiResource : kiwiAllResources) {
				kiwiAllResources2.add(kiWiResource);
				if (c==50) {
					saveResources(kiwiAllResources2);
					kiwiAllResources2.clear();
					c=0;
				}
				c++;
			}		
		log.info("delicious data saved");
		return "/home.xhtml";  
	}
	
	
	/**
	 * saveMockup
	 */
	public void evaluateMeco(){
		evaluationService.evaluateSearch();
		System.out.println("ended evaluationService");
	}
	
	
	
	/**
	 * saveMockup
	 */
	public void saveMockup(){
		Mockup mockup = new Mockup();
		Set<Resource> kiwiAllResources = mockup.loadTestData();;
		saveResources(kiwiAllResources);
		System.out.println("saved mockup");
	}
	
	/**
	 * saveMockup
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void saveRelevantResources(){
		MecoData mecoData = new MecoData();
		List<Resource> resources = new ArrayList<Resource>();
		Set<String> uniqueKey = new HashSet<String>();
		List<RelevantItem> relevantItems = new ArrayList<RelevantItem>();
		Map<String, List<Long>> mapRelevant = mecoData.getRelevantItemsByExpert();
		for (String key : mapRelevant.keySet()) {
			RelevantItem relevantItem = new RelevantItem();
			relevantItem.setQuery(key);
			List<Long> longIds = mapRelevant.get(key);
			for (Long resourceId : longIds) {
				resources.add(persistenceService.getResourceById(resourceId));	
			}
			relevantItem.setResources(resources);
			relevantItems.add(relevantItem);
		}
		System.out.println("loaded relevant resources");
		for (RelevantItem relevantItem : relevantItems) {
			if (!uniqueKey.contains(relevantItem.getQuery())) {
				entityManager.persist(relevantItem);
				uniqueKey.add(relevantItem.getQuery());
			}
		}
		System.out.println("saved relevant resources");
	}
	
	
	
	public String deleteSesame(){
		Set<Resource> kiwiAllResources = new HashSet<Resource>();
		List<Resource> listK = new ArrayList<Resource>(Sesame.loadKiWiData(Sesame.getFisicalRepository()));
		kiwiAllResources.addAll(listK);
		for (Resource kiWiResource : kiwiAllResources) {
				List<User> users = persistenceService.getUserByName(kiWiResource.getAuthor().getName());
				for (User user2 : users) {
					Resource kiWiResourceFim = persistenceService.getResourceByUser(user2);
					if (kiWiResourceFim!=null) {
						deleteResource(kiWiResourceFim);
						System.out.println("deleting..more "+kiWiResourceFim.getDescription());
					}
				}
		}
	log.info("data deleted");
	return "/home.xhtml";  
}	
	
	 public void extractHTMLContentAndSave(){
		 
		 List<Resource> kiwiresources = persistenceService.getResources();
		 for (Resource kiWiResource : kiwiresources) {
			 HTMLPageExtractor.extractContentAndSave(kiWiResource.getLink());
		 }
     }
	
	
	public void saveResources(Set<Resource> allResources ){
		for (Resource resource : allResources) {
			//if (kiWiResource!=null && kiWiResource.getTags()!=null){
			if (resource!=null){
//				if (kiWiResource.getLink().length()>80) {
//					kiWiResource.setLink(kiWiResource.getLink().substring(0, 60));
//				}
//				if (kiWiResource.getDesc().length()>80) {
//					kiWiResource.setDesc(kiWiResource.getDesc().substring(0, 60));
//				}
				User userSaved = saveUser(resource.getAuthor());
				for (Tag tag : resource.getTags()) {
					tag.setTagAuthor(userSaved);
					tag.setResource(resource);
					Tag newTag = persistenceService.getTagByAuthorAndName(userSaved, tag.getTagName());
					if (newTag!=null) {
						tag =  newTag;
						tag.setResource(resource);
					}
				}
				resource.setAuthor(userSaved);
				persistenceService.saveResource(resource);
			}
		}
	}


	/**
	 * save XML data in to KiwiResource 
	 */
	public void saveXML(){
		XmlReader xmlReader = new XmlReader();
		List<Resource> xmlKiwiResource = xmlReader.loadXMLData();
		printResources(xmlKiwiResource);
		this.saveResources(new HashSet<Resource>(xmlKiwiResource));
		System.out.println("xml items saved in the database");
	}
	
	
	
		
	/**
	 * This method print information from resources
	 * @param kiWiResources
	 */
	public void printResources(List<Resource> kiWiResources){
		for (Resource kiWiResource : kiWiResources) {
			System.out.println(kiWiResource.getDescription());
			System.out.println(kiWiResource.getAuthor().getName());
		}
	
	}
	
	
	public void computeTagNeibours(){
		long max = 12;
		long init = 4819;
		
		long begin = init;
		long end = init;
		//for (long i = 0; i < 13509; i++) {
		//for (long i = 0; i < 50; i++) {
		for (long i = init; i < init+max; i++) {			
			if (begin==end) {
				begin=end;
			}else {
				begin=end+1;
			}
			end=begin+10;
			//System.out.println("interval tags from "+begin+" to "+end);
			computeTagNeibours2(begin,end);	
			
		}
		System.out.println("ending FIRST computeTagNeibours FINAL..");	
		
	
	}	
	
	
	
	
	/**
	 * @return
	 */
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void computeTagNeibours2(Long begin, Long end){
		
        try {
            // starts an transaction for the next 100 items
            final UserTransaction transaction = Transaction.instance();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            transaction.enlist(entityManager);
            
            System.out.println("interval tags from "+begin+" to "+end);
			
			LuceneManagerServiceImpl neighbor= new LuceneManagerServiceImpl();
			List<Tag> tags = persistenceService.getTags(begin,end);
				List<String> tagsNames  = new ArrayList<String>();
				for (Tag tag :tags) {			
					tagsNames  = neighbor.getNeighboursByTag(tag.getTagName().trim().toLowerCase());
					//System.out.println(tagsNames.toString());
					if (!tagsNames.isEmpty()) {
						tag = persistenceService.getTagById(tag.getId());
						tag.setTagNeighbours(tagsNames);
						entityManager.persist(tag);
					}
				}
			
			
			
            transaction.commit();

        } catch (final Exception exception) {
        	exception.printStackTrace();
        }			
	}	
	
	
		@Asynchronous
		@Transactional
		@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
		public void generateTagNeighbors(){
			String INDEX_FIELD_SEARCH = "content"; //use "description" for twitter or "content" for medworm;
			
	        try {
	            System.out.println("Generating neighbors for tags without them...");
	            
	            LuceneManagerServiceImpl neighbor= new LuceneManagerServiceImpl();
	            
	          // List<String> tagName  = new ArrayList<String>();
	            Tag t = persistenceService.getTagWithoutNeighbour();
	            int i = 1;
	                  
	            while (t != null) {           	
	            	
	            	System.out.println("Generating neighbors for tag (" + Long.toString(t.getId()) + ") " + t.getTagName() + "...");
		            
	            	// starts an transaction for the next 100 items		            
	            	final UserTransaction transaction = Transaction.instance();
		            if (!transaction.isActive()) {
		                transaction.begin();
		            }
		            transaction.enlist(entityManager);
		            
		            neighbor.getWeightedNeighboursByTag(t, INDEX_FIELD_SEARCH, persistenceService);
		            
//	            	if (!tagNames.isEmpty()) {
//	            		t.setHasNeighbours(true);
//		            	List<Tag> tagSimblings = new ArrayList<Tag>();
//		            	for (String s : tagNames) {
//		            		Tag newNeighbor = new Tag(s, t.getTagAuthor());
//		            		newNeighbor.setTagRoot(t);
//		            		newNeighbor.setResource(t.getResource());
//		            		newNeighbor.setHasNeighbours(false);
//		            		
//		            		entityManager.persist(newNeighbor);
//		            		
//		            		tagSimblings.add(newNeighbor);
//		            	}
//		            	t.setTagSimblings(tagSimblings);
//	            	} else {
//	            		t.setHasNeighbours(false);
//	            	}
	            	entityManager.persist(t);
	            	
	                if ( i % 20 == 0 ) { //20, same as the JDBC batch size
	                    //flush a batch of inserts and release memory:
	                	entityManager.flush();
	                	entityManager.clear();
	                }	            	
	                
	                try {
	                	transaction.commit();
	                } catch (Exception e) {
	                	//ignore
	                	System.out.println(e.getMessage());
	                }
		            
	            	t = persistenceService.getTagWithoutNeighbour(Long.toString(t.getId()+1), Long.toString(t.getId()+100));
	            	
	            	i++;
	            }         
	            
	        } catch (final Exception exception) {
	        	exception.printStackTrace();
	        }			
		}	
	
	/**
	 * @return
	 */
//	public void computeTagNeibours(){
//		LuceneManager neighbor= new LuceneManager();
//		List<Tag> tags = getAllTags();
//			List<String> tagsNames  = new ArrayList<String>();
//			//System.out.println("adding neibour: "+tagsNames.toString());
//			for (Tag tag : tags.subList(1, 50)) {			
//				tagsNames  = neighbor.getNeighboursByTag(tag.getTagName().trim().toLowerCase());
//			if (!tagsNames.isEmpty()) {
//				tag = this.getTagById(tag.getId());
//				tag.setTagNeighbours(tagsNames);
//				entityManager.persist(tag);
//			}
//			}
//			System.out.println("ending computeTagNeibours...");			
//	}	
//	
	
	/**
	 * Compute Search
	 * @return
	 */
	public void computeSearch(){
		
		Set<String> searchUnique = new HashSet<String>();
		
		List<Tag> tags = persistenceService.getAllTags();// we need queries
		
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		
		LuceneManagerServiceImpl luceneManager = new LuceneManagerServiceImpl();

		for (Tag tag : tags) {
			String uniqueItem = new Long(tag.getTagAuthor().getId()).toString().concat(tag.getTagName());
			if (!searchUnique.contains(uniqueItem)) {
				searchResults.addAll(luceneManager.search(expandQuerySearch(tag.getTagName(), tag.getTagNeighbours())));
				searchUnique.add(uniqueItem);
			}				
			
		}
		
		for (Tag tag : tags) {
			String uniqueItem = new Long(tag.getTagAuthor().getId()).toString().concat(tag.getTagName());
			if (!searchUnique.contains(uniqueItem)) {
				searchResults.addAll(luceneManager.search(expandQuerySearch(tag.getTagName(), tag.getTagNeighbours())));
				searchUnique.add(uniqueItem);
			}				
			
		}		
		
		for (SearchResult searchResult : searchResults) {
			Resource resource = persistenceService.getResourceByTitle(searchResult.getResource().getTitle());
			searchResult.setResource(resource);
		}
		
		Collections.sort(searchResults);
		
		for (SearchResult searchResult : searchResults) {
			Resource resource = searchResult.getResource();
			searchResult.setSearchScore(searchResult.getSearchScore() * SimilarityMetrics.computeConsineSimilarity(resource.getTagString(), searchResult.getQuery()));
		}

		for (SearchResult searchResult : searchResults) {
			System.out.println("Resource "+ searchResult.getResource().getTitle()+ " query "+ searchResult.getQuery()+ " score "+ searchResult.getSearchScore());
		}
		System.out.println("ending computeSearch...");	
		
	}

	Map<String, Set<Long>> queries = new HashMap<String, Set<Long>>();
	
	public void loadQuery(){

		Set<Long> ids = new HashSet<Long>();
		ids.add(45646l);//ids.add(45646l);ids.add(45646l);ids.add(45646l);ids.add(45646l);
		queries.put("cancer", ids);
		
//		Set<Long> ids2 = new HashSet<Long>();
//		ids.add(45646l);ids.add(45646l);ids.add(45646l);ids.add(45646l);ids.add(45646l);
//		queries.put("ill", ids2);
	}
	
	/**
	 * indexMecoData
	 */
	public void saveMecoData(){

		//List<Resource> resources = getResources(0l, 141l);
		//List<Resource> resources = getResources(142l, 432l);
		//pdf. .mp4 files are missing
		//List<Resource> resources = getResources(434l, 558l);
		//List<Resource> resources = getResources(560, 729l);
		List<Resource> resources = persistenceService.getResources(30765l, 40000l);
		
		LuceneManagerServiceImpl wr = new LuceneManagerServiceImpl();
		wr.saveMecoData(resources);
		System.out.println("ending saveMECOData...");
	}
	
	/**
	 * indexMecoData
	 */
	public void indexMecoData(){
		LuceneManagerServiceImpl wr = new LuceneManagerServiceImpl();
		wr.indexMECOData();
		System.out.println("ending indexMecoData...");	
	}	
	
	
	/**
	 * @param initialQuery
	 * @param tagNeibours
	 * @return
	 */
	public String expandQuerySearch(String initialQuery, List<String> tagNeibours){
		StringBuilder expandedQuery = new StringBuilder();
		expandedQuery.append(initialQuery);
		for (String neibours : tagNeibours) {
			expandedQuery.append(" AND ");
			expandedQuery.append(neibours);
		}
		return expandedQuery.toString();
	}
	

	/**
	 * @return
	 */
	public void computeTagWeight(){
	
		List<Tag> tags = persistenceService.getAllTags();
		System.out.println("computing Tag Popularity/weight...");
		for (Tag tag : tags) {
			tag.setWeight(persistenceService.getTagWeight(tag.getTagName()));
			System.out.println(tag.getWeight());	
			
			entityManager.persist(tag);
		}	
		
		System.out.println("ending computing Tag Popularity/weight...");	
		
	}
		
	/**
	 * @return
	 */
	public void loadTags(){
		List<Tag> tags = persistenceService.getAllTags();
		for (Tag tag : tags) {
			System.out.println(tag.getWeight());	
		}	
	}	
	

	/**
	 * 
	 */
	@Asynchronous
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)	
	public void computeTagFrequency(){
		//List<Tag> tags = getTagsByName("code");
		List<Tag> tags = persistenceService.getAllTags();
		System.out.println("computing Tag Frequency...");
		for (Tag tag : tags) {
			System.out.println(tag.getTagFrequency());
			tag.setTagFrequency(persistenceService.getTagFrequency(tag.getTagName()));
			entityManager.persist(tag);
		}	
		
		System.out.println("ending computing Tag Frequency...");		
	}

	/**
	 * 
	 */
	@Asynchronous
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)	
	public void computeTagNeighbourFrequency(){
		try {
			//List<Tag> tags = null;//getTagsByName("code");
			List<Tag> tagNeighbours = persistenceService.getAllNeighbours();
			System.out.println("computing TagNeighbour Frequency...");
			for (Tag tagNeighbour : tagNeighbours) {
				final UserTransaction transaction = Transaction.instance();
		        if (!transaction.isActive()) {
		            transaction.begin();
		        }
		        transaction.enlist(entityManager);
		        
				float frequencyValue = persistenceService.getNeighbourFrequency(tagNeighbour.getTagRoot(),tagNeighbour.getTagName());
				tagNeighbour.setNeighbourFrequency(frequencyValue);
				
				System.out.println("Neighbour '(" + tagNeighbour.getId() + ") " + tagNeighbour.getTagName() + "' frequency is " + tagNeighbour.getNeighbourFrequency());
				//tag_tagneighbours.setNeighbourFrequency(persistenceService.getNeighbourFrequency(tag_tagneighbours.getTagNeighbourName()));
				entityManager.persist(tagNeighbour);
				
				entityManager.flush();
		        transaction.commit();				
			}	
		
        } catch (final Exception exception) {
        	exception.printStackTrace();
        }
		System.out.println("ending computing TagNeighbour Frequency...");		
	}
	
	/**
	 * 
	 */
	public void computeUserPreference2(){
        try {
            final UserTransaction transaction = Transaction.instance();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            transaction.enlist(entityManager);
    		//71566
    		for (long i = 1; i < 10000; i++) {
    			if (i==146) {
					continue;// and be happy
				}
    			User user = persistenceService.getUserById(i);
    			List<Tag> userTags = persistenceService.getTagsByUser(user);
    			System.out.println(i);
    			for (Tag userTag : userTags) {
    				UserPreference userPreference = new UserPreference();
    				userPreference.setUser(user);
    				userPreference.setTagFrequency(persistenceService.getTagFrequencyByUser(userTag.getTagName(),user));
    				userPreference.setTag(userTag);
    				entityManager.persist(userPreference);
    			}
    		}
            try {
                entityManager.flush();
                transaction.commit();
            } catch (final Exception exception) {
            	exception.printStackTrace();
            }
        } catch (final Exception exception) {
        	exception.printStackTrace();
        }
		System.out.println("userPreference persited!");        
	}
	
	
	
	/**
	 * 
	 */
	public void computeUserPreference(){
		MovieLensImporter movieLensImporter = new MovieLensImporter("test");
		int cont=START_VALUE;
		System.out.println(START_VALUE);
        try {

            for (long i = cont; i < 71566; i++) {
            	int contTags = 0;
    			if (i==146) {
					continue;// and be happy
				}
    			User user = persistenceService.getUserById(i);
    			List<Tag> userTags = persistenceService.getTagsByUser(user);
    			
    			for (Tag userTag : userTags) {
    	            final UserTransaction transaction = Transaction.instance();
    	            if (!transaction.isActive()) {
    	                transaction.begin();
    	            }
    	            transaction.enlist(entityManager);
    				System.out.println(i);
    				if (contTags++>20) {
						break;
					}
    				String script = movieLensImporter.createInsertScriptsUserPreference(cont++,persistenceService.getTagFrequencyByUser(userTag.getTagName(),user),userTag.getId(), user.getId());
    				FileUtils.writeApendResults(script,USER_PREFERENCE_DIR+"USER_PREFERENCE", "sql");
    				//FileUtils.writeApendResults(script,"H:\\USER_PREFERENCE", "sql");
    	            try {
    	                //entityManager.flush();
    	            	//transaction.commit();
    	            } catch (final Exception exception) {
    	            	//exception.printStackTrace();
    	            }    				
    			}
    		}

        } catch (final Exception exception) {
        	//exception.printStackTrace();
        }
		System.out.println("userPreference persited!");        
	}
	
	
	/**
	 * This method does that this and that
	 * 
	 * @throws Exception
	 */
	public void computeTagRepresentativeness() throws Exception{ 
		// load kiwiResouces from the database
		List<Resource> kiwiresources = persistenceService.getResources();
		// get the tags from kiwi resouces
		HelloLucene helloLucene = new HelloLucene();
		for (Resource kiwiresource : kiwiresources) {	
			Set<Tag> tags = kiwiresource.getTags();
			for (Tag tag : tags) {
				float rep = helloLucene.getRepresentativeness(tag.getTagName(),kiwiresource);
				System.out.println(" tag "+ tag.getTagName()+" from " +" doc "+ kiwiresource.getDescription() +" has representativeness score "+ rep);
				
			}
		}
	}
	

	
	
	/**
	 * It gets the consine similarity
	 */
	public void getConsineSimilarity(){
		List<Resource> kiwiresources = persistenceService.getResources();
		for (Resource kiWiResource : kiwiresources) {
			for (Resource kiWiResource1 : kiwiresources) {
				if(kiWiResource.getId()!=kiWiResource1.getId()){
					float sim=SimilarityMetrics.computeConsineSimilarity(kiWiResource.getTagString(), kiWiResource1.getTagString());
					System.out.println("cosineSmilarity between  "+kiWiResource.getDescription()+" and "+kiWiResource1.getDescription() +" is "+ sim);
				}
			}
		}
	}	
	
	
	/**
	 * @return
	 */
	public String calculate44(){

		List<Statistic> kiList = persistenceService.getKiWiStatistics();
		
		List<Resource> recPrevious = persistenceService.getResourcesPrevious();
		
			System.out.println("Total comparision expected "+((1143 * (1143-1) /2)));
			System.out.println("Total comparision real "+((recPrevious.size() * (recPrevious.size()-1) /2)));
			
			List<SemanticSimilarity> semSim = new ArrayList<SemanticSimilarity>();
			int synt = 0;
			int deli = 0;
			int onto = 0;
			int wn = 0;
			int unit = 0;
			int both = 0;
			for (Statistic kiWiStatisticsCol : kiList) {
				semSim.add(kiWiStatisticsCol.getSemanticSimilarity());
				synt = synt + kiWiStatisticsCol.getCosineSimilarityAmount();
				deli = deli + kiWiStatisticsCol.getSemanticSimilarity().getDeliciousAmount();
				onto = onto + kiWiStatisticsCol.getSemanticSimilarity().getOntoAmount();
				wn = wn + kiWiStatisticsCol.getSemanticSimilarity().getWordNetAmount();
				
				
				if (deli>0 || wn>0 || onto>0 ) {
					unit++;
				}
				
				if (wn>0 && onto>0 ) {
					both++;
				}
				
				
			}
			
			System.out.println("synt "+synt);
			System.out.println("sem unit "+unit);
			System.out.println("sem "+(wn+onto+deli));
			System.out.println("wn "+wn);
			//System.out.println("deli "+deli);
			System.out.println("onto "+onto);
			System.out.println("both "+both);
			
//			Collections.sort(semSim);
//			int i = 0;
//			for (SemanticSimilarity semanticSimilarity : semSim) {
//				if (semanticSimilarity.getSemanticSimilarityScore()<0.9f) {
//					System.out.println(semanticSimilarity.getSemanticSimilarityScore());
//					i++;
//				}
//				
//				
//				if (i==50) {
//					break;
//				}
//			}
			log.info("calculate");
		return "/home.xhtml";  
	}
	
	@End
	public void end(){
		log.info("end");
	}
	
	
	public String correctTags(){
		Random random = new Random();
		
			
		//similarity.printResults(this.getKiWiStatistics());
			List<Tag> kiList = persistenceService.listOddTags();
			for (Tag t : kiList) {
				if (t.getTagName().equals("-")) {
					String name = new ArrayList<String>(Delicious.loadDeliciousTags()).get(random.nextInt(Delicious.loadDeliciousTags().size()));
					t.setTagName(name);
				}

			}
			log.info("correctTags");
		return "/home.xhtml";  
	}
	
		
	public void deleteResource(Resource kiWiResource) {
		try {
				entityManager.remove(kiWiResource);
				entityManager.flush();
		} catch (NonUniqueObjectException e) {
			
		}
		
		//entityManager.flush();
	}
	public void deleteUser(User user) {
		try {
			entityManager.remove(user);
			entityManager.flush();
			
		} catch (NonUniqueObjectException e) {
			
		}
		
		//entityManager.flush();
	}
	public void deleteTag(Tag tag) {
		try {
			entityManager.remove(tag);
			entityManager.flush();
			
		} catch (NonUniqueObjectException e) {
			
		}
		
		//entityManager.flush();
	}
	
	public User saveUser(User user) {
		if (persistenceService.getUserByLogin(user)==null) {
			entityManager.persist(user);
		}
		return persistenceService.getUserByLogin(user);
	}
	
	/**
	 * @param tags
	 */
	public void saveTags(Set<Tag> tags) {
		for (Tag tag : tags) {
			if (persistenceService.getTagByAuthorAndName(tag.getTagAuthor(),tag.getTagName())!=null) {
				tag.setTagAuthor(persistenceService.getUserByLogin(tag.getTagAuthor()));
				entityManager.persist(tag);
				entityManager.flush();
			}

		}
		entityManager.flush();
	}
	
	/**
	 * 
	 */
	public void generateTwitterIndex() {
		LuceneManagerServiceImpl lm = new LuceneManagerServiceImpl();

		String sql = "select id, description " +
			" from resources r " +
			" where description is not null ";	
		
		try {
			
			IndexField[] fieldList = new IndexField[2];
			fieldList[0] = new IndexField("id", "id", Field.Store.YES, Field.Index.NO);
			fieldList[1] = new IndexField("description", "description", Field.Store.YES, Field.Index.TOKENIZED,Field.TermVector.YES);
		
			lm.indexData(sql, fieldList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Index finished !");
		
	}

	/**
	 * 
	 */
	public void generateMovieLensIndex() {
		LuceneManagerServiceImpl lm = new LuceneManagerServiceImpl();

		String sql = "select id, title, description " +
			" from resources r " +
			" where description is not null " +
			" 	and exists ( " +
			"		select 1 " +
			"		from rate " +
			" 		where resource_id = r.id " +
			"		)";		
		
		try {
			lm.connectLuceneDB(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Index finished !");
		
	}
	
	/**
	 * EvaluateMovieLensIndexSearch
	 */
	public void evaluateMovieLensIndexSearch2() {
		/*	
		User user = ((EvaluationUser) persistenceService.getEvaluationUserById(20l)).getUser();
		
		List<Tag> taglabels = persistenceService.getTagLabelsFromRatedResourcesAndUser(3f,user);
		
		for (Tag string : taglabels) {
			System.out.println("The results are here "+string.getTagName());
		}
		
		
		user = ((EvaluationUser) persistenceService.getEvaluationUserById(50160l)).getUser();
		
		taglabels = persistenceService.getTagLabelsFromRatedResourcesAndUser(3f,user);
		
		for (Tag string : taglabels) {
			System.out.println("The results are here "+string.getTagName());
		}		
		
		
	System.out.println("Running.. "+IConstant.DICE_SIMILARITY);
		evaluationService.evaluatePersonalizedSearch(IConstant.DICE_SIMILARITY);
		System.out.println("Running.. "+IConstant.EUCLIDIAN_SIMILARITY);		
		evaluationService.evaluatePersonalizedSearch(IConstant.EUCLIDIAN_SIMILARITY);
		System.out.println("Running.. "+IConstant.JAKARD_SIMILARITY);		
		evaluationService.evaluatePersonalizedSearch(IConstant.JAKARD_SIMILARITY);
		System.out.println("Running.. "+IConstant.MATCHING_COEFFICIENT_SIMILARITY);		
		evaluationService.evaluatePersonalizedSearch(IConstant.MATCHING_COEFFICIENT_SIMILARITY);
		System.out.println("Running.. "+IConstant.SEARCH_SCORE);		
		evaluationService.evaluatePersonalizedSearch(IConstant.SEARCH_SCORE);
		System.out.println("Running.. "+IConstant.STRING_COSINE_SIMILARITY);		
		evaluationService.evaluatePersonalizedSearch(IConstant.STRING_COSINE_SIMILARITY);
		System.out.println("Running.. "+IConstant.PERSONALIZED_SEARCH_SCORE);		
		evaluationService.evaluatePersonalizedSearch(IConstant.PERSONALIZED_SEARCH_SCORE);	
		System.out.println("Running.. "+IConstant.ALL_SEARCH_TYPE);	*/		
		evaluationService.evaluatePersonalizedSearch(IConstant.ALL_SEARCH_TYPE);			
	}
	
	
	public void evaluateMovieLensIndexSearch3() {
		for (long i = 1; i < 95807; i++) {
			User user = ((EvaluationUser) persistenceService.getEvaluationUserById(i)).getUser();
			if ((user == null)) {
				continue;
			}

			List<Tag> userTags = persistenceService.getTagsFromUserPreferenceByUser(user);
			if ((userTags == null) || (userTags.isEmpty())) {
				continue;
			}
			if (userTags.size()<20 &&  userTags.size()>6) { 
				System.out.println("User tags " + userTags.size() + " for user id "+ user.getId());
			}	
		}
			
	}
	
	public void evaluateMovieLensIndexSearch() {
		for (Long i : getUserIds()) {
		//	System.out.println("User tags " + persistenceService.getTagCountByUserId(i));
			System.out.println("User rating tags " + persistenceService.getTagCountFromRatedItemsByUser(i,4f));
			
			}	
		}

	/**
	 * 
	 */
	public void computeTermFrequency() {
		List<IndexTerm> terms = luceneManagerService.getTermFrequency();
		for (IndexTerm term : terms) {
			entityManager.persist(term);
		}
		System.out.println("saved Term Frequency  ");		
	}	

	private static List<Long> getUserIds() {
		List<Long> userIds = new LinkedList<Long>();
		userIds.add(5161l);
		userIds.add(4650l);
		userIds.add(7088l);
		userIds.add(2514l);
		userIds.add(20l);
		userIds.add(3844l);
		userIds.add(3304l);
		userIds.add(33585l);
		userIds.add(5956l);
		userIds.add(2477l);
		userIds.add(50160l);
		userIds.add(3301l);
		userIds.add(4517l);
		userIds.add(3392l);
		userIds.add(7181l);
		userIds.add(190l);
		userIds.add(3825l);
		userIds.add(692l);
		userIds.add(477l);
		userIds.add(2606l);
		userIds.add(713l);
		userIds.add(528l);
		userIds.add(4856l);
		userIds.add(374l);
		userIds.add(3017l);
		userIds.add(1663l);
		userIds.add(49l);
		userIds.add(498l);
		userIds.add(61352l);
		userIds.add(5829l);
		userIds.add(46959l);
		userIds.add(5829l);
		userIds.add(1632l);
		userIds.add(728l);
		userIds.add(7315l);
		userIds.add(3595l);
		userIds.add(27744l);
		userIds.add(2901l);
		userIds.add(4022l);
		userIds.add(2212l);
		userIds.add(7287l);
		userIds.add(1355l);
		userIds.add(3489l);
		userIds.add(636l);
		userIds.add(1224l);
		userIds.add(2978l);
		userIds.add(3825l);
		userIds.add(3392l);
		userIds.add(190l);
		userIds.add(8302l);
		userIds.add(5739l);
		userIds.add(498l);
		userIds.add(61352l);
		userIds.add(4517l);
		userIds.add(61352l);
		userIds.add(3743l);
		userIds.add(7181l);
		userIds.add(1056l);
		userIds.add(1282l);
		userIds.add(3743l);
		userIds.add(2953l);
		userIds.add(2429l);
		userIds.add(3386l);
		userIds.add(3490l);
		userIds.add(4011l);
		userIds.add(31l);
		userIds.add(1920l);
		userIds.add(5917l);
		userIds.add(1081l);
		userIds.add(533l);
		userIds.add(5228l);
		userIds.add(845l);
		userIds.add(1698l);
		userIds.add(181l);
		userIds.add(27685l);
		userIds.add(33880l);
		userIds.add(26704l);
		userIds.add(1920l);
		userIds.add(5917l);
		userIds.add(61240l);
		userIds.add(1081l);
		userIds.add(533l);
		userIds.add(5228l);
		userIds.add(845l);
		userIds.add(1698l);
		userIds.add(181l);
		userIds.add(27685l);
		userIds.add(33880l);
		userIds.add(57274l);
		userIds.add(7885l);
		userIds.add(4078l);
		userIds.add(51086l);
		userIds.add(7369l);
		userIds.add(39l);
		userIds.add(6038l);
		userIds.add(233l);
		userIds.add(2471l);
		userIds.add(8777l);
		userIds.add(299l);

		return userIds;
	}
			
	
	

	
}
/*
 * 
User tags 21 for user id 5161
User tags 26 for user id 4650
User tags 26 for user id 7088
User tags 26 for user id 2514
User tags 50 for user id 3304
User tags 24 for user id 20
User tags 34 for user id 3844
User tags 38 for user id 33585
User tags 34 for user id 5829
User tags 21 for user id 46959
User tags 46 for user id 1632
User tags 25 for user id 728
User tags 25 for user id 7315
User tags 26 for user id 3595
User tags 54 for user id 27744
User tags 24 for user id 2901
User tags 40 for user id 4022
User tags 25 for user id 2212
User tags 45 for user id 7287
User tags 33 for user id 1355
User tags 26 for user id 1224
User tags 26 for user id 3489
User tags 28 for user id 5956
User tags 25 for user id 2477
User tags 22 for user id 3301
User tags 25 for user id 50160
User tags 22 for user id 636
User tags 25 for user id 2978
User tags 25 for user id 3825
User tags 25 for user id 692
User tags 35 for user id 477
User tags 30 for user id 713
User tags 25 for user id 528
User tags 41 for user id 2606
User tags 29 for user id 4856
User tags 30 for user id 3017
User tags 52 for user id 374
User tags 27 for user id 1663
User tags 30 for user id 49
User tags 25 for user id 1056
User tags 30 for user id 1282
User tags 25 for user id 3743
User tags 51 for user id 5739
User tags 23 for user id 498
User tags 37 for user id 61352
User tags 38 for user id 4517
User tags 25 for user id 7181
User tags 44 for user id 3392
User tags 52 for user id 190
User tags 40 for user id 8302
 */
