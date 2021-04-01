package Printer;

import Controller.HistoryStudentEnrolmentManager;
import Enrolment.StudentEnrolment;
import main.Utils;

public class PrintCourseEnrolments implements CommandPrint{
	private Utils utilities;
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();

	
	@Override
	public void print() {	
		System.out.println("Input course ID: ");
		String id = utilities.getInput();
		int count = 0;
		for(StudentEnrolment se: manager.getAllEnrolments()) {
			if(se.getCourseId().equals(id)) {
				System.out.println(se.toString());
				count += 1;
			}
		}
		System.out.println("Found (" + count + ") enrolments with course ID: " + id );	
	}

}
