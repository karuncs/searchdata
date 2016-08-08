package aau;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

import jena.LinkedOpenData;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import parser.ExtremmingTag;
import parser.KiWiDeliParser;
import sesame.IConstant;
import sesame.Sesame;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity;
import util.StringUtils;
import watson.TestOntologySearch;
import data.Data;
import delicious.DeliciousData;


@Name("similarity")
public class Similarity {
	
	@In
	private EntityManager entityManager;
	
	
	@In(create=true)
	private PersistenceService persistenceService;
	
	public static void main(String[] args) {
	
		System.out.println("Total comparision expected "+((12158  * (12158 -1) /2)));
		
//		double dArray[] = {1.5, 34.6, 433.1};
//		
//		Utils.normalize(dArray);
//		
//		for (int i = 0; i < dArray.length; i++) {
//			System.out.println(dArray[i]);
//		}
		
		
		//System.out.println(normalize(-3.5 , -3.5));

		Similarity similarity = new Similarity();
//		
//		//similarity.saveDeliciousData();
//
//		//similarity.saveDeliciousApiData();
//
	similarity.getSimilarResourcesByTag();
	}
	
	private static double normalize(double a, double b){
		double doube = a / Math.sqrt((a*a)+b);	
		double rest = 0.5 * (1-doube);
		return rest;
	}
	
	 
	
	public void saveDeliciousData() {
		KiWiDeliParser kiWiDeliParser = new KiWiDeliParser();
//		String stringTag="design,blog,video,software,tools,music,programming,webdesign,reference,tutorial,art,web,howto,javascript,free,linux,web2.0,development,google,inspiration,photography,news,food,flash,css,blogs,education,business,technology,travel";
		String stringTag="programming,software,development";		
		for (String termTag : StringUtils.getToken(stringTag, ",")) {
			Set<Resource> kiwiAllResources = kiWiDeliParser.getKiWiDeliData("http://delicious.com/popular/"+termTag);
			List<Resource> kiwiAllResourcesList = new ArrayList<Resource>(kiwiAllResources);
			Sesame.saveKiWiData(Sesame.getRepository(), kiwiAllResourcesList);
		}
    }	

	
	/**
	 * 
	 */
	public void saveDeliciousApiData() {
		DeliciousData deliciousData = new DeliciousData();
		Sesame.saveKiWiData(Sesame.getRepository(), deliciousData.getDeliciousBookmarkings());
		
    }
	
	
	public Set<Resource> getTestData() {
		Data data = new Data();
		return data.getData();
	}

	int individualUser = 0;
	
	int totalGroundgs = 0;
	
	List<Statistic> kiWiStatisticsRec = new ArrayList<Statistic>();
	
	
	public void getSimilarResourcesByTag() {
		//Data data = new Data();
		KiWiDeliParser kiWiDeliParser = new KiWiDeliParser();
		List<Resource> kiwiAllResources = new ArrayList<Resource>(); 
		kiwiAllResources.addAll(kiWiDeliParser.getKiWiDeliData("http://delicious.com/tag/programming+java+software+tutorial",5));
		getSimilarResourcesByTag(kiwiAllResources);
	}
	
