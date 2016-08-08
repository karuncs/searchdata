package watson;




import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.ac.open.kmi.watson.clientapi.EntityResult;
import uk.ac.open.kmi.watson.clientapi.OntologySearch;
import uk.ac.open.kmi.watson.clientapi.OntologySearchServiceLocator;
import uk.ac.open.kmi.watson.clientapi.SemanticContentResult;
import uk.ac.open.kmi.watson.clientapi.WatsonSearch;
import uk.ac.open.kmi.watson.clientapi.WatsonSearchServiceLocator;
import uk.ac.open.kmi.watson.clientapi.WatsonService;

public class TestOntologySearch {

	private OntologySearch os;
	private WatsonSearch ws;
	
	public TestOntologySearch(){
		OntologySearchServiceLocator locator = new OntologySearchServiceLocator();
		WatsonSearchServiceLocator locatorx = new WatsonSearchServiceLocator();
		
		
		try{
			os = locator.getUrnOntologySearch();
			
			ws = locatorx.getUrnWatsonSearch();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getCause());
		}
	}
	
	public static void main(String[] args){
		TestOntologySearch app = new TestOntologySearch();
		try {
//			app.search();
			//app.sparqlf();
			app.sparql();
			//app.fsearch();
//			app.rsearch();
//			app.rsearchp();
//			app.bestCoverage();
//			app.DLExpr();
//			app.size();
//			app.languages();	
//			app.locations();
//			app.cacheLocation();
//			app.listClasses();
//			app.listProperties();
//			app.listIndividuals();
//			app.nbReviews();
//			app.avgReviews();
//			app.revyuURI();
//			app.sparql();
//			app.imports();
//			app.imported();
//			app.labels();
//			app.comments();
//			app.nbstats();
			 //app.listSC(); // can be long...
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void sparqlf() throws RemoteException{
		System.out.println("***** Executing a SPARQL query sane_cows.daml (get labels) *****");
		String query = 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
			"PREFIX daml: <http://www.daml.org/2001/03/daml+oil#> " +
			"SELECT ?p " +
			"WHERE " +
			"{ ?s ?p ?o } " ;
		String res = os.executeSPARQLQuery("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", query);
		System.out.println(res);
	}
	
	public void search() throws RemoteException{
		System.out.println("***** Searching Cat and Dog *****");
		String[] params = {"cat", "dog"};
		String[] res = os.getSemanticContentByKeywords(params);
		for (String s : res) System.out.println(s);
	} 
	
	public void fsearch2() throws RemoteException{
		System.out.println("***** Searching Cat and Dog restricted to local name, label and comments of classes and individuals*****");
		String[] params = {"has"};
		String[] res = os.getSemanticContentByKeywordsWithRestrictions(params,WatsonService.LABEL,WatsonService.INDIVIDUAL, WatsonService.EXACT_MATCH);				
		for (String s : res) System.out.println(s);
	} 
	
	private void searchComplex() throws RemoteException {
		System.out.println("***** Searching Cat and dog *****");
		String[] params = { "dog" };
		int scopeModifier =  WatsonService.LOCAL_NAME + WatsonService.LABEL + WatsonService.COMMENT + WatsonService.LITERAL;
		int entityTypeModifier = WatsonService.CLASS + WatsonService.PROPERTY + WatsonService.INDIVIDUAL;
		int scInfo = WatsonSearch.SC_DLEXPR_INFO + WatsonSearch.SC_LANGUAGES_INFO;
		int entInfo = 255;
		SemanticContentResult[] sr = ws.searchWatsonWithKeywords(params,
				scopeModifier, entityTypeModifier, WatsonService.TOKEN_MATCH, scInfo, entInfo);
		
		System.out.println(sr.length);
	}
	
	public void fsearch() throws RemoteException{
		
		String[] params = {"animal"};
		String[] res = os.getSemanticContentByKeywordsWithRestrictions(params, 
				WatsonService.LABEL, WatsonService.CLASS+WatsonService.INDIVIDUAL+WatsonService.LITERAL, WatsonService.EXACT_MATCH);
		
		SemanticContentResult[] sr = ws.searchWatsonWithKeywords(params,
				WatsonService.LABEL, WatsonService.CLASS+WatsonService.INDIVIDUAL+WatsonService.LITERAL, WatsonService.EXACT_MATCH,WatsonSearch.SC_DLEXPR_INFO + WatsonSearch.SC_LANGUAGES_INFO,255);

		
		for (String s : res) System.out.println(s);
		seeRelations(sr,params[0]);

	} 
	
	
	public int findRelations(String term1, String term2) throws RemoteException{
		int amount = 0;
		String[] params = new String[2];
		params[0]=term1;
		params[1]=term2;
		
//		String[] res = os.getSemanticContentByKeywordsWithRestrictions(params, 
//				//WatsonService.LABEL, WatsonService.CLASS+WatsonService.INDIVIDUAL+WatsonService.LITERAL, WatsonService.EXACT_MATCH);
//				WatsonService.LABEL, WatsonService.CLASS, WatsonService.TOKEN_MATCH);
//		
//		
//		
//		
//		//if (res!=null && res.length>0 && res.equals("subClassOf")) {
//		if (res!=null && res.length>0) {
//			for (int i = 0; i < res.length; i++) {
//				System.out.println(res[i]);				
//				if (res[i].equalsIgnoreCase("subClassOf")) {
//					System.out.println("subClassOf");
//					amount = res.length;	
//				}
//			}
//			
//		}
		
		
		SemanticContentResult[] sr = ws.searchWatsonWithKeywords(params,
				WatsonService.LABEL, WatsonService.CLASS, WatsonService.EXACT_MATCH,WatsonSearch.SC_DLEXPR_INFO + WatsonSearch.SC_LANGUAGES_INFO,255);

		
		
		//for (String s : res) System.out.println(s);
		Set<String> relations = getRelations(sr,params[0]);
		for (String rel : relations) {
			if (rel.equalsIgnoreCase("subClassOf")) {
				amount = amount + 1;	
			}
		}
		
		return amount;

	} 	
	

	
	private void seeRelations(SemanticContentResult[] sr, String query){
		Set<String> properties = new HashSet<String>();
		
		for (SemanticContentResult s : sr){
			
			
			EntityResult[] aa =	s.getEntityResultList();
			for (EntityResult a : aa){
				String[][] xx = a.getRelationFrom();
		
				//System.out.println("getRelationFrom");
//				for (String[] x : xx) System.out.println(query+" ("+x[1]+") --- "+x[2]);
				for (String[] x : xx){
					//System.out.println("property "+x[1]);
					properties.add(x[1]);				
				}
				String[][] zz = a.getRelationTo();
				//System.out.println("getRelationTo");
				for (String[] z : zz){
					//System.out.println(z[2]+" ("+z[1]+") --- "+query);	
					properties.add(z[1]);
				}
								
			}
			
		

			//System.out.println(s);
		}	
		
		for (String ppp : properties)System.out.println(ppp);
		
	}
	
	private Set<String> getRelations(SemanticContentResult[] sr, String query){
		Set<String> properties = new HashSet<String>();
		
		for (SemanticContentResult s : sr){
			
			
			EntityResult[] aa =	s.getEntityResultList();
			for (EntityResult a : aa){
				String[][] xx = a.getRelationFrom();
				
				//System.out.println("getRelationFrom");
//				for (String[] x : xx) System.out.println(query+" ("+x[1]+") --- "+x[2]);
				for (String[] x : xx){
					//System.out.println("property "+x[1]);
					properties.add(x[1]);				
				}
				String[][] zz = a.getRelationTo();
				//System.out.println("getRelationTo");
				for (String[] z : zz){
					//System.out.println(z[2]+" ("+z[1]+") --- "+query);	
					properties.add(z[1]);
				}
				
			}
			
			
			
			//System.out.println(s);
		}	
		
		return properties;
		
	}
	

	public void rsearchp() throws RemoteException{
		System.out.println("***** Searching Cat and Dog Paginated *****");
		String[] params = {"cat", "dog"};
		String[] res = os.getSemanticContentByKeywordsWithRestrictionsPaginated(params, 
					WatsonService.LOCAL_NAME+WatsonService.LABEL+WatsonService.COMMENT, WatsonService.PROPERTY+WatsonService.INDIVIDUAL, WatsonService.TOKEN_MATCH, 0, 11);
		for (String s : res) System.out.println(s);
	} 

	
	
	private void bestCoverage() throws RemoteException{
		System.out.println("***** Searching best coverage of \"mammal man woman child god\", restricted to local name and label of Classes*****");
		String[] params = {"mammal", "man", "woman", "child", "god"};
		String[] res = os.getBestCoverageWithRestrictions(params, 
				WatsonService.LOCAL_NAME+WatsonService.LABEL, WatsonService.CLASS, WatsonService.TOKEN_MATCH);
		for (String s : res) System.out.println(s);		
	}
	
	public void listSC() throws RemoteException {
		System.out.println("***** Listing Semantic Contents (can take a while...) *****");
		for (int i = 0 ; i < 30000; i+=1000) {
			String[] res = os.listSemanticContents(i, i+1000);
		     for (String s : res) System.out.println(s);
		}
	}
	
	public void locations() throws RemoteException{
		System.out.println("***** Location(s) of sane_cows.daml *****");
		String[] res = os.getSemanticContentLocation("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml");
		for (String r : res) System.out.println(r);		
	}
	
	public void cacheLocation() throws RemoteException{
		System.out.println("***** Cache Location of sane_cows.daml *****");
		String res = os.getCacheLocation("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml");
		System.out.println(res);
		
	}
	
	/* Have not been deployed in the new version... 
	 	public void OWLSpecy() throws RemoteException{
		System.out.println("***** OWL Species of Mid-level-ontology *****");
		// TODO: change to int and change the name
		String res = os.getOWLSpecy("http://reliant.teknowledge.com/DAML/Mid-level-ontology.owl");
		if (Integer.parseInt(res) == FULL) System.out.println("OWL Full");
		if (Integer.parseInt(res) == DL) System.out.println("OWL DL");
		if (Integer.parseInt(res) == LITE) System.out.println("OWL Lite");
		if (Integer.parseInt(res) == NOT_OWL || Integer.parseInt(res) == -1) System.out.println("N/A");
	}*/
	
	public void DLExpr() throws RemoteException{
		System.out.println("***** DL Expr of sane_cows.daml *****");
		String res = os.getDLExpressivness("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml");
		System.out.println(res);
	}
	
	public void size() throws RemoteException{
		System.out.println("***** Size in bytes of sane_cows.daml *****");
		long res = os.getSizeInBytes("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml");
		System.out.println(res);
	}
	
	public void languages() throws RemoteException{
		System.out.println("***** Languages of sane_cows.daml *****");
		String[] res = os.getSemanticContentLanguages("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml");
		for (String s : res) System.out.println(s);
	} 
	
	public void listClasses() throws RemoteException{
		System.out.println("***** Classes of sane_cows.daml *****");
		String[] res = os.listClasses("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml");
		for (String s : res) System.out.println(s);
	}
	
	public void listProperties(String uri) throws RemoteException{
		System.out.println("***** Properties of sane_cows.daml *****");
		String[] res = os.listProperties(uri);
		for (String s : res) System.out.println(s);
	}
	
	public void listProperties() throws RemoteException{
		System.out.println("***** Properties of sane_cows.daml *****");
		String[] res = os.listProperties("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml");
		for (String s : res) System.out.println(s);
	}
	
	public void listIndividuals() throws RemoteException{
		System.out.println("***** Individuals of sane_cows.daml *****");
		String[] res = os.listIndividuals("http://www.aktors.org/ontology/support.daml");
		for (String s : res) System.out.println(s);
	}
	
	public void nbReviews() throws RemoteException{
		System.out.println("***** Number of reviews for http://owl.man.ac.uk/tutorial/twopets.rdf *****");
		int nb = os.getNumberOfReviews("http://owl.man.ac.uk/tutorial/twopets.rdf");
        System.out.println(nb);
	}
	
	public void avgReviews() throws RemoteException{
		System.out.println("***** Number of reviews for http://owl.man.ac.uk/tutorial/twopets.rdf *****");
		int nb = os.getAverageRating("http://owl.man.ac.uk/tutorial/twopets.rdf");
        System.out.println(nb);
	}
	
	public void revyuURI() throws RemoteException{
		System.out.println("***** Number of reviews for http://owl.man.ac.uk/tutorial/twopets.rdf *****");
		String uri = os.getRevyuURL("http://owl.man.ac.uk/tutorial/twopets.rdf");
        System.out.println(uri);
	}
	
	public void sparql() throws RemoteException{
		System.out.println("***** Executing a SPARQL query sane_cows.daml (get labels) *****");
		String query = 
			    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
				"PREFIX daml: <http://www.daml.org/2001/03/daml+oil#> " +
				"SELECT ?s ?p ?o " +
				"WHERE " +
				  "{ ?s rdfs:label ?o } " ;
		String res = os.executeSPARQLQuery("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", query);
		System.out.println(res);
	}

	
	public void imports() throws RemoteException{
		System.out.println("***** Ontology imported by http://www.aktors.org/ontology/portal *****");
		String[] res = os.getImports("http://www.aktors.org/ontology/portal");
	    for (String s : res) System.out.println(s);
	}
	
	public void imported() throws RemoteException{
		System.out.println("***** Ontology importing by http://www.aktors.org/ontology/portal *****");
		String[] res = os.getImportedBy("http://www.aktors.org/ontology/portal");
		for (String s : res) System.out.println(s);
	}
	
	public void labels() throws RemoteException{
		System.out.println("***** Labels of http://www.aktors.org/ontology/portal *****");
		String[] res = os.getLabels("http://www.aktors.org/ontology/portal");
		for (String s : res) System.out.println(s);
	}
	
	public void comments() throws RemoteException{
		System.out.println("***** Cpmments of http://www.mindswap.org/2003/owl/pet *****");
		String[] res = os.getLabels("http://www.mindswap.org/2003/owl/pet");
		for (String s : res) System.out.println(s);
	}
	
	public void nbstats() throws RemoteException{
		System.out.println("***** NBStats of http://www.mindswap.org/2003/owl/pet *****");
		long s = os.getNumberOfStatement("http://www.mindswap.org/2003/owl/pet");
		System.out.println(s);
	}
}
