package sesame;



public interface IConstant {
	
	public static String STANFOR_NS = "http://protege.stanford.edu/";

	public static String USER = STANFOR_NS+"rdfUser";
	
	public static String USER_INSTANCE = STANFOR_NS+"rdfkiwi_Instance_User";
	
	public static String USER_NAME = STANFOR_NS+"rdfname";
	
	public static String USER_EMAIL = STANFOR_NS+"rdfemail";

	public static String TAG = STANFOR_NS+"rdfTag";
	
	public static String TAG_INSTANCE = STANFOR_NS+"rdfkiwi_Instance_Tag";	
	
	public static String TAG_NAME = STANFOR_NS+"rdftagName";
	
	public static String TAG_WEIGHT = STANFOR_NS+"rdfweight";
	
	public static String TAG_FREQUENCY = STANFOR_NS+"rdftagFreq";	

	public static String KIWI_RESOURCE = STANFOR_NS+"rdfKiWiResource";
	
	public static String KIWI_RESOURCE_LINK = STANFOR_NS+"rdflink";
	
	public static String KIWI_RESOURCE_DESC = STANFOR_NS+"rdfdesc";
	
	public static String KIWI_RESOURCE_INSTANCE = STANFOR_NS+"rdfkiwi_Instance_Resource";
	
	public static String KIWI_RESOURCE_TAGS = STANFOR_NS+"rdftags";	
	
	public static String REPOSITORY = "repository";
	
	public static String FISICAL_REPOSITORY = "D:\\programs\\eclipse\\workspace\\Similar\\repository";
		
	
	public static String FLICKR_REPOSITORY = "flickr_repository";
	
	public static String DIGG_REPOSITORY = "digg_repository";

	public static String SANDBOX = "docs";
	
	public static String AAU_SMTP = "smtp.cs.aau.dk";
	
	public static int AMOUNT_FREQUENT_TAG = 5;
	
	public static int MINIMAL_AMOUNT_FREQUENT_TAG = 2;
	
	public static int MINIMUM_TAG_PER_DOCUMENT = 3;
	
	
	//
	public static String INVALID_TAG_BOOKMARK = "Bookmarks";
	
	public static String INVALID_TAG_IMPORTED = "imported";
	
	public static String INVALID_TAG_BOOKMARK_MENU = "Bookmarks_Menu";
	
	public static String INVALID_TAG_WEB20 = "web2.0";
	
	//
	public static float USER_TAG_AFFINITY = 2.5f;

}
