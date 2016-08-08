package importer;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import util.FileUtils;
import util.StringUtils;
import aau.Resource;
import aau.Tag;
import aau.User;

/**
 * @author kreddy,freddurao
 * 
 */
public class MEcoImporter {

	private final File fFile;

	private final static String USER = "user";

	private final static String TAG = "tag";

	private final static String RESOURCEANDTAG = "resourceandtag";

	private final static String RESOURCE = "resource";
	
	protected static boolean deleteSqlFile = true;
	
	Set<String> resourceTagIds = new HashSet<String>();

	
	public static void main(String[] aArgs) throws FileNotFoundException {
			
//		parser = new MEcoImporter("C:\\M-Eco Software\\medwormdata\\resources.txt");
//		deleteSqlFile = true;
//		parser.processLineByLine(RESOURCE);
//		log("Done for RESOURCE.");
//		
		
		
//		parser = new MEcoImporter("C:\\M-Eco Software\\medwormdata\\tags.txt");
//		deleteSqlFile = true;		
//		parser.processLineByLine(TAG);
//		log("Done for TAG.");
//				
//		
//		
//
//		parser = new MEcoImporter("C:\\M-Eco Software\\medwormdata\\quads.txt");
//		deleteSqlFile = true;		
//		parser.processLineByLine(RESOURCEANDTAG);
//		log("Done for RESOURCEANDTAG");
//		 
		 //for users
//		parser = new MEcoImporter("C:\\M-Eco Software\\medwormdata\\users.txt");
//		deleteSqlFile = true;		
//		parser.processLineByLine(USER);
//		log("Done for USERS.");
		
	}

	/**
	 * @param aFileName
	 *            full name of an existing, readable file.
	 */
	public MEcoImporter(String aFileName) {
		fFile = new File(aFileName);
	}

	/** Template method that calls {@link #processLineForResource(String)}. */
	public final void processLineByLine(String type)
			throws FileNotFoundException {
		Scanner scanner = new Scanner(fFile);
		try {
			// first use a Scanner to get each line
			while (scanner.hasNextLine()) {
				if (type.equals(RESOURCE)) {
					processLineForResource(scanner.nextLine());
				} else if (type.equals(USER)) {
					processLineForUser(scanner.nextLine());
				} else if (type.equals(TAG)) {
					processLineForTag(scanner.nextLine());
				} else if (type.equals(RESOURCEANDTAG)) {
					processLineForResouceAndTag(scanner.nextLine());
				}
				deleteSqlFile = false;
			}
		} finally {
			// ensure the underlying stream is always closed
			scanner.close();
		}
	}


	/**
	 * creates scripts for resource table according to table columns
	 * @param resource
	 * @return
	 */
	protected String createInsertScriptsForResouce(Resource resource) {
		String insertScript = "INSERT INTO RESOURCES VALUES (ID, URI, TITLE, DESCRIPTION,LINK, TYPE, GENERALTAGFREQUENCY, GENERALTAGWEIGHT,AUTHOR_ID) (" + resource.getId() + ","
				+ resource.getUri() + ",'" + resource.getTitle() + "',"
				+ resource.getDescription() + "," + resource.getLink() + ","
				+ resource.getType() + "," + resource.getGeneralTagFrequency()
				+ "," + resource.getGeneralTagWeight() + ","
				+ resource.getAuthor() + ");";
		return insertScript;
	}

	/**
	 * creates scripts for user table according to table columns but for M-Eco not creating any scripts for users
	 * 
	 * @param user
	 * @return
	 */
	protected String createInsertScriptsForUser(User user) {
		String insertScript ="(" + user.getId() + "," + user.getUri()
				+ "," + user.getName() + "," + user.getEmail() + ")";
		return insertScript;		
	}

	/**
	 * creates scripts for tag table according to table columns
	 * added extra column data insertion for tag neighbours 
	 * @param tag
	 * @return
	 */
	protected String createInsertScriptsForTag(Tag tag) {
		String insertScript ="INSERT INTO TAG VALUES(" + tag.getId() + "," + tag.getUri() + ", '"
				+ tag.getTagName() + "', " + tag.getWeight() + ", "
				+ tag.getTagFrequency() + ", " + tag.getTagAuthor() + ");";
		return insertScript;		
	}

	/**
	 * creates scripts for resource_tag table according to table columns
	 * @param resourceId
	 * @param tagId
	 * @return
	 */
	protected String createInsertScriptsForResouceAndTag(String resourceId,
			String tagId) {
		String insertScript ="INSERT INTO RESOURCES_TAG VALUES(" + resourceId + "," + tagId + ");";
		return insertScript;				
	}

	/**
	 * It processes the line for a resource
	 * 
	 * @param aLine
	 * 
	 */
	