	/**
	 * 
	 */
	//public List<KiWiStatistic> getSimilarResourcesByTag() {
	public List<Statistic> getSimilarResourcesByTag(List<Resource> kiwiAllResources) {

		
		
		int totalRec = kiwiAllResources.size();
		System.out.println("Total comparision expected "+((totalRec * (totalRec-1) /2)));
		
		
		
//		Set<KiWiResource> kiwiAllResources = Sesame.loadKiWiData(Sesame.getRepository());
//		Set<KiWiResource> kiwiAllResources = getTestData();
		//Set<KiWiResource> kiwiAllResources = Sesame.loadKiWiData(Sesame.getFlickrRepository());
		
		
		//Set<KiWiResource> kiwiAllResources = Sesame.loadKiWiData(Sesame.getDiggRepository());

		AbstractStringMetric cosineSimilarity = new CosineSimilarity();

        List<Resource> kiwiAllResourcesList = new ArrayList<Resource>(kiwiAllResources);
        
        Statistic kiWiStatistic = null;
        
        List<Statistic> kiWiStatistics = new ArrayList<Statistic>();
        
        
        
        Set<String> firstURI = new HashSet<String>(); 
        
        int compare = 0;
        
        int loadMTF = 0;
        
        for (int i = 0; i < kiwiAllResourcesList.size(); i++) {
        	
			boolean hasTag = false;
			
			int firstTagAmount = 0;
			
			int secondTagAmount = 0;				
			
			Resource firstKiWiResource = kiwiAllResourcesList.get(i);
			
			
//			if (!firstKiWiResource.getAuthor().getName().equals("user_2001")) {
//				continue;
//			}
        	
			for (int j = i; j < kiwiAllResourcesList.size(); j++) {
				
				Resource secondKiWiResource = kiwiAllResourcesList.get(j);
				
//				String firstAuthor = kiwiAllResourcesList.get(i).getAuthor().getName();
//				
//				String secondAuthor = kiwiAllResourcesList.get(j).getAuthor().getName();
				
				if (kiwiAllResourcesList.get(i).getTags()!=null && kiwiAllResourcesList.get(j).getTags()!=null){
					hasTag = true;
					firstTagAmount = kiwiAllResourcesList.get(i).getTags().size();
					secondTagAmount = kiwiAllResourcesList.get(j).getTags().size();
				}
				if (hasTag && (i!=j)) {	
					
				Statistic kiWiStatisticTest =  persistenceService.getStatisticByResources(firstKiWiResource,secondKiWiResource);
				if (kiWiStatisticTest != null) {
					System.out.println("Already done...");
					compare++;
					continue;
				}
				
				//System.out.println("comparing "+firstKiWiResource.getId() +" aginst resource "+secondKiWiResource.getId());
				//if (hasTag && (i!=j)&&(!firstAuthor.equalsIgnoreCase(secondAuthor))&& (firstTagAmount>IConstant.MINIMUM_TAG_PER_DOCUMENT)&& (secondTagAmount>IConstant.MINIMUM_TAG_PER_DOCUMENT)) {				
						
					//if (hasUserTagAfinity(kiwiAllResourcesList.get(i), kiwiAllResourcesList.get(j),kiWiStatistic)){
						
				

						
						kiWiStatistic = new Statistic();					
						
						kiWiStatistic.setConsineSimilarity(cosineSimilarity.getSimilarity(firstKiWiResource.getTagString(), secondKiWiResource.getTagString()));
						kiWiStatistic.setComparasionValue(kiWiStatistic.getConsineSimilarity());
						
						if (kiWiStatistic.getConsineSimilarity()>0) {
							kiWiStatistic.addCosineSimilarity(1);
						}else{
							compare++;
							continue;
						}
						
						compare++;
						
						//kiWiStatistic.setComparasionValue(cosineSimilarity.getSimilarity(firstKiWiResource.getSemanticTagString(), secondKiWiResource.getTagString()));
						
						kiWiStatistic.createNewSemanticSimilarity();
						System.out.println("item compared number  "+compare);
						compareTags(firstKiWiResource, secondKiWiResource, kiWiStatistic);

//						if (!firstURI.contains(firstKiWiResource.getAuthor().getName()+"_URI_"+secondKiWiResource.getUri())){
//							try {
//								if (firstKiWiResource.getAuthor().getTopTags()==null){
//									loadMTF = loadMTF+1;
//									firstKiWiResource.getAuthor().setTopTags(Sesame.getMostFrequentTagByUser(Sesame.getRepository().getConnection(), Sesame.extractBrackets(firstAuthor)));
//								}
//								if (secondKiWiResource.getAuthor().getTopTags()==null){
//									loadMTF = loadMTF+1;
//									secondKiWiResource.getAuthor().setTopTags(Sesame.getMostFrequentTagByUser(Sesame.getRepository().getConnection(), Sesame.extractBrackets(secondAuthor)));
//								}
//							} catch (RepositoryException e) {
//								e.printStackTrace();
//							}
//
//							if(firstKiWiResource.getAuthor().getTopTags()!= null && !firstKiWiResource.getAuthor().getTopTags().isEmpty() && secondKiWiResource.getTags()!= null && !secondKiWiResource.getTags().isEmpty()
//							){
//								calculateUserTagAfinity(firstKiWiResource, secondKiWiResource, kiWiStatistic);
//								firstURI.add(firstKiWiResource.getAuthor().getName()+"_URI_"+secondKiWiResource.getUri());
//							}
//							
//						}

						kiWiStatistic.setFirstResource(firstKiWiResource);
						
						kiWiStatistic.setSecondResource(secondKiWiResource);
						
						if (compare>400) {
							break;
						}
						
						if (kiWiStatistic.getConsineSimilarity()>0f || kiWiStatistic.getSemanticSimilarity().getSemanticSimilarityScore()>0f) {
							entityManager.persist(kiWiStatistic);
							//entityManager.flush();
							//kiWiStatistics.add(kiWiStatistic);	
						}
						

						
					//}
					
				}
			}	
		}
        
        

    	
        System.out.println("Total of comparisions "+compare);

        System.out.println("Total de karstens  "+individualUser);
        
        System.out.println("Total de loadMTF  "+loadMTF);
               
        Collections.sort(kiWiStatistics);
        kiWiStatisticsRec = kiWiStatistics;
        //printResults(kiWiStatistics);
    	
        System.out.println("Rec to be processed "+kiWiStatisticsRec.size());
        
       //doRecommendation(kiWiStatisticsRec);
        
        System.out.println("Total de comparisions  "+kiWiStatistics.size());
        
        System.out.println("Total de comparisions higher than threshold  "+kiWiStatisticsRec.size());

        
        if (totalGroundgs>0) {
        	System.out.println("Total de groundings "+totalGroundgs);	
		}
        
        
             
        return kiWiStatisticsRec;
	}
	
	
	
