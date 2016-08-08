package importer;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import util.FileUtils;
import aau.Resource;
import aau.Tag;
import aau.User;

/**
 * @author rong,freddurao
 * 
 */
public class MovieLensImporter {

	private final File fFile;

	private final static String USER = "USER";

	private final static String TAG = "TAG";

	private final static String RESOURCEANDTAG = "RESOURCEANDTAG";

	private final static String TAG_ID_REPEAT_ID = "TAG_ID_REPEAT_ID";
	
	private final static String TAG_ID_DISTINCT_ID = "TAG_ID_DISTINCT_ID";

	private final static String MOVIES = "MOVIES";
	
	private final static String RATING = "RATING";
	
	private final static int MAX_RATINGS_PER_SQL_FILE = 250000;
	
	int rateId=0;
	
	int rateCountLine=0;
	
	int ratingsPerFile = MAX_RATINGS_PER_SQL_FILE;			

	public static void main(String[] aArgs) throws FileNotFoundException {
		MovieLensImporter parser = null;
		/*
		ReadandWriteContent parser = new ReadandWriteContent(
				"C:\\M-Eco Software\\medworm by jan\\medwo\\resources.txt");
		
		parser.processLineByLine(RESOURCE);
		log("Done for Resource.");

		parser = new MovieLensImporter("C:\\M-Eco Software\\medworm by jan\\medwo\\users.txt");
		parser.processLineByLine(USER);
		log("Done for USER.");
		
	    parser = new MovieLensImporter("C:\\Users\\ricardol\\Documents\\TAG_FINAL.dat");
		parser.processLineByLine(TAG);
		log("Done for TAG.");
		
	    //parser = new MovieLensImporter("C:\\Users\\ricardol\\Documents\\TAG_FINAL.dat");
		parser = new MovieLensImporter("C:\\movieLens\\tags.dat");
		parser.processLineByLine(TAG_ID_DISTINCT_ID);
		log("Done for TAG_ID_DISTINCT_ID.");
		
				parser.processLineByLine(TAG);
		log("Done for TAG.");
		*/		
		
		
		parser = new MovieLensImporter("C:/TAG_FINAL.dat");
		
		parser.createBatScriptsForInsertingAllRatings("C:/RATING", "C:/test/ratings");


		
		/*	
		parser = new MovieLensImporter("E:\\Project\\movieLens\\ratings.dat");
		//parser.processLineByLine(USER);
		
		parser.generateUserScript();
		//parser = new MovieLensImporter("E:\\Project\\movieLens\\tag.dat");
		//parser.processLineByLine(USER);
		log("Done for USER.");	
		
		
		parser = new MovieLensImporter("E:\\Project\\movieLens\\tag.dat");
		parser.processLineByLine(RESOURCEANDTAG);
		log("Done for RESOURCE AND TAG.");
		
				parser = new MovieLensImporter("E:\\Project\\movieLens\\movies.dat");
		parser.processLineByLine(MOVIES);
		log("Done for resource.");



		parser = new MovieLensImporter("E:\\Project\\movieLens\\ratings.dat");
		parser.createBatScript();
		log("Done for ratings.");
		*/
		

	}

	/**
	 * @param aFileName
	 *            full name of an existing, readable file.
	 */
	public MovieLensImporter(String aFileName) {
		fFile = new File(aFileName);
	}
	


