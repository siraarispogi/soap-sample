package com.aon.enrollment.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Message")
public class MessageModel {
	
	private String message;

	public MessageModel() {
	}
	
	public MessageModel(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	@XmlElement(name="Message_Info")
	public void setMessage(String message) {
		this.message = message;
	}

}
