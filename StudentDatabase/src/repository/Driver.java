package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Driver {

	private final static String url = "jdbc:mysql://localhost:3306/students?autoReconnect=true&useSSL=false";
	private final static String user = "root";
	private final static String password = "123456";

	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public PreparedStatement getPstm() {
		return pstm;
	}

	public void setPstm(PreparedStatement pstm) {
		this.pstm = pstm;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public void openConnection() throws SQLException {
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

		this.setConn(conn);
	}

	public void closeConnection() {
		if (this.getConn() != null) {
			try {
				this.getConn().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closePreparedStatement() {
		if (this.getPstm() != null) {
			try {
				this.getPstm().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeResultSet() {
		if (this.getRs() != null) {
			try {
				this.getRs().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeAll() {
		closeConnection();
		closePreparedStatement();
		closeResultSet();
	}
}
