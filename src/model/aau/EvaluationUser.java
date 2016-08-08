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
@Name("evaluationUser")
@Scope(ScopeType.CONVERSATION)
@Table(name="EvaluationUser")
public class EvaluationUser {

	@GeneratedValue
	@Id
	private long id;
	
	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.PERSIST)
	private User user;

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
}