	public void printResults(List<Statistic> kiWiStatistics){
	       for (Statistic kiWiStatisticsCol : kiWiStatistics) {
//	        	if (kiWiStatisticsCol.getComparasionValue()>0.0f && !kiWiStatisticsCol.getFirstKiWiResource().getUri().equals(kiWiStatisticsCol.getSecondKiWiResource().getUri())) {
	        		kiWiStatisticsRec.add(kiWiStatisticsCol);
	   //fred     		
//	        		System.out.println( " RecommenderScore: "+kiWiStatisticsCol.getComparasionValue()+
//	        					        " 	cosineSimilarity: "+kiWiStatisticsCol.getConsineSimilarity()+
//	        							" 	SemanticSimilarity: "+kiWiStatisticsCol.getSemanticSimilarity().getSemanticSimilarityScore()+
//	        				            " 	Cosine: "+kiWiStatisticsCol.getCosineSimilarityAmount()+
//	        				            " 	Delicious: "+kiWiStatisticsCol.getSemanticSimilarity().getDeliciousAmount()+
//	        	        				" 	Ontological: "+kiWiStatisticsCol.getSemanticSimilarity().getOntoAmount()
//	        				           );
	        		
	        		System.out.println( "  "+kiWiStatisticsCol.getComparasionValue()+

	        				"	"+StringUtils.getDecimalFormat(kiWiStatisticsCol.getConsineSimilarity())+
	        				"	"+StringUtils.getDecimalFormat(kiWiStatisticsCol.getSemanticSimilarity().getSemanticSimilarityScore())+
	        				//"	"+StringUtils.getDecimalFormat((1 - (kiWiStatisticsCol.getConsineSimilarity()+ kiWiStatisticsCol.getSemanticSimilarity().getSemanticSimilarityScore())))+
	        				//"	"+kiWiStatisticsCol.getCosineSimilarityAmount()+
	        				//"	"+kiWiStatisticsCol.getSemanticSimilarity().getDeliciousAmount()+
	        				//"	"+kiWiStatisticsCol.getSemanticSimilarity().getOntoAmount() +
	        				//"	"+kiWiStatisticsCol.getSemanticSimilarity().getWordNetAmount() +
	        				//"	"+ ((kiWiStatisticsCol.getConsineSimilarity()+ kiWiStatisticsCol.getSemanticSimilarity().getSemanticSimilarityScore())<=1)+
	        				//"	"+kiWiStatisticsCol.getFirstKiWiResource().getDesc()+
	        				//"	"+kiWiStatisticsCol.getSecondKiWiResource().getDesc()+
	        				"	"+kiWiStatisticsCol.getFirstResource().getTagString()+        				
	        				"	"+kiWiStatisticsCol.getSecondResource().getTagString()        				
	        		);
	        		
	        		//System.out.println("Ranking  "+kiWiStatisticsCol.getComparasionValue()+"   Comparing   "+kiWiStatisticsCol.getFirstKiWiResource().getDesc()+"   with tags "+kiWiStatisticsCol.getFirstKiWiResource().getTagString()+"  WITH   "+kiWiStatisticsCol.getSecondKiWiResource().getDesc()+"  "+ kiWiStatisticsCol.getSecondKiWiResource().getTagString());
	        		
	        		//System.out.println("Sintax  "+(kiWiStatisticsCol.getComparasionValue() - kiWiStatisticsCol.getSemanticSimilarity().getSemanticSimilarityScore())+"   Semantic   "+kiWiStatisticsCol.getSemanticSimilarity().getSemanticSimilarityScore());
	        		
//	        		System.out.println("Total of  "+kiWiStatisticsCol.getSemanticSimilarity().getTotalAmountMatching()+"   semanitc similarities   with " +
//	        				" Delicious:  "+kiWiStatisticsCol.getSemanticSimilarity().getDeliciousAmount()+"  and " +
//	        				" Ontological "+kiWiStatisticsCol.getSemanticSimilarity().getOntoAmount());
	        		
	       //System.out.println("Ranking  "+kiWiStatisticsCol.getComparasionValue()+"   Comparing   "+kiWiStatisticsCol.getFirstKiWiResource().getDesc()+"  with   "+kiWiStatisticsCol.getSecondKiWiResource().getDesc());	
	        		//System.out.println("Ranking  "+kiWiStatisticsCol.getComparasionValue()+"   Comparing   "+kiWiStatisticsCol.getFirstKiWiResource().getDesc()+"   with tags "+kiWiStatisticsCol.getFirstKiWiResource().getTagString()+"  WITH   "+kiWiStatisticsCol.getSecondKiWiResource().getDesc()+"  "+ kiWiStatisticsCol.getSecondKiWiResource().getTagString());	
//	        		System.out.println("Ranking  "+kiWiStatisticsCol.getComparasionValue()+"   user   "+kiWiStatisticsCol.getFirstKiWiResource().getAuthor().getName()+"   Comparing   "+kiWiStatisticsCol.getFirstKiWiResource().getLink()+"  with   user2   "+kiWiStatisticsCol.getSecondKiWiResource().getAuthor().getName()+"  link   "+kiWiStatisticsCol.getSecondKiWiResource().getLink());	
//	        		System.out.println("Ranking  "+kiWiStatisticsCol.getComparasionValue()+
//	        				"  user1   "+kiWiStatisticsCol.getFirstKiWiResource().getAuthor().getName()+
//	        				"  Comparing   "+kiWiStatisticsCol.getFirstKiWiResource().getTagString()+
//	        				"  with   user2   "+kiWiStatisticsCol.getSecondKiWiResource().getAuthor().getName()+
//	        				"  link   "+kiWiStatisticsCol.getSecondKiWiResource().getTagString());	
				//}
	        	//if (c == 10  ) {
	        	//	break;
	        	//}
	        	//c++;
			}
	}
	

	
	/**
	 * @param kiWiResourceFirst
	 * @param kiWiResourceSecond
	 * @param kiWiStatistic
	 */
	private void calculateUserTagAfinity(Resource kiWiResourceFirst, Resource kiWiResourceSecond, Statistic kiWiStatistic){

			List<String> mostFrequentTags = new ArrayList<String>(kiWiResourceFirst.getAuthor().getTopTags());
			
				if (Sesame.extractBrackets(kiWiResourceFirst.getAuthor().getName()).equals("user_2018")) {
					individualUser = individualUser + 1;
					//System.out.println("Comparing resource de Karsten "+ kiWiResourceFirst.getDesc());
					
					if (Sesame.extractBrackets(kiWiResourceSecond.getAuthor().getName()).equals(Sesame.extractBrackets(kiWiResourceFirst.getAuthor().getName()))) {
						System.out.println("ERRADO !!!!!");
					}
				}
				if (Sesame.extractBrackets(kiWiResourceSecond.getAuthor().getName()).equals(Sesame.extractBrackets(kiWiResourceFirst.getAuthor().getName()))) {
					System.out.println("ERRADO !!!!!");
				}
			
				for (Tag tag : kiWiResourceSecond.getTags()) {
						for (int i = 0; i < mostFrequentTags.size() ; i++) {
							//System.out.println("Comparing "+Sesame.extractBrackets(mostFrequentTags.get(i))+" with "+  tag.getTagName());
							if ( Sesame.extractBrackets(mostFrequentTags.get(i)).equalsIgnoreCase(tag.getTagName())) {
								//System.out.println("calculating affinity of " +kiWiResourceFirst.getAuthor().getName() + "  against "+kiWiResourceSecond.getAuthor().getName());
								kiWiStatistic.setComparasionValue(kiWiStatistic.getComparasionValue() * IConstant.USER_TAG_AFFINITY);
							}
						}
				}
		}

