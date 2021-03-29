package Course;

public class Course {
	private String id;
	private String name;
	private int number_of_credits;
	
	public Course(String id, String name, int number_of_credits) {
		super();
		this.id = id;
		this.name = name;
		this.number_of_credits = number_of_credits;
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
