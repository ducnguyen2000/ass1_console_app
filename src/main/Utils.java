package main;
import Student.*;
import Enrolment.*;
import Course.*;
import Controller.*;
import List.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import CheckExistence.*;

public class Utils {
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	CheckExistVisitor checkExistence = new CheckExistVisitor();
	
	public boolean checkEmptyEnrolmentList() {
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
	
	public StudentEnrolment insertFromFile(Student student, Course course, String semester) {
		
		// Dependency Injection: create enrolment
		StudentEnrolment newEnrolment = new StudentEnrolment(student, course, semester);
		return newEnrolment;
	}
	
	public StudentEnrolment form(StudentList studentList, CourseList courseList) {
		System.out.println("All available enrolments");
		for(StudentEnrolment se: manager.getAllEnrolments()) {
			System.out.println(se.toString());
		}		
		
		// Get Student from id
		System.out.println("\nEnter student ID: ");
		String sId = getInput();
		studentList.setToBeCompared(sId);
		Pair studentResult = studentList.invite(checkExistence);
		if(!(Boolean)studentResult.isExisted) {
			return null;
		}
		int indexStudent = (Integer)studentResult.index;
		Student studentToEnrol = studentList.getStudents().get(indexStudent);
		System.out.println(studentToEnrol);
		
		// Get Course from id
		System.out.println("\nEnter course ID: ");
		String cId = getInput();
		courseList.setToBeCompared(cId);
		Pair courseResult = courseList.invite(checkExistence);
		if(!(Boolean)courseResult.isExisted) {
			return null;
		}
		int indexCourse = (Integer) courseResult.index;
		Course courseToEnrol = courseList.getCourses().get(indexCourse);
		System.out.println(courseToEnrol);
		
		// Get semester
		System.out.println("\nEnter semester(Format: YYYYA/YYYYB/YYYYC): ");
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
	
	public String getExport() {
		String export = "";
		while(!(export.toLowerCase().equals("y") || export.toLowerCase().equals("n"))){
			System.out.println("Do you want to export this file? (y/n)");
			export = getInput();
		}
		return export.toLowerCase();
	}
	
		
		

}
