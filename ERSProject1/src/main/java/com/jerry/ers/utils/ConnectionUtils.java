package com.jerry.ers.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class ConnectionUtils {
	private static Logger LOGGER = Logger.getLogger(ConnectionUtils.class);
	private Connection con;
	private static ConnectionUtils connectionUtils;

	public Connection getConnection() {
		return con;
	}

	private ConnectionUtils() throws SQLException {
		
		String url = System.getenv("DB_URL");
		String dbName = System.getenv("DB_NAMEFP");
		String user = System.getenv("DB_USER");
		String pwd = System.getenv("DB_PASS");
		try {
			LOGGER.info("ConnectionUtils - using DB creds for connection: URL="+ url+""+dbName + 
					", Username=" + System.getenv("DB_USER") + 
					", Password=" + System.getenv("DB_PASS"));
			Class.forName("org.postgresql.Driver");

			this.con = DriverManager.getConnection(url + dbName, user, pwd);

		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block
			LOGGER.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static ConnectionUtils getInstance() throws SQLException {
		if (connectionUtils == null) {
			connectionUtils = new ConnectionUtils();
		} else if (connectionUtils.getConnection().isClosed()) {
			connectionUtils = new ConnectionUtils();
		}
		return connectionUtils;

	}
}
