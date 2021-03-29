package Enrolment;

public class StudentEnrolment implements StudentEnrolmentManager{
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
	
	public void add() {};
	public void update() {};
	public void delete() {};
	public void getOne() {};
	public void getAll() {};
}
