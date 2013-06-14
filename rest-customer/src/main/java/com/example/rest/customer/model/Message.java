package com.example.rest.customer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Message{

    @XmlElement(required = true)
    protected String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
