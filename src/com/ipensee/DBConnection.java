package com.ipensee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class DBConnection {
	private static Connection conn = null;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (conn == null) {
			HashMap<String, String> config = ConfigXML.getConfig("config/config-db.xml");
	
			String host = (String) config.get("host");
			String port = (String) config.get("port");
			String user = (String) config.get("user");
			String password = (String) config.get("password");
			String database = (String) config.get("database");
	
			Class.forName("com.mysql.jdbc.Driver");
	
			String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url, user, password);
		}
		
		return conn;
	}
}
