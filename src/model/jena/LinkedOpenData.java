package jena;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;



public class LinkedOpenData {
	public static String SUBCLASS_QUERY = "SUBCLASS_QUERY";
	public static String DBPEDIA = "<http://dbpedia.org/ontology/";
	
	public static void main(String[] args) {
		LinkedOpenData linkedOpenData = new LinkedOpenData();
		System.out.println(linkedOpenData.findOpenDataRelations("river","place"));
		
	}
	
	
	public static String PREFIX =
	
	"   PREFIX owl: <http://www.w3.org/2002/07/owl#> "+
	" 	PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
	" 	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
	" 	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
	" 	PREFIX foaf: <http://xmlns.com/foaf/0.1/>"+
	" 	PREFIX dc: <http://purl.org/dc/elements/1.1/>"+
	" 	PREFIX : <http://dbpedia.org/resource/>"+
	" 	PREFIX dbpedia2: <http://dbpedia.org/property/>"+
	" 	PREFIX dbpedia: <http://dbpedia.org/>"+
	" 	PREFIX skos: <http://www.w3.org/2004/02/skos/core#>		";
	
	public LinkedOpenData() {

	}
	
	
	public boolean findOpenDataRelations(String tagFirst, String tagSecond){
		Set<String> relations = new HashSet<String>();
		relations.addAll(search(tagFirst,SUBCLASS_QUERY));
		return relations.contains(capitalLetter(tagSecond));
	}
	
	private String capitalLetter(String s){
		return (s.length()>0)? Character.toUpperCase(s.charAt(0))+s.substring(1):s;
	}
	
	private String builQuery(String query,String type){
		StringBuilder builtQuery = new StringBuilder(LinkedOpenData.PREFIX);
		if (type.equals(SUBCLASS_QUERY)) {
			builtQuery.append( 
			"SELECT distinct ?s  ?o" +
			"       WHERE { " +
			"       <http://dbpedia.org/ontology/"+query+"> rdfs:subClassOf ?o  ." +
			" } ");			
			
		}else if (type.equals("xx")) {
			builtQuery.append( 
					"SELECT distinct ?s  ?o" +
					"       WHERE { " +
					"       <http://dbpedia.org/ontology/"+query+"> rdfs:subClassOf ?o  ." +
					" } ");	
		}
		
		return builtQuery.toString();
	}


	
	/**
	 * @param sparqlQueryString
	 * @param type
	 * @return
	 */
	public Set<String> search(String sparqlQueryString,String type){
		Set<String> resultSet = new HashSet<String>();
 		String capitalLetter = capitalLetter(sparqlQueryString);
		String finalQuery = builQuery(capitalLetter, type);
		Query query = QueryFactory.create(finalQuery);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		try {
			ResultSet results = qexec.execSelect();
			for ( ; results.hasNext() ; )
		{
			QuerySolution soln = results.nextSolution() ;
			String o = soln.get("o").asNode().getLocalName();
			resultSet.add(o);
		}
	
		}
		finally { qexec.close() ; }
		return resultSet;

		}




	
	
	
	

	/**
	 * @param individual
	 * @return
	 */
	public HashSet<String> getRelatedTerms(String individual){
		HashSet<String> relatedTerms = new HashSet<String>();
		String queryString = 
		" PREFIX owl: <http://www.w3.org/2002/07/owl#> "+
		" 	PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
		" 	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
		" 	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
		" 	PREFIX foaf: <http://xmlns.com/foaf/0.1/>"+
		" 	PREFIX dc: <http://purl.org/dc/elements/1.1/>"+
		" 	PREFIX : <http://dbpedia.org/resource/>"+
		" 	PREFIX dbpedia2: <http://dbpedia.org/property/>"+
		" 	PREFIX dbpedia: <http://dbpedia.org/>"+
		" 	PREFIX skos: <http://www.w3.org/2004/02/skos/core#>		"+
		" SELECT ?z ?p ?c ?m ?o " +
		" WHERE { " +
		"   ?rs owl:unionOf ?z . " +
		" ?z ?m ?o." +
		" (?o ?p ?c)." +
		"}";
		String url = "http://dbpedia.org/";
		Query query = QueryFactory.create(queryString);
		//QueryExecution queryExecutione = QueryExecutionFactory.create(query,OntologyUtil.getInstance().getOntologyModel());
		QueryExecution queryExecutione = QueryExecutionFactory.create(query);
		
	 	QueryExecution remote = QueryExecutionFactory.sparqlService(url,
    			query);
		//QueryExecution qe = QueryExecutionFactory.create(query,OntologyUtil.getInstance().getOntologyModel());
	
		
		ResultSet rs = (ResultSet) remote.execConstruct();
		String temp = "";
		String individualValue = "";
		while(rs.hasNext()){
			temp = rs.next().toString();
			individualValue = temp.substring(temp.indexOf("\"")+1, temp.lastIndexOf("\""));
			relatedTerms.add(individualValue);
		}
		queryExecutione.close();
		return relatedTerms;
	}
	
	public void test(){
	try{
	 String query = "* <http://xmlns.com/foaf/0.1/workplaceHomepage> <http://www.deri.ie/>"
	    	+    		   "* <http://xmlns.com/foaf/0.1/knows> *"
	    	;
	    String url = "http://sindice.com/api/v2/search?qt=advanced&q="+URLEncoder.encode(query, "utf-8")+"&qt=advanced";
	    
	    URL urlObj = new URL(url);
	    URLConnection con = urlObj.openConnection();
		if (con!=null){
			Model model = ModelFactory.createDefaultModel();
	        model.read(con.getInputStream(), null);
		}
		
		System.out.println(url);
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String SEARCH_LINK = "http://sindice.com/vocab/search#link";

	  
	  private final String baseURL = "http://sindice.com/api/v2/search?qt=advanced&q=";

	  private String getURL(String query) {
	    try {
	      return baseURL + URLEncoder.encode(query, "utf-8");
	    } catch (UnsupportedEncodingException e) {
	      throw new RuntimeException(e);
	    }
	  }

	  public void findFriends() {
	    String query = "* <http://xmlns.com/foaf/0.1/workplaceHomepage> <http://www.deri.ie/> AND  " +
	    		   "* <http://xmlns.com/foaf/0.1/knows> *";
	    String url = getURL(query);
	    Model model = ModelFactory.createDefaultModel();
	    model.read(url);
	    StmtIterator it = model.listStatements(null, model.createProperty(SEARCH_LINK), (Resource)null);
	    
	    while (it.hasNext()) {
	      Statement stmt = it.nextStatement();
	      Resource link = stmt.getResource();
	      model.read(link.getURI());
	    }
	    
	    Query sparql = QueryFactory.create(
	      "SELECT ?aname ?bname " + 
	        "WHERE { " +
	          "?a <http://xmlns.com/foaf/0.1/knows> ?b . " + 
	          "?a <http://xmlns.com/foaf/0.1/name> ?aname . " +
	          "?b <http://xmlns.com/foaf/0.1/name> ?bname . " +
	        "}");
	    QueryExecution qe = QueryExecutionFactory.create(sparql, model);
	    ResultSet rs = qe.execSelect();
	    ResultSetFormatter.out(System.out, rs);
	    qe.close();
	  }

	
	

}
