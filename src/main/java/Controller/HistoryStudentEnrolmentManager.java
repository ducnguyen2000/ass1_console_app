package Controller;
import java.util.*;

import CheckExistence.*;
import Enrolment.*;

public class HistoryStudentEnrolmentManager implements StudentEnrolmentManager, Visitable{
	private List<StudentEnrolment> studentEnrolmentList = new ArrayList<>();
	private StudentEnrolment toBeCompared;
	
	// Singleton Pattern: Ensure create only 1 HistoryStudentEnrolmentManager instance
	private static HistoryStudentEnrolmentManager instance = new HistoryStudentEnrolmentManager();
	
	private HistoryStudentEnrolmentManager() {};
	
	public static HistoryStudentEnrolmentManager getInstance() {
		return instance;
	}
	
	public void setToBeCompared(StudentEnrolment toBeCompared) {
		this.toBeCompared = toBeCompared;
	}
	
	
	// Override StudentEnrolmentManager
	@Override
	public void createStudentEnrolment(StudentEnrolment newEnrolment) {
		studentEnrolmentList.add(newEnrolment);
		System.out.println("\nEnrolment created!\n" + newEnrolment.toString());
	}
	
	@Override
	public void updateStudentEnrolment(StudentEnrolment needUpdatedInfo, StudentEnrolment updatedInfo) {
		needUpdatedInfo.setStudent(updatedInfo.getStudent());
		needUpdatedInfo.setCourse(updatedInfo.getCourse());
		needUpdatedInfo.setSemester(updatedInfo.getSemester());
		System.out.println("\nUpdated 1 enrolment:\n" + updatedInfo.toString());
	}
	
	@Override
	public void deleteStudentEnrolment(StudentEnrolment enrolment) {
		studentEnrolmentList.remove(enrolment);
		System.out.println("\nDeleted 1 enrolment\n" + enrolment.toString());
	}
	
	@Override
	public List<StudentEnrolment> getAllEnrolments(){
		return this.studentEnrolmentList;
	}
	
	// Override Visitable
	@Override
	public Pair<Boolean, Integer> invite(Visitor visitor) {
		return visitor.visit(this.studentEnrolmentList, toBeCompared);
	}
}
