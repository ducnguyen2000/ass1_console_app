package Course;

public class CourseBuilder {
	Course course = new Course();
	
	public CourseBuilder addId(String id) {
		course.setId(id);
		return this;
	}
	
	public CourseBuilder addName(String name) {
		course.setName(name);
		return this;
	}
	
	public CourseBuilder addNumberOfCredits(int number_of_credits) {
		course.setNumberOfCredits(number_of_credits);
		return this;
	}
	
	public Course buildCourse() {
		return course;
	}
}
