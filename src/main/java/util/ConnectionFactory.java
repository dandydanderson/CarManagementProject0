package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionFactory {
	
	//private static Logger log = Logger.getRootLogger();

	private static String url;
	private static String username;
	private static String password;
	
	private static ConnectionFactory cf;
	
	public static Connection getConnection(boolean actual) {
		if(actual) {//if true is put into the connection, then use the actual db
		if(cf == null) {
			cf = new ConnectionFactory(true);
		}
		
		}
		else {//if false is input when connection made, it's the test db
			if(cf == null) {
				cf = new ConnectionFactory(false);
			}
			
		}
		return cf.createConnection();
	}
	
	
	private ConnectionFactory(boolean actual) {//	private static String url = "jdbc:postgresql://localhost:5432/CarLot";

		if(actual) {
		
		url = "jdbc:postgresql://"+System.getenv("carLoturl")+":5432/"+System.getenv("CarLotDBName");
		username = System.getenv("CarLotUsername");
		password = System.getenv("CarLotPassword");
		}
		else {

			url = "jdbc:postgresql://localhost:5432/CarLotTest";
			username = System.getenv("CarLotUsername");
			password = System.getenv("CarLotPassword");
		}
			
		}
	
	

	private Connection createConnection() {
		Connection conn = null;
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("Could not load PostgreSQL Driver");
			e1.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Problem with creating DB connection");
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
