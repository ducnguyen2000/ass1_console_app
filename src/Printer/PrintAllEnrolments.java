package Printer;

import Controller.HistoryStudentEnrolmentManager;
import Enrolment.StudentEnrolment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class PrintAllEnrolments implements CommandPrint {
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	
	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ", ";
	private static final String NEW_LINE_SEPERATOR = "\n";
	
	// CSV file header
	private static final String FILE_HEADER = "studentID, studentName, courseID, courseName, semester";
	
	@Override
	public void print() {	
		System.out.println("All available enrolments");
		for(StudentEnrolment se: manager.getAllEnrolments()) {
			System.out.println(se.toString());
		}		
	}
	

	public void exportCSV() {
		
		String fileName = "../Printer/all_enrolment.csv";
		
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(fileName);
			
			// Write the CSV file header
			fileWriter.append(FILE_HEADER);
			
			// Add a new line seperator after the header
			fileWriter.append(NEW_LINE_SEPERATOR);
			
			// Write a new Student Enrolment object list to the CSV file
			
			System.out.println("All available enrolments");
			for(StudentEnrolment se: manager.getAllEnrolments()) {
				fileWriter.append(se.getStudent().getId());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(se.getStudent().getName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(se.getCourse().getId());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(se.getCourse().getName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(se.getSemester());
				fileWriter.append(NEW_LINE_SEPERATOR);
			}
			
			 System.out.println("\nCSV StudentEnrolment file was created successfully !!!\n");
			 System.out.println("Open CSV folder to see the file !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CSV File Writer!!!");
		} finally {
			try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
		}
		
		
	}
}
