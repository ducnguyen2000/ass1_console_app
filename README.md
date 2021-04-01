# Test Case:
- Consider that the enrolment are existed already and user input the same studentId, courseId and semester in.

# Design patterns used:
- Builder: build Student and Course objects
- Iterator: create tje class StudentList and CourseList
- Dependency Injection: inject contructor and setters of Student and Course into StudentEnrolment
- Facade: implement getStudentId() from Student and getCourseId() from Course into StudentEnrolment
- Singleton: create 1 instance of HistoryStudentEnrolmentManager class
- Command: + use command to execute Create, Read, Update, Delete StudentEnrolment objects or undo it.
	   + use commandPrint to execute Print StudentEnrolment, Student, Course, Semester.
- Visitor: check existed information from a list (CheckExistence Visitor) of objects of 3 different classes Student, Course, StudentEnrolment