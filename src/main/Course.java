package main;

public class Course {
	private String id;
	private String name;
	private String number_of_credits;
	
	public Course(String id, String name, String number_of_credits) {
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
	
	public String getNumberOfCredits() {
		return number_of_credits;
	}
	
}
