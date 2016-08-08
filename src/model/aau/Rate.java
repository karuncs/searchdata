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
@Name("rate")
@Scope(ScopeType.CONVERSATION)
@Table(name="rate")
public class Rate {
	
	
	
	@GeneratedValue
	@Id
	private long id;
	
	public Rate(){
	}
	
	private float value;

	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private Resource resource;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private User rateAuthor;


	public Rate(float value, User rateAuthor) {
		super();
		this.value = value;
		this.rateAuthor = rateAuthor;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public User getRateAuthor() {
		return rateAuthor;
	}

	public void setRateAuthor(User rateAuthor) {
		this.rateAuthor = rateAuthor;
	}
	
	
	public Resource getResource() {
		return resource;
	}


	public void setResource(Resource resource) {
		this.resource = resource;
	}	
}
