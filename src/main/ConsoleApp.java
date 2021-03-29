package main;
import java.util.*;

import Enrolment.Course;
import Enrolment.Student;

public class ConsoleApp {
	public static void main(String args[]) {
		Student duc = new Student("01","Duc","14/11/2000");
		Course sadi = new Course("COSC2440","Software Architecture: Design and Implementation", 12);
		System.out.println(duc);
		System.out.println(sadi);
	
	
	}
}
