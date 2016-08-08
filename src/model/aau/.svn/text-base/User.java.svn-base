package aau;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CollectionOfElements;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;



@Entity 
@Name("user")
@Scope(ScopeType.CONVERSATION)
@Table(name="USERS", uniqueConstraints = {@UniqueConstraint(columnNames={"NAME"})}
)
public class User {
	
	
	@GeneratedValue
	@Id
	private long id;

	@Column(columnDefinition="TEXT")	
	private String uri;

	@Column(columnDefinition="TEXT")
	private String name;
	
	private String email;
	
	@CollectionOfElements
	private Set<String> topTags;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	public User() {
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Set<String> getTopTags() {
		return topTags;
	}

	public void setTopTags(Set<String> topTags) {
		this.topTags = topTags;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	/**
	 * It builds a string builder for all strings 
	 * @return
	 */
	public String getTagString(){
		StringBuilder tagString = new StringBuilder();
		Set<String> singleTag = new HashSet<String>();	
		if (topTags!=null) {
			for (String tagName : topTags) {
				if (singleTag!=null && !singleTag.equals("") && !singleTag.contains(tagName)) {
					singleTag.add(tagName);
					tagString.append(tagName);
					tagString.append(" ");
				}
			}
		}
		
		try {
			if (topTags.size()>0 && tagString.length()>1 && (tagString.lastIndexOf(",")==tagString.length()-1)){
				tagString = new StringBuilder(tagString.substring(0, tagString.lastIndexOf(",")));
				//System.out.println("*******   "+tagString);
			}
		} catch (StringIndexOutOfBoundsException e) {
			tagString = new StringBuilder(tagString.substring(0, 5));
		}

		return tagString.toString();
	}	
}
