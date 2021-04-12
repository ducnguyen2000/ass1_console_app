package Printer;

import Controller.HistoryStudentEnrolmentManager;
import Enrolment.StudentEnrolment;
import MainApp.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;


public class PrintAllEnrolments implements CommandPrint {
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();

	// CSV file header
	
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
		Utils utilities = new Utils();
		System.out.println("\nDo you want to name the file? (y/n)\n");
		String sc = utilities.getInput();
		String fileName = "";
		
		if(sc.equals("y")) {
			System.out.println("Your file name: ");
			fileName = utilities.getInput();
		} else {		
			fileName = "all_enrolments";
			System.out.println("Default name of file: " + fileName);
		}
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName + ".csv"))){
			for(StudentEnrolment se: manager.getAllEnrolments()) {
				bw.write(se.getStudent().getId());
				bw.write(",");
				bw.write(se.getStudent().getName());
				bw.write(",");
				bw.write(se.getStudent().getBirthdate());
				bw.write(",");
				bw.write(se.getCourse().getId());
				bw.write(",");
				bw.write(se.getCourse().getName());
				bw.write(",");
				bw.write(String.valueOf(se.getCourse().getNumberOfCredits()));
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
