package aau;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionOfElements;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;


@Entity 
@Name("semanticSimilarity")
@Scope(ScopeType.CONVERSATION)
@Table(name="semanticSimilarity")
public class SemanticSimilarity  implements Comparable<SemanticSimilarity>,Serializable {

	@GeneratedValue
	@Id
	private long id;

	private float semanticSimilarityScore;
	
	private int wordNetAmount;
	
	private int deliciousAmount;
	
	private int ontoAmount;

	
	
	@CollectionOfElements
	private Map<String, String> semanticRelations;
	

	public void addWordNetMatching(int i){
		this.wordNetAmount = wordNetAmount +i;
	}
	
	public void addDeliciousMatching(int i){
		this.deliciousAmount = deliciousAmount + i;
	}
	
	public void addOntoMatching(int i){
		this.ontoAmount = ontoAmount + i;
	}
	
	public int getTotalAmountMatching() {
		return this.getOntoAmount()+this.getWordNetAmount()+this.getDeliciousAmount();
	}

	public float getSemanticSimilarityScore() {
		return semanticSimilarityScore;
	}

	public void setSemanticSimilarityScore(float semanticSimilarityScore) {
		this.semanticSimilarityScore = semanticSimilarityScore;
	}

	public int getWordNetAmount() {
		return wordNetAmount;
	}

	public void setWordNetAmount(int wordNetAmount) {
		this.wordNetAmount = wordNetAmount;
	}

	public int getDeliciousAmount() {
		return deliciousAmount;
	}

	public void setDeliciousAmount(int deliciousAmount) {
		this.deliciousAmount = deliciousAmount;
	}

	public int getOntoAmount() {
		return ontoAmount;
	}

	public void setOntoAmount(int ontoAmount) {
		this.ontoAmount = ontoAmount;
	}

	public Map<String, String> getSemanticRelations() {
		return semanticRelations;
	}

	public void setSemanticRelations(Map<String, String> semanticRelations) {
		this.semanticRelations = semanticRelations;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
    public int compareTo(SemanticSimilarity kiWiStatistic) {
        if (this.semanticSimilarityScore == kiWiStatistic.getSemanticSimilarityScore())
            return 0;
        else if (this.semanticSimilarityScore < kiWiStatistic.getSemanticSimilarityScore())
            return 1;
        else
            return -1;
    }

	
}
