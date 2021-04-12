import CheckExistence.CheckExistVisitor;
import CheckExistence.Pair;
import Controller.CreateEnrolmentCommand;
import Controller.DeleteEnrolmentCommand;
import Controller.HistoryStudentEnrolmentManager;
import Controller.UpdateEnrolmentCommand;
import Course.*;
import MainApp.Utils;
import Student.*;
import Enrolment.StudentEnrolment;
import List.CourseList;
import List.StudentList;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class Testing {
    private static StudentList studentList;
    private static CourseList courseList;
    private static StudentEnrolment se;
    private List<StudentEnrolment> studentEnrolmentList = new ArrayList<>();
    Utils utilities = new Utils();
    HistoryStudentEnrolmentManager manager = HistoryStudentEnrolmentManager.getInstance();
    CheckExistVisitor checkExistence = new CheckExistVisitor();

    @BeforeEach
    public void setUp() {
        studentList = new StudentList();
        courseList = new CourseList();
        se = new StudentEnrolment();
        studentEnrolmentList = new ArrayList<>();

    }

    @Test
    public void TestEnrolmentStudentIdAddNew() {
        StudentBuilder stdBuilder = new StudentBuilder();
        Student std = stdBuilder.addId("S3777226").addName("Nguyen Danh Duc").addBirthdate("14/11/2000").buildStudent();

        CourseBuilder crsBuilder = new CourseBuilder();
        Course crs = crsBuilder.addId("COSC3066").addName("SADI").addNumberOfCredits(5).buildCourse();

        se = new StudentEnrolment(std, crs, "2020C");
        assertEquals("S3777226", se.getStudentId());
    }
    @Test
    public void TestEnrolmentStudentNameAddNew() {
        StudentBuilder stdBuilder = new StudentBuilder();
        Student std = stdBuilder.addId("S3777226").addName("Nguyen Danh Duc").addBirthdate("14/11/2000").buildStudent();

        CourseBuilder crsBuilder = new CourseBuilder();
        Course crs = crsBuilder.addId("COSC3066").addName("SADI").addNumberOfCredits(5).buildCourse();

        se = new StudentEnrolment(std, crs, "2020C");
        assertEquals("Nguyen Danh Duc", se.getStudent().getName());
    }
    @Test
    public void TestEnrolmentCourseIDAddNew() {
        StudentBuilder stdBuilder = new StudentBuilder();
        Student std = stdBuilder.addId("S3777226").addName("Nguyen Danh Duc").addBirthdate("14/11/2000").buildStudent();

        CourseBuilder crsBuilder = new CourseBuilder();
        Course crs = crsBuilder.addId("COSC3066").addName("SADI").addNumberOfCredits(5).buildCourse();

        se = new StudentEnrolment(std, crs, "2020C");
        CreateEnrolmentCommand addCommand = new CreateEnrolmentCommand(se);
        addCommand.execute();
        assertEquals("COSC3066", se.getCourseId());
    }
    @Test
    public void TestEnrolmentCourseNameAddNew() {
        StudentBuilder stdBuilder = new StudentBuilder();
        Student std = stdBuilder.addId("S3777226").addName("Nguyen Danh Duc").addBirthdate("14/11/2000").buildStudent();

        CourseBuilder crsBuilder = new CourseBuilder();
        Course crs = crsBuilder.addId("COSC3066").addName("SADI").addNumberOfCredits(5).buildCourse();

        se = new StudentEnrolment(std, crs, "2020C");
        assertEquals("SADI", se.getCourse().getName());
    }
    @Test
    public void TestEnrolmentUpdateNew() {
        StudentBuilder stdBuilder = new StudentBuilder();
        Student std = stdBuilder.addId("S3777226").addName("Nguyen Danh Duc").addBirthdate("14/11/2000").buildStudent();
        Student std2 = stdBuilder.addId("S3771113").addName("Quang dz").addBirthdate("14/11/2000").buildStudent();

        CourseBuilder crsBuilder = new CourseBuilder();
        Course crs = crsBuilder.addId("COSC3066").addName("SADI").addNumberOfCredits(5).buildCourse();

        se = new StudentEnrolment(std, crs, "2020C");
        studentEnrolmentList.add(se);

        StudentEnrolment toBeUpdated = new StudentEnrolment(std2, crs, "2021C");
        manager.setToBeCompared(toBeUpdated);

        UpdateEnrolmentCommand update = new UpdateEnrolmentCommand(toBeUpdated, 0);
        update.execute();
        assertEquals("Quang dz", se.getStudent().getName());
    }

    @Test
    public void TestEnrolmentDelete() {
        StudentBuilder stdBuilder = new StudentBuilder();
        Student std = stdBuilder.addId("S3777226").addName("Nguyen Danh Duc").addBirthdate("14/11/2000").buildStudent();

        CourseBuilder crsBuilder = new CourseBuilder();
        Course crs = crsBuilder.addId("COSC3066").addName("SADI").addNumberOfCredits(5).buildCourse();

        se = new StudentEnrolment(std, crs, "2020C");
        boolean existedEnrolment1 = utilities.checkEmptyEnrolmentList();

        StudentEnrolment toBeDeleted = new StudentEnrolment(std, crs, "2020C");

        manager.setToBeCompared(toBeDeleted);
        boolean exist = (Boolean) manager.invite(checkExistence).isExisted;

        // Command Pattern
        DeleteEnrolmentCommand remove = new DeleteEnrolmentCommand(toBeDeleted);
        remove.execute();

        assertEquals("S3777226", se.getStudentId());
    }


}