package com.aon.enrollment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aon.enrollment.model.MessageModel;
import com.aon.enrollment.model.StudentProfileModel;

public class StudentProfileService {

	private static Map<Integer, StudentProfileModel> studentProfileList = new HashMap<>();
	
	public List<MessageModel> addStudentProfile(List<StudentProfileModel> studentProfile) {
		List<MessageModel> msgModelList = new ArrayList<>();
		MessageModel msgModel = new MessageModel();
		for(StudentProfileModel model : studentProfile){
			if(checkStudentProfileExist(model.getStudentNumber())){
				msgModel = new MessageModel("Student number "+model.getStudentNumber() + " already existed");
				
			}else{
				studentProfileList.put(model.getStudentNumber(), model);
				msgModel = new MessageModel("Student number "+model.getStudentNumber()+" successfully added!");
			}
			msgModelList.add(msgModel);
		}
	
		return msgModelList;
	}

	public List<StudentProfileModel> getAllStudentProfile() {
		return new ArrayList<StudentProfileModel>(studentProfileList.values());
	}

	public boolean checkStudentProfileExist(int studentNumber){
		boolean result = true;
		if(studentProfileList.get(studentNumber)==null){
			result = false;
		}
		return result;
	}
}
