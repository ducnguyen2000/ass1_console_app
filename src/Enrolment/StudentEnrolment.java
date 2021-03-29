package Enrolment;
import java.util.*;

import Course.Course;
import Student.Student;

public class StudentEnrolment implements StudentEnrolmentManager{
	private Student student;
	private Course course;
	private ArrayList<Student> studentList;
	private ArrayList<Course> courseList;
//	private ArrayList<StudentEnrolment> semesterList;
	private String semester;
	
	public StudentEnrolment(String semester) {
		super();
		studentList = new ArrayList<Student>();
		courseList = new ArrayList<Course>();

		this.semester = semester;
	}
	
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	public ArrayList<Course> getCourseList() {
		return courseList;
	}
	
	public String semester() {
		return semester;
	}
	
	public boolean add(Student student, Course course) {
		if(studentList.contains(student)) {
			return false;
		}
		if(courseList.contains(course)) {
			return false;
		}
		studentList.add(course);
		courseList.add(student);
//		student.getCourseList().add(course);
		System.out.print(this);
		return true;
	};
//	public boolean update() {
//		if(studentList.contains(student)) {
//			return false;
//		}
//		if(courseList.contains(course)) {
//			return false;
//		}
//		studentList.add(student);
//		student.getCourseList.add(this);
//		return true;
//	};
//	public boolean delete() {
//		if(studentList.contains(student)) {
//			return false;
//		}
//		if(courseList.contains(course)) {
//			return false;
//		}
//		studentList.add(student);
//		student.getCourseList.add(this);
//		return true;
//	};
//	public void getOne() {};
//	public void getAll() {};
	
	@Override
	public String toString() {
		return "Semester: " + semester + "[students= " + studentList + "; courseList= " + courseList + "]";
	}
}
