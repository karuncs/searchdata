package xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import aau.Resource;
import aau.User;


public class XmlReader {

	Document dom;

	public List<Resource> getKiWiResources() {
		return kiWiResources;
	}

	public void setKiWiResources(List<Resource> kiWiResources) {
		this.kiWiResources = kiWiResources;
	}


	List<Resource> kiWiResources;
	

	public XmlReader(){
		//create a list to hold the employee objects
		kiWiResources = new ArrayList<Resource>();
	}

	public List<Resource> loadXMLData(){
		  //File folder = new File("C:\\M-Eco Software\\medworm datasource\\medwormblogs\\curn200905111304");
		  File folder = new File("C:\\fred");
		  File[] listOfFiles = folder.listFiles();
		  for (File file : listOfFiles) {
			   if (file.isDirectory()) {
				   File[] listOfChild =  file.listFiles();
				   for (File fileChild : listOfChild) {
					   if (fileChild.isFile()) {
						   parseXmlFile(fileChild);
					   }
				   }		   
			   }else if (file.isFile()) {
				   parseXmlFile(file);
			   }
		   }
		  System.out.println(getKiWiResources().isEmpty());
		return getKiWiResources();
	 }

 public static void main(String[] args){
	 
	 		File file2 = new File("C:\\fred\\Acinetobacter.xml");
	 		 XmlReader xmlReader = new XmlReader();
	 		xmlReader.parseXmlFile(file2);
	  
 }


  private void parseXmlFile(File file){
		//get the factory
	   Document dom;
	    
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			dom = db.parse(file);

			parseDocument(dom);
			
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
  }


				 
		private void parseDocument(Document domT){  
			Element docEle = domT.getDocumentElement();
			//get a nodelist of  elements
			NodeList nl = docEle.getElementsByTagName("item");

			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {

					//get the employee element
					Element el = (Element)nl.item(i);

					//get the Item object
					Resource e = getItem(el);

					//add it to list
					getKiWiResources().add(e);
				}
			}
		}
		/*
		 * I take an Item element and read the values in, create
		 * an Item object and return it
		 */
		private Resource getItem(Element itEl) {
			
			

			String title = getTextValue(itEl,"title");
			//System.out.println("Title is:   "+title);
			String author =getTextValue(itEl,"author");
			//System.out.println("author is:   "+author);
			//String description = getTextValue(itEl,"description");
			//System.out.println("description is:   "+description);
			String link = getTextValue(itEl,"link");
			
			
			User user = new User(author,author+"@aau.dk");
			Resource kiwiResource = new Resource();
			kiwiResource.setAuthor(user);
			kiwiResource.setDescription(title);
			kiwiResource.setLink(link);
			kiwiResource.setUri(title);
			kiwiResource.addTag("tag");			
			
			return kiwiResource;
		}


		/**
		 * I take a xml element and the tag name, look for the tag and get
		 * the text content
		 * i.e for <item><name>/name></employee> xml snippet if
		 * the Element points to item node and tagName is 'name' I will return disease
		 */
		private String getTextValue(Element ele, String tagName) {
			String textVal = null;
			NodeList nl = ele.getElementsByTagName(tagName);
			if(nl != null && nl.getLength() > 0) {
				Element el = (Element)nl.item(0);
				if(el.hasChildNodes()){
					textVal = el.getFirstChild().getNodeValue();
				}else {
					textVal="";	
				}
				
			}

			return textVal;
		}
  }

	
		
	
	


			
