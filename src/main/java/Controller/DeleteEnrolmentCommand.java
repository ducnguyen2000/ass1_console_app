package Controller;

import Enrolment.*;

public class DeleteEnrolmentCommand implements Command{
	StudentEnrolment needDeletedInfo;
	HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
	
	public DeleteEnrolmentCommand(StudentEnrolment enrolment) {
		this.needDeletedInfo = enrolment;
	}
	
	@Override
	public void execute() {
		manager.deleteStudentEnrolment(needDeletedInfo);
	}
	
	@Override
	public void undo() {
		manager.createStudentEnrolment(needDeletedInfo);
		System.out.println("Undo successful!");
	}
}
