package Enrolment;
import java.util.*;

public class Student {
	private String id;
	private String name;
	private String birthdate;
	private ArrayList<Course> courseList;


	
	public Student(String id, String name, String birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		courseList = new ArrayList<Course>();
	}
	
	public ArrayList<Course> getCourseList(){
		return courseList;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBirthdate() {
		return birthdate;
	}
	
	@Override
	public String toString() {
		return "Student [studentName=" + name + ", studentID=" + id + ", birthdate=" + birthdate + "]";
	}
	
}
