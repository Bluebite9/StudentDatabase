package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DBUtil {

	private final static String url = "jdbc:mysql://localhost:3306/students?autoReconnect=true&useSSL=false";
	private final static String user = "root";
	private final static String password = "123456";

	public static Connection openConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		Connection conn = DriverManager.getConnection(url, user, password);

		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeStatement(Statement stm) {
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeAll(Connection conn, PreparedStatement pstm, ResultSet rs) {
		closeAll(conn, pstm);
		closeResultSet(rs);
	}

	public static void closeAll(Connection conn, PreparedStatement pstm) {
		closeConnection(conn);
		closeStatement(pstm);
	}

	public static ResultSet getAllFromTable(String table, Connection conn, PreparedStatement ps) throws SQLException {
		String query = "select * from " + table;
		ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		return rs;
	}

	public static JTable buildTableFromResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();

		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new JTable(new DefaultTableModel(data, columnNames));
	}
	
	public static int countResultSetRows(ResultSet rs) throws SQLException {
		int rows = 0;
		while(rs.next()) {
			rows++;
		}
		
		rs.beforeFirst();
		return rows;
	}
	
	public static void printNames(@SuppressWarnings("rawtypes") List list) {
		for (Object o : list) {
			System.out.println(o.toString());
		}
	}
	
}
