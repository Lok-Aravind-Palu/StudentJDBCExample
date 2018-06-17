package JDBCSample.com.practice.jdbcexample;

public class StudentInfo {

	private int Id;
	
	private String studentName;
	
	private String lastName;
	
	private int marks;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "StudentInfo [Id=" + Id + ", studentName=" + studentName + ", lastName=" + lastName + ", marks=" + marks
				+ "]";
	}
	
	
}