	/** Template method that calls {@link #processLineForResource(String)}. */
	public final void processLineByLine(String type)
			throws FileNotFoundException {
		Scanner scanner = new Scanner(fFile);
		
		try {
			// first use a Scanner to get each line
			while (scanner.hasNextLine()) {
				if (type.equals(MOVIES)) {
					processLineForResource(scanner.nextLine());
				} else if (type.equals(USER)) {
					processLineForUser(scanner.nextLine());
				} else if (type.equals(TAG)) {
					processLineForTag(scanner.nextLine());
				} else if (type.equals(RESOURCEANDTAG)) {
					processLineForResouceAndTag(scanner.nextLine());
				} else if (type.equals(TAG_ID_REPEAT_ID)) {
					this.processLineForCreatingTagId(scanner.nextLine(),true);					
				} else if (type.equals(TAG_ID_DISTINCT_ID)) {
					this.processLineForCreatingTagId(scanner.nextLine(),false);					
				}else if (type.equals(RATING)) {
					rateCountLine++;
					if (rateCountLine<ratingsPerFile) {
						processLineForRatingParts(scanner.nextLine());
					}else{
						ratingsPerFile = rateCountLine+MAX_RATINGS_PER_SQL_FILE;
						processLineForRatingParts(scanner.nextLine());
					}					
					//processLineForRating(scanner.nextLine());
				}					
			}
		} finally {
			// ensure the underlying stream is always closed
			scanner.close();
		}
	}

	protected String createInsertScriptsForResouce(Resource resource) {
		String insertScript = "INSERT INTO RESOURCES VALUES(" + resource.getId() + ","
				+ resource.getUri() + "," + resource.getTitle() + ","
				+ resource.getDescription() + "," + resource.getLink() + ","
				+ resource.getType() + "," + resource.getGeneralTagFrequency()
				+ "," + resource.getGeneralTagWeight() + ","
				+ resource.getAuthor() + ");";
		return insertScript;
	}

	/**
	 * @param id
	 * @param tagFrequency
	 * @param tagId
	 * @param userId
	 * @return
	 */
	public String createInsertScriptsUserPreference(int id, float tagFrequency, long tagId, long userId) {
		String insertScript = "INSERT INTO USERPREFERENCE (ID, TAGFREQUENCY, TAG_ID, USER_ID) VALUES " +
				"(" + id + "," + tagFrequency + ", " + tagId + ", " + userId + ");";
		return insertScript;		
	}	

	/**
	 * @param user
	 * @return
	 */
	protected String createInsertScriptsForUser(User user) {
		String insertScript ="INSERT INTO USERS VALUES(" + user.getId() + "," + user.getUri()
				+ "," + user.getName() + "," + user.getEmail() + ");";
		return insertScript;		
	}

	/**
	 * @param tag
	 * @return
	 */
	protected String createInsertScriptsForTag(Tag tag,String userId,String resourceId) {
		
		String insertScript = "INSERT INTO TAG VALUES (ID, URI, TAGNAME, WEIGHT,TAGFREQUENCY, TAGAUTHOR_ID, RESOURCE_ID) (" + tag.getId() + "," + tag.getUri() + ", "
				+ tag.getTagName() + ", " + tag.getWeight() + ", "
				+ tag.getTagFrequency() + ", " + userId + ", " + resourceId + ");";
		return insertScript;		
	}

	/**
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
	 * @param resourceId
	 * @param tagId
	 * @return
	 */
	protected String createInsertScriptsForRating(int rateId, String userId, String resourceId,
			String ratingValue) {
		//you need a rating id
		String insertScript ="INSERT INTO RATE VALUES(" + rateId + "," + ratingValue + "," + resourceId + "," + userId + ");";
		
		return insertScript;				
	}	
	
	protected String createInsertScriptsForRatingResouces(String userId, String resourceId,
			String ratingValue) {
		String insertScript ="INSERT INTO RATE_RESOURCE VALUES(" + resourceId + "," + ratingValue + ");";
		return insertScript;				
	}	
	
	
	/**
	 * 
	 */
	public void createBatScriptsForInsertingAllRatings(String ratingsSQLDirHome, String fileBatSavePath) {
		for (int i = 250000; i <= 10250000; i=i+250000) {
			String batCommend = "psql -U postgres -p iwis -h localhost -d iwis -p 5432 \\i "+ratingsSQLDirHome+"/RATING_"+i+".sql";
			//System.out.println("psql -U iwis -h localhost -d iwis \\i C:\\RATING\\RATING_"+i);
			FileUtils.writeApendResults(batCommend, fileBatSavePath, "bat");			
		}
		System.out.println("Bat Script generated ");
		
	}		
	


