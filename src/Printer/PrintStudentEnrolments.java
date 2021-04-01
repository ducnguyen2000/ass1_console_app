package Printer;

import Controller.HistoryStudentEnrolmentManager;
import Enrolment.StudentEnrolment;
import main.Utils;

public class PrintStudentEnrolments implements CommandPrint{
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	private Utils utilities = new Utils();
	
	
	@Override
	public void print() {
		int count = 0;
		System.out.println("Input student ID: ");
		String id = utilities.getInput();
		for(StudentEnrolment se: manager.getAllEnrolments()) {
			if(se.getStudentId().equals(id)) {
				System.out.println(se.toString());
				count += 1;
			}
		}
		System.out.println("Found (" + count + ") enrolments with student ID: " + id);
		
	}
}
