package com.lti.online_exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Document")
public class Document {
	@Id
	@Column(name="Doc_Name")
	private String docName;
	
	@Column(name="Doc_Uri")
	private String docUri;
	
    public Document(String docName, String docUri) {                               
		this.docName = docName;
		this.docUri = docUri;
	}

	@Override
	public String toString() {
		return "Document [docName=" + docName + ", docUri=" + docUri + "]";
	}

	public Document() {
	
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocUri() {
		return docUri;
	}

	public void setDocUri(String docUri) {
		this.docUri = docUri;
	}

}