	/**
	 * @param recommendations
	 * @param recommendation
	 * @return
	 */
	public boolean existRec(Set<Recommendation> recommendations, Recommendation recommendation){
		boolean exist = false;
		for (Recommendation recommendationIndividual : recommendations) {
			if (recommendationIndividual.getKiWiResource().getLink().equals(recommendation.getKiWiResource().getLink()) && (recommendationIndividual.getUser().getName().equals(recommendation.getUser().getName()))) {
				exist = true;
			}
		}
		return exist;
	}
	
	
	/**
	 * @param kiWiStatistics
	 */
	public void doRecommendation(List<Statistic> kiWiStatistics) {
		Set<Recommendation> recommendations = new HashSet<Recommendation>(); 
		Recommendation recommendation = null;
		for (Statistic kiWiStatisticRec : kiWiStatistics) {
        	Resource firstKiWiResource = kiWiStatisticRec.getFirstResource();
        	Resource secondKiWiResource = kiWiStatisticRec.getSecondResource();
        	for (Tag tagFirst : firstKiWiResource.getTags()) {
            	for (Tag tagSecond : secondKiWiResource.getTags()) {
            		if (!tagSecond.getTagAuthor().getName().equals(firstKiWiResource.getAuthor().getName())
            							&& !tagFirst.getTagAuthor().getName().equals(tagSecond.getTagAuthor().getName())) {
            			recommendation = new Recommendation();
            			//System.out.println(firstKiWiResource.getDesc());
            			
            			recommendation.setKiWiResource(firstKiWiResource);
            			recommendation.setUser(tagSecond.getTagAuthor());
            			recommendation.setKiWiStatistic(kiWiStatisticRec);
            			recommendation.setRecomendationScore(kiWiStatisticRec.getComparasionValue());

            			if (recommendation!=null && !existRec(recommendations,recommendation)){
            				recommendations.add(recommendation);
            			}
            			
            		}
    			}
			}

        }
		
		if (!recommendations.isEmpty()) {
			showRecommendationsByUser(recommendations);
		}
		
		
		//showRecommendationsByUserFlickr(recommendations);
		
//    	for (Recommendation recommendationEnd : recommendations) {
//    		System.out.println("User "+recommendationEnd.getUser().getName()+"   should be recommended to  "+recommendationEnd.getKiWiResource().getDesc()+"  at   "+recommendationEnd.getKiWiResource().getLink());
//		}
		
		

		System.out.println("Total de recommendations  "+recommendations.size());
		
	}
	
	
	/**
	 * @param kiWiStatistics
	 */
	public void doRecommendationAdvanced(List<Statistic> kiWiStatistics) {
		Set<Recommendation> recommendations = new HashSet<Recommendation>(); 
		Recommendation recommendation = null;
		for (Statistic kiWiStatisticRec : kiWiStatistics) {
        	Resource firstKiWiResource = kiWiStatisticRec.getFirstResource();
        	Resource secondKiWiResource = kiWiStatisticRec.getSecondResource();
        	recommendation = new Recommendation();
			recommendation.setKiWiResource(firstKiWiResource);
			recommendation.setUser(secondKiWiResource.getAuthor());
			recommendation.setKiWiStatistic(kiWiStatisticRec);
			recommendation.setRecomendationScore(kiWiStatisticRec.getComparasionValue());
			if (recommendation!=null && !existRec(recommendations,recommendation)){
				recommendations.add(recommendation);
			}
		}
            			
         
		showRecommendationsByUser(recommendations);
		
		//showRecommendationsByUserFlickr(recommendations);
		
		
//    	for (Recommendation recommendationEnd : recommendations) {
//    		System.out.println("User "+recommendationEnd.getUser().getName()+"   should be recommended to  "+recommendationEnd.getKiWiResource().getDesc()+"  at   "+recommendationEnd.getKiWiResource().getLink());
//		}
		
		System.out.println("Total de recommendations  before 8442");
        System.out.println("Total de recommendations  "+recommendations.size());
	}	

