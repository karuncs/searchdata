package mail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import util.StringUtils;

public class Metricer {
	
	public static void main(String[] args) {
		//Metricer.notifyPeople();
		//Metricer.compare2();
		
		Metricer.createUserSetTag(Metricer.createStringTags(loadAccepeted()));
		//Inviter.compare();
		//DeliciousData.reportUsersBookmarking();
		
	}
	
	
	public static Map<String,Set<String>> createUserSetTag(Map<String,String> userTagString){
		Map<String,Set<String>> userSets = new HashMap<String,Set<String>>();
			for (String keyA : userTagString.keySet()) {
					userSets.put(keyA, StringUtils.getToken(userTagString.get(keyA), ","));
			}
			for (String keyA : userTagString.keySet()) {
				System.out.println(keyA+" has tags  "+ userSets.get(keyA));
			}
			return userSets;
	}	
	
	
	
	public static Map<String,String> createStringTags(Map<String,Set<String>> loadStringSet){
		Map<String,Set<String>> loadRecsA =  loadStringSet;
		Map<String,String> returnCol =  new HashMap<String,String>();		
		String auString = (String)loadRecsA.keySet().toArray()[0];
		StringBuilder stringBuilder = new StringBuilder();
			for (String keyA : loadRecsA.keySet()) {
				if (!keyA.equals(auString)) {
					if (stringBuilder.length()>0 && (stringBuilder.lastIndexOf(",")==stringBuilder.length()-1)){
						stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.lastIndexOf(",")));
						System.out.println("*******   "+stringBuilder);
					}
					//System.out.println(stringBuilder.toString());
					returnCol.put(auString, stringBuilder.toString());
					stringBuilder = new StringBuilder();
					auString = keyA.toString();
				}				
				//System.out.println(keyA.toString());
				for (String recA : loadRecsA.get(keyA)) {
					String a = recA.subSequence(recA.lastIndexOf("with TAGS:"),recA.length()).toString();
					//System.out.println(a);
					String b = a.replace("with TAGS:","").trim();
					String c = b.replace(" ",",").trim();
					stringBuilder.append(c);
					stringBuilder.append(",");
					//System.out.println(stringBuilder);
				}
			}
			return returnCol;
	}	
	

	public static void compare2(){
		int i = 0;
		int j = 0;
		Map<String,Set<String>> loadRecsA =  loadRecA();
			for (String keyA : loadRecsA.keySet()) {
				//System.out.println(keyA.toString());
				
				//System.out.println("Set<String> bag_"+j+" = new HashSet<String>();");				
				j++;
				for (String recA : loadRecsA.get(keyA)) {
					
					String a = recA.subSequence(recA.lastIndexOf("with TAGS:"),recA.length()).toString();
					//System.out.println(a);
					String b = a.replace("with TAGS:","").trim();
					String c = b.replace(" ",",").trim();
					//System.out.println(c);
					
//					System.out.println("Set<String> recs_"+i+" = new HashSet<String>();");
					//System.out.println("recs_"+keyA.toString()+"_"+i+".add(\""+c+"\");");
//					//System.out.println("recs_"+i+".add(recs_"+i+");");					
//					System.out.println("bag_"+j+".add(recs_"+i+");");					
//
//					
System.out.println("mapRecTags.put(\""+keyA.toString()+"_"+i+"\""+",\""+c+"\");");
					i++;
				}
				
				}
	}
	
	public static void compare(){
		int found = 0;
		Map<String,Set<String>> loadRecsA =  loadRecA();
		Map<String,Set<String>> loadSemRec =  loadSemRec();
		for (String semkey : loadSemRec.keySet()) {
			for (String keyA : loadRecsA.keySet()) {
				if(keyA.equals(semkey)){
					//System.out.println("comparing user  "+keyA);
					for (String recSem : loadSemRec.get(semkey)) {
						for (String recA : loadRecsA.get(keyA)) {
//							System.out.println(recSem);
//							System.out.println("    "+ recA);
							if (recSem.subSequence(0,20).toString().contains(recA.subSequence(0,20).toString())  && (recSem.length() == recA.length()) ) {
							//if (recSem.subSequence(recSem.indexOf("with TAGS:"),recSem.length()). toString().contains(recA.subSequence(recA.indexOf("with TAGS:"),recA.length()).toString())) {
							//if (recSem.subSequence(recSem.indexOf("with TAGS:")).toString().contains(recA.subSequence(0,4).toString())) {
								System.out.println("Found "+ recSem + " by user "+ keyA);
								System.out.println("Found "+ recA + " by user "+ keyA);
								System.out.println("  ");
								found = found+1;
							}
						}						
					}
				}
				
			}	
		}
		System.out.println("Found "+found);
		int recAmount = loadSemRec.size()*5;
		System.out.println("Rec "+ recAmount);
		System.out.println("Percent found "+ ((found*100)/recAmount));
		
		
	}
	
	

		

	public static void notifyPeople() {
		
		String msgBody = "";
		
		//String sub = "Your Delicious account was created!";
		

//		msgBody = "Now you already can start tagging! " +
//	 		  "Your username is "+pp.toArray()[i]+" and your password is aau.tag.test" +
//			  "\n" +
//			  "Thank you for your participation !" +
//			  "\n" +
//			  "You are welcome to visite our FAQ web site: http://iwis.cs.aau.dk/evalfaq" +
//			  "\n" +
//			  "Please reply me confirming you received this email." +
//			  "\n" +	
//			  "Best Regards," +
//			  "\nFred Durao and Peter Dolog"+
//			  "\nIWIS - Aalborg University";
		
		String sub = "Your recommendations based on your bookmarks";
		
		Map<String,String> loadPeople = loadPeople();
		
		Map<String,String> loadKeys = loadKey();
		
		Map<String,Set<String>> loadRecsA =  loadRecA();
		
		Map<String,Set<String>> loadRecsB =  loadRecB();

		Set<String> pp = loadPeople.keySet();
		
		Set<String> redundant = new HashSet<String>();
		
		Set<String> redundantKey = new HashSet<String>();
		
		int recProcessed = 0;
		int recAProcessed = 0;
		int recBProcessed = 0;
		int notProcessed = 0;
		int userProcessed = 0;
		
		for (int i = 0; i < 44 && i < pp.size(); i++) {
//			if(i >= pp.size()){
//				break;
//			}
			if ((loadRecsA.get(pp.toArray()[i]) == null) || (loadRecsB.get(pp.toArray()[i]) == null)) {
				//System.out.println("Not processing "+pp.toArray()[i]);
				notProcessed = notProcessed +1;
				continue;
			}
			String reci = loadPeople.get(pp.toArray()[i]);
			if (redundantKey!=null && !redundantKey.contains(loadKeys.get(pp.toArray()[i])) &&  redundant!=null && !redundant.contains(reci)) {

				StringBuilder recString = new StringBuilder();
				
				StringBuilder recStringTwo = new StringBuilder();
				
				  msgBody = "your bookmarks (at http://delicious.com/"+pp.toArray()[i]+") " +
				  "\n";
				
				  String recSimbol[] = {"1A","2A","3A","4A","5A"}; 
				
				  for (int j = 0; j < recSimbol.length; j++) {
					  recString.append("Recommendation "+recSimbol[j]+":  "+loadRecsA.get(pp.toArray()[i]).toArray()[j]);
					  recString.append("\n");
					  recString.append("\n");
					  recProcessed = recProcessed+1;
					  recAProcessed = recAProcessed+1;
				  }
				  
				  StringBuilder recString1 = new StringBuilder(msgBody);
				  recString1.append(recString);
				  msgBody = recString1.toString() +	
				  
				  
				  "\n" +
				  "Your SECOND set of recommendations is:" +
				  "\n" +
				  "\n";
				  String recSimbolTwo[] = {"1B","2B","3B","4B","5B"}; 
				  for (int f = 0; f < recSimbolTwo.length; f++) {
					  recStringTwo.append("Recommendation "+recSimbolTwo[f]+":  "+loadRecsB.get(pp.toArray()[i]).toArray()[f]);
					  recStringTwo.append("\n");
					  recStringTwo.append("\n");
					  recProcessed = recProcessed+1;
					  recBProcessed = recBProcessed+1;
					  
				  }
				  StringBuilder recString2 = new StringBuilder(msgBody);
				  recString2.append(recStringTwo);
				  msgBody = recString2.toString() +	
				  
				  
				  "\n" +

				  "\nIWIS - Aalborg University";				
				  userProcessed++;
				//Sender sender = new Sender(reci,sub,msgBody);
				//sender.doSend();
				redundant.add(reci);
				redundantKey.add(loadKeys.get(pp.toArray()[i]));
				System.out.println("Message sent to "+reci);
				System.out.println(msgBody);

			}
		}

		System.out.println("\n");
		System.out.println("Users not processed "+notProcessed);
		System.out.println("Users processed "+userProcessed);
		System.out.println("Rec A expected "+(userProcessed * 5));
		System.out.println("Rec B expected "+(userProcessed * 5));
		System.out.println("Total Rec expected "+(userProcessed * 10));
		
		System.out.println("\n");
		System.out.println("Rec A processed "+recAProcessed);
		System.out.println("Rec B processed "+recBProcessed);
		System.out.println("Total Rec processed "+recProcessed);
		
		
	}
	/**
	 * @return
	 */
	public static Map<String,String> loadKey(){
		Map<String,String> keys = new HashMap<String,String>();
		
		keys.put("user_2000","87DLJELZWMC1");
		keys.put("user_2001","R8AMSXSDWR3N");
		keys.put("user_2002","YZ27UXJZQ7C5");
		keys.put("user_2003","VHD7JASUQ8C1");
		keys.put("user_2004","XZ48SXUZQQCK");
		keys.put("user_2005","C5VMUAJGYM15");
		keys.put("user_2006","U2MMUXUZWM96");
		keys.put("user_2009","72KLJTSCWQC5");
		keys.put("user_2010","A985VHUGQ311");
		keys.put("user_2012","YT6KES7VQ432");
		keys.put("user_2013","WNKQLTUHY496");
		keys.put("user_2015","DKVQSSSDQR3J");
		keys.put("user_2016","T6TMUFUCWL12");
		keys.put("user_2017","W3FCG5EZNMCJ");
		keys.put("user_2018","HJT7L9LDY811");
		keys.put("user_2019","WWV8JXLGQQ12");
		keys.put("user_2020","S4J5FF2ZQ4CP");
		keys.put("user_2021","8WT3LASYQ89K");
		keys.put("user_2022","RATLSWJVQ49N");
		keys.put("user_2023","E8KMJTLCQ79J");
		keys.put("user_2024","ALM3JFJYNR11");
		keys.put("user_2025","VMVMJALHYR3J");
		keys.put("user_2026","F8K3SELYYL9K");
		keys.put("user_2027","P4MLSTLCQ736");
		keys.put("user_2028","9DK4USLUQ8C6");
		keys.put("user_2029","LCV4UWJDY43P");
		keys.put("user_2030","NUMRLFLGN8CJ");
		keys.put("user_2031","VUKLUSLZQ83K");
		keys.put("user_2032","FGT4UFUGYMC2");
		keys.put("user_2033","U164LFLDQ7C5");
		keys.put("user_2034","EKF3LXLHN436");
		keys.put("user_2036","A163JSJHQQCJ");
		keys.put("user_2037","1SHLLEUCYQ9K");
		keys.put("user_2038","LS8QJWUVNL9J");
		keys.put("user_2039","7TH8SFUUQL36");
		keys.put("user_2042","QXHLJXLGQL35");
		keys.put("user_2043","DW63JELCY41K");
		keys.put("user_2044","TSFRLSUCN31J");
		
		return keys;
	}
	
	public static Map<String,String> loadPeople(){
		
		Map<String,String> people = new HashMap<String,String>();
		
//		people.put("user_2000","shick.shi@gmail.com");
//		people.put("user_2001","brunojm@gmail.com");
//		people.put("user_2002","mendes.rodrigo@gmail.com");
//		people.put("user_2003","silviacanedo@gmail.com");
//		people.put("user_2004","julianosoyama@gmail.com");
//		people.put("user_2005","mgomeslage@hotmail.com");
//		people.put("user_2006","lblisboa@gmail.com");
//		people.put("user_2008","alexandremartins@gmail.com");
//		people.put("user_2009","paulaguglielmi@yahoo.com.br");
//		people.put("user_2010","gustavo.wo.costa@gmail.com");
//		people.put("user_2011","sebastian.schaffert@salzburgresearch.at");
//		people.put("user_2012","Jakub.Kotowski@ifi.lmu.de");
//		people.put("user_2013","blumauera@punkt.at");
//		people.put("user_2014","Josef.Holy@Sun.COM");
//		people.put("user_2015","sergio.r.dasilva@gmail.com");
//		people.put("user_2016","cullrich@activemath.org");
//		people.put("user_2017","freddurao@gmail.com");
//		people.put("user_2018","jahn@cs.aau.dk");
//		people.put("user_2019","yjlin@cs.aau.dk");
//		people.put("user_2020","dolog@cs.aau.dk");
//		people.put("user_2021","stephanie.stroka@salzburgresearch.at");
//		people.put("user_2022","andreas.gruber@salzburgresearch.at");
//		people.put("user_2023","Peter.Reiser@Sun.COM");		
//		people.put("user_2024","j.herwig@semantic-web.at");		
//		people.put("user_2025","roberto.uem@gmail.com");
//		people.put("user_2026","wolle@upb.de");	
//		people.put("user_2027","A.M.Bogers@uvt.nl");
//		people.put("user_2028","amm@cs.aau.dk");
//		people.put("user_2029","martin.lindner@gmail.com"); 
//		people.put("user_2030","jjj@cs.aau.dk");
//		people.put("user_2031","sergio.fernandez@fundacionctic.org");
//		people.put("user_2032","koller@punkt.at");
//		people.put("user_2033","patricia@iprj.uerj.br");
people.put("user_2034","judy@it.usyd.edu.au");	
//		people.put("user_2035","dorait@imorph.com");
//		people.put("user_2036","k.wodzicki@iwm-kmrc.de");
//		people.put("user_2037","liseh@cs.aau.dk");	
//		people.put("user_2038","vcgarcia@gmail.com");	
//		people.put("user_2039","thunpin@gmail.com");	
//		people.put("user_2040","tobias@zityzen.dk");
//		people.put("user_2041","simonabrazenova@gmail.com");
//		people.put("user_2042","denisparra@gmail.com");
//		people.put("user_2043","luca.mazzola@lu.unisi.ch");
		people.put("user_2044","c.held@iwm-kmrc.de");
		
		// aau.tag.test
		
		
		
		
		return people;
	}
	
	
	public static Map<String,String> mapRecTags(){
		
		Map<String,String> mapRecTags = new HashMap<String,String>();
		mapRecTags.put("user_2036_0","resources,webdesign,hci,interaction,articles,research,design,jobs,usability");
		mapRecTags.put("user_2036_1","software,community,design,socialsoftware,paper,research,toread,social");
		mapRecTags.put("user_2036_2","socialsoftware,blog,social_software,social,software");
		mapRecTags.put("user_2036_3","productivity,rewrite,architecture,programming,joelonsoftware,design,development,software");
		mapRecTags.put("user_2036_4","professional,university,network,PhD,resources,TENCompetence,research");
		mapRecTags.put("user_2037_5","research,OpenLearnerModel,elearning,reference");
		mapRecTags.put("user_2037_6","engine,searchengine,reference,search,web,everything,doubts,google");
		mapRecTags.put("user_2037_7","online,television,movies,free,tv,streaming,film,community,video");
		mapRecTags.put("user_2037_8","politiken,english,denmark,news");
		mapRecTags.put("user_2037_9","search,research,reference,academic,science,google,literature,citations,scholar");
		mapRecTags.put("user_2038_10","neural-networks,ai,framework,java");
		mapRecTags.put("user_2038_11","homepage,theory,research,work,software");
		mapRecTags.put("user_2038_12","gcc,software,vectorization,compiler,optimization,programming");
		mapRecTags.put("user_2038_13","development,lectures,tutorial,python,science,reference,software,programming");
		mapRecTags.put("user_2038_14","rdf,web3.0,software,semantic");
		mapRecTags.put("user_2031_15","social,google,network,opensocial");
		mapRecTags.put("user_2031_16","socialsoftware,blog,social_software,social,software");
		mapRecTags.put("user_2031_17","fun,polls,tools,blog,social,vote");
		mapRecTags.put("user_2031_18","social,virtual,community");
		mapRecTags.put("user_2031_19","social,collaboration,enterprise,search,tagging");
		mapRecTags.put("user_2032_20","graph,opensource,software,java,semanticweb,visualization,library,programming");
		mapRecTags.put("user_2032_21","software,logiclibrary,service,governance,soa");
		mapRecTags.put("user_2032_22","linux,3d,computer,opensource,games,quake,programming");
		mapRecTags.put("user_2032_23","osx,mysql,mac,php,apple,opensource,software");
		mapRecTags.put("user_2032_24","rdf,web3.0,software,semantic");
		mapRecTags.put("user_2033_25","development,tutorials,games,gamedev,news,software,programming");
		mapRecTags.put("user_2033_26","wiki,programming,books,python,tutorial,reference");
		mapRecTags.put("user_2033_27","tutorial,language,haskell,functional,learning,programming");
		mapRecTags.put("user_2033_28","education,blog,technology");
		mapRecTags.put("user_2033_29","tools,hci,design,interaction,usability,heuristics");
		mapRecTags.put("user_2034_30","interaction,usability,information,hci,interface");
		mapRecTags.put("user_2034_31","research,OpenLearnerModel,elearning,reference");
		mapRecTags.put("user_2034_32","book,programming,ai,java,neural-networks");
		mapRecTags.put("user_2034_33","scheme,howto,design,reference,books,programming");
		mapRecTags.put("user_2034_34","tools,hci,design,interaction,usability,heuristics");
		mapRecTags.put("user_2039_35","scripting,PHP,programming,development,webdev,webdevelopment,web");
		mapRecTags.put("user_2039_36","webdev,mochikit,python,web,turbogears,tutorial,programming");
		mapRecTags.put("user_2039_37","java,asp,c++,tutorial,programming,resources,Windows");
		mapRecTags.put("user_2039_38","ssl,java,Security,tomcat,https");
		mapRecTags.put("user_2039_39","ruby,javascript,scala,java,languages,code,programming");
		mapRecTags.put("user_2019_40","resources,webdesign,hci,interaction,articles,research,design,jobs,usability");
		mapRecTags.put("user_2019_41","search,semanticweb,book,calais,tools,semantic,programming");
		mapRecTags.put("user_2019_42","Standards,internet,web,reference,programming,developerinformation,rfc");
		mapRecTags.put("user_2019_43","reference,research,Libraries,medical,government,health,medicine");
		mapRecTags.put("user_2019_44","rdf,browser,ajax,web,semanticweb,javascript,SemanticWikis,semantic");
		mapRecTags.put("user_2018_45","funny,idols,humor,fun,video,google,music");
		mapRecTags.put("user_2018_46","weinberger,tagging,blog");
		mapRecTags.put("user_2018_47","internet,tv,fernsehen,video,blog,fun,iTV,german");
		mapRecTags.put("user_2018_48","education,blog,technology");
		mapRecTags.put("user_2018_49","fun,polls,tools,blog,social,vote");
		mapRecTags.put("user_2017_50","neural-networks,ai,framework,java");
		mapRecTags.put("user_2017_51","owl,api,semanticweb,library,ontology,development,prolog");
		mapRecTags.put("user_2017_52","java,tools,Testing,web,opensource,software");
		mapRecTags.put("user_2017_53","semweb,discussion,semantic,blog,semanticweb,web");
		mapRecTags.put("user_2017_54","rdf,web3.0,software,semantic");
		mapRecTags.put("user_2012_55","book,programming,ai,java,neural-networks");
		mapRecTags.put("user_2012_56","professional,university,network,PhD,resources,TENCompetence,research");
		mapRecTags.put("user_2012_57","ah2008,personalisation,research,adaptive,conference,hypermedia");
		mapRecTags.put("user_2012_58","language,acm,programming-languages,history,hopl,conference,programming");
		mapRecTags.put("user_2012_59","list,cinema,fun,film,cool,quotes,movie");
		mapRecTags.put("user_2030_60","english,thesaurus,online,tools,Learning,language,reference,Dictionary");
		mapRecTags.put("user_2030_61","usability,ui,HCI,webdesign,design,accessibility,web,Neilsen,reference");
		mapRecTags.put("user_2030_62","Dictionary,grammar,tools,language");
		mapRecTags.put("user_2030_63","scheme,howto,design,reference,books,programming");
		mapRecTags.put("user_2030_64","microsoft,freeware,utilities,tools,free,windows,software");
		mapRecTags.put("user_2010_65","funny,idols,humor,fun,video,google,music");
		mapRecTags.put("user_2010_66","course,distributed,tutorial,google,hadoop,mapreduce,programming");
		mapRecTags.put("user_2010_67","fun,polls,tools,blog,social,vote");
		mapRecTags.put("user_2010_68","folha,noticias,jornal,brasil,news,not�cias,jornais");
		mapRecTags.put("user_2010_69","politics,funny,culture,humor,photoshop,russia,fun,video");
		mapRecTags.put("user_2016_70","semantic_web,blog,semanticweb,socialsemanticweb,education,socialsoftware,semantic");
		mapRecTags.put("user_2016_71","del.icio.us,delicious,preview,blog,tagging,design,technology");
		mapRecTags.put("user_2016_72","weinberger,tagging,blog");
		mapRecTags.put("user_2016_73","cs,computerscience,education,compilers,programming,blog,development");
		mapRecTags.put("user_2016_74","technology,teaching,education,DLACIII,academic,research,games");
		mapRecTags.put("user_2015_75","python,pylons,wsgi,web,framework,programming,tutorial,reference");
		mapRecTags.put("user_2015_76","webdev,mochikit,python,web,turbogears,tutorial,programming");
		mapRecTags.put("user_2015_77","scheme,howto,design,reference,books,programming");
		mapRecTags.put("user_2015_78","osx,mysql,mac,php,apple,opensource,software");
		mapRecTags.put("user_2015_79","development,lectures,tutorial,python,science,reference,software,programming");
		mapRecTags.put("user_2013_80","social,socialnetworking,socialsoftware,blog,software,definitions");
		mapRecTags.put("user_2013_81","socialsoftware,blog,social_software,social,software");
		mapRecTags.put("user_2013_82","homepage,theory,research,work,software");
		mapRecTags.put("user_2013_83","semweb,discussion,semantic,blog,semanticweb,web");
		mapRecTags.put("user_2013_84","rdf,web3.0,software,semantic");
		mapRecTags.put("user_2044_85","blog,discussion,language,games,Learning,kids,programming");
		mapRecTags.put("user_2044_86","professional,university,network,PhD,resources,TENCompetence,research");
		mapRecTags.put("user_2044_87","social,collaboration,enterprise,search,tagging");
		mapRecTags.put("user_2044_88","homepage,theory,research,work,software");
		mapRecTags.put("user_2044_89","opensource,AI,programming,HCI,university,research");
		mapRecTags.put("user_2042_90","research,toread,design,community,socialsoftware,software,paper,social");
		mapRecTags.put("user_2042_91","webdev,mochikit,python,web,turbogears,tutorial,programming");
		mapRecTags.put("user_2042_92","Dictionary,grammar,tools,language");
		mapRecTags.put("user_2042_93","social,virtual,community");
		mapRecTags.put("user_2042_94","rdf,browser,ajax,web,semanticweb,javascript,SemanticWikis,semantic");
		mapRecTags.put("user_2043_95","wiki,information,encyclopedia,research,wikipedia,search,reference");
		mapRecTags.put("user_2043_96","Standards,internet,web,reference,programming,developerinformation,rfc");
		mapRecTags.put("user_2043_97","reference,research,Libraries,medical,government,health,medicine");
		mapRecTags.put("user_2043_98","interaction,usability,design,interface,bibliography,reference,research,HCI");
		mapRecTags.put("user_2043_99","search,research,reference,academic,science,google,literature,citations,scholar");
		mapRecTags.put("user_2029_100","development,software_engineering,programming,SoftwareEngineering,engineering,sei,software");
		mapRecTags.put("user_2029_101","development,tutorials,games,gamedev,news,software,programming");
		mapRecTags.put("user_2029_102","productivity,rewrite,architecture,programming,joelonsoftware,design,development,software");
		mapRecTags.put("user_2029_103","development,linux,mobile,maemo,programming,nokia,software");
		mapRecTags.put("user_2029_104","development,lectures,tutorial,python,science,reference,software,programming");
		mapRecTags.put("user_2028_105","classification,categorization,ontology,delicious,folksonomy,toread,tagging,social");
		mapRecTags.put("user_2028_106","funny,idols,humor,fun,video,google,music");
		mapRecTags.put("user_2028_107","batman,fanmovie,comic,video");
		mapRecTags.put("user_2028_108","college,work,streaming,useful,google,video");
		mapRecTags.put("user_2028_109","Hardware,graphics,company,computer,video,matrox");
		mapRecTags.put("user_2006_110","google,facts,article,work,useful,search");
		mapRecTags.put("user_2006_111","blog,discussion,language,games,Learning,kids,programming");
		mapRecTags.put("user_2006_112","weinberger,tagging,blog");
		mapRecTags.put("user_2006_113","reviews,Magazines,tech,news,microsoft,blog,windows,software");
		mapRecTags.put("user_2006_114","semweb,discussion,semantic,blog,semanticweb,web");
		mapRecTags.put("user_2009_115","wiki,programming,books,python,tutorial,reference");
		mapRecTags.put("user_2009_116","wiki,information,encyclopedia,research,wikipedia,search,reference");
		mapRecTags.put("user_2009_117","encyclopedia,mythology,PersonalPages,history,literature,religion,information,reference");
		mapRecTags.put("user_2009_118","culture,search,images,artists,art,encyclopedia,reference");
		mapRecTags.put("user_2009_119","search,research,reference,academic,science,google,literature,citations,scholar");
		mapRecTags.put("user_2003_120","english,thesaurus,online,tools,Learning,language,reference,Dictionary");
		mapRecTags.put("user_2003_121","words,tools,Dictionary,academic,reference,english,search,language");
		mapRecTags.put("user_2003_122","reviews,Magazines,tech,news,microsoft,blog,windows,software");
		mapRecTags.put("user_2003_123","aalborg,travel,denmark,tourism,information");
		mapRecTags.put("user_2003_124","search,useful,flights,airlines,tickets,cheap,travel");
		mapRecTags.put("user_2021_125","basic,games,development,programming,language,gamedev,blitz");
		mapRecTags.put("user_2021_126","productivity,rewrite,architecture,programming,joelonsoftware,design,development,software");
		mapRecTags.put("user_2021_127","games,kids,programming,opensource,squeak,smalltalk,software,development,language");
		mapRecTags.put("user_2021_128","gamedev,games,ruby,programming,development,library");
		mapRecTags.put("user_2021_129","development,lectures,tutorial,python,science,reference,software,programming");
		mapRecTags.put("user_2002_130","graph,opensource,software,java,semanticweb,visualization,library,programming");
		mapRecTags.put("user_2002_131","software,tools,design,development,java,programming,uml");
		mapRecTags.put("user_2002_132","education,blog,technology");
		mapRecTags.put("user_2002_133","osx,mysql,mac,php,apple,opensource,software");
		mapRecTags.put("user_2002_134","software,convert,web,Praktisk,online_tools,free,pdf,tools");
		mapRecTags.put("user_2020_135","opensource,zimbra,ajax,collaboration,linux,groupware,software");
		mapRecTags.put("user_2020_136","zope,python,web,OpenSource,cms,programming,software");
		mapRecTags.put("user_2020_137","java,tools,Testing,web,opensource,software");
		mapRecTags.put("user_2020_138","productivity,rewrite,architecture,programming,joelonsoftware,design,development,software");
		mapRecTags.put("user_2020_139","rdf,web3.0,software,semantic");
		mapRecTags.put("user_2005_140","research,toread,design,community,socialsoftware,software,paper,social");
		mapRecTags.put("user_2005_141","social,google,network,opensocial");
		mapRecTags.put("user_2005_142","social,virtual,community");
		mapRecTags.put("user_2005_143","homepage,theory,research,work,software");
		mapRecTags.put("user_2005_144","rdf,web3.0,software,semantic");
		mapRecTags.put("user_2023_145","book,programming,ai,java,neural-networks");
		mapRecTags.put("user_2023_146","java,tools,Testing,web,opensource,software");
		mapRecTags.put("user_2023_147","social,virtual,community");
		mapRecTags.put("user_2023_148","scheme,howto,design,reference,books,programming");
		mapRecTags.put("user_2023_149","semweb,discussion,semantic,blog,semanticweb,web");
		mapRecTags.put("user_2004_150","english,thesaurus,online,tools,Learning,language,reference,Dictionary");
		mapRecTags.put("user_2004_151","encyclopedia,mythology,PersonalPages,history,literature,religion,information,reference");
		mapRecTags.put("user_2004_152","scheme,howto,design,reference,books,programming");
		mapRecTags.put("user_2004_153","culture,search,images,artists,art,encyclopedia,reference");
		mapRecTags.put("user_2004_154","hotel,munich,trip,travel");
		mapRecTags.put("user_2022_155","editors,article,forms,eclipse");
		mapRecTags.put("user_2022_156","owl,api,semanticweb,library,ontology,development,prolog");
		mapRecTags.put("user_2022_157","folksonomy,books,tags,ontology");
		mapRecTags.put("user_2022_158","ontology,coeedito,tutorial,design");
		mapRecTags.put("user_2022_159","ontology,protege,knowledge,semanticweb,tools");
		mapRecTags.put("user_2025_160","blog,discussion,language,games,Learning,kids,programming");
		mapRecTags.put("user_2025_161","semantic_web,blog,semanticweb,socialsemanticweb,education,socialsoftware,semantic");
		mapRecTags.put("user_2025_162","photography,tutorial,graphics,photoshop,howto,design,reference");
		mapRecTags.put("user_2025_163","zope,python,web,OpenSource,cms,programming,software");
		mapRecTags.put("user_2025_164","rdf,browser,ajax,web,semanticweb,javascript,SemanticWikis,semantic");
		mapRecTags.put("user_2024_165","YouTube,marketing,video,social,internet");
		mapRecTags.put("user_2024_166","community,tools,search,social");
		mapRecTags.put("user_2024_167","weinberger,tagging,blog");
		mapRecTags.put("user_2024_168","flickr,social,photo");
		mapRecTags.put("user_2024_169","semweb,discussion,semantic,blog,semanticweb,web");
		mapRecTags.put("user_2001_170","linux,3d,computer,opensource,games,quake,programming");
		mapRecTags.put("user_2001_171","book,programming,ai,java,neural-networks");
		mapRecTags.put("user_2001_172","opensource,zimbra,ajax,collaboration,linux,groupware,software");
		mapRecTags.put("user_2001_173","java,tools,Testing,web,opensource,software");
		mapRecTags.put("user_2001_174","monitoring,service,network,linux,software,opensource");
		mapRecTags.put("user_2027_175","weinberger,tagging,blog");
		mapRecTags.put("user_2027_176","scheme,howto,design,reference,books,programming");
		mapRecTags.put("user_2027_177","osx,mysql,mac,php,apple,opensource,software");
		mapRecTags.put("user_2027_178","development,lectures,tutorial,python,science,reference,software,programming");
		mapRecTags.put("user_2027_179","graphics,tex,postscript,latex,pstricks,tutorial,software,reference");
		mapRecTags.put("user_2000_180","process,bpm,course,management,workflow,tutorial,business");
		mapRecTags.put("user_2000_181","software,erp,freeware,java,crm,opensource,management");
		mapRecTags.put("user_2000_182","softwareengineering,engineering,eclipse,unifiedprocess,xp,scrum,process,framework,tools,software,workflow");
		mapRecTags.put("user_2000_183","economia,investimentos,de,valores,bolsa,finan�as,bovespa");
		mapRecTags.put("user_2000_184","ontology,protege,knowledge,semanticweb,tools");
		mapRecTags.put("user_2026_185","learning,Kids,python,education,language,coding,programming");
		mapRecTags.put("user_2026_186","elearning,learning,problembasedlearning,PBL,reference,education");
		mapRecTags.put("user_2026_187","education,object,metadata,learning");
		mapRecTags.put("user_2026_188","education,blog,technology");
		mapRecTags.put("user_2026_189","ontology,coeedito,tutorial,design");
	
	return mapRecTags;
}
	
	
	public static Map<String,Set<String>> loadRecB(){
		
		Map<String,Set<String>> mapRecB = new HashMap<String,Set<String>>();

		Set<String> recs_2000 = new HashSet<String>();
		recs_2000.add("Information and knowledge management dimensions and approaches  authored by   user_2020  at   http://informationr.net/ir/10-4/paper235.html  with TAGS:   management information article knowledge km");
		recs_2000.add("The nonsense of 'knowledge management'  authored by   user_2020  at   http://informationr.net/ir/8-1/paper144.html  with TAGS:   business knowledgemanagement SWKM2008 knowledge_management km article management knowledge");
		recs_2000.add("download prot�g� software  authored by   user_2025  at   http://protege.stanford.edu/download/download.html  with TAGS:   knowledge tools semanticweb protege ontology");
		recs_2000.add("A Course On Workflow Management  authored by   user_2020  at   http://is.tm.tue.nl/staff/wvdaalst/workflowcourse/  with TAGS:   tutorial process bpm management workflow course business");
		recs_2000.add("TRIPLE-I '08  authored by   user_2013  at   http://www.triple-i.info/  with TAGS:   semanticweb conference graz 2007 knowledge_management semantic_web enterprise2.0 media congress knowledge");
		mapRecB.put("user_2000", recs_2000);
		
		Set<String> recs_2001 = new HashSet<String>();
		recs_2001.add("Planeta Delphi  authored by   user_2025  at   http://www.delphi.eti.br/  with TAGS:   programacao programa��o programming development delphi forum software tutorial ");
		recs_2001.add("Django | The Web framework for perfectionists with deadlines  authored by   user_2023  at   http://www.djangoproject.com/  with TAGS:   web webdev framework webdevelopment python development programming django ");
		recs_2001.add("UML Modeling Tool - JUDE  authored by   user_2015  at   http://jude.change-vision.com/jude-web/index.html  with TAGS:   java tools design software programming uml development ");
		recs_2001.add("GameDev.net - all your game development needs  authored by   user_2021  at   http://www.gamedev.net/  with TAGS:   software games news tutorials development gamedev programming ");
		recs_2001.add("The future of PHP  authored by   user_2043  at   http://www.ibm.com/developerworks/opensource/library/os-php-future/?ca=dgr-lnxw01PHP-Future  with TAGS:   webdev PHP programming webdevelopment scripting web development ");
		mapRecB.put("user_2001", recs_2001);
		
		Set<String> recs_2002 = new HashSet<String>();
		recs_2002.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education technology blog ");
		recs_2002.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   social community tools search ");
		recs_2002.add("digg labs  authored by   user_2015  at   http://labs.digg.com/  with TAGS:   design visualization news labs digg socialsoftware flash folksonomy ");
		recs_2002.add("Excel .NetMap - Social Network Add-in for Excel 2007 - Windows Live SkyDrive  authored by   user_2020  at   http://cid-ae935b3cde8015dd.skydrive.live.com/browse.aspx/Excel%20.NetMap%20-%20Social%20Network%20Add-in%20for%20Excel%202007  with TAGS:   Excell DLACIII social networks Microsoft ");
		recs_2002.add("GUUUI - The Interaction Designer's Coffee Break  authored by   user_2025  at   http://www.guuui.com/  with TAGS:   internet research ui gui blog usability article toread interaction user design webdesign news ");		
		mapRecB.put("user_2002", recs_2002);

		Set<String> recs_2003 = new HashSet<String>();
		recs_2003.add("Welcome to Facebook! | Facebook  authored by   user_2005  at   http://facebook.com/  with TAGS:   socialnetworking facebook community social networking friends ");
		recs_2003.add("News - Home - kicker online  authored by   user_2018  at   http://kicker.de/  with TAGS:   football german sport news ");
		recs_2003.add("tv2.dk - nyheder, vejret, sport, spil, gossip og danmarks bedste tv-guide  authored by   user_2037  at   http://www.tv2.dk/  with TAGS:   denmark weather nyheder underholdning tv media news ");
		recs_2003.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   social community tools search ");
		recs_2003.add("Lada Adamic School of Information  authored by   user_2020  at   http://www-personal.umich.edu/~ladamic/  with TAGS:   networks research KIWI academic sna people social ");
		mapRecB.put("user_2003", recs_2003);		

		Set<String> recs_2004 = new HashSet<String>();
		recs_2004.add("verb2verbe - Learn the French language - French verb conjugation. Tests to learn French verbs.  authored by   user_2033  at   http://www.verb2verbe.com/  with TAGS:   tools french dictionary grammar reference english language ");
		recs_2004.add("News - Home - kicker online  authored by   user_2018  at   http://kicker.de/  with TAGS:   football german sport news ");
		recs_2004.add("Rejse Planen  authored by   user_2009  at   http://www.rejseplanen.dk/  with TAGS:   rejseplan copenhagen denmark travel transport bus information rejser ");
		recs_2004.add("AaB  authored by   user_2018  at   http://www.aabsport.dk/  with TAGS:   sasligaen denmark sport aalborg football ");
		recs_2004.add("International standards for HCI and usability - GUILHERMO.com - Arquitetura de Informa��o  authored by   user_2025  at   http://www.guilhermo.com/ai_biblioteca/referencia.asp?referencia=127  with TAGS:   hci information interaction interface usability ");
		mapRecB.put("user_2004", recs_2004);

		Set<String> recs_2005 = new HashSet<String>();
		recs_2005.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   social community tools search ");
		recs_2005.add("Google Operating System (Unofficial Google Blog)  authored by   user_2016  at   http://googlesystem.blogspot.com/  with TAGS:   blog technology google ");
		recs_2005.add("LiveJournal.com - Start a Free Blog / Journal Today  authored by   user_2042  at   http://www.livejournal.com/  with TAGS:   citeulike livejournal community social journal blogs share ");
		recs_2005.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   community social virtual ");
		recs_2005.add("Excel .NetMap - Social Network Add-in for Excel 2007 - Windows Live SkyDrive  authored by   user_2020  at   http://cid-ae935b3cde8015dd.skydrive.live.com/browse.aspx/Excel%20.NetMap%20-%20Social%20Network%20Add-in%20for%20Excel%202007  with TAGS:   Excell DLACIII social networks Microsoft ");
		mapRecB.put("user_2005", recs_2005);
		
		Set<String> recs_2006 = new HashSet<String>();
		recs_2006.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education technology blog ");
		recs_2006.add("Digg - All News, Videos, & Images  authored by   user_2002  at   http://digg.com/  with TAGS:   social online technology news digg tech blog ");
		recs_2006.add("tv2.dk - nyheder, vejret, sport, spil, gossip og danmarks bedste tv-guide  authored by   user_2037  at   http://www.tv2.dk/  with TAGS:   denmark weather nyheder underholdning tv media news ");
		recs_2006.add("83 Beautiful Wordpress Themes You (Probably) Haven�t Seen | Design Showcase | Smashing Magazine  authored by   user_2001  at   http://www.smashingmagazine.com/2007/02/09/83-beautiful-wordpress-themes-you-probably-havent-seen/  with TAGS:   design themes css blog wordpress templates webdesign ");
		recs_2006.add("The nonsense of 'knowledge management'  authored by   user_2020  at   http://informationr.net/ir/8-1/paper144.html  with TAGS:   business knowledgemanagement SWKM2008 knowledge_management km article management knowledge ");
		mapRecB.put("user_2006", recs_2006);
				
		Set<String> recs_2009 = new HashSet<String>();
		recs_2009.add("Exalead: Choose a new search engine.  authored by   user_2013  at   http://www.exalead.com/  with TAGS:   internet search engine moderatedsearch web searchengine reference wikipedia ");
		recs_2009.add("News - Home - kicker online  authored by   user_2018  at   http://kicker.de/  with TAGS:   football german sport news ");
		recs_2009.add("Wikipedia, the free encyclopedia  authored by   user_2021  at   http://en.wikipedia.org/wiki/Main_Page  with TAGS:   encyclopedia wiki information research reference search wikipedia ");
		recs_2009.add("Google Operating System (Unofficial Google Blog)  authored by   user_2016  at   http://googlesystem.blogspot.com/  with TAGS:   blog technology google ");
		recs_2009.add("International standards for HCI and usability - GUILHERMO.com - Arquitetura de Informa��o  authored by   user_2025  at   http://www.guilhermo.com/ai_biblioteca/referencia.asp?referencia=127  with TAGS:   hci information interaction interface usability ");
		mapRecB.put("user_2009", recs_2009);
				
		Set<String> recs_2010 = new HashSet<String>();		
		recs_2010.add("Google Operating System (Unofficial Google Blog)  authored by   user_2016  at   http://googlesystem.blogspot.com/  with TAGS:   blog technology google ");
		recs_2010.add("Folha Online - Primeiro jornal em tempo real em l�ngua portuguesa  authored by   user_2004  at   http://www.folha.uol.com.br/  with TAGS:   not�cias noticias folha jornal brasil jornais news ");
		recs_2010.add("The Innovation Game | Tux Deluxe  authored by   user_2001  at   http://www.tuxdeluxe.org/node/267  with TAGS:   innovation business ideas patents software free ");
		recs_2010.add("Alltop, all the top stories  authored by   user_2024  at   http://alltop.com/  with TAGS:   aggregator portal news rss newsportal blogs ");
		recs_2010.add("European Association of Technology Enhanced Learning  authored by   user_2043  at   http://www.prolearn-academy.org/  with TAGS:   portal e-learning TEL PROLEARN research association elearning community ");
		mapRecB.put("user_2010", recs_2010);

		Set<String> recs_2012 = new HashSet<String>();
		recs_2012.add("The 10th International Software Product Line Conference (SPLC 2006)  authored by   user_2020  at   http://www.sei.cmu.edu/splc2006/tech_program.html  with TAGS:   conference lines product software ");
		recs_2012.add("The Innovation Game | Tux Deluxe  authored by   user_2001  at   http://www.tuxdeluxe.org/node/267  with TAGS:   innovation business ideas patents software free ");
		recs_2012.add("Workshop on the Issues of Designing and Evaluating Ambient Information Systems Conference on Pervasive Computing  authored by   user_2020  at   http://informatics.indiana.edu/subtletech/  with TAGS:   conference ambient visualization workshop research information design ");
		recs_2012.add("TreeHugger Tip: How to Bike to Work (without all the sweat!) : TreeHugger  authored by   user_2001  at   http://www.treehugger.com/files/2008/07/treehugger_tips_bike_to_work.php  with TAGS:   howto transportation ideas bikes video bicycle cool ");
		recs_2012.add("Confreaks: Expert Recording and Networking Services  authored by   user_2001  at   http://www.confreaks.com/  with TAGS:   rails screencast podcast programming ruby conference video ");
		mapRecB.put("user_2012", recs_2012);

		Set<String> recs_2013 = new HashSet<String>();
		recs_2013.add("ser_2013   OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education technology blog ");
		recs_2013.add("Google Operating System (Unofficial Google Blog)  authored by   user_2016  at   http://googlesystem.blogspot.com/  with TAGS:   blog technology google ");
		recs_2013.add("The Semantic Web: An Introduction  authored by   user_2017  at   http://infomesh.net/2001/swintro/  with TAGS:   semantic semanticweb web rdf ontology xml introduction semantic-web semantic_web ");
		recs_2013.add("Semantic Book Suggestions: knowledgelounge.org  authored by   user_2023  at   http://kapustar.punkt.at/labs/knowledgelounge.org/  with TAGS:   search semanticweb book calais tools semantic programming ");
		recs_2013.add("Health Perspectives  authored by   user_2024  at   http://nelh.blogspot.com/  with TAGS:   semanticweb healthinformatics nhs blog health ");
		mapRecB.put("user_2013", recs_2013);

		Set<String> recs_2015 = new HashSet<String>();
		recs_2015.add("ARTICLE:  When tags work and when they don't: Amazon and LibraryThing by  authored by   user_2027  at   http://www.librarything.com/thingology/2007/02/when-tags-works-and-when-they-dont.php  with TAGS:   work tags folksonomy statistics social_tagging librarything useful article amazon blog ");
		recs_2015.add("prefuse | interactive information visualization toolkit  authored by   user_2023  at   http://prefuse.org/  with TAGS:   java opensource visualization software library graph semanticweb programming ");
		recs_2015.add("HCIRN: Human-Computer Interaction Resource Network  authored by   user_2033  at   http://www.hcirn.com/index.php  with TAGS:   resources webdesign hci interaction articles research design jobs usability ");
		recs_2015.add("Open Source Code Search Engine - Koders  authored by   user_2002  at   http://www.koders.com/  with TAGS:   programming source opensource code development reference engine search ");
		recs_2015.add("Shapes in the Fire  authored by   user_2016  at   http://ipwebdev.com/hermit/  with TAGS:   future books steiner news spirit ");
		mapRecB.put("user_2015", recs_2015);
		
		Set<String> recs_2016 = new HashSet<String>();
		recs_2016.add("Digg - All News, Videos, & Images  authored by   user_2002  at   http://digg.com/  with TAGS:   social online technology news digg tech blog ");
		recs_2016.add("IsLifeCorp :: Home  authored by   user_2006  at   http://www.islifecorp.com.br/  with TAGS:   download series tv blog ");
		recs_2016.add("Web Radio delle SE della citt� di Lugano  authored by   user_2043  at   http://webradioselugano.blogspot.com/  with TAGS:   PodCast USI ICT research education ");
		recs_2016.add("Google documentaire voor ICTvS&W college  authored by   user_2027  at   http://videotheek.surfnet.nl/play_proxy/mmc/24213/googleachterdeschermen.asf  with TAGS:   work streaming college useful google video ");
		recs_2016.add("83 Beautiful Wordpress Themes You (Probably) Haven�t Seen | Design Showcase | Smashing Magazine  authored by   user_2001  at   http://www.smashingmagazine.com/2007/02/09/83-beautiful-wordpress-themes-you-probably-havent-seen/  with TAGS:   design themes css blog wordpress templates webdesign ");
		mapRecB.put("user_2016", recs_2016);
		
		Set<String> recs_2017 = new HashSet<String>();
		recs_2017.add("Struts 2 - Welcome  authored by   user_2039  at   http://struts.apache.org/2.x/  with TAGS:   webdev j2EE framework java web ");
		recs_2017.add("Thea - [SWI] Prolog OWL library  authored by   user_2015  at   http://www.semanticweb.gr/TheaOWLLib/  with TAGS:   semanticweb owl development ontology api prolog library ");
		recs_2017.add("Prefuse | interactive information visualization toolkit  authored by   user_2023  at   http://prefuse.org/  with TAGS:   java opensource visualization software library graph semanticweb programming ");
		recs_2017.add("Neologism - Easy Vocabulary Publishing  authored by   user_2031  at   http://neologism.deri.ie/  with TAGS:   drupal php ontology owl vocabulary rdf ");
		recs_2017.add("mozilla.org  authored by   user_2023  at   http://www.mozilla.org/  with TAGS:   firefox browser web mozilla opensource software ");
		mapRecB.put("user_2017", recs_2017);
		
		Set<String> recs_2018 = new HashSet<String>();
		recs_2018.add("Welcome to Facebook! | Facebook  authored by   user_2005  at   http://facebook.com/  with TAGS:   socialnetworking facebook community social networking friends ");
		recs_2018.add("tv2.dk - nyheder, vejret, sport, spil, gossip og danmarks bedste tv-guide  authored by   user_2037  at   http://www.tv2.dk/  with TAGS:   denmark weather nyheder underholdning tv media news ");		
		recs_2018.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   community social virtual ");
		recs_2018.add("Consumating  authored by   user_2027  at   http://www.consumating.com/  with TAGS:   tags dating social_tagging folksonomy community social ");
		recs_2018.add("orkut  authored by   user_2005  at   http://www.orkut.com/  with TAGS:   community social google orkut networking ");		
		mapRecB.put("user_2018", recs_2018);		

		Set<String> recs_2019 = new HashSet<String>();
		recs_2019.add("Momondo  authored by   user_2027  at   http://momondo.com/  with TAGS:   search useful flights airlines tickets cheap travel ");		
		recs_2019.add("Munich.com - Munich Travel, Munich Flights, Munich Hotels  authored by   user_2017  at   http://www.munich.com/  with TAGS:   hotel munich trip travel ");
		recs_2019.add("download prot�g� software  authored by   user_2025  at   http://protege.stanford.edu/download/download.html  with TAGS:   knowledge tools semanticweb protege ontology");
		recs_2019.add("The Semantic Web: An Introduction  authored by   user_2017  at   http://infomesh.net/2001/swintro/  with TAGS:   semantic semanticweb web rdf ontology xml introduction semantic-web semantic_web ");
		recs_2019.add("Digg - All News, Videos, & Images  authored by   user_2002  at   http://digg.com/  with TAGS:   social online technology news digg tech blog ");
		mapRecB.put("user_2019", recs_2019);

		Set<String> recs_2020 = new HashSet<String>();
		recs_2020.add("Social Software Building Blocks / nForm / Customer Insight, Strategy, Design and Development  authored by   user_2025  at   http://nform.ca/publications/social-software-building-block  with TAGS:   software community design socialsoftware paper research toread social ");
		recs_2020.add("Web Radio delle SE della citt� di Lugano  authored by   user_2043  at   http://webradioselugano.blogspot.com/  with TAGS:   PodCast USI ICT research education ");
		recs_2020.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education technology blog ");
		recs_2020.add("C.R.U.I.S.E - Component Reuse in Software Engineering  authored by   user_2038  at   http://cruise.rise.com.br/  with TAGS:   spl cesar reuse cruise softwareengineering rise science research software ");
		recs_2020.add("Plone CMS: Open Source Content Management  authored by   user_2025  at   http://plone.org/  with TAGS:   cms zope plone python community software opensource ");
		mapRecB.put("user_2020", recs_2020);		

		Set<String> recs_2021 = new HashSet<String>();
		recs_2021.add("Main Page - Scholarpedia  authored by   user_2015  at   http://www.scholarpedia.org/  with TAGS:   research education reference scholarpedia wikipedia encyclopedia wiki ");
		recs_2021.add("gosu - Google Code  authored by   user_2001  at   http://code.google.com/p/gosu/  with TAGS:   gamedev development ruby games programming library ");
		recs_2021.add("rSqueak: Squeak  authored by   user_2033  at   http://www.squeak.org/  with TAGS:   opensource smalltalk development kids games squeak software programming language ");
		recs_2021.add("rMain Page - WikiViz  authored by   user_2043  at   http://www.wikiviz.org/wiki/Main_Page  with TAGS:   reference graphics wiki infovis resources design visualization data information ");
		recs_2021.add("UML Modeling Tool - JUDE  authored by   user_2015  at   http://jude.change-vision.com/jude-web/index.html  with TAGS:   java tools design software programming uml development ");
		mapRecB.put("user_2021", recs_2021);
		
		Set<String> recs_2022 = new HashSet<String>();
		recs_2022.add("LibraryThing | Catalog your books online  authored by   user_2015  at   chrome://ietab/content/reloaded.html?url=http://www.librarything.com/thingology/2007/02/when-tags-works-and-when-they-dont.php  with TAGS:   ontology books tags folksonomy ");
		recs_2022.add("Tutorial sobre COE editor da CMAPTools  authored by   user_2025  at   http://www2.dcc.ufmg.br/laboratorios/lsi/EAD/MapasConc/TutorialCMapTools.html  with TAGS:   ontology tutorial coeedito design ");
		recs_2022.add("download prot�g� software  authored by   user_2025  at   http://protege.stanford.edu/download/download.html  with TAGS:   knowledge tools semanticweb protege ontology ");
		recs_2022.add("Neologism - Easy Vocabulary Publishing  authored by   user_2031  at   http://neologism.deri.ie/  with TAGS:   drupal php ontology owl vocabulary rdf ");
		recs_2022.add("Thea - [SWI] Prolog OWL library  authored by   user_2015  at   http://www.semanticweb.gr/TheaOWLLib/  with TAGS:   semanticweb owl development ontology api prolog library ");
		mapRecB.put("user_2022", recs_2022);
		
		Set<String> recs_2023 = new HashSet<String>();
		recs_2023.add("Flickr  authored by   user_2031  at   http://flickr.com/  with TAGS:   flickr photo social ");
		recs_2023.add("Google Operating System (Unofficial Google Blog)  authored by   user_2016  at   http://googlesystem.blogspot.com/  with TAGS:   blog technology google ");
		recs_2023.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education technology blog ");
		recs_2023.add("Excel .NetMap - Social Network Add-in for Excel 2007 - Windows Live SkyDrive  authored by   user_2020  at   http://cid-ae935b3cde8015dd.skydrive.live.com/browse.aspx/Excel%20.NetMap%20-%20Social%20Network%20Add-in%20for%20Excel%202007  with TAGS:   Excell DLACIII social networks Microsoft ");
		recs_2023.add("Hypertext 2009  authored by   user_2044  at   http://www.ht2009.org/  with TAGS:   tagging torino hypertext 2009 conference ");
		mapRecB.put("user_2023", recs_2023);
		
		Set<String> recs_2024 = new HashSet<String>();
		recs_2024.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   social community tools search ");
		recs_2024.add("News - Home - kicker online  authored by   user_2018  at   http://kicker.de/  with TAGS:   football german sport news ");
		recs_2024.add("Guardian Unlimited  authored by   user_2018  at   http://www.guardian.co.uk/  with TAGS:   uk media blog news ");
		recs_2024.add("Politiken.dk - det levende net  authored by   user_2003  at   http://politiken.dk/newsinenglish  with TAGS:   english denmark politiken news ");
		recs_2024.add("OLDaily ~ by Stephen Downes  authored by   user_2016  at   http://www.downes.ca/news/OLDaily.htm  with TAGS:   report constructivism elearning education ");
		mapRecB.put("user_2024", recs_2024);

		Set<String> recs_2025 = new HashSet<String>();
		recs_2025.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   social community tools search ");
		recs_2025.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   collaboration search tagging enterprise social ");
		recs_2025.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education technology blog ");
		recs_2025.add("4suite.org  authored by   user_2001  at   http://4suite.org/index.xhtml  with TAGS:   software RDF python xml programming library tools ");
		recs_2025.add("prefuse | interactive information visualization toolkit  authored by   user_2023  at   http://prefuse.org/  with TAGS:   java opensource visualization software library graph semanticweb programming ");
		mapRecB.put("user_2025", recs_2025);

		Set<String> recs_2026 = new HashSet<String>();
		recs_2026.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education technology blog ");
		recs_2026.add("ARIADNE Educational Metadata Recommendation  authored by   user_2034  at   http://www.ariadne-eu.org/3_MD/ariadne_metadata_v31.htm  with TAGS:   object learning metadata education ");
		recs_2026.add("Tutorial sobre COE editor da CMAPTools  authored by   user_2025  at   http://www2.dcc.ufmg.br/laboratorios/lsi/EAD/MapasConc/TutorialCMapTools.html  with TAGS:   ontology tutorial coeedito design ");
		recs_2026.add("International standards for HCI and usability - GUILHERMO.com - Arquitetura de Informa��o  authored by   user_2025  at   http://www.guilhermo.com/ai_biblioteca/referencia.asp?referencia=127  with TAGS:   hci information interaction interface usability ");
		recs_2026.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   social community tools search ");
		mapRecB.put("user_2026", recs_2026);
		
		Set<String> recs_2027 = new HashSet<String>();
		recs_2027.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   social community tools search ");
		recs_2027.add("LibraryThing | Catalog your books online  authored by   user_2015  at   chrome://ietab/content/reloaded.html?url=http://www.librarything.com/thingology/2007/02/when-tags-works-and-when-they-dont.php  with TAGS:   ontology books tags folksonomy ");
		recs_2027.add("Welcome to Facebook! | Facebook  authored by   user_2005  at   http://facebook.com/  with TAGS:   socialnetworking facebook community social networking friends ");
		recs_2027.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   community social virtual ");
		recs_2027.add("LiveJournal.com - Start a Free Blog / Journal Today  authored by   user_2042  at   http://www.livejournal.com/  with TAGS:   citeulike livejournal community social journal blogs share ");
		mapRecB.put("user_2027", recs_2027);
		
		Set<String> recs_2028 = new HashSet<String>();		
		recs_2028.add("Matrox  authored by   user_2015  at   http://www.matrox.com/  with TAGS:   video Hardware computer company matrox graphics ");
		recs_2028.add("Google documentaire voor ICTvS&W college  authored by   user_2027  at   http://videotheek.surfnet.nl/play_proxy/mmc/24213/googleachterdeschermen.asf  with TAGS:   work streaming college useful google video ");
		recs_2028.add("Shirky: Ontology is Overrated -- Categories, Links, and Tags  authored by   user_2025  at   http://shirky.com/writings/ontology_overrated.html  with TAGS:   toread social folksonomy ontology categorization delicious classification tagging ");
		recs_2028.add("YouTube - batman dead end  authored by   user_2018  at   http://de.youtube.com/watch?v=Hjp0I_okX0w  with TAGS:   video comic batman fanmovie ");
		recs_2028.add("VideoCure.com  authored by   user_2027  at   http://www.videocure.com/  with TAGS:   HTML music video streaming myspace code entertainment ");
		mapRecB.put("user_2028", recs_2028);		

		Set<String> recs_2029 = new HashSet<String>();
		recs_2029.add("The 10th International Software Product Line Conference (SPLC 2006)  authored by   user_2020  at   http://www.sei.cmu.edu/splc2006/tech_program.html  with TAGS:   conference lines product software ");
		recs_2029.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   work theory research homepage software ");
		recs_2029.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   social community tools search ");
		recs_2029.add("Personal and Group Learning Using Web 2.0 Tools : eLearning Technology  authored by   user_2033  at   http://elearningtech.blogspot.com/2006/09/personal-and-group-learning-using-web.html  with TAGS:   howto blog ple wiki e-learning collaboration elearning ");
		recs_2029.add("News - Home - kicker online  authored by   user_2018  at   http://kicker.de/  with TAGS:   football german sport news ");
		mapRecB.put("user_2029", recs_2029);
		
		Set<String> recs_2030 = new HashSet<String>();
		recs_2030.add("HCI Bibliography : Human-Computer Interaction / User Interface Usability  authored by   user_2034  at   http://www.hcibib.org/  with TAGS:   HCI design reference research bibliography interaction interface usability ");
		recs_2030.add("Confusability: Usersaurus, Folksonomy and the abdication of responsibility for design  authored by   user_2025  at   http://usability.typepad.com/confusability/2005/04/usersaurus_vs_f.html  with TAGS:   usability folksonomy informa��o toread tagging design ");
		recs_2030.add("Home page for Heuristics interactivity  authored by   user_2015  at   http://www.id-book.com/catherb/index.htm  with TAGS:   hci design interaction heuristics usability tools ");
		recs_2030.add("useit.com: Jakob Nielsen on Usability and Web Design  authored by   user_2034  at   http://www.useit.com/  with TAGS:   accessibility HCI ui usability Neilsen reference design web webdesign ");
		recs_2030.add("Meryl.net  175+ Data and Information Visualization Examples and Resources  authored by   user_2043  at   http://meryl.net/2008/01/22/175-data-and-information-visualization-examples-and-resources/  with TAGS:   examples resources graphics data design visualization information reference ");
		mapRecB.put("user_2030", recs_2030);
		
		Set<String> recs_2031 = new HashSet<String>();
		recs_2031.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   social community tools search ");
		recs_2031.add("Welcome to Facebook! | Facebook  authored by   user_2005  at   http://facebook.com/  with TAGS:   socialnetworking facebook community social networking friends ");
		recs_2031.add("Excel .NetMap - Social Network Add-in for Excel 2007 - Windows Live SkyDrive  authored by   user_2020  at   http://cid-ae935b3cde8015dd.skydrive.live.com/browse.aspx/Excel%20.NetMap%20-%20Social%20Network%20Add-in%20for%20Excel%202007  with TAGS:   Excell DLACIII social networks Microsoft ");
		recs_2031.add("Consumating  authored by   user_2027  at   http://www.consumating.com/  with TAGS:   tags dating social_tagging folksonomy community social ");
		recs_2031.add("studiVZ | Bist Du schon drin?  authored by   user_2018  at   http://www.studivz.net/  with TAGS:   community students german networking social ");
		mapRecB.put("user_2031", recs_2031);
		
		Set<String> recs_2032 = new HashSet<String>();
		recs_2032.add("mozilla.org  authored by   user_2023  at   http://www.mozilla.org/  with TAGS:   firefox browser web mozilla opensource software ");
		recs_2032.add("MoinMoin - MoinMoin  authored by   user_2001  at   http://moinmo.in/MoinMoin  with TAGS:   software python tools linux wiki ");
		recs_2032.add("Open Channel Foundation: SAILE  authored by   user_2033  at   http://openchannelsoftware.org/projects/SAILE/  with TAGS:   collaboration PBL tools Learning ");
		recs_2032.add("The 10th International Software Product Line Conference (SPLC 2006)  authored by   user_2020  at   http://www.sei.cmu.edu/splc2006/tech_program.html  with TAGS:   conference lines product software ");
		recs_2032.add("Open Source Digital Forensics  authored by   user_2001  at   http://www.opensourceforensics.org/  with TAGS:   security software tools computer linux forensics opensource ");
		mapRecB.put("user_2032", recs_2032);

		Set<String> recs_2033 = new HashSet<String>();
		recs_2033.add("GameDev.net - all your game development needs  authored by   user_2021  at   http://www.gamedev.net/  with TAGS:   software games news tutorials development gamedev programming ");
		recs_2033.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education technology blog ");
		recs_2033.add("Haskell Study Plan  authored by   user_2001  at   http://www.alpheccar.org/en/posts/show/67  with TAGS:   tutorial haskell functional programming language learning ");
		recs_2033.add("Google Operating System (Unofficial Google Blog)  authored by   user_2016  at   http://googlesystem.blogspot.com/  with TAGS:   blog technology google ");
		recs_2033.add("gosu - Google Code  authored by   user_2001  at   http://code.google.com/p/gosu/  with TAGS:   gamedev development ruby games programming library ");
		mapRecB.put("user_2033", recs_2033);
		
		Set<String> recs_2034 = new HashSet<String>();
		recs_2034.add("International standards for HCI and usability - GUILHERMO.com - Arquitetura de Informa��o  authored by   user_2025  at   http://www.guilhermo.com/ai_biblioteca/referencia.asp?referencia=127  with TAGS:   hci information interaction interface usability ");
		recs_2034.add("Home page for Heuristics interactivity  authored by   user_2015  at   http://www.id-book.com/catherb/index.htm  with TAGS:   hci design interaction heuristics usability tools ");
		recs_2034.add("Confusability: Usersaurus, Folksonomy and the abdication of responsibility for design  authored by   user_2025  at   http://usability.typepad.com/confusability/2005/04/usersaurus_vs_f.html  with TAGS:   usability folksonomy informa��o toread tagging design ");
		recs_2034.add("HCIRN: Human-Computer Interaction Resource Network  authored by   user_2033  at   http://www.hcirn.com/index.php  with TAGS:   resources webdesign hci interaction articles research design jobs usability ");
		recs_2034.add("Lukew authored by   user_2025  at   http://www.lukew.com/ff/entry.asp?156  with TAGS:   interface experience ux Design reference ui diagrams webdesign usability user ");
		mapRecB.put("user_2034", recs_2034);
		
		Set<String> recs_2036 = new HashSet<String>();
		recs_2036.add("Personal and Group Learning Using Web 2.0 Tools : eLearning Technology  authored by   user_2033  at   http://elearningtech.blogspot.com/2006/09/personal-and-group-learning-using-web.html  with TAGS:   howto blog ple wiki e-learning collaboration elearning ");
		recs_2036.add("Zimbra  authored by   user_2032  at   http://www.zimbra.com/  with TAGS:   opensource zimbra ajax collaboration linux groupware software ");
		recs_2036.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   reference research Libraries medical government health medicine ");
		recs_2036.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		recs_2036.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   social collaboration enterprise search tagging ");
		mapRecB.put("user_2036", recs_2036);
		
		Set<String> recs_2037 = new HashSet<String>();
		recs_2037.add("HulaBoys  authored by   user_2018  at   http://www.hulaboys.de/  with TAGS:   fun german blog video ");
		recs_2037.add("Guardian Unlimited  authored by   user_2018  at   http://www.guardian.co.uk/  with TAGS:   uk media blog news ");
		recs_2037.add("Lyrics.com  authored by   user_2004  at   http://www.lyrics.com/  with TAGS:   lyrics songs many entertainment music musica search reference ");
		recs_2037.add("Politiken.dk - det levende net  authored by   user_2003  at   http://politiken.dk/newsinenglish  with TAGS:   english denmark politiken news ");
		recs_2037.add("Google  authored by   user_2009  at   http://www.google.com/  with TAGS:   searchengine search web everything engine doubts google reference ");
		mapRecB.put("user_2037", recs_2037);

		Set<String> recs_2038 = new HashSet<String>();
		recs_2038.add("The 10th International Software Product Line Conference (SPLC 2006)  authored by   user_2020  at   http://www.sei.cmu.edu/splc2006/tech_program.html  with TAGS:   conference lines product software ");
		recs_2038.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   work theory research homepage software ");
		recs_2038.add("Google Operating System (Unofficial Google Blog)  authored by   user_2016  at   http://googlesystem.blogspot.com/  with TAGS:   blog technology google ");
		recs_2038.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education technology blog ");
		recs_2038.add("Social Software Building Blocks / nForm / Customer Insight, Strategy, Design and Development  authored by   user_2025  at   http://nform.ca/publications/social-software-building-block  with TAGS:   software community design socialsoftware paper research toread social ");
		mapRecB.put("user_2038", recs_2038);
		
		Set<String> recs_2039 = new HashSet<String>();
		recs_2039.add("Python Paste  authored by   user_2001  at   http://pythonpaste.org/  with TAGS:   wsgi web framework development paste webdev python programming ");
		recs_2039.add("Jena Semantic Web Framework  authored by   user_2017  at   http://jena.sourceforge.net/  with TAGS:   ontology semanticweb jena java web owl framework api opensource semantic rdf ");
		recs_2039.add("Django | The Web framework for perfectionists with deadlines  authored by   user_2023  at   http://www.djangoproject.com/  with TAGS:   web webdev framework webdevelopment python development programming django ");
		recs_2039.add("The future of PHP  authored by   user_2043  at   http://www.ibm.com/developerworks/opensource/library/os-php-future/?ca=dgr-lnxw01PHP-Future  with TAGS:   webdev PHP programming webdevelopment scripting web development ");
		recs_2039.add("Simple Interface for Global Optimization Algorithms  authored by   user_2015  at   http://www.sigoa.org/  with TAGS:   mathematics Algorithms ai optimization books ");
		mapRecB.put("user_2039", recs_2039);

		Set<String> recs_2042 = new HashSet<String>();
		recs_2042.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   social community tools search ");
		recs_2042.add("Welcome to Facebook! | Facebook  authored by   user_2005  at   http://facebook.com/  with TAGS:   socialnetworking facebook community social networking friends ");
		recs_2042.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   tools vote social fun blog polls ");
		recs_2042.add("script.aculo.us  authored by   user_2027  at   http://script.aculo.us/  with TAGS:   css archive useful javascript code HTML ajax webdesign reference programming ");
		recs_2042.add("The Innovation Game | Tux Deluxe  authored by   user_2001  at   http://www.tuxdeluxe.org/node/267  with TAGS:   innovation business ideas patents software free ");
		mapRecB.put("user_2042", recs_2042);

		Set<String> recs_2043 = new HashSet<String>();
		recs_2043.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   social community tools search ");
		recs_2043.add("Workshop on the Issues of Designing and Evaluating Ambient Information Systems Conference on Pervasive Computing  authored by   user_2020  at   http://informatics.indiana.edu/subtletech/  with TAGS:   conference ambient visualization workshop research information design ");
		recs_2043.add("Tutorial sobre COE editor da CMAPTools  authored by   user_2025  at   http://www2.dcc.ufmg.br/laboratorios/lsi/EAD/MapasConc/TutorialCMapTools.html  with TAGS:   ontology tutorial coeedito design ");
		recs_2043.add("Home page for Heuristics interactivity  authored by   user_2015  at   http://www.id-book.com/catherb/index.htm  with TAGS:   hci design interaction heuristics usability tools ");
		recs_2043.add("/PSTricks/main  authored by   user_2001  at   http://tug.org/PSTricks/main.cgi/  with TAGS:   graphics software postscript tex tutorial latex pstricks reference ");
		mapRecB.put("user_2043", recs_2043);

		Set<String> recs_2044 = new HashSet<String>();
		recs_2044.add("Google Operating System (Unofficial Google Blog)  authored by   user_2016  at   http://googlesystem.blogspot.com/  with TAGS:   blog technology google ");
		recs_2044.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education technology blog ");
		recs_2044.add("Guardian Unlimited  authored by   user_2018  at   http://www.guardian.co.uk/  with TAGS:   uk media blog news ");
		recs_2044.add("Preview of new Delicious features  authored by   user_2027  at   http://www.techcrunch.com/2007/09/06/exclusive-screen-shots-and-feature-overview-of-delicious-20-preview/  with TAGS:   blog design preview delicious tagging del.icio.us technology ");
		recs_2044.add("IsLifeCorp :: Home  authored by   user_2006  at   http://www.islifecorp.com.br/  with TAGS:   download series tv blog ");
		mapRecB.put("user_2044", recs_2044);	

		return mapRecB;
	}
	
	
	
	
	public static Map<String,Set<String>> loadRecA(){
		Map<String,Set<String>> mapRecA = new HashMap<String,Set<String>>();

		Set<String> recs_2000 = new HashSet<String>();
		recs_2000.add("download prot�g� software  authored by   user_2025  at   http://protege.stanford.edu/download/download.html  with TAGS:   ontology protege knowledge semanticweb tools ");
		recs_2000.add("Eclipse Process Framework Project (EPF)  authored by   user_2038  at   http://www.eclipse.org/epf/  with TAGS:   softwareengineering engineering eclipse unifiedprocess xp scrum process framework tools software workflow ");
		recs_2000.add("SourceForge.net opentaps open source ERP+CRM  authored by   user_2020  at   http://sourceforge.net/projects/opentaps/  with TAGS:   software erp freeware java crm opensource management ");
		recs_2000.add("A Course On Workflow Management  authored by   user_2020  at   http://is.tm.tue.nl/staff/wvdaalst/workflowcourse/  with TAGS:   process bpm course management workflow tutorial business ");
		recs_2000.add("Guia: como funciona a Bolsa de Valores e como aplicar em a��es na Bovespa - UOL Economia  authored by   user_2025  at   http://economia.uol.com.br/ultnot/2008/05/14/guia_bovespa_bolsa_valores_aplicar_acoes.jhtm  with TAGS:   economia investimentos de valores bolsa finan�as bovespa ");
		mapRecA.put("user_2000", recs_2000);
		
		Set<String> recs_2001 = new HashSet<String>();
		recs_2001.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		recs_2001.add("Zimbra  authored by   user_2032  at   http://www.zimbra.com/  with TAGS:   opensource zimbra ajax collaboration linux groupware software ");
		recs_2001.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		recs_2001.add("The QuakeForge Project: Welcome!  authored by   user_2033  at   http://www.quakeforge.net/  with TAGS:   linux 3d computer opensource games quake programming ");
		recs_2001.add("Nagios  authored by   user_2032  at   http://www.nagios.org/  with TAGS:   monitoring service network linux software opensource ");
		mapRecA.put("user_2001", recs_2001);
		
		Set<String> recs_2002 = new HashSet<String>();
		recs_2002.add("prefuse | interactive information visualization toolkit  authored by   user_2023  at   http://prefuse.org/  with TAGS:   graph opensource software java semanticweb visualization library programming ");
		recs_2002.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		recs_2002.add("UML Modeling Tool - JUDE  authored by   user_2015  at   http://jude.change-vision.com/jude-web/index.html  with TAGS:   software tools design development java programming uml ");
		recs_2002.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		recs_2002.add("Doc til Pdf converter  authored by   user_2030  at   http://www.gohtm.com/convert_pdf.asp  with TAGS:   software convert web Praktisk online_tools free pdf tools ");
		mapRecA.put("user_2002", recs_2002);
		
		Set<String> recs_2003 = new HashSet<String>();
		recs_2003.add("Aalborg - VisitAalborg - Denmark.The official guide to Aalborg with ...  authored by   user_2004  at   http://www.visitaalborg.com/international/en-gb/menu/tourist/turist-maalgruppe-forside.htm  with TAGS:   aalborg travel denmark tourism information ");
		recs_2003.add("Paul Thurrott's SuperSite for Windows: It's the future of Windows ... Today!  authored by   user_2015  at   http://www.winsupersite.com/  with TAGS:   reviews Magazines tech news microsoft blog windows software ");
		recs_2003.add("Momondo  authored by   user_2027  at   http://momondo.com/  with TAGS:   search useful flights airlines tickets cheap travel ");
		recs_2003.add("Merriam-Webster Online  authored by   user_2033  at   http://www.merriam-webster.com/  with TAGS:   english thesaurus online tools Learning language reference Dictionary ");
		recs_2003.add("Merriam-Webster Unabridged  authored by   user_2033  at   http://unabridged.merriam-webster.com/  with TAGS:   words tools Dictionary academic reference english search language ");
		mapRecA.put("user_2003", recs_2003);
		
		Set<String> recs_2004 = new HashSet<String>();
		recs_2004.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2004.add("Encyclopedia Mythica: All mythologies.  authored by   user_2015  at   http://www.pantheon.org/areas/all/articles.html  with TAGS:   encyclopedia mythology PersonalPages history literature religion information reference ");
		recs_2004.add("Munich.com - Munich Travel, Munich Flights, Munich Hotels  authored by   user_2017  at   http://www.munich.com/  with TAGS:   hotel munich trip travel ");
		recs_2004.add("Merriam-Webster Online  authored by   user_2033  at   http://www.merriam-webster.com/  with TAGS:   english thesaurus online tools Learning language reference Dictionary ");
		recs_2004.add("Artcyclopedia  authored by   user_2027  at   http://www.artcyclopedia.com/  with TAGS:   culture search images artists art encyclopedia reference ");
		mapRecA.put("user_2004", recs_2004);
		
		Set<String> recs_2005 = new HashSet<String>();
		recs_2005.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		recs_2005.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		recs_2005.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		recs_2005.add("OpenSocial: a converg�ncia das redes sociais | Meio Bit  authored by   user_2025  at   http://www.meiobit.com/google/opensocial_converg_ncia_das_redes_sociais  with TAGS:   social google network opensocial ");
		recs_2005.add("Social Software Building Blocks / nForm / Customer Insight, Strategy, Design and Development  authored by   user_2025  at   http://nform.ca/publications/social-software-building-block  with TAGS:   research toread design community socialsoftware software paper social ");
		mapRecA.put("user_2005", recs_2005);
		
		Set<String> recs_2006 = new HashSet<String>();
		recs_2006.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		recs_2006.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		recs_2006.add("Why Johnny can't code | Lambda the Ultimate  authored by   user_2033  at   http://lambda-the-ultimate.org/node/1733#comment-21414  with TAGS:   blog discussion language games Learning kids programming ");
		recs_2006.add("Paul Thurrott's SuperSite for Windows: It's the future of Windows ... Today!  authored by   user_2015  at   http://www.winsupersite.com/  with TAGS:   reviews Magazines tech news microsoft blog windows software ");
		recs_2006.add("ARTICLE: More Anecdotes About Google - Brad DeLong  authored by   user_2027  at   http://www.j-bradford-delong.net/movable_type/2004_archives/001272.html  with TAGS:   google facts article work useful search ");
		mapRecA.put("user_2006", recs_2006);

		

		Set<String> recs_2009 = new HashSet<String>();
		recs_2009.add("Wikipedia, the free encyclopedia  authored by   user_2021  at   http://en.wikipedia.org/wiki/Main_Page  with TAGS:   wiki information encyclopedia research wikipedia search reference ");
		recs_2009.add("Artcyclopedia  authored by   user_2027  at   http://www.artcyclopedia.com/  with TAGS:   culture search images artists art encyclopedia reference ");
		recs_2009.add("Scholar  authored by   user_2034  at   http://scholar.google.com/  with TAGS:   search research reference academic science google literature citations scholar ");
		recs_2009.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki programming books python tutorial reference ");
		recs_2009.add("Encyclopedia Mythica: All mythologies.  authored by   user_2015  at   http://www.pantheon.org/areas/all/articles.html  with TAGS:   encyclopedia mythology PersonalPages history literature religion information reference ");
		mapRecA.put("user_2009", recs_2009);
		
		Set<String> recs_2010 = new HashSet<String>();
		recs_2010.add("Folha Online - Primeiro jornal em tempo real em l�ngua portuguesa  authored by   user_2004  at   http://www.folha.uol.com.br/  with TAGS:   folha noticias jornal brasil news not�cias jornais ");
		recs_2010.add("Google Idol  authored by   user_2027  at   http://www.googleidol.com/  with TAGS:   funny idols humor fun video google music ");
		recs_2010.add("Google Code for Educators - Google: MapReduce in a Week  authored by   user_2001  at   http://code.google.com/edu/content/submissions/mapreduce/listing.html  with TAGS:   course distributed tutorial google hadoop mapreduce programming ");
		recs_2010.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   fun polls tools blog social vote ");
		recs_2010.add("English Russia � Glazastik. The New Internet Hero  authored by   user_2001  at   http://englishrussia.com/?p=1060#more-1060  with TAGS:   politics funny culture humor photoshop russia fun video ");
		mapRecA.put("user_2010", recs_2010);

		Set<String> recs_2012 = new HashSet<String>();
		recs_2012.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		recs_2012.add("HOPL  authored by   user_2001  at   http://research.ihost.com/hopl/HOPL.html  with TAGS:   language acm programming-languages history hopl conference programming ");
		recs_2012.add("American Film Institute: Top 100 movie quotes  authored by   user_2027  at   http://www.afi.com/tvevents/100years/quotes.aspx#list  with TAGS:   list cinema fun film cool quotes movie ");
		recs_2012.add("Adaptive Hypermedia 2008  authored by   user_2034  at   http://www.ah2008.org/index.php?section=1  with TAGS:   ah2008 personalisation research adaptive conference hypermedia ");
		recs_2012.add("PhD researcher Network | TENCompetence  authored by   user_2043  at   http://www.tencompetence.org/node/57  with TAGS:   professional university network PhD resources TENCompetence research ");
		mapRecA.put("user_2012", recs_2012);
		
		Set<String> recs_2013 = new HashSet<String>();
		recs_2013.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		recs_2013.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		recs_2013.add("The Social Software Weblog  authored by   user_2025  at   http://socialsoftware.weblogsinc.com/  with TAGS:   socialsoftware blog social_software social software ");
		recs_2013.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		recs_2013.add("http://www.stoweboyd.com/message/2006/10/are_you_ready_f.html  authored by   user_2025  at   http://www.stoweboyd.com/message/2006/10/are_you_ready_f.html  with TAGS:   social socialnetworking socialsoftware blog software definitions ");
		mapRecA.put("user_2013", recs_2013);
		
		Set<String> recs_2015 = new HashSet<String>();
		recs_2015.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		recs_2015.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2015.add("Beginning Pylons - an introduction to using the Pylons web framework  authored by   user_2001  at   http://workaround.org/pylons/beginning-pylons.html  with TAGS:   python pylons wsgi web framework programming tutorial reference ");
		recs_2015.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		recs_2015.add("Brian's TurboGears Tutorial  authored by   user_2001  at   http://exogen.case.edu/turbogears.html  with TAGS:   webdev mochikit python web turbogears tutorial programming ");
		mapRecA.put("user_2015", recs_2015);
		
		Set<String> recs_2016 = new HashSet<String>();
		recs_2016.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		recs_2016.add("About the social semantic web  authored by   user_2013  at   http://ablvienna.wordpress.com/  with TAGS:   semantic_web blog semanticweb socialsemanticweb education socialsoftware semantic ");
		recs_2016.add("Sasha Barab  authored by   user_2020  at   http://inkido.indiana.edu/barab/index.html  with TAGS:   technology teaching education DLACIII academic research games ");
		recs_2016.add("Stevey's Blog Rants: Rich Programmer Food  authored by   user_2001  at   http://steve-yegge.blogspot.com/2007/06/rich-programmer-food.html?  with TAGS:   cs computerscience education compilers programming blog development ");
		recs_2016.add("Preview of new Delicious features  authored by   user_2027  at   http://www.techcrunch.com/2007/09/06/exclusive-screen-shots-and-feature-overview-of-delicious-20-preview/  with TAGS:   del.icio.us delicious preview blog tagging design technology ");
		mapRecA.put("user_2016", recs_2016);
		
		Set<String> recs_2017 = new HashSet<String>();
		recs_2017.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
		recs_2017.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		recs_2017.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		recs_2017.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		recs_2017.add("Thea - [SWI] Prolog OWL library  authored by   user_2015  at   http://www.semanticweb.gr/TheaOWLLib/  with TAGS:   owl api semanticweb library ontology development prolog ");
		mapRecA.put("user_2017", recs_2017);
		
		
		Set<String> recs_2018 = new HashSet<String>();
		recs_2018.add("Ehrensenf Internet TV  authored by   user_2023  at   http://www.ehrensenf.de/  with TAGS:   internet tv fernsehen video blog fun iTV german ");
		recs_2018.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   fun polls tools blog social vote ");
		recs_2018.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		recs_2018.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		recs_2018.add("Google Idol  authored by   user_2027  at   http://www.googleidol.com/  with TAGS:   funny idols humor fun video google music ");
		mapRecA.put("user_2018", recs_2018);
		
		
		Set<String> recs_2019 = new HashSet<String>();
		recs_2019.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   reference research Libraries medical government health medicine ");
		recs_2019.add("tabulator Generic data browser  authored by   user_2020  at   http://www.w3.org/2005/ajar/tab  with TAGS:   rdf browser ajax web semanticweb javascript SemanticWikis semantic ");
		recs_2019.add("RFC Archive  authored by   user_2023  at   http://www.faqs.org/rfcs/  with TAGS:   Standards internet web reference programming developerinformation rfc ");
		recs_2019.add("Semantic Book Suggestions: knowledgelounge.org  authored by   user_2023  at   http://kapustar.punkt.at/labs/knowledgelounge.org/  with TAGS:   search semanticweb book calais tools semantic programming ");
		recs_2019.add("HCIRN: Human-Computer Interaction Resource Network  authored by   user_2033  at   http://www.hcirn.com/index.php  with TAGS:   resources webdesign hci interaction articles research design jobs usability ");		
		mapRecA.put("user_2019", recs_2019);
		
		Set<String> recs_2020 = new HashSet<String>();
		recs_2020.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		recs_2020.add("Zope.org  authored by   user_2001  at   http://www.zope.org/  with TAGS:   zope python web OpenSource cms programming software ");
		recs_2020.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		recs_2020.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		recs_2020.add("Zimbra  authored by   user_2032  at   http://www.zimbra.com/  with TAGS:   opensource zimbra ajax collaboration linux groupware software ");
		mapRecA.put("user_2020", recs_2020);
		
		Set<String> recs_2021 = new HashSet<String>();
		recs_2021.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		recs_2021.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		recs_2021.add("gosu - Google Code  authored by   user_2001  at   http://code.google.com/p/gosu/  with TAGS:   gamedev games ruby programming development library ");
		recs_2021.add("The Official Blitz Website  authored by   user_2033  at   http://www.blitzbasic.com/  with TAGS:   basic games development programming language gamedev blitz ");
		recs_2021.add("Squeak: Squeak  authored by   user_2033  at   http://www.squeak.org/  with TAGS:   games kids programming opensource squeak smalltalk software development language ");
		mapRecA.put("user_2021", recs_2021);
		
		Set<String> recs_2022 = new HashSet<String>();
		recs_2022.add("Tutorial sobre COE editor da CMAPTools  authored by   user_2025  at   http://www2.dcc.ufmg.br/laboratorios/lsi/EAD/MapasConc/TutorialCMapTools.html  with TAGS:   ontology coeedito tutorial design ");
		recs_2022.add("Eclipse Corner Article: Eclipse Forms: New in 3.3  authored by   user_2006  at   http://www.eclipse.org/articles/article.php?file=Article-Forms33/index.html  with TAGS:   editors article forms eclipse ");
		recs_2022.add("download prot�g� software  authored by   user_2025  at   http://protege.stanford.edu/download/download.html  with TAGS:   ontology protege knowledge semanticweb tools ");
		recs_2022.add("LibraryThing | Catalog your books online  authored by   user_2015  at   chrome://ietab/content/reloaded.html?url=http://www.librarything.com/thingology/2007/02/when-tags-works-and-when-they-dont.php  with TAGS:   folksonomy books tags ontology ");
		recs_2022.add("Thea - [SWI] Prolog OWL library  authored by   user_2015  at   http://www.semanticweb.gr/TheaOWLLib/  with TAGS:   owl api semanticweb library ontology development prolog ");
		mapRecA.put("user_2022", recs_2022);
		
		Set<String> recs_2023 = new HashSet<String>();
		recs_2023.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		recs_2023.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		recs_2023.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2023.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		recs_2023.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		mapRecA.put("user_2023", recs_2023);
		
		Set<String> recs_2024 = new HashSet<String>();
		recs_2024.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		recs_2024.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		recs_2024.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   community tools search social ");
		recs_2024.add("Flickr  authored by   user_2031  at   http://flickr.com/  with TAGS:   flickr social photo ");
		recs_2024.add("YouTube - TV1 Trend Report  authored by   user_2025  at   http://www.youtube.com/watch?v=JMRF_ZXms9E  with TAGS:   YouTube marketing video social internet ");
		mapRecA.put("user_2024", recs_2024);
		
		Set<String> recs_2025 = new HashSet<String>();
		recs_2025.add("About the social semantic web  authored by   user_2013  at   http://ablvienna.wordpress.com/  with TAGS:   semantic_web blog semanticweb socialsemanticweb education socialsoftware semantic ");
		recs_2025.add("tabulator Generic data browser  authored by   user_2020  at   http://www.w3.org/2005/ajar/tab  with TAGS:   rdf browser ajax web semanticweb javascript SemanticWikis semantic ");
		recs_2025.add("Why Johnny can't code | Lambda the Ultimate  authored by   user_2033  at   http://lambda-the-ultimate.org/node/1733#comment-21414  with TAGS:   blog discussion language games Learning kids programming ");
		recs_2025.add("Top 5 Photoshop tutorials  authored by   user_2027  at   http://www.techeblog.com/index.php/tech-gadget/top-5-photoshop-tutorials  with TAGS:   photography tutorial graphics photoshop howto design reference ");
		recs_2025.add("Zope.org  authored by   user_2001  at   http://www.zope.org/  with TAGS:   zope python web OpenSource cms programming software ");
		mapRecA.put("user_2025", recs_2025);
		
		Set<String> recs_2026 = new HashSet<String>();
		recs_2026.add("Guido van Robot  authored by   user_2033  at   http://gvr.sourceforge.net/about.php  with TAGS:   learning Kids python education language coding programming ");
		recs_2026.add("Tutorial sobre COE editor da CMAPTools  authored by   user_2025  at   http://www2.dcc.ufmg.br/laboratorios/lsi/EAD/MapasConc/TutorialCMapTools.html  with TAGS:   ontology coeedito tutorial design ");
		recs_2026.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		recs_2026.add("ARIADNE Educational Metadata Recommendation  authored by   user_2034  at   http://www.ariadne-eu.org/3_MD/ariadne_metadata_v31.htm  with TAGS:   education object metadata learning ");
		recs_2026.add("Generic Problem-Based Learning Essentials  authored by   user_2043  at   http://pbli.org/pbl/generic_pbl.htm  with TAGS:   elearning learning problembasedlearning PBL reference education ");
		mapRecA.put("user_2026", recs_2026);
		
		Set<String> recs_2027 = new HashSet<String>();
		recs_2027.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2027.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		recs_2027.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		recs_2027.add("/PSTricks/main  authored by   user_2001  at   http://tug.org/PSTricks/main.cgi/  with TAGS:   graphics tex postscript latex pstricks tutorial software reference ");
		recs_2027.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		mapRecA.put("user_2027", recs_2027);
		
		Set<String> recs_2028 = new HashSet<String>();
		recs_2028.add("Matrox  authored by   user_2015  at   http://www.matrox.com/  with TAGS:   Hardware graphics company computer video matrox ");
		recs_2028.add("YouTube - batman dead end  authored by   user_2018  at   http://de.youtube.com/watch?v=Hjp0I_okX0w  with TAGS:   batman fanmovie comic video ");
		recs_2028.add("Shirky: Ontology is Overrated -- Categories, Links, and Tags  authored by   user_2025  at   http://shirky.com/writings/ontology_overrated.html  with TAGS:   classification categorization ontology delicious folksonomy toread tagging social ");
		recs_2028.add("Google Idol  authored by   user_2027  at   http://www.googleidol.com/  with TAGS:   funny idols humor fun video google music ");
		recs_2028.add("Google documentaire voor ICTvS&W college  authored by   user_2027  at   http://videotheek.surfnet.nl/play_proxy/mmc/24213/googleachterdeschermen.asf  with TAGS:   college work streaming useful google video ");
		mapRecA.put("user_2028", recs_2028);
		
		Set<String> recs_2029 = new HashSet<String>();
		recs_2029.add("GameDev.net - all your game development needs  authored by   user_2021  at   http://www.gamedev.net/  with TAGS:   development tutorials games gamedev news software programming ");
		recs_2029.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		recs_2029.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		recs_2029.add("maemo.org - maemo.org: Maemo is the application development platform for Internet Tablets  authored by   user_2001  at   http://maemo.org/  with TAGS:   development linux mobile maemo programming nokia software ");
		recs_2029.add("Software Engineering Institute Information Server  authored by   user_2015  at   http://www.sei.cmu.edu/  with TAGS:   development software_engineering programming SoftwareEngineering engineering sei software ");
		mapRecA.put("user_2029", recs_2029);
		
		Set<String> recs_2030 = new HashSet<String>();
		recs_2030.add("The Road to Know Where: Ultimate List of Free Windows Software from Microsoft  authored by   user_2015  at   http://bhandler.spaces.live.com/blog/cns!70F64BC910C9F7F3!1231.entry  with TAGS:   microsoft freeware utilities tools free windows software ");
		recs_2030.add("useit.com: Jakob Nielsen on Usability and Web Design  authored by   user_2034  at   http://www.useit.com/  with TAGS:   usability ui HCI webdesign design accessibility web Neilsen reference ");
		recs_2030.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2030.add("Merriam-Webster Online  authored by   user_2033  at   http://www.merriam-webster.com/  with TAGS:   english thesaurus online tools Learning language reference Dictionary ");
		recs_2030.add("Conjugation Trainer - Select verbs and language  authored by   user_2033  at   http://www.vocabulix.com/online/Learn/Verbs  with TAGS:   Dictionary grammar tools language ");
		mapRecA.put("user_2030", recs_2030);
		
		Set<String> recs_2031 = new HashSet<String>();
		recs_2031.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		recs_2031.add("The Social Software Weblog  authored by   user_2025  at   http://socialsoftware.weblogsinc.com/  with TAGS:   socialsoftware blog social_software social software ");
		recs_2031.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   social collaboration enterprise search tagging ");
		recs_2031.add("OpenSocial: a converg�ncia das redes sociais | Meio Bit  authored by   user_2025  at   http://www.meiobit.com/google/opensocial_converg_ncia_das_redes_sociais  with TAGS:   social google network opensocial ");
		recs_2031.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   fun polls tools blog social vote ");
		mapRecA.put("user_2031", recs_2031);
		
		Set<String> recs_2032 = new HashSet<String>();
		recs_2032.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		recs_2032.add("SOA Software - SOA Software Acquires LogicLibrary  authored by   user_2038  at   http://www.soa.com/index.php/section/company_press_detail/soa_software_acquires_logiclibrary  with TAGS:   software logiclibrary service governance soa ");
		recs_2032.add("The QuakeForge Project: Welcome!  authored by   user_2033  at   http://www.quakeforge.net/  with TAGS:   linux 3d computer opensource games quake programming ");
		recs_2032.add("prefuse | interactive information visualization toolkit  authored by   user_2023  at   http://prefuse.org/  with TAGS:   graph opensource software java semanticweb visualization library programming ");
		recs_2032.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		mapRecA.put("user_2032", recs_2032);
		
		Set<String> recs_2033 = new HashSet<String>();
		recs_2033.add("Haskell Study Plan  authored by   user_2001  at   http://www.alpheccar.org/en/posts/show/67  with TAGS:   tutorial language haskell functional learning programming ");
		recs_2033.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		recs_2033.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki programming books python tutorial reference ");
		recs_2033.add("Home page for Heuristics interactivity  authored by   user_2015  at   http://www.id-book.com/catherb/index.htm  with TAGS:   tools hci design interaction usability heuristics ");
		recs_2033.add("GameDev.net - all your game development needs  authored by   user_2021  at   http://www.gamedev.net/  with TAGS:   development tutorials games gamedev news software programming ");
		mapRecA.put("user_2033", recs_2033);
		
		Set<String> recs_2034 = new HashSet<String>();
		recs_2034.add("Learner Modelling for Reflection  authored by   user_2043  at   http://www.eee.bham.ac.uk/bull/lemore/  with TAGS:   research OpenLearnerModel elearning reference ");
		recs_2034.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2034.add("Home page for Heuristics interactivity  authored by   user_2015  at   http://www.id-book.com/catherb/index.htm  with TAGS:   tools hci design interaction usability heuristics ");
		recs_2034.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		recs_2034.add("International standards for HCI and usability - GUILHERMO.com - Arquitetura de Informa��o  authored by   user_2025  at   http://www.guilhermo.com/ai_biblioteca/referencia.asp?referencia=127  with TAGS:   interaction usability information hci interface ");
		mapRecA.put("user_2034", recs_2034);
		
		
		Set<String> recs_2036 = new HashSet<String>();
		recs_2036.add("Social Software Building Blocks / nForm / Customer Insight, Strategy, Design and Development  authored by   user_2025  at   http://nform.ca/publications/social-software-building-block  with TAGS:   software community design socialsoftware paper research toread social ");
		recs_2036.add("PhD researcher Network | TENCompetence  authored by   user_2043  at   http://www.tencompetence.org/node/57  with TAGS:   professional university network PhD resources TENCompetence research ");
		recs_2036.add("HCIRN: Human-Computer Interaction Resource Network  authored by   user_2033  at   http://www.hcirn.com/index.php  with TAGS:   resources webdesign hci interaction articles research design jobs usability ");
		recs_2036.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		recs_2036.add("The Social Software Weblog  authored by   user_2025  at   http://socialsoftware.weblogsinc.com/  with TAGS:   socialsoftware blog social_software social software ");		
		mapRecA.put("user_2036", recs_2036);	

		Set<String> recs_2037 = new HashSet<String>();
		recs_2037.add("Politiken.dk - det levende net  authored by   user_2003  at   http://politiken.dk/newsinenglish  with TAGS:   politiken english denmark news ");
		recs_2037.add("Google  authored by   user_2009  at   http://www.google.com/  with TAGS:   engine searchengine reference search web everything doubts google ");
		recs_2037.add("Learner Modelling for Reflection  authored by   user_2043  at   http://www.eee.bham.ac.uk/bull/lemore/  with TAGS:   research OpenLearnerModel elearning reference ");
		recs_2037.add("Scholar  authored by   user_2034  at   http://scholar.google.com/  with TAGS:   search research reference academic science google literature citations scholar ");
		recs_2037.add("Watch Movies Online For Free Full Movie Downloads  authored by   user_2005  at   http://www.watch-movies.net/  with TAGS:   online television movies free tv streaming film community video ");
		mapRecA.put("user_2037", recs_2037);
		
		Set<String> recs_2038 = new HashSet<String>();
		recs_2038.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		recs_2038.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		recs_2038.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		recs_2038.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
		recs_2038.add("Auto-vectorization in GCC - GNU Project - Free Software Foundation (FSF)  authored by   user_2001  at   http://gcc.gnu.org/projects/tree-ssa/vectorization.html  with TAGS:   gcc software vectorization compiler optimization programming ");
		mapRecA.put("user_2038", recs_2038);
		
		Set<String> recs_2039 = new HashSet<String>();
		recs_2039.add("Brian's TurboGears Tutorial  authored by   user_2001  at   http://exogen.case.edu/turbogears.html  with TAGS:   webdev mochikit python web turbogears tutorial programming ");
		recs_2039.add("DevCentral Home  authored by   user_2015  at   http://devcentral.iticentral.com/  with TAGS:   java asp c++ tutorial programming resources Windows ");
		recs_2039.add("The future of PHP  authored by   user_2043  at   http://www.ibm.com/developerworks/opensource/library/os-php-future/?ca=dgr-lnxw01PHP-Future  with TAGS:   scripting PHP programming development webdev webdevelopment web ");
		recs_2039.add("Ola Bini on Java, Lisp, Ruby and AI: Code size and dynamic languages  authored by   user_2001  at   http://ola-bini.blogspot.com/2007/12/code-size-and-dynamic-languages.html  with TAGS:   ruby javascript scala java languages code programming ");
		recs_2039.add("TOMCAT + HTTPS  authored by   user_2002  at   http://www.guj.com.br/posts/list/29710.java  with TAGS:   ssl java Security tomcat https ");
		mapRecA.put("user_2039", recs_2039);

		Set<String> recs_2042 = new HashSet<String>();
		recs_2042.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		recs_2042.add("Brian's TurboGears Tutorial  authored by   user_2001  at   http://exogen.case.edu/turbogears.html  with TAGS:   webdev mochikit python web turbogears tutorial programming ");
		recs_2042.add("Conjugation Trainer - Select verbs and language  authored by   user_2033  at   http://www.vocabulix.com/online/Learn/Verbs  with TAGS:   Dictionary grammar tools language ");
		recs_2042.add("tabulator Generic data browser  authored by   user_2020  at   http://www.w3.org/2005/ajar/tab  with TAGS:   rdf browser ajax web semanticweb javascript SemanticWikis semantic ");
		recs_2042.add("Social Software Building Blocks / nForm / Customer Insight, Strategy, Design and Development  authored by   user_2025  at   http://nform.ca/publications/social-software-building-block  with TAGS:   research toread design community socialsoftware software paper social ");
		mapRecA.put("user_2042", recs_2042);
		
		Set<String> recs_2043 = new HashSet<String>();
		recs_2043.add("Wikipedia, the free encyclopedia  authored by   user_2021  at   http://en.wikipedia.org/wiki/Main_Page  with TAGS:   wiki information encyclopedia research wikipedia search reference ");
		recs_2043.add("HCI Bibliography : Human-Computer Interaction / User Interface Usability  authored by   user_2034  at   http://www.hcibib.org/  with TAGS:   interaction usability design interface bibliography reference research HCI ");
		recs_2043.add("Scholar  authored by   user_2034  at   http://scholar.google.com/  with TAGS:   search research reference academic science google literature citations scholar ");
		recs_2043.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   reference research Libraries medical government health medicine ");
		recs_2043.add("RFC Archive  authored by   user_2023  at   http://www.faqs.org/rfcs/  with TAGS:   Standards internet web reference programming developerinformation rfc ");
		mapRecA.put("user_2043", recs_2043);
		
		Set<String> recs_2044 = new HashSet<String>();
		recs_2044.add("Main Page - OpenViBEWiki  authored by   user_2043  at   http://www.irisa.fr/bunraku/OpenViBE/wiki/index.php?title=Main_Page  with TAGS:   opensource AI programming HCI university research ");
		recs_2044.add("PhD researcher Network | TENCompetence  authored by   user_2043  at   http://www.tencompetence.org/node/57  with TAGS:   professional university network PhD resources TENCompetence research ");
		recs_2044.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		recs_2044.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   social collaboration enterprise search tagging ");
		recs_2044.add("Why Johnny can't code | Lambda the Ultimate  authored by   user_2033  at   http://lambda-the-ultimate.org/node/1733#comment-21414  with TAGS:   blog discussion language games Learning kids programming ");
		mapRecA.put("user_2044", recs_2044);	

		return mapRecA;
	}	
	
	
	public static Map<String,Set<String>> loadSemRec(){
		
	Map<String,Set<String>> mapRec = new HashMap<String,Set<String>>();
	
	Set<String> recs_2000 = new HashSet<String>();
	recs_2000.add("Information and knowledge management dimensions and approaches  authored by   user_2020  at   http://informationr.net/ir/10-4/paper235.html  with TAGS:   information article km management knowledge ");
	recs_2000.add("TRIPLE-I '08  authored by   user_2013  at   http://www.triple-i.info/  with TAGS:   semanticweb graz conference congress knowledge_management media 2007 enterprise2.0 semantic_web knowledge ");
	recs_2000.add("SourceForge.net opentaps open source ERP+CRM  authored by   user_2020  at   http://sourceforge.net/projects/opentaps/  with TAGS:   management software crm freeware opensource java erp ");
	recs_2000.add("Redmine - Overview - Redmine  authored by   user_2001  at   http://www.redmine.org/  with TAGS:   opensource management rubyonrails bugtracker rails ruby project projectmanagement ");
	recs_2000.add("The 10th International Software Product Line Conference (SPLC 2006)  authored by   user_2020  at   http://www.sei.cmu.edu/splc2006/tech_program.html  with TAGS:   lines conference product software ");
	mapRec.put("user_2000", recs_2000);
	  
	Set<String> recs_2001 = new HashSet<String>();
	recs_2001.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
	recs_2001.add("Online-Journals.org  authored by   user_2043  at   http://online-journals.org/  with TAGS:   reference journals university web research online ");
	recs_2001.add("Web Design References  authored by   user_2027  at   http://www.d.umn.edu/itss/support/Training/Online/webdesign/  with TAGS:   useful programming javascript ajax webdesign howto CSS HTML reference design ");
	recs_2001.add("Encyclopedia Mythica: All mythologies.  authored by   user_2015  at   http://www.pantheon.org/areas/all/articles.html  with TAGS:   encyclopedia PersonalPages literature history mythology information religion reference ");
	recs_2001.add("Main Page - Scholarpedia  authored by   user_2015  at   http://www.scholarpedia.org/  with TAGS:   scholarpedia research wikipedia encyclopedia education wiki reference ");
	mapRec.put("user_2001", recs_2001);
	  
	Set<String> recs_2002 = new HashSet<String>();
	recs_2002.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
	recs_2002.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2002.add("http--www.microsoft.com-usability-UEPostings-p9-hanna.pdf  authored by   user_2030  at   http://www.microsoft.com/usability/UEPostings/p9-hanna.pdf  with TAGS:   HCI_artikler design children usability reference ");
	recs_2002.add("Krak.dk  authored by   user_2020  at   http://www.krak.dk/  with TAGS:   kort search map adresser denmark reference ");
	recs_2002.add("jedi: JEDI - Java Education and Development Initiative  authored by   user_2033  at   https://jedi.dev.java.net/  with TAGS:   opensource programming Java JEDI sun education tutorial ");
	mapRec.put("user_2002", recs_2002);
	  
	Set<String> recs_2003 = new HashSet<String>();
	recs_2003.add("Piled Higher and Deeper  authored by   user_2018  at   http://www.phdcomics.com/  with TAGS:   english comic fun phd ");
	recs_2003.add("Munich.com - Munich Travel, Munich Flights, Munich Hotels  authored by   user_2017  at   http://www.munich.com/  with TAGS:   hotel travel munich trip ");
	recs_2003.add("Online-Journals.org  authored by   user_2043  at   http://online-journals.org/  with TAGS:   reference journals university web research online ");
	recs_2003.add("Momondo  authored by   user_2027  at   http://momondo.com/  with TAGS:   tickets travel search airlines flights useful cheap ");
	recs_2003.add("Eskildsen  authored by   user_2010  at   http://www.eskildsen.dk/  with TAGS:   store musikinstrumenter online music denmark ");
	mapRec.put("user_2003", recs_2003);
	  
	Set<String> recs_2004 = new HashSet<String>();
	recs_2004.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2004.add("Daswetter  authored by   user_2009  at   http://www.wetteronline.de/  with TAGS:   weather wetterbericht news german deutschland fast wetter hilfsmittel_und_datenbanken ");
	recs_2004.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki python tutorial reference books programming ");
	recs_2004.add("Krak.dk  authored by   user_2020  at   http://www.krak.dk/  with TAGS:   kort search map adresser denmark reference ");
	recs_2004.add("verb2verbe - Learn the French language - French verb conjugation. Tests to learn French verbs.  authored by   user_2033  at   http://www.verb2verbe.com/  with TAGS:   french dictionary language grammar english tools reference ");
	mapRec.put("user_2004", recs_2004);
	  
	Set<String> recs_2005 = new HashSet<String>();
	recs_2005.add("The 10th International Software Product Line Conference (SPLC 2006)  authored by   user_2020  at   http://www.sei.cmu.edu/splc2006/tech_program.html  with TAGS:   lines conference product software ");
	recs_2005.add("A review of open source content management systems ? OpenAdvantage - Freedom, Choice, Control  authored by   user_2020  at   http://www.openadvantage.org/articles/oadocument.2005-04-19.0329097790  with TAGS:   webdesign opensource review cms web software ");
	recs_2005.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   search enterprise social collaboration tagging ");
	recs_2005.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   work research theory homepage software ");
	recs_2005.add("SOA Software - SOA Software Acquires LogicLibrary  authored by   user_2038  at   http://www.soa.com/index.php/section/company_press_detail/soa_software_acquires_logiclibrary  with TAGS:   logiclibrary soa governance service software ");
	mapRec.put("user_2005", recs_2005);
	  
	Set<String> recs_2006 = new HashSet<String>();
	recs_2006.add("Knowing Me, Knowing You: Anonymity Effects on Social Identity Processes within Groups -- Lea et al. 27 (5): 526 -- Personality and Social Psychology Bulletin  authored by   user_2036  at   http://psp.sagepub.com/cgi/content/refs/27/5/526  with TAGS:   SIDE Anonymity Article Social_Identity ");
	recs_2006.add("Social influence in small groups: An interactive model of social identity formation - European Review of Social Psychology  authored by   user_2036  at   http://www.informaworld.com/smpp/1218702802-25319260/content~db=all~content=a743350973~tab=references  with TAGS:   Article Social_Influence SIDE Social_Identity ");
	recs_2006.add("Anonymity Effects and Implications in the Social Identity Model of Deindividuation: From Crowd to Computer-Mediated Communication  authored by   user_2036  at   http://www.allacademic.com/meta/p_mla_apa_research_citation/1/7/3/0/0/p173006_index.html  with TAGS:   Anonymity Social_Identity Article SIDE ");
	recs_2006.add("Information and knowledge management dimensions and approaches  authored by   user_2020  at   http://informationr.net/ir/10-4/paper235.html  with TAGS:   information article km management knowledge ");
	recs_2006.add("Folksonomies: power to the people  authored by   user_2025  at   http://www-dimat.unipv.it/biblio/isko/doc/folksonomies.htm  with TAGS:   folksonomy tagcloud socialsoftware article tag tools research ");
	mapRec.put("user_2006", recs_2006);
	  
	  
	Set<String> recs_2009 = new HashSet<String>();
	recs_2009.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2009.add("http--www.microsoft.com-usability-UEPostings-p9-hanna.pdf  authored by   user_2030  at   http://www.microsoft.com/usability/UEPostings/p9-hanna.pdf  with TAGS:   HCI_artikler design children usability reference ");
	recs_2009.add("Krak.dk  authored by   user_2020  at   http://www.krak.dk/  with TAGS:   kort search map adresser denmark reference ");
	recs_2009.add("SOA Software - SOA Software Acquires LogicLibrary  authored by   user_2038  at   http://www.soa.com/index.php/section/company_press_detail/soa_software_acquires_logiclibrary  with TAGS:   logiclibrary soa governance service software ");
	recs_2009.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   research medical Libraries government health medicine reference ");
	mapRec.put("user_2009", recs_2009);
	  
	Set<String> recs_2010 = new HashSet<String>();
	recs_2010.add("Online-Journals.org  authored by   user_2043  at   http://online-journals.org/  with TAGS:   reference journals university web research online ");
	recs_2010.add("Twotone icons  authored by   user_2027  at   http://2pt3.com/news/twotone-icons-for-free/  with TAGS:   webdesign graphics useful free icons web design ");
	recs_2010.add("Google Operating System (Unofficial Google Blog)  authored by   user_2016  at   http://googlesystem.blogspot.com/  with TAGS:   technology blog google ");
	recs_2010.add("Chickipedia - The Wiki of Hot Women  authored by   user_2018  at   http://www.chickipedia.com/  with TAGS:   wiki women photos girls fun ");
	recs_2010.add("Charges.com.br  authored by   user_2005  at   http://www.charges.com.br/  with TAGS:   com�dia divers�o humor anima��es anima��o Charges ");
	mapRec.put("user_2010", recs_2010);

	Set<String> recs_2012 = new HashSet<String>();
	recs_2012.add("The 10th International Software Product Line Conference (SPLC 2006)  authored by   user_2020  at   http://www.sei.cmu.edu/splc2006/tech_program.html  with TAGS:   lines conference product software ");
	recs_2012.add("Hypertext 2009  authored by   user_2044  at   http://www.ht2009.org/  with TAGS:   tagging 2009 hypertext conference torino ");
	recs_2012.add("Adaptive Hypermedia 2008  authored by   user_2034  at   http://www.ah2008.org/index.php?section=1  with TAGS:   hypermedia ah2008 personalisation adaptive conference research ");
	recs_2012.add("Stanford Logic Group  authored by   user_2015  at   http://logic.stanford.edu/  with TAGS:   university logic AI InteligentAgents stanford ");
	recs_2012.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
	mapRec.put("user_2012", recs_2012);
	  
	Set<String> recs_2013 = new HashSet<String>();
	recs_2013.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
	recs_2013.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2013.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki python tutorial reference books programming ");
	recs_2013.add("http--www.microsoft.com-usability-UEPostings-p9-hanna.pdf  authored by   user_2030  at   http://www.microsoft.com/usability/UEPostings/p9-hanna.pdf  with TAGS:   HCI_artikler design children usability reference ");
	recs_2013.add("UNIX 101  authored by   user_2027  at   http://www.geekcomix.com/cgi-bin/classnotes/wiki.pl?UNIX01  with TAGS:   unix work linux useful tutorial reference ");
	mapRec.put("user_2013", recs_2013);
	  
	  
	Set<String> recs_2015 = new HashSet<String>();
	recs_2015.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2015.add("http--www.microsoft.com-usability-UEPostings-p9-hanna.pdf  authored by   user_2030  at   http://www.microsoft.com/usability/UEPostings/p9-hanna.pdf  with TAGS:   HCI_artikler design children usability reference ");
	recs_2015.add("Krak.dk  authored by   user_2020  at   http://www.krak.dk/  with TAGS:   kort search map adresser denmark reference ");
	recs_2015.add("Specifications  authored by   user_2001  at   http://nocash.emubase.de/gbatek.htm  with TAGS:   homebrew ds nds reference nintendo hardware gba programming ");
	recs_2015.add("Categorization - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Categorization  with TAGS:   classification philosophy categorization categories folksonomy article reference ");
	mapRec.put("user_2015", recs_2015);
	  
	Set<String> recs_2016 = new HashSet<String>();
	recs_2016.add("Preview of new Delicious features  authored by   user_2027  at   http://www.techcrunch.com/2007/09/06/exclusive-screen-shots-and-feature-overview-of-delicious-20-preview/  with TAGS:   tagging delicious del.icio.us technology preview blog design ");
	recs_2016.add("The 10th International Software Product Line Conference (SPLC 2006)  authored by   user_2020  at   http://www.sei.cmu.edu/splc2006/tech_program.html  with TAGS:   lines conference product software ");
	recs_2016.add("Michael Jackson - Published Papers  authored by   user_2020  at   http://mcs.open.ac.uk/mj665/papers.html  with TAGS:   software requirements jackson modelling design ");
	recs_2016.add("ReadWriteWeb - Web Apps, Web Technology Trends, Social Networking ...  authored by   user_2013  at   http://readwriteweb.com/  with TAGS:   news searchengine ajax socialsoftware google blog technology business ");
	recs_2016.add("E-Learn Conference  authored by   user_2043  at   http://www.aace.org/conf/ELEARN/  with TAGS:   technology e-learning 2007 conference elearning education GRAPPLE ");
	mapRec.put("user_2016", recs_2016);
	  

	  
	Set<String> recs_2018 = new HashSet<String>();
	recs_2018.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   search enterprise social collaboration tagging ");
	recs_2018.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   polls tools social fun vote blog ");
	recs_2018.add("YouTube - Newton: Web 2.0  authored by   user_2023  at   http://www.youtube.com/watch?v=sYIi-cTWOwg  with TAGS:   youtube 2.0 video del.icio.us/cenintelligence ");
	recs_2018.add("http://www.stoweboyd.com/message/2006/10/are_you_ready_f.html  authored by   user_2025  at   http://www.stoweboyd.com/message/2006/10/are_you_ready_f.html  with TAGS:   socialsoftware software definitions social socialnetworking blog ");
	recs_2018.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
	mapRec.put("user_2018", recs_2018);
	  
	  
	Set<String> recs_2020 = new HashSet<String>();
	recs_2020.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
	recs_2020.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2020.add("Online-Journals.org  authored by   user_2043  at   http://online-journals.org/  with TAGS:   reference journals university web research online ");
	recs_2020.add("C.R.U.I.S.E - Component Reuse in Software Engineering  authored by   user_2038  at   http://cruise.rise.com.br/  with TAGS:   reuse spl softwareengineering rise software cesar science cruise research ");
	recs_2020.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   tools opensource java software Testing web ");
	mapRec.put("user_2020", recs_2020);
	  
	Set<String> recs_2021 = new HashSet<String>();
	recs_2021.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
	recs_2021.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki python tutorial reference books programming ");
	recs_2021.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2021.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   research medical Libraries government health medicine reference ");
	recs_2021.add("Online-Journals.org  authored by   user_2043  at   http://online-journals.org/  with TAGS:   reference journals university web research online ");
	mapRec.put("user_2021", recs_2021);
	  
	Set<String> recs_2022 = new HashSet<String>();
	recs_2022.add("Tutorial sobre COE editor da CMAPTools  authored by   user_2025  at   http://www2.dcc.ufmg.br/laboratorios/lsi/EAD/MapasConc/TutorialCMapTools.html  with TAGS:   ontology coeedito design tutorial ");
	recs_2022.add("LibraryThing | Catalog your books online  authored by   user_2015  at   chrome://ietab/content/reloaded.html?url=http://www.librarything.com/thingology/2007/02/when-tags-works-and-when-they-dont.php  with TAGS:   folksonomy ontology books tags ");
	recs_2022.add("The Semantic Web: An Introduction  authored by   user_2017  at   http://infomesh.net/2001/swintro/  with TAGS:   semanticweb ontology semantic_web rdf xml introduction semantic web semantic-web ");
	recs_2022.add("Gnowsis - About  authored by   user_2015  at   http://www.gnowsis.org/About/index_html  with TAGS:   pim ontology SemanticWeb desktop SemanticDesktop semantic rdf ");
	recs_2022.add("Thea - [SWI] Prolog OWL library  authored by   user_2015  at   http://www.semanticweb.gr/TheaOWLLib/  with TAGS:   library prolog semanticweb development owl api ontology ");
	mapRec.put("user_2022", recs_2022);
	  
	Set<String> recs_2023 = new HashSet<String>();
	recs_2023.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
	recs_2023.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2023.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki python tutorial reference books programming ");
	recs_2023.add("http--www.microsoft.com-usability-UEPostings-p9-hanna.pdf  authored by   user_2030  at   http://www.microsoft.com/usability/UEPostings/p9-hanna.pdf  with TAGS:   HCI_artikler design children usability reference ");
	recs_2023.add("A review of open source content management systems ? OpenAdvantage - Freedom, Choice, Control  authored by   user_2020  at   http://www.openadvantage.org/articles/oadocument.2005-04-19.0329097790  with TAGS:   webdesign opensource review cms web software ");
	mapRec.put("user_2023", recs_2023);
	  
	Set<String> recs_2024 = new HashSet<String>();
	recs_2024.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2024.add("verb2verbe - Learn the French language - French verb conjugation. Tests to learn French verbs.  authored by   user_2033  at   http://www.verb2verbe.com/  with TAGS:   french dictionary language grammar english tools reference ");
	recs_2024.add("Krak.dk  authored by   user_2020  at   http://www.krak.dk/  with TAGS:   kort search map adresser denmark reference ");
	recs_2024.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   research medical Libraries government health medicine reference ");
	recs_2024.add("Online-Journals.org  authored by   user_2043  at   http://online-journals.org/  with TAGS:   reference journals university web research online ");
	mapRec.put("user_2024", recs_2024);
	  
	Set<String> recs_2025 = new HashSet<String>();
	recs_2025.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2025.add("http--www.microsoft.com-usability-UEPostings-p9-hanna.pdf  authored by   user_2030  at   http://www.microsoft.com/usability/UEPostings/p9-hanna.pdf  with TAGS:   HCI_artikler design children usability reference ");
	recs_2025.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   community social search tools ");
	recs_2025.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   search enterprise social collaboration tagging ");
	recs_2025.add("Krak.dk  authored by   user_2020  at   http://www.krak.dk/  with TAGS:   kort search map adresser denmark reference ");
	mapRec.put("user_2025", recs_2025);
	  
	Set<String> recs_2026 = new HashSet<String>();
	recs_2026.add("WinSite: Leopard  authored by   user_2033  at   http://www.winsite.com/bin/Info?19000000037359  with TAGS:   language games kids programming ");
	recs_2026.add("International standards for HCI and usability - GUILHERMO.com - Arquitetura de Informa��o  authored by   user_2025  at   http://www.guilhermo.com/ai_biblioteca/referencia.asp?referencia=127  with TAGS:   interaction information interface hci usability ");
	recs_2026.add("MapleTop Software  authored by   user_2015  at   http://www.mapletop.com/  with TAGS:   pda supermemo learning flashcard Palm education language software ");
	recs_2026.add("The Official Blitz Website  authored by   user_2033  at   http://www.blitzbasic.com/  with TAGS:   gamedev blitz basic programming development language games ");
	recs_2026.add("SoftWiki Home�Page  authored by   user_2020  at   http://softwiki.de/HomePage  with TAGS:   wiki tool requirements softwiki software semantic ");
	mapRec.put("user_2026", recs_2026);
	  
	Set<String> recs_2027 = new HashSet<String>();
	recs_2027.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki python tutorial reference books programming ");
	recs_2027.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2027.add("http--www.microsoft.com-usability-UEPostings-p9-hanna.pdf  authored by   user_2030  at   http://www.microsoft.com/usability/UEPostings/p9-hanna.pdf  with TAGS:   HCI_artikler design children usability reference ");
	recs_2027.add("Web 2.0 Directory : eConsultant  authored by   user_2023  at   http://www.econsultant.com/web2/  with TAGS:   directory 2.0 list reference ajax ");
	recs_2027.add("Krak.dk  authored by   user_2020  at   http://www.krak.dk/  with TAGS:   kort search map adresser denmark reference ");
	mapRec.put("user_2027", recs_2027);
	  
	Set<String> recs_2028 = new HashSet<String>();
	recs_2028.add("YouTube - Newton: Web 2.0  authored by   user_2023  at   http://www.youtube.com/watch?v=sYIi-cTWOwg  with TAGS:   youtube 2.0 video del.icio.us/cenintelligence ");
	recs_2028.add("HulaBoys  authored by   user_2018  at   http://www.hulaboys.de/  with TAGS:   fun blog video german ");
	recs_2028.add("YouTube - batman dead end  authored by   user_2018  at   http://de.youtube.com/watch?v=Hjp0I_okX0w  with TAGS:   video comic batman fanmovie ");
	recs_2028.add("Parody of Fall Out Boy's Sugar, we're going down video  authored by   user_2027  at   http://video.google.com/videoplay?docid=4068767512452275304&q=fall+out+boy  with TAGS:   music video fall_out_boy falloutboy funny fun ");
	recs_2028.add("TreeHugger Tip: How to Bike to Work (without all the sweat!) : TreeHugger  authored by   user_2001  at   http://www.treehugger.com/files/2008/07/treehugger_tips_bike_to_work.php  with TAGS:   bicycle bikes howto transportation ideas video cool ");
	mapRec.put("user_2028", recs_2028);
	  
	Set<String> recs_2029 = new HashSet<String>();
	recs_2029.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   search enterprise social collaboration tagging ");
	recs_2029.add("http://www.stoweboyd.com/message/2006/10/are_you_ready_f.html  authored by   user_2025  at   http://www.stoweboyd.com/message/2006/10/are_you_ready_f.html  with TAGS:   socialsoftware software definitions social socialnetworking blog ");
	recs_2029.add("Stevey's Blog Rants: Rich Programmer Food  authored by   user_2001  at   http://steve-yegge.blogspot.com/2007/06/rich-programmer-food.html?  with TAGS:   computerscience cs blog education development compilers programming ");
	recs_2029.add("The 10th International Software Product Line Conference (SPLC 2006)  authored by   user_2020  at   http://www.sei.cmu.edu/splc2006/tech_program.html  with TAGS:   lines conference product software ");
	recs_2029.add("EasyEclipse | Distributions | EasyEclipse for Python  authored by   user_2015  at   http://www.easyeclipse.org/site/distributions/python.html  with TAGS:   editor python eclipse easyeclipse ide development programming ");
	mapRec.put("user_2029", recs_2029);
	  
	Set<String> recs_2030 = new HashSet<String>();
	recs_2030.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2030.add("Krak.dk  authored by   user_2020  at   http://www.krak.dk/  with TAGS:   kort search map adresser denmark reference ");
	recs_2030.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki python tutorial reference books programming ");
	recs_2030.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   research medical Libraries government health medicine reference ");
	recs_2030.add("Online-Journals.org  authored by   user_2043  at   http://online-journals.org/  with TAGS:   reference journals university web research online ");
	mapRec.put("user_2030", recs_2030);
	  
	Set<String> recs_2031 = new HashSet<String>();
	recs_2031.add("The Social Software Weblog  authored by   user_2025  at   http://socialsoftware.weblogsinc.com/  with TAGS:   blog socialsoftware social_software social software ");
	recs_2031.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   community social search tools ");
	recs_2031.add("Life With Alacrity: Tracing the Evolution of Social Software  authored by   user_2025  at   http://www.lifewithalacrity.com/2004/10/tracing_the_evo.html  with TAGS:   socialsoftware history toread social social_software collaboration software ");
	recs_2031.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   virtual social community ");
	recs_2031.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   search enterprise social collaboration tagging ");
	mapRec.put("user_2031", recs_2031);
	  
	Set<String> recs_2032 = new HashSet<String>();
	recs_2032.add("The 10th International Software Product Line Conference (SPLC 2006)  authored by   user_2020  at   http://www.sei.cmu.edu/splc2006/tech_program.html  with TAGS:   lines conference product software ");
	recs_2032.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   web3.0 semantic software rdf ");
	recs_2032.add("Michael Jackson - Published Papers  authored by   user_2020  at   http://mcs.open.ac.uk/mj665/papers.html  with TAGS:   software requirements jackson modelling design ");
	recs_2032.add("SOA Software - SOA Software Acquires LogicLibrary  authored by   user_2038  at   http://www.soa.com/index.php/section/company_press_detail/soa_software_acquires_logiclibrary  with TAGS:   logiclibrary soa governance service software ");
	recs_2032.add("Task Coach  authored by   user_2001  at   http://www.taskcoach.org/  with TAGS:   freeware productivity taskmanager wxpython todo gtd opensource software ");
	mapRec.put("user_2032", recs_2032);
	  
	Set<String> recs_2033 = new HashSet<String>();
	recs_2033.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
	recs_2033.add("Haskell Study Plan  authored by   user_2001  at   http://www.alpheccar.org/en/posts/show/67  with TAGS:   language programming functional haskell tutorial learning ");
	recs_2033.add("MapleTop Software  authored by   user_2015  at   http://www.mapletop.com/  with TAGS:   pda supermemo learning flashcard Palm education language software ");
	recs_2033.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2033.add("LEO Deutsch-Englisches Wörterbuch  authored by   user_2023  at   http://dict.leo.org/?lang=de&lp=ende  with TAGS:   english translation Dictionary german w�rterbuch language deutsch ");
	mapRec.put("user_2033", recs_2033);
	  
	Set<String> recs_2034 = new HashSet<String>();
	recs_2034.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2034.add("Online-Journals.org  authored by   user_2043  at   http://online-journals.org/  with TAGS:   reference journals university web research online ");
	recs_2034.add("Main Page - Scholarpedia  authored by   user_2015  at   http://www.scholarpedia.org/  with TAGS:   scholarpedia research wikipedia encyclopedia education wiki reference ");
	recs_2034.add("Encyclopedia Mythica: All mythologies.  authored by   user_2015  at   http://www.pantheon.org/areas/all/articles.html  with TAGS:   encyclopedia PersonalPages literature history mythology information religion reference ");
	recs_2034.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki python tutorial reference books programming ");
	mapRec.put("user_2034", recs_2034);
	  
	  
	Set<String> recs_2036 = new HashSet<String>();
	recs_2036.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   work research theory homepage software ");
	recs_2036.add("Main Page - OpenViBEWiki  authored by   user_2043  at   http://www.irisa.fr/bunraku/OpenViBE/wiki/index.php?title=Main_Page  with TAGS:   research university HCI AI opensource programming ");
	recs_2036.add("Adaptive Hypermedia 2008  authored by   user_2034  at   http://www.ah2008.org/index.php?section=1  with TAGS:   hypermedia ah2008 personalisation adaptive conference research ");
	recs_2036.add("red-ink doctoral school  authored by   user_2043  at   http://red-inklings.blogspot.com/  with TAGS:   sharing research innovation newideas ");
	recs_2036.add("Folksonomies: power to the people  authored by   user_2025  at   http://www-dimat.unipv.it/biblio/isko/doc/folksonomies.htm  with TAGS:   folksonomy tagcloud socialsoftware article tag tools research ");
	mapRec.put("user_2036", recs_2036);
	  
	Set<String> recs_2037 = new HashSet<String>();
	recs_2037.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2037.add("http--www.microsoft.com-usability-UEPostings-p9-hanna.pdf  authored by   user_2030  at   http://www.microsoft.com/usability/UEPostings/p9-hanna.pdf  with TAGS:   HCI_artikler design children usability reference ");
	recs_2037.add("Krak.dk  authored by   user_2020  at   http://www.krak.dk/  with TAGS:   kort search map adresser denmark reference ");
	recs_2037.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki python tutorial reference books programming ");
	recs_2037.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   research medical Libraries government health medicine reference ");
	mapRec.put("user_2037", recs_2037);
	  
	Set<String> recs_2038 = new HashSet<String>();
	recs_2038.add("Nagios  authored by   user_2032  at   http://www.nagios.org/  with TAGS:   service monitoring opensource network linux software ");
	recs_2038.add("A review of open source content management systems ? OpenAdvantage - Freedom, Choice, Control  authored by   user_2020  at   http://www.openadvantage.org/articles/oadocument.2005-04-19.0329097790  with TAGS:   webdesign opensource review cms web software ");
	recs_2038.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   web3.0 semantic software rdf ");
	recs_2038.add("The 10th International Software Product Line Conference (SPLC 2006)  authored by   user_2020  at   http://www.sei.cmu.edu/splc2006/tech_program.html  with TAGS:   lines conference product software ");
	recs_2038.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   work research theory homepage software ");
	mapRec.put("user_2038", recs_2038);
	  
	Set<String> recs_2039 = new HashSet<String>();
	recs_2039.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   tools opensource java software Testing web ");
	recs_2039.add("jedi: JEDI - Java Education and Development Initiative  authored by   user_2033  at   https://jedi.dev.java.net/  with TAGS:   opensource programming Java JEDI sun education tutorial ");
	recs_2039.add("TOMCAT + HTTPS  authored by   user_2002  at   http://www.guj.com.br/posts/list/29710.java  with TAGS:   java https tomcat ssl Security ");
	recs_2039.add("UML Modeling Tool - JUDE  authored by   user_2015  at   http://jude.change-vision.com/jude-web/index.html  with TAGS:   software development design programming java tools uml ");
	recs_2039.add("Ola Bini on Java, Lisp, Ruby and AI: Code size and dynamic languages  authored by   user_2001  at   http://ola-bini.blogspot.com/2007/12/code-size-and-dynamic-languages.html  with TAGS:   code programming javascript ruby languages scala java ");
	mapRec.put("user_2039", recs_2039);
	  
	  
	Set<String> recs_2042 = new HashSet<String>();
	recs_2042.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   community social search tools ");
	recs_2042.add("The Social Software Weblog  authored by   user_2025  at   http://socialsoftware.weblogsinc.com/  with TAGS:   blog socialsoftware social_software social software ");
	recs_2042.add("Life With Alacrity: Tracing the Evolution of Social Software  authored by   user_2025  at   http://www.lifewithalacrity.com/2004/10/tracing_the_evo.html  with TAGS:   socialsoftware history toread social social_software collaboration software ");
	recs_2042.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   polls tools social fun vote blog ");
	recs_2042.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   virtual social community ");
	mapRec.put("user_2042", recs_2042);
	  
	Set<String> recs_2043 = new HashSet<String>();
	recs_2043.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   books howto reference design scheme programming ");
	recs_2043.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   research medical Libraries government health medicine reference ");
	recs_2043.add("Main Page - Scholarpedia  authored by   user_2015  at   http://www.scholarpedia.org/  with TAGS:   scholarpedia research wikipedia encyclopedia education wiki reference ");
	recs_2043.add("http--www.microsoft.com-usability-UEPostings-p9-hanna.pdf  authored by   user_2030  at   http://www.microsoft.com/usability/UEPostings/p9-hanna.pdf  with TAGS:   HCI_artikler design children usability reference ");
	recs_2043.add("HCI Bibliography : Human-Computer Interaction / User Interface Usability  authored by   user_2034  at   http://www.hcibib.org/  with TAGS:   interaction HCI research bibliography interface usability reference design ");
	mapRec.put("user_2043", recs_2043);
	  
	Set<String> recs_2044 = new HashSet<String>();
	recs_2044.add("Russell Spears  authored by   user_2036  at   http://spears.socialpsychology.org/  with TAGS:   Russell_Spears Social_Identity SIDE Research ");
	recs_2044.add("Main Page - OpenViBEWiki  authored by   user_2043  at   http://www.irisa.fr/bunraku/OpenViBE/wiki/index.php?title=Main_Page  with TAGS:   research university HCI AI opensource programming ");
	recs_2044.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   work research theory homepage software ");
	recs_2044.add("Research Tom Postmes  authored by   user_2036  at   http://psy.ex.ac.uk/~tpostmes/research.php  with TAGS:   SIDE Research Tom_Postmes Social_Identity ");
	recs_2044.add("Martin Lea (Psychological Sciences - University of Manchester)  authored by   user_2036  at   http://www.psych-sci.manchester.ac.uk/staff/MartinLea  with TAGS:   Martin_Lea Social_Identity Research SIDE ");
	mapRec.put("user_2044", recs_2044);
	
	return mapRec;
	
	}
	
	
	public static Map<String,Set<String>> loadAccepeted(){
		Map<String,Set<String>> mapRecA = new HashMap<String,Set<String>>();

		Set<String> recs_2000 = new HashSet<String>();
		recs_2000.add("download prot�g� software  authored by   user_2025  at   http://protege.stanford.edu/download/download.html  with TAGS:   ontology protege knowledge semanticweb tools ");
		//recs_2000.add("Eclipse Process Framework Project (EPF)  authored by   user_2038  at   http://www.eclipse.org/epf/  with TAGS:   softwareengineering engineering eclipse unifiedprocess xp scrum process framework tools software workflow ");
		recs_2000.add("SourceForge.net opentaps open source ERP+CRM  authored by   user_2020  at   http://sourceforge.net/projects/opentaps/  with TAGS:   software erp freeware java crm opensource management ");
		//recs_2000.add("A Course On Workflow Management  authored by   user_2020  at   http://is.tm.tue.nl/staff/wvdaalst/workflowcourse/  with TAGS:   process bpm course management workflow tutorial business ");
		//recs_2000.add("Guia: como funciona a Bolsa de Valores e como aplicar em a��es na Bovespa - UOL Economia  authored by   user_2025  at   http://economia.uol.com.br/ultnot/2008/05/14/guia_bovespa_bolsa_valores_aplicar_acoes.jhtm  with TAGS:   economia investimentos de valores bolsa finan�as bovespa ");
		mapRecA.put("user_2000", recs_2000);
		
		Set<String> recs_2001 = new HashSet<String>();
		recs_2001.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		recs_2001.add("Zimbra  authored by   user_2032  at   http://www.zimbra.com/  with TAGS:   opensource zimbra ajax collaboration linux groupware software ");
		recs_2001.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		//recs_2001.add("The QuakeForge Project: Welcome!  authored by   user_2033  at   http://www.quakeforge.net/  with TAGS:   linux 3d computer opensource games quake programming ");
		recs_2001.add("Nagios  authored by   user_2032  at   http://www.nagios.org/  with TAGS:   monitoring service network linux software opensource ");
		mapRecA.put("user_2001", recs_2001);
	
		Set<String> recs_2002 = new HashSet<String>();
		//recs_2002.add("prefuse | interactive information visualization toolkit  authored by   user_2023  at   http://prefuse.org/  with TAGS:   graph opensource software java semanticweb visualization library programming ");
		recs_2002.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		//recs_2002.add("UML Modeling Tool - JUDE  authored by   user_2015  at   http://jude.change-vision.com/jude-web/index.html  with TAGS:   software tools design development java programming uml ");
		//recs_2002.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		//recs_2002.add("Doc til Pdf converter  authored by   user_2030  at   http://www.gohtm.com/convert_pdf.asp  with TAGS:   software convert web Praktisk online_tools free pdf tools ");
		mapRecA.put("user_2002", recs_2002);
		
		Set<String> recs_2003 = new HashSet<String>();
		recs_2003.add("Aalborg - VisitAalborg - Denmark.The official guide to Aalborg with ...  authored by   user_2004  at   http://www.visitaalborg.com/international/en-gb/menu/tourist/turist-maalgruppe-forside.htm  with TAGS:   aalborg travel denmark tourism information ");
		recs_2003.add("Paul Thurrott's SuperSite for Windows: It's the future of Windows ... Today!  authored by   user_2015  at   http://www.winsupersite.com/  with TAGS:   reviews Magazines tech news microsoft blog windows software ");
		recs_2003.add("Momondo  authored by   user_2027  at   http://momondo.com/  with TAGS:   search useful flights airlines tickets cheap travel ");
		recs_2003.add("Merriam-Webster Online  authored by   user_2033  at   http://www.merriam-webster.com/  with TAGS:   english thesaurus online tools Learning language reference Dictionary ");
		//recs_2003.add("Merriam-Webster Unabridged  authored by   user_2033  at   http://unabridged.merriam-webster.com/  with TAGS:   words tools Dictionary academic reference english search language ");
		mapRecA.put("user_2003", recs_2003);
		
		Set<String> recs_2004 = new HashSet<String>();
		recs_2004.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		//recs_2004.add("Encyclopedia Mythica: All mythologies.  authored by   user_2015  at   http://www.pantheon.org/areas/all/articles.html  with TAGS:   encyclopedia mythology PersonalPages history literature religion information reference ");
		recs_2004.add("Munich.com - Munich Travel, Munich Flights, Munich Hotels  authored by   user_2017  at   http://www.munich.com/  with TAGS:   hotel munich trip travel ");
		recs_2004.add("Merriam-Webster Online  authored by   user_2033  at   http://www.merriam-webster.com/  with TAGS:   english thesaurus online tools Learning language reference Dictionary ");
		recs_2004.add("Artcyclopedia  authored by   user_2027  at   http://www.artcyclopedia.com/  with TAGS:   culture search images artists art encyclopedia reference ");
		mapRecA.put("user_2004", recs_2004);
		
		Set<String> recs_2005 = new HashSet<String>();
		recs_2005.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		recs_2005.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		recs_2005.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		recs_2005.add("OpenSocial: a converg�ncia das redes sociais | Meio Bit  authored by   user_2025  at   http://www.meiobit.com/google/opensocial_converg_ncia_das_redes_sociais  with TAGS:   social google network opensocial ");
		recs_2005.add("Social Software Building Blocks / nForm / Customer Insight, Strategy, Design and Development  authored by   user_2025  at   http://nform.ca/publications/social-software-building-block  with TAGS:   research toread design community socialsoftware software paper social ");
		mapRecA.put("user_2005", recs_2005);
		
		Set<String> recs_2006 = new HashSet<String>();
		//recs_2006.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		recs_2006.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		//recs_2006.add("Why Johnny can't code | Lambda the Ultimate  authored by   user_2033  at   http://lambda-the-ultimate.org/node/1733#comment-21414  with TAGS:   blog discussion language games Learning kids programming ");
		recs_2006.add("Paul Thurrott's SuperSite for Windows: It's the future of Windows ... Today!  authored by   user_2015  at   http://www.winsupersite.com/  with TAGS:   reviews Magazines tech news microsoft blog windows software ");
		recs_2006.add("ARTICLE: More Anecdotes About Google - Brad DeLong  authored by   user_2027  at   http://www.j-bradford-delong.net/movable_type/2004_archives/001272.html  with TAGS:   google facts article work useful search ");
		mapRecA.put("user_2006", recs_2006);
	

		Set<String> recs_2009 = new HashSet<String>();
		//recs_2009.add("Wikipedia, the free encyclopedia  authored by   user_2021  at   http://en.wikipedia.org/wiki/Main_Page  with TAGS:   wiki information encyclopedia research wikipedia search reference ");
		recs_2009.add("Artcyclopedia  authored by   user_2027  at   http://www.artcyclopedia.com/  with TAGS:   culture search images artists art encyclopedia reference ");
		//recs_2009.add("Scholar  authored by   user_2034  at   http://scholar.google.com/  with TAGS:   search research reference academic science google literature citations scholar ");
		recs_2009.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki programming books python tutorial reference ");
		recs_2009.add("Encyclopedia Mythica: All mythologies.  authored by   user_2015  at   http://www.pantheon.org/areas/all/articles.html  with TAGS:   encyclopedia mythology PersonalPages history literature religion information reference ");
		mapRecA.put("user_2009", recs_2009);
		
		Set<String> recs_2010 = new HashSet<String>();
		//recs_2010.add("Folha Online - Primeiro jornal em tempo real em l�ngua portuguesa  authored by   user_2004  at   http://www.folha.uol.com.br/  with TAGS:   folha noticias jornal brasil news not�cias jornais ");
		//recs_2010.add("Google Idol  authored by   user_2027  at   http://www.googleidol.com/  with TAGS:   funny idols humor fun video google music ");
		//recs_2010.add("Google Code for Educators - Google: MapReduce in a Week  authored by   user_2001  at   http://code.google.com/edu/content/submissions/mapreduce/listing.html  with TAGS:   course distributed tutorial google hadoop mapreduce programming ");
		//recs_2010.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   fun polls tools blog social vote ");
		recs_2010.add("English Russia � Glazastik. The New Internet Hero  authored by   user_2001  at   http://englishrussia.com/?p=1060#more-1060  with TAGS:   politics funny culture humor photoshop russia fun video ");
		mapRecA.put("user_2010", recs_2010);

		Set<String> recs_2012 = new HashSet<String>();
		//recs_2012.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		recs_2012.add("HOPL  authored by   user_2001  at   http://research.ihost.com/hopl/HOPL.html  with TAGS:   language acm programming-languages history hopl conference programming ");
		recs_2012.add("American Film Institute: Top 100 movie quotes  authored by   user_2027  at   http://www.afi.com/tvevents/100years/quotes.aspx#list  with TAGS:   list cinema fun film cool quotes movie ");
		//recs_2012.add("Adaptive Hypermedia 2008  authored by   user_2034  at   http://www.ah2008.org/index.php?section=1  with TAGS:   ah2008 personalisation research adaptive conference hypermedia ");
		recs_2012.add("PhD researcher Network | TENCompetence  authored by   user_2043  at   http://www.tencompetence.org/node/57  with TAGS:   professional university network PhD resources TENCompetence research ");
		mapRecA.put("user_2012", recs_2012);
		
		Set<String> recs_2013 = new HashSet<String>();
		recs_2013.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		//recs_2013.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		recs_2013.add("The Social Software Weblog  authored by   user_2025  at   http://socialsoftware.weblogsinc.com/  with TAGS:   socialsoftware blog social_software social software ");
		//recs_2013.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		//recs_2013.add("http://www.stoweboyd.com/message/2006/10/are_you_ready_f.html  authored by   user_2025  at   http://www.stoweboyd.com/message/2006/10/are_you_ready_f.html  with TAGS:   social socialnetworking socialsoftware blog software definitions ");
		mapRecA.put("user_2013", recs_2013);
		
		Set<String> recs_2015 = new HashSet<String>();
		recs_2015.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		recs_2015.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		//recs_2015.add("Beginning Pylons - an introduction to using the Pylons web framework  authored by   user_2001  at   http://workaround.org/pylons/beginning-pylons.html  with TAGS:   python pylons wsgi web framework programming tutorial reference ");
		recs_2015.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		recs_2015.add("Brian's TurboGears Tutorial  authored by   user_2001  at   http://exogen.case.edu/turbogears.html  with TAGS:   webdev mochikit python web turbogears tutorial programming ");
		mapRecA.put("user_2015", recs_2015);
		
		Set<String> recs_2016 = new HashSet<String>();
		recs_2016.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		//recs_2016.add("About the social semantic web  authored by   user_2013  at   http://ablvienna.wordpress.com/  with TAGS:   semantic_web blog semanticweb socialsemanticweb education socialsoftware semantic ");
		recs_2016.add("Sasha Barab  authored by   user_2020  at   http://inkido.indiana.edu/barab/index.html  with TAGS:   technology teaching education DLACIII academic research games ");
		recs_2016.add("Stevey's Blog Rants: Rich Programmer Food  authored by   user_2001  at   http://steve-yegge.blogspot.com/2007/06/rich-programmer-food.html?  with TAGS:   cs computerscience education compilers programming blog development ");
		recs_2016.add("Preview of new Delicious features  authored by   user_2027  at   http://www.techcrunch.com/2007/09/06/exclusive-screen-shots-and-feature-overview-of-delicious-20-preview/  with TAGS:   del.icio.us delicious preview blog tagging design technology ");
		mapRecA.put("user_2016", recs_2016);
		
		Set<String> recs_2017 = new HashSet<String>();
		recs_2017.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
		recs_2017.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		recs_2017.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		recs_2017.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		recs_2017.add("Thea - [SWI] Prolog OWL library  authored by   user_2015  at   http://www.semanticweb.gr/TheaOWLLib/  with TAGS:   owl api semanticweb library ontology development prolog ");
		mapRecA.put("user_2017", recs_2017);
		
		
		Set<String> recs_2018 = new HashSet<String>();
		recs_2018.add("Ehrensenf Internet TV  authored by   user_2023  at   http://www.ehrensenf.de/  with TAGS:   internet tv fernsehen video blog fun iTV german ");
		//recs_2018.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   fun polls tools blog social vote ");
		//recs_2018.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		//recs_2018.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		recs_2018.add("Google Idol  authored by   user_2027  at   http://www.googleidol.com/  with TAGS:   funny idols humor fun video google music ");
		mapRecA.put("user_2018", recs_2018);
		
		
		Set<String> recs_2019 = new HashSet<String>();
		recs_2019.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   reference research Libraries medical government health medicine ");
		recs_2019.add("tabulator Generic data browser  authored by   user_2020  at   http://www.w3.org/2005/ajar/tab  with TAGS:   rdf browser ajax web semanticweb javascript SemanticWikis semantic ");
		//recs_2019.add("RFC Archive  authored by   user_2023  at   http://www.faqs.org/rfcs/  with TAGS:   Standards internet web reference programming developerinformation rfc ");
		recs_2019.add("Semantic Book Suggestions: knowledgelounge.org  authored by   user_2023  at   http://kapustar.punkt.at/labs/knowledgelounge.org/  with TAGS:   search semanticweb book calais tools semantic programming ");
		recs_2019.add("HCIRN: Human-Computer Interaction Resource Network  authored by   user_2033  at   http://www.hcirn.com/index.php  with TAGS:   resources webdesign hci interaction articles research design jobs usability ");		
		mapRecA.put("user_2019", recs_2019);
		
		Set<String> recs_2020 = new HashSet<String>();
		recs_2020.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		recs_2020.add("Zope.org  authored by   user_2001  at   http://www.zope.org/  with TAGS:   zope python web OpenSource cms programming software ");
		recs_2020.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		recs_2020.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		recs_2020.add("Zimbra  authored by   user_2032  at   http://www.zimbra.com/  with TAGS:   opensource zimbra ajax collaboration linux groupware software ");
		mapRecA.put("user_2020", recs_2020);
		
		Set<String> recs_2021 = new HashSet<String>();
		recs_2021.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		recs_2021.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		recs_2021.add("gosu - Google Code  authored by   user_2001  at   http://code.google.com/p/gosu/  with TAGS:   gamedev games ruby programming development library ");
		recs_2021.add("The Official Blitz Website  authored by   user_2033  at   http://www.blitzbasic.com/  with TAGS:   basic games development programming language gamedev blitz ");
		recs_2021.add("Squeak: Squeak  authored by   user_2033  at   http://www.squeak.org/  with TAGS:   games kids programming opensource squeak smalltalk software development language ");
		mapRecA.put("user_2021", recs_2021);
		
		Set<String> recs_2022 = new HashSet<String>();
		//recs_2022.add("Tutorial sobre COE editor da CMAPTools  authored by   user_2025  at   http://www2.dcc.ufmg.br/laboratorios/lsi/EAD/MapasConc/TutorialCMapTools.html  with TAGS:   ontology coeedito tutorial design ");
		//recs_2022.add("Eclipse Corner Article: Eclipse Forms: New in 3.3  authored by   user_2006  at   http://www.eclipse.org/articles/article.php?file=Article-Forms33/index.html  with TAGS:   editors article forms eclipse ");
		//recs_2022.add("download prot�g� software  authored by   user_2025  at   http://protege.stanford.edu/download/download.html  with TAGS:   ontology protege knowledge semanticweb tools ");
		recs_2022.add("LibraryThing | Catalog your books online  authored by   user_2015  at   chrome://ietab/content/reloaded.html?url=http://www.librarything.com/thingology/2007/02/when-tags-works-and-when-they-dont.php  with TAGS:   folksonomy books tags ontology ");
		recs_2022.add("Thea - [SWI] Prolog OWL library  authored by   user_2015  at   http://www.semanticweb.gr/TheaOWLLib/  with TAGS:   owl api semanticweb library ontology development prolog ");
		mapRecA.put("user_2022", recs_2022);
		
		Set<String> recs_2023 = new HashSet<String>();
		recs_2023.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		//recs_2023.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		recs_2023.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		//recs_2023.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		//recs_2023.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		mapRecA.put("user_2023", recs_2023);
		
		Set<String> recs_2024 = new HashSet<String>();
		recs_2024.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		//recs_2024.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		recs_2024.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   community tools search social ");
		recs_2024.add("Flickr  authored by   user_2031  at   http://flickr.com/  with TAGS:   flickr social photo ");
		//recs_2024.add("YouTube - TV1 Trend Report  authored by   user_2025  at   http://www.youtube.com/watch?v=JMRF_ZXms9E  with TAGS:   YouTube marketing video social internet ");
		mapRecA.put("user_2024", recs_2024);
		
		Set<String> recs_2025 = new HashSet<String>();
		recs_2025.add("About the social semantic web  authored by   user_2013  at   http://ablvienna.wordpress.com/  with TAGS:   semantic_web blog semanticweb socialsemanticweb education socialsoftware semantic ");
		recs_2025.add("tabulator Generic data browser  authored by   user_2020  at   http://www.w3.org/2005/ajar/tab  with TAGS:   rdf browser ajax web semanticweb javascript SemanticWikis semantic ");
		recs_2025.add("Why Johnny can't code | Lambda the Ultimate  authored by   user_2033  at   http://lambda-the-ultimate.org/node/1733#comment-21414  with TAGS:   blog discussion language games Learning kids programming ");
		recs_2025.add("Top 5 Photoshop tutorials  authored by   user_2027  at   http://www.techeblog.com/index.php/tech-gadget/top-5-photoshop-tutorials  with TAGS:   photography tutorial graphics photoshop howto design reference ");
		recs_2025.add("Zope.org  authored by   user_2001  at   http://www.zope.org/  with TAGS:   zope python web OpenSource cms programming software ");
		mapRecA.put("user_2025", recs_2025);
		
		Set<String> recs_2026 = new HashSet<String>();
		//recs_2026.add("Guido van Robot  authored by   user_2033  at   http://gvr.sourceforge.net/about.php  with TAGS:   learning Kids python education language coding programming ");
		//recs_2026.add("Tutorial sobre COE editor da CMAPTools  authored by   user_2025  at   http://www2.dcc.ufmg.br/laboratorios/lsi/EAD/MapasConc/TutorialCMapTools.html  with TAGS:   ontology coeedito tutorial design ");
		//recs_2026.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		//recs_2026.add("ARIADNE Educational Metadata Recommendation  authored by   user_2034  at   http://www.ariadne-eu.org/3_MD/ariadne_metadata_v31.htm  with TAGS:   education object metadata learning ");
		recs_2026.add("Generic Problem-Based Learning Essentials  authored by   user_2043  at   http://pbli.org/pbl/generic_pbl.htm  with TAGS:   elearning learning problembasedlearning PBL reference education ");
		mapRecA.put("user_2026", recs_2026);
		
		Set<String> recs_2027 = new HashSet<String>();
		//recs_2027.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		//recs_2027.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		//recs_2027.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		recs_2027.add("/PSTricks/main  authored by   user_2001  at   http://tug.org/PSTricks/main.cgi/  with TAGS:   graphics tex postscript latex pstricks tutorial software reference ");
		//recs_2027.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		mapRecA.put("user_2027", recs_2027);
		
		Set<String> recs_2028 = new HashSet<String>();
		//recs_2028.add("Matrox  authored by   user_2015  at   http://www.matrox.com/  with TAGS:   Hardware graphics company computer video matrox ");
		//recs_2028.add("YouTube - batman dead end  authored by   user_2018  at   http://de.youtube.com/watch?v=Hjp0I_okX0w  with TAGS:   batman fanmovie comic video ");
		//recs_2028.add("Shirky: Ontology is Overrated -- Categories, Links, and Tags  authored by   user_2025  at   http://shirky.com/writings/ontology_overrated.html  with TAGS:   classification categorization ontology delicious folksonomy toread tagging social ");
		recs_2028.add("Google Idol  authored by   user_2027  at   http://www.googleidol.com/  with TAGS:   funny idols humor fun video google music ");
		//recs_2028.add("Google documentaire voor ICTvS&W college  authored by   user_2027  at   http://videotheek.surfnet.nl/play_proxy/mmc/24213/googleachterdeschermen.asf  with TAGS:   college work streaming useful google video ");
		mapRecA.put("user_2028", recs_2028);
		
		Set<String> recs_2029 = new HashSet<String>();
		//recs_2029.add("GameDev.net - all your game development needs  authored by   user_2021  at   http://www.gamedev.net/  with TAGS:   development tutorials games gamedev news software programming ");
		//recs_2029.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		recs_2029.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		//recs_2029.add("maemo.org - maemo.org: Maemo is the application development platform for Internet Tablets  authored by   user_2001  at   http://maemo.org/  with TAGS:   development linux mobile maemo programming nokia software ");
		//recs_2029.add("Software Engineering Institute Information Server  authored by   user_2015  at   http://www.sei.cmu.edu/  with TAGS:   development software_engineering programming SoftwareEngineering engineering sei software ");
		mapRecA.put("user_2029", recs_2029);
		
		Set<String> recs_2030 = new HashSet<String>();
		recs_2030.add("The Road to Know Where: Ultimate List of Free Windows Software from Microsoft  authored by   user_2015  at   http://bhandler.spaces.live.com/blog/cns!70F64BC910C9F7F3!1231.entry  with TAGS:   microsoft freeware utilities tools free windows software ");
		//recs_2030.add("useit.com: Jakob Nielsen on Usability and Web Design  authored by   user_2034  at   http://www.useit.com/  with TAGS:   usability ui HCI webdesign design accessibility web Neilsen reference ");
		recs_2030.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		//recs_2030.add("Merriam-Webster Online  authored by   user_2033  at   http://www.merriam-webster.com/  with TAGS:   english thesaurus online tools Learning language reference Dictionary ");
		recs_2030.add("Conjugation Trainer - Select verbs and language  authored by   user_2033  at   http://www.vocabulix.com/online/Learn/Verbs  with TAGS:   Dictionary grammar tools language ");
		mapRecA.put("user_2030", recs_2030);
		
		Set<String> recs_2031 = new HashSet<String>();
		//recs_2031.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		//recs_2031.add("The Social Software Weblog  authored by   user_2025  at   http://socialsoftware.weblogsinc.com/  with TAGS:   socialsoftware blog social_software social software ");
		recs_2031.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   social collaboration enterprise search tagging ");
		//recs_2031.add("OpenSocial: a converg�ncia das redes sociais | Meio Bit  authored by   user_2025  at   http://www.meiobit.com/google/opensocial_converg_ncia_das_redes_sociais  with TAGS:   social google network opensocial ");
		recs_2031.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   fun polls tools blog social vote ");
		mapRecA.put("user_2031", recs_2031);
		
		Set<String> recs_2032 = new HashSet<String>();
		recs_2032.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		//recs_2032.add("SOA Software - SOA Software Acquires LogicLibrary  authored by   user_2038  at   http://www.soa.com/index.php/section/company_press_detail/soa_software_acquires_logiclibrary  with TAGS:   software logiclibrary service governance soa ");
		recs_2032.add("The QuakeForge Project: Welcome!  authored by   user_2033  at   http://www.quakeforge.net/  with TAGS:   linux 3d computer opensource games quake programming ");
		//recs_2032.add("prefuse | interactive information visualization toolkit  authored by   user_2023  at   http://prefuse.org/  with TAGS:   graph opensource software java semanticweb visualization library programming ");
		recs_2032.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		mapRecA.put("user_2032", recs_2032);
		
		Set<String> recs_2033 = new HashSet<String>();
		recs_2033.add("Haskell Study Plan  authored by   user_2001  at   http://www.alpheccar.org/en/posts/show/67  with TAGS:   tutorial language haskell functional learning programming ");
		recs_2033.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		recs_2033.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki programming books python tutorial reference ");
		recs_2033.add("Home page for Heuristics interactivity  authored by   user_2015  at   http://www.id-book.com/catherb/index.htm  with TAGS:   tools hci design interaction usability heuristics ");
		recs_2033.add("GameDev.net - all your game development needs  authored by   user_2021  at   http://www.gamedev.net/  with TAGS:   development tutorials games gamedev news software programming ");
		mapRecA.put("user_2033", recs_2033);
		
		Set<String> recs_2034 = new HashSet<String>();
		//recs_2034.add("Learner Modelling for Reflection  authored by   user_2043  at   http://www.eee.bham.ac.uk/bull/lemore/  with TAGS:   research OpenLearnerModel elearning reference ");
		recs_2034.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2034.add("Home page for Heuristics interactivity  authored by   user_2015  at   http://www.id-book.com/catherb/index.htm  with TAGS:   tools hci design interaction usability heuristics ");
		recs_2034.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		//recs_2034.add("International standards for HCI and usability - GUILHERMO.com - Arquitetura de Informa��o  authored by   user_2025  at   http://www.guilhermo.com/ai_biblioteca/referencia.asp?referencia=127  with TAGS:   interaction usability information hci interface ");
		mapRecA.put("user_2034", recs_2034);
		
		
		Set<String> recs_2036 = new HashSet<String>();
		//recs_2036.add("Social Software Building Blocks / nForm / Customer Insight, Strategy, Design and Development  authored by   user_2025  at   http://nform.ca/publications/social-software-building-block  with TAGS:   software community design socialsoftware paper research toread social ");
		//recs_2036.add("PhD researcher Network | TENCompetence  authored by   user_2043  at   http://www.tencompetence.org/node/57  with TAGS:   professional university network PhD resources TENCompetence research ");
		//recs_2036.add("HCIRN: Human-Computer Interaction Resource Network  authored by   user_2033  at   http://www.hcirn.com/index.php  with TAGS:   resources webdesign hci interaction articles research design jobs usability ");
		//recs_2036.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		recs_2036.add("The Social Software Weblog  authored by   user_2025  at   http://socialsoftware.weblogsinc.com/  with TAGS:   socialsoftware blog social_software social software ");		
		mapRecA.put("user_2036", recs_2036);	

		Set<String> recs_2037 = new HashSet<String>();
		recs_2037.add("Politiken.dk - det levende net  authored by   user_2003  at   http://politiken.dk/newsinenglish  with TAGS:   politiken english denmark news ");
		recs_2037.add("Google  authored by   user_2009  at   http://www.google.com/  with TAGS:   engine searchengine reference search web everything doubts google ");
		recs_2037.add("Learner Modelling for Reflection  authored by   user_2043  at   http://www.eee.bham.ac.uk/bull/lemore/  with TAGS:   research OpenLearnerModel elearning reference ");
		recs_2037.add("Scholar  authored by   user_2034  at   http://scholar.google.com/  with TAGS:   search research reference academic science google literature citations scholar ");
		recs_2037.add("Watch Movies Online For Free Full Movie Downloads  authored by   user_2005  at   http://www.watch-movies.net/  with TAGS:   online television movies free tv streaming film community video ");
		mapRecA.put("user_2037", recs_2037);
		
		Set<String> recs_2038 = new HashSet<String>();
		//recs_2038.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		//recs_2038.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		recs_2038.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		//recs_2038.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
		//recs_2038.add("Auto-vectorization in GCC - GNU Project - Free Software Foundation (FSF)  authored by   user_2001  at   http://gcc.gnu.org/projects/tree-ssa/vectorization.html  with TAGS:   gcc software vectorization compiler optimization programming ");
		mapRecA.put("user_2038", recs_2038);
		
		Set<String> recs_2039 = new HashSet<String>();
		recs_2039.add("Brian's TurboGears Tutorial  authored by   user_2001  at   http://exogen.case.edu/turbogears.html  with TAGS:   webdev mochikit python web turbogears tutorial programming ");
		recs_2039.add("DevCentral Home  authored by   user_2015  at   http://devcentral.iticentral.com/  with TAGS:   java asp c++ tutorial programming resources Windows ");
		recs_2039.add("The future of PHP  authored by   user_2043  at   http://www.ibm.com/developerworks/opensource/library/os-php-future/?ca=dgr-lnxw01PHP-Future  with TAGS:   scripting PHP programming development webdev webdevelopment web ");
		recs_2039.add("Ola Bini on Java, Lisp, Ruby and AI: Code size and dynamic languages  authored by   user_2001  at   http://ola-bini.blogspot.com/2007/12/code-size-and-dynamic-languages.html  with TAGS:   ruby javascript scala java languages code programming ");
		recs_2039.add("TOMCAT + HTTPS  authored by   user_2002  at   http://www.guj.com.br/posts/list/29710.java  with TAGS:   ssl java Security tomcat https ");
		mapRecA.put("user_2039", recs_2039);

		Set<String> recs_2042 = new HashSet<String>();
		recs_2042.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		recs_2042.add("Brian's TurboGears Tutorial  authored by   user_2001  at   http://exogen.case.edu/turbogears.html  with TAGS:   webdev mochikit python web turbogears tutorial programming ");
		recs_2042.add("Conjugation Trainer - Select verbs and language  authored by   user_2033  at   http://www.vocabulix.com/online/Learn/Verbs  with TAGS:   Dictionary grammar tools language ");
		//recs_2042.add("tabulator Generic data browser  authored by   user_2020  at   http://www.w3.org/2005/ajar/tab  with TAGS:   rdf browser ajax web semanticweb javascript SemanticWikis semantic ");
		//recs_2042.add("Social Software Building Blocks / nForm / Customer Insight, Strategy, Design and Development  authored by   user_2025  at   http://nform.ca/publications/social-software-building-block  with TAGS:   research toread design community socialsoftware software paper social ");
		mapRecA.put("user_2042", recs_2042);
		
		Set<String> recs_2043 = new HashSet<String>();
		//recs_2043.add("Wikipedia, the free encyclopedia  authored by   user_2021  at   http://en.wikipedia.org/wiki/Main_Page  with TAGS:   wiki information encyclopedia research wikipedia search reference ");
		recs_2043.add("HCI Bibliography : Human-Computer Interaction / User Interface Usability  authored by   user_2034  at   http://www.hcibib.org/  with TAGS:   interaction usability design interface bibliography reference research HCI ");
		//recs_2043.add("Scholar  authored by   user_2034  at   http://scholar.google.com/  with TAGS:   search research reference academic science google literature citations scholar ");
		recs_2043.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   reference research Libraries medical government health medicine ");
		recs_2043.add("RFC Archive  authored by   user_2023  at   http://www.faqs.org/rfcs/  with TAGS:   Standards internet web reference programming developerinformation rfc ");
		mapRecA.put("user_2043", recs_2043);
		
		Set<String> recs_2044 = new HashSet<String>();
		//recs_2044.add("Main Page - OpenViBEWiki  authored by   user_2043  at   http://www.irisa.fr/bunraku/OpenViBE/wiki/index.php?title=Main_Page  with TAGS:   opensource AI programming HCI university research ");
		//recs_2044.add("PhD researcher Network | TENCompetence  authored by   user_2043  at   http://www.tencompetence.org/node/57  with TAGS:   professional university network PhD resources TENCompetence research ");
		recs_2044.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		recs_2044.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   social collaboration enterprise search tagging ");
		//recs_2044.add("Why Johnny can't code | Lambda the Ultimate  authored by   user_2033  at   http://lambda-the-ultimate.org/node/1733#comment-21414  with TAGS:   blog discussion language games Learning kids programming ");
		mapRecA.put("user_2044", recs_2044);	

		return mapRecA;
	}	
	
	
	public static Map<String,Set<String>> loadRejected(){
		Map<String,Set<String>> mapRecA = new HashMap<String,Set<String>>();

		Set<String> recs_2000 = new HashSet<String>();
		//recs_2000.add("download prot�g� software  authored by   user_2025  at   http://protege.stanford.edu/download/download.html  with TAGS:   ontology protege knowledge semanticweb tools ");
		recs_2000.add("Eclipse Process Framework Project (EPF)  authored by   user_2038  at   http://www.eclipse.org/epf/  with TAGS:   softwareengineering engineering eclipse unifiedprocess xp scrum process framework tools software workflow ");
		//recs_2000.add("SourceForge.net opentaps open source ERP+CRM  authored by   user_2020  at   http://sourceforge.net/projects/opentaps/  with TAGS:   software erp freeware java crm opensource management ");
		recs_2000.add("A Course On Workflow Management  authored by   user_2020  at   http://is.tm.tue.nl/staff/wvdaalst/workflowcourse/  with TAGS:   process bpm course management workflow tutorial business ");
		recs_2000.add("Guia: como funciona a Bolsa de Valores e como aplicar em a��es na Bovespa - UOL Economia  authored by   user_2025  at   http://economia.uol.com.br/ultnot/2008/05/14/guia_bovespa_bolsa_valores_aplicar_acoes.jhtm  with TAGS:   economia investimentos de valores bolsa finan�as bovespa ");
		mapRecA.put("user_2000", recs_2000);
		
		Set<String> recs_2001 = new HashSet<String>();
		//recs_2001.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		//recs_2001.add("Zimbra  authored by   user_2032  at   http://www.zimbra.com/  with TAGS:   opensource zimbra ajax collaboration linux groupware software ");
		//recs_2001.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		recs_2001.add("The QuakeForge Project: Welcome!  authored by   user_2033  at   http://www.quakeforge.net/  with TAGS:   linux 3d computer opensource games quake programming ");
		//recs_2001.add("Nagios  authored by   user_2032  at   http://www.nagios.org/  with TAGS:   monitoring service network linux software opensource ");
		mapRecA.put("user_2001", recs_2001);
		
		Set<String> recs_2002 = new HashSet<String>();
		recs_2002.add("prefuse | interactive information visualization toolkit  authored by   user_2023  at   http://prefuse.org/  with TAGS:   graph opensource software java semanticweb visualization library programming ");
		//recs_2002.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		recs_2002.add("UML Modeling Tool - JUDE  authored by   user_2015  at   http://jude.change-vision.com/jude-web/index.html  with TAGS:   software tools design development java programming uml ");
		recs_2002.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		recs_2002.add("Doc til Pdf converter  authored by   user_2030  at   http://www.gohtm.com/convert_pdf.asp  with TAGS:   software convert web Praktisk online_tools free pdf tools ");
		mapRecA.put("user_2002", recs_2002);
		
		Set<String> recs_2003 = new HashSet<String>();
		//recs_2003.add("Aalborg - VisitAalborg - Denmark.The official guide to Aalborg with ...  authored by   user_2004  at   http://www.visitaalborg.com/international/en-gb/menu/tourist/turist-maalgruppe-forside.htm  with TAGS:   aalborg travel denmark tourism information ");
		//recs_2003.add("Paul Thurrott's SuperSite for Windows: It's the future of Windows ... Today!  authored by   user_2015  at   http://www.winsupersite.com/  with TAGS:   reviews Magazines tech news microsoft blog windows software ");
		//recs_2003.add("Momondo  authored by   user_2027  at   http://momondo.com/  with TAGS:   search useful flights airlines tickets cheap travel ");
		//recs_2003.add("Merriam-Webster Online  authored by   user_2033  at   http://www.merriam-webster.com/  with TAGS:   english thesaurus online tools Learning language reference Dictionary ");
		recs_2003.add("Merriam-Webster Unabridged  authored by   user_2033  at   http://unabridged.merriam-webster.com/  with TAGS:   words tools Dictionary academic reference english search language ");
		mapRecA.put("user_2003", recs_2003);
		
		Set<String> recs_2004 = new HashSet<String>();
		//recs_2004.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2004.add("Encyclopedia Mythica: All mythologies.  authored by   user_2015  at   http://www.pantheon.org/areas/all/articles.html  with TAGS:   encyclopedia mythology PersonalPages history literature religion information reference ");
		//recs_2004.add("Munich.com - Munich Travel, Munich Flights, Munich Hotels  authored by   user_2017  at   http://www.munich.com/  with TAGS:   hotel munich trip travel ");
		//recs_2004.add("Merriam-Webster Online  authored by   user_2033  at   http://www.merriam-webster.com/  with TAGS:   english thesaurus online tools Learning language reference Dictionary ");
		//recs_2004.add("Artcyclopedia  authored by   user_2027  at   http://www.artcyclopedia.com/  with TAGS:   culture search images artists art encyclopedia reference ");
		mapRecA.put("user_2004", recs_2004);
		
		Set<String> recs_2005 = new HashSet<String>();
		//recs_2005.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		//recs_2005.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		//recs_2005.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		//recs_2005.add("OpenSocial: a converg�ncia das redes sociais | Meio Bit  authored by   user_2025  at   http://www.meiobit.com/google/opensocial_converg_ncia_das_redes_sociais  with TAGS:   social google network opensocial ");
		//recs_2005.add("Social Software Building Blocks / nForm / Customer Insight, Strategy, Design and Development  authored by   user_2025  at   http://nform.ca/publications/social-software-building-block  with TAGS:   research toread design community socialsoftware software paper social ");
		mapRecA.put("user_2005", recs_2005);
		
		Set<String> recs_2006 = new HashSet<String>();
		recs_2006.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		//recs_2006.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		recs_2006.add("Why Johnny can't code | Lambda the Ultimate  authored by   user_2033  at   http://lambda-the-ultimate.org/node/1733#comment-21414  with TAGS:   blog discussion language games Learning kids programming ");
		//recs_2006.add("Paul Thurrott's SuperSite for Windows: It's the future of Windows ... Today!  authored by   user_2015  at   http://www.winsupersite.com/  with TAGS:   reviews Magazines tech news microsoft blog windows software ");
		//recs_2006.add("ARTICLE: More Anecdotes About Google - Brad DeLong  authored by   user_2027  at   http://www.j-bradford-delong.net/movable_type/2004_archives/001272.html  with TAGS:   google facts article work useful search ");
		mapRecA.put("user_2006", recs_2006);
	

		Set<String> recs_2009 = new HashSet<String>();
		recs_2009.add("Wikipedia, the free encyclopedia  authored by   user_2021  at   http://en.wikipedia.org/wiki/Main_Page  with TAGS:   wiki information encyclopedia research wikipedia search reference ");
		//recs_2009.add("Artcyclopedia  authored by   user_2027  at   http://www.artcyclopedia.com/  with TAGS:   culture search images artists art encyclopedia reference ");
		recs_2009.add("Scholar  authored by   user_2034  at   http://scholar.google.com/  with TAGS:   search research reference academic science google literature citations scholar ");
		//recs_2009.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki programming books python tutorial reference ");
		//recs_2009.add("Encyclopedia Mythica: All mythologies.  authored by   user_2015  at   http://www.pantheon.org/areas/all/articles.html  with TAGS:   encyclopedia mythology PersonalPages history literature religion information reference ");
		mapRecA.put("user_2009", recs_2009);
		
		Set<String> recs_2010 = new HashSet<String>();
		recs_2010.add("Folha Online - Primeiro jornal em tempo real em l�ngua portuguesa  authored by   user_2004  at   http://www.folha.uol.com.br/  with TAGS:   folha noticias jornal brasil news not�cias jornais ");
		recs_2010.add("Google Idol  authored by   user_2027  at   http://www.googleidol.com/  with TAGS:   funny idols humor fun video google music ");
		recs_2010.add("Google Code for Educators - Google: MapReduce in a Week  authored by   user_2001  at   http://code.google.com/edu/content/submissions/mapreduce/listing.html  with TAGS:   course distributed tutorial google hadoop mapreduce programming ");
		recs_2010.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   fun polls tools blog social vote ");
		//recs_2010.add("English Russia � Glazastik. The New Internet Hero  authored by   user_2001  at   http://englishrussia.com/?p=1060#more-1060  with TAGS:   politics funny culture humor photoshop russia fun video ");
		mapRecA.put("user_2010", recs_2010);

		Set<String> recs_2012 = new HashSet<String>();
		recs_2012.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		//recs_2012.add("HOPL  authored by   user_2001  at   http://research.ihost.com/hopl/HOPL.html  with TAGS:   language acm programming-languages history hopl conference programming ");
		//recs_2012.add("American Film Institute: Top 100 movie quotes  authored by   user_2027  at   http://www.afi.com/tvevents/100years/quotes.aspx#list  with TAGS:   list cinema fun film cool quotes movie ");
		recs_2012.add("Adaptive Hypermedia 2008  authored by   user_2034  at   http://www.ah2008.org/index.php?section=1  with TAGS:   ah2008 personalisation research adaptive conference hypermedia ");
		//recs_2012.add("PhD researcher Network | TENCompetence  authored by   user_2043  at   http://www.tencompetence.org/node/57  with TAGS:   professional university network PhD resources TENCompetence research ");
		mapRecA.put("user_2012", recs_2012);
		
		Set<String> recs_2013 = new HashSet<String>();
		//recs_2013.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		recs_2013.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		//recs_2013.add("The Social Software Weblog  authored by   user_2025  at   http://socialsoftware.weblogsinc.com/  with TAGS:   socialsoftware blog social_software social software ");
		recs_2013.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		recs_2013.add("http://www.stoweboyd.com/message/2006/10/are_you_ready_f.html  authored by   user_2025  at   http://www.stoweboyd.com/message/2006/10/are_you_ready_f.html  with TAGS:   social socialnetworking socialsoftware blog software definitions ");
		mapRecA.put("user_2013", recs_2013);
		
		Set<String> recs_2015 = new HashSet<String>();
		//recs_2015.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		//recs_2015.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2015.add("Beginning Pylons - an introduction to using the Pylons web framework  authored by   user_2001  at   http://workaround.org/pylons/beginning-pylons.html  with TAGS:   python pylons wsgi web framework programming tutorial reference ");
		//recs_2015.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		//recs_2015.add("Brian's TurboGears Tutorial  authored by   user_2001  at   http://exogen.case.edu/turbogears.html  with TAGS:   webdev mochikit python web turbogears tutorial programming ");
		mapRecA.put("user_2015", recs_2015);
		
		Set<String> recs_2016 = new HashSet<String>();
		//recs_2016.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		recs_2016.add("About the social semantic web  authored by   user_2013  at   http://ablvienna.wordpress.com/  with TAGS:   semantic_web blog semanticweb socialsemanticweb education socialsoftware semantic ");
		//recs_2016.add("Sasha Barab  authored by   user_2020  at   http://inkido.indiana.edu/barab/index.html  with TAGS:   technology teaching education DLACIII academic research games ");
		//recs_2016.add("Stevey's Blog Rants: Rich Programmer Food  authored by   user_2001  at   http://steve-yegge.blogspot.com/2007/06/rich-programmer-food.html?  with TAGS:   cs computerscience education compilers programming blog development ");
		//recs_2016.add("Preview of new Delicious features  authored by   user_2027  at   http://www.techcrunch.com/2007/09/06/exclusive-screen-shots-and-feature-overview-of-delicious-20-preview/  with TAGS:   del.icio.us delicious preview blog tagging design technology ");
		mapRecA.put("user_2016", recs_2016);
		
		Set<String> recs_2017 = new HashSet<String>();
		//recs_2017.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
		//recs_2017.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		//recs_2017.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		//recs_2017.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		//recs_2017.add("Thea - [SWI] Prolog OWL library  authored by   user_2015  at   http://www.semanticweb.gr/TheaOWLLib/  with TAGS:   owl api semanticweb library ontology development prolog ");
		mapRecA.put("user_2017", recs_2017);
		
		
		Set<String> recs_2018 = new HashSet<String>();
		//recs_2018.add("Ehrensenf Internet TV  authored by   user_2023  at   http://www.ehrensenf.de/  with TAGS:   internet tv fernsehen video blog fun iTV german ");
		recs_2018.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   fun polls tools blog social vote ");
		recs_2018.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		recs_2018.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		//recs_2018.add("Google Idol  authored by   user_2027  at   http://www.googleidol.com/  with TAGS:   funny idols humor fun video google music ");
		mapRecA.put("user_2018", recs_2018);
		
		
		Set<String> recs_2019 = new HashSet<String>();
		//recs_2019.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   reference research Libraries medical government health medicine ");
		//recs_2019.add("tabulator Generic data browser  authored by   user_2020  at   http://www.w3.org/2005/ajar/tab  with TAGS:   rdf browser ajax web semanticweb javascript SemanticWikis semantic ");
		recs_2019.add("RFC Archive  authored by   user_2023  at   http://www.faqs.org/rfcs/  with TAGS:   Standards internet web reference programming developerinformation rfc ");
		//recs_2019.add("Semantic Book Suggestions: knowledgelounge.org  authored by   user_2023  at   http://kapustar.punkt.at/labs/knowledgelounge.org/  with TAGS:   search semanticweb book calais tools semantic programming ");
		//recs_2019.add("HCIRN: Human-Computer Interaction Resource Network  authored by   user_2033  at   http://www.hcirn.com/index.php  with TAGS:   resources webdesign hci interaction articles research design jobs usability ");		
		mapRecA.put("user_2019", recs_2019);
		
		Set<String> recs_2020 = new HashSet<String>();
		//recs_2020.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		//recs_2020.add("Zope.org  authored by   user_2001  at   http://www.zope.org/  with TAGS:   zope python web OpenSource cms programming software ");
		//recs_2020.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		//recs_2020.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		//recs_2020.add("Zimbra  authored by   user_2032  at   http://www.zimbra.com/  with TAGS:   opensource zimbra ajax collaboration linux groupware software ");
		mapRecA.put("user_2020", recs_2020);
		
		Set<String> recs_2021 = new HashSet<String>();
		//recs_2021.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		//recs_2021.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		//recs_2021.add("gosu - Google Code  authored by   user_2001  at   http://code.google.com/p/gosu/  with TAGS:   gamedev games ruby programming development library ");
		//recs_2021.add("The Official Blitz Website  authored by   user_2033  at   http://www.blitzbasic.com/  with TAGS:   basic games development programming language gamedev blitz ");
		//recs_2021.add("Squeak: Squeak  authored by   user_2033  at   http://www.squeak.org/  with TAGS:   games kids programming opensource squeak smalltalk software development language ");
		mapRecA.put("user_2021", recs_2021);
		
		Set<String> recs_2022 = new HashSet<String>();
		recs_2022.add("Tutorial sobre COE editor da CMAPTools  authored by   user_2025  at   http://www2.dcc.ufmg.br/laboratorios/lsi/EAD/MapasConc/TutorialCMapTools.html  with TAGS:   ontology coeedito tutorial design ");
		recs_2022.add("Eclipse Corner Article: Eclipse Forms: New in 3.3  authored by   user_2006  at   http://www.eclipse.org/articles/article.php?file=Article-Forms33/index.html  with TAGS:   editors article forms eclipse ");
		recs_2022.add("download prot�g� software  authored by   user_2025  at   http://protege.stanford.edu/download/download.html  with TAGS:   ontology protege knowledge semanticweb tools ");
		//recs_2022.add("LibraryThing | Catalog your books online  authored by   user_2015  at   chrome://ietab/content/reloaded.html?url=http://www.librarything.com/thingology/2007/02/when-tags-works-and-when-they-dont.php  with TAGS:   folksonomy books tags ontology ");
		//recs_2022.add("Thea - [SWI] Prolog OWL library  authored by   user_2015  at   http://www.semanticweb.gr/TheaOWLLib/  with TAGS:   owl api semanticweb library ontology development prolog ");
		mapRecA.put("user_2022", recs_2022);
		
		Set<String> recs_2023 = new HashSet<String>();
		//recs_2023.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		recs_2023.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		//recs_2023.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2023.add("Open Source Web Testing Tools in Java  authored by   user_2002  at   http://java-source.net/open-source/web-testing-tools  with TAGS:   java tools Testing web opensource software ");
		recs_2023.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		mapRecA.put("user_2023", recs_2023);
		
		Set<String> recs_2024 = new HashSet<String>();
		//recs_2024.add("http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  authored by   user_2025  at   http://www.semanticfocus.com/blog/entry/title/some-truth-about-the-semantic-web/  with TAGS:   semweb discussion semantic blog semanticweb web ");
		recs_2024.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		//recs_2024.add("StumbleUpon  authored by   user_2023  at   http://www.stumbleupon.com/  with TAGS:   community tools search social ");
		//recs_2024.add("Flickr  authored by   user_2031  at   http://flickr.com/  with TAGS:   flickr social photo ");
		recs_2024.add("YouTube - TV1 Trend Report  authored by   user_2025  at   http://www.youtube.com/watch?v=JMRF_ZXms9E  with TAGS:   YouTube marketing video social internet ");
		mapRecA.put("user_2024", recs_2024);
		
		Set<String> recs_2025 = new HashSet<String>();
		//recs_2025.add("About the social semantic web  authored by   user_2013  at   http://ablvienna.wordpress.com/  with TAGS:   semantic_web blog semanticweb socialsemanticweb education socialsoftware semantic ");
		//recs_2025.add("tabulator Generic data browser  authored by   user_2020  at   http://www.w3.org/2005/ajar/tab  with TAGS:   rdf browser ajax web semanticweb javascript SemanticWikis semantic ");
		//recs_2025.add("Why Johnny can't code | Lambda the Ultimate  authored by   user_2033  at   http://lambda-the-ultimate.org/node/1733#comment-21414  with TAGS:   blog discussion language games Learning kids programming ");
		//recs_2025.add("Top 5 Photoshop tutorials  authored by   user_2027  at   http://www.techeblog.com/index.php/tech-gadget/top-5-photoshop-tutorials  with TAGS:   photography tutorial graphics photoshop howto design reference ");
		//recs_2025.add("Zope.org  authored by   user_2001  at   http://www.zope.org/  with TAGS:   zope python web OpenSource cms programming software ");
		mapRecA.put("user_2025", recs_2025);
		
		Set<String> recs_2026 = new HashSet<String>();
		recs_2026.add("Guido van Robot  authored by   user_2033  at   http://gvr.sourceforge.net/about.php  with TAGS:   learning Kids python education language coding programming ");
		recs_2026.add("Tutorial sobre COE editor da CMAPTools  authored by   user_2025  at   http://www2.dcc.ufmg.br/laboratorios/lsi/EAD/MapasConc/TutorialCMapTools.html  with TAGS:   ontology coeedito tutorial design ");
		recs_2026.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		recs_2026.add("ARIADNE Educational Metadata Recommendation  authored by   user_2034  at   http://www.ariadne-eu.org/3_MD/ariadne_metadata_v31.htm  with TAGS:   education object metadata learning ");
		//recs_2026.add("Generic Problem-Based Learning Essentials  authored by   user_2043  at   http://pbli.org/pbl/generic_pbl.htm  with TAGS:   elearning learning problembasedlearning PBL reference education ");
		mapRecA.put("user_2026", recs_2026);
		
		Set<String> recs_2027 = new HashSet<String>();
		recs_2027.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2027.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		recs_2027.add("Everything is Miscellaneous  authored by   user_2044  at   http://www.everythingismiscellaneous.com/  with TAGS:   weinberger tagging blog ");
		//recs_2027.add("/PSTricks/main  authored by   user_2001  at   http://tug.org/PSTricks/main.cgi/  with TAGS:   graphics tex postscript latex pstricks tutorial software reference ");
		recs_2027.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		mapRecA.put("user_2027", recs_2027);
		
		Set<String> recs_2028 = new HashSet<String>();
		recs_2028.add("Matrox  authored by   user_2015  at   http://www.matrox.com/  with TAGS:   Hardware graphics company computer video matrox ");
		recs_2028.add("YouTube - batman dead end  authored by   user_2018  at   http://de.youtube.com/watch?v=Hjp0I_okX0w  with TAGS:   batman fanmovie comic video ");
		recs_2028.add("Shirky: Ontology is Overrated -- Categories, Links, and Tags  authored by   user_2025  at   http://shirky.com/writings/ontology_overrated.html  with TAGS:   classification categorization ontology delicious folksonomy toread tagging social ");
		//recs_2028.add("Google Idol  authored by   user_2027  at   http://www.googleidol.com/  with TAGS:   funny idols humor fun video google music ");
		recs_2028.add("Google documentaire voor ICTvS&W college  authored by   user_2027  at   http://videotheek.surfnet.nl/play_proxy/mmc/24213/googleachterdeschermen.asf  with TAGS:   college work streaming useful google video ");
		mapRecA.put("user_2028", recs_2028);
		
		Set<String> recs_2029 = new HashSet<String>();
		recs_2029.add("GameDev.net - all your game development needs  authored by   user_2021  at   http://www.gamedev.net/  with TAGS:   development tutorials games gamedev news software programming ");
		recs_2029.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		//recs_2029.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		recs_2029.add("maemo.org - maemo.org: Maemo is the application development platform for Internet Tablets  authored by   user_2001  at   http://maemo.org/  with TAGS:   development linux mobile maemo programming nokia software ");
		recs_2029.add("Software Engineering Institute Information Server  authored by   user_2015  at   http://www.sei.cmu.edu/  with TAGS:   development software_engineering programming SoftwareEngineering engineering sei software ");
		mapRecA.put("user_2029", recs_2029);
		
		Set<String> recs_2030 = new HashSet<String>();
		//recs_2030.add("The Road to Know Where: Ultimate List of Free Windows Software from Microsoft  authored by   user_2015  at   http://bhandler.spaces.live.com/blog/cns!70F64BC910C9F7F3!1231.entry  with TAGS:   microsoft freeware utilities tools free windows software ");
		recs_2030.add("useit.com: Jakob Nielsen on Usability and Web Design  authored by   user_2034  at   http://www.useit.com/  with TAGS:   usability ui HCI webdesign design accessibility web Neilsen reference ");
		//recs_2030.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		recs_2030.add("Merriam-Webster Online  authored by   user_2033  at   http://www.merriam-webster.com/  with TAGS:   english thesaurus online tools Learning language reference Dictionary ");
		//recs_2030.add("Conjugation Trainer - Select verbs and language  authored by   user_2033  at   http://www.vocabulix.com/online/Learn/Verbs  with TAGS:   Dictionary grammar tools language ");
		mapRecA.put("user_2030", recs_2030);
		
		Set<String> recs_2031 = new HashSet<String>();
		recs_2031.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		recs_2031.add("The Social Software Weblog  authored by   user_2025  at   http://socialsoftware.weblogsinc.com/  with TAGS:   socialsoftware blog social_software social software ");
		//recs_2031.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   social collaboration enterprise search tagging ");
		recs_2031.add("OpenSocial: a converg�ncia das redes sociais | Meio Bit  authored by   user_2025  at   http://www.meiobit.com/google/opensocial_converg_ncia_das_redes_sociais  with TAGS:   social google network opensocial ");
		//recs_2031.add("Buzzvote - Do you work out? - yes or no  authored by   user_2023  at   http://www.buzzvote.com/  with TAGS:   fun polls tools blog social vote ");
		mapRecA.put("user_2031", recs_2031);
		
		Set<String> recs_2032 = new HashSet<String>();
		//recs_2032.add("Marc Liyanage - Software - Mac OS X Packages  authored by   user_2023  at   http://www.entropy.ch/software/macosx/welcome.html  with TAGS:   osx mysql mac php apple opensource software ");
		recs_2032.add("SOA Software - SOA Software Acquires LogicLibrary  authored by   user_2038  at   http://www.soa.com/index.php/section/company_press_detail/soa_software_acquires_logiclibrary  with TAGS:   software logiclibrary service governance soa ");
		//recs_2032.add("The QuakeForge Project: Welcome!  authored by   user_2033  at   http://www.quakeforge.net/  with TAGS:   linux 3d computer opensource games quake programming ");
		recs_2032.add("prefuse | interactive information visualization toolkit  authored by   user_2023  at   http://prefuse.org/  with TAGS:   graph opensource software java semanticweb visualization library programming ");
		//recs_2032.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		mapRecA.put("user_2032", recs_2032);
		
		Set<String> recs_2033 = new HashSet<String>();
		//recs_2033.add("Haskell Study Plan  authored by   user_2001  at   http://www.alpheccar.org/en/posts/show/67  with TAGS:   tutorial language haskell functional learning programming ");
		//recs_2033.add("OUseful.Info, the blog�  authored by   user_2016  at   http://ouseful.wordpress.com/  with TAGS:   education blog technology ");
		//recs_2033.add("Byte of Python:Main Page - Text  authored by   user_2015  at   http://swaroopch.info/text/Byte_of_Python:Main_Page  with TAGS:   wiki programming books python tutorial reference ");
		//recs_2033.add("Home page for Heuristics interactivity  authored by   user_2015  at   http://www.id-book.com/catherb/index.htm  with TAGS:   tools hci design interaction usability heuristics ");
		//recs_2033.add("GameDev.net - all your game development needs  authored by   user_2021  at   http://www.gamedev.net/  with TAGS:   development tutorials games gamedev news software programming ");
		mapRecA.put("user_2033", recs_2033);
		
		Set<String> recs_2034 = new HashSet<String>();
		recs_2034.add("Learner Modelling for Reflection  authored by   user_2043  at   http://www.eee.bham.ac.uk/bull/lemore/  with TAGS:   research OpenLearnerModel elearning reference ");
		//recs_2034.add("How to Design Programs: An Introduction to Computing and Programming  authored by   user_2001  at   http://www.htdp.org/2003-09-26/Book/  with TAGS:   scheme howto design reference books programming ");
		//recs_2034.add("Home page for Heuristics interactivity  authored by   user_2015  at   http://www.id-book.com/catherb/index.htm  with TAGS:   tools hci design interaction usability heuristics ");
		//recs_2034.add("Java Neural Networks | Jeff Heaton  authored by   user_2039  at   http://www.jeffheaton.com/ai  with TAGS:   book programming ai java neural-networks ");
		recs_2034.add("International standards for HCI and usability - GUILHERMO.com - Arquitetura de Informa��o  authored by   user_2025  at   http://www.guilhermo.com/ai_biblioteca/referencia.asp?referencia=127  with TAGS:   interaction usability information hci interface ");
		mapRecA.put("user_2034", recs_2034);
		
		
		Set<String> recs_2036 = new HashSet<String>();
		recs_2036.add("Social Software Building Blocks / nForm / Customer Insight, Strategy, Design and Development  authored by   user_2025  at   http://nform.ca/publications/social-software-building-block  with TAGS:   software community design socialsoftware paper research toread social ");
		recs_2036.add("PhD researcher Network | TENCompetence  authored by   user_2043  at   http://www.tencompetence.org/node/57  with TAGS:   professional university network PhD resources TENCompetence research ");
		recs_2036.add("HCIRN: Human-Computer Interaction Resource Network  authored by   user_2033  at   http://www.hcirn.com/index.php  with TAGS:   resources webdesign hci interaction articles research design jobs usability ");
		recs_2036.add("Things You Should Never Do, Part I - Joel on Software  authored by   user_2001  at   http://www.joelonsoftware.com/articles/fog0000000069.html  with TAGS:   productivity rewrite architecture programming joelonsoftware design development software ");
		//recs_2036.add("The Social Software Weblog  authored by   user_2025  at   http://socialsoftware.weblogsinc.com/  with TAGS:   socialsoftware blog social_software social software ");		
		mapRecA.put("user_2036", recs_2036);	

		Set<String> recs_2037 = new HashSet<String>();
		//recs_2037.add("Politiken.dk - det levende net  authored by   user_2003  at   http://politiken.dk/newsinenglish  with TAGS:   politiken english denmark news ");
		//recs_2037.add("Google  authored by   user_2009  at   http://www.google.com/  with TAGS:   engine searchengine reference search web everything doubts google ");
		//recs_2037.add("Learner Modelling for Reflection  authored by   user_2043  at   http://www.eee.bham.ac.uk/bull/lemore/  with TAGS:   research OpenLearnerModel elearning reference ");
		//recs_2037.add("Scholar  authored by   user_2034  at   http://scholar.google.com/  with TAGS:   search research reference academic science google literature citations scholar ");
		//recs_2037.add("Watch Movies Online For Free Full Movie Downloads  authored by   user_2005  at   http://www.watch-movies.net/  with TAGS:   online television movies free tv streaming film community video ");
		mapRecA.put("user_2037", recs_2037);
		
		Set<String> recs_2038 = new HashSet<String>();
		recs_2038.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		recs_2038.add("Talis  authored by   user_2016  at   http://www.talis.com/  with TAGS:   rdf web3.0 software semantic ");
		//recs_2038.add("Software Carpentry (Version 1122)  authored by   user_2001  at   http://swc.scipy.org/  with TAGS:   development lectures tutorial python science reference software programming ");
		recs_2038.add("Neural Networks with Java  authored by   user_2039  at   http://fbim.fh-regensburg.de/~saj39122/jfroehl/diplom/e-index.html  with TAGS:   neural-networks ai framework java ");
		recs_2038.add("Auto-vectorization in GCC - GNU Project - Free Software Foundation (FSF)  authored by   user_2001  at   http://gcc.gnu.org/projects/tree-ssa/vectorization.html  with TAGS:   gcc software vectorization compiler optimization programming ");
		mapRecA.put("user_2038", recs_2038);
		
		Set<String> recs_2039 = new HashSet<String>();
		//recs_2039.add("Brian's TurboGears Tutorial  authored by   user_2001  at   http://exogen.case.edu/turbogears.html  with TAGS:   webdev mochikit python web turbogears tutorial programming ");
		//recs_2039.add("DevCentral Home  authored by   user_2015  at   http://devcentral.iticentral.com/  with TAGS:   java asp c++ tutorial programming resources Windows ");
		//recs_2039.add("The future of PHP  authored by   user_2043  at   http://www.ibm.com/developerworks/opensource/library/os-php-future/?ca=dgr-lnxw01PHP-Future  with TAGS:   scripting PHP programming development webdev webdevelopment web ");
		//recs_2039.add("Ola Bini on Java, Lisp, Ruby and AI: Code size and dynamic languages  authored by   user_2001  at   http://ola-bini.blogspot.com/2007/12/code-size-and-dynamic-languages.html  with TAGS:   ruby javascript scala java languages code programming ");
		//recs_2039.add("TOMCAT + HTTPS  authored by   user_2002  at   http://www.guj.com.br/posts/list/29710.java  with TAGS:   ssl java Security tomcat https ");
		mapRecA.put("user_2039", recs_2039);

		Set<String> recs_2042 = new HashSet<String>();
		//recs_2042.add("Virtual community - Wikipedia, the free encyclopedia  authored by   user_2025  at   http://en.wikipedia.org/wiki/Motivations_for_contributing_to_online_communities#Motivations_for_contributing_to_online_communities  with TAGS:   social virtual community ");
		//recs_2042.add("Brian's TurboGears Tutorial  authored by   user_2001  at   http://exogen.case.edu/turbogears.html  with TAGS:   webdev mochikit python web turbogears tutorial programming ");
		//recs_2042.add("Conjugation Trainer - Select verbs and language  authored by   user_2033  at   http://www.vocabulix.com/online/Learn/Verbs  with TAGS:   Dictionary grammar tools language ");
		recs_2042.add("tabulator Generic data browser  authored by   user_2020  at   http://www.w3.org/2005/ajar/tab  with TAGS:   rdf browser ajax web semanticweb javascript SemanticWikis semantic ");
		recs_2042.add("Social Software Building Blocks / nForm / Customer Insight, Strategy, Design and Development  authored by   user_2025  at   http://nform.ca/publications/social-software-building-block  with TAGS:   research toread design community socialsoftware software paper social ");
		mapRecA.put("user_2042", recs_2042);
		
		Set<String> recs_2043 = new HashSet<String>();
		recs_2043.add("Wikipedia, the free encyclopedia  authored by   user_2021  at   http://en.wikipedia.org/wiki/Main_Page  with TAGS:   wiki information encyclopedia research wikipedia search reference ");
		//recs_2043.add("HCI Bibliography : Human-Computer Interaction / User Interface Usability  authored by   user_2034  at   http://www.hcibib.org/  with TAGS:   interaction usability design interface bibliography reference research HCI ");
		recs_2043.add("Scholar  authored by   user_2034  at   http://scholar.google.com/  with TAGS:   search research reference academic science google literature citations scholar ");
		//recs_2043.add("The National Library of Medicine  authored by   user_2015  at   http://www.nlm.nih.gov/  with TAGS:   reference research Libraries medical government health medicine ");
		//recs_2043.add("RFC Archive  authored by   user_2023  at   http://www.faqs.org/rfcs/  with TAGS:   Standards internet web reference programming developerinformation rfc ");
		mapRecA.put("user_2043", recs_2043);
		
		Set<String> recs_2044 = new HashSet<String>();
		recs_2044.add("Main Page - OpenViBEWiki  authored by   user_2043  at   http://www.irisa.fr/bunraku/OpenViBE/wiki/index.php?title=Main_Page  with TAGS:   opensource AI programming HCI university research ");
		recs_2044.add("PhD researcher Network | TENCompetence  authored by   user_2043  at   http://www.tencompetence.org/node/57  with TAGS:   professional university network PhD resources TENCompetence research ");
		//recs_2044.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		//recs_2044.add("Connectbeam  authored by   user_2023  at   http://www.connectbeam.com/  with TAGS:   social collaboration enterprise search tagging ");
		recs_2044.add("Why Johnny can't code | Lambda the Ultimate  authored by   user_2033  at   http://lambda-the-ultimate.org/node/1733#comment-21414  with TAGS:   blog discussion language games Learning kids programming ");
		mapRecA.put("user_2044", recs_2044);	

		return mapRecA;
	}	
	
	
	
	
	public static Map<String,Set<String>> loadOtherLanguage(){

		Map<String,Set<String>> mapRecA = new HashMap<String,Set<String>>();
		Set<String> recs_2000 = new HashSet<String>();
		recs_2000.add("Guia: como funciona a Bolsa de Valores e como aplicar em a��es na Bovespa - UOL Economia  authored by   user_2025  at   http://economia.uol.com.br/ultnot/2008/05/14/guia_bovespa_bolsa_valores_aplicar_acoes.jhtm  with TAGS:   economia investimentos de valores bolsa finan�as bovespa ");
		mapRecA.put("user_2000", recs_2000);
		
		Set<String> recs_2001 = new HashSet<String>();
		mapRecA.put("user_2001", recs_2001);
		
		Set<String> recs_2002 = new HashSet<String>();
		mapRecA.put("user_2002", recs_2002);
		
		Set<String> recs_2003 = new HashSet<String>();
		recs_2003.add("Aalborg - VisitAalborg - Denmark.The official guide to Aalborg with ...  authored by   user_2004  at   http://www.visitaalborg.com/international/en-gb/menu/tourist/turist-maalgruppe-forside.htm  with TAGS:   aalborg travel denmark tourism information ");
		mapRecA.put("user_2003", recs_2003);
		
		Set<String> recs_2004 = new HashSet<String>();
		mapRecA.put("user_2004", recs_2004);
		
		Set<String> recs_2005 = new HashSet<String>();
		mapRecA.put("user_2005", recs_2005);
		
		Set<String> recs_2006 = new HashSet<String>();
		mapRecA.put("user_2006", recs_2006);

		Set<String> recs_2009 = new HashSet<String>();
		mapRecA.put("user_2009", recs_2009);
		
		Set<String> recs_2010 = new HashSet<String>();
		recs_2010.add("Folha Online - Primeiro jornal em tempo real em l�ngua portuguesa  authored by   user_2004  at   http://www.folha.uol.com.br/  with TAGS:   folha noticias jornal brasil news not�cias jornais ");
		mapRecA.put("user_2010", recs_2010);

		Set<String> recs_2012 = new HashSet<String>();
		mapRecA.put("user_2012", recs_2012);
		
		Set<String> recs_2013 = new HashSet<String>();
		mapRecA.put("user_2013", recs_2013);
		
		Set<String> recs_2015 = new HashSet<String>();
		mapRecA.put("user_2015", recs_2015);
		
		Set<String> recs_2016 = new HashSet<String>();
		mapRecA.put("user_2016", recs_2016);
		
		Set<String> recs_2017 = new HashSet<String>();
		mapRecA.put("user_2017", recs_2017);
		
		Set<String> recs_2018 = new HashSet<String>();
		mapRecA.put("user_2018", recs_2018);
		
		Set<String> recs_2019 = new HashSet<String>();
		mapRecA.put("user_2019", recs_2019);
		
		Set<String> recs_2020 = new HashSet<String>();
		mapRecA.put("user_2020", recs_2020);
		
		Set<String> recs_2021 = new HashSet<String>();
		mapRecA.put("user_2021", recs_2021);
		
		Set<String> recs_2022 = new HashSet<String>();
		mapRecA.put("user_2022", recs_2022);
		
		Set<String> recs_2023 = new HashSet<String>();
		mapRecA.put("user_2023", recs_2023);
		
		Set<String> recs_2024 = new HashSet<String>();
		mapRecA.put("user_2024", recs_2024);
		
		Set<String> recs_2025 = new HashSet<String>();
		mapRecA.put("user_2025", recs_2025);
		
		Set<String> recs_2026 = new HashSet<String>();
		mapRecA.put("user_2026", recs_2026);
		
		Set<String> recs_2027 = new HashSet<String>();
		mapRecA.put("user_2027", recs_2027);
		
		Set<String> recs_2028 = new HashSet<String>();
		mapRecA.put("user_2028", recs_2028);
		
		Set<String> recs_2029 = new HashSet<String>();
		mapRecA.put("user_2029", recs_2029);
		
		Set<String> recs_2030 = new HashSet<String>();
		mapRecA.put("user_2030", recs_2030);
		
		Set<String> recs_2031 = new HashSet<String>();
		mapRecA.put("user_2031", recs_2031);
		
		Set<String> recs_2032 = new HashSet<String>();
		mapRecA.put("user_2032", recs_2032);
		
		Set<String> recs_2033 = new HashSet<String>();
		mapRecA.put("user_2033", recs_2033);
		
		Set<String> recs_2034 = new HashSet<String>();
		recs_2034.add("International standards for HCI and usability - GUILHERMO.com - Arquitetura de Informa��o  authored by   user_2025  at   http://www.guilhermo.com/ai_biblioteca/referencia.asp?referencia=127  with TAGS:   interaction usability information hci interface ");
		mapRecA.put("user_2034", recs_2034);
		
		
		Set<String> recs_2036 = new HashSet<String>();
		mapRecA.put("user_2036", recs_2036);	

		Set<String> recs_2037 = new HashSet<String>();
		recs_2037.add("Politiken.dk - det levende net  authored by   user_2003  at   http://politiken.dk/newsinenglish  with TAGS:   politiken english denmark news ");
		mapRecA.put("user_2037", recs_2037);
		
		Set<String> recs_2038 = new HashSet<String>();
		mapRecA.put("user_2038", recs_2038);
		
		Set<String> recs_2039 = new HashSet<String>();
		mapRecA.put("user_2039", recs_2039);

		Set<String> recs_2042 = new HashSet<String>();
		mapRecA.put("user_2042", recs_2042);
		
		Set<String> recs_2043 = new HashSet<String>();
		mapRecA.put("user_2043", recs_2043);
		
		Set<String> recs_2044 = new HashSet<String>();
		recs_2044.add("Christian Stefansen  authored by   user_2020  at   http://www.stefansen.dk/  with TAGS:   homepage theory research work software ");
		mapRecA.put("user_2044", recs_2044);	

		return mapRecA;
	}	
	
	

}