	/**
	 * It processes the line for a resource
	 * 
	 * @param aLine
	 * 
	 */
	protected void processLineForResource(String aLine) {
		// use a second Scanner to parse the content of each line
		System.out.println(aLine);
		Scanner scanner = new Scanner(aLine.trim());
		scanner.useDelimiter("::");
		StringBuffer stringBuffer = new StringBuffer();

		if (scanner.hasNext()) {
			
			String resourceId = scanner.next();
			String title = scanner.next().trim();
			String type = scanner.next().trim();

			Resource resource = new Resource();
			resource.setId(new Long(resourceId.trim()));
			resource.setUri(null);
			resource.setTitle(quote(title.trim()));
			resource.setDescription(null);
			resource.setLink(null);
			resource.setType(quote(type.trim()));
			resource.setGeneralTagFrequency(0f);
			resource.setGeneralTagWeight(0f);
			resource.setAuthor(null);

			stringBuffer.append(createInsertScriptsForResouce(resource));
			stringBuffer.append("\n");
						
		} else {
			log("Empty or invalid line. Unable to process.");
		}
		scanner.close();
		
		String fileName = "E:\\Project\\movieLens\\"+MOVIES+".sql";
		FileUtils.saveFile(fileName, stringBuffer.toString());
	}

	/**
	 * processLineForUser
	 * 
	 * @param aLine
	 */
	protected void processLineForUser(String aLine) {
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter("::");
		StringBuffer stringBuffer = new StringBuffer();
		if (scanner.hasNext()) {
			String userId = scanner.next();
			String name = "user"+userId ;
			if (!userIds.contains(userId)) {
				User user = new User();
				user.setId(new Long(userId.trim()));
				user.setUri(null);
				user.setName(quote(name));
				user.setEmail(null);
				stringBuffer.append(createInsertScriptsForUser(user));
				stringBuffer.append("\n");
				userIds.add(userId);
			}
	} else {
		log("Empty or invalid line. Unable to process.");
	}
	scanner.close();
	
	String fileName = "E:\\Project\\movieLens\\"+USER+".sql";
	FileUtils.saveFile(fileName, stringBuffer.toString());
	}
	
