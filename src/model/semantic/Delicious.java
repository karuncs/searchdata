package semantic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Delicious {
	
	public static void main(String[] args) {
		getContextMapKey();
	}
	
	public static Map<String, String> getContextMapKey(){
		Map<String, String> mapKey = new HashMap<String, String>();
		Set<String> tagsDigg = loadDeliciousOntologyTagGround().keySet();
		for (String keyOntology : tagsDigg) {
			String ontology = keyOntology.substring(keyOntology.lastIndexOf("_")+1,keyOntology.length());
			//System.out.println(ontology);
			//System.out.println(keyOntology.substring(0,keyOntology.lastIndexOf("_")));
			mapKey.put(keyOntology.substring(0,keyOntology.lastIndexOf("_")), keyOntology);
		}
		return mapKey;
	}
	
	
	public static Set<String> loadDeliciousTags () {
		
            Set<String> tagsDeli = new HashSet<String>();

		  tagsDeli.add("batman");
		  tagsDeli.add("video");
		  tagsDeli.add("fanmovie");
		  tagsDeli.add("comic");
		  tagsDeli.add("social");
		  tagsDeli.add("web2.0");
		  tagsDeli.add("tagging");
		  tagsDeli.add("youtube");
		  tagsDeli.add("design");
		  tagsDeli.add("wario");
		  tagsDeli.add("game");
		  tagsDeli.add("viral");
		  tagsDeli.add("marketing");
		  tagsDeli.add("nintendo");
		  tagsDeli.add("concept");
		  tagsDeli.add("wii");
		  tagsDeli.add("messaging");
		  tagsDeli.add("im");
		  tagsDeli.add("tools");
		  tagsDeli.add("blog");
		  tagsDeli.add("personal");
		  tagsDeli.add("german");
		  tagsDeli.add("art");
		  tagsDeli.add("posters");
		  tagsDeli.add("effect");
		  tagsDeli.add("phrase");
		  tagsDeli.add("photography");
		  tagsDeli.add("shopping");
		  tagsDeli.add("frames");
		  tagsDeli.add("image");
		  tagsDeli.add("scotland");
		  tagsDeli.add("sport");
		  tagsDeli.add("glasgow");
		  tagsDeli.add("uk");
		  tagsDeli.add("football");
		  tagsDeli.add("tricolor");
		  tagsDeli.add("brasil");
		  tagsDeli.add("futebol");
		  tagsDeli.add("esportes");
		  tagsDeli.add("spfc");
		  tagsDeli.add("paulista");
		  tagsDeli.add("saopaulo");
		  tagsDeli.add("schalke");
		  tagsDeli.add("ruhrgebiet");
		  tagsDeli.add("gelsenkirchen");
		  tagsDeli.add("bundesliga");
		  tagsDeli.add("rdf");
		  tagsDeli.add("web");
		  tagsDeli.add("introduction");
		  tagsDeli.add("semantic_web");
		  tagsDeli.add("ontology");
		  tagsDeli.add("xml");
		  tagsDeli.add("semantic-web");
		  tagsDeli.add("semantic");
		  tagsDeli.add("semanticweb");
		  tagsDeli.add("owl");
		  tagsDeli.add("Semantic");
		  tagsDeli.add("Web");
		  tagsDeli.add("w3c");
		  tagsDeli.add("media");
		  tagsDeli.add("news");
		  tagsDeli.add("engineering");
		  tagsDeli.add("softwareengineering");
		  tagsDeli.add("cesar");
		  tagsDeli.add("rise");
		  tagsDeli.add("software");
		  tagsDeli.add("reuso");
		  tagsDeli.add("group");
		  tagsDeli.add("reuse");
		  tagsDeli.add("travel");
		  tagsDeli.add("trip");
		  tagsDeli.add("hotel");
		  tagsDeli.add("munich");
		  tagsDeli.add("english");
		  tagsDeli.add("fun");
		  tagsDeli.add("phd");
		  tagsDeli.add("Workstation");
		  tagsDeli.add("Trinity");
		  tagsDeli.add("intrument");
		  tagsDeli.add("Korg");
		  tagsDeli.add("Keyboard");
		  tagsDeli.add("synth");
		  tagsDeli.add("music");
		  tagsDeli.add("photos");
		  tagsDeli.add("wiki");
		  tagsDeli.add("girls");
		  tagsDeli.add("women");
		  tagsDeli.add("protege");
		  tagsDeli.add("editor");
		  tagsDeli.add("inference");
		  tagsDeli.add("java");
		  tagsDeli.add("opensource");
		  tagsDeli.add("jena");
		  tagsDeli.add("api");
		  tagsDeli.add("framework");
		  tagsDeli.add("database");
		  tagsDeli.add("sesame");
		  tagsDeli.add("openrdf");
		  tagsDeli.add("systems");
		  tagsDeli.add("science");
		  tagsDeli.add("information");
		  tagsDeli.add("computer");
		  tagsDeli.add("intelligent");
		  tagsDeli.add("students");
		  tagsDeli.add("community");
		  tagsDeli.add("networking");
		  tagsDeli.add("search");
		  tagsDeli.add("research");
		  tagsDeli.add("journal");
		  tagsDeli.add("ieee");
		  tagsDeli.add("society");
		  tagsDeli.add("library");
		  tagsDeli.add("reference");
		  tagsDeli.add("avatar");
		  tagsDeli.add("cartoon");
		  tagsDeli.add("photo");
		  tagsDeli.add("generator");
		  tagsDeli.add("aalborg");
		  tagsDeli.add("sasligaen");
		  tagsDeli.add("denmark");
		  tagsDeli.add("osx86");
		  tagsDeli.add("kext");
		  tagsDeli.add("cinema");
		  tagsDeli.add("filmes");
		  tagsDeli.add("legendas");
		  tagsDeli.add("movies");
		  tagsDeli.add("Subtitles");
		  tagsDeli.add("download");
		  tagsDeli.add("Libraries");
		  tagsDeli.add("technology");
		  tagsDeli.add("computers");
		  tagsDeli.add("resources");
		  tagsDeli.add("book");
		  tagsDeli.add("simulation");
		  tagsDeli.add("simpy");
		  tagsDeli.add("tutorial");
		  tagsDeli.add("course");
		  tagsDeli.add("python");
		  tagsDeli.add("programming");
		  tagsDeli.add("AI");
		  tagsDeli.add("toread");
		  tagsDeli.add("Books");
		  tagsDeli.add("norvig");
		  tagsDeli.add("lisp");
		  tagsDeli.add("books");
		  tagsDeli.add("steiner");
		  tagsDeli.add("spirit");
		  tagsDeli.add("netTV");
		  tagsDeli.add("logic");
		  tagsDeli.add("stanford");
		  tagsDeli.add("university");
		  tagsDeli.add("InteligentAgents");
		  tagsDeli.add("ObjectOrientation");
		  tagsDeli.add("development");
		  tagsDeli.add("software_engineering");
		  tagsDeli.add("sei");
		  tagsDeli.add("SoftwareEngineering");
		  tagsDeli.add("Authority");
		  tagsDeli.add("socialsoftware");
		  tagsDeli.add("windows");
		  tagsDeli.add("keyboard");
		  tagsDeli.add("mac");
		  tagsDeli.add("hackintosh");
		  tagsDeli.add("leopard");
		  tagsDeli.add("leo4all");
		  tagsDeli.add("apple");
		  tagsDeli.add("howto");
		  tagsDeli.add("macosx");
		  tagsDeli.add("clustering");
		  tagsDeli.add("analysis");
		  tagsDeli.add("WindowsVista");
		  tagsDeli.add("install");
		  tagsDeli.add("microsoft");
		  tagsDeli.add("dualboot");
		  tagsDeli.add("vista");
		  tagsDeli.add("xp");
		  tagsDeli.add("magazine");
		  tagsDeli.add("subs");
		  tagsDeli.add("subtitles");
		  tagsDeli.add("subtitulos");
		  tagsDeli.add("files");
		  tagsDeli.add("sg1");
		  tagsDeli.add("stargate");
		  tagsDeli.add("Logic");
		  tagsDeli.add("AITools");
		  tagsDeli.add("pim");
		  tagsDeli.add("desktop");
		  tagsDeli.add("SemanticDesktop");
		  tagsDeli.add("SemanticWeb");
		  tagsDeli.add("Languages");
		  tagsDeli.add("mythology");
		  tagsDeli.add("history");
		  tagsDeli.add("encyclopedia");
		  tagsDeli.add("religion");
		  tagsDeli.add("PersonalPages");
		  tagsDeli.add("literature");
		  tagsDeli.add("manifesto");
		  tagsDeli.add("ideas");
		  tagsDeli.add("lifehacks");
		  tagsDeli.add("business");
		  tagsDeli.add("medical");
		  tagsDeli.add("health");
		  tagsDeli.add("government");
		  tagsDeli.add("medicine");
		  tagsDeli.add("learning");
		  tagsDeli.add("estudos");
		  tagsDeli.add("guide");
		  tagsDeli.add("concursos");
		  tagsDeli.add("study");
		  tagsDeli.add("Teaching");
		  tagsDeli.add("knowledge");
		  tagsDeli.add("Magazines");
		  tagsDeli.add("reviews");
		  tagsDeli.add("tech");
		  tagsDeli.add("FreeSoftware");
		  tagsDeli.add("Ebooks");
		  tagsDeli.add("mathematics");
		  tagsDeli.add("ai");
		  tagsDeli.add("optimization");
		  tagsDeli.add("Algorithms");
		  tagsDeli.add("Entertainment&LifeStyles");
		  tagsDeli.add("Software");
		  tagsDeli.add("VoiceCommand");
		  tagsDeli.add("delicious");
		  tagsDeli.add("folksonomy");
		  tagsDeli.add("icq");
		  tagsDeli.add("cool");
		  tagsDeli.add("people");
		  tagsDeli.add("internet");
		  tagsDeli.add("Windows");
		  tagsDeli.add("user");
		  tagsDeli.add("blogger");
		  tagsDeli.add("UserExperience");
		  tagsDeli.add("interactiondesign");
		  tagsDeli.add("ia");
		  tagsDeli.add("usability");
		  tagsDeli.add("ux");
		  tagsDeli.add("Python");
		  tagsDeli.add("processors");
		  tagsDeli.add("intel");
		  tagsDeli.add("Hardware");
		  tagsDeli.add("companies");
		  tagsDeli.add("interaction");
		  tagsDeli.add("hci");
		  tagsDeli.add("heuristics");
		  tagsDeli.add("SUSE");
		  tagsDeli.add("Linux");
		  tagsDeli.add("planet");
		  tagsDeli.add("opensuse");
		  tagsDeli.add("network");
		  tagsDeli.add("text");
		  tagsDeli.add("qualitative");
		  tagsDeli.add("textanalysis");
		  tagsDeli.add("sna");
		  tagsDeli.add("Games");
		  tagsDeli.add("matrox");
		  tagsDeli.add("graphics");
		  tagsDeli.add("company");
		  tagsDeli.add("asp");
		  tagsDeli.add("c++");
		  tagsDeli.add("uml");
		  tagsDeli.add("pucrio");
		  tagsDeli.add("education");
		  tagsDeli.add("elearning");
		  tagsDeli.add("constructivism");
		  tagsDeli.add("report");
		  tagsDeli.add("politics");
		  tagsDeli.add("tags");
		  tagsDeli.add("webservices");
		  tagsDeli.add("web3.0");
		  tagsDeli.add("systemarchitecturestyles");
		  tagsDeli.add("softwarearchitecture");
		  tagsDeli.add("architecture");
		  tagsDeli.add("sites");
		  tagsDeli.add("links");
		  tagsDeli.add("HCI");
		  tagsDeli.add("ComputerScience");
		  tagsDeli.add("math");
		  tagsDeli.add("matlab");
		  tagsDeli.add("SymbolicMath");
		  tagsDeli.add("digg");
		  tagsDeli.add("flash");
		  tagsDeli.add("visualization");
		  tagsDeli.add("labs");
		  tagsDeli.add("KnowledgeRepresentation");
		  tagsDeli.add("google");
		  tagsDeli.add("Macintosh");
		  tagsDeli.add("osx");
		  tagsDeli.add("apps");
		  tagsDeli.add("artificial_intelligence");
		  tagsDeli.add("artificialintelligence");
		  tagsDeli.add("mit");
		  tagsDeli.add("a.i.");
		  tagsDeli.add("ipa");
		  tagsDeli.add("unicode");
		  tagsDeli.add("NLP");
		  tagsDeli.add("DRT");
		  tagsDeli.add("future");
		  tagsDeli.add("EUP");
		  tagsDeli.add("quine");
		  tagsDeli.add("philosophy");
		  tagsDeli.add("philosophers");
		  tagsDeli.add("SocialSoftware");
		  tagsDeli.add("busca");
		  tagsDeli.add("acadêmico");
		  tagsDeli.add("fontes");
		  tagsDeli.add("cnpq");
		  tagsDeli.add("pesquisa");
		  tagsDeli.add("lattes");
		  tagsDeli.add("wikipedia");
		  tagsDeli.add("scholarpedia");
		  tagsDeli.add("bookmarking");
		  tagsDeli.add("bibliography");
		  tagsDeli.add("searchengine");
		  tagsDeli.add("citeseer");
		  tagsDeli.add("easyeclipse");
		  tagsDeli.add("eclipse");
		  tagsDeli.add("ide");
		  tagsDeli.add("utilities");
		  tagsDeli.add("free");
		  tagsDeli.add("freeware");
		  tagsDeli.add("Publishers");
		  tagsDeli.add("mapas");
		  tagsDeli.add("mapas_mentais");
		  tagsDeli.add("pnl");
		  tagsDeli.add("MindMaps");
		  tagsDeli.add("educação");
		  tagsDeli.add("mapasmentais");
		  tagsDeli.add("criatividade");
		  tagsDeli.add("Prolog");
		  tagsDeli.add("Uncertainty");
		  tagsDeli.add("Courses");
		  tagsDeli.add("bayes");
		  tagsDeli.add("datamining");
		  tagsDeli.add("Bayesian");
		  tagsDeli.add("webdesign");
		  tagsDeli.add("supermemo");
		  tagsDeli.add("language");
		  tagsDeli.add("Palm");
		  tagsDeli.add("flashcard");
		  tagsDeli.add("pda");
		  tagsDeli.add("sysinternals");
		  tagsDeli.add("mysql");
		  tagsDeli.add("Tags");
		  tagsDeli.add("Folksonomy");
		  tagsDeli.add("php");
		  tagsDeli.add("downloads");
		  tagsDeli.add("zdnet");
		  tagsDeli.add("audio");
		  tagsDeli.add("Tutorials");
		  tagsDeli.add("dvd");
		  tagsDeli.add("Media");
		  tagsDeli.add("backup");
		  tagsDeli.add("toshiba");
		  tagsDeli.add("electronics");
		  tagsDeli.add("computersandtechnology");
		  tagsDeli.add("Laptop");
		  tagsDeli.add("prolog");
		  tagsDeli.add("Programming");
		  tagsDeli.add("help");
		  tagsDeli.add("rcp");
		  tagsDeli.add("article");
		  tagsDeli.add("Chords");
		  tagsDeli.add("Guitar");
		  tagsDeli.add("Scales");
		  tagsDeli.add("Licks");
		  tagsDeli.add("Progressions");
		  tagsDeli.add("union");
		  tagsDeli.add("espiritualidade");
		  tagsDeli.add("j2ee");
		  tagsDeli.add("examination");
		  tagsDeli.add("certification");
		  tagsDeli.add("tutorials");
		  tagsDeli.add("training");
		  tagsDeli.add("towrite");
		  tagsDeli.add("congresso");
		  tagsDeli.add("interface");
		  tagsDeli.add("evento");
		  tagsDeli.add("ergonomia");
		  tagsDeli.add("usabilidade");
		  tagsDeli.add("ihc");
		  tagsDeli.add("miles");
		  tagsDeli.add("skos");
		  tagsDeli.add("thesaurus");
		  tagsDeli.add("Social_Identity");
		  tagsDeli.add("Anonymity");
		  tagsDeli.add("SIDE");
		  tagsDeli.add("Article");
		  tagsDeli.add("Yahoo!");
		  tagsDeli.add("functional");
		  tagsDeli.add("haskell");
		  tagsDeli.add("acm");
		  tagsDeli.add("hopl");
		  tagsDeli.add("conference");
		  tagsDeli.add("programming-languages");
		  tagsDeli.add("standards");
		  tagsDeli.add("applications");
		  tagsDeli.add("list");
		  tagsDeli.add("gadgets");
		  tagsDeli.add("men");
		  tagsDeli.add("fashion");
		  tagsDeli.add("ERP");
		  tagsDeli.add("hait");
		  tagsDeli.add("work");
		  tagsDeli.add("useful");
		  tagsDeli.add("Skole");
		  tagsDeli.add("rss");
		  tagsDeli.add("bittorrent");
		  tagsDeli.add("tv");
		  tagsDeli.add("pvr");
		  tagsDeli.add("torrent");
		  tagsDeli.add("tecnologia");
		  tagsDeli.add("igreja");
		  tagsDeli.add("meditação");
		  tagsDeli.add("diária");
		  tagsDeli.add("oração");
		  tagsDeli.add("homilia");
		  tagsDeli.add("nokia61");
		  tagsDeli.add("HCI_artikler");
		  tagsDeli.add("weather");
		  tagsDeli.add("climate");
		  tagsDeli.add("climatechange");
		  tagsDeli.add("environment");
		  tagsDeli.add("globalwarming");
		  tagsDeli.add("culture");
		  tagsDeli.add("socialnetworking");
		  tagsDeli.add("mozilla.org");
		  tagsDeli.add("mozilla");
		  tagsDeli.add("firefox");
		  tagsDeli.add("browser");
		  tagsDeli.add("puzzle");
		  tagsDeli.add("grow");
		  tagsDeli.add("online");
		  tagsDeli.add("reputation");
		  tagsDeli.add("blogging");
		  tagsDeli.add("rating");
		  tagsDeli.add("humor");
		  tagsDeli.add("fail");
		  tagsDeli.add("funny");
		  tagsDeli.add("videos");
		  tagsDeli.add("pictures");
		  tagsDeli.add("hipotese");
		  tagsDeli.add("falsear");
		  tagsDeli.add("teoria");
		  tagsDeli.add("tennis");
		  tagsDeli.add("fernando-gonzalez");
		  tagsDeli.add("chile");
		  tagsDeli.add("gentoo");
		  tagsDeli.add("linux");
		  tagsDeli.add("os");
		  tagsDeli.add("tips");
		  tagsDeli.add("barradosfavoritos");
		  tagsDeli.add("noticias");
		  tagsDeli.add("Newspaper");
		  tagsDeli.add("firefox:toolbar");
		  tagsDeli.add("firefox:rss");
		  tagsDeli.add("CEC2007");
		  tagsDeli.add("tostudy");
		  tagsDeli.add("tag");
		  tagsDeli.add("adaptivity");
		  tagsDeli.add("game-based");
		  tagsDeli.add("Learning");
		  tagsDeli.add("discussion");
		  tagsDeli.add("games");
		  tagsDeli.add("kids");
		  tagsDeli.add("experiential");
		  tagsDeli.add("teaching");
		  tagsDeli.add("Learning_by_Doing");
		  tagsDeli.add("approach");
		  tagsDeli.add("learningstyles");
		  tagsDeli.add("gibbs");
		  tagsDeli.add("e-learning");
		  tagsDeli.add("scheme");
		  tagsDeli.add("review");
		  tagsDeli.add("cms");
		  tagsDeli.add("gravata");
		  tagsDeli.add("Java");
		  tagsDeli.add("JEDI");
		  tagsDeli.add("sun");
		  tagsDeli.add("ttd");
		  tagsDeli.add("cheap");
		  tagsDeli.add("Momondo");
		  tagsDeli.add("flights");
		  tagsDeli.add("km");
		  tagsDeli.add("management");
		  tagsDeli.add("web-research");
		  tagsDeli.add("recommnder");
		  tagsDeli.add("social-bookmarking");
		  tagsDeli.add("horses");
		  tagsDeli.add("nds");
		  tagsDeli.add("ds");
		  tagsDeli.add("hacks");
		  tagsDeli.add("driver");
		  tagsDeli.add("homebrew");
		  tagsDeli.add("control");
		  tagsDeli.add("elementary");
		  tagsDeli.add("logo");
		  tagsDeli.add("ict");
		  tagsDeli.add("strands");
		  tagsDeli.add("recommender-system");
		  tagsDeli.add("discovery");
		  tagsDeli.add("recommendation");
		  tagsDeli.add("raft");
		  tagsDeli.add("jogos");
		  tagsDeli.add("Free");
		  tagsDeli.add("miniclip");
		  tagsDeli.add("wars");
		  tagsDeli.add("biology");
		  tagsDeli.add("emergence");
		  tagsDeli.add("self-organization");
		  tagsDeli.add("stigmergy");
		  tagsDeli.add("theory");
		  tagsDeli.add("collaboration");
		  tagsDeli.add("multicore");
		  tagsDeli.add("primas");
		  tagsDeli.add("suitetwo");
		  tagsDeli.add("enterprise2.0_app");
		  tagsDeli.add("enterprise2.0");
		  tagsDeli.add("Functions");
		  tagsDeli.add("Groups");
		  tagsDeli.add("film");
		  tagsDeli.add("streaming");
		  tagsDeli.add("television");
		  tagsDeli.add("madrid");
		  tagsDeli.add("confort");
		  tagsDeli.add("program");
		  tagsDeli.add("ui");
		  tagsDeli.add("editing");
		  tagsDeli.add("Transmeta");
		  tagsDeli.add("livros");
		  tagsDeli.add("cultura");
		  tagsDeli.add("paper");
		  tagsDeli.add("literatura");
		  tagsDeli.add("resumos");
		  tagsDeli.add("breeder");
		  tagsDeli.add("pedigree");
		  tagsDeli.add("databases");
		  tagsDeli.add("genetics");
		  tagsDeli.add("drawing");
		  tagsDeli.add("illustration");
		  tagsDeli.add("opensocial");
		  tagsDeli.add("startup");
		  tagsDeli.add("Lugano");
		  tagsDeli.add("professional");
		  tagsDeli.add("USI");
		  tagsDeli.add("CPI");
		  tagsDeli.add("projectmanagement2.0");
		  tagsDeli.add("basecamp");
		  tagsDeli.add("pm2.0");
		  tagsDeli.add("tripplan");
		  tagsDeli.add("planeticket");
		  tagsDeli.add("hotelbooking");
		  tagsDeli.add("WikiSource");
		  tagsDeli.add("KIWI");
		  tagsDeli.add("PersonalToolbarFolder");
		  tagsDeli.add("Fritid");
		  tagsDeli.add("GRAPPLE");
		  tagsDeli.add("adaptation");
		  tagsDeli.add("PLE");
		  tagsDeli.add("semweb");
		  tagsDeli.add("sparql");
		  tagsDeli.add("aggregator");
		  tagsDeli.add("triplestore");
		  tagsDeli.add("kort");
		  tagsDeli.add("map");
		  tagsDeli.add("ruter");
		  tagsDeli.add("adresser");
		  tagsDeli.add("telefon");
		  tagsDeli.add("innovation");
		  tagsDeli.add("creativity");
		  tagsDeli.add("puzzles");
		  tagsDeli.add("idSpace_project");
		  tagsDeli.add("messenger");
		  tagsDeli.add("block");
		  tagsDeli.add("msn");
		  tagsDeli.add("status");
		  tagsDeli.add("ajax");
		  tagsDeli.add("animation");
		  tagsDeli.add("mochikit");
		  tagsDeli.add("css");
		  tagsDeli.add("javascript");
		  tagsDeli.add("effects");
		  tagsDeli.add("fernsehen");
		  tagsDeli.add("iTV");
		  tagsDeli.add("color");
		  tagsDeli.add("flickr");
		  tagsDeli.add("shows");
		  tagsDeli.add("series");
		  tagsDeli.add("maemo");
		  tagsDeli.add("mobile");
		  tagsDeli.add("nokia");
		  tagsDeli.add("ce2.0");
		  tagsDeli.add("Alias");
		  tagsDeli.add("psicology");
		  tagsDeli.add("sociology");
		  tagsDeli.add("authority");
		  tagsDeli.add("colocolo");
		  tagsDeli.add("futbol");
		  tagsDeli.add("soccer");
		  tagsDeli.add("deporte");
		  tagsDeli.add("colo-colo");
		  tagsDeli.add("liturgia");
		  tagsDeli.add("bíblia");
		  tagsDeli.add("evangelho");
		  tagsDeli.add("ie7");
		  tagsDeli.add("microsoftwebsites");
		  tagsDeli.add("Microsoft_Websites");
		  tagsDeli.add("microsoft-websites");
		  tagsDeli.add("wishlist");
		  tagsDeli.add("Anarchy");
		  tagsDeli.add("2.0");
		  tagsDeli.add("del.icio.us/cenintelligence");
		  tagsDeli.add("bikes");
		  tagsDeli.add("bicycle");
		  tagsDeli.add("transportation");
		  tagsDeli.add("code");
		  tagsDeli.add("HTML");
		  tagsDeli.add("archive");
		  tagsDeli.add("forms");
		  tagsDeli.add("slider");
		  tagsDeli.add("Shopping");
		  tagsDeli.add("8051");
		  tagsDeli.add("mcs51");
		  tagsDeli.add("mide");
		  tagsDeli.add("store");
		  tagsDeli.add("pirate");
		  tagsDeli.add("webcomic");
		  tagsDeli.add("comics");
		  tagsDeli.add("monkey");
		  tagsDeli.add("daily");
		  tagsDeli.add("robot");
		  tagsDeli.add("IBM");
		  tagsDeli.add("inspiration");
		  tagsDeli.add("webdev");
		  tagsDeli.add("workflow");
		  tagsDeli.add("communitysoftware");
		  tagsDeli.add("webdevelopment");
		  tagsDeli.add("zimbra");
		  tagsDeli.add("Links");
		  tagsDeli.add("yahoo");
		  tagsDeli.add("del.icio.us");
		  tagsDeli.add("folksonomia");
		  tagsDeli.add("tolkien");
		  tagsDeli.add("dictionary");
		  tagsDeli.add("arda");
		  tagsDeli.add("lotr");
		  tagsDeli.add("fantasy");
		  tagsDeli.add("capes");
		  tagsDeli.add("mestrado");
		  tagsDeli.add("publicações");
		  tagsDeli.add("portal");
		  tagsDeli.add("teses");
		  tagsDeli.add("revistas");
		  tagsDeli.add("JavaDesktopSystem");
		  tagsDeli.add("AAU");
		  tagsDeli.add("censors");
		  tagsDeli.add("computerscience");
		  tagsDeli.add("deutsch");
		  tagsDeli.add("spanish");
		  tagsDeli.add("wörterbuch");
		  tagsDeli.add("translation");
		  tagsDeli.add("diary");
		  tagsDeli.add("powerlaw");
		  tagsDeli.add("pareto");
		  tagsDeli.add("zipf");
		  tagsDeli.add("longtail");
		  tagsDeli.add("statistics");
		  tagsDeli.add("pm2.0_star5");
		  tagsDeli.add("distributed");
		  tagsDeli.add("timbray");
		  tagsDeli.add("performance");
		  tagsDeli.add("concurrency");
		  tagsDeli.add("threads");
		  tagsDeli.add("delphi");
		  tagsDeli.add("string");
		  tagsDeli.add("e-mail");
		  tagsDeli.add("mail");
		  tagsDeli.add("signature");
		  tagsDeli.add("email");
		  tagsDeli.add("CSS");
		  tagsDeli.add("karma");
		  tagsDeli.add("homepage");
		  tagsDeli.add("widgets");
		  tagsDeli.add("netvibes");
		  tagsDeli.add("security");
		  tagsDeli.add("forensics");
		  tagsDeli.add("campinas");
		  tagsDeli.add("puc");
		  tagsDeli.add("internacional");
		  tagsDeli.add("conjuntura");
		  tagsDeli.add("categories");
		  tagsDeli.add("classification");
		  tagsDeli.add("categorization");
		  tagsDeli.add("regex");
		  tagsDeli.add("regexp");
		  tagsDeli.add("vectorization");
		  tagsDeli.add("gcc");
		  tagsDeli.add("compiler");
		  tagsDeli.add("ingles");
		  tagsDeli.add("idiomas");
		  tagsDeli.add("esl");
		  tagsDeli.add("inglês");
		  tagsDeli.add("independent");
		  tagsDeli.add("modelling");
		  tagsDeli.add("conceptual");
		  tagsDeli.add("device");
		  tagsDeli.add("dissertation");
		  tagsDeli.add("humour");
		  tagsDeli.add("HCC");
		  tagsDeli.add("Norman");
		  tagsDeli.add("precipitation");
		  tagsDeli.add("snow");
		  tagsDeli.add("rain");
		  tagsDeli.add("umbrella");
		  tagsDeli.add("copypaste");
		  tagsDeli.add("type");
		  tagsDeli.add("paste");
		  tagsDeli.add("typography");
		  tagsDeli.add("character");
		  tagsDeli.add("copy");
		  tagsDeli.add("writing");
		  tagsDeli.add("swiss");
		  tagsDeli.add("entrepreneurship");
		  tagsDeli.add("jobs");
		  tagsDeli.add("switzerland");
		  tagsDeli.add("CTI");
		  tagsDeli.add("venture");
		  tagsDeli.add("license");
		  tagsDeli.add("neural-networks");
		  tagsDeli.add("cscl");
		  tagsDeli.add("2009");
		  tagsDeli.add("rhodes");
		  tagsDeli.add("david_foster_wallace");
		  tagsDeli.add("infinite_jest");
		  tagsDeli.add("Infinite");
		  tagsDeli.add("English");
		  tagsDeli.add("dfw");
		  tagsDeli.add("InfiniteJest");
		  tagsDeli.add("Wallace");
		  tagsDeli.add("DavidFosterWallace");
		  tagsDeli.add("novel");
		  tagsDeli.add("David");
		  tagsDeli.add("Foster");
		  tagsDeli.add("Jest");
		  tagsDeli.add("anarchyonline");
		  tagsDeli.add("tagcloud");
		  tagsDeli.add("social_tagging");
		  tagsDeli.add("liferay");
		  tagsDeli.add("professsionals");
		  tagsDeli.add("twitter");
		  tagsDeli.add("cognitive");
		  tagsDeli.add("cartoons");
		  tagsDeli.add("episodes");
		  tagsDeli.add("futurama");
		  tagsDeli.add("virtual");
		  tagsDeli.add("templates");
		  tagsDeli.add("themes");
		  tagsDeli.add("wordpress");
		  tagsDeli.add("denbosch");
		  tagsDeli.add("concerts");
		  tagsDeli.add("mapreduce");
		  tagsDeli.add("hadoop");
		  tagsDeli.add("methodology");
		  tagsDeli.add("neon");
		  tagsDeli.add("gangemi");
		  tagsDeli.add("bookmarks_toolbar");
		  tagsDeli.add("bbc");
		  tagsDeli.add("Lines");
		  tagsDeli.add("Product");
		  tagsDeli.add("it");
		  tagsDeli.add("Economic");
		  tagsDeli.add("rup");
		  tagsDeli.add("process");
		  tagsDeli.add("cs");
		  tagsDeli.add("compilers");
		  tagsDeli.add("Google");
		  tagsDeli.add("engine");
		  tagsDeli.add("everything");
		  tagsDeli.add("doubts");
		  tagsDeli.add("breda");
		  tagsDeli.add("poppodium");
		  tagsDeli.add("scripting");
		  tagsDeli.add("PHP");
		  tagsDeli.add("vidensystemer");
		  tagsDeli.add("Jobmuligheder");
		  tagsDeli.add("magritte");
		  tagsDeli.add("influence");
		  tagsDeli.add("advertising");
		  tagsDeli.add("DLACIII");
		  tagsDeli.add("trends");
		  tagsDeli.add("tippingpoint");
		  tagsDeli.add("electronic");
		  tagsDeli.add("sports");
		  tagsDeli.add("gaming");
		  tagsDeli.add("league");
		  tagsDeli.add("esports");
		  tagsDeli.add("profile");
		  tagsDeli.add("LinkedIn");
		  tagsDeli.add("enterprise2.0_tech");
		  tagsDeli.add("microbook");
		  tagsDeli.add("organisationalman");
		  tagsDeli.add("organisation");
		  tagsDeli.add("_explosion");
		  tagsDeli.add("conferences");
		  tagsDeli.add("VirtualWorld");
		  tagsDeli.add("3D");
		  tagsDeli.add("taal");
		  tagsDeli.add("woordenboek");
		  tagsDeli.add("nederlands");
		  tagsDeli.add("swedish");
		  tagsDeli.add("zweeds");
		  tagsDeli.add("dutch");
		  tagsDeli.add("grammar");
		  tagsDeli.add("linguistics");
		  tagsDeli.add("errors");
		  tagsDeli.add("workshop");
		  tagsDeli.add("_martin");
		  tagsDeli.add("computational_humanities");
		  tagsDeli.add("socialmedia");
		  tagsDeli.add("career");
		  tagsDeli.add("danahboyd");
		  tagsDeli.add("uni2.0");
		  tagsDeli.add("cache");
		  tagsDeli.add("memcached");
		  tagsDeli.add("SemanticWikis");
		  tagsDeli.add("feelings");
		  tagsDeli.add("articles");
		  tagsDeli.add("futuretelling");
		  tagsDeli.add("CS1");
		  tagsDeli.add("CSCL");
		  tagsDeli.add("mmo");
		  tagsDeli.add("ao");
		  tagsDeli.add("mmorpg");
		  tagsDeli.add("anarchy-online");
		  tagsDeli.add("anarchy_online");
		  tagsDeli.add("olympics");
		  tagsDeli.add("disability");
		  tagsDeli.add("trisomy21");
		  tagsDeli.add("downsyndrome");
		  tagsDeli.add("specialolympics");
		  tagsDeli.add("fonts");
		  tagsDeli.add("resume");
		  tagsDeli.add("cv");
		  tagsDeli.add("academic");
		  tagsDeli.add("latex");
		  tagsDeli.add("tex");
		  tagsDeli.add("bugtracker");
		  tagsDeli.add("projectmanagement");
		  tagsDeli.add("rails");
		  tagsDeli.add("ruby");
		  tagsDeli.add("project");
		  tagsDeli.add("rubyonrails");
		  tagsDeli.add("sql");
		  tagsDeli.add("commands");
		  tagsDeli.add("photoshop");
		  tagsDeli.add("gradient");
		  tagsDeli.add("favoritos");
		  tagsDeli.add("ie");
		  tagsDeli.add("turbogears");
		  tagsDeli.add("actors");
		  tagsDeli.add("entertainment");
		  tagsDeli.add("IRC");
		  tagsDeli.add("geek");
		  tagsDeli.add("quotes");
		  tagsDeli.add("icons");
		  tagsDeli.add("glossary");
		  tagsDeli.add("adobe");
		  tagsDeli.add("images");
		  tagsDeli.add("competitions");
		  tagsDeli.add("Dictionary");
		  tagsDeli.add("facts");
		  tagsDeli.add("international");
		  tagsDeli.add("emms");
		  tagsDeli.add("student");
		  tagsDeli.add("association");
		  tagsDeli.add("vídeos");
		  tagsDeli.add("YouTube");
		  tagsDeli.add("baixar");
		  tagsDeli.add("Encyclopedia");
		  tagsDeli.add("Education");
		  tagsDeli.add("edtech");
		  tagsDeli.add("wsgi");
		  tagsDeli.add("librarything");
		  tagsDeli.add("amazon");
		  tagsDeli.add("radio");
		  tagsDeli.add("kuechenradio");
		  tagsDeli.add("mp3");
		  tagsDeli.add("berlin");
		  tagsDeli.add("podcast");
		  tagsDeli.add("spoof");
		  tagsDeli.add("slides");
		  tagsDeli.add("algorithms");
		  tagsDeli.add("material");
		  tagsDeli.add("home&living");
		  tagsDeli.add("Home_&_Living");
		  tagsDeli.add("calendar");
		  tagsDeli.add("bolsa");
		  tagsDeli.add("bovespa");
		  tagsDeli.add("de");
		  tagsDeli.add("finanças");
		  tagsDeli.add("valores");
		  tagsDeli.add("investimentos");
		  tagsDeli.add("economia");
		  tagsDeli.add("recomendation");
		  tagsDeli.add("monitoring");
		  tagsDeli.add("service");
		  tagsDeli.add("blitz");
		  tagsDeli.add("gamedev");
		  tagsDeli.add("basic");
		  tagsDeli.add("phone");
		  tagsDeli.add("maps");
		  tagsDeli.add("søgning");
		  tagsDeli.add("adresses");
		  tagsDeli.add("telefonnumre");
		  tagsDeli.add("Mozilla_Firefox");
		  tagsDeli.add("mediastudies");
		  tagsDeli.add("blogosphere");
		  tagsDeli.add("corporate");
		  tagsDeli.add("socialbookmarking");
		  tagsDeli.add("collaborative");
		  tagsDeli.add("ibm");
		  tagsDeli.add("Stowe");
		  tagsDeli.add("Uncle");
		  tagsDeli.add("slavery");
		  tagsDeli.add("Harriet");
		  tagsDeli.add("UncleTomsCabin");
		  tagsDeli.add("Key");
		  tagsDeli.add("Cabin");
		  tagsDeli.add("Tom's");
		  tagsDeli.add("Beecher");
		  tagsDeli.add("bpm");
		  tagsDeli.add("talks");
		  tagsDeli.add("c");
		  tagsDeli.add("mundus");
		  tagsDeli.add("scholarship");
		  tagsDeli.add("programs");
		  tagsDeli.add("europe");
		  tagsDeli.add("master");
		  tagsDeli.add("erasmus");
		  tagsDeli.add("recomendados");
		  tagsDeli.add("Portuguese");
		  tagsDeli.add("jornais");
		  tagsDeli.add("notícias");
		  tagsDeli.add("jornal");
		  tagsDeli.add("Brasil");
		  tagsDeli.add("dhtml");
		  tagsDeli.add("dom");
		  tagsDeli.add("js");
		  tagsDeli.add("programação");
		  tagsDeli.add("converter");
		  tagsDeli.add("parsing");
		  tagsDeli.add("symbols");
		  tagsDeli.add("tickets");
		  tagsDeli.add("airlines");
		  tagsDeli.add("cbs");
		  tagsDeli.add("howimetyourmother");
		  tagsDeli.add("comedy");
		  tagsDeli.add("float");
		  tagsDeli.add("musikinstrumenter");
		  tagsDeli.add("Timani");
		  tagsDeli.add("zityzen");
		  tagsDeli.add("psychology");
		  tagsDeli.add("freud");
		  tagsDeli.add("informação");
		  tagsDeli.add("UMAP");
		  tagsDeli.add("OpenLearnerModel");
		  tagsDeli.add("TEL");
		  tagsDeli.add("steel");
		  tagsDeli.add("stainless");
		  tagsDeli.add("cambridge");
		  tagsDeli.add("Vínculos");
		  tagsDeli.add("otherlinks");
		  tagsDeli.add("Microsoft");
		  tagsDeli.add("unix");
		  tagsDeli.add("C");
		  tagsDeli.add("data");
		  tagsDeli.add("thesis");
		  tagsDeli.add("IR");
		  tagsDeli.add("cocoa");
		  tagsDeli.add("idols");
		  tagsDeli.add("SPLadoption");
		  tagsDeli.add("movie");
		  tagsDeli.add("speech");
		  tagsDeli.add("supervisor");
		  tagsDeli.add("sysadmin");
		  tagsDeli.add("daemon");
		  tagsDeli.add("algoritmos");
		  tagsDeli.add("adblocker");
		  tagsDeli.add("extension");
		  tagsDeli.add("blocker");
		  tagsDeli.add("plugins");
		  tagsDeli.add("ads");
		  tagsDeli.add("engraçado");
		  tagsDeli.add("comédia");
		  tagsDeli.add("diss");
		  tagsDeli.add("philosophie");
		  tagsDeli.add("cern");
		  tagsDeli.add("medientheorie");
		  tagsDeli.add("webcomics");
		  tagsDeli.add("nerd");
		  tagsDeli.add("xkcd");
		  tagsDeli.add("interoperability");
		  tagsDeli.add("real_estate");
		  tagsDeli.add("houses");
		  tagsDeli.add("for_sale");
		  tagsDeli.add("relationships");
		  tagsDeli.add("sex");
		  tagsDeli.add("flex");
		  tagsDeli.add("as3");
		  tagsDeli.add("actionscript");
		  tagsDeli.add("datastructures");
		  tagsDeli.add("words");
		  tagsDeli.add("children");
		  tagsDeli.add("2007");
		  tagsDeli.add("mozillaproject");
		  tagsDeli.add("mozilla_project");
		  tagsDeli.add("mozilla-project");
		  tagsDeli.add("me");
		  tagsDeli.add("joone");
		  tagsDeli.add("SPLreferences");
		  tagsDeli.add("accessibility");
		  tagsDeli.add("Neilsen");
		  tagsDeli.add("repository");
		  tagsDeli.add("pedagogy");
		  tagsDeli.add("cloud");
		  tagsDeli.add("switch");
		  tagsDeli.add("retrieal");
		  tagsDeli.add("foto");
		  tagsDeli.add("web20");
		  tagsDeli.add("creativecommons");
		  tagsDeli.add("dating");
		  tagsDeli.add("dataset");
		  tagsDeli.add("challenge");
		  tagsDeli.add("2008");
		  tagsDeli.add("spam");
		  tagsDeli.add("mining");
		  tagsDeli.add("SPLcompanies");
		  tagsDeli.add("SoftwareProductLines");
		  tagsDeli.add("forum");
		  tagsDeli.add("programacao");
		  tagsDeli.add("embedded");
		  tagsDeli.add("ubuntu");
		  tagsDeli.add("quizzes");
		  tagsDeli.add("frankfurt");
		  tagsDeli.add("airport");
		  tagsDeli.add("germany");
		  tagsDeli.add("heidelberg");
		  tagsDeli.add("semantics");
		  tagsDeli.add("event");
		  tagsDeli.add("iswc");
		  tagsDeli.add("Publications");
		  tagsDeli.add("Tom_Postmes");
		  tagsDeli.add("Apache");
		  tagsDeli.add("Tutorial");
		  tagsDeli.add("Tomcat");
		  tagsDeli.add("Websphere");
		  tagsDeli.add("quality");
		  tagsDeli.add("good");
		  tagsDeli.add("12seasons");
		  tagsDeli.add("southpark");
		  tagsDeli.add("semanticsearch");
		  tagsDeli.add("knowledge_management");
		  tagsDeli.add("consulting");
		  tagsDeli.add("austria");
		  tagsDeli.add("debug");
		  tagsDeli.add("communication");
		  tagsDeli.add("powerpoint");
		  tagsDeli.add("presentation");
		  tagsDeli.add("industrial");
		  tagsDeli.add("nachrichten");
		  tagsDeli.add("pc");
		  tagsDeli.add("Usability");
		  tagsDeli.add("webdesing");
		  tagsDeli.add("Accessibility");
		  tagsDeli.add("mentoring");
		  tagsDeli.add("NetGeneration");
		  tagsDeli.add("vasco");
		  tagsDeli.add("casaca");
		  tagsDeli.add("eurico");
		  tagsDeli.add("miranda");
		  tagsDeli.add("blogs");
		  tagsDeli.add("socialsemanticweb");
		  tagsDeli.add("social_bookmarking");
		  tagsDeli.add("javadoc");
		  tagsDeli.add("ectel08");
		  tagsDeli.add("enterprise");
		  tagsDeli.add("taxonomy");
		  tagsDeli.add("kernel");
		  tagsDeli.add("documentation");
		  tagsDeli.add("meaning");
		  tagsDeli.add("pronunciation");
		  tagsDeli.add("Research");
		  tagsDeli.add("Praktisk");
		  tagsDeli.add("svm");
		  tagsDeli.add("machine");
		  tagsDeli.add("Machine-Learning");
		  tagsDeli.add("implementation");
		  tagsDeli.add("machine_learning");
		  tagsDeli.add("weka");
		  tagsDeli.add("copenhagen");
		  tagsDeli.add("transport");
		  tagsDeli.add("rejser");
		  tagsDeli.add("bus");
		  tagsDeli.add("rejseplan");
		  tagsDeli.add("acessibilidade");
		  tagsDeli.add("journals");
		  tagsDeli.add("https");
		  tagsDeli.add("ssl");
		  tagsDeli.add("Security");
		  tagsDeli.add("tomcat");
		  tagsDeli.add("paddle_steamer");
		  tagsDeli.add("Mariager_Fjord");
		  tagsDeli.add("Svanen");
		  tagsDeli.add("hjuldamper");
		  tagsDeli.add("limo");
		  tagsDeli.add("socialnetworks");
		  tagsDeli.add("visualization-graphs");
		  tagsDeli.add("social-network");
		  tagsDeli.add("web-2.0");
		  tagsDeli.add("efimova");
		  tagsDeli.add("productivity");
		  tagsDeli.add("email20");
		  tagsDeli.add("email20_star5");
		  tagsDeli.add("pm20");
		  tagsDeli.add("_overload");
		  tagsDeli.add("webgames");
		  tagsDeli.add("time");
		  tagsDeli.add("world");
		  tagsDeli.add("conversion");
		  tagsDeli.add("timezones");
		  tagsDeli.add("politiken");
		  tagsDeli.add("argumentmapping");
		  tagsDeli.add("NewMineLab");
		  tagsDeli.add("argumentation");
		  tagsDeli.add("argument");
		  tagsDeli.add("discover");
		  tagsDeli.add("organization");
		  tagsDeli.add("not–for–profit");
		  tagsDeli.add("stocks");
		  tagsDeli.add("indicators");
		  tagsDeli.add("investing");
		  tagsDeli.add("finance");
		  tagsDeli.add("forex");
		  tagsDeli.add("trading");
		  tagsDeli.add("zurich");
		  tagsDeli.add("börse");
		  tagsDeli.add("schweiz");
		  tagsDeli.add("Banking");
		  tagsDeli.add("banken");
		  tagsDeli.add("plone");
		  tagsDeli.add("zope");
		  tagsDeli.add("components");
		  tagsDeli.add("piracy");
		  tagsDeli.add("antipiracy");
		  tagsDeli.add("digitalculture");
		  tagsDeli.add("netculture");
		  tagsDeli.add("News");
		  tagsDeli.add("reuters");
		  tagsDeli.add("works");
		  tagsDeli.add("AJAX");
		  tagsDeli.add("JavaScript");
		  tagsDeli.add("Django");
		  tagsDeli.add("spl");
		  tagsDeli.add("typesetting");
		  tagsDeli.add("pdf");
		  tagsDeli.add("directory");
		  tagsDeli.add("música");
		  tagsDeli.add("tabs");
		  tagsDeli.add("desenho");
		  tagsDeli.add("guitar");
		  tagsDeli.add("cifras");
		  tagsDeli.add("violão");
		  tagsDeli.add("acordes");
		  tagsDeli.add("musica");
		  tagsDeli.add("developerinformation");
		  tagsDeli.add("html");
		  tagsDeli.add("Standards");
		  tagsDeli.add("gestão");
		  tagsDeli.add("conhecimento");
		  tagsDeli.add("do");
		  tagsDeli.add("neural");
		  tagsDeli.add("neuralnetworks");
		  tagsDeli.add("neuralnetwork");
		  tagsDeli.add("lib");
		  tagsDeli.add("PROLEARN");
		  tagsDeli.add("autoajuda");
		  tagsDeli.add("success");
		  tagsDeli.add("cerebro");
		  tagsDeli.add("interessante");
		  tagsDeli.add("brain");
		  tagsDeli.add("mensagens");
		  tagsDeli.add("Testing");
		  tagsDeli.add("softwiki");
		  tagsDeli.add("requirements");
		  tagsDeli.add("tool");
		  tagsDeli.add("CMC");
		  tagsDeli.add("Social_Influence");
		  tagsDeli.add("Russell_Spears");
		  tagsDeli.add("j2EE");
		  tagsDeli.add("Bookmarks_Toolbar");
		  tagsDeli.add("governance");
		  tagsDeli.add("logiclibrary");
		  tagsDeli.add("soa");
		  tagsDeli.add("Bauen");
		  tagsDeli.add("crisis");
		  tagsDeli.add("bfo");
		  tagsDeli.add("smith");
		  tagsDeli.add("brasileiro");
		  tagsDeli.add("papers");
		  tagsDeli.add("wuerzburg");
		  tagsDeli.add("lsa");
		  tagsDeli.add("microblogging");
		  tagsDeli.add("academia");
		  tagsDeli.add("psp");
		  tagsDeli.add("pe");
		  tagsDeli.add("PHDstudies");
		  tagsDeli.add("regulations");
		  tagsDeli.add("tabletpc");
		  tagsDeli.add("presenter");
		  tagsDeli.add("metrics");
		  tagsDeli.add("nielsen");
		  tagsDeli.add("linkeddata");
		  tagsDeli.add("agile");
		  tagsDeli.add("extreme");
		  tagsDeli.add("3d");
		  tagsDeli.add("quake");
		  tagsDeli.add("environmentalpsychology");
		  tagsDeli.add("013");
		  tagsDeli.add("muziek");
		  tagsDeli.add("gigs");
		  tagsDeli.add("tilburg");
		  tagsDeli.add("vocabulary");
		  tagsDeli.add("drupal");
		  tagsDeli.add("cyberinfrastructure");
		  tagsDeli.add("nsf");
		  tagsDeli.add("automation");
		  tagsDeli.add("testing");
		  tagsDeli.add("tdd");
		  tagsDeli.add("online_tools");
		  tagsDeli.add("convert");
		  tagsDeli.add("liens");
		  tagsDeli.add("preview");
		  tagsDeli.add("lines");
		  tagsDeli.add("product");
		  tagsDeli.add("customization");
		  tagsDeli.add("productline");
		  tagsDeli.add("configmanagement");
		  tagsDeli.add("graz");
		  tagsDeli.add("congress");
		  tagsDeli.add("sls");
		  tagsDeli.add("jquery");
		  tagsDeli.add("tooltip");
		  tagsDeli.add("MSN");
		  tagsDeli.add("Internet");
		  tagsDeli.add("firefox:bookmarks");
		  tagsDeli.add("harrypotter");
		  tagsDeli.add("hp");
		  tagsDeli.add("harry_potter");
		  tagsDeli.add("fanart");
		  tagsDeli.add("potter");
		  tagsDeli.add("drawings");
		  tagsDeli.add("machinelearning");
		  tagsDeli.add("webservice");
		  tagsDeli.add("cfp");
		  tagsDeli.add("meetings2009");
		  tagsDeli.add("hcii");
		  tagsDeli.add("human_computer_interaction");
		  tagsDeli.add("rct");
		  tagsDeli.add("winterthur");
		  tagsDeli.add("json");
		  tagsDeli.add("triplify");
		  tagsDeli.add("negócio");
		  tagsDeli.add("analyst");
		  tagsDeli.add("Business");
		  tagsDeli.add("analista");
		  tagsDeli.add("newspaper");
		  tagsDeli.add("avis");
		  tagsDeli.add("nyheder");
		  tagsDeli.add("aviser");
		  tagsDeli.add("danish");
		  tagsDeli.add("SWKM2008");
		  tagsDeli.add("knowledgemanagement");
		  tagsDeli.add("collectivism");
		  tagsDeli.add("symbol");
		  tagsDeli.add("loops");
		  tagsDeli.add("zen");
		  tagsDeli.add("local+community");
		  tagsDeli.add("support");
		  tagsDeli.add("fishing");
		  tagsDeli.add("rfc");
		  tagsDeli.add("symbian");
		  tagsDeli.add("Nokia61");
		  tagsDeli.add("Personal_Publishing");
		  tagsDeli.add("nxt");
		  tagsDeli.add("robotics");
		  tagsDeli.add("idc");
		  tagsDeli.add("scrum");
		  tagsDeli.add("policy/elearning");
		  tagsDeli.add("theory/education");
		  tagsDeli.add("social_software");
		  tagsDeli.add("friends");
		  tagsDeli.add("facebook");
		  tagsDeli.add("cable");
		  tagsDeli.add("p");
		  tagsDeli.add("pylons");
		  tagsDeli.add("slang");
		  tagsDeli.add("pirates");
		  tagsDeli.add("piratetalk");
		  tagsDeli.add("pirateglossary");
		  tagsDeli.add("operatingsystem");
		  tagsDeli.add("distro");
		  tagsDeli.add("distribution");
		  tagsDeli.add("mindmap");
		  tagsDeli.add("mapping");
		  tagsDeli.add("davinci");
		  tagsDeli.add("leonardo");
		  tagsDeli.add("leonardo_da_vinci");
		  tagsDeli.add("screencast");
		  tagsDeli.add("graph");
		  tagsDeli.add("iwk");
		  tagsDeli.add("events");
		  tagsDeli.add("uniwien");
		  tagsDeli.add("usergeneratedcontent");
		  tagsDeli.add("remixing");
		  tagsDeli.add("object");
		  tagsDeli.add("metadata");
		  tagsDeli.add("Motives");
		  tagsDeli.add("dogear");
		  tagsDeli.add("rewrite");
		  tagsDeli.add("joelonsoftware");
		  tagsDeli.add("faq");
		  tagsDeli.add("gnuplot");
		  tagsDeli.add("sioc");
		  tagsDeli.add("stats");
		  tagsDeli.add("webanalytics");
		  tagsDeli.add("Piwik");
		  tagsDeli.add("analytics");
		  tagsDeli.add("webstats");
		  tagsDeli.add("openproj");
		  tagsDeli.add("leadership");
		  tagsDeli.add("john");
		  tagsDeli.add("maxwell");
		  tagsDeli.add("loa");
		  tagsDeli.add("guarino");
		  tagsDeli.add("dolce");
		  tagsDeli.add("moderatedsearch");
		  tagsDeli.add("eu");
		  tagsDeli.add("shadows");
		  tagsDeli.add("moodle");
		  tagsDeli.add("gtd");
		  tagsDeli.add("motivation");
		  tagsDeli.add("LIVELY");
		  tagsDeli.add("secondlife");
		  tagsDeli.add("recipes");
		  tagsDeli.add("food");
		  tagsDeli.add("oliverstwist");
		  tagsDeli.add("cooking");
		  tagsDeli.add("detergente");
		  tagsDeli.add("maldoso");
		  tagsDeli.add("torrada");
		  tagsDeli.add("pula");
		  tagsDeli.add("pirata");
		  tagsDeli.add("putaria");
		  tagsDeli.add("sri");
		  tagsDeli.add("caipirinha");
		  tagsDeli.add("bolo");
		  tagsDeli.add("lanka");
		  tagsDeli.add("maluca");
		  tagsDeli.add("wine");
		  tagsDeli.add("hvv");
		  tagsDeli.add("fahrplan");
		  tagsDeli.add("hamburg");
		  tagsDeli.add("Germany");
		  tagsDeli.add("bahn");
		  tagsDeli.add("oreilly");
		  tagsDeli.add("dollar");
		  tagsDeli.add("euro");
		  tagsDeli.add("exchance");
		  tagsDeli.add("money");
		  tagsDeli.add("Communication");
		  tagsDeli.add("bdd");
		  tagsDeli.add("socail");
		  tagsDeli.add("languages");
		  tagsDeli.add("scala");
		  tagsDeli.add("hardware");
		  tagsDeli.add("solaris");
		  tagsDeli.add("crude_oil");
		  tagsDeli.add("brent");
		  tagsDeli.add("west_texas_intermediate");
		  tagsDeli.add("oil_prices");
		  tagsDeli.add("doctorlife");
		  tagsDeli.add("chinesemedicine");
		  tagsDeli.add("girlsfeeling");
		  tagsDeli.add("pets");
		  tagsDeli.add("floorball");
		  tagsDeli.add("innebandy");
		  tagsDeli.add("intelligence");
		  tagsDeli.add("system");
		  tagsDeli.add("parker");
		  tagsDeli.add("sommelier");
		  tagsDeli.add("OLM");
		  tagsDeli.add("its");
		  tagsDeli.add("tcl");
		  tagsDeli.add("enciclopedia");
		  tagsDeli.add("sign");
		  tagsDeli.add("continue");
		  tagsDeli.add("dev");
		  tagsDeli.add("remote");
		  tagsDeli.add("uefa");
		  tagsDeli.add("hund");
		  tagsDeli.add("ferien");
		  tagsDeli.add("Trento");
		  tagsDeli.add("VentureChallenge");
		  tagsDeli.add("VentureLab");
		  tagsDeli.add("casestudy");
		  tagsDeli.add("college");
		  tagsDeli.add("falloutboy");
		  tagsDeli.add("fall_out_boy");
		  tagsDeli.add("examples");
		  tagsDeli.add("wired");
		  tagsDeli.add("geeks");
		  tagsDeli.add("as");
		  tagsDeli.add("autism");
		  tagsDeli.add("aspergers");
		  tagsDeli.add("protocol");
		  tagsDeli.add("queue");
		  tagsDeli.add("specifications");
		  tagsDeli.add("amqp");
		  tagsDeli.add("gba");
		  tagsDeli.add("writing-papers");
		  tagsDeli.add("conversions");
		  tagsDeli.add("fahrenheit");
		  tagsDeli.add("celsius");
		  tagsDeli.add("temperature");
		  tagsDeli.add("cepedia");
		  tagsDeli.add("scifi");
		  tagsDeli.add("firewall");
		  tagsDeli.add("iproute2");
		  tagsDeli.add("types");
		  tagsDeli.add("medication");
		  tagsDeli.add("mental_disorder");
		  tagsDeli.add("webstandards");
		  tagsDeli.add("apache");
		  tagsDeli.add("solr");
		  tagsDeli.add("OpenSource");
		  tagsDeli.add("lucene");
		  tagsDeli.add("ler");
		  tagsDeli.add("pesquisar");
		  tagsDeli.add("estudar");
		  tagsDeli.add("artigo");
		  tagsDeli.add("ctic");
		  tagsDeli.add("foundation");
		  tagsDeli.add("asturias");
		  tagsDeli.add("deutschland");
		  tagsDeli.add("info");
		  tagsDeli.add("stadt");
		  tagsDeli.add("turism");
		  tagsDeli.add("Hamburg");
		  tagsDeli.add("R");
		  tagsDeli.add("wallpapers");
		  tagsDeli.add("kp_consult");
		  tagsDeli.add("job");
		  tagsDeli.add("pstricks");
		  tagsDeli.add("postscript");
		  tagsDeli.add("portuguese");
		  tagsDeli.add("tolearn");
		  tagsDeli.add("todo");
		  tagsDeli.add("wxpython");
		  tagsDeli.add("taskmanager");
		  tagsDeli.add("numbers");
		  tagsDeli.add("comparison");
		  tagsDeli.add("weltinzahlen");
		  tagsDeli.add("countries");
		  tagsDeli.add("zahlen");
		  tagsDeli.add("statistik");
		  tagsDeli.add("welt");
		  tagsDeli.add("Design");
		  tagsDeli.add("diagrams");
		  tagsDeli.add("experience");
		  tagsDeli.add("finder");
		  tagsDeli.add("PhD");
		  tagsDeli.add("TENCompetence");
		  tagsDeli.add("networks");
		  tagsDeli.add("sustainability");
		  tagsDeli.add("ecosystems");
		  tagsDeli.add("resilient");
		  tagsDeli.add("balance");
		  tagsDeli.add("amsterdam");
		  tagsDeli.add("netherlands");
		  tagsDeli.add("venue");
		  tagsDeli.add("war");
		  tagsDeli.add("released");
		  tagsDeli.add("John");
		  tagsDeli.add("prisoner_of_war");
		  tagsDeli.add("McCain");
		  tagsDeli.add("pow");
		  tagsDeli.add("sadness");
		  tagsDeli.add("gaussiana");
		  tagsDeli.add("gaussean");
		  tagsDeli.add("matematica");
		  tagsDeli.add("iso");
		  tagsDeli.add("iec");
		  tagsDeli.add("bolig");
		  tagsDeli.add("hus");
		  tagsDeli.add("boligpriser");
		  tagsDeli.add("housing");
		  tagsDeli.add("e-commerce");
		  tagsDeli.add("e-business");
		  tagsDeli.add("visualisation");
		  tagsDeli.add("s60");
		  tagsDeli.add("e-series");
		  tagsDeli.add("e61");
		  tagsDeli.add("webmail");
		  tagsDeli.add("hotmail");
		  tagsDeli.add("correo");
		  tagsDeli.add("Martin_Lea");
		  tagsDeli.add("newsportal");
		  tagsDeli.add("ERPcourse");
		  tagsDeli.add("Systems");
		  tagsDeli.add("wetter");
		  tagsDeli.add("hilfsmittel_und_datenbanken");
		  tagsDeli.add("wetterbericht");
		  tagsDeli.add("fast");
		  tagsDeli.add("supercomputer");
		  tagsDeli.add("emulation");
		  tagsDeli.add("coffee");
		  tagsDeli.add("uol");
		  tagsDeli.add("biograf");
		  tagsDeli.add("desenvolvimento");
		  tagsDeli.add("kenai");
		  tagsDeli.add("Aalborg");
		  tagsDeli.add("Community");
		  tagsDeli.add("lectures");
		  tagsDeli.add("collegamenti");
		  tagsDeli.add("more");
		  tagsDeli.add("Architecture");
		  tagsDeli.add("interesting");
		  tagsDeli.add("illusion");
		  tagsDeli.add("orchestration");
		  tagsDeli.add("patterns");
		  tagsDeli.add("ple");
		  tagsDeli.add("buttons");
		  tagsDeli.add("serious_games");
		  tagsDeli.add("Metodologia");
		  tagsDeli.add("tcc");
		  tagsDeli.add("dicas");
		  tagsDeli.add("monografia");
		  tagsDeli.add("anc");
		  tagsDeli.add("salzburgresearch");
		  tagsDeli.add("kis");
		  tagsDeli.add("forschung");
		  tagsDeli.add("salzburg");
		  tagsDeli.add("mowi");
		  tagsDeli.add("jsp");
		  tagsDeli.add("Information_Management");
		  tagsDeli.add("informationmanagement");
		  tagsDeli.add("application");
		  tagsDeli.add("widget");
		  tagsDeli.add("mashup");
		  tagsDeli.add("polls");
		  tagsDeli.add("vote");
		  tagsDeli.add("meeting");
		  tagsDeli.add("ambient");
		  tagsDeli.add("wedding");
		  tagsDeli.add("trust");
		  tagsDeli.add("cooperation");
		  tagsDeli.add("bank");
		  tagsDeli.add("microformat");
		  tagsDeli.add("extensions");
		  tagsDeli.add("fpga");
		  tagsDeli.add("diy");
		  tagsDeli.add("netgear");
		  tagsDeli.add("wireless");
		  tagsDeli.add("admin");
		  tagsDeli.add("router");
		  tagsDeli.add("hosting");
		  tagsDeli.add("webhosting");
		  tagsDeli.add("notpr0n");
		  tagsDeli.add("riddle");
		  tagsDeli.add("notpron");
		  tagsDeli.add("dbpedia");
		  tagsDeli.add("pessoas");
		  tagsDeli.add("evolution");
		  tagsDeli.add("groupware");
		  tagsDeli.add("symbiants");
		  tagsDeli.add("sprog");
		  tagsDeli.add("opslagsværker");
		  tagsDeli.add("dansk");
		  tagsDeli.add("ordbog");
		  tagsDeli.add("copyright");
		  tagsDeli.add("squeak");
		  tagsDeli.add("smalltalk");
		  tagsDeli.add("toolsforbusiness");
		  tagsDeli.add("Tools_for_Business");
		  tagsDeli.add("ranking");
		  tagsDeli.add("iprospect");
		  tagsDeli.add("share");
		  tagsDeli.add("nhs");
		  tagsDeli.add("healthinformatics");
		  tagsDeli.add("PodCast");
		  tagsDeli.add("ICT");
		  tagsDeli.add("spring");
		  tagsDeli.add("ioc");
		  tagsDeli.add("django");
		  tagsDeli.add("reflect");
		  tagsDeli.add("self-assessment");
		  tagsDeli.add("facetedsearch");
		  tagsDeli.add("songs");
		  tagsDeli.add("lyrics");
		  tagsDeli.add("many");
		  tagsDeli.add("mc2009");
		  tagsDeli.add("firm");
		  tagsDeli.add("strategy");
		  tagsDeli.add("frances");
		  tagsDeli.add("français");
		  tagsDeli.add("french");
		  tagsDeli.add("patents");
		  tagsDeli.add("artists");
		  tagsDeli.add("cold_Hawaii");
		  tagsDeli.add("webcam");
		  tagsDeli.add("beach");
		  tagsDeli.add("Klitmøller");
		  tagsDeli.add("windsurfing");
		  tagsDeli.add("rest");
		  tagsDeli.add("definitions");
		  tagsDeli.add("egovernment");
		  tagsDeli.add("government2.0");
		  tagsDeli.add("tourism");
		  tagsDeli.add("a-journal");
		  tagsDeli.add("bean-counting");
		  tagsDeli.add("b-journal");
		  tagsDeli.add("norway");
		  tagsDeli.add("PBL");
		  tagsDeli.add("preparation");
		  tagsDeli.add("document");
		  tagsDeli.add("23tingnordjylland");
		  tagsDeli.add("medier");
		  tagsDeli.add("danmark");
		  tagsDeli.add("nordjylland");
		  tagsDeli.add("Services");
		  tagsDeli.add("freemind");
		  tagsDeli.add("comentário");
		  tagsDeli.add("opusdei");
		  tagsDeli.add("folha");
		  tagsDeli.add("economics");
		  tagsDeli.add("models");
		  tagsDeli.add("monetizing");
		  tagsDeli.add("businessmodel");
		  tagsDeli.add("ontoclean");
		  tagsDeli.add("welty");
		  tagsDeli.add("leech");
		  tagsDeli.add("internal");
		  tagsDeli.add("IC");
		  tagsDeli.add("fuse");
		  tagsDeli.add("tagfs");
		  tagsDeli.add("tagsistant");
		  tagsDeli.add("filesystem");
		  tagsDeli.add("file");
		  tagsDeli.add("esb");
		  tagsDeli.add("aub");
		  tagsDeli.add("bibliotek");
		  tagsDeli.add("hypertext");
		  tagsDeli.add("torino");
		  tagsDeli.add("openid");
		  tagsDeli.add("identity");
		  tagsDeli.add("oauth");
		  tagsDeli.add("authentication");
		  tagsDeli.add("stock");
		  tagsDeli.add("banking");
		  tagsDeli.add("financial");
		  tagsDeli.add("benefits");
		  tagsDeli.add("options");
		  tagsDeli.add("barney");
		  tagsDeli.add("onlineshop");
		  tagsDeli.add("einkaufen");
		  tagsDeli.add("bücher");
		  tagsDeli.add("plugin");
		  tagsDeli.add("Excell");
		  tagsDeli.add("seo");
		  tagsDeli.add("acap");
		  tagsDeli.add("robots.txt");
		  tagsDeli.add("ACL");
		  tagsDeli.add("editors");
		  tagsDeli.add("anarchism");
		  tagsDeli.add("activism");
		  tagsDeli.add("anarchy");
		  tagsDeli.add("storm");
		  tagsDeli.add("orm");
		  tagsDeli.add("interactive");
		  tagsDeli.add("confluence");
		  tagsDeli.add("Logo");
		  tagsDeli.add("projetos");
		  tagsDeli.add("prática");
		  tagsDeli.add("ps3");
		  tagsDeli.add("cpu");
		  tagsDeli.add("cell");
		  tagsDeli.add("quakelive");
		  tagsDeli.add("live");
		  tagsDeli.add("multiplayer");
		  tagsDeli.add("Kids");
		  tagsDeli.add("coding");
		  tagsDeli.add("fiction");
		  tagsDeli.add("sf");
		  tagsDeli.add("ebook");
		  tagsDeli.add("gutenberg");
		  tagsDeli.add("recognition");
		  tagsDeli.add("ocr");
		  tagsDeli.add("scanning");
		  tagsDeli.add("post");
		  tagsDeli.add("ebanking");
		  tagsDeli.add("finanzen");
		  tagsDeli.add("yellownet");
		  tagsDeli.add("postfinance");
		  tagsDeli.add("posix");
		  tagsDeli.add("pthreads");
		  tagsDeli.add("unifiedprocess");
		  tagsDeli.add("wikier");
		  tagsDeli.add("viajar");
		  tagsDeli.add("experiência");
		  tagsDeli.add("viagens");
		  tagsDeli.add("Blog");
		  tagsDeli.add("cruise");
		  tagsDeli.add("personalpublishing");
		  tagsDeli.add("simplejson");
		  tagsDeli.add("doc");
		  tagsDeli.add("grice");
		  tagsDeli.add("Charges");
		  tagsDeli.add("diversão");
		  tagsDeli.add("animações");
		  tagsDeli.add("animação");
		  tagsDeli.add("Ontology");
		  tagsDeli.add("Engineering");
		  tagsDeli.add("Online");
		  tagsDeli.add("Development");
		  tagsDeli.add("dynamic");
		  tagsDeli.add("concurso");
		  tagsDeli.add("sweden");
		  tagsDeli.add("tidningar");
		  tagsDeli.add("nyheter");
		  tagsDeli.add("webex");
		  tagsDeli.add("asc");
		  tagsDeli.add("parc");
		  tagsDeli.add("edchi");
		  tagsDeli.add("Arhitecture");
		  tagsDeli.add("mde");
		  tagsDeli.add("dsl");
		  tagsDeli.add("mda");
		  tagsDeli.add("model");
		  tagsDeli.add("model-driven");
		  tagsDeli.add("rdfa");
		  tagsDeli.add("foaf");
		  tagsDeli.add("doap");
		  tagsDeli.add("manual");
		  tagsDeli.add("docs");
		  tagsDeli.add("dr");
		  tagsDeli.add("infovis");
		  tagsDeli.add("curriculum");
		  tagsDeli.add("curriculo");
		  tagsDeli.add("currículo");
		  tagsDeli.add("emotion");
		  tagsDeli.add("actor");
		  tagsDeli.add("forecast");
		  tagsDeli.add("gmail");
		  tagsDeli.add("gui");
		  tagsDeli.add("console");
		  tagsDeli.add("curses");
		  tagsDeli.add("lug");
		  tagsDeli.add("gnu");
		  tagsDeli.add("citeulike");
		  tagsDeli.add("livejournal");
		  tagsDeli.add("Ajax");
		  tagsDeli.add("prototype");
		  tagsDeli.add("jackson");
		  tagsDeli.add("evaluation");
		  tagsDeli.add("testes");
		  tagsDeli.add("sony");
		  tagsDeli.add("videogames");
		  tagsDeli.add("design_pattern");
		  tagsDeli.add("presutti");
		  tagsDeli.add("recorder");
		  tagsDeli.add("Cognition");
		  tagsDeli.add("Psychology");
		  tagsDeli.add("account");
		  tagsDeli.add("Bookmarks_Toolbar_Folder");
		  tagsDeli.add("jam");
		  tagsDeli.add("userinterfacedesign");
		  tagsDeli.add("user_interface_design");
		  tagsDeli.add("user-interface-design");
		  tagsDeli.add("Entertainment");
		  tagsDeli.add("curl");
		  tagsDeli.add("http");
		  tagsDeli.add("museums");
		  tagsDeli.add("recommender_systems");
		  tagsDeli.add("adaptive");
		  tagsDeli.add("personalisation");
		  tagsDeli.add("ah2008");
		  tagsDeli.add("hypermedia");
		  tagsDeli.add("mozilla_firefox");
		  tagsDeli.add("Mozilla");
		  tagsDeli.add("Firefox");
		  tagsDeli.add("MozillaFirefox");
		  tagsDeli.add("UbuntuandFreeSoftwarelinks");
		  tagsDeli.add("RDF");
		  tagsDeli.add("casa");
		  tagsDeli.add("intranet2.0");
		  tagsDeli.add("søgemaskiner");
		  tagsDeli.add("søgemaskine");
		  tagsDeli.add("Search");
		  tagsDeli.add("everywhere");
		  tagsDeli.add("meebo");
		  tagsDeli.add("instanting");
		  tagsDeli.add("message");
		  tagsDeli.add("Translator");
		  tagsDeli.add("ilps");
		  tagsDeli.add("weinberger");
		  tagsDeli.add("sharing");
		  tagsDeli.add("newideas");
		  tagsDeli.add("estatistica");
		  tagsDeli.add("mediana");
		  tagsDeli.add("catholic");
		  tagsDeli.add("church");
		  tagsDeli.add("CE2.0");
		  tagsDeli.add("ICWE");
		  tagsDeli.add("telefonkonferenz");
		  tagsDeli.add("voip");
		  tagsDeli.add("konferenz");
		  tagsDeli.add("lint");
		  tagsDeli.add("mellon");
		  tagsDeli.add("carnegie");
		  tagsDeli.add("urls'");
		  tagsDeli.add("developer-information");
		  tagsDeli.add("developer_information");
		  tagsDeli.add("chat");
		  tagsDeli.add("IM");
		  tagsDeli.add("asynchronous");
		  tagsDeli.add("twisted");
		  tagsDeli.add("eLearning");
		  tagsDeli.add("eGovernment");
		  tagsDeli.add("eTourism");
		  tagsDeli.add("nintendoDS");
		  tagsDeli.add("knoppix");
		  tagsDeli.add("livecd");
		  tagsDeli.add("webtools");
		  tagsDeli.add("source");
		  tagsDeli.add("common-sense-reasoning");
		  tagsDeli.add("computing");
		  tagsDeli.add("Commonsense");
		  tagsDeli.add("artificial-intelligence");
		  tagsDeli.add("author");
		  tagsDeli.add("bibtex");
		  tagsDeli.add("ir");
		  tagsDeli.add("syscall");
		  tagsDeli.add("vsyscall");
		  tagsDeli.add("sysenter");
		  tagsDeli.add("2.6");
		  tagsDeli.add("graphs");
		  tagsDeli.add("handheld");
		  tagsDeli.add("funding");
		  tagsDeli.add("aau");
		  tagsDeli.add("workplace");
		  tagsDeli.add("myspace");
		  tagsDeli.add("perl");
		  tagsDeli.add("freeticket");
		  tagsDeli.add("eventcalendar");
		  tagsDeli.add("assembly");
		  tagsDeli.add("x86");
		  tagsDeli.add("viralmarketing");
		  tagsDeli.add("guerillamarketing");
		  tagsDeli.add("deadlines");
		  tagsDeli.add("computational_linguistics");
		  tagsDeli.add("nlp");
		  tagsDeli.add("lastfm");
		  tagsDeli.add("smackbook");
		  tagsDeli.add("hack");
		  tagsDeli.add("calais");
		  tagsDeli.add("holes");
		  tagsDeli.add("Sports_&_Outdoors");
		  tagsDeli.add("sports&outdoors");
		  tagsDeli.add("precision");
		  tagsDeli.add("recall");
		  tagsDeli.add("iceberg");
		  tagsDeli.add("SME2.0");
		  tagsDeli.add("european");
		  tagsDeli.add("cheapflight");
		  tagsDeli.add("worldwide");
		  tagsDeli.add("Interviews");
		  tagsDeli.add("htaccess");
		  tagsDeli.add("problembasedlearning");
		  tagsDeli.add("scripts");
		  tagsDeli.add("scraping");
		  tagsDeli.add("fachhochschule");
		  tagsDeli.add("universityofappliedsciences");
		  tagsDeli.add("studium");
		  tagsDeli.add("fh");
		  tagsDeli.add("ohloh");
		  tagsDeli.add("mkberkman");
		  tagsDeli.add("example");
		  tagsDeli.add("orkut");
		  tagsDeli.add("uri");
		  tagsDeli.add("validator");
		  tagsDeli.add("quotations");
		  tagsDeli.add("nepomuk");
		  tagsDeli.add("crm");
		  tagsDeli.add("erp");
		  tagsDeli.add("jeff");
		  tagsDeli.add("calculator");
		  tagsDeli.add("poulin");
		  tagsDeli.add("commitment");
		  tagsDeli.add("bdi");
		  tagsDeli.add("nicholas-jennings");
		  tagsDeli.add("teams");
		  tagsDeli.add("agents");
		  tagsDeli.add("idea");
		  tagsDeli.add("phoenix");
		  tagsDeli.add("nba");
		  tagsDeli.add("suns");
		  tagsDeli.add("basketball");
		  tagsDeli.add("gonsalves");
		  tagsDeli.add("escher");
		  tagsDeli.add("illusions");
		  tagsDeli.add("personalization");
		  tagsDeli.add("dblp");
		  tagsDeli.add("wsdm");
		  tagsDeli.add("socialcapital");
		  tagsDeli.add("grid");
		  tagsDeli.add("parallel");
		  tagsDeli.add("hpc");
		  tagsDeli.add("fofoca");
		  tagsDeli.add("portais");
		  tagsDeli.add("terra");
		  tagsDeli.add("entretenimento");
		  tagsDeli.add("feeds");
		  tagsDeli.add("feed_apps");
		  tagsDeli.add("martin20_core");
		  tagsDeli.add("skywriting");
		  tagsDeli.add("redes");
		  tagsDeli.add("inteligenciacoletiva");
		  tagsDeli.add("rheingold");
		  tagsDeli.add("yellowpages");
		  tagsDeli.add("navne");
		  tagsDeli.add("names");
		  tagsDeli.add("services");
		  tagsDeli.add("key");
		  tagsDeli.add("warcraft");
		  tagsDeli.add("diablo");
		  tagsDeli.add("blizzard");
		  tagsDeli.add("starcraft");
		  tagsDeli.add("software_developers");
		  tagsDeli.add("russia");
		  tagsDeli.add("AIED");
		  tagsDeli.add("manga");
		  tagsDeli.add("watch");
		  tagsDeli.add("anime");
		  tagsDeli.add("idSpace");
		  tagsDeli.add("courses");
		  tagsDeli.add("underholdning");
		  tagsDeli.add("mostra");
		  tagsDeli.add("roms");
		  tagsDeli.add("original");
		  tagsDeli.add("Greg_Lake");
		  tagsDeli.add("Christmas");
		  tagsDeli.add("anti-war");
		  tagsDeli.add("uncensored");
		  tagsDeli.add("connectivism");
		  tagsDeli.add("encoder");
		  tagsDeli.add("word");
		  tagsDeli.add("coeedito");
		  tagsDeli.add("scholar");
		  tagsDeli.add("citations");
		  tagsDeli.add("nutrition");
		  tagsDeli.add("diet");
		  tagsDeli.add("vegetarian");
		  tagsDeli.add("medicalarticle");
		  tagsDeli.add("instructionvideo");
		  tagsDeli.add("liderança");
		  tagsDeli.add("wysiwyg");
		  tagsDeli.add("mediawiki");
		  tagsDeli.add("chords");
		  tagsDeli.add("tablature");
		  tagsDeli.add("walking");
		  tagsDeli.add("psychogeography");
		  tagsDeli.add("astronomy");
		  tagsDeli.add("netpromoter");
		  tagsDeli.add("space");
		  tagsDeli.add("galaxy");
		  tagsDeli.add("lego");
		return tagsDeli;
		
		
	}
	
	
	public static Map<String, Set<String>>  loadDeliciousOntologyTagGround () {
		
		Map<String, Set<String>> mapTagGround = new HashMap<String, Set<String>>();
		
		Set<String> grounds_0 = new HashSet<String>();
		grounds_0.add("MultiMediaPrefs");
		mapTagGround.put("media_contextvocabulary.owl",grounds_0);

		Set<String> grounds_1 = new HashSet<String>();
		grounds_1.add("GraduateStudent");
		grounds_1.add("MaleStudentWith3Daughters");
		mapTagGround.put("student_koala.owl",grounds_1);

		Set<String> grounds_2 = new HashSet<String>();
		grounds_2.add("File_Management");
		mapTagGround.put("management_infra.owl",grounds_2);

		Set<String> grounds_3 = new HashSet<String>();
		grounds_3.add("LightSensitiveMedia");
		grounds_3.add("hasSensitiveMedia");
		mapTagGround.put("media_photography.owl",grounds_3);

		Set<String> grounds_4 = new HashSet<String>();
		grounds_4.add("hasKnowledge");
		mapTagGround.put("knowledge_contextvocabulary.owl",grounds_4);

		Set<String> grounds_5 = new HashSet<String>();
		grounds_5.add("StudySite");
		grounds_5.add("SecondaryStudy");
		grounds_5.add("StudyCommittee");
		grounds_5.add("SecondaryStudyProtocol");
		mapTagGround.put("study_cto.owl",grounds_5);

		Set<String> grounds_6 = new HashSet<String>();
		grounds_6.add("Board_Games");
		mapTagGround.put("games_infra.owl",grounds_6);

		Set<String> grounds_7 = new HashSet<String>();
		grounds_7.add("JavaLangReflect");
		mapTagGround.put("reflect_java.owl",grounds_7);

		Set<String> grounds_8 = new HashSet<String>();
		grounds_8.add("filterType");
		grounds_8.add("typeObject");
		mapTagGround.put("type_svecore.owl",grounds_8);

		Set<String> grounds_9 = new HashSet<String>();
		grounds_9.add("programCommittee");
		mapTagGround.put("program_ka.owl",grounds_9);

		Set<String> grounds_10 = new HashSet<String>();
		grounds_10.add("ReferenceSystem");
		grounds_10.add("AL_relatedToAbsoluteLocationReferenceSystem");
		grounds_10.add("RL_relatedToRelativeLocationReferenceSystem");
		mapTagGround.put("reference_contextvocabulary.owl",grounds_10);

		Set<String> grounds_11 = new HashSet<String>();
		grounds_11.add("ConversionFilter");
		mapTagGround.put("conversion_photography.owl",grounds_11);

		Set<String> grounds_12 = new HashSet<String>();
		grounds_12.add("KnowledgeManagement");
		mapTagGround.put("management_ka.owl",grounds_12);

		Set<String> grounds_13 = new HashSet<String>();
		grounds_13.add("versionInfo");
		mapTagGround.put("info_countries.owl",grounds_13);

		Set<String> grounds_14 = new HashSet<String>();
		grounds_14.add("versionInfo");
		mapTagGround.put("info_sport.owl",grounds_14);

		Set<String> grounds_15 = new HashSet<String>();
		grounds_15.add("IRFilter");
		mapTagGround.put("ir_photography.owl",grounds_15);

		Set<String> grounds_16 = new HashSet<String>();
		grounds_16.add("IndependentState");
		mapTagGround.put("independent_countries.owl",grounds_16);

		Set<String> grounds_17 = new HashSet<String>();
		grounds_17.add("A_relatedToUserActivity");
		grounds_17.add("U_relatedToUserCompany");
		grounds_17.add("C_relatedToUserCommunity");
		grounds_17.add("U_relatedToUserSchedule");
		grounds_17.add("U_relatedToUserService");
		grounds_17.add("S_relatedToUserSchedule");
		grounds_17.add("S_relatedToUserSpace");
		grounds_17.add("U_relatedToUserEvent");
		grounds_17.add("userID");
		grounds_17.add("U_relatedToUserApplication");
		grounds_17.add("U_relatedToUserActivity");
		grounds_17.add("U_relatedToUserSpace");
		grounds_17.add("U_relatedToUserCommunity");
		grounds_17.add("U_relatedToUserDevice");
		mapTagGround.put("user_contextvocabulary.owl",grounds_17);

		Set<String> grounds_18 = new HashSet<String>();
		grounds_18.add("researchGroups");
		grounds_18.add("ResearchProject");
		grounds_18.add("ResearchTopic");
		grounds_18.add("ResearchGroup");
		grounds_18.add("researchInterest");
		mapTagGround.put("research_ka.owl",grounds_18);

		Set<String> grounds_19 = new HashSet<String>();
		grounds_19.add("dataSource");
		grounds_19.add("structureData");
		grounds_19.add("patoData");
		mapTagGround.put("data_biopax-level3.owl",grounds_19);

		Set<String> grounds_20 = new HashSet<String>();
		grounds_20.add("SimulationHardware");
		mapTagGround.put("hardware_esg1.1.owl",grounds_20);

		Set<String> grounds_21 = new HashSet<String>();
		grounds_21.add("ComputerSupport");
		mapTagGround.put("computer_ka.owl",grounds_21);

		Set<String> grounds_22 = new HashSet<String>();
		grounds_22.add("SoftwareReuse");
		grounds_22.add("SoftwareApplications");
		grounds_22.add("SoftwareProject");
		mapTagGround.put("software_ka.owl",grounds_22);

		Set<String> grounds_23 = new HashSet<String>();
		grounds_23.add("DynamicCapBlock");
		grounds_23.add("DynamicReqBlock");
		grounds_23.add("DynamicBlock");
		mapTagGround.put("block_midas_profile.owl",grounds_23);

		Set<String> grounds_24 = new HashSet<String>();
		grounds_24.add("MoviesGenrePrefs");
		grounds_24.add("MoviesPrefs");
		mapTagGround.put("movies_contextvocabulary.owl",grounds_24);

		Set<String> grounds_25 = new HashSet<String>();
		grounds_25.add("MovableObject");
		grounds_25.add("HomogeneousObjectContainer");
		grounds_25.add("objectBaseName");
		grounds_25.add("objectFree");
		grounds_25.add("ObjectContainer");
		grounds_25.add("HeterogeneousObjectContainer");
		grounds_25.add("typeObject");
		grounds_25.add("BaseObject");
		mapTagGround.put("object_svecore.owl",grounds_25);

		Set<String> grounds_26 = new HashSet<String>();
		grounds_26.add("GoodBoca");
		mapTagGround.put("good_photography.owl",grounds_26);

		Set<String> grounds_27 = new HashSet<String>();
		grounds_27.add("LightSensitiveMedia");
		grounds_27.add("hasSensitiveMedia");
		mapTagGround.put("media_photography.owl",grounds_27);

		Set<String> grounds_28 = new HashSet<String>();
		grounds_28.add("SpaceTimeRegion");
		mapTagGround.put("space_cto.owl",grounds_28);

		Set<String> grounds_29 = new HashSet<String>();
		grounds_29.add("C_relatedToUserCommunity");
		grounds_29.add("U_relatedToUserCommunity");
		mapTagGround.put("community_contextvocabulary.owl",grounds_29);

		Set<String> grounds_30 = new HashSet<String>();
		grounds_30.add("masterOf");
		mapTagGround.put("master_data.owl",grounds_30);

		Set<String> grounds_31 = new HashSet<String>();
		grounds_31.add("IceWine");
		grounds_31.add("WineSugar");
		grounds_31.add("WineFlavor");
		grounds_31.add("SelaksIceWine");
		grounds_31.add("FrenchWine");
		grounds_31.add("AmericanWine");
		grounds_31.add("CaliforniaWine");
		grounds_31.add("SweetWine");
		grounds_31.add("DryRedWine");
		grounds_31.add("RedTableWine");
		grounds_31.add("DryWine");
		grounds_31.add("RedWine");
		grounds_31.add("DryWhiteWine");
		grounds_31.add("FullBodiedWine");
		grounds_31.add("GermanWine");
		grounds_31.add("AlsatianWine");
		grounds_31.add("WineDescriptor");
		grounds_31.add("WineBody");
		grounds_31.add("madeIntoWine");
		grounds_31.add("WineColor");
		grounds_31.add("DessertWine");
		grounds_31.add("WineGrape");
		grounds_31.add("TexasWine");
		grounds_31.add("WineTaste");
		grounds_31.add("WhiteNonSweetWine");
		grounds_31.add("WhiteWine");
		grounds_31.add("WhiteTableWine");
		grounds_31.add("TableWine");
		grounds_31.add("RoseWine");
		grounds_31.add("producesWine");
		grounds_31.add("ItalianWine");
		grounds_31.add("hasWineDescriptor");
		mapTagGround.put("wine_wine.owl",grounds_31);

		Set<String> grounds_32 = new HashSet<String>();
		grounds_32.add("KAMethodology");
		grounds_32.add("SpecificationMethodology");
		mapTagGround.put("methodology_ka.owl",grounds_32);

		Set<String> grounds_33 = new HashSet<String>();
		grounds_33.add("SpaceCoverage");
		grounds_33.add("hasSpaceCoverage");
		mapTagGround.put("space_esg1.1.owl",grounds_33);

		Set<String> grounds_34 = new HashSet<String>();
		grounds_34.add("LearningApprenticeSystems");
		grounds_34.add("ReinforcementLearning");
		grounds_34.add("KAThroughMachineLearning");
		mapTagGround.put("learning_ka.owl",grounds_34);

		Set<String> grounds_35 = new HashSet<String>();
		grounds_35.add("hasUncle");
		mapTagGround.put("uncle_terrorism.owl",grounds_35);

		Set<String> grounds_36 = new HashSet<String>();
		grounds_36.add("SimulationStatus");
		grounds_36.add("hasStatus");
		mapTagGround.put("status_esg1.1.owl",grounds_36);

		Set<String> grounds_37 = new HashSet<String>();
		grounds_37.add("GermanyRegion");
		mapTagGround.put("germany_wine.owl",grounds_37);

		Set<String> grounds_38 = new HashSet<String>();
		grounds_38.add("versionInfo");
		mapTagGround.put("info_terrorism.owl",grounds_38);

		Set<String> grounds_39 = new HashSet<String>();
		grounds_39.add("C_relatedToUserCommunity");
		grounds_39.add("U_relatedToUserCommunity");
		mapTagGround.put("community_contextvocabulary.owl",grounds_39);

		Set<String> grounds_40 = new HashSet<String>();
		grounds_40.add("SpecificationLanguages");
		grounds_40.add("ExecutableSpecificationLanguages");
		mapTagGround.put("languages_ka.owl",grounds_40);

		Set<String> grounds_41 = new HashSet<String>();
		grounds_41.add("VirtualTerritory");
		mapTagGround.put("virtual_countries.owl",grounds_41);

		Set<String> grounds_42 = new HashSet<String>();
		grounds_42.add("positionStatus");
		mapTagGround.put("status_biopax-level3.owl",grounds_42);

		Set<String> grounds_43 = new HashSet<String>();
		grounds_43.add("MuitUser");
		mapTagGround.put("user_infra.owl",grounds_43);

		Set<String> grounds_44 = new HashSet<String>();
		grounds_44.add("versionInfo");
		mapTagGround.put("info_koala.owl",grounds_44);

		Set<String> grounds_45 = new HashSet<String>();
		grounds_45.add("SportsNewsPrefs");
		mapTagGround.put("sports_contextvocabulary.owl",grounds_45);

		Set<String> grounds_46 = new HashSet<String>();
		grounds_46.add("mappingAuthorityName");
		grounds_46.add("mappingAuthority");
		mapTagGround.put("authority_esg1.1.owl",grounds_46);

		Set<String> grounds_47 = new HashSet<String>();
		grounds_47.add("ModelingLanguage");
		grounds_47.add("SpecificationLanguage");
		mapTagGround.put("language_ka.owl",grounds_47);

		Set<String> grounds_48 = new HashSet<String>();
		grounds_48.add("SimulationInput");
		grounds_48.add("SimulationStatus");
		grounds_48.add("SimulationCode");
		grounds_48.add("SimulationHardware");
		mapTagGround.put("simulation_esg1.1.owl",grounds_48);

		Set<String> grounds_49 = new HashSet<String>();
		grounds_49.add("ColourBalance");
		mapTagGround.put("balance_photography.owl",grounds_49);

		Set<String> grounds_50 = new HashSet<String>();
		grounds_50.add("InternetTool");
		mapTagGround.put("internet_ka.owl",grounds_50);

		Set<String> grounds_51 = new HashSet<String>();
		grounds_51.add("domainInterGroupSocialRelation");
		grounds_51.add("AgentSocialRelation");
		grounds_51.add("SocialRelation");
		grounds_51.add("InterGroupSocialRelation");
		grounds_51.add("targetAgentSocialRelation");
		grounds_51.add("targetInterGroupSocialRelation");
		grounds_51.add("domainAgentSocialRelation");
		grounds_51.add("targetGroupSocialRelation");
		grounds_51.add("socialProperty");
		grounds_51.add("GroupSocialRelation");
		grounds_51.add("domainGroupSocialRelation");
		mapTagGround.put("social_svecore.owl",grounds_51);

		Set<String> grounds_52 = new HashSet<String>();
		grounds_52.add("ReferenceType");
		grounds_52.add("hasCalendarType");
		grounds_52.add("NameResolutionxyzType");
		mapTagGround.put("type_esg1.1.owl",grounds_52);

		Set<String> grounds_53 = new HashSet<String>();
		grounds_53.add("InternetTool");
		mapTagGround.put("internet_ka.owl",grounds_53);

		Set<String> grounds_54 = new HashSet<String>();
		grounds_54.add("baseFree");
		grounds_54.add("objectFree");
		mapTagGround.put("free_svecore.owl",grounds_54);

		Set<String> grounds_55 = new HashSet<String>();
		grounds_55.add("JDK1.1JavaUtil");
		grounds_55.add("JDK1.1JavaIO");
		grounds_55.add("JavaRenderingEngine");
		grounds_55.add("JavaAWT");
		grounds_55.add("JavaText");
		grounds_55.add("JDKJavaLang");
		grounds_55.add("JDKJavaUtil");
		grounds_55.add("CLDCJavaUtil");
		grounds_55.add("JavaAWTEvent");
		grounds_55.add("JavaLibrary");
		grounds_55.add("JavaLangRef");
		grounds_55.add("JavaLangReflect");
		grounds_55.add("hasPersonalJavaVersion");
		grounds_55.add("JavaJar");
		grounds_55.add("CLDCJavaLang");
		grounds_55.add("JavaIO");
		grounds_55.add("JavaUtilZip");
		grounds_55.add("JavaRMI");
		grounds_55.add("JavaVM");
		grounds_55.add("StreamJavaIO");
		grounds_55.add("JavaMath");
		grounds_55.add("JavaSQL");
		grounds_55.add("JavaNet");
		grounds_55.add("JavaBeans");
		grounds_55.add("PersonalJava");
		mapTagGround.put("java_java.owl",grounds_55);

		Set<String> grounds_56 = new HashSet<String>();
		grounds_56.add("hasOntologyMapping");
		grounds_56.add("mappingAuthorityName");
		grounds_56.add("mappingAuthority");
		grounds_56.add("hasMapping");
		mapTagGround.put("mapping_esg1.1.owl",grounds_56);

		Set<String> grounds_57 = new HashSet<String>();
		grounds_57.add("PhDStudent");
		mapTagGround.put("student_ka.owl",grounds_57);

		Set<String> grounds_58 = new HashSet<String>();
		grounds_58.add("ITProfessional");
		grounds_58.add("EIProfessional");
		mapTagGround.put("professional_data.owl",grounds_58);

		Set<String> grounds_59 = new HashSet<String>();
		grounds_59.add("hasCDCVersion");
		grounds_59.add("CLDC");
		grounds_59.add("JavaxMicroeditionLCDUI");
		grounds_59.add("JavaxMicroeditionLCDUIGame");
		grounds_59.add("CLDCJavaUtil");
		grounds_59.add("CDC");
		grounds_59.add("hasCLDCVersion");
		grounds_59.add("J2MECHAPI");
		grounds_59.add("CLDCJavaLang");
		mapTagGround.put("c++_java.owl",grounds_59);

		Set<String> grounds_60 = new HashSet<String>();
		grounds_60.add("BlackAndWhiteFilm");
		grounds_60.add("MediumFormatFilm");
		grounds_60.add("PrintFilm");
		grounds_60.add("LithFilm");
		grounds_60.add("ColourFilm");
		grounds_60.add("SlideFilm");
		grounds_60.add("x5inFilm");
		grounds_60.add("RollFilm");
		grounds_60.add("SmallFormatFilm");
		grounds_60.add("LargeFormatFilm");
		grounds_60.add("InfraRedFilm");
		mapTagGround.put("film_photography.owl",grounds_60);

		Set<String> grounds_61 = new HashSet<String>();
		grounds_61.add("ServiceContainer");
		mapTagGround.put("service_svecore.owl",grounds_61);

		Set<String> grounds_62 = new HashSet<String>();
		grounds_62.add("Evaluation-Testing");
		mapTagGround.put("testing_resist.owl",grounds_62);

		Set<String> grounds_63 = new HashSet<String>();
		grounds_63.add("travelTo");
		grounds_63.add("travelFrom");
		grounds_63.add("travelVehicle");
		grounds_63.add("travelDate");
		grounds_63.add("travelAction");
		grounds_63.add("TravelEvent");
		mapTagGround.put("travel_terrorism.owl",grounds_63);

		Set<String> grounds_64 = new HashSet<String>();
		grounds_64.add("versionInfo");
		mapTagGround.put("info_esg1.1.owl",grounds_64);

		Set<String> grounds_65 = new HashSet<String>();
		grounds_65.add("ProgrammingLanguage");
		mapTagGround.put("programming_data.owl",grounds_65);

		Set<String> grounds_66 = new HashSet<String>();
		grounds_66.add("eyeColor");
		mapTagGround.put("color_contextvocabulary.owl",grounds_66);

		Set<String> grounds_67 = new HashSet<String>();
		grounds_67.add("ReferenceType");
		grounds_67.add("hasReference");
		mapTagGround.put("reference_esg1.1.owl",grounds_67);

		Set<String> grounds_68 = new HashSet<String>();
		grounds_68.add("postalCode");
		grounds_68.add("SimulationCode");
		mapTagGround.put("code_esg1.1.owl",grounds_68);

		Set<String> grounds_69 = new HashSet<String>();
		grounds_69.add("TamoxifenMore5YrCourse");
		grounds_69.add("Tamoxifen5YrCourse");
		grounds_69.add("Tamoxifen2YrCourse");
		mapTagGround.put("course_cancer.owl",grounds_69);

		Set<String> grounds_70 = new HashSet<String>();
		grounds_70.add("JavaText");
		mapTagGround.put("text_java.owl",grounds_70);

		Set<String> grounds_71 = new HashSet<String>();
		grounds_71.add("LaptopComputer");
		mapTagGround.put("laptop_office.rdf",grounds_71);

		Set<String> grounds_72 = new HashSet<String>();
		grounds_72.add("SpaceTimeRegion");
		mapTagGround.put("time_cto.owl",grounds_72);

		Set<String> grounds_73 = new HashSet<String>();
		grounds_73.add("Inadequate-Authentication");
		mapTagGround.put("authentication_resist.owl",grounds_73);

		Set<String> grounds_74 = new HashSet<String>();
		grounds_74.add("GamesGenrePrefs");
		grounds_74.add("GamesPrefs");
		mapTagGround.put("games_contextvocabulary.owl",grounds_74);

		Set<String> grounds_75 = new HashSet<String>();
		grounds_75.add("mapOfLocation");
		mapTagGround.put("map_terrorism.owl",grounds_75);

		Set<String> grounds_76 = new HashSet<String>();
		grounds_76.add("MathematicalFoundation");
		mapTagGround.put("foundation_data.owl",grounds_76);

		Set<String> grounds_77 = new HashSet<String>();
		grounds_77.add("versionInfo");
		mapTagGround.put("info_java.owl",grounds_77);

		Set<String> grounds_78 = new HashSet<String>();
		grounds_78.add("ComputerGraphics");
		grounds_78.add("ComputerScience");
		mapTagGround.put("computer_data.owl",grounds_78);

		Set<String> grounds_79 = new HashSet<String>();
		grounds_79.add("SimulationHardware");
		mapTagGround.put("hardware_esg1.1.owl",grounds_79);

		Set<String> grounds_80 = new HashSet<String>();
		grounds_80.add("roleInEvent");
		grounds_80.add("TravelEvent");
		grounds_80.add("vehicleEvent");
		mapTagGround.put("event_terrorism.owl",grounds_80);

		Set<String> grounds_81 = new HashSet<String>();
		grounds_81.add("RemoteRelease");
		mapTagGround.put("remote_photography.owl",grounds_81);

		Set<String> grounds_82 = new HashSet<String>();
		grounds_82.add("PoliticsNewsPrefs");
		mapTagGround.put("politics_contextvocabulary.owl",grounds_82);

		Set<String> grounds_83 = new HashSet<String>();
		grounds_83.add("nameEnglishLong");
		grounds_83.add("nameEnglish");
		mapTagGround.put("english_countries.owl",grounds_83);

		Set<String> grounds_84 = new HashSet<String>();
		grounds_84.add("countryCodeISO3166Alpha2");
		grounds_84.add("countryCodeISO3166Alpha3");
		grounds_84.add("countryCodeUNNumeric3");
		mapTagGround.put("code_countries.owl",grounds_84);

		Set<String> grounds_85 = new HashSet<String>();
		grounds_85.add("Software_Development");
		mapTagGround.put("development_infra.owl",grounds_85);

		Set<String> grounds_86 = new HashSet<String>();
		grounds_86.add("hasText");
		mapTagGround.put("text_esg1.1.owl",grounds_86);

		Set<String> grounds_87 = new HashSet<String>();
		grounds_87.add("KnowledgeElicitation");
		grounds_87.add("CooperativeKnowledgeAcquisition");
		grounds_87.add("KnowledgeManagement");
		grounds_87.add("KnowledgeDiscoveryInDatasets");
		grounds_87.add("AnomalyRepairAndKnowledgeRevision");
		grounds_87.add("SpecificationOfControlKnowledge");
		grounds_87.add("KnowledgeBasedRefinement");
		mapTagGround.put("knowledge_ka.owl",grounds_87);

		Set<String> grounds_88 = new HashSet<String>();
		grounds_88.add("groupType");
		grounds_88.add("cellType");
		grounds_88.add("relationshipType");
		grounds_88.add("modificationType");
		grounds_88.add("controlType");
		grounds_88.add("interactionType");
		grounds_88.add("regionType");
		mapTagGround.put("type_biopax-level3.owl",grounds_88);

		Set<String> grounds_89 = new HashSet<String>();
		grounds_89.add("researchGroups");
		grounds_89.add("ResearchProject");
		grounds_89.add("ResearchTopic");
		grounds_89.add("ResearchGroup");
		grounds_89.add("researchInterest");
		mapTagGround.put("research_ka.owl",grounds_89);

		Set<String> grounds_90 = new HashSet<String>();
		grounds_90.add("ArticleInBook");
		mapTagGround.put("book_ka.owl",grounds_90);

		Set<String> grounds_91 = new HashSet<String>();
		grounds_91.add("authorOfOntology");
		grounds_91.add("OntologyLibrary");
		mapTagGround.put("ontology_ka.owl",grounds_91);

		Set<String> grounds_92 = new HashSet<String>();
		grounds_92.add("baseFree");
		grounds_92.add("objectFree");
		mapTagGround.put("free_svecore.owl",grounds_92);

		Set<String> grounds_93 = new HashSet<String>();
		grounds_93.add("_Security");
		mapTagGround.put("security_infra.owl",grounds_93);

		Set<String> grounds_94 = new HashSet<String>();
		grounds_94.add("DomainConcept");
		mapTagGround.put("concept_infra.owl",grounds_94);

		Set<String> grounds_95 = new HashSet<String>();
		grounds_95.add("Personal_Status");
		mapTagGround.put("status_terrorism.owl",grounds_95);

		Set<String> grounds_96 = new HashSet<String>();
		grounds_96.add("U_relatedToUserEvent");
		mapTagGround.put("event_contextvocabulary.owl",grounds_96);

		Set<String> grounds_97 = new HashSet<String>();
		grounds_97.add("PhDStudent");
		mapTagGround.put("phd_ka.owl",grounds_97);

		Set<String> grounds_98 = new HashSet<String>();
		grounds_98.add("versionInfo");
		mapTagGround.put("info_midas_profile.owl",grounds_98);

		Set<String> grounds_99 = new HashSet<String>();
		grounds_99.add("DataMining");
		mapTagGround.put("mining_ka.owl",grounds_99);

		Set<String> grounds_100 = new HashSet<String>();
		grounds_100.add("DevelopmentProject");
		mapTagGround.put("development_ka.owl",grounds_100);

		Set<String> grounds_101 = new HashSet<String>();
		grounds_101.add("SoftwareReuse");
		mapTagGround.put("reuse_ka.owl",grounds_101);

		Set<String> grounds_102 = new HashSet<String>();
		grounds_102.add("U_relatedToUserApplication");
		mapTagGround.put("application_contextvocabulary.owl",grounds_102);

		Set<String> grounds_103 = new HashSet<String>();
		grounds_103.add("productFAQ");
		grounds_103.add("productMailingList");
		grounds_103.add("productName");
		grounds_103.add("productPublication");
		mapTagGround.put("product_ka.owl",grounds_103);

		Set<String> grounds_104 = new HashSet<String>();
		grounds_104.add("hasZipCode");
		mapTagGround.put("code_travel.owl",grounds_104);

		Set<String> grounds_105 = new HashSet<String>();
		grounds_105.add("PaperLibrary");
		grounds_105.add("PSMLibrary");
		grounds_105.add("OntologyLibrary");
		mapTagGround.put("library_ka.owl",grounds_105);

		Set<String> grounds_106 = new HashSet<String>();
		grounds_106.add("BudgetHotelDestination");
		grounds_106.add("LuxuryHotel");
		mapTagGround.put("hotel_travel.owl",grounds_106);

		Set<String> grounds_107 = new HashSet<String>();
		grounds_107.add("Threat-To-Dependable-Systems");
		grounds_107.add("Means-To-Attain-Dependable-Systems");
		grounds_107.add("Attribute-Of-Dependable-Systems");
		mapTagGround.put("systems_resist.owl",grounds_107);

		Set<String> grounds_108 = new HashSet<String>();
		grounds_108.add("SoftwareReuse");
		grounds_108.add("SoftwareApplications");
		grounds_108.add("SoftwareProject");
		mapTagGround.put("software_ka.owl",grounds_108);

		Set<String> grounds_109 = new HashSet<String>();
		grounds_109.add("SecondaryStudyProtocol");
		grounds_109.add("ProtocolChange");
		mapTagGround.put("protocol_cto.owl",grounds_109);

		Set<String> grounds_110 = new HashSet<String>();
		grounds_110.add("ImplementationEnvironment");
		mapTagGround.put("implementation_ka.owl",grounds_110);

		Set<String> grounds_111 = new HashSet<String>();
		grounds_111.add("interactionScore");
		grounds_111.add("MolecularInteraction");
		grounds_111.add("InteractionVocabulary");
		grounds_111.add("interactionType");
		grounds_111.add("GeneticInteraction");
		mapTagGround.put("interaction_biopax-level3.owl",grounds_111);

		Set<String> grounds_112 = new HashSet<String>();
		grounds_112.add("_3D");
		mapTagGround.put("3d_infra.owl",grounds_112);

		Set<String> grounds_113 = new HashSet<String>();
		grounds_113.add("CommercialEnterprise");
		mapTagGround.put("enterprise_terrorism.owl",grounds_113);

		Set<String> grounds_114 = new HashSet<String>();
		grounds_114.add("versionInfo");
		mapTagGround.put("info_resist.owl",grounds_114);

		Set<String> grounds_115 = new HashSet<String>();
		grounds_115.add("DynamicCapBlock");
		grounds_115.add("DynamicReqBlock");
		grounds_115.add("DynamicBlock");
		mapTagGround.put("dynamic_midas_profile.owl",grounds_115);

		Set<String> grounds_116 = new HashSet<String>();
		grounds_116.add("ActorPrefs");
		mapTagGround.put("actor_contextvocabulary.owl",grounds_116);

		Set<String> grounds_117 = new HashSet<String>();
		grounds_117.add("ShoppingAdsPrefs");
		mapTagGround.put("ads_contextvocabulary.owl",grounds_117);

		Set<String> grounds_118 = new HashSet<String>();
		grounds_118.add("hasColourTemperature");
		mapTagGround.put("temperature_photography.owl",grounds_118);

		Set<String> grounds_119 = new HashSet<String>();
		grounds_119.add("memberOfPC");
		mapTagGround.put("c++_ka.owl",grounds_119);

		Set<String> grounds_120 = new HashSet<String>();
		grounds_120.add("dataSource");
		grounds_120.add("scoreSource");
		grounds_120.add("BioSource");
		mapTagGround.put("source_biopax-level3.owl",grounds_120);

		Set<String> grounds_121 = new HashSet<String>();
		grounds_121.add("Genetic_Algorithms");
		mapTagGround.put("algorithms_infra.owl",grounds_121);

		Set<String> grounds_122 = new HashSet<String>();
		grounds_122.add("Target_Type");
		grounds_122.add("Specific_Unit_Type");
		grounds_122.add("Plot_Type");
		grounds_122.add("Weapon_Type");
		grounds_122.add("organizationType");
		mapTagGround.put("type_terrorism.owl",grounds_122);

		Set<String> grounds_123 = new HashSet<String>();
		grounds_123.add("cellType");
		grounds_123.add("CellVocabulary");
		mapTagGround.put("cell_biopax-level3.owl",grounds_123);

		Set<String> grounds_124 = new HashSet<String>();
		grounds_124.add("DevelopmentProject");
		mapTagGround.put("development_ka.owl",grounds_124);

		Set<String> grounds_125 = new HashSet<String>();
		grounds_125.add("controlType");
		mapTagGround.put("control_biopax-level3.owl",grounds_125);

		Set<String> grounds_126 = new HashSet<String>();
		grounds_126.add("JavaxMicroeditionLCDUIGame");
		mapTagGround.put("game_java.owl",grounds_126);

		Set<String> grounds_127 = new HashSet<String>();
		grounds_127.add("ControlledVocabulary");
		grounds_127.add("PhenotypeVocabulary");
		grounds_127.add("SequenceModificationVocabulary");
		grounds_127.add("EntityReferenceGroupVocabulary");
		grounds_127.add("SequenceRegionVocabulary");
		grounds_127.add("CellularLocationVocabulary");
		grounds_127.add("ExperimentalFormVocabulary");
		grounds_127.add("EvidenceCodeVocabulary");
		grounds_127.add("InteractionVocabulary");
		grounds_127.add("CellVocabulary");
		grounds_127.add("TissueVocabulary");
		mapTagGround.put("vocabulary_biopax-level3.owl",grounds_127);

		Set<String> grounds_128 = new HashSet<String>();
		grounds_128.add("CICluster");
		mapTagGround.put("c++_data.owl",grounds_128);

		Set<String> grounds_129 = new HashSet<String>();
		grounds_129.add("TherapeuticType");
		grounds_129.add("ECMFRegimeType");
		grounds_129.add("hasPathologicalType");
		grounds_129.add("ECMFTypeInstance");
		mapTagGround.put("type_cancer.owl",grounds_129);

		Set<String> grounds_130 = new HashSet<String>();
		grounds_130.add("researchGroups");
		mapTagGround.put("groups_ka.owl",grounds_130);

		Set<String> grounds_131 = new HashSet<String>();
		grounds_131.add("ESGDataset");
		mapTagGround.put("dataset_esg1.1.owl",grounds_131);

		Set<String> grounds_132 = new HashSet<String>();
		grounds_132.add("ImplementationEnvironment");
		mapTagGround.put("environment_ka.owl",grounds_132);

		Set<String> grounds_133 = new HashSet<String>();
		grounds_133.add("JournalArticle");
		mapTagGround.put("journal_ka.owl",grounds_133);

		Set<String> grounds_134 = new HashSet<String>();
		grounds_134.add("applicationAreas");
		mapTagGround.put("application_ka.owl",grounds_134);

		Set<String> grounds_135 = new HashSet<String>();
		grounds_135.add("DevelopmentProject");
		grounds_135.add("ResearchProject");
		grounds_135.add("projectInfo");
		grounds_135.add("worksAtProject");
		grounds_135.add("describesProject");
		grounds_135.add("SoftwareProject");
		mapTagGround.put("project_ka.owl",grounds_135);

		Set<String> grounds_136 = new HashSet<String>();
		grounds_136.add("RegisteringAuthority");
		mapTagGround.put("authority_terrorism.owl",grounds_136);

		Set<String> grounds_137 = new HashSet<String>();
		grounds_137.add("ComputerSupport");
		grounds_137.add("SupportToolsForFormalMethods");
		mapTagGround.put("support_ka.owl",grounds_137);

		Set<String> grounds_138 = new HashSet<String>();
		grounds_138.add("Terrorist_Group");
		grounds_138.add("Baker_Street_Prayer_Group");
		mapTagGround.put("group_terrorism.owl",grounds_138);

		Set<String> grounds_139 = new HashSet<String>();
		grounds_139.add("VideoQualityPrefs");
		mapTagGround.put("quality_contextvocabulary.owl",grounds_139);

		Set<String> grounds_140 = new HashSet<String>();
		grounds_140.add("hasOntologyMapping");
		mapTagGround.put("ontology_esg1.1.owl",grounds_140);

		Set<String> grounds_141 = new HashSet<String>();
		grounds_141.add("MenopausalStatus");
		grounds_141.add("LNStatus");
		grounds_141.add("hasMenopausalStatus");
		grounds_141.add("HRStatus");
		grounds_141.add("hasHRStatus");
		grounds_141.add("hasLNStatus");
		mapTagGround.put("status_cancer.owl",grounds_141);

		Set<String> grounds_142 = new HashSet<String>();
		grounds_142.add("groupType");
		grounds_142.add("EntityReferenceGroupVocabulary");
		mapTagGround.put("group_biopax-level3.owl",grounds_142);

		Set<String> grounds_143 = new HashSet<String>();
		grounds_143.add("LearningApprenticeSystems");
		mapTagGround.put("systems_ka.owl",grounds_143);

		Set<String> grounds_144 = new HashSet<String>();
		grounds_144.add("nameEnglishLong");
		grounds_144.add("nameEnglish");
		mapTagGround.put("english_countries.owl",grounds_144);

		Set<String> grounds_145 = new HashSet<String>();
		grounds_145.add("TransportWithBiochemicalReaction");
		mapTagGround.put("transport_biopax-level3.owl",grounds_145);

		Set<String> grounds_146 = new HashSet<String>();
		grounds_146.add("Privileged-Utilities");
		grounds_146.add("Unprivileged-Utilities");
		mapTagGround.put("utilities_resist.owl",grounds_146);

		Set<String> grounds_147 = new HashSet<String>();
		grounds_147.add("MusicPrefs");
		grounds_147.add("MusicGenrePrefs");
		mapTagGround.put("music_contextvocabulary.owl",grounds_147);

		Set<String> grounds_148 = new HashSet<String>();
		grounds_148.add("hasFileCabinet");
		grounds_148.add("FileCabinet");
		mapTagGround.put("file_office.rdf",grounds_148);

		Set<String> grounds_149 = new HashSet<String>();
		grounds_149.add("ComputerGraphics");
		mapTagGround.put("graphics_data.owl",grounds_149);

		Set<String> grounds_150 = new HashSet<String>();
		grounds_150.add("VideoAgePrefs");
		grounds_150.add("VideoQualityPrefs");
		mapTagGround.put("video_contextvocabulary.owl",grounds_150);

		Set<String> grounds_151 = new HashSet<String>();
		grounds_151.add("hasIncidentDataProvider");
		mapTagGround.put("data_terrorism.owl",grounds_151);

		Set<String> grounds_152 = new HashSet<String>();
		grounds_152.add("technicalReport");
		grounds_152.add("TechnicalReport");
		mapTagGround.put("report_ka.owl",grounds_152);

		Set<String> grounds_153 = new HashSet<String>();
		grounds_153.add("ComplexAssembly");
		mapTagGround.put("assembly_biopax-level3.owl",grounds_153);

		Set<String> grounds_154 = new HashSet<String>();
		grounds_154.add("Games_Entertainment");
		mapTagGround.put("entertainment_infra.owl",grounds_154);

		Set<String> grounds_155 = new HashSet<String>();
		grounds_155.add("MultiMediaPrefs");
		mapTagGround.put("media_contextvocabulary.owl",grounds_155);

		Set<String> grounds_156 = new HashSet<String>();
		grounds_156.add("SoftwareApplications");
		mapTagGround.put("applications_ka.owl",grounds_156);

		Set<String> grounds_157 = new HashSet<String>();
		grounds_157.add("NBCR_Attack");
		mapTagGround.put("c++_terrorism.owl",grounds_157);

		Set<String> grounds_158 = new HashSet<String>();
		grounds_158.add("ShoppingAdsPrefs");
		mapTagGround.put("shopping_contextvocabulary.owl",grounds_158);

		Set<String> grounds_159 = new HashSet<String>();
		grounds_159.add("JDK1.1JavaUtil");
		grounds_159.add("JDK1.1JavaIO");
		grounds_159.add("JavaRenderingEngine");
		grounds_159.add("JavaAWT");
		grounds_159.add("JavaText");
		grounds_159.add("JDKJavaLang");
		grounds_159.add("JDKJavaUtil");
		grounds_159.add("CLDCJavaUtil");
		grounds_159.add("JavaAWTEvent");
		grounds_159.add("JavaLibrary");
		grounds_159.add("JavaLangRef");
		grounds_159.add("JavaLangReflect");
		grounds_159.add("hasPersonalJavaVersion");
		grounds_159.add("JavaJar");
		grounds_159.add("CLDCJavaLang");
		grounds_159.add("JavaIO");
		grounds_159.add("JavaUtilZip");
		grounds_159.add("JavaRMI");
		grounds_159.add("JavaVM");
		grounds_159.add("StreamJavaIO");
		grounds_159.add("JavaMath");
		grounds_159.add("JavaSQL");
		grounds_159.add("JavaNet");
		grounds_159.add("JavaBeans");
		grounds_159.add("PersonalJava");
		mapTagGround.put("java_java.owl",grounds_159);

		Set<String> grounds_160 = new HashSet<String>();
		grounds_160.add("PaperLibrary");
		grounds_160.add("ConferencePaper");
		grounds_160.add("WorkshopPaper");
		mapTagGround.put("paper_ka.owl",grounds_160);

		Set<String> grounds_161 = new HashSet<String>();
		grounds_161.add("Explosive_Fuse");
		mapTagGround.put("fuse_terrorism.owl",grounds_161);

		Set<String> grounds_162 = new HashSet<String>();
		grounds_162.add("GermanyRegion");
		mapTagGround.put("germany_wine.owl",grounds_162);

		Set<String> grounds_163 = new HashSet<String>();
		grounds_163.add("FoodPrefs");
		mapTagGround.put("food_contextvocabulary.owl",grounds_163);

		Set<String> grounds_164 = new HashSet<String>();
		grounds_164.add("hasCalendarType");
		grounds_164.add("hasCalendar");
		mapTagGround.put("calendar_esg1.1.owl",grounds_164);

		Set<String> grounds_165 = new HashSet<String>();
		grounds_165.add("FlashLight");
		mapTagGround.put("flash_photography.owl",grounds_165);

		Set<String> grounds_166 = new HashSet<String>();
		grounds_166.add("ComputerScience");
		mapTagGround.put("science_data.owl",grounds_166);

		Set<String> grounds_167 = new HashSet<String>();
		grounds_167.add("Software_Development");
		mapTagGround.put("development_infra.owl",grounds_167);

		Set<String> grounds_168 = new HashSet<String>();
		grounds_168.add("Static-Analysis");
		mapTagGround.put("analysis_resist.owl",grounds_168);

		Set<String> grounds_169 = new HashSet<String>();
		grounds_169.add("ServesData");
		mapTagGround.put("data_esg1.1.owl",grounds_169);

		Set<String> grounds_170 = new HashSet<String>();
		grounds_170.add("ArtificialIntelligence");
		mapTagGround.put("intelligence_data.owl",grounds_170);

		Set<String> grounds_171 = new HashSet<String>();
		grounds_171.add("versionInfo");
		mapTagGround.put("info_wine.owl",grounds_171);

		Set<String> grounds_172 = new HashSet<String>();
		grounds_172.add("WebMediatedPSMSelection");
		mapTagGround.put("web_ka.owl",grounds_172);

		Set<String> grounds_173 = new HashSet<String>();
		grounds_173.add("DecreasedRiskPersistentSideEffects");
		grounds_173.add("ChangeRiskPersistentSideEffects");
		grounds_173.add("PersistentSideEffectsInst");
		grounds_173.add("IncreasedRiskPersistentSideEffects");
		grounds_173.add("NoChangeRiskPersistentSideEffects");
		grounds_173.add("NoChangeRiskSideEffects");
		grounds_173.add("ChangeRiskSideEffects");
		grounds_173.add("DecreasedRiskSideEffects");
		grounds_173.add("PersistentSideEffects");
		grounds_173.add("IncreasedRiskSideEffects");
		mapTagGround.put("effects_cancer.owl",grounds_173);

		Set<String> grounds_174 = new HashSet<String>();
		grounds_174.add("AutomatedCodeGenerationFromSpecification");
		mapTagGround.put("code_ka.owl",grounds_174);

		Set<String> grounds_175 = new HashSet<String>();
		grounds_175.add("LightType");
		grounds_175.add("producesLightType");
		mapTagGround.put("type_photography.owl",grounds_175);

		Set<String> grounds_176 = new HashSet<String>();
		grounds_176.add("_Internet");
		mapTagGround.put("internet_infra.owl",grounds_176);

		Set<String> grounds_177 = new HashSet<String>();
		grounds_177.add("disjointObjectProperties");
		mapTagGround.put("object_cancer.owl",grounds_177);

		Set<String> grounds_178 = new HashSet<String>();
		grounds_178.add("DataBase");
		mapTagGround.put("data_infra.owl",grounds_178);

		Set<String> grounds_179 = new HashSet<String>();
		grounds_179.add("associatedToService");
		mapTagGround.put("service_esg1.1.owl",grounds_179);

		Set<String> grounds_180 = new HashSet<String>();
		grounds_180.add("JavaAWTEvent");
		mapTagGround.put("event_java.owl",grounds_180);

		Set<String> grounds_181 = new HashSet<String>();
		grounds_181.add("JavaLibrary");
		mapTagGround.put("library_java.owl",grounds_181);

		Set<String> grounds_182 = new HashSet<String>();
		grounds_182.add("CurrawongBeach");
		grounds_182.add("BondiBeach");
		mapTagGround.put("beach_travel.owl",grounds_182);

		Set<String> grounds_183 = new HashSet<String>();
		grounds_183.add("evidenceCode");
		grounds_183.add("EvidenceCodeVocabulary");
		mapTagGround.put("code_biopax-level3.owl",grounds_183);

		Set<String> grounds_184 = new HashSet<String>();
		grounds_184.add("MedicalIntervention");
		grounds_184.add("MedicalThings");
		mapTagGround.put("medical_cancer.owl",grounds_184);

		Set<String> grounds_185 = new HashSet<String>();
		grounds_185.add("subOrganizationOf");
		grounds_185.add("hasSubOrganization");
		grounds_185.add("roleInEncompasingOrganization");
		grounds_185.add("organizationType");
		mapTagGround.put("organization_terrorism.owl",grounds_185);

		Set<String> grounds_186 = new HashSet<String>();
		grounds_186.add("stepProcess");
		mapTagGround.put("process_biopax-level3.owl",grounds_186);

		Set<String> grounds_187 = new HashSet<String>();
		grounds_187.add("SportsNewsPrefs");
		grounds_187.add("GossipsNewsPrefs");
		grounds_187.add("WeatherNewsPrefs");
		grounds_187.add("PoliticsNewsPrefs");
		grounds_187.add("NewsPrefs");
		mapTagGround.put("news_contextvocabulary.owl",grounds_187);

		Set<String> grounds_188 = new HashSet<String>();
		grounds_188.add("KoalaWithPhD");
		mapTagGround.put("phd_koala.owl",grounds_188);

		Set<String> grounds_189 = new HashSet<String>();
		grounds_189.add("mappingAuthorityName");
		grounds_189.add("mappingAuthority");
		mapTagGround.put("authority_esg1.1.owl",grounds_189);

		Set<String> grounds_190 = new HashSet<String>();
		grounds_190.add("FunctionalLanguage");
		mapTagGround.put("functional_data.owl",grounds_190);

		Set<String> grounds_191 = new HashSet<String>();
		grounds_191.add("hasEMail");
		mapTagGround.put("mail_travel.owl",grounds_191);

		Set<String> grounds_192 = new HashSet<String>();
		grounds_192.add("ProgrammingLanguage");
		mapTagGround.put("programming_data.owl",grounds_192);

		Set<String> grounds_193 = new HashSet<String>();
		grounds_193.add("versionInfo");
		mapTagGround.put("info_cto.owl",grounds_193);

		Set<String> grounds_194 = new HashSet<String>();
		grounds_194.add("ReferenceSystem");
		grounds_194.add("AL_relatedToAbsoluteLocationReferenceSystem");
		grounds_194.add("RL_relatedToRelativeLocationReferenceSystem");
		mapTagGround.put("system_contextvocabulary.owl",grounds_194);

		Set<String> grounds_195 = new HashSet<String>();
		grounds_195.add("FastExposure");
		grounds_195.add("VeryFastExposure");
		mapTagGround.put("fast_photography.owl",grounds_195);

		Set<String> grounds_196 = new HashSet<String>();
		grounds_196.add("Version_Control");
		mapTagGround.put("control_infra.owl",grounds_196);

		Set<String> grounds_197 = new HashSet<String>();
		grounds_197.add("GamesGenrePrefs");
		grounds_197.add("GamesPrefs");
		mapTagGround.put("games_contextvocabulary.owl",grounds_197);

		Set<String> grounds_198 = new HashSet<String>();
		grounds_198.add("versionInfo");
		mapTagGround.put("info_svecore.owl",grounds_198);

		Set<String> grounds_199 = new HashSet<String>();
		grounds_199.add("DevicePrefs");
		grounds_199.add("DeviceCapability");
		grounds_199.add("hasDeviceCapabilities");
		grounds_199.add("U_relatedToUserDevice");
		mapTagGround.put("device_contextvocabulary.owl",grounds_199);

		Set<String> grounds_200 = new HashSet<String>();
		grounds_200.add("EvaluationOfKA");
		mapTagGround.put("evaluation_ka.owl",grounds_200);

		Set<String> grounds_201 = new HashSet<String>();
		grounds_201.add("JavaxMicroeditionMedia");
		mapTagGround.put("media_java.owl",grounds_201);

		Set<String> grounds_202 = new HashSet<String>();
		grounds_202.add("SpecificationLanguages");
		grounds_202.add("ExecutableSpecificationLanguages");
		mapTagGround.put("languages_ka.owl",grounds_202);

		Set<String> grounds_203 = new HashSet<String>();
		grounds_203.add("CF");
		grounds_203.add("CPU");
		mapTagGround.put("c++_esg1.1.owl",grounds_203);

		Set<String> grounds_204 = new HashSet<String>();
		grounds_204.add("ClosDeLaPoussieSancerre");
		grounds_204.add("ChateauDeMeursaultMeursault");
		grounds_204.add("ClosDeLaPoussie");
		grounds_204.add("ClosDeVougeotCotesDOr");
		grounds_204.add("ClosDeVougeot");
		grounds_204.add("ChateauDeMeursault");
		mapTagGround.put("de_wine.owl",grounds_204);

		Set<String> grounds_205 = new HashSet<String>();
		grounds_205.add("MultimodalInterfacePrefs");
		grounds_205.add("SpeechInterfacePrefs");
		grounds_205.add("InterfacePrefs");
		mapTagGround.put("interface_contextvocabulary.owl",grounds_205);

		Set<String> grounds_206 = new HashSet<String>();
		grounds_206.add("DataMining");
		mapTagGround.put("data_ka.owl",grounds_206);

		Set<String> grounds_207 = new HashSet<String>();
		grounds_207.add("CD");
		grounds_207.add("CVS");
		grounds_207.add("CGI");
		grounds_207.add("CASE");
		mapTagGround.put("c++_infra.owl",grounds_207);

		Set<String> grounds_208 = new HashSet<String>();
		grounds_208.add("JavaRenderingEngine");
		mapTagGround.put("engine_java.owl",grounds_208);

		Set<String> grounds_209 = new HashSet<String>();
		grounds_209.add("authorOfOntology");
		mapTagGround.put("author_ka.owl",grounds_209);

		Set<String> grounds_210 = new HashSet<String>();
		grounds_210.add("OnlinePublication");
		grounds_210.add("onlineVersionOf");
		grounds_210.add("onlineVersion");
		mapTagGround.put("online_ka.owl",grounds_210);

		Set<String> grounds_211 = new HashSet<String>();
		grounds_211.add("versionInfo");
		mapTagGround.put("info_office.rdf",grounds_211);

		Set<String> grounds_212 = new HashSet<String>();
		grounds_212.add("hasOntologyMapping");
		mapTagGround.put("ontology_esg1.1.owl",grounds_212);

		Set<String> grounds_213 = new HashSet<String>();
		grounds_213.add("_Authentication");
		mapTagGround.put("authentication_infra.owl",grounds_213);

		Set<String> grounds_214 = new HashSet<String>();
		grounds_214.add("J2ME3D");
		mapTagGround.put("3d_java.owl",grounds_214);

		Set<String> grounds_215 = new HashSet<String>();
		grounds_215.add("DesktopComputer");
		grounds_215.add("LaptopComputer");
		grounds_215.add("hasComputer");
		mapTagGround.put("computer_office.rdf",grounds_215);

		Set<String> grounds_216 = new HashSet<String>();
		grounds_216.add("Build_Tools");
		mapTagGround.put("tools_infra.owl",grounds_216);

		Set<String> grounds_217 = new HashSet<String>();
		grounds_217.add("ProtoEarlyCMFTChemoTam");
		grounds_217.add("IncreasedRiskCRC1.9");
		grounds_217.add("CMFRegimeTypes");
		grounds_217.add("EarlyCMFTChemoTam");
		grounds_217.add("ACTChemoTam");
		grounds_217.add("ECMFRegimeType");
		grounds_217.add("ProtoLateCMFTChemTam");
		grounds_217.add("ECMFTypeInstance");
		grounds_217.add("ProtoACT");
		grounds_217.add("CMFTChemoTam");
		grounds_217.add("LateCMFTChemoTam");
		mapTagGround.put("c++_cancer.owl",grounds_217);

		Set<String> grounds_218 = new HashSet<String>();
		grounds_218.add("_Internet");
		mapTagGround.put("internet_infra.owl",grounds_218);

		Set<String> grounds_219 = new HashSet<String>();
		grounds_219.add("productFAQ");
		grounds_219.add("productMailingList");
		grounds_219.add("productName");
		grounds_219.add("productPublication");
		mapTagGround.put("product_ka.owl",grounds_219);

		Set<String> grounds_220 = new HashSet<String>();
		grounds_220.add("WeatherNewsPrefs");
		mapTagGround.put("weather_contextvocabulary.owl",grounds_220);

		Set<String> grounds_221 = new HashSet<String>();
		grounds_221.add("hasImage");
		mapTagGround.put("image_office.rdf",grounds_221);

		Set<String> grounds_222 = new HashSet<String>();
		grounds_222.add("S_relatedToPhysicalEntitySpace");
		grounds_222.add("S_relatedToSpaceAbsoluteLocation");
		grounds_222.add("S_relatedToActivitySpace");
		grounds_222.add("A_relatedToActivitySpace");
		grounds_222.add("S_relatedToSpaceEnvironmentalProfile");
		grounds_222.add("U_relatedToUserSpace");
		grounds_222.add("S_relatedToUserSpace");
		mapTagGround.put("space_contextvocabulary.owl",grounds_222);

		Set<String> grounds_223 = new HashSet<String>();
		grounds_223.add("atEvent");
		grounds_223.add("eventTitle");
		grounds_223.add("SpecialIssueEvent");
		mapTagGround.put("event_ka.owl",grounds_223);

		Set<String> grounds_224 = new HashSet<String>();
		grounds_224.add("versionInfo");
		mapTagGround.put("info_biopax-level3.owl",grounds_224);

		Set<String> grounds_225 = new HashSet<String>();
		grounds_225.add("DesktopComputer");
		mapTagGround.put("desktop_office.rdf",grounds_225);

		Set<String> grounds_226 = new HashSet<String>();
		grounds_226.add("hasColor");
		grounds_226.add("WineColor");
		mapTagGround.put("color_wine.owl",grounds_226);

		Set<String> grounds_227 = new HashSet<String>();
		grounds_227.add("Threat-To-Dependable-Systems");
		grounds_227.add("Means-To-Attain-Dependable-Systems");
		grounds_227.add("Attribute-Of-Dependable-Systems");
		mapTagGround.put("systems_resist.owl",grounds_227);

		Set<String> grounds_228 = new HashSet<String>();
		grounds_228.add("worksAtProject");
		mapTagGround.put("works_ka.owl",grounds_228);

		Set<String> grounds_229 = new HashSet<String>();
		grounds_229.add("SpeechInterfacePrefs");
		mapTagGround.put("speech_contextvocabulary.owl",grounds_229);

		Set<String> grounds_230 = new HashSet<String>();
		grounds_230.add("CMFRegimeTypes");
		grounds_230.add("DrugRegimeTypes");
		grounds_230.add("AnastrazoleRegimeTypes");
		grounds_230.add("ChemoTxRegimeTypes");
		grounds_230.add("SymptomTypes");
		grounds_230.add("HormoneRARegimeTypes");
		grounds_230.add("AnthracyclineChemoTxRegimeTypes");
		grounds_230.add("PolyChemoTxRegimeTypes");
		grounds_230.add("CancerTypes");
		grounds_230.add("DiseaseTypes");
		grounds_230.add("NonAnthracyclineChemoTxRegimeTypes");
		grounds_230.add("ChemoTamRegimeTypes");
		mapTagGround.put("types_cancer.owl",grounds_230);

		Set<String> grounds_231 = new HashSet<String>();
		grounds_231.add("_Security");
		mapTagGround.put("security_infra.owl",grounds_231);

		Set<String> grounds_232 = new HashSet<String>();
		grounds_232.add("versionInfo");
		grounds_232.add("projectInfo");
		mapTagGround.put("info_ka.owl",grounds_232);

		Set<String> grounds_233 = new HashSet<String>();
		grounds_233.add("WorkshopPaper");
		mapTagGround.put("workshop_ka.owl",grounds_233);

		Set<String> grounds_234 = new HashSet<String>();
		grounds_234.add("KnowledgeSkillGP");
		grounds_234.add("KnowledgeSkillCluster");
		mapTagGround.put("knowledge_data.owl",grounds_234);

		Set<String> grounds_235 = new HashSet<String>();
		grounds_235.add("KAThroughMachineLearning");
		mapTagGround.put("machine_ka.owl",grounds_235);

		Set<String> grounds_236 = new HashSet<String>();
		grounds_236.add("ElectronicSensor");
		mapTagGround.put("electronic_photography.owl",grounds_236);

		Set<String> grounds_237 = new HashSet<String>();
		grounds_237.add("InformationObject");
		mapTagGround.put("information_cto.owl",grounds_237);

		Set<String> grounds_238 = new HashSet<String>();
		grounds_238.add("stepConversion");
		mapTagGround.put("conversion_biopax-level3.owl",grounds_238);

		Set<String> grounds_239 = new HashSet<String>();
		grounds_239.add("KnowledgeDiscoveryInDatasets");
		mapTagGround.put("discovery_ka.owl",grounds_239);

		Set<String> grounds_240 = new HashSet<String>();
		grounds_240.add("ProgrammingLanguage");
		grounds_240.add("FunctionalLanguage");
		grounds_240.add("ProcedualLanguage");
		mapTagGround.put("language_data.owl",grounds_240);

		Set<String> grounds_241 = new HashSet<String>();
		grounds_241.add("SmallMoleculeReference");
		grounds_241.add("EntityReferenceGroupVocabulary");
		grounds_241.add("ProteinReference");
		grounds_241.add("DnaReference");
		grounds_241.add("referenceEntity");
		grounds_241.add("RnaReference");
		grounds_241.add("EntityReference");
		mapTagGround.put("reference_biopax-level3.owl",grounds_241);

		Set<String> grounds_242 = new HashSet<String>();
		grounds_242.add("SpecialEffectsFilter");
		mapTagGround.put("effects_photography.owl",grounds_242);

		Set<String> grounds_243 = new HashSet<String>();
		grounds_243.add("Email_Account");
		mapTagGround.put("account_terrorism.owl",grounds_243);

		Set<String> grounds_244 = new HashSet<String>();
		grounds_244.add("SpecificationOfControlKnowledge");
		mapTagGround.put("control_ka.owl",grounds_244);

		Set<String> grounds_245 = new HashSet<String>();
		grounds_245.add("ComputingMethodology");
		mapTagGround.put("computing_data.owl",grounds_245);

		Set<String> grounds_246 = new HashSet<String>();
		grounds_246.add("Games_Entertainment");
		mapTagGround.put("entertainment_infra.owl",grounds_246);

		Set<String> grounds_247 = new HashSet<String>();
		grounds_247.add("hasDateTimeEncoding");
		grounds_247.add("hasTimeCoverage");
		grounds_247.add("TimeCoverage");
		grounds_247.add("timeName");
		grounds_247.add("DateTimeEncoding");
		mapTagGround.put("time_esg1.1.owl",grounds_247);

		Set<String> grounds_248 = new HashSet<String>();
		grounds_248.add("_Network");
		mapTagGround.put("network_infra.owl",grounds_248);

		Set<String> grounds_249 = new HashSet<String>();
		grounds_249.add("U_relatedToUserCompany");
		grounds_249.add("CompanyBuilding");
		mapTagGround.put("company_contextvocabulary.owl",grounds_249);

		Set<String> grounds_250 = new HashSet<String>();
		grounds_250.add("Dependable-Systems-Technology");
		mapTagGround.put("technology_resist.owl",grounds_250);

		Set<String> grounds_251 = new HashSet<String>();
		grounds_251.add("Board_Games");
		mapTagGround.put("games_infra.owl",grounds_251);

		Set<String> grounds_252 = new HashSet<String>();
		grounds_252.add("WebMediatedPSMSelection");
		mapTagGround.put("web_ka.owl",grounds_252);

		Set<String> grounds_253 = new HashSet<String>();
		grounds_253.add("versionInfo");
		mapTagGround.put("info_infra.owl",grounds_253);

		Set<String> grounds_254 = new HashSet<String>();
		grounds_254.add("ManualFocus");
		mapTagGround.put("manual_photography.owl",grounds_254);

		Set<String> grounds_255 = new HashSet<String>();
		grounds_255.add("Ordinal-Evaluation");
		grounds_255.add("Probabilistic-Evaluation");
		mapTagGround.put("evaluation_resist.owl",grounds_255);

		Set<String> grounds_256 = new HashSet<String>();
		grounds_256.add("RegisteringAuthority");
		mapTagGround.put("authority_terrorism.owl",grounds_256);

		Set<String> grounds_257 = new HashSet<String>();
		grounds_257.add("ShoppingAdsPrefs");
		mapTagGround.put("shopping_contextvocabulary.owl",grounds_257);

		Set<String> grounds_258 = new HashSet<String>();
		grounds_258.add("LearningApprenticeSystems");
		mapTagGround.put("systems_ka.owl",grounds_258);

		Set<String> grounds_259 = new HashSet<String>();
		grounds_259.add("internationalFundingAgencies");
		mapTagGround.put("international_ka.owl",grounds_259);

		Set<String> grounds_260 = new HashSet<String>();
		grounds_260.add("authorOfOntology");
		grounds_260.add("OntologyLibrary");
		mapTagGround.put("ontology_ka.owl",grounds_260);

		Set<String> grounds_261 = new HashSet<String>();
		grounds_261.add("versionInfo");
		mapTagGround.put("info_travel.owl",grounds_261);

		Set<String> grounds_262 = new HashSet<String>();
		grounds_262.add("containsArticle");
		grounds_262.add("JournalArticle");
		grounds_262.add("ArticleInBook");
		mapTagGround.put("article_ka.owl",grounds_262);

		Set<String> grounds_263 = new HashSet<String>();
		grounds_263.add("internationalFundingAgencies");
		grounds_263.add("nationalFundingAgencies");
		mapTagGround.put("funding_ka.owl",grounds_263);

		Set<String> grounds_264 = new HashSet<String>();
		grounds_264.add("Evaluation-Testing");
		mapTagGround.put("testing_resist.owl",grounds_264);

		Set<String> grounds_265 = new HashSet<String>();
		grounds_265.add("ElicitationTool");
		grounds_265.add("InternetTool");
		grounds_265.add("TransformationTool");
		mapTagGround.put("tool_ka.owl",grounds_265);

		Set<String> grounds_266 = new HashSet<String>();
		grounds_266.add("GermanWine");
		mapTagGround.put("german_wine.owl",grounds_266);

		Set<String> grounds_267 = new HashSet<String>();
		grounds_267.add("Number_Released");
		mapTagGround.put("released_terrorism.owl",grounds_267);

		Set<String> grounds_268 = new HashSet<String>();
		grounds_268.add("NegativeImage");
		grounds_268.add("PositiveImage");
		grounds_268.add("ImageFeature");
		grounds_268.add("producesImage");
		mapTagGround.put("image_photography.owl",grounds_268);

		Set<String> grounds_269 = new HashSet<String>();
		grounds_269.add("CongressSpringsSemillon");
		grounds_269.add("CongressSprings");
		mapTagGround.put("congress_wine.owl",grounds_269);

		Set<String> grounds_270 = new HashSet<String>();
		grounds_270.add("Genetic_Algorithms");
		mapTagGround.put("algorithms_infra.owl",grounds_270);

		Set<String> grounds_271 = new HashSet<String>();
		grounds_271.add("containsArticle");
		grounds_271.add("JournalArticle");
		grounds_271.add("ArticleInBook");
		mapTagGround.put("article_ka.owl",grounds_271);

		Set<String> grounds_272 = new HashSet<String>();
		grounds_272.add("AcademicStaff");
		mapTagGround.put("academic_ka.owl",grounds_272);

		Set<String> grounds_273 = new HashSet<String>();
		grounds_273.add("SupportToolsForFormalMethods");
		mapTagGround.put("tools_ka.owl",grounds_273);

		Set<String> grounds_274 = new HashSet<String>();
		grounds_274.add("DiscreteMathematics");
		mapTagGround.put("mathematics_data.owl",grounds_274);

		Set<String> grounds_275 = new HashSet<String>();
		grounds_275.add("versionInfo");
		mapTagGround.put("info_contextvocabulary.owl",grounds_275);

		Set<String> grounds_276 = new HashSet<String>();
		grounds_276.add("hasRating");
		grounds_276.add("TwoStarRating");
		grounds_276.add("ThreeStarRating");
		grounds_276.add("OneStarRating");
		grounds_276.add("AccommodationRating");
		mapTagGround.put("rating_travel.owl",grounds_276);

		Set<String> grounds_277 = new HashSet<String>();
		grounds_277.add("hasPersonalJavaVersion");
		grounds_277.add("PersonalJava");
		mapTagGround.put("personal_java.owl",grounds_277);

		Set<String> grounds_278 = new HashSet<String>();
		grounds_278.add("headOfGroup");
		grounds_278.add("ResearchGroup");
		mapTagGround.put("group_ka.owl",grounds_278);

		Set<String> grounds_279 = new HashSet<String>();
		grounds_279.add("versionInfo");
		mapTagGround.put("info_photography.owl",grounds_279);

		Set<String> grounds_280 = new HashSet<String>();
		grounds_280.add("PersonalDetails");
		grounds_280.add("hasPersonalDetails");
		mapTagGround.put("personal_contextvocabulary.owl",grounds_280);

		Set<String> grounds_281 = new HashSet<String>();
		grounds_281.add("versionInfo");
		mapTagGround.put("info_data.owl",grounds_281);

		Set<String> grounds_282 = new HashSet<String>();
		grounds_282.add("RangeFinder");
		mapTagGround.put("finder_photography.owl",grounds_282);

		Set<String> grounds_283 = new HashSet<String>();
		grounds_283.add("hasChildren");
		mapTagGround.put("children_koala.owl",grounds_283);

		Set<String> grounds_284 = new HashSet<String>();
		grounds_284.add("JavaxMicroeditionMedia");
		mapTagGround.put("media_java.owl",grounds_284);

		Set<String> grounds_285 = new HashSet<String>();
		grounds_285.add("hasFunding");
		mapTagGround.put("funding_esg1.1.owl",grounds_285);

		Set<String> grounds_286 = new HashSet<String>();
		grounds_286.add("FrenchRegion");
		grounds_286.add("FrenchWine");
		mapTagGround.put("french_wine.owl",grounds_286);

		Set<String> grounds_287 = new HashSet<String>();
		grounds_287.add("A_relatedToActivityService");
		grounds_287.add("ServicePrefs");
		grounds_287.add("U_relatedToUserService");
		mapTagGround.put("service_contextvocabulary.owl",grounds_287);

		Set<String> grounds_288 = new HashSet<String>();
		grounds_288.add("ConferencePaper");
		mapTagGround.put("conference_ka.owl",grounds_288);

		Set<String> grounds_289 = new HashSet<String>();
		grounds_289.add("InformationObject");
		mapTagGround.put("object_cto.owl",grounds_289);

		Set<String> grounds_290 = new HashSet<String>();
		grounds_290.add("eCNumber");
		mapTagGround.put("c++_biopax-level3.owl",grounds_290);

		Set<String> grounds_291 = new HashSet<String>();
		grounds_291.add("ComputingMethodology");
		mapTagGround.put("methodology_data.owl",grounds_291);

		Set<String> grounds_292 = new HashSet<String>();
		grounds_292.add("OnlinePublication");
		grounds_292.add("onlineVersionOf");
		grounds_292.add("onlineVersion");
		mapTagGround.put("online_ka.owl",grounds_292);

		Set<String> grounds_293 = new HashSet<String>();
		grounds_293.add("JavaMath");
		mapTagGround.put("math_java.owl",grounds_293);

		Set<String> grounds_294 = new HashSet<String>();
		grounds_294.add("_Database");
		mapTagGround.put("database_infra.owl",grounds_294);

		Set<String> grounds_295 = new HashSet<String>();
		grounds_295.add("I_relatedToIntervalTime");
		mapTagGround.put("time_contextvocabulary.owl",grounds_295);

		Set<String> grounds_296 = new HashSet<String>();
		grounds_296.add("hasProfile");
		mapTagGround.put("profile_midas_profile.owl",grounds_296);

		Set<String> grounds_297 = new HashSet<String>();
		grounds_297.add("versionInfo");
		mapTagGround.put("info_cancer.owl",grounds_297);

		Set<String> grounds_298 = new HashSet<String>();
		grounds_298.add("StreetPhotography");
		mapTagGround.put("photography_photography.owl",grounds_298);

		Set<String> grounds_299 = new HashSet<String>();
		grounds_299.add("S_relatedToSpaceEnvironmentalProfile");
		grounds_299.add("EnvironmentalProfile");
		mapTagGround.put("profile_contextvocabulary.owl",grounds_299);

		Set<String> grounds_300 = new HashSet<String>();
		grounds_300.add("CMOS");
		grounds_300.add("CCD");
		mapTagGround.put("c++_photography.owl",grounds_300);

		Set<String> grounds_301 = new HashSet<String>();
		grounds_301.add("domainInterGroupSocialRelation");
		grounds_301.add("InterGroupSocialRelation");
		grounds_301.add("targetInterGroupSocialRelation");
		grounds_301.add("targetGroupSocialRelation");
		grounds_301.add("GroupSocialRelation");
		grounds_301.add("domainGroupSocialRelation");
		mapTagGround.put("group_svecore.owl",grounds_301);

		Set<String> grounds_302 = new HashSet<String>();
		grounds_302.add("productMailingList");
		mapTagGround.put("list_ka.owl",grounds_302);

		Set<String> grounds_303 = new HashSet<String>();
		grounds_303.add("SportsNewsPrefs");
		grounds_303.add("GossipsNewsPrefs");
		grounds_303.add("WeatherNewsPrefs");
		grounds_303.add("PoliticsNewsPrefs");
		grounds_303.add("NewsPrefs");
		mapTagGround.put("news_contextvocabulary.owl",grounds_303);

		Set<String> grounds_304 = new HashSet<String>();
		grounds_304.add("LearningApprenticeSystems");
		grounds_304.add("ReinforcementLearning");
		grounds_304.add("KAThroughMachineLearning");
		mapTagGround.put("learning_ka.owl",grounds_304);
		
		return mapTagGround; 
	}
	
	
	
	
	
	public static Map<String, Set<String>>  loadDeliciousTagGround () {
		
		Map<String, Set<String>> mapTagGround = new HashMap<String, Set<String>>();
		
		Set<String> grounds_0 = new HashSet<String>();
		grounds_0.add("FlashLight");
		mapTagGround.put("flash",grounds_0);
		 
		Set<String> grounds_1 = new HashSet<String>();
		grounds_1.add("ProgrammingLanguage");
		mapTagGround.put("programming",grounds_1);
		 
		Set<String> grounds_2 = new HashSet<String>();
		grounds_2.add("OnlinePublication");
		grounds_2.add("onlineVersionOf");
		grounds_2.add("onlineVersion");
		mapTagGround.put("online",grounds_2);
		 
		Set<String> grounds_3 = new HashSet<String>();
		grounds_3.add("DesktopComputer");
		mapTagGround.put("desktop",grounds_3);
		 
		Set<String> grounds_4 = new HashSet<String>();
		grounds_4.add("FrenchRegion");
		grounds_4.add("FrenchWine");
		mapTagGround.put("french",grounds_4);
		 
		Set<String> grounds_5 = new HashSet<String>();
		grounds_5.add("ActorPrefs");
		mapTagGround.put("actor",grounds_5);
		 
		Set<String> grounds_6 = new HashSet<String>();
		grounds_6.add("Project-Officer-In-Academia");
		grounds_6.add("Research-Fellow-In-Academia");
		grounds_6.add("Senior-Lecturer-In-Academia");
		grounds_6.add("Researcher-In-Academia");
		grounds_6.add("Senior-Research-Fellow-In-Academia");
		grounds_6.add("Professor-In-Academia");
		grounds_6.add("Research-Assistant-In-Academia");
		grounds_6.add("Lecturer-In-Academia");
		grounds_6.add("Reader-In-Academia");
		mapTagGround.put("academia",grounds_6);
		 
		Set<String> grounds_7 = new HashSet<String>();
		grounds_7.add("FloatValue");
		mapTagGround.put("float",grounds_7);
		 
		Set<String> grounds_8 = new HashSet<String>();
		grounds_8.add("baseFree");
		grounds_8.add("objectFree");
		mapTagGround.put("free",grounds_8);
		 
		Set<String> grounds_9 = new HashSet<String>();
		grounds_9.add("versionInfo");
		grounds_9.add("projectInfo");
		mapTagGround.put("info",grounds_9);
		 
		Set<String> grounds_10 = new HashSet<String>();
		grounds_10.add("AcademicStaff");
		mapTagGround.put("academic",grounds_10);
		 
		Set<String> grounds_11 = new HashSet<String>();
		grounds_11.add("hasColourTemperature");
		mapTagGround.put("temperature",grounds_11);
		 
		Set<String> grounds_12 = new HashSet<String>();
		grounds_12.add("Software-Visualization");
		mapTagGround.put("visualization",grounds_12);
		 
		Set<String> grounds_13 = new HashSet<String>();
		grounds_13.add("baseFree");
		grounds_13.add("objectFree");
		mapTagGround.put("free",grounds_13);
		 
		Set<String> grounds_14 = new HashSet<String>();
		grounds_14.add("Full-Time");
		grounds_14.add("hasDateTimeEncoding");
		grounds_14.add("hasTimeCoverage");
		grounds_14.add("SpaceTimeRegion");
		grounds_14.add("Part-Time");
		grounds_14.add("TimeCoverage");
		grounds_14.add("IntervalTime");
		grounds_14.add("timeName");
		grounds_14.add("I_relatedToIntervalTime");
		grounds_14.add("hasTime");
		grounds_14.add("DateTimeEncoding");
		mapTagGround.put("time",grounds_14);
		 
		Set<String> grounds_15 = new HashSet<String>();
		grounds_15.add("AgentSocialRelation");
		grounds_15.add("domainInterGroupSocialRelation");
		grounds_15.add("InterGroupSocialRelation");
		grounds_15.add("SocialContext");
		grounds_15.add("targetInterGroupSocialRelation");
		grounds_15.add("hasSocialContext");
		grounds_15.add("GroupSocialRelation");
		grounds_15.add("SocialRelation");
		grounds_15.add("targetAgentSocialRelation");
		grounds_15.add("targetGroupSocialRelation");
		grounds_15.add("domainAgentSocialRelation");
		grounds_15.add("socialProperty");
		grounds_15.add("domainGroupSocialRelation");
		mapTagGround.put("social",grounds_15);
		 
		Set<String> grounds_16 = new HashSet<String>();
		grounds_16.add("UserEvent");
		grounds_16.add("E_relatedToUserEvent");
		grounds_16.add("eventTitle");
		grounds_16.add("Information-Transfer-Event");
		grounds_16.add("TravelEvent");
		grounds_16.add("U_relatedToUserEvent");
		grounds_16.add("atEvent");
		grounds_16.add("Attending-An-Event");
		grounds_16.add("Publication-Type-Event");
		grounds_16.add("roleInEvent");
		grounds_16.add("JavaAWTEvent");
		grounds_16.add("SpecialIssueEvent");
		grounds_16.add("vehicleEvent");
		mapTagGround.put("event",grounds_16);
		 
		Set<String> grounds_17 = new HashSet<String>();
		grounds_17.add("hasFileCabinet");
		grounds_17.add("FileCabinet");
		mapTagGround.put("file",grounds_17);
		 
		Set<String> grounds_18 = new HashSet<String>();
		grounds_18.add("technicalReport");
		grounds_18.add("TechnicalReport");
		mapTagGround.put("report",grounds_18);
		 
		Set<String> grounds_19 = new HashSet<String>();
		grounds_19.add("WeatherNewsPrefs");
		mapTagGround.put("weather",grounds_19);
		 
		Set<String> grounds_20 = new HashSet<String>();
		grounds_20.add("_3D");
		grounds_20.add("J2ME3D");
		mapTagGround.put("3d",grounds_20);
		 
		Set<String> grounds_21 = new HashSet<String>();
		grounds_21.add("Recorded-Video");
		grounds_21.add("VideoAgePrefs");
		grounds_21.add("VideoQualityPrefs");
		mapTagGround.put("video",grounds_21);
		 
		Set<String> grounds_22 = new HashSet<String>();
		grounds_22.add("ArtificialIntelligence");
		mapTagGround.put("intelligence",grounds_22);
		 
		Set<String> grounds_23 = new HashSet<String>();
		grounds_23.add("IceWine");
		grounds_23.add("WineSugar");
		grounds_23.add("WineFlavor");
		grounds_23.add("SelaksIceWine");
		grounds_23.add("FrenchWine");
		grounds_23.add("AmericanWine");
		grounds_23.add("CaliforniaWine");
		grounds_23.add("SweetWine");
		grounds_23.add("DryRedWine");
		grounds_23.add("RedTableWine");
		grounds_23.add("DryWine");
		grounds_23.add("RedWine");
		grounds_23.add("DryWhiteWine");
		grounds_23.add("FullBodiedWine");
		grounds_23.add("GermanWine");
		grounds_23.add("AlsatianWine");
		grounds_23.add("WineDescriptor");
		grounds_23.add("WineBody");
		grounds_23.add("madeIntoWine");
		grounds_23.add("WineColor");
		grounds_23.add("DessertWine");
		grounds_23.add("WineGrape");
		grounds_23.add("TexasWine");
		grounds_23.add("WineTaste");
		grounds_23.add("WhiteNonSweetWine");
		grounds_23.add("WhiteWine");
		grounds_23.add("WhiteTableWine");
		grounds_23.add("TableWine");
		grounds_23.add("RoseWine");
		grounds_23.add("producesWine");
		grounds_23.add("ItalianWine");
		grounds_23.add("hasWineDescriptor");
		mapTagGround.put("wine",grounds_23);
		 
		Set<String> grounds_24 = new HashSet<String>();
		grounds_24.add("InformationObject");
		grounds_24.add("HomeInformationAndEntertainment");
		grounds_24.add("Abstract-Information");
		mapTagGround.put("information",grounds_24);
		 
		Set<String> grounds_25 = new HashSet<String>();
		grounds_25.add("IntelligentHomeSupport");
		grounds_25.add("ComputerSupport");
		grounds_25.add("SupportToolsForFormalMethods");
		grounds_25.add("HomeCareAndSafetySupport");
		grounds_25.add("ExtendedHomeSupport");
		mapTagGround.put("support",grounds_25);
		 
		Set<String> grounds_26 = new HashSet<String>();
		grounds_26.add("Explosive_Fuse");
		mapTagGround.put("fuse",grounds_26);
		 
		Set<String> grounds_27 = new HashSet<String>();
		grounds_27.add("Daily-Newspaper");
		mapTagGround.put("newspaper",grounds_27);
		 
		Set<String> grounds_28 = new HashSet<String>();
		grounds_28.add("DevelopmentProject");
		grounds_28.add("ResearchProject");
		grounds_28.add("projectInfo");
		grounds_28.add("worksAtProject");
		grounds_28.add("describesProject");
		grounds_28.add("SoftwareProject");
		mapTagGround.put("project",grounds_28);
		 
		Set<String> grounds_29 = new HashSet<String>();
		grounds_29.add("Web-Based-Editor");
		mapTagGround.put("editor",grounds_29);
		 
		Set<String> grounds_30 = new HashSet<String>();
		grounds_30.add("Web-Based-System");
		grounds_30.add("ReferenceSystem");
		grounds_30.add("AbsoluteLocationReferenceSystem");
		grounds_30.add("AL_relatedToAbsoluteLocationReferenceSystem");
		grounds_30.add("RelativeLocationReferenceSystem");
		grounds_30.add("Implemented-System");
		grounds_30.add("RL_relatedToRelativeLocationReferenceSystem");
		grounds_30.add("OperatingSystem");
		grounds_30.add("Operating-System");
		mapTagGround.put("system",grounds_30);
		 
		Set<String> grounds_31 = new HashSet<String>();
		grounds_31.add("QualityConcept");
		grounds_31.add("VideoQualityPrefs");
		grounds_31.add("hasQuality");
		mapTagGround.put("quality",grounds_31);
		 
		Set<String> grounds_32 = new HashSet<String>();
		grounds_32.add("Specification-Language");
		grounds_32.add("ModelingLanguage");
		grounds_32.add("Programming-Language");
		grounds_32.add("Scripting-Language");
		grounds_32.add("usedLanguage");
		grounds_32.add("defaultLanguage");
		grounds_32.add("ProgrammingLanguage");
		grounds_32.add("SpecificationLanguage");
		grounds_32.add("FunctionalLanguage");
		grounds_32.add("ProcedualLanguage");
		grounds_32.add("Specification-Or-Computing-Language");
		mapTagGround.put("language",grounds_32);
		 
		Set<String> grounds_33 = new HashSet<String>();
		grounds_33.add("KnowledgeDiscoveryInDatasets");
		mapTagGround.put("discovery",grounds_33);
		 
		Set<String> grounds_34 = new HashSet<String>();
		grounds_34.add("_Internet");
		grounds_34.add("InternetTool");
		mapTagGround.put("internet",grounds_34);
		 
		Set<String> grounds_35 = new HashSet<String>();
		grounds_35.add("DesktopComputer");
		grounds_35.add("LaptopComputer");
		grounds_35.add("ComputerGraphics");
		grounds_35.add("ComputerSupport");
		grounds_35.add("ComputerScience");
		grounds_35.add("hasComputer");
		mapTagGround.put("computer",grounds_35);
		 
		Set<String> grounds_36 = new HashSet<String>();
		grounds_36.add("DynamicCapBlock");
		grounds_36.add("DynamicReqBlock");
		grounds_36.add("DynamicBlock");
		mapTagGround.put("dynamic",grounds_36);
		 
		Set<String> grounds_37 = new HashSet<String>();
		grounds_37.add("KnowledgeElicitation");
		grounds_37.add("CooperativeKnowledgeAcquisition");
		grounds_37.add("KnowledgeSkillGP");
		grounds_37.add("KnowledgeManagement");
		grounds_37.add("hasKnowledge");
		grounds_37.add("KnowledgeDiscoveryInDatasets");
		grounds_37.add("AnomalyRepairAndKnowledgeRevision");
		grounds_37.add("KnowledgeSkillCluster");
		grounds_37.add("SpecificationOfControlKnowledge");
		grounds_37.add("KnowledgeBasedRefinement");
		mapTagGround.put("knowledge",grounds_37);
		 
		Set<String> grounds_38 = new HashSet<String>();
		grounds_38.add("ComplexAssembly");
		mapTagGround.put("assembly",grounds_38);
		 
		Set<String> grounds_39 = new HashSet<String>();
		grounds_39.add("todoProperty");
		grounds_39.add("todoPrefix");
		mapTagGround.put("todo",grounds_39);
		 
		Set<String> grounds_40 = new HashSet<String>();
		grounds_40.add("Evaluation-Testing");
		mapTagGround.put("testing",grounds_40);
		 
		Set<String> grounds_41 = new HashSet<String>();
		grounds_41.add("S_relatedToActivitySpace");
		grounds_41.add("UserSpace");
		grounds_41.add("SpaceEnvironmentalProfile");
		grounds_41.add("SpaceTimeRegion");
		grounds_41.add("S_relatedToSpaceEnvironmentalProfile");
		grounds_41.add("hasSpaceCoverage");
		grounds_41.add("SpaceAbsoluteLocation");
		grounds_41.add("ActivitySpace");
		grounds_41.add("S_relatedToUserSpace");
		grounds_41.add("SpaceCoverage");
		grounds_41.add("S_relatedToSpaceAbsoluteLocation");
		grounds_41.add("S_relatedToPhysicalEntitySpace");
		grounds_41.add("A_relatedToActivitySpace");
		grounds_41.add("PhysicalEntitySpace");
		grounds_41.add("U_relatedToUserSpace");
		grounds_41.add("PE_relatedToPhysicalEntitySpace");
		mapTagGround.put("space",grounds_41);
		 
		Set<String> grounds_42 = new HashSet<String>();
		grounds_42.add("DiscreteMathematics");
		mapTagGround.put("mathematics",grounds_42);
		 
		Set<String> grounds_43 = new HashSet<String>();
		grounds_43.add("mappingAuthorityName");
		grounds_43.add("mappingAuthority");
		grounds_43.add("RegisteringAuthority");
		mapTagGround.put("authority",grounds_43);
		 
		Set<String> grounds_44 = new HashSet<String>();
		grounds_44.add("ProgrammingLanguage");
		mapTagGround.put("programming",grounds_44);
		 
		Set<String> grounds_45 = new HashSet<String>();
		grounds_45.add("Web-Browser");
		mapTagGround.put("browser",grounds_45);
		 
		Set<String> grounds_46 = new HashSet<String>();
		grounds_46.add("MathematicalFoundation");
		mapTagGround.put("foundation",grounds_46);
		 
		Set<String> grounds_47 = new HashSet<String>();
		grounds_47.add("NetworkingTechnology_NNT");
		grounds_47.add("NetworkingTechnology");
		grounds_47.add("NetworkNetworkingTechnology");
		grounds_47.add("NT_relatedToNetworkNetworkingTechnology");
		grounds_47.add("N_relatedToNetworkNetworkingTechnology");
		mapTagGround.put("networking",grounds_47);
		 
		Set<String> grounds_48 = new HashSet<String>();
		grounds_48.add("SportsNewsPrefs");
		grounds_48.add("GossipsNewsPrefs");
		grounds_48.add("WeatherNewsPrefs");
		grounds_48.add("PoliticsNewsPrefs");
		grounds_48.add("NewsPrefs");
		mapTagGround.put("news",grounds_48);
		 
		Set<String> grounds_49 = new HashSet<String>();
		grounds_49.add("WebMediatedPSMSelection");
		mapTagGround.put("web",grounds_49);
		 
		Set<String> grounds_50 = new HashSet<String>();
		grounds_50.add("LearningApprenticeSystems");
		grounds_50.add("Organizational-Learning");
		grounds_50.add("ReinforcementLearning");
		grounds_50.add("KAThroughMachineLearning");
		mapTagGround.put("learning",grounds_50);
		 
		Set<String> grounds_51 = new HashSet<String>();
		grounds_51.add("SpecificationLanguages");
		grounds_51.add("ExecutableSpecificationLanguages");
		mapTagGround.put("languages",grounds_51);
		 
		Set<String> grounds_52 = new HashSet<String>();
		grounds_52.add("worksAtProject");
		mapTagGround.put("works",grounds_52);
		 
		Set<String> grounds_53 = new HashSet<String>();
		grounds_53.add("stepProcess");
		mapTagGround.put("process",grounds_53);
		 
		Set<String> grounds_54 = new HashSet<String>();
		grounds_54.add("JavaMath");
		mapTagGround.put("math",grounds_54);
		 
		Set<String> grounds_55 = new HashSet<String>();
		grounds_55.add("containsArticle");
		grounds_55.add("JournalArticle");
		grounds_55.add("ArticleInBook");
		mapTagGround.put("article",grounds_55);
		 
		Set<String> grounds_56 = new HashSet<String>();
		grounds_56.add("PaperLibrary");
		grounds_56.add("JavaLibrary");
		grounds_56.add("PSMLibrary");
		grounds_56.add("OntologyLibrary");
		mapTagGround.put("library",grounds_56);
		 
		Set<String> grounds_57 = new HashSet<String>();
		grounds_57.add("GermanyRegion");
		mapTagGround.put("germany",grounds_57);
		 
		Set<String> grounds_58 = new HashSet<String>();
		grounds_58.add("JavaLangReflect");
		mapTagGround.put("reflect",grounds_58);
		 
		Set<String> grounds_59 = new HashSet<String>();
		grounds_59.add("hasUncle");
		mapTagGround.put("uncle",grounds_59);
		 
		Set<String> grounds_60 = new HashSet<String>();
		grounds_60.add("ElicitationTool");
		grounds_60.add("InternetTool");
		grounds_60.add("TransformationTool");
		mapTagGround.put("tool",grounds_60);
		 
		Set<String> grounds_61 = new HashSet<String>();
		grounds_61.add("Genetic_Algorithms");
		mapTagGround.put("algorithms",grounds_61);
		 
		Set<String> grounds_62 = new HashSet<String>();
		grounds_62.add("StudySite");
		grounds_62.add("SecondaryStudy");
		grounds_62.add("StudyCommittee");
		grounds_62.add("SecondaryStudyProtocol");
		mapTagGround.put("study",grounds_62);
		 
		Set<String> grounds_63 = new HashSet<String>();
		grounds_63.add("CurrawongBeach");
		grounds_63.add("BondiBeach");
		mapTagGround.put("beach",grounds_63);
		 
		Set<String> grounds_64 = new HashSet<String>();
		grounds_64.add("ComputingMethodology");
		grounds_64.add("Agent-Based-Computing");
		mapTagGround.put("computing",grounds_64);
		 
		Set<String> grounds_65 = new HashSet<String>();
		grounds_65.add("Build_Tools");
		grounds_65.add("SupportToolsForFormalMethods");
		mapTagGround.put("tools",grounds_65);
		 
		Set<String> grounds_66 = new HashSet<String>();
		grounds_66.add("MedicalIntervention");
		grounds_66.add("MedicalThings");
		mapTagGround.put("medical",grounds_66);
		 
		Set<String> grounds_67 = new HashSet<String>();
		grounds_67.add("GermanyRegion");
		mapTagGround.put("germany",grounds_67);
		 
		Set<String> grounds_68 = new HashSet<String>();
		grounds_68.add("researchGroups");
		grounds_68.add("ResearchProject");
		grounds_68.add("ResearchTopic");
		grounds_68.add("ResearchGroup");
		grounds_68.add("researchInterest");
		mapTagGround.put("research",grounds_68);
		 
		Set<String> grounds_69 = new HashSet<String>();
		grounds_69.add("ConferencePaper");
		grounds_69.add("Attending-A-Conference");
		mapTagGround.put("conference",grounds_69);
		 
		Set<String> grounds_70 = new HashSet<String>();
		grounds_70.add("ContentMetadata");
		mapTagGround.put("metadata",grounds_70);
		 
		Set<String> grounds_71 = new HashSet<String>();
		grounds_71.add("LaptopComputer");
		mapTagGround.put("laptop",grounds_71);
		 
		Set<String> grounds_72 = new HashSet<String>();
		grounds_72.add("MultiMediaPrefs");
		grounds_72.add("LightSensitiveMedia");
		grounds_72.add("MediaResource");
		grounds_72.add("hasSensitiveMedia");
		grounds_72.add("JavaxMicroeditionMedia");
		mapTagGround.put("media",grounds_72);
		 
		Set<String> grounds_73 = new HashSet<String>();
		grounds_73.add("GermanWine");
		mapTagGround.put("german",grounds_73);
		 
		Set<String> grounds_74 = new HashSet<String>();
		grounds_74.add("RangeFinder");
		mapTagGround.put("finder",grounds_74);
		 
		Set<String> grounds_75 = new HashSet<String>();
		grounds_75.add("Knowledge-Modelling");
		mapTagGround.put("modelling",grounds_75);
		 
		Set<String> grounds_76 = new HashSet<String>();
		grounds_76.add("KAThroughMachineLearning");
		grounds_76.add("VirtualMachine");
		mapTagGround.put("machine",grounds_76);
		 
		Set<String> grounds_77 = new HashSet<String>();
		grounds_77.add("ITProfessional");
		grounds_77.add("EIProfessional");
		mapTagGround.put("professional",grounds_77);
		 
		Set<String> grounds_78 = new HashSet<String>();
		grounds_78.add("_Security");
		mapTagGround.put("security",grounds_78);
		 
		Set<String> grounds_79 = new HashSet<String>();
		grounds_79.add("SpeechInterfacePrefs");
		mapTagGround.put("speech",grounds_79);
		 
		Set<String> grounds_80 = new HashSet<String>();
		grounds_80.add("Programming-Environment");
		grounds_80.add("EnvironmentCondition");
		grounds_80.add("ImplementationEnvironment");
		grounds_80.add("hasEnvironment");
		mapTagGround.put("environment",grounds_80);
		 
		Set<String> grounds_81 = new HashSet<String>();
		grounds_81.add("Sending-An-Email");
		mapTagGround.put("email",grounds_81);
		 
		Set<String> grounds_82 = new HashSet<String>();
		grounds_82.add("WebMediatedPSMSelection");
		mapTagGround.put("web",grounds_82);
		 
		Set<String> grounds_83 = new HashSet<String>();
		grounds_83.add("programCommittee");
		mapTagGround.put("program",grounds_83);
		 
		Set<String> grounds_84 = new HashSet<String>();
		grounds_84.add("C_relatedToUserCompany");
		grounds_84.add("Public-Company");
		grounds_84.add("U_relatedToUserCompany");
		grounds_84.add("UserCompany");
		grounds_84.add("CompanyBuilding");
		grounds_84.add("Private-Company");
		mapTagGround.put("company",grounds_84);
		 
		Set<String> grounds_85 = new HashSet<String>();
		grounds_85.add("MultiMediaPrefs");
		grounds_85.add("LightSensitiveMedia");
		grounds_85.add("MediaResource");
		grounds_85.add("hasSensitiveMedia");
		grounds_85.add("JavaxMicroeditionMedia");
		mapTagGround.put("media",grounds_85);
		 
		Set<String> grounds_86 = new HashSet<String>();
		grounds_86.add("KnowledgeManagement");
		grounds_86.add("Knowledge-Management");
		grounds_86.add("File_Management");
		mapTagGround.put("management",grounds_86);
		 
		Set<String> grounds_87 = new HashSet<String>();
		grounds_87.add("DataMining");
		mapTagGround.put("mining",grounds_87);
		 
		Set<String> grounds_88 = new HashSet<String>();
		grounds_88.add("QualityConcept");
		grounds_88.add("ServiceQoSConcept");
		grounds_88.add("QoSConcept");
		grounds_88.add("S_relatedToServiceContentConcept");
		grounds_88.add("ContextConcept");
		grounds_88.add("ContentConcept");
		grounds_88.add("N_relatedToNetworkQoSConcept");
		grounds_88.add("AmigoConcept");
		grounds_88.add("NetworkQoSConcept");
		grounds_88.add("ServiceContentConcept");
		grounds_88.add("Q_relatedToNetworkQoSConcept");
		grounds_88.add("CC_relatedToServiceContentConcept");
		grounds_88.add("S_relatedToServiceQoSConcept");
		grounds_88.add("DomainConcept");
		grounds_88.add("Q_relatedToServiceQoSConcept");
		mapTagGround.put("concept",grounds_88);
		 
		Set<String> grounds_89 = new HashSet<String>();
		grounds_89.add("ServiceModel");
		grounds_89.add("hasServiceModel");
		mapTagGround.put("model",grounds_89);
		 
		Set<String> grounds_90 = new HashSet<String>();
		grounds_90.add("PaperLibrary");
		grounds_90.add("ConferencePaper");
		grounds_90.add("WorkshopPaper");
		mapTagGround.put("paper",grounds_90);
		 
		Set<String> grounds_91 = new HashSet<String>();
		grounds_91.add("_Security");
		mapTagGround.put("security",grounds_91);
		 
		Set<String> grounds_92 = new HashSet<String>();
		grounds_92.add("Language-Engineering");
		mapTagGround.put("engineering",grounds_92);
		 
		Set<String> grounds_93 = new HashSet<String>();
		grounds_93.add("N_relatedToNetworkQoSConcept");
		grounds_93.add("NetworkQoSConcept");
		grounds_93.add("Q_relatedToNetworkQoSConcept");
		grounds_93.add("NetworkNetworkingTechnology");
		grounds_93.add("_Network");
		grounds_93.add("NT_relatedToNetworkNetworkingTechnology");
		grounds_93.add("NetworkResource");
		grounds_93.add("NetworkContext");
		grounds_93.add("N_relatedToNetworkNetworkingTechnology");
		mapTagGround.put("network",grounds_93);
		 
		Set<String> grounds_94 = new HashSet<String>();
		grounds_94.add("StreetPhotography");
		mapTagGround.put("photography",grounds_94);
		 
		Set<String> grounds_95 = new HashSet<String>();
		grounds_95.add("OnlinePublication");
		grounds_95.add("onlineVersionOf");
		grounds_95.add("onlineVersion");
		mapTagGround.put("online",grounds_95);
		 
		Set<String> grounds_96 = new HashSet<String>();
		grounds_96.add("researchGroups");
		mapTagGround.put("groups",grounds_96);
		 
		Set<String> grounds_97 = new HashSet<String>();
		grounds_97.add("Charitable-Organization");
		grounds_97.add("Government-Organization");
		grounds_97.add("Profit-Organization");
		grounds_97.add("Learning-Centred-Organization");
		grounds_97.add("hasSubOrganization");
		grounds_97.add("Educational-Organization");
		grounds_97.add("roleInEncompasingOrganization");
		grounds_97.add("Non-Profit-Organization");
		grounds_97.add("Industrial-Organization");
		grounds_97.add("Small-Or-Medium-Sized-Organization");
		grounds_97.add("subOrganizationOf");
		grounds_97.add("Political-Organization");
		grounds_97.add("Higher-Educational-Organization");
		grounds_97.add("organizationType");
		mapTagGround.put("organization",grounds_97);
		 
		Set<String> grounds_98 = new HashSet<String>();
		grounds_98.add("mappingAuthorityName");
		grounds_98.add("mappingAuthority");
		grounds_98.add("RegisteringAuthority");
		mapTagGround.put("authority",grounds_98);
		 
		Set<String> grounds_99 = new HashSet<String>();
		grounds_99.add("ComputerGraphics");
		mapTagGround.put("graphics",grounds_99);
		 
		Set<String> grounds_100 = new HashSet<String>();
		grounds_100.add("CMFRegimeTypes");
		grounds_100.add("DrugRegimeTypes");
		grounds_100.add("AnastrazoleRegimeTypes");
		grounds_100.add("ChemoTxRegimeTypes");
		grounds_100.add("SymptomTypes");
		grounds_100.add("HormoneRARegimeTypes");
		grounds_100.add("AnthracyclineChemoTxRegimeTypes");
		grounds_100.add("PolyChemoTxRegimeTypes");
		grounds_100.add("CancerTypes");
		grounds_100.add("DiseaseTypes");
		grounds_100.add("NonAnthracyclineChemoTxRegimeTypes");
		grounds_100.add("ChemoTamRegimeTypes");
		mapTagGround.put("types",grounds_100);
		 
		Set<String> grounds_101 = new HashSet<String>();
		grounds_101.add("BlackAndWhiteFilm");
		grounds_101.add("MediumFormatFilm");
		grounds_101.add("PrintFilm");
		grounds_101.add("LithFilm");
		grounds_101.add("ColourFilm");
		grounds_101.add("SlideFilm");
		grounds_101.add("x5inFilm");
		grounds_101.add("RollFilm");
		grounds_101.add("SmallFormatFilm");
		grounds_101.add("LargeFormatFilm");
		grounds_101.add("InfraRedFilm");
		mapTagGround.put("film",grounds_101);
		 
		Set<String> grounds_102 = new HashSet<String>();
		grounds_102.add("hasEMail");
		mapTagGround.put("mail",grounds_102);
		 
		Set<String> grounds_103 = new HashSet<String>();
		grounds_103.add("SecondaryStudyProtocol");
		grounds_103.add("ProtocolChange");
		mapTagGround.put("protocol",grounds_103);
		 
		Set<String> grounds_104 = new HashSet<String>();
		grounds_104.add("A_relatedToUserApplication");
		grounds_104.add("U_relatedToUserApplication");
		grounds_104.add("applicationAreas");
		grounds_104.add("UserApplication");
		grounds_104.add("ApplicationResource");
		mapTagGround.put("application",grounds_104);
		 
		Set<String> grounds_105 = new HashSet<String>();
		grounds_105.add("LearningApprenticeSystems");
		grounds_105.add("Organizational-Learning");
		grounds_105.add("ReinforcementLearning");
		grounds_105.add("KAThroughMachineLearning");
		mapTagGround.put("learning",grounds_105);
		 
		Set<String> grounds_106 = new HashSet<String>();
		grounds_106.add("Recorded-Audio");
		mapTagGround.put("audio",grounds_106);
		 
		Set<String> grounds_107 = new HashSet<String>();
		grounds_107.add("C_relatedToUserCommunity");
		grounds_107.add("U_relatedToUserCommunity");
		grounds_107.add("UserCommunity");
		mapTagGround.put("community",grounds_107);
		 
		Set<String> grounds_108 = new HashSet<String>();
		grounds_108.add("MultimodalInterfacePrefs");
		grounds_108.add("SpeechInterfacePrefs");
		grounds_108.add("InterfacePrefs");
		mapTagGround.put("interface",grounds_108);
		 
		Set<String> grounds_109 = new HashSet<String>();
		grounds_109.add("HomeInformationAndEntertainment");
		grounds_109.add("Games_Entertainment");
		mapTagGround.put("entertainment",grounds_109);
		 
		Set<String> grounds_110 = new HashSet<String>();
		grounds_110.add("Email-Message");
		mapTagGround.put("message",grounds_110);
		 
		Set<String> grounds_111 = new HashSet<String>();
		grounds_111.add("ManualFocus");
		mapTagGround.put("manual",grounds_111);
		 
		Set<String> grounds_112 = new HashSet<String>();
		grounds_112.add("_Authentication");
		grounds_112.add("Inadequate-Authentication");
		mapTagGround.put("authentication",grounds_112);
		 
		Set<String> grounds_113 = new HashSet<String>();
		grounds_113.add("ImplementationEnvironment");
		mapTagGround.put("implementation",grounds_113);
		 
		Set<String> grounds_114 = new HashSet<String>();
		grounds_114.add("S_relatedToActivityService");
		grounds_114.add("ServiceQoSConcept");
		grounds_114.add("D_relatedToDeviceService");
		grounds_114.add("S_relatedToServiceContentConcept");
		grounds_114.add("hasServiceProfile");
		grounds_114.add("ActivityService");
		grounds_114.add("U_relatedToUserService");
		grounds_114.add("associatedToService");
		grounds_114.add("A_relatedToActivityService");
		grounds_114.add("ServicePrefs");
		grounds_114.add("ServiceContentConcept");
		grounds_114.add("deploysService");
		grounds_114.add("CC_relatedToServiceContentConcept");
		grounds_114.add("S_relatedToDeviceService");
		grounds_114.add("ServiceGrounding");
		grounds_114.add("DeviceService");
		grounds_114.add("hasServiceGrounding");
		grounds_114.add("ServiceContainer");
		grounds_114.add("UserService");
		grounds_114.add("ServiceProfile");
		grounds_114.add("providesService");
		grounds_114.add("ServiceModel");
		grounds_114.add("ServiceContext");
		grounds_114.add("usesService");
		grounds_114.add("Civil-Service");
		grounds_114.add("S_relatedToServiceQoSConcept");
		grounds_114.add("Q_relatedToServiceQoSConcept");
		grounds_114.add("S_relatedToUserService");
		grounds_114.add("hasServiceModel");
		mapTagGround.put("service",grounds_114);
		 
		Set<String> grounds_115 = new HashSet<String>();
		grounds_115.add("usesIODevice");
		grounds_115.add("IODevice");
		grounds_115.add("D_relatedToDevicePlatform");
		grounds_115.add("D_relatedToDeviceService");
		grounds_115.add("DeviceContent");
		grounds_115.add("C_relatedToDeviceContent");
		grounds_115.add("OutputDevice");
		grounds_115.add("DeviceCapability");
		grounds_115.add("DevicePrefs");
		grounds_115.add("hasDeviceContext");
		grounds_115.add("P_relatedToDevicePlatform");
		grounds_115.add("DeviceContext");
		grounds_115.add("InputDevice");
		grounds_115.add("D_relatedToUserDevice");
		grounds_115.add("D_relatedToDeviceContent");
		grounds_115.add("DevicePlatform");
		grounds_115.add("S_relatedToDeviceService");
		grounds_115.add("UserDevice");
		grounds_115.add("Transportation-Device");
		grounds_115.add("hasDeviceCapabilities");
		grounds_115.add("hasDevice");
		grounds_115.add("DeviceService");
		grounds_115.add("U_relatedToUserDevice");
		mapTagGround.put("device",grounds_115);
		 
		Set<String> grounds_116 = new HashSet<String>();
		grounds_116.add("Edited-Book");
		grounds_116.add("ArticleInBook");
		mapTagGround.put("book",grounds_116);
		 
		Set<String> grounds_117 = new HashSet<String>();
		grounds_117.add("JDK1.1JavaUtil");
		grounds_117.add("JDK1.1JavaIO");
		grounds_117.add("JavaRenderingEngine");
		grounds_117.add("JavaAWT");
		grounds_117.add("JavaText");
		grounds_117.add("JDKJavaLang");
		grounds_117.add("JDKJavaUtil");
		grounds_117.add("CLDCJavaUtil");
		grounds_117.add("JavaAWTEvent");
		grounds_117.add("JavaLibrary");
		grounds_117.add("JavaLangRef");
		grounds_117.add("JavaLangReflect");
		grounds_117.add("hasPersonalJavaVersion");
		grounds_117.add("JavaJar");
		grounds_117.add("CLDCJavaLang");
		grounds_117.add("JavaIO");
		grounds_117.add("JavaUtilZip");
		grounds_117.add("JavaRMI");
		grounds_117.add("JavaVM");
		grounds_117.add("StreamJavaIO");
		grounds_117.add("JavaMath");
		grounds_117.add("JavaSQL");
		grounds_117.add("JavaNet");
		grounds_117.add("JavaBeans");
		grounds_117.add("PersonalJava");
		mapTagGround.put("java",grounds_117);
		 
		Set<String> grounds_118 = new HashSet<String>();
		grounds_118.add("domainInterGroupSocialRelation");
		grounds_118.add("groupType");
		grounds_118.add("InterGroupSocialRelation");
		grounds_118.add("EntityReferenceGroupVocabulary");
		grounds_118.add("targetInterGroupSocialRelation");
		grounds_118.add("headOfGroup");
		grounds_118.add("targetGroupSocialRelation");
		grounds_118.add("Terrorist_Group");
		grounds_118.add("ResearchGroup");
		grounds_118.add("Baker_Street_Prayer_Group");
		grounds_118.add("GroupSocialRelation");
		grounds_118.add("domainGroupSocialRelation");
		mapTagGround.put("group",grounds_118);
		
		return mapTagGround;
	}

}
