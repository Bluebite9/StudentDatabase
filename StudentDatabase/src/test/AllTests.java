package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class AllTests {
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.openConnection();
			
			DepartmentTests.allTests();
			DomainTests.allTests();
			FacultyTests.allTests();
			GroupTests.allTests();
			MarkTests.allTests();
			ProfessorTests.allTests();
			SpecializationTests.allTests();
			StudentTests.allTests();
			SubjectTests.allTests();
			
			DBUtil.closeAll(conn, pstm, rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
