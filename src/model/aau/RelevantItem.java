package aau;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;



@Entity 
@Name("relevantItem")
@Scope(ScopeType.CONVERSATION)
@Table(name="RelevantItem")
public class RelevantItem {

	@GeneratedValue
	@Id
	private long id;

	private String query;
	
	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.PERSIST)
	private List<Resource> resources;
	
	public RelevantItem(){
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
}
