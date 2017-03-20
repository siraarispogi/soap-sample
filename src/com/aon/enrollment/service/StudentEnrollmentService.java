package com.aon.enrollment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aon.enrollment.model.MessageModel;
import com.aon.enrollment.model.SubjectModel;

public class StudentEnrollmentService {
	
	private SubjectCatalogService subjectService = new SubjectCatalogService();
	
	private static Map<Integer, List<SubjectModel>> enrolledSubject = new HashMap<>();
	
	public List<MessageModel> enrollSubject(int studentNumber, List<String> subjectCode) {
		List<MessageModel> msgModelList = new ArrayList<>();
		MessageModel model = new MessageModel();
		List<SubjectModel> subjectList = new ArrayList<>();
		
		for(String code : subjectCode){
				subjectList.add(subjectService.getSubjectInList(code));
				model = new MessageModel("Subject Code " + code +" with Subject Name " +
												subjectService.getSubjectInList(code).getSubjectName()+
														" successfully enrolled");
				msgModelList.add(model);
		}
		if(enrolledSubject.get(studentNumber)!=null){
			subjectList.addAll(enrolledSubject.get(studentNumber));
		}
		
		enrolledSubject.put(studentNumber, subjectList);
		return msgModelList;
	}

	public List<SubjectModel> getEnrolledSubject(int studentNumber) {
		return enrolledSubject.get(studentNumber);
	}

	public List<MessageModel> removeSubjectInEnrolledList(int studentNumber, List<String> subjectCode) {
		List<MessageModel> msgModelList = new ArrayList<>();
		List<SubjectModel> subjectList = enrolledSubject.get(studentNumber);
		List<SubjectModel> toRemoveList = new ArrayList<>();
		if(subjectList!=null){
			for(String subjCode : subjectCode){
				for(SubjectModel mod : subjectList){
					if(mod.getSubjectCode().equalsIgnoreCase(subjCode)){
						toRemoveList.add(mod);
						msgModelList.add(new MessageModel("Subject Code "+ subjCode + " with subject name "+ mod.getSubjectName()+ " successfully remove"));
					}
				}
			}
			
//			for(SubjectModel mod : subjectList){
//				for(String subjCode : subjectCode){
//					if(mod.getSubjectCode().equalsIgnoreCase(subjCode)){
//						toRemoveList.add(mod);
//						msgModelList.add(new MessageModel("Subject Code "+ subjCode + " with subject name "+ mod.getSubjectName()+ " successfully remove"));
//					}else{
//						msgModelList.add(new MessageModel("Subject Code "+subjCode+" is not present in your enrolled subject"));
//					}
//				}
//			}
			subjectList.removeAll(toRemoveList);
		}else{
			msgModelList.add(new MessageModel("Student Number dont have any enrolled subject"));
		}
		return msgModelList;
	}
	
}
