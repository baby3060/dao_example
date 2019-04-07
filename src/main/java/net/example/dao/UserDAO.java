package net.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.example.model.*;
import net.example.connection.*;

public class UserDAO {
    
    public int addUser(User user) {
		
			ConnectionManager connMgr = new ConnectionManager();

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Insert Into TBUSER (user_id, pass_wd, name, age) Values (?, ?, ?, ?)";
        
		int result = 0;

		try {
      conn = connMgr.getConnection();

			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			pstmt.setString(2, user.getPass_wd());
			pstmt.setString(3, user.getName());
			pstmt.setInt(4, user.getAge());
				
			result = pstmt.executeUpdate();

		} catch(SQLException sql_ex) {
			sql_ex.printStackTrace();
			result = -1;
		} catch(Exception e) {
			e.printStackTrace();
			result = -1;
		} finally {
			if( result > 0 ) {
				try {
					conn.commit();
				} catch(Exception e) { }
			} else {
				try {
					conn.rollback();
				} catch(Exception e) { }
			}
			if( pstmt != null ) { try { pstmt.close(); } catch(Exception e) { } }
			connMgr.freeConnection(conn);
		}

		return result;
    }
    
    public int deleteAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Delete From TBUSER ";
        
		int result = 0;
		try {
			conn = ConnectionMaker.getConnection();

			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch(SQLException sql_ex) {
			sql_ex.printStackTrace();
			result = -1;
		} catch(Exception e) {
			e.printStackTrace();
			result = -1;
		} finally {
			if( result > 0 ) {
				try {
					conn.commit();
				} catch(Exception e) { }
			} else {
				try {
					conn.rollback();
				} catch(Exception e) { }
			}

			if( pstmt != null ) { try { pstmt.close(); } catch(Exception e) { } }
			if( conn != null ) { try { conn.close(); } catch(Exception e) { } }
		}
		return result;
	}

	/**
	 * Insert 후 Update
	 * @param user
	 */
	public void mixAddAndUpdate(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "Insert Into TBUSER (user_id, pass_wd, name, age) Values (?, ?, ?, ?)";

		String updateSql = "Update TBUSER Set age = 55 Where user_id = ? ";

		try {

			conn = ConnectionMaker.getConnection();

			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, user.getUser_id());
			pstmt.setString(2, user.getPass_wd());
			pstmt.setString(3, user.getName());
			pstmt.setInt(4, user.getAge());
				
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(updateSql);

			pstmt.setString(1, user.getUser_id());

			pstmt.executeUpdate();

			try {
				conn.commit();
			} catch(Exception coE) {

			}
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch(Exception innerE) {

			}
			e.printStackTrace();
		} finally {
			if( pstmt != null ) { try { pstmt.close(); } catch(Exception e) { } }
			if( conn != null ) { try { conn.close(); } catch(Exception e) { } }
		}
	}

	/**
	 * 서비스 메소드에서 사용하는게 좋은 메소드 둘
	 * mixAddAndUpdate를 둘로 나눔
	 * conn.setAutoCommit(false); 을 서비스 메소드에서 사용
	 * Connection 메소드를 계속해서 넘김(서비스 메소드에서 생성 후)
	 * 서비스 메소드에서 Connection 닫아줘야함.
	 * @param conn
	 * @param user
	 */
	public void addUser(Connection conn, User user) throws Exception {
		PreparedStatement pstmt = null;
		String sql = "Insert Into TBUSER (user_id, pass_wd, name, age) Values (?, ?, ?, ?)";
        
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			pstmt.setString(2, user.getPass_wd());
			pstmt.setString(3, user.getName());
			pstmt.setInt(4, user.getAge());
				
			pstmt.executeUpdate();
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			if( pstmt != null ) { try { pstmt.close(); } catch(Exception e) { } }
		}
	}

	public void updateUser(Connection conn, User user) throws Exception {
		PreparedStatement pstmt = null;
		String sql = "Update TBUSER Set age = 75 Where user_id = ? ";
        
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			if( pstmt != null ) { try { pstmt.close(); } catch(Exception e) { } }
		}
	}


}