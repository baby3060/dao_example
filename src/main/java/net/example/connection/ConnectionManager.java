package net.example.connection;

import java.sql.*;

public class ConnectionManager {
    private ConnectionPoolManager connMgr;

    public ConnectionManager() {
        connMgr = ConnectionPoolManager.getInstance();
        connMgr.init(10);
    }
    
    public Connection getConnection() {
        return (connMgr.getConnection());
    }
    
    public void freeConnection(Connection conn) {
        connMgr.freeConnection(conn);
    }
}