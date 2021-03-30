package Controller;

import Course.*;
import Enrolment.*;
import Student.*;

public class UpdateEnrolmentCommand implements Command {
	StudentEnrolment updatedInfo;
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	Student beforeStudent;
	Course beforeCourse;
	String beforeSemester;
	int indexOfNeedUpdatedInfo;
	
	public UpdateEnrolmentCommand(StudentEnrolment enrolment, int index) {
		this.updatedInfo = enrolment;
		this.indexOfNeedUpdatedInfo = index;
		this.beforeStudent = manager.getAllEnrolments().get(indexOfNeedUpdatedInfo).getStudent();
		this.beforeCourse = manager.getAllEnrolments().get(indexOfNeedUpdatedInfo).getCourse();
		this.beforeSemester = manager.getAllEnrolments().get(indexOfNeedUpdatedInfo).getSemester();
	}
	
	@Override
	public void execute() {
		StudentEnrolment needUpdatedInfo = manager.getAllEnrolments().get(indexOfNeedUpdatedInfo);
		manager.updateStudentEnrolment(needUpdatedInfo, updatedInfo);
	}
	
	@Override
	public void undo() {
		StudentEnrolment revertedInfo = manager.getAllEnrolments().get(indexOfNeedUpdatedInfo);
		revertedInfo.setStudent(this.beforeStudent);
		revertedInfo.setCourse(this.beforeCourse);
		revertedInfo.setSemester(this.beforeSemester);
		System.out.println("Undo succesful!");
		
	}
}
