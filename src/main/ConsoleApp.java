package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import CheckExistence.*;
import Controller.*;
import Course.*;
import Enrolment.*;
import List.*;
import Student.*;
import Printer.*;

public class ConsoleApp {
	public static void main(String args[]) {

		
		// Visitor Pattern
		CheckExistVisitor checkExistence = new CheckExistVisitor();
		
		// Singleton Pattern
		HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();

		
		boolean isQuit = false;
		
		// Menu
		System.out.println("\nWelcome to the enrolment system!\n");
		Utils utilities = new Utils();
		
		System.out.println("\nDo you want to load any file? (y/n)\n");
		
		String ans = utilities.getInput();
		String fileName = "";
	
		
		StudentList studentList = new StudentList();
		List<Student> students = new ArrayList<>();
		
		CourseList courseList = new CourseList();
		List<Course> courses = new ArrayList<>();
			
		try {		
			FileReader fr = null;

			if(ans.equals("y")) {
				System.out.println("\nEnter your file name: (Do not enter the .csv)\n");	
				
				String file = utilities.getInput();
				fileName = file + ".csv";
				File f = new File(fileName);
				if(f.isFile() && !f.isDirectory()) {
					fr = new FileReader(fileName);
				} else {
					System.out.println("File does not exists");
					System.out.println("Using default.csv instead");
					fileName = "default.csv";
					fr = new FileReader(fileName);
				}
											
			} else {
				System.out.println("\nLoad default csv file\n");
				fileName = "default.csv";
				fr = new FileReader(fileName);
			}
			
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";

			while((line = br.readLine()) != null) {
				List<String> tempArr = new ArrayList<String>(Arrays.asList(line.split(",")));
				
				StudentBuilder stdBuilder = new StudentBuilder();
				Student std = stdBuilder.addId(String.valueOf(tempArr.get(0))).addName(tempArr.get(1)).addBirthdate(tempArr.get(2)).buildStudent();
				
				// Check Student existence
				studentList.setToBeCompared(tempArr.get(0));
				Pair<Boolean, Integer> studentResult = studentList.invite(checkExistence);
				
				if(studentResult.isExisted) {
					System.out.println("\nThis Student has already existed");
				} else {
					students.addAll(Arrays.asList(std));
					studentList.setStudents(students);
				}
				
				
				
				CourseBuilder crsBuilder = new CourseBuilder();
				Course crs = crsBuilder.addId(String.valueOf(tempArr.get(3))).addName(tempArr.get(4)).addNumberOfCredits(Integer.parseInt(tempArr.get(5))).buildCourse();
				
				// Check Course existence
				courseList.setToBeCompared(tempArr.get(3));
				Pair<Boolean, Integer> courseResult = courseList.invite(checkExistence);
				
				if(courseResult.isExisted) {
					System.out.println("\nThis Course has already existed");
				} else {
					courses.addAll(Arrays.asList(crs));
					courseList.setCourses(courses);
				}
				
				StudentEnrolment se = utilities.insertFromFile(std, crs, String.valueOf(tempArr.get(6)));
				
				// Check Student Enrolment existence
				manager.setToBeCompared(se);
				boolean existEnrolment = (Boolean) manager.invite(checkExistence).isExisted;
				
				if(existEnrolment) {
					System.out.println("\nThis enrolment has already existed!");
					continue;
				}		
				
				// Command Pattern
				CreateEnrolmentCommand addInfo = new CreateEnrolmentCommand(se);
				addInfo.execute();
				
				
				
				
			}
			br.close();
			System.out.println("Load file sucessful!");
		} catch(IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Load file error!");
		}
		


		
		while(!isQuit) {
			System.out.println(
					"Press the following number to execute the program:\n" +
					"----------------------\n" +
					"CRUD Student Enrolment\n" +
					"1. Create an enrolment\n" + 
					"2. Update an enrolment\n" + 
					"3. Delete an enrolment\n" +
					"4. Show all students\n" +
					"5. Show all courses\n" +
					"----------------------\n" +
					"Export data to CSV file (See in CSV folder)\n" +
					"6. Print all enrolments and export\n" +
					"7. Print enrolments and export for student\n" +
					"8. Print enrolments and export for course\n" +
					"9. Print enrolments and export for semester\n" +
					"----------------------\n" +
					"0. Quit\n"
					);
			
			
			System.out.println("Your option: ");
			Scanner sc = new Scanner(System.in);
			String option = "";
			
			if(sc.hasNext()) {
				option = sc.next();
			}
			
			switch(option) {
				case "1":
					System.out.println("Create new enrolment:");
					StudentEnrolment newEnrolment = utilities.form(studentList, courseList);

					if(newEnrolment == null) {
						break;
					}
					
					
					
					// Check Existence			
					manager.setToBeCompared(newEnrolment);
					boolean existEnrolment = ((Boolean) manager.invite(checkExistence).isExisted);
					
					if(existEnrolment) {
						System.out.println("\nThis enrolment has already existed!");
						break;
					}
					
					// Command Pattern
					CreateEnrolmentCommand addCommand = new CreateEnrolmentCommand(newEnrolment);
					addCommand.execute();
					
					// Option undo task
					String undoCreate = utilities.getUndo();
					if(undoCreate.equals("y")) {
						addCommand.undo();
					}
					break;
				
				case "2":
					// Check if any enrolment exists
					boolean enrolmentExists = utilities.checkEmptyEnrolmentList();
					if(!enrolmentExists) {
						break;
					}

				
					
					System.out.println("Update an enrolment:");
					StudentEnrolment toBeUpdated = utilities.form(studentList, courseList);
					if(toBeUpdated == null) {
						break;
					}
					
					// Check if needed enrolment exists
					manager.setToBeCompared(toBeUpdated);
					Pair<Boolean, Integer> resultPair = manager.invite(checkExistence);
					boolean existedEnrolment = resultPair.isExisted;
					Integer indexEnrolment = resultPair.index;
					
					if((!existedEnrolment) && (indexEnrolment == null)) {
						System.out.println("\nNo such a data was found!");
						break;
					}
					
					System.out.println("\nWhat information you want to change?\n");
					// Command Pattern
					StudentEnrolment updateInfo = utilities.form(studentList, courseList);
					UpdateEnrolmentCommand update = new UpdateEnrolmentCommand(updateInfo, indexEnrolment);
					update.execute();
					String undoUpdate = utilities.getUndo();
					if(undoUpdate.equals("y")) {
						update.undo();
					}
					break;
					
				case "3":
					// Check if any enrolment exists
					boolean existedEnrolment1 = utilities.checkEmptyEnrolmentList();
					if(!existedEnrolment1) {
						break;
					}
					
					System.out.println("\nDelete an enrolment:\n");
					StudentEnrolment toBeDeleted = utilities.form(studentList, courseList);
					if (toBeDeleted == null) {
						break;
					}
					manager.setToBeCompared(toBeDeleted);
					boolean exist = (Boolean) manager.invite(checkExistence).isExisted;
					if(!exist) {
						System.out.println("\nNo such a data was found!");
						break;
					}
					
					// Command Pattern
					DeleteEnrolmentCommand remove = new DeleteEnrolmentCommand(toBeDeleted);
					remove.execute();
					String undoRemove = utilities.getUndo();
					if(undoRemove.equals("y")) {
						remove.undo();
					}
					break;
					
				case "4":
					studentList.showAllStudents();
					break;
				
				case "5":
					courseList.showAllCourses();
					break;
					
				case "6":
					// Check if any enrolment exists
					boolean isExistEnrolment1 = utilities.checkEmptyEnrolmentList();
					if(!isExistEnrolment1) {
						break;
					}
					
					PrintAllEnrolments all = new PrintAllEnrolments();
					all.print();
					String exportAll = utilities.getExport();
					if(exportAll.equals("y")) {
						all.exportCSV();
					}
					break;
				
				case "7":
					// Check if any enrolment exists
					boolean isExistEnrolment2 = utilities.checkEmptyEnrolmentList();
					if(!isExistEnrolment2) {
						break;
					}
					
					PrintStudentEnrolments stu = new PrintStudentEnrolments();
					stu.print();
					String exportStudent = utilities.getExport();
					if(exportStudent.equals("y")) {
						stu.exportCSV();
					}
					break;
					
				case "8":
					// Check if any enrolment exists
					boolean isExistEnrolment3 = utilities.checkEmptyEnrolmentList();
					if(!isExistEnrolment3) {
						break;
					}
					
					PrintCourseEnrolments cs = new PrintCourseEnrolments();
					cs.print();
					String exportCourse = utilities.getExport();
					if(exportCourse.equals("y")) {
						cs.exportCSV();
					}
					break;
			
				case "9":
					// Check if any enrolment exists
					boolean isExistEnrolment4 = utilities.checkEmptyEnrolmentList();
					if(!isExistEnrolment4) {
						break;
					}
					
					PrintSemesterEnrolments sem = new PrintSemesterEnrolments();
					sem.print();
					String exportSemester = utilities.getExport();
					if(exportSemester.equals("y")) {
						sem.exportCSV();
					}
					break;

				
					
				case "0":
					System.out.println("Goodbye!");
					isQuit = true;
					break;
					
				default:
					System.out.println("\nInvalid input\n");
					break;
		
			}
			
		}
		
		
	
	
	}
}
