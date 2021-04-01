package Printer;

import Controller.HistoryStudentEnrolmentManager;
import Enrolment.StudentEnrolment;
import main.Utils;

public class PrintSemesterEnrolments implements CommandPrint{
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	private Utils utilities = new Utils();
	
	@Override
	public void print() {
		int count = 0;
		System.out.println("Input semester: ");
		String semester = utilities.getInput();
		for(StudentEnrolment se: manager.getAllEnrolments()) {
			if(se.getSemester().equals(semester)) {
				System.out.println(se.toString());
				count += 1;
			}
		}
		System.out.println("Found (" + count + ") enrolments with the provided semester: " + semester);		
	}
	
	
}
