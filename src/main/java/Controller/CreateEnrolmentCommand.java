package Controller;

import Enrolment.StudentEnrolment;

public class CreateEnrolmentCommand implements Command{
	StudentEnrolment enrolment;
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	
	public CreateEnrolmentCommand(StudentEnrolment enrolment) {
		this.enrolment = enrolment;
	}
	
	@Override
	public void execute() {
		manager.createStudentEnrolment(enrolment);
	}
	
	@Override
	public void undo() {
		manager.getAllEnrolments().remove(enrolment);
		System.out.println("Undo successful!");
	}
	
}
