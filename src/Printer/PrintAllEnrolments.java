package Printer;

import Controller.HistoryStudentEnrolmentManager;
import Enrolment.StudentEnrolment;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;


public class PrintAllEnrolments implements CommandPrint {
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();

	// CSV file header
	private static final String FILE_HEADER = "studentID, studentName, courseID, courseName, semester";
	
	@Override
	public void print() {	
		System.out.println("All available enrolments");
		for(StudentEnrolment se: manager.getAllEnrolments()) {
			System.out.println(se.toString());
		}		
	}
	
	@Override
	public void exportCSV() {
		Writer out;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("all_enrolment.csv"))){
			bw.write(FILE_HEADER);
			bw.newLine();
			for(StudentEnrolment se: manager.getAllEnrolments()) {
				bw.write(se.getStudent().getId());
				bw.write(",");
				bw.write(se.getStudent().getName());
				bw.write(",");
				bw.write(se.getCourse().getId());
				bw.write(",");
				bw.write(se.getCourse().getName());
				bw.write(",");
				bw.write(se.getSemester());
				bw.newLine();
			}	
			System.out.println("\nCSV StudentEnrolment file was created successfully !!!\n");
			System.out.println("Open folder Console App to see the file !!!");
		} catch (IOException e) {
			System.out.println("Error in CSV File Writer!!!");
            e.printStackTrace();
		}
		
	}
	

}
