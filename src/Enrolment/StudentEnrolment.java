package Enrolment;
import java.util.*;

public class StudentEnrolment implements StudentEnrolmentManager{
	private String student;
	private String course;
	private ArrayList<Student> studentList;
	private ArrayList<Course> courseList;
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
	
	public boolean add(Student student, Course course, String semester) {
		if(studentList.contains(student)) {
			return false;
		}
		if(courseList.contains(course)) {
			return false;
		}
		studentList.add(student);
		student.getCourseList.add(this);
		return true;
	};
	public boolean update() {
		if(studentList.contains(student)) {
			return false;
		}
		if(courseList.contains(course)) {
			return false;
		}
		studentList.add(student);
		student.getCourseList.add(this);
		return true;
	};
	public boolean delete() {
		if(studentList.contains(student)) {
			return false;
		}
		if(courseList.contains(course)) {
			return false;
		}
		studentList.add(student);
		student.getCourseList.add(this);
		return true;
	};
	public void getOne() {};
	public void getAll() {};
}
