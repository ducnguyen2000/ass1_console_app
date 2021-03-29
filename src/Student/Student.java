package Student;
import java.util.*;

public class Student {
	private String id;
	private String name;
	private Calendar birthdate;
//  duc.birthday = new GregorianCalendar(2000, 11, 14);
	
	public Student(String id, String name, Calendar birthdate) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Calendar getBirthdate() {
		return birthdate;
	}
	
}