	protected void processLineForResource(String aLine) {
		// use a second Scanner to parse the content of each line
		
		String[] s = aLine.split("::u");
		s[1] = s[1].replace('"', ' ');
		s[1] = s[1].replace('\'', ' ');
		StringBuffer stringBuffer = new StringBuffer();	
		
				//if (scanner.hasNext()) {
			String resourceId = s[0].trim();// StringUtils.removeInvalidCharacteres(scanner.next().trim());
			String title = s[1].trim(); // StringUtils.removeInvalidCharacteres(scanner.next().replace("'", "").trim());
			title = title.replace("\\"," ");
			String link = s[2].trim();
			Resource resource = new Resource();
			resource.setId(new Long(resourceId.trim()));
			resource.setUri(null);
			resource.setTitle(title.trim());
			resource.setDescription(null);
			resource.setLink(link.trim());
			resource.setType(null);
			resource.setGeneralTagFrequency(0f);
			resource.setGeneralTagWeight(0f);
			resource.setAuthor(null);

			stringBuffer.append(createInsertScriptsForResouce(resource));
			stringBuffer.append("\n");
						
//		} else {
//			log("Empty or invalid line. Unable to process.");
//		}
//		scanner.close();
	
		String fileName = "C:\\M-Eco Software\\medwormdata\\"+RESOURCE+".sql";
		FileUtils.saveFile(fileName, stringBuffer.toString(),deleteSqlFile);
	}

	/**
	 * processLineForUser
	 * 
	 * @param aLine
	 */
	protected void processLineForUser(String aLine) {
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter("::u'");
		StringBuffer stringBuffer = new StringBuffer();
		String script = "INSERT INTO USERS VALUES(";
		if (scanner.hasNext()) {
			String userId = StringUtils.removeInvalidCharacteres(scanner.next().toString());
			String name = StringUtils.removeInvalidCharacteres(scanner.next().replace("'", "").trim());

			User user = new User();
			user.setId(new Long(userId.trim()));
			user.setUri(null);
			user.setName(quote(name.trim()));
			user.setEmail(null);
			stringBuffer.append(script);
			stringBuffer.append(createInsertScriptsForUser(user));
			if (scanner.hasNext()) {
				stringBuffer.append(",");
			}			
			stringBuffer.append("\n");
					
	} else {
		log("Empty or invalid line. Unable to process.");
	}
	scanner.close();
	stringBuffer.append(");");
	String fileName = "C:\\M-Eco Software\\medwormdata\\"+USER+".sql";
	FileUtils.saveFile(fileName, stringBuffer.toString(),deleteSqlFile);
	}
	
	
	

	/**
	 * process line for tag
	 * @param aLine
	 */
	protected void processLineForTag(String aLine) {
		// use a second Scanner to parse the content of each line
		//Scanner scanner = new Scanner(aLine);
		//scanner.useDelimiter("::u'");
		String[] s = aLine.split("::u");
		s[1] = s[1].replace('"', ' ');
		s[1] = s[1].replace('\'', ' ');
		StringBuffer stringBuffer = new StringBuffer();	
		
		//if (scanner.hasNext()) {

			String tagId = s[0].trim();//StringUtils.removeInvalidCharacteres(scanner.next().trim());
			String name = s[1].trim();// StringUtils.removeInvalidCharacteres(scanner.next().replace("'", "").trim());

			Tag tag = new Tag();
			tag.setId(new Long(tagId.trim()));
			tag.setUri(null);
			tag.setTagName(name.trim());
			tag.setWeight(0f);
			tag.setTagFrequency(0f);
			tag.setTagAuthor(null);

			stringBuffer.append(createInsertScriptsForTag(tag));
			stringBuffer.append("\n");
						
		//} else {
			//log("Empty or invalid line. Unable to process.");
		//}
		//scanner.close();
		
		String fileName = "C:\\M-Eco Software\\medwormdata\\"+TAG+".sql";
		FileUtils.saveFile(fileName, stringBuffer.toString(),deleteSqlFile);
	}
	


	/**
	 * process line for resource and tag
	 * @param aLine
	 */
	protected void processLineForResouceAndTag(String aLine) {

		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter("::");
		StringBuffer stringBuffer = new StringBuffer();		
		if (scanner.hasNext()) {
			
			String noIdeaId = StringUtils.removeInvalidCharacteres(scanner.next().trim());
			String resourceId = StringUtils.removeInvalidCharacteres(scanner.next().trim());
			String tagId = StringUtils.removeInvalidCharacteres(scanner.next().trim());
			String noIdea2Id = StringUtils.removeInvalidCharacteres(scanner.next().trim());
			
			if (!resourceTagIds.contains(resourceId.concat(tagId))) {
				stringBuffer.append(createInsertScriptsForResouceAndTag(resourceId, tagId));
				stringBuffer.append("\n");
				resourceTagIds.add(resourceId.concat(tagId));
			}else{
				//System.out.println(resourceId.concat(tagId));
			}
				
						
		} else {
			log("Empty or invalid line. Unable to process.");
		}
		scanner.close();
		
		String fileName = "C:\\M-Eco Software\\medwormdata\\"+RESOURCEANDTAG+".sql";
		//String fileName = "C:\\"+RESOURCEANDTAG+".sql";
		FileUtils.saveFile(fileName, stringBuffer.toString(),deleteSqlFile);
	
	}

	/**
	 * @param aObject
	 */
	private static void log(Object aObject) {
		System.out.println(String.valueOf(aObject));
	}

	/**
	 * @param aText
	 * @return
	 */
	private String quote(String aText) {
		String QUOTE = "'";
		return QUOTE + aText + QUOTE;
	}

	
}
