package com.aon.enrollment;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.aon.enrollment.impl.StudentEnrollmentImpl;
import com.aon.enrollment.impl.StudentProfileImpl;
import com.aon.enrollment.impl.SubjectCatalogImpl;
import com.aon.enrollment.model.MessageModel;
import com.aon.enrollment.model.StudentProfileModel;
import com.aon.enrollment.model.SubjectModel;
import com.aon.enrollment.service.StudentEnrollmentService;
import com.aon.enrollment.service.StudentProfileService;
import com.aon.enrollment.service.SubjectCatalogService;
import com.aon.enrollment.validator.StudentEnrollmentValidator;
import com.aon.enrollment.validator.SubjectCatalogValidator;

@WebService(portName = "EnrollmentServicePort",
			serviceName = "EnrollmentService",
			name = "EnrollmentService")
public class EnrollmentService implements SubjectCatalogImpl, StudentProfileImpl, StudentEnrollmentImpl{

	private SubjectCatalogService subjectService = new SubjectCatalogService();
	private StudentProfileService studentProfileService = new StudentProfileService();
	private StudentEnrollmentService studentEnrollmentService = new StudentEnrollmentService();
	
	private StudentEnrollmentValidator studentEnrollmentValidator = new StudentEnrollmentValidator();
	private SubjectCatalogValidator subjectValidator = new SubjectCatalogValidator();
	
	@Override
	@WebMethod(action="Add_Subject_In_Catalog", operationName="Add_Subject_In_Catalog")
	@WebResult(name="Response_Information")
	public List<MessageModel> addSubject(@WebParam(name="Subject_Details") List<SubjectModel> subject) {
		List<MessageModel> msgModelList = new ArrayList<>();
		
		for(SubjectModel model : subject){
			if(subjectValidator.subjectCodePresentInSubjectCatalog(model.getSubjectCode())){
				msgModelList.add(new MessageModel("Subject Code "+model.getSubjectCode()+" is already present in Subject Catalog"));
			}else{
				subjectService.addSubjectInCatalog(model);
				msgModelList.add(new MessageModel("Subject "+model.getSubjectName()+" with Subject Code "+model.getSubjectCode()+" successfully added"));
			}
		}
		return msgModelList;
	}

	@Override
	@WebMethod(action="View_Subject_In_Catalog", operationName="View_Subject_In_Catalog")
	@WebResult(name="Subject_Detail_List")
	public List<SubjectModel> getSubjectList() {
		return subjectService.getAllSubjectInCatalog();
	}

	@Override
	@WebMethod(action="Add_Student_Profile", operationName="Add_Student_Profile")
	@WebResult(name="Response_Information")
	public List<MessageModel> addStudentProfile(@WebParam(name="Student_Profile_Details") List<StudentProfileModel> studentProfile) {
		return studentProfileService.addStudentProfile(studentProfile);
	}

	@Override
	@WebMethod(action="View_Student_Profile", operationName="View_Student_Profile")
	@WebResult(name="Student_Profile_List")
	public List<StudentProfileModel> getStudentProfileList() {
		return studentProfileService.getAllStudentProfile();
	}

	@Override
	@WebMethod(action="Student_Enroll_Subject", operationName="Student_Enroll_Subject")
	@WebResult(name="Response_Information")
	public List<MessageModel> enrollSubject(@WebParam(name="Student_Number") int studentNumber,
										@WebParam(name="Subject_Codes")List<String> subjectCode) {
		
		if(!studentEnrollmentValidator.studentEnrolled(studentNumber)){
			List<MessageModel> modelList = new ArrayList<>();
			modelList.add(new MessageModel("Student Number"+ studentNumber + "is not present in Student Profile"));
			return modelList;
		}else{
			List<MessageModel> messageModelList = new ArrayList<>();
			List<String> subjectCodeCanEnroll = new ArrayList<>();
			for(String subjCode : subjectCode){
				if(!studentEnrollmentValidator.subjectCodeExistInCatalog(subjCode)){
					messageModelList.add(new MessageModel("Subject Code "+ subjCode+" is not present in Subject Catalog"));
				}else if(studentEnrollmentValidator.subjectNotEnrolled(studentNumber, subjCode)){
					subjectCodeCanEnroll.add(subjCode);
				}else{
					messageModelList.add(new MessageModel("Subject Code "+ subjCode+" is already enrolled"));
				}
			}
			if(subjectCodeCanEnroll.size()!=0){
				messageModelList.addAll(studentEnrollmentService.enrollSubject(studentNumber,subjectCodeCanEnroll));
			}
			return messageModelList;
		}
		
	}

	@Override
	@WebMethod(action="View_Enrolled_Subject", operationName="View_Enrolled_Subject")
	@WebResult(name="Enrolled_Subject_List")
	public List<SubjectModel> displayEnrolledSubject(@WebParam(name="Student_Number")int studentNumber) {
		return studentEnrollmentService.getEnrolledSubject(studentNumber);
	}

	@Override
	@WebMethod(action="Remove_Enrolled_Subject", operationName="Remove_Enrolled_Subject")
	@WebResult(name="Response_Information")
	public List<MessageModel> removeSubject(@WebParam(name="Student_Number") int studentNumber, 
										@WebParam(name="Subject_Code_List") List<String> subjectCode) {
		
		List<MessageModel> messageModelList = new ArrayList<>();
		if(!studentEnrollmentValidator.studentEnrolled(studentNumber)){
			List<MessageModel> modelList = new ArrayList<>();
			modelList.add(new MessageModel("Student Number "+ studentNumber + " is not present in Student Profile"));
			return modelList;
		}else{
			List<String> subjectCodeCanRemove = new ArrayList<>();
			for(String subjCode : subjectCode){
				if(!studentEnrollmentValidator.subjectCodeExistInCatalog(subjCode)){
					messageModelList.add(new MessageModel("Subject Code "+ subjCode+" is not present in Subject Catalog"));
				}else if(!studentEnrollmentValidator.subjectNotEnrolled(studentNumber, subjCode)){
					subjectCodeCanRemove.add(subjCode);
				}else{
					messageModelList.add(new MessageModel("Subject Code "+ subjCode+" is not present in your Subject Enrolled"));
				}
			}
			
			if(subjectCodeCanRemove.size()!=0){
				messageModelList.addAll(studentEnrollmentService.removeSubjectInEnrolledList(studentNumber,subjectCodeCanRemove));
			}
			return messageModelList;
		}
		
	}

}
