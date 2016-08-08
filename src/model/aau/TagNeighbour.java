package aau;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;



@Entity 
@Name("tagNeighbour")
@Scope(ScopeType.CONVERSATION)
@Table(name="tag_tagneighbours")
public class TagNeighbour {

	@GeneratedValue
	@Id
	private long tag_id;
	private String element;
	
	private float neighbourFrequency;
	
	
	public long getTag_id() {
		return tag_id;
	}

	public void setTag_id(long tagId) {
		tag_id = tagId;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public float getNeighbourFrequency() {
		return neighbourFrequency;
	}

	public void setNeighbourFrequency(float neighbourFrequency) {
		this.neighbourFrequency = neighbourFrequency;
	}

	
	
}
