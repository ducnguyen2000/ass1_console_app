package CheckExistence;

import Controller.HistoryStudentEnrolmentManager;
import Course.Course;
import Enrolment.StudentEnrolment;
import List.CourseList;
import List.StudentList;
import Student.Student;

import java.util.List;

public class CheckExistVisitor implements Visitor {
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	
	@Override
	public Pair<Boolean, Integer> visit(StudentList studentList, String studentId) {
		while (studentList.hasNext()) {
			Student currentStudent = studentList.next();
			if (currentStudent.getId().equals(studentId)) {
				Pair<Boolean, Integer> found = new Pair<Boolean, Integer>(true, studentList.getStudents().indexOf(currentStudent));
				studentList.reset();
				return found;
			}
		}
		System.out.println("Student ID cannot found!");
		Pair<Boolean, Integer> unfound = new Pair<Boolean, Integer>(false, null);
		return unfound;
	}
	
	@Override
	public Pair<Boolean, Integer> visit(CourseList courseList, String courseId) {
		while(courseList.hasNext()) {
			Course currentCourse = courseList.next();
			if(currentCourse.getId().equals(courseId)) {
				Pair<Boolean, Integer> found = new Pair<Boolean, Integer>(true, courseList.getCourses().indexOf(currentCourse));
				courseList.reset();
				return found;
			}
		}
		System.out.println("Course ID cannot found!");
		Pair<Boolean, Integer> unfound = new Pair<Boolean, Integer>(false, null);
		return unfound;
	}
	
	@Override
	public Pair<Boolean, Integer> visit(List<StudentEnrolment> studentEnrolmentList, StudentEnrolment toBeCompared) {
		for (StudentEnrolment enrolment: manager.getAllEnrolments()) {
			if (toBeCompared.equals(enrolment)) {
				Pair<Boolean, Integer> found = new Pair<Boolean, Integer>(true, manager.getAllEnrolments().indexOf(enrolment));
				return found;
			}
		}
		System.out.println("\nEnrolment cannot found!");
		Pair<Boolean, Integer> unfound = new Pair<Boolean, Integer>(false, null);
		return unfound;
	}
}