	/**
	 * @param recommendations
	 */
	public void showRecommendationsByUser(Set<Recommendation> recommendations) {
			
			List<Recommendation> rec = new ArrayList<Recommendation>(recommendations);
			
			Collections.sort(rec);
			int stop = 0;
				//for (int i = 2020; i < 2021; i++) {
				for (int i = 2000; i < 2045; i++) {
					String user = "user_"+i;
						if (i!=2007 && i!=2041) {
							System.out.println("Set<String> recs_"+i+" = new HashSet<String>();");
							for (Recommendation recommendationEnd : rec) {
					    		//if (stop < 800000 && StringUtils.replace(recommendationEnd.getUser().getName(),"\"","").equalsIgnoreCase(user)) {
					    		if (stop < 5 && StringUtils.replace(recommendationEnd.getUser().getName(),"\"","").equalsIgnoreCase(user)) {
					    			String test = recommendationEnd.getKiWiResource().getDescription()+"  authored by   "+recommendationEnd.getKiWiResource().getAuthor().getName()+"  at   "+recommendationEnd.getKiWiResource().getLink()+"  with TAGS:   "+recommendationEnd.getKiWiResource().getTagString();
					    			//System.out.println("recs_"+i+".add(\""+recommendationEnd.getKiWiResource().getDesc()+"  authored by   "+recommendationEnd.getKiWiResource().getAuthor().getName()+"  at   "+recommendationEnd.getKiWiResource().getLink()+"  with TAGS:   "+recommendationEnd.getKiWiResource().getTagString()+"\");");
					    			System.out.println("recs_"+i+".add(\""+test.replace("\"", "")+"\");");
					    			stop++;
								}
							}
							System.out.println("mapRec.put(\""+user+"\", recs_"+i+");");
		                stop = 0;
		                System.out.println("  ");                
					}
				
			}
	}
	
	/**
	 * @param recommendations
	 */
	public void showRecommendationsByUserFlickr(Set<Recommendation> recommendations) {
		
		List<Recommendation> rec = new ArrayList<Recommendation>(recommendations);
		Collections.sort(rec);
		
		List<String> users = new ArrayList(Sesame.getAllUsers(Sesame.getFlickrRepository()));
		
		int stop = 0;
		//for (int i = 2020; i < 2021; i++) {
		for (int i = 0; i < users.size(); i++) {
			String user = users.get(i);
			
				System.out.println("Set<String> recs_"+i+" = new HashSet<String>();");
				for (Recommendation recommendationEnd : rec) {
					//if (stop < 800000 && StringUtils.replace(recommendationEnd.getUser().getName(),"\"","").equalsIgnoreCase(user)) {
					if (stop < 5 && StringUtils.replace(recommendationEnd.getUser().getName(),"\"","").equalsIgnoreCase(user)) {
						System.out.println("recs_"+i+".add(\""+recommendationEnd.getKiWiResource().getDescription()+"  authored by   "+recommendationEnd.getKiWiResource().getAuthor().getName()+"  at   "+recommendationEnd.getKiWiResource().getLink()+"  with TAGS:   "+recommendationEnd.getKiWiResource().getTagString()+"\");");
						stop++;
					}
				}
				System.out.println("mapRec.put(\""+user+"\", recs_"+i+");");
				stop = 0;
				System.out.println("  ");                
			}
	}
	/**
	 * @param recommendations
	 */
	public void showRecommendationsByUserBefore(Set<Recommendation> recommendations) {
		
		List<Recommendation> rec = new ArrayList<Recommendation>(recommendations);
		Collections.sort(rec);
		int stop = 0;
		//for (int i = 2020; i < 2021; i++) {
		for (int i = 2000; i < 2045; i++) {
			String user = "user_"+i;
			if (i!=2007 && i!=2041) {
				for (Recommendation recommendationEnd : rec) {
					//if (stop < 800000 && StringUtils.replace(recommendationEnd.getUser().getName(),"\"","").equalsIgnoreCase(user)) {
					if (stop < 5 && StringUtils.replace(recommendationEnd.getUser().getName(),"\"","").equalsIgnoreCase(user)) {
						System.out.println("Score "+recommendationEnd.getRecomendationScore()+" User "+user+" should be recommended to  "+recommendationEnd.getKiWiResource().getDescription()+"  authored by   "+recommendationEnd.getKiWiResource().getAuthor().getName()+"  at   "+recommendationEnd.getKiWiResource().getLink()+"  with TAGS:   "+recommendationEnd.getKiWiResource().getTagString());
						//System.out.println("User "+user+" score  "+recommendationEnd.getRecomendationScore());
						stop++;
					}
				}
				stop = 0;
				System.out.println("  ");                
			}
			
		}
		
	}
	

