package com.aon.enrollment.impl;

import java.util.List;

import com.aon.enrollment.model.MessageModel;
import com.aon.enrollment.model.SubjectModel;

public interface StudentEnrollmentImpl {
	
	public abstract List<MessageModel> enrollSubject(int studentNumber, List<String> subjectCode);
	public abstract List<SubjectModel> displayEnrolledSubject(int studentNumber);
	public abstract List<MessageModel> removeSubject(int studentNumber, List<String> subjectCode);
}
