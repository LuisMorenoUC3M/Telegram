package com.telegram.entities.wikipedia;

import javax.xml.bind.annotation.XmlElement;

public class Query {
	
	private Pages pages;

	@XmlElement(name="pages")
	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
