package evaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.StringUtils;
import aau.Ranking;
import aau.Resource;

public class Performance {
		
	
	static double totalPre = 0;
	static double totalRec = 0;
	static List<PerformanceMetrics> performanceMetrics = new ArrayList<PerformanceMetrics>();
	static PerformanceMetrics performanceMetric = new PerformanceMetrics();		

	public static void main(String[] args) throws InterruptedException, Exception {
		Double d = new Double(4d);
		
		double d3 = 4.5d;
		float f1 = (float)d.floatValue();
		float f2 = (float)d3;
		
		System.out.println(f1);
		System.out.println(f2);

		//processMetrics();
		//loadRec();
		//getGroups();
		
		//nationality(Metricer.loadRejected());	
		
		//calculateScore(Metricer.createUserSetTag(Metricer.createStringTags(Metricer.loadRejected())));
		
		
		//processMetrics(List<String> returns,List<String> expectedItems);		
		
	}
	
	
	
	
	
	
	/**
	 * @param userTagString
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void calculateScore(Map<String,Set<String>> userTagString)throws InterruptedException, Exception{
			for (String keyA : userTagString.keySet()) {
				//processMetrics(new ArrayList<String>(userTagString.get(keyA)), Sesame.selectTagsByUser(Sesame.getRepository().getConnection(),keyA) );				
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


	/**
	 * @param returns
	 * @param expectedItems
	 * @param searchRanking
	 * @throws Exception
	 * @throws InterruptedException
	 */
	public static void processResourceMetrics(List<Resource> returns, List<Resource> expectedItems, Ranking searchRanking) throws Exception, InterruptedException {		
		int i = 0;
		while (i<1) {
			try {
				if(returns.isEmpty()){
					System.err.println("empty result set");
					//break;
				}
				double inter = getResourceIntersection(expectedItems, returns);
				double precision = 0;
				double recall = 0;
				if (inter != 0) {
					precision = inter / returns.size();
					if (precision>1d) {
						precision = 1d;
					}
					recall = inter / expectedItems.size();
				}
				 performanceMetric = new PerformanceMetrics(StringUtils.getNumberFormated(precision, 2),StringUtils.getNumberFormated(recall, 2));
				 performanceMetrics.add(performanceMetric);
				 searchRanking.setPrecision((float)StringUtils.getNumberFormated(precision, 2));
				 searchRanking.setRecall((float)StringUtils.getNumberFormated(recall, 2));
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			i++;
		}
	}		
	
	
	/**
	 * @param returns
	 * @param expectedItems
	 * @throws Exception
	 * @throws InterruptedException
	 */
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
				 performanceMetric = new PerformanceMetrics(StringUtils.getNumberFormated(precision, 2),StringUtils.getNumberFormated(recall, 2));
				 performanceMetrics.add(performanceMetric);

//				 System.out.println("Expected Files: " + expectedItems.size());
//				 System.out.println("Returned Files: " + returns.size());
				 
				 
				 //System.out.println("Intersection: " + inter);
				// System.out.println("Precision: " + getNumberFormated(precision, 2)+"  "+ "Recall: " + getNumberFormated(recall, 2)+"");
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
	
	/**
	 * @param files
	 * @param assets
	 * @return
	 */
	private static double getResourceIntersection(List<Resource> returns, List<Resource> expectedItems) {
		double result = 0;
		for (Resource returnedItem : returns) {
			for (Resource expectedItem : expectedItems) {
				try {
					if (returnedItem.getId()==expectedItem.getId()) {
						result=result+1d;
					}
				} catch (Exception e) {
					System.out.println("ERROR: " + returnedItem.getTitle() + " - " + returnedItem.getTitle());
				}
			}
		}
/*		if (result==0) {
			System.out.println("no similarity then precision 0");
		}*/
		return result;
	}		
}



/**
 * @author Fred
 *
 */
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
