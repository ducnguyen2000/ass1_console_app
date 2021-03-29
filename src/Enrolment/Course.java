package Enrolment;
import java.util.*;

public class Course {
	private String id;
	private String name;
	private int number_of_credits;
	private ArrayList<Student> studentList;
	
	public Course(String id, String name, int number_of_credits) {
		super();
		this.id = id;
		this.name = name;
		this.number_of_credits = number_of_credits;
		studentList = new ArrayList<Student>();
	}
	
	public ArrayList<Student> getStudentList(){
		return studentList;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumberOfCredits() {
		return number_of_credits;
	}
	
}
