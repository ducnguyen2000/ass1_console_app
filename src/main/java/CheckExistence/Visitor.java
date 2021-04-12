package CheckExistence;

import List.*;
import Enrolment.*;
import java.util.*;

public interface Visitor {
	public Pair visit(StudentList studentList, String studentId);
	public Pair visit(CourseList courseList, String courseId);
	public Pair visit(List<StudentEnrolment> studentEnrolmentList, StudentEnrolment toBeCompared);
}
