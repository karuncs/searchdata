package sesame;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.openrdf.OpenRDFException;
import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.query.BindingSet;
import org.openrdf.query.GraphQuery;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.resultio.sparqlxml.SPARQLResultsXMLWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandler;
import org.openrdf.rio.rdfxml.RDFXMLWriter;
import org.openrdf.rio.turtle.TurtleWriter;
import org.openrdf.sail.nativerdf.NativeStore;

import parser.DiggParser;
import parser.FlickrParser;
import parser.KiWiDeliParser;
import util.GratStringUtil;
import util.StringUtils;
import aau.Resource;
import aau.SemanticGraph;
import aau.Tag;
import aau.User;
import data.Data;
import delicious.DeliciousData;


public class Sesame {
	
	
	private static Repository myRepository;
	
	
	public static Repository getMyRepository() {
		return myRepository;
	}

	public static void setMyRepository(Repository myRepository) {
		Sesame.myRepository = myRepository;
	}

	public static Repository getRepository() {
		File dataDir = new File(IConstant.REPOSITORY);
		String indexes = "spoc,posc,cosp";
		Repository myRepository = new SailRepository(new NativeStore(dataDir,indexes));
		try {
			myRepository.initialize();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		setMyRepository(myRepository);
		return myRepository;
    }
	
	public static Repository getFisicalRepository() {
		File dataDir = new File(IConstant.FISICAL_REPOSITORY);
		String indexes = "spoc,posc,cosp";
		Repository myRepository = new SailRepository(new NativeStore(dataDir,indexes));
		try {
			myRepository.initialize();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		setMyRepository(myRepository);
		return myRepository;
	}
	
	public static Repository getFlickrRepository() {
		File dataDir = new File(IConstant.FLICKR_REPOSITORY);
		String indexes = "spoc,posc,cosp";
		Repository myRepository = new SailRepository(new NativeStore(dataDir,indexes));
		try {
			myRepository.initialize();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		setMyRepository(myRepository);
		return myRepository;
	}
	
	public static Repository getDiggRepository() {
		File dataDir = new File(IConstant.DIGG_REPOSITORY);
		String indexes = "spoc,posc,cosp";
		Repository myRepository = new SailRepository(new NativeStore(dataDir,indexes));
		try {
			myRepository.initialize();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		setMyRepository(myRepository);
		return myRepository;
	}
	
	
	public static void main(String[] args) throws RepositoryException {
	
			
//		File dataDir = new File(IConstant.REPOSITORY);
//		String indexes = "spoc,posc,cosp";
//		Repository myRepository = new SailRepository(new NativeStore(dataDir,indexes));
//		myRepository.initialize();
		
		try {
			
			System.out.println("  ATTENTION to THE REPOSITORY !!!!");
		//Data data = new Data();
		
		

		//Sesame.saveRDF(myRepository,"kiwiOwl.owl");
		//Sesame.writeXMLRDF(myRepository);
		//Sesame.evaluateRDFGraph(myRepository);
		
		
		//Sesame.saveKiWiData(myRepository,null);
		
		//Sesame.showStatements(myRepository.getConnection());
		
		//Sesame.saveKiWiData(myRepository, data.getData());
		
		//showGraphStatements(myRepository.getConnection());
		
		//Sesame.writeXMLInRDFFile(myRepository);
		
		//Sesame.eclwp(myRepository);
		
		//Sesame.runKiwi(myRepository);

			//saveFlickrData();
			//getAllUsersFlirkRepository();
		
		
			//saveDeliciousApiData();
		//calculateTagWeight(getFlickrRepository() , Sesame.loadKiWiData(getFlickrRepository()).size());

			
			//System.out.println(Sesame.loadKiWiData(getRepository()).size());
			
			//deleteResources(getRepository().getConnection(), loatTags());
			
		//System.out.println(Sesame.loadKiWiData(getRepository()).size());
		//System.out.println(Sesame.loadKiWiData(getFlickrRepository()).size());
		//System.out.println(Sesame.loadKiWiData(getDiggRepository()).size());
			
			//showSemantiTagString(Sesame.loadKiWiData(myRepository));
			
			
			
			//selectAllTags(getFlickrRepository());
			//selectAllTags(getRepository());
			//selectAllTags(getDiggRepository());
			
			//getAllUsersDeliciousRepository();
			
		//Sesame.removeRDFGraph(myRepository);
//		
			getMostFrequentTagByUser(myRepository.getConnection(), "user_2034");
		
			List<String> tags = selectTagsByUser(getRepository().getConnection(), "user_2034");
			
			Set<String> tagsUser = new HashSet<String>();
			for (String string : tags) {
				tagsUser.add(string);
			}
			for (String string2 : tagsUser) {
				System.out.println(extractBrackets(string2));
			}
			
			
			
		//listTagAmmount(myRepository, "blog");
	
			//queryEveryThing(myRepository.getConnection(), "blog");
			
			
			//saveDiggData();
			//System.out.println(Sesame.loadKiWiData(getDiggRepository()).size());
			
	
		
		} finally {
			getMyRepository().getConnection().close();
		}


	}
	
	public static void getAllUsersDeliciousRepository(){		
		for (int i = 2000; i < 2045; i++) {
			if (i!=2007 && i!=20041) {
				try {
					// System.out.println("user_"+i +"  has most frequent tags " + getMostFrequentTagByUser(getRepository().getConnection(), "user_"+i));
					
					System.out.println("user_"+i +"	" + getMostFrequentTagByUser(getRepository().getConnection(), "user_"+i));
				} catch (RepositoryException e) {

					e.printStackTrace();
				}	
			}
			
		}
	}
	
	public static void getAllUsersFlirkRepository(){
		getFlickrRepository();
		Set<String> users = getAllUsers(getMyRepository());
		for (String user : users) {
			try {
				System.out.println("user "+user +"  has most frequent tags " + getMostFrequentTagByUser(getMyRepository().getConnection(), user));
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void showSemantiTagString(Set<Resource> kiwiAllResources){
		for (Resource kiWiResource : kiwiAllResources) {
			System.out.println("  ");
			//System.out.println(kiWiResource.getSemanticTagString());
			System.out.println(kiWiResource.getUri() +"  "+ kiWiResource.getSemanticTagString());
		}
	}
	
	
	
	
	public static void saveDeliciousApiData() {
		DeliciousData deliciousData = new DeliciousData();
		Sesame.saveKiWiData(Sesame.getRepository(), deliciousData.getDeliciousBookmarkings());
		//deliciousData.getDeliciousBookmarkings();
		
    }	
	
	/**
	 * 
	 */
	public static void saveFlickrData() {
		Sesame.saveKiWiData(Sesame.getFlickrRepository(), FlickrParser.extractKiWI());
	}	
	
	/**
	 * 
	 */
	public static void saveDiggData() {
		Sesame.saveKiWiData(Sesame.getDiggRepository(), DiggParser.parseDigg());
		System.out.println(Sesame.loadKiWiData(getDiggRepository()).size());
	}	
	
	public static void saveDeliciousData(Repository myRepository) {
		KiWiDeliParser kiWiDeliParser = new KiWiDeliParser();
		String stringTag="design,blog,video,software,tools,music,programming,webdesign,reference,tutorial,art,web,howto,javascript,free,linux,web2.0,development,google,inspiration,photography,news,food,flash,css,blogs,education,business,technology,travel";		
		for (String termTag : StringUtils.getToken(stringTag, ",")) {
			Set<Resource> kiwiAllResources = kiWiDeliParser.getKiWiDeliData("http://delicious.com/popular/"+termTag);
			List<Resource> kiwiAllResourcesList = new ArrayList<Resource>(kiwiAllResources);
			Sesame.saveKiWiData(myRepository, kiwiAllResourcesList);
		}		
    }		
	
	public static void eclwp(Repository myRepository){
		Sesame.removeRDFGraph(myRepository);
		Sesame.saveRDF(myRepository,"kiwiOwl.owl");
		Sesame.writeXMLInRDFFile(myRepository);
	}
	
	public static void runKiwi(Repository myRepository){
		Data data = new Data();
		Sesame.removeRDFGraph(myRepository);

		Sesame.saveKiWiData(myRepository, data.getData());
		Sesame.writeXMLInRDFFile(myRepository);
	}	
	
	
	
	/**
	 * @param myRepository
	 */
	public static void removeRDFGraph(Repository myRepository){
		try {
		   RepositoryConnection con = myRepository.getConnection();

		   removeAllStatements(con);
		   ValueFactory f = myRepository.getValueFactory();
		   
		   try {
			   String location = "http://www.w3.org/2000/01/rdf-schema#";
			   String baseURI = location;
			   URL url = new URL(location);
			   URI context = f.createURI(location);
			   con.add(url, baseURI, RDFFormat.RDFXML, context);
			   con.remove(context, null, null);
			   con.clear(context);
		   }
		   finally {
		      con.close();
		   }
		}
		
		
		catch (Exception e) {
		   e.printStackTrace();
		}
		
		GratStringUtil.deleteDirectoryRecursively(IConstant.REPOSITORY);
	}	
	
	/**
	 * @param con
	 */
	public static void removeAllStatements(RepositoryConnection con){
	RepositoryResult<Statement> statements;
		try {
				statements = con.getStatements(null, null, null, true);
				
				try {
				   while (statements.hasNext()) {
				      Statement st = statements.next();
					   System.out.println("CONTEXT  "+st.getContext());
					   System.out.println("URI  "+st.getPredicate());
					   System.out.println("SUBJECT  "+st.getSubject());
					   System.out.println("OBJECT  "+st.getObject());
					   con.remove(st,st.getContext());
				   }
				}
				finally {
				   statements.close();
				}
			} catch (RepositoryException e) {
				e.printStackTrace();
			}		
	}

	
	public static void showStatements(RepositoryConnection con){
		RepositoryResult<Statement> statements;
		try {
			statements = con.getStatements(null, null, null, true);
			try {
			   while (statements.hasNext()) {
			      Statement st = statements.next();
				   System.out.println("____________________________________________________");				   
				   System.out.println("CONTEXT  "+st.getContext());
				   System.out.println("URI  "+st.getPredicate());
				   System.out.println("SUBJECT  "+st.getSubject());
				   System.out.println("OBJECT  "+st.getObject());	
				   System.out.println("____________________________________________________");	
			   }
			}
			finally {
			   statements.close();
			}
			} catch (RepositoryException e) {
				e.printStackTrace();
			}		
		}
	
	
	public static void showGraphStatements(RepositoryConnection con){
		try {
			String query = "CONSTRUCT * FROM {x} rdfs:label {y} where isLiteral(y)";
			GraphQuery graph  = con.prepareGraphQuery(QueryLanguage.SERQL, query);
			GraphQueryResult graphQueryResult =  graph.evaluate();
			while (graphQueryResult.hasNext()) {
				Statement st = (Statement) graphQueryResult.next();
				   System.out.println("____________________________________________________");				   
				   System.out.println("CONTEXT  "+st.getContext());
				   System.out.println("URI  "+st.getPredicate());
				   System.out.println("SUBJECT  "+st.getSubject());
				   System.out.println("OBJECT  "+st.getObject());	
				   System.out.println("____________________________________________________");					
				
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		} finally{
			try {
				con.close();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param con
	 * @param userName
	 * @return
	 */
	public static Statement getUserStatement(RepositoryConnection con, String userName) {
		Statement userStatement  = null;
		try {
			String query = "CONSTRUCT * FROM {x} ex:rdfname {y} where y LIKE \""+userName+"\" IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";
			GraphQuery graph  = con.prepareGraphQuery(QueryLanguage.SERQL, query);
			GraphQueryResult graphQueryResult =  graph.evaluate();
			if(graphQueryResult.hasNext()) {
				userStatement = (Statement) graphQueryResult.next();
//				if(graphQueryResult.hasNext()) {
//					//System.err.println("WRONG !!! More than on USER !!!!");
//				}
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		} 
		return userStatement;
	}
	
	/**
	 * @param con
	 * @param link
	 */
	public static void getKiWiStatement(RepositoryConnection con, String link) {
		Statement kiWiStatement  = null;
		try {
			String query = "CONSTRUCT * FROM {x} ex:rdflink {y} where y LIKE \""+link+"\" IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";
			GraphQuery graph  = con.prepareGraphQuery(QueryLanguage.SERQL, query);
			GraphQueryResult graphQueryResult =  graph.evaluate();
			if(graphQueryResult.hasNext()) {
				kiWiStatement = (Statement) graphQueryResult.next();
				   System.out.println("____________________________________________________");				   
				   System.out.println("CONTEXT  "+kiWiStatement.getContext());
				   System.out.println("URI  "+kiWiStatement.getPredicate());
				   System.out.println("SUBJECT  "+kiWiStatement.getSubject());
				   System.out.println("OBJECT  "+kiWiStatement.getObject());	
				   System.out.println("____________________________________________________");							
				
				if(graphQueryResult.hasNext()) {
					//System.err.println("WRONG !!! More than on USER !!!!");
				}
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		} 
	}	
	
	/**
	 * @param con
	 * @param tagName
	 * @return
	 */
	public static Statement getTagStatement(RepositoryConnection con, String tagName) {
		Statement tagStatement  = null;
		try {
			String query = "CONSTRUCT * FROM {x} ex:rdftagName {y} where y LIKE \""+tagName+"\" IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";
			GraphQuery graph  = con.prepareGraphQuery(QueryLanguage.SERQL, query);
			GraphQueryResult graphQueryResult =  graph.evaluate();
			
			if(graphQueryResult.hasNext()) {
				tagStatement = (Statement) graphQueryResult.next();
				if(graphQueryResult.hasNext()) {
					//System.err.println("WRONG !!! More than on TAG !!!!");
				}
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		} 
		return tagStatement;
	}	
	
	
	/**
	 * @param con
	 * @param tagName
	 * @return
	 */
	public static Statement getTagStatementByURI(RepositoryConnection con, String tagURI) {
		Statement tagStatement  = null;
		try {
			System.out.println(tagURI);
			String query = "CONSTRUCT * FROM {x} rdfs:label {y} where x LIKE \""+tagURI+"\" ";
			GraphQuery graph  = con.prepareGraphQuery(QueryLanguage.SERQL, query);
			GraphQueryResult graphQueryResult =  graph.evaluate();
			
			if(graphQueryResult.hasNext()) {
				tagStatement = (Statement) graphQueryResult.next();
				if(graphQueryResult.hasNext()) {
					System.err.println("WRONG !!! More than on TAG !!!!");
				}
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		} 
		return tagStatement;
	}	
	
	/**
	 * @param con
	 * @param tagName
	 * @return
	 */
	public static Statement getEveryStatementByURI(RepositoryConnection con, String name) {
		Statement statment  = null;
		try {
			
			String query = "CONSTRUCT * FROM {x} rdfs:label {y} where y LIKE \""+name+"\" ";
			GraphQuery graph  = con.prepareGraphQuery(QueryLanguage.SERQL, query);
			GraphQueryResult graphQueryResult =  graph.evaluate();
			
			if(graphQueryResult.hasNext()) {
				statment = (Statement) graphQueryResult.next();
				System.out.println(statment);
				
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		} 
		return statment;
	}	
	
	
	public static void evaluateRDFGraph(Repository myRepository){
		try {
		   RepositoryConnection con = myRepository.getConnection();
		   try {
			   GraphQueryResult graphResult = con.prepareGraphQuery(
					      QueryLanguage.SERQL, "CONSTRUCT * FROM {x} p {y}").evaluate();
			   while (graphResult.hasNext()) {
				   Statement st = graphResult.next();
				   
				   System.out.println("____________________________________________________");				   
				   System.out.println("CONTEXT  "+st.getContext());
				   System.out.println("URI  "+st.getPredicate());
				   System.out.println("SUBJECT  "+st.getSubject());
				   System.out.println("OBJECT  "+st.getObject());	
				   System.out.println("____________________________________________________");				   
				   
				   
				   TurtleWriter turtleWriter = new TurtleWriter(System.out);
				   con.prepareGraphQuery(QueryLanguage.SERQL,"CONSTRUCT * FROM {x} p {y}").evaluate(turtleWriter);

				}			   
		   }
		   finally {
		      con.close();
		   }
		}
		catch (OpenRDFException e) {
		   e.printStackTrace();
		}
		
	}	
	
	public static void saveRDF(Repository myRepository, String rdfName){
		File file = new File("rdf/"+File.separator+rdfName);
		String baseURI = "http://www.aau.fred/";

		try {
		   RepositoryConnection con = myRepository.getConnection();
		   try {
		      con.add(file, baseURI, RDFFormat.RDFXML);
		   }
		   finally {
		      con.close();
		   }
		}
		catch (OpenRDFException e) {
		   e.printStackTrace();
		}
		catch (IOException e) {
		   e.printStackTrace();
		}
	}
	
	/**
	 * @param myRepository
	 */
	public static void printXML(Repository myRepository){
		try {
		   ValueFactory f = myRepository.getValueFactory();	
		   
  		   RepositoryConnection con = myRepository.getConnection();		
		   String location = "rdf/kiwi_rdf";
		   File file = new File(location);	
		   String baseURI = "http://www.aau.fred/";
		   URI context = f.createURI(file.getAbsolutePath());		
		   con.add(file, baseURI, RDFFormat.RDFXML, context);	
		   RDFHandler rdfxmlWriter = new RDFXMLWriter(System.out);
		   con.export(rdfxmlWriter, context);
		}
		catch (OpenRDFException e) {
		   e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	
	/**
	 * @param con
	 */
	public static void writeXMLInRDFFile(RepositoryConnection con){
		try {
			 FileWriter rdfWriter = new FileWriter("rdf/kiwi_rdf_out.owl");
			 
			 con.export(new RDFXMLWriter(rdfWriter));
		}
		catch (OpenRDFException e) {
		   e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * @param myRepository
	 */
	public static void writeXMLInRDFFile(Repository myRepository){
		try {
			writeXMLInRDFFile(myRepository.getConnection());
		} catch (RepositoryException e) {
			e.printStackTrace();
		}finally{
			try {
				myRepository.getConnection().close();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
	}	
	
	/**
	 * @param con
	 * @param userEntity
	 */
	public static void createUser(RepositoryConnection con ,User userEntity) {
		  try {	
				if (validateUserEntity(userEntity)) {
					ValueFactory f = con.getRepository().getValueFactory();		
					URI instanceUser = f.createURI(IConstant.USER_INSTANCE+System.currentTimeMillis());
					URI user = f.createURI(IConstant.USER);
					URI name = f.createURI(IConstant.USER_NAME);		
					URI email = f.createURI(IConstant.USER_EMAIL);		
					Literal nameLiteral = f.createLiteral(userEntity.getName());
					Literal emailLiteral = f.createLiteral(userEntity.getEmail());
					Literal labelLiteral = f.createLiteral(instanceUser.getLocalName());
					con.add(instanceUser, RDF.TYPE, user);
					con.add(instanceUser, name, nameLiteral);
					con.add(instanceUser, email, emailLiteral);
					con.add(instanceUser, RDFS.LABEL, labelLiteral);
				}
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
	
	
	/**
	 * @param userEntity
	 * @return
	 */
	public static boolean validateUserEntity(User userEntity){
		boolean userValid = true;
		if(userEntity.getName().trim().equals("")) {
			userValid = false;
		}
		return userValid;
	}
	/**
	 * @param userEntity
	 * @return
	 */
	public static boolean validateTagEntity(Tag tagEntity){
		boolean tagValid = false;
		if(!tagEntity.getTagName().trim().equals("")) {
			tagValid = true;
		}
		return tagValid;
	}
	
	/**
	 * @param con
	 * @param tagEntity
	 * @throws Exception
	 */
	public static URI createTag(RepositoryConnection con, Tag tagEntity) throws Exception {
		URI instanceTag = null;  
		try {		
				if (validateTagEntity(tagEntity)) {			  
					ValueFactory f = con.getRepository().getValueFactory();		
					
					instanceTag = f.createURI(IConstant.TAG_INSTANCE+Math.random());
					URI tag = f.createURI(IConstant.TAG);
					URI user = f.createURI(IConstant.USER);
					
					URI tagName = f.createURI(IConstant.TAG_NAME);		
					URI rdfweight = f.createURI(IConstant.TAG_WEIGHT);
					URI rdftagFreq = f.createURI(IConstant.TAG_FREQUENCY);				
	
					Statement userStatement = getUserStatement(con, tagEntity.getTagAuthor().getName());
					if (userStatement==null) {
						throw new Exception("User "+ tagEntity.getTagAuthor().getName()+" must be created");
					}
					Literal tagNameLiteral = f.createLiteral(tagEntity.getTagName());
					Literal rdfweightLiteral = f.createLiteral(tagEntity.getWeight());
					Literal rdftagFreqLiteral = f.createLiteral(tagEntity.getTagFrequency());
					Literal labelLiteral = f.createLiteral(instanceTag.getLocalName());
	
					con.add(instanceTag, user, userStatement.getSubject());
					con.add(instanceTag, RDFS.LABEL, labelLiteral);
					con.add(instanceTag, RDF.TYPE, tag);
					con.add(instanceTag, tagName, tagNameLiteral);
					con.add(instanceTag, rdfweight, rdfweightLiteral);
					con.add(instanceTag, rdftagFreq, rdftagFreqLiteral);
				}
				
			} catch (RepositoryException e) {
				e.printStackTrace();
			} catch (MalformedQueryException e) {
				e.printStackTrace();
			} catch (QueryEvaluationException e) {
				e.printStackTrace();
			}
		return instanceTag;
		}
	
	/**
	 * @param con
	 * @param tagWeight
	 * @param tagURI
	 * @throws Exception
	 */
	public static void deleteResources(RepositoryConnection con, Set<String> uris) {
		//http://protege.stanford.edu/rdfkiwi_Instance_User1227081810968
		  try {		
			for (String uri : uris) {
			    ValueFactory f = con.getRepository().getValueFactory();	
				URI resource = f.createURI(uri);
				con.remove(resource, null, null);
				con.commit();
				System.out.println("deleting  "+uri);
			}
			} catch (RepositoryException e) {
				e.printStackTrace();
			} 
		}
	/**
	 * @param con
	 * @param tagWeight
	 * @param tagURI
	 * @throws Exception
	 */
	public static void updateTagWeight(RepositoryConnection con, float tagWeight, String tagURI) throws Exception {
		try {		
			ValueFactory f = con.getRepository().getValueFactory();	
			URI tag = f.createURI(tagURI);
			URI rdfweight = f.createURI(IConstant.TAG_WEIGHT);
			Literal rdfweightLiteral = f.createLiteral(tagWeight);
			con.remove(tag, rdfweight, null);
			//System.out.println("                        tagweight   "+rdfweightLiteral.doubleValue()+"   tagURI  "+tag.getLocalName());
			con.add(tag, rdfweight, rdfweightLiteral);	
			con.commit();
		} catch (RepositoryException e) {
			e.printStackTrace();
		} 
	}
	
	

	
	/**
	 * @param con
	 * @param tagFrequency
	 * @param tagURI
	 * @throws Exception
	 */
	public static void updateTagFrequency(RepositoryConnection con, float tagFrequency, String tagURI) throws Exception {
		  try {		
				ValueFactory f = con.getRepository().getValueFactory();	
				URI tag = f.createURI(tagURI);
				URI rdftagFreq = f.createURI(IConstant.TAG_FREQUENCY);
				Literal rdftagFreqLiteral = f.createLiteral(tagFrequency);
				con.remove(tag, rdftagFreq, null);
				con.add(tag, rdftagFreq, rdftagFreqLiteral);	
				con.commit();
				
			} catch (RepositoryException e) {
				e.printStackTrace();
			} 
		}	
	
	
	/**
	 * @param con
	 * @param kiWiResourceEntity
	 * @throws Exception
	 */
	public static void createKiWiResource(RepositoryConnection con, Resource kiWiResourceEntity) throws Exception {
		  try {	
			  String instanceKiWiURi = IConstant.KIWI_RESOURCE_INSTANCE+Math.random();
		  
			  if (!existKiWiResourceURI(con, instanceKiWiURi)){
					ValueFactory f = con.getRepository().getValueFactory();		
					URI instanceKiWi = f.createURI(instanceKiWiURi);
					URI link = f.createURI(IConstant.KIWI_RESOURCE_LINK);
					URI kiWiResourceType = f.createURI(IConstant.KIWI_RESOURCE);
					URI rdftags = f.createURI(IConstant.KIWI_RESOURCE_TAGS);
					URI desc = f.createURI(IConstant.KIWI_RESOURCE_DESC);
					URI user = f.createURI(IConstant.USER);
					
					Literal linkLiteral = f.createLiteral(kiWiResourceEntity.getLink());
					Literal descLiteral = f.createLiteral(kiWiResourceEntity.getDescription());
					Literal labelLiteral = f.createLiteral(instanceKiWi.getLocalName());
					
	
					Statement userStatement = getUserStatement(con, kiWiResourceEntity.getAuthor().getName());
					
					if (userStatement==null) {
						throw new Exception("User "+ kiWiResourceEntity.getAuthor().getName()+" must be created");
					}
					
					con.add(instanceKiWi, user, userStatement.getSubject());
					con.add(instanceKiWi, RDFS.LABEL, labelLiteral);
					con.add(instanceKiWi, RDF.TYPE, kiWiResourceType);
					con.add(instanceKiWi, link, linkLiteral);
					con.add(instanceKiWi, desc, descLiteral);
					URI tagUri = null;
					for (Tag tagEntity : kiWiResourceEntity.getTags()) {
						if (!existTagUserResource(con, tagEntity, kiWiResourceEntity)){
							tagUri = createTag(con, tagEntity);
						}
//						Statement tagStatement = getTagStatement(con, tagUri);
//						if (userStatement==null) {
//							throw new Exception("Tag "+ tagEntity.getTagName()+" must be created");
//						}
						
//						if ( (tagEntity.getTagName().equals("blog")) && tagEntity.getTagAuthor().getName().equals("user_2018") ) {
//							
//							System.out.println("addingg BLOG tag  "+tagUri);
//						}
						
						con.add(instanceKiWi, rdftags, tagUri);
					}
			  }
			} catch (RepositoryException e) {
				e.printStackTrace();
			} catch (MalformedQueryException e) {
				e.printStackTrace();
			} catch (QueryEvaluationException e) {
				e.printStackTrace();
			}
		}

	
	/**
	 * @param myRepository
	 * @param kiwiCollection
	 */
	public static void saveKiWiData(Repository myRepository, Collection<Resource> kiwiCollection){
		try {
			for (Resource kiWiResource : kiwiCollection) {
				RepositoryConnection con = myRepository.getConnection();
				try {
							if (!existUser(con, kiWiResource.getAuthor().getName(), kiWiResource.getAuthor().getEmail())){
								createUser(con, kiWiResource.getAuthor());
							}
							if (!existKiWiResource(con, kiWiResource.getLink())){
								//System.out.println("saving kiWiResource  "+kiWiResource.getDesc());	
								//System.out.println("saving user  "+kiWiResource.getAuthor().getName());							
								createKiWiResource(con, kiWiResource);
							}
							System.out.println("Saving...  "+kiWiResource.getDescription());
							con.commit();
					} finally {
					   con.close();
					}
			}
		} catch (OpenRDFException e) {
		   e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	/**
	 * @param myRepository
	 */
	public static void writeXMLRDF(Repository myRepository){
		 FileOutputStream out = null;
		try {
		   out = new FileOutputStream("rdf/result.xml");
		
		   SPARQLResultsXMLWriter sparqlWriter = new SPARQLResultsXMLWriter(out);

		   RepositoryConnection con = myRepository.getConnection();
		   try {
		      String queryString = "SELECT * FROM {x} p {y}";
		      TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
		      tupleQuery.evaluate(sparqlWriter);
		   }
		   finally {
		      con.close();
		   }
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
		   try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
	
	/**
	 * @param con
	 * @param userName
	 * @param email
	 * @return
	 */
	public static SemanticGraph queryEveryThing(RepositoryConnection con, String query){

		SemanticGraph semanticGraph = new SemanticGraph();
		//query = "user_2010";
		//query = "*user_20*";
		query = "*"+query+"*";

		try {
			  String queryString = "SELECT x, p, y FROM {x} p {y} where x LIKE \""+query+"\" OR p LIKE \""+query+"\" OR y LIKE \""+query+"\" IGNORE CASE";

			  TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
		      TupleQueryResult result = tupleQuery.evaluate();
		      
		      
		      while (result.hasNext()) {

       		    BindingSet bindingSet = result.next();
       		    Value valueOfSub = bindingSet.getValue("x");
       		    Value valueOfPred = bindingSet.getValue("p");
       		    Value valueOfObj = bindingSet.getValue("y");
       		    semanticGraph.addSubjects(valueOfSub.toString());
       		 	semanticGraph.addPredicates(valueOfPred.toString());
       		 	semanticGraph.addObjects(valueOfObj.toString());
       		    
//				System.out.println(valueOfSub.toString());
//				System.out.println(valueOfPred.toString());
//				System.out.println(valueOfObj.toString());
//				System.out.println(" NEW STUFF");
//				System.out.println("   ");
   			  }
			}catch (OpenRDFException e) {
			   e.printStackTrace();
			}
		return semanticGraph;	
	}
	

	
	
	/**
	 * @param con
	 * @param userName
	 * @param email
	 * @return
	 */
	public static boolean existUser(RepositoryConnection con, String userName, String email){
		boolean resourceExist = false;
		try {
			String queryString = "SELECT x, y FROM {x} ex:rdfname {y}; ex:rdfemail {z} WHERE y LIKE \""+userName+"\"  AND z LIKE \""+email+"\" IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";
			
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			if (result.hasNext()) {
				resourceExist = true;
//				if(result.hasNext()) {
//					System.err.println("WRONG !!! More than on USER with the same data !!!!");
//				}
				result.close();
			}
		}catch (OpenRDFException e) {
			e.printStackTrace();
		}
		return resourceExist;	
	}
	
	/**
	 * @param con
	 * @param userName
	 * @param email
	 * @return
	 */
	public static boolean existKiWiResource(RepositoryConnection con, String link){
		boolean resourceExist = false;
		try {
			  String queryString = "SELECT x, y FROM {x} ex:rdflink {y} WHERE y LIKE \""+link+"\" IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";

			  TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
		      TupleQueryResult result = tupleQuery.evaluate();
		      if (result.hasNext()) {
				resourceExist = true;
//				if(result.hasNext()) {
//					System.err.println("WRONG !!! More than on USER with the same data !!!!");
//				}
				result.close();
   			  }
			}catch (OpenRDFException e) {
			   e.printStackTrace();
			}
		return resourceExist;	
	}	
	
	/**
	 * @param con
	 * @param userName
	 * @param email
	 * @return
	 */
	public static boolean existKiWiResourceURI(RepositoryConnection con, String uri){
		boolean resourceExist = false;
		try {
			String queryString = "SELECT x, y FROM {x} rdf:type {y} WHERE x LIKE \""+uri+"\" IGNORE CASE";
			
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			if (result.hasNext()) {
				resourceExist = true;
				
	    		  BindingSet bindingSet = result.next();
	    		  Value valueOfX = bindingSet.getValue("x");
	    		  System.out.println("rec  "+valueOfX);
				
//				if(result.hasNext()) {
//					System.err.println("WRONG !!! More than on USER with the same data !!!!");
//				}
				result.close();
			}
		}catch (OpenRDFException e) {
			e.printStackTrace();
		}
		return resourceExist;	
	}	
	
	/**
	 * @param con
	 * @param tag
	 * @param kiWiResource
	 * @return
	 */
	public static boolean existTagUserResource(RepositoryConnection con, Tag tag, Resource kiWiResource){
		boolean resourceExist = false;
		try {
		      String queryString = "SELECT link FROM {x} rdf:type {y}; ex:rdflink {link};" +
	      		"[ex:rdftags {tags} ex:rdftagName {tagName}; ex:rdfweight {weight}; ex:rdftagFreq {tagFreq}; [ex:rdfUser {authorTag} ex:rdfname {authorTagName}; ex:rdfemail {authorTagEmail}]] " +
	      		" WHERE y LIKE \"http://protege.stanford.edu/rdfKiWiResource\" AND link LIKE \""+extractBrackets(kiWiResource.getLink())+"\"" +
	      		"  AND authorTag LIKE \""+kiWiResource.getAuthor().getUri()+"\" AND tags LIKE \""+tag.getUri()+"\" " +
	      		"  IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";			  

			  TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
		      TupleQueryResult result = tupleQuery.evaluate();
		      if (result.hasNext()) {
				resourceExist = true;

//				if(result.hasNext()) {
//					System.err.println("WRONG !!! More than on USER with the same data !!!!");
//				}
				result.close();
   			  }
			}catch (OpenRDFException e) {
			   e.printStackTrace();
			}
		return resourceExist;	
	}	
	
	
	/**
	 * @param myRepository
	 * @return
	 */
	public static Set<Resource> loadKiWiData(Repository myRepository){
		int i = 0;

		Set<Resource> kiwiAllResources = new HashSet<Resource>();

		Resource kiWiResource = null;

		try {
			   RepositoryConnection con = myRepository.getConnection();

			   try {
			      String queryString = "SELECT  DISTINCT x, y, desc, link, user, userName, email FROM " +
			      		"{x} rdf:type {y}; ex:rdfdesc {desc}; ex:rdflink {link};" +
			      		"[ex:rdfUser {user} ex:rdfname {userName}; ex:rdfemail {email}] " +
			      		"where y like \"http://protege.stanford.edu/rdfKiWiResource\" IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";
		      
			      TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
			      TupleQueryResult result = tupleQuery.evaluate();
			      try {
			    	  //while (result.hasNext() && i < 500) {
			    	  while (result.hasNext()) {
			    		  
			    		  BindingSet bindingSet = result.next();
			    		  Value valueOfX = bindingSet.getValue("x");
			    		  Value valueOfDesc = bindingSet.getValue("desc");
			    		  Value valueOfLink = bindingSet.getValue("link");
			    		  Value valueOfUser = bindingSet.getValue("user");			    		  
			    		  Value valueOfUserName = bindingSet.getValue("userName");
			    		  Value valueOfEmail = bindingSet.getValue("email");
			    		  
			    		  //KiWiResource			    		  
			    		  kiWiResource = new Resource();
			    		  kiWiResource.setDescription(Sesame.extractBrackets(valueOfDesc.toString()));
			    		  kiWiResource.setLink(Sesame.extractBrackets(valueOfLink.toString()));
			    		  kiWiResource.setUri(valueOfX.toString());

			    		  if (valueOfUserName!=null && valueOfEmail!=null && valueOfUser!=null ) {
			    		  //User			    		  
			    		  User user = new User();
			    		  user.setName(Sesame.extractBrackets(valueOfUserName.toString()));
			    		  user.setEmail(Sesame.extractBrackets(valueOfEmail.toString()));
			    		  user.setUri(Sesame.extractBrackets(valueOfUser.toString()));
			    		 
			    		  kiWiResource.setAuthor(user);

			    		  //Tag
			    		  loadTag(con, valueOfX.stringValue(), kiWiResource);

			    		  //System.out.println(valueOfX);
//
//			    		  System.out.println(valueOfDesc);
			    		  //System.out.println(valueOfLink);
//
//			    		  System.out.println(valueOfUser);			    		  
			    		  //System.out.println(valueOfUserName);
//			    		  System.out.println(valueOfEmail);
			    		  kiwiAllResources.add(kiWiResource);
			    		  i++;
			    		}
			    	  }
			    	  System.out.println("Resources in the Repository     "+  kiwiAllResources.size());
			    	  //populateMostFrequentTags(kiwiAllResources,con);
			      }
			      finally {
			         result.close();
			      }
			   }
			   finally {
			      con.close();
			   }
			}
			catch (OpenRDFException e) {
			   e.printStackTrace();
			}
		
		return kiwiAllResources;	
	}
	
	
	
	
	/**
	 * @param kiwiAllResources
	 * @param con
	 */
	private static void populateMostFrequentTags(Set<Resource> kiwiAllResources, RepositoryConnection con){
		Set<String> users = new HashSet<String>();
		for (Resource kiWiResource : kiwiAllResources) {
			User user = kiWiResource.getAuthor();
			if (!users.contains(user.getUri())) {
				user.setTopTags(getMostFrequentTagByUser(con, Sesame.extractBrackets(user.getName())));
				users.add(user.getUri());
			}
			
		}
	}
	
	/**
	 * @param link
	 * @return
	 */
	public static String extractBrackets(String link){
		String newS = new String();
		for (int i = 0; i < link.length(); i++) {
			 newS = link.replace("\"","");
		}
		return newS.trim();
	}
	
	
	/**
	 * @param con
	 * @param kiWiResourceResource
	 * @param kiWiResource
	 */
	public static void loadTag(RepositoryConnection con, String kiWiResourceResource, Resource kiWiResource){

		Set<Tag> tags = new HashSet<Tag>();
		try {
			      String queryString = "SELECT link, tags, tagName, weight, tagFreq, authorTag , authorTagName, authorTagEmail FROM {x} rdf:type {y}; ex:rdflink {link};" +
			      		"[ex:rdftags {tags} ex:rdftagName {tagName}; ex:rdfweight {weight}; ex:rdftagFreq {tagFreq}; [ex:rdfUser {authorTag} ex:rdfname {authorTagName}; ex:rdfemail {authorTagEmail}]] " +
			      		" WHERE x LIKE \""+kiWiResourceResource+"\" AND y LIKE \"http://protege.stanford.edu/rdfKiWiResource\" AND link LIKE \""+extractBrackets(kiWiResource.getLink())+"\"  IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";
			      TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
			      TupleQueryResult result = tupleQuery.evaluate();
			      try {

			    	  while (result.hasNext()) {
			    		  
			    		  BindingSet bindingSet = result.next();

			    		  Value valueOfTagName = bindingSet.getValue("tagName");
			    		  Value valueOfWeight = bindingSet.getValue("weight");
			    		  Value valueOfTagFreq = bindingSet.getValue("tagFreq");
			    		  Value valueOfTags = bindingSet.getValue("tags");
		
			    		  Value valueOfAuthorTag = bindingSet.getValue("authorTag");
			    		  Value valueOfAuthorTagName = bindingSet.getValue("authorTagName");
			    		  Value valueOfAuthorTagEmail = bindingSet.getValue("authorTagEmail");

			    		  //Tag			    		  
			    		  User authorTag = new User();
			    		  if (valueOfTagName!=null && valueOfAuthorTag!=null && valueOfAuthorTagName!=null && valueOfAuthorTagEmail!=null && valueOfTags!=null) {
				    		  authorTag.setUri(Sesame.extractBrackets(valueOfAuthorTag.toString()));
				    		  authorTag.setName(Sesame.extractBrackets(valueOfAuthorTagName.toString()));
				    		  authorTag.setEmail(Sesame.extractBrackets(valueOfAuthorTagEmail.toString()));
				    		  Tag tag = new Tag(Sesame.extractBrackets(valueOfTagName.stringValue()),authorTag);
				    		  tag.setUri(valueOfTags.toString());
				    		  Literal weightValue = new LiteralImpl(valueOfWeight.stringValue());
				    		  Literal tagFreqValue = new LiteralImpl(valueOfTagFreq.stringValue());
				    		  tag.setWeight(Float.valueOf(weightValue.stringValue()));
				    		  tag.setTagFrequency(Float.valueOf(tagFreqValue.stringValue()));
				    		  tags.add(tag);
				    		  kiWiResource.setTags(tags);
			    		  }
			    		  

			    		  //System.out.println(" Tag "+valueOfTagName);
//			    		  System.out.println(" URI da Tag "+tag.getUri());
//			    		  System.out.println(" URI do usuario da Tag "+tag.getTagAuthor().getUri());
//
//			    		  System.out.println(valueOfWeight);
//
//			    		  System.out.println(valueOfAuthorTagName);
//			    		  System.out.println(valueOfAuthorTagEmail);
			    		}
			      }
			      finally {
			         result.close();
			      }
			}
			catch (OpenRDFException e) {
			   e.printStackTrace();
			}	
	}
	
	
	public static List<String> selectCleanTagsByUser(RepositoryConnection con, String userName){
		List<String> sets = selectTagsByUser(con,userName);
		Set<String> cleansets = new HashSet<String>();
		for (String string : sets) {
			cleansets.add(string.toLowerCase().trim());
		}
		return new ArrayList<String>(cleansets);
	}
	
	/**
	 * @param con
	 * @param userName
	 * @return
	 */
	public static List<String> selectTagsByUser(RepositoryConnection con, String userName){
		
		List<String> tags = new ArrayList<String>();
		Set<String> tagURIs = new HashSet<String>();
		
		try {
			String queryString = "SELECT  tagName, tags, authorTagName FROM {x} rdf:type {y};" +
			"[ex:rdftags {tags} ex:rdftagName {tagName}; ex:rdfweight {weight}; ex:rdftagFreq {tagFreq}; [ex:rdfUser {authorTag} ex:rdfname {authorTagName}; ex:rdfemail {authorTagEmail}]] " +
			" WHERE y LIKE \"http://protege.stanford.edu/rdfKiWiResource\" AND authorTagName LIKE \""+userName+"\"  IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					Value valueOfTagName = bindingSet.getValue("tagName");
					Value tagURI = bindingSet.getValue("tags");

					if (!tagURIs.contains(extractBrackets(tagURI.toString()))){
						tags.add(Sesame.extractBrackets(valueOfTagName.toString()));
						tagURIs.add(extractBrackets(tagURI.toString()));
					}
				}
			}
			finally {
				result.close();
			}
		}
		catch (OpenRDFException e) {
			e.printStackTrace();
		}
		
		return tags;	
	}	
	
	
	
	
	/**
	 * @param tags
	 */
	private static List<Term> calculateTagFrequencyByUser(List<String> tags){
		TreeMap<String,Integer> tree = new TreeMap<String,Integer>();
		Set<String> singleTag = new HashSet<String>();
		List<Term> terms = new ArrayList<Term>();
		for (String tag : tags) {
			if (!singleTag.contains(tag)) {
				//System.out.println("Frequency of "+   tag  +"  "+  Collections.frequency(tags, tag));
				tree.put(tag, Collections.frequency(tags, tag));
				Term newTerm = new Term(tag,Collections.frequency(tags, tag));
				terms.add(newTerm);
			}
			singleTag.add(tag);
		}
		Collections.sort(terms);
		return terms;
	}
	
	
	/**
	 * @param tags
	 */
	public static Set<String> getMostFrequentTagByUser(RepositoryConnection con, String userName){
		Set<String> mostFrequentTags = new HashSet<String>();
		List<Term> terms = calculateTagFrequencyByUser(selectTagsByUser(con, userName));
	
		Float amount = new Float(IConstant.MINIMAL_AMOUNT_FREQUENT_TAG);
		if (terms!=null && terms.size()>0 && terms.get(0)!=null) {
			//System.out.println(terms.get(0).getValue());
			amount = new Float(terms.get(0).getValue() * 0.7f);
		}
		for (int i = 0; i < terms.size() && terms.get(i).getValue()>= amount && amount >= IConstant.MINIMAL_AMOUNT_FREQUENT_TAG; i++) {
		//for (int i = 0; i < terms.size() ; i++) {
				//System.out.println(terms.get(i).getTerm() +"           "+  terms.get(i).getValue()  );
				mostFrequentTags.add(extractBrackets(terms.get(i).getTerm()));	
		}
		return mostFrequentTags;
	}

	/**
	 * @param myRepository
	 * @param resorcesAmount
	 */
	public static void calculateTagWeight(Repository myRepository, int resorcesAmount){
		Map<String,Float> tagNames = new HashMap<String,Float>();
		
		
		
		TupleQueryResult result = null;
		
		try {
		
			RepositoryConnection con = myRepository.getConnection();
			try {
				String queryString = "SELECT distinct x, tagName, uri  FROM {x} rdfs:label {uri}; rdf:type {tagType}; ex:rdftagName {tagName}; ex:rdfweight {weight} WHERE tagType LIKE \"http://protege.stanford.edu/rdfTag\" IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";			   
				TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
				result = tupleQuery.evaluate();
				try {

					while (result.hasNext()) {
						BindingSet bindingSet = result.next();
						Value tagNameValue = bindingSet.getValue("tagName");
						Value uri = bindingSet.getValue("uri");
						String uriTag = IConstant.STANFOR_NS + extractBrackets(uri.toString());
						String tagName = extractBrackets(tagNameValue.toString());
						if (!tagNames.containsKey(tagName)) {
								float tagWeight = getTagAmmount(myRepository, tagName)/resorcesAmount;
								tagNames.put(tagName, new Float(tagWeight));
								updateTagWeight(con, tagNames.get(tagName).floatValue(), uriTag);
						}else {
								updateTagWeight(con, tagNames.get(tagName).floatValue() , uriTag);
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					result.close();
				}
			}
			finally {
				con.close();
			}
		}
		catch (OpenRDFException e) {
			e.printStackTrace();
		}
	}	
		

	
	/**
	 * @param myRepository
	 * @param tagName
	 * @return
	 */
	public static float getTagAmmount(Repository myRepository, String tagName){
		TupleQueryResult result = null;
		float total = 0f;
		try {
			   RepositoryConnection con = myRepository.getConnection();
			   try {
				   String queryString = "SELECT x, tagName, weight, uri  FROM {x} rdfs:label {uri};  ex:rdftagName {tagName}; ex:rdfweight {weight} where tagName LIKE \""+tagName+"\" IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";			   
			      TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
			      result = tupleQuery.evaluate();
			      try {
			    	  while (result.hasNext()) {
			    		 BindingSet bindingSet = result.next();
//			    		   Value tagNameValue = bindingSet.getValue("tagName");
//			    		   Value tagweight = bindingSet.getValue("weight");
//			    		   Value uri = bindingSet.getValue("uri");
//			    		   System.out.println("    tagNameValue   "+tagNameValue+"  tagweight  "+tagweight +"  tagURI  "+uri);
			    		   total++;
			    		}
			      }
			      finally {
			         result.close();
			      }
			   }
			   finally {
			      con.close();
			   }
			}
			catch (OpenRDFException e) {
			   e.printStackTrace();
			}
			//System.out.println("  total " + total);
		return total;	
	}
	
	/**
	 * @param myRepository
	 * @param tagName
	 */
	public static void listTagAmmount(Repository myRepository, String tagName){
		TupleQueryResult result = null;
		try {
			   RepositoryConnection con = myRepository.getConnection();
			   try {
			      //String queryString = "SELECT x, y , p FROM {x} p {y}";
				   String queryString = "SELECT x, tagName, weight, uri  FROM {x} rdfs:label {uri}; ex:rdftagName {tagName}; ex:rdfweight {weight} where tagName LIKE \""+tagName+"\" IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";			   
				  //String queryString = "SELECT x, y FROM {x} ex:rdfname {y} where y LIKE \"Fred\" IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";
				   //String queryString = "SELECT x, y FROM {x} ex:rdfname {y}; ex:rdfemail {z} WHERE y LIKE \"Fred\"  AND z LIKE \"fad@cs.dk\"  IGNORE CASE USING NAMESPACE ex = <http://protege.stanford.edu/> ";

				  //String queryString = "SELECT x, y FROM {x} rdfs:label {y} where isLiteral(y)";			      
				   //String queryString = "SELECT x, y FROM {x} p {y} where isURI(y)";			      
				   //String queryString = "SELECT x, y FROM {x} z {y} where true";			      
			      
				   //System.out.println(queryString);
			      TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
			      result = tupleQuery.evaluate();
			      try {
			    	  //System.out.println(result.hasNext());
			    	  while (result.hasNext()) {
			    		   BindingSet bindingSet = result.next();
			    		   Value tagNameValue = bindingSet.getValue("tagName");
			    		   Value tagweight = bindingSet.getValue("weight");
			    		   Value uri = bindingSet.getValue("uri");
			    		   
			    		   System.out.println("    tagNameValue   "+tagNameValue+"  tagweight  "+tagweight +"  tagURI  "+uri);
			    		}
			      }
			      finally {
			         result.close();
			      }
			   }
			   finally {
			      con.close();
			   }
			}
			catch (OpenRDFException e) {
			   e.printStackTrace();
			}
	}	
	
	/**
	 * @param myRepository
	 * @param tagName
	 */
	public static void selectAllTags(Repository myRepository) {
		int count = 0;
		
		//Map<String, Set<String>> mapTagGround = TagSemanticGround.loadGrounding();
		TupleQueryResult result = null;
		try {
			RepositoryConnection con = myRepository.getConnection();
			try {
				
				//String queryString = "SELECT DISTINCT tagName, weight  FROM {x} ex:rdftagName {tagName}; ex:rdfweight {weight} where weight < 0 USING NAMESPACE ex = <http://protege.stanford.edu/> ";			   
				String queryString = "SELECT DISTINCT tagName, weight FROM {x} ex:rdftagName {tagName}; ex:rdfweight {weight} USING NAMESPACE ex = <http://protege.stanford.edu/> ";			   

				TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
				result = tupleQuery.evaluate();
				try {
					while (result.hasNext()) {
						BindingSet bindingSet = result.next();
						Value tagNameValue = bindingSet.getValue("weight");
						
						//System.out.println("    tagNameValue   "+tagNameValue);
						//String key = extractBrackets(tagNameValue.toString().toLowerCase());
						
						System.out.println(tagNameValue.toString());
						Float d = new Float(tagNameValue.stringValue());
						System.out.println(d.floatValue());
						
						if (d.floatValue()>0f) {
							count++;
						}
						
						
//						if (mapTagGround.containsKey(key)) {
//							for (String semanticTag : mapTagGround.get(key)) {
//									System.out.println(tagNameValue+" contain semantic term "+ semanticTag);								
//								}
//						}else {
//							//System.out.println(tagNameValue+" nao contain semantic term ");
//						}
							
											
						
						
						
//						System.out.println("    tagNameValue   "+tagNameValue);
						//System.out.println("tagsDigg.add("+tagNameValue+");");						
						
					}
				}
				finally {
					result.close();
				}
				System.out.println("  total "+ count);
			}
			
			finally {
				con.close();
			}
		}
		catch (OpenRDFException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * @param myRepository
	 * @param tagName
	 */
	public static Set<String>  getAllUsers(Repository myRepository) {
		
		Set<String> users = new HashSet<String>();
		TupleQueryResult result = null;
		try {
			RepositoryConnection con = myRepository.getConnection();
			try {
				
				String queryString = "SELECT DISTINCT userName FROM {x} ex:rdfname {userName} USING NAMESPACE ex = <http://protege.stanford.edu/> ";			   
				
				TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
				result = tupleQuery.evaluate();
				try {
					while (result.hasNext()) {
						BindingSet bindingSet = result.next();
						Value tagNameValue = bindingSet.getValue("userName");
						
						//System.out.println("    tagNameValue   "+tagNameValue);
						String key = extractBrackets(tagNameValue.toString().toLowerCase());
						if (!users.contains(key)) {
							users.add(key);
						}else {
							//System.out.println(tagNameValue+" nao contain semantic term ");
						}
						
						
						
						
						
//						System.out.println("    tagNameValue   "+tagNameValue);
//						System.out.println("tags.add("+tagNameValue+");");						
						
					}
				}
				finally {
					result.close();
				}
			}
			finally {
				con.close();
			}
		}
		catch (OpenRDFException e) {
			e.printStackTrace();
		}
		return users;
	}	
	
}

class Term implements Comparable<Term> {
	String term;
	Integer value;
	  public int compareTo(Term term) {
	        if (this.value == term.getValue())
	            return 0;
	        else if (this.value < term.getValue())
	            return 1;
	        else
	            return -1;
	    }
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Term(String term, Integer value) {
		super();
		this.term = term;
		this.value = value;
	}
}