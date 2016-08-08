package lucene;

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import parser.HTMLPageExtractor;
import aau.Resource;

public class HelloLucene {
	
	private static String CONTENT = "content";
	
  public static void main(String[] args)  {
	  
  }
	  
  public float getRepresentativeness(String tagName, Resource kiWiResource) throws IOException, ParseException {
	  
	float result = 0f;
    // 0. Specify the analyzer for tokenizing text.
    //    The same analyzer should be used for indexing and searching
    StandardAnalyzer analyzer =  new StandardAnalyzer();

    // 1. create the index
    Directory ramDirectory = new RAMDirectory();

    // the boolean arg in the IndexWriter ctor means to
    // create a new index, overwriting any existing index
    IndexWriter indexWriter = new IndexWriter(ramDirectory, analyzer, true);
    
    HTMLPageExtractor htmlPageExtractor =new HTMLPageExtractor(); 
    
    String pageTerms = htmlPageExtractor.getTextFromHTMLPage(kiWiResource.getLink());

    addDoc(indexWriter, pageTerms);

    
    indexWriter.close();

    // 2. query
    String querystr =  tagName;

    // the "title" arg specifies the default field to use
    // when no field is explicitly specified in the query.
    QueryParser queryParser = new QueryParser(CONTENT, analyzer);
         
    Query query = queryParser.parse(querystr);
    
    IndexSearcher searcher = new IndexSearcher(ramDirectory);
    
    // the term name here is "art"
    Term term = new Term(CONTENT, "art");
    
   System.out.println(searcher.docFreq(term));
    
    
    
    Hits hits = searcher.search(query);

    
    // 4. display results
    for (int i = 0; i < hits.length(); i++) {
    	
    	 System.out.println("SCORE  "+((Document)hits.doc(i)).getField(CONTENT).stringValue());

    	 
    	 
    	 System.out.println("SCORE  "+((Document)hits.doc(i)).getField(CONTENT).stringValue());    	 
		 
    	 //tf*idf
    	 System.out.println("SCORE  "+hits.score(i));
		 

    	 System.out.println("SCORE  "+((Document)hits.doc(i)).getField(CONTENT).stringValue());
		 
		 System.out.println("  ");
		 
	 }

    // searcher can only be closed when there
    // is no need to access the documents any more. 
    searcher.close();
	return result;
  }

  private static void addDoc(IndexWriter indexWriter, String value) throws IOException {
    Document doc = new Document();
    doc.add(new Field(CONTENT, value, Field.Store.YES, Field.Index.TOKENIZED));
    indexWriter.addDocument(doc);
  }
}
