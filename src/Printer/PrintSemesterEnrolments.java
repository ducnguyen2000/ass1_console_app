package Printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import Controller.HistoryStudentEnrolmentManager;
import Enrolment.StudentEnrolment;
import main.Utils;

public class PrintSemesterEnrolments implements CommandPrint{
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	private Utils utilities = new Utils();
	String semester;
	
	// CSV file header
	private static final String FILE_HEADER = "studentID, studentName, courseID, courseName";
	
	@Override
	public void print() {
		int count = 0;
		System.out.println("Input semester: ");
		this.semester = utilities.getInput();
		for(StudentEnrolment se: manager.getAllEnrolments()) {
			if(se.getSemester().equals(this.semester)) {
				System.out.println(se.toString());
				count += 1;
			}
		}
		System.out.println("Found (" + count + ") enrolments with the provided semester: " + this.semester);		
	}
	
	@Override
	public void exportCSV() {
		Writer out;
		
		String fileName = this.semester + ".csv";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
			bw.write(FILE_HEADER);
			bw.newLine();			
			for(StudentEnrolment se: manager.getAllEnrolments()) {
				if(se.getSemester().equals(this.semester)) {
					bw.write(se.getCourse().getId());
					bw.write(",");
					bw.write(se.getCourse().getName());
					bw.write(",");
					bw.write(se.getStudent().getId());
					bw.write(",");
					bw.write(se.getStudent().getName());
					bw.newLine();
				}			
			}	
			System.out.println("\nCSV Semester file was created successfully !!!\n");
			System.out.println("Open folder Console App to see the file !!!");
		} catch (IOException e) {
			System.out.println("Error in CSV File Writer!!!");
            e.printStackTrace();
		}
		
	}
}
