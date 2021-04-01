package main;
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
		// Sample students, created by Builder Pattern
		StudentBuilder stdBuilder1 = new StudentBuilder();
		StudentBuilder stdBuilder2 = new StudentBuilder();
		StudentBuilder stdBuilder3 = new StudentBuilder();
		
		Student std1 = stdBuilder1.addId("0001").addName("Nguyen Danh Duc").addBirthdate("14/11/2000").buildStudent();
		Student std2 = stdBuilder2.addId("0002").addName("John Smith").addBirthdate("04/03/1999").buildStudent();
		Student std3 = stdBuilder3.addId("0003").addName("Snoop Dogg").addBirthdate("20/10/1971").buildStudent();
		
		List<Student> students = new ArrayList<>();
		students.addAll(Arrays.asList(std1, std2, std3));
		
		StudentList studentList = new StudentList();
		studentList.setStudents(students);
		
		
		// Sample courses, created by Builder Pattern
		CourseBuilder crsBuilder1 = new CourseBuilder();
		CourseBuilder crsBuilder2 = new CourseBuilder();
		CourseBuilder crsBuilder3 = new CourseBuilder();
			
		
		Course crs1 = crsBuilder1.addId("0001").addName("SADI").addNumberOfCredits(12).buildCourse();
		Course crs2 = crsBuilder2.addId("0002").addName("SEPM").addNumberOfCredits(12).buildCourse();
		Course crs3 = crsBuilder3.addId("0003").addName("P1").addNumberOfCredits(12).buildCourse();
		
		List<Course> courses = new ArrayList<>();
		courses.addAll(Arrays.asList(crs1, crs2, crs3));
		
		CourseList courseList = new CourseList();
		courseList.setCourses(courses);
		
		
		// Visitor Pattern
		CheckExistVisitor checkExistence = new CheckExistVisitor();
		
		// Singleton Pattern
		HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();

		
		
		
		boolean isQuit = false;
		
		// Menu
		System.out.println("\nWelcome to the enrolment system!\n");
		Utils utilities = new Utils();
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
					Boolean existEnrolment = ((Boolean)manager.invite(checkExistence).isExisted);
					
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
					boolean enrolmentExists = utilities.checkEmpryEnrolmentList();
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
					Boolean existedEnrolment = resultPair.isExisted;
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
					Boolean existedEnrolment1 = utilities.checkEmpryEnrolmentList();
					if(!existedEnrolment1) {
						break;
					}
					
					System.out.println("\nDelete an enrolment:\n");
					StudentEnrolment toBeDeleted = utilities.form(studentList, courseList);
					if (toBeDeleted == null) {
						break;
					}
					manager.setToBeCompared(toBeDeleted);
					Boolean exist = ((Boolean)manager.invite(checkExistence).isExisted);
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
					boolean isExistEnrolment1 = utilities.checkEmpryEnrolmentList();
					if(!isExistEnrolment1) {
						break;
					}
					
					PrintAllEnrolments all = new PrintAllEnrolments();
					all.print();
					break;
				
				case "7":
					// Check if any enrolment exists
					boolean isExistEnrolment2 = utilities.checkEmpryEnrolmentList();
					if(!isExistEnrolment2) {
						break;
					}
					
					PrintStudentEnrolments stu = new PrintStudentEnrolments();
					stu.print();
					break;
					
				case "8":
					// Check if any enrolment exists
					boolean isExistEnrolment3 = utilities.checkEmpryEnrolmentList();
					if(!isExistEnrolment3) {
						break;
					}
					
					PrintCourseEnrolments cou = new PrintCourseEnrolments();
					cou.print();
					break;
					
				case "9":
					// Check if any enrolment exists
					boolean isExistEnrolment4 = utilities.checkEmpryEnrolmentList();
					if(!isExistEnrolment4) {
						break;
					}
					
					PrintSemesterEnrolments sem = new PrintSemesterEnrolments();
					sem.print();
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
