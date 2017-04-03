package com.telegram.entities.wikipedia;

import javax.xml.bind.annotation.XmlElement;

public class Categories {

	private Category[] cl;

	@XmlElement(name="cl")
	public Category[] getCl() {
		return cl;
	}

	public void setCl(Category[] cl) {
		this.cl = cl;
	}
	
	
}
