package com.aon.enrollment.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="StudentProfile")
public class StudentProfileModel {
	
	private int studentNumber;
	private int age;
	private String firstName;
	private String lastName;
	private String gender;
	private String course;
	private int yearLevel;
	
	public int getStudentNumber() {
		return studentNumber;
	}
	@XmlElement (name = "Student_Number",required=true)
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	
	public int getAge() {
		return age;
	}
	@XmlElement (name = "Student_Age",required=true)
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	@XmlElement (name = "Student_First_Name",required=true)
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	@XmlElement (name = "Student_Last_Name",required=true)
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public String getGender() {
		return gender;
	}
	@XmlElement (name = "Student_Gender",required=true)
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public String getCourse() {
		return course;
	}
	@XmlElement (name = "Student_Course",required=true)
	public void setCourse(String course) {
		this.course = course;
	}
	
	
	public int getYearLevel() {
		return yearLevel;
	}
	@XmlElement (name = "Student_Year_Level",required=true)
	public void setYearLevel(int yearLevel) {
		this.yearLevel = yearLevel;
	}

}
