package main;
import Student.*;
import Enrolment.*;
import Course.*;
import Controller.*;
import List.*;

import java.util.Scanner;

import CheckExistence.*;

public class Utils {
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	CheckExistVisitor checkExistence = new CheckExistVisitor();
	
	public boolean checkEmpryEnrolmentList() {
		if(manager.getAllEnrolments().size() == 0) {
			System.out.println("\nEnrolment list is empty!\n");
			return false;
		}
		return true;
	}
	
	public String getInput() {
		Scanner sc = new Scanner(System.in);
		if(sc.hasNext()) {
			return sc.next();
		}
		else {
			return null;
		}
	}
	
	public StudentEnrolment form(StudentList studentList, CourseList courseList) {
		// Get Student from id
		System.out.println("\nEnter student ID: ");
		String sId = getInput();
		studentList.setToBeCompared(sId);
		Pair studentResult = studentList.invite(checkExistence);
		if(!(boolean)studentResult.isExisted) {
			return null;
		}
		int indexStudent = (int)studentResult.index;
		Student studentToEnrol = studentList.getStudents().get(indexStudent);
		
		// Get Course from id
		System.out.println("\nEnter course ID: ");
		String cId = getInput();
		courseList.setToBeCompared(cId);
		Pair courseResult = courseList.invite(checkExistence);
		if(!(boolean)courseResult.isExisted) {
			return null;
		}
		int indexCourse = (int)courseResult.index;
		Course courseToEnrol = courseList.getCourses().get(indexCourse);
		
		// Get semester
		System.out.println("\nEnter semester: ");
		String semester = getInput();
		
		// Dependency Injection: create enrolment
		StudentEnrolment newEnrolment = new StudentEnrolment(studentToEnrol, courseToEnrol, semester);
		return newEnrolment;
	}
	
	public String getUndo() {
		String undo = "";
		while(!(undo.toLowerCase().equals("y") || undo.toLowerCase().equals("n"))) {
			System.out.println("Do you want to undo your task? (y/n)");
			undo = getInput();
		}
		return undo.toLowerCase();
	}
}
