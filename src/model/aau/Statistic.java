package aau;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;



@Entity 
@Name("statistic")
@Scope(ScopeType.CONVERSATION)
@Table(name="STATISTIC", uniqueConstraints = {@UniqueConstraint(columnNames={"FIRSTRESOURCE_ID", "SECONDRESOURCE_ID"})}
)

public class Statistic implements Comparable<Statistic>,Serializable {

	public Statistic() {
	}
	
	public Statistic(Resource firstResource, Resource secondResource) {
		super();
		this.firstResource = firstResource;
		this.secondResource = secondResource;
	}

	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id
	private long id;

	private float comparasionValue;
	
	private float consineSimilarity;
	
	private int cosineSimilarityAmount;

	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private Resource firstResource;
	
	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private Resource secondResource;

	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private SemanticSimilarity semanticSimilarity;
	
	
	public void addCosineSimilarity(int i){
		this.cosineSimilarityAmount = cosineSimilarityAmount + i;
	}
		
	public float getComparasionValue() {
		return comparasionValue;
	}

	public void setComparasionValue(float comparasionValue) {
		this.comparasionValue = comparasionValue;
	}

	public Resource getFirstResource() {
		return firstResource;
	}

	public void setFirstResource(Resource firstResource) {
		this.firstResource = firstResource;
	 
	}
	
	public Resource getSecondResource() {
		return secondResource;
	}

	public void setSecondResource(Resource secondResource) {
		this.secondResource = secondResource;
	}

	
	public SemanticSimilarity createNewSemanticSimilarity(){
		if (semanticSimilarity==null) {
			this.semanticSimilarity = new SemanticSimilarity();
		}
		return semanticSimilarity;
	}
	
	
    public int compareTo(Statistic kiWiStatistic) {
        if (this.comparasionValue == kiWiStatistic.getComparasionValue())
            return 0;
        else if (this.comparasionValue < kiWiStatistic.getComparasionValue())
            return 1;
        else
            return -1;
    }

    public SemanticSimilarity getSemanticSimilarity() {
		return semanticSimilarity;
	}

	public void setSemanticSimilarity(SemanticSimilarity semanticSimilarity) {
		this.semanticSimilarity = semanticSimilarity;
	}

	public float getConsineSimilarity() {
		return consineSimilarity;
	}

	public void setConsineSimilarity(float consineSimilarity) {
		this.consineSimilarity = consineSimilarity;
	}

	public int getCosineSimilarityAmount() {
		return cosineSimilarityAmount;
	}

	public void setCosineSimilarityAmount(int cosineSimilarityAmount) {
		this.cosineSimilarityAmount = cosineSimilarityAmount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
