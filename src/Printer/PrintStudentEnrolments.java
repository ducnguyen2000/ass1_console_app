package Printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import Controller.HistoryStudentEnrolmentManager;
import Enrolment.StudentEnrolment;
import main.Utils;

public class PrintStudentEnrolments implements CommandPrint{
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	private Utils utilities = new Utils();
	String stId;
	String stName;
	
	// CSV file header
	private static final String FILE_HEADER = "courseID, courseName, semester";
	
	
	@Override
	public void print() {
		int count = 0;
		System.out.println("Input student ID: ");
		this.stId = utilities.getInput();
		for(StudentEnrolment se: manager.getAllEnrolments()) {
			if(se.getStudentId().equals(this.stId)) {
				System.out.println(se.toString());
				stName = se.getStudent().getName();
				count += 1;
			}
		}
		System.out.println("Found (" + count + ") enrolments with student ID: " + this.stId);
		
	}
	
	@Override
	public void exportCSV() {
		Writer out;

		
		String fileName = this.stId + "_" + this.stName + ".csv";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
			bw.write(FILE_HEADER);
			bw.newLine();			
			for(StudentEnrolment se: manager.getAllEnrolments()) {
				if(se.getCourseId().equals(this.stId)) {
					bw.write(se.getCourse().getId());
					bw.write(",");
					bw.write(se.getCourse().getName());
					bw.write(",");			
					bw.write(se.getSemester());
					bw.newLine();
				}			
			}	
			System.out.println("\nCSV Student file was created successfully !!!\n");
			System.out.println("Open folder Console App to see the file !!!");
		} catch (IOException e) {
			System.out.println("Error in CSV File Writer!!!");
            e.printStackTrace();
		}
		
	}
}
