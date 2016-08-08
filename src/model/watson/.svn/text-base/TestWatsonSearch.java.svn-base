package watson;




import java.rmi.RemoteException;

import uk.ac.open.kmi.watson.clientapi.EntityResult;
import uk.ac.open.kmi.watson.clientapi.SemanticContentResult;
import uk.ac.open.kmi.watson.clientapi.WatsonSearch;
import uk.ac.open.kmi.watson.clientapi.WatsonSearchServiceLocator;
import uk.ac.open.kmi.watson.clientapi.WatsonService;

public class TestWatsonSearch {

	// The service client interface
	private WatsonSearch ws;

	public TestWatsonSearch() {
		WatsonSearchServiceLocator locator = new WatsonSearchServiceLocator();
		try {
			ws = locator.getUrnWatsonSearch();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getCause());
		}
	}

	public static void main(String[] args) {
		TestWatsonSearch app = new TestWatsonSearch();
		try {
			//app.searchComplex();
			app.searchLimit();
//			app.bestCoverage();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
		displayResult(sr);
		System.out.println(sr.length);
	}
	
	private void searchLimit() throws RemoteException {
		System.out.println("***** Searching Cat and dog with limit 10 *****");
		String[] params = { "cat", "dog" };
		int scopeModifier = WatsonService.LOCAL_NAME + WatsonService.LABEL + WatsonService.COMMENT + WatsonService.LITERAL;
		int entityTypeModifier = WatsonService.CLASS + WatsonService.PROPERTY + WatsonService.INDIVIDUAL;
		int scInfo = WatsonSearch.SC_DLEXPR_INFO + WatsonSearch.SC_SIZE_INFO + WatsonSearch.SC_LANGUAGES_INFO;
		int entInfo = WatsonSearch.ENT_TYPE_INFO + WatsonSearch.ENT_LABEL_INFO;// +ENT_ANYRELATIONTO_INFO;
		SemanticContentResult[] sr = ws.searchWatsonWithKeywordsWithLimit(params,
				scopeModifier, entityTypeModifier, WatsonService.TOKEN_MATCH, scInfo, entInfo, 10);
		displayResult(sr);
	}

	private void bestCoverage() throws RemoteException {
		System.out.println("***** Best Coverage of \"mammal man woman child god *****");
		String[] params = // { "mammal", "man", "woman", "child", "god" };
         //		 other example...
		{"artificial intelligence", 
		"information retrieval", 
		"semantic web", 
		"ontologies",
		"service", 
		"metadata", 
		"natural language processing", 
		"semantics", 
		"language", 
		"processable" };
		int scopeModifier = WatsonService.LOCAL_NAME + WatsonService.LABEL;
		int entityTypeModifier = WatsonService.CLASS;
		int scInfo = WatsonSearch.SC_DLEXPR_INFO + WatsonSearch.SC_SIZE_INFO + WatsonSearch.SC_LANGUAGES_INFO;
		int entInfo = WatsonSearch.ENT_TYPE_INFO + WatsonSearch.ENT_LABEL_INFO;// +ENT_ANYRELATIONTO_INFO;
		SemanticContentResult[] sr = ws.searchBestCoverageWithRestriction(params,
				scopeModifier, entityTypeModifier, WatsonService.EXACT_MATCH, scInfo, entInfo);
		displayResult(sr);
	}
	
	private void displayResult(SemanticContentResult[] sr) {
		System.out.println("Number of results: " + sr.length);
		for (SemanticContentResult r : sr) {
			System.out.println("SC:: " + r.getURI() + " ("
					+ r.getDLExpressivness() + ", C:" + r.getNBClasses()
					+ ", P:" + r.getNBProperties() + ", I:"
					+ r.getNBIndividuals() + r.getSize() + "B)");
			String[] languages = r.getLanguages();
			if (languages != null) {
				System.out.print("  Languages: ");
				for (String l : languages)
					System.out.print(l + " ");
				System.out.println();
			}
			String[] locations = r.getLocations();
			if (locations != null) {
				System.out.print("  Locations: ");
				for (String l : locations)
					System.out.print(l + " ");
				System.out.println();
			}
			EntityResult[] er = r.getEntityResultList();
			for (EntityResult e : er) {
				System.out.println("\t" + e.getType() + "::" + e.getURI() + "("
						+ e.getLabel() + ")");
				String[][] relFrom = e.getRelationFrom();
				if (relFrom != null)
					for (String[] rf : relFrom)
						System.out.println("\t\t" + rf[1] + " -- " + rf[2]);
				String[][] relTo = e.getRelationTo();
				if (relTo != null)
					for (String[] rt : relTo)
						System.out.println("\t\t" + rt[2] + " -- " + rt[1]);
				String[][] literals = e.getLiterals();
				if (literals != null)
					for (String[] l : literals)
						System.out.println("\t\t" + l[1] + " = " + l[2]);
			}
		}

	}

}
