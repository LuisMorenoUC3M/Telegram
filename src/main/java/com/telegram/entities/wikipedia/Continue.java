package com.telegram.entities.wikipedia;

import javax.xml.bind.annotation.XmlAttribute;

public class Continue {

	private String clcontinue;
	private String Continue;

	@XmlAttribute(name="clcontinue")
	public String getClcContinue() {
		return clcontinue;
	}

	public void setClcContinue(String clcContinue) {
		this.clcontinue = clcContinue;
	}

	@XmlAttribute(name="continue")
	public String getContinue() {
		return Continue;
	}

	public void setContinue(String continue1) {
		Continue = continue1;
	}

}
