package aau;

import java.util.HashSet;
import java.util.Set;

public class SemanticGraph {

	public Set<String> subjects;
	
	public Set<String> predicates;
	
	public Set<String> objects;

	public Set<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<String> subjects) {
		this.subjects = subjects;
	}

	public Set<String> getPredicates() {
		return predicates;
	}

	public void setPredicates(Set<String> predicates) {
		this.predicates = predicates;
	}

	public Set<String> getObjects() {
		return objects;
	}

	public void setObjects(Set<String> objects) {
		this.objects = objects;
	}
	
	
	/**
	 * @param subject
	 */
	public void addSubjects(String subject) {
		if (this.getSubjects()==null) {
			subjects = new HashSet<String>();
			subjects.add(subject);
			setSubjects(subjects);
		}else {
			this.getSubjects().add(subject);
		}
	}
	/**
	 * @param object
	 */
	public void addObjects(String object) {
		if (this.getObjects()==null) {
			objects = new HashSet<String>();
			objects.add(object);
			setObjects(objects);
		}else {
			this.getObjects().add(object);
		}
	}
	/**
	 * @param predicate
	 */
	public void addPredicates(String predicate) {
		if (this.getPredicates()==null) {
			predicates = new HashSet<String>();
			predicates.add(predicate);
			setPredicates(predicates);
		}else {
			this.getPredicates().add(predicate);
		}
	}
}
