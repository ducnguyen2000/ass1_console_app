package List;

import java.util.ArrayList;
import java.util.List;

import CheckExistence.*;
import Course.*;
import Student.Student;


public class CourseList implements MyIterator, Visitable{
	List<Course> courses = new ArrayList<>();
	int currentItem = 0;
	String toBeCompared;
	
	public List<Course> getCourses(){
		return courses;
	}
	
	public void setCourses(List<Course> courses ) {
		this.courses = courses;
	}
	
	public void setToBeCompared(String toBeCompared) {
		this.toBeCompared = toBeCompared;
	}
	
	@Override
	public void reset() {
		currentItem = 0;
	}
	
	@Override
	public boolean hasNext() {
		if(currentItem >= courses.size()) {
			reset();
			return false;
		}
		return true;
	}
	
	@Override
	public Course next() {
		return courses.get(currentItem++);
	}
	
	@Override
	public Pair invite(Visitor visitor) {
		return visitor.visit(this, toBeCompared);
	}
	
	public void showAllCourses() {
		for(Course course: courses) {
			System.out.println(course.toString());
		}
	}
}