	/**
	 * @param kiWiResourceFirst
	 * @param kiWiResourceSecond
	 * @param kiWiStatistic
	 */
	public void compareTags(Resource kiWiResourceFirst, Resource kiWiResourceSecond, Statistic kiWiStatistic){
		
		float generalTagFrequency = (kiWiResourceFirst.getGeneralTagFrequency()+ kiWiResourceSecond.getGeneralTagFrequency());
		
		float generalWeightFrequency = (kiWiResourceFirst.getGeneralTagWeight()+ kiWiResourceSecond.getGeneralTagWeight());
		
		float documentsScore = (generalTagFrequency + generalWeightFrequency);
		
		documentsScore = normalize(documentsScore);
		
		//float contextScore = getContextValue(kiWiResourceFirst, kiWiResourceSecond);
		
		//float semanticRelationScore = 0f;//calculateSemanticRelation(kiWiResourceFirst, kiWiResourceSecond, kiWiStatistic, contextScore);
		
		calculateSemanticRelation(kiWiResourceFirst, kiWiResourceSecond, kiWiStatistic);
		SemanticSimilarity semanitcSimilarity = kiWiStatistic.getSemanticSimilarity();
		System.out.println( "Final Semantic Score "+semanitcSimilarity.getSemanticSimilarityScore());
	//	System.out.println(" score semantic relation " + semanticRelationScore);
		
//		calculateGroundings(kiWiResourceFirst, kiWiResourceSecond, kiWiStatistic, contextScore);
		
		
		
		if (semanitcSimilarity.getSemanticSimilarityScore()>0) {
			//System.out.println(kiWiStatistic.getComparasionValue());
			
			kiWiStatistic.setComparasionValue(kiWiStatistic.getComparasionValue() * semanitcSimilarity.getSemanticSimilarityScore());
			
//			if (kiWiStatistic.getComparasionValue()>0) {
//				kiWiStatistic.setComparasionValue(kiWiStatistic.getComparasionValue() * semanitcSimilarity.getSemanticSimilarityScore());	
//			}else {
//				kiWiStatistic.setComparasionValue(semanitcSimilarity.getSemanticSimilarityScore());
//			}
			
			//System.out.println(kiWiStatistic.getComparasionValue());
		}
		
		
		if (documentsScore>0) {
			//System.out.println(kiWiStatistic.getComparasionValue());
			if (kiWiStatistic.getComparasionValue()>0) {
				kiWiStatistic.setComparasionValue(kiWiStatistic.getComparasionValue() * documentsScore);	
			}else {
				kiWiStatistic.setComparasionValue(documentsScore);
			}
			
			
			
			normalizeSimilarFactor(kiWiStatistic);
			//System.out.println(kiWiStatistic.getComparasionValue());
		}

		
//		if (contextScore>0) {
//			kiWiStatistic.setComparasionValue(kiWiStatistic.getComparasionValue() * contextScore);
//		} else {
//			kiWiStatistic.setComparasionValue(kiWiStatistic.getComparasionValue());
//		}
	}
	
	
	ExtremmingTag extremmingTag = new ExtremmingTag();
	
	//WordNetFinder wordNetFinder = new WordNetFinder();
	
	Map<String, Float> semanticOntoRelations = new HashMap<String,Float>();
	
	Map<String, Float> semanticDeliciousRelations = new HashMap<String,Float>();
	
