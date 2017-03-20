package com.aon.enrollment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aon.enrollment.model.SubjectModel;

public class SubjectCatalogService {
	
	private static Map<String, SubjectModel> subjectList = new HashMap<>();

	public List<SubjectModel> getAllSubjectInCatalog() {
		return new ArrayList<>(subjectList.values());
	}

	public void addSubjectInCatalog(SubjectModel model){
		subjectList.put(model.getSubjectCode(), model);
	}
	
	public SubjectModel getSubjectInList(String subjectCode){
		return subjectList.get(subjectCode);
	}
	
}
