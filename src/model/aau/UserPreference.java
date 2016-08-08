package aau;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;



@Entity 
@Name("UserPreference")
@Scope(ScopeType.CONVERSATION)
@Table(name="UserPreference")
public class UserPreference {
	
	private static String SOURCE_TAG = "TAG";
	
	private static String SOURCE_RATE = "RATE";
	
	private static String SOURCE_BOOKMARK = "BOOKMARK";
	
	private static String SOURCE_VISIT = "VISIT";
	
	private static String SOURCE_PROFILE = "PROFILE";


	@GeneratedValue
	@Id
	private long id;

	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private User user;
	
	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private Tag tag;
	
	float tagFrequency;
	
	private String sourceType;	

	public UserPreference(){
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public float getTagFrequency() {
		return tagFrequency;
	}

	public void setTagFrequency(float tagFrequency) {
		this.tagFrequency = tagFrequency;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

}
