package com.aon.enrollment.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.aon.enrollment.model.MessageModel;
import com.aon.enrollment.model.SubjectModel;


public interface SubjectCatalogImpl {
	public abstract List<MessageModel> addSubject(List<SubjectModel> subject);
	public abstract List<SubjectModel> getSubjectList();
}
