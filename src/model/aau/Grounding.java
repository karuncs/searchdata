package aau;

public class Grounding {

	private SemanticExpansion semanticExpansionFirst;
	
	private Tag tag;

	public SemanticExpansion getSemanticExpansionFirst() {
		return semanticExpansionFirst;
	}

	public void setSemanticExpansionFirst(SemanticExpansion semanticExpansionFirst) {
		this.semanticExpansionFirst = semanticExpansionFirst;
	}

	public Grounding(SemanticExpansion semanticExpansionFirst, Tag tag) {
		super();
		this.semanticExpansionFirst = semanticExpansionFirst;
		this.tag = tag;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}
}
