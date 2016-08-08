package mail;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parser.ExtremmingTag;

import sesame.Sesame;
import util.StringUtils;

public class PerformanceTest {
	
		
	
	static double totalPre = 0;
	static double totalRec = 0;
	static List<PerformanceMetrics> performanceMetrics = new ArrayList<PerformanceMetrics>();
	static PerformanceMetrics performanceMetric = new PerformanceMetrics();		

	public static void main(String[] args) throws InterruptedException, Exception {
		//processMetrics();
		//loadRec();
		//getGroups();
		
		//nationality(Metricer.loadRejected());	
		listExpansions();
		//calculateScore(Metricer.createUserSetTag(Metricer.createStringTags(Metricer.loadRejected())));
		
		
		//processMetrics(List<String> returns,List<String> expectedItems);		
		
	}
	
	
	public static void listExpansions() throws InterruptedException, Exception{
		int total = 0;
		int expanded = 0;
		Set<String> tagsExp = new HashSet<String>();
		
		Set<String> repeat = new HashSet<String>();
		Map<String,String> loadRecsA =  Metricer.loadKey();
		Set<String> notExpanded = new HashSet<String>();		
		ExtremmingTag extremmingTag = new ExtremmingTag();
			for (String keyA : loadRecsA.keySet()) {
				System.out.println( "         USER    "+ keyA);
				List<String> tags = Sesame.selectCleanTagsByUser(Sesame.getRepository().getConnection(), keyA);
				total = total + tags.size();
//				if (keyA.equals("user_2038")) {
//					break;	
//				}
				for (String tag : tags) {
					if (repeat.contains(tag)) {
						continue;
					}
					tagsExp = extremmingTag.getRelatedTags(tag.toLowerCase().trim());
					expanded = expanded + 1;
					if (tagsExp.isEmpty()) {
						notExpanded.add(tag);
					}
					//System.out.println(tag  +" : " + tagsExp);
					repeat.add(tag);
					tagsExp.clear();
					tagsExp = new HashSet<String>();
				}
			}
			System.out.println(total - repeat.size()  +" tags provides " + expanded +" expandions");
			System.out.println(repeat.size()  +" repead size");
			System.out.println(total  +" TOTAL SIZE");
			System.out.println(notExpanded.size() +" tags were not expanded");
			
			
			System.out.println(" tags no expanded " + notExpanded);
			
		}	
	
	
	public static void getGroups() throws InterruptedException, Exception{
		Map<String,String> loadRecsA =  Metricer.loadKey();
		int a = 0;
		List<String> set3s = new ArrayList<String>(loadRecsA.keySet());
		Collections.sort(set3s);
		String lastAuto = new String(set3s.get(0)).substring(0,9);
		Set<String> sets = new HashSet<String>();
		Set<String> setsClean = new HashSet<String>();
			for (String keyA : set3s) {
				System.out.println(keyA +" is interested in "+ Sesame.getMostFrequentTagByUser(Sesame.getRepository().getConnection(), keyA));
			}
			
		}
	
	
	
	
	public static void nationality(Map<String,Set<String>> userTagString)throws InterruptedException, Exception{
			Map<String,String> nat = Inviter.loadPeopleNationality();
			Map<String,Integer> natCont = new HashMap<String, Integer> ();
			for (String keyA : userTagString.keySet()) {
				if (!natCont.keySet().contains(nat.get(keyA))) {
					natCont.put(nat.get(keyA),userTagString.get(keyA).size());
				}else {
					Integer a = natCont.get(nat.get(keyA));
					
					natCont.remove(nat.get(keyA));
					natCont.put(nat.get(keyA), a + userTagString.get(keyA).size());
					
				}
			}
			int total=0;
			for (String x : natCont.keySet()) {
				//System.out.println(" People from "+ x +" accepeted "+ natCont.get(x) );
				System.out.println(x +"	"+ natCont.get(x) );
				total= total + natCont.get(x);
			}
			System.out.println(total);
			
			
//			for (PerformanceMetrics performanceMetrics2 : performanceMetrics) {
//				//System.out.println(performanceMetrics2.getPrecision());
//				totalPre = totalPre + performanceMetrics2.getPrecision();
//				//System.out.println(performanceMetrics2.getRecall());
//				totalRec = totalRec + performanceMetrics2.getRecall();			
//			}
//			System.out.println(totalPre/performanceMetrics.size());
//			System.out.println(totalRec/performanceMetrics.size());
	}		
	
	
	
	public static void calculateScore(Map<String,Set<String>> userTagString)throws InterruptedException, Exception{
			for (String keyA : userTagString.keySet()) {
				processMetrics(new ArrayList<String>(userTagString.get(keyA)), Sesame.selectTagsByUser(Sesame.getRepository().getConnection(),keyA) );				
			}
			for (PerformanceMetrics performanceMetrics2 : performanceMetrics) {
				//System.out.println(performanceMetrics2.getPrecision());
				totalPre = totalPre + performanceMetrics2.getPrecision();
				//System.out.println(performanceMetrics2.getRecall());
				totalRec = totalRec + performanceMetrics2.getRecall();			
			}
			System.out.println(totalPre/performanceMetrics.size());
			System.out.println(totalRec/performanceMetrics.size());
	}	
	

