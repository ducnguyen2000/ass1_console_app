package main;

public class StudentEnrolment {
	private String student;
	private String course;
	private String semester;
	
	public StudentEnrolment(String student, String course, String semester) {
		super();
		this.student = student;
		this.course = course;
		this.semester = semester;
	}
	
	public String student() {
		return student;
	}
	
	public String course() {
		return course;
	}
	
	public String semester() {
		return semester;
	}
}
