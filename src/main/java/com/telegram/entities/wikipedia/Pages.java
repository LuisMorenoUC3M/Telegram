package com.telegram.entities.wikipedia;

import javax.xml.bind.annotation.XmlElement;

public class Pages {

	private Page[] pages;

	@XmlElement(name="page")
	public Page[] getPages() {
		return pages;
	}

	public void setPages(Page[] pages) {
		this.pages = pages;
	}
	
	
}
