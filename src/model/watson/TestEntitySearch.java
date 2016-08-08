package watson;

import java.rmi.RemoteException;

import uk.ac.open.kmi.watson.clientapi.EntitySearch;
import uk.ac.open.kmi.watson.clientapi.EntitySearchServiceLocator;
import uk.ac.open.kmi.watson.clientapi.WatsonService;

public class TestEntitySearch {
	
	private EntitySearch es;
	
	public TestEntitySearch(){
		EntitySearchServiceLocator locator = new EntitySearchServiceLocator();
		try{
			es = locator.getUrnEntitySearch();			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getCause());
		}
	}
	
	public static void main(String[] args){
		TestEntitySearch app = new TestEntitySearch();
		try {
			app.search();
//			app.rsearch();
//			app.lsearch();
//			app.type();
//			app.label();
//			//app.comment();
//			app.occurences();
			app.relations();
//			app.related();
//			app.literals();
//			app.subclasses();
//			app.superclasses();
//			app.allsubclasses();
//			app.allsuperclasses();
//			app.equivclasses();
//			app.disjointWith();
//			app.differentFrom();
//			app.instances();
//			app.classes();
//			app.domain();
//			app.range();
//			app.domainof();
			app.rangeof();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void search() throws RemoteException{
		System.out.println("***** Searching Cat in sane_cow.daml *****");
		String[] res = es.getEntitiesByKeyword("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "cat");
		for (String s : res) System.out.println(s);
	} 
	
	public void rsearch() throws RemoteException{
		System.out.println("***** Searching exactly for Cat in sane_cow.daml restricted to local names of classes*****");
		String[] res = es.getEntitiesByKeywordWithRestriction("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "cat", WatsonService.LOCAL_NAME, WatsonService.CLASS, WatsonService.EXACT_MATCH);
		for (String s : res) System.out.println(s);
	} 

	public void lsearch() throws RemoteException{
		System.out.println("***** Searching for literals containing Cat in sane_cow.daml *****");
		String[][] res = es.getLiteralsByKeyword("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "cat");
		for (String[] sa : res) {	
				for (String s : sa) System.out.print(s+"--");
				System.out.println();
		}
	} 

	
	public void type() throws RemoteException{
		System.out.println("***** Type of cat in sane_cow.daml *****");
		String res = es.getType("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#cat");
		 System.out.println(res);
	} 
	
	public void label() throws RemoteException{
		System.out.println("***** Label of cat in sane_cow.daml *****");
		String[] res = es.getLabels("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#cat");
		for (String s : res) System.out.println(s);
	}
	
//	public void comment() throws RemoteException{
//		System.out.println("***** Comment of cat in sane_cow.daml *****");
//		String[] res = es.getComments("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#cat");
//		for (String s : res) System.out.println(s);
//	}
	
	public void occurences() throws RemoteException{
		System.out.println("***** Docs in which http://cohse.semanticweb.org/ontologies/people#cat appears *****");
		String[] res = es.getBelongsTo("http://cohse.semanticweb.org/ontologies/people#cat");
		for (String s : res) System.out.println(s);
	}
	
	

	
	public void relations() throws RemoteException{
		System.out.println("***** entity relations FROM cat in sane_cow.daml *****");
		String[][] res = es.getRelationsFrom("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#cat");
		for (String[] s : res) System.out.println(s[0]+" ("+s[1]+") --- "+s[2]);
	}
	
	public void related() throws RemoteException{
		System.out.println("***** entity relations TO animal in sane_cow.daml *****");
		String[][] res = es.getRelationsTo("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#animal");
		for (String[] s : res) System.out.println(s[0]+" ("+s[1]+") --- "+s[2]);
	}
	
	public void literals() throws RemoteException{
		System.out.println("***** literal relations of person in Examplo.daml *****");
		String[][] res = es.getLiteralsFor("http://www.cin	.ufpe.br/~avcc/Projeto-AI/Mondial/RDF/DadosIntegrados/Exemplo.daml", "http://kmi-web05.open.ac.uk:81/cache/1/546/8ac3/00e51/e6d73c5ce7/ad8fe6c15ee1228a1#Person");
		for (String[] s : res) System.out.println(s[0]+" ("+s[1]+") --- "+s[2]+" ("+s[3]+")");
	}
	
	public void subclasses() throws RemoteException{
		System.out.println("***** subclasses of animal in sane_cow.daml *****");
		String[] res = es.getSubClasses("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#animal");
		for (String s : res) System.out.println(s);
	}
	
	public void superclasses() throws RemoteException{
		System.out.println("***** superclasses of cat in sane_cow.daml *****");
		String[] res = es.getSuperClasses("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#cat");
		for (String s : res) System.out.println(s);
	}

	public void allsubclasses() throws RemoteException{
	       System.out.println("***** All subclasses of animal in cyc  *****");
	       String[] res = es.getAllSubClasses("http://secse.atosorigin.es:10000/ontologies/cyc.owl", "http://paoli.open.ac.uk/watson-cache/5/4b6/466d/b79a5/f1f2298c82/d30800bfaeeff211a#Animal");
		   for (String s : res) System.out.println(s);
	}
	
	public void allsuperclasses() throws RemoteException{
		System.out.println("***** superclasses of cat in cyc *****");
		String[] res = es.getAllSuperClasses("http://secse.atosorigin.es:10000/ontologies/cyc.owl", "http://paoli.open.ac.uk/watson-cache/5/4b6/466d/b79a5/f1f2298c82/d30800bfaeeff211a#Cat");
		for (String s : res) System.out.println(s);
	}
	
	public void equivclasses() throws RemoteException{
		System.out.println("***** equivalent of cat in sane_cow.daml *****");
		String[] res = es.getEquivalentClasses("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#cat");
		for (String s : res) System.out.println(s);
	}
	
	public void disjointWith() throws RemoteException{
		System.out.println("***** classes disjoint with cat in sane_cow.daml *****");
		String[] res = es.getDisjointWith("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#cat");
		for (String s : res) System.out.println(s);
	}
	
	public void differentFrom() throws RemoteException{
		System.out.println("***** individual different from University of cat in iswc.daml *****");
		String[] res = es.getDifferentFrom("http://www.ifi.unizh.ch/ddis/fileadmin/pdf/service_broker/iswc.daml", "http://annotation.semanticweb.org/iswc/iswc.daml#University");
		for (String s : res) System.out.println(s);
	}
	
	public void instances() throws RemoteException{
		System.out.println("***** instances of University in iswc.daml *****");
		String[] res = es.getInstances("http://www.ifi.unizh.ch/ddis/fileadmin/pdf/service_broker/iswc.daml", "http://annotation.semanticweb.org/iswc/iswc.daml#University");
		for (String s : res) System.out.println(s);
	}
	
	public void classes() throws RemoteException{
		System.out.println("***** classes of University_of_Karlsruhe in iswc.daml *****");
		String[] res = es.getClasses("http://www.ifi.unizh.ch/ddis/fileadmin/pdf/service_broker/iswc.daml", "http://annotation.semanticweb.org/iswc/iswc.daml#University_of_Karlsruhe");
		for (String s : res) System.out.println(s);
	}
	
	public void domain() throws RemoteException{
		System.out.println("***** domain of eats in sane_cow.daml *****");
		String[] res = es.getDomain("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#eats");
		for (String s : res) System.out.println(s);
	}
	
	public void range() throws RemoteException{
		System.out.println("***** range of reads in sane_cow.daml *****");
		String[] res = es.getRange("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#reads");
		for (String s : res) System.out.println(s);
	}
	
	public void domainof() throws RemoteException{
		System.out.println("***** animal domain of in sane_cow.daml *****");
		String[] res = es.getDomainOf("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#animal");
		for (String s : res) System.out.println(s);
	}
	
	public void rangeof() throws RemoteException{
		System.out.println("***** man range of in sane_cow.daml *****");
		String[] res = es.getRangeOf("http://www.cs.man.ac.uk/~horrocks/ESSLLI2003/Ontologies/sane_cows.daml", "http://cohse.semanticweb.org/ontologies/people#man");
		for (String s : res) System.out.println(s);
	}
	
	// http://cohse.semanticweb.org/ontologies/people#drives
	// http://cohse.semanticweb.org/ontologies/people#eaten+by
	// http://cohse.semanticweb.org/ontologies/people#sex
	
	
}
