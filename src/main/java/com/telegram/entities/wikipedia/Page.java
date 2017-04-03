package com.telegram.entities.wikipedia;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Page {

	private String _idx;
	private String pageid;
	private String ns;
	private String title;
	private String index;
	private Categories categories;

	@XmlAttribute(name="_idx")
	public String get_idx() {
		return _idx;
	}

	public void set_idx(String _idx) {
		this._idx = _idx;
	}

	@XmlAttribute(name="pageid")
	public String getPageid() {
		return pageid;
	}

	public void setPageid(String pageid) {
		this.pageid = pageid;
	}

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

	@XmlAttribute(name="index")
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@XmlElement(name="categories")
	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}
	
	
	
}
