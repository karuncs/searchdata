package aau;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

public class IndexField {

	private String name;
	private String dbName;
	private Field.Store fieldStore; 
	private Field.Index fieldIndex;
	
	private boolean isTermVectorSet;
	private Field.TermVector fieldTermVector;


	
	public IndexField(String name, String dbName, Store fieldStore, Index fieldIndex) {
		this.name = name;
		this.dbName = dbName;
		this.fieldStore = fieldStore;
		this.fieldIndex = fieldIndex;
		this.isTermVectorSet = false;
	}
	
	public IndexField(String name, String dbName, Store fieldStore, Index fieldIndex, Field.TermVector fieldTermVector) {
		this.name = name;
		this.dbName = dbName;
		this.fieldStore = fieldStore;
		this.fieldIndex = fieldIndex;
		this.fieldTermVector = fieldTermVector;
		this.isTermVectorSet = true;
	}		
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Field.Store getFieldStore() {
		return fieldStore;
	}
	public void setFieldStore(Field.Store fieldStore) {
		this.fieldStore = fieldStore;
	}
	public Field.Index getFieldIndex() {
		return fieldIndex;
	}
	public void setFieldIndex(Field.Index fieldIndex) {
		this.fieldIndex = fieldIndex;
	}

	public String getDbName() {
		return dbName;
	}

	public boolean isTermVectorSet() {
		return isTermVectorSet;
	}

	public Field.TermVector getFieldTermVector() {
		return fieldTermVector;
	}	
}