	public static void loadRec() throws InterruptedException, Exception{
		//Map<String,String> loadRecsA =  Metricer.mapRecTags();
		Map<String,String> loadRecsA =  Metricer.mapRecTags();
		int a = 0;
		List<String> set3s = new ArrayList<String>(loadRecsA.keySet());
		Collections.sort(set3s);
		String lastAuto = new String(set3s.get(0)).substring(0,9);
		Set<String> sets = new HashSet<String>();
		Set<String> setsClean = new HashSet<String>();
			for (String keyA : set3s) {
				if (a < 100) {
					if(keyA.startsWith(lastAuto)) {
							lastAuto = keyA.substring(0,9);
							sets.addAll(StringUtils.getToken(loadRecsA.get(keyA), ","));
							continue;
						}else{
							System.out.println("Calculating "+keyA);
//							for (String string : sets) {
//								setsClean.add(string);
//							}
							setsClean = sets;
							processMetrics(new ArrayList<String>(setsClean), Sesame.selectTagsByUser(Sesame.getRepository().getConnection(),lastAuto) );							
//							processMetrics(new ArrayList<String>(setsClean), Sesame.selectCleanTagsByUser(Sesame.getRepository().getConnection(),lastAuto) );
							//processMetrics(new ArrayList<String>(setsClean), new ArrayList<String>(Sesame.getMostFrequentTagByUser(Sesame.getRepository().getConnection(), keyA.substring(0,9)) ));							
							sets = new HashSet<String>();
							sets.addAll(StringUtils.getToken(loadRecsA.get(keyA), ","));						
							lastAuto = keyA.substring(0,9);
							//processMetrics(new ArrayList<String>(sets), Sesame.selectTagsByUser(Sesame.getRepository().getConnection(),lastAuto) );
						   					
					}
				}	
				a++;
			}
			
			
			for (PerformanceMetrics performanceMetrics2 : performanceMetrics) {
				//System.out.println(performanceMetrics2.getPrecision());
				totalPre = totalPre + performanceMetrics2.getPrecision();
				//System.out.println(performanceMetrics2.getRecall());
				totalRec = totalRec + performanceMetrics2.getRecall();			
			}
			
			System.out.println(totalPre/performanceMetrics.size());
			System.out.println(totalRec/performanceMetrics.size());
			
			
			
	}

	
	
	public static final int QUERY_INDEX = 0;
	//public static final int DOMAIN_INDEX = 1;
	public static final int DATA_INDEX = 1;	
	
	/**
	 * @throws Exception
	 * @throws InterruptedException
	 */
	public static void processMetrics() throws Exception, InterruptedException {
	
	}

	
	public static void processMetrics(List<String> returns,List<String> expectedItems) throws Exception, InterruptedException {		

		long iniTest = System.currentTimeMillis();
		long fimTest = 0;
		int i = 0;
		while (i<1) {
			try {
				if(returns.isEmpty()){
					System.err.println("RETORNO VASIO");
					//break;
				}
				
				//long iniIter = System.currentTimeMillis();
				double inter = getItemIntersection(expectedItems, returns);
				//long fimIter = System.currentTimeMillis();
				double precision = 0;
				double recall = 0;
				
				
				if (inter != 0) {
					precision = inter / returns.size();
					if (precision>1d) {
						precision = 1d;
					}
					recall = inter / expectedItems.size();
				}

				 //System.out.println("CODE: " + i + " ********************");
				 performanceMetric = new PerformanceMetrics(getNumberFormated(precision, 2),getNumberFormated(recall, 2));
				 performanceMetrics.add(performanceMetric);

//				 System.out.println("Expected Files: " + expectedItems.size());
//				 System.out.println("Returned Files: " + returns.size());
				 
				 
				 //System.out.println("Intersection: " + inter);
				 System.out.println("Precision: " + getNumberFormated(precision, 2)+"  "+ "Recall: " + getNumberFormated(recall, 2)+"");
				 //System.out.println("Recall: " + getNumberFormated(recall, 4)+"");
				// System.out.println("Response Time: " + (fimIter-iniIter) +" s");
				// acmPrecision += precision;
				// acmRecall += recall;
				// acmIntersection += inter;
				// acmExpected += files.size();
				// acmReturned += results.size();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			i++;
		}
		
		



		fimTest = System.currentTimeMillis();
	}	
	
	
	/**
	 * @param pNumber
	 * @param scale
	 * @return
	 */
	private static double getNumberFormated(double pNumber, int scale) {
		double currency = new BigDecimal(pNumber).setScale(scale,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		return currency;
	}
	
	/**
	 * @param files
	 * @param assets
	 * @return
	 */
	private static double getIntersection(List<String> files, List<String> assets) {
		double result = 0;
		for (String file : files) {
			for (String asset : assets) {
				try {
					String assetWithoutExt = asset.replace(".txt", "");
					assetWithoutExt = asset.replace(".java", "");
					if (assetWithoutExt.equalsIgnoreCase(file)) {
						result++;
					}
				} catch (Exception e) {
					System.out.println("ERROR: " + file + " - " + asset);
				}
			}
		}
		return result;
	}
	
	/**
	 * @param files
	 * @param assets
	 * @return
	 */
	private static double getItemIntersection(List<String> returns, List<String> expectedItems) {
		double result = 0;
		for (String returnedItem : returns) {
			for (String expectedItem : expectedItems) {
				try {
					if (returnedItem.toLowerCase().trim().equals(expectedItem.toLowerCase().trim())) {
						result++;
					}
				} catch (Exception e) {
					System.out.println("ERROR: " + returnedItem + " - " + expectedItem);
				}
			}
		}
		return result;
	}	
	
	
	
	

}



class PerformanceMetrics {
	private Double precision;
	private Double recall;
	
	public PerformanceMetrics() {
		
	}
	
	public PerformanceMetrics(Double precision,Double recall) {
		this.precision = precision;
		this.recall = recall;
	}
	
	public Double getPrecision() {
		return precision;
	}
	public void setPrecision(Double precision) {
		this.precision = precision;
	}
	public Double getRecall() {
		return recall;
	}
	public void setRecall(Double recall) {
		this.recall = recall;
	}
	
}