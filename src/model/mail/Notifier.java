package mail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Notifier {
	
	public static void main(String[] args) {
		Notifier.notifyPeople();
		//DeliciousData.reportUsersBookmarking();
		
	}

	public static void notifyPeople() {
		
		String msgBody = "";
		
		//String sub = "Your Delicious account was created!";
		
		String sub = "Reminder for evaluation";
		
		Map<String,String> loadPeople = loadPeople();
		
		Set<String> pp = loadPeople.keySet();
		
		Set<String> redundant = new HashSet<String>();
		
		for (int i = 0; i < pp.size(); i++) {
			
			String reci = loadPeople.get(pp.toArray()[i]);
			
			if (redundant!=null && !redundant.contains(reci)) {

//				msgBody = "Now you already can start tagging! " +
//			 		  "Your username is "+pp.toArray()[i]+" and your password is aau.tag.test" +
//					  "\n" +
//					  "Thank you for your participation !" +
//					  "\n" +
//					  "You are welcome to visite our FAQ web site: http://iwis.cs.aau.dk/evalfaq" +
//					  "\n" +
//					  "Please reply me confirming you received this email." +
//					  "\n" +	
//					  "Best Regards," +
//					  "\nFred Durao and Peter Dolog"+
//					  "\nIWIS - Aalborg University";
				
//				msgBody = "" +
//		 		  "Dear participant, we noticed that you have not bookmarked yet." +
//				  "\n" +		 		  
//		 		  "This might have happened because the generated login and password we sent are in your spam mail box. Please check it out and go tagging!" +
//				  "\n" +
//		 		  "Next week we will be collecting your bookmarkings and starting the recommendations." +
//		 		  "\n" +				  
//				  "Just for reminder: Your username is "+pp.toArray()[i]+" and your password is aau.tag.test" +
//				  "\n" +
//				  "\n" +				  
//				  "Thank you for your participation!" +
//				  "\n" +
//				  "You are welcome to visit our FAQ web site: http://iwis.cs.aau.dk/evalfaq" +
//				  "\n" +
//				  "Please reply me if you received this email." +
//				  "\n" +	
//				  "\n" +				  
//				  "Best Regards," +
//				  "\nFred Durao and Peter Dolog"+
//				  "\nIWIS - Aalborg University";				
				
				msgBody = "" +
				"Dear participant, we noticed that you have not completed the questionnaire with your recommendations." +
				"\n" +		 		  
				"\n" +		 		  
				"This might have happened because last email we sent is in your spam mail box. Please check it out and complete the questionnaire. :)" +
				"\n" +
				"\n" +
				"It will not take more the 10 minutes :)" +
				"\n" +
				"\n" +
				"Next week we will be collecting your inputs and compiling the overall results of the experiment." +
				"\n" +
				"\n" +
				"Thank you for your participation!" +
				"\n" +				  
				"Best Regards," +
				"\nFred Durao and Peter Dolog"+
				"\nIWIS - Aalborg University";				

				//Sender sender = new Sender(reci,sub,msgBody);
				//sender.doSend();
				redundant.add(reci);
				System.out.println("Message sent to "+reci);
				//System.out.println(msgBody);

			}
		}
		
		
	}
	/**
	 * @return
	 */
	public static Map<String,String> loadPeople(){
		Map<String,String> people = new HashMap<String,String>();
		
		people.put("user_2000","shick.shi@gmail.com");
//		people.put("user_2001","brunojm@gmail.com");
		people.put("user_2002","mendes.rodrigo@gmail.com");
		people.put("user_2003","silviacanedo@gmail.com");
		people.put("user_2004","julianosoyama@gmail.com");
		people.put("user_2005","mgomeslage@hotmail.com");
		people.put("user_2006","lblisboa@gmail.com");
//		people.put("user_2008","alexandremartins@gmail.com");
		people.put("user_2009","paulaguglielmi@yahoo.com.br");
//		people.put("user_2010","gustavo.wo.costa@gmail.com");
//		people.put("user_2011","sebastian.schaffert@salzburgresearch.at");
		people.put("user_2012","Jakub.Kotowski@ifi.lmu.de");
//		people.put("user_2013","blumauera@punkt.at");
//		people.put("user_2014","Josef.Holy@Sun.COM");
		people.put("user_2015","sergio.r.dasilva@gmail.com");
//		people.put("user_2016","cullrich@activemath.org");
		people.put("user_2017","freddurao@gmail.com");
//		people.put("user_2018","jahn@cs.aau.dk");
		people.put("user_2019","yjlin@cs.aau.dk");
//		people.put("user_2020","dolog@cs.aau.dk");
		people.put("user_2021","stephanie.stroka@salzburgresearch.at");
		people.put("user_2022","andreas.gruber@salzburgresearch.at");
		people.put("user_2023","Peter.Reiser@Sun.COM");		
//		people.put("user_2024","j.herwig@semantic-web.at");		
//		people.put("user_2025","roberto.uem@gmail.com");
//		people.put("user_2026","wolle@upb.de");	
//		people.put("user_2027","A.M.Bogers@uvt.nl");
		people.put("user_2028","amm@cs.aau.dk");
//		people.put("user_2029","martin.lindner@gmail.com"); 
		people.put("user_2030","jjj@cs.aau.dk");
		people.put("user_2031","sergio.fernandez@fundacionctic.org");
		people.put("user_2032","koller@punkt.at");
		people.put("user_2033","patricia@iprj.uerj.br");
		people.put("user_2034","judy@it.usyd.edu.au");	
//		people.put("user_2035","dorait@imorph.com");
		people.put("user_2036","k.wodzicki@iwm-kmrc.de");
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
	public static Map<String,String> loadPeople2(){
		Map<String,String> people = new HashMap<String,String>();

		people.put("user_2017","freddurao@gmail.com");

		
		
		
		// aau.tag.test
		
		
		
		
		return people;
	}
	
	
	public static Map<String,String> loadResults(){
		Map<String,String> people = new HashMap<String,String>();
		
		people.put("user_2000","shick.shi@gmail.com");
//		people.put("user_2001","brunojm@gmail.com");
		people.put("user_2002","mendes.rodrigo@gmail.com");
		people.put("user_2003","silviacanedo@gmail.com");
		people.put("user_2004","julianosoyama@gmail.com");
		people.put("user_2005","mgomeslage@hotmail.com");
		people.put("user_2006","lblisboa@gmail.com");
//		people.put("user_2008","alexandremartins@gmail.com");
		people.put("user_2009","paulaguglielmi@yahoo.com.br");
//		people.put("user_2010","gustavo.wo.costa@gmail.com");
//		people.put("user_2011","sebastian.schaffert@salzburgresearch.at");
		people.put("user_2012","Jakub.Kotowski@ifi.lmu.de");
//		people.put("user_2013","blumauera@punkt.at");
//		people.put("user_2014","Josef.Holy@Sun.COM");
		people.put("user_2015","sergio.r.dasilva@gmail.com");
//		people.put("user_2016","cullrich@activemath.org");
		people.put("user_2017","5");
//		people.put("user_2018","jahn@cs.aau.dk");
		people.put("user_2019","yjlin@cs.aau.dk");
//		people.put("user_2020","dolog@cs.aau.dk");
		people.put("user_2021","stephanie.stroka@salzburgresearch.at");
		people.put("user_2022","andreas.gruber@salzburgresearch.at");
		people.put("user_2023","Peter.Reiser@Sun.COM");		
//		people.put("user_2024","j.herwig@semantic-web.at");		
//		people.put("user_2025","roberto.uem@gmail.com");
//		people.put("user_2026","wolle@upb.de");	
//		people.put("user_2027","A.M.Bogers@uvt.nl");
		people.put("user_2028","amm@cs.aau.dk");
//		people.put("user_2029","martin.lindner@gmail.com"); 
		people.put("user_2030","jjj@cs.aau.dk");
		people.put("user_2031","sergio.fernandez@fundacionctic.org");
		people.put("user_2032","koller@punkt.at");
		people.put("user_2033","patricia@iprj.uerj.br");
		people.put("user_2034","judy@it.usyd.edu.au");	
//		people.put("user_2035","dorait@imorph.com");
		people.put("user_2036","k.wodzicki@iwm-kmrc.de");
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

}

