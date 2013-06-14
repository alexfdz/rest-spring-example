package com.example.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ExtendedMessage{

    @XmlElement(required = true)
    protected String extendedText;

	public String getExtendedText() {
		return extendedText;
	}

	public void setExtendedText(String extendedText) {
		this.extendedText = extendedText;
	}
}
