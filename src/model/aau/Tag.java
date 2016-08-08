package aau;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionOfElements;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;



@Entity 
@Name("tag")
@Scope(ScopeType.CONVERSATION)
@Table(name="tag")
public class Tag {

	@GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="tag_id_seq", allocationSize=1)
	@Id
	private long id;
	
	public Tag(){
	}
	
	private String uri;
	
	private String tagName;
	private String tagNeighbourName;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private Resource resource;

	@CollectionOfElements
	private List<String> tagNeighbours;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Tag> tagSimblings;
	
	@ManyToOne()
	private Tag tagRoot;
	
	private Boolean hasNeighbours;
	
	private Long resourceId;
	
	@Transient
	private Long tagRootId; //Non-persistent field. If you want to persist, use 
	
	private Float weight;
	
	private Float tagFrequency;
	private Float neighbourFrequency;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private User tagAuthor;

	public String getTagName() {
		return tagName;
	}
	public String getTagNeighbourName() {
		return tagNeighbourName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public float getWeight() {
		return weight;
	}

	public float getNeighbourFrequency() {
		return neighbourFrequency;
	}

	public void setNeighbourFrequency(float neighbourFrequency) {
		this.neighbourFrequency = neighbourFrequency;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public User getTagAuthor() {
		return tagAuthor;
	}

	public void setTagAuthor(User tagAuthor) {
		this.tagAuthor = tagAuthor;
	}

	public Tag(String tagName, User tagAuthor) {
		super();
		this.tagName = tagName;
		this.tagAuthor = tagAuthor;
		this.tagFrequency = new Float(0);
		this.weight = new Float(0);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
		
	}

	public float getTagFrequency() {
		return tagFrequency;
	}

	public void setTagFrequency(float tagFrequency) {
		this.tagFrequency = tagFrequency;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if(((Tag)obj).getTagName().equals(this.tagName)){
			isEqual = true;
		}
		return isEqual;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public List<String> getTagNeighbours() {
		return tagNeighbours;
	}

	public void setTagNeighbours(List<String> tagNeighbours) {
		this.tagNeighbours = tagNeighbours;
	}
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public List<Tag> getTagSimblings() {
		return tagSimblings;
	}
	public void setTagSimblings(List<Tag> tagSimblings) {
		this.tagSimblings = tagSimblings;
	}
	public Tag getTagRoot() {
		return tagRoot;
	}
	public void setTagRoot(Tag tagRoot) {
		this.tagRoot = tagRoot;
	}
	public boolean HasNeighbours() {
		return hasNeighbours;
	}
	public void setHasNeighbours(boolean hasNeighbours) {
		this.hasNeighbours = hasNeighbours;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public Long getTagRootId() {
		return tagRootId;
	}
	public void setTagRootId(Long tagRootId) {
		this.tagRootId = tagRootId;
	}
}
