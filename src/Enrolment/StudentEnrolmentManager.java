package Enrolment;

import Course.Course;
import Student.Student;

public interface StudentEnrolmentManager {
	public boolean add(Student student, Course course, String semester);
//	public boolean update();
//	public boolean delete();
//	public void getOne();
//	public void getAll();
}