	Map<String, Float> semanticWordNetRelations = new HashMap<String,Float>();
	
	
	private void calculateSemanticRelation(Resource kiWiResourceFirst, Resource kiWiResourceSecond, Statistic kiWiStatistic) {

		float ontRelationScore = 0.0f;
		float deliRelationScore = 0.0f;
		float wordNetScore = 0.0f;
		float count = 0f;

		float totalDiff = 0.0f;
		
		SemanticSimilarity semanticSimilarity = kiWiStatistic.getSemanticSimilarity();
		TestOntologySearch testOntologySearch = new TestOntologySearch();
		LinkedOpenData linkedOpenData = new LinkedOpenData();
		
		float maxSemanticScore = (1 - kiWiStatistic.getConsineSimilarity());
		//System.out.println(maxSemanticScore);
	    System.out.println(":::::::::::::NOVO ITEM::::::::::::::::::::::  ");
		
		
		
		if (kiWiResourceFirst.getTags()!=null && kiWiResourceSecond.getTags()!=null){
			//System.out.println("Total max "+ kiWiResourceFirst.getTags().size()* kiWiResourceSecond.getTags().size());
			for (Tag tag : kiWiResourceFirst.getTags()) {
				for (Tag tagSecond : kiWiResourceSecond.getTags()) {
					//System.out.println(semanticSimilarity.getOntoAmount()+"  "+semanticSimilarity.getDeliciousAmount());
				String tagFirst = Sesame.extractBrackets(tag.getTagName().toLowerCase());
				String secondTag = Sesame.extractBrackets(tagSecond.getTagName().toLowerCase());
					if ( (!secondTag.equals(tagFirst))){
						totalDiff++;
					}
				}
			}
		}
		
		//System.out.println("Total diff "+totalDiff);

		if (kiWiResourceFirst.getTags()!=null && kiWiResourceSecond.getTags()!=null){
			
				for (Tag tag : kiWiResourceFirst.getTags()) {
					for (Tag tagSecond : kiWiResourceSecond.getTags()) {
						//System.out.println(semanticSimilarity.getOntoAmount()+"  "+semanticSimilarity.getDeliciousAmount());
					String tagFirst = Sesame.extractBrackets(tag.getTagName().toLowerCase());
					String secondTag = Sesame.extractBrackets(tagSecond.getTagName().toLowerCase());
					if ( (!secondTag.equals(tagFirst))){
						
						try {
							
							    //**********************   ONTOLOGICAL   **********************
							    //System.out.println(" comparing  "+tagFirst +" with "+ secondTag);
								if (!semanticOntoRelations.keySet().contains(tagFirst+"_"+secondTag)) {
									boolean wt = testOntologySearch.findRelations(tagFirst, secondTag)>0;
									boolean jt = linkedOpenData.findOpenDataRelations(tagFirst,secondTag);
									if (jt) {
										System.out.println(" found relation between  "+tagFirst +" and "+ secondTag);
									}
									if(wt || jt){
										ontRelationScore = 1;
									}
									semanticOntoRelations.put(tagFirst+"_"+secondTag, ontRelationScore);
								}else {
									ontRelationScore = semanticOntoRelations.get(tagFirst+"_"+secondTag);
								}
								
								//System.out.println(" verifying relation between  "+tagFirst +" and "+ secondTag);
								if (ontRelationScore>0) {
									//semanticSimilarity.setSemanticSimilarityScore(semanticSimilarity.getSemanticSimilarityScore() + ontRelationScore);
								
									semanticSimilarity.addOntoMatching(1);
									//System.out.println(" found ontological relation between  "+tagFirst +" and "+ secondTag);
								}
							
								
								
								//**********************   DELICIOUS   ******************************************
								
								if (!semanticDeliciousRelations.keySet().contains(tagFirst+"_"+secondTag)) {
									if(extremmingTag.isRelatedTerm(tagFirst,secondTag)){
										//something wrong here
										deliRelationScore = 1;
									}
									semanticDeliciousRelations.put(tagFirst+"_"+secondTag, deliRelationScore);
									
								}else {
									deliRelationScore = semanticDeliciousRelations.get(tagFirst+"_"+secondTag);
								}
							
								if (deliRelationScore>0) {
									//System.out.println(" found relateness between  "+tagFirst +" and "+ secondTag);
									//semanticSimilarity.setSemanticSimilarityScore(semanticSimilarity.getSemanticSimilarityScore() + deliRelationScore);
									
									semanticSimilarity.addDeliciousMatching(1);
								}

								//**********************   WORDNET   ******************************************
								
								if (!semanticWordNetRelations.keySet().contains(tagFirst+"_"+secondTag)) {
	//								if(wordNetFinder.isWNRelation(tagFirst,secondTag)){
//										wordNetScore = 1;
		//							}
									semanticWordNetRelations.put(tagFirst+"_"+secondTag, wordNetScore);
									
								}else {
									wordNetScore = semanticWordNetRelations.get(tagFirst+"_"+secondTag);
								}
								
								if (wordNetScore>0) {
									//System.out.println(" found relateness between  "+tagFirst +" and "+ secondTag);
									//semanticSimilarity.setSemanticSimilarityScore(semanticSimilarity.getSemanticSimilarityScore() + wordNetScore);
									semanticSimilarity.addWordNetMatching(1);
								}
								
								
								
								if (totalDiff>0 && (deliRelationScore>0 || ontRelationScore>0 || wordNetScore>0)) {
									//float s1 = kiWiResourceFirst.getTags().size();
									//float s2 = kiWiResourceSecond.getTags().size();
									//float unitSemanticTagScore = (maxSemanticScore / totalDiff);
									
									//float median = ((ontRelationScore * 3) + (wordNetScore * 3) +  (deliRelationScore * 4))/10;
									//float individualSimilarScore = unitSemanticTagScore;
									count = count + (1.0f/totalDiff);
									
									//semanticSimilarity.setSemanticSimilarityScore(semanticSimilarity.getSemanticSimilarityScore() + individualSimilarScore);
									semanticSimilarity.setSemanticSimilarityScore(count);
									//System.out.println("maxSemanticScore  "+maxSemanticScore+"  sim score "+semanticSimilarity.getSemanticSimilarityScore());
									
									//System.out.println("cosine  "+kiWiStatistic.getConsineSimilarity()+" maxSemanticScore  "+maxSemanticScore+"  sim score "+count+ "  before "+semanticSimilarity.getSemanticSimilarityScore());
																		
									deliRelationScore = 0.0f;
									ontRelationScore = 0.0f;
									wordNetScore = 0.0f;
									//median = 0.0f;
									
								}
								
								
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
					}
				}
			}

			totalDiff = 0f;
		}

	
//	private void calculateGroundings(KiWiResource kiWiResourceFirst, KiWiResource kiWiResourceSecond,KiWiStatistic kiWiStatistic, float contextScore) {
//		//Set<Grounding> groundings = new HashSet<Grounding>();
//		if (contextScore>0f && kiWiResourceFirst.getSemanticExpansions()!=null){
//			for (SemanticExpansion semanticExpansionFirst : kiWiResourceFirst.getSemanticExpansions()) {
//				for (Tag tag : kiWiResourceSecond.getTags()) {
//					
//					String tagString = Sesame.extractBrackets(tag.getTagName().toLowerCase());
//					String expandString = Sesame.extractBrackets(semanticExpansionFirst.getTag().getTagName().toLowerCase());
//					
//					//if ( (!expandString.equals(tagString)) && (verifyWord(tagString,expandString))) {
//					if ( (!expandString.equals(tagString)) && (expandString.contains(tagString))) {
//						
//						System.out.println(" expandString "+expandString);
//						System.out.println(" tagString    "+tagString);
//						
//						Grounding grounding = new Grounding(semanticExpansionFirst,tag);
//						//kiWiStatistic.setGrounding(grounding);
//						totalGroundgs = totalGroundgs + 1;
//						//groundings.add(grounding);
//					}
//				}
//			}
//		}
//	}
	
	public static boolean verifyWord(String findWord, String text) {
		boolean test = false;
		
		if (findWord.trim().toLowerCase().equals(text.trim().toLowerCase())) {
			test = true;
		//} else if (!findWord.isEmpty()) {
		} else if (!findWord.trim().equals("")) {
			findWord = findWord.replaceFirst(findWord.substring(0, 1), findWord.substring(0, 1).toUpperCase());
			String lower = findWord.replaceFirst(findWord.substring(0, 1), findWord.substring(0, 1).toLowerCase());
			Pattern p = Pattern.compile(findWord + "[A-Z]+|" + findWord + "$|^" + lower + "[A-Z]+");
			test = p.matcher(text).find();
		}
		
		return test;
	}

	/**
	 * @param kiWiResourceFirst
	 * @param kiWiResourceSecond
	 * @param kiWiStatistic
	 */
	public float getContextValue(Resource kiWiResourceFirst, Resource kiWiResourceSecond){
		float contextScore = 0.2f;
		Set<String> context1 = kiWiResourceFirst.getContexts();
		Set<String> context2 = kiWiResourceSecond.getContexts();
		
		StringBuilder contextString1 = new StringBuilder();
		StringBuilder contextString2 = new StringBuilder();
		
		if (context1!=null && context2!=null && !context1.isEmpty()&& !context2.isEmpty() ) {
			for (String cont1 : context1) {
				contextString1.append(cont1);
				contextString1.append(" ");
			}
			for (String cont2 : context2) {
				contextString2.append(cont2);
				contextString2.append(" ");
			}
			AbstractStringMetric cosineSimilarity = new CosineSimilarity();
			contextScore = cosineSimilarity.getSimilarity(contextString1.toString(), contextString2.toString());
		}
		return contextScore;
	}
	

	private boolean hasUserTagAfinity(Resource kiWiResourceFirst, Resource kiWiResourceSecond,Statistic kiWiStatistic){
		boolean hasAfinity = false;
		Set<String> mostFrequentTags = kiWiResourceFirst.getAuthor().getTopTags();
				for (Tag tag : kiWiResourceSecond.getTags()) {
					for (String mostFrequentTag : mostFrequentTags) {
						//System.out.println("Comparing "+Sesame.extractBrackets(mostFrequentTag)+" with "+  tag.getTagName());
						if (Sesame.extractBrackets(mostFrequentTag).equalsIgnoreCase(tag.getTagName())) {
							hasAfinity = true;
							//kiWiStatistic.setComparasionValue(kiWiStatistic.getComparasionValue()*2);
						}
			}
		}
				return hasAfinity;
}	
	
	
	
	/**
	 * @param documentsScore
	 * @return
	 */
	private float normalize(float documentsScore){
		//System.out.println(documentsScore);
		return documentsScore *= 10f;
	}	

	
	/**
	 * @param documentsScore
	 * @return
	 */
	private void normalizeSimilarFactor(Statistic kiWiStatistic){
		
		float leftCosine = (1-kiWiStatistic.getConsineSimilarity());
		float leftSemantic = (1-kiWiStatistic.getSemanticSimilarity().getSemanticSimilarityScore());
		float total = kiWiStatistic.getConsineSimilarity() + kiWiStatistic.getSemanticSimilarity().getSemanticSimilarityScore();
		total = total + leftCosine + leftSemantic;
		
		kiWiStatistic.setConsineSimilarity(kiWiStatistic.getConsineSimilarity()/total);
		kiWiStatistic.getSemanticSimilarity().setSemanticSimilarityScore(kiWiStatistic.getSemanticSimilarity().getSemanticSimilarityScore()/total);
		
		float test = kiWiStatistic.getConsineSimilarity() + kiWiStatistic.getSemanticSimilarity().getSemanticSimilarityScore();
		if (test>1) {
			System.out.println("something is wrong...");
		}
		//System.out.println(test);
	}

	
}
