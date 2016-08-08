package aau;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;



@Entity 
@Name("index_term")
@Scope(ScopeType.CONVERSATION)
@Table(name="index_term")
public class IndexTerm {
	
	@GeneratedValue
	@Id
	private long id;

	private String term;
	
	private Float termFrequency;
	
	private Float docFrequency;	
	
	public long getId() {
		return id;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Float getTermFrequency() {
		return termFrequency;
	}

	public void setTermFrequency(Float termFrequency) {
		this.termFrequency = termFrequency;
	}

	public Float getDocFrequency() {
		return docFrequency;
	}

	public void setDocFrequency(Float docFrequency) {
		this.docFrequency = docFrequency;
	}

	
}
