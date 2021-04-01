# Instructions:
```
Press the following number to execute the program:
----------------------
CRUD Student Enrolment
1. Create an enrolment				
2. Update an enrolment 
3. Delete an enrolment
4. Show all students 
5. Show all courses
----------------------
Export data to CSV file (See in CSV folder)
6. Print all enrolments and export
7. Print enrolments and export for student
8. Print enrolments and export for course
9. Print enrolments and export for semester
----------------------
0. Quit

Notice: "y" stands for Yes, "n" stands for No
```

# Test Case:
```
- Consider that the enrolment are existed already and user input the same studentId, courseId and semester in.
```

# Design patterns used:
```
- Builder: build Student and Course objects
- Iterator: create tje class StudentList and CourseList
- Dependency Injection: inject contructor and setters of Student and Course into StudentEnrolment
- Facade: implement getStudentId() from Student and getCourseId() from Course into StudentEnrolment
- Singleton: create 1 instance of HistoryStudentEnrolmentManager class
- Command: + use command to execute Create, Read, Update, Delete StudentEnrolment objects or undo it
	   + use commandPrint to execute Print StudentEnrolment, Student, Course, Semester.
- Visitor: check existed information from a list (CheckExistence Visitor) of objects of 3 different classes Student, Course, StudentEnrolment
```