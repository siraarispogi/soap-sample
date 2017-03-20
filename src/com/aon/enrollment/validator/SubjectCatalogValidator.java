package com.aon.enrollment.validator;

import com.aon.enrollment.service.SubjectCatalogService;

public class SubjectCatalogValidator {

	private SubjectCatalogService service = new SubjectCatalogService();
	
	public boolean subjectCodePresentInSubjectCatalog(String subjectCode){
		boolean result = true;
		if(service.getSubjectInList(subjectCode)==null){
			result = false;
		}
		return result;
	}
}
