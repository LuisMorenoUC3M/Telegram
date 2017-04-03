package com.telegram.entities.wikipedia;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="api")
public class Api {
	
	private Continue Continue;
	private Query query;

	@XmlElement(name="query")
	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	@XmlElement(name="continue")
	public Continue getContinue() {
		return Continue;
	}

	public void setContinue(Continue continue1) {
		Continue = continue1;
	}
	
	

}
