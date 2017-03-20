package com.aon.enrollment.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="Subject")
public class SubjectModel {
	
	private String subjectName;
	private String subjectCode;
	private String description;
	private int units;
	private String status;
	
	public SubjectModel() {
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	@XmlElement (name = "Subject_Name",required=true)
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
	public String getSubjectCode() {
		return subjectCode;
	}
	@XmlElement (name = "Subject_Code",required=true)
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public String getDescription() {
		return description;
	}
	@XmlElement (name = "Subject_Description",required=true)
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getUnits() {
		return units;
	}
	@XmlElement (name = "Subject_Units",required=true)
	public void setUnits(int units) {
		this.units = units;
	}
	
	public String getStatus() {
		return status;
	}
	@XmlElement (name = "Subject_Status",required=true)
	public void setStatus(String status) {
		this.status = status;
	}

}
