package Enrolment;
import java.util.List;


public interface StudentEnrolmentManager {
	public void createStudentEnrolment(StudentEnrolment newEnrolment);
	public void updateStudentEnrolment(StudentEnrolment needUpdatedInfo, StudentEnrolment updateInfo);
	public void deleteStudentEnrolment(StudentEnrolment enrolment);
	public List<StudentEnrolment> getAllEnrolments();
	

}
