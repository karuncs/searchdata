package aau;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionOfElements;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import semantic.Digg;
import semantic.Flickr;
import sesame.IConstant;
import sesame.Sesame;


@Entity 
@Name("resource")
@Scope(ScopeType.CONVERSATION)
@Table(name="RESOURCES")
public class Resource {

	@GeneratedValue
	@Id
	private long id;
	
	private String uri;
	
	@Column(columnDefinition="TEXT")
	private String title;
	
	@Column(columnDefinition="TEXT")
	private String description;
	
	@Column(columnDefinition="TEXT")	
	private String link;	

	private String type;
	
	@OneToMany(mappedBy="resource",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Tag> tags;
	
	private String tagsText;
	
	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Rate> rates;
	
	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Category> categories;
	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@CollectionOfElements
	private Set<String> contexts;
	
	//private Set<SemanticExpansion> semanticExpansions;
	
	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private User author;
	
	@Column(nullable = true, columnDefinition="float default 0")
	private float generalTagFrequency;
	
	@Column(nullable = true, columnDefinition="float default 0")
	private float generalTagWeight;

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
	public boolean hasTag(Tag tag) {
		return tags.contains(tag);
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void addTag(String tagName){
		Tag tag = new Tag(tagName,author);
		tag.setWeight(0f);
		if (this.getTags()==null){
			Set<Tag> newTags = new HashSet<Tag>();
			newTags.add(tag);
			setTags(newTags);
		}else{
			getTags().add(tag);
		}
	}
	
	public static Set<Resource> getResourcesWithContainsTag(Set<Resource> kiwiAllResources, Tag tag) {
		Set<Resource> resources = new HashSet<Resource>();
		for (Resource kiWiResource : resources) {
			if (kiWiResource.hasTag(tag)){
				resources.add(kiWiResource);
			}
		}
		return resources;
	}
	
	/**
	 * It builds a string builder for all strings 
	 * @return
	 */
	public String getTagString(){
		StringBuilder tagString = new StringBuilder();
		Set<String> singleTag = new HashSet<String>();	
		if (tags!=null) {
			for (Tag tag : tags) {
				if (singleTag!=null && !singleTag.equals("")
						&& !singleTag.contains(tag.getTagName())
						&& !tag.getTagName().equals(IConstant.INVALID_TAG_BOOKMARK)
						&& !tag.getTagName().equals(IConstant.INVALID_TAG_BOOKMARK_MENU)
						&& !tag.getTagName().equals(IConstant.INVALID_TAG_IMPORTED)
						&& !tag.getTagName().equals(IConstant.INVALID_TAG_WEB20)
						) {
					singleTag.add(tag.getTagName());
					tagString.append(tag.getTagName());
					tagString.append(" ");
				}
			}
		}
		
		try {
			if (tags.size()>0 && tagString.length()>1 && (tagString.lastIndexOf(",")==tagString.length()-1)){
				tagString = new StringBuilder(tagString.substring(0, tagString.lastIndexOf(",")));
				//System.out.println("*******   "+tagString);
			}
		} catch (StringIndexOutOfBoundsException e) {
			tagString = new StringBuilder(tagString.substring(0, 5));
		}

		return tagString.toString();
	}
	
	
	
	public String getSemanticTagString(){

		
		Map<String, Set<String>> mapTagGround = Digg.loaDiggOntologyTagGround();
		//Map<String, Set<String>> mapTagGround = Flickr.loadFlickrOntologyTagGround();
		//Set<String> semTa = mapTagGround.keySet();
		
		// Mapping tag to ontology
		//Map<String, String> mapTagContext = Flickr.getContextMapKey();
		Map<String, String> mapTagContext = Digg.getContextMapKey();
		Set<String> semTa = mapTagContext.keySet();

		StringBuilder tagString = new StringBuilder();
		Set<String> singleTags = new HashSet<String>();
		if (tags!=null) {
			for (Tag tag : tags) {
				String pureTag = Sesame.extractBrackets(tag.getTagName().toString().toLowerCase());
				if (singleTags!=null && !singleTags.equals("")
						&& !singleTags.contains(pureTag)
						&& !pureTag.equals(IConstant.INVALID_TAG_BOOKMARK)
						&& !pureTag.equals(IConstant.INVALID_TAG_BOOKMARK_MENU)
						&& !pureTag.equals(IConstant.INVALID_TAG_IMPORTED)
						&& !pureTag.equals(IConstant.INVALID_TAG_WEB20)
						&& !Flickr.stopTags().contains(pureTag)
				) {
					
					singleTags.add(tag.getTagName());
					//System.out.println(pureTag);
					if (semTa.contains(pureTag)) {
						//System.out.println("....................has semantic");	
						String ontologyContext = mapTagContext.get(pureTag);
						for (String semanticTag : mapTagGround.get(ontologyContext)) {
							if (!singleTags.contains(semanticTag.toLowerCase())){
								String ontology = ontologyContext.substring(ontologyContext.lastIndexOf("_")+1,ontologyContext.length());
								addContext(ontology);
								//addSemanticExpansion(new SemanticExpansion(tag,this,ontology,semanticTag.toLowerCase()));
								//System.out.println("ontology "+ontology);								
								//System.out.println("semantic terms.............................................."+semanticTag.toLowerCase());								
								singleTags.add(semanticTag.toLowerCase());
								
								
							}
						}
					}
				}
			}
		}

		if (singleTags!=null && !singleTags.isEmpty() ){
			for (String semTag : singleTags) {
					tagString.append(semTag);
					tagString.append(" ");
			}
		}
		
//		if (tags!=null && tags.size()>0 && (tagString.lastIndexOf(" ")==tagString.length()-1)){
//			
//			System.out.println(tagString.substring(0, tagString.lastIndexOf(" ")));
//			//tagString = new StringBuilder(tagString.substring(0, tagString.lastIndexOf(" ")));
//			
//			//System.out.println("*******   "+tagString);
//		}
		return tagString.toString().trim();
	}

	public Resource(String desc, Set<Tag> tags, Set<Rate> rates, User author) {
		super();
		this.description = desc;
		this.tags = tags;
		this.rates = rates;
		this.author = author;
	}
	
	public Resource(String desc, Set<Tag> tags, User author) {
		super();
		this.description = desc;
		this.tags = tags;

		this.author = author;
	}
	
	public Resource() {

	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public float getGeneralTagFrequency() {
		float gtf = 0f;
		if (getTags()!=null && !getTags().isEmpty()) {
			for (Tag tag : getTags()) {
				gtf = (gtf + tag.getTagFrequency())/tags.size();
			}
		}
		generalTagFrequency = gtf;
		return generalTagFrequency;
	}

	public void setGeneralTagFrequency(float generalTagFrequency) {
		this.generalTagFrequency = generalTagFrequency;
	}

	public float getGeneralTagWeight() {
		float gtw = 0f;
		if (getTags()!=null && !getTags().isEmpty()) {		
			for (Tag tag : getTags()) {
				gtw = (gtw + tag.getWeight())/tags.size();
			}
		}		
		generalTagWeight = gtw;
		return generalTagWeight;
	}

	public void setGeneralTagWeight(float generalTagWeight) {
		this.generalTagWeight = generalTagWeight;
	}

	public Set<String> getContexts() {
		return contexts;
	}

	public void setContexts(Set<String> contexts) {
		this.contexts = contexts;
	}
	
	/**
	 * @param ontologyContext
	 */
	public void addContext(String ontologyContext) {
		if (this.getContexts()==null) {
			Set<String> cont = new HashSet<String>();
			cont.add(ontologyContext);
			setContexts(cont);
		}else {
			if (!getContexts().contains(ontologyContext)) {
				getContexts().add(ontologyContext);
			} 
		}
	}
//	
//	/**
//	 * @param ontologyContext
//	 */
//	public void addSemanticExpansion(SemanticExpansion semanticExpansion) {
//		if (this.getSemanticExpansions()==null) {
//			Set<SemanticExpansion> groundings = new HashSet<SemanticExpansion>();
//			groundings.add(semanticExpansion);
//			setSemanticExpansions(groundings);
//		}else {
//			if (!getSemanticExpansions().contains(semanticExpansion)) {
//				getSemanticExpansions().add(semanticExpansion);
//			} 
//		}
//	}
//
//	public Set<SemanticExpansion> getSemanticExpansions() {
//		return semanticExpansions;
//	}
//
//	public void setSemanticExpansions(Set<SemanticExpansion> semanticExpansions) {
//		this.semanticExpansions = semanticExpansions;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Rate> getRates() {
		return rates;
	}

	public void setRates(Set<Rate> rates) {
		this.rates = rates;
	}

	public String getTagsText() {
		return tagsText;
	}

	public void setTagsText(String tagsText) {
		this.tagsText = tagsText;
	}	
	
}
