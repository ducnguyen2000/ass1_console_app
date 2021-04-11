package Enrolment;
import java.util.*;

import Course.Course;
import Student.Student;

public class StudentEnrolment{
	private Student student;
	private Course course;
	private String semester;
	
	// Dependency injection with constructor
	public StudentEnrolment() {};
	
	public StudentEnrolment(Student student, Course course, String semester) {
		this.student = student;
		this.course = course;
		this.semester = semester;
	}
	
	public String getSemester() {
		return semester;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	// Facade Pattern
	public String getStudentId() {
		return student.getId();
	}
	
	public String getCourseId() {
		return course.getId();
	}
	
	// Dependency injection with setters
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	// Method to check duplicated enrollments
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		StudentEnrolment that = (StudentEnrolment) obj;
		
		return Objects.equals(student, that.student) &&
				Objects.equals(course, that.course) &&
				Objects.equals(semester, that.semester);
		
	}
	
	@Override
	public String toString() {
		return "Enrolment {Student = [Student ID: " + student.getId() + ", Student name: " + student.getName() + ", Student birthdate: " + student.getBirthdate() +
				"]; Course = [Course ID: " + course.getId() + ", Course name: " + course.getName() + ", Course credits: " + course.getNumberOfCredits() + 
				"]; Semester = " + semester + "}";
	}

}
