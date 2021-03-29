package main;
import java.util.*;

import Course.*;
import Enrolment.StudentEnrolment;
import Student.*;

public class ConsoleApp {
	public static void main(String args[]) {
		// Sample students, created by Builder Pattern
		StudentBuilder stdBuilder1 = new StudentBuilder();
		StudentBuilder stdBuilder2 = new StudentBuilder();
		StudentBuilder stdBuilder3 = new StudentBuilder();
		
		Student std1 = stdBuilder1.addId("0001").addName("Nguyen Danh Duc").addBirthdate("14/11/2000").buildStudent();
		Student std2 = stdBuilder2.addId("0002").addName("John Smith").addBirthdate("04/03/1999").buildStudent();
		Student std3 = stdBuilder3.addId("0003").addName("Snoop Dogg").addBirthdate("20/10/1971").buildStudent();
		
		// Sample courses, created by Builder Pattern
		CourseBuilder crsBuilder1 = new CourseBuilder();
		CourseBuilder crsBuilder2 = new CourseBuilder();
		
		Course crs1 = crsBuilder1.addId("COSC2440").addName("Software Architecture: Design and Implementation").addNumberOfCredits(12).buildCourse();
		Course crs2 = crsBuilder2.addId("ISYS2101").addName("Software Engineering Project Management").addNumberOfCredits(12).buildCourse();
		
		System.out.println(std1);
		System.out.println(crs2);
	
	
	}
}
