package aau;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import mockup.Mockup;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity;
import util.StringUtils;

@Stateless
@Name("similarityService")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class MEcoSimilarity implements SimilarityService {
	
	@In
	private EntityManager entityManager;
	
	
	@In(create=true)
	private PersistenceService persistenceService;
	
	int individualUser = 0;
	
	int totalGroundgs = 0;
	
	List<Statistic> kiWiStatisticsRec = new ArrayList<Statistic>();

	public static void main(String[] args) {
		MEcoSimilarity mEcoSimilarity = new MEcoSimilarity();
		Mockup mockup = new Mockup();
		mEcoSimilarity.getSimilarResourcesByTag(new ArrayList<Resource>(mockup.loadTestData()));
	}
	

	
	/**
	 * 
	 */
	public List<Statistic> getSimilarResourcesByTag(List<Resource> resources) {

		int totalRec = resources.size();
		
		System.out.println("Total comparision expected "+((totalRec * (totalRec-1) /2)));

		AbstractStringMetric cosineSimilarity = new CosineSimilarity();

        List<Resource> resourceList = new ArrayList<Resource>(resources);
        
        Statistic statistic = null;
        
        List<Statistic> kiWiStatistics = new ArrayList<Statistic>();
        
        int compare = 0;
        
        int loadMTF = 0;
        
        for (int i = 0; i < resourceList.size(); i++) {
        	
			boolean hasTag = false;
			
			int firstTagAmount = 0;
			
			int secondTagAmount = 0;				
			
			Resource firstResource = resourceList.get(i);

			for (int j = i; j < resourceList.size(); j++) {
				
				Resource secondResource = resourceList.get(j);
				
				// it tests if the comparisions have taken place already

				Statistic statitic =  persistenceService.getStatisticByResources(firstResource,secondResource);
				if (statitic != null) {
					System.out.println("Similarity already computed...");
					continue;
				}

				// it tests if the resources have tags
				if (firstResource.getTags()!=null && secondResource.getTags()!=null){
					hasTag = true;
					firstTagAmount = resourceList.get(i).getTags().size();
					secondTagAmount = resourceList.get(j).getTags().size();
				}
				
				
				// if  it has tags and they are not the same, compute similarity
				if (hasTag && (i!=j)) {	

					compare++;

					statistic = new Statistic(firstResource,secondResource);

					statistic.setConsineSimilarity(cosineSimilarity.getSimilarity(firstResource.getTagString(), secondResource.getTagString()));
					
					statistic.setComparasionValue(statistic.getConsineSimilarity());

					//compute tag frequency, tag weitght, affinity user tag, popularity, representativeness
					computeExternalFactors(statistic);


					if (statistic.getConsineSimilarity()>0f) {
						entityManager.persist(statistic);
					}
				}
			}	
		}

        System.out.println("Total of comparisions "+compare);

        System.out.println("Total  news  "+individualUser);
        
        System.out.println("Total de loadMTF  "+loadMTF);
               
        Collections.sort(kiWiStatistics);
        
        kiWiStatisticsRec = kiWiStatistics;

        System.out.println("Rec to be processed "+kiWiStatisticsRec.size());
        
       //doRecommendation(kiWiStatisticsRec);
        
        System.out.println("Total de comparisions  "+kiWiStatistics.size());
        
        System.out.println("Total de comparisions higher than threshold  "+kiWiStatisticsRec.size());

        
        if (totalGroundgs>0) {
        	System.out.println("Total de groundings "+totalGroundgs);	
		}
        
        
             
        return kiWiStatisticsRec;
	}
	
	
	/**
	 * It calculates the external factors
	 * @param firstResource
	 * @param secondResource
	 * @param statistic
	 */
	public void computeExternalFactors(Statistic statistic){
		
		Resource firstResource = statistic.getFirstResource();
			
		Resource secondResource = statistic.getSecondResource();
		
		// we must call update tag frequency
		float generalTagFrequency = (firstResource.getGeneralTagFrequency() + secondResource.getGeneralTagFrequency());
		
		// we must call update tag wieht
		float generalWeightFrequency = (firstResource.getGeneralTagWeight()+ secondResource.getGeneralTagWeight());
		
		// we shuold compute tag representaiveness and 		
		
		
		float documentsScore = (generalTagFrequency + generalWeightFrequency);

		if (documentsScore>0) {
			if (statistic.getComparasionValue()>0) {
				statistic.setComparasionValue(statistic.getComparasionValue() * documentsScore);	
			}else {
				statistic.setComparasionValue(documentsScore);
			}
		}
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
	        		
	        		//System.out.println("Ranking  "+kiWiStatisticsCol.getComparasionValue()+"   Comparing   "+kiWiStatisticsCol.getFirstResource().getDesc()+"   with tags "+kiWiStatisticsCol.getFirstResource().getTagString()+"  WITH   "+kiWiStatisticsCol.getSecondKiWiResource().getDesc()+"  "+ kiWiStatisticsCol.getSecondKiWiResource().getTagString());
	        		
	        		//System.out.println("Sintax  "+(kiWiStatisticsCol.getComparasionValue() - kiWiStatisticsCol.getSemanticSimilarity().getSemanticSimilarityScore())+"   Semantic   "+kiWiStatisticsCol.getSemanticSimilarity().getSemanticSimilarityScore());
	        		
//	        		System.out.println("Total of  "+kiWiStatisticsCol.getSemanticSimilarity().getTotalAmountMatching()+"   semanitc similarities   with " +
//	        				" Delicious:  "+kiWiStatisticsCol.getSemanticSimilarity().getDeliciousAmount()+"  and " +
//	        				" Ontological "+kiWiStatisticsCol.getSemanticSimilarity().getOntoAmount());
	        		
	       //System.out.println("Ranking  "+kiWiStatisticsCol.getComparasionValue()+"   Comparing   "+kiWiStatisticsCol.getFirstResource().getDesc()+"  with   "+kiWiStatisticsCol.getSecondKiWiResource().getDesc());	
	        		//System.out.println("Ranking  "+kiWiStatisticsCol.getComparasionValue()+"   Comparing   "+kiWiStatisticsCol.getFirstResource().getDesc()+"   with tags "+kiWiStatisticsCol.getFirstResource().getTagString()+"  WITH   "+kiWiStatisticsCol.getSecondKiWiResource().getDesc()+"  "+ kiWiStatisticsCol.getSecondKiWiResource().getTagString());	
//	        		System.out.println("Ranking  "+kiWiStatisticsCol.getComparasionValue()+"   user   "+kiWiStatisticsCol.getFirstResource().getAuthor().getName()+"   Comparing   "+kiWiStatisticsCol.getFirstResource().getLink()+"  with   user2   "+kiWiStatisticsCol.getSecondKiWiResource().getAuthor().getName()+"  link   "+kiWiStatisticsCol.getSecondKiWiResource().getLink());	
//	        		System.out.println("Ranking  "+kiWiStatisticsCol.getComparasionValue()+
//	        				"  user1   "+kiWiStatisticsCol.getFirstResource().getAuthor().getName()+
//	        				"  Comparing   "+kiWiStatisticsCol.getFirstResource().getTagString()+
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
