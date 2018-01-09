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

public class Util {

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
	
	public static void printNames(@SuppressWarnings("rawtypes") List list) {
		for (Object o : list) {
			System.out.println(o.toString());
		}
	}
	
}
