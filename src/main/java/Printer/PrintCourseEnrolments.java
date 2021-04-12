package Printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import Controller.HistoryStudentEnrolmentManager;
import Enrolment.StudentEnrolment;
import MainApp.Utils;


public class PrintCourseEnrolments implements CommandPrint {
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	private Utils utilities = new Utils();
	String csId;
	String csName;
	

	
	@Override
	public void print() {
		int count = 0;
		System.out.println("Input course ID: ");
		this.csId = utilities.getInput();
		for(StudentEnrolment se: manager.getAllEnrolments()) {
			if(se.getCourseId().equals(this.csId)) {
				System.out.println(se.toString());
				this.csName = se.getCourse().getName();
				count += 1;
			}
		}
		System.out.println("Found (" + count + ") enrolments with course ID: " + this.csId);
		
	}
	
	@Override
	public void exportCSV() {
		Writer out;
		String fileName = this.csId + "_" + this.csName + ".csv";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){		
			for(StudentEnrolment se: manager.getAllEnrolments()) {
				if(se.getCourseId().equals(this.csId)) {
					bw.write(se.getStudent().getId());
					bw.write(",");
					bw.write(se.getStudent().getName());
					bw.write(",");		
					bw.write(se.getStudent().getBirthdate());
					bw.write(",");	
					bw.write(se.getSemester());
					bw.newLine();
				}			
			}	
			System.out.println("\nCSV Course file was created successfully !!!\n");
			System.out.println("Open folder Console App to see the file !!!");
		} catch (IOException e) {
			System.out.println("Error in CSV File Writer!!!");
            e.printStackTrace();
		}
		
	}

}
