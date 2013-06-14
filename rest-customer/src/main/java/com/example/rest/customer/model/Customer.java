package com.example.rest.customer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.rest.common.model.Resource;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Customer extends Resource{

    @XmlElement(required = true)
    protected String name;
    @XmlElement
    protected String customerDescription;
    @XmlElement
    protected String notes;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomerDescription() {
		return customerDescription;
	}
	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public String getUniqueId() {
		return getName();
	}
}
