package Course;
import java.util.*;

import Student.Student;

public class Course {
	private String id;
	private String name;
	private int number_of_credits;
	
	// Composite Pattern 
	private List<Course> courseList = new ArrayList<>();
	
	public Course() {};
	
	public Course(String id, String name, int number_of_credits) {
		super();
		this.id = id;
		this.name = name;
		this.number_of_credits = number_of_credits;
	}
		
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumberOfCredits() {
		return number_of_credits;
	}
	
	public void setNumberOfCredits(int number_of_credits) {
		this.number_of_credits = number_of_credits;
	}
	
	public List<Course> getCourseList(){
		return courseList;
	}
	
	public void addCourse(Course course) {
		courseList.add(course);
	}
	
	@Override
	public String toString() {
		return "Course [courseName = " + name + ", courseID = " + id + ", number_of_credits = " + number_of_credits + "]";
	}
}