	/**
	 * 
	 */
	protected void generateUserScript() {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 1; i < 71567; i++) {
				String name = "user"+i ;
				User user = new User();
				user.setId(new Long(i));
			user.setUri(null);
				user.setName(quote(name));
				user.setEmail(null);
				stringBuffer.append(createInsertScriptsForUser(user));
				stringBuffer.append("\n");
			}
		String fileName = "E:\\Project\\movieLens\\"+USER+".sql";
		FileUtils.saveFile(fileName, stringBuffer.toString());
	
	} 
	
	
	Map<String,Integer> map = new HashMap<String,Integer>();
	
	Set<String> userIds = new HashSet<String>();
	
	int count = 0;
	

	/**
	 * @param aLine
	 * @param reapeatTagId - this parameter decides is tagId must repeat or not
	 */
	protected void processLineForCreatingTagId(String aLine, boolean reapeatTagId) {
		// use a second Scanner to parse the content of each line
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter("::");
		StringBuffer stringBuffer = new StringBuffer();	
		
		if (scanner.hasNext()) {
			String userId = scanner.next();
			String movieId = scanner.next();
			String tagName = scanner.next();

			if(reapeatTagId && map.containsKey(tagName)){
				stringBuffer.append(userId+"::"+movieId+"::"+tagName+"::"+map.get(tagName));
			}else{
				map.put(tagName, count++);
				stringBuffer.append(userId+"::"+movieId+"::"+tagName+"::"+map.get(tagName));
			}
			//System.out.println(stringBuffer.toString());
			stringBuffer.append("\n");
						
		} else {
			log("Empty or invalid line. Unable to process.");
		}
		scanner.close();
		
		//String fileName = "C:\\Users\\ricardol\\Documents\\"+TAG+"_FINAL.dat";
		String fileName = "C:\\"+TAG+"_FINAL.dat";
		FileUtils.saveFile(fileName, stringBuffer.toString());
	}

	
	/**
	 * @param aLine
	 */
	protected void processLineForTag(String aLine) {
		// use a second Scanner to parse the content of each line
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter("::");
		StringBuffer stringBuffer = new StringBuffer();	
		
		if (scanner.hasNext()) {
			String userId = scanner.next();
			String movieId = scanner.next();
			String tagName = scanner.next().replace("'", "");
			tagName = tagName.replace("\t", "");
			String tagId = scanner.next();			

			//if (!tagIds.contains(tagId)) {
				Tag tag = new Tag();
				tag.setId(new Long(tagId.trim()));
				tag.setUri(null);
				tag.setTagName(quote(tagName.trim()));
				tag.setWeight(0f);
				tag.setTagFrequency(0f);
				stringBuffer.append(createInsertScriptsForTag(tag,userId,movieId));
				stringBuffer.append("\n");				
				tagIds.add(tagId);
			//}

		} else {
			log("Empty or invalid line. Unable to process.");
		}
		scanner.close();
		
		//String fileName = "C:\\Users\\ricardol\\Documents\\"+TAG+".sql";
		String fileName = "C:\\TAG_FINAL.sql";
		FileUtils.saveFile(fileName, stringBuffer.toString());
	}
	
	Set<String> tagIds = new HashSet<String>();

	/**
	 * @param aLine
	 */
	protected void processLineForResouceAndTag(String aLine) {
		/*
		 * Use the SQL script below to generate the list of tags by resource 
		 * in the corresponding table:  
		 * insert into resources_tag
		 * 	select distinct resource_id, id
		 * 	from tag */
		
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter("::");
		StringBuffer stringBuffer = new StringBuffer();		
		if (scanner.hasNext()) {
			String userId = scanner.next();
			String resourceId = scanner.next();
			String tagName = scanner.next().replace("'", "");
			String tagId = scanner.next();	
			stringBuffer.append(createInsertScriptsForResouceAndTag(resourceId, tagId));
			stringBuffer.append("\n");
						
		} else {
			log("Empty or invalid line. Unable to process.");
		}
		scanner.close();
		
		String fileName = "E:\\Project\\movieLens\\"+RESOURCEANDTAG+".sql";
		FileUtils.saveFile(fileName, stringBuffer.toString());
	
	}


	/**
	 * @param aLine
	 */
	protected void processLineForRatingParts(String aLine) {
			Scanner scanner = new Scanner(aLine);
			scanner.useDelimiter("::");
			StringBuffer stringBuffer = new StringBuffer();		
			if (scanner.hasNext()) {
				int ratingId = rateCountLine; 
				String userId = scanner.next();
				String resourceId = scanner.next();
				String ratingValue = scanner.next();
				stringBuffer.append(createInsertScriptsForRating(ratingId, userId,resourceId, ratingValue));
				stringBuffer.append("\n");
							
			} else {
				log("Empty or invalid line. Unable to process.");
			}
			scanner.close();
			
			String fileName = "E:\\Project\\movieLens\\TEST\\"+RATING+"_"+ratingsPerFile+".sql";
			FileUtils.saveFile(fileName, stringBuffer.toString());
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

	
	
	protected void createBatScript() {
		for (int i = 6250000; i <= 10250000; i=i+250000) {
			String insertScript = "psql -h localhost -U postgres -d iwis -f C:/Users/Public/Documents/movieLens_SQL/RATING/RATING_"+i+".sql";
			System.out.println(insertScript);
		}
	}
	
}

