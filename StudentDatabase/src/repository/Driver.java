package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class Driver {

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
		this.setConn(DBUtil.openConnection());
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
