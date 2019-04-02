package net.example.connection;

import net.example.common.ConnectionBean;
import net.example.common.XMLParsingConfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMaker {
  
    public static Connection getConnection() {
        XMLParsingConfig parConfig = new XMLParsingConfig();      
        Connection conn = null;

        try {
            ConnectionBean mysqlConfig1 = parConfig.setConfig("mysql_conn.xml");

            String connectionStr = String.format("%s%s", mysqlConfig1.getHost(), mysqlConfig1.getDatabaseName());

            Class.forName(mysqlConfig1.getClassName());
            
			conn = DriverManager.getConnection(connectionStr, mysqlConfig1.getUserName(), mysqlConfig1.getUserPass());
        } catch(Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

}