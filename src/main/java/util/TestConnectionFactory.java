package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class TestConnectionFactory {
	
	//private static Logger log = Logger.getRootLogger();

	private static String url;
	private static String username;
	private static String password;
	
	private static TestConnectionFactory cf;
	
	public static Connection getConnection() {
		
		if(cf == null) {
			cf = new TestConnectionFactory();
		}
		
		return cf.createConnection();
	}
	
	
	private TestConnectionFactory() {//	private static String url = "jdbc:postgresql://localhost:5432/CarLot";

		url = "jdbc:postgresql://localhost:5432:CarLotTest";
		username = System.getenv("CarLotUsername");
		password = System.getenv("CarLotPassword");
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
