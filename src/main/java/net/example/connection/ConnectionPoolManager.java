package net.example.connection;

import java.sql.*;

public class ConnectionPoolManager {
    private static ConnectionPoolManager connPoolManager = null;

    private ConnectionPool connPool;

    private ConnectionPoolManager() { }

    public static synchronized ConnectionPoolManager getInstance() {
        if(connPoolManager == null) {
            connPoolManager = new ConnectionPoolManager();
        }

        return connPoolManager;
    }

    public void init(int initCount) {
        connPool = new ConnectionPool(initCount);
    }

    public Connection getConnection() {
        return connPool.getConnection();
    }

    public void freeConnection(Connection conn) {
        connPool.freeConnection(conn);
    }
}