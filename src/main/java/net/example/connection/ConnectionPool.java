package net.example.connection;

import java.util.*;
import java.sql.*;
import javax.sql.*;

public class ConnectionPool {
    // 사용 수량
    private int useCount;

    // 사용하지 않고 풀에 저장되어 있는 Connection
    private List<Connection> freeConnectionList = new ArrayList<Connection>();

    // 최대 카운트
    private int maxCount;

    // 초기 갯수
    private int initCount;

    public ConnectionPool(int maxCount, int initCount) {
        this.useCount = 0;
        this.maxCount = maxCount;
        this.initCount = initCount;

        if(initCount > 0) {
            for( int i = 0; i < initCount; i++ ) {
                freeConnectionList.add(ConnectionMaker.getConnection());
            }
        }
    }

    // Connnection 반납
    public synchronized void freeConnection(Connection conn) {
        if( useCount > 0 ) {
            freeConnectionList.add(conn);
            useCount--;
            notifyAll();
        }
    }

    // Pool에서 커넥션 가져옴
    public synchronized Connection getConnection() {
        Connection conn = null;

        if( freeConnectionList.size() > 0 ) {
            conn = freeConnectionList.get(0);

            freeConnectionList.remove(0);

            try {
                if( conn.isClosed() ) {
                    conn = ConnectionMaker.getConnection();
                }
            } catch(SQLException e) {
                conn = ConnectionMaker.getConnection();
            }
        } else {
            conn = ConnectionMaker.getConnection();
        }
        
        return conn;
    }
}