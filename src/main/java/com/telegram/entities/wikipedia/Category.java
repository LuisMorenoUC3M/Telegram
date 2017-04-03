package com.telegram.entities.wikipedia;

import javax.xml.bind.annotation.XmlAttribute;

public class Category {

	private String ns;
	private String title;

	@XmlAttribute(name="ns")
	public String getNs() {
		return ns;
	}

	public void setNs(String ns) {
		this.ns = ns;
	}

	@XmlAttribute(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
