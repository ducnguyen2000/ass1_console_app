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
	public Pair visit(StudentList studentList, String studentId) {
		while (studentList.hasNext()) {
			Student currentStudent = studentList.next();
			if (currentStudent.getId().equals(studentId)) {
				Pair found = new Pair(true, studentList.getStudents().indexOf(currentStudent));
				studentList.reset();
				return found;
			}
		}
		System.out.println("Student ID cannot found!");
		Pair unfound = new Pair(false, null);
		return unfound;
	}
	
	@Override
	public Pair visit(CourseList courseList, String courseId) {
		while(courseList.hasNext()) {
			Course currentCourse = courseList.next();
			if(currentCourse.getId().equals(courseId)) {
				Pair found = new Pair(true, courseList.getCourses().indexOf(currentCourse));
				courseList.reset();
				return found;
			}
		}
		System.out.println("Course ID cannot found!");
		Pair unfound = new Pair(false, null);
		return unfound;
	}
	
	@Override
	public Pair visit(List<StudentEnrolment> studentEnrolmentList, StudentEnrolment toBeCompared) {
		for (StudentEnrolment enrolment: manager.getAllEnrolments()) {
			if (toBeCompared.getCourseId().equals(enrolment)) {
				Pair found = new Pair(true, manager.getAllEnrolments().indexOf(enrolment));
				return found;
			}
		}
		System.out.println("Enrolment cannot found!");
		Pair unfound = new Pair(false, null);
		return unfound;
	}
}
