import java.sql.*;

public class Driver {
	
	private final static String url = "jdbc:mysql://localhost:3306/students";
	private final static String user = "root";
	private final static String password = "123456";

	public static void main(String[] args) {
		
		ResultSet myRs = findAll("student");

		try {
			while(myRs.next()) {
				System.out.println(myRs.getString("student_name") + " " + myRs.getString("student_surname"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static ResultSet findAll(String table) {
		ResultSet myRs = null;
		try {
			Connection myConn = DriverManager.getConnection(url, user, password);
			Statement myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from " + table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myRs;
	}
	
}
