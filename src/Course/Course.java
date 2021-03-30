package Course;


public class Course{
	private String id;
	private String name;
	private int number_of_credits;
	
	
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
	
	
	@Override
	public String toString() {
		return "Course [courseName = " + name + ", courseID = " + id + ", number_of_credits = " + number_of_credits + "]";
	}
	
	
}
