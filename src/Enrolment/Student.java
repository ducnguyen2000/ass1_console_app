package Enrolment;
import java.util.*;

public class Student {
	private String id;
	private String name;
	private Calendar birthdate;
	private ArrayList<Course> courseList;
//  duc.birthday = new GregorianCalendar(2000, 11, 14);
	
	public Student(String id, String name, Calendar birthdate) {
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
	
	public Calendar getBirthdate() {
		return birthdate;
	}
	
}
