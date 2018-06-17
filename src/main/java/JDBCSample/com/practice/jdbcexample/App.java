package JDBCSample.com.practice.jdbcexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

/**
 * Hello world!
 *
 */
public class App {
	static Connection conn;

	public static void main(String[] args) throws SQLException {
		System.out.println("Hello World!");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
			System.out.println("Connection established to Data Base...");
			App a = new App();
			while (true) {
				Scanner s = new Scanner(System.in);

				System.out.println(
						"Enter The option you like:\n 1.create table \n 2.insert values to table \n 3.displayvalues \n 4.updatefirstname \n 5.updatelastname \n 6.delete ");

				int value = s.nextInt();
				switch (value) {
				case 1:

					a.creatStudentTable();

					break;
				case 2:
					a.insertValues(3, "aravind", "paluvadi", 50);
					a.insertValues(1, "Ragu", "paluvadi", 100);
					a.insertValues(2, "Hema", "paluvadi", 100);
					a.insertValues(4, "mounika", "paluvadi", 70);

				case 3:
					a.displayValues(7);
					break;
				case 4:
					a.updatefirstName();
					break;
				case 5:
					a.updatelastName();
					break;
				case 6:
					a.delete();
					break;
				default:
					System.out.println("unknown selection please try again");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void creatStudentTable() throws Exception {
		
		
		
		try{
			String query = "select * from StudentInfo";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			ps.executeQuery();
			System.out.println("Table already Exist ");
		}catch(Exception e){
			//e.printStackTrace();
			if (e instanceof MySQLSyntaxErrorException) {
				//System.out.println("Table already exist");
				try {

					String query = "create table StudentInfo(ID int not null, studentName varchar(50), lastName varchar(50), marks int not null, primary key(ID))";

					PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

					ps.execute();

					System.out.println("Created Table");
					ps.close();

				} catch (Exception e1) {
					throw e1;
					/*if (e1 instanceof MySQLSyntaxErrorException) {
						System.out.println("Table already exist");
					} else {
						throw e1;
					}*/

				}
			} else {
				throw e;
			}
			
		}
		
		
	}

	public void insertValues(int ID, String sname, String lname, int marks) throws Exception {
		try {
			String query = "insert into StudentInfo values(?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setInt(1, ID);
			ps.setString(2, sname);
			ps.setString(3, lname);
			ps.setInt(4, marks);
			ps.execute();
			System.out.println("Inserted Values");
		} catch (Exception e) {
			throw e;
		}
	}

	public List<StudentInfo> displayValues(int ID) throws Exception {
		List<StudentInfo> infoList = new ArrayList<StudentInfo>();
		try {
			String query = "select * from StudentInfo where Id=?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String fname = rs.getString(2);
				String lname = rs.getString(3);
				int marks = rs.getInt(4);
				
				StudentInfo info = new StudentInfo();
				info.setId(id);
				info.setStudentName(fname);;
				info.setLastName(lname);;
				info.setMarks(marks);
				infoList.add(info);
				System.out.println("ID:" + id + "\tfname:" + fname + "\tlname:" + lname + "\tmarks:" + marks);
			}
			ps.close();
		} catch (Exception e) {
			throw e;
		}
		return infoList;
	}

	public void updatefirstName() throws Exception {
		try {
			String query = "UPDATE studentInfo " + "SET studentName = ? " + "WHERE ID = ?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setString(1, "lokaravind");
			ps.setInt(2, 7);
			ps.executeUpdate();
			ps.close();
			System.out.println("First name in the table is updated");
		} catch (Exception e) {
			throw e;
		}
	}

	public void updatelastName() throws Exception {
		try {
			String query = "UPDATE studentInfo " + "SET lastName = ? " + "WHERE ID = ?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setString(1, "palu");
			ps.setInt(2, 2);
			ps.executeUpdate();
			ps.close();
			System.out.println("The last name in the table is updated");
		} catch (Exception e) {
			throw e;
		}
	}

	public void delete() throws Exception {
		try {
			String query = "delete from studentInfo where id=?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setInt(1, 8);
			ps.execute();
			System.out.println("Your selected option is deleted");
		} catch (Exception e) {
			throw e;
		}
	}

}