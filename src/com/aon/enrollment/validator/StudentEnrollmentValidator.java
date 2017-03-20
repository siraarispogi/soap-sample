package com.aon.enrollment.validator;

import java.util.ArrayList;
import java.util.List;
import com.aon.enrollment.model.SubjectModel;
import com.aon.enrollment.service.StudentEnrollmentService;
import com.aon.enrollment.service.StudentProfileService;
import com.aon.enrollment.service.SubjectCatalogService;

public class StudentEnrollmentValidator {
	
	private StudentEnrollmentService studentEnrollmentService = new StudentEnrollmentService();
	private SubjectCatalogService subjectService = new SubjectCatalogService();
	private StudentProfileService studentProfileService = new StudentProfileService();
	
	public boolean studentEnrolled(int studentNumber){
		boolean result = true;
		if(!studentProfileService.checkStudentProfileExist(studentNumber)){
			result = false;
		}
		return result;
	}
	
	public boolean subjectCodeExistInCatalog(String subjectCode){
		boolean result = true;
		if(subjectService.getSubjectInList(subjectCode)==null){
			result = false;
		}
		
		return result;
	}
	
	public boolean subjectNotEnrolled(int studentNumber, String subjectCode){
		boolean result = true;
		if(checkSubjectEnrolled(studentNumber, subjectCode)){
			result = false;
		}
		return result;
		
	}
	
	private boolean checkSubjectEnrolled(int studentNumber, String subjectCode){
		boolean result = false;
		
		List<SubjectModel> subjectEnrolledList = studentEnrollmentService.getEnrolledSubject(studentNumber);
		if(subjectEnrolledList!=null){
			for(SubjectModel model : subjectEnrolledList){
				if(model.getSubjectCode().equalsIgnoreCase(subjectCode)){
					result = true;
				}
			}
		}

		return result;
	}
	
}
