package com.aon.enrollment.impl;

import java.util.List;

import javax.jws.WebMethod;

import com.aon.enrollment.model.MessageModel;
import com.aon.enrollment.model.StudentProfileModel;

public interface StudentProfileImpl {

	public abstract List<MessageModel> addStudentProfile(List<StudentProfileModel> studentProfile);
	public abstract List<StudentProfileModel> getStudentProfileList();

}
