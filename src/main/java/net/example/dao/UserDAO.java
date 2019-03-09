package net.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.example.model.*;
import net.example.common.ConnectionBean;

public class UserDAO {
    
    private ConnectionBean config;

    public UserDAO(ConnectionBean config) {
        this.config = config;
    }

    // 엉망진창 소스
    public int addUser(User user) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Insert Into TBUSER (user_id, pass_wd, name, age) Values (?, ?, ?, ?)";
        
        String connectionStr = String.format("%s%s", this.config.getHost(), this.config.getDatabaseName());
		
		int result = 0;

		try {
            Class.forName(this.config.getClassName());
            
            conn = DriverManager.getConnection(connectionStr, this.config.getUserName(), this.config.getUserPass());

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			pstmt.setString(2, user.getPass_wd());
			pstmt.setString(3, user.getName());
			pstmt.setInt(4, user.getAge());
				
			result = pstmt.executeUpdate();
		} catch(SQLException sql_ex) {
			sql_ex.printStackTrace();
		} catch(ClassNotFoundException cnfe_out) {
            cnfe_out.printStackTrace();
        } finally {
			if( pstmt != null ) { try { pstmt.close(); } catch(Exception e) { } }
			if( conn != null ) { try { conn.close(); } catch(Exception e) { } }
		}

		return result;
    }
    

    public void deleteAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Delete From TBUSER ";
        
        String connectionStr = String.format("%s%s", this.config.getHost(), this.config.getDatabaseName());

		try {
			Class.forName(this.config.getClassName());
            
            conn = DriverManager.getConnection(connectionStr, this.config.getUserName(), this.config.getUserPass());

			pstmt = conn.prepareStatement(sql);
			int result = pstmt.executeUpdate();
		} catch(SQLException sql_ex) {
			sql_ex.printStackTrace();
		} catch(ClassNotFoundException cnfe_out) {
            cnfe_out.printStackTrace();
        } finally {
			if( pstmt != null ) { try { pstmt.close(); } catch(Exception e) { } }
			if( conn != null ) { try { conn.close(); } catch(Exception e) { } }
		}
	}

}